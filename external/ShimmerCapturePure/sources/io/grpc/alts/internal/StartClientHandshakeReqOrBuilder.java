package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface StartClientHandshakeReqOrBuilder extends MessageOrBuilder {
    String getApplicationProtocols(int i);

    ByteString getApplicationProtocolsBytes(int i);

    int getApplicationProtocolsCount();

    /* renamed from: getApplicationProtocolsList */
    List<String> mo6835getApplicationProtocolsList();

    HandshakeProtocol getHandshakeSecurityProtocol();

    int getHandshakeSecurityProtocolValue();

    Endpoint getLocalEndpoint();

    EndpointOrBuilder getLocalEndpointOrBuilder();

    Identity getLocalIdentity();

    IdentityOrBuilder getLocalIdentityOrBuilder();

    int getMaxFrameSize();

    String getRecordProtocols(int i);

    ByteString getRecordProtocolsBytes(int i);

    int getRecordProtocolsCount();

    /* renamed from: getRecordProtocolsList */
    List<String> mo6838getRecordProtocolsList();

    Endpoint getRemoteEndpoint();

    EndpointOrBuilder getRemoteEndpointOrBuilder();

    RpcProtocolVersions getRpcVersions();

    RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder();

    Identity getTargetIdentities(int i);

    int getTargetIdentitiesCount();

    List<Identity> getTargetIdentitiesList();

    IdentityOrBuilder getTargetIdentitiesOrBuilder(int i);

    List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList();

    String getTargetName();

    ByteString getTargetNameBytes();

    boolean hasLocalEndpoint();

    boolean hasLocalIdentity();

    boolean hasRemoteEndpoint();

    boolean hasRpcVersions();
}
