package icu.samnyan.aqua.sega.general.filter;

import icu.samnyan.aqua.sega.util.Compression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class CompressionFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CompressionFilter.class);
    private final List<String> filterList;

    public CompressionFilter() {
        filterList = new ArrayList<>();
        filterList.add("/ChuniServlet");
        filterList.add("/OngekiServlet");
        filterList.add("/MaimaiServlet");
        filterList.add("/Maimai2Servlet");
        filterList.add("/ChusanServlet");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.debug("Do compress filter");
        String encoding = request.getHeader("content-encoding");
        byte[] reqSrc = request.getInputStream().readAllBytes();

        byte[] reqResult;
        if (encoding != null && encoding.equals("deflate")) {
            logger.debug("Request length (compressed): {}", reqSrc.length);
            reqResult = Compression.decompress(reqSrc);
            logger.debug("Request length (decompressed): {}", reqResult.length);
        } else {
            reqResult = reqSrc;
        }

        CompressRequestWrapper requestWrapper = new CompressRequestWrapper(request, reqResult);
        CompressResponseWrapper responseWrapper = new CompressResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        byte[] respSrc = responseWrapper.toByteArray();
        logger.debug("Response length (uncompressed): {}", respSrc.length);
        byte[] respResult = Compression.compress(respSrc);
        logger.debug("Response length (compressed): {}", respResult.length);


        response.setContentLength(respResult.length);
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Content-Encoding", "deflate");

        response.getOutputStream().write(respResult);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean notFilter = true;
        for (String prefix : filterList) {
            if (path.startsWith(prefix)) {
                notFilter = false;
                break;
            }
        }
        return notFilter;
    }
}
