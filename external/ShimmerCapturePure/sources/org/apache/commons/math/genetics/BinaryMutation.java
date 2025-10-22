package org.apache.commons.math.genetics;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/BinaryMutation.class */
public class BinaryMutation implements MutationPolicy {
    @Override // org.apache.commons.math.genetics.MutationPolicy
    public Chromosome mutate(Chromosome original) {
        if (!(original instanceof BinaryChromosome)) {
            throw new IllegalArgumentException("Binary mutation works on BinaryChromosome only.");
        }
        BinaryChromosome origChrom = (BinaryChromosome) original;
        ArrayList arrayList = new ArrayList(origChrom.getRepresentation());
        int geneIndex = GeneticAlgorithm.getRandomGenerator().nextInt(origChrom.getLength());
        arrayList.set(geneIndex, Integer.valueOf(origChrom.getRepresentation().get(geneIndex).intValue() == 0 ? 1 : 0));
        Chromosome newChrom = origChrom.newFixedLengthChromosome(arrayList);
        return newChrom;
    }
}
