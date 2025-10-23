package io.grpc.alts.internal;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import io.grpc.alts.internal.RpcProtocolVersions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes2.dex */
public final class AltsContext extends GeneratedMessageV3 implements AltsContextOrBuilder {
    public static final int APPLICATION_PROTOCOL_FIELD_NUMBER = 1;
    public static final int LOCAL_SERVICE_ACCOUNT_FIELD_NUMBER = 5;
    public static final int PEER_ATTRIBUTES_FIELD_NUMBER = 7;
    public static final int PEER_RPC_VERSIONS_FIELD_NUMBER = 6;
    public static final int PEER_SERVICE_ACCOUNT_FIELD_NUMBER = 4;
    public static final int RECORD_PROTOCOL_FIELD_NUMBER = 2;
    public static final int SECURITY_LEVEL_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final AltsContext DEFAULT_INSTANCE = new AltsContext();
    private static final Parser<AltsContext> PARSER = new AbstractParser<AltsContext>() { // from class: io.grpc.alts.internal.AltsContext.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AltsContext m6332parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AltsContext(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object applicationProtocol_;
    private volatile Object localServiceAccount_;
    private byte memoizedIsInitialized;
    private MapField<String, String> peerAttributes_;
    private RpcProtocolVersions peerRpcVersions_;
    private volatile Object peerServiceAccount_;
    private volatile Object recordProtocol_;
    private int securityLevel_;

    private AltsContext(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AltsContext() {
        this.memoizedIsInitialized = (byte) -1;
        this.applicationProtocol_ = "";
        this.recordProtocol_ = "";
        this.securityLevel_ = 0;
        this.peerServiceAccount_ = "";
        this.localServiceAccount_ = "";
    }

    private AltsContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.applicationProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.recordProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 24) {
                                this.securityLevel_ = codedInputStream.readEnum();
                            } else if (tag == 34) {
                                this.peerServiceAccount_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 42) {
                                this.localServiceAccount_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 50) {
                                RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
                                RpcProtocolVersions.Builder builderM6700toBuilder = rpcProtocolVersions != null ? rpcProtocolVersions.m6700toBuilder() : null;
                                RpcProtocolVersions rpcProtocolVersions2 = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                                this.peerRpcVersions_ = rpcProtocolVersions2;
                                if (builderM6700toBuilder != null) {
                                    builderM6700toBuilder.mergeFrom(rpcProtocolVersions2);
                                    this.peerRpcVersions_ = builderM6700toBuilder.m6707buildPartial();
                                }
                            } else if (tag == 58) {
                                if (!(z2 & true)) {
                                    this.peerAttributes_ = MapField.newMapField(PeerAttributesDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry message = codedInputStream.readMessage(PeerAttributesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.peerAttributes_.getMutableMap().put(message.getKey(), message.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static AltsContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AltsContext> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
    }

    public static AltsContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(byteBuffer);
    }

    public static AltsContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AltsContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(byteString);
    }

    public static AltsContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AltsContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(bArr);
    }

    public static AltsContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AltsContext) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AltsContext parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AltsContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AltsContext parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AltsContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AltsContext parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AltsContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6330toBuilder();
    }

    public static Builder newBuilder(AltsContext altsContext) {
        return DEFAULT_INSTANCE.m6330toBuilder().mergeFrom(altsContext);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AltsContext m6325getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<AltsContext> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public int getSecurityLevelValue() {
        return this.securityLevel_;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public boolean hasPeerRpcVersions() {
        return this.peerRpcVersions_ != null;
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
        return new AltsContext();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 7) {
            return internalGetPeerAttributes();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AltsContextProto.internal_static_grpc_gcp_AltsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(AltsContext.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getApplicationProtocol() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.applicationProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getApplicationProtocolBytes() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.applicationProtocol_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getRecordProtocol() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.recordProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getRecordProtocolBytes() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.recordProtocol_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public SecurityLevel getSecurityLevel() {
        SecurityLevel securityLevelValueOf = SecurityLevel.valueOf(this.securityLevel_);
        return securityLevelValueOf == null ? SecurityLevel.UNRECOGNIZED : securityLevelValueOf;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerServiceAccount() {
        Object obj = this.peerServiceAccount_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.peerServiceAccount_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getPeerServiceAccountBytes() {
        Object obj = this.peerServiceAccount_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.peerServiceAccount_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getLocalServiceAccount() {
        Object obj = this.localServiceAccount_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.localServiceAccount_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getLocalServiceAccountBytes() {
        Object obj = this.localServiceAccount_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.localServiceAccount_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public RpcProtocolVersions getPeerRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder() {
        return getPeerRpcVersions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetPeerAttributes() {
        MapField<String, String> mapField = this.peerAttributes_;
        return mapField == null ? MapField.emptyMapField(PeerAttributesDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public int getPeerAttributesCount() {
        return internalGetPeerAttributes().getMap().size();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public boolean containsPeerAttributes(String str) {
        str.getClass();
        return internalGetPeerAttributes().getMap().containsKey(str);
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    @Deprecated
    public Map<String, String> getPeerAttributes() {
        return getPeerAttributesMap();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public Map<String, String> getPeerAttributesMap() {
        return internalGetPeerAttributes().getMap();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerAttributesOrDefault(String str, String str2) {
        str.getClass();
        Map map = internalGetPeerAttributes().getMap();
        return map.containsKey(str) ? (String) map.get(str) : str2;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerAttributesOrThrow(String str) {
        str.getClass();
        Map map = internalGetPeerAttributes().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (String) map.get(str);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getApplicationProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.applicationProtocol_);
        }
        if (!getRecordProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.recordProtocol_);
        }
        if (this.securityLevel_ != SecurityLevel.SECURITY_NONE.getNumber()) {
            codedOutputStream.writeEnum(3, this.securityLevel_);
        }
        if (!getPeerServiceAccountBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.peerServiceAccount_);
        }
        if (!getLocalServiceAccountBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.localServiceAccount_);
        }
        if (this.peerRpcVersions_ != null) {
            codedOutputStream.writeMessage(6, getPeerRpcVersions());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetPeerAttributes(), PeerAttributesDefaultEntryHolder.defaultEntry, 7);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getApplicationProtocolBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.applicationProtocol_) : 0;
        if (!getRecordProtocolBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.recordProtocol_);
        }
        if (this.securityLevel_ != SecurityLevel.SECURITY_NONE.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.securityLevel_);
        }
        if (!getPeerServiceAccountBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.peerServiceAccount_);
        }
        if (!getLocalServiceAccountBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.localServiceAccount_);
        }
        if (this.peerRpcVersions_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(6, getPeerRpcVersions());
        }
        for (Map.Entry entry : internalGetPeerAttributes().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(7, PeerAttributesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AltsContext)) {
            return super.equals(obj);
        }
        AltsContext altsContext = (AltsContext) obj;
        if (getApplicationProtocol().equals(altsContext.getApplicationProtocol()) && getRecordProtocol().equals(altsContext.getRecordProtocol()) && this.securityLevel_ == altsContext.securityLevel_ && getPeerServiceAccount().equals(altsContext.getPeerServiceAccount()) && getLocalServiceAccount().equals(altsContext.getLocalServiceAccount()) && hasPeerRpcVersions() == altsContext.hasPeerRpcVersions()) {
            return (!hasPeerRpcVersions() || getPeerRpcVersions().equals(altsContext.getPeerRpcVersions())) && internalGetPeerAttributes().equals(altsContext.internalGetPeerAttributes()) && this.unknownFields.equals(altsContext.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getApplicationProtocol().hashCode()) * 37) + 2) * 53) + getRecordProtocol().hashCode()) * 37) + 3) * 53) + this.securityLevel_) * 37) + 4) * 53) + getPeerServiceAccount().hashCode()) * 37) + 5) * 53) + getLocalServiceAccount().hashCode();
        if (hasPeerRpcVersions()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getPeerRpcVersions().hashCode();
        }
        if (!internalGetPeerAttributes().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 7) * 53) + internalGetPeerAttributes().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6327newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6330toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class PeerAttributesDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(AltsContextProto.internal_static_grpc_gcp_AltsContext_PeerAttributesEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private PeerAttributesDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AltsContextOrBuilder {
        private Object applicationProtocol_;
        private int bitField0_;
        private Object localServiceAccount_;
        private MapField<String, String> peerAttributes_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> peerRpcVersionsBuilder_;
        private RpcProtocolVersions peerRpcVersions_;
        private Object peerServiceAccount_;
        private Object recordProtocol_;
        private int securityLevel_;

        private Builder() {
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public int getSecurityLevelValue() {
            return this.securityLevel_;
        }

        public Builder setSecurityLevelValue(int i) {
            this.securityLevel_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public boolean hasPeerRpcVersions() {
            return (this.peerRpcVersionsBuilder_ == null && this.peerRpcVersions_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 7) {
                return internalGetPeerAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 7) {
                return internalGetMutablePeerAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(AltsContext.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AltsContext.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6341clear() {
            super.clear();
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersions_ = null;
            } else {
                this.peerRpcVersions_ = null;
                this.peerRpcVersionsBuilder_ = null;
            }
            internalGetMutablePeerAttributes().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AltsContext m6354getDefaultInstanceForType() {
            return AltsContext.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AltsContext m6335build() throws UninitializedMessageException {
            AltsContext altsContextM6337buildPartial = m6337buildPartial();
            if (altsContextM6337buildPartial.isInitialized()) {
                return altsContextM6337buildPartial;
            }
            throw newUninitializedMessageException(altsContextM6337buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AltsContext m6337buildPartial() {
            AltsContext altsContext = new AltsContext(this);
            altsContext.applicationProtocol_ = this.applicationProtocol_;
            altsContext.recordProtocol_ = this.recordProtocol_;
            altsContext.securityLevel_ = this.securityLevel_;
            altsContext.peerServiceAccount_ = this.peerServiceAccount_;
            altsContext.localServiceAccount_ = this.localServiceAccount_;
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                altsContext.peerRpcVersions_ = this.peerRpcVersions_;
            } else {
                altsContext.peerRpcVersions_ = singleFieldBuilderV3.build();
            }
            altsContext.peerAttributes_ = internalGetPeerAttributes();
            altsContext.peerAttributes_.makeImmutable();
            onBuilt();
            return altsContext;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6353clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6365setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6343clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6346clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6367setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6333addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6358mergeFrom(Message message) {
            if (message instanceof AltsContext) {
                return mergeFrom((AltsContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AltsContext altsContext) {
            if (altsContext == AltsContext.getDefaultInstance()) {
                return this;
            }
            if (!altsContext.getApplicationProtocol().isEmpty()) {
                this.applicationProtocol_ = altsContext.applicationProtocol_;
                onChanged();
            }
            if (!altsContext.getRecordProtocol().isEmpty()) {
                this.recordProtocol_ = altsContext.recordProtocol_;
                onChanged();
            }
            if (altsContext.securityLevel_ != 0) {
                setSecurityLevelValue(altsContext.getSecurityLevelValue());
            }
            if (!altsContext.getPeerServiceAccount().isEmpty()) {
                this.peerServiceAccount_ = altsContext.peerServiceAccount_;
                onChanged();
            }
            if (!altsContext.getLocalServiceAccount().isEmpty()) {
                this.localServiceAccount_ = altsContext.localServiceAccount_;
                onChanged();
            }
            if (altsContext.hasPeerRpcVersions()) {
                mergePeerRpcVersions(altsContext.getPeerRpcVersions());
            }
            internalGetMutablePeerAttributes().mergeFrom(altsContext.internalGetPeerAttributes());
            m6363mergeUnknownFields(altsContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.AltsContext.Builder m6359mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.AltsContext.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.AltsContext r3 = (io.grpc.alts.internal.AltsContext) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.AltsContext r4 = (io.grpc.alts.internal.AltsContext) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.AltsContext.Builder.m6359mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.AltsContext$Builder");
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getApplicationProtocol() {
            Object obj = this.applicationProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.applicationProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setApplicationProtocol(String str) {
            str.getClass();
            this.applicationProtocol_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getApplicationProtocolBytes() {
            Object obj = this.applicationProtocol_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.applicationProtocol_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setApplicationProtocolBytes(ByteString byteString) {
            byteString.getClass();
            AltsContext.checkByteStringIsUtf8(byteString);
            this.applicationProtocol_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearApplicationProtocol() {
            this.applicationProtocol_ = AltsContext.getDefaultInstance().getApplicationProtocol();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getRecordProtocol() {
            Object obj = this.recordProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.recordProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRecordProtocol(String str) {
            str.getClass();
            this.recordProtocol_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getRecordProtocolBytes() {
            Object obj = this.recordProtocol_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.recordProtocol_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRecordProtocolBytes(ByteString byteString) {
            byteString.getClass();
            AltsContext.checkByteStringIsUtf8(byteString);
            this.recordProtocol_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRecordProtocol() {
            this.recordProtocol_ = AltsContext.getDefaultInstance().getRecordProtocol();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public SecurityLevel getSecurityLevel() {
            SecurityLevel securityLevelValueOf = SecurityLevel.valueOf(this.securityLevel_);
            return securityLevelValueOf == null ? SecurityLevel.UNRECOGNIZED : securityLevelValueOf;
        }

        public Builder setSecurityLevel(SecurityLevel securityLevel) {
            securityLevel.getClass();
            this.securityLevel_ = securityLevel.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSecurityLevel() {
            this.securityLevel_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerServiceAccount() {
            Object obj = this.peerServiceAccount_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.peerServiceAccount_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setPeerServiceAccount(String str) {
            str.getClass();
            this.peerServiceAccount_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getPeerServiceAccountBytes() {
            Object obj = this.peerServiceAccount_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.peerServiceAccount_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPeerServiceAccountBytes(ByteString byteString) {
            byteString.getClass();
            AltsContext.checkByteStringIsUtf8(byteString);
            this.peerServiceAccount_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPeerServiceAccount() {
            this.peerServiceAccount_ = AltsContext.getDefaultInstance().getPeerServiceAccount();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getLocalServiceAccount() {
            Object obj = this.localServiceAccount_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.localServiceAccount_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setLocalServiceAccount(String str) {
            str.getClass();
            this.localServiceAccount_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getLocalServiceAccountBytes() {
            Object obj = this.localServiceAccount_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.localServiceAccount_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocalServiceAccountBytes(ByteString byteString) {
            byteString.getClass();
            AltsContext.checkByteStringIsUtf8(byteString);
            this.localServiceAccount_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearLocalServiceAccount() {
            this.localServiceAccount_ = AltsContext.getDefaultInstance().getLocalServiceAccount();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public RpcProtocolVersions getPeerRpcVersions() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        public Builder setPeerRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                rpcProtocolVersions.getClass();
                this.peerRpcVersions_ = rpcProtocolVersions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(rpcProtocolVersions);
            }
            return this;
        }

        public Builder setPeerRpcVersions(RpcProtocolVersions.Builder builder) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.peerRpcVersions_ = builder.m6705build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6705build());
            }
            return this;
        }

        public Builder mergePeerRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions2 = this.peerRpcVersions_;
                if (rpcProtocolVersions2 != null) {
                    this.peerRpcVersions_ = RpcProtocolVersions.newBuilder(rpcProtocolVersions2).mergeFrom(rpcProtocolVersions).m6707buildPartial();
                } else {
                    this.peerRpcVersions_ = rpcProtocolVersions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(rpcProtocolVersions);
            }
            return this;
        }

        public Builder clearPeerRpcVersions() {
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersions_ = null;
                onChanged();
            } else {
                this.peerRpcVersions_ = null;
                this.peerRpcVersionsBuilder_ = null;
            }
            return this;
        }

        public RpcProtocolVersions.Builder getPeerRpcVersionsBuilder() {
            onChanged();
            return getPeerRpcVersionsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RpcProtocolVersionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> getPeerRpcVersionsFieldBuilder() {
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersionsBuilder_ = new SingleFieldBuilderV3<>(getPeerRpcVersions(), getParentForChildren(), isClean());
                this.peerRpcVersions_ = null;
            }
            return this.peerRpcVersionsBuilder_;
        }

        private MapField<String, String> internalGetPeerAttributes() {
            MapField<String, String> mapField = this.peerAttributes_;
            return mapField == null ? MapField.emptyMapField(PeerAttributesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutablePeerAttributes() {
            onChanged();
            if (this.peerAttributes_ == null) {
                this.peerAttributes_ = MapField.newMapField(PeerAttributesDefaultEntryHolder.defaultEntry);
            }
            if (!this.peerAttributes_.isMutable()) {
                this.peerAttributes_ = this.peerAttributes_.copy();
            }
            return this.peerAttributes_;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public int getPeerAttributesCount() {
            return internalGetPeerAttributes().getMap().size();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public boolean containsPeerAttributes(String str) {
            str.getClass();
            return internalGetPeerAttributes().getMap().containsKey(str);
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        @Deprecated
        public Map<String, String> getPeerAttributes() {
            return getPeerAttributesMap();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public Map<String, String> getPeerAttributesMap() {
            return internalGetPeerAttributes().getMap();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerAttributesOrDefault(String str, String str2) {
            str.getClass();
            Map map = internalGetPeerAttributes().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerAttributesOrThrow(String str) {
            str.getClass();
            Map map = internalGetPeerAttributes().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (String) map.get(str);
        }

        public Builder clearPeerAttributes() {
            internalGetMutablePeerAttributes().getMutableMap().clear();
            return this;
        }

        public Builder removePeerAttributes(String str) {
            str.getClass();
            internalGetMutablePeerAttributes().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutablePeerAttributes() {
            return internalGetMutablePeerAttributes().getMutableMap();
        }

        public Builder putPeerAttributes(String str, String str2) {
            str.getClass();
            str2.getClass();
            internalGetMutablePeerAttributes().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllPeerAttributes(Map<String, String> map) {
            internalGetMutablePeerAttributes().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6369setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6363mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
