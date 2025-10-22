package org.apache.commons.math.optimization.linear;

import com.shimmerresearch.driver.Configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/linear/SimplexTableau.class */
class SimplexTableau implements Serializable {
    private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
    private static final long serialVersionUID = -1369660067587938365L;
    private final LinearObjectiveFunction f;
    private final List<LinearConstraint> constraints;
    private final boolean restrictToNonNegative;
    private final List<String> columnLabels = new ArrayList();
    private final int numDecisionVariables;
    private final int numSlackVariables;
    private final double epsilon;
    private transient RealMatrix tableau;
    private int numArtificialVariables;

    SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon) {
        this.f = f;
        this.constraints = normalizeConstraints(constraints);
        this.restrictToNonNegative = restrictToNonNegative;
        this.epsilon = epsilon;
        this.numDecisionVariables = f.getCoefficients().getDimension() + (restrictToNonNegative ? 0 : 1);
        this.numSlackVariables = getConstraintTypeCounts(Relationship.LEQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.tableau = createTableau(goalType == GoalType.MAXIMIZE);
        initializeColumnLabels();
    }

    protected static double getInvertedCoeffiecientSum(RealVector coefficients) {
        double sum = 0.0d;
        double[] arr$ = coefficients.getData();
        for (double coefficient : arr$) {
            sum -= coefficient;
        }
        return sum;
    }

    protected void initializeColumnLabels() {
        if (getNumObjectiveFunctions() == 2) {
            this.columnLabels.add("W");
        }
        this.columnLabels.add("Z");
        for (int i = 0; i < getOriginalNumDecisionVariables(); i++) {
            this.columnLabels.add("x" + i);
        }
        if (!this.restrictToNonNegative) {
            this.columnLabels.add(NEGATIVE_VAR_COLUMN_LABEL);
        }
        for (int i2 = 0; i2 < getNumSlackVariables(); i2++) {
            this.columnLabels.add(Configuration.CHANNEL_UNITS.SECONDS + i2);
        }
        for (int i3 = 0; i3 < getNumArtificialVariables(); i3++) {
            this.columnLabels.add("a" + i3);
        }
        this.columnLabels.add("RHS");
    }

    protected RealMatrix createTableau(boolean maximize) throws MatrixIndexException {
        int width = this.numDecisionVariables + this.numSlackVariables + this.numArtificialVariables + getNumObjectiveFunctions() + 1;
        int height = this.constraints.size() + getNumObjectiveFunctions();
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(height, width);
        if (getNumObjectiveFunctions() == 2) {
            matrix.setEntry(0, 0, -1.0d);
        }
        int zIndex = getNumObjectiveFunctions() == 1 ? 0 : 1;
        matrix.setEntry(zIndex, zIndex, maximize ? 1.0d : -1.0d);
        RealVector objectiveCoefficients = maximize ? this.f.getCoefficients().mapMultiply(-1.0d) : this.f.getCoefficients();
        copyArray(objectiveCoefficients.getData(), matrix.getDataRef()[zIndex]);
        matrix.setEntry(zIndex, width - 1, maximize ? this.f.getConstantTerm() : (-1.0d) * this.f.getConstantTerm());
        if (!this.restrictToNonNegative) {
            matrix.setEntry(zIndex, getSlackVariableOffset() - 1, getInvertedCoeffiecientSum(objectiveCoefficients));
        }
        int slackVar = 0;
        int artificialVar = 0;
        for (int i = 0; i < this.constraints.size(); i++) {
            LinearConstraint constraint = this.constraints.get(i);
            int row = getNumObjectiveFunctions() + i;
            copyArray(constraint.getCoefficients().getData(), matrix.getDataRef()[row]);
            if (!this.restrictToNonNegative) {
                matrix.setEntry(row, getSlackVariableOffset() - 1, getInvertedCoeffiecientSum(constraint.getCoefficients()));
            }
            matrix.setEntry(row, width - 1, constraint.getValue());
            if (constraint.getRelationship() == Relationship.LEQ) {
                int i2 = slackVar;
                slackVar++;
                matrix.setEntry(row, getSlackVariableOffset() + i2, 1.0d);
            } else if (constraint.getRelationship() == Relationship.GEQ) {
                int i3 = slackVar;
                slackVar++;
                matrix.setEntry(row, getSlackVariableOffset() + i3, -1.0d);
            }
            if (constraint.getRelationship() == Relationship.EQ || constraint.getRelationship() == Relationship.GEQ) {
                matrix.setEntry(0, getArtificialVariableOffset() + artificialVar, 1.0d);
                int i4 = artificialVar;
                artificialVar++;
                matrix.setEntry(row, getArtificialVariableOffset() + i4, 1.0d);
                matrix.setRowVector(0, matrix.getRowVector(0).subtract(matrix.getRowVector(row)));
            }
        }
        return matrix;
    }

    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> originalConstraints) {
        List<LinearConstraint> normalized = new ArrayList<>();
        for (LinearConstraint constraint : originalConstraints) {
            normalized.add(normalize(constraint));
        }
        return normalized;
    }

    private LinearConstraint normalize(LinearConstraint constraint) {
        if (constraint.getValue() < 0.0d) {
            return new LinearConstraint(constraint.getCoefficients().mapMultiply(-1.0d), constraint.getRelationship().oppositeRelationship(), (-1.0d) * constraint.getValue());
        }
        return new LinearConstraint(constraint.getCoefficients(), constraint.getRelationship(), constraint.getValue());
    }

    protected final int getNumObjectiveFunctions() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    private int getConstraintTypeCounts(Relationship relationship) {
        int count = 0;
        for (LinearConstraint constraint : this.constraints) {
            if (constraint.getRelationship() == relationship) {
                count++;
            }
        }
        return count;
    }

    protected Integer getBasicRow(int col) {
        Integer row = null;
        for (int i = 0; i < getHeight(); i++) {
            if (MathUtils.equals(getEntry(i, col), 1.0d, this.epsilon) && row == null) {
                row = Integer.valueOf(i);
            } else if (!MathUtils.equals(getEntry(i, col), 0.0d, this.epsilon)) {
                return null;
            }
        }
        return row;
    }

    protected void dropPhase1Objective() {
        if (getNumObjectiveFunctions() == 1) {
            return;
        }
        List<Integer> columnsToDrop = new ArrayList<>();
        columnsToDrop.add(0);
        for (int i = getNumObjectiveFunctions(); i < getArtificialVariableOffset(); i++) {
            if (MathUtils.compareTo(this.tableau.getEntry(0, i), 0.0d, this.epsilon) > 0) {
                columnsToDrop.add(Integer.valueOf(i));
            }
        }
        for (int i2 = 0; i2 < getNumArtificialVariables(); i2++) {
            int col = i2 + getArtificialVariableOffset();
            if (getBasicRow(col) == null) {
                columnsToDrop.add(Integer.valueOf(col));
            }
        }
        double[][] matrix = new double[getHeight() - 1][getWidth() - columnsToDrop.size()];
        for (int i3 = 1; i3 < getHeight(); i3++) {
            int col2 = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (!columnsToDrop.contains(Integer.valueOf(j))) {
                    int i4 = col2;
                    col2++;
                    matrix[i3 - 1][i4] = this.tableau.getEntry(i3, j);
                }
            }
        }
        for (int i5 = columnsToDrop.size() - 1; i5 >= 0; i5--) {
            this.columnLabels.remove(columnsToDrop.get(i5).intValue());
        }
        this.tableau = new Array2DRowRealMatrix(matrix);
        this.numArtificialVariables = 0;
    }

    private void copyArray(double[] src, double[] dest) {
        System.arraycopy(src, 0, dest, getNumObjectiveFunctions(), src.length);
    }

    boolean isOptimal() {
        for (int i = getNumObjectiveFunctions(); i < getWidth() - 1; i++) {
            if (MathUtils.compareTo(this.tableau.getEntry(0, i), 0.0d, this.epsilon) < 0) {
                return false;
            }
        }
        return true;
    }

    protected RealPointValuePair getSolution() {
        int negativeVarColumn = this.columnLabels.indexOf(NEGATIVE_VAR_COLUMN_LABEL);
        Integer negativeVarBasicRow = negativeVarColumn > 0 ? getBasicRow(negativeVarColumn) : null;
        double mostNegative = negativeVarBasicRow == null ? 0.0d : getEntry(negativeVarBasicRow.intValue(), getRhsOffset());
        Set<Integer> basicRows = new HashSet<>();
        double[] coefficients = new double[getOriginalNumDecisionVariables()];
        for (int i = 0; i < coefficients.length; i++) {
            int colIndex = this.columnLabels.indexOf("x" + i);
            if (colIndex < 0) {
                coefficients[i] = 0.0d;
            } else {
                Integer basicRow = getBasicRow(colIndex);
                if (basicRows.contains(basicRow)) {
                    coefficients[i] = 0.0d;
                } else {
                    basicRows.add(basicRow);
                    coefficients[i] = (basicRow == null ? 0.0d : getEntry(basicRow.intValue(), getRhsOffset())) - (this.restrictToNonNegative ? 0.0d : mostNegative);
                }
            }
        }
        return new RealPointValuePair(coefficients, this.f.getValue(coefficients));
    }

    protected void divideRow(int dividendRow, double divisor) throws MatrixIndexException {
        for (int j = 0; j < getWidth(); j++) {
            this.tableau.setEntry(dividendRow, j, this.tableau.getEntry(dividendRow, j) / divisor);
        }
    }

    protected void subtractRow(int minuendRow, int subtrahendRow, double multiple) throws MatrixIndexException, InvalidMatrixException {
        this.tableau.setRowVector(minuendRow, this.tableau.getRowVector(minuendRow).subtract(this.tableau.getRowVector(subtrahendRow).mapMultiply(multiple)));
    }

    protected final int getWidth() {
        return this.tableau.getColumnDimension();
    }

    protected final int getHeight() {
        return this.tableau.getRowDimension();
    }

    protected final double getEntry(int row, int column) {
        return this.tableau.getEntry(row, column);
    }

    protected final void setEntry(int row, int column, double value) throws MatrixIndexException {
        this.tableau.setEntry(row, column, value);
    }

    protected final int getSlackVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables;
    }

    protected final int getArtificialVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
    }

    protected final int getRhsOffset() {
        return getWidth() - 1;
    }

    protected final int getNumDecisionVariables() {
        return this.numDecisionVariables;
    }

    protected final int getOriginalNumDecisionVariables() {
        return this.f.getCoefficients().getDimension();
    }

    protected final int getNumSlackVariables() {
        return this.numSlackVariables;
    }

    protected final int getNumArtificialVariables() {
        return this.numArtificialVariables;
    }

    protected final double[][] getData() {
        return this.tableau.getData();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof SimplexTableau) {
            SimplexTableau rhs = (SimplexTableau) other;
            return this.restrictToNonNegative == rhs.restrictToNonNegative && this.numDecisionVariables == rhs.numDecisionVariables && this.numSlackVariables == rhs.numSlackVariables && this.numArtificialVariables == rhs.numArtificialVariables && this.epsilon == rhs.epsilon && this.f.equals(rhs.f) && this.constraints.equals(rhs.constraints) && this.tableau.equals(rhs.tableau);
        }
        return false;
    }

    public int hashCode() {
        return ((((((Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables) ^ this.numSlackVariables) ^ this.numArtificialVariables) ^ Double.valueOf(this.epsilon).hashCode()) ^ this.f.hashCode()) ^ this.constraints.hashCode()) ^ this.tableau.hashCode();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(this.tableau, oos);
    }

    private void readObject(ObjectInputStream ois) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IOException, IllegalArgumentException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", ois);
    }
}
