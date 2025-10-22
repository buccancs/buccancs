package com.squareup.okhttp.internal.http;

import com.google.api.client.http.HttpMethods;

/* loaded from: classes2.dex */
public final class HttpMethod {
    private HttpMethod() {
    }

    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals(HttpMethods.PATCH) || str.equals(HttpMethods.PUT) || str.equals(HttpMethods.DELETE) || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals(HttpMethods.PUT) || str.equals(HttpMethods.PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals(HttpMethods.OPTIONS) || str.equals(HttpMethods.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }
}
