package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Vhds extends GeneratedMessageV3 implements VhdsOrBuilder {
    public static final int CONFIG_SOURCE_FIELD_NUMBER = 1;
    private static final Vhds DEFAULT_INSTANCE = new Vhds();
    private static final Parser<Vhds> PARSER = new AbstractParser<Vhds>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Vhds m29840parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Vhds(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private ConfigSource configSource_;
    private byte memoizedIsInitialized;

    private Vhds(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Vhds() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Vhds(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                ConfigSource configSource = this.configSource_;
                                ConfigSource.Builder builderM21799toBuilder = configSource != null ? configSource.m21799toBuilder() : null;
                                ConfigSource configSource2 = (ConfigSource) codedInputStream.readMessage(ConfigSource.parser(), extensionRegistryLite);
                                this.configSource_ = configSource2;
                                if (builderM21799toBuilder != null) {
                                    builderM21799toBuilder.mergeFrom(configSource2);
                                    this.configSource_ = builderM21799toBuilder.m21806buildPartial();
                                }
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

    public static Vhds getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Vhds> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteProto.internal_static_envoy_config_route_v3_Vhds_descriptor;
    }

    public static Vhds parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(byteBuffer);
    }

    public static Vhds parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Vhds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(byteString);
    }

    public static Vhds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Vhds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(bArr);
    }

    public static Vhds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Vhds) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Vhds parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Vhds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Vhds parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Vhds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Vhds parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Vhds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m29838toBuilder();
    }

    public static Builder newBuilder(Vhds vhds) {
        return DEFAULT_INSTANCE.m29838toBuilder().mergeFrom(vhds);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Vhds m29833getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Vhds> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
    public boolean hasConfigSource() {
        return this.configSource_ != null;
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
        return new Vhds();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteProto.internal_static_envoy_config_route_v3_Vhds_fieldAccessorTable.ensureFieldAccessorsInitialized(Vhds.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
    public ConfigSource getConfigSource() {
        ConfigSource configSource = this.configSource_;
        return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
    public ConfigSourceOrBuilder getConfigSourceOrBuilder() {
        return getConfigSource();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.configSource_ != null) {
            codedOutputStream.writeMessage(1, getConfigSource());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.configSource_ != null ? CodedOutputStream.computeMessageSize(1, getConfigSource()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vhds)) {
            return super.equals(obj);
        }
        Vhds vhds = (Vhds) obj;
        if (hasConfigSource() != vhds.hasConfigSource()) {
            return false;
        }
        return (!hasConfigSource() || getConfigSource().equals(vhds.getConfigSource())) && this.unknownFields.equals(vhds.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasConfigSource()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getConfigSource().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m29835newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m29838toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VhdsOrBuilder {
        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> configSourceBuilder_;
        private ConfigSource configSource_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteProto.internal_static_envoy_config_route_v3_Vhds_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
        public boolean hasConfigSource() {
            return (this.configSourceBuilder_ == null && this.configSource_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteProto.internal_static_envoy_config_route_v3_Vhds_fieldAccessorTable.ensureFieldAccessorsInitialized(Vhds.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Vhds.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29849clear() {
            super.clear();
            if (this.configSourceBuilder_ == null) {
                this.configSource_ = null;
            } else {
                this.configSource_ = null;
                this.configSourceBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteProto.internal_static_envoy_config_route_v3_Vhds_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Vhds m29862getDefaultInstanceForType() {
            return Vhds.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Vhds m29843build() throws UninitializedMessageException {
            Vhds vhdsM29845buildPartial = m29845buildPartial();
            if (vhdsM29845buildPartial.isInitialized()) {
                return vhdsM29845buildPartial;
            }
            throw newUninitializedMessageException(vhdsM29845buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Vhds m29845buildPartial() {
            Vhds vhds = new Vhds(this);
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                vhds.configSource_ = this.configSource_;
            } else {
                vhds.configSource_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return vhds;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29861clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29873setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29851clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29854clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29875setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29841addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29866mergeFrom(Message message) {
            if (message instanceof Vhds) {
                return mergeFrom((Vhds) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Vhds vhds) {
            if (vhds == Vhds.getDefaultInstance()) {
                return this;
            }
            if (vhds.hasConfigSource()) {
                mergeConfigSource(vhds.getConfigSource());
            }
            m29871mergeUnknownFields(vhds.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds.Builder m29867mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds.Builder.m29867mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Vhds$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
        public ConfigSource getConfigSource() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ConfigSource configSource = this.configSource_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        public Builder setConfigSource(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                configSource.getClass();
                this.configSource_ = configSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(configSource);
            }
            return this;
        }

        public Builder setConfigSource(ConfigSource.Builder builder) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSource_ = builder.m21804build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m21804build());
            }
            return this;
        }

        public Builder mergeConfigSource(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                ConfigSource configSource2 = this.configSource_;
                if (configSource2 != null) {
                    this.configSource_ = ConfigSource.newBuilder(configSource2).mergeFrom(configSource).m21806buildPartial();
                } else {
                    this.configSource_ = configSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(configSource);
            }
            return this;
        }

        public Builder clearConfigSource() {
            if (this.configSourceBuilder_ == null) {
                this.configSource_ = null;
                onChanged();
            } else {
                this.configSource_ = null;
                this.configSourceBuilder_ = null;
            }
            return this;
        }

        public ConfigSource.Builder getConfigSourceBuilder() {
            onChanged();
            return getConfigSourceFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.VhdsOrBuilder
        public ConfigSourceOrBuilder getConfigSourceOrBuilder() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ConfigSource configSource = this.configSource_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> getConfigSourceFieldBuilder() {
            if (this.configSourceBuilder_ == null) {
                this.configSourceBuilder_ = new SingleFieldBuilderV3<>(getConfigSource(), getParentForChildren(), isClean());
                this.configSource_ = null;
            }
            return this.configSourceBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m29877setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m29871mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
