package org.apache.commons.lang.mutable;

/* loaded from: classes5.dex */
public class MutableLong extends Number implements Comparable, Mutable {
    private static final long serialVersionUID = 62986528375L;
    private long value;

    public MutableLong() {
    }

    public MutableLong(long j) {
        this.value = j;
    }

    public MutableLong(Number number) {
        this.value = number.longValue();
    }

    public MutableLong(String str) throws NumberFormatException {
        this.value = Long.parseLong(str);
    }

    public void add(long j) {
        this.value += j;
    }

    public void decrement() {
        this.value--;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }

    public void increment() {
        this.value++;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(long j) {
        this.value -= j;
    }

    @Override // org.apache.commons.lang.mutable.Mutable
    public Object getValue() {
        return new Long(this.value);
    }

    public void setValue(long j) {
        this.value = j;
    }

    @Override // org.apache.commons.lang.mutable.Mutable
    public void setValue(Object obj) {
        setValue(((Number) obj).longValue());
    }

    public void add(Number number) {
        this.value += number.longValue();
    }

    public void subtract(Number number) {
        this.value -= number.longValue();
    }

    public Long toLong() {
        return new Long(longValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableLong) && this.value == ((MutableLong) obj).longValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        long j = ((MutableLong) obj).value;
        long j2 = this.value;
        if (j2 < j) {
            return -1;
        }
        return j2 == j ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
