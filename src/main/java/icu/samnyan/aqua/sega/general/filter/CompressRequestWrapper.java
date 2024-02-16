package icu.samnyan.aqua.sega.general.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class CompressRequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayInputStream input;
    private ServletInputStream filterInput;

    public CompressRequestWrapper(HttpServletRequest request, byte[] input) {
        super(request);
        this.input = new ByteArrayInputStream(input);
    }


    @Override
    public ServletInputStream getInputStream() {
        if (filterInput == null) {
            filterInput = new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() {
                    return input.read();
                }
            };


        }
        return filterInput;
    }
}
