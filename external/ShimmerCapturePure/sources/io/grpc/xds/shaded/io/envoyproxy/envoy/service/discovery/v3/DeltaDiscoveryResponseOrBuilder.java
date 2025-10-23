package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceName;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceNameOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface DeltaDiscoveryResponseOrBuilder extends MessageOrBuilder {
    String getNonce();

    ByteString getNonceBytes();

    String getRemovedResources(int i);

    ByteString getRemovedResourcesBytes(int i);

    int getRemovedResourcesCount();

    /* renamed from: getRemovedResourcesList */
    List<String> mo32436getRemovedResourcesList();

    Resource getResources(int i);

    int getResourcesCount();

    List<Resource> getResourcesList();

    ResourceOrBuilder getResourcesOrBuilder(int i);

    List<? extends ResourceOrBuilder> getResourcesOrBuilderList();

    String getSystemVersionInfo();

    ByteString getSystemVersionInfoBytes();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    ResourceName getUdpaRemovedResources(int i);

    int getUdpaRemovedResourcesCount();

    List<ResourceName> getUdpaRemovedResourcesList();

    ResourceNameOrBuilder getUdpaRemovedResourcesOrBuilder(int i);

    List<? extends ResourceNameOrBuilder> getUdpaRemovedResourcesOrBuilderList();
}
