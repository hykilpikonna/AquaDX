package icu.samnyan.aqua.sega.general.filter

import ext.logger
import icu.samnyan.aqua.sega.util.Compression
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.eclipse.jetty.io.EofException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
class CompressionFilter : OncePerRequestFilter() {
    companion object {
        val logger = logger()
    }

    override fun doFilterInternal(req: HttpServletRequest, resp: HttpServletResponse, chain: FilterChain) {
        val reqSrc = req.inputStream.readAllBytes().let {
            if (req.getHeader("content-encoding") == "deflate") Compression.decompress(it)
            else it
        }

        val requestWrapper = CompressRequestWrapper(req, reqSrc)
        val responseWrapper = CompressResponseWrapper(resp)

        chain.doFilter(requestWrapper, responseWrapper)

        val result = Compression.compress(responseWrapper.toByteArray())

        resp.setContentLength(result.size)
        resp.contentType = "application/json; charset=utf-8"
        resp.addHeader("Content-Encoding", "deflate")

        try {
            resp.outputStream.write(result)
        } catch (e: EofException) {
            logger.warn("- EOF: Client closed connection when writing result")
        }
    }

    /**
     * Filter games that are not diva
     */
    override fun shouldNotFilter(req: HttpServletRequest) =
        !(req.servletPath.startsWith("/g/") && !req.servletPath.startsWith("/g/diva")
            && !req.servletPath.startsWith("/g/wacca"))
}
