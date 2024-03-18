package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.db.AquaGameOptions
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import icu.samnyan.aqua.net.db.AquaUserServices
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

@RestController
@API("/api/v2/settings")
class SettingsApi(
    val us: AquaUserServices,
    val userRepo: AquaNetUserRepo
) {
    // Get all params with SettingField annotation
    // Kotlin by default put all the @Annotations in the constructor param
    val params = AquaGameOptions::class.primaryConstructor!!.parameters
        .mapNotNull { it.findAnnotation<SettingField>()?.let { an -> it to an } }
    val fields = AquaGameOptions::class.vars().associateBy { it.name }
    val fieldMap = params.associate { (key, _) -> key.name to fields[key.name]!! }
    val fieldDesc = params.map { (key, an) -> mapOf(
        "key" to key.name,
        "name" to an.name,
        "desc" to an.desc
    ) }

    @API("get")
    @Doc("Get the game options of the logged in user")
    fun getSettings(@RP token: String) = us.jwt.auth(token) { u ->
        val go = u.gameOptions ?: AquaGameOptions()
        fieldDesc.map { it + ("value" to fieldMap[it["key"]]!!.get(go)) }
    }

    @API("set")
    @Doc("Set a field in the game options")
    fun setField(@RP token: String, @RP key: String, value: String) = us.jwt.auth(token) { u ->
        val field = fieldMap[key] ?: error("Field not found")
        val options = u.gameOptions ?: AquaGameOptions().also {
            userRepo.save(u.apply { gameOptions = it })
        }
        // Check field type
        val type = field.returnType
        val newValue = when {
            type == String::class -> value
            type == Int::class -> value.toInt()
            type == Boolean::class -> value.toBoolean()
            else -> error("Unsupported type")
        }
        field.set(options, newValue)
    }
}