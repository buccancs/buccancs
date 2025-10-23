package org.apache.commons.math.random;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/RandomData.class */
public interface RandomData {
    String nextHexString(int i);

    int nextInt(int i, int i2);

    long nextLong(long j, long j2);

    String nextSecureHexString(int i);

    int nextSecureInt(int i, int i2);

    long nextSecureLong(long j, long j2);

    long nextPoisson(double d);

    double nextGaussian(double d, double d2);

    double nextExponential(double d);

    double nextUniform(double d, double d2);

    int[] nextPermutation(int i, int i2);

    Object[] nextSample(Collection<?> collection, int i);
}
