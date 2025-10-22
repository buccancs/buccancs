package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
final class PemReader {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PemReader.class);
    private static final Pattern CERT_PATTERN = Pattern.compile("-+BEGIN\\s+.*CERTIFICATE[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*CERTIFICATE[^-]*-+", 2);
    private static final Pattern KEY_PATTERN = Pattern.compile("-+BEGIN\\s+.*PRIVATE\\s+KEY[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*PRIVATE\\s+KEY[^-]*-+", 2);

    private PemReader() {
    }

    static ByteBuf[] readCertificates(File file) throws IOException, CertificateException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return readCertificates(fileInputStream);
            } finally {
                safeClose(fileInputStream);
            }
        } catch (FileNotFoundException unused) {
            throw new CertificateException("could not find certificate file: " + file);
        }
    }

    static ByteBuf[] readCertificates(InputStream inputStream) throws CertificateException {
        try {
            String content = readContent(inputStream);
            ArrayList arrayList = new ArrayList();
            Matcher matcher = CERT_PATTERN.matcher(content);
            for (int iEnd = 0; matcher.find(iEnd); iEnd = matcher.end()) {
                ByteBuf byteBufCopiedBuffer = Unpooled.copiedBuffer(matcher.group(1), CharsetUtil.US_ASCII);
                ByteBuf byteBufDecode = Base64.decode(byteBufCopiedBuffer);
                byteBufCopiedBuffer.release();
                arrayList.add(byteBufDecode);
            }
            if (arrayList.isEmpty()) {
                throw new CertificateException("found no certificates in input stream");
            }
            return (ByteBuf[]) arrayList.toArray(new ByteBuf[0]);
        } catch (IOException e) {
            throw new CertificateException("failed to read certificate input stream", e);
        }
    }

    static ByteBuf readPrivateKey(File file) throws KeyException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return readPrivateKey(fileInputStream);
            } finally {
                safeClose(fileInputStream);
            }
        } catch (FileNotFoundException unused) {
            throw new KeyException("could not find key file: " + file);
        }
    }

    static ByteBuf readPrivateKey(InputStream inputStream) throws KeyException {
        try {
            Matcher matcher = KEY_PATTERN.matcher(readContent(inputStream));
            if (!matcher.find()) {
                throw new KeyException("could not find a PKCS #8 private key in input stream (see https://netty.io/wiki/sslcontextbuilder-and-private-key.html for more information)");
            }
            ByteBuf byteBufCopiedBuffer = Unpooled.copiedBuffer(matcher.group(1), CharsetUtil.US_ASCII);
            ByteBuf byteBufDecode = Base64.decode(byteBufCopiedBuffer);
            byteBufCopiedBuffer.release();
            return byteBufDecode;
        } catch (IOException e) {
            throw new KeyException("failed to read key input stream", e);
        }
    }

    private static String readContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStream.read(bArr);
                if (i >= 0) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toString(CharsetUtil.US_ASCII.name());
                }
            }
        } finally {
            safeClose(byteArrayOutputStream);
        }
    }

    private static void safeClose(InputStream inputStream) throws IOException {
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.warn("Failed to close a stream.", (Throwable) e);
        }
    }

    private static void safeClose(OutputStream outputStream) throws IOException {
        try {
            outputStream.close();
        } catch (IOException e) {
            logger.warn("Failed to close a stream.", (Throwable) e);
        }
    }
}
