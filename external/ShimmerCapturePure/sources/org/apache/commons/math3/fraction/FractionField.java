package org.apache.commons.math3.fraction;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

/* loaded from: classes5.dex */
public class FractionField implements Field<Fraction>, Serializable {
    private static final long serialVersionUID = -1257768487499119313L;

    private FractionField() {
    }

    public static FractionField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override // org.apache.commons.math3.Field
    public Fraction getOne() {
        return Fraction.ONE;
    }

    @Override // org.apache.commons.math3.Field
    public Fraction getZero() {
        return Fraction.ZERO;
    }

    @Override // org.apache.commons.math3.Field
    public Class<? extends FieldElement<Fraction>> getRuntimeClass() {
        return Fraction.class;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final FractionField INSTANCE = new FractionField();

        private LazyHolder() {
        }
    }
}
