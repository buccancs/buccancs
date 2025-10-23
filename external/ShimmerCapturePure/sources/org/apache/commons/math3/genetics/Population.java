package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooLargeException;

/* loaded from: classes5.dex */
public interface Population extends Iterable<Chromosome> {
    void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException;

    Chromosome getFittestChromosome();

    int getPopulationLimit();

    int getPopulationSize();

    Population nextGeneration();
}
