package io.grpc.alts.internal;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.Endpoint;
import io.grpc.alts.internal.Identity;
import io.grpc.alts.internal.RpcProtocolVersions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class StartClientHandshakeReq extends GeneratedMessageV3 implements StartClientHandshakeReqOrBuilder {
    public static final int APPLICATION_PROTOCOLS_FIELD_NUMBER = 2;
    public static final int HANDSHAKE_SECURITY_PROTOCOL_FIELD_NUMBER = 1;
    public static final int LOCAL_ENDPOINT_FIELD_NUMBER = 6;
    public static final int LOCAL_IDENTITY_FIELD_NUMBER = 5;
    public static final int MAX_FRAME_SIZE_FIELD_NUMBER = 10;
    public static final int RECORD_PROTOCOLS_FIELD_NUMBER = 3;
    public static final int REMOTE_ENDPOINT_FIELD_NUMBER = 7;
    public static final int RPC_VERSIONS_FIELD_NUMBER = 9;
    public static final int TARGET_IDENTITIES_FIELD_NUMBER = 4;
    public static final int TARGET_NAME_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private static final StartClientHandshakeReq DEFAULT_INSTANCE = new StartClientHandshakeReq();
    private static final Parser<StartClientHandshakeReq> PARSER = new AbstractParser<StartClientHandshakeReq>() { // from class: io.grpc.alts.internal.StartClientHandshakeReq.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public StartClientHandshakeReq m6844parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StartClientHandshakeReq(codedInputStream, extensionRegistryLite);
        }
    };
    private LazyStringList applicationProtocols_;
    private int handshakeSecurityProtocol_;
    private Endpoint localEndpoint_;
    private Identity localIdentity_;
    private int maxFrameSize_;
    private byte memoizedIsInitialized;
    private LazyStringList recordProtocols_;
    private Endpoint remoteEndpoint_;
    private RpcProtocolVersions rpcVersions_;
    private List<Identity> targetIdentities_;
    private volatile Object targetName_;

    private StartClientHandshakeReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private StartClientHandshakeReq() {
        this.memoizedIsInitialized = (byte) -1;
        this.handshakeSecurityProtocol_ = 0;
        this.applicationProtocols_ = LazyStringArrayList.EMPTY;
        this.recordProtocols_ = LazyStringArrayList.EMPTY;
        this.targetIdentities_ = Collections.emptyList();
        this.targetName_ = "";
    }

    private StartClientHandshakeReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 8:
                            this.handshakeSecurityProtocol_ = codedInputStream.readEnum();
                        case 18:
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.applicationProtocols_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.applicationProtocols_.add(stringRequireUtf8);
                        case 26:
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.recordProtocols_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.recordProtocols_.add(stringRequireUtf82);
                        case 34:
                            if ((i & 4) == 0) {
                                this.targetIdentities_ = new ArrayList();
                                i |= 4;
                            }
                            this.targetIdentities_.add(codedInputStream.readMessage(Identity.parser(), extensionRegistryLite));
                        case 42:
                            Identity identity = this.localIdentity_;
                            Identity.Builder builderM6607toBuilder = identity != null ? identity.m6607toBuilder() : null;
                            Identity identity2 = (Identity) codedInputStream.readMessage(Identity.parser(), extensionRegistryLite);
                            this.localIdentity_ = identity2;
                            if (builderM6607toBuilder != null) {
                                builderM6607toBuilder.mergeFrom(identity2);
                                this.localIdentity_ = builderM6607toBuilder.m6614buildPartial();
                            }
                        case 50:
                            Endpoint endpoint = this.localEndpoint_;
                            Endpoint.Builder builderM6376toBuilder = endpoint != null ? endpoint.m6376toBuilder() : null;
                            Endpoint endpoint2 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                            this.localEndpoint_ = endpoint2;
                            if (builderM6376toBuilder != null) {
                                builderM6376toBuilder.mergeFrom(endpoint2);
                                this.localEndpoint_ = builderM6376toBuilder.m6383buildPartial();
                            }
                        case 58:
                            Endpoint endpoint3 = this.remoteEndpoint_;
                            Endpoint.Builder builderM6376toBuilder2 = endpoint3 != null ? endpoint3.m6376toBuilder() : null;
                            Endpoint endpoint4 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                            this.remoteEndpoint_ = endpoint4;
                            if (builderM6376toBuilder2 != null) {
                                builderM6376toBuilder2.mergeFrom(endpoint4);
                                this.remoteEndpoint_ = builderM6376toBuilder2.m6383buildPartial();
                            }
                        case 66:
                            this.targetName_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
                            RpcProtocolVersions.Builder builderM6700toBuilder = rpcProtocolVersions != null ? rpcProtocolVersions.m6700toBuilder() : null;
                            RpcProtocolVersions rpcProtocolVersions2 = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                            this.rpcVersions_ = rpcProtocolVersions2;
                            if (builderM6700toBuilder != null) {
                                builderM6700toBuilder.mergeFrom(rpcProtocolVersions2);
                                this.rpcVersions_ = builderM6700toBuilder.m6707buildPartial();
                            }
                        case 80:
                            this.maxFrameSize_ = codedInputStream.readUInt32();
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                }
                if ((i & 4) != 0) {
                    this.targetIdentities_ = Collections.unmodifiableList(this.targetIdentities_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static StartClientHandshakeReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StartClientHandshakeReq> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
    }

    public static StartClientHandshakeReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(byteBuffer);
    }

    public static StartClientHandshakeReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(byteString);
    }

    public static StartClientHandshakeReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(bArr);
    }

    public static StartClientHandshakeReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StartClientHandshakeReq) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StartClientHandshakeReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StartClientHandshakeReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StartClientHandshakeReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6842toBuilder();
    }

    public static Builder newBuilder(StartClientHandshakeReq startClientHandshakeReq) {
        return DEFAULT_INSTANCE.m6842toBuilder().mergeFrom(startClientHandshakeReq);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    /* renamed from: getApplicationProtocolsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo6835getApplicationProtocolsList() {
        return this.applicationProtocols_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public StartClientHandshakeReq m6836getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getHandshakeSecurityProtocolValue() {
        return this.handshakeSecurityProtocol_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getMaxFrameSize() {
        return this.maxFrameSize_;
    }

    public Parser<StartClientHandshakeReq> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    /* renamed from: getRecordProtocolsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo6838getRecordProtocolsList() {
        return this.recordProtocols_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public List<Identity> getTargetIdentitiesList() {
        return this.targetIdentities_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList() {
        return this.targetIdentities_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasLocalEndpoint() {
        return this.localEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasLocalIdentity() {
        return this.localIdentity_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasRemoteEndpoint() {
        return this.remoteEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasRpcVersions() {
        return this.rpcVersions_ != null;
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
        return new StartClientHandshakeReq();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(StartClientHandshakeReq.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public HandshakeProtocol getHandshakeSecurityProtocol() {
        HandshakeProtocol handshakeProtocolValueOf = HandshakeProtocol.valueOf(this.handshakeSecurityProtocol_);
        return handshakeProtocolValueOf == null ? HandshakeProtocol.UNRECOGNIZED : handshakeProtocolValueOf;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getApplicationProtocolsCount() {
        return this.applicationProtocols_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getApplicationProtocols(int i) {
        return (String) this.applicationProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getApplicationProtocolsBytes(int i) {
        return this.applicationProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getRecordProtocolsCount() {
        return this.recordProtocols_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getRecordProtocols(int i) {
        return (String) this.recordProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getRecordProtocolsBytes(int i) {
        return this.recordProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getTargetIdentitiesCount() {
        return this.targetIdentities_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Identity getTargetIdentities(int i) {
        return this.targetIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public IdentityOrBuilder getTargetIdentitiesOrBuilder(int i) {
        return this.targetIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Identity getLocalIdentity() {
        Identity identity = this.localIdentity_;
        return identity == null ? Identity.getDefaultInstance() : identity;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public IdentityOrBuilder getLocalIdentityOrBuilder() {
        return getLocalIdentity();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Endpoint getLocalEndpoint() {
        Endpoint endpoint = this.localEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public EndpointOrBuilder getLocalEndpointOrBuilder() {
        return getLocalEndpoint();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Endpoint getRemoteEndpoint() {
        Endpoint endpoint = this.remoteEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public EndpointOrBuilder getRemoteEndpointOrBuilder() {
        return getRemoteEndpoint();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getTargetName() {
        Object obj = this.targetName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.targetName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getTargetNameBytes() {
        Object obj = this.targetName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.targetName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public RpcProtocolVersions getRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder() {
        return getRpcVersions();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.handshakeSecurityProtocol_ != HandshakeProtocol.HANDSHAKE_PROTOCOL_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.handshakeSecurityProtocol_);
        }
        for (int i = 0; i < this.applicationProtocols_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.applicationProtocols_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.recordProtocols_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.recordProtocols_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.targetIdentities_.size(); i3++) {
            codedOutputStream.writeMessage(4, this.targetIdentities_.get(i3));
        }
        if (this.localIdentity_ != null) {
            codedOutputStream.writeMessage(5, getLocalIdentity());
        }
        if (this.localEndpoint_ != null) {
            codedOutputStream.writeMessage(6, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            codedOutputStream.writeMessage(7, getRemoteEndpoint());
        }
        if (!getTargetNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.targetName_);
        }
        if (this.rpcVersions_ != null) {
            codedOutputStream.writeMessage(9, getRpcVersions());
        }
        int i4 = this.maxFrameSize_;
        if (i4 != 0) {
            codedOutputStream.writeUInt32(10, i4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.handshakeSecurityProtocol_ != HandshakeProtocol.HANDSHAKE_PROTOCOL_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.handshakeSecurityProtocol_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.applicationProtocols_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.applicationProtocols_.getRaw(i2));
        }
        int size = iComputeEnumSize + iComputeStringSizeNoTag + mo6835getApplicationProtocolsList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.recordProtocols_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.recordProtocols_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo6838getRecordProtocolsList().size();
        for (int i4 = 0; i4 < this.targetIdentities_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.targetIdentities_.get(i4));
        }
        if (this.localIdentity_ != null) {
            size2 += CodedOutputStream.computeMessageSize(5, getLocalIdentity());
        }
        if (this.localEndpoint_ != null) {
            size2 += CodedOutputStream.computeMessageSize(6, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            size2 += CodedOutputStream.computeMessageSize(7, getRemoteEndpoint());
        }
        if (!getTargetNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(8, this.targetName_);
        }
        if (this.rpcVersions_ != null) {
            size2 += CodedOutputStream.computeMessageSize(9, getRpcVersions());
        }
        int i5 = this.maxFrameSize_;
        if (i5 != 0) {
            size2 += CodedOutputStream.computeUInt32Size(10, i5);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StartClientHandshakeReq)) {
            return super.equals(obj);
        }
        StartClientHandshakeReq startClientHandshakeReq = (StartClientHandshakeReq) obj;
        if (this.handshakeSecurityProtocol_ != startClientHandshakeReq.handshakeSecurityProtocol_ || !mo6835getApplicationProtocolsList().equals(startClientHandshakeReq.mo6835getApplicationProtocolsList()) || !mo6838getRecordProtocolsList().equals(startClientHandshakeReq.mo6838getRecordProtocolsList()) || !getTargetIdentitiesList().equals(startClientHandshakeReq.getTargetIdentitiesList()) || hasLocalIdentity() != startClientHandshakeReq.hasLocalIdentity()) {
            return false;
        }
        if ((hasLocalIdentity() && !getLocalIdentity().equals(startClientHandshakeReq.getLocalIdentity())) || hasLocalEndpoint() != startClientHandshakeReq.hasLocalEndpoint()) {
            return false;
        }
        if ((hasLocalEndpoint() && !getLocalEndpoint().equals(startClientHandshakeReq.getLocalEndpoint())) || hasRemoteEndpoint() != startClientHandshakeReq.hasRemoteEndpoint()) {
            return false;
        }
        if ((!hasRemoteEndpoint() || getRemoteEndpoint().equals(startClientHandshakeReq.getRemoteEndpoint())) && getTargetName().equals(startClientHandshakeReq.getTargetName()) && hasRpcVersions() == startClientHandshakeReq.hasRpcVersions()) {
            return (!hasRpcVersions() || getRpcVersions().equals(startClientHandshakeReq.getRpcVersions())) && getMaxFrameSize() == startClientHandshakeReq.getMaxFrameSize() && this.unknownFields.equals(startClientHandshakeReq.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.handshakeSecurityProtocol_;
        if (getApplicationProtocolsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo6835getApplicationProtocolsList().hashCode();
        }
        if (getRecordProtocolsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + mo6838getRecordProtocolsList().hashCode();
        }
        if (getTargetIdentitiesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getTargetIdentitiesList().hashCode();
        }
        if (hasLocalIdentity()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getLocalIdentity().hashCode();
        }
        if (hasLocalEndpoint()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getLocalEndpoint().hashCode();
        }
        if (hasRemoteEndpoint()) {
            iHashCode = (((iHashCode * 37) + 7) * 53) + getRemoteEndpoint().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 8) * 53) + getTargetName().hashCode();
        if (hasRpcVersions()) {
            iHashCode2 = (((iHashCode2 * 37) + 9) * 53) + getRpcVersions().hashCode();
        }
        int maxFrameSize = (((((iHashCode2 * 37) + 10) * 53) + getMaxFrameSize()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = maxFrameSize;
        return maxFrameSize;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6839newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6842toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StartClientHandshakeReqOrBuilder {
        private LazyStringList applicationProtocols_;
        private int bitField0_;
        private int handshakeSecurityProtocol_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> localEndpointBuilder_;
        private Endpoint localEndpoint_;
        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> localIdentityBuilder_;
        private Identity localIdentity_;
        private int maxFrameSize_;
        private LazyStringList recordProtocols_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> remoteEndpointBuilder_;
        private Endpoint remoteEndpoint_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> rpcVersionsBuilder_;
        private RpcProtocolVersions rpcVersions_;
        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> targetIdentitiesBuilder_;
        private List<Identity> targetIdentities_;
        private Object targetName_;

        private Builder() {
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.targetIdentities_ = Collections.emptyList();
            this.targetName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.targetIdentities_ = Collections.emptyList();
            this.targetName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getHandshakeSecurityProtocolValue() {
            return this.handshakeSecurityProtocol_;
        }

        public Builder setHandshakeSecurityProtocolValue(int i) {
            this.handshakeSecurityProtocol_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getMaxFrameSize() {
            return this.maxFrameSize_;
        }

        public Builder setMaxFrameSize(int i) {
            this.maxFrameSize_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasLocalEndpoint() {
            return (this.localEndpointBuilder_ == null && this.localEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasLocalIdentity() {
            return (this.localIdentityBuilder_ == null && this.localIdentity_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasRemoteEndpoint() {
            return (this.remoteEndpointBuilder_ == null && this.remoteEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasRpcVersions() {
            return (this.rpcVersionsBuilder_ == null && this.rpcVersions_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(StartClientHandshakeReq.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (StartClientHandshakeReq.alwaysUseFieldBuilders) {
                getTargetIdentitiesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6853clear() {
            super.clear();
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.targetIdentities_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.localIdentityBuilder_ == null) {
                this.localIdentity_ = null;
            } else {
                this.localIdentity_ = null;
                this.localIdentityBuilder_ = null;
            }
            if (this.localEndpointBuilder_ == null) {
                this.localEndpoint_ = null;
            } else {
                this.localEndpoint_ = null;
                this.localEndpointBuilder_ = null;
            }
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpoint_ = null;
            } else {
                this.remoteEndpoint_ = null;
                this.remoteEndpointBuilder_ = null;
            }
            this.targetName_ = "";
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersions_ = null;
            } else {
                this.rpcVersions_ = null;
                this.rpcVersionsBuilder_ = null;
            }
            this.maxFrameSize_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StartClientHandshakeReq m6866getDefaultInstanceForType() {
            return StartClientHandshakeReq.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StartClientHandshakeReq m6847build() throws UninitializedMessageException {
            StartClientHandshakeReq startClientHandshakeReqM6849buildPartial = m6849buildPartial();
            if (startClientHandshakeReqM6849buildPartial.isInitialized()) {
                return startClientHandshakeReqM6849buildPartial;
            }
            throw newUninitializedMessageException(startClientHandshakeReqM6849buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StartClientHandshakeReq m6849buildPartial() {
            StartClientHandshakeReq startClientHandshakeReq = new StartClientHandshakeReq(this);
            startClientHandshakeReq.handshakeSecurityProtocol_ = this.handshakeSecurityProtocol_;
            if ((this.bitField0_ & 1) != 0) {
                this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            startClientHandshakeReq.applicationProtocols_ = this.applicationProtocols_;
            if ((this.bitField0_ & 2) != 0) {
                this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            startClientHandshakeReq.recordProtocols_ = this.recordProtocols_;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.targetIdentities_ = Collections.unmodifiableList(this.targetIdentities_);
                    this.bitField0_ &= -5;
                }
                startClientHandshakeReq.targetIdentities_ = this.targetIdentities_;
            } else {
                startClientHandshakeReq.targetIdentities_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                startClientHandshakeReq.localIdentity_ = this.localIdentity_;
            } else {
                startClientHandshakeReq.localIdentity_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV32 = this.localEndpointBuilder_;
            if (singleFieldBuilderV32 == null) {
                startClientHandshakeReq.localEndpoint_ = this.localEndpoint_;
            } else {
                startClientHandshakeReq.localEndpoint_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV33 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV33 == null) {
                startClientHandshakeReq.remoteEndpoint_ = this.remoteEndpoint_;
            } else {
                startClientHandshakeReq.remoteEndpoint_ = singleFieldBuilderV33.build();
            }
            startClientHandshakeReq.targetName_ = this.targetName_;
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV34 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV34 == null) {
                startClientHandshakeReq.rpcVersions_ = this.rpcVersions_;
            } else {
                startClientHandshakeReq.rpcVersions_ = singleFieldBuilderV34.build();
            }
            startClientHandshakeReq.maxFrameSize_ = this.maxFrameSize_;
            onBuilt();
            return startClientHandshakeReq;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6865clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6877setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6855clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6858clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6879setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6845addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6870mergeFrom(Message message) {
            if (message instanceof StartClientHandshakeReq) {
                return mergeFrom((StartClientHandshakeReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StartClientHandshakeReq startClientHandshakeReq) {
            if (startClientHandshakeReq == StartClientHandshakeReq.getDefaultInstance()) {
                return this;
            }
            if (startClientHandshakeReq.handshakeSecurityProtocol_ != 0) {
                setHandshakeSecurityProtocolValue(startClientHandshakeReq.getHandshakeSecurityProtocolValue());
            }
            if (!startClientHandshakeReq.applicationProtocols_.isEmpty()) {
                if (this.applicationProtocols_.isEmpty()) {
                    this.applicationProtocols_ = startClientHandshakeReq.applicationProtocols_;
                    this.bitField0_ &= -2;
                } else {
                    ensureApplicationProtocolsIsMutable();
                    this.applicationProtocols_.addAll(startClientHandshakeReq.applicationProtocols_);
                }
                onChanged();
            }
            if (!startClientHandshakeReq.recordProtocols_.isEmpty()) {
                if (this.recordProtocols_.isEmpty()) {
                    this.recordProtocols_ = startClientHandshakeReq.recordProtocols_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRecordProtocolsIsMutable();
                    this.recordProtocols_.addAll(startClientHandshakeReq.recordProtocols_);
                }
                onChanged();
            }
            if (this.targetIdentitiesBuilder_ == null) {
                if (!startClientHandshakeReq.targetIdentities_.isEmpty()) {
                    if (this.targetIdentities_.isEmpty()) {
                        this.targetIdentities_ = startClientHandshakeReq.targetIdentities_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTargetIdentitiesIsMutable();
                        this.targetIdentities_.addAll(startClientHandshakeReq.targetIdentities_);
                    }
                    onChanged();
                }
            } else if (!startClientHandshakeReq.targetIdentities_.isEmpty()) {
                if (!this.targetIdentitiesBuilder_.isEmpty()) {
                    this.targetIdentitiesBuilder_.addAllMessages(startClientHandshakeReq.targetIdentities_);
                } else {
                    this.targetIdentitiesBuilder_.dispose();
                    this.targetIdentitiesBuilder_ = null;
                    this.targetIdentities_ = startClientHandshakeReq.targetIdentities_;
                    this.bitField0_ &= -5;
                    this.targetIdentitiesBuilder_ = StartClientHandshakeReq.alwaysUseFieldBuilders ? getTargetIdentitiesFieldBuilder() : null;
                }
            }
            if (startClientHandshakeReq.hasLocalIdentity()) {
                mergeLocalIdentity(startClientHandshakeReq.getLocalIdentity());
            }
            if (startClientHandshakeReq.hasLocalEndpoint()) {
                mergeLocalEndpoint(startClientHandshakeReq.getLocalEndpoint());
            }
            if (startClientHandshakeReq.hasRemoteEndpoint()) {
                mergeRemoteEndpoint(startClientHandshakeReq.getRemoteEndpoint());
            }
            if (!startClientHandshakeReq.getTargetName().isEmpty()) {
                this.targetName_ = startClientHandshakeReq.targetName_;
                onChanged();
            }
            if (startClientHandshakeReq.hasRpcVersions()) {
                mergeRpcVersions(startClientHandshakeReq.getRpcVersions());
            }
            if (startClientHandshakeReq.getMaxFrameSize() != 0) {
                setMaxFrameSize(startClientHandshakeReq.getMaxFrameSize());
            }
            m6875mergeUnknownFields(startClientHandshakeReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.StartClientHandshakeReq.Builder m6871mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.StartClientHandshakeReq.access$1600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.StartClientHandshakeReq r3 = (io.grpc.alts.internal.StartClientHandshakeReq) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.StartClientHandshakeReq r4 = (io.grpc.alts.internal.StartClientHandshakeReq) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.StartClientHandshakeReq.Builder.m6871mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.StartClientHandshakeReq$Builder");
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public HandshakeProtocol getHandshakeSecurityProtocol() {
            HandshakeProtocol handshakeProtocolValueOf = HandshakeProtocol.valueOf(this.handshakeSecurityProtocol_);
            return handshakeProtocolValueOf == null ? HandshakeProtocol.UNRECOGNIZED : handshakeProtocolValueOf;
        }

        public Builder setHandshakeSecurityProtocol(HandshakeProtocol handshakeProtocol) {
            handshakeProtocol.getClass();
            this.handshakeSecurityProtocol_ = handshakeProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearHandshakeSecurityProtocol() {
            this.handshakeSecurityProtocol_ = 0;
            onChanged();
            return this;
        }

        private void ensureApplicationProtocolsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.applicationProtocols_ = new LazyStringArrayList(this.applicationProtocols_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        /* renamed from: getApplicationProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo6835getApplicationProtocolsList() {
            return this.applicationProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getApplicationProtocolsCount() {
            return this.applicationProtocols_.size();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getApplicationProtocols(int i) {
            return (String) this.applicationProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ByteString getApplicationProtocolsBytes(int i) {
            return this.applicationProtocols_.getByteString(i);
        }

        public Builder setApplicationProtocols(int i, String str) {
            str.getClass();
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addApplicationProtocols(String str) {
            str.getClass();
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllApplicationProtocols(Iterable<String> iterable) {
            ensureApplicationProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.applicationProtocols_);
            onChanged();
            return this;
        }

        public Builder clearApplicationProtocols() {
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addApplicationProtocolsBytes(ByteString byteString) {
            byteString.getClass();
            StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureRecordProtocolsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.recordProtocols_ = new LazyStringArrayList(this.recordProtocols_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        /* renamed from: getRecordProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo6838getRecordProtocolsList() {
            return this.recordProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getRecordProtocolsCount() {
            return this.recordProtocols_.size();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getRecordProtocols(int i) {
            return (String) this.recordProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ByteString getRecordProtocolsBytes(int i) {
            return this.recordProtocols_.getByteString(i);
        }

        public Builder setRecordProtocols(int i, String str) {
            str.getClass();
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRecordProtocols(String str) {
            str.getClass();
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRecordProtocols(Iterable<String> iterable) {
            ensureRecordProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.recordProtocols_);
            onChanged();
            return this;
        }

        public Builder clearRecordProtocols() {
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addRecordProtocolsBytes(ByteString byteString) {
            byteString.getClass();
            StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureTargetIdentitiesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.targetIdentities_ = new ArrayList(this.targetIdentities_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public List<Identity> getTargetIdentitiesList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.targetIdentities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getTargetIdentitiesCount() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public Identity getTargetIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTargetIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.set(i, identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, identity);
            }
            return this;
        }

        public Builder setTargetIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.set(i, builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m6612build());
            }
            return this;
        }

        public Builder addTargetIdentities(Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(identity);
            }
            return this;
        }

        public Builder addTargetIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(i, identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, identity);
            }
            return this;
        }

        public Builder addTargetIdentities(Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m6612build());
            }
            return this;
        }

        public Builder addTargetIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(i, builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m6612build());
            }
            return this;
        }

        public Builder addAllTargetIdentities(Iterable<? extends Identity> iterable) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.targetIdentities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTargetIdentities() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.targetIdentities_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTargetIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Identity.Builder getTargetIdentitiesBuilder(int i) {
            return getTargetIdentitiesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public IdentityOrBuilder getTargetIdentitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.get(i);
            }
            return (IdentityOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.targetIdentities_);
        }

        public Identity.Builder addTargetIdentitiesBuilder() {
            return getTargetIdentitiesFieldBuilder().addBuilder(Identity.getDefaultInstance());
        }

        public Identity.Builder addTargetIdentitiesBuilder(int i) {
            return getTargetIdentitiesFieldBuilder().addBuilder(i, Identity.getDefaultInstance());
        }

        public List<Identity.Builder> getTargetIdentitiesBuilderList() {
            return getTargetIdentitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getTargetIdentitiesFieldBuilder() {
            if (this.targetIdentitiesBuilder_ == null) {
                this.targetIdentitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.targetIdentities_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.targetIdentities_ = null;
            }
            return this.targetIdentitiesBuilder_;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public Identity getLocalIdentity() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Identity identity = this.localIdentity_;
            return identity == null ? Identity.getDefaultInstance() : identity;
        }

        public Builder setLocalIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                identity.getClass();
                this.localIdentity_ = identity;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(identity);
            }
            return this;
        }

        public Builder setLocalIdentity(Identity.Builder builder) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.localIdentity_ = builder.m6612build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6612build());
            }
            return this;
        }

        public Builder mergeLocalIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Identity identity2 = this.localIdentity_;
                if (identity2 != null) {
                    this.localIdentity_ = Identity.newBuilder(identity2).mergeFrom(identity).m6614buildPartial();
                } else {
                    this.localIdentity_ = identity;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(identity);
            }
            return this;
        }

        public Builder clearLocalIdentity() {
            if (this.localIdentityBuilder_ == null) {
                this.localIdentity_ = null;
                onChanged();
            } else {
                this.localIdentity_ = null;
                this.localIdentityBuilder_ = null;
            }
            return this;
        }

        public Identity.Builder getLocalIdentityBuilder() {
            onChanged();
            return getLocalIdentityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public IdentityOrBuilder getLocalIdentityOrBuilder() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (IdentityOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Identity identity = this.localIdentity_;
            return identity == null ? Identity.getDefaultInstance() : identity;
        }

        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getLocalIdentityFieldBuilder() {
            if (this.localIdentityBuilder_ == null) {
                this.localIdentityBuilder_ = new SingleFieldBuilderV3<>(getLocalIdentity(), getParentForChildren(), isClean());
                this.localIdentity_ = null;
            }
            return this.localIdentityBuilder_;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public Endpoint getLocalEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Endpoint endpoint = this.localEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        public Builder setLocalEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                endpoint.getClass();
                this.localEndpoint_ = endpoint;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(endpoint);
            }
            return this;
        }

        public Builder setLocalEndpoint(Endpoint.Builder builder) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.localEndpoint_ = builder.m6381build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6381build());
            }
            return this;
        }

        public Builder mergeLocalEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint2 = this.localEndpoint_;
                if (endpoint2 != null) {
                    this.localEndpoint_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).m6383buildPartial();
                } else {
                    this.localEndpoint_ = endpoint;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(endpoint);
            }
            return this;
        }

        public Builder clearLocalEndpoint() {
            if (this.localEndpointBuilder_ == null) {
                this.localEndpoint_ = null;
                onChanged();
            } else {
                this.localEndpoint_ = null;
                this.localEndpointBuilder_ = null;
            }
            return this;
        }

        public Endpoint.Builder getLocalEndpointBuilder() {
            onChanged();
            return getLocalEndpointFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public EndpointOrBuilder getLocalEndpointOrBuilder() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (EndpointOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Endpoint endpoint = this.localEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getLocalEndpointFieldBuilder() {
            if (this.localEndpointBuilder_ == null) {
                this.localEndpointBuilder_ = new SingleFieldBuilderV3<>(getLocalEndpoint(), getParentForChildren(), isClean());
                this.localEndpoint_ = null;
            }
            return this.localEndpointBuilder_;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public Endpoint getRemoteEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Endpoint endpoint = this.remoteEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        public Builder setRemoteEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                endpoint.getClass();
                this.remoteEndpoint_ = endpoint;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(endpoint);
            }
            return this;
        }

        public Builder setRemoteEndpoint(Endpoint.Builder builder) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.remoteEndpoint_ = builder.m6381build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6381build());
            }
            return this;
        }

        public Builder mergeRemoteEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint2 = this.remoteEndpoint_;
                if (endpoint2 != null) {
                    this.remoteEndpoint_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).m6383buildPartial();
                } else {
                    this.remoteEndpoint_ = endpoint;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(endpoint);
            }
            return this;
        }

        public Builder clearRemoteEndpoint() {
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpoint_ = null;
                onChanged();
            } else {
                this.remoteEndpoint_ = null;
                this.remoteEndpointBuilder_ = null;
            }
            return this;
        }

        public Endpoint.Builder getRemoteEndpointBuilder() {
            onChanged();
            return getRemoteEndpointFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public EndpointOrBuilder getRemoteEndpointOrBuilder() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (EndpointOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Endpoint endpoint = this.remoteEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getRemoteEndpointFieldBuilder() {
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpointBuilder_ = new SingleFieldBuilderV3<>(getRemoteEndpoint(), getParentForChildren(), isClean());
                this.remoteEndpoint_ = null;
            }
            return this.remoteEndpointBuilder_;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getTargetName() {
            Object obj = this.targetName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.targetName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTargetName(String str) {
            str.getClass();
            this.targetName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ByteString getTargetNameBytes() {
            Object obj = this.targetName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTargetNameBytes(ByteString byteString) {
            byteString.getClass();
            StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
            this.targetName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTargetName() {
            this.targetName_ = StartClientHandshakeReq.getDefaultInstance().getTargetName();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public RpcProtocolVersions getRpcVersions() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        public Builder setRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                rpcProtocolVersions.getClass();
                this.rpcVersions_ = rpcProtocolVersions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(rpcProtocolVersions);
            }
            return this;
        }

        public Builder setRpcVersions(RpcProtocolVersions.Builder builder) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rpcVersions_ = builder.m6705build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6705build());
            }
            return this;
        }

        public Builder mergeRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions2 = this.rpcVersions_;
                if (rpcProtocolVersions2 != null) {
                    this.rpcVersions_ = RpcProtocolVersions.newBuilder(rpcProtocolVersions2).mergeFrom(rpcProtocolVersions).m6707buildPartial();
                } else {
                    this.rpcVersions_ = rpcProtocolVersions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(rpcProtocolVersions);
            }
            return this;
        }

        public Builder clearRpcVersions() {
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersions_ = null;
                onChanged();
            } else {
                this.rpcVersions_ = null;
                this.rpcVersionsBuilder_ = null;
            }
            return this;
        }

        public RpcProtocolVersions.Builder getRpcVersionsBuilder() {
            onChanged();
            return getRpcVersionsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RpcProtocolVersionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> getRpcVersionsFieldBuilder() {
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersionsBuilder_ = new SingleFieldBuilderV3<>(getRpcVersions(), getParentForChildren(), isClean());
                this.rpcVersions_ = null;
            }
            return this.rpcVersionsBuilder_;
        }

        public Builder clearMaxFrameSize() {
            this.maxFrameSize_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6881setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6875mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
