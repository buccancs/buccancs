package org.apache.commons.math.fraction;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/FractionConversionException.class */
public class FractionConversionException extends ConvergenceException {
    private static final long serialVersionUID = -4661812640132576263L;

    public FractionConversionException(double value, int maxIterations) {
        super(LocalizedFormats.FAILED_FRACTION_CONVERSION, Double.valueOf(value), Integer.valueOf(maxIterations));
    }

    public FractionConversionException(double value, long p, long q) {
        super(LocalizedFormats.FRACTION_CONVERSION_OVERFLOW, Double.valueOf(value), Long.valueOf(p), Long.valueOf(q));
    }
}
