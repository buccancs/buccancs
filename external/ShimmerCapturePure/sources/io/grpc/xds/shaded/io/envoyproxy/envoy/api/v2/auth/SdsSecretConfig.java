package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class SdsSecretConfig extends GeneratedMessageV3 implements SdsSecretConfigOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int SDS_CONFIG_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final SdsSecretConfig DEFAULT_INSTANCE = new SdsSecretConfig();
    private static final Parser<SdsSecretConfig> PARSER = new AbstractParser<SdsSecretConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SdsSecretConfig m13750parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SdsSecretConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private ConfigSource sdsConfig_;

    private SdsSecretConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SdsSecretConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private SdsSecretConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            ConfigSource configSource = this.sdsConfig_;
                            ConfigSource.Builder builderM14628toBuilder = configSource != null ? configSource.m14628toBuilder() : null;
                            ConfigSource configSource2 = (ConfigSource) codedInputStream.readMessage(ConfigSource.parser(), extensionRegistryLite);
                            this.sdsConfig_ = configSource2;
                            if (builderM14628toBuilder != null) {
                                builderM14628toBuilder.mergeFrom(configSource2);
                                this.sdsConfig_ = builderM14628toBuilder.m14635buildPartial();
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

    public static SdsSecretConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SdsSecretConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SecretProto.internal_static_envoy_api_v2_auth_SdsSecretConfig_descriptor;
    }

    public static SdsSecretConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(byteBuffer);
    }

    public static SdsSecretConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SdsSecretConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(byteString);
    }

    public static SdsSecretConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SdsSecretConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(bArr);
    }

    public static SdsSecretConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsSecretConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SdsSecretConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SdsSecretConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SdsSecretConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SdsSecretConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SdsSecretConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SdsSecretConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13748toBuilder();
    }

    public static Builder newBuilder(SdsSecretConfig sdsSecretConfig) {
        return DEFAULT_INSTANCE.m13748toBuilder().mergeFrom(sdsSecretConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SdsSecretConfig m13743getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SdsSecretConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
    public boolean hasSdsConfig() {
        return this.sdsConfig_ != null;
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
        return new SdsSecretConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SecretProto.internal_static_envoy_api_v2_auth_SdsSecretConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(SdsSecretConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
    public ConfigSource getSdsConfig() {
        ConfigSource configSource = this.sdsConfig_;
        return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
    public ConfigSourceOrBuilder getSdsConfigOrBuilder() {
        return getSdsConfig();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.sdsConfig_ != null) {
            codedOutputStream.writeMessage(2, getSdsConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.sdsConfig_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getSdsConfig());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdsSecretConfig)) {
            return super.equals(obj);
        }
        SdsSecretConfig sdsSecretConfig = (SdsSecretConfig) obj;
        if (getName().equals(sdsSecretConfig.getName()) && hasSdsConfig() == sdsSecretConfig.hasSdsConfig()) {
            return (!hasSdsConfig() || getSdsConfig().equals(sdsSecretConfig.getSdsConfig())) && this.unknownFields.equals(sdsSecretConfig.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasSdsConfig()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getSdsConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13745newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13748toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SdsSecretConfigOrBuilder {
        private Object name_;
        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> sdsConfigBuilder_;
        private ConfigSource sdsConfig_;

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
            return SecretProto.internal_static_envoy_api_v2_auth_SdsSecretConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
        public boolean hasSdsConfig() {
            return (this.sdsConfigBuilder_ == null && this.sdsConfig_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecretProto.internal_static_envoy_api_v2_auth_SdsSecretConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(SdsSecretConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SdsSecretConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13759clear() {
            super.clear();
            this.name_ = "";
            if (this.sdsConfigBuilder_ == null) {
                this.sdsConfig_ = null;
            } else {
                this.sdsConfig_ = null;
                this.sdsConfigBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SecretProto.internal_static_envoy_api_v2_auth_SdsSecretConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsSecretConfig m13772getDefaultInstanceForType() {
            return SdsSecretConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsSecretConfig m13753build() throws UninitializedMessageException {
            SdsSecretConfig sdsSecretConfigM13755buildPartial = m13755buildPartial();
            if (sdsSecretConfigM13755buildPartial.isInitialized()) {
                return sdsSecretConfigM13755buildPartial;
            }
            throw newUninitializedMessageException(sdsSecretConfigM13755buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsSecretConfig m13755buildPartial() {
            SdsSecretConfig sdsSecretConfig = new SdsSecretConfig(this);
            sdsSecretConfig.name_ = this.name_;
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                sdsSecretConfig.sdsConfig_ = this.sdsConfig_;
            } else {
                sdsSecretConfig.sdsConfig_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return sdsSecretConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13771clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13783setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13761clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13764clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13785setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13751addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13776mergeFrom(Message message) {
            if (message instanceof SdsSecretConfig) {
                return mergeFrom((SdsSecretConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SdsSecretConfig sdsSecretConfig) {
            if (sdsSecretConfig == SdsSecretConfig.getDefaultInstance()) {
                return this;
            }
            if (!sdsSecretConfig.getName().isEmpty()) {
                this.name_ = sdsSecretConfig.name_;
                onChanged();
            }
            if (sdsSecretConfig.hasSdsConfig()) {
                mergeSdsConfig(sdsSecretConfig.getSdsConfig());
            }
            m13781mergeUnknownFields(sdsSecretConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig.Builder m13777mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig.Builder.m13777mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
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
            SdsSecretConfig.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = SdsSecretConfig.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
        public ConfigSource getSdsConfig() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ConfigSource configSource = this.sdsConfig_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        public Builder setSdsConfig(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                configSource.getClass();
                this.sdsConfig_ = configSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(configSource);
            }
            return this;
        }

        public Builder setSdsConfig(ConfigSource.Builder builder) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sdsConfig_ = builder.m14633build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14633build());
            }
            return this;
        }

        public Builder mergeSdsConfig(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                ConfigSource configSource2 = this.sdsConfig_;
                if (configSource2 != null) {
                    this.sdsConfig_ = ConfigSource.newBuilder(configSource2).mergeFrom(configSource).m14635buildPartial();
                } else {
                    this.sdsConfig_ = configSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(configSource);
            }
            return this;
        }

        public Builder clearSdsConfig() {
            if (this.sdsConfigBuilder_ == null) {
                this.sdsConfig_ = null;
                onChanged();
            } else {
                this.sdsConfig_ = null;
                this.sdsConfigBuilder_ = null;
            }
            return this;
        }

        public ConfigSource.Builder getSdsConfigBuilder() {
            onChanged();
            return getSdsConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.SdsSecretConfigOrBuilder
        public ConfigSourceOrBuilder getSdsConfigOrBuilder() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.sdsConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ConfigSource configSource = this.sdsConfig_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> getSdsConfigFieldBuilder() {
            if (this.sdsConfigBuilder_ == null) {
                this.sdsConfigBuilder_ = new SingleFieldBuilderV3<>(getSdsConfig(), getParentForChildren(), isClean());
                this.sdsConfig_ = null;
            }
            return this.sdsConfigBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13787setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13781mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
