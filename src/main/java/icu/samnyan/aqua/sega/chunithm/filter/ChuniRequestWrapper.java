package icu.samnyan.aqua.sega.chunithm.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class ChuniRequestWrapper extends HttpServletRequestWrapper {

    private ByteArrayInputStream input;
    private ServletInputStream filterInput;

    public ChuniRequestWrapper(HttpServletRequest request, byte[] input) {
        super(request);
        this.input = new ByteArrayInputStream(input);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
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
                public int read() throws IOException {
                    return input.read();
                }
            };


        }
        return filterInput;
    }
}
