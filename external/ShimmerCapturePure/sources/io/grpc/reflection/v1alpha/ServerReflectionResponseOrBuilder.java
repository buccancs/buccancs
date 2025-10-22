package io.grpc.reflection.v1alpha;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.reflection.v1alpha.ServerReflectionResponse;

/* loaded from: classes3.dex */
public interface ServerReflectionResponseOrBuilder extends MessageOrBuilder {
    ExtensionNumberResponse getAllExtensionNumbersResponse();

    ExtensionNumberResponseOrBuilder getAllExtensionNumbersResponseOrBuilder();

    ErrorResponse getErrorResponse();

    ErrorResponseOrBuilder getErrorResponseOrBuilder();

    FileDescriptorResponse getFileDescriptorResponse();

    FileDescriptorResponseOrBuilder getFileDescriptorResponseOrBuilder();

    ListServiceResponse getListServicesResponse();

    ListServiceResponseOrBuilder getListServicesResponseOrBuilder();

    ServerReflectionResponse.MessageResponseCase getMessageResponseCase();

    ServerReflectionRequest getOriginalRequest();

    ServerReflectionRequestOrBuilder getOriginalRequestOrBuilder();

    String getValidHost();

    ByteString getValidHostBytes();

    boolean hasAllExtensionNumbersResponse();

    boolean hasErrorResponse();

    boolean hasFileDescriptorResponse();

    boolean hasListServicesResponse();

    boolean hasOriginalRequest();
}
