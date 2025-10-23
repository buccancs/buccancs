package com.androidplot.xy;

/* loaded from: classes.dex */
public class XYStep {
    private final float a;
    private final float b;
    private final double c;

    public XYStep(float f, float f2, double d) {
        this.a = f;
        this.b = f2;
        this.c = d;
    }

    public double getStepCount() {
        return this.a;
    }

    public float getStepPix() {
        return this.b;
    }

    public double getStepVal() {
        return this.c;
    }
}
