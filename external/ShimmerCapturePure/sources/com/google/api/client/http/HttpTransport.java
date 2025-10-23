package com.google.api.client.http;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class HttpTransport {
    static final Logger LOGGER = Logger.getLogger(HttpTransport.class.getName());
    private static final String[] SUPPORTED_METHODS;

    static {
        String[] strArr = {HttpMethods.DELETE, HttpMethods.GET, "POST", HttpMethods.PUT};
        SUPPORTED_METHODS = strArr;
        Arrays.sort(strArr);
    }

    protected abstract LowLevelHttpRequest buildRequest(String str, String str2) throws IOException;

    public void shutdown() throws IOException {
    }

    public final HttpRequestFactory createRequestFactory() {
        return createRequestFactory(null);
    }

    public final HttpRequestFactory createRequestFactory(HttpRequestInitializer httpRequestInitializer) {
        return new HttpRequestFactory(this, httpRequestInitializer);
    }

    HttpRequest buildRequest() {
        return new HttpRequest(this, null);
    }

    public boolean supportsMethod(String str) throws IOException {
        return Arrays.binarySearch(SUPPORTED_METHODS, str) >= 0;
    }
}
