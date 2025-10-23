package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;

/* loaded from: classes5.dex */
public interface UpdatingMultipleLinearRegression {
    void addObservation(double[] dArr, double d) throws ModelSpecificationException;

    void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException;

    void clear();

    long getN();

    boolean hasIntercept();

    RegressionResults regress() throws NoDataException, ModelSpecificationException;

    RegressionResults regress(int[] iArr) throws MathIllegalArgumentException;
}
