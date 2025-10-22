package com.androidplot.ui.widget;

import com.androidplot.Plot;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;

/* loaded from: classes.dex */
public class TitleWidget extends TextLabelWidget {
    private Plot a;

    public TitleWidget(Plot plot, SizeMetrics sizeMetrics, TextOrientationType textOrientationType) {
        super(sizeMetrics, textOrientationType);
        this.a = plot;
        getLabelPaint().setTextSize(14.0f);
    }

    @Override // com.androidplot.ui.widget.TextLabelWidget
    protected final String a() {
        return this.a.getTitle();
    }
}
