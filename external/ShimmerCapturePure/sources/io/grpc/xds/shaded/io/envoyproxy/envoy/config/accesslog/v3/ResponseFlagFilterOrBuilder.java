package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface ResponseFlagFilterOrBuilder extends MessageOrBuilder {
    String getFlags(int i);

    ByteString getFlagsBytes(int i);

    int getFlagsCount();

    /* renamed from: getFlagsList */
    List<String> mo20081getFlagsList();
}
