package icu.samnyan.aqua.sega.general.filter

import icu.samnyan.aqua.sega.util.Compression
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.eclipse.jetty.io.EofException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
class CompressionFilter : OncePerRequestFilter() {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CompressionFilter::class.java)
    }

    override fun doFilterInternal(req: HttpServletRequest, resp: HttpServletResponse, chain: FilterChain) {
        logger.debug("Do compress filter")
        val encoding = req.getHeader("content-encoding")
        val reqSrc = req.inputStream.readAllBytes()

        val reqResult: ByteArray
        if (encoding != null && encoding == "deflate") {
            logger.debug("Request length (compressed): ${reqSrc.size}")
            reqResult = Compression.decompress(reqSrc)
            logger.debug("Request length (decompressed): ${reqResult.size}")
        } else {
            reqResult = reqSrc
        }

        val requestWrapper = CompressRequestWrapper(req, reqResult)
        val responseWrapper = CompressResponseWrapper(resp)

        chain.doFilter(requestWrapper, responseWrapper)

        val respSrc = responseWrapper.toByteArray()
        logger.debug("Response length (uncompressed): ${respSrc.size}")
        val respResult = Compression.compress(respSrc)
        logger.debug("Response length (compressed): ${respResult.size}")

        resp.setContentLength(respResult.size)
        resp.contentType = "application/json; charset=utf-8"
        resp.addHeader("Content-Encoding", "deflate")

        try {
            resp.outputStream.write(respResult)
        } catch (e: EofException) {
            logger.warn("Client closed connection")
        }
    }

    /**
     * Filter games that are not diva
     */
    override fun shouldNotFilter(req: HttpServletRequest) =
        !(req.servletPath.startsWith("/g/") && !req.servletPath.startsWith("/g/diva"))
}
