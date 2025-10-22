package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

/* loaded from: classes5.dex */
public abstract class TlsProtocol {
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    protected boolean allowCertificateStatus;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    protected short connection_state;
    protected boolean expectSessionTicket;
    protected ByteQueueInputStream inputBuffers;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected ByteQueueOutputStream outputBuffer;
    protected Certificate peerCertificate;
    protected boolean receivedChangeCipherSpec;
    protected boolean resumedSession;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation;
    protected SecurityParameters securityParameters;
    protected Hashtable serverExtensions;
    protected SessionParameters sessionParameters;
    protected TlsSession tlsSession;
    RecordStream recordStream;
    private ByteQueue alertQueue;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;
    private volatile boolean closed;
    private byte[] expected_verify_data;
    private volatile boolean failedWithError;
    private ByteQueue handshakeQueue;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom;
    }

    public TlsProtocol(SecureRandom secureRandom) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom;
    }

    protected static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    protected static byte[] createRandomBlock(boolean z, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    protected static byte[] createRenegotiationInfo(byte[] bArr) throws IOException {
        return TlsUtils.encodeOpaque8(bArr);
    }

    protected static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        byte[] bArrGeneratePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, bArrGeneratePremasterSecret);
        } finally {
            if (bArrGeneratePremasterSecret != null) {
                Arrays.fill(bArrGeneratePremasterSecret, (byte) 0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest digestForkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            digestForkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[digestForkPRFHash.getDigestSize()];
        digestForkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected static int getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext r4, int r5) throws java.io.IOException {
        /*
            boolean r4 = org.bouncycastle.crypto.tls.TlsUtils.isTLSv12(r4)
            r0 = 1
            r1 = 47
            switch(r5) {
                case 59: goto L38;
                case 60: goto L38;
                case 61: goto L38;
                case 62: goto L38;
                case 63: goto L38;
                case 64: goto L38;
                default: goto La;
            }
        La:
            switch(r5) {
                case 103: goto L38;
                case 104: goto L38;
                case 105: goto L38;
                case 106: goto L38;
                case 107: goto L38;
                case 108: goto L38;
                case 109: goto L38;
                default: goto Ld;
            }
        Ld:
            r2 = 2
            switch(r5) {
                case 156: goto L38;
                case 157: goto L2f;
                case 158: goto L38;
                case 159: goto L2f;
                case 160: goto L38;
                case 161: goto L2f;
                case 162: goto L38;
                case 163: goto L2f;
                case 164: goto L38;
                case 165: goto L2f;
                case 166: goto L38;
                case 167: goto L2f;
                case 168: goto L38;
                case 169: goto L2f;
                case 170: goto L38;
                case 171: goto L2f;
                case 172: goto L38;
                case 173: goto L2f;
                default: goto L11;
            }
        L11:
            r3 = 0
            switch(r5) {
                case 175: goto L2b;
                case 177: goto L2b;
                case 179: goto L2b;
                case 181: goto L2b;
                case 183: goto L2b;
                case 49208: goto L2b;
                case 49211: goto L2b;
                case 49266: goto L38;
                case 49267: goto L2f;
                case 49268: goto L38;
                case 49269: goto L2f;
                case 49270: goto L38;
                case 49271: goto L2f;
                case 49272: goto L38;
                case 49273: goto L2f;
                case 49274: goto L38;
                case 49275: goto L2f;
                case 49276: goto L38;
                case 49277: goto L2f;
                case 49278: goto L38;
                case 49279: goto L2f;
                case 49280: goto L38;
                case 49281: goto L2f;
                case 49282: goto L38;
                case 49283: goto L2f;
                case 49284: goto L38;
                case 49285: goto L2f;
                case 49286: goto L38;
                case 49287: goto L2f;
                case 49288: goto L38;
                case 49289: goto L2f;
                case 49290: goto L38;
                case 49291: goto L2f;
                case 49292: goto L38;
                case 49293: goto L2f;
                case 49294: goto L38;
                case 49295: goto L2f;
                case 49296: goto L38;
                case 49297: goto L2f;
                case 49298: goto L38;
                case 49299: goto L2f;
                case 49301: goto L2b;
                case 49303: goto L2b;
                case 49305: goto L2b;
                default: goto L15;
            }
        L15:
            switch(r5) {
                case 185: goto L2b;
                case 186: goto L38;
                case 187: goto L38;
                case 188: goto L38;
                case 189: goto L38;
                case 190: goto L38;
                case 191: goto L38;
                case 192: goto L38;
                case 193: goto L38;
                case 194: goto L38;
                case 195: goto L38;
                case 196: goto L38;
                case 197: goto L38;
                default: goto L18;
            }
        L18:
            switch(r5) {
                case 49187: goto L38;
                case 49188: goto L2f;
                case 49189: goto L38;
                case 49190: goto L2f;
                case 49191: goto L38;
                case 49192: goto L2f;
                case 49193: goto L38;
                case 49194: goto L2f;
                case 49195: goto L38;
                case 49196: goto L2f;
                case 49197: goto L38;
                case 49198: goto L2f;
                case 49199: goto L38;
                case 49200: goto L2f;
                case 49201: goto L38;
                case 49202: goto L2f;
                default: goto L1b;
            }
        L1b:
            switch(r5) {
                case 49307: goto L2b;
                case 49308: goto L38;
                case 49309: goto L38;
                case 49310: goto L38;
                case 49311: goto L38;
                case 49312: goto L38;
                case 49313: goto L38;
                case 49314: goto L38;
                case 49315: goto L38;
                case 49316: goto L38;
                case 49317: goto L38;
                case 49318: goto L38;
                case 49319: goto L38;
                case 49320: goto L38;
                case 49321: goto L38;
                case 49322: goto L38;
                case 49323: goto L38;
                case 49324: goto L38;
                case 49325: goto L38;
                case 49326: goto L38;
                case 49327: goto L38;
                default: goto L1e;
            }
        L1e:
            switch(r5) {
                case 52392: goto L38;
                case 52393: goto L38;
                case 52394: goto L38;
                case 52395: goto L38;
                case 52396: goto L38;
                case 52397: goto L38;
                case 52398: goto L38;
                default: goto L21;
            }
        L21:
            switch(r5) {
                case 65280: goto L38;
                case 65281: goto L38;
                case 65282: goto L38;
                case 65283: goto L38;
                case 65284: goto L38;
                case 65285: goto L38;
                default: goto L24;
            }
        L24:
            switch(r5) {
                case 65296: goto L38;
                case 65297: goto L38;
                case 65298: goto L38;
                case 65299: goto L38;
                case 65300: goto L38;
                case 65301: goto L38;
                default: goto L27;
            }
        L27:
            if (r4 == 0) goto L2a
            return r0
        L2a:
            return r3
        L2b:
            if (r4 == 0) goto L2e
            return r2
        L2e:
            return r3
        L2f:
            if (r4 == 0) goto L32
            return r2
        L32:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        L38:
            if (r4 == 0) goto L3b
            return r0
        L3b:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext, int):int");
    }

    protected static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] opaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(opaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert((short) 47);
            }
        }
        return hashtable;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] opaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(opaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    protected static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z) throws IOException {
        Enumeration enumerationKeys = hashtable.keys();
        while (enumerationKeys.hasMoreElements()) {
            Integer num = (Integer) enumerationKeys.nextElement();
            int iIntValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z == (bArr.length == 0)) {
                TlsUtils.checkUint16(iIntValue);
                TlsUtils.writeUint16(iIntValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    protected static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    private void processAlertQueue() throws IOException {
        while (this.alertQueue.available() >= 2) {
            byte[] bArrRemoveData = this.alertQueue.removeData(2, 0);
            handleAlertMessage(bArrRemoveData[0], bArrRemoveData[1]);
        }
    }

    private void processApplicationDataQueue() {
    }

    private void processChangeCipherSpec(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert((short) 10);
            }
            this.recordStream.receivedReadCipherSpec();
            this.receivedChangeCipherSpec = true;
            handleChangeCipherSpecMessage();
        }
    }

    private void processHandshakeQueue(ByteQueue byteQueue) throws IOException {
        while (byteQueue.available() >= 4) {
            byte[] bArr = new byte[4];
            byteQueue.read(bArr, 0, 4, 0);
            short uint8 = TlsUtils.readUint8(bArr, 0);
            int uint24 = TlsUtils.readUint24(bArr, 1);
            int i = uint24 + 4;
            if (byteQueue.available() < i) {
                return;
            }
            if (uint8 != 0) {
                if (20 == uint8) {
                    checkReceivedChangeCipherSpec(true);
                    TlsContext context = getContext();
                    if (this.expected_verify_data == null && context.getSecurityParameters().getMasterSecret() != null) {
                        this.expected_verify_data = createVerifyData(!context.isServer());
                    }
                } else {
                    checkReceivedChangeCipherSpec(this.connection_state == 16);
                }
                byteQueue.copyTo(this.recordStream.getHandshakeHashUpdater(), i);
            }
            byteQueue.removeData(4);
            handleHandshakeMessage(uint8, byteQueue.readFrom(uint24));
        }
    }

    protected int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    protected void applyMaxFragmentLengthExtension() throws IOException {
        if (this.securityParameters.maxFragmentLength >= 0) {
            if (!MaxFragmentLength.isValid(this.securityParameters.maxFragmentLength)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.recordStream.setPlaintextLimit(1 << (this.securityParameters.maxFragmentLength + 8));
        }
    }

    protected void blockForHandshake() throws IOException {
        if (this.blocking) {
            while (this.connection_state != 16) {
                if (this.closed) {
                    throw new TlsFatalAlert((short) 80);
                }
                safeReadRecord();
            }
        }
    }

    protected void checkReceivedChangeCipherSpec(boolean z) throws IOException {
        if (z != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    protected void cleanupHandshake() {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    public void close() throws IOException {
        handleClose(true);
    }

    public void closeInput() throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
        }
        if (this.closed) {
            return;
        }
        if (this.inputBuffers.available() > 0) {
            throw new EOFException();
        }
        if (!this.appDataReady) {
            throw new TlsFatalAlert((short) 40);
        }
        throw new TlsNoCloseNotifyException();
    }

    protected void completeHandshake() throws IOException {
        try {
            this.connection_state = (short) 16;
            this.alertQueue.shrink();
            this.handshakeQueue.shrink();
            this.recordStream.finaliseHandshake();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(getContext());
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setExtendedMasterSecret(this.securityParameters.isExtendedMasterSecret()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                getContextAdmin().setResumableSession(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
        } finally {
            cleanupHandshake();
        }
    }

    protected byte[] createVerifyData(boolean z) {
        TlsContext context = getContext();
        return TlsUtils.calculateVerifyData(context, z ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.recordStream.getHandshakeHash(), z ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT));
    }

    protected void flush() throws IOException {
        this.recordStream.flush();
    }

    public int getAvailableInputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
        }
        return applicationDataAvailable();
    }

    public int getAvailableOutputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
        }
        return this.outputBuffer.getBuffer().available();
    }

    protected abstract TlsContext getContext();

    abstract AbstractTlsContext getContextAdmin();

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    protected abstract TlsPeer getPeer();

    protected void handleAlertMessage(short s, short s2) throws IOException {
        getPeer().notifyAlertReceived(s, s2);
        if (s == 1) {
            handleAlertWarningMessage(s2);
        } else {
            handleFailure();
            throw new TlsFatalAlertReceived(s2);
        }
    }

    protected void handleAlertWarningMessage(short s) throws IOException {
        if (s == 0) {
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleClose(false);
        }
    }

    protected void handleChangeCipherSpecMessage() throws IOException {
    }

    protected void handleClose(boolean z) throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (z && !this.appDataReady) {
            raiseAlertWarning((short) 90, "User canceled handshake");
        }
        raiseAlertWarning((short) 0, "Connection closed");
        this.recordStream.safeClose();
        if (this.appDataReady) {
            return;
        }
        cleanupHandshake();
    }

    protected void handleException(short s, String str, Throwable th) throws IOException {
        if (this.closed) {
            return;
        }
        raiseAlertFatal(s, str, th);
        handleFailure();
    }

    protected void handleFailure() {
        this.closed = true;
        this.failedWithError = true;
        invalidateSession();
        this.recordStream.safeClose();
        if (this.appDataReady) {
            return;
        }
        cleanupHandshake();
    }

    protected abstract void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException;

    protected void invalidateSession() {
        SessionParameters sessionParameters = this.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void offerInput(byte[] bArr) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        }
        if (this.closed) {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
        this.inputBuffers.addBytes(bArr);
        while (this.inputBuffers.available() >= 5) {
            byte[] bArr2 = new byte[5];
            this.inputBuffers.peek(bArr2);
            if (this.inputBuffers.available() < TlsUtils.readUint16(bArr2, 3) + 5) {
                safeCheckRecordHeader(bArr2);
                return;
            }
            safeReadRecord();
            if (this.closed) {
                if (this.connection_state != 16) {
                    throw new TlsFatalAlert((short) 80);
                }
                return;
            }
        }
    }

    public void offerOutput(byte[] bArr, int i, int i2) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        }
        if (!this.appDataReady) {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
        writeData(bArr, i, i2);
    }

    protected void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] bArr = this.expected_verify_data;
        if (bArr == null) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] fully = TlsUtils.readFully(bArr.length, byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(this.expected_verify_data, fully)) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    protected short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    protected void processRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        switch (s) {
            case 20:
                processChangeCipherSpec(bArr, i, i2);
                return;
            case 21:
                this.alertQueue.addData(bArr, i, i2);
                processAlertQueue();
                return;
            case 22:
                if (this.handshakeQueue.available() > 0) {
                    this.handshakeQueue.addData(bArr, i, i2);
                    processHandshakeQueue(this.handshakeQueue);
                    return;
                }
                ByteQueue byteQueue = new ByteQueue(bArr, i, i2);
                processHandshakeQueue(byteQueue);
                int iAvailable = byteQueue.available();
                if (iAvailable > 0) {
                    this.handshakeQueue.addData(bArr, (i + i2) - iAvailable, iAvailable);
                    return;
                }
                return;
            case 23:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.applicationDataQueue.addData(bArr, i, i2);
                processApplicationDataQueue();
                return;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    protected void raiseAlertFatal(short s, String str, Throwable th) throws IOException {
        getPeer().notifyAlertRaised((short) 2, s, str, th);
        try {
            this.recordStream.writeRecord((short) 21, new byte[]{2, (byte) s}, 0, 2);
        } catch (Exception unused) {
        }
    }

    protected void raiseAlertWarning(short s, String str) throws IOException {
        getPeer().notifyAlertRaised((short) 1, s, str, null);
        safeWriteRecord((short) 21, new byte[]{1, (byte) s}, 0, 2);
    }

    protected int readApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (this.closed) {
                if (this.failedWithError) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                if (this.appDataReady) {
                    return -1;
                }
                throw new IllegalStateException("Cannot read application data until initial handshake completed.");
            }
            safeReadRecord();
        }
        int iMin = Math.min(i2, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i, iMin, 0);
        return iMin;
    }

    public int readInput(byte[] bArr, int i, int i2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        try {
            return readApplicationData(bArr, i, Math.min(i2, applicationDataAvailable()));
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public int readOutput(byte[] bArr, int i, int i2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int iMin = Math.min(getAvailableOutputBytes(), i2);
        this.outputBuffer.getBuffer().removeData(bArr, i, iMin, 0);
        return iMin;
    }

    protected void refuseRenegotiation() throws IOException {
        if (TlsUtils.isSSL(getContext())) {
            throw new TlsFatalAlert((short) 40);
        }
        raiseAlertWarning((short) 100, "Renegotiation not supported");
    }

    protected void safeCheckRecordHeader(byte[] bArr) throws IOException {
        try {
            this.recordStream.checkRecordHeader(bArr);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to read record", e3);
            throw e3;
        }
    }

    protected void safeReadRecord() throws IOException {
        try {
            if (this.recordStream.readRecord()) {
                return;
            }
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleFailure();
            throw new TlsNoCloseNotifyException();
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (TlsFatalAlertReceived e3) {
            throw e3;
        } catch (IOException e4) {
            handleException((short) 80, "Failed to read record", e4);
            throw e4;
        }
    }

    protected void safeWriteRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        try {
            this.recordStream.writeRecord(s, bArr, i, i2);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to write record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to write record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to write record", e3);
            throw e3;
        }
    }

    protected void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseAlertWarning((short) 41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 11);
        certificate.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendChangeCipherSpecMessage() throws IOException {
        safeWriteRecord((short) 20, new byte[]{1}, 0, 1);
        this.recordStream.sentWriteCipherSpec();
    }

    protected void sendFinishedMessage() throws IOException {
        byte[] bArrCreateVerifyData = createVerifyData(getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage((short) 20, bArrCreateVerifyData.length);
        handshakeMessage.write(bArrCreateVerifyData);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 23);
        writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    protected void setAppDataSplitMode(int i) {
        if (i >= 0 && i <= 2) {
            this.appDataSplitMode = i;
        } else {
            throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i);
        }
    }

    protected void writeData(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write application data on closed/failed TLS connection");
        }
        while (i2 > 0) {
            if (this.appDataSplitEnabled) {
                int i3 = this.appDataSplitMode;
                if (i3 == 1) {
                    safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                } else if (i3 != 2) {
                    safeWriteRecord((short) 23, bArr, i, 1);
                    i++;
                    i2--;
                } else {
                    this.appDataSplitEnabled = false;
                    safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                }
            }
            if (i2 > 0) {
                int iMin = Math.min(i2, this.recordStream.getPlaintextLimit());
                safeWriteRecord((short) 23, bArr, i, iMin);
                i += iMin;
                i2 -= iMin;
            }
        }
    }

    protected void writeHandshakeMessage(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 4) {
            throw new TlsFatalAlert((short) 80);
        }
        if (TlsUtils.readUint8(bArr, i) != 0) {
            this.recordStream.getHandshakeHashUpdater().write(bArr, i, i2);
        }
        int i3 = 0;
        do {
            int iMin = Math.min(i2 - i3, this.recordStream.getPlaintextLimit());
            safeWriteRecord((short) 22, bArr, i + i3, iMin);
            i3 += iMin;
        } while (i3 < i2);
    }

    class HandshakeMessage extends ByteArrayOutputStream {
        HandshakeMessage(TlsProtocol tlsProtocol, short s) throws IOException {
            this(s, 60);
        }

        HandshakeMessage(short s, int i) throws IOException {
            super(i + 4);
            TlsUtils.writeUint8(s, (OutputStream) this);
            this.count += 3;
        }

        void writeToRecordStream() throws IOException {
            int i = this.count - 4;
            TlsUtils.checkUint24(i);
            TlsUtils.writeUint24(i, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}
