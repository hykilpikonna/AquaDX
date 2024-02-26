package icu.samnyan.aqua.sega.allnet

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * Handle token check for secure requests.
 *
 * When checkKeychip is enabled, the game URLs will be set to /gs/SS{token}/{game}/...
 * This interceptor will check if the token exists in the database.
 */
@Component
class TokenChecker(
    val keyChipRepo: KeyChipRepo
) : HandlerInterceptor {
    val log = LoggerFactory.getLogger(TokenChecker::class.java)

    /**
     * Handle request before it's processed.
     */
    override fun preHandle(req: HttpServletRequest, resp: HttpServletResponse, handler: Any): Boolean {
        val token = extractTokenFromPath(req.requestURI)
        log.debug("PreHandle: ${req.requestURI} from ip ${req.remoteAddr}, token: $token")

        // Check whether the token exists in the database
        // The token can either be a keychip id (old method) or a session id (new method)
        if (token != null && keyChipRepo.existsByKeychipId(token))
        {
            // Forward the request
            val w = RewriteWrapper(req)
            req.getRequestDispatcher(w.requestURI).forward(w, resp)

            // Prevent the request from being processed twice
            return false
        }

        // Token doesn't exist, reject the request
        resp.status = HttpServletResponse.SC_FORBIDDEN
        log.warn("Request rejected: ${req.requestURI} from ip ${req.remoteAddr}")
        return false
    }

    /**
     * Extract the token from the request path.
     * Example: "/gs/SS12033897/mai2/SomeEndpoint" -> "12033897"
     */
    fun extractTokenFromPath(path: String) = path.split("/").find { it.startsWith("SS") }?.substring(2)
}

val tokenRegex = Regex("/gs/SS.*?/")

/**
 * Request wrapper for rewriting the URI after token check.
 */
class RewriteWrapper(req: HttpServletRequest) : HttpServletRequestWrapper(req) {
    val newUri = req.requestURI.replace(tokenRegex, "/g/")
    val newUrl = req.requestURL.toString().replace(tokenRegex, "/g/")
    val newSp = req.servletPath.replace(tokenRegex, "/g/")

    init {
        println("RewriteWrapper: $newUri, $newUrl, $newSp")
    }

    override fun getRequestURI() = newUri
    override fun getRequestURL() = StringBuffer(newUrl)
    override fun getServletPath() = newSp
}

/**
 * Register the token checker interceptor
 */
@Configuration
@ConditionalOnProperty(
    value = ["allnet.server.check-keychip"],
    havingValue = "true",
    matchIfMissing = false
)
class AllNetSecureInit(
    val tokenChecker: TokenChecker,
) : WebMvcConfigurer {
    val log = LoggerFactory.getLogger(AllNetSecureInit::class.java)

    override fun addInterceptors(reg: InterceptorRegistry) {
        log.info("AllNet: Added token interceptor to secure requests.")
        reg.addInterceptor(tokenChecker).addPathPatterns("/gs/**")
    }
}
