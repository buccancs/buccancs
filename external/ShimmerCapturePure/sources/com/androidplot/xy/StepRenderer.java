package com.androidplot.xy;

import android.graphics.Path;
import android.graphics.PointF;

/* loaded from: classes.dex */
public class StepRenderer extends LineAndPointRenderer<StepFormatter> {
    public StepRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    @Override // com.androidplot.xy.LineAndPointRenderer
    protected final void a(Path path, PointF pointF, PointF pointF2) {
        path.lineTo(pointF.x, pointF2.y);
        path.lineTo(pointF.x, pointF.y);
    }
}
