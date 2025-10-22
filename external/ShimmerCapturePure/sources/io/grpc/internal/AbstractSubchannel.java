package io.grpc.internal;

import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.LoadBalancer;

/* loaded from: classes2.dex */
abstract class AbstractSubchannel extends LoadBalancer.Subchannel {
    AbstractSubchannel() {
    }

    abstract InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel();
}
