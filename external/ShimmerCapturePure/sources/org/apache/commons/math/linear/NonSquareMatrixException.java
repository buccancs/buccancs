package org.apache.commons.math.linear;

import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/NonSquareMatrixException.class */
public class NonSquareMatrixException extends InvalidMatrixException {
    private static final long serialVersionUID = 8996207526636673730L;

    public NonSquareMatrixException(int rows, int columns) {
        super(LocalizedFormats.NON_SQUARE_MATRIX, Integer.valueOf(rows), Integer.valueOf(columns));
    }
}
