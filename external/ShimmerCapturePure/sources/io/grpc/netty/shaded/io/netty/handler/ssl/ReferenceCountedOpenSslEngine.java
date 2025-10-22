package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig;
import io.grpc.netty.shaded.io.netty.internal.tcnative.Buffer;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetectorFactory;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;

/* loaded from: classes3.dex */
public class ReferenceCountedOpenSslEngine extends SSLEngine implements ReferenceCounted, ApplicationProtocolAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_PLAINTEXT_LENGTH = SSL.SSL_MAX_PLAINTEXT_LENGTH;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV2 = 0;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV3 = 1;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1 = 2;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_1 = 3;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_2 = 4;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_3 = 5;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslEngine.class);
    private static final ResourceLeakDetector<ReferenceCountedOpenSslEngine> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedOpenSslEngine.class);
    private static final int[] OPENSSL_OP_NO_PROTOCOLS = {SSL.SSL_OP_NO_SSLv2, SSL.SSL_OP_NO_SSLv3, SSL.SSL_OP_NO_TLSv1, SSL.SSL_OP_NO_TLSv1_1, SSL.SSL_OP_NO_TLSv1_2, SSL.SSL_OP_NO_TLSv1_3};
    private static final int MAX_RECORD_SIZE = SSL.SSL_MAX_RECORD_LENGTH;
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
    final ByteBufAllocator alloc;
    final boolean jdkCompatibilityMode;
    private final OpenSslApplicationProtocolNegotiator apn;
    private final boolean clientMode;
    private final boolean enableOcsp;
    private final OpenSslEngineMap engineMap;
    private final ResourceLeakTracker<ReferenceCountedOpenSslEngine> leak;
    private final ReferenceCountedOpenSslContext parentContext;
    private final AbstractReferenceCounted refCnt;
    private final OpenSslSession session;
    private final ByteBuffer[] singleDstBuffer;
    private final ByteBuffer[] singleSrcBuffer;
    private Object algorithmConstraints;
    private volatile String applicationProtocol;
    private volatile ClientAuth clientAuth;
    private volatile boolean destroyed;
    private String endPointIdentificationAlgorithm;
    private HandshakeState handshakeState;
    private boolean isInboundDone;
    private volatile long lastAccessed;
    private volatile Certificate[] localCertificateChain;
    private volatile Collection<?> matchers;
    private int maxWrapBufferSize;
    private int maxWrapOverhead;
    private volatile boolean needTask;
    private long networkBIO;
    private boolean outboundClosed;
    private Throwable pendingException;
    private boolean receivedShutdown;
    private List<String> sniHostNames;
    private long ssl;

    ReferenceCountedOpenSslEngine(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, ByteBufAllocator byteBufAllocator, String str, int i, boolean z, boolean z2) {
        super(str, i);
        this.handshakeState = HandshakeState.NOT_STARTED;
        this.refCnt = new AbstractReferenceCounted() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
            public ReferenceCounted touch(Object obj) {
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.record(obj);
                }
                return ReferenceCountedOpenSslEngine.this;
            }

            @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
            protected void deallocate() {
                ReferenceCountedOpenSslEngine.this.shutdown();
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.close(ReferenceCountedOpenSslEngine.this);
                }
                ReferenceCountedOpenSslEngine.this.parentContext.release();
            }
        };
        this.clientAuth = ClientAuth.NONE;
        this.lastAccessed = -1L;
        this.singleSrcBuffer = new ByteBuffer[1];
        this.singleDstBuffer = new ByteBuffer[1];
        OpenSsl.ensureAvailability();
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        this.apn = (OpenSslApplicationProtocolNegotiator) referenceCountedOpenSslContext.applicationProtocolNegotiator();
        boolean zIsClient = referenceCountedOpenSslContext.isClient();
        this.clientMode = zIsClient;
        if (PlatformDependent.javaVersion() >= 7) {
            this.session = new ExtendedOpenSslSession(new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext())) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.2
                private String[] peerSupportedSignatureAlgorithms;
                private List requestedServerNames;

                @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession, javax.net.ssl.ExtendedSSLSession
                public List getRequestedServerNames() {
                    List list;
                    if (ReferenceCountedOpenSslEngine.this.clientMode) {
                        return Java8SslUtils.getSniHostNames((List<String>) ReferenceCountedOpenSslEngine.this.sniHostNames);
                    }
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.requestedServerNames == null) {
                            if (ReferenceCountedOpenSslEngine.this.isDestroyed() || SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl) == null) {
                                this.requestedServerNames = Collections.emptyList();
                            } else {
                                this.requestedServerNames = Java8SslUtils.getSniHostName(SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl).getBytes(CharsetUtil.UTF_8));
                            }
                        }
                        list = this.requestedServerNames;
                    }
                    return list;
                }

                @Override // javax.net.ssl.ExtendedSSLSession
                public String[] getPeerSupportedSignatureAlgorithms() {
                    String[] strArr;
                    String[] sigAlgs;
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.peerSupportedSignatureAlgorithms == null) {
                            if (ReferenceCountedOpenSslEngine.this.isDestroyed() || (sigAlgs = SSL.getSigAlgs(ReferenceCountedOpenSslEngine.this.ssl)) == null) {
                                this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                            } else {
                                LinkedHashSet linkedHashSet = new LinkedHashSet(sigAlgs.length);
                                for (String str2 : sigAlgs) {
                                    String javaName = SignatureAlgorithmConverter.toJavaName(str2);
                                    if (javaName != null) {
                                        linkedHashSet.add(javaName);
                                    }
                                }
                                this.peerSupportedSignatureAlgorithms = (String[]) linkedHashSet.toArray(new String[0]);
                            }
                        }
                        strArr = (String[]) this.peerSupportedSignatureAlgorithms.clone();
                    }
                    return strArr;
                }

                @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession
                public List<byte[]> getStatusResponses() {
                    if (ReferenceCountedOpenSslEngine.this.enableOcsp && ReferenceCountedOpenSslEngine.this.clientMode) {
                        synchronized (ReferenceCountedOpenSslEngine.this) {
                            ocspResponse = ReferenceCountedOpenSslEngine.this.isDestroyed() ? null : SSL.getOcspResponse(ReferenceCountedOpenSslEngine.this.ssl);
                        }
                    }
                    return ocspResponse == null ? Collections.emptyList() : Collections.singletonList(ocspResponse);
                }
            };
        } else {
            this.session = new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext());
        }
        this.engineMap = referenceCountedOpenSslContext.engineMap;
        boolean z3 = referenceCountedOpenSslContext.enableOcsp;
        this.enableOcsp = z3;
        if (!referenceCountedOpenSslContext.sessionContext().useKeyManager()) {
            this.localCertificateChain = referenceCountedOpenSslContext.keyCertChain;
        }
        this.jdkCompatibilityMode = z;
        Lock lock = referenceCountedOpenSslContext.ctxLock.readLock();
        lock.lock();
        try {
            long jNewSSL = SSL.newSSL(referenceCountedOpenSslContext.ctx, true ^ referenceCountedOpenSslContext.isClient());
            synchronized (this) {
                this.ssl = jNewSSL;
                try {
                    this.networkBIO = SSL.bioNewByteBuffer(jNewSSL, referenceCountedOpenSslContext.getBioNonApplicationBufferSize());
                    setClientAuth(zIsClient ? ClientAuth.NONE : referenceCountedOpenSslContext.clientAuth);
                    if (referenceCountedOpenSslContext.protocols != null) {
                        setEnabledProtocols(referenceCountedOpenSslContext.protocols);
                    }
                    if (zIsClient && SslUtils.isValidHostNameForSNI(str)) {
                        SSL.setTlsExtHostName(this.ssl, str);
                        this.sniHostNames = Collections.singletonList(str);
                    }
                    if (z3) {
                        SSL.enableOcsp(this.ssl);
                    }
                    if (!z) {
                        long j = this.ssl;
                        SSL.setMode(j, SSL.getMode(j) | SSL.SSL_MODE_ENABLE_PARTIAL_WRITE);
                    }
                    calculateMaxWrapOverhead();
                } catch (Throwable th) {
                    shutdown();
                    PlatformDependent.throwException(th);
                }
            }
            this.parentContext = referenceCountedOpenSslContext;
            referenceCountedOpenSslContext.retain();
            this.leak = z2 ? leakDetector.track(this) : null;
        } finally {
            lock.unlock();
        }
    }

    private static boolean isProtocolEnabled(int i, int i2, String str) {
        return (i & i2) == 0 && OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str);
    }

    private static SSLEngineResult.HandshakeStatus pendingStatus(int i) {
        return i > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    private static String toJavaCipherSuitePrefix(String str) {
        char cCharAt = 0;
        if (str != null && !str.isEmpty()) {
            cCharAt = str.charAt(0);
        }
        return cCharAt != 'S' ? cCharAt != 'T' ? "UNKNOWN" : "TLS" : "SSL";
    }

    private static boolean isEndPointVerificationEnabled(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    private static long bufferAddress(ByteBuffer byteBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.directBufferAddress(byteBuffer);
        }
        return Buffer.address(byteBuffer);
    }

    private boolean isBytesAvailableEnoughForWrap(int i, int i2, int i3) {
        return ((long) i) - (((long) this.maxWrapOverhead) * ((long) i3)) >= ((long) i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getEnableSessionCreation() {
        return false;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnableSessionCreation(boolean z) {
        if (z) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolAccessor
    public String getNegotiatedApplicationProtocol() {
        return this.applicationProtocol;
    }

    @Override // javax.net.ssl.SSLEngine
    public final SSLSession getSession() {
        return this.session;
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getUseClientMode() {
        return this.clientMode;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setUseClientMode(boolean z) {
        if (z != this.clientMode) {
            throw new UnsupportedOperationException();
        }
    }

    final int maxEncryptedPacketLength0() {
        return this.maxWrapOverhead + MAX_PLAINTEXT_LENGTH;
    }

    final synchronized String[] authMethods() {
        if (isDestroyed()) {
            return EmptyArrays.EMPTY_STRINGS;
        }
        return SSL.authenticationMethods(this.ssl);
    }

    final boolean setKeyMaterial(OpenSslKeyMaterial openSslKeyMaterial) throws Exception {
        synchronized (this) {
            if (isDestroyed()) {
                return false;
            }
            SSL.setKeyMaterial(this.ssl, openSslKeyMaterial.certificateChainAddress(), openSslKeyMaterial.privateKeyAddress());
            this.localCertificateChain = openSslKeyMaterial.certificateChain();
            return true;
        }
    }

    final synchronized SecretKeySpec masterKey() {
        if (isDestroyed()) {
            return null;
        }
        return new SecretKeySpec(SSL.getMasterKey(this.ssl), "AES");
    }

    public byte[] getOcspResponse() {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (!this.clientMode) {
            throw new IllegalStateException("Not a client SSLEngine");
        }
        synchronized (this) {
            if (isDestroyed()) {
                return EmptyArrays.EMPTY_BYTES;
            }
            return SSL.getOcspResponse(this.ssl);
        }
    }

    public void setOcspResponse(byte[] bArr) {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (this.clientMode) {
            throw new IllegalStateException("Not a server SSLEngine");
        }
        synchronized (this) {
            if (!isDestroyed()) {
                SSL.setOcspResponse(this.ssl, bArr);
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final int refCnt() {
        return this.refCnt.refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted retain() {
        this.refCnt.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted retain(int i) {
        this.refCnt.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted touch() {
        this.refCnt.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted touch(Object obj) {
        this.refCnt.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release() {
        return this.refCnt.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release(int i) {
        return this.refCnt.release(i);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLSession getHandshakeSession() {
        int i = AnonymousClass4.$SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[this.handshakeState.ordinal()];
        if (i == 1 || i == 2) {
            return null;
        }
        return this.session;
    }

    public final synchronized long sslPointer() {
        return this.ssl;
    }

    public final synchronized void shutdown() {
        if (!this.destroyed) {
            this.destroyed = true;
            this.engineMap.remove(this.ssl);
            SSL.freeSSL(this.ssl);
            this.networkBIO = 0L;
            this.ssl = 0L;
            this.outboundClosed = true;
            this.isInboundDone = true;
        }
        SSL.clearError();
    }

    private int writePlaintextData(ByteBuffer byteBuffer, int i) {
        int iWriteToSSL;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        if (byteBuffer.isDirect()) {
            iWriteToSSL = SSL.writeToSSL(this.ssl, bufferAddress(byteBuffer) + iPosition, i);
            if (iWriteToSSL > 0) {
                byteBuffer.position(iPosition + iWriteToSSL);
            }
        } else {
            ByteBuf byteBufDirectBuffer = this.alloc.directBuffer(i);
            try {
                byteBuffer.limit(iPosition + i);
                byteBufDirectBuffer.setBytes(0, byteBuffer);
                byteBuffer.limit(iLimit);
                iWriteToSSL = SSL.writeToSSL(this.ssl, OpenSsl.memoryAddress(byteBufDirectBuffer), i);
                if (iWriteToSSL > 0) {
                    byteBuffer.position(iPosition + iWriteToSSL);
                } else {
                    byteBuffer.position(iPosition);
                }
            } finally {
                byteBufDirectBuffer.release();
            }
        }
        return iWriteToSSL;
    }

    private ByteBuf writeEncryptedData(ByteBuffer byteBuffer, int i) throws Throwable {
        int iPosition = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(byteBuffer) + iPosition, i, false);
            return null;
        }
        ByteBuf byteBufDirectBuffer = this.alloc.directBuffer(i);
        try {
            int iLimit = byteBuffer.limit();
            byteBuffer.limit(iPosition + i);
            byteBufDirectBuffer.writeBytes(byteBuffer);
            byteBuffer.position(iPosition);
            byteBuffer.limit(iLimit);
            SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(byteBufDirectBuffer), i, false);
            return byteBufDirectBuffer;
        } catch (Throwable th) {
            byteBufDirectBuffer.release();
            PlatformDependent.throwException(th);
            return null;
        }
    }

    private int readPlaintextData(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            int fromSSL = SSL.readFromSSL(this.ssl, bufferAddress(byteBuffer) + iPosition, byteBuffer.limit() - iPosition);
            if (fromSSL <= 0) {
                return fromSSL;
            }
            byteBuffer.position(iPosition + fromSSL);
            return fromSSL;
        }
        int iLimit = byteBuffer.limit();
        int iMin = Math.min(maxEncryptedPacketLength0(), iLimit - iPosition);
        ByteBuf byteBufDirectBuffer = this.alloc.directBuffer(iMin);
        try {
            int fromSSL2 = SSL.readFromSSL(this.ssl, OpenSsl.memoryAddress(byteBufDirectBuffer), iMin);
            if (fromSSL2 > 0) {
                byteBuffer.limit(iPosition + fromSSL2);
                byteBufDirectBuffer.getBytes(byteBufDirectBuffer.readerIndex(), byteBuffer);
                byteBuffer.limit(iLimit);
            }
            return fromSSL2;
        } finally {
            byteBufDirectBuffer.release();
        }
    }

    final synchronized int maxWrapOverhead() {
        return this.maxWrapOverhead;
    }

    final synchronized int maxEncryptedPacketLength() {
        return maxEncryptedPacketLength0();
    }

    final int calculateMaxLengthForWrap(int i, int i2) {
        return (int) Math.min(this.maxWrapBufferSize, i + (this.maxWrapOverhead * i2));
    }

    final synchronized int sslPending() {
        return sslPending0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateMaxWrapOverhead() {
        this.maxWrapOverhead = SSL.getMaxWrapOverhead(this.ssl);
        this.maxWrapBufferSize = this.jdkCompatibilityMode ? maxEncryptedPacketLength0() : maxEncryptedPacketLength0() << 4;
    }

    private int sslPending0() {
        if (this.handshakeState != HandshakeState.FINISHED) {
            return 0;
        }
        return SSL.sslPending(this.ssl);
    }

    /* JADX WARN: Removed duplicated region for block: B:295:0x051f A[Catch: all -> 0x0537, TryCatch #3 {, blocks: (B:11:0x0013, B:13:0x0019, B:15:0x001f, B:18:0x0026, B:20:0x002b, B:19:0x0029, B:33:0x0084, B:35:0x008b, B:37:0x00a1, B:36:0x0093, B:42:0x00b1, B:44:0x00b8, B:46:0x00cf, B:45:0x00c1, B:51:0x00dd, B:53:0x00e4, B:55:0x00fb, B:54:0x00ed, B:60:0x010a, B:62:0x0111, B:64:0x0128, B:63:0x011a, B:293:0x0518, B:295:0x051f, B:297:0x0536, B:296:0x052e, B:80:0x0158, B:82:0x015f, B:84:0x0176, B:83:0x0168, B:87:0x0180, B:89:0x0187, B:91:0x019e, B:90:0x0190, B:96:0x01b4, B:98:0x01bb, B:100:0x01d2, B:99:0x01c4, B:110:0x01f4, B:112:0x01fb, B:114:0x0212, B:113:0x0204, B:122:0x0223, B:124:0x022a, B:126:0x0241, B:125:0x0233, B:132:0x0251, B:134:0x0258, B:136:0x026f, B:135:0x0261, B:158:0x02c2, B:160:0x02c9, B:162:0x02e0, B:161:0x02d2, B:167:0x02ee, B:169:0x02f5, B:171:0x030c, B:170:0x02fe, B:204:0x0389, B:206:0x0390, B:208:0x03a7, B:207:0x0399, B:224:0x03e5, B:226:0x03ec, B:228:0x0403, B:227:0x03f5, B:231:0x040b, B:233:0x0412, B:235:0x0429, B:234:0x041b, B:240:0x0435, B:242:0x043c, B:244:0x0453, B:243:0x0445, B:250:0x0461, B:252:0x0468, B:254:0x047f, B:253:0x0471, B:257:0x0487, B:259:0x048e, B:261:0x04a5, B:260:0x0497, B:273:0x04c1, B:275:0x04c8, B:277:0x04df, B:276:0x04d1, B:185:0x0342, B:187:0x0349, B:189:0x0360, B:188:0x0352, B:280:0x04e5, B:282:0x04ec, B:284:0x0503, B:283:0x04f5), top: B:312:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:296:0x052e A[Catch: all -> 0x0537, TryCatch #3 {, blocks: (B:11:0x0013, B:13:0x0019, B:15:0x001f, B:18:0x0026, B:20:0x002b, B:19:0x0029, B:33:0x0084, B:35:0x008b, B:37:0x00a1, B:36:0x0093, B:42:0x00b1, B:44:0x00b8, B:46:0x00cf, B:45:0x00c1, B:51:0x00dd, B:53:0x00e4, B:55:0x00fb, B:54:0x00ed, B:60:0x010a, B:62:0x0111, B:64:0x0128, B:63:0x011a, B:293:0x0518, B:295:0x051f, B:297:0x0536, B:296:0x052e, B:80:0x0158, B:82:0x015f, B:84:0x0176, B:83:0x0168, B:87:0x0180, B:89:0x0187, B:91:0x019e, B:90:0x0190, B:96:0x01b4, B:98:0x01bb, B:100:0x01d2, B:99:0x01c4, B:110:0x01f4, B:112:0x01fb, B:114:0x0212, B:113:0x0204, B:122:0x0223, B:124:0x022a, B:126:0x0241, B:125:0x0233, B:132:0x0251, B:134:0x0258, B:136:0x026f, B:135:0x0261, B:158:0x02c2, B:160:0x02c9, B:162:0x02e0, B:161:0x02d2, B:167:0x02ee, B:169:0x02f5, B:171:0x030c, B:170:0x02fe, B:204:0x0389, B:206:0x0390, B:208:0x03a7, B:207:0x0399, B:224:0x03e5, B:226:0x03ec, B:228:0x0403, B:227:0x03f5, B:231:0x040b, B:233:0x0412, B:235:0x0429, B:234:0x041b, B:240:0x0435, B:242:0x043c, B:244:0x0453, B:243:0x0445, B:250:0x0461, B:252:0x0468, B:254:0x047f, B:253:0x0471, B:257:0x0487, B:259:0x048e, B:261:0x04a5, B:260:0x0497, B:273:0x04c1, B:275:0x04c8, B:277:0x04df, B:276:0x04d1, B:185:0x0342, B:187:0x0349, B:189:0x0360, B:188:0x0352, B:280:0x04e5, B:282:0x04ec, B:284:0x0503, B:283:0x04f5), top: B:312:0x0013 }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final javax.net.ssl.SSLEngineResult wrap(java.nio.ByteBuffer[] r12, int r13, int r14, java.nio.ByteBuffer r15) throws javax.net.ssl.SSLException {
        /*
            Method dump skipped, instructions count: 1402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.wrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer):javax.net.ssl.SSLEngineResult");
    }

    private SSLEngineResult newResult(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        return newResult(SSLEngineResult.Status.OK, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResult(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        if (isOutboundDone()) {
            if (isInboundDone()) {
                handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                shutdown();
            }
            return new SSLEngineResult(SSLEngineResult.Status.CLOSED, handshakeStatus, i, i2);
        }
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_TASK) {
            this.needTask = true;
        }
        return new SSLEngineResult(status, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(status, mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLException shutdownWithError(String str, int i) {
        return shutdownWithError(str, i, SSL.getLastErrorNumber());
    }

    private SSLException shutdownWithError(String str, int i, int i2) {
        String errorString = SSL.getErrorString(i2);
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            internalLogger.debug("{} failed with {}: OpenSSL error: {} {}", str, Integer.valueOf(i), Integer.valueOf(i2), errorString);
        }
        shutdown();
        if (this.handshakeState == HandshakeState.FINISHED) {
            return new SSLException(errorString);
        }
        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException(errorString);
        Throwable th = this.pendingException;
        if (th != null) {
            sSLHandshakeException.initCause(th);
            this.pendingException = null;
        }
        return sSLHandshakeException;
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x01a4, code lost:

        r13.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0213, code lost:

        if (r13 == null) goto L192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x029f, code lost:

        io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.bioClearByteBuffer(r18.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02a9, code lost:

        if (r18.receivedShutdown != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x02b6, code lost:

        if ((io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.getShutdown(r18.ssl) & io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN) != io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x02b8, code lost:

        closeAll();
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x02bf, code lost:

        if (isInboundDone() == false) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x02c1, code lost:

        r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x02c4, code lost:

        r0 = javax.net.ssl.SSLEngineResult.Status.OK;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x02c6, code lost:

        r0 = newResultMayFinishHandshake(r0, r6, r9, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x02cb, code lost:

        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final javax.net.ssl.SSLEngineResult unwrap(java.nio.ByteBuffer[] r19, int r20, int r21, java.nio.ByteBuffer[] r22, int r23, int r24) throws javax.net.ssl.SSLException {
        /*
            Method dump skipped, instructions count: 837
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.unwrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer[], int, int):javax.net.ssl.SSLEngineResult");
    }

    private SSLEngineResult sslReadErrorResult(int i, int i2, int i3, int i4) throws SSLException {
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            String errorString = SSL.getErrorString(i2);
            Throwable sSLException = this.handshakeState == HandshakeState.FINISHED ? new SSLException(errorString) : new SSLHandshakeException(errorString);
            Throwable th = this.pendingException;
            if (th == null) {
                this.pendingException = sSLException;
            } else {
                ThrowableUtil.addSuppressed(th, sSLException);
            }
            SSL.clearError();
            return new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, i3, i4);
        }
        throw shutdownWithError("SSL_read", i, i2);
    }

    private void closeAll() throws SSLException {
        this.receivedShutdown = true;
        closeOutbound();
        closeInbound();
    }

    private void rejectRemoteInitiatedRenegotiation() throws SSLHandshakeException {
        if (isDestroyed() || SSL.getHandshakeCount(this.ssl) <= 1 || "TLSv1.3".equals(this.session.getProtocol()) || this.handshakeState != HandshakeState.FINISHED) {
            return;
        }
        shutdown();
        throw new SSLHandshakeException("remote-initiated renegotiation not allowed");
    }

    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        return unwrap(byteBufferArr, 0, byteBufferArr.length, byteBufferArr2, 0, byteBufferArr2.length);
    }

    private ByteBuffer[] singleSrcBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleSrcBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleSrcBuffer() {
        this.singleSrcBuffer[0] = null;
    }

    private ByteBuffer[] singleDstBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleDstBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleDstBuffer() {
        this.singleDstBuffer[0] = null;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), 0, 1, byteBufferArr, i, i2);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return wrap(singleSrcBuffer(byteBuffer), byteBuffer2);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
            resetSingleDstBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), singleDstBuffer(byteBuffer2));
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), byteBufferArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized Runnable getDelegatedTask() {
        if (isDestroyed()) {
            return null;
        }
        final Runnable task = SSL.getTask(this.ssl);
        if (task == null) {
            return null;
        }
        return new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.3
            @Override // java.lang.Runnable
            public void run() {
                if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    return;
                }
                try {
                    task.run();
                } finally {
                    ReferenceCountedOpenSslEngine.this.needTask = false;
                }
            }
        };
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void closeInbound() throws SSLException {
        if (this.isInboundDone) {
            return;
        }
        this.isInboundDone = true;
        if (isOutboundDone()) {
            shutdown();
        }
        if (this.handshakeState != HandshakeState.NOT_STARTED && !this.receivedShutdown) {
            throw new SSLException("Inbound closed before receiving peer's close_notify: possible truncation attack?");
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized boolean isInboundDone() {
        return this.isInboundDone;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void closeOutbound() {
        if (this.outboundClosed) {
            return;
        }
        this.outboundClosed = true;
        if (this.handshakeState != HandshakeState.NOT_STARTED && !isDestroyed()) {
            if ((SSL.getShutdown(this.ssl) & SSL.SSL_SENT_SHUTDOWN) != SSL.SSL_SENT_SHUTDOWN) {
                doSSLShutdown();
            }
        } else {
            shutdown();
        }
    }

    private boolean doSSLShutdown() {
        if (SSL.isInInit(this.ssl) != 0) {
            return false;
        }
        int iShutdownSSL = SSL.shutdownSSL(this.ssl);
        if (iShutdownSSL >= 0) {
            return true;
        }
        int error = SSL.getError(this.ssl, iShutdownSSL);
        if (error == SSL.SSL_ERROR_SYSCALL || error == SSL.SSL_ERROR_SSL) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                int lastErrorNumber = SSL.getLastErrorNumber();
                internalLogger.debug("SSL_shutdown failed: OpenSSL error: {} {}", Integer.valueOf(lastErrorNumber), SSL.getErrorString(lastErrorNumber));
            }
            shutdown();
            return false;
        }
        SSL.clearError();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean isOutboundDone() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.outboundClosed     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L15
            long r0 = r5.networkBIO     // Catch: java.lang.Throwable -> L18
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L13
            int r0 = io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.bioLengthNonApplication(r0)     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L15
        L13:
            r0 = 1
            goto L16
        L15:
            r0 = 0
        L16:
            monitor-exit(r5)
            return r0
        L18:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.isOutboundDone():boolean");
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getSupportedCipherSuites() {
        return (String[]) OpenSsl.AVAILABLE_CIPHER_SUITES.toArray(new String[0]);
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getEnabledCipherSuites() {
        String[] strArr;
        synchronized (this) {
            if (!isDestroyed()) {
                String[] ciphers = SSL.getCiphers(this.ssl);
                if (isProtocolEnabled(SSL.getOptions(this.ssl), SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
                    strArr = OpenSsl.EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
                } else {
                    strArr = EmptyArrays.EMPTY_STRINGS;
                }
                if (ciphers == null) {
                    return EmptyArrays.EMPTY_STRINGS;
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet(ciphers.length + strArr.length);
                synchronized (this) {
                    for (int i = 0; i < ciphers.length; i++) {
                        String javaCipherSuite = toJavaCipherSuite(ciphers[i]);
                        if (javaCipherSuite == null) {
                            javaCipherSuite = ciphers[i];
                        }
                        if (OpenSsl.isTlsv13Supported() || !SslUtils.isTLSv13Cipher(javaCipherSuite)) {
                            linkedHashSet.add(javaCipherSuite);
                        }
                    }
                    Collections.addAll(linkedHashSet, strArr);
                }
                return (String[]) linkedHashSet.toArray(new String[0]);
            }
            return EmptyArrays.EMPTY_STRINGS;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnabledCipherSuites(String[] strArr) {
        ObjectUtil.checkNotNull(strArr, "cipherSuites");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        CipherSuiteConverter.convertToCipherStrings(Arrays.asList(strArr), sb, sb2, OpenSsl.isBoringSSL());
        String string = sb.toString();
        String string2 = sb2.toString();
        if (!OpenSsl.isTlsv13Supported() && !string2.isEmpty()) {
            throw new IllegalArgumentException("TLSv1.3 is not supported by this java version.");
        }
        synchronized (this) {
            if (!isDestroyed()) {
                try {
                    SSL.setCipherSuites(this.ssl, string, false);
                    if (OpenSsl.isTlsv13Supported()) {
                        SSL.setCipherSuites(this.ssl, string2, true);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("failed to enable cipher suites: " + string, e);
                }
            } else {
                throw new IllegalStateException("failed to enable cipher suites: " + string);
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getSupportedProtocols() {
        return (String[]) OpenSsl.SUPPORTED_PROTOCOLS_SET.toArray(new String[0]);
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getEnabledProtocols() {
        ArrayList arrayList = new ArrayList(6);
        arrayList.add("SSLv2Hello");
        synchronized (this) {
            if (!isDestroyed()) {
                int options = SSL.getOptions(this.ssl);
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1, "TLSv1")) {
                    arrayList.add("TLSv1");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_1, "TLSv1.1")) {
                    arrayList.add("TLSv1.1");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_2, "TLSv1.2")) {
                    arrayList.add("TLSv1.2");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
                    arrayList.add("TLSv1.3");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv2, "SSLv2")) {
                    arrayList.add("SSLv2");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv3, "SSLv3")) {
                    arrayList.add("SSLv3");
                }
                return (String[]) arrayList.toArray(new String[0]);
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnabledProtocols(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException();
        }
        int length = OPENSSL_OP_NO_PROTOCOLS.length;
        int i = 0;
        for (String str : strArr) {
            if (!OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str)) {
                throw new IllegalArgumentException("Protocol " + str + " is not supported.");
            }
            if (str.equals("SSLv2")) {
                if (length > 0) {
                    length = 0;
                }
                if (i < 0) {
                    i = 0;
                }
            } else if (str.equals("SSLv3")) {
                if (length > 1) {
                    length = 1;
                }
                if (i < 1) {
                    i = 1;
                }
            } else if (str.equals("TLSv1")) {
                if (length > 2) {
                    length = 2;
                }
                if (i < 2) {
                    i = 2;
                }
            } else if (str.equals("TLSv1.1")) {
                if (length > 3) {
                    length = 3;
                }
                if (i < 3) {
                    i = 3;
                }
            } else if (str.equals("TLSv1.2")) {
                if (length > 4) {
                    length = 4;
                }
                if (i < 4) {
                    i = 4;
                }
            } else if (str.equals("TLSv1.3")) {
                if (length > 5) {
                    length = 5;
                }
                if (i < 5) {
                    i = 5;
                }
            }
        }
        synchronized (this) {
            if (!isDestroyed()) {
                SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_NO_TLSv1 | SSL.SSL_OP_NO_TLSv1_1 | SSL.SSL_OP_NO_TLSv1_2 | SSL.SSL_OP_NO_TLSv1_3);
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    i2 |= OPENSSL_OP_NO_PROTOCOLS[i3];
                }
                int i4 = i + 1;
                while (true) {
                    int[] iArr = OPENSSL_OP_NO_PROTOCOLS;
                    if (i4 < iArr.length) {
                        i2 |= iArr[i4];
                        i4++;
                    } else {
                        SSL.setOptions(this.ssl, i2);
                    }
                }
            } else {
                throw new IllegalStateException("failed to enable protocols: " + Arrays.asList(strArr));
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void beginHandshake() throws SSLException {
        int i = AnonymousClass4.$SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[this.handshakeState.ordinal()];
        if (i == 1) {
            this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
            if (handshake() == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                this.needTask = true;
            }
            calculateMaxWrapOverhead();
        } else {
            if (i == 2) {
                throw new SSLException("renegotiation unsupported");
            }
            if (i == 3) {
                checkEngineClosed();
                this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
                calculateMaxWrapOverhead();
            } else if (i != 4) {
                throw new Error();
            }
        }
    }

    private void checkEngineClosed() throws SSLException {
        if (isDestroyed()) {
            throw new SSLException("engine closed");
        }
    }

    private SSLEngineResult.HandshakeStatus handshakeException() throws SSLException {
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        Throwable th = this.pendingException;
        this.pendingException = null;
        shutdown();
        if (th instanceof SSLHandshakeException) {
            throw ((SSLHandshakeException) th);
        }
        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("General OpenSslEngine problem");
        sSLHandshakeException.initCause(th);
        throw sSLHandshakeException;
    }

    final void initHandshakeException(Throwable th) {
        Throwable th2 = this.pendingException;
        if (th2 == null) {
            this.pendingException = th;
        } else {
            ThrowableUtil.addSuppressed(th2, th);
        }
    }

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        if (this.handshakeState == HandshakeState.FINISHED) {
            return SSLEngineResult.HandshakeStatus.FINISHED;
        }
        checkEngineClosed();
        if (this.pendingException != null) {
            if (SSL.doHandshake(this.ssl) <= 0) {
                SSL.clearError();
            }
            return handshakeException();
        }
        this.engineMap.add(this);
        if (this.lastAccessed == -1) {
            this.lastAccessed = System.currentTimeMillis();
        }
        int iDoHandshake = SSL.doHandshake(this.ssl);
        if (iDoHandshake <= 0) {
            int error = SSL.getError(this.ssl, iDoHandshake);
            if (error == SSL.SSL_ERROR_WANT_READ || error == SSL.SSL_ERROR_WANT_WRITE) {
                return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
            }
            if (error == SSL.SSL_ERROR_WANT_X509_LOOKUP || error == SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY || error == SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                return SSLEngineResult.HandshakeStatus.NEED_TASK;
            }
            if (this.pendingException != null) {
                return handshakeException();
            }
            throw shutdownWithError("SSL_do_handshake", error);
        }
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        this.session.handshakeFinished();
        return SSLEngineResult.HandshakeStatus.FINISHED;
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        return (handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING || this.handshakeState == HandshakeState.FINISHED) ? handshakeStatus : handshake();
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        if (!needPendingStatus()) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        if (!needPendingStatus()) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        return pendingStatus(i);
    }

    private boolean needPendingStatus() {
        return (this.handshakeState == HandshakeState.NOT_STARTED || isDestroyed() || (this.handshakeState == HandshakeState.FINISHED && !isInboundDone() && !isOutboundDone())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toJavaCipherSuite(String str) {
        if (str == null) {
            return null;
        }
        return CipherSuiteConverter.toJava(str, toJavaCipherSuitePrefix(SSL.getVersion(this.ssl)));
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getNeedClientAuth() {
        return this.clientAuth == ClientAuth.REQUIRE;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setNeedClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.REQUIRE : ClientAuth.NONE);
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getWantClientAuth() {
        return this.clientAuth == ClientAuth.OPTIONAL;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setWantClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.OPTIONAL : ClientAuth.NONE);
    }

    public final synchronized void setVerify(int i, int i2) {
        if (!isDestroyed()) {
            SSL.setVerify(this.ssl, i, i2);
        }
    }

    private void setClientAuth(ClientAuth clientAuth) {
        if (this.clientMode) {
            return;
        }
        synchronized (this) {
            if (this.clientAuth == clientAuth) {
                return;
            }
            if (!isDestroyed()) {
                int i = AnonymousClass4.$SwitchMap$io$netty$handler$ssl$ClientAuth[clientAuth.ordinal()];
                if (i == 1) {
                    SSL.setVerify(this.ssl, 0, 10);
                } else if (i == 2) {
                    SSL.setVerify(this.ssl, 2, 10);
                } else if (i == 3) {
                    SSL.setVerify(this.ssl, 1, 10);
                } else {
                    throw new Error(clientAuth.toString());
                }
            }
            this.clientAuth = clientAuth;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLParameters getSSLParameters() {
        SSLParameters sSLParameters;
        sSLParameters = super.getSSLParameters();
        int iJavaVersion = PlatformDependent.javaVersion();
        if (iJavaVersion >= 7) {
            sSLParameters.setEndpointIdentificationAlgorithm(this.endPointIdentificationAlgorithm);
            Java7SslParametersUtils.setAlgorithmConstraints(sSLParameters, this.algorithmConstraints);
            if (iJavaVersion >= 8) {
                List<String> list = this.sniHostNames;
                if (list != null) {
                    Java8SslUtils.setSniHostNames(sSLParameters, list);
                }
                if (!isDestroyed()) {
                    Java8SslUtils.setUseCipherSuitesOrder(sSLParameters, (SSL.getOptions(this.ssl) & SSL.SSL_OP_CIPHER_SERVER_PREFERENCE) != 0);
                }
                Java8SslUtils.setSNIMatchers(sSLParameters, this.matchers);
            }
        }
        return sSLParameters;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void setSSLParameters(SSLParameters sSLParameters) {
        int iJavaVersion = PlatformDependent.javaVersion();
        if (iJavaVersion >= 7) {
            if (sSLParameters.getAlgorithmConstraints() != null) {
                throw new IllegalArgumentException("AlgorithmConstraints are not supported.");
            }
            boolean zIsDestroyed = isDestroyed();
            if (iJavaVersion >= 8) {
                if (!zIsDestroyed) {
                    if (this.clientMode) {
                        List<String> sniHostNames = Java8SslUtils.getSniHostNames(sSLParameters);
                        Iterator<String> it2 = sniHostNames.iterator();
                        while (it2.hasNext()) {
                            SSL.setTlsExtHostName(this.ssl, it2.next());
                        }
                        this.sniHostNames = sniHostNames;
                    }
                    if (Java8SslUtils.getUseCipherSuitesOrder(sSLParameters)) {
                        SSL.setOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                    } else {
                        SSL.clearOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                    }
                }
                this.matchers = sSLParameters.getSNIMatchers();
            }
            String endpointIdentificationAlgorithm = sSLParameters.getEndpointIdentificationAlgorithm();
            if (!zIsDestroyed && this.clientMode && isEndPointVerificationEnabled(endpointIdentificationAlgorithm)) {
                SSL.setVerify(this.ssl, 2, -1);
            }
            this.endPointIdentificationAlgorithm = endpointIdentificationAlgorithm;
            this.algorithmConstraints = sSLParameters.getAlgorithmConstraints();
        }
        super.setSSLParameters(sSLParameters);
    }

    final boolean checkSniHostnameMatch(byte[] bArr) {
        return Java8SslUtils.checkSniHostnameMatch(this.matchers, bArr);
    }

    private enum HandshakeState {
        NOT_STARTED,
        STARTED_IMPLICITLY,
        STARTED_EXPLICITLY,
        FINISHED
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ClientAuth;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState;

        static {
            int[] iArr = new int[ApplicationProtocolConfig.Protocol.values().length];
            $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol = iArr;
            try {
                iArr[ApplicationProtocolConfig.Protocol.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[ApplicationProtocolConfig.Protocol.ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[ApplicationProtocolConfig.Protocol.NPN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[ApplicationProtocolConfig.Protocol.NPN_AND_ALPN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ClientAuth.values().length];
            $SwitchMap$io$netty$handler$ssl$ClientAuth = iArr2;
            try {
                iArr2[ClientAuth.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ClientAuth[ClientAuth.REQUIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ClientAuth[ClientAuth.OPTIONAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[HandshakeState.values().length];
            $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState = iArr3;
            try {
                iArr3[HandshakeState.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[HandshakeState.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[HandshakeState.STARTED_IMPLICITLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[HandshakeState.STARTED_EXPLICITLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private final class DefaultOpenSslSession implements OpenSslSession {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final OpenSslSessionContext sessionContext;
        private volatile int applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        private String cipher;
        private long creationTime;
        private byte[] id;
        private Certificate[] peerCerts;
        private String protocol;
        private Map<String, Object> values;
        private X509Certificate[] x509PeerCerts;

        DefaultOpenSslSession(OpenSslSessionContext openSslSessionContext) {
            this.sessionContext = openSslSessionContext;
        }

        @Override // javax.net.ssl.SSLSession
        public int getApplicationBufferSize() {
            return this.applicationBufferSize;
        }

        @Override // javax.net.ssl.SSLSession
        public SSLSessionContext getSessionContext() {
            return this.sessionContext;
        }

        private SSLSessionBindingEvent newSSLSessionBindingEvent(String str) {
            return new SSLSessionBindingEvent(ReferenceCountedOpenSslEngine.this.session, str);
        }

        @Override // javax.net.ssl.SSLSession
        public byte[] getId() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                byte[] bArr = this.id;
                if (bArr == null) {
                    return EmptyArrays.EMPTY_BYTES;
                }
                return (byte[]) bArr.clone();
            }
        }

        @Override // javax.net.ssl.SSLSession
        public long getCreationTime() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.creationTime == 0 && !ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.creationTime = SSL.getTime(ReferenceCountedOpenSslEngine.this.ssl) * 1000;
                }
            }
            return this.creationTime;
        }

        @Override // javax.net.ssl.SSLSession
        public long getLastAccessedTime() {
            long j = ReferenceCountedOpenSslEngine.this.lastAccessed;
            return j == -1 ? getCreationTime() : j;
        }

        @Override // javax.net.ssl.SSLSession
        public void invalidate() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    SSL.setTimeout(ReferenceCountedOpenSslEngine.this.ssl, 0L);
                }
            }
        }

        @Override // javax.net.ssl.SSLSession
        public boolean isValid() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    return false;
                }
                return System.currentTimeMillis() - (SSL.getTimeout(ReferenceCountedOpenSslEngine.this.ssl) * 1000) < SSL.getTime(ReferenceCountedOpenSslEngine.this.ssl) * 1000;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void putValue(String str, Object obj) {
            Object objPut;
            ObjectUtil.checkNotNull(str, "name");
            ObjectUtil.checkNotNull(obj, "value");
            synchronized (this) {
                Map map = this.values;
                if (map == null) {
                    map = new HashMap(2);
                    this.values = map;
                }
                objPut = map.put(str, obj);
            }
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueBound(newSSLSessionBindingEvent(str));
            }
            notifyUnbound(objPut, str);
        }

        @Override // javax.net.ssl.SSLSession
        public Object getValue(String str) {
            ObjectUtil.checkNotNull(str, "name");
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map == null) {
                    return null;
                }
                return map.get(str);
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void removeValue(String str) {
            ObjectUtil.checkNotNull(str, "name");
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map == null) {
                    return;
                }
                notifyUnbound(map.remove(str), str);
            }
        }

        @Override // javax.net.ssl.SSLSession
        public String[] getValueNames() {
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map != null && !map.isEmpty()) {
                    return (String[]) map.keySet().toArray(new String[0]);
                }
                return EmptyArrays.EMPTY_STRINGS;
            }
        }

        private void notifyUnbound(Object obj, String str) {
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueUnbound(newSSLSessionBindingEvent(str));
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSession
        public void handshakeFinished() throws SSLException {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.id = SSL.getSessionId(ReferenceCountedOpenSslEngine.this.ssl);
                    ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = ReferenceCountedOpenSslEngine.this;
                    this.cipher = referenceCountedOpenSslEngine.toJavaCipherSuite(SSL.getCipherForSSL(referenceCountedOpenSslEngine.ssl));
                    this.protocol = SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl);
                    initPeerCerts();
                    selectApplicationProtocol();
                    ReferenceCountedOpenSslEngine.this.calculateMaxWrapOverhead();
                    ReferenceCountedOpenSslEngine.this.handshakeState = HandshakeState.FINISHED;
                } else {
                    throw new SSLException("Already closed");
                }
            }
        }

        private void initPeerCerts() {
            byte[][] peerCertChain = SSL.getPeerCertChain(ReferenceCountedOpenSslEngine.this.ssl);
            if (ReferenceCountedOpenSslEngine.this.clientMode) {
                if (ReferenceCountedOpenSslEngine.isEmpty(peerCertChain)) {
                    this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                    this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
                    return;
                } else {
                    this.peerCerts = new Certificate[peerCertChain.length];
                    this.x509PeerCerts = new X509Certificate[peerCertChain.length];
                    initCerts(peerCertChain, 0);
                    return;
                }
            }
            byte[] peerCertificate = SSL.getPeerCertificate(ReferenceCountedOpenSslEngine.this.ssl);
            if (!ReferenceCountedOpenSslEngine.isEmpty(peerCertificate)) {
                if (ReferenceCountedOpenSslEngine.isEmpty(peerCertChain)) {
                    this.peerCerts = new Certificate[]{new OpenSslX509Certificate(peerCertificate)};
                    this.x509PeerCerts = new X509Certificate[]{new OpenSslJavaxX509Certificate(peerCertificate)};
                    return;
                }
                Certificate[] certificateArr = new Certificate[peerCertChain.length + 1];
                this.peerCerts = certificateArr;
                this.x509PeerCerts = new X509Certificate[peerCertChain.length + 1];
                certificateArr[0] = new OpenSslX509Certificate(peerCertificate);
                this.x509PeerCerts[0] = new OpenSslJavaxX509Certificate(peerCertificate);
                initCerts(peerCertChain, 1);
                return;
            }
            this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
            this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
        }

        private void initCerts(byte[][] bArr, int i) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                int i3 = i + i2;
                this.peerCerts[i3] = new OpenSslX509Certificate(bArr[i2]);
                this.x509PeerCerts[i3] = new OpenSslJavaxX509Certificate(bArr[i2]);
            }
        }

        private void selectApplicationProtocol() throws SSLException {
            ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior = ReferenceCountedOpenSslEngine.this.apn.selectedListenerFailureBehavior();
            List<String> listProtocols = ReferenceCountedOpenSslEngine.this.apn.protocols();
            int i = AnonymousClass4.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[ReferenceCountedOpenSslEngine.this.apn.protocol().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    String alpnSelected = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.this.ssl);
                    if (alpnSelected != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(listProtocols, selectedListenerFailureBehavior, alpnSelected);
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    String nextProtoNegotiated = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.this.ssl);
                    if (nextProtoNegotiated != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(listProtocols, selectedListenerFailureBehavior, nextProtoNegotiated);
                        return;
                    }
                    return;
                }
                if (i == 4) {
                    String alpnSelected2 = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.this.ssl);
                    if (alpnSelected2 == null) {
                        alpnSelected2 = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.this.ssl);
                    }
                    if (alpnSelected2 != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(listProtocols, selectedListenerFailureBehavior, alpnSelected2);
                        return;
                    }
                    return;
                }
                throw new Error();
            }
        }

        private String selectApplicationProtocol(List<String> list, ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior, String str) throws SSLException {
            if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT) {
                return str;
            }
            int size = list.size();
            if (list.contains(str)) {
                return str;
            }
            if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL) {
                return list.get(size - 1);
            }
            throw new SSLException("unknown protocol " + str);
        }

        @Override // javax.net.ssl.SSLSession
        public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
            Certificate[] certificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.isEmpty(this.peerCerts)) {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
                certificateArr = (Certificate[]) this.peerCerts.clone();
            }
            return certificateArr;
        }

        @Override // javax.net.ssl.SSLSession
        public Certificate[] getLocalCertificates() {
            Certificate[] certificateArr = ReferenceCountedOpenSslEngine.this.localCertificateChain;
            if (certificateArr == null) {
                return null;
            }
            return (Certificate[]) certificateArr.clone();
        }

        @Override // javax.net.ssl.SSLSession
        public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
            X509Certificate[] x509CertificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.isEmpty(this.x509PeerCerts)) {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
                x509CertificateArr = (X509Certificate[]) this.x509PeerCerts.clone();
            }
            return x509CertificateArr;
        }

        @Override // javax.net.ssl.SSLSession
        public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
            return ((java.security.cert.X509Certificate) getPeerCertificates()[0]).getSubjectX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public Principal getLocalPrincipal() {
            Certificate[] certificateArr = ReferenceCountedOpenSslEngine.this.localCertificateChain;
            if (certificateArr == null || certificateArr.length == 0) {
                return null;
            }
            return ((java.security.cert.X509Certificate) certificateArr[0]).getIssuerX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public String getCipherSuite() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                String str = this.cipher;
                return str == null ? "SSL_NULL_WITH_NULL_NULL" : str;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public String getProtocol() {
            String version = this.protocol;
            if (version == null) {
                synchronized (ReferenceCountedOpenSslEngine.this) {
                    version = !ReferenceCountedOpenSslEngine.this.isDestroyed() ? SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl) : "";
                }
            }
            return version;
        }

        @Override // javax.net.ssl.SSLSession
        public String getPeerHost() {
            return ReferenceCountedOpenSslEngine.this.getPeerHost();
        }

        @Override // javax.net.ssl.SSLSession
        public int getPeerPort() {
            return ReferenceCountedOpenSslEngine.this.getPeerPort();
        }

        @Override // javax.net.ssl.SSLSession
        public int getPacketBufferSize() {
            return ReferenceCountedOpenSslEngine.this.maxEncryptedPacketLength();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSession
        public void tryExpandApplicationBufferSize(int i) {
            if (i <= ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH || this.applicationBufferSize == ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE) {
                return;
            }
            this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
        }
    }
}
