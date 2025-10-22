package org.apache.commons.math3.random;

import java.util.Random;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;

/* loaded from: classes5.dex */
public class RandomGeneratorFactory {
    private RandomGeneratorFactory() {
    }

    public static RandomGenerator createRandomGenerator(final Random random) {
        return new RandomGenerator() { // from class: org.apache.commons.math3.random.RandomGeneratorFactory.1
            @Override // org.apache.commons.math3.random.RandomGenerator
            public void setSeed(int i) {
                random.setSeed(i);
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public void setSeed(int[] iArr) {
                random.setSeed(RandomGeneratorFactory.convertToLong(iArr));
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public void setSeed(long j) {
                random.setSeed(j);
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public void nextBytes(byte[] bArr) {
                random.nextBytes(bArr);
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public int nextInt() {
                return random.nextInt();
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public int nextInt(int i) {
                if (i <= 0) {
                    throw new NotStrictlyPositiveException(Integer.valueOf(i));
                }
                return random.nextInt(i);
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public long nextLong() {
                return random.nextLong();
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public boolean nextBoolean() {
                return random.nextBoolean();
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public float nextFloat() {
                return random.nextFloat();
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public double nextDouble() {
                return random.nextDouble();
            }

            @Override // org.apache.commons.math3.random.RandomGenerator
            public double nextGaussian() {
                return random.nextGaussian();
            }
        };
    }

    public static long convertToLong(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            j = (j * 4294967291L) + i;
        }
        return j;
    }
}
