package org.apache.commons.math3.random;

/* loaded from: classes5.dex */
public interface RandomGenerator {
    boolean nextBoolean();

    void nextBytes(byte[] bArr);

    double nextDouble();

    float nextFloat();

    double nextGaussian();

    int nextInt();

    int nextInt(int i);

    long nextLong();

    void setSeed(int i);

    void setSeed(long j);

    void setSeed(int[] iArr);
}
