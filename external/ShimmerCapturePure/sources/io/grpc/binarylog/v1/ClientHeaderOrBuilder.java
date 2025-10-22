package io.grpc.binarylog.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;

/* loaded from: classes2.dex */
public interface ClientHeaderOrBuilder extends com.google.protobuf.MessageOrBuilder {
    String getAuthority();

    ByteString getAuthorityBytes();

    Metadata getMetadata();

    MetadataOrBuilder getMetadataOrBuilder();

    String getMethodName();

    ByteString getMethodNameBytes();

    Duration getTimeout();

    DurationOrBuilder getTimeoutOrBuilder();

    boolean hasMetadata();

    boolean hasTimeout();
}
