package org.apache.commons.math.linear;

import org.apache.commons.math.MathException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/NotSymmetricMatrixException.class */
public class NotSymmetricMatrixException extends MathException {
    private static final long serialVersionUID = -7012803946709786097L;

    public NotSymmetricMatrixException() {
        super(LocalizedFormats.NOT_SYMMETRIC_MATRIX, new Object[0]);
    }
}
