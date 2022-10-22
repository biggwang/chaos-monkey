package com.chaos.monkey.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestBodyWrapper extends HttpServletRequestWrapper {

    private byte[] bodyData;

    public HttpRequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.bodyData = IOUtils.toByteArray(super.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(bodyData);
        return new SimpleServletInputStream(bis);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bodyData)));
    }
}
