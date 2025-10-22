package org.apache.commons.math.random;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/JDKRandomGenerator.class */
public class JDKRandomGenerator extends Random implements RandomGenerator {
    private static final long serialVersionUID = -7745277476784028798L;

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
}
