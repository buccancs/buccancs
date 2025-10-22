package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface SelectionPolicy {
    ChromosomePair select(Population population) throws MathIllegalArgumentException;
}
