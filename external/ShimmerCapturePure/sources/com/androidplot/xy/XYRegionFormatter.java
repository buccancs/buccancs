package com.androidplot.xy;

import android.graphics.Paint;

/* loaded from: classes.dex */
public class XYRegionFormatter {
    private Paint a;

    public XYRegionFormatter(int i) {
        Paint paint = new Paint();
        this.a = paint;
        paint.setColor(i);
        this.a.setStyle(Paint.Style.FILL);
        this.a.setAntiAlias(true);
    }

    public Paint getPaint() {
        return this.a;
    }

    public int getColor() {
        return this.a.getColor();
    }

    public void setColor(int i) {
        this.a.setColor(i);
    }
}
