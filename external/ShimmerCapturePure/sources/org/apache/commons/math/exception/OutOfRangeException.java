package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/OutOfRangeException.class */
public class OutOfRangeException extends MathIllegalNumberException {
    private static final long serialVersionUID = 111601815794403609L;
    private final Number lo;
    private final Number hi;

    public OutOfRangeException(Number wrong, Number lo, Number hi) {
        super(LocalizedFormats.OUT_OF_RANGE_SIMPLE, wrong, lo, hi);
        this.lo = lo;
        this.hi = hi;
    }

    public Number getLo() {
        return this.lo;
    }

    public Number getHi() {
        return this.hi;
    }
}
