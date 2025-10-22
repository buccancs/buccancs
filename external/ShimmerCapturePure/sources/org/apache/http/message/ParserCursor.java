package org.apache.http.message;

import kotlin.text.Typography;

/* loaded from: classes5.dex */
public class ParserCursor {
    private final int lowerBound;
    private final int upperBound;
    private int pos;

    public ParserCursor(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.lowerBound = i;
        this.upperBound = i2;
        this.pos = i;
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getPos() {
        return this.pos;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public void updatePos(int i) {
        if (i < this.lowerBound) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.lowerBound);
        }
        if (i <= this.upperBound) {
            this.pos = i;
            return;
        }
        throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.upperBound);
    }

    public String toString() {
        return "[" + Integer.toString(this.lowerBound) + Typography.greater + Integer.toString(this.pos) + Typography.greater + Integer.toString(this.upperBound) + ']';
    }
}
