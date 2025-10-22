package org.apache.commons.lang3.mutable;

import java.io.Serializable;

import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes5.dex */
public class MutableBoolean implements Mutable<Boolean>, Serializable, Comparable<MutableBoolean> {
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;

    public MutableBoolean() {
    }

    public MutableBoolean(boolean z) {
        this.value = z;
    }

    public MutableBoolean(Boolean bool) {
        this.value = bool.booleanValue();
    }

    public boolean booleanValue() {
        return this.value;
    }

    public boolean isFalse() {
        return !this.value;
    }

    public boolean isTrue() {
        return this.value;
    }

    public void setFalse() {
        this.value = false;
    }

    public void setTrue() {
        this.value = true;
    }

    public void setValue(boolean z) {
        this.value = z;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Boolean getValue2() {
        return Boolean.valueOf(this.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Boolean bool) {
        this.value = bool.booleanValue();
    }

    public Boolean toBoolean() {
        return Boolean.valueOf(booleanValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableBoolean) && this.value == ((MutableBoolean) obj).booleanValue();
    }

    public int hashCode() {
        return (this.value ? Boolean.TRUE : Boolean.FALSE).hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableBoolean mutableBoolean) {
        return BooleanUtils.compare(this.value, mutableBoolean.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
