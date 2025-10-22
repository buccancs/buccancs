package io.grpc.health.v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.health.v1.HealthCheckResponse;

/* loaded from: classes2.dex */
public interface HealthCheckResponseOrBuilder extends MessageOrBuilder {
    HealthCheckResponse.ServingStatus getStatus();

    int getStatusValue();
}
