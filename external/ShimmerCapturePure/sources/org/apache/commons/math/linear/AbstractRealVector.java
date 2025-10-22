package org.apache.commons.math.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.BinaryFunction;
import org.apache.commons.math.analysis.ComposableFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.DimensionMismatchException;
import org.apache.commons.math.exception.MathUnsupportedOperationException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/AbstractRealVector.class */
public abstract class AbstractRealVector implements RealVector {
    @Override // org.apache.commons.math.linear.RealVector
    public abstract AbstractRealVector copy();

    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        int d = getDimension();
        if (d != n) {
            throw new DimensionMismatchException(d, n);
        }
    }

    protected void checkIndex(int index) throws MatrixIndexException {
        if (index < 0 || index >= getDimension()) {
            throw new MatrixIndexException(LocalizedFormats.INDEX_OUT_OF_RANGE, Integer.valueOf(index), 0, Integer.valueOf(getDimension() - 1));
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public void setSubVector(int index, RealVector v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex((index + v.getDimension()) - 1);
        setSubVector(index, v.getData());
    }

    @Override // org.apache.commons.math.linear.RealVector
    public void setSubVector(int index, double[] v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex((index + v.length) - 1);
        for (int i = 0; i < v.length; i++) {
            setEntry(i + index, v[i]);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector add(double[] v) throws IllegalArgumentException {
        RealVector.Entry e;
        double[] result = (double[]) v.clone();
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            int index = e.getIndex();
            result[index] = result[index] + e.getValue();
        }
        return new ArrayRealVector(result, false);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector add(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        if (v instanceof ArrayRealVector) {
            double[] values = ((ArrayRealVector) v).getDataRef();
            return add(values);
        }
        RealVector result = v.copy();
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            int index = e.getIndex();
            result.setEntry(index, e.getValue() + result.getEntry(index));
        }
        return result;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector subtract(double[] v) throws IllegalArgumentException {
        RealVector.Entry e;
        double[] result = (double[]) v.clone();
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            int index = e.getIndex();
            result[index] = e.getValue() - result[index];
        }
        return new ArrayRealVector(result, false);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector subtract(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        if (v instanceof ArrayRealVector) {
            double[] values = ((ArrayRealVector) v).getDataRef();
            return add(values);
        }
        RealVector result = v.copy();
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            int index = e.getIndex();
            v.setEntry(index, e.getValue() - result.getEntry(index));
        }
        return result;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAddToSelf(double d) {
        if (d != 0.0d) {
            try {
                return mapToSelf(BinaryFunction.ADD.fix1stArgument(d));
            } catch (FunctionEvaluationException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double dotProduct(double[] v) throws IllegalArgumentException {
        return dotProduct(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double dotProduct(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            d += e.getValue() * v.getEntry(e.getIndex());
        }
        return d;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector ebeDivide(double[] v) throws IllegalArgumentException {
        return ebeDivide(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector ebeMultiply(double[] v) throws IllegalArgumentException {
        return ebeMultiply(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getDistance(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            double diff = e.getValue() - v.getEntry(e.getIndex());
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getNorm() {
        RealVector.Entry e;
        double sum = 0.0d;
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            double value = e.getValue();
            sum += value * value;
        }
        return FastMath.sqrt(sum);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getL1Norm() {
        RealVector.Entry e;
        double norm = 0.0d;
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            norm += FastMath.abs(e.getValue());
        }
        return norm;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getLInfNorm() {
        RealVector.Entry e;
        double norm = 0.0d;
        Iterator<RealVector.Entry> it2 = sparseIterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            norm = FastMath.max(norm, FastMath.abs(e.getValue()));
        }
        return norm;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getDistance(double[] v) throws IllegalArgumentException {
        return getDistance(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getL1Distance(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            d += FastMath.abs(e.getValue() - v.getEntry(e.getIndex()));
        }
        return d;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getL1Distance(double[] v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v.length);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            d += FastMath.abs(e.getValue() - v[e.getIndex()]);
        }
        return d;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getLInfDistance(RealVector v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            d = FastMath.max(FastMath.abs(e.getValue() - v.getEntry(e.getIndex())), d);
        }
        return d;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getLInfDistance(double[] v) throws IllegalArgumentException {
        RealVector.Entry e;
        checkVectorDimensions(v.length);
        double d = 0.0d;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            d = FastMath.max(FastMath.abs(e.getValue() - v[e.getIndex()]), d);
        }
        return d;
    }

    public int getMinIndex() {
        int minIndex = -1;
        double minValue = Double.POSITIVE_INFINITY;
        Iterator<RealVector.Entry> iterator = iterator();
        while (iterator.hasNext()) {
            RealVector.Entry entry = iterator.next();
            if (entry.getValue() <= minValue) {
                minIndex = entry.getIndex();
                minValue = entry.getValue();
            }
        }
        return minIndex;
    }

    public double getMinValue() {
        int minIndex = getMinIndex();
        if (minIndex < 0) {
            return Double.NaN;
        }
        return getEntry(minIndex);
    }

    public int getMaxIndex() {
        int maxIndex = -1;
        double maxValue = Double.NEGATIVE_INFINITY;
        Iterator<RealVector.Entry> iterator = iterator();
        while (iterator.hasNext()) {
            RealVector.Entry entry = iterator.next();
            if (entry.getValue() >= maxValue) {
                maxIndex = entry.getIndex();
                maxValue = entry.getValue();
            }
        }
        return maxIndex;
    }

    public double getMaxValue() {
        int maxIndex = getMaxIndex();
        if (maxIndex < 0) {
            return Double.NaN;
        }
        return getEntry(maxIndex);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAbs() {
        return copy().mapAbsToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAbsToSelf() {
        try {
            return mapToSelf(ComposableFunction.ABS);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAcos() {
        return copy().mapAcosToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAcosToSelf() {
        try {
            return mapToSelf(ComposableFunction.ACOS);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAsin() {
        return copy().mapAsinToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAsinToSelf() {
        try {
            return mapToSelf(ComposableFunction.ASIN);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAtan() {
        return copy().mapAtanToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapAtanToSelf() {
        try {
            return mapToSelf(ComposableFunction.ATAN);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCbrt() {
        return copy().mapCbrtToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCbrtToSelf() {
        try {
            return mapToSelf(ComposableFunction.CBRT);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCeil() {
        return copy().mapCeilToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCeilToSelf() {
        try {
            return mapToSelf(ComposableFunction.CEIL);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCos() {
        return copy().mapCosToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCosToSelf() {
        try {
            return mapToSelf(ComposableFunction.COS);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCosh() {
        return copy().mapCoshToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapCoshToSelf() {
        try {
            return mapToSelf(ComposableFunction.COSH);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapDivide(double d) {
        return copy().mapDivideToSelf(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapDivideToSelf(double d) {
        try {
            return mapToSelf(BinaryFunction.DIVIDE.fix2ndArgument(d));
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapExp() {
        return copy().mapExpToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapExpToSelf() {
        try {
            return mapToSelf(ComposableFunction.EXP);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapExpm1() {
        return copy().mapExpm1ToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapExpm1ToSelf() {
        try {
            return mapToSelf(ComposableFunction.EXPM1);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapFloor() {
        return copy().mapFloorToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapFloorToSelf() {
        try {
            return mapToSelf(ComposableFunction.FLOOR);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapInv() {
        return copy().mapInvToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapInvToSelf() {
        try {
            return mapToSelf(ComposableFunction.INVERT);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLog() {
        return copy().mapLogToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLogToSelf() {
        try {
            return mapToSelf(ComposableFunction.LOG);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLog10() {
        return copy().mapLog10ToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLog10ToSelf() {
        try {
            return mapToSelf(ComposableFunction.LOG10);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLog1p() {
        return copy().mapLog1pToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapLog1pToSelf() {
        try {
            return mapToSelf(ComposableFunction.LOG1P);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapMultiply(double d) {
        return copy().mapMultiplyToSelf(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapMultiplyToSelf(double d) {
        try {
            return mapToSelf(BinaryFunction.MULTIPLY.fix1stArgument(d));
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapPow(double d) {
        return copy().mapPowToSelf(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapPowToSelf(double d) {
        try {
            return mapToSelf(BinaryFunction.POW.fix2ndArgument(d));
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapRint() {
        return copy().mapRintToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapRintToSelf() {
        try {
            return mapToSelf(ComposableFunction.RINT);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSignum() {
        return copy().mapSignumToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSignumToSelf() {
        try {
            return mapToSelf(ComposableFunction.SIGNUM);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSin() {
        return copy().mapSinToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSinToSelf() {
        try {
            return mapToSelf(ComposableFunction.SIN);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSinh() {
        return copy().mapSinhToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSinhToSelf() {
        try {
            return mapToSelf(ComposableFunction.SINH);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSqrt() {
        return copy().mapSqrtToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSqrtToSelf() {
        try {
            return mapToSelf(ComposableFunction.SQRT);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSubtract(double d) {
        return copy().mapSubtractToSelf(d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapSubtractToSelf(double d) {
        return mapAddToSelf(-d);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapTan() {
        return copy().mapTanToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapTanToSelf() {
        try {
            return mapToSelf(ComposableFunction.TAN);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapTanh() {
        return copy().mapTanhToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapTanhToSelf() {
        try {
            return mapToSelf(ComposableFunction.TANH);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapUlp() {
        return copy().mapUlpToSelf();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapUlpToSelf() {
        try {
            return mapToSelf(ComposableFunction.ULP);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealMatrix outerProduct(RealVector v) throws MatrixIndexException, IllegalArgumentException {
        RealMatrix product;
        RealVector.Entry thisE;
        RealVector.Entry otherE;
        if ((v instanceof SparseRealVector) || (this instanceof SparseRealVector)) {
            product = new OpenMapRealMatrix(getDimension(), v.getDimension());
        } else {
            product = new Array2DRowRealMatrix(getDimension(), v.getDimension());
        }
        Iterator<RealVector.Entry> thisIt = sparseIterator();
        while (thisIt.hasNext() && (thisE = thisIt.next()) != null) {
            Iterator<RealVector.Entry> otherIt = v.sparseIterator();
            while (otherIt.hasNext() && (otherE = otherIt.next()) != null) {
                product.setEntry(thisE.getIndex(), otherE.getIndex(), thisE.getValue() * otherE.getValue());
            }
        }
        return product;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealMatrix outerProduct(double[] v) throws IllegalArgumentException {
        return outerProduct(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector projection(double[] v) throws IllegalArgumentException {
        return projection(new ArrayRealVector(v, false));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public void set(double value) {
        RealVector.Entry e;
        Iterator<RealVector.Entry> it2 = iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            e.setValue(value);
        }
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double[] toArray() {
        int dim = getDimension();
        double[] values = new double[dim];
        for (int i = 0; i < dim; i++) {
            values[i] = getEntry(i);
        }
        return values;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double[] getData() {
        return toArray();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector unitVector() {
        RealVector copy = copy();
        copy.unitize();
        return copy;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public void unitize() {
        mapDivideToSelf(getNorm());
    }

    @Override // org.apache.commons.math.linear.RealVector
    public Iterator<RealVector.Entry> sparseIterator() {
        return new SparseEntryIterator();
    }

    @Override // org.apache.commons.math.linear.RealVector
    public Iterator<RealVector.Entry> iterator() {
        final int dim = getDimension();
        return new Iterator<RealVector.Entry>() { // from class: org.apache.commons.math.linear.AbstractRealVector.1
            private int i = 0;
            private EntryImpl e;

            {
                this.e = AbstractRealVector.this.new EntryImpl();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < dim;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public RealVector.Entry next() {
                EntryImpl entryImpl = this.e;
                int i = this.i;
                this.i = i + 1;
                entryImpl.setIndex(i);
                return this.e;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new MathUnsupportedOperationException(new Object[0]);
            }
        };
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector map(UnivariateRealFunction function) throws FunctionEvaluationException {
        return copy().mapToSelf(function);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector mapToSelf(UnivariateRealFunction function) throws FunctionEvaluationException {
        RealVector.Entry e;
        Iterator<RealVector.Entry> it2 = function.value(0.0d) == 0.0d ? sparseIterator() : iterator();
        while (it2.hasNext() && (e = it2.next()) != null) {
            e.setValue(function.value(e.getValue()));
        }
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/AbstractRealVector$EntryImpl.class */
    protected class EntryImpl extends RealVector.Entry {
        public EntryImpl() {
            setIndex(0);
        }

        @Override // org.apache.commons.math.linear.RealVector.Entry
        public double getValue() {
            return AbstractRealVector.this.getEntry(getIndex());
        }

        @Override // org.apache.commons.math.linear.RealVector.Entry
        public void setValue(double newValue) {
            AbstractRealVector.this.setEntry(getIndex(), newValue);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/AbstractRealVector$SparseEntryIterator.class */
    protected class SparseEntryIterator implements Iterator<RealVector.Entry> {
        private final int dim;
        private EntryImpl current;
        private EntryImpl next;

        protected SparseEntryIterator() {
            this.dim = AbstractRealVector.this.getDimension();
            this.current = AbstractRealVector.this.new EntryImpl();
            this.next = AbstractRealVector.this.new EntryImpl();
            if (this.next.getValue() == 0.0d) {
                advance(this.next);
            }
        }

        protected void advance(EntryImpl e) {
            if (e == null) {
                return;
            }
            do {
                e.setIndex(e.getIndex() + 1);
                if (e.getIndex() >= this.dim) {
                    break;
                }
            } while (e.getValue() == 0.0d);
            if (e.getIndex() >= this.dim) {
                e.setIndex(-1);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next.getIndex() >= 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public RealVector.Entry next() {
            int index = this.next.getIndex();
            if (index < 0) {
                throw new NoSuchElementException();
            }
            this.current.setIndex(index);
            advance(this.next);
            return this.current;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new MathUnsupportedOperationException(new Object[0]);
        }
    }
}
