package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.util.IterationEvent;

/* loaded from: classes5.dex */
public abstract class IterativeLinearSolverEvent extends IterationEvent {
    private static final long serialVersionUID = 20120129;

    public IterativeLinearSolverEvent(Object obj, int i) {
        super(obj, i);
    }

    public abstract double getNormOfResidual();

    public abstract RealVector getRightHandSideVector();

    public abstract RealVector getSolution();

    public boolean providesResidual() {
        return false;
    }

    public RealVector getResidual() {
        throw new MathUnsupportedOperationException();
    }
}
