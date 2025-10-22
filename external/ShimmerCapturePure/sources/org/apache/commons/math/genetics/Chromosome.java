package org.apache.commons.math.genetics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/Chromosome.class */
public abstract class Chromosome implements Comparable<Chromosome>, Fitness {
    private double fitness = Double.MIN_VALUE;

    public double getFitness() {
        if (this.fitness == Double.MIN_VALUE) {
            this.fitness = fitness();
        }
        return this.fitness;
    }

    @Override // java.lang.Comparable
    public int compareTo(Chromosome another) {
        return Double.valueOf(getFitness()).compareTo(Double.valueOf(another.getFitness()));
    }

    protected boolean isSame(Chromosome another) {
        return false;
    }

    protected Chromosome findSameChromosome(Population population) {
        for (Chromosome anotherChr : population) {
            if (isSame(anotherChr)) {
                return anotherChr;
            }
        }
        return null;
    }

    public void searchForFitnessUpdate(Population population) {
        Chromosome sameChromosome = findSameChromosome(population);
        if (sameChromosome != null) {
            this.fitness = sameChromosome.getFitness();
        }
    }
}
