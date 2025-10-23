package org.apache.commons.math.random;

import org.apache.commons.math.exception.NotStrictlyPositiveException;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/BitsStreamGenerator.class */
public abstract class BitsStreamGenerator implements RandomGenerator {
    private double nextGaussian = Double.NaN;

    @Override // org.apache.commons.math.random.RandomGenerator
    public abstract void setSeed(int i);

    @Override // org.apache.commons.math.random.RandomGenerator
    public abstract void setSeed(int[] iArr);

    @Override // org.apache.commons.math.random.RandomGenerator
    public abstract void setSeed(long j);

    protected abstract int next(int i);

    @Override // org.apache.commons.math.random.RandomGenerator
    public boolean nextBoolean() {
        return next(1) != 0;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void nextBytes(byte[] bytes) {
        int i = 0;
        int iEnd = bytes.length - 3;
        while (i < iEnd) {
            int random = next(32);
            bytes[i] = (byte) (random & 255);
            bytes[i + 1] = (byte) ((random >> 8) & 255);
            bytes[i + 2] = (byte) ((random >> 16) & 255);
            bytes[i + 3] = (byte) ((random >> 24) & 255);
            i += 4;
        }
        int next = next(32);
        while (true) {
            int random2 = next;
            if (i < bytes.length) {
                int i2 = i;
                i++;
                bytes[i2] = (byte) (random2 & 255);
                next = random2 >> 8;
            } else {
                return;
            }
        }
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public double nextDouble() {
        long high = next(26) << 26;
        int low = next(26);
        return (high | low) * 2.220446049250313E-16d;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public float nextFloat() {
        return next(23) * 1.1920929E-7f;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public double nextGaussian() {
        double random;
        if (Double.isNaN(this.nextGaussian)) {
            double x = nextDouble();
            double y = nextDouble();
            double alpha = 6.283185307179586d * x;
            double r = FastMath.sqrt((-2.0d) * FastMath.log(y));
            random = r * FastMath.cos(alpha);
            this.nextGaussian = r * FastMath.sin(alpha);
        } else {
            random = this.nextGaussian;
            this.nextGaussian = Double.NaN;
        }
        return random;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public int nextInt() {
        return next(32);
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public int nextInt(int n) throws IllegalArgumentException {
        int random;
        if (n < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(n));
        }
        int mask = n | (n >> 1);
        int mask2 = mask | (mask >> 2);
        int mask3 = mask2 | (mask2 >> 4);
        int mask4 = mask3 | (mask3 >> 8);
        int mask5 = mask4 | (mask4 >> 16);
        do {
            random = next(32) & mask5;
        } while (random >= n);
        return random;
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public long nextLong() {
        long high = next(32) << 32;
        long low = next(32) & 4294967295L;
        return high | low;
    }
}
