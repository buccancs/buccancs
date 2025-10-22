package org.apache.commons.math.genetics;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/RandomKeyMutation.class */
public class RandomKeyMutation implements MutationPolicy {
    @Override // org.apache.commons.math.genetics.MutationPolicy
    public Chromosome mutate(Chromosome original) {
        if (!(original instanceof RandomKey)) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.RANDOMKEY_MUTATION_WRONG_CLASS, original.getClass().getSimpleName());
        }
        RandomKey<?> originalRk = (RandomKey) original;
        List<?> representation = originalRk.getRepresentation();
        int rInd = GeneticAlgorithm.getRandomGenerator().nextInt(representation.size());
        List<Double> newRepr = new ArrayList<>(representation);
        newRepr.set(rInd, Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
        return originalRk.newFixedLengthChromosome(newRepr);
    }
}
