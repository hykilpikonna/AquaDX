package icu.samnyan.aqua.net.games

import ext.jackson
import ext.splitLines
import java.lang.reflect.Field
import kotlin.reflect.KClass

// Import class with renaming
data class ImportClass<T : Any>(
    val type: KClass<T>,
    val renames: Map<String, String?>? = null,
    val name: String = type.simpleName!!.lowercase()
)

abstract class ImportController<T: Any>(
    val exportFields: Map<String, Field>,
    val renameTable: Map<String, ImportClass<*>>
) {
    abstract fun createEmpty(): T

    init {
        renameTable.values.forEach {
            if (it.name !in exportFields) error("Code error! Export fields incomplete")
        }
    }

    /**
     * Read an artemis SQL dump file and return Aqua JSON
     */
    @Suppress("UNCHECKED_CAST")
    fun importArtemisSql(sql: String): ImportResult {
        val data = createEmpty()
        val errors = ArrayList<String>()
        val warnings = ArrayList<String>()
        fun err(msg: String) { errors.add(msg) }
        fun warn(msg: String) { warnings.add(msg) }

        val lists = exportFields.filter { it.value.type == List::class.java }
            .mapValues { it.value.get(data) as ArrayList<Any> }

        val statements = sql.splitLines().mapNotNull {
            try { it.asSqlInsert() }
            catch (e: Exception) { err("Failed to parse insert: $it\n${e.message}"); null }
        }

        // For each insert statement, we will try to parse the values
        statements.forEachIndexed fi@{ i, insert ->
            // Try to map tables
            val tb = renameTable[insert.table] ?: return@fi warn("Unknown table ${insert.table} in insert $i")
            val field = exportFields[tb.name]!!
            val obj = tb.mapTo(insert.mapping)

            // Add value to list or set field
            lists[tb.name]?.add(obj) ?: field.set(data, obj)
        }

        return ImportResult(errors, warnings, jackson.writeValueAsString(data))
    }

    companion object
    {
        // Map a dictionary to a class
        fun <T : Any> ImportClass<T>.mapTo(rawDict: Map<String, String>): T {
            // Process renaming
            var dict = renames?.let { rawDict
                .filter { (k, _) -> if (k in it) it[k] != null else true }
                .mapKeys { (k, _) -> it[k] ?: k } } ?: rawDict

            // Process Nones
            dict = dict.filterValues { it != "None" }

            return jackson.convertValue(dict, type.java)
        }
    }
}

// Read SQL dump and convert to dictionary
val insertPattern = """INSERT INTO\s+(\w+)\s*\(([^)]+)\)\s*VALUES\s*\(([^)]+)\);""".toRegex()
data class SqlInsert(val table: String, val mapping: Map<String, String>)
fun String.asSqlInsert(): SqlInsert {
    val match = insertPattern.matchEntire(this) ?: error("Does not match insert pattern")
    val (table, rawCols, rawVals) = match.destructured
    val cols = rawCols.split(',').map { it.trim(' ', '"') }

    // Parse values with proper quote handling
    val vals = mutableListOf<String>()
    var startI = 0
    var insideQuote = false
    rawVals.forEachIndexed { i, c ->
        if (c == ',' && !insideQuote) {
            vals.add(rawVals.substring(startI, i).trim(' ', '"'))
            startI = i + 1
        } else if (c == '"') insideQuote = !insideQuote
    }

    assert(cols.size == vals.size) { "Column and value count mismatch" }
    return SqlInsert(table, cols.zip(vals).toMap())
}
