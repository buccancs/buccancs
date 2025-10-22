package org.apache.commons.math.random;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/RandomAdaptor.class */
public class RandomAdaptor extends Random implements RandomGenerator {
    private static final long serialVersionUID = 2306581345647615033L;
    private final RandomGenerator randomGenerator;

    private RandomAdaptor() {
        this.randomGenerator = null;
    }

    public RandomAdaptor(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public static Random createAdaptor(RandomGenerator randomGenerator) {
        return new RandomAdaptor(randomGenerator);
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public boolean nextBoolean() {
        return this.randomGenerator.nextBoolean();
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public void nextBytes(byte[] bytes) {
        this.randomGenerator.nextBytes(bytes);
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public double nextDouble() {
        return this.randomGenerator.nextDouble();
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public float nextFloat() {
        return this.randomGenerator.nextFloat();
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public double nextGaussian() {
        return this.randomGenerator.nextGaussian();
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public int nextInt() {
        return this.randomGenerator.nextInt();
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public int nextInt(int n) {
        return this.randomGenerator.nextInt(n);
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public long nextLong() {
        return this.randomGenerator.nextLong();
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void setSeed(int seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }

    @Override // org.apache.commons.math.random.RandomGenerator
    public void setSeed(int[] seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }

    @Override // java.util.Random, org.apache.commons.math.random.RandomGenerator
    public void setSeed(long seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }
}
