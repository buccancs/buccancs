package com.androidplot.ui;

import com.androidplot.xy.XLayoutStyle;
import com.androidplot.xy.XPositionMetric;
import com.androidplot.xy.YLayoutStyle;
import com.androidplot.xy.YPositionMetric;

/* loaded from: classes.dex */
public class PositionMetrics implements Comparable<PositionMetrics> {
    private XPositionMetric a;
    private YPositionMetric b;
    private AnchorPosition c;

    public PositionMetrics(float f, XLayoutStyle xLayoutStyle, float f2, YLayoutStyle yLayoutStyle, AnchorPosition anchorPosition) {
        setxPositionMetric(new XPositionMetric(f, xLayoutStyle));
        setyPositionMetric(new YPositionMetric(f2, yLayoutStyle));
        setAnchor(anchorPosition);
    }

    @Override // java.lang.Comparable
    public int compareTo(PositionMetrics positionMetrics) {
        return 0;
    }

    public AnchorPosition getAnchor() {
        return this.c;
    }

    public void setAnchor(AnchorPosition anchorPosition) {
        this.c = anchorPosition;
    }

    public XPositionMetric getxPositionMetric() {
        return this.a;
    }

    public void setxPositionMetric(XPositionMetric xPositionMetric) {
        this.a = xPositionMetric;
    }

    public YPositionMetric getyPositionMetric() {
        return this.b;
    }

    public void setyPositionMetric(YPositionMetric yPositionMetric) {
        this.b = yPositionMetric;
    }
}
