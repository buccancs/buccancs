package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class NonMonotonicSequenceException extends MathIllegalNumberException {
    private static final long serialVersionUID = 3596849179428944575L;
    private final MathArrays.OrderDirection direction;
    private final int index;
    private final Number previous;
    private final boolean strict;

    public NonMonotonicSequenceException(Number number, Number number2, int i) {
        this(number, number2, i, MathArrays.OrderDirection.INCREASING, true);
    }

    public NonMonotonicSequenceException(Number number, Number number2, int i, MathArrays.OrderDirection orderDirection, boolean z) {
        super(orderDirection == MathArrays.OrderDirection.INCREASING ? z ? LocalizedFormats.NOT_STRICTLY_INCREASING_SEQUENCE : LocalizedFormats.NOT_INCREASING_SEQUENCE : z ? LocalizedFormats.NOT_STRICTLY_DECREASING_SEQUENCE : LocalizedFormats.NOT_DECREASING_SEQUENCE, number, number2, Integer.valueOf(i), Integer.valueOf(i - 1));
        this.direction = orderDirection;
        this.strict = z;
        this.index = i;
        this.previous = number2;
    }

    public MathArrays.OrderDirection getDirection() {
        return this.direction;
    }

    public int getIndex() {
        return this.index;
    }

    public Number getPrevious() {
        return this.previous;
    }

    public boolean getStrict() {
        return this.strict;
    }
}
