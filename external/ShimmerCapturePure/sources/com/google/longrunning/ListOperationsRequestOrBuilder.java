package com.google.longrunning;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface ListOperationsRequestOrBuilder extends MessageOrBuilder {
    String getFilter();

    ByteString getFilterBytes();

    String getName();

    ByteString getNameBytes();

    int getPageSize();

    String getPageToken();

    ByteString getPageTokenBytes();
}
