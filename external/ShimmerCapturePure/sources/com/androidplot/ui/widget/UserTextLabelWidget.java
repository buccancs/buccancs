package com.androidplot.ui.widget;

import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;

/* loaded from: classes.dex */
public class UserTextLabelWidget extends TextLabelWidget {
    private String a;

    public UserTextLabelWidget(String str, SizeMetrics sizeMetrics, TextOrientationType textOrientationType) {
        super(sizeMetrics, textOrientationType);
        this.a = str;
    }

    @Override // com.androidplot.ui.widget.TextLabelWidget
    protected final String a() {
        return this.a;
    }
}
