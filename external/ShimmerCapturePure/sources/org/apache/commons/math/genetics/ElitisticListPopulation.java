package org.apache.commons.math.genetics;

import java.util.Collections;
import java.util.List;

import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/ElitisticListPopulation.class */
public class ElitisticListPopulation extends ListPopulation {
    private double elitismRate;

    public ElitisticListPopulation(List<Chromosome> chromosomes, int populationLimit, double elitismRate) {
        super(chromosomes, populationLimit);
        this.elitismRate = 0.9d;
        this.elitismRate = elitismRate;
    }

    public ElitisticListPopulation(int populationLimit, double elitismRate) {
        super(populationLimit);
        this.elitismRate = 0.9d;
        this.elitismRate = elitismRate;
    }

    @Override // org.apache.commons.math.genetics.Population
    public Population nextGeneration() {
        ElitisticListPopulation nextGeneration = new ElitisticListPopulation(getPopulationLimit(), getElitismRate());
        List<Chromosome> oldChromosomes = getChromosomes();
        Collections.sort(oldChromosomes);
        int boundIndex = (int) FastMath.ceil((1.0d - getElitismRate()) * oldChromosomes.size());
        for (int i = boundIndex; i < oldChromosomes.size(); i++) {
            nextGeneration.addChromosome(oldChromosomes.get(i));
        }
        return nextGeneration;
    }

    public double getElitismRate() {
        return this.elitismRate;
    }

    public void setElitismRate(double elitismRate) {
        if (elitismRate < 0.0d || elitismRate > 1.0d) {
            throw new IllegalArgumentException("Elitism rate has to be in [0,1]");
        }
        this.elitismRate = elitismRate;
    }
}
