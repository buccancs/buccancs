package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Tracing extends GeneratedMessageV3 implements TracingOrBuilder {
    public static final int HTTP_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Tracing DEFAULT_INSTANCE = new Tracing();
    private static final Parser<Tracing> PARSER = new AbstractParser<Tracing>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Tracing m30679parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Tracing(codedInputStream, extensionRegistryLite);
        }
    };
    private Http http_;
    private byte memoizedIsInitialized;

    private Tracing(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Tracing() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Tracing(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Http http = this.http_;
                                Http.Builder builderM30723toBuilder = http != null ? http.m30723toBuilder() : null;
                                Http http2 = (Http) codedInputStream.readMessage(Http.parser(), extensionRegistryLite);
                                this.http_ = http2;
                                if (builderM30723toBuilder != null) {
                                    builderM30723toBuilder.mergeFrom(http2);
                                    this.http_ = builderM30723toBuilder.m30730buildPartial();
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

    public static Tracing getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Tracing> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_descriptor;
    }

    public static Tracing parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteBuffer);
    }

    public static Tracing parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Tracing parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteString);
    }

    public static Tracing parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Tracing parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(bArr);
    }

    public static Tracing parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Tracing parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Tracing parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Tracing parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Tracing parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Tracing parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Tracing parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m30677toBuilder();
    }

    public static Builder newBuilder(Tracing tracing) {
        return DEFAULT_INSTANCE.m30677toBuilder().mergeFrom(tracing);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Tracing m30672getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Tracing> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
    public boolean hasHttp() {
        return this.http_ != null;
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
        return new Tracing();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_fieldAccessorTable.ensureFieldAccessorsInitialized(Tracing.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.getDefaultInstance() : http;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
    public HttpOrBuilder getHttpOrBuilder() {
        return getHttp();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.http_ != null) {
            codedOutputStream.writeMessage(1, getHttp());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.http_ != null ? CodedOutputStream.computeMessageSize(1, getHttp()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tracing)) {
            return super.equals(obj);
        }
        Tracing tracing = (Tracing) obj;
        if (hasHttp() != tracing.hasHttp()) {
            return false;
        }
        return (!hasHttp() || getHttp().equals(tracing.getHttp())) && this.unknownFields.equals(tracing.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasHttp()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getHttp().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30674newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30677toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface HttpOrBuilder extends MessageOrBuilder {
        Http.ConfigTypeCase getConfigTypeCase();

        String getName();

        ByteString getNameBytes();

        Any getTypedConfig();

        AnyOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public static final class Http extends GeneratedMessageV3 implements HttpOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final Http DEFAULT_INSTANCE = new Http();
        private static final Parser<Http> PARSER = new AbstractParser<Http>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Http m30725parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Http(codedInputStream, extensionRegistryLite);
            }
        };
        private int configTypeCase_;
        private Object configType_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private Http(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Http() {
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private Http(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

        public static Http getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Http> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_Http_descriptor;
        }

        public static Http parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(byteBuffer);
        }

        public static Http parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Http parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(byteString);
        }

        public static Http parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Http parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(bArr);
        }

        public static Http parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Http) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Http parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Http parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Http parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Http parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Http parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Http parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m30723toBuilder();
        }

        public static Builder newBuilder(Http http) {
            return DEFAULT_INSTANCE.m30723toBuilder().mergeFrom(http);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Http m30718getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Http> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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
            return new Http();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_Http_fieldAccessorTable.ensureFieldAccessorsInitialized(Http.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
        public Any getTypedConfig() {
            if (this.configTypeCase_ == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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
            if (!(obj instanceof Http)) {
                return super.equals(obj);
            }
            Http http = (Http) obj;
            if (getName().equals(http.getName()) && getConfigTypeCase().equals(http.getConfigTypeCase())) {
                return (this.configTypeCase_ != 3 || getTypedConfig().equals(http.getTypedConfig())) && this.unknownFields.equals(http.unknownFields);
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
        public Builder m30720newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30723toBuilder() {
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpOrBuilder {
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
                return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_Http_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
            public boolean hasTypedConfig() {
                return this.configTypeCase_ == 3;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_Http_fieldAccessorTable.ensureFieldAccessorsInitialized(Http.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Http.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30734clear() {
                super.clear();
                this.name_ = "";
                this.configTypeCase_ = 0;
                this.configType_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_Http_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Http m30747getDefaultInstanceForType() {
                return Http.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Http m30728build() throws UninitializedMessageException {
                Http httpM30730buildPartial = m30730buildPartial();
                if (httpM30730buildPartial.isInitialized()) {
                    return httpM30730buildPartial;
                }
                throw newUninitializedMessageException(httpM30730buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Http m30730buildPartial() {
                Http http = new Http(this);
                http.name_ = this.name_;
                if (this.configTypeCase_ == 3) {
                    SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        http.configType_ = this.configType_;
                    } else {
                        http.configType_ = singleFieldBuilderV3.build();
                    }
                }
                http.configTypeCase_ = this.configTypeCase_;
                onBuilt();
                return http;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30746clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30758setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30736clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30739clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30760setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30726addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m30751mergeFrom(Message message) {
                if (message instanceof Http) {
                    return mergeFrom((Http) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Http http) {
                if (http == Http.getDefaultInstance()) {
                    return this;
                }
                if (!http.getName().isEmpty()) {
                    this.name_ = http.name_;
                    onChanged();
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$trace$v3$Tracing$Http$ConfigTypeCase[http.getConfigTypeCase().ordinal()] == 1) {
                    mergeTypedConfig(http.getTypedConfig());
                }
                m30756mergeUnknownFields(http.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http.Builder m30752mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing$Http r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing$Http r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Http.Builder.m30752mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing$Http$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
            public ConfigTypeCase getConfigTypeCase() {
                return ConfigTypeCase.forNumber(this.configTypeCase_);
            }

            public Builder clearConfigType() {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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
                Http.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = Http.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.HttpOrBuilder
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
            public final Builder m30762setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m30756mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$trace$v3$Tracing$Http$ConfigTypeCase;

        static {
            int[] iArr = new int[Http.ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$trace$v3$Tracing$Http$ConfigTypeCase = iArr;
            try {
                iArr[Http.ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$trace$v3$Tracing$Http$ConfigTypeCase[Http.ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TracingOrBuilder {
        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> httpBuilder_;
        private Http http_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
        public boolean hasHttp() {
            return (this.httpBuilder_ == null && this.http_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_fieldAccessorTable.ensureFieldAccessorsInitialized(Tracing.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Tracing.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30688clear() {
            super.clear();
            if (this.httpBuilder_ == null) {
                this.http_ = null;
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpTracerProto.internal_static_envoy_config_trace_v3_Tracing_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m30701getDefaultInstanceForType() {
            return Tracing.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m30682build() throws UninitializedMessageException {
            Tracing tracingM30684buildPartial = m30684buildPartial();
            if (tracingM30684buildPartial.isInitialized()) {
                return tracingM30684buildPartial;
            }
            throw newUninitializedMessageException(tracingM30684buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m30684buildPartial() {
            Tracing tracing = new Tracing(this);
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                tracing.http_ = this.http_;
            } else {
                tracing.http_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return tracing;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30700clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30712setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30690clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30693clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30714setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30680addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30705mergeFrom(Message message) {
            if (message instanceof Tracing) {
                return mergeFrom((Tracing) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Tracing tracing) {
            if (tracing == Tracing.getDefaultInstance()) {
                return this;
            }
            if (tracing.hasHttp()) {
                mergeHttp(tracing.getHttp());
            }
            m30710mergeUnknownFields(tracing.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Builder m30706mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing.Builder.m30706mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
        public Http getHttp() {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Http http = this.http_;
            return http == null ? Http.getDefaultInstance() : http;
        }

        public Builder setHttp(Http http) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                http.getClass();
                this.http_ = http;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(http);
            }
            return this;
        }

        public Builder setHttp(Http.Builder builder) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.http_ = builder.m30728build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m30728build());
            }
            return this;
        }

        public Builder mergeHttp(Http http) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                Http http2 = this.http_;
                if (http2 != null) {
                    this.http_ = Http.newBuilder(http2).mergeFrom(http).m30730buildPartial();
                } else {
                    this.http_ = http;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(http);
            }
            return this;
        }

        public Builder clearHttp() {
            if (this.httpBuilder_ == null) {
                this.http_ = null;
                onChanged();
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            return this;
        }

        public Http.Builder getHttpBuilder() {
            onChanged();
            return getHttpFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.TracingOrBuilder
        public HttpOrBuilder getHttpOrBuilder() {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HttpOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Http http = this.http_;
            return http == null ? Http.getDefaultInstance() : http;
        }

        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> getHttpFieldBuilder() {
            if (this.httpBuilder_ == null) {
                this.httpBuilder_ = new SingleFieldBuilderV3<>(getHttp(), getParentForChildren(), isClean());
                this.http_ = null;
            }
            return this.httpBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30716setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30710mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
