package com.androidplot.xy;

import android.graphics.Path;
import android.graphics.PointF;

/* loaded from: classes.dex */
public class BezierLineAndPointRenderer extends LineAndPointRenderer<BezierLineAndPointFormatter> {
    public BezierLineAndPointRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    @Override // com.androidplot.xy.LineAndPointRenderer
    protected final void a(Path path, PointF pointF, PointF pointF2) {
        PointF pointF3 = new PointF();
        pointF3.set((pointF2.x + pointF.x) / 2.0f, (pointF2.y + pointF.y) / 2.0f);
        path.quadTo((pointF2.x + pointF3.x) / 2.0f, pointF2.y, pointF3.x, pointF3.y);
        path.quadTo((pointF3.x + pointF.x) / 2.0f, pointF2.y, pointF.x, pointF.y);
    }
}
