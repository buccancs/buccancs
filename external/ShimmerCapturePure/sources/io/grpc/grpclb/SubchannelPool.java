package io.grpc.grpclb;

import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;

/* loaded from: classes2.dex */
interface SubchannelPool {

    void clear();

    void registerListener(PooledSubchannelStateListener pooledSubchannelStateListener);

    void returnSubchannel(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo);

    LoadBalancer.Subchannel takeOrCreateSubchannel(EquivalentAddressGroup equivalentAddressGroup, Attributes attributes);

    public interface PooledSubchannelStateListener {
        void onSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo);
    }
}
