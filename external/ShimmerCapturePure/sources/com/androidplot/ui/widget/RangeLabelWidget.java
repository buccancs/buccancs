package com.androidplot.ui.widget;

import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;
import com.androidplot.xy.XYPlot;

/* loaded from: classes.dex */
public class RangeLabelWidget extends TextLabelWidget {
    private XYPlot a;

    public RangeLabelWidget(XYPlot xYPlot, SizeMetrics sizeMetrics, TextOrientationType textOrientationType) {
        super(sizeMetrics, textOrientationType);
        this.a = xYPlot;
    }

    @Override // com.androidplot.ui.widget.TextLabelWidget
    protected final String a() {
        return this.a.getRangeLabel();
    }
}
