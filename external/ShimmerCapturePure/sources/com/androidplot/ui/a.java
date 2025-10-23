package com.androidplot.ui;

import java.lang.Enum;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LayoutMetric.java */
/* loaded from: classes.dex */
public abstract class a<LayoutType extends Enum> {
    private LayoutType a;
    private float b;

    public a(float f, LayoutType layouttype) {
        a(f, layouttype);
        set(f, layouttype);
    }

    protected abstract void a(float f, LayoutType layouttype);

    public LayoutType getLayoutType() {
        return this.a;
    }

    public void setLayoutType(LayoutType layouttype) {
        a(this.b, layouttype);
        this.a = layouttype;
    }

    public abstract float getPixelValue(float f);

    public float getValue() {
        return this.b;
    }

    public void setValue(float f) {
        a(f, this.a);
        this.b = f;
    }

    public void set(float f, LayoutType layouttype) {
        a(f, layouttype);
        this.b = f;
        this.a = layouttype;
    }
}
