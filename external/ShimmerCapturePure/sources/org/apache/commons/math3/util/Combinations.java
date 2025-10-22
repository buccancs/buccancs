package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

/* loaded from: classes5.dex */
public class Combinations implements Iterable<int[]> {
    private final IterationOrder iterationOrder;
    private final int k;
    private final int n;

    public Combinations(int i, int i2) {
        this(i, i2, IterationOrder.LEXICOGRAPHIC);
    }

    private Combinations(int i, int i2, IterationOrder iterationOrder) throws NotPositiveException, NumberIsTooLargeException {
        CombinatoricsUtils.checkBinomial(i, i2);
        this.n = i;
        this.k = i2;
        this.iterationOrder = iterationOrder;
    }

    public int getK() {
        return this.k;
    }

    public int getN() {
        return this.n;
    }

    @Override // java.lang.Iterable
    public Iterator<int[]> iterator() {
        int i = this.k;
        if (i == 0 || i == this.n) {
            return new SingletonIterator(MathArrays.natural(i));
        }
        if (AnonymousClass1.$SwitchMap$org$apache$commons$math3$util$Combinations$IterationOrder[this.iterationOrder.ordinal()] == 1) {
            return new LexicographicIterator(this.n, this.k);
        }
        throw new MathInternalError();
    }

    public Comparator<int[]> comparator() {
        return new LexicographicComparator(this.n, this.k);
    }

    private enum IterationOrder {
        LEXICOGRAPHIC
    }

    /* renamed from: org.apache.commons.math3.util.Combinations$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$Combinations$IterationOrder;

        static {
            int[] iArr = new int[IterationOrder.values().length];
            $SwitchMap$org$apache$commons$math3$util$Combinations$IterationOrder = iArr;
            try {
                iArr[IterationOrder.LEXICOGRAPHIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static class LexicographicIterator implements Iterator<int[]> {
        private final int[] c;
        private final int k;
        private int j;
        private boolean more;

        LexicographicIterator(int i, int i2) {
            this.more = true;
            this.k = i2;
            this.c = new int[i2 + 3];
            if (i2 == 0 || i2 >= i) {
                this.more = false;
                return;
            }
            for (int i3 = 1; i3 <= i2; i3++) {
                this.c[i3] = i3 - 1;
            }
            int[] iArr = this.c;
            iArr[i2 + 1] = i;
            iArr[i2 + 2] = 0;
            this.j = i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.more;
        }

        @Override // java.util.Iterator
        public int[] next() {
            if (!this.more) {
                throw new NoSuchElementException();
            }
            int i = this.k;
            int[] iArr = new int[i];
            System.arraycopy(this.c, 1, iArr, 0, i);
            int i2 = this.j;
            if (i2 > 0) {
                this.c[i2] = i2;
                this.j = i2 - 1;
                return iArr;
            }
            int[] iArr2 = this.c;
            int i3 = iArr2[1];
            if (i3 + 1 < iArr2[2]) {
                iArr2[1] = i3 + 1;
                return iArr;
            }
            this.j = 2;
            boolean z = false;
            int i4 = 0;
            while (!z) {
                int[] iArr3 = this.c;
                int i5 = this.j;
                iArr3[i5 - 1] = i5 - 2;
                int i6 = iArr3[i5] + 1;
                if (i6 == iArr3[i5 + 1]) {
                    this.j = i5 + 1;
                    i4 = i6;
                } else {
                    i4 = i6;
                    z = true;
                }
            }
            int i7 = this.j;
            if (i7 > this.k) {
                this.more = false;
                return iArr;
            }
            this.c[i7] = i4;
            this.j = i7 - 1;
            return iArr;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class SingletonIterator implements Iterator<int[]> {
        private final int[] singleton;
        private boolean more = true;

        SingletonIterator(int[] iArr) {
            this.singleton = iArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.more;
        }

        @Override // java.util.Iterator
        public int[] next() {
            if (!this.more) {
                throw new NoSuchElementException();
            }
            this.more = false;
            return this.singleton;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class LexicographicComparator implements Comparator<int[]>, Serializable {
        private static final long serialVersionUID = 20130906;
        private final int k;
        private final int n;

        LexicographicComparator(int i, int i2) {
            this.n = i;
            this.k = i2;
        }

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int length = iArr.length;
            int i = this.k;
            if (length != i) {
                throw new DimensionMismatchException(iArr.length, this.k);
            }
            if (iArr2.length != i) {
                throw new DimensionMismatchException(iArr2.length, this.k);
            }
            int[] iArrCopyOf = MathArrays.copyOf(iArr);
            Arrays.sort(iArrCopyOf);
            int[] iArrCopyOf2 = MathArrays.copyOf(iArr2);
            Arrays.sort(iArrCopyOf2);
            long jLexNorm = lexNorm(iArrCopyOf);
            long jLexNorm2 = lexNorm(iArrCopyOf2);
            if (jLexNorm < jLexNorm2) {
                return -1;
            }
            return jLexNorm > jLexNorm2 ? 1 : 0;
        }

        private long lexNorm(int[] iArr) {
            int i;
            long jPow = 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                if (i3 < 0 || i3 >= (i = this.n)) {
                    throw new OutOfRangeException(Integer.valueOf(i3), 0, Integer.valueOf(this.n - 1));
                }
                jPow += i3 * ArithmeticUtils.pow(i, i2);
            }
            return jPow;
        }
    }
}
