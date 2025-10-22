package org.apache.commons.lang.mutable;

import org.apache.commons.lang.math.NumberUtils;

/* loaded from: classes5.dex */
public class MutableDouble extends Number implements Comparable, Mutable {
    private static final long serialVersionUID = 1587163916;
    private double value;

    public MutableDouble() {
    }

    public MutableDouble(double d) {
        this.value = d;
    }

    public MutableDouble(Number number) {
        this.value = number.doubleValue();
    }

    public MutableDouble(String str) throws NumberFormatException {
        this.value = Double.parseDouble(str);
    }

    public void add(double d) {
        this.value += d;
    }

    public void decrement() {
        this.value -= 1.0d;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) this.value;
    }

    public void increment() {
        this.value += 1.0d;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) this.value;
    }

    public void subtract(double d) {
        this.value -= d;
    }

    @Override // org.apache.commons.lang.mutable.Mutable
    public Object getValue() {
        return new Double(this.value);
    }

    public void setValue(double d) {
        this.value = d;
    }

    @Override // org.apache.commons.lang.mutable.Mutable
    public void setValue(Object obj) {
        setValue(((Number) obj).doubleValue());
    }

    public boolean isNaN() {
        return Double.isNaN(this.value);
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.value);
    }

    public void add(Number number) {
        this.value += number.doubleValue();
    }

    public void subtract(Number number) {
        this.value -= number.doubleValue();
    }

    public Double toDouble() {
        return new Double(doubleValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableDouble) && Double.doubleToLongBits(((MutableDouble) obj).value) == Double.doubleToLongBits(this.value);
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.value);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return NumberUtils.compare(this.value, ((MutableDouble) obj).value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
