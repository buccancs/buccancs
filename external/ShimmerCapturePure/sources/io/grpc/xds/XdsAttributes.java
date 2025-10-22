package io.grpc.xds;

import io.grpc.Attributes;
import io.grpc.internal.ObjectPool;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.LoadStatsManager;

/* loaded from: classes3.dex */
public final class XdsAttributes {
    public static final Attributes.Key<EnvoyServerProtoData.UpstreamTlsContext> ATTR_UPSTREAM_TLS_CONTEXT = Attributes.Key.create("io.grpc.xds.XdsAttributes.upstreamTlsContext");
    static final Attributes.Key<ObjectPool<XdsClient>> XDS_CLIENT_POOL = Attributes.Key.create("io.grpc.xds.XdsAttributes.xdsClientPool");
    static final Attributes.Key<LoadStatsManager.LoadStatsStore> ATTR_CLUSTER_SERVICE_LOAD_STATS_STORE = Attributes.Key.create("io.grpc.xds.XdsAttributes.loadStatsStore");

    private XdsAttributes() {
    }
}
