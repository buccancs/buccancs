package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

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
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Rds extends GeneratedMessageV3 implements RdsOrBuilder {
    public static final int CONFIG_SOURCE_FIELD_NUMBER = 1;
    public static final int RDS_RESOURCE_LOCATOR_FIELD_NUMBER = 3;
    public static final int ROUTE_CONFIG_NAME_FIELD_NUMBER = 2;
    private static final Rds DEFAULT_INSTANCE = new Rds();
    private static final Parser<Rds> PARSER = new AbstractParser<Rds>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Rds m31144parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Rds(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private ConfigSource configSource_;
    private byte memoizedIsInitialized;
    private ResourceLocator rdsResourceLocator_;
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
                            } else if (tag == 18) {
                                this.routeConfigName_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                ResourceLocator resourceLocator = this.rdsResourceLocator_;
                                ResourceLocator.Builder builderM10221toBuilder = resourceLocator != null ? resourceLocator.m10221toBuilder() : null;
                                ResourceLocator resourceLocator2 = (ResourceLocator) codedInputStream.readMessage(ResourceLocator.parser(), extensionRegistryLite);
                                this.rdsResourceLocator_ = resourceLocator2;
                                if (builderM10221toBuilder != null) {
                                    builderM10221toBuilder.mergeFrom(resourceLocator2);
                                    this.rdsResourceLocator_ = builderM10221toBuilder.m10228buildPartial();
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

    public static Rds getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Rds> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_descriptor;
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
        return DEFAULT_INSTANCE.m31142toBuilder();
    }

    public static Builder newBuilder(Rds rds) {
        return DEFAULT_INSTANCE.m31142toBuilder().mergeFrom(rds);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Rds m31137getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Rds> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public boolean hasConfigSource() {
        return this.configSource_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public boolean hasRdsResourceLocator() {
        return this.rdsResourceLocator_ != null;
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
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_fieldAccessorTable.ensureFieldAccessorsInitialized(Rds.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public ConfigSource getConfigSource() {
        ConfigSource configSource = this.configSource_;
        return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public ConfigSourceOrBuilder getConfigSourceOrBuilder() {
        return getConfigSource();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public String getRouteConfigName() {
        Object obj = this.routeConfigName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.routeConfigName_ = stringUtf8;
        return stringUtf8;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public ByteString getRouteConfigNameBytes() {
        Object obj = this.routeConfigName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.routeConfigName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public ResourceLocator getRdsResourceLocator() {
        ResourceLocator resourceLocator = this.rdsResourceLocator_;
        return resourceLocator == null ? ResourceLocator.getDefaultInstance() : resourceLocator;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
    public ResourceLocatorOrBuilder getRdsResourceLocatorOrBuilder() {
        return getRdsResourceLocator();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.configSource_ != null) {
            codedOutputStream.writeMessage(1, getConfigSource());
        }
        if (!getRouteConfigNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.routeConfigName_);
        }
        if (this.rdsResourceLocator_ != null) {
            codedOutputStream.writeMessage(3, getRdsResourceLocator());
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
        if (this.rdsResourceLocator_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getRdsResourceLocator());
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
        if ((!hasConfigSource() || getConfigSource().equals(rds.getConfigSource())) && getRouteConfigName().equals(rds.getRouteConfigName()) && hasRdsResourceLocator() == rds.hasRdsResourceLocator()) {
            return (!hasRdsResourceLocator() || getRdsResourceLocator().equals(rds.getRdsResourceLocator())) && this.unknownFields.equals(rds.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasConfigSource()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getConfigSource().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 2) * 53) + getRouteConfigName().hashCode();
        if (hasRdsResourceLocator()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getRdsResourceLocator().hashCode();
        }
        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode3;
        return iHashCode3;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31139newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31142toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RdsOrBuilder {
        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> configSourceBuilder_;
        private ConfigSource configSource_;
        private SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> rdsResourceLocatorBuilder_;
        private ResourceLocator rdsResourceLocator_;
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
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
        public boolean hasConfigSource() {
            return (this.configSourceBuilder_ == null && this.configSource_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
        public boolean hasRdsResourceLocator() {
            return (this.rdsResourceLocatorBuilder_ == null && this.rdsResourceLocator_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_fieldAccessorTable.ensureFieldAccessorsInitialized(Rds.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Rds.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31153clear() {
            super.clear();
            if (this.configSourceBuilder_ == null) {
                this.configSource_ = null;
            } else {
                this.configSource_ = null;
                this.configSourceBuilder_ = null;
            }
            this.routeConfigName_ = "";
            if (this.rdsResourceLocatorBuilder_ == null) {
                this.rdsResourceLocator_ = null;
            } else {
                this.rdsResourceLocator_ = null;
                this.rdsResourceLocatorBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m31166getDefaultInstanceForType() {
            return Rds.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m31147build() throws UninitializedMessageException {
            Rds rdsM31149buildPartial = m31149buildPartial();
            if (rdsM31149buildPartial.isInitialized()) {
                return rdsM31149buildPartial;
            }
            throw newUninitializedMessageException(rdsM31149buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Rds m31149buildPartial() {
            Rds rds = new Rds(this);
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.configSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                rds.configSource_ = this.configSource_;
            } else {
                rds.configSource_ = singleFieldBuilderV3.build();
            }
            rds.routeConfigName_ = this.routeConfigName_;
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV32 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV32 == null) {
                rds.rdsResourceLocator_ = this.rdsResourceLocator_;
            } else {
                rds.rdsResourceLocator_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return rds;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31165clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31177setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31155clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31158clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31179setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31145addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31170mergeFrom(Message message) {
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
            if (rds.hasRdsResourceLocator()) {
                mergeRdsResourceLocator(rds.getRdsResourceLocator());
            }
            m31175mergeUnknownFields(rds.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds.Builder m31171mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds.Builder.m31171mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
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
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
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
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
        public ResourceLocator getRdsResourceLocator() {
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ResourceLocator resourceLocator = this.rdsResourceLocator_;
            return resourceLocator == null ? ResourceLocator.getDefaultInstance() : resourceLocator;
        }

        public Builder setRdsResourceLocator(ResourceLocator resourceLocator) {
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV3 == null) {
                resourceLocator.getClass();
                this.rdsResourceLocator_ = resourceLocator;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(resourceLocator);
            }
            return this;
        }

        public Builder setRdsResourceLocator(ResourceLocator.Builder builder) {
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rdsResourceLocator_ = builder.m10226build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10226build());
            }
            return this;
        }

        public Builder mergeRdsResourceLocator(ResourceLocator resourceLocator) {
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV3 == null) {
                ResourceLocator resourceLocator2 = this.rdsResourceLocator_;
                if (resourceLocator2 != null) {
                    this.rdsResourceLocator_ = ResourceLocator.newBuilder(resourceLocator2).mergeFrom(resourceLocator).m10228buildPartial();
                } else {
                    this.rdsResourceLocator_ = resourceLocator;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(resourceLocator);
            }
            return this;
        }

        public Builder clearRdsResourceLocator() {
            if (this.rdsResourceLocatorBuilder_ == null) {
                this.rdsResourceLocator_ = null;
                onChanged();
            } else {
                this.rdsResourceLocator_ = null;
                this.rdsResourceLocatorBuilder_ = null;
            }
            return this;
        }

        public ResourceLocator.Builder getRdsResourceLocatorBuilder() {
            onChanged();
            return getRdsResourceLocatorFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.RdsOrBuilder
        public ResourceLocatorOrBuilder getRdsResourceLocatorOrBuilder() {
            SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.rdsResourceLocatorBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ResourceLocatorOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ResourceLocator resourceLocator = this.rdsResourceLocator_;
            return resourceLocator == null ? ResourceLocator.getDefaultInstance() : resourceLocator;
        }

        private SingleFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> getRdsResourceLocatorFieldBuilder() {
            if (this.rdsResourceLocatorBuilder_ == null) {
                this.rdsResourceLocatorBuilder_ = new SingleFieldBuilderV3<>(getRdsResourceLocator(), getParentForChildren(), isClean());
                this.rdsResourceLocator_ = null;
            }
            return this.rdsResourceLocatorBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31181setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31175mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
