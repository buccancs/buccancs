package org.apache.commons.math3.fraction;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class FractionConversionException extends ConvergenceException {
    private static final long serialVersionUID = -4661812640132576263L;

    public FractionConversionException(double d, int i) {
        super(LocalizedFormats.FAILED_FRACTION_CONVERSION, Double.valueOf(d), Integer.valueOf(i));
    }

    public FractionConversionException(double d, long j, long j2) {
        super(LocalizedFormats.FRACTION_CONVERSION_OVERFLOW, Double.valueOf(d), Long.valueOf(j), Long.valueOf(j2));
    }
}
