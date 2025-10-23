package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.Localizable;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/NullArgumentException.class */
public class NullArgumentException extends MathIllegalArgumentException {
    private static final long serialVersionUID = -6024911025449780478L;

    public NullArgumentException() {
        super(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
    }

    public NullArgumentException(Localizable specific) {
        super(specific, LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
    }
}
