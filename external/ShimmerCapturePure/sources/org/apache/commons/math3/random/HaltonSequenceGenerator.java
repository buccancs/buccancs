package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathUtils;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class HaltonSequenceGenerator implements RandomVectorGenerator {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 173};
    private static final int[] WEIGHTS = {1, 2, 3, 3, 8, 11, 12, 14, 7, 18, 12, 13, 17, 18, 29, 14, 18, 43, 41, 44, 40, 30, 47, 65, 71, 28, 40, 60, 79, 89, 56, 50, 52, 61, 108, 56, 66, 63, 60, 66};
    private final int[] base;
    private final int dimension;
    private final int[] weight;
    private int count;

    public HaltonSequenceGenerator(int i) throws OutOfRangeException {
        this(i, PRIMES, WEIGHTS);
    }

    public HaltonSequenceGenerator(int i, int[] iArr, int[] iArr2) throws OutOfRangeException, NullArgumentException, DimensionMismatchException {
        this.count = 0;
        MathUtils.checkNotNull(iArr);
        if (i < 1 || i > iArr.length) {
            throw new OutOfRangeException(Integer.valueOf(i), 1, Integer.valueOf(PRIMES.length));
        }
        if (iArr2 != null && iArr2.length != iArr.length) {
            throw new DimensionMismatchException(iArr2.length, iArr.length);
        }
        this.dimension = i;
        this.base = (int[]) iArr.clone();
        this.weight = iArr2 == null ? null : (int[]) iArr2.clone();
        this.count = 0;
    }

    public int getNextIndex() {
        return this.count;
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        double[] dArr = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            int i2 = this.count;
            double d = this.base[i];
            double d2 = 1.0d;
            while (true) {
                d2 /= d;
                if (i2 > 0) {
                    int i3 = this.base[i];
                    dArr[i] = dArr[i] + (scramble(i, 0, i3, i2 % i3) * d2);
                    int i4 = this.base[i];
                    i2 /= i4;
                    d = i4;
                }
            }
        }
        this.count++;
        return dArr;
    }

    protected int scramble(int i, int i2, int i3, int i4) {
        int[] iArr = this.weight;
        return iArr != null ? (iArr[i] * i4) % i3 : i4;
    }

    public double[] skipTo(int i) throws NotPositiveException {
        this.count = i;
        return nextVector();
    }
}
