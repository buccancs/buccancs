package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

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

/* loaded from: classes6.dex */
public final class Rds extends GeneratedMessageV3 implements RdsOrBuilder {
    public static final int CONFIG_SOURCE_FIELD_NUMBER = 1;
    public static final int ROUTE_CONFIG_NAME_FIELD_NUMBER = 2;
    private static final Rds DEFAULT_INSTANCE = new Rds();
    private static final Parser<Rds> PARSER = new AbstractParser<Rds>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Rds m26377parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Rds(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private ConfigSource configSource_;
    private byte memoizedIsInitialized;
    private volatile Object routeConfigName_;

    private Rds(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Rds() {
        this.memoizedIsInitialized = (byte) -1;
        this.routeConfigName_ = "";
    }

    private Rds(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            ConfigSource configSource = this.configSource_;
                            ConfigSource.Builder builderM14628toBuilder = configSource != null ? configSource.m14628toBuilder() : null;
                            ConfigSource configSource2 = (ConfigSource) codedInputStream.readMessage(ConfigSource.parser(), extensionRegistryLite);
                            this.configSource_ = configSource2;
                            if (builderM14628toBuilder != null) {
                                builderM14628toBuilder.mergeFrom(configSource2);
                                this.configSource_ = builderM14628toBuilder.m14635buildPartial();
                            }
                        } else if (tag == 18) {
                            this.routeConfigName_ = codedInputStream.readStringRequireUtf8();
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

    public static Rds getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Rds> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_Rds_descriptor;
    }

    public static Rds parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(byteBuffer);
    }

    public static Rds parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Rds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(byteString);
    }

    public static Rds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Rds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(bArr);
    }

    public static Rds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Rds) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Rds parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Rds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Rds parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Rds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Rds parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Rds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m26375toBuilder();
    }

    public static Builder newBuilder(Rds rds) {
        return DEFAULT_INSTANCE.m26375toBuilder().mergeFrom(rds);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Rds m26370getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Rds> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
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
        return new Rds();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_Rds_fieldAccessorTable.ensureFieldAccessorsInitialized(Rds.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
    public ConfigSource getConfigSource() {
        ConfigSource configSource = this.configSource_;
        return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
    public ConfigSourceOrBuilder getConfigSourceOrBuilder() {
        return getConfigSource();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
    public String getRouteConfigName() {
        Object obj = this.routeConfigName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.routeConfigName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
    public ByteString getRouteConfigNameBytes() {
        Object obj = this.routeConfigName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.routeConfigName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.configSource_ != null) {
            codedOutputStream.writeMessage(1, getConfigSource());
        }
        if (!getRouteConfigNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.routeConfigName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.configSource_ != null ? CodedOutputStream.computeMessageSize(1, getConfigSource()) : 0;
        if (!getRouteConfigNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.routeConfigName_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rds)) {
            return super.equals(obj);
        }
        Rds rds = (Rds) obj;
        if (hasConfigSource() != rds.hasConfigSource()) {
            return false;
        }
        return (!hasConfigSource() || getConfigSource().equals(rds.getConfigSource())) && getRouteConfigName().equals(rds.getRouteConfigName()) && this.unknownFields.equals(rds.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasConfigSource()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getConfigSource().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 2) * 53) + getRouteConfigName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m26372newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m26375toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RdsOrBuilder {
        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> configSourceBuilder_;
        private ConfigSource configSource_;
        private Object routeConfigName_;

        private Builder() {
            this.routeConfigName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.routeConfigName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_Rds_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
        public boolean hasConfigSource() {
            return (this.configSourceBuilder_ == null && this.configSource_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_Rds_fieldAccessorTable.ensureFieldAccessorsInitialized(Rds.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Rds.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26386clear() {
            super.clear();
            if (this.configSourceBuilder_ == null) {
                this.configSource_ = null;
            } else {
                this.configSource_ = null;
                this.configSourceBuilder_ = null;
            }
            this.routeConfigName_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_Rds_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m26399getDefaultInstanceForType() {
            return Rds.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m26380build() throws UninitializedMessageException {
            Rds rdsM26382buildPartial = m26382buildPartial();
            if (rdsM26382buildPartial.isInitialized()) {
                return rdsM26382buildPartial;
            }
            throw newUninitializedMessageException(rdsM26382buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m26382buildPartial() {
            Rds rds = new Rds(this);
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                rds.configSource_ = this.configSource_;
            } else {
                rds.configSource_ = singleFieldBuilderV3.build();
            }
            rds.routeConfigName_ = this.routeConfigName_;
            onBuilt();
            return rds;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26398clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26410setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26388clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26391clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26412setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26378addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26403mergeFrom(Message message) {
            if (message instanceof Rds) {
                return mergeFrom((Rds) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Rds rds) {
            if (rds == Rds.getDefaultInstance()) {
                return this;
            }
            if (rds.hasConfigSource()) {
                mergeConfigSource(rds.getConfigSource());
            }
            if (!rds.getRouteConfigName().isEmpty()) {
                this.routeConfigName_ = rds.routeConfigName_;
                onChanged();
            }
            m26408mergeUnknownFields(rds.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds.Builder m26404mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds.Builder.m26404mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.Rds$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
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
                this.configSource_ = builder.m14633build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14633build());
            }
            return this;
        }

        public Builder mergeConfigSource(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                ConfigSource configSource2 = this.configSource_;
                if (configSource2 != null) {
                    this.configSource_ = ConfigSource.newBuilder(configSource2).mergeFrom(configSource).m14635buildPartial();
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
        public String getRouteConfigName() {
            Object obj = this.routeConfigName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.routeConfigName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRouteConfigName(String str) {
            str.getClass();
            this.routeConfigName_ = str;
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RdsOrBuilder
        public ByteString getRouteConfigNameBytes() {
            Object obj = this.routeConfigName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.routeConfigName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRouteConfigNameBytes(ByteString byteString) {
            byteString.getClass();
            Rds.checkByteStringIsUtf8(byteString);
            this.routeConfigName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRouteConfigName() {
            this.routeConfigName_ = Rds.getDefaultInstance().getRouteConfigName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m26414setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m26408mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
