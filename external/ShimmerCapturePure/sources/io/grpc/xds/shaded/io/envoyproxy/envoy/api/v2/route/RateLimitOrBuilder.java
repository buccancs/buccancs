package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RateLimit;

import java.util.List;

/* loaded from: classes5.dex */
public interface RateLimitOrBuilder extends MessageOrBuilder {
    RateLimit.Action getActions(int i);

    int getActionsCount();

    List<RateLimit.Action> getActionsList();

    RateLimit.ActionOrBuilder getActionsOrBuilder(int i);

    List<? extends RateLimit.ActionOrBuilder> getActionsOrBuilderList();

    String getDisableKey();

    ByteString getDisableKeyBytes();

    UInt32Value getStage();

    UInt32ValueOrBuilder getStageOrBuilder();

    boolean hasStage();
}
