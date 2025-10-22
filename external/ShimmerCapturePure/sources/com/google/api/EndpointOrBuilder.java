package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes.dex */
public interface EndpointOrBuilder extends MessageOrBuilder {
    @Deprecated
    String getAliases(int i);

    @Deprecated
    ByteString getAliasesBytes(int i);

    @Deprecated
    int getAliasesCount();

    @Deprecated
        /* renamed from: getAliasesList */
    List<String> mo1261getAliasesList();

    boolean getAllowCors();

    String getFeatures(int i);

    ByteString getFeaturesBytes(int i);

    int getFeaturesCount();

    /* renamed from: getFeaturesList */
    List<String> mo1264getFeaturesList();

    String getName();

    ByteString getNameBytes();

    String getTarget();

    ByteString getTargetBytes();
}
