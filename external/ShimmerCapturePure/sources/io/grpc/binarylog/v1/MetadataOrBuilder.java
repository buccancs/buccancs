package io.grpc.binarylog.v1;

import java.util.List;

/* loaded from: classes2.dex */
public interface MetadataOrBuilder extends com.google.protobuf.MessageOrBuilder {
    MetadataEntry getEntry(int i);

    int getEntryCount();

    List<MetadataEntry> getEntryList();

    MetadataEntryOrBuilder getEntryOrBuilder(int i);

    List<? extends MetadataEntryOrBuilder> getEntryOrBuilderList();
}
