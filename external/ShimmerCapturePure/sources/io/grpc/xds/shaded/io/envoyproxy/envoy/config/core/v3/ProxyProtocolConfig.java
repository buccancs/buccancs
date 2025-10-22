package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class ProxyProtocolConfig extends GeneratedMessageV3 implements ProxyProtocolConfigOrBuilder {
    public static final int VERSION_FIELD_NUMBER = 1;
    private static final ProxyProtocolConfig DEFAULT_INSTANCE = new ProxyProtocolConfig();
    private static final Parser<ProxyProtocolConfig> PARSER = new AbstractParser<ProxyProtocolConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ProxyProtocolConfig m23739parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProxyProtocolConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int version_;

    private ProxyProtocolConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ProxyProtocolConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.version_ = 0;
    }

    private ProxyProtocolConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.version_ = codedInputStream.readEnum();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ProxyProtocolConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProxyProtocolConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ProxyProtocolProto.internal_static_envoy_config_core_v3_ProxyProtocolConfig_descriptor;
    }

    public static ProxyProtocolConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(byteBuffer);
    }

    public static ProxyProtocolConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ProxyProtocolConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(byteString);
    }

    public static ProxyProtocolConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ProxyProtocolConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(bArr);
    }

    public static ProxyProtocolConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProxyProtocolConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ProxyProtocolConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ProxyProtocolConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProxyProtocolConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ProxyProtocolConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProxyProtocolConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ProxyProtocolConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m23737toBuilder();
    }

    public static Builder newBuilder(ProxyProtocolConfig proxyProtocolConfig) {
        return DEFAULT_INSTANCE.m23737toBuilder().mergeFrom(proxyProtocolConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ProxyProtocolConfig m23732getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ProxyProtocolConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfigOrBuilder
    public int getVersionValue() {
        return this.version_;
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
        return new ProxyProtocolConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ProxyProtocolProto.internal_static_envoy_config_core_v3_ProxyProtocolConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(ProxyProtocolConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfigOrBuilder
    public Version getVersion() {
        Version versionValueOf = Version.valueOf(this.version_);
        return versionValueOf == null ? Version.UNRECOGNIZED : versionValueOf;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != Version.V1.getNumber()) {
            codedOutputStream.writeEnum(1, this.version_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = (this.version_ != Version.V1.getNumber() ? CodedOutputStream.computeEnumSize(1, this.version_) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeEnumSize;
        return iComputeEnumSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProxyProtocolConfig)) {
            return super.equals(obj);
        }
        ProxyProtocolConfig proxyProtocolConfig = (ProxyProtocolConfig) obj;
        return this.version_ == proxyProtocolConfig.version_ && this.unknownFields.equals(proxyProtocolConfig.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.version_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23734newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23737toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Version implements ProtocolMessageEnum {
        V1(0),
        V2(1),
        UNRECOGNIZED(-1);

        public static final int V1_VALUE = 0;
        public static final int V2_VALUE = 1;
        private static final Internal.EnumLiteMap<Version> internalValueMap = new Internal.EnumLiteMap<Version>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig.Version.1
            public Version findValueByNumber(int i) {
                return Version.forNumber(i);
            }
        };
        private static final Version[] VALUES = values();
        private final int value;

        Version(int i) {
            this.value = i;
        }

        public static Version forNumber(int i) {
            if (i == 0) {
                return V1;
            }
            if (i != 1) {
                return null;
            }
            return V2;
        }

        public static Internal.EnumLiteMap<Version> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Version valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ProxyProtocolConfig.getDescriptor().getEnumTypes().get(0);
        }

        public static Version valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProxyProtocolConfigOrBuilder {
        private int version_;

        private Builder() {
            this.version_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.version_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProxyProtocolProto.internal_static_envoy_config_core_v3_ProxyProtocolConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfigOrBuilder
        public int getVersionValue() {
            return this.version_;
        }

        public Builder setVersionValue(int i) {
            this.version_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProxyProtocolProto.internal_static_envoy_config_core_v3_ProxyProtocolConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(ProxyProtocolConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ProxyProtocolConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23748clear() {
            super.clear();
            this.version_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ProxyProtocolProto.internal_static_envoy_config_core_v3_ProxyProtocolConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProxyProtocolConfig m23761getDefaultInstanceForType() {
            return ProxyProtocolConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProxyProtocolConfig m23742build() throws UninitializedMessageException {
            ProxyProtocolConfig proxyProtocolConfigM23744buildPartial = m23744buildPartial();
            if (proxyProtocolConfigM23744buildPartial.isInitialized()) {
                return proxyProtocolConfigM23744buildPartial;
            }
            throw newUninitializedMessageException(proxyProtocolConfigM23744buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProxyProtocolConfig m23744buildPartial() {
            ProxyProtocolConfig proxyProtocolConfig = new ProxyProtocolConfig(this);
            proxyProtocolConfig.version_ = this.version_;
            onBuilt();
            return proxyProtocolConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23760clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23772setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23750clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23753clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23774setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23740addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23765mergeFrom(Message message) {
            if (message instanceof ProxyProtocolConfig) {
                return mergeFrom((ProxyProtocolConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ProxyProtocolConfig proxyProtocolConfig) {
            if (proxyProtocolConfig == ProxyProtocolConfig.getDefaultInstance()) {
                return this;
            }
            if (proxyProtocolConfig.version_ != 0) {
                setVersionValue(proxyProtocolConfig.getVersionValue());
            }
            m23770mergeUnknownFields(proxyProtocolConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig.Builder m23766mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig.Builder.m23766mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProxyProtocolConfigOrBuilder
        public Version getVersion() {
            Version versionValueOf = Version.valueOf(this.version_);
            return versionValueOf == null ? Version.UNRECOGNIZED : versionValueOf;
        }

        public Builder setVersion(Version version) {
            version.getClass();
            this.version_ = version.getNumber();
            onChanged();
            return this;
        }

        public Builder clearVersion() {
            this.version_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23776setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23770mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
