package io.grpc.reflection.v1alpha;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface ListServiceResponseOrBuilder extends MessageOrBuilder {
    ServiceResponse getService(int i);

    int getServiceCount();

    List<ServiceResponse> getServiceList();

    ServiceResponseOrBuilder getServiceOrBuilder(int i);

    List<? extends ServiceResponseOrBuilder> getServiceOrBuilderList();
}
