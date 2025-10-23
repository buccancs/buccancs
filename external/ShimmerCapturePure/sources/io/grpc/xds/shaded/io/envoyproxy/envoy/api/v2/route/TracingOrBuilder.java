package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
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
