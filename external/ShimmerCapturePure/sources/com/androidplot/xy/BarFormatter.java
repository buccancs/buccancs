package com.androidplot.xy;

import android.graphics.Paint;

/* loaded from: classes.dex */
public class BarFormatter extends XYSeriesFormatter {
    private Paint a;
    private Paint b;

    public BarFormatter(int i, int i2) {
        Paint paint = new Paint();
        this.a = paint;
        paint.setStyle(Paint.Style.FILL);
        this.a.setAlpha(100);
        Paint paint2 = new Paint();
        this.b = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.b.setAlpha(100);
        this.a.setColor(i);
        this.b.setColor(i2);
    }

    public Paint getBorderPaint() {
        return this.b;
    }

    public void setBorderPaint(Paint paint) {
        this.b = paint;
    }

    public Paint getFillPaint() {
        return this.a;
    }

    public void setFillPaint(Paint paint) {
        this.a = paint;
    }
}
