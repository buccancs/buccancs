package io.grpc.health.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface HealthCheckRequestOrBuilder extends MessageOrBuilder {
    String getService();

    ByteString getServiceBytes();
}
