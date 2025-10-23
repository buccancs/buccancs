package io.opencensus.proto.agent.common.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.agent.common.v1.LibraryInfo;

/* loaded from: classes4.dex */
public interface LibraryInfoOrBuilder extends MessageOrBuilder {
    String getCoreLibraryVersion();

    ByteString getCoreLibraryVersionBytes();

    String getExporterVersion();

    ByteString getExporterVersionBytes();

    LibraryInfo.Language getLanguage();

    int getLanguageValue();
}
