package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes5.dex */
public class MathIllegalNumberException extends MathIllegalArgumentException {
    protected static final Integer INTEGER_ZERO = 0;
    private static final long serialVersionUID = -7447085893598031110L;
    private final Number argument;

    protected MathIllegalNumberException(Localizable localizable, Number number, Object... objArr) {
        super(localizable, number, objArr);
        this.argument = number;
    }

    public Number getArgument() {
        return this.argument;
    }
}
