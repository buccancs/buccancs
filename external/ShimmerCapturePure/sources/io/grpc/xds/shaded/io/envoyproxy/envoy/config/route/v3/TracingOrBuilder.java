package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v3.CustomTag;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v3.CustomTagOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface TracingOrBuilder extends MessageOrBuilder {
    FractionalPercent getClientSampling();

    FractionalPercentOrBuilder getClientSamplingOrBuilder();

    CustomTag getCustomTags(int i);

    int getCustomTagsCount();

    List<CustomTag> getCustomTagsList();

    CustomTagOrBuilder getCustomTagsOrBuilder(int i);

    List<? extends CustomTagOrBuilder> getCustomTagsOrBuilderList();

    FractionalPercent getOverallSampling();

    FractionalPercentOrBuilder getOverallSamplingOrBuilder();

    FractionalPercent getRandomSampling();

    FractionalPercentOrBuilder getRandomSamplingOrBuilder();

    boolean hasClientSampling();

    boolean hasOverallSampling();

    boolean hasRandomSampling();
}
