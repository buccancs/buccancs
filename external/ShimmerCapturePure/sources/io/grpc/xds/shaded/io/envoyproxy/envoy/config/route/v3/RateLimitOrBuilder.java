package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RateLimit;

import java.util.List;

/* loaded from: classes4.dex */
public interface RateLimitOrBuilder extends MessageOrBuilder {
    RateLimit.Action getActions(int i);

    int getActionsCount();

    List<RateLimit.Action> getActionsList();

    RateLimit.ActionOrBuilder getActionsOrBuilder(int i);

    List<? extends RateLimit.ActionOrBuilder> getActionsOrBuilderList();

    String getDisableKey();

    ByteString getDisableKeyBytes();

    RateLimit.Override getLimit();

    RateLimit.OverrideOrBuilder getLimitOrBuilder();

    UInt32Value getStage();

    UInt32ValueOrBuilder getStageOrBuilder();

    boolean hasLimit();

    boolean hasStage();
}
