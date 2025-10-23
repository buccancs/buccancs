package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.Localizable;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/ConvergenceException.class */
public class ConvergenceException extends MathIllegalStateException {
    private static final long serialVersionUID = 4330003017885151975L;

    public ConvergenceException() {
        this(null);
    }

    public ConvergenceException(Localizable specific) {
        this(specific, LocalizedFormats.CONVERGENCE_FAILED, null);
    }

    public ConvergenceException(Localizable specific, Object... args) {
        super(specific, LocalizedFormats.CONVERGENCE_FAILED, args);
    }
}
