package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.NodeOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface DiscoveryRequestOrBuilder extends MessageOrBuilder {
    Status getErrorDetail();

    StatusOrBuilder getErrorDetailOrBuilder();

    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    String getResourceNames(int i);

    ByteString getResourceNamesBytes(int i);

    int getResourceNamesCount();

    /* renamed from: getResourceNamesList */
    List<String> mo32483getResourceNamesList();

    String getResponseNonce();

    ByteString getResponseNonceBytes();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    String getVersionInfo();

    ByteString getVersionInfoBytes();

    boolean hasErrorDetail();

    boolean hasNode();
}
