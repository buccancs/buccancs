package org.apache.commons.math.random;

import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/UniformRandomGenerator.class */
public class UniformRandomGenerator implements NormalizedRandomGenerator {
    private static final long serialVersionUID = 1569292426375546027L;
    private static final double SQRT3 = FastMath.sqrt(3.0d);
    private final RandomGenerator generator;

    public UniformRandomGenerator(RandomGenerator generator) {
        this.generator = generator;
    }

    @Override // org.apache.commons.math.random.NormalizedRandomGenerator
    public double nextNormalizedDouble() {
        return SQRT3 * ((2.0d * this.generator.nextDouble()) - 1.0d);
    }
}
