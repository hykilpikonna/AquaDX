package icu.samnyan.aqua.sega.chunithm.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class ChuniResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream output;
    private ServletOutputStream filterOutput;


    public ChuniResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {

                }

                @Override
                public void write(int b) throws IOException {
                    output.write(b);
                }
            };
        }
        return filterOutput;
    }

    public byte[] toByteArray() {
        return output.toByteArray();
    }
}
