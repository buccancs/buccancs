package com.google.logging.type;

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

/* loaded from: classes.dex */
public final class HttpRequest extends GeneratedMessageV3 implements HttpRequestOrBuilder {
    public static final int CACHE_FILL_BYTES_FIELD_NUMBER = 12;
    public static final int CACHE_HIT_FIELD_NUMBER = 9;
    public static final int CACHE_LOOKUP_FIELD_NUMBER = 11;
    public static final int CACHE_VALIDATED_WITH_ORIGIN_SERVER_FIELD_NUMBER = 10;
    public static final int LATENCY_FIELD_NUMBER = 14;
    public static final int PROTOCOL_FIELD_NUMBER = 15;
    public static final int REFERER_FIELD_NUMBER = 8;
    public static final int REMOTE_IP_FIELD_NUMBER = 7;
    public static final int REQUEST_METHOD_FIELD_NUMBER = 1;
    public static final int REQUEST_SIZE_FIELD_NUMBER = 3;
    public static final int REQUEST_URL_FIELD_NUMBER = 2;
    public static final int RESPONSE_SIZE_FIELD_NUMBER = 5;
    public static final int SERVER_IP_FIELD_NUMBER = 13;
    public static final int STATUS_FIELD_NUMBER = 4;
    public static final int USER_AGENT_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private static final HttpRequest DEFAULT_INSTANCE = new HttpRequest();
    private static final Parser<HttpRequest> PARSER = new AbstractParser<HttpRequest>() { // from class: com.google.logging.type.HttpRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HttpRequest m2984parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private long cacheFillBytes_;
    private boolean cacheHit_;
    private boolean cacheLookup_;
    private boolean cacheValidatedWithOriginServer_;
    private Duration latency_;
    private byte memoizedIsInitialized;
    private volatile Object protocol_;
    private volatile Object referer_;
    private volatile Object remoteIp_;
    private volatile Object requestMethod_;
    private long requestSize_;
    private volatile Object requestUrl_;
    private long responseSize_;
    private volatile Object serverIp_;
    private int status_;
    private volatile Object userAgent_;

    private HttpRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.requestMethod_ = "";
        this.requestUrl_ = "";
        this.userAgent_ = "";
        this.remoteIp_ = "";
        this.serverIp_ = "";
        this.referer_ = "";
        this.protocol_ = "";
    }

    private HttpRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 10:
                            this.requestMethod_ = codedInputStream.readStringRequireUtf8();
                        case 18:
                            this.requestUrl_ = codedInputStream.readStringRequireUtf8();
                        case 24:
                            this.requestSize_ = codedInputStream.readInt64();
                        case 32:
                            this.status_ = codedInputStream.readInt32();
                        case 40:
                            this.responseSize_ = codedInputStream.readInt64();
                        case 50:
                            this.userAgent_ = codedInputStream.readStringRequireUtf8();
                        case 58:
                            this.remoteIp_ = codedInputStream.readStringRequireUtf8();
                        case 66:
                            this.referer_ = codedInputStream.readStringRequireUtf8();
                        case 72:
                            this.cacheHit_ = codedInputStream.readBool();
                        case 80:
                            this.cacheValidatedWithOriginServer_ = codedInputStream.readBool();
                        case BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE:
                            this.cacheLookup_ = codedInputStream.readBool();
                        case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                            this.cacheFillBytes_ = codedInputStream.readInt64();
                        case 106:
                            this.serverIp_ = codedInputStream.readStringRequireUtf8();
                        case 114:
                            Duration duration = this.latency_;
                            Duration.Builder builder = duration != null ? duration.toBuilder() : null;
                            Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.latency_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.latency_ = builder.buildPartial();
                            }
                        case 122:
                            this.protocol_ = codedInputStream.readStringRequireUtf8();
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
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

    public static HttpRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteBuffer);
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteString);
    }

    public static HttpRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(bArr);
    }

    public static HttpRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2983toBuilder();
    }

    public static Builder newBuilder(HttpRequest httpRequest) {
        return DEFAULT_INSTANCE.m2983toBuilder().mergeFrom(httpRequest);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getCacheFillBytes() {
        return this.cacheFillBytes_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheHit() {
        return this.cacheHit_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheLookup() {
        return this.cacheLookup_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheValidatedWithOriginServer() {
        return this.cacheValidatedWithOriginServer_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HttpRequest m2978getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HttpRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getRequestSize() {
        return this.requestSize_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getResponseSize() {
        return this.responseSize_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public int getStatus() {
        return this.status_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean hasLatency() {
        return this.latency_ != null;
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpRequestProto.internal_static_google_logging_type_HttpRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRequest.class, Builder.class);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRequestMethod() {
        Object obj = this.requestMethod_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestMethod_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRequestMethodBytes() {
        Object obj = this.requestMethod_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestMethod_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRequestUrl() {
        Object obj = this.requestUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRequestUrlBytes() {
        Object obj = this.requestUrl_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestUrl_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getUserAgent() {
        Object obj = this.userAgent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.userAgent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getUserAgentBytes() {
        Object obj = this.userAgent_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.userAgent_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRemoteIp() {
        Object obj = this.remoteIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.remoteIp_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRemoteIpBytes() {
        Object obj = this.remoteIp_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.remoteIp_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getServerIp() {
        Object obj = this.serverIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.serverIp_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getServerIpBytes() {
        Object obj = this.serverIp_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serverIp_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getReferer() {
        Object obj = this.referer_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.referer_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRefererBytes() {
        Object obj = this.referer_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.referer_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public Duration getLatency() {
        Duration duration = this.latency_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public DurationOrBuilder getLatencyOrBuilder() {
        return getLatency();
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getProtocol() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.protocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getProtocolBytes() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.protocol_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRequestMethodBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requestMethod_);
        }
        if (!getRequestUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.requestUrl_);
        }
        long j = this.requestSize_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        int i = this.status_;
        if (i != 0) {
            codedOutputStream.writeInt32(4, i);
        }
        long j2 = this.responseSize_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(5, j2);
        }
        if (!getUserAgentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.userAgent_);
        }
        if (!getRemoteIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.remoteIp_);
        }
        if (!getRefererBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.referer_);
        }
        boolean z = this.cacheHit_;
        if (z) {
            codedOutputStream.writeBool(9, z);
        }
        boolean z2 = this.cacheValidatedWithOriginServer_;
        if (z2) {
            codedOutputStream.writeBool(10, z2);
        }
        boolean z3 = this.cacheLookup_;
        if (z3) {
            codedOutputStream.writeBool(11, z3);
        }
        long j3 = this.cacheFillBytes_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(12, j3);
        }
        if (!getServerIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 13, this.serverIp_);
        }
        if (this.latency_ != null) {
            codedOutputStream.writeMessage(14, getLatency());
        }
        if (!getProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 15, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRequestMethodBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.requestMethod_) : 0;
        if (!getRequestUrlBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.requestUrl_);
        }
        long j = this.requestSize_;
        if (j != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(3, j);
        }
        int i2 = this.status_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(4, i2);
        }
        long j2 = this.responseSize_;
        if (j2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(5, j2);
        }
        if (!getUserAgentBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(6, this.userAgent_);
        }
        if (!getRemoteIpBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.remoteIp_);
        }
        if (!getRefererBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(8, this.referer_);
        }
        boolean z = this.cacheHit_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(9, z);
        }
        boolean z2 = this.cacheValidatedWithOriginServer_;
        if (z2) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(10, z2);
        }
        boolean z3 = this.cacheLookup_;
        if (z3) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(11, z3);
        }
        long j3 = this.cacheFillBytes_;
        if (j3 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(12, j3);
        }
        if (!getServerIpBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(13, this.serverIp_);
        }
        if (this.latency_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(14, getLatency());
        }
        if (!getProtocolBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(15, this.protocol_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpRequest)) {
            return super.equals(obj);
        }
        HttpRequest httpRequest = (HttpRequest) obj;
        if (getRequestMethod().equals(httpRequest.getRequestMethod()) && getRequestUrl().equals(httpRequest.getRequestUrl()) && getRequestSize() == httpRequest.getRequestSize() && getStatus() == httpRequest.getStatus() && getResponseSize() == httpRequest.getResponseSize() && getUserAgent().equals(httpRequest.getUserAgent()) && getRemoteIp().equals(httpRequest.getRemoteIp()) && getServerIp().equals(httpRequest.getServerIp()) && getReferer().equals(httpRequest.getReferer()) && hasLatency() == httpRequest.hasLatency()) {
            return (!hasLatency() || getLatency().equals(httpRequest.getLatency())) && getCacheLookup() == httpRequest.getCacheLookup() && getCacheHit() == httpRequest.getCacheHit() && getCacheValidatedWithOriginServer() == httpRequest.getCacheValidatedWithOriginServer() && getCacheFillBytes() == httpRequest.getCacheFillBytes() && getProtocol().equals(httpRequest.getProtocol()) && this.unknownFields.equals(httpRequest.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRequestMethod().hashCode()) * 37) + 2) * 53) + getRequestUrl().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getRequestSize())) * 37) + 4) * 53) + getStatus()) * 37) + 5) * 53) + Internal.hashLong(getResponseSize())) * 37) + 6) * 53) + getUserAgent().hashCode()) * 37) + 7) * 53) + getRemoteIp().hashCode()) * 37) + 13) * 53) + getServerIp().hashCode()) * 37) + 8) * 53) + getReferer().hashCode();
        if (hasLatency()) {
            iHashCode = (((iHashCode * 37) + 14) * 53) + getLatency().hashCode();
        }
        int iHashBoolean = (((((((((((((((((((((iHashCode * 37) + 11) * 53) + Internal.hashBoolean(getCacheLookup())) * 37) + 9) * 53) + Internal.hashBoolean(getCacheHit())) * 37) + 10) * 53) + Internal.hashBoolean(getCacheValidatedWithOriginServer())) * 37) + 12) * 53) + Internal.hashLong(getCacheFillBytes())) * 37) + 15) * 53) + getProtocol().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashBoolean;
        return iHashBoolean;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2981newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2983toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2980newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpRequestOrBuilder {
        private long cacheFillBytes_;
        private boolean cacheHit_;
        private boolean cacheLookup_;
        private boolean cacheValidatedWithOriginServer_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> latencyBuilder_;
        private Duration latency_;
        private Object protocol_;
        private Object referer_;
        private Object remoteIp_;
        private Object requestMethod_;
        private long requestSize_;
        private Object requestUrl_;
        private long responseSize_;
        private Object serverIp_;
        private int status_;
        private Object userAgent_;

        private Builder() {
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getCacheFillBytes() {
            return this.cacheFillBytes_;
        }

        public Builder setCacheFillBytes(long j) {
            this.cacheFillBytes_ = j;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheHit() {
            return this.cacheHit_;
        }

        public Builder setCacheHit(boolean z) {
            this.cacheHit_ = z;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheLookup() {
            return this.cacheLookup_;
        }

        public Builder setCacheLookup(boolean z) {
            this.cacheLookup_ = z;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheValidatedWithOriginServer() {
            return this.cacheValidatedWithOriginServer_;
        }

        public Builder setCacheValidatedWithOriginServer(boolean z) {
            this.cacheValidatedWithOriginServer_ = z;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getRequestSize() {
            return this.requestSize_;
        }

        public Builder setRequestSize(long j) {
            this.requestSize_ = j;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getResponseSize() {
            return this.responseSize_;
        }

        public Builder setResponseSize(long j) {
            this.responseSize_ = j;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public int getStatus() {
            return this.status_;
        }

        public Builder setStatus(int i) {
            this.status_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean hasLatency() {
            return (this.latencyBuilder_ == null && this.latency_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HttpRequest.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2994clear() {
            super.clear();
            this.requestMethod_ = "";
            this.requestUrl_ = "";
            this.requestSize_ = 0L;
            this.status_ = 0;
            this.responseSize_ = 0L;
            this.userAgent_ = "";
            this.remoteIp_ = "";
            this.serverIp_ = "";
            this.referer_ = "";
            if (this.latencyBuilder_ == null) {
                this.latency_ = null;
            } else {
                this.latency_ = null;
                this.latencyBuilder_ = null;
            }
            this.cacheLookup_ = false;
            this.cacheHit_ = false;
            this.cacheValidatedWithOriginServer_ = false;
            this.cacheFillBytes_ = 0L;
            this.protocol_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpRequestProto.internal_static_google_logging_type_HttpRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRequest m3007getDefaultInstanceForType() {
            return HttpRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRequest m2988build() throws UninitializedMessageException {
            HttpRequest httpRequestM2990buildPartial = m2990buildPartial();
            if (httpRequestM2990buildPartial.isInitialized()) {
                return httpRequestM2990buildPartial;
            }
            throw newUninitializedMessageException(httpRequestM2990buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRequest m2990buildPartial() {
            HttpRequest httpRequest = new HttpRequest(this);
            httpRequest.requestMethod_ = this.requestMethod_;
            httpRequest.requestUrl_ = this.requestUrl_;
            httpRequest.requestSize_ = this.requestSize_;
            httpRequest.status_ = this.status_;
            httpRequest.responseSize_ = this.responseSize_;
            httpRequest.userAgent_ = this.userAgent_;
            httpRequest.remoteIp_ = this.remoteIp_;
            httpRequest.serverIp_ = this.serverIp_;
            httpRequest.referer_ = this.referer_;
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                httpRequest.latency_ = this.latency_;
            } else {
                httpRequest.latency_ = singleFieldBuilderV3.build();
            }
            httpRequest.cacheLookup_ = this.cacheLookup_;
            httpRequest.cacheHit_ = this.cacheHit_;
            httpRequest.cacheValidatedWithOriginServer_ = this.cacheValidatedWithOriginServer_;
            httpRequest.cacheFillBytes_ = this.cacheFillBytes_;
            httpRequest.protocol_ = this.protocol_;
            onBuilt();
            return httpRequest;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3005clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3018setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2996clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2999clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3020setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2986addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3012mergeFrom(Message message) {
            if (message instanceof HttpRequest) {
                return mergeFrom((HttpRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpRequest httpRequest) {
            if (httpRequest == HttpRequest.getDefaultInstance()) {
                return this;
            }
            if (!httpRequest.getRequestMethod().isEmpty()) {
                this.requestMethod_ = httpRequest.requestMethod_;
                onChanged();
            }
            if (!httpRequest.getRequestUrl().isEmpty()) {
                this.requestUrl_ = httpRequest.requestUrl_;
                onChanged();
            }
            if (httpRequest.getRequestSize() != 0) {
                setRequestSize(httpRequest.getRequestSize());
            }
            if (httpRequest.getStatus() != 0) {
                setStatus(httpRequest.getStatus());
            }
            if (httpRequest.getResponseSize() != 0) {
                setResponseSize(httpRequest.getResponseSize());
            }
            if (!httpRequest.getUserAgent().isEmpty()) {
                this.userAgent_ = httpRequest.userAgent_;
                onChanged();
            }
            if (!httpRequest.getRemoteIp().isEmpty()) {
                this.remoteIp_ = httpRequest.remoteIp_;
                onChanged();
            }
            if (!httpRequest.getServerIp().isEmpty()) {
                this.serverIp_ = httpRequest.serverIp_;
                onChanged();
            }
            if (!httpRequest.getReferer().isEmpty()) {
                this.referer_ = httpRequest.referer_;
                onChanged();
            }
            if (httpRequest.hasLatency()) {
                mergeLatency(httpRequest.getLatency());
            }
            if (httpRequest.getCacheLookup()) {
                setCacheLookup(httpRequest.getCacheLookup());
            }
            if (httpRequest.getCacheHit()) {
                setCacheHit(httpRequest.getCacheHit());
            }
            if (httpRequest.getCacheValidatedWithOriginServer()) {
                setCacheValidatedWithOriginServer(httpRequest.getCacheValidatedWithOriginServer());
            }
            if (httpRequest.getCacheFillBytes() != 0) {
                setCacheFillBytes(httpRequest.getCacheFillBytes());
            }
            if (!httpRequest.getProtocol().isEmpty()) {
                this.protocol_ = httpRequest.protocol_;
                onChanged();
            }
            m3016mergeUnknownFields(httpRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.logging.type.HttpRequest.Builder m3013mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.logging.type.HttpRequest.access$2000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.logging.type.HttpRequest r3 = (com.google.logging.type.HttpRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.logging.type.HttpRequest r4 = (com.google.logging.type.HttpRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.logging.type.HttpRequest.Builder.m3013mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.logging.type.HttpRequest$Builder");
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRequestMethod() {
            Object obj = this.requestMethod_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.requestMethod_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRequestMethod(String str) {
            str.getClass();
            this.requestMethod_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRequestMethodBytes() {
            Object obj = this.requestMethod_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.requestMethod_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRequestMethodBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.requestMethod_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRequestMethod() {
            this.requestMethod_ = HttpRequest.getDefaultInstance().getRequestMethod();
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRequestUrl() {
            Object obj = this.requestUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.requestUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRequestUrl(String str) {
            str.getClass();
            this.requestUrl_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRequestUrlBytes() {
            Object obj = this.requestUrl_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.requestUrl_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRequestUrlBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.requestUrl_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRequestUrl() {
            this.requestUrl_ = HttpRequest.getDefaultInstance().getRequestUrl();
            onChanged();
            return this;
        }

        public Builder clearRequestSize() {
            this.requestSize_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearStatus() {
            this.status_ = 0;
            onChanged();
            return this;
        }

        public Builder clearResponseSize() {
            this.responseSize_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getUserAgent() {
            Object obj = this.userAgent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.userAgent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setUserAgent(String str) {
            str.getClass();
            this.userAgent_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getUserAgentBytes() {
            Object obj = this.userAgent_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.userAgent_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setUserAgentBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.userAgent_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearUserAgent() {
            this.userAgent_ = HttpRequest.getDefaultInstance().getUserAgent();
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRemoteIp() {
            Object obj = this.remoteIp_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.remoteIp_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRemoteIp(String str) {
            str.getClass();
            this.remoteIp_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRemoteIpBytes() {
            Object obj = this.remoteIp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.remoteIp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRemoteIpBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.remoteIp_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRemoteIp() {
            this.remoteIp_ = HttpRequest.getDefaultInstance().getRemoteIp();
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getServerIp() {
            Object obj = this.serverIp_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.serverIp_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setServerIp(String str) {
            str.getClass();
            this.serverIp_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getServerIpBytes() {
            Object obj = this.serverIp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serverIp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setServerIpBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.serverIp_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearServerIp() {
            this.serverIp_ = HttpRequest.getDefaultInstance().getServerIp();
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getReferer() {
            Object obj = this.referer_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.referer_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setReferer(String str) {
            str.getClass();
            this.referer_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRefererBytes() {
            Object obj = this.referer_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.referer_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRefererBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.referer_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearReferer() {
            this.referer_ = HttpRequest.getDefaultInstance().getReferer();
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public Duration getLatency() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.latency_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setLatency(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.latency_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setLatency(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.latency_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLatency(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.latency_;
                if (duration2 != null) {
                    this.latency_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.latency_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearLatency() {
            if (this.latencyBuilder_ == null) {
                this.latency_ = null;
                onChanged();
            } else {
                this.latency_ = null;
                this.latencyBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getLatencyBuilder() {
            onChanged();
            return getLatencyFieldBuilder().getBuilder();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public DurationOrBuilder getLatencyOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.latencyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.latency_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getLatencyFieldBuilder() {
            if (this.latencyBuilder_ == null) {
                this.latencyBuilder_ = new SingleFieldBuilderV3<>(getLatency(), getParentForChildren(), isClean());
                this.latency_ = null;
            }
            return this.latencyBuilder_;
        }

        public Builder clearCacheLookup() {
            this.cacheLookup_ = false;
            onChanged();
            return this;
        }

        public Builder clearCacheHit() {
            this.cacheHit_ = false;
            onChanged();
            return this;
        }

        public Builder clearCacheValidatedWithOriginServer() {
            this.cacheValidatedWithOriginServer_ = false;
            onChanged();
            return this;
        }

        public Builder clearCacheFillBytes() {
            this.cacheFillBytes_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getProtocol() {
            Object obj = this.protocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.protocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setProtocol(String str) {
            str.getClass();
            this.protocol_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getProtocolBytes() {
            Object obj = this.protocol_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.protocol_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            byteString.getClass();
            HttpRequest.checkByteStringIsUtf8(byteString);
            this.protocol_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = HttpRequest.getDefaultInstance().getProtocol();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3022setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3016mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
