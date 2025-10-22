package org.apache.commons.math3.optimization;

@Deprecated
/* loaded from: classes5.dex */
public interface ConvergenceChecker<PAIR> {
    boolean converged(int i, PAIR pair, PAIR pair2);
}
