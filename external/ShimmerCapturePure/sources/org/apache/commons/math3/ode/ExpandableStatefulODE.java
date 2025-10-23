package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public class ExpandableStatefulODE {
    private final FirstOrderDifferentialEquations primary;
    private final EquationsMapper primaryMapper;
    private final double[] primaryState;
    private final double[] primaryStateDot;
    private List<SecondaryComponent> components;
    private double time;

    public ExpandableStatefulODE(FirstOrderDifferentialEquations firstOrderDifferentialEquations) {
        int dimension = firstOrderDifferentialEquations.getDimension();
        this.primary = firstOrderDifferentialEquations;
        this.primaryMapper = new EquationsMapper(0, dimension);
        this.time = Double.NaN;
        this.primaryState = new double[dimension];
        this.primaryStateDot = new double[dimension];
        this.components = new ArrayList();
    }

    public FirstOrderDifferentialEquations getPrimary() {
        return this.primary;
    }

    public EquationsMapper getPrimaryMapper() {
        return this.primaryMapper;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public int getTotalDimension() {
        if (this.components.isEmpty()) {
            return this.primaryMapper.getDimension();
        }
        EquationsMapper equationsMapper = this.components.get(r0.size() - 1).mapper;
        return equationsMapper.getFirstIndex() + equationsMapper.getDimension();
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws DimensionMismatchException, MaxCountExceededException {
        this.primaryMapper.extractEquationData(dArr, this.primaryState);
        this.primary.computeDerivatives(d, this.primaryState, this.primaryStateDot);
        for (SecondaryComponent secondaryComponent : this.components) {
            secondaryComponent.mapper.extractEquationData(dArr, secondaryComponent.state);
            secondaryComponent.equation.computeDerivatives(d, this.primaryState, this.primaryStateDot, secondaryComponent.state, secondaryComponent.stateDot);
            secondaryComponent.mapper.insertEquationData(secondaryComponent.stateDot, dArr2);
        }
        this.primaryMapper.insertEquationData(this.primaryStateDot, dArr2);
    }

    public int addSecondaryEquations(SecondaryEquations secondaryEquations) {
        int dimension;
        if (this.components.isEmpty()) {
            this.components = new ArrayList();
            dimension = this.primary.getDimension();
        } else {
            SecondaryComponent secondaryComponent = this.components.get(r0.size() - 1);
            dimension = secondaryComponent.mapper.getDimension() + secondaryComponent.mapper.getFirstIndex();
        }
        this.components.add(new SecondaryComponent(secondaryEquations, dimension));
        return this.components.size() - 1;
    }

    public EquationsMapper[] getSecondaryMappers() {
        int size = this.components.size();
        EquationsMapper[] equationsMapperArr = new EquationsMapper[size];
        for (int i = 0; i < size; i++) {
            equationsMapperArr[i] = this.components.get(i).mapper;
        }
        return equationsMapperArr;
    }

    public double[] getPrimaryState() {
        return (double[]) this.primaryState.clone();
    }

    public void setPrimaryState(double[] dArr) throws DimensionMismatchException {
        int length = dArr.length;
        double[] dArr2 = this.primaryState;
        if (length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, this.primaryState.length);
        }
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
    }

    public double[] getPrimaryStateDot() {
        return (double[]) this.primaryStateDot.clone();
    }

    public void setSecondaryState(int i, double[] dArr) throws DimensionMismatchException {
        double[] dArr2 = this.components.get(i).state;
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
    }

    public double[] getSecondaryState(int i) {
        return (double[]) this.components.get(i).state.clone();
    }

    public double[] getSecondaryStateDot(int i) {
        return (double[]) this.components.get(i).stateDot.clone();
    }

    public double[] getCompleteState() throws DimensionMismatchException {
        double[] dArr = new double[getTotalDimension()];
        this.primaryMapper.insertEquationData(this.primaryState, dArr);
        for (SecondaryComponent secondaryComponent : this.components) {
            secondaryComponent.mapper.insertEquationData(secondaryComponent.state, dArr);
        }
        return dArr;
    }

    public void setCompleteState(double[] dArr) throws DimensionMismatchException {
        if (dArr.length != getTotalDimension()) {
            throw new DimensionMismatchException(dArr.length, getTotalDimension());
        }
        this.primaryMapper.extractEquationData(dArr, this.primaryState);
        for (SecondaryComponent secondaryComponent : this.components) {
            secondaryComponent.mapper.extractEquationData(dArr, secondaryComponent.state);
        }
    }

    private static class SecondaryComponent {
        private final SecondaryEquations equation;
        private final EquationsMapper mapper;
        private final double[] state;
        private final double[] stateDot;

        SecondaryComponent(SecondaryEquations secondaryEquations, int i) {
            int dimension = secondaryEquations.getDimension();
            this.equation = secondaryEquations;
            this.mapper = new EquationsMapper(i, dimension);
            this.state = new double[dimension];
            this.stateDot = new double[dimension];
        }
    }
}
