package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class SymmLQ extends PreconditionedIterativeLinearSolver {
    private static final String OPERATOR = "operator";
    private static final String THRESHOLD = "threshold";
    private static final String VECTOR = "vector";
    private static final String VECTOR1 = "vector1";
    private static final String VECTOR2 = "vector2";
    private final boolean check;
    private final double delta;

    public SymmLQ(int i, double d, boolean z) {
        super(i);
        this.delta = d;
        this.check = z;
    }

    public SymmLQ(IterationManager iterationManager, double d, boolean z) {
        super(iterationManager);
        this.delta = d;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2.copy(), false, 0.0d);
    }

    @Override
    // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector) throws OutOfRangeException, NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        ArrayRealVector arrayRealVector = new ArrayRealVector(realLinearOperator.getColumnDimension());
        arrayRealVector.set(0.0d);
        return solveInPlace(realLinearOperator, null, realVector, arrayRealVector, false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, null, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    @Override
    // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, null, realVector, realVector2.copy(), false, 0.0d);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2, false, 0.0d);
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2, boolean z, double d) throws OutOfRangeException, NonSelfAdjointOperatorException, NullArgumentException, MaxCountExceededException, DimensionMismatchException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        checkParameters(realLinearOperator, realLinearOperator2, realVector, realVector2);
        IterationManager iterationManager = getIterationManager();
        iterationManager.resetIterationCount();
        iterationManager.incrementIterationCount();
        State state = new State(realLinearOperator, realLinearOperator2, realVector, z, d, this.delta, this.check);
        state.init();
        state.refineSolution(realVector2);
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state.getNormOfResidual());
        if (state.bEqualsNullVector()) {
            iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent);
            return realVector2;
        }
        boolean z2 = state.betaEqualsZero() || state.hasConverged();
        iterationManager.fireInitializationEvent(defaultIterativeLinearSolverEvent);
        if (!z2) {
            do {
                iterationManager.incrementIterationCount();
                iterationManager.fireIterationStartedEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state.getNormOfResidual()));
                state.update();
                state.refineSolution(realVector2);
                iterationManager.fireIterationPerformedEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state.getNormOfResidual()));
            } while (!state.hasConverged());
        }
        iterationManager.fireTerminationEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state.getNormOfResidual()));
        return realVector2;
    }

    @Override
    // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSelfAdjointOperatorException, DimensionMismatchException, MaxCountExceededException, IllConditionedOperatorException {
        return solveInPlace(realLinearOperator, null, realVector, realVector2, false, 0.0d);
    }

    private static class State {
        static final double CBRT_MACH_PREC;
        static final double MACH_PREC;

        static {
            double dUlp = FastMath.ulp(1.0d);
            MACH_PREC = dUlp;
            CBRT_MACH_PREC = FastMath.cbrt(dUlp);
        }

        private final RealLinearOperator a;
        private final RealVector b;
        private final boolean check;
        private final double delta;
        private final boolean goodb;
        private final RealLinearOperator m;
        private final RealVector mb;
        private final double shift;
        private final RealVector xL;
        private boolean bIsNull;
        private double beta;
        private double beta1;
        private double bstep;
        private double cgnorm;
        private double dbar;
        private double gammaZeta;
        private double gbar;
        private double gmax;
        private double gmin;
        private boolean hasConverged;
        private double lqnorm;
        private double minusEpsZeta;
        private double oldb;
        private RealVector r1;
        private RealVector r2;
        private double rnorm;
        private double snprod;
        private double tnorm;
        private RealVector wbar;
        private RealVector y;
        private double ynorm2;

        State(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d, double d2, boolean z2) {
            this.a = realLinearOperator;
            this.m = realLinearOperator2;
            this.b = realVector;
            this.xL = new ArrayRealVector(realVector.getDimension());
            this.goodb = z;
            this.shift = d;
            this.mb = realLinearOperator2 != null ? realLinearOperator2.operate(realVector) : realVector;
            this.hasConverged = false;
            this.check = z2;
            this.delta = d2;
        }

        private static void checkSymmetry(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2, RealVector realVector3) throws NonSelfAdjointOperatorException, DimensionMismatchException {
            double dDotProduct = realVector2.dotProduct(realVector2);
            double dDotProduct2 = realVector.dotProduct(realVector3);
            double d = (MACH_PREC + dDotProduct) * CBRT_MACH_PREC;
            if (FastMath.abs(dDotProduct - dDotProduct2) <= d) {
                return;
            }
            NonSelfAdjointOperatorException nonSelfAdjointOperatorException = new NonSelfAdjointOperatorException();
            ExceptionContext context = nonSelfAdjointOperatorException.getContext();
            context.setValue("operator", realLinearOperator);
            context.setValue(SymmLQ.VECTOR1, realVector);
            context.setValue(SymmLQ.VECTOR2, realVector2);
            context.setValue(SymmLQ.THRESHOLD, Double.valueOf(d));
            throw nonSelfAdjointOperatorException;
        }

        private static void throwNPDLOException(RealLinearOperator realLinearOperator, RealVector realVector) throws NonPositiveDefiniteOperatorException {
            NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException = new NonPositiveDefiniteOperatorException();
            ExceptionContext context = nonPositiveDefiniteOperatorException.getContext();
            context.setValue("operator", realLinearOperator);
            context.setValue("vector", realVector);
            throw nonPositiveDefiniteOperatorException;
        }

        private static void daxpy(double d, RealVector realVector, RealVector realVector2) throws OutOfRangeException {
            int dimension = realVector.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector2.setEntry(i, (realVector.getEntry(i) * d) + realVector2.getEntry(i));
            }
        }

        private static void daxpbypz(double d, RealVector realVector, double d2, RealVector realVector2, RealVector realVector3) throws OutOfRangeException {
            int dimension = realVector3.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector3.setEntry(i, (realVector.getEntry(i) * d) + (realVector2.getEntry(i) * d2) + realVector3.getEntry(i));
            }
        }

        boolean bEqualsNullVector() {
            return this.bIsNull;
        }

        boolean betaEqualsZero() {
            return this.beta < MACH_PREC;
        }

        double getNormOfResidual() {
            return this.rnorm;
        }

        boolean hasConverged() {
            return this.hasConverged;
        }

        void refineSolution(RealVector realVector) throws OutOfRangeException {
            int dimension = this.xL.getDimension();
            int i = 0;
            if (this.lqnorm < this.cgnorm) {
                if (!this.goodb) {
                    realVector.setSubVector(0, this.xL);
                    return;
                }
                double d = this.bstep / this.beta1;
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.mb.getEntry(i) * d));
                    i++;
                }
                return;
            }
            double dSqrt = FastMath.sqrt(this.tnorm);
            double d2 = this.gbar;
            if (d2 == 0.0d) {
                d2 = MACH_PREC * dSqrt;
            }
            double d3 = this.gammaZeta / d2;
            double d4 = (this.bstep + (this.snprod * d3)) / this.beta1;
            if (!this.goodb) {
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * d3));
                    i++;
                }
            } else {
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * d3) + (this.mb.getEntry(i) * d4));
                    i++;
                }
            }
        }

        void init() throws OutOfRangeException, NonSelfAdjointOperatorException, DimensionMismatchException, NonPositiveDefiniteOperatorException {
            this.xL.set(0.0d);
            RealVector realVectorCopy = this.b.copy();
            this.r1 = realVectorCopy;
            RealLinearOperator realLinearOperator = this.m;
            RealVector realVectorCopy2 = realLinearOperator == null ? this.b.copy() : realLinearOperator.operate(realVectorCopy);
            this.y = realVectorCopy2;
            RealLinearOperator realLinearOperator2 = this.m;
            if (realLinearOperator2 != null && this.check) {
                checkSymmetry(realLinearOperator2, this.r1, realVectorCopy2, realLinearOperator2.operate(realVectorCopy2));
            }
            double dDotProduct = this.r1.dotProduct(this.y);
            this.beta1 = dDotProduct;
            if (dDotProduct < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double d = this.beta1;
            if (d == 0.0d) {
                this.bIsNull = true;
                return;
            }
            this.bIsNull = false;
            double dSqrt = FastMath.sqrt(d);
            this.beta1 = dSqrt;
            RealVector realVectorMapMultiply = this.y.mapMultiply(1.0d / dSqrt);
            RealVector realVectorOperate = this.a.operate(realVectorMapMultiply);
            this.y = realVectorOperate;
            if (this.check) {
                RealLinearOperator realLinearOperator3 = this.a;
                checkSymmetry(realLinearOperator3, realVectorMapMultiply, realVectorOperate, realLinearOperator3.operate(realVectorOperate));
            }
            daxpy(-this.shift, realVectorMapMultiply, this.y);
            double dDotProduct2 = realVectorMapMultiply.dotProduct(this.y);
            daxpy((-dDotProduct2) / this.beta1, this.r1, this.y);
            daxpy((-realVectorMapMultiply.dotProduct(this.y)) / realVectorMapMultiply.dotProduct(realVectorMapMultiply), realVectorMapMultiply, this.y);
            RealVector realVectorCopy3 = this.y.copy();
            this.r2 = realVectorCopy3;
            RealLinearOperator realLinearOperator4 = this.m;
            if (realLinearOperator4 != null) {
                this.y = realLinearOperator4.operate(realVectorCopy3);
            }
            this.oldb = this.beta1;
            double dDotProduct3 = this.r2.dotProduct(this.y);
            this.beta = dDotProduct3;
            if (dDotProduct3 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double dSqrt2 = FastMath.sqrt(this.beta);
            this.beta = dSqrt2;
            double d2 = this.beta1;
            this.cgnorm = d2;
            this.gbar = dDotProduct2;
            this.dbar = dSqrt2;
            this.gammaZeta = d2;
            this.minusEpsZeta = 0.0d;
            this.bstep = 0.0d;
            this.snprod = 1.0d;
            this.tnorm = (dDotProduct2 * dDotProduct2) + (dSqrt2 * dSqrt2);
            this.ynorm2 = 0.0d;
            double dAbs = FastMath.abs(dDotProduct2) + MACH_PREC;
            this.gmax = dAbs;
            this.gmin = dAbs;
            if (this.goodb) {
                ArrayRealVector arrayRealVector = new ArrayRealVector(this.a.getRowDimension());
                this.wbar = arrayRealVector;
                arrayRealVector.set(0.0d);
            } else {
                this.wbar = realVectorMapMultiply;
            }
            updateNorms();
        }

        void update() throws OutOfRangeException, DimensionMismatchException, NonPositiveDefiniteOperatorException {
            RealVector realVectorMapMultiply = this.y.mapMultiply(1.0d / this.beta);
            RealVector realVectorOperate = this.a.operate(realVectorMapMultiply);
            this.y = realVectorOperate;
            daxpbypz(-this.shift, realVectorMapMultiply, (-this.beta) / this.oldb, this.r1, realVectorOperate);
            double dDotProduct = realVectorMapMultiply.dotProduct(this.y);
            daxpy((-dDotProduct) / this.beta, this.r2, this.y);
            this.r1 = this.r2;
            RealVector realVector = this.y;
            this.r2 = realVector;
            RealLinearOperator realLinearOperator = this.m;
            if (realLinearOperator != null) {
                this.y = realLinearOperator.operate(realVector);
            }
            this.oldb = this.beta;
            double dDotProduct2 = this.r2.dotProduct(this.y);
            this.beta = dDotProduct2;
            if (dDotProduct2 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double dSqrt = FastMath.sqrt(this.beta);
            this.beta = dSqrt;
            double d = this.tnorm;
            double d2 = this.oldb;
            this.tnorm = d + (dDotProduct * dDotProduct) + (d2 * d2) + (dSqrt * dSqrt);
            double d3 = this.gbar;
            double dSqrt2 = FastMath.sqrt((d3 * d3) + (d2 * d2));
            double d4 = this.gbar / dSqrt2;
            double d5 = this.oldb / dSqrt2;
            double d6 = this.dbar;
            double d7 = (d4 * d6) + (d5 * dDotProduct);
            this.gbar = (d6 * d5) - (dDotProduct * d4);
            double d8 = this.beta;
            double d9 = d5 * d8;
            this.dbar = (-d4) * d8;
            double d10 = this.gammaZeta / dSqrt2;
            double d11 = d10 * d4;
            double d12 = d10 * d5;
            int dimension = this.xL.getDimension();
            int i = 0;
            while (i < dimension) {
                double entry = this.xL.getEntry(i);
                double entry2 = realVectorMapMultiply.getEntry(i);
                double entry3 = this.wbar.getEntry(i);
                this.xL.setEntry(i, entry + (entry3 * d11) + (entry2 * d12));
                this.wbar.setEntry(i, (entry3 * d5) - (entry2 * d4));
                i++;
                dimension = dimension;
                d11 = d11;
            }
            double d13 = this.bstep;
            double d14 = this.snprod;
            this.bstep = d13 + (d4 * d14 * d10);
            this.snprod = d14 * d5;
            this.gmax = FastMath.max(this.gmax, dSqrt2);
            this.gmin = FastMath.min(this.gmin, dSqrt2);
            this.ynorm2 += d10 * d10;
            this.gammaZeta = this.minusEpsZeta - (d7 * d10);
            this.minusEpsZeta = (-d9) * d10;
            updateNorms();
        }

        private void updateNorms() {
            double dSqrt = FastMath.sqrt(this.tnorm);
            double dSqrt2 = FastMath.sqrt(this.ynorm2);
            double d = MACH_PREC;
            double d2 = dSqrt * d;
            double d3 = dSqrt * dSqrt2;
            double d4 = d3 * d;
            double d5 = d3 * this.delta;
            double d6 = this.gbar;
            if (d6 != 0.0d) {
                d2 = d6;
            }
            double d7 = this.gammaZeta;
            double d8 = this.minusEpsZeta;
            this.lqnorm = FastMath.sqrt((d7 * d7) + (d8 * d8));
            double dAbs = ((this.snprod * this.beta1) * this.beta) / FastMath.abs(d2);
            this.cgnorm = dAbs;
            double dMin = this.lqnorm <= dAbs ? this.gmax / this.gmin : this.gmax / FastMath.min(this.gmin, FastMath.abs(d2));
            if (d * dMin >= 0.1d) {
                throw new IllConditionedOperatorException(dMin);
            }
            if (this.beta1 <= d4) {
                throw new SingularOperatorException();
            }
            this.rnorm = FastMath.min(this.cgnorm, this.lqnorm);
            double d9 = this.cgnorm;
            this.hasConverged = d9 <= d4 || d9 <= d5;
        }
    }
}
