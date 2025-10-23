package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class TooManyIterationsException extends MaxCountExceededException {
    private static final long serialVersionUID = 20121211;

    public TooManyIterationsException(Number number) {
        super(number);
        getContext().addMessage(LocalizedFormats.ITERATIONS, new Object[0]);
    }
}
