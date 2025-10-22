package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class CycleCrossover<T> implements CrossoverPolicy {
    private final boolean randomStart;

    public CycleCrossover() {
        this(false);
    }

    public CycleCrossover(boolean z) {
        this.randomStart = z;
    }

    public boolean isRandomStart() {
        return this.randomStart;
    }

    @Override // org.apache.commons.math3.genetics.CrossoverPolicy
    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws MathIllegalArgumentException {
        if (!(chromosome instanceof AbstractListChromosome) || !(chromosome2 instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return mate((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
    }

    protected ChromosomePair mate(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        List<T> representation = abstractListChromosome.getRepresentation();
        List<T> representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(abstractListChromosome2.getRepresentation());
        ArrayList arrayList2 = new ArrayList(abstractListChromosome.getRepresentation());
        HashSet hashSet = new HashSet(length);
        ArrayList arrayList3 = new ArrayList(length);
        int iNextInt = this.randomStart ? GeneticAlgorithm.getRandomGenerator().nextInt(length) : 0;
        int i = 1;
        while (hashSet.size() < length) {
            arrayList3.add(Integer.valueOf(iNextInt));
            for (int iIndexOf = representation.indexOf(representation2.get(iNextInt)); iIndexOf != ((Integer) arrayList3.get(0)).intValue(); iIndexOf = representation.indexOf(representation2.get(iIndexOf))) {
                arrayList3.add(Integer.valueOf(iIndexOf));
            }
            int i2 = i + 1;
            if (i % 2 != 0) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    int iIntValue = ((Integer) it2.next()).intValue();
                    Object obj = arrayList.get(iIntValue);
                    arrayList.set(iIntValue, arrayList2.get(iIntValue));
                    arrayList2.set(iIntValue, obj);
                }
            }
            hashSet.addAll(arrayList3);
            int iIntValue2 = (((Integer) arrayList3.get(0)).intValue() + 1) % length;
            while (hashSet.contains(Integer.valueOf(iIntValue2)) && hashSet.size() < length) {
                iIntValue2++;
                if (iIntValue2 >= length) {
                    iIntValue2 = 0;
                }
            }
            arrayList3.clear();
            int i3 = iIntValue2;
            i = i2;
            iNextInt = i3;
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
