package org.apache.commons.math.random;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/RandomGenerator.class */
public interface RandomGenerator {
    void setSeed(int i);

    void setSeed(int[] iArr);

    void setSeed(long j);

    void nextBytes(byte[] bArr);

    int nextInt();

    int nextInt(int i);

    long nextLong();

    boolean nextBoolean();

    float nextFloat();

    double nextDouble();

    double nextGaussian();
}
