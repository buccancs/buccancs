package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.HeaderMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class RetryPolicy extends GeneratedMessageV3 implements RetryPolicyOrBuilder {
    public static final int HOST_SELECTION_RETRY_MAX_ATTEMPTS_FIELD_NUMBER = 6;
    public static final int NUM_RETRIES_FIELD_NUMBER = 2;
    public static final int PER_TRY_TIMEOUT_FIELD_NUMBER = 3;
    public static final int RETRIABLE_HEADERS_FIELD_NUMBER = 9;
    public static final int RETRIABLE_REQUEST_HEADERS_FIELD_NUMBER = 10;
    public static final int RETRIABLE_STATUS_CODES_FIELD_NUMBER = 7;
    public static final int RETRY_BACK_OFF_FIELD_NUMBER = 8;
    public static final int RETRY_HOST_PREDICATE_FIELD_NUMBER = 5;
    public static final int RETRY_ON_FIELD_NUMBER = 1;
    public static final int RETRY_PRIORITY_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final RetryPolicy DEFAULT_INSTANCE = new RetryPolicy();
    private static final Parser<RetryPolicy> PARSER = new AbstractParser<RetryPolicy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RetryPolicy m28729parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RetryPolicy(codedInputStream, extensionRegistryLite);
        }
    };
    private long hostSelectionRetryMaxAttempts_;
    private byte memoizedIsInitialized;
    private UInt32Value numRetries_;
    private Duration perTryTimeout_;
    private List<HeaderMatcher> retriableHeaders_;
    private List<HeaderMatcher> retriableRequestHeaders_;
    private int retriableStatusCodesMemoizedSerializedSize;
    private Internal.IntList retriableStatusCodes_;
    private RetryBackOff retryBackOff_;
    private List<RetryHostPredicate> retryHostPredicate_;
    private volatile Object retryOn_;
    private RetryPriority retryPriority_;

    private RetryPolicy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.retriableStatusCodesMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private RetryPolicy() {
        this.retriableStatusCodesMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.retryOn_ = "";
        this.retryHostPredicate_ = Collections.emptyList();
        this.retriableStatusCodes_ = emptyIntList();
        this.retriableHeaders_ = Collections.emptyList();
        this.retriableRequestHeaders_ = Collections.emptyList();
    }

    private RetryPolicy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 10:
                            this.retryOn_ = codedInputStream.readStringRequireUtf8();
                        case 18:
                            UInt32Value uInt32Value = this.numRetries_;
                            builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.numRetries_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.numRetries_ = builder.buildPartial();
                            }
                        case 26:
                            Duration duration = this.perTryTimeout_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.perTryTimeout_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.perTryTimeout_ = builder.buildPartial();
                            }
                        case 34:
                            RetryPriority retryPriority = this.retryPriority_;
                            builder = retryPriority != null ? retryPriority.m28865toBuilder() : null;
                            RetryPriority retryPriority2 = (RetryPriority) codedInputStream.readMessage(RetryPriority.parser(), extensionRegistryLite);
                            this.retryPriority_ = retryPriority2;
                            if (builder != null) {
                                builder.mergeFrom(retryPriority2);
                                this.retryPriority_ = builder.m28872buildPartial();
                            }
                        case 42:
                            if ((i & 1) == 0) {
                                this.retryHostPredicate_ = new ArrayList();
                                i |= 1;
                            }
                            this.retryHostPredicate_.add(codedInputStream.readMessage(RetryHostPredicate.parser(), extensionRegistryLite));
                        case 48:
                            this.hostSelectionRetryMaxAttempts_ = codedInputStream.readInt64();
                        case 56:
                            if ((i & 2) == 0) {
                                this.retriableStatusCodes_ = newIntList();
                                i |= 2;
                            }
                            this.retriableStatusCodes_.addInt(codedInputStream.readUInt32());
                        case 58:
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 2) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.retriableStatusCodes_ = newIntList();
                                i |= 2;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.retriableStatusCodes_.addInt(codedInputStream.readUInt32());
                            }
                            codedInputStream.popLimit(iPushLimit);
                            break;
                        case 66:
                            RetryBackOff retryBackOff = this.retryBackOff_;
                            builder = retryBackOff != null ? retryBackOff.m28773toBuilder() : null;
                            RetryBackOff retryBackOff2 = (RetryBackOff) codedInputStream.readMessage(RetryBackOff.parser(), extensionRegistryLite);
                            this.retryBackOff_ = retryBackOff2;
                            if (builder != null) {
                                builder.mergeFrom(retryBackOff2);
                                this.retryBackOff_ = builder.m28780buildPartial();
                            }
                        case 74:
                            if ((i & 4) == 0) {
                                this.retriableHeaders_ = new ArrayList();
                                i |= 4;
                            }
                            this.retriableHeaders_.add(codedInputStream.readMessage(HeaderMatcher.parser(), extensionRegistryLite));
                        case 82:
                            if ((i & 8) == 0) {
                                this.retriableRequestHeaders_ = new ArrayList();
                                i |= 8;
                            }
                            this.retriableRequestHeaders_.add(codedInputStream.readMessage(HeaderMatcher.parser(), extensionRegistryLite));
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.retryHostPredicate_ = Collections.unmodifiableList(this.retryHostPredicate_);
                }
                if ((i & 2) != 0) {
                    this.retriableStatusCodes_.makeImmutable();
                }
                if ((i & 4) != 0) {
                    this.retriableHeaders_ = Collections.unmodifiableList(this.retriableHeaders_);
                }
                if ((i & 8) != 0) {
                    this.retriableRequestHeaders_ = Collections.unmodifiableList(this.retriableRequestHeaders_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static RetryPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RetryPolicy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_descriptor;
    }

    public static RetryPolicy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(byteBuffer);
    }

    public static RetryPolicy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RetryPolicy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(byteString);
    }

    public static RetryPolicy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RetryPolicy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(bArr);
    }

    public static RetryPolicy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryPolicy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RetryPolicy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RetryPolicy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryPolicy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RetryPolicy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryPolicy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RetryPolicy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m28727toBuilder();
    }

    public static Builder newBuilder(RetryPolicy retryPolicy) {
        return DEFAULT_INSTANCE.m28727toBuilder().mergeFrom(retryPolicy);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RetryPolicy m28722getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public long getHostSelectionRetryMaxAttempts() {
        return this.hostSelectionRetryMaxAttempts_;
    }

    public Parser<RetryPolicy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<HeaderMatcher> getRetriableHeadersList() {
        return this.retriableHeaders_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<? extends HeaderMatcherOrBuilder> getRetriableHeadersOrBuilderList() {
        return this.retriableHeaders_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<HeaderMatcher> getRetriableRequestHeadersList() {
        return this.retriableRequestHeaders_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<? extends HeaderMatcherOrBuilder> getRetriableRequestHeadersOrBuilderList() {
        return this.retriableRequestHeaders_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<Integer> getRetriableStatusCodesList() {
        return this.retriableStatusCodes_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<RetryHostPredicate> getRetryHostPredicateList() {
        return this.retryHostPredicate_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public List<? extends RetryHostPredicateOrBuilder> getRetryHostPredicateOrBuilderList() {
        return this.retryHostPredicate_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public boolean hasNumRetries() {
        return this.numRetries_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public boolean hasPerTryTimeout() {
        return this.perTryTimeout_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public boolean hasRetryBackOff() {
        return this.retryBackOff_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public boolean hasRetryPriority() {
        return this.retryPriority_ != null;
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
        return new RetryPolicy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryPolicy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public String getRetryOn() {
        Object obj = this.retryOn_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.retryOn_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public ByteString getRetryOnBytes() {
        Object obj = this.retryOn_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.retryOn_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public UInt32Value getNumRetries() {
        UInt32Value uInt32Value = this.numRetries_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public UInt32ValueOrBuilder getNumRetriesOrBuilder() {
        return getNumRetries();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public Duration getPerTryTimeout() {
        Duration duration = this.perTryTimeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public DurationOrBuilder getPerTryTimeoutOrBuilder() {
        return getPerTryTimeout();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryPriority getRetryPriority() {
        RetryPriority retryPriority = this.retryPriority_;
        return retryPriority == null ? RetryPriority.getDefaultInstance() : retryPriority;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryPriorityOrBuilder getRetryPriorityOrBuilder() {
        return getRetryPriority();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public int getRetryHostPredicateCount() {
        return this.retryHostPredicate_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryHostPredicate getRetryHostPredicate(int i) {
        return this.retryHostPredicate_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryHostPredicateOrBuilder getRetryHostPredicateOrBuilder(int i) {
        return this.retryHostPredicate_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public int getRetriableStatusCodesCount() {
        return this.retriableStatusCodes_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public int getRetriableStatusCodes(int i) {
        return this.retriableStatusCodes_.getInt(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryBackOff getRetryBackOff() {
        RetryBackOff retryBackOff = this.retryBackOff_;
        return retryBackOff == null ? RetryBackOff.getDefaultInstance() : retryBackOff;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public RetryBackOffOrBuilder getRetryBackOffOrBuilder() {
        return getRetryBackOff();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public int getRetriableHeadersCount() {
        return this.retriableHeaders_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public HeaderMatcher getRetriableHeaders(int i) {
        return this.retriableHeaders_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public HeaderMatcherOrBuilder getRetriableHeadersOrBuilder(int i) {
        return this.retriableHeaders_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public int getRetriableRequestHeadersCount() {
        return this.retriableRequestHeaders_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public HeaderMatcher getRetriableRequestHeaders(int i) {
        return this.retriableRequestHeaders_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
    public HeaderMatcherOrBuilder getRetriableRequestHeadersOrBuilder(int i) {
        return this.retriableRequestHeaders_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (!getRetryOnBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.retryOn_);
        }
        if (this.numRetries_ != null) {
            codedOutputStream.writeMessage(2, getNumRetries());
        }
        if (this.perTryTimeout_ != null) {
            codedOutputStream.writeMessage(3, getPerTryTimeout());
        }
        if (this.retryPriority_ != null) {
            codedOutputStream.writeMessage(4, getRetryPriority());
        }
        for (int i = 0; i < this.retryHostPredicate_.size(); i++) {
            codedOutputStream.writeMessage(5, this.retryHostPredicate_.get(i));
        }
        long j = this.hostSelectionRetryMaxAttempts_;
        if (j != 0) {
            codedOutputStream.writeInt64(6, j);
        }
        if (getRetriableStatusCodesList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(58);
            codedOutputStream.writeUInt32NoTag(this.retriableStatusCodesMemoizedSerializedSize);
        }
        for (int i2 = 0; i2 < this.retriableStatusCodes_.size(); i2++) {
            codedOutputStream.writeUInt32NoTag(this.retriableStatusCodes_.getInt(i2));
        }
        if (this.retryBackOff_ != null) {
            codedOutputStream.writeMessage(8, getRetryBackOff());
        }
        for (int i3 = 0; i3 < this.retriableHeaders_.size(); i3++) {
            codedOutputStream.writeMessage(9, this.retriableHeaders_.get(i3));
        }
        for (int i4 = 0; i4 < this.retriableRequestHeaders_.size(); i4++) {
            codedOutputStream.writeMessage(10, this.retriableRequestHeaders_.get(i4));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRetryOnBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.retryOn_) : 0;
        if (this.numRetries_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getNumRetries());
        }
        if (this.perTryTimeout_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getPerTryTimeout());
        }
        if (this.retryPriority_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getRetryPriority());
        }
        for (int i2 = 0; i2 < this.retryHostPredicate_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.retryHostPredicate_.get(i2));
        }
        long j = this.hostSelectionRetryMaxAttempts_;
        if (j != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(6, j);
        }
        int iComputeUInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < this.retriableStatusCodes_.size(); i3++) {
            iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(this.retriableStatusCodes_.getInt(i3));
        }
        int iComputeMessageSize = iComputeStringSize + iComputeUInt32SizeNoTag;
        if (!getRetriableStatusCodesList().isEmpty()) {
            iComputeMessageSize = iComputeMessageSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeUInt32SizeNoTag);
        }
        this.retriableStatusCodesMemoizedSerializedSize = iComputeUInt32SizeNoTag;
        if (this.retryBackOff_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(8, getRetryBackOff());
        }
        for (int i4 = 0; i4 < this.retriableHeaders_.size(); i4++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(9, this.retriableHeaders_.get(i4));
        }
        for (int i5 = 0; i5 < this.retriableRequestHeaders_.size(); i5++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(10, this.retriableRequestHeaders_.get(i5));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetryPolicy)) {
            return super.equals(obj);
        }
        RetryPolicy retryPolicy = (RetryPolicy) obj;
        if (!getRetryOn().equals(retryPolicy.getRetryOn()) || hasNumRetries() != retryPolicy.hasNumRetries()) {
            return false;
        }
        if ((hasNumRetries() && !getNumRetries().equals(retryPolicy.getNumRetries())) || hasPerTryTimeout() != retryPolicy.hasPerTryTimeout()) {
            return false;
        }
        if ((hasPerTryTimeout() && !getPerTryTimeout().equals(retryPolicy.getPerTryTimeout())) || hasRetryPriority() != retryPolicy.hasRetryPriority()) {
            return false;
        }
        if ((!hasRetryPriority() || getRetryPriority().equals(retryPolicy.getRetryPriority())) && getRetryHostPredicateList().equals(retryPolicy.getRetryHostPredicateList()) && getHostSelectionRetryMaxAttempts() == retryPolicy.getHostSelectionRetryMaxAttempts() && getRetriableStatusCodesList().equals(retryPolicy.getRetriableStatusCodesList()) && hasRetryBackOff() == retryPolicy.hasRetryBackOff()) {
            return (!hasRetryBackOff() || getRetryBackOff().equals(retryPolicy.getRetryBackOff())) && getRetriableHeadersList().equals(retryPolicy.getRetriableHeadersList()) && getRetriableRequestHeadersList().equals(retryPolicy.getRetriableRequestHeadersList()) && this.unknownFields.equals(retryPolicy.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRetryOn().hashCode();
        if (hasNumRetries()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getNumRetries().hashCode();
        }
        if (hasPerTryTimeout()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getPerTryTimeout().hashCode();
        }
        if (hasRetryPriority()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getRetryPriority().hashCode();
        }
        if (getRetryHostPredicateCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getRetryHostPredicateList().hashCode();
        }
        int iHashLong = (((iHashCode * 37) + 6) * 53) + Internal.hashLong(getHostSelectionRetryMaxAttempts());
        if (getRetriableStatusCodesCount() > 0) {
            iHashLong = (((iHashLong * 37) + 7) * 53) + getRetriableStatusCodesList().hashCode();
        }
        if (hasRetryBackOff()) {
            iHashLong = (((iHashLong * 37) + 8) * 53) + getRetryBackOff().hashCode();
        }
        if (getRetriableHeadersCount() > 0) {
            iHashLong = (((iHashLong * 37) + 9) * 53) + getRetriableHeadersList().hashCode();
        }
        if (getRetriableRequestHeadersCount() > 0) {
            iHashLong = (((iHashLong * 37) + 10) * 53) + getRetriableRequestHeadersList().hashCode();
        }
        int iHashCode2 = (iHashLong * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m28724newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m28727toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface RetryBackOffOrBuilder extends MessageOrBuilder {
        Duration getBaseInterval();

        DurationOrBuilder getBaseIntervalOrBuilder();

        Duration getMaxInterval();

        DurationOrBuilder getMaxIntervalOrBuilder();

        boolean hasBaseInterval();

        boolean hasMaxInterval();
    }

    public interface RetryHostPredicateOrBuilder extends MessageOrBuilder {
        RetryHostPredicate.ConfigTypeCase getConfigTypeCase();

        String getName();

        ByteString getNameBytes();

        Any getTypedConfig();

        AnyOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public interface RetryPriorityOrBuilder extends MessageOrBuilder {
        RetryPriority.ConfigTypeCase getConfigTypeCase();

        String getName();

        ByteString getNameBytes();

        Any getTypedConfig();

        AnyOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public static final class RetryPriority extends GeneratedMessageV3 implements RetryPriorityOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final RetryPriority DEFAULT_INSTANCE = new RetryPriority();
        private static final Parser<RetryPriority> PARSER = new AbstractParser<RetryPriority>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public RetryPriority m28867parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RetryPriority(codedInputStream, extensionRegistryLite);
            }
        };
        private int configTypeCase_;
        private Object configType_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private RetryPriority(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private RetryPriority() {
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private RetryPriority(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

        public static RetryPriority getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RetryPriority> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryPriority_descriptor;
        }

        public static RetryPriority parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(byteBuffer);
        }

        public static RetryPriority parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RetryPriority parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(byteString);
        }

        public static RetryPriority parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RetryPriority parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(bArr);
        }

        public static RetryPriority parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryPriority) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RetryPriority parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RetryPriority parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryPriority parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RetryPriority parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryPriority parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RetryPriority parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m28865toBuilder();
        }

        public static Builder newBuilder(RetryPriority retryPriority) {
            return DEFAULT_INSTANCE.m28865toBuilder().mergeFrom(retryPriority);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryPriority m28860getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<RetryPriority> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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
            return new RetryPriority();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryPriority_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryPriority.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
        public Any getTypedConfig() {
            if (this.configTypeCase_ == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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
            if (!(obj instanceof RetryPriority)) {
                return super.equals(obj);
            }
            RetryPriority retryPriority = (RetryPriority) obj;
            if (getName().equals(retryPriority.getName()) && getConfigTypeCase().equals(retryPriority.getConfigTypeCase())) {
                return (this.configTypeCase_ != 3 || getTypedConfig().equals(retryPriority.getTypedConfig())) && this.unknownFields.equals(retryPriority.unknownFields);
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
        public Builder m28862newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28865toBuilder() {
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryPriorityOrBuilder {
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
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryPriority_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
            public boolean hasTypedConfig() {
                return this.configTypeCase_ == 3;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryPriority_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryPriority.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RetryPriority.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28876clear() {
                super.clear();
                this.name_ = "";
                this.configTypeCase_ = 0;
                this.configType_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryPriority_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryPriority m28889getDefaultInstanceForType() {
                return RetryPriority.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryPriority m28870build() throws UninitializedMessageException {
                RetryPriority retryPriorityM28872buildPartial = m28872buildPartial();
                if (retryPriorityM28872buildPartial.isInitialized()) {
                    return retryPriorityM28872buildPartial;
                }
                throw newUninitializedMessageException(retryPriorityM28872buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryPriority m28872buildPartial() {
                RetryPriority retryPriority = new RetryPriority(this);
                retryPriority.name_ = this.name_;
                if (this.configTypeCase_ == 3) {
                    SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        retryPriority.configType_ = this.configType_;
                    } else {
                        retryPriority.configType_ = singleFieldBuilderV3.build();
                    }
                }
                retryPriority.configTypeCase_ = this.configTypeCase_;
                onBuilt();
                return retryPriority;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28888clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28900setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28878clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28881clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28902setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28868addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28893mergeFrom(Message message) {
                if (message instanceof RetryPriority) {
                    return mergeFrom((RetryPriority) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RetryPriority retryPriority) {
                if (retryPriority == RetryPriority.getDefaultInstance()) {
                    return this;
                }
                if (!retryPriority.getName().isEmpty()) {
                    this.name_ = retryPriority.name_;
                    onChanged();
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryPriority$ConfigTypeCase[retryPriority.getConfigTypeCase().ordinal()] == 1) {
                    mergeTypedConfig(retryPriority.getTypedConfig());
                }
                m28898mergeUnknownFields(retryPriority.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority.Builder m28894mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryPriority r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryPriority r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriority.Builder.m28894mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryPriority$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
            public ConfigTypeCase getConfigTypeCase() {
                return ConfigTypeCase.forNumber(this.configTypeCase_);
            }

            public Builder clearConfigType() {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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
                RetryPriority.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = RetryPriority.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryPriorityOrBuilder
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
            public final Builder m28904setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m28898mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RetryHostPredicate extends GeneratedMessageV3 implements RetryHostPredicateOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final RetryHostPredicate DEFAULT_INSTANCE = new RetryHostPredicate();
        private static final Parser<RetryHostPredicate> PARSER = new AbstractParser<RetryHostPredicate>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public RetryHostPredicate m28821parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RetryHostPredicate(codedInputStream, extensionRegistryLite);
            }
        };
        private int configTypeCase_;
        private Object configType_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private RetryHostPredicate(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private RetryHostPredicate() {
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private RetryHostPredicate(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

        public static RetryHostPredicate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RetryHostPredicate> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryHostPredicate_descriptor;
        }

        public static RetryHostPredicate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(byteBuffer);
        }

        public static RetryHostPredicate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RetryHostPredicate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(byteString);
        }

        public static RetryHostPredicate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RetryHostPredicate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(bArr);
        }

        public static RetryHostPredicate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryHostPredicate) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RetryHostPredicate parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RetryHostPredicate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryHostPredicate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RetryHostPredicate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryHostPredicate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RetryHostPredicate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m28819toBuilder();
        }

        public static Builder newBuilder(RetryHostPredicate retryHostPredicate) {
            return DEFAULT_INSTANCE.m28819toBuilder().mergeFrom(retryHostPredicate);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryHostPredicate m28814getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<RetryHostPredicate> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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
            return new RetryHostPredicate();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryHostPredicate_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryHostPredicate.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
        public Any getTypedConfig() {
            if (this.configTypeCase_ == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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
            if (!(obj instanceof RetryHostPredicate)) {
                return super.equals(obj);
            }
            RetryHostPredicate retryHostPredicate = (RetryHostPredicate) obj;
            if (getName().equals(retryHostPredicate.getName()) && getConfigTypeCase().equals(retryHostPredicate.getConfigTypeCase())) {
                return (this.configTypeCase_ != 3 || getTypedConfig().equals(retryHostPredicate.getTypedConfig())) && this.unknownFields.equals(retryHostPredicate.unknownFields);
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
        public Builder m28816newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28819toBuilder() {
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryHostPredicateOrBuilder {
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
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryHostPredicate_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
            public boolean hasTypedConfig() {
                return this.configTypeCase_ == 3;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryHostPredicate_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryHostPredicate.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RetryHostPredicate.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28830clear() {
                super.clear();
                this.name_ = "";
                this.configTypeCase_ = 0;
                this.configType_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryHostPredicate_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryHostPredicate m28843getDefaultInstanceForType() {
                return RetryHostPredicate.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryHostPredicate m28824build() throws UninitializedMessageException {
                RetryHostPredicate retryHostPredicateM28826buildPartial = m28826buildPartial();
                if (retryHostPredicateM28826buildPartial.isInitialized()) {
                    return retryHostPredicateM28826buildPartial;
                }
                throw newUninitializedMessageException(retryHostPredicateM28826buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryHostPredicate m28826buildPartial() {
                RetryHostPredicate retryHostPredicate = new RetryHostPredicate(this);
                retryHostPredicate.name_ = this.name_;
                if (this.configTypeCase_ == 3) {
                    SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        retryHostPredicate.configType_ = this.configType_;
                    } else {
                        retryHostPredicate.configType_ = singleFieldBuilderV3.build();
                    }
                }
                retryHostPredicate.configTypeCase_ = this.configTypeCase_;
                onBuilt();
                return retryHostPredicate;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28842clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28854setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28832clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28835clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28856setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28822addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28847mergeFrom(Message message) {
                if (message instanceof RetryHostPredicate) {
                    return mergeFrom((RetryHostPredicate) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RetryHostPredicate retryHostPredicate) {
                if (retryHostPredicate == RetryHostPredicate.getDefaultInstance()) {
                    return this;
                }
                if (!retryHostPredicate.getName().isEmpty()) {
                    this.name_ = retryHostPredicate.name_;
                    onChanged();
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryHostPredicate$ConfigTypeCase[retryHostPredicate.getConfigTypeCase().ordinal()] == 1) {
                    mergeTypedConfig(retryHostPredicate.getTypedConfig());
                }
                m28852mergeUnknownFields(retryHostPredicate.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate.Builder m28848mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryHostPredicate r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryHostPredicate r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicate.Builder.m28848mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryHostPredicate$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
            public ConfigTypeCase getConfigTypeCase() {
                return ConfigTypeCase.forNumber(this.configTypeCase_);
            }

            public Builder clearConfigType() {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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
                RetryHostPredicate.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = RetryHostPredicate.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryHostPredicateOrBuilder
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
            public final Builder m28858setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m28852mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryHostPredicate$ConfigTypeCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryPriority$ConfigTypeCase;

        static {
            int[] iArr = new int[RetryHostPredicate.ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryHostPredicate$ConfigTypeCase = iArr;
            try {
                iArr[RetryHostPredicate.ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryHostPredicate$ConfigTypeCase[RetryHostPredicate.ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[RetryPriority.ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryPriority$ConfigTypeCase = iArr2;
            try {
                iArr2[RetryPriority.ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RetryPolicy$RetryPriority$ConfigTypeCase[RetryPriority.ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static final class RetryBackOff extends GeneratedMessageV3 implements RetryBackOffOrBuilder {
        public static final int BASE_INTERVAL_FIELD_NUMBER = 1;
        public static final int MAX_INTERVAL_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final RetryBackOff DEFAULT_INSTANCE = new RetryBackOff();
        private static final Parser<RetryBackOff> PARSER = new AbstractParser<RetryBackOff>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public RetryBackOff m28775parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RetryBackOff(codedInputStream, extensionRegistryLite);
            }
        };
        private Duration baseInterval_;
        private Duration maxInterval_;
        private byte memoizedIsInitialized;

        private RetryBackOff(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RetryBackOff() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private RetryBackOff(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Duration.Builder builder;
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
                                Duration duration = this.baseInterval_;
                                builder = duration != null ? duration.toBuilder() : null;
                                Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                this.baseInterval_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.baseInterval_ = builder.buildPartial();
                                }
                            } else if (tag == 18) {
                                Duration duration2 = this.maxInterval_;
                                builder = duration2 != null ? duration2.toBuilder() : null;
                                Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                this.maxInterval_ = message2;
                                if (builder != null) {
                                    builder.mergeFrom(message2);
                                    this.maxInterval_ = builder.buildPartial();
                                }
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

        public static RetryBackOff getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RetryBackOff> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryBackOff_descriptor;
        }

        public static RetryBackOff parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(byteBuffer);
        }

        public static RetryBackOff parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RetryBackOff parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(byteString);
        }

        public static RetryBackOff parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RetryBackOff parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(bArr);
        }

        public static RetryBackOff parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RetryBackOff) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RetryBackOff parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RetryBackOff parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryBackOff parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RetryBackOff parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RetryBackOff parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RetryBackOff parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m28773toBuilder();
        }

        public static Builder newBuilder(RetryBackOff retryBackOff) {
            return DEFAULT_INSTANCE.m28773toBuilder().mergeFrom(retryBackOff);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryBackOff m28768getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<RetryBackOff> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public boolean hasBaseInterval() {
            return this.baseInterval_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public boolean hasMaxInterval() {
            return this.maxInterval_ != null;
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
            return new RetryBackOff();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryBackOff_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryBackOff.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public Duration getBaseInterval() {
            Duration duration = this.baseInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public DurationOrBuilder getBaseIntervalOrBuilder() {
            return getBaseInterval();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public Duration getMaxInterval() {
            Duration duration = this.maxInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
        public DurationOrBuilder getMaxIntervalOrBuilder() {
            return getMaxInterval();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.baseInterval_ != null) {
                codedOutputStream.writeMessage(1, getBaseInterval());
            }
            if (this.maxInterval_ != null) {
                codedOutputStream.writeMessage(2, getMaxInterval());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.baseInterval_ != null ? CodedOutputStream.computeMessageSize(1, getBaseInterval()) : 0;
            if (this.maxInterval_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getMaxInterval());
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RetryBackOff)) {
                return super.equals(obj);
            }
            RetryBackOff retryBackOff = (RetryBackOff) obj;
            if (hasBaseInterval() != retryBackOff.hasBaseInterval()) {
                return false;
            }
            if ((!hasBaseInterval() || getBaseInterval().equals(retryBackOff.getBaseInterval())) && hasMaxInterval() == retryBackOff.hasMaxInterval()) {
                return (!hasMaxInterval() || getMaxInterval().equals(retryBackOff.getMaxInterval())) && this.unknownFields.equals(retryBackOff.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasBaseInterval()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getBaseInterval().hashCode();
            }
            if (hasMaxInterval()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getMaxInterval().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28770newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28773toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryBackOffOrBuilder {
            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> baseIntervalBuilder_;
            private Duration baseInterval_;
            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> maxIntervalBuilder_;
            private Duration maxInterval_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryBackOff_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public boolean hasBaseInterval() {
                return (this.baseIntervalBuilder_ == null && this.baseInterval_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public boolean hasMaxInterval() {
                return (this.maxIntervalBuilder_ == null && this.maxInterval_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryBackOff_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryBackOff.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RetryBackOff.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28784clear() {
                super.clear();
                if (this.baseIntervalBuilder_ == null) {
                    this.baseInterval_ = null;
                } else {
                    this.baseInterval_ = null;
                    this.baseIntervalBuilder_ = null;
                }
                if (this.maxIntervalBuilder_ == null) {
                    this.maxInterval_ = null;
                } else {
                    this.maxInterval_ = null;
                    this.maxIntervalBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_RetryBackOff_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryBackOff m28797getDefaultInstanceForType() {
                return RetryBackOff.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryBackOff m28778build() throws UninitializedMessageException {
                RetryBackOff retryBackOffM28780buildPartial = m28780buildPartial();
                if (retryBackOffM28780buildPartial.isInitialized()) {
                    return retryBackOffM28780buildPartial;
                }
                throw newUninitializedMessageException(retryBackOffM28780buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryBackOff m28780buildPartial() {
                RetryBackOff retryBackOff = new RetryBackOff(this);
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    retryBackOff.baseInterval_ = this.baseInterval_;
                } else {
                    retryBackOff.baseInterval_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV32 == null) {
                    retryBackOff.maxInterval_ = this.maxInterval_;
                } else {
                    retryBackOff.maxInterval_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return retryBackOff;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28796clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28808setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28786clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28789clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28810setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28776addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m28801mergeFrom(Message message) {
                if (message instanceof RetryBackOff) {
                    return mergeFrom((RetryBackOff) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RetryBackOff retryBackOff) {
                if (retryBackOff == RetryBackOff.getDefaultInstance()) {
                    return this;
                }
                if (retryBackOff.hasBaseInterval()) {
                    mergeBaseInterval(retryBackOff.getBaseInterval());
                }
                if (retryBackOff.hasMaxInterval()) {
                    mergeMaxInterval(retryBackOff.getMaxInterval());
                }
                m28806mergeUnknownFields(retryBackOff.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff.Builder m28802mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff.access$2900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryBackOff r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryBackOff r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOff.Builder.m28802mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$RetryBackOff$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public Duration getBaseInterval() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Duration duration = this.baseInterval_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            public Builder setBaseInterval(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    duration.getClass();
                    this.baseInterval_ = duration;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(duration);
                }
                return this;
            }

            public Builder setBaseInterval(Duration.Builder builder) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.baseInterval_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeBaseInterval(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Duration duration2 = this.baseInterval_;
                    if (duration2 != null) {
                        this.baseInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                    } else {
                        this.baseInterval_ = duration;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(duration);
                }
                return this;
            }

            public Builder clearBaseInterval() {
                if (this.baseIntervalBuilder_ == null) {
                    this.baseInterval_ = null;
                    onChanged();
                } else {
                    this.baseInterval_ = null;
                    this.baseIntervalBuilder_ = null;
                }
                return this;
            }

            public Duration.Builder getBaseIntervalBuilder() {
                onChanged();
                return getBaseIntervalFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public DurationOrBuilder getBaseIntervalOrBuilder() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Duration duration = this.baseInterval_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getBaseIntervalFieldBuilder() {
                if (this.baseIntervalBuilder_ == null) {
                    this.baseIntervalBuilder_ = new SingleFieldBuilderV3<>(getBaseInterval(), getParentForChildren(), isClean());
                    this.baseInterval_ = null;
                }
                return this.baseIntervalBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public Duration getMaxInterval() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Duration duration = this.maxInterval_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            public Builder setMaxInterval(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    duration.getClass();
                    this.maxInterval_ = duration;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(duration);
                }
                return this;
            }

            public Builder setMaxInterval(Duration.Builder builder) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxInterval_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxInterval(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Duration duration2 = this.maxInterval_;
                    if (duration2 != null) {
                        this.maxInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                    } else {
                        this.maxInterval_ = duration;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(duration);
                }
                return this;
            }

            public Builder clearMaxInterval() {
                if (this.maxIntervalBuilder_ == null) {
                    this.maxInterval_ = null;
                    onChanged();
                } else {
                    this.maxInterval_ = null;
                    this.maxIntervalBuilder_ = null;
                }
                return this;
            }

            public Duration.Builder getMaxIntervalBuilder() {
                onChanged();
                return getMaxIntervalFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.RetryBackOffOrBuilder
            public DurationOrBuilder getMaxIntervalOrBuilder() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Duration duration = this.maxInterval_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getMaxIntervalFieldBuilder() {
                if (this.maxIntervalBuilder_ == null) {
                    this.maxIntervalBuilder_ = new SingleFieldBuilderV3<>(getMaxInterval(), getParentForChildren(), isClean());
                    this.maxInterval_ = null;
                }
                return this.maxIntervalBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m28812setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m28806mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryPolicyOrBuilder {
        private int bitField0_;
        private long hostSelectionRetryMaxAttempts_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> numRetriesBuilder_;
        private UInt32Value numRetries_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> perTryTimeoutBuilder_;
        private Duration perTryTimeout_;
        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> retriableHeadersBuilder_;
        private List<HeaderMatcher> retriableHeaders_;
        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> retriableRequestHeadersBuilder_;
        private List<HeaderMatcher> retriableRequestHeaders_;
        private Internal.IntList retriableStatusCodes_;
        private SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> retryBackOffBuilder_;
        private RetryBackOff retryBackOff_;
        private RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> retryHostPredicateBuilder_;
        private List<RetryHostPredicate> retryHostPredicate_;
        private Object retryOn_;
        private SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> retryPriorityBuilder_;
        private RetryPriority retryPriority_;

        private Builder() {
            this.retryOn_ = "";
            this.retryHostPredicate_ = Collections.emptyList();
            this.retriableStatusCodes_ = RetryPolicy.emptyIntList();
            this.retriableHeaders_ = Collections.emptyList();
            this.retriableRequestHeaders_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.retryOn_ = "";
            this.retryHostPredicate_ = Collections.emptyList();
            this.retriableStatusCodes_ = RetryPolicy.emptyIntList();
            this.retriableHeaders_ = Collections.emptyList();
            this.retriableRequestHeaders_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public long getHostSelectionRetryMaxAttempts() {
            return this.hostSelectionRetryMaxAttempts_;
        }

        public Builder setHostSelectionRetryMaxAttempts(long j) {
            this.hostSelectionRetryMaxAttempts_ = j;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public boolean hasNumRetries() {
            return (this.numRetriesBuilder_ == null && this.numRetries_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public boolean hasPerTryTimeout() {
            return (this.perTryTimeoutBuilder_ == null && this.perTryTimeout_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public boolean hasRetryBackOff() {
            return (this.retryBackOffBuilder_ == null && this.retryBackOff_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public boolean hasRetryPriority() {
            return (this.retryPriorityBuilder_ == null && this.retryPriority_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryPolicy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (RetryPolicy.alwaysUseFieldBuilders) {
                getRetryHostPredicateFieldBuilder();
                getRetriableHeadersFieldBuilder();
                getRetriableRequestHeadersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28738clear() {
            super.clear();
            this.retryOn_ = "";
            if (this.numRetriesBuilder_ == null) {
                this.numRetries_ = null;
            } else {
                this.numRetries_ = null;
                this.numRetriesBuilder_ = null;
            }
            if (this.perTryTimeoutBuilder_ == null) {
                this.perTryTimeout_ = null;
            } else {
                this.perTryTimeout_ = null;
                this.perTryTimeoutBuilder_ = null;
            }
            if (this.retryPriorityBuilder_ == null) {
                this.retryPriority_ = null;
            } else {
                this.retryPriority_ = null;
                this.retryPriorityBuilder_ = null;
            }
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.retryHostPredicate_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.hostSelectionRetryMaxAttempts_ = 0L;
            this.retriableStatusCodes_ = RetryPolicy.emptyIntList();
            this.bitField0_ &= -3;
            if (this.retryBackOffBuilder_ == null) {
                this.retryBackOff_ = null;
            } else {
                this.retryBackOff_ = null;
                this.retryBackOffBuilder_ = null;
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV32 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.retriableHeaders_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV33 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.retriableRequestHeaders_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_RetryPolicy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryPolicy m28751getDefaultInstanceForType() {
            return RetryPolicy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryPolicy m28732build() throws UninitializedMessageException {
            RetryPolicy retryPolicyM28734buildPartial = m28734buildPartial();
            if (retryPolicyM28734buildPartial.isInitialized()) {
                return retryPolicyM28734buildPartial;
            }
            throw newUninitializedMessageException(retryPolicyM28734buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryPolicy m28734buildPartial() {
            RetryPolicy retryPolicy = new RetryPolicy(this);
            retryPolicy.retryOn_ = this.retryOn_;
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryPolicy.numRetries_ = this.numRetries_;
            } else {
                retryPolicy.numRetries_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV32 == null) {
                retryPolicy.perTryTimeout_ = this.perTryTimeout_;
            } else {
                retryPolicy.perTryTimeout_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV33 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV33 == null) {
                retryPolicy.retryPriority_ = this.retryPriority_;
            } else {
                retryPolicy.retryPriority_ = singleFieldBuilderV33.build();
            }
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.retryHostPredicate_ = Collections.unmodifiableList(this.retryHostPredicate_);
                    this.bitField0_ &= -2;
                }
                retryPolicy.retryHostPredicate_ = this.retryHostPredicate_;
            } else {
                retryPolicy.retryHostPredicate_ = repeatedFieldBuilderV3.build();
            }
            retryPolicy.hostSelectionRetryMaxAttempts_ = this.hostSelectionRetryMaxAttempts_;
            if ((this.bitField0_ & 2) != 0) {
                this.retriableStatusCodes_.makeImmutable();
                this.bitField0_ &= -3;
            }
            retryPolicy.retriableStatusCodes_ = this.retriableStatusCodes_;
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV34 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV34 == null) {
                retryPolicy.retryBackOff_ = this.retryBackOff_;
            } else {
                retryPolicy.retryBackOff_ = singleFieldBuilderV34.build();
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV32 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.retriableHeaders_ = Collections.unmodifiableList(this.retriableHeaders_);
                    this.bitField0_ &= -5;
                }
                retryPolicy.retriableHeaders_ = this.retriableHeaders_;
            } else {
                retryPolicy.retriableHeaders_ = repeatedFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV33 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 8) != 0) {
                    this.retriableRequestHeaders_ = Collections.unmodifiableList(this.retriableRequestHeaders_);
                    this.bitField0_ &= -9;
                }
                retryPolicy.retriableRequestHeaders_ = this.retriableRequestHeaders_;
            } else {
                retryPolicy.retriableRequestHeaders_ = repeatedFieldBuilderV33.build();
            }
            onBuilt();
            return retryPolicy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28750clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28762setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28740clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28743clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28764setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28730addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m28755mergeFrom(Message message) {
            if (message instanceof RetryPolicy) {
                return mergeFrom((RetryPolicy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RetryPolicy retryPolicy) {
            if (retryPolicy == RetryPolicy.getDefaultInstance()) {
                return this;
            }
            if (!retryPolicy.getRetryOn().isEmpty()) {
                this.retryOn_ = retryPolicy.retryOn_;
                onChanged();
            }
            if (retryPolicy.hasNumRetries()) {
                mergeNumRetries(retryPolicy.getNumRetries());
            }
            if (retryPolicy.hasPerTryTimeout()) {
                mergePerTryTimeout(retryPolicy.getPerTryTimeout());
            }
            if (retryPolicy.hasRetryPriority()) {
                mergeRetryPriority(retryPolicy.getRetryPriority());
            }
            if (this.retryHostPredicateBuilder_ == null) {
                if (!retryPolicy.retryHostPredicate_.isEmpty()) {
                    if (this.retryHostPredicate_.isEmpty()) {
                        this.retryHostPredicate_ = retryPolicy.retryHostPredicate_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRetryHostPredicateIsMutable();
                        this.retryHostPredicate_.addAll(retryPolicy.retryHostPredicate_);
                    }
                    onChanged();
                }
            } else if (!retryPolicy.retryHostPredicate_.isEmpty()) {
                if (!this.retryHostPredicateBuilder_.isEmpty()) {
                    this.retryHostPredicateBuilder_.addAllMessages(retryPolicy.retryHostPredicate_);
                } else {
                    this.retryHostPredicateBuilder_.dispose();
                    this.retryHostPredicateBuilder_ = null;
                    this.retryHostPredicate_ = retryPolicy.retryHostPredicate_;
                    this.bitField0_ &= -2;
                    this.retryHostPredicateBuilder_ = RetryPolicy.alwaysUseFieldBuilders ? getRetryHostPredicateFieldBuilder() : null;
                }
            }
            if (retryPolicy.getHostSelectionRetryMaxAttempts() != 0) {
                setHostSelectionRetryMaxAttempts(retryPolicy.getHostSelectionRetryMaxAttempts());
            }
            if (!retryPolicy.retriableStatusCodes_.isEmpty()) {
                if (this.retriableStatusCodes_.isEmpty()) {
                    this.retriableStatusCodes_ = retryPolicy.retriableStatusCodes_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRetriableStatusCodesIsMutable();
                    this.retriableStatusCodes_.addAll(retryPolicy.retriableStatusCodes_);
                }
                onChanged();
            }
            if (retryPolicy.hasRetryBackOff()) {
                mergeRetryBackOff(retryPolicy.getRetryBackOff());
            }
            if (this.retriableHeadersBuilder_ == null) {
                if (!retryPolicy.retriableHeaders_.isEmpty()) {
                    if (this.retriableHeaders_.isEmpty()) {
                        this.retriableHeaders_ = retryPolicy.retriableHeaders_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureRetriableHeadersIsMutable();
                        this.retriableHeaders_.addAll(retryPolicy.retriableHeaders_);
                    }
                    onChanged();
                }
            } else if (!retryPolicy.retriableHeaders_.isEmpty()) {
                if (!this.retriableHeadersBuilder_.isEmpty()) {
                    this.retriableHeadersBuilder_.addAllMessages(retryPolicy.retriableHeaders_);
                } else {
                    this.retriableHeadersBuilder_.dispose();
                    this.retriableHeadersBuilder_ = null;
                    this.retriableHeaders_ = retryPolicy.retriableHeaders_;
                    this.bitField0_ &= -5;
                    this.retriableHeadersBuilder_ = RetryPolicy.alwaysUseFieldBuilders ? getRetriableHeadersFieldBuilder() : null;
                }
            }
            if (this.retriableRequestHeadersBuilder_ == null) {
                if (!retryPolicy.retriableRequestHeaders_.isEmpty()) {
                    if (this.retriableRequestHeaders_.isEmpty()) {
                        this.retriableRequestHeaders_ = retryPolicy.retriableRequestHeaders_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureRetriableRequestHeadersIsMutable();
                        this.retriableRequestHeaders_.addAll(retryPolicy.retriableRequestHeaders_);
                    }
                    onChanged();
                }
            } else if (!retryPolicy.retriableRequestHeaders_.isEmpty()) {
                if (!this.retriableRequestHeadersBuilder_.isEmpty()) {
                    this.retriableRequestHeadersBuilder_.addAllMessages(retryPolicy.retriableRequestHeaders_);
                } else {
                    this.retriableRequestHeadersBuilder_.dispose();
                    this.retriableRequestHeadersBuilder_ = null;
                    this.retriableRequestHeaders_ = retryPolicy.retriableRequestHeaders_;
                    this.bitField0_ &= -9;
                    this.retriableRequestHeadersBuilder_ = RetryPolicy.alwaysUseFieldBuilders ? getRetriableRequestHeadersFieldBuilder() : null;
                }
            }
            m28760mergeUnknownFields(retryPolicy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.Builder m28756mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.access$5000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy.Builder.m28756mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicy$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public String getRetryOn() {
            Object obj = this.retryOn_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.retryOn_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRetryOn(String str) {
            str.getClass();
            this.retryOn_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public ByteString getRetryOnBytes() {
            Object obj = this.retryOn_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.retryOn_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRetryOnBytes(ByteString byteString) {
            byteString.getClass();
            RetryPolicy.checkByteStringIsUtf8(byteString);
            this.retryOn_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRetryOn() {
            this.retryOn_ = RetryPolicy.getDefaultInstance().getRetryOn();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public UInt32Value getNumRetries() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.numRetries_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setNumRetries(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.numRetries_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setNumRetries(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.numRetries_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeNumRetries(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.numRetries_;
                if (uInt32Value2 != null) {
                    this.numRetries_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.numRetries_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearNumRetries() {
            if (this.numRetriesBuilder_ == null) {
                this.numRetries_ = null;
                onChanged();
            } else {
                this.numRetries_ = null;
                this.numRetriesBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getNumRetriesBuilder() {
            onChanged();
            return getNumRetriesFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public UInt32ValueOrBuilder getNumRetriesOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.numRetriesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.numRetries_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getNumRetriesFieldBuilder() {
            if (this.numRetriesBuilder_ == null) {
                this.numRetriesBuilder_ = new SingleFieldBuilderV3<>(getNumRetries(), getParentForChildren(), isClean());
                this.numRetries_ = null;
            }
            return this.numRetriesBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public Duration getPerTryTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.perTryTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setPerTryTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.perTryTimeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setPerTryTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.perTryTimeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePerTryTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.perTryTimeout_;
                if (duration2 != null) {
                    this.perTryTimeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.perTryTimeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearPerTryTimeout() {
            if (this.perTryTimeoutBuilder_ == null) {
                this.perTryTimeout_ = null;
                onChanged();
            } else {
                this.perTryTimeout_ = null;
                this.perTryTimeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getPerTryTimeoutBuilder() {
            onChanged();
            return getPerTryTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public DurationOrBuilder getPerTryTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.perTryTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.perTryTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getPerTryTimeoutFieldBuilder() {
            if (this.perTryTimeoutBuilder_ == null) {
                this.perTryTimeoutBuilder_ = new SingleFieldBuilderV3<>(getPerTryTimeout(), getParentForChildren(), isClean());
                this.perTryTimeout_ = null;
            }
            return this.perTryTimeoutBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryPriority getRetryPriority() {
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV3 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RetryPriority retryPriority = this.retryPriority_;
            return retryPriority == null ? RetryPriority.getDefaultInstance() : retryPriority;
        }

        public Builder setRetryPriority(RetryPriority retryPriority) {
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV3 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryPriority.getClass();
                this.retryPriority_ = retryPriority;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(retryPriority);
            }
            return this;
        }

        public Builder setRetryPriority(RetryPriority.Builder builder) {
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV3 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryPriority_ = builder.m28870build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m28870build());
            }
            return this;
        }

        public Builder mergeRetryPriority(RetryPriority retryPriority) {
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV3 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV3 == null) {
                RetryPriority retryPriority2 = this.retryPriority_;
                if (retryPriority2 != null) {
                    this.retryPriority_ = RetryPriority.newBuilder(retryPriority2).mergeFrom(retryPriority).m28872buildPartial();
                } else {
                    this.retryPriority_ = retryPriority;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(retryPriority);
            }
            return this;
        }

        public Builder clearRetryPriority() {
            if (this.retryPriorityBuilder_ == null) {
                this.retryPriority_ = null;
                onChanged();
            } else {
                this.retryPriority_ = null;
                this.retryPriorityBuilder_ = null;
            }
            return this;
        }

        public RetryPriority.Builder getRetryPriorityBuilder() {
            onChanged();
            return getRetryPriorityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryPriorityOrBuilder getRetryPriorityOrBuilder() {
            SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> singleFieldBuilderV3 = this.retryPriorityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RetryPriorityOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RetryPriority retryPriority = this.retryPriority_;
            return retryPriority == null ? RetryPriority.getDefaultInstance() : retryPriority;
        }

        private SingleFieldBuilderV3<RetryPriority, RetryPriority.Builder, RetryPriorityOrBuilder> getRetryPriorityFieldBuilder() {
            if (this.retryPriorityBuilder_ == null) {
                this.retryPriorityBuilder_ = new SingleFieldBuilderV3<>(getRetryPriority(), getParentForChildren(), isClean());
                this.retryPriority_ = null;
            }
            return this.retryPriorityBuilder_;
        }

        private void ensureRetryHostPredicateIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.retryHostPredicate_ = new ArrayList(this.retryHostPredicate_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<RetryHostPredicate> getRetryHostPredicateList() {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.retryHostPredicate_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public int getRetryHostPredicateCount() {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retryHostPredicate_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryHostPredicate getRetryHostPredicate(int i) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retryHostPredicate_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRetryHostPredicate(int i, RetryHostPredicate retryHostPredicate) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                retryHostPredicate.getClass();
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.set(i, retryHostPredicate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, retryHostPredicate);
            }
            return this;
        }

        public Builder setRetryHostPredicate(int i, RetryHostPredicate.Builder builder) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.set(i, builder.m28824build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m28824build());
            }
            return this;
        }

        public Builder addRetryHostPredicate(RetryHostPredicate retryHostPredicate) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                retryHostPredicate.getClass();
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.add(retryHostPredicate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(retryHostPredicate);
            }
            return this;
        }

        public Builder addRetryHostPredicate(int i, RetryHostPredicate retryHostPredicate) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                retryHostPredicate.getClass();
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.add(i, retryHostPredicate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, retryHostPredicate);
            }
            return this;
        }

        public Builder addRetryHostPredicate(RetryHostPredicate.Builder builder) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.add(builder.m28824build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m28824build());
            }
            return this;
        }

        public Builder addRetryHostPredicate(int i, RetryHostPredicate.Builder builder) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.add(i, builder.m28824build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m28824build());
            }
            return this;
        }

        public Builder addAllRetryHostPredicate(Iterable<? extends RetryHostPredicate> iterable) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetryHostPredicateIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.retryHostPredicate_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRetryHostPredicate() {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.retryHostPredicate_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRetryHostPredicate(int i) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetryHostPredicateIsMutable();
                this.retryHostPredicate_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public RetryHostPredicate.Builder getRetryHostPredicateBuilder(int i) {
            return getRetryHostPredicateFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryHostPredicateOrBuilder getRetryHostPredicateOrBuilder(int i) {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retryHostPredicate_.get(i);
            }
            return (RetryHostPredicateOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<? extends RetryHostPredicateOrBuilder> getRetryHostPredicateOrBuilderList() {
            RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> repeatedFieldBuilderV3 = this.retryHostPredicateBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.retryHostPredicate_);
        }

        public RetryHostPredicate.Builder addRetryHostPredicateBuilder() {
            return getRetryHostPredicateFieldBuilder().addBuilder(RetryHostPredicate.getDefaultInstance());
        }

        public RetryHostPredicate.Builder addRetryHostPredicateBuilder(int i) {
            return getRetryHostPredicateFieldBuilder().addBuilder(i, RetryHostPredicate.getDefaultInstance());
        }

        public List<RetryHostPredicate.Builder> getRetryHostPredicateBuilderList() {
            return getRetryHostPredicateFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<RetryHostPredicate, RetryHostPredicate.Builder, RetryHostPredicateOrBuilder> getRetryHostPredicateFieldBuilder() {
            if (this.retryHostPredicateBuilder_ == null) {
                this.retryHostPredicateBuilder_ = new RepeatedFieldBuilderV3<>(this.retryHostPredicate_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.retryHostPredicate_ = null;
            }
            return this.retryHostPredicateBuilder_;
        }

        public Builder clearHostSelectionRetryMaxAttempts() {
            this.hostSelectionRetryMaxAttempts_ = 0L;
            onChanged();
            return this;
        }

        private void ensureRetriableStatusCodesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.retriableStatusCodes_ = RetryPolicy.mutableCopy(this.retriableStatusCodes_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<Integer> getRetriableStatusCodesList() {
            return (this.bitField0_ & 2) != 0 ? Collections.unmodifiableList(this.retriableStatusCodes_) : this.retriableStatusCodes_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public int getRetriableStatusCodesCount() {
            return this.retriableStatusCodes_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public int getRetriableStatusCodes(int i) {
            return this.retriableStatusCodes_.getInt(i);
        }

        public Builder setRetriableStatusCodes(int i, int i2) {
            ensureRetriableStatusCodesIsMutable();
            this.retriableStatusCodes_.setInt(i, i2);
            onChanged();
            return this;
        }

        public Builder addRetriableStatusCodes(int i) {
            ensureRetriableStatusCodesIsMutable();
            this.retriableStatusCodes_.addInt(i);
            onChanged();
            return this;
        }

        public Builder addAllRetriableStatusCodes(Iterable<? extends Integer> iterable) {
            ensureRetriableStatusCodesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.retriableStatusCodes_);
            onChanged();
            return this;
        }

        public Builder clearRetriableStatusCodes() {
            this.retriableStatusCodes_ = RetryPolicy.emptyIntList();
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryBackOff getRetryBackOff() {
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV3 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RetryBackOff retryBackOff = this.retryBackOff_;
            return retryBackOff == null ? RetryBackOff.getDefaultInstance() : retryBackOff;
        }

        public Builder setRetryBackOff(RetryBackOff retryBackOff) {
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV3 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryBackOff.getClass();
                this.retryBackOff_ = retryBackOff;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(retryBackOff);
            }
            return this;
        }

        public Builder setRetryBackOff(RetryBackOff.Builder builder) {
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV3 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryBackOff_ = builder.m28778build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m28778build());
            }
            return this;
        }

        public Builder mergeRetryBackOff(RetryBackOff retryBackOff) {
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV3 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV3 == null) {
                RetryBackOff retryBackOff2 = this.retryBackOff_;
                if (retryBackOff2 != null) {
                    this.retryBackOff_ = RetryBackOff.newBuilder(retryBackOff2).mergeFrom(retryBackOff).m28780buildPartial();
                } else {
                    this.retryBackOff_ = retryBackOff;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(retryBackOff);
            }
            return this;
        }

        public Builder clearRetryBackOff() {
            if (this.retryBackOffBuilder_ == null) {
                this.retryBackOff_ = null;
                onChanged();
            } else {
                this.retryBackOff_ = null;
                this.retryBackOffBuilder_ = null;
            }
            return this;
        }

        public RetryBackOff.Builder getRetryBackOffBuilder() {
            onChanged();
            return getRetryBackOffFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public RetryBackOffOrBuilder getRetryBackOffOrBuilder() {
            SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> singleFieldBuilderV3 = this.retryBackOffBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RetryBackOffOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RetryBackOff retryBackOff = this.retryBackOff_;
            return retryBackOff == null ? RetryBackOff.getDefaultInstance() : retryBackOff;
        }

        private SingleFieldBuilderV3<RetryBackOff, RetryBackOff.Builder, RetryBackOffOrBuilder> getRetryBackOffFieldBuilder() {
            if (this.retryBackOffBuilder_ == null) {
                this.retryBackOffBuilder_ = new SingleFieldBuilderV3<>(getRetryBackOff(), getParentForChildren(), isClean());
                this.retryBackOff_ = null;
            }
            return this.retryBackOffBuilder_;
        }

        private void ensureRetriableHeadersIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.retriableHeaders_ = new ArrayList(this.retriableHeaders_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<HeaderMatcher> getRetriableHeadersList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.retriableHeaders_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public int getRetriableHeadersCount() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableHeaders_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public HeaderMatcher getRetriableHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableHeaders_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRetriableHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.set(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder setRetriableHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.set(i, builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m27995build());
            }
            return this;
        }

        public Builder addRetriableHeaders(HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.add(headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerMatcher);
            }
            return this;
        }

        public Builder addRetriableHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.add(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder addRetriableHeaders(HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.add(builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m27995build());
            }
            return this;
        }

        public Builder addRetriableHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.add(i, builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m27995build());
            }
            return this;
        }

        public Builder addAllRetriableHeaders(Iterable<? extends HeaderMatcher> iterable) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableHeadersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.retriableHeaders_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRetriableHeaders() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.retriableHeaders_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRetriableHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableHeadersIsMutable();
                this.retriableHeaders_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderMatcher.Builder getRetriableHeadersBuilder(int i) {
            return getRetriableHeadersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public HeaderMatcherOrBuilder getRetriableHeadersOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableHeaders_.get(i);
            }
            return (HeaderMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<? extends HeaderMatcherOrBuilder> getRetriableHeadersOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableHeadersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.retriableHeaders_);
        }

        public HeaderMatcher.Builder addRetriableHeadersBuilder() {
            return getRetriableHeadersFieldBuilder().addBuilder(HeaderMatcher.getDefaultInstance());
        }

        public HeaderMatcher.Builder addRetriableHeadersBuilder(int i) {
            return getRetriableHeadersFieldBuilder().addBuilder(i, HeaderMatcher.getDefaultInstance());
        }

        public List<HeaderMatcher.Builder> getRetriableHeadersBuilderList() {
            return getRetriableHeadersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> getRetriableHeadersFieldBuilder() {
            if (this.retriableHeadersBuilder_ == null) {
                this.retriableHeadersBuilder_ = new RepeatedFieldBuilderV3<>(this.retriableHeaders_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.retriableHeaders_ = null;
            }
            return this.retriableHeadersBuilder_;
        }

        private void ensureRetriableRequestHeadersIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.retriableRequestHeaders_ = new ArrayList(this.retriableRequestHeaders_);
                this.bitField0_ |= 8;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<HeaderMatcher> getRetriableRequestHeadersList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.retriableRequestHeaders_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public int getRetriableRequestHeadersCount() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableRequestHeaders_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public HeaderMatcher getRetriableRequestHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableRequestHeaders_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRetriableRequestHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.set(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder setRetriableRequestHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.set(i, builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m27995build());
            }
            return this;
        }

        public Builder addRetriableRequestHeaders(HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.add(headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerMatcher);
            }
            return this;
        }

        public Builder addRetriableRequestHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.add(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder addRetriableRequestHeaders(HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.add(builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m27995build());
            }
            return this;
        }

        public Builder addRetriableRequestHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.add(i, builder.m27995build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m27995build());
            }
            return this;
        }

        public Builder addAllRetriableRequestHeaders(Iterable<? extends HeaderMatcher> iterable) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableRequestHeadersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.retriableRequestHeaders_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRetriableRequestHeaders() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.retriableRequestHeaders_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRetriableRequestHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRetriableRequestHeadersIsMutable();
                this.retriableRequestHeaders_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderMatcher.Builder getRetriableRequestHeadersBuilder(int i) {
            return getRetriableRequestHeadersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public HeaderMatcherOrBuilder getRetriableRequestHeadersOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.retriableRequestHeaders_.get(i);
            }
            return (HeaderMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RetryPolicyOrBuilder
        public List<? extends HeaderMatcherOrBuilder> getRetriableRequestHeadersOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.retriableRequestHeadersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.retriableRequestHeaders_);
        }

        public HeaderMatcher.Builder addRetriableRequestHeadersBuilder() {
            return getRetriableRequestHeadersFieldBuilder().addBuilder(HeaderMatcher.getDefaultInstance());
        }

        public HeaderMatcher.Builder addRetriableRequestHeadersBuilder(int i) {
            return getRetriableRequestHeadersFieldBuilder().addBuilder(i, HeaderMatcher.getDefaultInstance());
        }

        public List<HeaderMatcher.Builder> getRetriableRequestHeadersBuilderList() {
            return getRetriableRequestHeadersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> getRetriableRequestHeadersFieldBuilder() {
            if (this.retriableRequestHeadersBuilder_ == null) {
                this.retriableRequestHeadersBuilder_ = new RepeatedFieldBuilderV3<>(this.retriableRequestHeaders_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                this.retriableRequestHeaders_ = null;
            }
            return this.retriableRequestHeadersBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m28766setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m28760mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
