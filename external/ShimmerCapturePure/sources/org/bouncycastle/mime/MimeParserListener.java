package org.bouncycastle.mime;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public interface MimeParserListener {
    MimeContext createContext(MimeParserContext mimeParserContext, Headers headers);

    void object(MimeParserContext mimeParserContext, Headers headers, InputStream inputStream) throws IOException;
}
