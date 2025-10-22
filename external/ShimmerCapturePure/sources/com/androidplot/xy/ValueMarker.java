package com.androidplot.xy;

import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import com.androidplot.ui.PositionMetric;

/* loaded from: classes.dex */
public abstract class ValueMarker<PositionMetricType extends PositionMetric> {
    private Number a;
    private Paint b;
    private Paint c;
    private TextOrientation d;
    private int e;
    private PositionMetricType f;
    private String g;

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype) {
        this.e = 2;
        Paint paint = new Paint();
        this.b = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.b.setAntiAlias(true);
        this.b.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.c = paint2;
        paint2.setAntiAlias(true);
        this.c.setColor(SupportMenu.CATEGORY_MASK);
        this.a = number;
        this.f = positionmetrictype;
        this.g = str;
    }

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype, Paint paint, Paint paint2) {
        this(number, str, positionmetrictype);
        this.b = paint;
        this.c = paint2;
    }

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype, int i, int i2) {
        this(number, str, positionmetrictype);
        this.b.setColor(i);
        this.c.setColor(i2);
    }

    public Paint getLinePaint() {
        return this.b;
    }

    public void setLinePaint(Paint paint) {
        this.b = paint;
    }

    public String getText() {
        return this.g;
    }

    public void setText(String str) {
        this.g = str;
    }

    public int getTextMargin() {
        return this.e;
    }

    public void setTextMargin(int i) {
        this.e = i;
    }

    public TextOrientation getTextOrientation() {
        return this.d;
    }

    public void setTextOrientation(TextOrientation textOrientation) {
        this.d = textOrientation;
    }

    public Paint getTextPaint() {
        return this.c;
    }

    public void setTextPaint(Paint paint) {
        this.c = paint;
    }

    public PositionMetricType getTextPosition() {
        return this.f;
    }

    public void setTextPosition(PositionMetricType positionmetrictype) {
        this.f = positionmetrictype;
    }

    public Number getValue() {
        return this.a;
    }

    public void setValue(Number number) {
        this.a = number;
    }

    public enum TextOrientation {
        HORIZONTAL,
        VERTICAL
    }
}
