package com.androidplot.util;

import android.content.Context;
import android.graphics.Paint;

/* loaded from: classes.dex */
public class PaintUtils {
    public static Paint getPaint() {
        return new Paint();
    }

    public static void setLineSizeDp(Context context, Paint paint, float f) {
        paint.setStrokeWidth(PixelUtils.dpToPix(context, f));
    }

    public static void setFontSizeDp(Context context, Paint paint, float f) {
        paint.setTextSize(PixelUtils.dpToPix(context, f));
    }

    public static void setShadowDp(Context context, Paint paint, float f, float f2, float f3, int i) {
        paint.setShadowLayer(PixelUtils.dpToPix(context, f), PixelUtils.dpToPix(context, f2), PixelUtils.dpToPix(context, f3), i);
    }
}
