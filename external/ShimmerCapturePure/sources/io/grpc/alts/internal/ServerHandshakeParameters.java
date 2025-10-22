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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.Identity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class ServerHandshakeParameters extends GeneratedMessageV3 implements ServerHandshakeParametersOrBuilder {
    public static final int LOCAL_IDENTITIES_FIELD_NUMBER = 2;
    public static final int RECORD_PROTOCOLS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ServerHandshakeParameters DEFAULT_INSTANCE = new ServerHandshakeParameters();
    private static final Parser<ServerHandshakeParameters> PARSER = new AbstractParser<ServerHandshakeParameters>() { // from class: io.grpc.alts.internal.ServerHandshakeParameters.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ServerHandshakeParameters m6796parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServerHandshakeParameters(codedInputStream, extensionRegistryLite);
        }
    };
    private List<Identity> localIdentities_;
    private byte memoizedIsInitialized;
    private LazyStringList recordProtocols_;

    private ServerHandshakeParameters(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerHandshakeParameters() {
        this.memoizedIsInitialized = (byte) -1;
        this.recordProtocols_ = LazyStringArrayList.EMPTY;
        this.localIdentities_ = Collections.emptyList();
    }

    private ServerHandshakeParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.recordProtocols_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.recordProtocols_.add(stringRequireUtf8);
                        } else if (tag == 18) {
                            if ((i & 2) == 0) {
                                this.localIdentities_ = new ArrayList();
                                i |= 2;
                            }
                            this.localIdentities_.add(codedInputStream.readMessage(Identity.parser(), extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.localIdentities_ = Collections.unmodifiableList(this.localIdentities_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ServerHandshakeParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServerHandshakeParameters> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
    }

    public static ServerHandshakeParameters parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(byteBuffer);
    }

    public static ServerHandshakeParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(byteString);
    }

    public static ServerHandshakeParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(bArr);
    }

    public static ServerHandshakeParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHandshakeParameters) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServerHandshakeParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServerHandshakeParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServerHandshakeParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6794toBuilder();
    }

    public static Builder newBuilder(ServerHandshakeParameters serverHandshakeParameters) {
        return DEFAULT_INSTANCE.m6794toBuilder().mergeFrom(serverHandshakeParameters);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ServerHandshakeParameters m6788getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public List<Identity> getLocalIdentitiesList() {
        return this.localIdentities_;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public List<? extends IdentityOrBuilder> getLocalIdentitiesOrBuilderList() {
        return this.localIdentities_;
    }

    public Parser<ServerHandshakeParameters> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    /* renamed from: getRecordProtocolsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo6790getRecordProtocolsList() {
        return this.recordProtocols_;
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
        return new ServerHandshakeParameters();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerHandshakeParameters.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public int getRecordProtocolsCount() {
        return this.recordProtocols_.size();
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public String getRecordProtocols(int i) {
        return (String) this.recordProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public ByteString getRecordProtocolsBytes(int i) {
        return this.recordProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public int getLocalIdentitiesCount() {
        return this.localIdentities_.size();
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public Identity getLocalIdentities(int i) {
        return this.localIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public IdentityOrBuilder getLocalIdentitiesOrBuilder(int i) {
        return this.localIdentities_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.recordProtocols_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.recordProtocols_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.localIdentities_.size(); i2++) {
            codedOutputStream.writeMessage(2, this.localIdentities_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.recordProtocols_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.recordProtocols_.getRaw(i2));
        }
        int size = iComputeStringSizeNoTag + mo6790getRecordProtocolsList().size();
        for (int i3 = 0; i3 < this.localIdentities_.size(); i3++) {
            size += CodedOutputStream.computeMessageSize(2, this.localIdentities_.get(i3));
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerHandshakeParameters)) {
            return super.equals(obj);
        }
        ServerHandshakeParameters serverHandshakeParameters = (ServerHandshakeParameters) obj;
        return mo6790getRecordProtocolsList().equals(serverHandshakeParameters.mo6790getRecordProtocolsList()) && getLocalIdentitiesList().equals(serverHandshakeParameters.getLocalIdentitiesList()) && this.unknownFields.equals(serverHandshakeParameters.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getRecordProtocolsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + mo6790getRecordProtocolsList().hashCode();
        }
        if (getLocalIdentitiesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getLocalIdentitiesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6791newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6794toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerHandshakeParametersOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> localIdentitiesBuilder_;
        private List<Identity> localIdentities_;
        private LazyStringList recordProtocols_;

        private Builder() {
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.localIdentities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.localIdentities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerHandshakeParameters.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ServerHandshakeParameters.alwaysUseFieldBuilders) {
                getLocalIdentitiesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6805clear() {
            super.clear();
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.localIdentities_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHandshakeParameters m6818getDefaultInstanceForType() {
            return ServerHandshakeParameters.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHandshakeParameters m6799build() throws UninitializedMessageException {
            ServerHandshakeParameters serverHandshakeParametersM6801buildPartial = m6801buildPartial();
            if (serverHandshakeParametersM6801buildPartial.isInitialized()) {
                return serverHandshakeParametersM6801buildPartial;
            }
            throw newUninitializedMessageException(serverHandshakeParametersM6801buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHandshakeParameters m6801buildPartial() {
            ServerHandshakeParameters serverHandshakeParameters = new ServerHandshakeParameters(this);
            if ((this.bitField0_ & 1) != 0) {
                this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            serverHandshakeParameters.recordProtocols_ = this.recordProtocols_;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.localIdentities_ = Collections.unmodifiableList(this.localIdentities_);
                    this.bitField0_ &= -3;
                }
                serverHandshakeParameters.localIdentities_ = this.localIdentities_;
            } else {
                serverHandshakeParameters.localIdentities_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return serverHandshakeParameters;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6817clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6829setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6807clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6810clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6831setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6797addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6822mergeFrom(Message message) {
            if (message instanceof ServerHandshakeParameters) {
                return mergeFrom((ServerHandshakeParameters) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServerHandshakeParameters serverHandshakeParameters) {
            if (serverHandshakeParameters == ServerHandshakeParameters.getDefaultInstance()) {
                return this;
            }
            if (!serverHandshakeParameters.recordProtocols_.isEmpty()) {
                if (this.recordProtocols_.isEmpty()) {
                    this.recordProtocols_ = serverHandshakeParameters.recordProtocols_;
                    this.bitField0_ &= -2;
                } else {
                    ensureRecordProtocolsIsMutable();
                    this.recordProtocols_.addAll(serverHandshakeParameters.recordProtocols_);
                }
                onChanged();
            }
            if (this.localIdentitiesBuilder_ == null) {
                if (!serverHandshakeParameters.localIdentities_.isEmpty()) {
                    if (this.localIdentities_.isEmpty()) {
                        this.localIdentities_ = serverHandshakeParameters.localIdentities_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLocalIdentitiesIsMutable();
                        this.localIdentities_.addAll(serverHandshakeParameters.localIdentities_);
                    }
                    onChanged();
                }
            } else if (!serverHandshakeParameters.localIdentities_.isEmpty()) {
                if (!this.localIdentitiesBuilder_.isEmpty()) {
                    this.localIdentitiesBuilder_.addAllMessages(serverHandshakeParameters.localIdentities_);
                } else {
                    this.localIdentitiesBuilder_.dispose();
                    this.localIdentitiesBuilder_ = null;
                    this.localIdentities_ = serverHandshakeParameters.localIdentities_;
                    this.bitField0_ &= -3;
                    this.localIdentitiesBuilder_ = ServerHandshakeParameters.alwaysUseFieldBuilders ? getLocalIdentitiesFieldBuilder() : null;
                }
            }
            m6827mergeUnknownFields(serverHandshakeParameters.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.ServerHandshakeParameters.Builder m6823mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.ServerHandshakeParameters.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.ServerHandshakeParameters r3 = (io.grpc.alts.internal.ServerHandshakeParameters) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.ServerHandshakeParameters r4 = (io.grpc.alts.internal.ServerHandshakeParameters) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.ServerHandshakeParameters.Builder.m6823mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.ServerHandshakeParameters$Builder");
        }

        private void ensureRecordProtocolsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.recordProtocols_ = new LazyStringArrayList(this.recordProtocols_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        /* renamed from: getRecordProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo6790getRecordProtocolsList() {
            return this.recordProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public int getRecordProtocolsCount() {
            return this.recordProtocols_.size();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public String getRecordProtocols(int i) {
            return (String) this.recordProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
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
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addRecordProtocolsBytes(ByteString byteString) {
            byteString.getClass();
            ServerHandshakeParameters.checkByteStringIsUtf8(byteString);
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureLocalIdentitiesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.localIdentities_ = new ArrayList(this.localIdentities_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public List<Identity> getLocalIdentitiesList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.localIdentities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public int getLocalIdentitiesCount() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public Identity getLocalIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLocalIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.set(i, identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, identity);
            }
            return this;
        }

        public Builder setLocalIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.set(i, builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m6612build());
            }
            return this;
        }

        public Builder addLocalIdentities(Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(identity);
            }
            return this;
        }

        public Builder addLocalIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                identity.getClass();
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(i, identity);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, identity);
            }
            return this;
        }

        public Builder addLocalIdentities(Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m6612build());
            }
            return this;
        }

        public Builder addLocalIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(i, builder.m6612build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m6612build());
            }
            return this;
        }

        public Builder addAllLocalIdentities(Iterable<? extends Identity> iterable) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.localIdentities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLocalIdentities() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.localIdentities_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLocalIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Identity.Builder getLocalIdentitiesBuilder(int i) {
            return getLocalIdentitiesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public IdentityOrBuilder getLocalIdentitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.get(i);
            }
            return (IdentityOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public List<? extends IdentityOrBuilder> getLocalIdentitiesOrBuilderList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.localIdentities_);
        }

        public Identity.Builder addLocalIdentitiesBuilder() {
            return getLocalIdentitiesFieldBuilder().addBuilder(Identity.getDefaultInstance());
        }

        public Identity.Builder addLocalIdentitiesBuilder(int i) {
            return getLocalIdentitiesFieldBuilder().addBuilder(i, Identity.getDefaultInstance());
        }

        public List<Identity.Builder> getLocalIdentitiesBuilderList() {
            return getLocalIdentitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getLocalIdentitiesFieldBuilder() {
            if (this.localIdentitiesBuilder_ == null) {
                this.localIdentitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.localIdentities_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.localIdentities_ = null;
            }
            return this.localIdentitiesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6833setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6827mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
