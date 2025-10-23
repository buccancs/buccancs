package io.grpc.binarylog.v1;

import com.google.protobuf.ByteString;

/* loaded from: classes2.dex */
public interface TrailerOrBuilder extends com.google.protobuf.MessageOrBuilder {
    Metadata getMetadata();

    MetadataOrBuilder getMetadataOrBuilder();

    int getStatusCode();

    ByteString getStatusDetails();

    String getStatusMessage();

    ByteString getStatusMessageBytes();

    boolean hasMetadata();
}
