package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class LaguerreSolver extends AbstractPolynomialSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private final ComplexSolver complexSolver;

    public LaguerreSolver() {
        this(1.0E-6d);
    }

    public LaguerreSolver(double d) {
        super(d);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2) {
        super(d, d2);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2, double d3) {
        super(d, d2, d3);
        this.complexSolver = new ComplexSolver();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double dComputeObjectiveValue = computeObjectiveValue(startValue);
        if (FastMath.abs(dComputeObjectiveValue) <= functionValueAccuracy) {
            return startValue;
        }
        double dComputeObjectiveValue2 = computeObjectiveValue(min);
        if (FastMath.abs(dComputeObjectiveValue2) <= functionValueAccuracy) {
            return min;
        }
        if (dComputeObjectiveValue * dComputeObjectiveValue2 < 0.0d) {
            return laguerre(min, startValue, dComputeObjectiveValue2, dComputeObjectiveValue);
        }
        double dComputeObjectiveValue3 = computeObjectiveValue(max);
        if (FastMath.abs(dComputeObjectiveValue3) <= functionValueAccuracy) {
            return max;
        }
        if (dComputeObjectiveValue * dComputeObjectiveValue3 < 0.0d) {
            return laguerre(startValue, max, dComputeObjectiveValue, dComputeObjectiveValue3);
        }
        throw new NoBracketingException(min, max, dComputeObjectiveValue2, dComputeObjectiveValue3);
    }

    @Deprecated
    public double laguerre(double d, double d2, double d3, double d4) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        Complex[] complexArrConvertToComplex = ComplexUtils.convertToComplex(getCoefficients());
        Complex complex = new Complex((d + d2) * 0.5d, 0.0d);
        Complex complexSolve = this.complexSolver.solve(complexArrConvertToComplex, complex);
        if (this.complexSolver.isRoot(d, d2, complexSolve)) {
            return complexSolve.getReal();
        }
        Complex[] complexArrSolveAll = this.complexSolver.solveAll(complexArrConvertToComplex, complex);
        for (int i = 0; i < complexArrSolveAll.length; i++) {
            if (this.complexSolver.isRoot(d, d2, complexArrSolveAll[i])) {
                return complexArrSolveAll[i].getReal();
            }
        }
        return Double.NaN;
    }

    public Complex[] solveAllComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveAllComplex(dArr, d, Integer.MAX_VALUE);
    }

    public Complex[] solveAllComplex(double[] dArr, double d, int i) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(i, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solveAll(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }

    public Complex solveComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveComplex(dArr, d, Integer.MAX_VALUE);
    }

    public Complex solveComplex(double[] dArr, double d, int i) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(i, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solve(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }

    private class ComplexSolver {
        private ComplexSolver() {
        }

        public boolean isRoot(double d, double d2, Complex complex) {
            if (LaguerreSolver.this.isSequence(d, complex.getReal(), d2)) {
                return FastMath.abs(complex.getImaginary()) <= FastMath.max(LaguerreSolver.this.getRelativeAccuracy() * complex.abs(), LaguerreSolver.this.getAbsoluteAccuracy()) || complex.abs() <= LaguerreSolver.this.getFunctionValueAccuracy();
            }
            return false;
        }

        public Complex[] solveAll(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            if (complexArr == null) {
                throw new NullArgumentException();
            }
            int length = complexArr.length;
            int i = length - 1;
            if (i == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            Complex[] complexArr2 = new Complex[length];
            for (int i2 = 0; i2 <= i; i2++) {
                complexArr2[i2] = complexArr[i2];
            }
            Complex[] complexArr3 = new Complex[i];
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i - i3;
                int i5 = i4 + 1;
                Complex[] complexArr4 = new Complex[i5];
                System.arraycopy(complexArr2, 0, complexArr4, 0, i5);
                complexArr3[i3] = solve(complexArr4, complex);
                Complex complexAdd = complexArr2[i4];
                for (int i6 = i4 - 1; i6 >= 0; i6--) {
                    Complex complex2 = complexArr2[i6];
                    complexArr2[i6] = complexAdd;
                    complexAdd = complex2.add(complexAdd.multiply(complexArr3[i3]));
                }
            }
            return complexArr3;
        }

        public Complex solve(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            Complex complexSubtract;
            Complex complex2;
            Complex[] complexArr2 = complexArr;
            if (complexArr2 == null) {
                throw new NullArgumentException();
            }
            int length = complexArr2.length;
            int i = length - 1;
            if (i == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            double absoluteAccuracy = LaguerreSolver.this.getAbsoluteAccuracy();
            double relativeAccuracy = LaguerreSolver.this.getRelativeAccuracy();
            double functionValueAccuracy = LaguerreSolver.this.getFunctionValueAccuracy();
            Complex complex3 = new Complex(i, 0.0d);
            int i2 = length - 2;
            Complex complex4 = new Complex(i2, 0.0d);
            Complex complex5 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            Complex complex6 = complex;
            while (true) {
                Complex complexAdd = complexArr2[i];
                int i3 = i2;
                Complex complexAdd2 = Complex.ZERO;
                Complex complexAdd3 = Complex.ZERO;
                while (i3 >= 0) {
                    complexAdd3 = complexAdd2.add(complex6.multiply(complexAdd3));
                    complexAdd2 = complexAdd.add(complex6.multiply(complexAdd2));
                    complexAdd = complexArr2[i3].add(complex6.multiply(complexAdd));
                    i3--;
                    i2 = i2;
                }
                int i4 = i2;
                Complex complex7 = complex3;
                Complex complex8 = complex4;
                Complex complexMultiply = complexAdd3.multiply(new Complex(2.0d, 0.0d));
                if (complex6.subtract(complex5).abs() <= FastMath.max(complex6.abs() * relativeAccuracy, absoluteAccuracy) || complexAdd.abs() <= functionValueAccuracy) {
                    return complex6;
                }
                Complex complexDivide = complexAdd2.divide(complexAdd);
                Complex complexMultiply2 = complexDivide.multiply(complexDivide);
                Complex complexSqrt = complex8.multiply(complex7.multiply(complexMultiply2.subtract(complexMultiply.divide(complexAdd))).subtract(complexMultiply2)).sqrt();
                Complex complexAdd4 = complexDivide.add(complexSqrt);
                Complex complexSubtract2 = complexDivide.subtract(complexSqrt);
                if (complexAdd4.abs() <= complexSubtract2.abs()) {
                    complexAdd4 = complexSubtract2;
                }
                if (complexAdd4.equals(new Complex(0.0d, 0.0d))) {
                    complexSubtract = complex6.add(new Complex(absoluteAccuracy, absoluteAccuracy));
                    complex2 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    complexSubtract = complex6.subtract(complex7.divide(complexAdd4));
                    complex2 = complex6;
                }
                complex6 = complexSubtract;
                LaguerreSolver.this.incrementEvaluationCount();
                complex4 = complex8;
                complex3 = complex7;
                i2 = i4;
                complex5 = complex2;
                complexArr2 = complexArr;
            }
        }
    }
}
