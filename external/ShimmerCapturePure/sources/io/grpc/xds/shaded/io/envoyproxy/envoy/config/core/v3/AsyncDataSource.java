package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RemoteDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class AsyncDataSource extends GeneratedMessageV3 implements AsyncDataSourceOrBuilder {
    public static final int LOCAL_FIELD_NUMBER = 1;
    public static final int REMOTE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final AsyncDataSource DEFAULT_INSTANCE = new AsyncDataSource();
    private static final Parser<AsyncDataSource> PARSER = new AbstractParser<AsyncDataSource>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AsyncDataSource m21571parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AsyncDataSource(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private int specifierCase_;
    private Object specifier_;

    private AsyncDataSource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.specifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private AsyncDataSource() {
        this.specifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private AsyncDataSource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                DataSource.Builder builderM21891toBuilder = this.specifierCase_ == 1 ? ((DataSource) this.specifier_).m21891toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                                this.specifier_ = message;
                                if (builderM21891toBuilder != null) {
                                    builderM21891toBuilder.mergeFrom((DataSource) message);
                                    this.specifier_ = builderM21891toBuilder.m21898buildPartial();
                                }
                                this.specifierCase_ = 1;
                            } else if (tag == 18) {
                                RemoteDataSource.Builder builderM23830toBuilder = this.specifierCase_ == 2 ? ((RemoteDataSource) this.specifier_).m23830toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(RemoteDataSource.parser(), extensionRegistryLite);
                                this.specifier_ = message2;
                                if (builderM23830toBuilder != null) {
                                    builderM23830toBuilder.mergeFrom((RemoteDataSource) message2);
                                    this.specifier_ = builderM23830toBuilder.m23837buildPartial();
                                }
                                this.specifierCase_ = 2;
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

    public static AsyncDataSource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AsyncDataSource> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_AsyncDataSource_descriptor;
    }

    public static AsyncDataSource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(byteBuffer);
    }

    public static AsyncDataSource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AsyncDataSource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(byteString);
    }

    public static AsyncDataSource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AsyncDataSource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(bArr);
    }

    public static AsyncDataSource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AsyncDataSource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AsyncDataSource parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AsyncDataSource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AsyncDataSource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AsyncDataSource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AsyncDataSource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AsyncDataSource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m21569toBuilder();
    }

    public static Builder newBuilder(AsyncDataSource asyncDataSource) {
        return DEFAULT_INSTANCE.m21569toBuilder().mergeFrom(asyncDataSource);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AsyncDataSource m21564getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<AsyncDataSource> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public boolean hasLocal() {
        return this.specifierCase_ == 1;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public boolean hasRemote() {
        return this.specifierCase_ == 2;
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
        return new AsyncDataSource();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_AsyncDataSource_fieldAccessorTable.ensureFieldAccessorsInitialized(AsyncDataSource.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public SpecifierCase getSpecifierCase() {
        return SpecifierCase.forNumber(this.specifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public DataSource getLocal() {
        if (this.specifierCase_ == 1) {
            return (DataSource) this.specifier_;
        }
        return DataSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public DataSourceOrBuilder getLocalOrBuilder() {
        if (this.specifierCase_ == 1) {
            return (DataSource) this.specifier_;
        }
        return DataSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public RemoteDataSource getRemote() {
        if (this.specifierCase_ == 2) {
            return (RemoteDataSource) this.specifier_;
        }
        return RemoteDataSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
    public RemoteDataSourceOrBuilder getRemoteOrBuilder() {
        if (this.specifierCase_ == 2) {
            return (RemoteDataSource) this.specifier_;
        }
        return RemoteDataSource.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.specifierCase_ == 1) {
            codedOutputStream.writeMessage(1, (DataSource) this.specifier_);
        }
        if (this.specifierCase_ == 2) {
            codedOutputStream.writeMessage(2, (RemoteDataSource) this.specifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.specifierCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (DataSource) this.specifier_) : 0;
        if (this.specifierCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (RemoteDataSource) this.specifier_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AsyncDataSource)) {
            return super.equals(obj);
        }
        AsyncDataSource asyncDataSource = (AsyncDataSource) obj;
        if (!getSpecifierCase().equals(asyncDataSource.getSpecifierCase())) {
            return false;
        }
        int i = this.specifierCase_;
        if (i == 1) {
            if (!getLocal().equals(asyncDataSource.getLocal())) {
                return false;
            }
        } else if (i == 2 && !getRemote().equals(asyncDataSource.getRemote())) {
            return false;
        }
        return this.unknownFields.equals(asyncDataSource.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.specifierCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getLocal().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getRemote().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21566newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21569toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum SpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        LOCAL(1),
        REMOTE(2),
        SPECIFIER_NOT_SET(0);

        private final int value;

        SpecifierCase(int i) {
            this.value = i;
        }

        public static SpecifierCase forNumber(int i) {
            if (i == 0) {
                return SPECIFIER_NOT_SET;
            }
            if (i == 1) {
                return LOCAL;
            }
            if (i != 2) {
                return null;
            }
            return REMOTE;
        }

        @Deprecated
        public static SpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AsyncDataSourceOrBuilder {
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> localBuilder_;
        private SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> remoteBuilder_;
        private int specifierCase_;
        private Object specifier_;

        private Builder() {
            this.specifierCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.specifierCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_AsyncDataSource_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public boolean hasLocal() {
            return this.specifierCase_ == 1;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public boolean hasRemote() {
            return this.specifierCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_AsyncDataSource_fieldAccessorTable.ensureFieldAccessorsInitialized(AsyncDataSource.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AsyncDataSource.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21580clear() {
            super.clear();
            this.specifierCase_ = 0;
            this.specifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_AsyncDataSource_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AsyncDataSource m21593getDefaultInstanceForType() {
            return AsyncDataSource.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AsyncDataSource m21574build() throws UninitializedMessageException {
            AsyncDataSource asyncDataSourceM21576buildPartial = m21576buildPartial();
            if (asyncDataSourceM21576buildPartial.isInitialized()) {
                return asyncDataSourceM21576buildPartial;
            }
            throw newUninitializedMessageException(asyncDataSourceM21576buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AsyncDataSource m21576buildPartial() {
            AsyncDataSource asyncDataSource = new AsyncDataSource(this);
            if (this.specifierCase_ == 1) {
                SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
                if (singleFieldBuilderV3 == null) {
                    asyncDataSource.specifier_ = this.specifier_;
                } else {
                    asyncDataSource.specifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.specifierCase_ == 2) {
                SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV32 = this.remoteBuilder_;
                if (singleFieldBuilderV32 == null) {
                    asyncDataSource.specifier_ = this.specifier_;
                } else {
                    asyncDataSource.specifier_ = singleFieldBuilderV32.build();
                }
            }
            asyncDataSource.specifierCase_ = this.specifierCase_;
            onBuilt();
            return asyncDataSource;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21592clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21604setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21582clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21585clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21606setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21572addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21597mergeFrom(Message message) {
            if (message instanceof AsyncDataSource) {
                return mergeFrom((AsyncDataSource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AsyncDataSource asyncDataSource) {
            if (asyncDataSource == AsyncDataSource.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$AsyncDataSource$SpecifierCase[asyncDataSource.getSpecifierCase().ordinal()];
            if (i == 1) {
                mergeLocal(asyncDataSource.getLocal());
            } else if (i == 2) {
                mergeRemote(asyncDataSource.getRemote());
            }
            m21602mergeUnknownFields(asyncDataSource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource.Builder m21598mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource.Builder.m21598mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public SpecifierCase getSpecifierCase() {
            return SpecifierCase.forNumber(this.specifierCase_);
        }

        public Builder clearSpecifier() {
            this.specifierCase_ = 0;
            this.specifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public DataSource getLocal() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.specifierCase_ == 1) {
                    return (DataSource) this.specifier_;
                }
                return DataSource.getDefaultInstance();
            }
            if (this.specifierCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return DataSource.getDefaultInstance();
        }

        public Builder setLocal(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                dataSource.getClass();
                this.specifier_ = dataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(dataSource);
            }
            this.specifierCase_ = 1;
            return this;
        }

        public Builder setLocal(DataSource.Builder builder) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.specifier_ = builder.m21896build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m21896build());
            }
            this.specifierCase_ = 1;
            return this;
        }

        public Builder mergeLocal(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.specifierCase_ != 1 || this.specifier_ == DataSource.getDefaultInstance()) {
                    this.specifier_ = dataSource;
                } else {
                    this.specifier_ = DataSource.newBuilder((DataSource) this.specifier_).mergeFrom(dataSource).m21898buildPartial();
                }
                onChanged();
            } else {
                if (this.specifierCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(dataSource);
                }
                this.localBuilder_.setMessage(dataSource);
            }
            this.specifierCase_ = 1;
            return this;
        }

        public Builder clearLocal() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.specifierCase_ == 1) {
                    this.specifierCase_ = 0;
                    this.specifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.specifierCase_ == 1) {
                this.specifierCase_ = 0;
                this.specifier_ = null;
                onChanged();
            }
            return this;
        }

        public DataSource.Builder getLocalBuilder() {
            return getLocalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public DataSourceOrBuilder getLocalOrBuilder() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3;
            int i = this.specifierCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.localBuilder_) != null) {
                return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (DataSource) this.specifier_;
            }
            return DataSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getLocalFieldBuilder() {
            if (this.localBuilder_ == null) {
                if (this.specifierCase_ != 1) {
                    this.specifier_ = DataSource.getDefaultInstance();
                }
                this.localBuilder_ = new SingleFieldBuilderV3<>((DataSource) this.specifier_, getParentForChildren(), isClean());
                this.specifier_ = null;
            }
            this.specifierCase_ = 1;
            onChanged();
            return this.localBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public RemoteDataSource getRemote() {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.specifierCase_ == 2) {
                    return (RemoteDataSource) this.specifier_;
                }
                return RemoteDataSource.getDefaultInstance();
            }
            if (this.specifierCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return RemoteDataSource.getDefaultInstance();
        }

        public Builder setRemote(RemoteDataSource remoteDataSource) {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                remoteDataSource.getClass();
                this.specifier_ = remoteDataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(remoteDataSource);
            }
            this.specifierCase_ = 2;
            return this;
        }

        public Builder setRemote(RemoteDataSource.Builder builder) {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.specifier_ = builder.m23835build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23835build());
            }
            this.specifierCase_ = 2;
            return this;
        }

        public Builder mergeRemote(RemoteDataSource remoteDataSource) {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.specifierCase_ != 2 || this.specifier_ == RemoteDataSource.getDefaultInstance()) {
                    this.specifier_ = remoteDataSource;
                } else {
                    this.specifier_ = RemoteDataSource.newBuilder((RemoteDataSource) this.specifier_).mergeFrom(remoteDataSource).m23837buildPartial();
                }
                onChanged();
            } else {
                if (this.specifierCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(remoteDataSource);
                }
                this.remoteBuilder_.setMessage(remoteDataSource);
            }
            this.specifierCase_ = 2;
            return this;
        }

        public Builder clearRemote() {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.specifierCase_ == 2) {
                    this.specifierCase_ = 0;
                    this.specifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.specifierCase_ == 2) {
                this.specifierCase_ = 0;
                this.specifier_ = null;
                onChanged();
            }
            return this;
        }

        public RemoteDataSource.Builder getRemoteBuilder() {
            return getRemoteFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSourceOrBuilder
        public RemoteDataSourceOrBuilder getRemoteOrBuilder() {
            SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> singleFieldBuilderV3;
            int i = this.specifierCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.remoteBuilder_) != null) {
                return (RemoteDataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (RemoteDataSource) this.specifier_;
            }
            return RemoteDataSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RemoteDataSource, RemoteDataSource.Builder, RemoteDataSourceOrBuilder> getRemoteFieldBuilder() {
            if (this.remoteBuilder_ == null) {
                if (this.specifierCase_ != 2) {
                    this.specifier_ = RemoteDataSource.getDefaultInstance();
                }
                this.remoteBuilder_ = new SingleFieldBuilderV3<>((RemoteDataSource) this.specifier_, getParentForChildren(), isClean());
                this.specifier_ = null;
            }
            this.specifierCase_ = 2;
            onChanged();
            return this.remoteBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21608setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21602mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$AsyncDataSource$SpecifierCase;

        static {
            int[] iArr = new int[SpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$AsyncDataSource$SpecifierCase = iArr;
            try {
                iArr[SpecifierCase.LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$AsyncDataSource$SpecifierCase[SpecifierCase.REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$AsyncDataSource$SpecifierCase[SpecifierCase.SPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
