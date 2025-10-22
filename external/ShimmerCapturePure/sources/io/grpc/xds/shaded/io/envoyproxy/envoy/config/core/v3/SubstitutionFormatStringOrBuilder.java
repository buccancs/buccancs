package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString;

/* loaded from: classes6.dex */
public interface SubstitutionFormatStringOrBuilder extends MessageOrBuilder {
    SubstitutionFormatString.FormatCase getFormatCase();

    Struct getJsonFormat();

    StructOrBuilder getJsonFormatOrBuilder();

    String getTextFormat();

    ByteString getTextFormatBytes();

    boolean hasJsonFormat();
}
