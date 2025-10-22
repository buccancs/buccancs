package org.apache.commons.math.util;

import java.io.Serializable;

import org.apache.commons.math.Field;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/util/BigRealField.class */
public class BigRealField implements Field<BigReal>, Serializable {
    private static final long serialVersionUID = 4756431066541037559L;

    private BigRealField() {
    }

    public static BigRealField getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math.Field
    public BigReal getOne() {
        return BigReal.ONE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math.Field
    public BigReal getZero() {
        return BigReal.ZERO;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/util/BigRealField$LazyHolder.class */
    private static class LazyHolder {
        private static final BigRealField INSTANCE = new BigRealField();

        private LazyHolder() {
        }
    }
}
