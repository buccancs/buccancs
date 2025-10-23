package io.grpc.reflection.v1alpha;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface ExtensionNumberResponseOrBuilder extends MessageOrBuilder {
    String getBaseTypeName();

    ByteString getBaseTypeNameBytes();

    int getExtensionNumber(int i);

    int getExtensionNumberCount();

    List<Integer> getExtensionNumberList();
}
