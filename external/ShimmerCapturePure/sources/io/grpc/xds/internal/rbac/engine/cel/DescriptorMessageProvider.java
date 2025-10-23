package io.grpc.xds.internal.rbac.engine.cel;

import com.google.protobuf.Descriptors;

/* loaded from: classes3.dex */
public class DescriptorMessageProvider implements RuntimeTypeProvider {
    public static DescriptorMessageProvider dynamicMessages(Iterable<Descriptors.Descriptor> iterable) {
        return new DescriptorMessageProvider();
    }
}
