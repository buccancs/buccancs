package io.grpc.xds.shaded.com.google.security.meshca.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface MeshCertificateRequestOrBuilder extends MessageOrBuilder {
    String getCsr();

    ByteString getCsrBytes();

    String getRequestId();

    ByteString getRequestIdBytes();

    Duration getValidity();

    DurationOrBuilder getValidityOrBuilder();

    boolean hasValidity();
}
