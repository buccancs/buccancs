package org.apache.commons.math.complex;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/complex/ComplexUtils.class */
public class ComplexUtils {
    private ComplexUtils() {
    }

    public static Complex polar2Complex(double r, double theta) {
        if (r < 0.0d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NEGATIVE_COMPLEX_MODULE, Double.valueOf(r));
        }
        return new Complex(r * FastMath.cos(theta), r * FastMath.sin(theta));
    }
}
