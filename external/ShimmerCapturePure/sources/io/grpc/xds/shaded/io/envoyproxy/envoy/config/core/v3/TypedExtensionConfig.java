package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
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
public final class TypedExtensionConfig extends GeneratedMessageV3 implements TypedExtensionConfigOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPED_CONFIG_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final TypedExtensionConfig DEFAULT_INSTANCE = new TypedExtensionConfig();
    private static final Parser<TypedExtensionConfig> PARSER = new AbstractParser<TypedExtensionConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TypedExtensionConfig m24435parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TypedExtensionConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private Any typedConfig_;

    private TypedExtensionConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TypedExtensionConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private TypedExtensionConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (tag == 18) {
                            Any any = this.typedConfig_;
                            Any.Builder builder = any != null ? any.toBuilder() : null;
                            Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                            this.typedConfig_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.typedConfig_ = builder.buildPartial();
                            }
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

    public static TypedExtensionConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TypedExtensionConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ExtensionProto.internal_static_envoy_config_core_v3_TypedExtensionConfig_descriptor;
    }

    public static TypedExtensionConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(byteBuffer);
    }

    public static TypedExtensionConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TypedExtensionConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(byteString);
    }

    public static TypedExtensionConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TypedExtensionConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(bArr);
    }

    public static TypedExtensionConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TypedExtensionConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TypedExtensionConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TypedExtensionConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TypedExtensionConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TypedExtensionConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TypedExtensionConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TypedExtensionConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24433toBuilder();
    }

    public static Builder newBuilder(TypedExtensionConfig typedExtensionConfig) {
        return DEFAULT_INSTANCE.m24433toBuilder().mergeFrom(typedExtensionConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TypedExtensionConfig m24428getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<TypedExtensionConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
    public boolean hasTypedConfig() {
        return this.typedConfig_ != null;
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
        return new TypedExtensionConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ExtensionProto.internal_static_envoy_config_core_v3_TypedExtensionConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(TypedExtensionConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
    public Any getTypedConfig() {
        Any any = this.typedConfig_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        return getTypedConfig();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.typedConfig_ != null) {
            codedOutputStream.writeMessage(2, getTypedConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.typedConfig_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getTypedConfig());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypedExtensionConfig)) {
            return super.equals(obj);
        }
        TypedExtensionConfig typedExtensionConfig = (TypedExtensionConfig) obj;
        if (getName().equals(typedExtensionConfig.getName()) && hasTypedConfig() == typedExtensionConfig.hasTypedConfig()) {
            return (!hasTypedConfig() || getTypedConfig().equals(typedExtensionConfig.getTypedConfig())) && this.unknownFields.equals(typedExtensionConfig.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasTypedConfig()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getTypedConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24430newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24433toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TypedExtensionConfigOrBuilder {
        private Object name_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;
        private Any typedConfig_;

        private Builder() {
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ExtensionProto.internal_static_envoy_config_core_v3_TypedExtensionConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
        public boolean hasTypedConfig() {
            return (this.typedConfigBuilder_ == null && this.typedConfig_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExtensionProto.internal_static_envoy_config_core_v3_TypedExtensionConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(TypedExtensionConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = TypedExtensionConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24444clear() {
            super.clear();
            this.name_ = "";
            if (this.typedConfigBuilder_ == null) {
                this.typedConfig_ = null;
            } else {
                this.typedConfig_ = null;
                this.typedConfigBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ExtensionProto.internal_static_envoy_config_core_v3_TypedExtensionConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TypedExtensionConfig m24457getDefaultInstanceForType() {
            return TypedExtensionConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TypedExtensionConfig m24438build() throws UninitializedMessageException {
            TypedExtensionConfig typedExtensionConfigM24440buildPartial = m24440buildPartial();
            if (typedExtensionConfigM24440buildPartial.isInitialized()) {
                return typedExtensionConfigM24440buildPartial;
            }
            throw newUninitializedMessageException(typedExtensionConfigM24440buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TypedExtensionConfig m24440buildPartial() {
            TypedExtensionConfig typedExtensionConfig = new TypedExtensionConfig(this);
            typedExtensionConfig.name_ = this.name_;
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                typedExtensionConfig.typedConfig_ = this.typedConfig_;
            } else {
                typedExtensionConfig.typedConfig_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return typedExtensionConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24456clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24468setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24446clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24449clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24470setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24436addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24461mergeFrom(Message message) {
            if (message instanceof TypedExtensionConfig) {
                return mergeFrom((TypedExtensionConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TypedExtensionConfig typedExtensionConfig) {
            if (typedExtensionConfig == TypedExtensionConfig.getDefaultInstance()) {
                return this;
            }
            if (!typedExtensionConfig.getName().isEmpty()) {
                this.name_ = typedExtensionConfig.name_;
                onChanged();
            }
            if (typedExtensionConfig.hasTypedConfig()) {
                mergeTypedConfig(typedExtensionConfig.getTypedConfig());
            }
            m24466mergeUnknownFields(typedExtensionConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig.Builder m24462mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig.Builder.m24462mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
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
            TypedExtensionConfig.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = TypedExtensionConfig.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
        public Any getTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Any any = this.typedConfig_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        public Builder setTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.typedConfig_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setTypedConfig(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.typedConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.typedConfig_;
                if (any2 != null) {
                    this.typedConfig_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.typedConfig_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearTypedConfig() {
            if (this.typedConfigBuilder_ == null) {
                this.typedConfig_ = null;
                onChanged();
            } else {
                this.typedConfig_ = null;
                this.typedConfigBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getTypedConfigBuilder() {
            onChanged();
            return getTypedConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.typedConfig_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
            if (this.typedConfigBuilder_ == null) {
                this.typedConfigBuilder_ = new SingleFieldBuilderV3<>(getTypedConfig(), getParentForChildren(), isClean());
                this.typedConfig_ = null;
            }
            return this.typedConfigBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24472setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24466mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
