package org.bouncycastle.mime;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface MimeMultipartContext extends MimeContext {
    MimeContext createContext(int i) throws IOException;
}
