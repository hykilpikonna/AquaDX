package icu.samnyan.aqua.net

import ext.HTTP
import ext.async
import ext.toJson
import icu.samnyan.aqua.net.games.BaseEntity
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import jakarta.persistence.Entity
import kotlinx.serialization.Serializable
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.text.Normalizer

@Configuration
@ConfigurationProperties(prefix = "aqua-net.openai")
class OpenAIConfig {
    var apiKey: String = ""
}

@Entity
class AquaNetSafety : BaseEntity() {
    var content: String = ""
    var safe: Boolean = false
}

interface AquaNetSafetyRepo : JpaRepository<AquaNetSafety, Long> {
    fun findByContent(content: String): AquaNetSafety?
}

@Serializable
data class OpenAIResp<T>(
    val id: String,
    val model: String,
    val results: List<T>
)

@Serializable
data class OpenAIMod(
    val flagged: Boolean,
    val categories: Map<String, Boolean>,
    val categoryScores: Map<String, Double>,
)

@Service
class AquaNetSafetyService(
    val safety: AquaNetSafetyRepo,
    val openAIConfig: OpenAIConfig
) {
    suspend fun isSafe(rawContent: String): Boolean {
        // NFKC normalize
        val content = Normalizer.normalize(rawContent, Normalizer.Form.NFKC)
        if (content.isBlank()) return true

        async { safety.findByContent(content) }?.let { return it.safe }

        // Query OpenAI
        HTTP.post("https://api.openai.com/v1/moderations") {
            header("Authorization", "Bearer ${openAIConfig.apiKey}")
            header("Content-Type", "application/json")
            setBody(mapOf("input" to content).toJson())
        }.let {
            if (!it.status.isSuccess()) return true
            val body = it.body<OpenAIResp<OpenAIMod>>()
            return AquaNetSafety().apply {
                this.content = content
                this.safe = !body.results.first().flagged
            }.also { safety.save(it) }.safe
        }
    }
}
