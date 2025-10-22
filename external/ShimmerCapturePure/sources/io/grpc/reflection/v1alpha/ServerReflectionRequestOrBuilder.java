package io.grpc.reflection.v1alpha;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.reflection.v1alpha.ServerReflectionRequest;

/* loaded from: classes3.dex */
public interface ServerReflectionRequestOrBuilder extends MessageOrBuilder {
    String getAllExtensionNumbersOfType();

    ByteString getAllExtensionNumbersOfTypeBytes();

    String getFileByFilename();

    ByteString getFileByFilenameBytes();

    ExtensionRequest getFileContainingExtension();

    ExtensionRequestOrBuilder getFileContainingExtensionOrBuilder();

    String getFileContainingSymbol();

    ByteString getFileContainingSymbolBytes();

    String getHost();

    ByteString getHostBytes();

    String getListServices();

    ByteString getListServicesBytes();

    ServerReflectionRequest.MessageRequestCase getMessageRequestCase();

    boolean hasFileContainingExtension();
}
