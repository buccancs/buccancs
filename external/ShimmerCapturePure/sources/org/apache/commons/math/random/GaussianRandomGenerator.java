package org.apache.commons.math.random;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/GaussianRandomGenerator.class */
public class GaussianRandomGenerator implements NormalizedRandomGenerator {
    private final RandomGenerator generator;

    public GaussianRandomGenerator(RandomGenerator generator) {
        this.generator = generator;
    }

    @Override // org.apache.commons.math.random.NormalizedRandomGenerator
    public double nextNormalizedDouble() {
        return this.generator.nextGaussian();
    }
}
