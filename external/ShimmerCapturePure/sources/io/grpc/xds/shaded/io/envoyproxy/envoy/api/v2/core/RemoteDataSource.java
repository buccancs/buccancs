package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpUri;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RetryPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class RemoteDataSource extends GeneratedMessageV3 implements RemoteDataSourceOrBuilder {
    public static final int HTTP_URI_FIELD_NUMBER = 1;
    public static final int RETRY_POLICY_FIELD_NUMBER = 3;
    public static final int SHA256_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final RemoteDataSource DEFAULT_INSTANCE = new RemoteDataSource();
    private static final Parser<RemoteDataSource> PARSER = new AbstractParser<RemoteDataSource>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RemoteDataSource m16475parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RemoteDataSource(codedInputStream, extensionRegistryLite);
        }
    };
    private HttpUri httpUri_;
    private byte memoizedIsInitialized;
    private RetryPolicy retryPolicy_;
    private volatile Object sha256_;

    private RemoteDataSource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RemoteDataSource() {
        this.memoizedIsInitialized = (byte) -1;
        this.sha256_ = "";
    }

    private RemoteDataSource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                HttpUri httpUri = this.httpUri_;
                                HttpUri.Builder builderM16196toBuilder = httpUri != null ? httpUri.m16196toBuilder() : null;
                                HttpUri httpUri2 = (HttpUri) codedInputStream.readMessage(HttpUri.parser(), extensionRegistryLite);
                                this.httpUri_ = httpUri2;
                                if (builderM16196toBuilder != null) {
                                    builderM16196toBuilder.mergeFrom(httpUri2);
                                    this.httpUri_ = builderM16196toBuilder.m16203buildPartial();
                                }
                            } else if (tag == 18) {
                                this.sha256_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                RetryPolicy retryPolicy = this.retryPolicy_;
                                RetryPolicy.Builder builderM16520toBuilder = retryPolicy != null ? retryPolicy.m16520toBuilder() : null;
                                RetryPolicy retryPolicy2 = (RetryPolicy) codedInputStream.readMessage(RetryPolicy.parser(), extensionRegistryLite);
                                this.retryPolicy_ = retryPolicy2;
                                if (builderM16520toBuilder != null) {
                                    builderM16520toBuilder.mergeFrom(retryPolicy2);
                                    this.retryPolicy_ = builderM16520toBuilder.m16527buildPartial();
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

    public static RemoteDataSource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RemoteDataSource> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_api_v2_core_RemoteDataSource_descriptor;
    }

    public static RemoteDataSource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(byteBuffer);
    }

    public static RemoteDataSource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RemoteDataSource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(byteString);
    }

    public static RemoteDataSource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RemoteDataSource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(bArr);
    }

    public static RemoteDataSource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RemoteDataSource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RemoteDataSource parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RemoteDataSource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RemoteDataSource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RemoteDataSource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RemoteDataSource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RemoteDataSource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m16473toBuilder();
    }

    public static Builder newBuilder(RemoteDataSource remoteDataSource) {
        return DEFAULT_INSTANCE.m16473toBuilder().mergeFrom(remoteDataSource);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RemoteDataSource m16468getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RemoteDataSource> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public boolean hasHttpUri() {
        return this.httpUri_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public boolean hasRetryPolicy() {
        return this.retryPolicy_ != null;
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
        return new RemoteDataSource();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_api_v2_core_RemoteDataSource_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteDataSource.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public HttpUri getHttpUri() {
        HttpUri httpUri = this.httpUri_;
        return httpUri == null ? HttpUri.getDefaultInstance() : httpUri;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public HttpUriOrBuilder getHttpUriOrBuilder() {
        return getHttpUri();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public String getSha256() {
        Object obj = this.sha256_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sha256_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public ByteString getSha256Bytes() {
        Object obj = this.sha256_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sha256_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy = this.retryPolicy_;
        return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
    public RetryPolicyOrBuilder getRetryPolicyOrBuilder() {
        return getRetryPolicy();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.httpUri_ != null) {
            codedOutputStream.writeMessage(1, getHttpUri());
        }
        if (!getSha256Bytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.sha256_);
        }
        if (this.retryPolicy_ != null) {
            codedOutputStream.writeMessage(3, getRetryPolicy());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.httpUri_ != null ? CodedOutputStream.computeMessageSize(1, getHttpUri()) : 0;
        if (!getSha256Bytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.sha256_);
        }
        if (this.retryPolicy_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getRetryPolicy());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RemoteDataSource)) {
            return super.equals(obj);
        }
        RemoteDataSource remoteDataSource = (RemoteDataSource) obj;
        if (hasHttpUri() != remoteDataSource.hasHttpUri()) {
            return false;
        }
        if ((!hasHttpUri() || getHttpUri().equals(remoteDataSource.getHttpUri())) && getSha256().equals(remoteDataSource.getSha256()) && hasRetryPolicy() == remoteDataSource.hasRetryPolicy()) {
            return (!hasRetryPolicy() || getRetryPolicy().equals(remoteDataSource.getRetryPolicy())) && this.unknownFields.equals(remoteDataSource.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasHttpUri()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getHttpUri().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 2) * 53) + getSha256().hashCode();
        if (hasRetryPolicy()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getRetryPolicy().hashCode();
        }
        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode3;
        return iHashCode3;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16470newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16473toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteDataSourceOrBuilder {
        private SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> httpUriBuilder_;
        private HttpUri httpUri_;
        private SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> retryPolicyBuilder_;
        private RetryPolicy retryPolicy_;
        private Object sha256_;

        private Builder() {
            this.sha256_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sha256_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_api_v2_core_RemoteDataSource_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public boolean hasHttpUri() {
            return (this.httpUriBuilder_ == null && this.httpUri_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public boolean hasRetryPolicy() {
            return (this.retryPolicyBuilder_ == null && this.retryPolicy_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_api_v2_core_RemoteDataSource_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteDataSource.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RemoteDataSource.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16484clear() {
            super.clear();
            if (this.httpUriBuilder_ == null) {
                this.httpUri_ = null;
            } else {
                this.httpUri_ = null;
                this.httpUriBuilder_ = null;
            }
            this.sha256_ = "";
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicy_ = null;
            } else {
                this.retryPolicy_ = null;
                this.retryPolicyBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_api_v2_core_RemoteDataSource_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RemoteDataSource m16497getDefaultInstanceForType() {
            return RemoteDataSource.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RemoteDataSource m16478build() throws UninitializedMessageException {
            RemoteDataSource remoteDataSourceM16480buildPartial = m16480buildPartial();
            if (remoteDataSourceM16480buildPartial.isInitialized()) {
                return remoteDataSourceM16480buildPartial;
            }
            throw newUninitializedMessageException(remoteDataSourceM16480buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RemoteDataSource m16480buildPartial() {
            RemoteDataSource remoteDataSource = new RemoteDataSource(this);
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 == null) {
                remoteDataSource.httpUri_ = this.httpUri_;
            } else {
                remoteDataSource.httpUri_ = singleFieldBuilderV3.build();
            }
            remoteDataSource.sha256_ = this.sha256_;
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV32 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV32 == null) {
                remoteDataSource.retryPolicy_ = this.retryPolicy_;
            } else {
                remoteDataSource.retryPolicy_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return remoteDataSource;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16496clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16508setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16486clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16489clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16510setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16476addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16501mergeFrom(Message message) {
            if (message instanceof RemoteDataSource) {
                return mergeFrom((RemoteDataSource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RemoteDataSource remoteDataSource) {
            if (remoteDataSource == RemoteDataSource.getDefaultInstance()) {
                return this;
            }
            if (remoteDataSource.hasHttpUri()) {
                mergeHttpUri(remoteDataSource.getHttpUri());
            }
            if (!remoteDataSource.getSha256().isEmpty()) {
                this.sha256_ = remoteDataSource.sha256_;
                onChanged();
            }
            if (remoteDataSource.hasRetryPolicy()) {
                mergeRetryPolicy(remoteDataSource.getRetryPolicy());
            }
            m16506mergeUnknownFields(remoteDataSource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource.Builder m16502mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource.Builder.m16502mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSource$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public HttpUri getHttpUri() {
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            HttpUri httpUri = this.httpUri_;
            return httpUri == null ? HttpUri.getDefaultInstance() : httpUri;
        }

        public Builder setHttpUri(HttpUri httpUri) {
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 == null) {
                httpUri.getClass();
                this.httpUri_ = httpUri;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(httpUri);
            }
            return this;
        }

        public Builder setHttpUri(HttpUri.Builder builder) {
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.httpUri_ = builder.m16201build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16201build());
            }
            return this;
        }

        public Builder mergeHttpUri(HttpUri httpUri) {
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 == null) {
                HttpUri httpUri2 = this.httpUri_;
                if (httpUri2 != null) {
                    this.httpUri_ = HttpUri.newBuilder(httpUri2).mergeFrom(httpUri).m16203buildPartial();
                } else {
                    this.httpUri_ = httpUri;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(httpUri);
            }
            return this;
        }

        public Builder clearHttpUri() {
            if (this.httpUriBuilder_ == null) {
                this.httpUri_ = null;
                onChanged();
            } else {
                this.httpUri_ = null;
                this.httpUriBuilder_ = null;
            }
            return this;
        }

        public HttpUri.Builder getHttpUriBuilder() {
            onChanged();
            return getHttpUriFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public HttpUriOrBuilder getHttpUriOrBuilder() {
            SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> singleFieldBuilderV3 = this.httpUriBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HttpUriOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            HttpUri httpUri = this.httpUri_;
            return httpUri == null ? HttpUri.getDefaultInstance() : httpUri;
        }

        private SingleFieldBuilderV3<HttpUri, HttpUri.Builder, HttpUriOrBuilder> getHttpUriFieldBuilder() {
            if (this.httpUriBuilder_ == null) {
                this.httpUriBuilder_ = new SingleFieldBuilderV3<>(getHttpUri(), getParentForChildren(), isClean());
                this.httpUri_ = null;
            }
            return this.httpUriBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public String getSha256() {
            Object obj = this.sha256_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sha256_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSha256(String str) {
            str.getClass();
            this.sha256_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public ByteString getSha256Bytes() {
            Object obj = this.sha256_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sha256_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSha256Bytes(ByteString byteString) {
            byteString.getClass();
            RemoteDataSource.checkByteStringIsUtf8(byteString);
            this.sha256_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSha256() {
            this.sha256_ = RemoteDataSource.getDefaultInstance().getSha256();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public RetryPolicy getRetryPolicy() {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RetryPolicy retryPolicy = this.retryPolicy_;
            return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
        }

        public Builder setRetryPolicy(RetryPolicy retryPolicy) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryPolicy.getClass();
                this.retryPolicy_ = retryPolicy;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(retryPolicy);
            }
            return this;
        }

        public Builder setRetryPolicy(RetryPolicy.Builder builder) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryPolicy_ = builder.m16525build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16525build());
            }
            return this;
        }

        public Builder mergeRetryPolicy(RetryPolicy retryPolicy) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                RetryPolicy retryPolicy2 = this.retryPolicy_;
                if (retryPolicy2 != null) {
                    this.retryPolicy_ = RetryPolicy.newBuilder(retryPolicy2).mergeFrom(retryPolicy).m16527buildPartial();
                } else {
                    this.retryPolicy_ = retryPolicy;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(retryPolicy);
            }
            return this;
        }

        public Builder clearRetryPolicy() {
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicy_ = null;
                onChanged();
            } else {
                this.retryPolicy_ = null;
                this.retryPolicyBuilder_ = null;
            }
            return this;
        }

        public RetryPolicy.Builder getRetryPolicyBuilder() {
            onChanged();
            return getRetryPolicyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RemoteDataSourceOrBuilder
        public RetryPolicyOrBuilder getRetryPolicyOrBuilder() {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RetryPolicyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RetryPolicy retryPolicy = this.retryPolicy_;
            return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
        }

        private SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> getRetryPolicyFieldBuilder() {
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicyBuilder_ = new SingleFieldBuilderV3<>(getRetryPolicy(), getParentForChildren(), isClean());
                this.retryPolicy_ = null;
            }
            return this.retryPolicyBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16512setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16506mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
