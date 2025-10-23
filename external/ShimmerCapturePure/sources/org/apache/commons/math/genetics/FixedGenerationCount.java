package org.apache.commons.math.genetics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/FixedGenerationCount.class */
public class FixedGenerationCount implements StoppingCondition {
    private final int maxGenerations;
    private int numGenerations = 0;

    public FixedGenerationCount(int maxGenerations) {
        if (maxGenerations <= 0) {
            throw new IllegalArgumentException("The number of generations has to be >= 0");
        }
        this.maxGenerations = maxGenerations;
    }

    @Override // org.apache.commons.math.genetics.StoppingCondition
    public boolean isSatisfied(Population population) {
        if (this.numGenerations < this.maxGenerations) {
            this.numGenerations++;
            return false;
        }
        return true;
    }

    public int getNumGenerations() {
        return this.numGenerations;
    }
}
