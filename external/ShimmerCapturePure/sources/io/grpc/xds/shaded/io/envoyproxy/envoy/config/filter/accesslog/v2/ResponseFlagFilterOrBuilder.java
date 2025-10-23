package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface ResponseFlagFilterOrBuilder extends MessageOrBuilder {
    String getFlags(int i);

    ByteString getFlagsBytes(int i);

    int getFlagsCount();

    /* renamed from: getFlagsList */
    List<String> mo25536getFlagsList();
}
