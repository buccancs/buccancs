package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class ArrayRealVector extends RealVector implements Serializable {
    private static final RealVectorFormat DEFAULT_FORMAT = RealVectorFormat.getInstance();
    private static final long serialVersionUID = -1097961340710804027L;
    private double[] data;

    public ArrayRealVector() {
        this.data = new double[0];
    }

    public ArrayRealVector(int i) {
        this.data = new double[i];
    }

    public ArrayRealVector(int i, double d) {
        double[] dArr = new double[i];
        this.data = dArr;
        Arrays.fill(dArr, d);
    }

    public ArrayRealVector(double[] dArr) {
        this.data = (double[]) dArr.clone();
    }

    public ArrayRealVector(double[] dArr, boolean z) throws NullArgumentException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        this.data = z ? (double[]) dArr.clone() : dArr;
    }

    public ArrayRealVector(double[] dArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (dArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        }
        double[] dArr2 = new double[i2];
        this.data = dArr2;
        System.arraycopy(dArr, i, dArr2, 0, i2);
    }

    public ArrayRealVector(Double[] dArr) {
        this.data = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            this.data[i] = dArr[i].doubleValue();
        }
    }

    public ArrayRealVector(Double[] dArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (dArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        }
        this.data = new double[i2];
        for (int i4 = i; i4 < i3; i4++) {
            this.data[i4 - i] = dArr[i4].doubleValue();
        }
    }

    public ArrayRealVector(RealVector realVector) throws NullArgumentException {
        if (realVector == null) {
            throw new NullArgumentException();
        }
        this.data = new double[realVector.getDimension()];
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return;
            }
            dArr[i] = realVector.getEntry(i);
            i++;
        }
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector) throws NullArgumentException {
        this(arrayRealVector, true);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, boolean z) {
        double[] dArr = arrayRealVector.data;
        this.data = z ? (double[]) dArr.clone() : dArr;
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, ArrayRealVector arrayRealVector2) {
        double[] dArr = new double[arrayRealVector.data.length + arrayRealVector2.data.length];
        this.data = dArr;
        double[] dArr2 = arrayRealVector.data;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        double[] dArr3 = arrayRealVector2.data;
        System.arraycopy(dArr3, 0, this.data, arrayRealVector.data.length, dArr3.length);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, RealVector realVector) {
        int length = arrayRealVector.data.length;
        int dimension = realVector.getDimension();
        double[] dArr = new double[length + dimension];
        this.data = dArr;
        System.arraycopy(arrayRealVector.data, 0, dArr, 0, length);
        for (int i = 0; i < dimension; i++) {
            this.data[length + i] = realVector.getEntry(i);
        }
    }

    public ArrayRealVector(RealVector realVector, ArrayRealVector arrayRealVector) {
        int dimension = realVector.getDimension();
        int length = arrayRealVector.data.length;
        this.data = new double[dimension + length];
        for (int i = 0; i < dimension; i++) {
            this.data[i] = realVector.getEntry(i);
        }
        System.arraycopy(arrayRealVector.data, 0, this.data, dimension, length);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, double[] dArr) {
        int dimension = arrayRealVector.getDimension();
        int length = dArr.length;
        double[] dArr2 = new double[dimension + length];
        this.data = dArr2;
        System.arraycopy(arrayRealVector.data, 0, dArr2, 0, dimension);
        System.arraycopy(dArr, 0, this.data, dimension, length);
    }

    public ArrayRealVector(double[] dArr, ArrayRealVector arrayRealVector) {
        int length = dArr.length;
        int dimension = arrayRealVector.getDimension();
        double[] dArr2 = new double[length + dimension];
        this.data = dArr2;
        System.arraycopy(dArr, 0, dArr2, 0, length);
        System.arraycopy(arrayRealVector.data, 0, this.data, length, dimension);
    }

    public ArrayRealVector(double[] dArr, double[] dArr2) {
        int length = dArr.length;
        int length2 = dArr2.length;
        double[] dArr3 = new double[length + length2];
        this.data = dArr3;
        System.arraycopy(dArr, 0, dArr3, 0, length);
        System.arraycopy(dArr2, 0, this.data, length, length2);
    }

    public double[] getDataRef() {
        return this.data;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector copy() {
        return new ArrayRealVector(this, true);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector add(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] + dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        Iterator<RealVector.Entry> it2 = realVector.iterator();
        while (it2.hasNext()) {
            RealVector.Entry next = it2.next();
            int index = next.getIndex();
            dArr3[index] = dArr3[index] + next.getValue();
        }
        return new ArrayRealVector(dArr3, false);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector subtract(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] - dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        Iterator<RealVector.Entry> it2 = realVector.iterator();
        while (it2.hasNext()) {
            RealVector.Entry next = it2.next();
            int index = next.getIndex();
            dArr3[index] = dArr3[index] - next.getValue();
        }
        return new ArrayRealVector(dArr3, false);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector map(UnivariateFunction univariateFunction) {
        return copy().mapToSelf(univariateFunction);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector mapToSelf(UnivariateFunction univariateFunction) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return this;
            }
            dArr[i] = univariateFunction.value(dArr[i]);
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector mapAddToSelf(double d) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return this;
            }
            dArr[i] = dArr[i] + d;
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector mapSubtractToSelf(double d) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return this;
            }
            dArr[i] = dArr[i] - d;
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector mapMultiplyToSelf(double d) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return this;
            }
            dArr[i] = dArr[i] * d;
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector mapDivideToSelf(double d) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return this;
            }
            dArr[i] = dArr[i] / d;
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] * dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        for (int i2 = 0; i2 < this.data.length; i2++) {
            dArr3[i2] = dArr3[i2] * realVector.getEntry(i2);
        }
        return new ArrayRealVector(dArr3, false);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector ebeDivide(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] / dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        for (int i2 = 0; i2 < this.data.length; i2++) {
            dArr3[i2] = dArr3[i2] / realVector.getEntry(i2);
        }
        return new ArrayRealVector(dArr3, false);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double dotProduct(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            double d = 0.0d;
            int i = 0;
            while (true) {
                double[] dArr2 = this.data;
                if (i >= dArr2.length) {
                    return d;
                }
                d += dArr2[i] * dArr[i];
                i++;
            }
        } else {
            return super.dotProduct(realVector);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getNorm() {
        double d = 0.0d;
        for (double d2 : this.data) {
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getL1Norm() {
        double dAbs = 0.0d;
        for (double d : this.data) {
            dAbs += FastMath.abs(d);
        }
        return dAbs;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getLInfNorm() {
        double dMax = 0.0d;
        for (double d : this.data) {
            dMax = FastMath.max(dMax, FastMath.abs(d));
        }
        return dMax;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getDistance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double d = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (true) {
                double[] dArr2 = this.data;
                if (i < dArr2.length) {
                    double d2 = dArr2[i] - dArr[i];
                    d += d2 * d2;
                    i++;
                } else {
                    return FastMath.sqrt(d);
                }
            }
        } else {
            checkVectorDimensions(realVector);
            while (true) {
                double[] dArr3 = this.data;
                if (i < dArr3.length) {
                    double entry = dArr3[i] - realVector.getEntry(i);
                    d += entry * entry;
                    i++;
                } else {
                    return FastMath.sqrt(d);
                }
            }
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double dAbs = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (true) {
                double[] dArr2 = this.data;
                if (i >= dArr2.length) {
                    return dAbs;
                }
                dAbs += FastMath.abs(dArr2[i] - dArr[i]);
                i++;
            }
        } else {
            checkVectorDimensions(realVector);
            while (true) {
                double[] dArr3 = this.data;
                if (i >= dArr3.length) {
                    return dAbs;
                }
                dAbs += FastMath.abs(dArr3[i] - realVector.getEntry(i));
                i++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double dMax = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (true) {
                double[] dArr2 = this.data;
                if (i >= dArr2.length) {
                    return dMax;
                }
                dMax = FastMath.max(dMax, FastMath.abs(dArr2[i] - dArr[i]));
                i++;
            }
        } else {
            checkVectorDimensions(realVector);
            while (true) {
                double[] dArr3 = this.data;
                if (i >= dArr3.length) {
                    return dMax;
                }
                dMax = FastMath.max(dMax, FastMath.abs(dArr3[i] - realVector.getEntry(i)));
                i++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealMatrix outerProduct(RealVector realVector) throws OutOfRangeException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = this.data.length;
            int length2 = dArr.length;
            RealMatrix realMatrixCreateRealMatrix = MatrixUtils.createRealMatrix(length, length2);
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < length2; i2++) {
                    realMatrixCreateRealMatrix.setEntry(i, i2, this.data[i] * dArr[i2]);
                }
            }
            return realMatrixCreateRealMatrix;
        }
        int length3 = this.data.length;
        int dimension = realVector.getDimension();
        RealMatrix realMatrixCreateRealMatrix2 = MatrixUtils.createRealMatrix(length3, dimension);
        for (int i3 = 0; i3 < length3; i3++) {
            for (int i4 = 0; i4 < dimension; i4++) {
                realMatrixCreateRealMatrix2.setEntry(i3, i4, this.data[i3] * realVector.getEntry(i4));
            }
        }
        return realMatrixCreateRealMatrix2;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getEntry(int i) throws OutOfRangeException {
        try {
            return this.data[i];
        } catch (IndexOutOfBoundsException unused) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), 0, Integer.valueOf(getDimension() - 1));
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public int getDimension() {
        return this.data.length;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector append(RealVector realVector) {
        try {
            return new ArrayRealVector(this, (ArrayRealVector) realVector);
        } catch (ClassCastException unused) {
            return new ArrayRealVector(this, realVector);
        }
    }

    public ArrayRealVector append(ArrayRealVector arrayRealVector) {
        return new ArrayRealVector(this, arrayRealVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector append(double d) {
        double[] dArr = this.data;
        double[] dArr2 = new double[dArr.length + 1];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        dArr2[this.data.length] = d;
        return new ArrayRealVector(dArr2, false);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        ArrayRealVector arrayRealVector = new ArrayRealVector(i2);
        try {
            System.arraycopy(this.data, i, arrayRealVector.data, 0, i2);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + i2) - 1);
        }
        return arrayRealVector;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void setEntry(int i, double d) throws OutOfRangeException {
        try {
            this.data[i] = d;
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void addToEntry(int i, double d) throws OutOfRangeException {
        try {
            double[] dArr = this.data;
            dArr[i] = dArr[i] + d;
        } catch (IndexOutOfBoundsException unused) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), 0, Integer.valueOf(this.data.length - 1));
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void setSubVector(int i, RealVector realVector) throws OutOfRangeException {
        if (realVector instanceof ArrayRealVector) {
            setSubVector(i, ((ArrayRealVector) realVector).data);
            return;
        }
        for (int i2 = i; i2 < realVector.getDimension() + i; i2++) {
            try {
                this.data[i2] = realVector.getEntry(i2 - i);
            } catch (IndexOutOfBoundsException unused) {
                checkIndex(i);
                checkIndex((i + realVector.getDimension()) - 1);
                return;
            }
        }
    }

    public void setSubVector(int i, double[] dArr) throws OutOfRangeException {
        try {
            System.arraycopy(dArr, 0, this.data, i, dArr.length);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + dArr.length) - 1);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void set(double d) {
        Arrays.fill(this.data, d);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double[] toArray() {
        return (double[]) this.data.clone();
    }

    public String toString() {
        return DEFAULT_FORMAT.format(this);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    protected void checkVectorDimensions(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
    }

    @Override // org.apache.commons.math3.linear.RealVector
    protected void checkVectorDimensions(int i) throws DimensionMismatchException {
        if (this.data.length != i) {
            throw new DimensionMismatchException(this.data.length, i);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean isNaN() {
        for (double d : this.data) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean isInfinite() {
        if (isNaN()) {
            return false;
        }
        for (double d : this.data) {
            if (Double.isInfinite(d)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RealVector)) {
            return false;
        }
        RealVector realVector = (RealVector) obj;
        if (this.data.length != realVector.getDimension()) {
            return false;
        }
        if (realVector.isNaN()) {
            return isNaN();
        }
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return true;
            }
            if (dArr[i] != realVector.getEntry(i)) {
                return false;
            }
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public int hashCode() {
        if (isNaN()) {
            return 9;
        }
        return MathUtils.hash(this.data);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector combine(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        return copy().combineToSelf(d, d2, realVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public ArrayRealVector combineToSelf(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (true) {
                double[] dArr2 = this.data;
                if (i >= dArr2.length) {
                    break;
                }
                dArr2[i] = (dArr2[i] * d) + (dArr[i] * d2);
                i++;
            }
        } else {
            checkVectorDimensions(realVector);
            while (true) {
                double[] dArr3 = this.data;
                if (i >= dArr3.length) {
                    break;
                }
                dArr3[i] = (dArr3[i] * d) + (realVector.getEntry(i) * d2);
                i++;
            }
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor) {
        int i = 0;
        realVectorPreservingVisitor.start(this.data.length, 0, r0.length - 1);
        while (true) {
            double[] dArr = this.data;
            if (i < dArr.length) {
                realVectorPreservingVisitor.visit(i, dArr[i]);
                i++;
            } else {
                return realVectorPreservingVisitor.end();
            }
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorPreservingVisitor.start(this.data.length, i, i2);
        while (i <= i2) {
            realVectorPreservingVisitor.visit(i, this.data[i]);
            i++;
        }
        return realVectorPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInOptimizedOrder(RealVectorPreservingVisitor realVectorPreservingVisitor) {
        return walkInDefaultOrder(realVectorPreservingVisitor);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInOptimizedOrder(RealVectorPreservingVisitor realVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(realVectorPreservingVisitor, i, i2);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInDefaultOrder(RealVectorChangingVisitor realVectorChangingVisitor) {
        int i = 0;
        realVectorChangingVisitor.start(this.data.length, 0, r0.length - 1);
        while (true) {
            double[] dArr = this.data;
            if (i < dArr.length) {
                dArr[i] = realVectorChangingVisitor.visit(i, dArr[i]);
                i++;
            } else {
                return realVectorChangingVisitor.end();
            }
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInDefaultOrder(RealVectorChangingVisitor realVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorChangingVisitor.start(this.data.length, i, i2);
        while (i <= i2) {
            double[] dArr = this.data;
            dArr[i] = realVectorChangingVisitor.visit(i, dArr[i]);
            i++;
        }
        return realVectorChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInOptimizedOrder(RealVectorChangingVisitor realVectorChangingVisitor) {
        return walkInDefaultOrder(realVectorChangingVisitor);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double walkInOptimizedOrder(RealVectorChangingVisitor realVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(realVectorChangingVisitor, i, i2);
    }
}
