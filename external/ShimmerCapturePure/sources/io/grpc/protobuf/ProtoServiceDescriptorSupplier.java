package io.grpc.protobuf;

import com.google.protobuf.Descriptors;

/* loaded from: classes3.dex */
public interface ProtoServiceDescriptorSupplier extends ProtoFileDescriptorSupplier {
    Descriptors.ServiceDescriptor getServiceDescriptor();
}
