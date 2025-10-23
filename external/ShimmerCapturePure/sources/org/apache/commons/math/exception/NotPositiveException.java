package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/NotPositiveException.class */
public class NotPositiveException extends NumberIsTooSmallException {
    private static final long serialVersionUID = -2250556892093726375L;

    public NotPositiveException(Number value) {
        super(value, 0, true);
    }

    public NotPositiveException(Localizable specific, Number value) {
        super(specific, value, 0, true);
    }
}
