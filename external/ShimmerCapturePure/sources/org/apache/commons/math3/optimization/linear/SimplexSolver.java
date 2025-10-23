package org.apache.commons.math3.optimization.linear;

import java.util.ArrayList;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes5.dex */
public class SimplexSolver extends AbstractLinearOptimizer {
    private static final double DEFAULT_EPSILON = 1.0E-6d;
    private static final int DEFAULT_ULPS = 10;
    private final double epsilon;
    private final int maxUlps;

    public SimplexSolver() {
        this(1.0E-6d, 10);
    }

    public SimplexSolver(double d, int i) {
        this.epsilon = d;
        this.maxUlps = i;
    }

    private Integer getPivotColumn(SimplexTableau simplexTableau) {
        double d = 0.0d;
        Integer numValueOf = null;
        for (int numObjectiveFunctions = simplexTableau.getNumObjectiveFunctions(); numObjectiveFunctions < simplexTableau.getWidth() - 1; numObjectiveFunctions++) {
            double entry = simplexTableau.getEntry(0, numObjectiveFunctions);
            if (entry < d) {
                numValueOf = Integer.valueOf(numObjectiveFunctions);
                d = entry;
            }
        }
        return numValueOf;
    }

    private Integer getPivotRow(SimplexTableau simplexTableau, int i) {
        ArrayList<Integer> arrayList = new ArrayList();
        double d = Double.MAX_VALUE;
        for (int numObjectiveFunctions = simplexTableau.getNumObjectiveFunctions(); numObjectiveFunctions < simplexTableau.getHeight(); numObjectiveFunctions++) {
            double entry = simplexTableau.getEntry(numObjectiveFunctions, simplexTableau.getWidth() - 1);
            double entry2 = simplexTableau.getEntry(numObjectiveFunctions, i);
            if (Precision.compareTo(entry2, 0.0d, this.maxUlps) > 0) {
                double d2 = entry / entry2;
                int iCompare = Double.compare(d2, d);
                if (iCompare == 0) {
                    arrayList.add(Integer.valueOf(numObjectiveFunctions));
                } else if (iCompare < 0) {
                    arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(numObjectiveFunctions));
                    d = d2;
                }
            }
        }
        Integer num = null;
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() > 1) {
            if (simplexTableau.getNumArtificialVariables() > 0) {
                for (Integer num2 : arrayList) {
                    for (int i2 = 0; i2 < simplexTableau.getNumArtificialVariables(); i2++) {
                        int artificialVariableOffset = simplexTableau.getArtificialVariableOffset() + i2;
                        if (Precision.equals(simplexTableau.getEntry(num2.intValue(), artificialVariableOffset), 1.0d, this.maxUlps) && num2.equals(simplexTableau.getBasicRow(artificialVariableOffset))) {
                            return num2;
                        }
                    }
                }
            }
            if (getIterations() < getMaxIterations() / 2) {
                int width = simplexTableau.getWidth();
                int numObjectiveFunctions2 = simplexTableau.getNumObjectiveFunctions();
                int width2 = simplexTableau.getWidth() - 1;
                for (Integer num3 : arrayList) {
                    for (int i3 = numObjectiveFunctions2; i3 < width2 && !num3.equals(num); i3++) {
                        Integer basicRow = simplexTableau.getBasicRow(i3);
                        if (basicRow != null && basicRow.equals(num3) && i3 < width) {
                            num = num3;
                            width = i3;
                        }
                    }
                }
                return num;
            }
        }
        return (Integer) arrayList.get(0);
    }

    protected void doIteration(SimplexTableau simplexTableau) throws OutOfRangeException, UnboundedSolutionException, MaxCountExceededException {
        incrementIterationsCounter();
        Integer pivotColumn = getPivotColumn(simplexTableau);
        Integer pivotRow = getPivotRow(simplexTableau, pivotColumn.intValue());
        if (pivotRow == null) {
            throw new UnboundedSolutionException();
        }
        simplexTableau.divideRow(pivotRow.intValue(), simplexTableau.getEntry(pivotRow.intValue(), pivotColumn.intValue()));
        for (int i = 0; i < simplexTableau.getHeight(); i++) {
            if (i != pivotRow.intValue()) {
                simplexTableau.subtractRow(i, pivotRow.intValue(), simplexTableau.getEntry(i, pivotColumn.intValue()));
            }
        }
    }

    protected void solvePhase1(SimplexTableau simplexTableau) throws OutOfRangeException, UnboundedSolutionException, MaxCountExceededException, NoFeasibleSolutionException {
        if (simplexTableau.getNumArtificialVariables() == 0) {
            return;
        }
        while (!simplexTableau.isOptimal()) {
            doIteration(simplexTableau);
        }
        if (!Precision.equals(simplexTableau.getEntry(0, simplexTableau.getRhsOffset()), 0.0d, this.epsilon)) {
            throw new NoFeasibleSolutionException();
        }
    }

    @Override // org.apache.commons.math3.optimization.linear.AbstractLinearOptimizer
    public PointValuePair doOptimize() throws OutOfRangeException, UnboundedSolutionException, MaxCountExceededException, NoFeasibleSolutionException {
        SimplexTableau simplexTableau = new SimplexTableau(getFunction(), getConstraints(), getGoalType(), restrictToNonNegative(), this.epsilon, this.maxUlps);
        solvePhase1(simplexTableau);
        simplexTableau.dropPhase1Objective();
        while (!simplexTableau.isOptimal()) {
            doIteration(simplexTableau);
        }
        return simplexTableau.getSolution();
    }
}
