package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class NonSquareOperatorException extends DimensionMismatchException {
    private static final long serialVersionUID = -4145007524150846242L;

    public NonSquareOperatorException(int i, int i2) {
        super(LocalizedFormats.NON_SQUARE_OPERATOR, i, i2);
    }
}
