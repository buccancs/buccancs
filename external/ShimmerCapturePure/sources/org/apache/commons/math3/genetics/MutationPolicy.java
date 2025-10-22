package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface MutationPolicy {
    Chromosome mutate(Chromosome chromosome) throws MathIllegalArgumentException;
}
