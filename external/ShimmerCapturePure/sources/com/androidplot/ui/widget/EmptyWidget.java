package com.androidplot.ui.widget;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.ui.SizeMetrics;

/* loaded from: classes.dex */
public class EmptyWidget extends Widget {
    public EmptyWidget(SizeMetrics sizeMetrics) {
        super(sizeMetrics);
    }

    @Override // com.androidplot.ui.widget.Widget
    protected void doOnDraw(Canvas canvas, RectF rectF) throws PlotRenderException {
    }
}
