package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

/* loaded from: classes5.dex */
public interface BinomialConfidenceInterval {
    ConfidenceInterval createInterval(int i, int i2, double d) throws OutOfRangeException, NotStrictlyPositiveException, NotPositiveException, NumberIsTooLargeException;
}
