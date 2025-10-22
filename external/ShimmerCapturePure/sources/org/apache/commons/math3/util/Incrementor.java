package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IntegerSequence;

@Deprecated
/* loaded from: classes5.dex */
public class Incrementor {
    private final MaxCountExceededCallback maxCountCallback;
    private int count;
    private int maximalCount;

    public Incrementor() {
        this(0);
    }

    public Incrementor(int i) {
        this(i, new MaxCountExceededCallback() { // from class: org.apache.commons.math3.util.Incrementor.1
            @Override // org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback
            public void trigger(int i2) throws MaxCountExceededException {
                throw new MaxCountExceededException(Integer.valueOf(i2));
            }
        });
    }

    public Incrementor(int i, MaxCountExceededCallback maxCountExceededCallback) throws NullArgumentException {
        this.count = 0;
        if (maxCountExceededCallback == null) {
            throw new NullArgumentException();
        }
        this.maximalCount = i;
        this.maxCountCallback = maxCountExceededCallback;
    }

    public static Incrementor wrap(IntegerSequence.Incrementor incrementor) {
        return new Incrementor() { // from class: org.apache.commons.math3.util.Incrementor.2
            private IntegerSequence.Incrementor delegate;

            {
                this.delegate = this.val$incrementor;
                super.setMaximalCount(this.val$incrementor.getMaximalCount());
                super.incrementCount(this.delegate.getCount());
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void setMaximalCount(int i) {
                super.setMaximalCount(i);
                this.delegate = this.delegate.withMaximalCount(i);
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void resetCount() {
                super.resetCount();
                this.delegate = this.delegate.withStart(0);
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void incrementCount() throws MaxCountExceededException {
                super.incrementCount();
                this.delegate.increment();
            }
        };
    }

    public boolean canIncrement() {
        return this.count < this.maximalCount;
    }

    public int getCount() {
        return this.count;
    }

    public int getMaximalCount() {
        return this.maximalCount;
    }

    public void setMaximalCount(int i) {
        this.maximalCount = i;
    }

    public void resetCount() {
        this.count = 0;
    }

    public void incrementCount(int i) throws MaxCountExceededException {
        for (int i2 = 0; i2 < i; i2++) {
            incrementCount();
        }
    }

    public void incrementCount() throws MaxCountExceededException {
        int i = this.count + 1;
        this.count = i;
        int i2 = this.maximalCount;
        if (i > i2) {
            this.maxCountCallback.trigger(i2);
        }
    }

    public interface MaxCountExceededCallback {
        void trigger(int i) throws MaxCountExceededException;
    }
}
