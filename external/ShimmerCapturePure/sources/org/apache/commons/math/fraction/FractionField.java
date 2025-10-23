package org.apache.commons.math.fraction;

import java.io.Serializable;

import org.apache.commons.math.Field;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/FractionField.class */
public class FractionField implements Field<Fraction>, Serializable {
    private static final long serialVersionUID = -1257768487499119313L;

    private FractionField() {
    }

    public static FractionField getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math.Field
    public Fraction getOne() {
        return Fraction.ONE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math.Field
    public Fraction getZero() {
        return Fraction.ZERO;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/FractionField$LazyHolder.class */
    private static class LazyHolder {
        private static final FractionField INSTANCE = new FractionField();

        private LazyHolder() {
        }
    }
}
