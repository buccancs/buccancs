package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
interface CMSReadable {
    InputStream getInputStream() throws CMSException, IOException;
}
