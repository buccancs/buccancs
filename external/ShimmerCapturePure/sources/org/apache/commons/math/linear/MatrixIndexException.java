package org.apache.commons.math.linear;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.DummyLocalizable;
import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/MatrixIndexException.class */
public class MatrixIndexException extends MathRuntimeException {
    private static final long serialVersionUID = 8120540015829487660L;

    @Deprecated
    public MatrixIndexException(String pattern, Object... arguments) {
        this(new DummyLocalizable(pattern), arguments);
    }

    public MatrixIndexException(Localizable pattern, Object... arguments) {
        super(pattern, arguments);
    }
}
