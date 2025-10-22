package org.apache.commons.math.genetics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/Population.class */
public interface Population extends Iterable<Chromosome> {
    int getPopulationSize();

    int getPopulationLimit();

    Population nextGeneration();

    void addChromosome(Chromosome chromosome);

    Chromosome getFittestChromosome();
}
