package com.androidplot.ui;

import android.graphics.RectF;
import com.androidplot.util.PixelUtils;

/* loaded from: classes.dex */
public class SizeMetrics {
    private SizeMetric a;
    private SizeMetric b;

    public SizeMetrics(float f, SizeLayoutType sizeLayoutType, float f2, SizeLayoutType sizeLayoutType2) {
        this.a = new SizeMetric(f, sizeLayoutType);
        this.b = new SizeMetric(f2, sizeLayoutType2);
    }

    public SizeMetrics(SizeMetric sizeMetric, SizeMetric sizeMetric2) {
        this.a = sizeMetric;
        this.b = sizeMetric2;
    }

    public SizeMetric getHeightMetric() {
        return this.a;
    }

    public void setHeightMetric(SizeMetric sizeMetric) {
        this.a = sizeMetric;
    }

    public SizeMetric getWidthMetric() {
        return this.b;
    }

    public void setWidthMetric(SizeMetric sizeMetric) {
        this.b = sizeMetric;
    }

    public RectF getRectF(RectF rectF) {
        return new RectF(0.0f, 0.0f, this.b.getPixelValue(rectF.width()), this.a.getPixelValue(rectF.height()));
    }

    public RectF getRoundedRect(RectF rectF) {
        return PixelUtils.nearestPixRect(0.0f, 0.0f, this.b.getPixelValue(rectF.width()), this.a.getPixelValue(rectF.height()));
    }
}
