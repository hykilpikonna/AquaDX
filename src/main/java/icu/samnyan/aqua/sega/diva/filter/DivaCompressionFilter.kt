package icu.samnyan.aqua.sega.diva.filter

import icu.samnyan.aqua.sega.general.filter.CompressRequestWrapper
import icu.samnyan.aqua.sega.general.filter.CompressResponseWrapper
import icu.samnyan.aqua.sega.util.Compression
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.eclipse.jetty.io.EofException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
class DivaCompressionFilter : OncePerRequestFilter() {
    companion object {
        val log: Logger = LoggerFactory.getLogger(DivaCompressionFilter::class.java)
    }

    override fun doFilterInternal(req: HttpServletRequest, resp: HttpServletResponse, chain: FilterChain) {
        log.debug(">>> DIVA Incoming request: ${req.servletPath}")
        log.debug("> ${req.headerNames.toList().map { it to req.getHeader(it) }}")
        val encoding = req.getHeader("pragma")
        val reqSrc = req.inputStream.readAllBytes()

        log.debug("> Encoding: $encoding")

        var reqResult: ByteArray?
        if (encoding != null && encoding == "DFI") {
            log.debug("> Request length (compressed): ${reqSrc.size}")
            reqResult = Base64.getMimeDecoder().decode(reqSrc)
            reqResult = Compression.decompress(reqResult)
            log.debug("> Request length (decompressed): ${reqResult.size}")
        } else {
            reqResult = reqSrc
        }

        val requestWrapper = CompressRequestWrapper(req, reqResult)
        val responseWrapper = CompressResponseWrapper(resp)

        chain.doFilter(requestWrapper, responseWrapper)

        val respSrc = responseWrapper.toByteArray()
        log.debug(">>> DIVA Outgoing response: $respSrc")
        log.debug("> Response length (uncompressed): ${respSrc.size}")
        var respResult = Compression.compress(respSrc)
        log.debug("> Response length (compressed): ${respResult.size}")
        respResult = Base64.getMimeEncoder().encode(respResult)

        resp.setContentLength(respResult.size)
        resp.setHeader("pragma", "DFI")

        try {
            resp.outputStream.write(respResult)
        } catch (e: EofException) {
            log.warn("- EOF: Client closed connection when writing result :(")
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.startsWith("/g/diva")
    }
}
