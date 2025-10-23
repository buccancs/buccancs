package org.apache.commons.math.linear;

import org.apache.commons.math.MathException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/NotPositiveDefiniteMatrixException.class */
public class NotPositiveDefiniteMatrixException extends MathException {
    private static final long serialVersionUID = 4122929125438624648L;

    public NotPositiveDefiniteMatrixException() {
        super(LocalizedFormats.NOT_POSITIVE_DEFINITE_MATRIX, new Object[0]);
    }
}
