package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;

/* loaded from: classes5.dex */
public class DefaultIterativeLinearSolverEvent extends IterativeLinearSolverEvent {
    private static final long serialVersionUID = 20120129;
    private final RealVector b;
    private final RealVector r;
    private final double rnorm;
    private final RealVector x;

    public DefaultIterativeLinearSolverEvent(Object obj, int i, RealVector realVector, RealVector realVector2, RealVector realVector3, double d) {
        super(obj, i);
        this.x = realVector;
        this.b = realVector2;
        this.r = realVector3;
        this.rnorm = d;
    }

    public DefaultIterativeLinearSolverEvent(Object obj, int i, RealVector realVector, RealVector realVector2, double d) {
        super(obj, i);
        this.x = realVector;
        this.b = realVector2;
        this.r = null;
        this.rnorm = d;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public double getNormOfResidual() {
        return this.rnorm;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getRightHandSideVector() {
        return this.b;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getSolution() {
        return this.x;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public boolean providesResidual() {
        return this.r != null;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getResidual() {
        RealVector realVector = this.r;
        if (realVector != null) {
            return realVector;
        }
        throw new MathUnsupportedOperationException();
    }
}
