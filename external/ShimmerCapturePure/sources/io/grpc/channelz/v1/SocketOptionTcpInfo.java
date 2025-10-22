package io.grpc.channelz.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes2.dex */
public final class SocketOptionTcpInfo extends GeneratedMessageV3 implements SocketOptionTcpInfoOrBuilder {
    public static final int TCPI_ADVMSS_FIELD_NUMBER = 28;
    public static final int TCPI_ATO_FIELD_NUMBER = 10;
    public static final int TCPI_BACKOFF_FIELD_NUMBER = 5;
    public static final int TCPI_CA_STATE_FIELD_NUMBER = 2;
    public static final int TCPI_FACKETS_FIELD_NUMBER = 17;
    public static final int TCPI_LAST_ACK_RECV_FIELD_NUMBER = 21;
    public static final int TCPI_LAST_ACK_SENT_FIELD_NUMBER = 19;
    public static final int TCPI_LAST_DATA_RECV_FIELD_NUMBER = 20;
    public static final int TCPI_LAST_DATA_SENT_FIELD_NUMBER = 18;
    public static final int TCPI_LOST_FIELD_NUMBER = 15;
    public static final int TCPI_OPTIONS_FIELD_NUMBER = 6;
    public static final int TCPI_PMTU_FIELD_NUMBER = 22;
    public static final int TCPI_PROBES_FIELD_NUMBER = 4;
    public static final int TCPI_RCV_MSS_FIELD_NUMBER = 12;
    public static final int TCPI_RCV_SSTHRESH_FIELD_NUMBER = 23;
    public static final int TCPI_RCV_WSCALE_FIELD_NUMBER = 8;
    public static final int TCPI_REORDERING_FIELD_NUMBER = 29;
    public static final int TCPI_RETRANSMITS_FIELD_NUMBER = 3;
    public static final int TCPI_RETRANS_FIELD_NUMBER = 16;
    public static final int TCPI_RTO_FIELD_NUMBER = 9;
    public static final int TCPI_RTTVAR_FIELD_NUMBER = 25;
    public static final int TCPI_RTT_FIELD_NUMBER = 24;
    public static final int TCPI_SACKED_FIELD_NUMBER = 14;
    public static final int TCPI_SND_CWND_FIELD_NUMBER = 27;
    public static final int TCPI_SND_MSS_FIELD_NUMBER = 11;
    public static final int TCPI_SND_SSTHRESH_FIELD_NUMBER = 26;
    public static final int TCPI_SND_WSCALE_FIELD_NUMBER = 7;
    public static final int TCPI_STATE_FIELD_NUMBER = 1;
    public static final int TCPI_UNACKED_FIELD_NUMBER = 13;
    private static final SocketOptionTcpInfo DEFAULT_INSTANCE = new SocketOptionTcpInfo();
    private static final Parser<SocketOptionTcpInfo> PARSER = new AbstractParser<SocketOptionTcpInfo>() { // from class: io.grpc.channelz.v1.SocketOptionTcpInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SocketOptionTcpInfo m8874parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SocketOptionTcpInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int tcpiAdvmss_;
    private int tcpiAto_;
    private int tcpiBackoff_;
    private int tcpiCaState_;
    private int tcpiFackets_;
    private int tcpiLastAckRecv_;
    private int tcpiLastAckSent_;
    private int tcpiLastDataRecv_;
    private int tcpiLastDataSent_;
    private int tcpiLost_;
    private int tcpiOptions_;
    private int tcpiPmtu_;
    private int tcpiProbes_;
    private int tcpiRcvMss_;
    private int tcpiRcvSsthresh_;
    private int tcpiRcvWscale_;
    private int tcpiReordering_;
    private int tcpiRetrans_;
    private int tcpiRetransmits_;
    private int tcpiRto_;
    private int tcpiRtt_;
    private int tcpiRttvar_;
    private int tcpiSacked_;
    private int tcpiSndCwnd_;
    private int tcpiSndMss_;
    private int tcpiSndSsthresh_;
    private int tcpiSndWscale_;
    private int tcpiState_;
    private int tcpiUnacked_;

    private SocketOptionTcpInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketOptionTcpInfo() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketOptionTcpInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 8:
                            this.tcpiState_ = codedInputStream.readUInt32();
                        case 16:
                            this.tcpiCaState_ = codedInputStream.readUInt32();
                        case 24:
                            this.tcpiRetransmits_ = codedInputStream.readUInt32();
                        case 32:
                            this.tcpiProbes_ = codedInputStream.readUInt32();
                        case 40:
                            this.tcpiBackoff_ = codedInputStream.readUInt32();
                        case 48:
                            this.tcpiOptions_ = codedInputStream.readUInt32();
                        case 56:
                            this.tcpiSndWscale_ = codedInputStream.readUInt32();
                        case 64:
                            this.tcpiRcvWscale_ = codedInputStream.readUInt32();
                        case 72:
                            this.tcpiRto_ = codedInputStream.readUInt32();
                        case 80:
                            this.tcpiAto_ = codedInputStream.readUInt32();
                        case BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE:
                            this.tcpiSndMss_ = codedInputStream.readUInt32();
                        case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                            this.tcpiRcvMss_ = codedInputStream.readUInt32();
                        case 104:
                            this.tcpiUnacked_ = codedInputStream.readUInt32();
                        case 112:
                            this.tcpiSacked_ = codedInputStream.readUInt32();
                        case 120:
                            this.tcpiLost_ = codedInputStream.readUInt32();
                        case 128:
                            this.tcpiRetrans_ = codedInputStream.readUInt32();
                        case 136:
                            this.tcpiFackets_ = codedInputStream.readUInt32();
                        case 144:
                            this.tcpiLastDataSent_ = codedInputStream.readUInt32();
                        case 152:
                            this.tcpiLastAckSent_ = codedInputStream.readUInt32();
                        case 160:
                            this.tcpiLastDataRecv_ = codedInputStream.readUInt32();
                        case 168:
                            this.tcpiLastAckRecv_ = codedInputStream.readUInt32();
                        case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
                            this.tcpiPmtu_ = codedInputStream.readUInt32();
                        case 184:
                            this.tcpiRcvSsthresh_ = codedInputStream.readUInt32();
                        case 192:
                            this.tcpiRtt_ = codedInputStream.readUInt32();
                        case 200:
                            this.tcpiRttvar_ = codedInputStream.readUInt32();
                        case 208:
                            this.tcpiSndSsthresh_ = codedInputStream.readUInt32();
                        case 216:
                            this.tcpiSndCwnd_ = codedInputStream.readUInt32();
                        case 224:
                            this.tcpiAdvmss_ = codedInputStream.readUInt32();
                        case 232:
                            this.tcpiReordering_ = codedInputStream.readUInt32();
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static SocketOptionTcpInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SocketOptionTcpInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionTcpInfo_descriptor;
    }

    public static SocketOptionTcpInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(byteBuffer);
    }

    public static SocketOptionTcpInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SocketOptionTcpInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(byteString);
    }

    public static SocketOptionTcpInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SocketOptionTcpInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(bArr);
    }

    public static SocketOptionTcpInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionTcpInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SocketOptionTcpInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SocketOptionTcpInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOptionTcpInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SocketOptionTcpInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOptionTcpInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SocketOptionTcpInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8872toBuilder();
    }

    public static Builder newBuilder(SocketOptionTcpInfo socketOptionTcpInfo) {
        return DEFAULT_INSTANCE.m8872toBuilder().mergeFrom(socketOptionTcpInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SocketOptionTcpInfo m8867getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SocketOptionTcpInfo> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiAdvmss() {
        return this.tcpiAdvmss_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiAto() {
        return this.tcpiAto_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiBackoff() {
        return this.tcpiBackoff_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiCaState() {
        return this.tcpiCaState_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiFackets() {
        return this.tcpiFackets_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiLastAckRecv() {
        return this.tcpiLastAckRecv_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiLastAckSent() {
        return this.tcpiLastAckSent_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiLastDataRecv() {
        return this.tcpiLastDataRecv_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiLastDataSent() {
        return this.tcpiLastDataSent_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiLost() {
        return this.tcpiLost_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiOptions() {
        return this.tcpiOptions_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiPmtu() {
        return this.tcpiPmtu_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiProbes() {
        return this.tcpiProbes_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRcvMss() {
        return this.tcpiRcvMss_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRcvSsthresh() {
        return this.tcpiRcvSsthresh_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRcvWscale() {
        return this.tcpiRcvWscale_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiReordering() {
        return this.tcpiReordering_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRetrans() {
        return this.tcpiRetrans_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRetransmits() {
        return this.tcpiRetransmits_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRto() {
        return this.tcpiRto_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRtt() {
        return this.tcpiRtt_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiRttvar() {
        return this.tcpiRttvar_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiSacked() {
        return this.tcpiSacked_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiSndCwnd() {
        return this.tcpiSndCwnd_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiSndMss() {
        return this.tcpiSndMss_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiSndSsthresh() {
        return this.tcpiSndSsthresh_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiSndWscale() {
        return this.tcpiSndWscale_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiState() {
        return this.tcpiState_;
    }

    @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
    public int getTcpiUnacked() {
        return this.tcpiUnacked_;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SocketOptionTcpInfo();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionTcpInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOptionTcpInfo.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.tcpiState_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.tcpiCaState_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
        int i3 = this.tcpiRetransmits_;
        if (i3 != 0) {
            codedOutputStream.writeUInt32(3, i3);
        }
        int i4 = this.tcpiProbes_;
        if (i4 != 0) {
            codedOutputStream.writeUInt32(4, i4);
        }
        int i5 = this.tcpiBackoff_;
        if (i5 != 0) {
            codedOutputStream.writeUInt32(5, i5);
        }
        int i6 = this.tcpiOptions_;
        if (i6 != 0) {
            codedOutputStream.writeUInt32(6, i6);
        }
        int i7 = this.tcpiSndWscale_;
        if (i7 != 0) {
            codedOutputStream.writeUInt32(7, i7);
        }
        int i8 = this.tcpiRcvWscale_;
        if (i8 != 0) {
            codedOutputStream.writeUInt32(8, i8);
        }
        int i9 = this.tcpiRto_;
        if (i9 != 0) {
            codedOutputStream.writeUInt32(9, i9);
        }
        int i10 = this.tcpiAto_;
        if (i10 != 0) {
            codedOutputStream.writeUInt32(10, i10);
        }
        int i11 = this.tcpiSndMss_;
        if (i11 != 0) {
            codedOutputStream.writeUInt32(11, i11);
        }
        int i12 = this.tcpiRcvMss_;
        if (i12 != 0) {
            codedOutputStream.writeUInt32(12, i12);
        }
        int i13 = this.tcpiUnacked_;
        if (i13 != 0) {
            codedOutputStream.writeUInt32(13, i13);
        }
        int i14 = this.tcpiSacked_;
        if (i14 != 0) {
            codedOutputStream.writeUInt32(14, i14);
        }
        int i15 = this.tcpiLost_;
        if (i15 != 0) {
            codedOutputStream.writeUInt32(15, i15);
        }
        int i16 = this.tcpiRetrans_;
        if (i16 != 0) {
            codedOutputStream.writeUInt32(16, i16);
        }
        int i17 = this.tcpiFackets_;
        if (i17 != 0) {
            codedOutputStream.writeUInt32(17, i17);
        }
        int i18 = this.tcpiLastDataSent_;
        if (i18 != 0) {
            codedOutputStream.writeUInt32(18, i18);
        }
        int i19 = this.tcpiLastAckSent_;
        if (i19 != 0) {
            codedOutputStream.writeUInt32(19, i19);
        }
        int i20 = this.tcpiLastDataRecv_;
        if (i20 != 0) {
            codedOutputStream.writeUInt32(20, i20);
        }
        int i21 = this.tcpiLastAckRecv_;
        if (i21 != 0) {
            codedOutputStream.writeUInt32(21, i21);
        }
        int i22 = this.tcpiPmtu_;
        if (i22 != 0) {
            codedOutputStream.writeUInt32(22, i22);
        }
        int i23 = this.tcpiRcvSsthresh_;
        if (i23 != 0) {
            codedOutputStream.writeUInt32(23, i23);
        }
        int i24 = this.tcpiRtt_;
        if (i24 != 0) {
            codedOutputStream.writeUInt32(24, i24);
        }
        int i25 = this.tcpiRttvar_;
        if (i25 != 0) {
            codedOutputStream.writeUInt32(25, i25);
        }
        int i26 = this.tcpiSndSsthresh_;
        if (i26 != 0) {
            codedOutputStream.writeUInt32(26, i26);
        }
        int i27 = this.tcpiSndCwnd_;
        if (i27 != 0) {
            codedOutputStream.writeUInt32(27, i27);
        }
        int i28 = this.tcpiAdvmss_;
        if (i28 != 0) {
            codedOutputStream.writeUInt32(28, i28);
        }
        int i29 = this.tcpiReordering_;
        if (i29 != 0) {
            codedOutputStream.writeUInt32(29, i29);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.tcpiState_;
        int iComputeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) : 0;
        int i3 = this.tcpiCaState_;
        if (i3 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
        }
        int i4 = this.tcpiRetransmits_;
        if (i4 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(3, i4);
        }
        int i5 = this.tcpiProbes_;
        if (i5 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(4, i5);
        }
        int i6 = this.tcpiBackoff_;
        if (i6 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(5, i6);
        }
        int i7 = this.tcpiOptions_;
        if (i7 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(6, i7);
        }
        int i8 = this.tcpiSndWscale_;
        if (i8 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(7, i8);
        }
        int i9 = this.tcpiRcvWscale_;
        if (i9 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(8, i9);
        }
        int i10 = this.tcpiRto_;
        if (i10 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(9, i10);
        }
        int i11 = this.tcpiAto_;
        if (i11 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(10, i11);
        }
        int i12 = this.tcpiSndMss_;
        if (i12 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(11, i12);
        }
        int i13 = this.tcpiRcvMss_;
        if (i13 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(12, i13);
        }
        int i14 = this.tcpiUnacked_;
        if (i14 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(13, i14);
        }
        int i15 = this.tcpiSacked_;
        if (i15 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(14, i15);
        }
        int i16 = this.tcpiLost_;
        if (i16 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(15, i16);
        }
        int i17 = this.tcpiRetrans_;
        if (i17 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(16, i17);
        }
        int i18 = this.tcpiFackets_;
        if (i18 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(17, i18);
        }
        int i19 = this.tcpiLastDataSent_;
        if (i19 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(18, i19);
        }
        int i20 = this.tcpiLastAckSent_;
        if (i20 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(19, i20);
        }
        int i21 = this.tcpiLastDataRecv_;
        if (i21 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(20, i21);
        }
        int i22 = this.tcpiLastAckRecv_;
        if (i22 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(21, i22);
        }
        int i23 = this.tcpiPmtu_;
        if (i23 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(22, i23);
        }
        int i24 = this.tcpiRcvSsthresh_;
        if (i24 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(23, i24);
        }
        int i25 = this.tcpiRtt_;
        if (i25 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(24, i25);
        }
        int i26 = this.tcpiRttvar_;
        if (i26 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(25, i26);
        }
        int i27 = this.tcpiSndSsthresh_;
        if (i27 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(26, i27);
        }
        int i28 = this.tcpiSndCwnd_;
        if (i28 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(27, i28);
        }
        int i29 = this.tcpiAdvmss_;
        if (i29 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(28, i29);
        }
        int i30 = this.tcpiReordering_;
        if (i30 != 0) {
            iComputeUInt32Size += CodedOutputStream.computeUInt32Size(29, i30);
        }
        int serializedSize = iComputeUInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocketOptionTcpInfo)) {
            return super.equals(obj);
        }
        SocketOptionTcpInfo socketOptionTcpInfo = (SocketOptionTcpInfo) obj;
        return getTcpiState() == socketOptionTcpInfo.getTcpiState() && getTcpiCaState() == socketOptionTcpInfo.getTcpiCaState() && getTcpiRetransmits() == socketOptionTcpInfo.getTcpiRetransmits() && getTcpiProbes() == socketOptionTcpInfo.getTcpiProbes() && getTcpiBackoff() == socketOptionTcpInfo.getTcpiBackoff() && getTcpiOptions() == socketOptionTcpInfo.getTcpiOptions() && getTcpiSndWscale() == socketOptionTcpInfo.getTcpiSndWscale() && getTcpiRcvWscale() == socketOptionTcpInfo.getTcpiRcvWscale() && getTcpiRto() == socketOptionTcpInfo.getTcpiRto() && getTcpiAto() == socketOptionTcpInfo.getTcpiAto() && getTcpiSndMss() == socketOptionTcpInfo.getTcpiSndMss() && getTcpiRcvMss() == socketOptionTcpInfo.getTcpiRcvMss() && getTcpiUnacked() == socketOptionTcpInfo.getTcpiUnacked() && getTcpiSacked() == socketOptionTcpInfo.getTcpiSacked() && getTcpiLost() == socketOptionTcpInfo.getTcpiLost() && getTcpiRetrans() == socketOptionTcpInfo.getTcpiRetrans() && getTcpiFackets() == socketOptionTcpInfo.getTcpiFackets() && getTcpiLastDataSent() == socketOptionTcpInfo.getTcpiLastDataSent() && getTcpiLastAckSent() == socketOptionTcpInfo.getTcpiLastAckSent() && getTcpiLastDataRecv() == socketOptionTcpInfo.getTcpiLastDataRecv() && getTcpiLastAckRecv() == socketOptionTcpInfo.getTcpiLastAckRecv() && getTcpiPmtu() == socketOptionTcpInfo.getTcpiPmtu() && getTcpiRcvSsthresh() == socketOptionTcpInfo.getTcpiRcvSsthresh() && getTcpiRtt() == socketOptionTcpInfo.getTcpiRtt() && getTcpiRttvar() == socketOptionTcpInfo.getTcpiRttvar() && getTcpiSndSsthresh() == socketOptionTcpInfo.getTcpiSndSsthresh() && getTcpiSndCwnd() == socketOptionTcpInfo.getTcpiSndCwnd() && getTcpiAdvmss() == socketOptionTcpInfo.getTcpiAdvmss() && getTcpiReordering() == socketOptionTcpInfo.getTcpiReordering() && this.unknownFields.equals(socketOptionTcpInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTcpiState()) * 37) + 2) * 53) + getTcpiCaState()) * 37) + 3) * 53) + getTcpiRetransmits()) * 37) + 4) * 53) + getTcpiProbes()) * 37) + 5) * 53) + getTcpiBackoff()) * 37) + 6) * 53) + getTcpiOptions()) * 37) + 7) * 53) + getTcpiSndWscale()) * 37) + 8) * 53) + getTcpiRcvWscale()) * 37) + 9) * 53) + getTcpiRto()) * 37) + 10) * 53) + getTcpiAto()) * 37) + 11) * 53) + getTcpiSndMss()) * 37) + 12) * 53) + getTcpiRcvMss()) * 37) + 13) * 53) + getTcpiUnacked()) * 37) + 14) * 53) + getTcpiSacked()) * 37) + 15) * 53) + getTcpiLost()) * 37) + 16) * 53) + getTcpiRetrans()) * 37) + 17) * 53) + getTcpiFackets()) * 37) + 18) * 53) + getTcpiLastDataSent()) * 37) + 19) * 53) + getTcpiLastAckSent()) * 37) + 20) * 53) + getTcpiLastDataRecv()) * 37) + 21) * 53) + getTcpiLastAckRecv()) * 37) + 22) * 53) + getTcpiPmtu()) * 37) + 23) * 53) + getTcpiRcvSsthresh()) * 37) + 24) * 53) + getTcpiRtt()) * 37) + 25) * 53) + getTcpiRttvar()) * 37) + 26) * 53) + getTcpiSndSsthresh()) * 37) + 27) * 53) + getTcpiSndCwnd()) * 37) + 28) * 53) + getTcpiAdvmss()) * 37) + 29) * 53) + getTcpiReordering()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8869newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8872toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketOptionTcpInfoOrBuilder {
        private int tcpiAdvmss_;
        private int tcpiAto_;
        private int tcpiBackoff_;
        private int tcpiCaState_;
        private int tcpiFackets_;
        private int tcpiLastAckRecv_;
        private int tcpiLastAckSent_;
        private int tcpiLastDataRecv_;
        private int tcpiLastDataSent_;
        private int tcpiLost_;
        private int tcpiOptions_;
        private int tcpiPmtu_;
        private int tcpiProbes_;
        private int tcpiRcvMss_;
        private int tcpiRcvSsthresh_;
        private int tcpiRcvWscale_;
        private int tcpiReordering_;
        private int tcpiRetrans_;
        private int tcpiRetransmits_;
        private int tcpiRto_;
        private int tcpiRtt_;
        private int tcpiRttvar_;
        private int tcpiSacked_;
        private int tcpiSndCwnd_;
        private int tcpiSndMss_;
        private int tcpiSndSsthresh_;
        private int tcpiSndWscale_;
        private int tcpiState_;
        private int tcpiUnacked_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionTcpInfo_descriptor;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiAdvmss() {
            return this.tcpiAdvmss_;
        }

        public Builder setTcpiAdvmss(int i) {
            this.tcpiAdvmss_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiAto() {
            return this.tcpiAto_;
        }

        public Builder setTcpiAto(int i) {
            this.tcpiAto_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiBackoff() {
            return this.tcpiBackoff_;
        }

        public Builder setTcpiBackoff(int i) {
            this.tcpiBackoff_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiCaState() {
            return this.tcpiCaState_;
        }

        public Builder setTcpiCaState(int i) {
            this.tcpiCaState_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiFackets() {
            return this.tcpiFackets_;
        }

        public Builder setTcpiFackets(int i) {
            this.tcpiFackets_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiLastAckRecv() {
            return this.tcpiLastAckRecv_;
        }

        public Builder setTcpiLastAckRecv(int i) {
            this.tcpiLastAckRecv_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiLastAckSent() {
            return this.tcpiLastAckSent_;
        }

        public Builder setTcpiLastAckSent(int i) {
            this.tcpiLastAckSent_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiLastDataRecv() {
            return this.tcpiLastDataRecv_;
        }

        public Builder setTcpiLastDataRecv(int i) {
            this.tcpiLastDataRecv_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiLastDataSent() {
            return this.tcpiLastDataSent_;
        }

        public Builder setTcpiLastDataSent(int i) {
            this.tcpiLastDataSent_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiLost() {
            return this.tcpiLost_;
        }

        public Builder setTcpiLost(int i) {
            this.tcpiLost_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiOptions() {
            return this.tcpiOptions_;
        }

        public Builder setTcpiOptions(int i) {
            this.tcpiOptions_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiPmtu() {
            return this.tcpiPmtu_;
        }

        public Builder setTcpiPmtu(int i) {
            this.tcpiPmtu_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiProbes() {
            return this.tcpiProbes_;
        }

        public Builder setTcpiProbes(int i) {
            this.tcpiProbes_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRcvMss() {
            return this.tcpiRcvMss_;
        }

        public Builder setTcpiRcvMss(int i) {
            this.tcpiRcvMss_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRcvSsthresh() {
            return this.tcpiRcvSsthresh_;
        }

        public Builder setTcpiRcvSsthresh(int i) {
            this.tcpiRcvSsthresh_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRcvWscale() {
            return this.tcpiRcvWscale_;
        }

        public Builder setTcpiRcvWscale(int i) {
            this.tcpiRcvWscale_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiReordering() {
            return this.tcpiReordering_;
        }

        public Builder setTcpiReordering(int i) {
            this.tcpiReordering_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRetrans() {
            return this.tcpiRetrans_;
        }

        public Builder setTcpiRetrans(int i) {
            this.tcpiRetrans_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRetransmits() {
            return this.tcpiRetransmits_;
        }

        public Builder setTcpiRetransmits(int i) {
            this.tcpiRetransmits_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRto() {
            return this.tcpiRto_;
        }

        public Builder setTcpiRto(int i) {
            this.tcpiRto_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRtt() {
            return this.tcpiRtt_;
        }

        public Builder setTcpiRtt(int i) {
            this.tcpiRtt_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiRttvar() {
            return this.tcpiRttvar_;
        }

        public Builder setTcpiRttvar(int i) {
            this.tcpiRttvar_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiSacked() {
            return this.tcpiSacked_;
        }

        public Builder setTcpiSacked(int i) {
            this.tcpiSacked_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiSndCwnd() {
            return this.tcpiSndCwnd_;
        }

        public Builder setTcpiSndCwnd(int i) {
            this.tcpiSndCwnd_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiSndMss() {
            return this.tcpiSndMss_;
        }

        public Builder setTcpiSndMss(int i) {
            this.tcpiSndMss_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiSndSsthresh() {
            return this.tcpiSndSsthresh_;
        }

        public Builder setTcpiSndSsthresh(int i) {
            this.tcpiSndSsthresh_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiSndWscale() {
            return this.tcpiSndWscale_;
        }

        public Builder setTcpiSndWscale(int i) {
            this.tcpiSndWscale_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiState() {
            return this.tcpiState_;
        }

        public Builder setTcpiState(int i) {
            this.tcpiState_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionTcpInfoOrBuilder
        public int getTcpiUnacked() {
            return this.tcpiUnacked_;
        }

        public Builder setTcpiUnacked(int i) {
            this.tcpiUnacked_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionTcpInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOptionTcpInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SocketOptionTcpInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8883clear() {
            super.clear();
            this.tcpiState_ = 0;
            this.tcpiCaState_ = 0;
            this.tcpiRetransmits_ = 0;
            this.tcpiProbes_ = 0;
            this.tcpiBackoff_ = 0;
            this.tcpiOptions_ = 0;
            this.tcpiSndWscale_ = 0;
            this.tcpiRcvWscale_ = 0;
            this.tcpiRto_ = 0;
            this.tcpiAto_ = 0;
            this.tcpiSndMss_ = 0;
            this.tcpiRcvMss_ = 0;
            this.tcpiUnacked_ = 0;
            this.tcpiSacked_ = 0;
            this.tcpiLost_ = 0;
            this.tcpiRetrans_ = 0;
            this.tcpiFackets_ = 0;
            this.tcpiLastDataSent_ = 0;
            this.tcpiLastAckSent_ = 0;
            this.tcpiLastDataRecv_ = 0;
            this.tcpiLastAckRecv_ = 0;
            this.tcpiPmtu_ = 0;
            this.tcpiRcvSsthresh_ = 0;
            this.tcpiRtt_ = 0;
            this.tcpiRttvar_ = 0;
            this.tcpiSndSsthresh_ = 0;
            this.tcpiSndCwnd_ = 0;
            this.tcpiAdvmss_ = 0;
            this.tcpiReordering_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionTcpInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionTcpInfo m8896getDefaultInstanceForType() {
            return SocketOptionTcpInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionTcpInfo m8877build() throws UninitializedMessageException {
            SocketOptionTcpInfo socketOptionTcpInfoM8879buildPartial = m8879buildPartial();
            if (socketOptionTcpInfoM8879buildPartial.isInitialized()) {
                return socketOptionTcpInfoM8879buildPartial;
            }
            throw newUninitializedMessageException(socketOptionTcpInfoM8879buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionTcpInfo m8879buildPartial() {
            SocketOptionTcpInfo socketOptionTcpInfo = new SocketOptionTcpInfo(this);
            socketOptionTcpInfo.tcpiState_ = this.tcpiState_;
            socketOptionTcpInfo.tcpiCaState_ = this.tcpiCaState_;
            socketOptionTcpInfo.tcpiRetransmits_ = this.tcpiRetransmits_;
            socketOptionTcpInfo.tcpiProbes_ = this.tcpiProbes_;
            socketOptionTcpInfo.tcpiBackoff_ = this.tcpiBackoff_;
            socketOptionTcpInfo.tcpiOptions_ = this.tcpiOptions_;
            socketOptionTcpInfo.tcpiSndWscale_ = this.tcpiSndWscale_;
            socketOptionTcpInfo.tcpiRcvWscale_ = this.tcpiRcvWscale_;
            socketOptionTcpInfo.tcpiRto_ = this.tcpiRto_;
            socketOptionTcpInfo.tcpiAto_ = this.tcpiAto_;
            socketOptionTcpInfo.tcpiSndMss_ = this.tcpiSndMss_;
            socketOptionTcpInfo.tcpiRcvMss_ = this.tcpiRcvMss_;
            socketOptionTcpInfo.tcpiUnacked_ = this.tcpiUnacked_;
            socketOptionTcpInfo.tcpiSacked_ = this.tcpiSacked_;
            socketOptionTcpInfo.tcpiLost_ = this.tcpiLost_;
            socketOptionTcpInfo.tcpiRetrans_ = this.tcpiRetrans_;
            socketOptionTcpInfo.tcpiFackets_ = this.tcpiFackets_;
            socketOptionTcpInfo.tcpiLastDataSent_ = this.tcpiLastDataSent_;
            socketOptionTcpInfo.tcpiLastAckSent_ = this.tcpiLastAckSent_;
            socketOptionTcpInfo.tcpiLastDataRecv_ = this.tcpiLastDataRecv_;
            socketOptionTcpInfo.tcpiLastAckRecv_ = this.tcpiLastAckRecv_;
            socketOptionTcpInfo.tcpiPmtu_ = this.tcpiPmtu_;
            socketOptionTcpInfo.tcpiRcvSsthresh_ = this.tcpiRcvSsthresh_;
            socketOptionTcpInfo.tcpiRtt_ = this.tcpiRtt_;
            socketOptionTcpInfo.tcpiRttvar_ = this.tcpiRttvar_;
            socketOptionTcpInfo.tcpiSndSsthresh_ = this.tcpiSndSsthresh_;
            socketOptionTcpInfo.tcpiSndCwnd_ = this.tcpiSndCwnd_;
            socketOptionTcpInfo.tcpiAdvmss_ = this.tcpiAdvmss_;
            socketOptionTcpInfo.tcpiReordering_ = this.tcpiReordering_;
            onBuilt();
            return socketOptionTcpInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8895clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8907setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8885clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8888clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8909setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8875addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8900mergeFrom(Message message) {
            if (message instanceof SocketOptionTcpInfo) {
                return mergeFrom((SocketOptionTcpInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SocketOptionTcpInfo socketOptionTcpInfo) {
            if (socketOptionTcpInfo == SocketOptionTcpInfo.getDefaultInstance()) {
                return this;
            }
            if (socketOptionTcpInfo.getTcpiState() != 0) {
                setTcpiState(socketOptionTcpInfo.getTcpiState());
            }
            if (socketOptionTcpInfo.getTcpiCaState() != 0) {
                setTcpiCaState(socketOptionTcpInfo.getTcpiCaState());
            }
            if (socketOptionTcpInfo.getTcpiRetransmits() != 0) {
                setTcpiRetransmits(socketOptionTcpInfo.getTcpiRetransmits());
            }
            if (socketOptionTcpInfo.getTcpiProbes() != 0) {
                setTcpiProbes(socketOptionTcpInfo.getTcpiProbes());
            }
            if (socketOptionTcpInfo.getTcpiBackoff() != 0) {
                setTcpiBackoff(socketOptionTcpInfo.getTcpiBackoff());
            }
            if (socketOptionTcpInfo.getTcpiOptions() != 0) {
                setTcpiOptions(socketOptionTcpInfo.getTcpiOptions());
            }
            if (socketOptionTcpInfo.getTcpiSndWscale() != 0) {
                setTcpiSndWscale(socketOptionTcpInfo.getTcpiSndWscale());
            }
            if (socketOptionTcpInfo.getTcpiRcvWscale() != 0) {
                setTcpiRcvWscale(socketOptionTcpInfo.getTcpiRcvWscale());
            }
            if (socketOptionTcpInfo.getTcpiRto() != 0) {
                setTcpiRto(socketOptionTcpInfo.getTcpiRto());
            }
            if (socketOptionTcpInfo.getTcpiAto() != 0) {
                setTcpiAto(socketOptionTcpInfo.getTcpiAto());
            }
            if (socketOptionTcpInfo.getTcpiSndMss() != 0) {
                setTcpiSndMss(socketOptionTcpInfo.getTcpiSndMss());
            }
            if (socketOptionTcpInfo.getTcpiRcvMss() != 0) {
                setTcpiRcvMss(socketOptionTcpInfo.getTcpiRcvMss());
            }
            if (socketOptionTcpInfo.getTcpiUnacked() != 0) {
                setTcpiUnacked(socketOptionTcpInfo.getTcpiUnacked());
            }
            if (socketOptionTcpInfo.getTcpiSacked() != 0) {
                setTcpiSacked(socketOptionTcpInfo.getTcpiSacked());
            }
            if (socketOptionTcpInfo.getTcpiLost() != 0) {
                setTcpiLost(socketOptionTcpInfo.getTcpiLost());
            }
            if (socketOptionTcpInfo.getTcpiRetrans() != 0) {
                setTcpiRetrans(socketOptionTcpInfo.getTcpiRetrans());
            }
            if (socketOptionTcpInfo.getTcpiFackets() != 0) {
                setTcpiFackets(socketOptionTcpInfo.getTcpiFackets());
            }
            if (socketOptionTcpInfo.getTcpiLastDataSent() != 0) {
                setTcpiLastDataSent(socketOptionTcpInfo.getTcpiLastDataSent());
            }
            if (socketOptionTcpInfo.getTcpiLastAckSent() != 0) {
                setTcpiLastAckSent(socketOptionTcpInfo.getTcpiLastAckSent());
            }
            if (socketOptionTcpInfo.getTcpiLastDataRecv() != 0) {
                setTcpiLastDataRecv(socketOptionTcpInfo.getTcpiLastDataRecv());
            }
            if (socketOptionTcpInfo.getTcpiLastAckRecv() != 0) {
                setTcpiLastAckRecv(socketOptionTcpInfo.getTcpiLastAckRecv());
            }
            if (socketOptionTcpInfo.getTcpiPmtu() != 0) {
                setTcpiPmtu(socketOptionTcpInfo.getTcpiPmtu());
            }
            if (socketOptionTcpInfo.getTcpiRcvSsthresh() != 0) {
                setTcpiRcvSsthresh(socketOptionTcpInfo.getTcpiRcvSsthresh());
            }
            if (socketOptionTcpInfo.getTcpiRtt() != 0) {
                setTcpiRtt(socketOptionTcpInfo.getTcpiRtt());
            }
            if (socketOptionTcpInfo.getTcpiRttvar() != 0) {
                setTcpiRttvar(socketOptionTcpInfo.getTcpiRttvar());
            }
            if (socketOptionTcpInfo.getTcpiSndSsthresh() != 0) {
                setTcpiSndSsthresh(socketOptionTcpInfo.getTcpiSndSsthresh());
            }
            if (socketOptionTcpInfo.getTcpiSndCwnd() != 0) {
                setTcpiSndCwnd(socketOptionTcpInfo.getTcpiSndCwnd());
            }
            if (socketOptionTcpInfo.getTcpiAdvmss() != 0) {
                setTcpiAdvmss(socketOptionTcpInfo.getTcpiAdvmss());
            }
            if (socketOptionTcpInfo.getTcpiReordering() != 0) {
                setTcpiReordering(socketOptionTcpInfo.getTcpiReordering());
            }
            m8905mergeUnknownFields(socketOptionTcpInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.SocketOptionTcpInfo.Builder m8901mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.SocketOptionTcpInfo.access$3400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.SocketOptionTcpInfo r3 = (io.grpc.channelz.v1.SocketOptionTcpInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                io.grpc.channelz.v1.SocketOptionTcpInfo r4 = (io.grpc.channelz.v1.SocketOptionTcpInfo) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.SocketOptionTcpInfo.Builder.m8901mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.SocketOptionTcpInfo$Builder");
        }

        public Builder clearTcpiState() {
            this.tcpiState_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiCaState() {
            this.tcpiCaState_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRetransmits() {
            this.tcpiRetransmits_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiProbes() {
            this.tcpiProbes_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiBackoff() {
            this.tcpiBackoff_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiOptions() {
            this.tcpiOptions_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiSndWscale() {
            this.tcpiSndWscale_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRcvWscale() {
            this.tcpiRcvWscale_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRto() {
            this.tcpiRto_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiAto() {
            this.tcpiAto_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiSndMss() {
            this.tcpiSndMss_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRcvMss() {
            this.tcpiRcvMss_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiUnacked() {
            this.tcpiUnacked_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiSacked() {
            this.tcpiSacked_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiLost() {
            this.tcpiLost_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRetrans() {
            this.tcpiRetrans_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiFackets() {
            this.tcpiFackets_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiLastDataSent() {
            this.tcpiLastDataSent_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiLastAckSent() {
            this.tcpiLastAckSent_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiLastDataRecv() {
            this.tcpiLastDataRecv_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiLastAckRecv() {
            this.tcpiLastAckRecv_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiPmtu() {
            this.tcpiPmtu_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRcvSsthresh() {
            this.tcpiRcvSsthresh_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRtt() {
            this.tcpiRtt_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiRttvar() {
            this.tcpiRttvar_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiSndSsthresh() {
            this.tcpiSndSsthresh_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiSndCwnd() {
            this.tcpiSndCwnd_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiAdvmss() {
            this.tcpiAdvmss_ = 0;
            onChanged();
            return this;
        }

        public Builder clearTcpiReordering() {
            this.tcpiReordering_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8911setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8905mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
