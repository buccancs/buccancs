package org.apache.http;

import java.util.Locale;

/* loaded from: classes5.dex */
public interface HttpResponse extends HttpMessage {
    HttpEntity getEntity();

    void setEntity(HttpEntity httpEntity);

    Locale getLocale();

    void setLocale(Locale locale);

    StatusLine getStatusLine();

    void setStatusLine(StatusLine statusLine);

    void setReasonPhrase(String str) throws IllegalStateException;

    void setStatusCode(int i) throws IllegalStateException;

    void setStatusLine(ProtocolVersion protocolVersion, int i);

    void setStatusLine(ProtocolVersion protocolVersion, int i, String str);
}
