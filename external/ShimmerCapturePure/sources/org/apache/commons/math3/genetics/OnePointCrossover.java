package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class OnePointCrossover<T> implements CrossoverPolicy {
    @Override // org.apache.commons.math3.genetics.CrossoverPolicy
    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws MathIllegalArgumentException {
        if (!(chromosome instanceof AbstractListChromosome) || !(chromosome2 instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return crossover((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
    }

    private ChromosomePair crossover(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        List<T> representation = abstractListChromosome.getRepresentation();
        List<T> representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(length);
        List<T> arrayList2 = new ArrayList<>(length);
        int iNextInt = GeneticAlgorithm.getRandomGenerator().nextInt(length - 2) + 1;
        for (int i = 0; i < iNextInt; i++) {
            arrayList.add(representation.get(i));
            arrayList2.add(representation2.get(i));
        }
        while (iNextInt < length) {
            arrayList.add(representation2.get(iNextInt));
            arrayList2.add(representation.get(iNextInt));
            iNextInt++;
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
