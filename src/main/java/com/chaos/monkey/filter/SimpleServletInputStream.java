package com.chaos.monkey.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleServletInputStream extends ServletInputStream {

    private InputStream is;

    protected SimpleServletInputStream(InputStream is) {
        this.is = is;
    }

    @Override
    public int read() throws IOException {
        return this.is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.is.read(b);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }
}
