package com.androidplot.xy;

import android.graphics.Paint;

/* loaded from: classes.dex */
public class XValueMarker extends ValueMarker<YPositionMetric> {
    public XValueMarker(Number number, String str) {
        super(number, str, new YPositionMetric(3.0f, YLayoutStyle.ABSOLUTE_FROM_TOP));
    }

    public XValueMarker(Number number, String str, YPositionMetric yPositionMetric, Paint paint, Paint paint2) {
        super(number, str, yPositionMetric, paint, paint2);
    }

    public XValueMarker(Number number, String str, YPositionMetric yPositionMetric, int i, int i2) {
        super(number, str, yPositionMetric, i, i2);
    }
}
