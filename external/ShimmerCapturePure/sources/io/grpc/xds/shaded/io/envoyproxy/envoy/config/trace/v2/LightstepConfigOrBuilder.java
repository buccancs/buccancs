package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig;

import java.util.List;

/* loaded from: classes4.dex */
public interface LightstepConfigOrBuilder extends MessageOrBuilder {
    String getAccessTokenFile();

    ByteString getAccessTokenFileBytes();

    String getCollectorCluster();

    ByteString getCollectorClusterBytes();

    LightstepConfig.PropagationMode getPropagationModes(int i);

    int getPropagationModesCount();

    List<LightstepConfig.PropagationMode> getPropagationModesList();

    int getPropagationModesValue(int i);

    List<Integer> getPropagationModesValueList();
}
