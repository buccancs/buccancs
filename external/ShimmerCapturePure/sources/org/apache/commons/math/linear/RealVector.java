package org.apache.commons.math.linear;

import java.util.Iterator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/RealVector.class */
public interface RealVector {
    RealVector mapToSelf(UnivariateRealFunction univariateRealFunction) throws FunctionEvaluationException;

    RealVector map(UnivariateRealFunction univariateRealFunction) throws FunctionEvaluationException;

    Iterator<Entry> iterator();

    Iterator<Entry> sparseIterator();

    RealVector copy();

    RealVector add(RealVector realVector);

    RealVector add(double[] dArr);

    RealVector subtract(RealVector realVector);

    RealVector subtract(double[] dArr);

    RealVector mapAdd(double d);

    RealVector mapAddToSelf(double d);

    RealVector mapSubtract(double d);

    RealVector mapSubtractToSelf(double d);

    RealVector mapMultiply(double d);

    RealVector mapMultiplyToSelf(double d);

    RealVector mapDivide(double d);

    RealVector mapDivideToSelf(double d);

    @Deprecated
    RealVector mapPow(double d);

    @Deprecated
    RealVector mapPowToSelf(double d);

    @Deprecated
    RealVector mapExp();

    @Deprecated
    RealVector mapExpToSelf();

    @Deprecated
    RealVector mapExpm1();

    @Deprecated
    RealVector mapExpm1ToSelf();

    @Deprecated
    RealVector mapLog();

    @Deprecated
    RealVector mapLogToSelf();

    @Deprecated
    RealVector mapLog10();

    @Deprecated
    RealVector mapLog10ToSelf();

    @Deprecated
    RealVector mapLog1p();

    @Deprecated
    RealVector mapLog1pToSelf();

    @Deprecated
    RealVector mapCosh();

    @Deprecated
    RealVector mapCoshToSelf();

    @Deprecated
    RealVector mapSinh();

    @Deprecated
    RealVector mapSinhToSelf();

    @Deprecated
    RealVector mapTanh();

    @Deprecated
    RealVector mapTanhToSelf();

    @Deprecated
    RealVector mapCos();

    @Deprecated
    RealVector mapCosToSelf();

    @Deprecated
    RealVector mapSin();

    @Deprecated
    RealVector mapSinToSelf();

    @Deprecated
    RealVector mapTan();

    @Deprecated
    RealVector mapTanToSelf();

    @Deprecated
    RealVector mapAcos();

    @Deprecated
    RealVector mapAcosToSelf();

    @Deprecated
    RealVector mapAsin();

    @Deprecated
    RealVector mapAsinToSelf();

    @Deprecated
    RealVector mapAtan();

    @Deprecated
    RealVector mapAtanToSelf();

    @Deprecated
    RealVector mapInv();

    @Deprecated
    RealVector mapInvToSelf();

    @Deprecated
    RealVector mapAbs();

    @Deprecated
    RealVector mapAbsToSelf();

    @Deprecated
    RealVector mapSqrt();

    @Deprecated
    RealVector mapSqrtToSelf();

    @Deprecated
    RealVector mapCbrt();

    @Deprecated
    RealVector mapCbrtToSelf();

    @Deprecated
    RealVector mapCeil();

    @Deprecated
    RealVector mapCeilToSelf();

    @Deprecated
    RealVector mapFloor();

    @Deprecated
    RealVector mapFloorToSelf();

    @Deprecated
    RealVector mapRint();

    @Deprecated
    RealVector mapRintToSelf();

    @Deprecated
    RealVector mapSignum();

    @Deprecated
    RealVector mapSignumToSelf();

    @Deprecated
    RealVector mapUlp();

    @Deprecated
    RealVector mapUlpToSelf();

    RealVector ebeMultiply(RealVector realVector);

    RealVector ebeMultiply(double[] dArr);

    RealVector ebeDivide(RealVector realVector);

    RealVector ebeDivide(double[] dArr);

    double[] getData();

    double dotProduct(RealVector realVector);

    double dotProduct(double[] dArr);

    double getNorm();

    double getL1Norm();

    double getLInfNorm();

    double getDistance(RealVector realVector);

    double getDistance(double[] dArr);

    double getL1Distance(RealVector realVector);

    double getL1Distance(double[] dArr);

    double getLInfDistance(RealVector realVector);

    double getLInfDistance(double[] dArr);

    RealVector unitVector();

    void unitize();

    RealVector projection(RealVector realVector);

    RealVector projection(double[] dArr);

    RealMatrix outerProduct(RealVector realVector);

    RealMatrix outerProduct(double[] dArr);

    double getEntry(int i);

    void setEntry(int i, double d);

    int getDimension();

    RealVector append(RealVector realVector);

    RealVector append(double d);

    RealVector append(double[] dArr);

    RealVector getSubVector(int i, int i2);

    void setSubVector(int i, RealVector realVector);

    void setSubVector(int i, double[] dArr);

    void set(double d);

    double[] toArray();

    boolean isNaN();

    boolean isInfinite();

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/RealVector$Entry.class */
    public static abstract class Entry {
        private int index;

        public abstract double getValue();

        public abstract void setValue(double d);

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
