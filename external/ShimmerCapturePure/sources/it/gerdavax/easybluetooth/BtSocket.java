package it.gerdavax.easybluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public interface BtSocket {
    void close() throws IOException;

    InputStream getInputStream() throws Exception;

    OutputStream getOutputStream() throws Exception;
}
