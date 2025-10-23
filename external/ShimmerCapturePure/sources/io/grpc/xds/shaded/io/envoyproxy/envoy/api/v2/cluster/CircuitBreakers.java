package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RoutingPriority;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.Percent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.PercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class CircuitBreakers extends GeneratedMessageV3 implements CircuitBreakersOrBuilder {
    public static final int THRESHOLDS_FIELD_NUMBER = 1;
    private static final CircuitBreakers DEFAULT_INSTANCE = new CircuitBreakers();
    private static final Parser<CircuitBreakers> PARSER = new AbstractParser<CircuitBreakers>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CircuitBreakers m14029parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CircuitBreakers(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Thresholds> thresholds_;

    private CircuitBreakers(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CircuitBreakers() {
        this.memoizedIsInitialized = (byte) -1;
        this.thresholds_ = Collections.emptyList();
    }

    private CircuitBreakers(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.thresholds_ = new ArrayList();
                                z2 |= true;
                            }
                            this.thresholds_.add(codedInputStream.readMessage(Thresholds.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.thresholds_ = Collections.unmodifiableList(this.thresholds_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static CircuitBreakers getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CircuitBreakers> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_descriptor;
    }

    public static CircuitBreakers parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(byteBuffer);
    }

    public static CircuitBreakers parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CircuitBreakers parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(byteString);
    }

    public static CircuitBreakers parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CircuitBreakers parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(bArr);
    }

    public static CircuitBreakers parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CircuitBreakers) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CircuitBreakers parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CircuitBreakers parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CircuitBreakers parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CircuitBreakers parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CircuitBreakers parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CircuitBreakers parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14027toBuilder();
    }

    public static Builder newBuilder(CircuitBreakers circuitBreakers) {
        return DEFAULT_INSTANCE.m14027toBuilder().mergeFrom(circuitBreakers);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CircuitBreakers m14022getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CircuitBreakers> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
    public List<Thresholds> getThresholdsList() {
        return this.thresholds_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
    public List<? extends ThresholdsOrBuilder> getThresholdsOrBuilderList() {
        return this.thresholds_;
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
        return new CircuitBreakers();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_fieldAccessorTable.ensureFieldAccessorsInitialized(CircuitBreakers.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
    public int getThresholdsCount() {
        return this.thresholds_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
    public Thresholds getThresholds(int i) {
        return this.thresholds_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
    public ThresholdsOrBuilder getThresholdsOrBuilder(int i) {
        return this.thresholds_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.thresholds_.size(); i++) {
            codedOutputStream.writeMessage(1, this.thresholds_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.thresholds_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.thresholds_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CircuitBreakers)) {
            return super.equals(obj);
        }
        CircuitBreakers circuitBreakers = (CircuitBreakers) obj;
        return getThresholdsList().equals(circuitBreakers.getThresholdsList()) && this.unknownFields.equals(circuitBreakers.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getThresholdsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getThresholdsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14024newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14027toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface ThresholdsOrBuilder extends MessageOrBuilder {
        UInt32Value getMaxConnectionPools();

        UInt32ValueOrBuilder getMaxConnectionPoolsOrBuilder();

        UInt32Value getMaxConnections();

        UInt32ValueOrBuilder getMaxConnectionsOrBuilder();

        UInt32Value getMaxPendingRequests();

        UInt32ValueOrBuilder getMaxPendingRequestsOrBuilder();

        UInt32Value getMaxRequests();

        UInt32ValueOrBuilder getMaxRequestsOrBuilder();

        UInt32Value getMaxRetries();

        UInt32ValueOrBuilder getMaxRetriesOrBuilder();

        RoutingPriority getPriority();

        int getPriorityValue();

        Thresholds.RetryBudget getRetryBudget();

        Thresholds.RetryBudgetOrBuilder getRetryBudgetOrBuilder();

        boolean getTrackRemaining();

        boolean hasMaxConnectionPools();

        boolean hasMaxConnections();

        boolean hasMaxPendingRequests();

        boolean hasMaxRequests();

        boolean hasMaxRetries();

        boolean hasRetryBudget();
    }

    public static final class Thresholds extends GeneratedMessageV3 implements ThresholdsOrBuilder {
        public static final int MAX_CONNECTIONS_FIELD_NUMBER = 2;
        public static final int MAX_CONNECTION_POOLS_FIELD_NUMBER = 7;
        public static final int MAX_PENDING_REQUESTS_FIELD_NUMBER = 3;
        public static final int MAX_REQUESTS_FIELD_NUMBER = 4;
        public static final int MAX_RETRIES_FIELD_NUMBER = 5;
        public static final int PRIORITY_FIELD_NUMBER = 1;
        public static final int RETRY_BUDGET_FIELD_NUMBER = 8;
        public static final int TRACK_REMAINING_FIELD_NUMBER = 6;
        private static final long serialVersionUID = 0;
        private static final Thresholds DEFAULT_INSTANCE = new Thresholds();
        private static final Parser<Thresholds> PARSER = new AbstractParser<Thresholds>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Thresholds m14075parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Thresholds(codedInputStream, extensionRegistryLite);
            }
        };
        private UInt32Value maxConnectionPools_;
        private UInt32Value maxConnections_;
        private UInt32Value maxPendingRequests_;
        private UInt32Value maxRequests_;
        private UInt32Value maxRetries_;
        private byte memoizedIsInitialized;
        private int priority_;
        private RetryBudget retryBudget_;
        private boolean trackRemaining_;

        private Thresholds(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Thresholds() {
            this.memoizedIsInitialized = (byte) -1;
            this.priority_ = 0;
        }

        private Thresholds(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            UInt32Value.Builder builderM14119toBuilder;
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
                                if (tag != 8) {
                                    if (tag == 18) {
                                        UInt32Value uInt32Value = this.maxConnections_;
                                        builderM14119toBuilder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                                        UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.maxConnections_ = message;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(message);
                                            this.maxConnections_ = builderM14119toBuilder.buildPartial();
                                        }
                                    } else if (tag == 26) {
                                        UInt32Value uInt32Value2 = this.maxPendingRequests_;
                                        builderM14119toBuilder = uInt32Value2 != null ? uInt32Value2.toBuilder() : null;
                                        UInt32Value message2 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.maxPendingRequests_ = message2;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(message2);
                                            this.maxPendingRequests_ = builderM14119toBuilder.buildPartial();
                                        }
                                    } else if (tag == 34) {
                                        UInt32Value uInt32Value3 = this.maxRequests_;
                                        builderM14119toBuilder = uInt32Value3 != null ? uInt32Value3.toBuilder() : null;
                                        UInt32Value message3 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.maxRequests_ = message3;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(message3);
                                            this.maxRequests_ = builderM14119toBuilder.buildPartial();
                                        }
                                    } else if (tag == 42) {
                                        UInt32Value uInt32Value4 = this.maxRetries_;
                                        builderM14119toBuilder = uInt32Value4 != null ? uInt32Value4.toBuilder() : null;
                                        UInt32Value message4 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.maxRetries_ = message4;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(message4);
                                            this.maxRetries_ = builderM14119toBuilder.buildPartial();
                                        }
                                    } else if (tag == 48) {
                                        this.trackRemaining_ = codedInputStream.readBool();
                                    } else if (tag == 58) {
                                        UInt32Value uInt32Value5 = this.maxConnectionPools_;
                                        builderM14119toBuilder = uInt32Value5 != null ? uInt32Value5.toBuilder() : null;
                                        UInt32Value message5 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.maxConnectionPools_ = message5;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(message5);
                                            this.maxConnectionPools_ = builderM14119toBuilder.buildPartial();
                                        }
                                    } else if (tag == 66) {
                                        RetryBudget retryBudget = this.retryBudget_;
                                        builderM14119toBuilder = retryBudget != null ? retryBudget.m14119toBuilder() : null;
                                        RetryBudget retryBudget2 = (RetryBudget) codedInputStream.readMessage(RetryBudget.parser(), extensionRegistryLite);
                                        this.retryBudget_ = retryBudget2;
                                        if (builderM14119toBuilder != null) {
                                            builderM14119toBuilder.mergeFrom(retryBudget2);
                                            this.retryBudget_ = builderM14119toBuilder.m14126buildPartial();
                                        }
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    this.priority_ = codedInputStream.readEnum();
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

        public static Thresholds getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Thresholds> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_descriptor;
        }

        public static Thresholds parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(byteBuffer);
        }

        public static Thresholds parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Thresholds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(byteString);
        }

        public static Thresholds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Thresholds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(bArr);
        }

        public static Thresholds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Thresholds) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Thresholds parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Thresholds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Thresholds parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Thresholds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Thresholds parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Thresholds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m14073toBuilder();
        }

        public static Builder newBuilder(Thresholds thresholds) {
            return DEFAULT_INSTANCE.m14073toBuilder().mergeFrom(thresholds);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Thresholds m14068getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Thresholds> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public int getPriorityValue() {
            return this.priority_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean getTrackRemaining() {
            return this.trackRemaining_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasMaxConnectionPools() {
            return this.maxConnectionPools_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasMaxConnections() {
            return this.maxConnections_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasMaxPendingRequests() {
            return this.maxPendingRequests_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasMaxRequests() {
            return this.maxRequests_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasMaxRetries() {
            return this.maxRetries_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public boolean hasRetryBudget() {
            return this.retryBudget_ != null;
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
            return new Thresholds();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_fieldAccessorTable.ensureFieldAccessorsInitialized(Thresholds.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public RoutingPriority getPriority() {
            RoutingPriority routingPriorityValueOf = RoutingPriority.valueOf(this.priority_);
            return routingPriorityValueOf == null ? RoutingPriority.UNRECOGNIZED : routingPriorityValueOf;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32Value getMaxConnections() {
            UInt32Value uInt32Value = this.maxConnections_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32ValueOrBuilder getMaxConnectionsOrBuilder() {
            return getMaxConnections();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32Value getMaxPendingRequests() {
            UInt32Value uInt32Value = this.maxPendingRequests_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32ValueOrBuilder getMaxPendingRequestsOrBuilder() {
            return getMaxPendingRequests();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32Value getMaxRequests() {
            UInt32Value uInt32Value = this.maxRequests_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32ValueOrBuilder getMaxRequestsOrBuilder() {
            return getMaxRequests();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32Value getMaxRetries() {
            UInt32Value uInt32Value = this.maxRetries_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32ValueOrBuilder getMaxRetriesOrBuilder() {
            return getMaxRetries();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public RetryBudget getRetryBudget() {
            RetryBudget retryBudget = this.retryBudget_;
            return retryBudget == null ? RetryBudget.getDefaultInstance() : retryBudget;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public RetryBudgetOrBuilder getRetryBudgetOrBuilder() {
            return getRetryBudget();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32Value getMaxConnectionPools() {
            UInt32Value uInt32Value = this.maxConnectionPools_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
        public UInt32ValueOrBuilder getMaxConnectionPoolsOrBuilder() {
            return getMaxConnectionPools();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.priority_ != RoutingPriority.DEFAULT.getNumber()) {
                codedOutputStream.writeEnum(1, this.priority_);
            }
            if (this.maxConnections_ != null) {
                codedOutputStream.writeMessage(2, getMaxConnections());
            }
            if (this.maxPendingRequests_ != null) {
                codedOutputStream.writeMessage(3, getMaxPendingRequests());
            }
            if (this.maxRequests_ != null) {
                codedOutputStream.writeMessage(4, getMaxRequests());
            }
            if (this.maxRetries_ != null) {
                codedOutputStream.writeMessage(5, getMaxRetries());
            }
            boolean z = this.trackRemaining_;
            if (z) {
                codedOutputStream.writeBool(6, z);
            }
            if (this.maxConnectionPools_ != null) {
                codedOutputStream.writeMessage(7, getMaxConnectionPools());
            }
            if (this.retryBudget_ != null) {
                codedOutputStream.writeMessage(8, getRetryBudget());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = this.priority_ != RoutingPriority.DEFAULT.getNumber() ? CodedOutputStream.computeEnumSize(1, this.priority_) : 0;
            if (this.maxConnections_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(2, getMaxConnections());
            }
            if (this.maxPendingRequests_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(3, getMaxPendingRequests());
            }
            if (this.maxRequests_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(4, getMaxRequests());
            }
            if (this.maxRetries_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(5, getMaxRetries());
            }
            boolean z = this.trackRemaining_;
            if (z) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(6, z);
            }
            if (this.maxConnectionPools_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(7, getMaxConnectionPools());
            }
            if (this.retryBudget_ != null) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(8, getRetryBudget());
            }
            int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Thresholds)) {
                return super.equals(obj);
            }
            Thresholds thresholds = (Thresholds) obj;
            if (this.priority_ != thresholds.priority_ || hasMaxConnections() != thresholds.hasMaxConnections()) {
                return false;
            }
            if ((hasMaxConnections() && !getMaxConnections().equals(thresholds.getMaxConnections())) || hasMaxPendingRequests() != thresholds.hasMaxPendingRequests()) {
                return false;
            }
            if ((hasMaxPendingRequests() && !getMaxPendingRequests().equals(thresholds.getMaxPendingRequests())) || hasMaxRequests() != thresholds.hasMaxRequests()) {
                return false;
            }
            if ((hasMaxRequests() && !getMaxRequests().equals(thresholds.getMaxRequests())) || hasMaxRetries() != thresholds.hasMaxRetries()) {
                return false;
            }
            if ((hasMaxRetries() && !getMaxRetries().equals(thresholds.getMaxRetries())) || hasRetryBudget() != thresholds.hasRetryBudget()) {
                return false;
            }
            if ((!hasRetryBudget() || getRetryBudget().equals(thresholds.getRetryBudget())) && getTrackRemaining() == thresholds.getTrackRemaining() && hasMaxConnectionPools() == thresholds.hasMaxConnectionPools()) {
                return (!hasMaxConnectionPools() || getMaxConnectionPools().equals(thresholds.getMaxConnectionPools())) && this.unknownFields.equals(thresholds.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.priority_;
            if (hasMaxConnections()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getMaxConnections().hashCode();
            }
            if (hasMaxPendingRequests()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getMaxPendingRequests().hashCode();
            }
            if (hasMaxRequests()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getMaxRequests().hashCode();
            }
            if (hasMaxRetries()) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + getMaxRetries().hashCode();
            }
            if (hasRetryBudget()) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getRetryBudget().hashCode();
            }
            int iHashBoolean = (((iHashCode * 37) + 6) * 53) + Internal.hashBoolean(getTrackRemaining());
            if (hasMaxConnectionPools()) {
                iHashBoolean = (((iHashBoolean * 37) + 7) * 53) + getMaxConnectionPools().hashCode();
            }
            int iHashCode2 = (iHashBoolean * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14070newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14073toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface RetryBudgetOrBuilder extends MessageOrBuilder {
            Percent getBudgetPercent();

            PercentOrBuilder getBudgetPercentOrBuilder();

            UInt32Value getMinRetryConcurrency();

            UInt32ValueOrBuilder getMinRetryConcurrencyOrBuilder();

            boolean hasBudgetPercent();

            boolean hasMinRetryConcurrency();
        }

        public static final class RetryBudget extends GeneratedMessageV3 implements RetryBudgetOrBuilder {
            public static final int BUDGET_PERCENT_FIELD_NUMBER = 1;
            public static final int MIN_RETRY_CONCURRENCY_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private static final RetryBudget DEFAULT_INSTANCE = new RetryBudget();
            private static final Parser<RetryBudget> PARSER = new AbstractParser<RetryBudget>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public RetryBudget m14121parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new RetryBudget(codedInputStream, extensionRegistryLite);
                }
            };
            private Percent budgetPercent_;
            private byte memoizedIsInitialized;
            private UInt32Value minRetryConcurrency_;

            private RetryBudget(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private RetryBudget() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private RetryBudget(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Percent.Builder builder;
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
                                    Percent percent = this.budgetPercent_;
                                    builder = percent != null ? percent.m32998toBuilder() : null;
                                    Percent percent2 = (Percent) codedInputStream.readMessage(Percent.parser(), extensionRegistryLite);
                                    this.budgetPercent_ = percent2;
                                    if (builder != null) {
                                        builder.mergeFrom(percent2);
                                        this.budgetPercent_ = builder.m33005buildPartial();
                                    }
                                } else if (tag == 18) {
                                    UInt32Value uInt32Value = this.minRetryConcurrency_;
                                    builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                                    UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                    this.minRetryConcurrency_ = message;
                                    if (builder != null) {
                                        builder.mergeFrom(message);
                                        this.minRetryConcurrency_ = builder.buildPartial();
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

            public static RetryBudget getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RetryBudget> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_descriptor;
            }

            public static RetryBudget parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(byteBuffer);
            }

            public static RetryBudget parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static RetryBudget parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(byteString);
            }

            public static RetryBudget parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static RetryBudget parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(bArr);
            }

            public static RetryBudget parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RetryBudget) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static RetryBudget parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static RetryBudget parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static RetryBudget parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static RetryBudget parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static RetryBudget parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static RetryBudget parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m14119toBuilder();
            }

            public static Builder newBuilder(RetryBudget retryBudget) {
                return DEFAULT_INSTANCE.m14119toBuilder().mergeFrom(retryBudget);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RetryBudget m14114getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<RetryBudget> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public boolean hasBudgetPercent() {
                return this.budgetPercent_ != null;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public boolean hasMinRetryConcurrency() {
                return this.minRetryConcurrency_ != null;
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
                return new RetryBudget();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryBudget.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public Percent getBudgetPercent() {
                Percent percent = this.budgetPercent_;
                return percent == null ? Percent.getDefaultInstance() : percent;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public PercentOrBuilder getBudgetPercentOrBuilder() {
                return getBudgetPercent();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public UInt32Value getMinRetryConcurrency() {
                UInt32Value uInt32Value = this.minRetryConcurrency_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
            public UInt32ValueOrBuilder getMinRetryConcurrencyOrBuilder() {
                return getMinRetryConcurrency();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.budgetPercent_ != null) {
                    codedOutputStream.writeMessage(1, getBudgetPercent());
                }
                if (this.minRetryConcurrency_ != null) {
                    codedOutputStream.writeMessage(2, getMinRetryConcurrency());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeMessageSize = this.budgetPercent_ != null ? CodedOutputStream.computeMessageSize(1, getBudgetPercent()) : 0;
                if (this.minRetryConcurrency_ != null) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getMinRetryConcurrency());
                }
                int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof RetryBudget)) {
                    return super.equals(obj);
                }
                RetryBudget retryBudget = (RetryBudget) obj;
                if (hasBudgetPercent() != retryBudget.hasBudgetPercent()) {
                    return false;
                }
                if ((!hasBudgetPercent() || getBudgetPercent().equals(retryBudget.getBudgetPercent())) && hasMinRetryConcurrency() == retryBudget.hasMinRetryConcurrency()) {
                    return (!hasMinRetryConcurrency() || getMinRetryConcurrency().equals(retryBudget.getMinRetryConcurrency())) && this.unknownFields.equals(retryBudget.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasBudgetPercent()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getBudgetPercent().hashCode();
                }
                if (hasMinRetryConcurrency()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getMinRetryConcurrency().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14116newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14119toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryBudgetOrBuilder {
                private SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> budgetPercentBuilder_;
                private Percent budgetPercent_;
                private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> minRetryConcurrencyBuilder_;
                private UInt32Value minRetryConcurrency_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public boolean hasBudgetPercent() {
                    return (this.budgetPercentBuilder_ == null && this.budgetPercent_ == null) ? false : true;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public boolean hasMinRetryConcurrency() {
                    return (this.minRetryConcurrencyBuilder_ == null && this.minRetryConcurrency_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryBudget.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = RetryBudget.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14130clear() {
                    super.clear();
                    if (this.budgetPercentBuilder_ == null) {
                        this.budgetPercent_ = null;
                    } else {
                        this.budgetPercent_ = null;
                        this.budgetPercentBuilder_ = null;
                    }
                    if (this.minRetryConcurrencyBuilder_ == null) {
                        this.minRetryConcurrency_ = null;
                    } else {
                        this.minRetryConcurrency_ = null;
                        this.minRetryConcurrencyBuilder_ = null;
                    }
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public RetryBudget m14143getDefaultInstanceForType() {
                    return RetryBudget.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public RetryBudget m14124build() throws UninitializedMessageException {
                    RetryBudget retryBudgetM14126buildPartial = m14126buildPartial();
                    if (retryBudgetM14126buildPartial.isInitialized()) {
                        return retryBudgetM14126buildPartial;
                    }
                    throw newUninitializedMessageException(retryBudgetM14126buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public RetryBudget m14126buildPartial() {
                    RetryBudget retryBudget = new RetryBudget(this);
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        retryBudget.budgetPercent_ = this.budgetPercent_;
                    } else {
                        retryBudget.budgetPercent_ = singleFieldBuilderV3.build();
                    }
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV32 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        retryBudget.minRetryConcurrency_ = this.minRetryConcurrency_;
                    } else {
                        retryBudget.minRetryConcurrency_ = singleFieldBuilderV32.build();
                    }
                    onBuilt();
                    return retryBudget;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14142clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14154setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14132clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14135clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14156setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14122addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m14147mergeFrom(Message message) {
                    if (message instanceof RetryBudget) {
                        return mergeFrom((RetryBudget) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(RetryBudget retryBudget) {
                    if (retryBudget == RetryBudget.getDefaultInstance()) {
                        return this;
                    }
                    if (retryBudget.hasBudgetPercent()) {
                        mergeBudgetPercent(retryBudget.getBudgetPercent());
                    }
                    if (retryBudget.hasMinRetryConcurrency()) {
                        mergeMinRetryConcurrency(retryBudget.getMinRetryConcurrency());
                    }
                    m14152mergeUnknownFields(retryBudget.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget.Builder m14148mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds$RetryBudget r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds$RetryBudget r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget.Builder.m14148mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds$RetryBudget$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public Percent getBudgetPercent() {
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    Percent percent = this.budgetPercent_;
                    return percent == null ? Percent.getDefaultInstance() : percent;
                }

                public Builder setBudgetPercent(Percent percent) {
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        percent.getClass();
                        this.budgetPercent_ = percent;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(percent);
                    }
                    return this;
                }

                public Builder setBudgetPercent(Percent.Builder builder) {
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.budgetPercent_ = builder.m33003build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m33003build());
                    }
                    return this;
                }

                public Builder mergeBudgetPercent(Percent percent) {
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Percent percent2 = this.budgetPercent_;
                        if (percent2 != null) {
                            this.budgetPercent_ = Percent.newBuilder(percent2).mergeFrom(percent).m33005buildPartial();
                        } else {
                            this.budgetPercent_ = percent;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(percent);
                    }
                    return this;
                }

                public Builder clearBudgetPercent() {
                    if (this.budgetPercentBuilder_ == null) {
                        this.budgetPercent_ = null;
                        onChanged();
                    } else {
                        this.budgetPercent_ = null;
                        this.budgetPercentBuilder_ = null;
                    }
                    return this;
                }

                public Percent.Builder getBudgetPercentBuilder() {
                    onChanged();
                    return getBudgetPercentFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public PercentOrBuilder getBudgetPercentOrBuilder() {
                    SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> singleFieldBuilderV3 = this.budgetPercentBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (PercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Percent percent = this.budgetPercent_;
                    return percent == null ? Percent.getDefaultInstance() : percent;
                }

                private SingleFieldBuilderV3<Percent, Percent.Builder, PercentOrBuilder> getBudgetPercentFieldBuilder() {
                    if (this.budgetPercentBuilder_ == null) {
                        this.budgetPercentBuilder_ = new SingleFieldBuilderV3<>(getBudgetPercent(), getParentForChildren(), isClean());
                        this.budgetPercent_ = null;
                    }
                    return this.budgetPercentBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public UInt32Value getMinRetryConcurrency() {
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    UInt32Value uInt32Value = this.minRetryConcurrency_;
                    return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
                }

                public Builder setMinRetryConcurrency(UInt32Value uInt32Value) {
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        uInt32Value.getClass();
                        this.minRetryConcurrency_ = uInt32Value;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(uInt32Value);
                    }
                    return this;
                }

                public Builder setMinRetryConcurrency(UInt32Value.Builder builder) {
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.minRetryConcurrency_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    return this;
                }

                public Builder mergeMinRetryConcurrency(UInt32Value uInt32Value) {
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        UInt32Value uInt32Value2 = this.minRetryConcurrency_;
                        if (uInt32Value2 != null) {
                            this.minRetryConcurrency_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                        } else {
                            this.minRetryConcurrency_ = uInt32Value;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(uInt32Value);
                    }
                    return this;
                }

                public Builder clearMinRetryConcurrency() {
                    if (this.minRetryConcurrencyBuilder_ == null) {
                        this.minRetryConcurrency_ = null;
                        onChanged();
                    } else {
                        this.minRetryConcurrency_ = null;
                        this.minRetryConcurrencyBuilder_ = null;
                    }
                    return this;
                }

                public UInt32Value.Builder getMinRetryConcurrencyBuilder() {
                    onChanged();
                    return getMinRetryConcurrencyFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudgetOrBuilder
                public UInt32ValueOrBuilder getMinRetryConcurrencyOrBuilder() {
                    SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.minRetryConcurrencyBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    UInt32Value uInt32Value = this.minRetryConcurrency_;
                    return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
                }

                private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMinRetryConcurrencyFieldBuilder() {
                    if (this.minRetryConcurrencyBuilder_ == null) {
                        this.minRetryConcurrencyBuilder_ = new SingleFieldBuilderV3<>(getMinRetryConcurrency(), getParentForChildren(), isClean());
                        this.minRetryConcurrency_ = null;
                    }
                    return this.minRetryConcurrencyBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m14158setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m14152mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ThresholdsOrBuilder {
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxConnectionPoolsBuilder_;
            private UInt32Value maxConnectionPools_;
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxConnectionsBuilder_;
            private UInt32Value maxConnections_;
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxPendingRequestsBuilder_;
            private UInt32Value maxPendingRequests_;
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxRequestsBuilder_;
            private UInt32Value maxRequests_;
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxRetriesBuilder_;
            private UInt32Value maxRetries_;
            private int priority_;
            private SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> retryBudgetBuilder_;
            private RetryBudget retryBudget_;
            private boolean trackRemaining_;

            private Builder() {
                this.priority_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.priority_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public int getPriorityValue() {
                return this.priority_;
            }

            public Builder setPriorityValue(int i) {
                this.priority_ = i;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean getTrackRemaining() {
                return this.trackRemaining_;
            }

            public Builder setTrackRemaining(boolean z) {
                this.trackRemaining_ = z;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasMaxConnectionPools() {
                return (this.maxConnectionPoolsBuilder_ == null && this.maxConnectionPools_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasMaxConnections() {
                return (this.maxConnectionsBuilder_ == null && this.maxConnections_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasMaxPendingRequests() {
                return (this.maxPendingRequestsBuilder_ == null && this.maxPendingRequests_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasMaxRequests() {
                return (this.maxRequestsBuilder_ == null && this.maxRequests_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasMaxRetries() {
                return (this.maxRetriesBuilder_ == null && this.maxRetries_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public boolean hasRetryBudget() {
                return (this.retryBudgetBuilder_ == null && this.retryBudget_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_fieldAccessorTable.ensureFieldAccessorsInitialized(Thresholds.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Thresholds.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14084clear() {
                super.clear();
                this.priority_ = 0;
                if (this.maxConnectionsBuilder_ == null) {
                    this.maxConnections_ = null;
                } else {
                    this.maxConnections_ = null;
                    this.maxConnectionsBuilder_ = null;
                }
                if (this.maxPendingRequestsBuilder_ == null) {
                    this.maxPendingRequests_ = null;
                } else {
                    this.maxPendingRequests_ = null;
                    this.maxPendingRequestsBuilder_ = null;
                }
                if (this.maxRequestsBuilder_ == null) {
                    this.maxRequests_ = null;
                } else {
                    this.maxRequests_ = null;
                    this.maxRequestsBuilder_ = null;
                }
                if (this.maxRetriesBuilder_ == null) {
                    this.maxRetries_ = null;
                } else {
                    this.maxRetries_ = null;
                    this.maxRetriesBuilder_ = null;
                }
                if (this.retryBudgetBuilder_ == null) {
                    this.retryBudget_ = null;
                } else {
                    this.retryBudget_ = null;
                    this.retryBudgetBuilder_ = null;
                }
                this.trackRemaining_ = false;
                if (this.maxConnectionPoolsBuilder_ == null) {
                    this.maxConnectionPools_ = null;
                } else {
                    this.maxConnectionPools_ = null;
                    this.maxConnectionPoolsBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Thresholds m14097getDefaultInstanceForType() {
                return Thresholds.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Thresholds m14078build() throws UninitializedMessageException {
                Thresholds thresholdsM14080buildPartial = m14080buildPartial();
                if (thresholdsM14080buildPartial.isInitialized()) {
                    return thresholdsM14080buildPartial;
                }
                throw newUninitializedMessageException(thresholdsM14080buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Thresholds m14080buildPartial() {
                Thresholds thresholds = new Thresholds(this);
                thresholds.priority_ = this.priority_;
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    thresholds.maxConnections_ = this.maxConnections_;
                } else {
                    thresholds.maxConnections_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV32 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    thresholds.maxPendingRequests_ = this.maxPendingRequests_;
                } else {
                    thresholds.maxPendingRequests_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV33 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV33 == null) {
                    thresholds.maxRequests_ = this.maxRequests_;
                } else {
                    thresholds.maxRequests_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV34 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV34 == null) {
                    thresholds.maxRetries_ = this.maxRetries_;
                } else {
                    thresholds.maxRetries_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV35 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV35 == null) {
                    thresholds.retryBudget_ = this.retryBudget_;
                } else {
                    thresholds.retryBudget_ = singleFieldBuilderV35.build();
                }
                thresholds.trackRemaining_ = this.trackRemaining_;
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV36 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV36 == null) {
                    thresholds.maxConnectionPools_ = this.maxConnectionPools_;
                } else {
                    thresholds.maxConnectionPools_ = singleFieldBuilderV36.build();
                }
                onBuilt();
                return thresholds;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14096clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14108setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14086clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14089clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14110setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14076addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14101mergeFrom(Message message) {
                if (message instanceof Thresholds) {
                    return mergeFrom((Thresholds) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Thresholds thresholds) {
                if (thresholds == Thresholds.getDefaultInstance()) {
                    return this;
                }
                if (thresholds.priority_ != 0) {
                    setPriorityValue(thresholds.getPriorityValue());
                }
                if (thresholds.hasMaxConnections()) {
                    mergeMaxConnections(thresholds.getMaxConnections());
                }
                if (thresholds.hasMaxPendingRequests()) {
                    mergeMaxPendingRequests(thresholds.getMaxPendingRequests());
                }
                if (thresholds.hasMaxRequests()) {
                    mergeMaxRequests(thresholds.getMaxRequests());
                }
                if (thresholds.hasMaxRetries()) {
                    mergeMaxRetries(thresholds.getMaxRetries());
                }
                if (thresholds.hasRetryBudget()) {
                    mergeRetryBudget(thresholds.getRetryBudget());
                }
                if (thresholds.getTrackRemaining()) {
                    setTrackRemaining(thresholds.getTrackRemaining());
                }
                if (thresholds.hasMaxConnectionPools()) {
                    mergeMaxConnectionPools(thresholds.getMaxConnectionPools());
                }
                m14106mergeUnknownFields(thresholds.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.Builder m14102mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.access$2200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Thresholds.Builder.m14102mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Thresholds$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public RoutingPriority getPriority() {
                RoutingPriority routingPriorityValueOf = RoutingPriority.valueOf(this.priority_);
                return routingPriorityValueOf == null ? RoutingPriority.UNRECOGNIZED : routingPriorityValueOf;
            }

            public Builder setPriority(RoutingPriority routingPriority) {
                routingPriority.getClass();
                this.priority_ = routingPriority.getNumber();
                onChanged();
                return this;
            }

            public Builder clearPriority() {
                this.priority_ = 0;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32Value getMaxConnections() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.maxConnections_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setMaxConnections(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.maxConnections_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setMaxConnections(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxConnections_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxConnections(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.maxConnections_;
                    if (uInt32Value2 != null) {
                        this.maxConnections_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.maxConnections_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearMaxConnections() {
                if (this.maxConnectionsBuilder_ == null) {
                    this.maxConnections_ = null;
                    onChanged();
                } else {
                    this.maxConnections_ = null;
                    this.maxConnectionsBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getMaxConnectionsBuilder() {
                onChanged();
                return getMaxConnectionsFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32ValueOrBuilder getMaxConnectionsOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.maxConnections_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxConnectionsFieldBuilder() {
                if (this.maxConnectionsBuilder_ == null) {
                    this.maxConnectionsBuilder_ = new SingleFieldBuilderV3<>(getMaxConnections(), getParentForChildren(), isClean());
                    this.maxConnections_ = null;
                }
                return this.maxConnectionsBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32Value getMaxPendingRequests() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.maxPendingRequests_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setMaxPendingRequests(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.maxPendingRequests_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setMaxPendingRequests(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxPendingRequests_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxPendingRequests(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.maxPendingRequests_;
                    if (uInt32Value2 != null) {
                        this.maxPendingRequests_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.maxPendingRequests_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearMaxPendingRequests() {
                if (this.maxPendingRequestsBuilder_ == null) {
                    this.maxPendingRequests_ = null;
                    onChanged();
                } else {
                    this.maxPendingRequests_ = null;
                    this.maxPendingRequestsBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getMaxPendingRequestsBuilder() {
                onChanged();
                return getMaxPendingRequestsFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32ValueOrBuilder getMaxPendingRequestsOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxPendingRequestsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.maxPendingRequests_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxPendingRequestsFieldBuilder() {
                if (this.maxPendingRequestsBuilder_ == null) {
                    this.maxPendingRequestsBuilder_ = new SingleFieldBuilderV3<>(getMaxPendingRequests(), getParentForChildren(), isClean());
                    this.maxPendingRequests_ = null;
                }
                return this.maxPendingRequestsBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32Value getMaxRequests() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.maxRequests_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setMaxRequests(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.maxRequests_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setMaxRequests(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxRequests_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxRequests(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.maxRequests_;
                    if (uInt32Value2 != null) {
                        this.maxRequests_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.maxRequests_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearMaxRequests() {
                if (this.maxRequestsBuilder_ == null) {
                    this.maxRequests_ = null;
                    onChanged();
                } else {
                    this.maxRequests_ = null;
                    this.maxRequestsBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getMaxRequestsBuilder() {
                onChanged();
                return getMaxRequestsFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32ValueOrBuilder getMaxRequestsOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRequestsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.maxRequests_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxRequestsFieldBuilder() {
                if (this.maxRequestsBuilder_ == null) {
                    this.maxRequestsBuilder_ = new SingleFieldBuilderV3<>(getMaxRequests(), getParentForChildren(), isClean());
                    this.maxRequests_ = null;
                }
                return this.maxRequestsBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32Value getMaxRetries() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.maxRetries_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setMaxRetries(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.maxRetries_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setMaxRetries(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxRetries_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxRetries(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.maxRetries_;
                    if (uInt32Value2 != null) {
                        this.maxRetries_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.maxRetries_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearMaxRetries() {
                if (this.maxRetriesBuilder_ == null) {
                    this.maxRetries_ = null;
                    onChanged();
                } else {
                    this.maxRetries_ = null;
                    this.maxRetriesBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getMaxRetriesBuilder() {
                onChanged();
                return getMaxRetriesFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32ValueOrBuilder getMaxRetriesOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxRetriesBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.maxRetries_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxRetriesFieldBuilder() {
                if (this.maxRetriesBuilder_ == null) {
                    this.maxRetriesBuilder_ = new SingleFieldBuilderV3<>(getMaxRetries(), getParentForChildren(), isClean());
                    this.maxRetries_ = null;
                }
                return this.maxRetriesBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public RetryBudget getRetryBudget() {
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV3 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RetryBudget retryBudget = this.retryBudget_;
                return retryBudget == null ? RetryBudget.getDefaultInstance() : retryBudget;
            }

            public Builder setRetryBudget(RetryBudget retryBudget) {
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV3 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    retryBudget.getClass();
                    this.retryBudget_ = retryBudget;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(retryBudget);
                }
                return this;
            }

            public Builder setRetryBudget(RetryBudget.Builder builder) {
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV3 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.retryBudget_ = builder.m14124build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m14124build());
                }
                return this;
            }

            public Builder mergeRetryBudget(RetryBudget retryBudget) {
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV3 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RetryBudget retryBudget2 = this.retryBudget_;
                    if (retryBudget2 != null) {
                        this.retryBudget_ = RetryBudget.newBuilder(retryBudget2).mergeFrom(retryBudget).m14126buildPartial();
                    } else {
                        this.retryBudget_ = retryBudget;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(retryBudget);
                }
                return this;
            }

            public Builder clearRetryBudget() {
                if (this.retryBudgetBuilder_ == null) {
                    this.retryBudget_ = null;
                    onChanged();
                } else {
                    this.retryBudget_ = null;
                    this.retryBudgetBuilder_ = null;
                }
                return this;
            }

            public RetryBudget.Builder getRetryBudgetBuilder() {
                onChanged();
                return getRetryBudgetFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public RetryBudgetOrBuilder getRetryBudgetOrBuilder() {
                SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> singleFieldBuilderV3 = this.retryBudgetBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (RetryBudgetOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                RetryBudget retryBudget = this.retryBudget_;
                return retryBudget == null ? RetryBudget.getDefaultInstance() : retryBudget;
            }

            private SingleFieldBuilderV3<RetryBudget, RetryBudget.Builder, RetryBudgetOrBuilder> getRetryBudgetFieldBuilder() {
                if (this.retryBudgetBuilder_ == null) {
                    this.retryBudgetBuilder_ = new SingleFieldBuilderV3<>(getRetryBudget(), getParentForChildren(), isClean());
                    this.retryBudget_ = null;
                }
                return this.retryBudgetBuilder_;
            }

            public Builder clearTrackRemaining() {
                this.trackRemaining_ = false;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32Value getMaxConnectionPools() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.maxConnectionPools_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setMaxConnectionPools(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.maxConnectionPools_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setMaxConnectionPools(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.maxConnectionPools_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMaxConnectionPools(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.maxConnectionPools_;
                    if (uInt32Value2 != null) {
                        this.maxConnectionPools_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.maxConnectionPools_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearMaxConnectionPools() {
                if (this.maxConnectionPoolsBuilder_ == null) {
                    this.maxConnectionPools_ = null;
                    onChanged();
                } else {
                    this.maxConnectionPools_ = null;
                    this.maxConnectionPoolsBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getMaxConnectionPoolsBuilder() {
                onChanged();
                return getMaxConnectionPoolsFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.ThresholdsOrBuilder
            public UInt32ValueOrBuilder getMaxConnectionPoolsOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxConnectionPoolsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.maxConnectionPools_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxConnectionPoolsFieldBuilder() {
                if (this.maxConnectionPoolsBuilder_ == null) {
                    this.maxConnectionPoolsBuilder_ = new SingleFieldBuilderV3<>(getMaxConnectionPools(), getParentForChildren(), isClean());
                    this.maxConnectionPools_ = null;
                }
                return this.maxConnectionPoolsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m14112setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m14106mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CircuitBreakersOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> thresholdsBuilder_;
        private List<Thresholds> thresholds_;

        private Builder() {
            this.thresholds_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.thresholds_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_fieldAccessorTable.ensureFieldAccessorsInitialized(CircuitBreakers.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (CircuitBreakers.alwaysUseFieldBuilders) {
                getThresholdsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14038clear() {
            super.clear();
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.thresholds_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CircuitBreakerProto.internal_static_envoy_api_v2_cluster_CircuitBreakers_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CircuitBreakers m14051getDefaultInstanceForType() {
            return CircuitBreakers.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CircuitBreakers m14032build() throws UninitializedMessageException {
            CircuitBreakers circuitBreakersM14034buildPartial = m14034buildPartial();
            if (circuitBreakersM14034buildPartial.isInitialized()) {
                return circuitBreakersM14034buildPartial;
            }
            throw newUninitializedMessageException(circuitBreakersM14034buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CircuitBreakers m14034buildPartial() {
            CircuitBreakers circuitBreakers = new CircuitBreakers(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.thresholds_ = Collections.unmodifiableList(this.thresholds_);
                    this.bitField0_ &= -2;
                }
                circuitBreakers.thresholds_ = this.thresholds_;
            } else {
                circuitBreakers.thresholds_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return circuitBreakers;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14050clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14062setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14040clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14043clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14064setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14030addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14055mergeFrom(Message message) {
            if (message instanceof CircuitBreakers) {
                return mergeFrom((CircuitBreakers) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CircuitBreakers circuitBreakers) {
            if (circuitBreakers == CircuitBreakers.getDefaultInstance()) {
                return this;
            }
            if (this.thresholdsBuilder_ == null) {
                if (!circuitBreakers.thresholds_.isEmpty()) {
                    if (this.thresholds_.isEmpty()) {
                        this.thresholds_ = circuitBreakers.thresholds_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureThresholdsIsMutable();
                        this.thresholds_.addAll(circuitBreakers.thresholds_);
                    }
                    onChanged();
                }
            } else if (!circuitBreakers.thresholds_.isEmpty()) {
                if (!this.thresholdsBuilder_.isEmpty()) {
                    this.thresholdsBuilder_.addAllMessages(circuitBreakers.thresholds_);
                } else {
                    this.thresholdsBuilder_.dispose();
                    this.thresholdsBuilder_ = null;
                    this.thresholds_ = circuitBreakers.thresholds_;
                    this.bitField0_ &= -2;
                    this.thresholdsBuilder_ = CircuitBreakers.alwaysUseFieldBuilders ? getThresholdsFieldBuilder() : null;
                }
            }
            m14060mergeUnknownFields(circuitBreakers.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Builder m14056mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.access$3100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers.Builder.m14056mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers$Builder");
        }

        private void ensureThresholdsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.thresholds_ = new ArrayList(this.thresholds_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
        public List<Thresholds> getThresholdsList() {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.thresholds_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
        public int getThresholdsCount() {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.thresholds_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
        public Thresholds getThresholds(int i) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.thresholds_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setThresholds(int i, Thresholds thresholds) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                thresholds.getClass();
                ensureThresholdsIsMutable();
                this.thresholds_.set(i, thresholds);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, thresholds);
            }
            return this;
        }

        public Builder setThresholds(int i, Thresholds.Builder builder) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureThresholdsIsMutable();
                this.thresholds_.set(i, builder.m14078build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14078build());
            }
            return this;
        }

        public Builder addThresholds(Thresholds thresholds) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                thresholds.getClass();
                ensureThresholdsIsMutable();
                this.thresholds_.add(thresholds);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(thresholds);
            }
            return this;
        }

        public Builder addThresholds(int i, Thresholds thresholds) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                thresholds.getClass();
                ensureThresholdsIsMutable();
                this.thresholds_.add(i, thresholds);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, thresholds);
            }
            return this;
        }

        public Builder addThresholds(Thresholds.Builder builder) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureThresholdsIsMutable();
                this.thresholds_.add(builder.m14078build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14078build());
            }
            return this;
        }

        public Builder addThresholds(int i, Thresholds.Builder builder) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureThresholdsIsMutable();
                this.thresholds_.add(i, builder.m14078build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14078build());
            }
            return this;
        }

        public Builder addAllThresholds(Iterable<? extends Thresholds> iterable) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureThresholdsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.thresholds_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearThresholds() {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.thresholds_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeThresholds(int i) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureThresholdsIsMutable();
                this.thresholds_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Thresholds.Builder getThresholdsBuilder(int i) {
            return getThresholdsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
        public ThresholdsOrBuilder getThresholdsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.thresholds_.get(i);
            }
            return (ThresholdsOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakersOrBuilder
        public List<? extends ThresholdsOrBuilder> getThresholdsOrBuilderList() {
            RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> repeatedFieldBuilderV3 = this.thresholdsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.thresholds_);
        }

        public Thresholds.Builder addThresholdsBuilder() {
            return getThresholdsFieldBuilder().addBuilder(Thresholds.getDefaultInstance());
        }

        public Thresholds.Builder addThresholdsBuilder(int i) {
            return getThresholdsFieldBuilder().addBuilder(i, Thresholds.getDefaultInstance());
        }

        public List<Thresholds.Builder> getThresholdsBuilderList() {
            return getThresholdsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Thresholds, Thresholds.Builder, ThresholdsOrBuilder> getThresholdsFieldBuilder() {
            if (this.thresholdsBuilder_ == null) {
                this.thresholdsBuilder_ = new RepeatedFieldBuilderV3<>(this.thresholds_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.thresholds_ = null;
            }
            return this.thresholdsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14066setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14060mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
