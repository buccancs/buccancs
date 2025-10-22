package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public abstract class RandomKey<T> extends AbstractListChromosome<Double> implements PermutationChromosome<T> {
    private final List<Integer> baseSeqPermutation;
    private final List<Double> sortedRepresentation;

    public RandomKey(List<Double> list) throws InvalidRepresentationException {
        super(list);
        ArrayList arrayList = new ArrayList(getRepresentation());
        Collections.sort(arrayList);
        List<Double> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        this.sortedRepresentation = listUnmodifiableList;
        this.baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(getLength()), getRepresentation(), listUnmodifiableList));
    }

    public RandomKey(Double[] dArr) throws InvalidRepresentationException {
        this((List<Double>) Arrays.asList(dArr));
    }

    private static <S> List<S> decodeGeneric(List<S> list, List<Double> list2, List<Double> list3) throws DimensionMismatchException {
        int size = list.size();
        if (list2.size() != size) {
            throw new DimensionMismatchException(list2.size(), size);
        }
        if (list3.size() != size) {
            throw new DimensionMismatchException(list3.size(), size);
        }
        ArrayList arrayList = new ArrayList(list2);
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            int iIndexOf = arrayList.indexOf(list3.get(i));
            arrayList2.add(list.get(iIndexOf));
            arrayList.set(iIndexOf, null);
        }
        return arrayList2;
    }

    public static final List<Double> randomPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
        }
        return arrayList;
    }

    public static final List<Double> identityPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(i2 / i));
        }
        return arrayList;
    }

    public static <S> List<Double> comparatorPermutation(List<S> list, Comparator<S> comparator) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, comparator);
        return inducedPermutation(list, arrayList);
    }

    public static <S> List<Double> inducedPermutation(List<S> list, List<S> list2) throws MathIllegalArgumentException {
        if (list.size() != list2.size()) {
            throw new DimensionMismatchException(list2.size(), list.size());
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(list);
        Double[] dArr = new Double[size];
        for (int i = 0; i < size; i++) {
            int iIndexOf = arrayList.indexOf(list2.get(i));
            if (iIndexOf == -1) {
                throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]);
            }
            dArr[iIndexOf] = Double.valueOf(i / size);
            arrayList.set(iIndexOf, null);
        }
        return Arrays.asList(dArr);
    }

    private static List<Integer> baseSequence(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    @Override // org.apache.commons.math3.genetics.PermutationChromosome
    public List<T> decode(List<T> list) {
        return decodeGeneric(list, getRepresentation(), this.sortedRepresentation);
    }

    @Override // org.apache.commons.math3.genetics.Chromosome
    protected boolean isSame(Chromosome chromosome) {
        if (!(chromosome instanceof RandomKey)) {
            return false;
        }
        RandomKey randomKey = (RandomKey) chromosome;
        if (getLength() != randomKey.getLength()) {
            return false;
        }
        List<Integer> list = this.baseSeqPermutation;
        List<Integer> list2 = randomKey.baseSeqPermutation;
        for (int i = 0; i < getLength(); i++) {
            if (list.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.math3.genetics.AbstractListChromosome
    protected void checkValidity(List<Double> list) throws InvalidRepresentationException {
        Iterator<Double> it2 = list.iterator();
        while (it2.hasNext()) {
            double dDoubleValue = it2.next().doubleValue();
            if (dDoubleValue < 0.0d || dDoubleValue > 1.0d) {
                throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(dDoubleValue), 0, 1);
            }
        }
    }

    @Override // org.apache.commons.math3.genetics.AbstractListChromosome
    public String toString() {
        return String.format("(f=%s pi=(%s))", Double.valueOf(getFitness()), this.baseSeqPermutation);
    }
}
