package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ControlPlane;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ControlPlaneOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface DiscoveryResponseOrBuilder extends MessageOrBuilder {
    boolean getCanary();

    ControlPlane getControlPlane();

    ControlPlaneOrBuilder getControlPlaneOrBuilder();

    String getNonce();

    ByteString getNonceBytes();

    Any getResources(int i);

    int getResourcesCount();

    List<Any> getResourcesList();

    AnyOrBuilder getResourcesOrBuilder(int i);

    List<? extends AnyOrBuilder> getResourcesOrBuilderList();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    String getVersionInfo();

    ByteString getVersionInfoBytes();

    boolean hasControlPlane();
}
