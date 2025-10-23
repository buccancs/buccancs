package org.apache.commons.math3.random;

/* loaded from: classes5.dex */
public class SynchronizedRandomGenerator implements RandomGenerator {
    private final RandomGenerator wrapped;

    public SynchronizedRandomGenerator(RandomGenerator randomGenerator) {
        this.wrapped = randomGenerator;
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized void setSeed(int i) {
        this.wrapped.setSeed(i);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized void setSeed(int[] iArr) {
        this.wrapped.setSeed(iArr);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized void setSeed(long j) {
        this.wrapped.setSeed(j);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized void nextBytes(byte[] bArr) {
        this.wrapped.nextBytes(bArr);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized int nextInt() {
        return this.wrapped.nextInt();
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized int nextInt(int i) {
        return this.wrapped.nextInt(i);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized long nextLong() {
        return this.wrapped.nextLong();
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized boolean nextBoolean() {
        return this.wrapped.nextBoolean();
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized float nextFloat() {
        return this.wrapped.nextFloat();
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized double nextDouble() {
        return this.wrapped.nextDouble();
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public synchronized double nextGaussian() {
        return this.wrapped.nextGaussian();
    }
}
