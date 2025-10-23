package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.Map;

/* loaded from: classes3.dex */
public interface ContextParamsOrBuilder extends MessageOrBuilder {
    boolean containsParams(String str);

    @Deprecated
    Map<String, String> getParams();

    int getParamsCount();

    Map<String, String> getParamsMap();

    String getParamsOrDefault(String str, String str2);

    String getParamsOrThrow(String str);
}
