package com.androidplot;

/* loaded from: classes.dex */
public class LineRegion {
    private Number a;
    private Number b;

    public LineRegion(Number number, Number number2) {
        setMinVal(number);
        setMaxVal(number2);
    }

    public Number getMaxVal() {
        return this.b;
    }

    public void setMaxVal(Number number) {
        if (number == null) {
            throw new NullPointerException("Region values can never be null.");
        }
        this.b = number;
    }

    public Number getMinVal() {
        return this.a;
    }

    public void setMinVal(Number number) {
        if (number == null) {
            throw new NullPointerException("Region values can never be null.");
        }
        this.a = number;
    }

    public boolean contains(Number number) {
        return number.doubleValue() >= this.a.doubleValue() && number.doubleValue() <= this.b.doubleValue();
    }

    public boolean intersects(LineRegion lineRegion) {
        return intersects(lineRegion.getMinVal(), lineRegion.getMaxVal());
    }

    public boolean intersects(Number number, Number number2) {
        return (number.doubleValue() <= this.a.doubleValue() && number2.doubleValue() >= this.b.doubleValue()) || contains(number) || contains(number2);
    }
}
