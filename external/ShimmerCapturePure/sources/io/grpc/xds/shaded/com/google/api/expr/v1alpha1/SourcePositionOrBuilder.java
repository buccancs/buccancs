package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface SourcePositionOrBuilder extends MessageOrBuilder {
    int getColumn();

    int getLine();

    String getLocation();

    ByteString getLocationBytes();

    int getOffset();
}
