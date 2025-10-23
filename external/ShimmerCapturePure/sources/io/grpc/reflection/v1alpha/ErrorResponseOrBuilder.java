package io.grpc.reflection.v1alpha;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface ErrorResponseOrBuilder extends MessageOrBuilder {
    int getErrorCode();

    String getErrorMessage();

    ByteString getErrorMessageBytes();
}
