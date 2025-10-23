package org.apache.commons.lang.enums;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.ClassUtils;

/* loaded from: classes5.dex */
public abstract class ValuedEnum extends Enum {
    private static final long serialVersionUID = -7129650521543789085L;
    private final int iValue;

    protected ValuedEnum(String str, int i) {
        super(str);
        this.iValue = i;
    }

    protected static Enum getEnum(Class cls, int i) {
        if (cls == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        }
        for (ValuedEnum valuedEnum : Enum.getEnumList(cls)) {
            if (valuedEnum.getValue() == i) {
                return valuedEnum;
            }
        }
        return null;
    }

    public final int getValue() {
        return this.iValue;
    }

    @Override // org.apache.commons.lang.enums.Enum, java.lang.Comparable
    public int compareTo(Object obj) {
        int i;
        int valueInOtherClassLoader;
        if (obj == this) {
            return 0;
        }
        if (obj.getClass() != getClass()) {
            if (obj.getClass().getName().equals(getClass().getName())) {
                i = this.iValue;
                valueInOtherClassLoader = getValueInOtherClassLoader(obj);
            } else {
                throw new ClassCastException(new StringBuffer("Different enum class '").append(ClassUtils.getShortClassName(obj.getClass())).append("'").toString());
            }
        } else {
            i = this.iValue;
            valueInOtherClassLoader = ((ValuedEnum) obj).iValue;
        }
        return i - valueInOtherClassLoader;
    }

    private int getValueInOtherClassLoader(Object obj) {
        try {
            return ((Integer) obj.getClass().getMethod("getValue", null).invoke(obj, null)).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new IllegalStateException("This should not happen");
        }
    }

    @Override // org.apache.commons.lang.enums.Enum
    public String toString() {
        if (this.iToString == null) {
            this.iToString = new StringBuffer().append(ClassUtils.getShortClassName(getEnumClass())).append("[").append(getName()).append("=").append(getValue()).append("]").toString();
        }
        return this.iToString;
    }
}
