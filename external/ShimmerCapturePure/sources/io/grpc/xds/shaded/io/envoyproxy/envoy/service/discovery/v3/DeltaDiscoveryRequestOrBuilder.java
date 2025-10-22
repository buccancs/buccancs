package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.NodeOrBuilder;

import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface DeltaDiscoveryRequestOrBuilder extends MessageOrBuilder {
    boolean containsInitialResourceVersions(String str);

    Status getErrorDetail();

    StatusOrBuilder getErrorDetailOrBuilder();

    @Deprecated
    Map<String, String> getInitialResourceVersions();

    int getInitialResourceVersionsCount();

    Map<String, String> getInitialResourceVersionsMap();

    String getInitialResourceVersionsOrDefault(String str, String str2);

    String getInitialResourceVersionsOrThrow(String str);

    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    String getResourceNamesSubscribe(int i);

    ByteString getResourceNamesSubscribeBytes(int i);

    int getResourceNamesSubscribeCount();

    /* renamed from: getResourceNamesSubscribeList */
    List<String> mo32388getResourceNamesSubscribeList();

    String getResourceNamesUnsubscribe(int i);

    ByteString getResourceNamesUnsubscribeBytes(int i);

    int getResourceNamesUnsubscribeCount();

    /* renamed from: getResourceNamesUnsubscribeList */
    List<String> mo32389getResourceNamesUnsubscribeList();

    String getResponseNonce();

    ByteString getResponseNonceBytes();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    ResourceLocator getUdpaResourcesSubscribe(int i);

    int getUdpaResourcesSubscribeCount();

    List<ResourceLocator> getUdpaResourcesSubscribeList();

    ResourceLocatorOrBuilder getUdpaResourcesSubscribeOrBuilder(int i);

    List<? extends ResourceLocatorOrBuilder> getUdpaResourcesSubscribeOrBuilderList();

    ResourceLocator getUdpaResourcesUnsubscribe(int i);

    int getUdpaResourcesUnsubscribeCount();

    List<ResourceLocator> getUdpaResourcesUnsubscribeList();

    ResourceLocatorOrBuilder getUdpaResourcesUnsubscribeOrBuilder(int i);

    List<? extends ResourceLocatorOrBuilder> getUdpaResourcesUnsubscribeOrBuilderList();

    boolean hasErrorDetail();

    boolean hasNode();
}
