package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ExtensionConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ExtensionConfigSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class HttpFilter extends GeneratedMessageV3 implements HttpFilterOrBuilder {
    public static final int CONFIG_DISCOVERY_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPED_CONFIG_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final HttpFilter DEFAULT_INSTANCE = new HttpFilter();
    private static final Parser<HttpFilter> PARSER = new AbstractParser<HttpFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HttpFilter m31052parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private int configTypeCase_;
    private Object configType_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private HttpFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpFilter() {
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private HttpFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Any.Builder builderM22030toBuilder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 10) {
                            if (tag == 34) {
                                builderM22030toBuilder = this.configTypeCase_ == 4 ? ((Any) this.configType_).toBuilder() : null;
                                Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.configType_ = message;
                                if (builderM22030toBuilder != null) {
                                    builderM22030toBuilder.mergeFrom(message);
                                    this.configType_ = builderM22030toBuilder.buildPartial();
                                }
                                this.configTypeCase_ = 4;
                            } else if (tag == 42) {
                                builderM22030toBuilder = this.configTypeCase_ == 5 ? ((ExtensionConfigSource) this.configType_).m22030toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(ExtensionConfigSource.parser(), extensionRegistryLite);
                                this.configType_ = message2;
                                if (builderM22030toBuilder != null) {
                                    builderM22030toBuilder.mergeFrom((ExtensionConfigSource) message2);
                                    this.configType_ = builderM22030toBuilder.m22037buildPartial();
                                }
                                this.configTypeCase_ = 5;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static HttpFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_descriptor;
    }

    public static HttpFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(byteBuffer);
    }

    public static HttpFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(byteString);
    }

    public static HttpFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(bArr);
    }

    public static HttpFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m31050toBuilder();
    }

    public static Builder newBuilder(HttpFilter httpFilter) {
        return DEFAULT_INSTANCE.m31050toBuilder().mergeFrom(httpFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HttpFilter m31045getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HttpFilter> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public boolean hasConfigDiscovery() {
        return this.configTypeCase_ == 5;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public boolean hasTypedConfig() {
        return this.configTypeCase_ == 4;
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
        return new HttpFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpFilter.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public ConfigTypeCase getConfigTypeCase() {
        return ConfigTypeCase.forNumber(this.configTypeCase_);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public Any getTypedConfig() {
        if (this.configTypeCase_ == 4) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        if (this.configTypeCase_ == 4) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public ExtensionConfigSource getConfigDiscovery() {
        if (this.configTypeCase_ == 5) {
            return (ExtensionConfigSource) this.configType_;
        }
        return ExtensionConfigSource.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
    public ExtensionConfigSourceOrBuilder getConfigDiscoveryOrBuilder() {
        if (this.configTypeCase_ == 5) {
            return (ExtensionConfigSource) this.configType_;
        }
        return ExtensionConfigSource.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.configTypeCase_ == 4) {
            codedOutputStream.writeMessage(4, (Any) this.configType_);
        }
        if (this.configTypeCase_ == 5) {
            codedOutputStream.writeMessage(5, (ExtensionConfigSource) this.configType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.configTypeCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (Any) this.configType_);
        }
        if (this.configTypeCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (ExtensionConfigSource) this.configType_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpFilter)) {
            return super.equals(obj);
        }
        HttpFilter httpFilter = (HttpFilter) obj;
        if (!getName().equals(httpFilter.getName()) || !getConfigTypeCase().equals(httpFilter.getConfigTypeCase())) {
            return false;
        }
        int i = this.configTypeCase_;
        if (i == 4) {
            if (!getTypedConfig().equals(httpFilter.getTypedConfig())) {
                return false;
            }
        } else if (i == 5 && !getConfigDiscovery().equals(httpFilter.getConfigDiscovery())) {
            return false;
        }
        return this.unknownFields.equals(httpFilter.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        int i2 = this.configTypeCase_;
        if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getTypedConfig().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getConfigDiscovery().hashCode();
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
    public Builder m31047newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31050toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TYPED_CONFIG(4),
        CONFIG_DISCOVERY(5),
        CONFIGTYPE_NOT_SET(0);

        private final int value;

        ConfigTypeCase(int i) {
            this.value = i;
        }

        public static ConfigTypeCase forNumber(int i) {
            if (i == 0) {
                return CONFIGTYPE_NOT_SET;
            }
            if (i == 4) {
                return TYPED_CONFIG;
            }
            if (i != 5) {
                return null;
            }
            return CONFIG_DISCOVERY;
        }

        @Deprecated
        public static ConfigTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpFilterOrBuilder {
        private SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> configDiscoveryBuilder_;
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
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public boolean hasConfigDiscovery() {
            return this.configTypeCase_ == 5;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public boolean hasTypedConfig() {
            return this.configTypeCase_ == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HttpFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31061clear() {
            super.clear();
            this.name_ = "";
            this.configTypeCase_ = 0;
            this.configType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpFilter m31074getDefaultInstanceForType() {
            return HttpFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpFilter m31055build() throws UninitializedMessageException {
            HttpFilter httpFilterM31057buildPartial = m31057buildPartial();
            if (httpFilterM31057buildPartial.isInitialized()) {
                return httpFilterM31057buildPartial;
            }
            throw newUninitializedMessageException(httpFilterM31057buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpFilter m31057buildPartial() {
            HttpFilter httpFilter = new HttpFilter(this);
            httpFilter.name_ = this.name_;
            if (this.configTypeCase_ == 4) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    httpFilter.configType_ = this.configType_;
                } else {
                    httpFilter.configType_ = singleFieldBuilderV3.build();
                }
            }
            if (this.configTypeCase_ == 5) {
                SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV32 = this.configDiscoveryBuilder_;
                if (singleFieldBuilderV32 == null) {
                    httpFilter.configType_ = this.configType_;
                } else {
                    httpFilter.configType_ = singleFieldBuilderV32.build();
                }
            }
            httpFilter.configTypeCase_ = this.configTypeCase_;
            onBuilt();
            return httpFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31073clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31085setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31063clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31066clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31087setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31053addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31078mergeFrom(Message message) {
            if (message instanceof HttpFilter) {
                return mergeFrom((HttpFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpFilter httpFilter) {
            if (httpFilter == HttpFilter.getDefaultInstance()) {
                return this;
            }
            if (!httpFilter.getName().isEmpty()) {
                this.name_ = httpFilter.name_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$HttpFilter$ConfigTypeCase[httpFilter.getConfigTypeCase().ordinal()];
            if (i == 1) {
                mergeTypedConfig(httpFilter.getTypedConfig());
            } else if (i == 2) {
                mergeConfigDiscovery(httpFilter.getConfigDiscovery());
            }
            m31083mergeUnknownFields(httpFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter.Builder m31079mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter.Builder.m31079mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        public Builder clearConfigType() {
            this.configTypeCase_ = 0;
            this.configType_ = null;
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
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
            HttpFilter.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = HttpFilter.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public Any getTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 4) {
                    return (Any) this.configType_;
                }
                return Any.getDefaultInstance();
            }
            if (this.configTypeCase_ == 4) {
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
            this.configTypeCase_ = 4;
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
            this.configTypeCase_ = 4;
            return this;
        }

        public Builder mergeTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 4 || this.configType_ == Any.getDefaultInstance()) {
                    this.configType_ = any;
                } else {
                    this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.typedConfigBuilder_.setMessage(any);
            }
            this.configTypeCase_ = 4;
            return this;
        }

        public Builder clearTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 4) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 4) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        public Any.Builder getTypedConfigBuilder() {
            return getTypedConfigFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
            if (this.typedConfigBuilder_ == null) {
                if (this.configTypeCase_ != 4) {
                    this.configType_ = Any.getDefaultInstance();
                }
                this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 4;
            onChanged();
            return this.typedConfigBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public ExtensionConfigSource getConfigDiscovery() {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3 = this.configDiscoveryBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 5) {
                    return (ExtensionConfigSource) this.configType_;
                }
                return ExtensionConfigSource.getDefaultInstance();
            }
            if (this.configTypeCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return ExtensionConfigSource.getDefaultInstance();
        }

        public Builder setConfigDiscovery(ExtensionConfigSource extensionConfigSource) {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3 = this.configDiscoveryBuilder_;
            if (singleFieldBuilderV3 == null) {
                extensionConfigSource.getClass();
                this.configType_ = extensionConfigSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(extensionConfigSource);
            }
            this.configTypeCase_ = 5;
            return this;
        }

        public Builder setConfigDiscovery(ExtensionConfigSource.Builder builder) {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3 = this.configDiscoveryBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configType_ = builder.m22035build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m22035build());
            }
            this.configTypeCase_ = 5;
            return this;
        }

        public Builder mergeConfigDiscovery(ExtensionConfigSource extensionConfigSource) {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3 = this.configDiscoveryBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 5 || this.configType_ == ExtensionConfigSource.getDefaultInstance()) {
                    this.configType_ = extensionConfigSource;
                } else {
                    this.configType_ = ExtensionConfigSource.newBuilder((ExtensionConfigSource) this.configType_).mergeFrom(extensionConfigSource).m22037buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(extensionConfigSource);
                }
                this.configDiscoveryBuilder_.setMessage(extensionConfigSource);
            }
            this.configTypeCase_ = 5;
            return this;
        }

        public Builder clearConfigDiscovery() {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3 = this.configDiscoveryBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 5) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 5) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        public ExtensionConfigSource.Builder getConfigDiscoveryBuilder() {
            return getConfigDiscoveryFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilterOrBuilder
        public ExtensionConfigSourceOrBuilder getConfigDiscoveryOrBuilder() {
            SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.configDiscoveryBuilder_) != null) {
                return (ExtensionConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (ExtensionConfigSource) this.configType_;
            }
            return ExtensionConfigSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ExtensionConfigSource, ExtensionConfigSource.Builder, ExtensionConfigSourceOrBuilder> getConfigDiscoveryFieldBuilder() {
            if (this.configDiscoveryBuilder_ == null) {
                if (this.configTypeCase_ != 5) {
                    this.configType_ = ExtensionConfigSource.getDefaultInstance();
                }
                this.configDiscoveryBuilder_ = new SingleFieldBuilderV3<>((ExtensionConfigSource) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 5;
            onChanged();
            return this.configDiscoveryBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31089setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31083mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$HttpFilter$ConfigTypeCase;

        static {
            int[] iArr = new int[ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$HttpFilter$ConfigTypeCase = iArr;
            try {
                iArr[ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$HttpFilter$ConfigTypeCase[ConfigTypeCase.CONFIG_DISCOVERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$HttpFilter$ConfigTypeCase[ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
