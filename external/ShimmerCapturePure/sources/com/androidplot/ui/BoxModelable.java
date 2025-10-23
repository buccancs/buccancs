package com.androidplot.ui;

import android.graphics.RectF;

/* loaded from: classes.dex */
public interface BoxModelable {
    float getMarginBottom();

    float getMarginLeft();

    void setMarginLeft(float f);

    float getMarginRight();

    void setMarginRight(float f);

    float getMarginTop();

    void setMarginTop(float f);

    RectF getMarginatedRect(RectF rectF);

    RectF getPaddedRect(RectF rectF);

    float getPaddingBottom();

    void setPaddingBottom(float f);

    float getPaddingLeft();

    void setPaddingLeft(float f);

    float getPaddingRight();

    void setPaddingRight(float f);

    float getPaddingTop();

    void setPaddingTop(float f);

    void setMargins(float f, float f2, float f3, float f4);

    void setPadding(float f, float f2, float f3, float f4);
}
