package com.google.api;

import com.google.api.ResourceDescriptor;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes.dex */
public interface ResourceDescriptorOrBuilder extends MessageOrBuilder {
    ResourceDescriptor.History getHistory();

    int getHistoryValue();

    String getNameField();

    ByteString getNameFieldBytes();

    String getPattern(int i);

    ByteString getPatternBytes(int i);

    int getPatternCount();

    /* renamed from: getPatternList */
    List<String> mo2331getPatternList();

    String getPlural();

    ByteString getPluralBytes();

    String getSingular();

    ByteString getSingularBytes();

    String getType();

    ByteString getTypeBytes();
}
