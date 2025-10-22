package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/NotStrictlyPositiveException.class */
public class NotStrictlyPositiveException extends NumberIsTooSmallException {
    private static final long serialVersionUID = -7824848630829852237L;

    public NotStrictlyPositiveException(Number value) {
        super(value, 0, false);
    }

    public NotStrictlyPositiveException(Localizable specific, Number value) {
        super(specific, value, 0, false);
    }
}
