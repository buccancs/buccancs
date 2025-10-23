package org.apache.commons.math3.genetics;

import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class ElitisticListPopulation extends ListPopulation {
    private double elitismRate;

    public ElitisticListPopulation(List<Chromosome> list, int i, double d) throws OutOfRangeException, NotPositiveException, NullArgumentException, NumberIsTooLargeException {
        super(list, i);
        this.elitismRate = 0.9d;
        setElitismRate(d);
    }

    public ElitisticListPopulation(int i, double d) throws OutOfRangeException, NotPositiveException {
        super(i);
        this.elitismRate = 0.9d;
        setElitismRate(d);
    }

    public double getElitismRate() {
        return this.elitismRate;
    }

    public void setElitismRate(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, Double.valueOf(d), 0, 1);
        }
        this.elitismRate = d;
    }

    @Override // org.apache.commons.math3.genetics.Population
    public Population nextGeneration() {
        ElitisticListPopulation elitisticListPopulation = new ElitisticListPopulation(getPopulationLimit(), getElitismRate());
        List<Chromosome> chromosomeList = getChromosomeList();
        Collections.sort(chromosomeList);
        for (int iCeil = (int) FastMath.ceil((1.0d - getElitismRate()) * chromosomeList.size()); iCeil < chromosomeList.size(); iCeil++) {
            elitisticListPopulation.addChromosome(chromosomeList.get(iCeil));
        }
        return elitisticListPopulation;
    }
}
