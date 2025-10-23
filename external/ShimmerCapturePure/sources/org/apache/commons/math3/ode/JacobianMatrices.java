package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class JacobianMatrices {
    private boolean dirtyParameter;
    private ExpandableStatefulODE efode;
    private int index;
    private List<ParameterJacobianProvider> jacobianProviders;
    private MainStateJacobianProvider jode;
    private double[] matricesData;
    private int paramDim;
    private ParameterizedODE pode;
    private ParameterConfiguration[] selectedParameters;
    private int stateDim;

    public JacobianMatrices(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr, String... strArr) throws DimensionMismatchException {
        this(new MainStateJacobianWrapper(firstOrderDifferentialEquations, dArr), strArr);
    }

    public JacobianMatrices(MainStateJacobianProvider mainStateJacobianProvider, String... strArr) {
        this.efode = null;
        this.index = -1;
        this.jode = mainStateJacobianProvider;
        this.pode = null;
        this.stateDim = mainStateJacobianProvider.getDimension();
        int i = 0;
        if (strArr == null) {
            this.selectedParameters = null;
            this.paramDim = 0;
        } else {
            this.selectedParameters = new ParameterConfiguration[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                this.selectedParameters[i2] = new ParameterConfiguration(strArr[i2], Double.NaN);
            }
            this.paramDim = strArr.length;
        }
        this.dirtyParameter = false;
        this.jacobianProviders = new ArrayList();
        int i3 = this.stateDim;
        this.matricesData = new double[(this.paramDim + i3) * i3];
        while (true) {
            int i4 = this.stateDim;
            if (i >= i4) {
                return;
            }
            this.matricesData[(i4 + 1) * i] = 1.0d;
            i++;
        }
    }

    public void setParameterizedODE(ParameterizedODE parameterizedODE) {
        this.pode = parameterizedODE;
        this.dirtyParameter = true;
    }

    public void registerVariationalEquations(ExpandableStatefulODE expandableStatefulODE) throws MismatchedEquations, DimensionMismatchException {
        FirstOrderDifferentialEquations firstOrderDifferentialEquations = this.jode;
        if (firstOrderDifferentialEquations instanceof MainStateJacobianWrapper) {
            firstOrderDifferentialEquations = ((MainStateJacobianWrapper) firstOrderDifferentialEquations).ode;
        }
        if (expandableStatefulODE.getPrimary() != firstOrderDifferentialEquations) {
            throw new MismatchedEquations();
        }
        this.efode = expandableStatefulODE;
        int iAddSecondaryEquations = expandableStatefulODE.addSecondaryEquations(new JacobiansSecondaryEquations());
        this.index = iAddSecondaryEquations;
        this.efode.setSecondaryState(iAddSecondaryEquations, this.matricesData);
    }

    public void addParameterJacobianProvider(ParameterJacobianProvider parameterJacobianProvider) {
        this.jacobianProviders.add(parameterJacobianProvider);
    }

    public void setParameterStep(String str, double d) throws UnknownParameterException {
        for (ParameterConfiguration parameterConfiguration : this.selectedParameters) {
            if (str.equals(parameterConfiguration.getParameterName())) {
                parameterConfiguration.setHP(d);
                this.dirtyParameter = true;
                return;
            }
        }
        throw new UnknownParameterException(str);
    }

    public void setInitialMainStateJacobian(double[][] dArr) throws DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        checkDimension(this.stateDim, dArr[0]);
        int i = 0;
        for (double[] dArr2 : dArr) {
            System.arraycopy(dArr2, 0, this.matricesData, i, this.stateDim);
            i += this.stateDim;
        }
        ExpandableStatefulODE expandableStatefulODE = this.efode;
        if (expandableStatefulODE != null) {
            expandableStatefulODE.setSecondaryState(this.index, this.matricesData);
        }
    }

    public void setInitialParameterJacobian(String str, double[] dArr) throws UnknownParameterException, DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        int i = this.stateDim;
        int i2 = i * i;
        for (ParameterConfiguration parameterConfiguration : this.selectedParameters) {
            if (str.equals(parameterConfiguration.getParameterName())) {
                System.arraycopy(dArr, 0, this.matricesData, i2, this.stateDim);
                ExpandableStatefulODE expandableStatefulODE = this.efode;
                if (expandableStatefulODE != null) {
                    expandableStatefulODE.setSecondaryState(this.index, this.matricesData);
                    return;
                }
                return;
            }
            i2 += this.stateDim;
        }
        throw new UnknownParameterException(str);
    }

    public void getCurrentMainSetJacobian(double[][] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = this.stateDim;
            if (i >= i3) {
                return;
            }
            System.arraycopy(secondaryState, i2, dArr[i], 0, i3);
            i2 += this.stateDim;
            i++;
        }
    }

    public void getCurrentParameterJacobian(String str, double[] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = this.stateDim;
        int i2 = i * i;
        for (ParameterConfiguration parameterConfiguration : this.selectedParameters) {
            if (parameterConfiguration.getParameterName().equals(str)) {
                System.arraycopy(secondaryState, i2, dArr, 0, this.stateDim);
                return;
            }
            i2 += this.stateDim;
        }
    }

    private void checkDimension(int i, Object obj) throws DimensionMismatchException {
        int length = obj == null ? 0 : Array.getLength(obj);
        if (length != i) {
            throw new DimensionMismatchException(length, i);
        }
    }

    private static class MainStateJacobianWrapper implements MainStateJacobianProvider {
        private final double[] hY;
        private final FirstOrderDifferentialEquations ode;

        MainStateJacobianWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr) throws DimensionMismatchException {
            this.ode = firstOrderDifferentialEquations;
            this.hY = (double[]) dArr.clone();
            if (dArr.length != firstOrderDifferentialEquations.getDimension()) {
                throw new DimensionMismatchException(firstOrderDifferentialEquations.getDimension(), dArr.length);
            }
        }

        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
        public int getDimension() {
            return this.ode.getDimension();
        }

        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
        public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
            this.ode.computeDerivatives(d, dArr, dArr2);
        }

        @Override // org.apache.commons.math3.ode.MainStateJacobianProvider
        public void computeMainStateJacobian(double d, double[] dArr, double[] dArr2, double[][] dArr3) throws MaxCountExceededException, DimensionMismatchException {
            int dimension = this.ode.getDimension();
            double[] dArr4 = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                double d2 = dArr[i];
                dArr[i] = this.hY[i] + d2;
                this.ode.computeDerivatives(d, dArr, dArr4);
                for (int i2 = 0; i2 < dimension; i2++) {
                    dArr3[i2][i] = (dArr4[i2] - dArr2[i2]) / this.hY[i];
                }
                dArr[i] = d2;
            }
        }
    }

    public static class MismatchedEquations extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20120902;

        public MismatchedEquations() {
            super(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET, new Object[0]);
        }
    }

    private class JacobiansSecondaryEquations implements SecondaryEquations {
        private JacobiansSecondaryEquations() {
        }

        @Override // org.apache.commons.math3.ode.SecondaryEquations
        public int getDimension() {
            return JacobianMatrices.this.stateDim * (JacobianMatrices.this.stateDim + JacobianMatrices.this.paramDim);
        }

        @Override // org.apache.commons.math3.ode.SecondaryEquations
        public void computeDerivatives(double d, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) throws UnknownParameterException, MaxCountExceededException, DimensionMismatchException {
            int i;
            int i2;
            int i3;
            if (JacobianMatrices.this.dirtyParameter && JacobianMatrices.this.paramDim != 0) {
                JacobianMatrices.this.jacobianProviders.add(new ParameterJacobianWrapper(JacobianMatrices.this.jode, JacobianMatrices.this.pode, JacobianMatrices.this.selectedParameters));
                JacobianMatrices.this.dirtyParameter = false;
            }
            double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, JacobianMatrices.this.stateDim, JacobianMatrices.this.stateDim);
            JacobianMatrices.this.jode.computeMainStateJacobian(d, dArr, dArr2, dArr5);
            for (int i4 = 0; i4 < JacobianMatrices.this.stateDim; i4++) {
                double[] dArr6 = dArr5[i4];
                for (int i5 = 0; i5 < JacobianMatrices.this.stateDim; i5++) {
                    double d2 = 0.0d;
                    int i6 = i5;
                    for (int i7 = 0; i7 < JacobianMatrices.this.stateDim; i7++) {
                        d2 += dArr6[i7] * dArr3[i6];
                        i6 += JacobianMatrices.this.stateDim;
                    }
                    dArr4[(JacobianMatrices.this.stateDim * i4) + i5] = d2;
                }
            }
            if (JacobianMatrices.this.paramDim != 0) {
                double[] dArr7 = new double[JacobianMatrices.this.stateDim];
                int i8 = JacobianMatrices.this.stateDim * JacobianMatrices.this.stateDim;
                ParameterConfiguration[] parameterConfigurationArr = JacobianMatrices.this.selectedParameters;
                int length = parameterConfigurationArr.length;
                int i9 = 0;
                while (i9 < length) {
                    ParameterConfiguration parameterConfiguration = parameterConfigurationArr[i9];
                    boolean z = false;
                    int i10 = 0;
                    while (!z && i10 < JacobianMatrices.this.jacobianProviders.size()) {
                        ParameterJacobianProvider parameterJacobianProvider = (ParameterJacobianProvider) JacobianMatrices.this.jacobianProviders.get(i10);
                        if (parameterJacobianProvider.isSupported(parameterConfiguration.getParameterName())) {
                            i = i10;
                            i2 = i9;
                            i3 = length;
                            parameterJacobianProvider.computeParameterJacobian(d, dArr, dArr2, parameterConfiguration.getParameterName(), dArr7);
                            for (int i11 = 0; i11 < JacobianMatrices.this.stateDim; i11++) {
                                double[] dArr8 = dArr5[i11];
                                double d3 = dArr7[i11];
                                int i12 = i8;
                                for (int i13 = 0; i13 < JacobianMatrices.this.stateDim; i13++) {
                                    d3 += dArr8[i13] * dArr3[i12];
                                    i12++;
                                }
                                dArr4[i8 + i11] = d3;
                            }
                            z = true;
                        } else {
                            i = i10;
                            i2 = i9;
                            i3 = length;
                        }
                        i10 = i + 1;
                        length = i3;
                        i9 = i2;
                    }
                    int i14 = i9;
                    int i15 = length;
                    if (!z) {
                        Arrays.fill(dArr4, i8, JacobianMatrices.this.stateDim + i8, 0.0d);
                    }
                    i8 += JacobianMatrices.this.stateDim;
                    i9 = i14 + 1;
                    length = i15;
                }
            }
        }
    }
}
