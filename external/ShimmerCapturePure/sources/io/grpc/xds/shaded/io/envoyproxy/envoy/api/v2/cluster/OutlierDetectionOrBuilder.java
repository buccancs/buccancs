package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes3.dex */
public interface OutlierDetectionOrBuilder extends MessageOrBuilder {
    Duration getBaseEjectionTime();

    DurationOrBuilder getBaseEjectionTimeOrBuilder();

    UInt32Value getConsecutive5Xx();

    UInt32ValueOrBuilder getConsecutive5XxOrBuilder();

    UInt32Value getConsecutiveGatewayFailure();

    UInt32ValueOrBuilder getConsecutiveGatewayFailureOrBuilder();

    UInt32Value getConsecutiveLocalOriginFailure();

    UInt32ValueOrBuilder getConsecutiveLocalOriginFailureOrBuilder();

    UInt32Value getEnforcingConsecutive5Xx();

    UInt32ValueOrBuilder getEnforcingConsecutive5XxOrBuilder();

    UInt32Value getEnforcingConsecutiveGatewayFailure();

    UInt32ValueOrBuilder getEnforcingConsecutiveGatewayFailureOrBuilder();

    UInt32Value getEnforcingConsecutiveLocalOriginFailure();

    UInt32ValueOrBuilder getEnforcingConsecutiveLocalOriginFailureOrBuilder();

    UInt32Value getEnforcingFailurePercentage();

    UInt32Value getEnforcingFailurePercentageLocalOrigin();

    UInt32ValueOrBuilder getEnforcingFailurePercentageLocalOriginOrBuilder();

    UInt32ValueOrBuilder getEnforcingFailurePercentageOrBuilder();

    UInt32Value getEnforcingLocalOriginSuccessRate();

    UInt32ValueOrBuilder getEnforcingLocalOriginSuccessRateOrBuilder();

    UInt32Value getEnforcingSuccessRate();

    UInt32ValueOrBuilder getEnforcingSuccessRateOrBuilder();

    UInt32Value getFailurePercentageMinimumHosts();

    UInt32ValueOrBuilder getFailurePercentageMinimumHostsOrBuilder();

    UInt32Value getFailurePercentageRequestVolume();

    UInt32ValueOrBuilder getFailurePercentageRequestVolumeOrBuilder();

    UInt32Value getFailurePercentageThreshold();

    UInt32ValueOrBuilder getFailurePercentageThresholdOrBuilder();

    Duration getInterval();

    DurationOrBuilder getIntervalOrBuilder();

    UInt32Value getMaxEjectionPercent();

    UInt32ValueOrBuilder getMaxEjectionPercentOrBuilder();

    boolean getSplitExternalLocalOriginErrors();

    UInt32Value getSuccessRateMinimumHosts();

    UInt32ValueOrBuilder getSuccessRateMinimumHostsOrBuilder();

    UInt32Value getSuccessRateRequestVolume();

    UInt32ValueOrBuilder getSuccessRateRequestVolumeOrBuilder();

    UInt32Value getSuccessRateStdevFactor();

    UInt32ValueOrBuilder getSuccessRateStdevFactorOrBuilder();

    boolean hasBaseEjectionTime();

    boolean hasConsecutive5Xx();

    boolean hasConsecutiveGatewayFailure();

    boolean hasConsecutiveLocalOriginFailure();

    boolean hasEnforcingConsecutive5Xx();

    boolean hasEnforcingConsecutiveGatewayFailure();

    boolean hasEnforcingConsecutiveLocalOriginFailure();

    boolean hasEnforcingFailurePercentage();

    boolean hasEnforcingFailurePercentageLocalOrigin();

    boolean hasEnforcingLocalOriginSuccessRate();

    boolean hasEnforcingSuccessRate();

    boolean hasFailurePercentageMinimumHosts();

    boolean hasFailurePercentageRequestVolume();

    boolean hasFailurePercentageThreshold();

    boolean hasInterval();

    boolean hasMaxEjectionPercent();

    boolean hasSuccessRateMinimumHosts();

    boolean hasSuccessRateRequestVolume();

    boolean hasSuccessRateStdevFactor();
}
