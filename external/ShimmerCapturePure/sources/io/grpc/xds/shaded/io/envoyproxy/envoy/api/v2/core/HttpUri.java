package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
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

/* loaded from: classes3.dex */
public final class HttpUri extends GeneratedMessageV3 implements HttpUriOrBuilder {
    public static final int CLUSTER_FIELD_NUMBER = 2;
    public static final int TIMEOUT_FIELD_NUMBER = 3;
    public static final int URI_FIELD_NUMBER = 1;
    private static final HttpUri DEFAULT_INSTANCE = new HttpUri();
    private static final Parser<HttpUri> PARSER = new AbstractParser<HttpUri>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HttpUri m16198parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpUri(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private int httpUpstreamTypeCase_;
    private Object httpUpstreamType_;
    private byte memoizedIsInitialized;
    private Duration timeout_;
    private volatile Object uri_;

    private HttpUri(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.httpUpstreamTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpUri() {
        this.httpUpstreamTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.uri_ = "";
    }

    private HttpUri(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.uri_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.httpUpstreamTypeCase_ = 2;
                                this.httpUpstreamType_ = stringRequireUtf8;
                            } else if (tag == 26) {
                                Duration duration = this.timeout_;
                                Duration.Builder builder = duration != null ? duration.toBuilder() : null;
                                Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                this.timeout_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.timeout_ = builder.buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static HttpUri getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpUri> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpUriProto.internal_static_envoy_api_v2_core_HttpUri_descriptor;
    }

    public static HttpUri parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(byteBuffer);
    }

    public static HttpUri parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpUri parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(byteString);
    }

    public static HttpUri parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpUri parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(bArr);
    }

    public static HttpUri parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpUri) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpUri parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpUri parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpUri parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpUri parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpUri parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpUri parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m16196toBuilder();
    }

    public static Builder newBuilder(HttpUri httpUri) {
        return DEFAULT_INSTANCE.m16196toBuilder().mergeFrom(httpUri);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HttpUri m16191getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HttpUri> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public boolean hasTimeout() {
        return this.timeout_ != null;
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
        return new HttpUri();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpUriProto.internal_static_envoy_api_v2_core_HttpUri_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpUri.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public HttpUpstreamTypeCase getHttpUpstreamTypeCase() {
        return HttpUpstreamTypeCase.forNumber(this.httpUpstreamTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public String getUri() {
        Object obj = this.uri_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.uri_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public ByteString getUriBytes() {
        Object obj = this.uri_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.uri_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public String getCluster() {
        String str = this.httpUpstreamTypeCase_ == 2 ? this.httpUpstreamType_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.httpUpstreamTypeCase_ == 2) {
            this.httpUpstreamType_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public ByteString getClusterBytes() {
        String str = this.httpUpstreamTypeCase_ == 2 ? this.httpUpstreamType_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.httpUpstreamTypeCase_ == 2) {
                this.httpUpstreamType_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public Duration getTimeout() {
        Duration duration = this.timeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
    public DurationOrBuilder getTimeoutOrBuilder() {
        return getTimeout();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.uri_);
        }
        if (this.httpUpstreamTypeCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.httpUpstreamType_);
        }
        if (this.timeout_ != null) {
            codedOutputStream.writeMessage(3, getTimeout());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getUriBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.uri_) : 0;
        if (this.httpUpstreamTypeCase_ == 2) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.httpUpstreamType_);
        }
        if (this.timeout_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getTimeout());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpUri)) {
            return super.equals(obj);
        }
        HttpUri httpUri = (HttpUri) obj;
        if (!getUri().equals(httpUri.getUri()) || hasTimeout() != httpUri.hasTimeout()) {
            return false;
        }
        if ((!hasTimeout() || getTimeout().equals(httpUri.getTimeout())) && getHttpUpstreamTypeCase().equals(httpUri.getHttpUpstreamTypeCase())) {
            return (this.httpUpstreamTypeCase_ != 2 || getCluster().equals(httpUri.getCluster())) && this.unknownFields.equals(httpUri.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getUri().hashCode();
        if (hasTimeout()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getTimeout().hashCode();
        }
        if (this.httpUpstreamTypeCase_ == 2) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getCluster().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16193newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16196toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum HttpUpstreamTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CLUSTER(2),
        HTTPUPSTREAMTYPE_NOT_SET(0);

        private final int value;

        HttpUpstreamTypeCase(int i) {
            this.value = i;
        }

        public static HttpUpstreamTypeCase forNumber(int i) {
            if (i == 0) {
                return HTTPUPSTREAMTYPE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return CLUSTER;
        }

        @Deprecated
        public static HttpUpstreamTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpUriOrBuilder {
        private int httpUpstreamTypeCase_;
        private Object httpUpstreamType_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> timeoutBuilder_;
        private Duration timeout_;
        private Object uri_;

        private Builder() {
            this.httpUpstreamTypeCase_ = 0;
            this.uri_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.httpUpstreamTypeCase_ = 0;
            this.uri_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpUriProto.internal_static_envoy_api_v2_core_HttpUri_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public boolean hasTimeout() {
            return (this.timeoutBuilder_ == null && this.timeout_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpUriProto.internal_static_envoy_api_v2_core_HttpUri_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpUri.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HttpUri.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16207clear() {
            super.clear();
            this.uri_ = "";
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            this.httpUpstreamTypeCase_ = 0;
            this.httpUpstreamType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpUriProto.internal_static_envoy_api_v2_core_HttpUri_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpUri m16220getDefaultInstanceForType() {
            return HttpUri.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpUri m16201build() throws UninitializedMessageException {
            HttpUri httpUriM16203buildPartial = m16203buildPartial();
            if (httpUriM16203buildPartial.isInitialized()) {
                return httpUriM16203buildPartial;
            }
            throw newUninitializedMessageException(httpUriM16203buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpUri m16203buildPartial() {
            HttpUri httpUri = new HttpUri(this);
            httpUri.uri_ = this.uri_;
            if (this.httpUpstreamTypeCase_ == 2) {
                httpUri.httpUpstreamType_ = this.httpUpstreamType_;
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                httpUri.timeout_ = this.timeout_;
            } else {
                httpUri.timeout_ = singleFieldBuilderV3.build();
            }
            httpUri.httpUpstreamTypeCase_ = this.httpUpstreamTypeCase_;
            onBuilt();
            return httpUri;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16219clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16231setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16209clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16212clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16233setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16199addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16224mergeFrom(Message message) {
            if (message instanceof HttpUri) {
                return mergeFrom((HttpUri) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpUri httpUri) {
            if (httpUri == HttpUri.getDefaultInstance()) {
                return this;
            }
            if (!httpUri.getUri().isEmpty()) {
                this.uri_ = httpUri.uri_;
                onChanged();
            }
            if (httpUri.hasTimeout()) {
                mergeTimeout(httpUri.getTimeout());
            }
            if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$HttpUri$HttpUpstreamTypeCase[httpUri.getHttpUpstreamTypeCase().ordinal()] == 1) {
                this.httpUpstreamTypeCase_ = 2;
                this.httpUpstreamType_ = httpUri.httpUpstreamType_;
                onChanged();
            }
            m16229mergeUnknownFields(httpUri.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri.Builder m16225mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri.Builder.m16225mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public HttpUpstreamTypeCase getHttpUpstreamTypeCase() {
            return HttpUpstreamTypeCase.forNumber(this.httpUpstreamTypeCase_);
        }

        public Builder clearHttpUpstreamType() {
            this.httpUpstreamTypeCase_ = 0;
            this.httpUpstreamType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public String getUri() {
            Object obj = this.uri_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uri_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setUri(String str) {
            str.getClass();
            this.uri_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public ByteString getUriBytes() {
            Object obj = this.uri_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.uri_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setUriBytes(ByteString byteString) {
            byteString.getClass();
            HttpUri.checkByteStringIsUtf8(byteString);
            this.uri_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearUri() {
            this.uri_ = HttpUri.getDefaultInstance().getUri();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public String getCluster() {
            String str = this.httpUpstreamTypeCase_ == 2 ? this.httpUpstreamType_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.httpUpstreamTypeCase_ == 2) {
                    this.httpUpstreamType_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setCluster(String str) {
            str.getClass();
            this.httpUpstreamTypeCase_ = 2;
            this.httpUpstreamType_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public ByteString getClusterBytes() {
            String str = this.httpUpstreamTypeCase_ == 2 ? this.httpUpstreamType_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.httpUpstreamTypeCase_ == 2) {
                    this.httpUpstreamType_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setClusterBytes(ByteString byteString) {
            byteString.getClass();
            HttpUri.checkByteStringIsUtf8(byteString);
            this.httpUpstreamTypeCase_ = 2;
            this.httpUpstreamType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCluster() {
            if (this.httpUpstreamTypeCase_ == 2) {
                this.httpUpstreamTypeCase_ = 0;
                this.httpUpstreamType_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public Duration getTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.timeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.timeout_;
                if (duration2 != null) {
                    this.timeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.timeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearTimeout() {
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
                onChanged();
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getTimeoutBuilder() {
            onChanged();
            return getTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUriOrBuilder
        public DurationOrBuilder getTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getTimeoutFieldBuilder() {
            if (this.timeoutBuilder_ == null) {
                this.timeoutBuilder_ = new SingleFieldBuilderV3<>(getTimeout(), getParentForChildren(), isClean());
                this.timeout_ = null;
            }
            return this.timeoutBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16235setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16229mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$HttpUri$HttpUpstreamTypeCase;

        static {
            int[] iArr = new int[HttpUpstreamTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$HttpUri$HttpUpstreamTypeCase = iArr;
            try {
                iArr[HttpUpstreamTypeCase.CLUSTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$HttpUri$HttpUpstreamTypeCase[HttpUpstreamTypeCase.HTTPUPSTREAMTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
