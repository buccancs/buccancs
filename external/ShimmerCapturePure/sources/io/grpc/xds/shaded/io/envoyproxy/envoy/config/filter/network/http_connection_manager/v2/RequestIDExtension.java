package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

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
public final class RequestIDExtension extends GeneratedMessageV3 implements RequestIDExtensionOrBuilder {
    public static final int TYPED_CONFIG_FIELD_NUMBER = 1;
    private static final RequestIDExtension DEFAULT_INSTANCE = new RequestIDExtension();
    private static final Parser<RequestIDExtension> PARSER = new AbstractParser<RequestIDExtension>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RequestIDExtension m26423parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RequestIDExtension(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Any typedConfig_;

    private RequestIDExtension(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RequestIDExtension() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private RequestIDExtension(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static RequestIDExtension getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestIDExtension> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_RequestIDExtension_descriptor;
    }

    public static RequestIDExtension parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(byteBuffer);
    }

    public static RequestIDExtension parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RequestIDExtension parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(byteString);
    }

    public static RequestIDExtension parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RequestIDExtension parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(bArr);
    }

    public static RequestIDExtension parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestIDExtension) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RequestIDExtension parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RequestIDExtension parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestIDExtension parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RequestIDExtension parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestIDExtension parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RequestIDExtension parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m26421toBuilder();
    }

    public static Builder newBuilder(RequestIDExtension requestIDExtension) {
        return DEFAULT_INSTANCE.m26421toBuilder().mergeFrom(requestIDExtension);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RequestIDExtension m26416getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RequestIDExtension> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
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
        return new RequestIDExtension();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_RequestIDExtension_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestIDExtension.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
    public Any getTypedConfig() {
        Any any = this.typedConfig_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        return getTypedConfig();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.typedConfig_ != null) {
            codedOutputStream.writeMessage(1, getTypedConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.typedConfig_ != null ? CodedOutputStream.computeMessageSize(1, getTypedConfig()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestIDExtension)) {
            return super.equals(obj);
        }
        RequestIDExtension requestIDExtension = (RequestIDExtension) obj;
        if (hasTypedConfig() != requestIDExtension.hasTypedConfig()) {
            return false;
        }
        return (!hasTypedConfig() || getTypedConfig().equals(requestIDExtension.getTypedConfig())) && this.unknownFields.equals(requestIDExtension.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasTypedConfig()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getTypedConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m26418newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m26421toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RequestIDExtensionOrBuilder {
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;
        private Any typedConfig_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_RequestIDExtension_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
        public boolean hasTypedConfig() {
            return (this.typedConfigBuilder_ == null && this.typedConfig_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_RequestIDExtension_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestIDExtension.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RequestIDExtension.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26432clear() {
            super.clear();
            if (this.typedConfigBuilder_ == null) {
                this.typedConfig_ = null;
            } else {
                this.typedConfig_ = null;
                this.typedConfigBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_config_filter_network_http_connection_manager_v2_RequestIDExtension_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestIDExtension m26445getDefaultInstanceForType() {
            return RequestIDExtension.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestIDExtension m26426build() throws UninitializedMessageException {
            RequestIDExtension requestIDExtensionM26428buildPartial = m26428buildPartial();
            if (requestIDExtensionM26428buildPartial.isInitialized()) {
                return requestIDExtensionM26428buildPartial;
            }
            throw newUninitializedMessageException(requestIDExtensionM26428buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestIDExtension m26428buildPartial() {
            RequestIDExtension requestIDExtension = new RequestIDExtension(this);
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                requestIDExtension.typedConfig_ = this.typedConfig_;
            } else {
                requestIDExtension.typedConfig_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return requestIDExtension;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26444clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26456setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26434clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26437clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26458setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26424addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26449mergeFrom(Message message) {
            if (message instanceof RequestIDExtension) {
                return mergeFrom((RequestIDExtension) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RequestIDExtension requestIDExtension) {
            if (requestIDExtension == RequestIDExtension.getDefaultInstance()) {
                return this;
            }
            if (requestIDExtension.hasTypedConfig()) {
                mergeTypedConfig(requestIDExtension.getTypedConfig());
            }
            m26454mergeUnknownFields(requestIDExtension.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension.Builder m26450mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension.Builder.m26450mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtension$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionOrBuilder
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
        public final Builder m26460setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m26454mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
