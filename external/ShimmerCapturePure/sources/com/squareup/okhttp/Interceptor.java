package com.squareup.okhttp;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface Interceptor {

    Response intercept(Chain chain) throws IOException;

    public interface Chain {
        Connection connection();

        Response proceed(Request request) throws IOException;

        Request request();
    }
}
