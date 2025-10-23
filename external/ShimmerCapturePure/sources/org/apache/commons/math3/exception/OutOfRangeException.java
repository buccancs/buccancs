package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class OutOfRangeException extends MathIllegalNumberException {
    private static final long serialVersionUID = 111601815794403609L;
    private final Number hi;
    private final Number lo;

    public OutOfRangeException(Number number, Number number2, Number number3) {
        this(LocalizedFormats.OUT_OF_RANGE_SIMPLE, number, number2, number3);
    }

    public OutOfRangeException(Localizable localizable, Number number, Number number2, Number number3) {
        super(localizable, number, number2, number3);
        this.lo = number2;
        this.hi = number3;
    }

    public Number getHi() {
        return this.hi;
    }

    public Number getLo() {
        return this.lo;
    }
}
