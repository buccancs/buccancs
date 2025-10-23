package org.apache.commons.math.random;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/AbstractWell.class */
public abstract class AbstractWell extends BitsStreamGenerator implements Serializable {
    private static final long serialVersionUID = -817701723016583596L;
    protected final int[] v;
    protected final int[] iRm1;
    protected final int[] iRm2;
    protected final int[] i1;
    protected final int[] i2;
    protected final int[] i3;
    protected int index;

    protected AbstractWell(int k, int m1, int m2, int m3) {
        this(k, m1, m2, m3, System.currentTimeMillis());
    }

    protected AbstractWell(int k, int m1, int m2, int m3, int seed) {
        this(k, m1, m2, m3, new int[]{seed});
    }

    protected AbstractWell(int k, int m1, int m2, int m3, int[] seed) {
        int r = ((k + 32) - 1) / 32;
        this.v = new int[r];
        this.index = 0;
        this.iRm1 = new int[r];
        this.iRm2 = new int[r];
        this.i1 = new int[r];
        this.i2 = new int[r];
        this.i3 = new int[r];
        for (int j = 0; j < r; j++) {
            this.iRm1[j] = ((j + r) - 1) % r;
            this.iRm2[j] = ((j + r) - 2) % r;
            this.i1[j] = (j + m1) % r;
            this.i2[j] = (j + m2) % r;
            this.i3[j] = (j + m3) % r;
        }
        setSeed(seed);
    }

    protected AbstractWell(int k, int m1, int m2, int m3, long seed) {
        this(k, m1, m2, m3, new int[]{(int) (seed >>> 32), (int) (seed & 4294967295L)});
    }

    @Override // org.apache.commons.math.random.BitsStreamGenerator
    protected abstract int next(int i);

    @Override // org.apache.commons.math.random.BitsStreamGenerator, org.apache.commons.math.random.RandomGenerator
    public void setSeed(int seed) {
        setSeed(new int[]{seed});
    }

    @Override // org.apache.commons.math.random.BitsStreamGenerator, org.apache.commons.math.random.RandomGenerator
    public void setSeed(int[] seed) {
        if (seed == null) {
            setSeed(System.currentTimeMillis());
            return;
        }
        System.arraycopy(seed, 0, this.v, 0, Math.min(seed.length, this.v.length));
        if (seed.length < this.v.length) {
            for (int i = seed.length; i < this.v.length; i++) {
                long l = this.v[i - seed.length];
                this.v[i] = (int) (((1812433253 * (l ^ (l >> 30))) + i) & 4294967295L);
            }
        }
        this.index = 0;
    }

    @Override // org.apache.commons.math.random.BitsStreamGenerator, org.apache.commons.math.random.RandomGenerator
    public void setSeed(long seed) {
        setSeed(new int[]{(int) (seed >>> 32), (int) (seed & 4294967295L)});
    }
}
