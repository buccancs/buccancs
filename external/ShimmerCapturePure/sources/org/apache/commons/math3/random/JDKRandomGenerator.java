package org.apache.commons.math3.random;

import java.util.Random;

/* loaded from: classes5.dex */
public class JDKRandomGenerator extends Random implements RandomGenerator {
    private static final long serialVersionUID = -7745277476784028798L;

    public JDKRandomGenerator() {
    }

    public JDKRandomGenerator(int i) {
        setSeed(i);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public void setSeed(int i) {
        setSeed(i);
    }

    @Override // org.apache.commons.math3.random.RandomGenerator
    public void setSeed(int[] iArr) {
        setSeed(RandomGeneratorFactory.convertToLong(iArr));
    }
}
