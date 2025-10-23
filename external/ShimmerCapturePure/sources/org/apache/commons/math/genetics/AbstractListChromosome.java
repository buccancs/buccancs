package org.apache.commons.math.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/AbstractListChromosome.class */
public abstract class AbstractListChromosome<T> extends Chromosome {
    private final List<T> representation;

    public AbstractListChromosome(List<T> representation) {
        try {
            checkValidity(representation);
            this.representation = Collections.unmodifiableList(new ArrayList(representation));
        } catch (InvalidRepresentationException e) {
            throw new IllegalArgumentException(String.format("Invalid representation for %s", getClass().getSimpleName()), e);
        }
    }

    public AbstractListChromosome(T[] representation) {
        this(Arrays.asList(representation));
    }

    protected abstract void checkValidity(List<T> list) throws InvalidRepresentationException;

    public abstract AbstractListChromosome<T> newFixedLengthChromosome(List<T> list);

    protected List<T> getRepresentation() {
        return this.representation;
    }

    public int getLength() {
        return getRepresentation().size();
    }

    public String toString() {
        return String.format("(f=%s %s)", Double.valueOf(getFitness()), getRepresentation());
    }
}
