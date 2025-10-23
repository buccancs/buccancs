package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface ReferenceOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    String getOverloadId(int i);

    ByteString getOverloadIdBytes(int i);

    int getOverloadIdCount();

    /* renamed from: getOverloadIdList */
    List<String> mo11142getOverloadIdList();

    Constant getValue();

    ConstantOrBuilder getValueOrBuilder();

    boolean hasValue();
}
