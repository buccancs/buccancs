package org.apache.commons.math.random;

import org.apache.commons.math.exception.NotStrictlyPositiveException;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/AbstractRandomGenerator.class */
public abstract class AbstractRandomGenerator implements RandomGenerator {
    private double cachedNormalDeviate = Double.NaN;

    @Override // org.apache.commons.math.random.RandomGenerator
    public abstract void setSeed(long j);

    @Override // org.apache.commons.math.random.RandomGenerator
    public abstract double nextDouble();

    public void clear() {
        this.cachedNormalDeviate = Double.NaN;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void setSeed(int seed) {
        setSeed(seed);
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void setSeed(int[] seed) {
        long combined = 0;
        for (int s : seed) {
            combined = (combined * 4294967291L) + s;
        }
        setSeed(combined);
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void nextBytes(byte[] bytes) {
        int bytesOut = 0;
        while (bytesOut < bytes.length) {
            int randInt = nextInt();
            for (int i = 0; i < 3; i++) {
                if (i > 0) {
                    randInt >>= 8;
                }
                int i2 = bytesOut;
                bytesOut++;
                bytes[i2] = (byte) randInt;
                if (bytesOut == bytes.length) {
                    return;
                }
            }
        }
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public int nextInt() {
        return (int) (nextDouble() * 2.147483647E9d);
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public int nextInt(int n) {
        if (n <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(n));
        }
        int result = (int) (nextDouble() * n);
        return result < n ? result : n - 1;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public long nextLong() {
        return (long) (nextDouble() * 9.223372036854776E18d);
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public boolean nextBoolean() {
        return nextDouble() <= 0.5d;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public float nextFloat() {
        return (float) nextDouble();
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public double nextGaussian() {
        double s;
        if (!Double.isNaN(this.cachedNormalDeviate)) {
            double dev = this.cachedNormalDeviate;
            this.cachedNormalDeviate = Double.NaN;
            return dev;
        }
        double v1 = 0.0d;
        double v2 = 0.0d;
        double d = 1.0d;
        while (true) {
            s = d;
            if (s < 1.0d) {
                break;
            }
            v1 = (2.0d * nextDouble()) - 1.0d;
            v2 = (2.0d * nextDouble()) - 1.0d;
            d = (v1 * v1) + (v2 * v2);
        }
        if (s != 0.0d) {
            s = FastMath.sqrt(((-2.0d) * FastMath.log(s)) / s);
        }
        this.cachedNormalDeviate = v2 * s;
        return v1 * s;
    }
}
