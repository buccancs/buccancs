package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

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
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class OutlierDetection extends GeneratedMessageV3 implements OutlierDetectionOrBuilder {
    public static final int BASE_EJECTION_TIME_FIELD_NUMBER = 3;
    public static final int CONSECUTIVE_5XX_FIELD_NUMBER = 1;
    public static final int CONSECUTIVE_GATEWAY_FAILURE_FIELD_NUMBER = 10;
    public static final int CONSECUTIVE_LOCAL_ORIGIN_FAILURE_FIELD_NUMBER = 13;
    public static final int ENFORCING_CONSECUTIVE_5XX_FIELD_NUMBER = 5;
    public static final int ENFORCING_CONSECUTIVE_GATEWAY_FAILURE_FIELD_NUMBER = 11;
    public static final int ENFORCING_CONSECUTIVE_LOCAL_ORIGIN_FAILURE_FIELD_NUMBER = 14;
    public static final int ENFORCING_FAILURE_PERCENTAGE_FIELD_NUMBER = 17;
    public static final int ENFORCING_FAILURE_PERCENTAGE_LOCAL_ORIGIN_FIELD_NUMBER = 18;
    public static final int ENFORCING_LOCAL_ORIGIN_SUCCESS_RATE_FIELD_NUMBER = 15;
    public static final int ENFORCING_SUCCESS_RATE_FIELD_NUMBER = 6;
    public static final int FAILURE_PERCENTAGE_MINIMUM_HOSTS_FIELD_NUMBER = 19;
    public static final int FAILURE_PERCENTAGE_REQUEST_VOLUME_FIELD_NUMBER = 20;
    public static final int FAILURE_PERCENTAGE_THRESHOLD_FIELD_NUMBER = 16;
    public static final int INTERVAL_FIELD_NUMBER = 2;
    public static final int MAX_EJECTION_PERCENT_FIELD_NUMBER = 4;
    public static final int SPLIT_EXTERNAL_LOCAL_ORIGIN_ERRORS_FIELD_NUMBER = 12;
    public static final int SUCCESS_RATE_MINIMUM_HOSTS_FIELD_NUMBER = 7;
    public static final int SUCCESS_RATE_REQUEST_VOLUME_FIELD_NUMBER = 8;
    public static final int SUCCESS_RATE_STDEV_FACTOR_FIELD_NUMBER = 9;
    private static final long serialVersionUID = 0;
    private static final OutlierDetection DEFAULT_INSTANCE = new OutlierDetection();
    private static final Parser<OutlierDetection> PARSER = new AbstractParser<OutlierDetection>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public OutlierDetection m14213parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new OutlierDetection(codedInputStream, extensionRegistryLite);
        }
    };
    private Duration baseEjectionTime_;
    private UInt32Value consecutive5Xx_;
    private UInt32Value consecutiveGatewayFailure_;
    private UInt32Value consecutiveLocalOriginFailure_;
    private UInt32Value enforcingConsecutive5Xx_;
    private UInt32Value enforcingConsecutiveGatewayFailure_;
    private UInt32Value enforcingConsecutiveLocalOriginFailure_;
    private UInt32Value enforcingFailurePercentageLocalOrigin_;
    private UInt32Value enforcingFailurePercentage_;
    private UInt32Value enforcingLocalOriginSuccessRate_;
    private UInt32Value enforcingSuccessRate_;
    private UInt32Value failurePercentageMinimumHosts_;
    private UInt32Value failurePercentageRequestVolume_;
    private UInt32Value failurePercentageThreshold_;
    private Duration interval_;
    private UInt32Value maxEjectionPercent_;
    private byte memoizedIsInitialized;
    private boolean splitExternalLocalOriginErrors_;
    private UInt32Value successRateMinimumHosts_;
    private UInt32Value successRateRequestVolume_;
    private UInt32Value successRateStdevFactor_;

    private OutlierDetection(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private OutlierDetection() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private OutlierDetection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        UInt32Value.Builder builder;
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
                            UInt32Value uInt32Value = this.consecutive5Xx_;
                            builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.consecutive5Xx_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.consecutive5Xx_ = builder.buildPartial();
                            }
                        case 18:
                            Duration duration = this.interval_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.interval_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.interval_ = builder.buildPartial();
                            }
                        case 26:
                            Duration duration2 = this.baseEjectionTime_;
                            builder = duration2 != null ? duration2.toBuilder() : null;
                            Duration message3 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.baseEjectionTime_ = message3;
                            if (builder != null) {
                                builder.mergeFrom(message3);
                                this.baseEjectionTime_ = builder.buildPartial();
                            }
                        case 34:
                            UInt32Value uInt32Value2 = this.maxEjectionPercent_;
                            builder = uInt32Value2 != null ? uInt32Value2.toBuilder() : null;
                            UInt32Value message4 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.maxEjectionPercent_ = message4;
                            if (builder != null) {
                                builder.mergeFrom(message4);
                                this.maxEjectionPercent_ = builder.buildPartial();
                            }
                        case 42:
                            UInt32Value uInt32Value3 = this.enforcingConsecutive5Xx_;
                            builder = uInt32Value3 != null ? uInt32Value3.toBuilder() : null;
                            UInt32Value message5 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingConsecutive5Xx_ = message5;
                            if (builder != null) {
                                builder.mergeFrom(message5);
                                this.enforcingConsecutive5Xx_ = builder.buildPartial();
                            }
                        case 50:
                            UInt32Value uInt32Value4 = this.enforcingSuccessRate_;
                            builder = uInt32Value4 != null ? uInt32Value4.toBuilder() : null;
                            UInt32Value message6 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingSuccessRate_ = message6;
                            if (builder != null) {
                                builder.mergeFrom(message6);
                                this.enforcingSuccessRate_ = builder.buildPartial();
                            }
                        case 58:
                            UInt32Value uInt32Value5 = this.successRateMinimumHosts_;
                            builder = uInt32Value5 != null ? uInt32Value5.toBuilder() : null;
                            UInt32Value message7 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.successRateMinimumHosts_ = message7;
                            if (builder != null) {
                                builder.mergeFrom(message7);
                                this.successRateMinimumHosts_ = builder.buildPartial();
                            }
                        case 66:
                            UInt32Value uInt32Value6 = this.successRateRequestVolume_;
                            builder = uInt32Value6 != null ? uInt32Value6.toBuilder() : null;
                            UInt32Value message8 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.successRateRequestVolume_ = message8;
                            if (builder != null) {
                                builder.mergeFrom(message8);
                                this.successRateRequestVolume_ = builder.buildPartial();
                            }
                        case 74:
                            UInt32Value uInt32Value7 = this.successRateStdevFactor_;
                            builder = uInt32Value7 != null ? uInt32Value7.toBuilder() : null;
                            UInt32Value message9 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.successRateStdevFactor_ = message9;
                            if (builder != null) {
                                builder.mergeFrom(message9);
                                this.successRateStdevFactor_ = builder.buildPartial();
                            }
                        case 82:
                            UInt32Value uInt32Value8 = this.consecutiveGatewayFailure_;
                            builder = uInt32Value8 != null ? uInt32Value8.toBuilder() : null;
                            UInt32Value message10 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.consecutiveGatewayFailure_ = message10;
                            if (builder != null) {
                                builder.mergeFrom(message10);
                                this.consecutiveGatewayFailure_ = builder.buildPartial();
                            }
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            UInt32Value uInt32Value9 = this.enforcingConsecutiveGatewayFailure_;
                            builder = uInt32Value9 != null ? uInt32Value9.toBuilder() : null;
                            UInt32Value message11 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingConsecutiveGatewayFailure_ = message11;
                            if (builder != null) {
                                builder.mergeFrom(message11);
                                this.enforcingConsecutiveGatewayFailure_ = builder.buildPartial();
                            }
                        case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                            this.splitExternalLocalOriginErrors_ = codedInputStream.readBool();
                        case 106:
                            UInt32Value uInt32Value10 = this.consecutiveLocalOriginFailure_;
                            builder = uInt32Value10 != null ? uInt32Value10.toBuilder() : null;
                            UInt32Value message12 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.consecutiveLocalOriginFailure_ = message12;
                            if (builder != null) {
                                builder.mergeFrom(message12);
                                this.consecutiveLocalOriginFailure_ = builder.buildPartial();
                            }
                        case 114:
                            UInt32Value uInt32Value11 = this.enforcingConsecutiveLocalOriginFailure_;
                            builder = uInt32Value11 != null ? uInt32Value11.toBuilder() : null;
                            UInt32Value message13 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingConsecutiveLocalOriginFailure_ = message13;
                            if (builder != null) {
                                builder.mergeFrom(message13);
                                this.enforcingConsecutiveLocalOriginFailure_ = builder.buildPartial();
                            }
                        case 122:
                            UInt32Value uInt32Value12 = this.enforcingLocalOriginSuccessRate_;
                            builder = uInt32Value12 != null ? uInt32Value12.toBuilder() : null;
                            UInt32Value message14 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingLocalOriginSuccessRate_ = message14;
                            if (builder != null) {
                                builder.mergeFrom(message14);
                                this.enforcingLocalOriginSuccessRate_ = builder.buildPartial();
                            }
                        case 130:
                            UInt32Value uInt32Value13 = this.failurePercentageThreshold_;
                            builder = uInt32Value13 != null ? uInt32Value13.toBuilder() : null;
                            UInt32Value message15 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.failurePercentageThreshold_ = message15;
                            if (builder != null) {
                                builder.mergeFrom(message15);
                                this.failurePercentageThreshold_ = builder.buildPartial();
                            }
                        case 138:
                            UInt32Value uInt32Value14 = this.enforcingFailurePercentage_;
                            builder = uInt32Value14 != null ? uInt32Value14.toBuilder() : null;
                            UInt32Value message16 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingFailurePercentage_ = message16;
                            if (builder != null) {
                                builder.mergeFrom(message16);
                                this.enforcingFailurePercentage_ = builder.buildPartial();
                            }
                        case 146:
                            UInt32Value uInt32Value15 = this.enforcingFailurePercentageLocalOrigin_;
                            builder = uInt32Value15 != null ? uInt32Value15.toBuilder() : null;
                            UInt32Value message17 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.enforcingFailurePercentageLocalOrigin_ = message17;
                            if (builder != null) {
                                builder.mergeFrom(message17);
                                this.enforcingFailurePercentageLocalOrigin_ = builder.buildPartial();
                            }
                        case 154:
                            UInt32Value uInt32Value16 = this.failurePercentageMinimumHosts_;
                            builder = uInt32Value16 != null ? uInt32Value16.toBuilder() : null;
                            UInt32Value message18 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.failurePercentageMinimumHosts_ = message18;
                            if (builder != null) {
                                builder.mergeFrom(message18);
                                this.failurePercentageMinimumHosts_ = builder.buildPartial();
                            }
                        case 162:
                            UInt32Value uInt32Value17 = this.failurePercentageRequestVolume_;
                            builder = uInt32Value17 != null ? uInt32Value17.toBuilder() : null;
                            UInt32Value message19 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.failurePercentageRequestVolume_ = message19;
                            if (builder != null) {
                                builder.mergeFrom(message19);
                                this.failurePercentageRequestVolume_ = builder.buildPartial();
                            }
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

    public static OutlierDetection getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<OutlierDetection> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OutlierDetectionProto.internal_static_envoy_api_v2_cluster_OutlierDetection_descriptor;
    }

    public static OutlierDetection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(byteBuffer);
    }

    public static OutlierDetection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static OutlierDetection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(byteString);
    }

    public static OutlierDetection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static OutlierDetection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(bArr);
    }

    public static OutlierDetection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OutlierDetection) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static OutlierDetection parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static OutlierDetection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OutlierDetection parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static OutlierDetection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OutlierDetection parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static OutlierDetection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14211toBuilder();
    }

    public static Builder newBuilder(OutlierDetection outlierDetection) {
        return DEFAULT_INSTANCE.m14211toBuilder().mergeFrom(outlierDetection);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public OutlierDetection m14206getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<OutlierDetection> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean getSplitExternalLocalOriginErrors() {
        return this.splitExternalLocalOriginErrors_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasBaseEjectionTime() {
        return this.baseEjectionTime_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasConsecutive5Xx() {
        return this.consecutive5Xx_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasConsecutiveGatewayFailure() {
        return this.consecutiveGatewayFailure_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasConsecutiveLocalOriginFailure() {
        return this.consecutiveLocalOriginFailure_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingConsecutive5Xx() {
        return this.enforcingConsecutive5Xx_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingConsecutiveGatewayFailure() {
        return this.enforcingConsecutiveGatewayFailure_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingConsecutiveLocalOriginFailure() {
        return this.enforcingConsecutiveLocalOriginFailure_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingFailurePercentage() {
        return this.enforcingFailurePercentage_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingFailurePercentageLocalOrigin() {
        return this.enforcingFailurePercentageLocalOrigin_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingLocalOriginSuccessRate() {
        return this.enforcingLocalOriginSuccessRate_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasEnforcingSuccessRate() {
        return this.enforcingSuccessRate_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasFailurePercentageMinimumHosts() {
        return this.failurePercentageMinimumHosts_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasFailurePercentageRequestVolume() {
        return this.failurePercentageRequestVolume_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasFailurePercentageThreshold() {
        return this.failurePercentageThreshold_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasInterval() {
        return this.interval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasMaxEjectionPercent() {
        return this.maxEjectionPercent_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasSuccessRateMinimumHosts() {
        return this.successRateMinimumHosts_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasSuccessRateRequestVolume() {
        return this.successRateRequestVolume_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public boolean hasSuccessRateStdevFactor() {
        return this.successRateStdevFactor_ != null;
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
        return new OutlierDetection();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OutlierDetectionProto.internal_static_envoy_api_v2_cluster_OutlierDetection_fieldAccessorTable.ensureFieldAccessorsInitialized(OutlierDetection.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getConsecutive5Xx() {
        UInt32Value uInt32Value = this.consecutive5Xx_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getConsecutive5XxOrBuilder() {
        return getConsecutive5Xx();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public Duration getInterval() {
        Duration duration = this.interval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public DurationOrBuilder getIntervalOrBuilder() {
        return getInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public Duration getBaseEjectionTime() {
        Duration duration = this.baseEjectionTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public DurationOrBuilder getBaseEjectionTimeOrBuilder() {
        return getBaseEjectionTime();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getMaxEjectionPercent() {
        UInt32Value uInt32Value = this.maxEjectionPercent_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getMaxEjectionPercentOrBuilder() {
        return getMaxEjectionPercent();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingConsecutive5Xx() {
        UInt32Value uInt32Value = this.enforcingConsecutive5Xx_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingConsecutive5XxOrBuilder() {
        return getEnforcingConsecutive5Xx();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingSuccessRate() {
        UInt32Value uInt32Value = this.enforcingSuccessRate_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingSuccessRateOrBuilder() {
        return getEnforcingSuccessRate();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getSuccessRateMinimumHosts() {
        UInt32Value uInt32Value = this.successRateMinimumHosts_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getSuccessRateMinimumHostsOrBuilder() {
        return getSuccessRateMinimumHosts();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getSuccessRateRequestVolume() {
        UInt32Value uInt32Value = this.successRateRequestVolume_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getSuccessRateRequestVolumeOrBuilder() {
        return getSuccessRateRequestVolume();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getSuccessRateStdevFactor() {
        UInt32Value uInt32Value = this.successRateStdevFactor_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getSuccessRateStdevFactorOrBuilder() {
        return getSuccessRateStdevFactor();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getConsecutiveGatewayFailure() {
        UInt32Value uInt32Value = this.consecutiveGatewayFailure_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getConsecutiveGatewayFailureOrBuilder() {
        return getConsecutiveGatewayFailure();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingConsecutiveGatewayFailure() {
        UInt32Value uInt32Value = this.enforcingConsecutiveGatewayFailure_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingConsecutiveGatewayFailureOrBuilder() {
        return getEnforcingConsecutiveGatewayFailure();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getConsecutiveLocalOriginFailure() {
        UInt32Value uInt32Value = this.consecutiveLocalOriginFailure_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getConsecutiveLocalOriginFailureOrBuilder() {
        return getConsecutiveLocalOriginFailure();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingConsecutiveLocalOriginFailure() {
        UInt32Value uInt32Value = this.enforcingConsecutiveLocalOriginFailure_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingConsecutiveLocalOriginFailureOrBuilder() {
        return getEnforcingConsecutiveLocalOriginFailure();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingLocalOriginSuccessRate() {
        UInt32Value uInt32Value = this.enforcingLocalOriginSuccessRate_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingLocalOriginSuccessRateOrBuilder() {
        return getEnforcingLocalOriginSuccessRate();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getFailurePercentageThreshold() {
        UInt32Value uInt32Value = this.failurePercentageThreshold_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getFailurePercentageThresholdOrBuilder() {
        return getFailurePercentageThreshold();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingFailurePercentage() {
        UInt32Value uInt32Value = this.enforcingFailurePercentage_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingFailurePercentageOrBuilder() {
        return getEnforcingFailurePercentage();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getEnforcingFailurePercentageLocalOrigin() {
        UInt32Value uInt32Value = this.enforcingFailurePercentageLocalOrigin_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getEnforcingFailurePercentageLocalOriginOrBuilder() {
        return getEnforcingFailurePercentageLocalOrigin();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getFailurePercentageMinimumHosts() {
        UInt32Value uInt32Value = this.failurePercentageMinimumHosts_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getFailurePercentageMinimumHostsOrBuilder() {
        return getFailurePercentageMinimumHosts();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32Value getFailurePercentageRequestVolume() {
        UInt32Value uInt32Value = this.failurePercentageRequestVolume_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
    public UInt32ValueOrBuilder getFailurePercentageRequestVolumeOrBuilder() {
        return getFailurePercentageRequestVolume();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.consecutive5Xx_ != null) {
            codedOutputStream.writeMessage(1, getConsecutive5Xx());
        }
        if (this.interval_ != null) {
            codedOutputStream.writeMessage(2, getInterval());
        }
        if (this.baseEjectionTime_ != null) {
            codedOutputStream.writeMessage(3, getBaseEjectionTime());
        }
        if (this.maxEjectionPercent_ != null) {
            codedOutputStream.writeMessage(4, getMaxEjectionPercent());
        }
        if (this.enforcingConsecutive5Xx_ != null) {
            codedOutputStream.writeMessage(5, getEnforcingConsecutive5Xx());
        }
        if (this.enforcingSuccessRate_ != null) {
            codedOutputStream.writeMessage(6, getEnforcingSuccessRate());
        }
        if (this.successRateMinimumHosts_ != null) {
            codedOutputStream.writeMessage(7, getSuccessRateMinimumHosts());
        }
        if (this.successRateRequestVolume_ != null) {
            codedOutputStream.writeMessage(8, getSuccessRateRequestVolume());
        }
        if (this.successRateStdevFactor_ != null) {
            codedOutputStream.writeMessage(9, getSuccessRateStdevFactor());
        }
        if (this.consecutiveGatewayFailure_ != null) {
            codedOutputStream.writeMessage(10, getConsecutiveGatewayFailure());
        }
        if (this.enforcingConsecutiveGatewayFailure_ != null) {
            codedOutputStream.writeMessage(11, getEnforcingConsecutiveGatewayFailure());
        }
        boolean z = this.splitExternalLocalOriginErrors_;
        if (z) {
            codedOutputStream.writeBool(12, z);
        }
        if (this.consecutiveLocalOriginFailure_ != null) {
            codedOutputStream.writeMessage(13, getConsecutiveLocalOriginFailure());
        }
        if (this.enforcingConsecutiveLocalOriginFailure_ != null) {
            codedOutputStream.writeMessage(14, getEnforcingConsecutiveLocalOriginFailure());
        }
        if (this.enforcingLocalOriginSuccessRate_ != null) {
            codedOutputStream.writeMessage(15, getEnforcingLocalOriginSuccessRate());
        }
        if (this.failurePercentageThreshold_ != null) {
            codedOutputStream.writeMessage(16, getFailurePercentageThreshold());
        }
        if (this.enforcingFailurePercentage_ != null) {
            codedOutputStream.writeMessage(17, getEnforcingFailurePercentage());
        }
        if (this.enforcingFailurePercentageLocalOrigin_ != null) {
            codedOutputStream.writeMessage(18, getEnforcingFailurePercentageLocalOrigin());
        }
        if (this.failurePercentageMinimumHosts_ != null) {
            codedOutputStream.writeMessage(19, getFailurePercentageMinimumHosts());
        }
        if (this.failurePercentageRequestVolume_ != null) {
            codedOutputStream.writeMessage(20, getFailurePercentageRequestVolume());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.consecutive5Xx_ != null ? CodedOutputStream.computeMessageSize(1, getConsecutive5Xx()) : 0;
        if (this.interval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getInterval());
        }
        if (this.baseEjectionTime_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getBaseEjectionTime());
        }
        if (this.maxEjectionPercent_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getMaxEjectionPercent());
        }
        if (this.enforcingConsecutive5Xx_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getEnforcingConsecutive5Xx());
        }
        if (this.enforcingSuccessRate_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, getEnforcingSuccessRate());
        }
        if (this.successRateMinimumHosts_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(7, getSuccessRateMinimumHosts());
        }
        if (this.successRateRequestVolume_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(8, getSuccessRateRequestVolume());
        }
        if (this.successRateStdevFactor_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(9, getSuccessRateStdevFactor());
        }
        if (this.consecutiveGatewayFailure_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(10, getConsecutiveGatewayFailure());
        }
        if (this.enforcingConsecutiveGatewayFailure_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(11, getEnforcingConsecutiveGatewayFailure());
        }
        boolean z = this.splitExternalLocalOriginErrors_;
        if (z) {
            iComputeMessageSize += CodedOutputStream.computeBoolSize(12, z);
        }
        if (this.consecutiveLocalOriginFailure_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(13, getConsecutiveLocalOriginFailure());
        }
        if (this.enforcingConsecutiveLocalOriginFailure_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(14, getEnforcingConsecutiveLocalOriginFailure());
        }
        if (this.enforcingLocalOriginSuccessRate_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(15, getEnforcingLocalOriginSuccessRate());
        }
        if (this.failurePercentageThreshold_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(16, getFailurePercentageThreshold());
        }
        if (this.enforcingFailurePercentage_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(17, getEnforcingFailurePercentage());
        }
        if (this.enforcingFailurePercentageLocalOrigin_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(18, getEnforcingFailurePercentageLocalOrigin());
        }
        if (this.failurePercentageMinimumHosts_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(19, getFailurePercentageMinimumHosts());
        }
        if (this.failurePercentageRequestVolume_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(20, getFailurePercentageRequestVolume());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutlierDetection)) {
            return super.equals(obj);
        }
        OutlierDetection outlierDetection = (OutlierDetection) obj;
        if (hasConsecutive5Xx() != outlierDetection.hasConsecutive5Xx()) {
            return false;
        }
        if ((hasConsecutive5Xx() && !getConsecutive5Xx().equals(outlierDetection.getConsecutive5Xx())) || hasInterval() != outlierDetection.hasInterval()) {
            return false;
        }
        if ((hasInterval() && !getInterval().equals(outlierDetection.getInterval())) || hasBaseEjectionTime() != outlierDetection.hasBaseEjectionTime()) {
            return false;
        }
        if ((hasBaseEjectionTime() && !getBaseEjectionTime().equals(outlierDetection.getBaseEjectionTime())) || hasMaxEjectionPercent() != outlierDetection.hasMaxEjectionPercent()) {
            return false;
        }
        if ((hasMaxEjectionPercent() && !getMaxEjectionPercent().equals(outlierDetection.getMaxEjectionPercent())) || hasEnforcingConsecutive5Xx() != outlierDetection.hasEnforcingConsecutive5Xx()) {
            return false;
        }
        if ((hasEnforcingConsecutive5Xx() && !getEnforcingConsecutive5Xx().equals(outlierDetection.getEnforcingConsecutive5Xx())) || hasEnforcingSuccessRate() != outlierDetection.hasEnforcingSuccessRate()) {
            return false;
        }
        if ((hasEnforcingSuccessRate() && !getEnforcingSuccessRate().equals(outlierDetection.getEnforcingSuccessRate())) || hasSuccessRateMinimumHosts() != outlierDetection.hasSuccessRateMinimumHosts()) {
            return false;
        }
        if ((hasSuccessRateMinimumHosts() && !getSuccessRateMinimumHosts().equals(outlierDetection.getSuccessRateMinimumHosts())) || hasSuccessRateRequestVolume() != outlierDetection.hasSuccessRateRequestVolume()) {
            return false;
        }
        if ((hasSuccessRateRequestVolume() && !getSuccessRateRequestVolume().equals(outlierDetection.getSuccessRateRequestVolume())) || hasSuccessRateStdevFactor() != outlierDetection.hasSuccessRateStdevFactor()) {
            return false;
        }
        if ((hasSuccessRateStdevFactor() && !getSuccessRateStdevFactor().equals(outlierDetection.getSuccessRateStdevFactor())) || hasConsecutiveGatewayFailure() != outlierDetection.hasConsecutiveGatewayFailure()) {
            return false;
        }
        if ((hasConsecutiveGatewayFailure() && !getConsecutiveGatewayFailure().equals(outlierDetection.getConsecutiveGatewayFailure())) || hasEnforcingConsecutiveGatewayFailure() != outlierDetection.hasEnforcingConsecutiveGatewayFailure()) {
            return false;
        }
        if ((hasEnforcingConsecutiveGatewayFailure() && !getEnforcingConsecutiveGatewayFailure().equals(outlierDetection.getEnforcingConsecutiveGatewayFailure())) || getSplitExternalLocalOriginErrors() != outlierDetection.getSplitExternalLocalOriginErrors() || hasConsecutiveLocalOriginFailure() != outlierDetection.hasConsecutiveLocalOriginFailure()) {
            return false;
        }
        if ((hasConsecutiveLocalOriginFailure() && !getConsecutiveLocalOriginFailure().equals(outlierDetection.getConsecutiveLocalOriginFailure())) || hasEnforcingConsecutiveLocalOriginFailure() != outlierDetection.hasEnforcingConsecutiveLocalOriginFailure()) {
            return false;
        }
        if ((hasEnforcingConsecutiveLocalOriginFailure() && !getEnforcingConsecutiveLocalOriginFailure().equals(outlierDetection.getEnforcingConsecutiveLocalOriginFailure())) || hasEnforcingLocalOriginSuccessRate() != outlierDetection.hasEnforcingLocalOriginSuccessRate()) {
            return false;
        }
        if ((hasEnforcingLocalOriginSuccessRate() && !getEnforcingLocalOriginSuccessRate().equals(outlierDetection.getEnforcingLocalOriginSuccessRate())) || hasFailurePercentageThreshold() != outlierDetection.hasFailurePercentageThreshold()) {
            return false;
        }
        if ((hasFailurePercentageThreshold() && !getFailurePercentageThreshold().equals(outlierDetection.getFailurePercentageThreshold())) || hasEnforcingFailurePercentage() != outlierDetection.hasEnforcingFailurePercentage()) {
            return false;
        }
        if ((hasEnforcingFailurePercentage() && !getEnforcingFailurePercentage().equals(outlierDetection.getEnforcingFailurePercentage())) || hasEnforcingFailurePercentageLocalOrigin() != outlierDetection.hasEnforcingFailurePercentageLocalOrigin()) {
            return false;
        }
        if ((hasEnforcingFailurePercentageLocalOrigin() && !getEnforcingFailurePercentageLocalOrigin().equals(outlierDetection.getEnforcingFailurePercentageLocalOrigin())) || hasFailurePercentageMinimumHosts() != outlierDetection.hasFailurePercentageMinimumHosts()) {
            return false;
        }
        if ((!hasFailurePercentageMinimumHosts() || getFailurePercentageMinimumHosts().equals(outlierDetection.getFailurePercentageMinimumHosts())) && hasFailurePercentageRequestVolume() == outlierDetection.hasFailurePercentageRequestVolume()) {
            return (!hasFailurePercentageRequestVolume() || getFailurePercentageRequestVolume().equals(outlierDetection.getFailurePercentageRequestVolume())) && this.unknownFields.equals(outlierDetection.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasConsecutive5Xx()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getConsecutive5Xx().hashCode();
        }
        if (hasInterval()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getInterval().hashCode();
        }
        if (hasBaseEjectionTime()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getBaseEjectionTime().hashCode();
        }
        if (hasMaxEjectionPercent()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getMaxEjectionPercent().hashCode();
        }
        if (hasEnforcingConsecutive5Xx()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getEnforcingConsecutive5Xx().hashCode();
        }
        if (hasEnforcingSuccessRate()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getEnforcingSuccessRate().hashCode();
        }
        if (hasSuccessRateMinimumHosts()) {
            iHashCode = (((iHashCode * 37) + 7) * 53) + getSuccessRateMinimumHosts().hashCode();
        }
        if (hasSuccessRateRequestVolume()) {
            iHashCode = (((iHashCode * 37) + 8) * 53) + getSuccessRateRequestVolume().hashCode();
        }
        if (hasSuccessRateStdevFactor()) {
            iHashCode = (((iHashCode * 37) + 9) * 53) + getSuccessRateStdevFactor().hashCode();
        }
        if (hasConsecutiveGatewayFailure()) {
            iHashCode = (((iHashCode * 37) + 10) * 53) + getConsecutiveGatewayFailure().hashCode();
        }
        if (hasEnforcingConsecutiveGatewayFailure()) {
            iHashCode = (((iHashCode * 37) + 11) * 53) + getEnforcingConsecutiveGatewayFailure().hashCode();
        }
        int iHashBoolean = (((iHashCode * 37) + 12) * 53) + Internal.hashBoolean(getSplitExternalLocalOriginErrors());
        if (hasConsecutiveLocalOriginFailure()) {
            iHashBoolean = (((iHashBoolean * 37) + 13) * 53) + getConsecutiveLocalOriginFailure().hashCode();
        }
        if (hasEnforcingConsecutiveLocalOriginFailure()) {
            iHashBoolean = (((iHashBoolean * 37) + 14) * 53) + getEnforcingConsecutiveLocalOriginFailure().hashCode();
        }
        if (hasEnforcingLocalOriginSuccessRate()) {
            iHashBoolean = (((iHashBoolean * 37) + 15) * 53) + getEnforcingLocalOriginSuccessRate().hashCode();
        }
        if (hasFailurePercentageThreshold()) {
            iHashBoolean = (((iHashBoolean * 37) + 16) * 53) + getFailurePercentageThreshold().hashCode();
        }
        if (hasEnforcingFailurePercentage()) {
            iHashBoolean = (((iHashBoolean * 37) + 17) * 53) + getEnforcingFailurePercentage().hashCode();
        }
        if (hasEnforcingFailurePercentageLocalOrigin()) {
            iHashBoolean = (((iHashBoolean * 37) + 18) * 53) + getEnforcingFailurePercentageLocalOrigin().hashCode();
        }
        if (hasFailurePercentageMinimumHosts()) {
            iHashBoolean = (((iHashBoolean * 37) + 19) * 53) + getFailurePercentageMinimumHosts().hashCode();
        }
        if (hasFailurePercentageRequestVolume()) {
            iHashBoolean = (((iHashBoolean * 37) + 20) * 53) + getFailurePercentageRequestVolume().hashCode();
        }
        int iHashCode2 = (iHashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14208newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14211toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OutlierDetectionOrBuilder {
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> baseEjectionTimeBuilder_;
        private Duration baseEjectionTime_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> consecutive5XxBuilder_;
        private UInt32Value consecutive5Xx_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> consecutiveGatewayFailureBuilder_;
        private UInt32Value consecutiveGatewayFailure_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> consecutiveLocalOriginFailureBuilder_;
        private UInt32Value consecutiveLocalOriginFailure_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingConsecutive5XxBuilder_;
        private UInt32Value enforcingConsecutive5Xx_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingConsecutiveGatewayFailureBuilder_;
        private UInt32Value enforcingConsecutiveGatewayFailure_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingConsecutiveLocalOriginFailureBuilder_;
        private UInt32Value enforcingConsecutiveLocalOriginFailure_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingFailurePercentageBuilder_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingFailurePercentageLocalOriginBuilder_;
        private UInt32Value enforcingFailurePercentageLocalOrigin_;
        private UInt32Value enforcingFailurePercentage_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingLocalOriginSuccessRateBuilder_;
        private UInt32Value enforcingLocalOriginSuccessRate_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> enforcingSuccessRateBuilder_;
        private UInt32Value enforcingSuccessRate_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> failurePercentageMinimumHostsBuilder_;
        private UInt32Value failurePercentageMinimumHosts_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> failurePercentageRequestVolumeBuilder_;
        private UInt32Value failurePercentageRequestVolume_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> failurePercentageThresholdBuilder_;
        private UInt32Value failurePercentageThreshold_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> intervalBuilder_;
        private Duration interval_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxEjectionPercentBuilder_;
        private UInt32Value maxEjectionPercent_;
        private boolean splitExternalLocalOriginErrors_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> successRateMinimumHostsBuilder_;
        private UInt32Value successRateMinimumHosts_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> successRateRequestVolumeBuilder_;
        private UInt32Value successRateRequestVolume_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> successRateStdevFactorBuilder_;
        private UInt32Value successRateStdevFactor_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OutlierDetectionProto.internal_static_envoy_api_v2_cluster_OutlierDetection_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean getSplitExternalLocalOriginErrors() {
            return this.splitExternalLocalOriginErrors_;
        }

        public Builder setSplitExternalLocalOriginErrors(boolean z) {
            this.splitExternalLocalOriginErrors_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasBaseEjectionTime() {
            return (this.baseEjectionTimeBuilder_ == null && this.baseEjectionTime_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasConsecutive5Xx() {
            return (this.consecutive5XxBuilder_ == null && this.consecutive5Xx_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasConsecutiveGatewayFailure() {
            return (this.consecutiveGatewayFailureBuilder_ == null && this.consecutiveGatewayFailure_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasConsecutiveLocalOriginFailure() {
            return (this.consecutiveLocalOriginFailureBuilder_ == null && this.consecutiveLocalOriginFailure_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingConsecutive5Xx() {
            return (this.enforcingConsecutive5XxBuilder_ == null && this.enforcingConsecutive5Xx_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingConsecutiveGatewayFailure() {
            return (this.enforcingConsecutiveGatewayFailureBuilder_ == null && this.enforcingConsecutiveGatewayFailure_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingConsecutiveLocalOriginFailure() {
            return (this.enforcingConsecutiveLocalOriginFailureBuilder_ == null && this.enforcingConsecutiveLocalOriginFailure_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingFailurePercentage() {
            return (this.enforcingFailurePercentageBuilder_ == null && this.enforcingFailurePercentage_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingFailurePercentageLocalOrigin() {
            return (this.enforcingFailurePercentageLocalOriginBuilder_ == null && this.enforcingFailurePercentageLocalOrigin_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingLocalOriginSuccessRate() {
            return (this.enforcingLocalOriginSuccessRateBuilder_ == null && this.enforcingLocalOriginSuccessRate_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasEnforcingSuccessRate() {
            return (this.enforcingSuccessRateBuilder_ == null && this.enforcingSuccessRate_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasFailurePercentageMinimumHosts() {
            return (this.failurePercentageMinimumHostsBuilder_ == null && this.failurePercentageMinimumHosts_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasFailurePercentageRequestVolume() {
            return (this.failurePercentageRequestVolumeBuilder_ == null && this.failurePercentageRequestVolume_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasFailurePercentageThreshold() {
            return (this.failurePercentageThresholdBuilder_ == null && this.failurePercentageThreshold_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasInterval() {
            return (this.intervalBuilder_ == null && this.interval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasMaxEjectionPercent() {
            return (this.maxEjectionPercentBuilder_ == null && this.maxEjectionPercent_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasSuccessRateMinimumHosts() {
            return (this.successRateMinimumHostsBuilder_ == null && this.successRateMinimumHosts_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasSuccessRateRequestVolume() {
            return (this.successRateRequestVolumeBuilder_ == null && this.successRateRequestVolume_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public boolean hasSuccessRateStdevFactor() {
            return (this.successRateStdevFactorBuilder_ == null && this.successRateStdevFactor_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OutlierDetectionProto.internal_static_envoy_api_v2_cluster_OutlierDetection_fieldAccessorTable.ensureFieldAccessorsInitialized(OutlierDetection.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = OutlierDetection.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14222clear() {
            super.clear();
            if (this.consecutive5XxBuilder_ == null) {
                this.consecutive5Xx_ = null;
            } else {
                this.consecutive5Xx_ = null;
                this.consecutive5XxBuilder_ = null;
            }
            if (this.intervalBuilder_ == null) {
                this.interval_ = null;
            } else {
                this.interval_ = null;
                this.intervalBuilder_ = null;
            }
            if (this.baseEjectionTimeBuilder_ == null) {
                this.baseEjectionTime_ = null;
            } else {
                this.baseEjectionTime_ = null;
                this.baseEjectionTimeBuilder_ = null;
            }
            if (this.maxEjectionPercentBuilder_ == null) {
                this.maxEjectionPercent_ = null;
            } else {
                this.maxEjectionPercent_ = null;
                this.maxEjectionPercentBuilder_ = null;
            }
            if (this.enforcingConsecutive5XxBuilder_ == null) {
                this.enforcingConsecutive5Xx_ = null;
            } else {
                this.enforcingConsecutive5Xx_ = null;
                this.enforcingConsecutive5XxBuilder_ = null;
            }
            if (this.enforcingSuccessRateBuilder_ == null) {
                this.enforcingSuccessRate_ = null;
            } else {
                this.enforcingSuccessRate_ = null;
                this.enforcingSuccessRateBuilder_ = null;
            }
            if (this.successRateMinimumHostsBuilder_ == null) {
                this.successRateMinimumHosts_ = null;
            } else {
                this.successRateMinimumHosts_ = null;
                this.successRateMinimumHostsBuilder_ = null;
            }
            if (this.successRateRequestVolumeBuilder_ == null) {
                this.successRateRequestVolume_ = null;
            } else {
                this.successRateRequestVolume_ = null;
                this.successRateRequestVolumeBuilder_ = null;
            }
            if (this.successRateStdevFactorBuilder_ == null) {
                this.successRateStdevFactor_ = null;
            } else {
                this.successRateStdevFactor_ = null;
                this.successRateStdevFactorBuilder_ = null;
            }
            if (this.consecutiveGatewayFailureBuilder_ == null) {
                this.consecutiveGatewayFailure_ = null;
            } else {
                this.consecutiveGatewayFailure_ = null;
                this.consecutiveGatewayFailureBuilder_ = null;
            }
            if (this.enforcingConsecutiveGatewayFailureBuilder_ == null) {
                this.enforcingConsecutiveGatewayFailure_ = null;
            } else {
                this.enforcingConsecutiveGatewayFailure_ = null;
                this.enforcingConsecutiveGatewayFailureBuilder_ = null;
            }
            this.splitExternalLocalOriginErrors_ = false;
            if (this.consecutiveLocalOriginFailureBuilder_ == null) {
                this.consecutiveLocalOriginFailure_ = null;
            } else {
                this.consecutiveLocalOriginFailure_ = null;
                this.consecutiveLocalOriginFailureBuilder_ = null;
            }
            if (this.enforcingConsecutiveLocalOriginFailureBuilder_ == null) {
                this.enforcingConsecutiveLocalOriginFailure_ = null;
            } else {
                this.enforcingConsecutiveLocalOriginFailure_ = null;
                this.enforcingConsecutiveLocalOriginFailureBuilder_ = null;
            }
            if (this.enforcingLocalOriginSuccessRateBuilder_ == null) {
                this.enforcingLocalOriginSuccessRate_ = null;
            } else {
                this.enforcingLocalOriginSuccessRate_ = null;
                this.enforcingLocalOriginSuccessRateBuilder_ = null;
            }
            if (this.failurePercentageThresholdBuilder_ == null) {
                this.failurePercentageThreshold_ = null;
            } else {
                this.failurePercentageThreshold_ = null;
                this.failurePercentageThresholdBuilder_ = null;
            }
            if (this.enforcingFailurePercentageBuilder_ == null) {
                this.enforcingFailurePercentage_ = null;
            } else {
                this.enforcingFailurePercentage_ = null;
                this.enforcingFailurePercentageBuilder_ = null;
            }
            if (this.enforcingFailurePercentageLocalOriginBuilder_ == null) {
                this.enforcingFailurePercentageLocalOrigin_ = null;
            } else {
                this.enforcingFailurePercentageLocalOrigin_ = null;
                this.enforcingFailurePercentageLocalOriginBuilder_ = null;
            }
            if (this.failurePercentageMinimumHostsBuilder_ == null) {
                this.failurePercentageMinimumHosts_ = null;
            } else {
                this.failurePercentageMinimumHosts_ = null;
                this.failurePercentageMinimumHostsBuilder_ = null;
            }
            if (this.failurePercentageRequestVolumeBuilder_ == null) {
                this.failurePercentageRequestVolume_ = null;
            } else {
                this.failurePercentageRequestVolume_ = null;
                this.failurePercentageRequestVolumeBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OutlierDetectionProto.internal_static_envoy_api_v2_cluster_OutlierDetection_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OutlierDetection m14235getDefaultInstanceForType() {
            return OutlierDetection.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OutlierDetection m14216build() throws UninitializedMessageException {
            OutlierDetection outlierDetectionM14218buildPartial = m14218buildPartial();
            if (outlierDetectionM14218buildPartial.isInitialized()) {
                return outlierDetectionM14218buildPartial;
            }
            throw newUninitializedMessageException(outlierDetectionM14218buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OutlierDetection m14218buildPartial() {
            OutlierDetection outlierDetection = new OutlierDetection(this);
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                outlierDetection.consecutive5Xx_ = this.consecutive5Xx_;
            } else {
                outlierDetection.consecutive5Xx_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.intervalBuilder_;
            if (singleFieldBuilderV32 == null) {
                outlierDetection.interval_ = this.interval_;
            } else {
                outlierDetection.interval_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV33 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV33 == null) {
                outlierDetection.baseEjectionTime_ = this.baseEjectionTime_;
            } else {
                outlierDetection.baseEjectionTime_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV34 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV34 == null) {
                outlierDetection.maxEjectionPercent_ = this.maxEjectionPercent_;
            } else {
                outlierDetection.maxEjectionPercent_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV35 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV35 == null) {
                outlierDetection.enforcingConsecutive5Xx_ = this.enforcingConsecutive5Xx_;
            } else {
                outlierDetection.enforcingConsecutive5Xx_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV36 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV36 == null) {
                outlierDetection.enforcingSuccessRate_ = this.enforcingSuccessRate_;
            } else {
                outlierDetection.enforcingSuccessRate_ = singleFieldBuilderV36.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV37 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV37 == null) {
                outlierDetection.successRateMinimumHosts_ = this.successRateMinimumHosts_;
            } else {
                outlierDetection.successRateMinimumHosts_ = singleFieldBuilderV37.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV38 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV38 == null) {
                outlierDetection.successRateRequestVolume_ = this.successRateRequestVolume_;
            } else {
                outlierDetection.successRateRequestVolume_ = singleFieldBuilderV38.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV39 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV39 == null) {
                outlierDetection.successRateStdevFactor_ = this.successRateStdevFactor_;
            } else {
                outlierDetection.successRateStdevFactor_ = singleFieldBuilderV39.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV310 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV310 == null) {
                outlierDetection.consecutiveGatewayFailure_ = this.consecutiveGatewayFailure_;
            } else {
                outlierDetection.consecutiveGatewayFailure_ = singleFieldBuilderV310.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV311 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV311 == null) {
                outlierDetection.enforcingConsecutiveGatewayFailure_ = this.enforcingConsecutiveGatewayFailure_;
            } else {
                outlierDetection.enforcingConsecutiveGatewayFailure_ = singleFieldBuilderV311.build();
            }
            outlierDetection.splitExternalLocalOriginErrors_ = this.splitExternalLocalOriginErrors_;
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV312 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV312 == null) {
                outlierDetection.consecutiveLocalOriginFailure_ = this.consecutiveLocalOriginFailure_;
            } else {
                outlierDetection.consecutiveLocalOriginFailure_ = singleFieldBuilderV312.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV313 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV313 == null) {
                outlierDetection.enforcingConsecutiveLocalOriginFailure_ = this.enforcingConsecutiveLocalOriginFailure_;
            } else {
                outlierDetection.enforcingConsecutiveLocalOriginFailure_ = singleFieldBuilderV313.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV314 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV314 == null) {
                outlierDetection.enforcingLocalOriginSuccessRate_ = this.enforcingLocalOriginSuccessRate_;
            } else {
                outlierDetection.enforcingLocalOriginSuccessRate_ = singleFieldBuilderV314.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV315 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV315 == null) {
                outlierDetection.failurePercentageThreshold_ = this.failurePercentageThreshold_;
            } else {
                outlierDetection.failurePercentageThreshold_ = singleFieldBuilderV315.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV316 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV316 == null) {
                outlierDetection.enforcingFailurePercentage_ = this.enforcingFailurePercentage_;
            } else {
                outlierDetection.enforcingFailurePercentage_ = singleFieldBuilderV316.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV317 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV317 == null) {
                outlierDetection.enforcingFailurePercentageLocalOrigin_ = this.enforcingFailurePercentageLocalOrigin_;
            } else {
                outlierDetection.enforcingFailurePercentageLocalOrigin_ = singleFieldBuilderV317.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV318 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV318 == null) {
                outlierDetection.failurePercentageMinimumHosts_ = this.failurePercentageMinimumHosts_;
            } else {
                outlierDetection.failurePercentageMinimumHosts_ = singleFieldBuilderV318.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV319 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV319 == null) {
                outlierDetection.failurePercentageRequestVolume_ = this.failurePercentageRequestVolume_;
            } else {
                outlierDetection.failurePercentageRequestVolume_ = singleFieldBuilderV319.build();
            }
            onBuilt();
            return outlierDetection;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14234clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14246setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14224clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14227clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14248setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14214addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14239mergeFrom(Message message) {
            if (message instanceof OutlierDetection) {
                return mergeFrom((OutlierDetection) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(OutlierDetection outlierDetection) {
            if (outlierDetection == OutlierDetection.getDefaultInstance()) {
                return this;
            }
            if (outlierDetection.hasConsecutive5Xx()) {
                mergeConsecutive5Xx(outlierDetection.getConsecutive5Xx());
            }
            if (outlierDetection.hasInterval()) {
                mergeInterval(outlierDetection.getInterval());
            }
            if (outlierDetection.hasBaseEjectionTime()) {
                mergeBaseEjectionTime(outlierDetection.getBaseEjectionTime());
            }
            if (outlierDetection.hasMaxEjectionPercent()) {
                mergeMaxEjectionPercent(outlierDetection.getMaxEjectionPercent());
            }
            if (outlierDetection.hasEnforcingConsecutive5Xx()) {
                mergeEnforcingConsecutive5Xx(outlierDetection.getEnforcingConsecutive5Xx());
            }
            if (outlierDetection.hasEnforcingSuccessRate()) {
                mergeEnforcingSuccessRate(outlierDetection.getEnforcingSuccessRate());
            }
            if (outlierDetection.hasSuccessRateMinimumHosts()) {
                mergeSuccessRateMinimumHosts(outlierDetection.getSuccessRateMinimumHosts());
            }
            if (outlierDetection.hasSuccessRateRequestVolume()) {
                mergeSuccessRateRequestVolume(outlierDetection.getSuccessRateRequestVolume());
            }
            if (outlierDetection.hasSuccessRateStdevFactor()) {
                mergeSuccessRateStdevFactor(outlierDetection.getSuccessRateStdevFactor());
            }
            if (outlierDetection.hasConsecutiveGatewayFailure()) {
                mergeConsecutiveGatewayFailure(outlierDetection.getConsecutiveGatewayFailure());
            }
            if (outlierDetection.hasEnforcingConsecutiveGatewayFailure()) {
                mergeEnforcingConsecutiveGatewayFailure(outlierDetection.getEnforcingConsecutiveGatewayFailure());
            }
            if (outlierDetection.getSplitExternalLocalOriginErrors()) {
                setSplitExternalLocalOriginErrors(outlierDetection.getSplitExternalLocalOriginErrors());
            }
            if (outlierDetection.hasConsecutiveLocalOriginFailure()) {
                mergeConsecutiveLocalOriginFailure(outlierDetection.getConsecutiveLocalOriginFailure());
            }
            if (outlierDetection.hasEnforcingConsecutiveLocalOriginFailure()) {
                mergeEnforcingConsecutiveLocalOriginFailure(outlierDetection.getEnforcingConsecutiveLocalOriginFailure());
            }
            if (outlierDetection.hasEnforcingLocalOriginSuccessRate()) {
                mergeEnforcingLocalOriginSuccessRate(outlierDetection.getEnforcingLocalOriginSuccessRate());
            }
            if (outlierDetection.hasFailurePercentageThreshold()) {
                mergeFailurePercentageThreshold(outlierDetection.getFailurePercentageThreshold());
            }
            if (outlierDetection.hasEnforcingFailurePercentage()) {
                mergeEnforcingFailurePercentage(outlierDetection.getEnforcingFailurePercentage());
            }
            if (outlierDetection.hasEnforcingFailurePercentageLocalOrigin()) {
                mergeEnforcingFailurePercentageLocalOrigin(outlierDetection.getEnforcingFailurePercentageLocalOrigin());
            }
            if (outlierDetection.hasFailurePercentageMinimumHosts()) {
                mergeFailurePercentageMinimumHosts(outlierDetection.getFailurePercentageMinimumHosts());
            }
            if (outlierDetection.hasFailurePercentageRequestVolume()) {
                mergeFailurePercentageRequestVolume(outlierDetection.getFailurePercentageRequestVolume());
            }
            m14244mergeUnknownFields(outlierDetection.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection.Builder m14240mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection.access$2500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection.Builder.m14240mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetection$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getConsecutive5Xx() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.consecutive5Xx_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setConsecutive5Xx(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.consecutive5Xx_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setConsecutive5Xx(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.consecutive5Xx_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeConsecutive5Xx(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.consecutive5Xx_;
                if (uInt32Value2 != null) {
                    this.consecutive5Xx_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.consecutive5Xx_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearConsecutive5Xx() {
            if (this.consecutive5XxBuilder_ == null) {
                this.consecutive5Xx_ = null;
                onChanged();
            } else {
                this.consecutive5Xx_ = null;
                this.consecutive5XxBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getConsecutive5XxBuilder() {
            onChanged();
            return getConsecutive5XxFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getConsecutive5XxOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutive5XxBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.consecutive5Xx_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getConsecutive5XxFieldBuilder() {
            if (this.consecutive5XxBuilder_ == null) {
                this.consecutive5XxBuilder_ = new SingleFieldBuilderV3<>(getConsecutive5Xx(), getParentForChildren(), isClean());
                this.consecutive5Xx_ = null;
            }
            return this.consecutive5XxBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public Duration getInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.interval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.interval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.interval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.interval_;
                if (duration2 != null) {
                    this.interval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.interval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearInterval() {
            if (this.intervalBuilder_ == null) {
                this.interval_ = null;
                onChanged();
            } else {
                this.interval_ = null;
                this.intervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getIntervalBuilder() {
            onChanged();
            return getIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public DurationOrBuilder getIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.interval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getIntervalFieldBuilder() {
            if (this.intervalBuilder_ == null) {
                this.intervalBuilder_ = new SingleFieldBuilderV3<>(getInterval(), getParentForChildren(), isClean());
                this.interval_ = null;
            }
            return this.intervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public Duration getBaseEjectionTime() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.baseEjectionTime_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setBaseEjectionTime(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.baseEjectionTime_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setBaseEjectionTime(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.baseEjectionTime_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBaseEjectionTime(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.baseEjectionTime_;
                if (duration2 != null) {
                    this.baseEjectionTime_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.baseEjectionTime_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearBaseEjectionTime() {
            if (this.baseEjectionTimeBuilder_ == null) {
                this.baseEjectionTime_ = null;
                onChanged();
            } else {
                this.baseEjectionTime_ = null;
                this.baseEjectionTimeBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getBaseEjectionTimeBuilder() {
            onChanged();
            return getBaseEjectionTimeFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public DurationOrBuilder getBaseEjectionTimeOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseEjectionTimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.baseEjectionTime_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getBaseEjectionTimeFieldBuilder() {
            if (this.baseEjectionTimeBuilder_ == null) {
                this.baseEjectionTimeBuilder_ = new SingleFieldBuilderV3<>(getBaseEjectionTime(), getParentForChildren(), isClean());
                this.baseEjectionTime_ = null;
            }
            return this.baseEjectionTimeBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getMaxEjectionPercent() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.maxEjectionPercent_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setMaxEjectionPercent(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.maxEjectionPercent_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setMaxEjectionPercent(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.maxEjectionPercent_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMaxEjectionPercent(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.maxEjectionPercent_;
                if (uInt32Value2 != null) {
                    this.maxEjectionPercent_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.maxEjectionPercent_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearMaxEjectionPercent() {
            if (this.maxEjectionPercentBuilder_ == null) {
                this.maxEjectionPercent_ = null;
                onChanged();
            } else {
                this.maxEjectionPercent_ = null;
                this.maxEjectionPercentBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getMaxEjectionPercentBuilder() {
            onChanged();
            return getMaxEjectionPercentFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getMaxEjectionPercentOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxEjectionPercentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.maxEjectionPercent_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxEjectionPercentFieldBuilder() {
            if (this.maxEjectionPercentBuilder_ == null) {
                this.maxEjectionPercentBuilder_ = new SingleFieldBuilderV3<>(getMaxEjectionPercent(), getParentForChildren(), isClean());
                this.maxEjectionPercent_ = null;
            }
            return this.maxEjectionPercentBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingConsecutive5Xx() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingConsecutive5Xx_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingConsecutive5Xx(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingConsecutive5Xx_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingConsecutive5Xx(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingConsecutive5Xx_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingConsecutive5Xx(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingConsecutive5Xx_;
                if (uInt32Value2 != null) {
                    this.enforcingConsecutive5Xx_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingConsecutive5Xx_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingConsecutive5Xx() {
            if (this.enforcingConsecutive5XxBuilder_ == null) {
                this.enforcingConsecutive5Xx_ = null;
                onChanged();
            } else {
                this.enforcingConsecutive5Xx_ = null;
                this.enforcingConsecutive5XxBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingConsecutive5XxBuilder() {
            onChanged();
            return getEnforcingConsecutive5XxFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingConsecutive5XxOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutive5XxBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingConsecutive5Xx_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingConsecutive5XxFieldBuilder() {
            if (this.enforcingConsecutive5XxBuilder_ == null) {
                this.enforcingConsecutive5XxBuilder_ = new SingleFieldBuilderV3<>(getEnforcingConsecutive5Xx(), getParentForChildren(), isClean());
                this.enforcingConsecutive5Xx_ = null;
            }
            return this.enforcingConsecutive5XxBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingSuccessRate() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingSuccessRate_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingSuccessRate(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingSuccessRate_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingSuccessRate(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingSuccessRate_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingSuccessRate(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingSuccessRate_;
                if (uInt32Value2 != null) {
                    this.enforcingSuccessRate_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingSuccessRate_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingSuccessRate() {
            if (this.enforcingSuccessRateBuilder_ == null) {
                this.enforcingSuccessRate_ = null;
                onChanged();
            } else {
                this.enforcingSuccessRate_ = null;
                this.enforcingSuccessRateBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingSuccessRateBuilder() {
            onChanged();
            return getEnforcingSuccessRateFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingSuccessRateOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingSuccessRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingSuccessRate_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingSuccessRateFieldBuilder() {
            if (this.enforcingSuccessRateBuilder_ == null) {
                this.enforcingSuccessRateBuilder_ = new SingleFieldBuilderV3<>(getEnforcingSuccessRate(), getParentForChildren(), isClean());
                this.enforcingSuccessRate_ = null;
            }
            return this.enforcingSuccessRateBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getSuccessRateMinimumHosts() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.successRateMinimumHosts_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setSuccessRateMinimumHosts(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.successRateMinimumHosts_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setSuccessRateMinimumHosts(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.successRateMinimumHosts_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSuccessRateMinimumHosts(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.successRateMinimumHosts_;
                if (uInt32Value2 != null) {
                    this.successRateMinimumHosts_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.successRateMinimumHosts_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearSuccessRateMinimumHosts() {
            if (this.successRateMinimumHostsBuilder_ == null) {
                this.successRateMinimumHosts_ = null;
                onChanged();
            } else {
                this.successRateMinimumHosts_ = null;
                this.successRateMinimumHostsBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getSuccessRateMinimumHostsBuilder() {
            onChanged();
            return getSuccessRateMinimumHostsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getSuccessRateMinimumHostsOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateMinimumHostsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.successRateMinimumHosts_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getSuccessRateMinimumHostsFieldBuilder() {
            if (this.successRateMinimumHostsBuilder_ == null) {
                this.successRateMinimumHostsBuilder_ = new SingleFieldBuilderV3<>(getSuccessRateMinimumHosts(), getParentForChildren(), isClean());
                this.successRateMinimumHosts_ = null;
            }
            return this.successRateMinimumHostsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getSuccessRateRequestVolume() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.successRateRequestVolume_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setSuccessRateRequestVolume(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.successRateRequestVolume_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setSuccessRateRequestVolume(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.successRateRequestVolume_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSuccessRateRequestVolume(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.successRateRequestVolume_;
                if (uInt32Value2 != null) {
                    this.successRateRequestVolume_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.successRateRequestVolume_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearSuccessRateRequestVolume() {
            if (this.successRateRequestVolumeBuilder_ == null) {
                this.successRateRequestVolume_ = null;
                onChanged();
            } else {
                this.successRateRequestVolume_ = null;
                this.successRateRequestVolumeBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getSuccessRateRequestVolumeBuilder() {
            onChanged();
            return getSuccessRateRequestVolumeFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getSuccessRateRequestVolumeOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateRequestVolumeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.successRateRequestVolume_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getSuccessRateRequestVolumeFieldBuilder() {
            if (this.successRateRequestVolumeBuilder_ == null) {
                this.successRateRequestVolumeBuilder_ = new SingleFieldBuilderV3<>(getSuccessRateRequestVolume(), getParentForChildren(), isClean());
                this.successRateRequestVolume_ = null;
            }
            return this.successRateRequestVolumeBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getSuccessRateStdevFactor() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.successRateStdevFactor_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setSuccessRateStdevFactor(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.successRateStdevFactor_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setSuccessRateStdevFactor(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.successRateStdevFactor_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSuccessRateStdevFactor(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.successRateStdevFactor_;
                if (uInt32Value2 != null) {
                    this.successRateStdevFactor_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.successRateStdevFactor_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearSuccessRateStdevFactor() {
            if (this.successRateStdevFactorBuilder_ == null) {
                this.successRateStdevFactor_ = null;
                onChanged();
            } else {
                this.successRateStdevFactor_ = null;
                this.successRateStdevFactorBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getSuccessRateStdevFactorBuilder() {
            onChanged();
            return getSuccessRateStdevFactorFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getSuccessRateStdevFactorOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.successRateStdevFactorBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.successRateStdevFactor_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getSuccessRateStdevFactorFieldBuilder() {
            if (this.successRateStdevFactorBuilder_ == null) {
                this.successRateStdevFactorBuilder_ = new SingleFieldBuilderV3<>(getSuccessRateStdevFactor(), getParentForChildren(), isClean());
                this.successRateStdevFactor_ = null;
            }
            return this.successRateStdevFactorBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getConsecutiveGatewayFailure() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.consecutiveGatewayFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setConsecutiveGatewayFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.consecutiveGatewayFailure_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setConsecutiveGatewayFailure(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.consecutiveGatewayFailure_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeConsecutiveGatewayFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.consecutiveGatewayFailure_;
                if (uInt32Value2 != null) {
                    this.consecutiveGatewayFailure_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.consecutiveGatewayFailure_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearConsecutiveGatewayFailure() {
            if (this.consecutiveGatewayFailureBuilder_ == null) {
                this.consecutiveGatewayFailure_ = null;
                onChanged();
            } else {
                this.consecutiveGatewayFailure_ = null;
                this.consecutiveGatewayFailureBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getConsecutiveGatewayFailureBuilder() {
            onChanged();
            return getConsecutiveGatewayFailureFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getConsecutiveGatewayFailureOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.consecutiveGatewayFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getConsecutiveGatewayFailureFieldBuilder() {
            if (this.consecutiveGatewayFailureBuilder_ == null) {
                this.consecutiveGatewayFailureBuilder_ = new SingleFieldBuilderV3<>(getConsecutiveGatewayFailure(), getParentForChildren(), isClean());
                this.consecutiveGatewayFailure_ = null;
            }
            return this.consecutiveGatewayFailureBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingConsecutiveGatewayFailure() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingConsecutiveGatewayFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingConsecutiveGatewayFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingConsecutiveGatewayFailure_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingConsecutiveGatewayFailure(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingConsecutiveGatewayFailure_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingConsecutiveGatewayFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingConsecutiveGatewayFailure_;
                if (uInt32Value2 != null) {
                    this.enforcingConsecutiveGatewayFailure_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingConsecutiveGatewayFailure_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingConsecutiveGatewayFailure() {
            if (this.enforcingConsecutiveGatewayFailureBuilder_ == null) {
                this.enforcingConsecutiveGatewayFailure_ = null;
                onChanged();
            } else {
                this.enforcingConsecutiveGatewayFailure_ = null;
                this.enforcingConsecutiveGatewayFailureBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingConsecutiveGatewayFailureBuilder() {
            onChanged();
            return getEnforcingConsecutiveGatewayFailureFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingConsecutiveGatewayFailureOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveGatewayFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingConsecutiveGatewayFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingConsecutiveGatewayFailureFieldBuilder() {
            if (this.enforcingConsecutiveGatewayFailureBuilder_ == null) {
                this.enforcingConsecutiveGatewayFailureBuilder_ = new SingleFieldBuilderV3<>(getEnforcingConsecutiveGatewayFailure(), getParentForChildren(), isClean());
                this.enforcingConsecutiveGatewayFailure_ = null;
            }
            return this.enforcingConsecutiveGatewayFailureBuilder_;
        }

        public Builder clearSplitExternalLocalOriginErrors() {
            this.splitExternalLocalOriginErrors_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getConsecutiveLocalOriginFailure() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.consecutiveLocalOriginFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setConsecutiveLocalOriginFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.consecutiveLocalOriginFailure_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setConsecutiveLocalOriginFailure(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.consecutiveLocalOriginFailure_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeConsecutiveLocalOriginFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.consecutiveLocalOriginFailure_;
                if (uInt32Value2 != null) {
                    this.consecutiveLocalOriginFailure_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.consecutiveLocalOriginFailure_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearConsecutiveLocalOriginFailure() {
            if (this.consecutiveLocalOriginFailureBuilder_ == null) {
                this.consecutiveLocalOriginFailure_ = null;
                onChanged();
            } else {
                this.consecutiveLocalOriginFailure_ = null;
                this.consecutiveLocalOriginFailureBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getConsecutiveLocalOriginFailureBuilder() {
            onChanged();
            return getConsecutiveLocalOriginFailureFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getConsecutiveLocalOriginFailureOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.consecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.consecutiveLocalOriginFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getConsecutiveLocalOriginFailureFieldBuilder() {
            if (this.consecutiveLocalOriginFailureBuilder_ == null) {
                this.consecutiveLocalOriginFailureBuilder_ = new SingleFieldBuilderV3<>(getConsecutiveLocalOriginFailure(), getParentForChildren(), isClean());
                this.consecutiveLocalOriginFailure_ = null;
            }
            return this.consecutiveLocalOriginFailureBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingConsecutiveLocalOriginFailure() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingConsecutiveLocalOriginFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingConsecutiveLocalOriginFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingConsecutiveLocalOriginFailure_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingConsecutiveLocalOriginFailure(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingConsecutiveLocalOriginFailure_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingConsecutiveLocalOriginFailure(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingConsecutiveLocalOriginFailure_;
                if (uInt32Value2 != null) {
                    this.enforcingConsecutiveLocalOriginFailure_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingConsecutiveLocalOriginFailure_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingConsecutiveLocalOriginFailure() {
            if (this.enforcingConsecutiveLocalOriginFailureBuilder_ == null) {
                this.enforcingConsecutiveLocalOriginFailure_ = null;
                onChanged();
            } else {
                this.enforcingConsecutiveLocalOriginFailure_ = null;
                this.enforcingConsecutiveLocalOriginFailureBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingConsecutiveLocalOriginFailureBuilder() {
            onChanged();
            return getEnforcingConsecutiveLocalOriginFailureFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingConsecutiveLocalOriginFailureOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingConsecutiveLocalOriginFailureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingConsecutiveLocalOriginFailure_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingConsecutiveLocalOriginFailureFieldBuilder() {
            if (this.enforcingConsecutiveLocalOriginFailureBuilder_ == null) {
                this.enforcingConsecutiveLocalOriginFailureBuilder_ = new SingleFieldBuilderV3<>(getEnforcingConsecutiveLocalOriginFailure(), getParentForChildren(), isClean());
                this.enforcingConsecutiveLocalOriginFailure_ = null;
            }
            return this.enforcingConsecutiveLocalOriginFailureBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingLocalOriginSuccessRate() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingLocalOriginSuccessRate_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingLocalOriginSuccessRate(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingLocalOriginSuccessRate_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingLocalOriginSuccessRate(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingLocalOriginSuccessRate_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingLocalOriginSuccessRate(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingLocalOriginSuccessRate_;
                if (uInt32Value2 != null) {
                    this.enforcingLocalOriginSuccessRate_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingLocalOriginSuccessRate_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingLocalOriginSuccessRate() {
            if (this.enforcingLocalOriginSuccessRateBuilder_ == null) {
                this.enforcingLocalOriginSuccessRate_ = null;
                onChanged();
            } else {
                this.enforcingLocalOriginSuccessRate_ = null;
                this.enforcingLocalOriginSuccessRateBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingLocalOriginSuccessRateBuilder() {
            onChanged();
            return getEnforcingLocalOriginSuccessRateFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingLocalOriginSuccessRateOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingLocalOriginSuccessRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingLocalOriginSuccessRate_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingLocalOriginSuccessRateFieldBuilder() {
            if (this.enforcingLocalOriginSuccessRateBuilder_ == null) {
                this.enforcingLocalOriginSuccessRateBuilder_ = new SingleFieldBuilderV3<>(getEnforcingLocalOriginSuccessRate(), getParentForChildren(), isClean());
                this.enforcingLocalOriginSuccessRate_ = null;
            }
            return this.enforcingLocalOriginSuccessRateBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getFailurePercentageThreshold() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.failurePercentageThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setFailurePercentageThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.failurePercentageThreshold_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setFailurePercentageThreshold(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.failurePercentageThreshold_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeFailurePercentageThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.failurePercentageThreshold_;
                if (uInt32Value2 != null) {
                    this.failurePercentageThreshold_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.failurePercentageThreshold_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearFailurePercentageThreshold() {
            if (this.failurePercentageThresholdBuilder_ == null) {
                this.failurePercentageThreshold_ = null;
                onChanged();
            } else {
                this.failurePercentageThreshold_ = null;
                this.failurePercentageThresholdBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getFailurePercentageThresholdBuilder() {
            onChanged();
            return getFailurePercentageThresholdFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getFailurePercentageThresholdOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.failurePercentageThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getFailurePercentageThresholdFieldBuilder() {
            if (this.failurePercentageThresholdBuilder_ == null) {
                this.failurePercentageThresholdBuilder_ = new SingleFieldBuilderV3<>(getFailurePercentageThreshold(), getParentForChildren(), isClean());
                this.failurePercentageThreshold_ = null;
            }
            return this.failurePercentageThresholdBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingFailurePercentage() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingFailurePercentage_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingFailurePercentage(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingFailurePercentage_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingFailurePercentage(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingFailurePercentage_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingFailurePercentage(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingFailurePercentage_;
                if (uInt32Value2 != null) {
                    this.enforcingFailurePercentage_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingFailurePercentage_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingFailurePercentage() {
            if (this.enforcingFailurePercentageBuilder_ == null) {
                this.enforcingFailurePercentage_ = null;
                onChanged();
            } else {
                this.enforcingFailurePercentage_ = null;
                this.enforcingFailurePercentageBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingFailurePercentageBuilder() {
            onChanged();
            return getEnforcingFailurePercentageFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingFailurePercentageOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingFailurePercentage_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingFailurePercentageFieldBuilder() {
            if (this.enforcingFailurePercentageBuilder_ == null) {
                this.enforcingFailurePercentageBuilder_ = new SingleFieldBuilderV3<>(getEnforcingFailurePercentage(), getParentForChildren(), isClean());
                this.enforcingFailurePercentage_ = null;
            }
            return this.enforcingFailurePercentageBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getEnforcingFailurePercentageLocalOrigin() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.enforcingFailurePercentageLocalOrigin_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setEnforcingFailurePercentageLocalOrigin(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.enforcingFailurePercentageLocalOrigin_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setEnforcingFailurePercentageLocalOrigin(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enforcingFailurePercentageLocalOrigin_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEnforcingFailurePercentageLocalOrigin(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.enforcingFailurePercentageLocalOrigin_;
                if (uInt32Value2 != null) {
                    this.enforcingFailurePercentageLocalOrigin_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.enforcingFailurePercentageLocalOrigin_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearEnforcingFailurePercentageLocalOrigin() {
            if (this.enforcingFailurePercentageLocalOriginBuilder_ == null) {
                this.enforcingFailurePercentageLocalOrigin_ = null;
                onChanged();
            } else {
                this.enforcingFailurePercentageLocalOrigin_ = null;
                this.enforcingFailurePercentageLocalOriginBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getEnforcingFailurePercentageLocalOriginBuilder() {
            onChanged();
            return getEnforcingFailurePercentageLocalOriginFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getEnforcingFailurePercentageLocalOriginOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.enforcingFailurePercentageLocalOriginBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.enforcingFailurePercentageLocalOrigin_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getEnforcingFailurePercentageLocalOriginFieldBuilder() {
            if (this.enforcingFailurePercentageLocalOriginBuilder_ == null) {
                this.enforcingFailurePercentageLocalOriginBuilder_ = new SingleFieldBuilderV3<>(getEnforcingFailurePercentageLocalOrigin(), getParentForChildren(), isClean());
                this.enforcingFailurePercentageLocalOrigin_ = null;
            }
            return this.enforcingFailurePercentageLocalOriginBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getFailurePercentageMinimumHosts() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.failurePercentageMinimumHosts_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setFailurePercentageMinimumHosts(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.failurePercentageMinimumHosts_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setFailurePercentageMinimumHosts(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.failurePercentageMinimumHosts_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeFailurePercentageMinimumHosts(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.failurePercentageMinimumHosts_;
                if (uInt32Value2 != null) {
                    this.failurePercentageMinimumHosts_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.failurePercentageMinimumHosts_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearFailurePercentageMinimumHosts() {
            if (this.failurePercentageMinimumHostsBuilder_ == null) {
                this.failurePercentageMinimumHosts_ = null;
                onChanged();
            } else {
                this.failurePercentageMinimumHosts_ = null;
                this.failurePercentageMinimumHostsBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getFailurePercentageMinimumHostsBuilder() {
            onChanged();
            return getFailurePercentageMinimumHostsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getFailurePercentageMinimumHostsOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageMinimumHostsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.failurePercentageMinimumHosts_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getFailurePercentageMinimumHostsFieldBuilder() {
            if (this.failurePercentageMinimumHostsBuilder_ == null) {
                this.failurePercentageMinimumHostsBuilder_ = new SingleFieldBuilderV3<>(getFailurePercentageMinimumHosts(), getParentForChildren(), isClean());
                this.failurePercentageMinimumHosts_ = null;
            }
            return this.failurePercentageMinimumHostsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32Value getFailurePercentageRequestVolume() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.failurePercentageRequestVolume_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setFailurePercentageRequestVolume(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.failurePercentageRequestVolume_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setFailurePercentageRequestVolume(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.failurePercentageRequestVolume_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeFailurePercentageRequestVolume(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.failurePercentageRequestVolume_;
                if (uInt32Value2 != null) {
                    this.failurePercentageRequestVolume_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.failurePercentageRequestVolume_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearFailurePercentageRequestVolume() {
            if (this.failurePercentageRequestVolumeBuilder_ == null) {
                this.failurePercentageRequestVolume_ = null;
                onChanged();
            } else {
                this.failurePercentageRequestVolume_ = null;
                this.failurePercentageRequestVolumeBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getFailurePercentageRequestVolumeBuilder() {
            onChanged();
            return getFailurePercentageRequestVolumeFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.OutlierDetectionOrBuilder
        public UInt32ValueOrBuilder getFailurePercentageRequestVolumeOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.failurePercentageRequestVolumeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.failurePercentageRequestVolume_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getFailurePercentageRequestVolumeFieldBuilder() {
            if (this.failurePercentageRequestVolumeBuilder_ == null) {
                this.failurePercentageRequestVolumeBuilder_ = new SingleFieldBuilderV3<>(getFailurePercentageRequestVolume(), getParentForChildren(), isClean());
                this.failurePercentageRequestVolume_ = null;
            }
            return this.failurePercentageRequestVolumeBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14250setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14244mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
