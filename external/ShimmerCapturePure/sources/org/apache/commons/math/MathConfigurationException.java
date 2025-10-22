package org.apache.commons.math;

import java.io.Serializable;

import org.apache.commons.math.exception.util.DummyLocalizable;
import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/MathConfigurationException.class */
public class MathConfigurationException extends MathException implements Serializable {
    private static final long serialVersionUID = 5261476508226103366L;

    public MathConfigurationException() {
    }

    public MathConfigurationException(String pattern, Object... arguments) {
        this(new DummyLocalizable(pattern), arguments);
    }

    public MathConfigurationException(Localizable pattern, Object... arguments) {
        super(pattern, arguments);
    }

    public MathConfigurationException(Throwable cause) {
        super(cause);
    }

    public MathConfigurationException(Throwable cause, String pattern, Object... arguments) {
        this(cause, new DummyLocalizable(pattern), arguments);
    }

    public MathConfigurationException(Throwable cause, Localizable pattern, Object... arguments) {
        super(cause, pattern, arguments);
    }
}
