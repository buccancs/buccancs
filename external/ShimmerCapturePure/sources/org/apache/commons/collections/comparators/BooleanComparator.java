package org.apache.commons.collections.comparators;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes5.dex */
public final class BooleanComparator implements Comparator, Serializable {
    private static final long serialVersionUID = 1830042991606340609L;
    private static final BooleanComparator TRUE_FIRST = new BooleanComparator(true);
    private static final BooleanComparator FALSE_FIRST = new BooleanComparator(false);
    private boolean trueFirst;

    public BooleanComparator() {
        this(false);
    }

    public BooleanComparator(boolean z) {
        this.trueFirst = z;
    }

    public static BooleanComparator getBooleanComparator(boolean z) {
        return z ? TRUE_FIRST : FALSE_FIRST;
    }

    public static BooleanComparator getFalseFirstComparator() {
        return FALSE_FIRST;
    }

    public static BooleanComparator getTrueFirstComparator() {
        return TRUE_FIRST;
    }

    public int hashCode() {
        return this.trueFirst ? -478003966 : 478003966;
    }

    public boolean sortsTrueFirst() {
        return this.trueFirst;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return compare((Boolean) obj, (Boolean) obj2);
    }

    public int compare(Boolean bool, Boolean bool2) {
        boolean zBooleanValue = bool.booleanValue();
        if (bool2.booleanValue() ^ zBooleanValue) {
            return zBooleanValue ^ this.trueFirst ? 1 : -1;
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof BooleanComparator) && this.trueFirst == ((BooleanComparator) obj).trueFirst);
    }
}
