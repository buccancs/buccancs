package io.opencensus.proto.resource.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.Map;

/* loaded from: classes4.dex */
public interface ResourceOrBuilder extends MessageOrBuilder {
    boolean containsLabels(String str);

    @Deprecated
    Map<String, String> getLabels();

    int getLabelsCount();

    Map<String, String> getLabelsMap();

    String getLabelsOrDefault(String str, String str2);

    String getLabelsOrThrow(String str);

    String getType();

    ByteString getTypeBytes();
}
