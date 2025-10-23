package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes.dex */
public interface ResourceReferenceOrBuilder extends MessageOrBuilder {
    String getChildType();

    ByteString getChildTypeBytes();

    String getType();

    ByteString getTypeBytes();
}
