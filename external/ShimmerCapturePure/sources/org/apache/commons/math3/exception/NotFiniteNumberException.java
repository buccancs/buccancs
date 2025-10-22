package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class NotFiniteNumberException extends MathIllegalNumberException {
    private static final long serialVersionUID = -6100997100383932834L;

    public NotFiniteNumberException(Number number, Object... objArr) {
        this(LocalizedFormats.NOT_FINITE_NUMBER, number, objArr);
    }

    public NotFiniteNumberException(Localizable localizable, Number number, Object... objArr) {
        super(localizable, number, objArr);
    }
}
