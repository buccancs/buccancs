package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.bouncycastle.crypto.tls.DTLSReliableHandshake;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class DTLSClientProtocol extends DTLSProtocol {

    public DTLSClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    protected static byte[] patchClientHelloWithCookie(byte[] bArr, byte[] bArr2) throws IOException {
        short uint8 = TlsUtils.readUint8(bArr, 34);
        int i = 35 + uint8;
        int i2 = uint8 + 36;
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, i);
        TlsUtils.checkUint8(bArr2.length);
        TlsUtils.writeUint8(bArr2.length, bArr3, i);
        System.arraycopy(bArr2, 0, bArr3, i2, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, bArr2.length + i2, bArr.length - i2);
        return bArr3;
    }

    protected void abortClientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        invalidateSession(clientHandshakeState);
    }

    protected DTLSTransport clientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        DTLSReliableHandshake.Message messageReceiveMessage;
        Certificate certificateProcessServerCertificate;
        SecurityParameters securityParameters = clientHandshakeState.clientContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(clientHandshakeState.clientContext, dTLSRecordLayer);
        byte[] bArrGenerateClientHello = generateClientHello(clientHandshakeState, clientHandshakeState.client);
        dTLSRecordLayer.setWriteVersion(ProtocolVersion.DTLSv10);
        dTLSReliableHandshake.sendMessage((short) 1, bArrGenerateClientHello);
        while (true) {
            DTLSReliableHandshake.Message messageReceiveMessage2 = dTLSReliableHandshake.receiveMessage();
            if (messageReceiveMessage2.getType() != 3) {
                if (messageReceiveMessage2.getType() != 2) {
                    throw new TlsFatalAlert((short) 10);
                }
                ProtocolVersion readVersion = dTLSRecordLayer.getReadVersion();
                reportServerVersion(clientHandshakeState, readVersion);
                dTLSRecordLayer.setWriteVersion(readVersion);
                processServerHello(clientHandshakeState, messageReceiveMessage2.getBody());
                dTLSReliableHandshake.notifyHelloComplete();
                applyMaxFragmentLengthExtension(dTLSRecordLayer, securityParameters.maxFragmentLength);
                if (clientHandshakeState.resumedSession) {
                    securityParameters.masterSecret = Arrays.clone(clientHandshakeState.sessionParameters.getMasterSecret());
                    dTLSRecordLayer.initPendingEpoch(clientHandshakeState.client.getCipher());
                    processFinished(dTLSReliableHandshake.receiveMessageBody((short) 20), TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                    dTLSReliableHandshake.sendMessage((short) 20, TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                    dTLSReliableHandshake.finish();
                    clientHandshakeState.clientContext.setResumableSession(clientHandshakeState.tlsSession);
                    clientHandshakeState.client.notifyHandshakeComplete();
                    return new DTLSTransport(dTLSRecordLayer);
                }
                invalidateSession(clientHandshakeState);
                if (clientHandshakeState.selectedSessionID.length > 0) {
                    clientHandshakeState.tlsSession = new TlsSessionImpl(clientHandshakeState.selectedSessionID, null);
                }
                DTLSReliableHandshake.Message messageReceiveMessage3 = dTLSReliableHandshake.receiveMessage();
                if (messageReceiveMessage3.getType() == 23) {
                    processServerSupplementalData(clientHandshakeState, messageReceiveMessage3.getBody());
                    messageReceiveMessage3 = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState.client.processServerSupplementalData(null);
                }
                clientHandshakeState.keyExchange = clientHandshakeState.client.getKeyExchange();
                clientHandshakeState.keyExchange.init(clientHandshakeState.clientContext);
                if (messageReceiveMessage3.getType() == 11) {
                    certificateProcessServerCertificate = processServerCertificate(clientHandshakeState, messageReceiveMessage3.getBody());
                    messageReceiveMessage = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState.keyExchange.skipServerCredentials();
                    messageReceiveMessage = messageReceiveMessage3;
                    certificateProcessServerCertificate = null;
                }
                if (certificateProcessServerCertificate == null || certificateProcessServerCertificate.isEmpty()) {
                    clientHandshakeState.allowCertificateStatus = false;
                }
                if (messageReceiveMessage.getType() == 22) {
                    processCertificateStatus(clientHandshakeState, messageReceiveMessage.getBody());
                    messageReceiveMessage = dTLSReliableHandshake.receiveMessage();
                }
                if (messageReceiveMessage.getType() == 12) {
                    processServerKeyExchange(clientHandshakeState, messageReceiveMessage.getBody());
                    messageReceiveMessage = dTLSReliableHandshake.receiveMessage();
                } else {
                    clientHandshakeState.keyExchange.skipServerKeyExchange();
                }
                if (messageReceiveMessage.getType() == 13) {
                    processCertificateRequest(clientHandshakeState, messageReceiveMessage.getBody());
                    TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), clientHandshakeState.certificateRequest.getSupportedSignatureAlgorithms());
                    messageReceiveMessage = dTLSReliableHandshake.receiveMessage();
                }
                if (messageReceiveMessage.getType() != 14) {
                    throw new TlsFatalAlert((short) 10);
                }
                if (messageReceiveMessage.getBody().length != 0) {
                    throw new TlsFatalAlert((short) 50);
                }
                dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
                Vector clientSupplementalData = clientHandshakeState.client.getClientSupplementalData();
                if (clientSupplementalData != null) {
                    dTLSReliableHandshake.sendMessage((short) 23, generateSupplementalData(clientSupplementalData));
                }
                if (clientHandshakeState.certificateRequest != null) {
                    clientHandshakeState.clientCredentials = clientHandshakeState.authentication.getClientCredentials(clientHandshakeState.certificateRequest);
                    Certificate certificate = clientHandshakeState.clientCredentials != null ? clientHandshakeState.clientCredentials.getCertificate() : null;
                    if (certificate == null) {
                        certificate = Certificate.EMPTY_CHAIN;
                    }
                    dTLSReliableHandshake.sendMessage((short) 11, generateCertificate(certificate));
                }
                if (clientHandshakeState.clientCredentials != null) {
                    clientHandshakeState.keyExchange.processClientCredentials(clientHandshakeState.clientCredentials);
                } else {
                    clientHandshakeState.keyExchange.skipClientCredentials();
                }
                dTLSReliableHandshake.sendMessage((short) 16, generateClientKeyExchange(clientHandshakeState));
                TlsHandshakeHash tlsHandshakeHashPrepareToFinish = dTLSReliableHandshake.prepareToFinish();
                securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, tlsHandshakeHashPrepareToFinish, null);
                TlsProtocol.establishMasterSecret(clientHandshakeState.clientContext, clientHandshakeState.keyExchange);
                dTLSRecordLayer.initPendingEpoch(clientHandshakeState.client.getCipher());
                if (clientHandshakeState.clientCredentials != null && (clientHandshakeState.clientCredentials instanceof TlsSignerCredentials)) {
                    TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) clientHandshakeState.clientCredentials;
                    SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(clientHandshakeState.clientContext, tlsSignerCredentials);
                    dTLSReliableHandshake.sendMessage((short) 15, generateCertificateVerify(clientHandshakeState, new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(signatureAndHashAlgorithm == null ? securityParameters.getSessionHash() : tlsHandshakeHashPrepareToFinish.getFinalHash(signatureAndHashAlgorithm.getHash())))));
                }
                dTLSReliableHandshake.sendMessage((short) 20, TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                if (clientHandshakeState.expectSessionTicket) {
                    DTLSReliableHandshake.Message messageReceiveMessage4 = dTLSReliableHandshake.receiveMessage();
                    if (messageReceiveMessage4.getType() != 4) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    processNewSessionTicket(clientHandshakeState, messageReceiveMessage4.getBody());
                }
                processFinished(dTLSReliableHandshake.receiveMessageBody((short) 20), TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                dTLSReliableHandshake.finish();
                if (clientHandshakeState.tlsSession != null) {
                    clientHandshakeState.sessionParameters = new SessionParameters.Builder().setCipherSuite(securityParameters.getCipherSuite()).setCompressionAlgorithm(securityParameters.getCompressionAlgorithm()).setExtendedMasterSecret(securityParameters.isExtendedMasterSecret()).setMasterSecret(securityParameters.getMasterSecret()).setPeerCertificate(certificateProcessServerCertificate).setPSKIdentity(securityParameters.getPSKIdentity()).setSRPIdentity(securityParameters.getSRPIdentity()).setServerExtensions(clientHandshakeState.serverExtensions).build();
                    clientHandshakeState.tlsSession = TlsUtils.importSession(clientHandshakeState.tlsSession.getSessionID(), clientHandshakeState.sessionParameters);
                    clientHandshakeState.clientContext.setResumableSession(clientHandshakeState.tlsSession);
                }
                clientHandshakeState.client.notifyHandshakeComplete();
                return new DTLSTransport(dTLSRecordLayer);
            }
            if (!dTLSRecordLayer.getReadVersion().isEqualOrEarlierVersionOf(clientHandshakeState.clientContext.getClientVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
            dTLSRecordLayer.setReadVersion(null);
            byte[] bArrPatchClientHelloWithCookie = patchClientHelloWithCookie(bArrGenerateClientHello, processHelloVerifyRequest(clientHandshakeState, messageReceiveMessage2.getBody()));
            dTLSReliableHandshake.resetHandshakeMessagesDigest();
            dTLSReliableHandshake.sendMessage((short) 1, bArrPatchClientHelloWithCookie);
        }
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) throws IOException {
        SessionParameters sessionParametersExportSessionParameters;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'client' cannot be null");
        }
        if (datagramTransport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 1;
        ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
        clientHandshakeState.client = tlsClient;
        clientHandshakeState.clientContext = new TlsClientContextImpl(this.secureRandom, securityParameters);
        securityParameters.clientRandom = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), clientHandshakeState.clientContext.getNonceRandomGenerator());
        tlsClient.init(clientHandshakeState.clientContext);
        DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, clientHandshakeState.clientContext, tlsClient, (short) 22);
        TlsSession sessionToResume = clientHandshakeState.client.getSessionToResume();
        if (sessionToResume != null && sessionToResume.isResumable() && (sessionParametersExportSessionParameters = sessionToResume.exportSessionParameters()) != null && sessionParametersExportSessionParameters.isExtendedMasterSecret()) {
            clientHandshakeState.tlsSession = sessionToResume;
            clientHandshakeState.sessionParameters = sessionParametersExportSessionParameters;
        }
        try {
            try {
                try {
                    try {
                        return clientHandshake(clientHandshakeState, dTLSRecordLayer);
                    } catch (RuntimeException e) {
                        abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short) 80);
                        throw new TlsFatalAlert((short) 80, e);
                    }
                } catch (TlsFatalAlert e2) {
                    abortClientHandshake(clientHandshakeState, dTLSRecordLayer, e2.getAlertDescription());
                    throw e2;
                }
            } catch (IOException e3) {
                abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short) 80);
                throw e3;
            }
        } finally {
            securityParameters.clear();
        }
    }

    protected byte[] generateCertificateVerify(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateClientHello(ClientHandshakeState clientHandshakeState, TlsClient tlsClient) throws IOException {
        ProtocolVersion clientVersion = tlsClient.getClientVersion();
        if (!clientVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        tlsClientContextImpl.setClientVersion(clientVersion);
        SecurityParameters securityParameters = tlsClientContextImpl.getSecurityParameters();
        byte[] sessionID = TlsUtils.EMPTY_BYTES;
        if (clientHandshakeState.tlsSession != null && ((sessionID = clientHandshakeState.tlsSession.getSessionID()) == null || sessionID.length > 32)) {
            sessionID = TlsUtils.EMPTY_BYTES;
        }
        boolean zIsFallback = tlsClient.isFallback();
        clientHandshakeState.offeredCipherSuites = tlsClient.getCipherSuites();
        if (sessionID.length > 0 && clientHandshakeState.sessionParameters != null && (!clientHandshakeState.sessionParameters.isExtendedMasterSecret() || !Arrays.contains(clientHandshakeState.offeredCipherSuites, clientHandshakeState.sessionParameters.getCipherSuite()) || clientHandshakeState.sessionParameters.getCompressionAlgorithm() != 0)) {
            sessionID = TlsUtils.EMPTY_BYTES;
        }
        clientHandshakeState.clientExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(tlsClient.getClientExtensions());
        TlsExtensionsUtils.addExtendedMasterSecretExtension(clientHandshakeState.clientExtensions);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsUtils.writeVersion(clientVersion, byteArrayOutputStream);
        byteArrayOutputStream.write(securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(sessionID, byteArrayOutputStream);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        boolean z = TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null;
        boolean z2 = !Arrays.contains(clientHandshakeState.offeredCipherSuites, 255);
        if (z && z2) {
            clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, 255);
        }
        if (zIsFallback && !Arrays.contains(clientHandshakeState.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV)) {
            clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(clientHandshakeState.offeredCipherSuites, byteArrayOutputStream);
        TlsUtils.writeUint8ArrayWithUint8Length(new short[]{0}, byteArrayOutputStream);
        TlsProtocol.writeExtensions(byteArrayOutputStream, clientHandshakeState.clientExtensions);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateClientKeyExchange(ClientHandshakeState clientHandshakeState) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.keyExchange.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void invalidateSession(ClientHandshakeState clientHandshakeState) {
        if (clientHandshakeState.sessionParameters != null) {
            clientHandshakeState.sessionParameters.clear();
            clientHandshakeState.sessionParameters = null;
        }
        if (clientHandshakeState.tlsSession != null) {
            clientHandshakeState.tlsSession.invalidate();
            clientHandshakeState.tlsSession = null;
        }
    }

    protected void processCertificateRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (clientHandshakeState.authentication == null) {
            throw new TlsFatalAlert((short) 40);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.certificateRequest = CertificateRequest.parse(clientHandshakeState.clientContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.keyExchange.validateCertificateRequest(clientHandshakeState.certificateRequest);
    }

    protected void processCertificateStatus(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (!clientHandshakeState.allowCertificateStatus) {
            throw new TlsFatalAlert((short) 10);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected byte[] processHelloVerifyRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion version = TlsUtils.readVersion(byteArrayInputStream);
        byte[] opaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!version.isEqualOrEarlierVersionOf(clientHandshakeState.clientContext.getClientVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        if (ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(version) || opaque8.length <= 32) {
            return opaque8;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void processNewSessionTicket(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.client.notifyNewSessionTicket(newSessionTicket);
    }

    protected Certificate processServerCertificate(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.keyExchange.processServerCertificate(certificate);
        clientHandshakeState.authentication = clientHandshakeState.client.getAuthentication();
        clientHandshakeState.authentication.notifyServerCertificate(certificate);
        return certificate;
    }

    protected void processServerHello(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        SecurityParameters securityParameters = clientHandshakeState.clientContext.getSecurityParameters();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        reportServerVersion(clientHandshakeState, TlsUtils.readVersion(byteArrayInputStream));
        securityParameters.serverRandom = TlsUtils.readFully(32, byteArrayInputStream);
        clientHandshakeState.selectedSessionID = TlsUtils.readOpaque8(byteArrayInputStream);
        if (clientHandshakeState.selectedSessionID.length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        clientHandshakeState.client.notifySessionID(clientHandshakeState.selectedSessionID);
        boolean z = false;
        clientHandshakeState.resumedSession = clientHandshakeState.selectedSessionID.length > 0 && clientHandshakeState.tlsSession != null && Arrays.areEqual(clientHandshakeState.selectedSessionID, clientHandshakeState.tlsSession.getSessionID());
        int uint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(clientHandshakeState.offeredCipherSuites, uint16) || uint16 == 0 || CipherSuite.isSCSV(uint16) || !TlsUtils.isValidCipherSuiteForVersion(uint16, clientHandshakeState.clientContext.getServerVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        validateSelectedCipherSuite(uint16, (short) 47);
        clientHandshakeState.client.notifySelectedCipherSuite(uint16);
        short uint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (uint8 != 0) {
            throw new TlsFatalAlert((short) 47);
        }
        clientHandshakeState.client.notifySelectedCompressionMethod(uint8);
        clientHandshakeState.serverExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(clientHandshakeState.serverExtensions);
        if (!securityParameters.isExtendedMasterSecret() && (clientHandshakeState.resumedSession || clientHandshakeState.client.requiresExtendedMasterSecret())) {
            throw new TlsFatalAlert((short) 40);
        }
        if (clientHandshakeState.serverExtensions != null) {
            Enumeration enumerationKeys = clientHandshakeState.serverExtensions.keys();
            while (enumerationKeys.hasMoreElements()) {
                Integer num = (Integer) enumerationKeys.nextElement();
                if (!num.equals(TlsProtocol.EXT_RenegotiationInfo)) {
                    if (TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, num) == null) {
                        throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                    }
                    boolean z2 = clientHandshakeState.resumedSession;
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(clientHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            clientHandshakeState.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        clientHandshakeState.client.notifySecureRenegotiation(clientHandshakeState.secure_renegotiation);
        Hashtable hashtable = clientHandshakeState.clientExtensions;
        Hashtable serverExtensions = clientHandshakeState.serverExtensions;
        if (clientHandshakeState.resumedSession) {
            if (uint16 != clientHandshakeState.sessionParameters.getCipherSuite() || uint8 != clientHandshakeState.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert((short) 47);
            }
            serverExtensions = clientHandshakeState.sessionParameters.readServerExtensions();
            hashtable = null;
        }
        securityParameters.cipherSuite = uint16;
        securityParameters.compressionAlgorithm = uint8;
        if (serverExtensions != null && !serverExtensions.isEmpty()) {
            boolean zHasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(serverExtensions);
            if (zHasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParameters.getCipherSuite())) {
                throw new TlsFatalAlert((short) 47);
            }
            securityParameters.encryptThenMAC = zHasEncryptThenMACExtension;
            securityParameters.maxFragmentLength = evaluateMaxFragmentLengthExtension(clientHandshakeState.resumedSession, hashtable, serverExtensions, (short) 47);
            securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverExtensions);
            clientHandshakeState.allowCertificateStatus = !clientHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 47);
            if (!clientHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z = true;
            }
            clientHandshakeState.expectSessionTicket = z;
        }
        if (hashtable != null) {
            clientHandshakeState.client.processServerExtensions(serverExtensions);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(clientHandshakeState.clientContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
    }

    protected void processServerKeyExchange(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.keyExchange.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processServerSupplementalData(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        clientHandshakeState.client.processServerSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    protected void reportServerVersion(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) throws IOException {
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        ProtocolVersion serverVersion = tlsClientContextImpl.getServerVersion();
        if (serverVersion == null) {
            tlsClientContextImpl.setServerVersion(protocolVersion);
            clientHandshakeState.client.notifyServerVersion(protocolVersion);
        } else if (!serverVersion.equals(protocolVersion)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    protected static class ClientHandshakeState {
        TlsClient client = null;
        TlsClientContextImpl clientContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        byte[] selectedSessionID = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        boolean allowCertificateStatus = false;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        TlsAuthentication authentication = null;
        CertificateStatus certificateStatus = null;
        CertificateRequest certificateRequest = null;
        TlsCredentials clientCredentials = null;

        protected ClientHandshakeState() {
        }
    }
}
