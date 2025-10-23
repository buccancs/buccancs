package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.MultistepIntegrator;

/* loaded from: classes5.dex */
public abstract class AdamsIntegrator extends MultistepIntegrator {
    private final AdamsNordsieckTransformer transformer;

    public AdamsIntegrator(String str, int i, int i2, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(str, i, i2, d, d2, d3, d4);
        this.transformer = AdamsNordsieckTransformer.getInstance(i);
    }

    public AdamsIntegrator(String str, int i, int i2, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(str, i, i2, d, d2, dArr, dArr2);
        this.transformer = AdamsNordsieckTransformer.getInstance(i);
    }

    @Override
    // org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public abstract void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    @Override // org.apache.commons.math3.ode.MultistepIntegrator
    protected Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3) {
        return this.transformer.initializeHighOrderDerivatives(d, dArr, dArr2, dArr3);
    }

    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix array2DRowRealMatrix) {
        return this.transformer.updateHighOrderDerivativesPhase1(array2DRowRealMatrix);
    }

    public void updateHighOrderDerivativesPhase2(double[] dArr, double[] dArr2, Array2DRowRealMatrix array2DRowRealMatrix) {
        this.transformer.updateHighOrderDerivativesPhase2(dArr, dArr2, array2DRowRealMatrix);
    }
}
