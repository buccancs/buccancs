package com.google.type;

import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface ColorOrBuilder extends MessageOrBuilder {
    FloatValue getAlpha();

    FloatValueOrBuilder getAlphaOrBuilder();

    float getBlue();

    float getGreen();

    float getRed();

    boolean hasAlpha();
}
