package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface DeltaDiscoveryResponseOrBuilder extends MessageOrBuilder {
    String getNonce();

    ByteString getNonceBytes();

    String getRemovedResources(int i);

    ByteString getRemovedResourcesBytes(int i);

    int getRemovedResourcesCount();

    /* renamed from: getRemovedResourcesList */
    List<String> mo12491getRemovedResourcesList();

    Resource getResources(int i);

    int getResourcesCount();

    List<Resource> getResourcesList();

    ResourceOrBuilder getResourcesOrBuilder(int i);

    List<? extends ResourceOrBuilder> getResourcesOrBuilderList();

    String getSystemVersionInfo();

    ByteString getSystemVersionInfoBytes();

    String getTypeUrl();

    ByteString getTypeUrlBytes();
}
