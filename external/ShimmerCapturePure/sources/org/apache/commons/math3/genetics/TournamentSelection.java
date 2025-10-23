package org.apache.commons.math3.genetics;

import java.util.ArrayList;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class TournamentSelection implements SelectionPolicy {
    private int arity;

    public TournamentSelection(int i) {
        this.arity = i;
    }

    public int getArity() {
        return this.arity;
    }

    public void setArity(int i) {
        this.arity = i;
    }

    @Override // org.apache.commons.math3.genetics.SelectionPolicy
    public ChromosomePair select(Population population) throws MathIllegalArgumentException {
        ListPopulation listPopulation = (ListPopulation) population;
        return new ChromosomePair(tournament(listPopulation), tournament(listPopulation));
    }

    private Chromosome tournament(ListPopulation listPopulation) throws MathIllegalArgumentException {
        int populationSize = listPopulation.getPopulationSize();
        int i = this.arity;
        if (populationSize < i) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_LARGE_TOURNAMENT_ARITY, Integer.valueOf(this.arity), Integer.valueOf(listPopulation.getPopulationSize()));
        }
        ListPopulation listPopulation2 = new ListPopulation(i) { // from class: org.apache.commons.math3.genetics.TournamentSelection.1
            @Override // org.apache.commons.math3.genetics.Population
            public Population nextGeneration() {
                return null;
            }
        };
        ArrayList arrayList = new ArrayList(listPopulation.getChromosomes());
        for (int i2 = 0; i2 < this.arity; i2++) {
            int iNextInt = GeneticAlgorithm.getRandomGenerator().nextInt(arrayList.size());
            listPopulation2.addChromosome((Chromosome) arrayList.get(iNextInt));
            arrayList.remove(iNextInt);
        }
        return listPopulation2.getFittestChromosome();
    }
}
