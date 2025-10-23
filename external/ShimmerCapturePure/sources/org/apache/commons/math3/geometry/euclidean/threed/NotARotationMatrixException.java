package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes5.dex */
public class NotARotationMatrixException extends MathIllegalArgumentException {
    private static final long serialVersionUID = 5647178478658937642L;

    public NotARotationMatrixException(Localizable localizable, Object... objArr) {
        super(localizable, objArr);
    }
}
