package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class TransportSocket extends GeneratedMessageV3 implements TransportSocketOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final TransportSocket DEFAULT_INSTANCE = new TransportSocket();
    private static final Parser<TransportSocket> PARSER = new AbstractParser<TransportSocket>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TransportSocket m24389parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TransportSocket(codedInputStream, extensionRegistryLite);
        }
    };
    private int configTypeCase_;
    private Object configType_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private TransportSocket(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private TransportSocket() {
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private TransportSocket(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            Any.Builder builder = this.configTypeCase_ == 3 ? ((Any) this.configType_).toBuilder() : null;
                            Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                            this.configType_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.configType_ = builder.buildPartial();
                            }
                            this.configTypeCase_ = 3;
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TransportSocket getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TransportSocket> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_TransportSocket_descriptor;
    }

    public static TransportSocket parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(byteBuffer);
    }

    public static TransportSocket parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TransportSocket parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(byteString);
    }

    public static TransportSocket parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TransportSocket parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(bArr);
    }

    public static TransportSocket parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransportSocket) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TransportSocket parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TransportSocket parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TransportSocket parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TransportSocket parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TransportSocket parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TransportSocket parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24387toBuilder();
    }

    public static Builder newBuilder(TransportSocket transportSocket) {
        return DEFAULT_INSTANCE.m24387toBuilder().mergeFrom(transportSocket);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TransportSocket m24382getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<TransportSocket> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public boolean hasTypedConfig() {
        return this.configTypeCase_ == 3;
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
        return new TransportSocket();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_TransportSocket_fieldAccessorTable.ensureFieldAccessorsInitialized(TransportSocket.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public ConfigTypeCase getConfigTypeCase() {
        return ConfigTypeCase.forNumber(this.configTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public Any getTypedConfig() {
        if (this.configTypeCase_ == 3) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        if (this.configTypeCase_ == 3) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.configTypeCase_ == 3) {
            codedOutputStream.writeMessage(3, (Any) this.configType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.configTypeCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (Any) this.configType_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransportSocket)) {
            return super.equals(obj);
        }
        TransportSocket transportSocket = (TransportSocket) obj;
        if (getName().equals(transportSocket.getName()) && getConfigTypeCase().equals(transportSocket.getConfigTypeCase())) {
            return (this.configTypeCase_ != 3 || getTypedConfig().equals(transportSocket.getTypedConfig())) && this.unknownFields.equals(transportSocket.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (this.configTypeCase_ == 3) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getTypedConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24384newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24387toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TYPED_CONFIG(3),
        CONFIGTYPE_NOT_SET(0);

        private final int value;

        ConfigTypeCase(int i) {
            this.value = i;
        }

        public static ConfigTypeCase forNumber(int i) {
            if (i == 0) {
                return CONFIGTYPE_NOT_SET;
            }
            if (i != 3) {
                return null;
            }
            return TYPED_CONFIG;
        }

        @Deprecated
        public static ConfigTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TransportSocketOrBuilder {
        private int configTypeCase_;
        private Object configType_;
        private Object name_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;

        private Builder() {
            this.configTypeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.configTypeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_TransportSocket_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public boolean hasTypedConfig() {
            return this.configTypeCase_ == 3;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_TransportSocket_fieldAccessorTable.ensureFieldAccessorsInitialized(TransportSocket.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = TransportSocket.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24398clear() {
            super.clear();
            this.name_ = "";
            this.configTypeCase_ = 0;
            this.configType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_TransportSocket_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TransportSocket m24411getDefaultInstanceForType() {
            return TransportSocket.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TransportSocket m24392build() throws UninitializedMessageException {
            TransportSocket transportSocketM24394buildPartial = m24394buildPartial();
            if (transportSocketM24394buildPartial.isInitialized()) {
                return transportSocketM24394buildPartial;
            }
            throw newUninitializedMessageException(transportSocketM24394buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TransportSocket m24394buildPartial() {
            TransportSocket transportSocket = new TransportSocket(this);
            transportSocket.name_ = this.name_;
            if (this.configTypeCase_ == 3) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    transportSocket.configType_ = this.configType_;
                } else {
                    transportSocket.configType_ = singleFieldBuilderV3.build();
                }
            }
            transportSocket.configTypeCase_ = this.configTypeCase_;
            onBuilt();
            return transportSocket;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24410clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24422setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24400clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24403clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24424setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24390addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24415mergeFrom(Message message) {
            if (message instanceof TransportSocket) {
                return mergeFrom((TransportSocket) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TransportSocket transportSocket) {
            if (transportSocket == TransportSocket.getDefaultInstance()) {
                return this;
            }
            if (!transportSocket.getName().isEmpty()) {
                this.name_ = transportSocket.name_;
                onChanged();
            }
            if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$TransportSocket$ConfigTypeCase[transportSocket.getConfigTypeCase().ordinal()] == 1) {
                mergeTypedConfig(transportSocket.getTypedConfig());
            }
            m24420mergeUnknownFields(transportSocket.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket.Builder m24416mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket.Builder.m24416mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        public Builder clearConfigType() {
            this.configTypeCase_ = 0;
            this.configType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            TransportSocket.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = TransportSocket.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public Any getTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 3) {
                    return (Any) this.configType_;
                }
                return Any.getDefaultInstance();
            }
            if (this.configTypeCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.configType_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            this.configTypeCase_ = 3;
            return this;
        }

        public Builder setTypedConfig(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.configTypeCase_ = 3;
            return this;
        }

        public Builder mergeTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 3 || this.configType_ == Any.getDefaultInstance()) {
                    this.configType_ = any;
                } else {
                    this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.typedConfigBuilder_.setMessage(any);
            }
            this.configTypeCase_ = 3;
            return this;
        }

        public Builder clearTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 3) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 3) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        public Any.Builder getTypedConfigBuilder() {
            return getTypedConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
            if (this.typedConfigBuilder_ == null) {
                if (this.configTypeCase_ != 3) {
                    this.configType_ = Any.getDefaultInstance();
                }
                this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 3;
            onChanged();
            return this.typedConfigBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24426setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24420mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$TransportSocket$ConfigTypeCase;

        static {
            int[] iArr = new int[ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$TransportSocket$ConfigTypeCase = iArr;
            try {
                iArr[ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$TransportSocket$ConfigTypeCase[ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
