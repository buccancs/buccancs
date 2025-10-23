package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.Localizable;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/ZeroException.class */
public class ZeroException extends MathIllegalNumberException {
    private static final long serialVersionUID = -1960874856936000015L;

    public ZeroException() {
        this(null);
    }

    public ZeroException(Localizable specific) {
        super(specific, LocalizedFormats.ZERO_NOT_ALLOWED, 0, new Object[0]);
    }
}
