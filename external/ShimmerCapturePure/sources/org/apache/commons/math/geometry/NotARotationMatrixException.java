package org.apache.commons.math.geometry;

import org.apache.commons.math.MathException;
import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/geometry/NotARotationMatrixException.class */
public class NotARotationMatrixException extends MathException {
    private static final long serialVersionUID = 5647178478658937642L;

    @Deprecated
    public NotARotationMatrixException(String specifier, Object... parts) {
        super(specifier, parts);
    }

    public NotARotationMatrixException(Localizable specifier, Object... parts) {
        super(specifier, parts);
    }
}
