package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;

/* loaded from: classes5.dex */
public class OpenMapRealVector extends SparseRealVector implements Serializable {
    public static final double DEFAULT_ZERO_TOLERANCE = 1.0E-12d;
    private static final long serialVersionUID = 8772222695580707260L;
    private final OpenIntToDoubleHashMap entries;
    private final double epsilon;
    private final int virtualSize;

    public OpenMapRealVector() {
        this(0, 1.0E-12d);
    }

    public OpenMapRealVector(int i) {
        this(i, 1.0E-12d);
    }

    public OpenMapRealVector(int i, double d) {
        this.virtualSize = i;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
    }

    protected OpenMapRealVector(OpenMapRealVector openMapRealVector, int i) {
        this.virtualSize = openMapRealVector.getDimension() + i;
        this.entries = new OpenIntToDoubleHashMap(openMapRealVector.entries);
        this.epsilon = openMapRealVector.epsilon;
    }

    public OpenMapRealVector(int i, int i2) {
        this(i, i2, 1.0E-12d);
    }

    public OpenMapRealVector(int i, int i2, double d) {
        this.virtualSize = i;
        this.entries = new OpenIntToDoubleHashMap(i2, 0.0d);
        this.epsilon = d;
    }

    public OpenMapRealVector(double[] dArr) {
        this(dArr, 1.0E-12d);
    }

    public OpenMapRealVector(double[] dArr, double d) {
        this.virtualSize = dArr.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i];
            if (!isDefaultValue(d2)) {
                this.entries.put(i, d2);
            }
        }
    }

    public OpenMapRealVector(Double[] dArr) {
        this(dArr, 1.0E-12d);
    }

    public OpenMapRealVector(Double[] dArr, double d) {
        this.virtualSize = dArr.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
        for (int i = 0; i < dArr.length; i++) {
            double dDoubleValue = dArr[i].doubleValue();
            if (!isDefaultValue(dDoubleValue)) {
                this.entries.put(i, dDoubleValue);
            }
        }
    }

    public OpenMapRealVector(OpenMapRealVector openMapRealVector) {
        this.virtualSize = openMapRealVector.getDimension();
        this.entries = new OpenIntToDoubleHashMap(openMapRealVector.getEntries());
        this.epsilon = openMapRealVector.epsilon;
    }

    public OpenMapRealVector(RealVector realVector) throws OutOfRangeException {
        this.virtualSize = realVector.getDimension();
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = 1.0E-12d;
        for (int i = 0; i < this.virtualSize; i++) {
            double entry = realVector.getEntry(i);
            if (!isDefaultValue(entry)) {
                this.entries.put(i, entry);
            }
        }
    }

    private OpenIntToDoubleHashMap getEntries() {
        return this.entries;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public int getDimension() {
        return this.virtualSize;
    }

    protected boolean isDefaultValue(double d) {
        return FastMath.abs(d) < this.epsilon;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector add(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return add((OpenMapRealVector) realVector);
        }
        return super.add(realVector);
    }

    public OpenMapRealVector add(OpenMapRealVector openMapRealVector) throws OutOfRangeException, DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(openMapRealVector.getDimension());
        boolean z = this.entries.size() > openMapRealVector.entries.size();
        OpenMapRealVector openMapRealVectorCopy = z ? copy() : openMapRealVector.copy();
        OpenIntToDoubleHashMap.Iterator it2 = (z ? openMapRealVector.entries : this.entries).iterator();
        OpenIntToDoubleHashMap openIntToDoubleHashMap = z ? this.entries : openMapRealVector.entries;
        while (it2.hasNext()) {
            it2.advance();
            int iKey = it2.key();
            if (openIntToDoubleHashMap.containsKey(iKey)) {
                openMapRealVectorCopy.setEntry(iKey, openIntToDoubleHashMap.get(iKey) + it2.value());
            } else {
                openMapRealVectorCopy.setEntry(iKey, it2.value());
            }
        }
        return openMapRealVectorCopy;
    }

    public OpenMapRealVector append(OpenMapRealVector openMapRealVector) throws OutOfRangeException, NoSuchElementException, ConcurrentModificationException {
        OpenMapRealVector openMapRealVector2 = new OpenMapRealVector(this, openMapRealVector.getDimension());
        OpenIntToDoubleHashMap.Iterator it2 = openMapRealVector.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            openMapRealVector2.setEntry(it2.key() + this.virtualSize, it2.value());
        }
        return openMapRealVector2;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector append(RealVector realVector) throws OutOfRangeException {
        if (realVector instanceof OpenMapRealVector) {
            return append((OpenMapRealVector) realVector);
        }
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this, realVector.getDimension());
        for (int i = 0; i < realVector.getDimension(); i++) {
            openMapRealVector.setEntry(this.virtualSize + i, realVector.getEntry(i));
        }
        return openMapRealVector;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector append(double d) throws OutOfRangeException {
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this, 1);
        openMapRealVector.setEntry(this.virtualSize, d);
        return openMapRealVector;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector copy() {
        return new OpenMapRealVector(this);
    }

    @Deprecated
    public double dotProduct(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        return dotProduct((RealVector) openMapRealVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector ebeDivide(RealVector realVector) throws OutOfRangeException, DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this);
        int dimension = getDimension();
        for (int i = 0; i < dimension; i++) {
            openMapRealVector.setEntry(i, getEntry(i) / realVector.getEntry(i));
        }
        return openMapRealVector;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector ebeMultiply(RealVector realVector) throws OutOfRangeException, DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(realVector.getDimension());
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            openMapRealVector.setEntry(it2.key(), it2.value() * realVector.getEntry(it2.key()));
        }
        return openMapRealVector;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException, NoSuchElementException, ConcurrentModificationException {
        checkIndex(i);
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        int i3 = i + i2;
        checkIndex(i3 - 1);
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(i2);
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            int iKey = it2.key();
            if (iKey >= i && iKey < i3) {
                openMapRealVector.setEntry(iKey - i, it2.value());
            }
        }
        return openMapRealVector;
    }

    public double getDistance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(openMapRealVector.getDimension());
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        double d = 0.0d;
        while (it2.hasNext()) {
            it2.advance();
            double dValue = it2.value() - openMapRealVector.getEntry(it2.key());
            d += dValue * dValue;
        }
        OpenIntToDoubleHashMap.Iterator it3 = openMapRealVector.getEntries().iterator();
        while (it3.hasNext()) {
            it3.advance();
            if (!this.entries.containsKey(it3.key())) {
                double dValue2 = it3.value();
                d += dValue2 * dValue2;
            }
        }
        return FastMath.sqrt(d);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getDistance((OpenMapRealVector) realVector);
        }
        return super.getDistance(realVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getEntry(int i) throws OutOfRangeException {
        checkIndex(i);
        return this.entries.get(i);
    }

    public double getL1Distance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(openMapRealVector.getDimension());
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        double dAbs = 0.0d;
        while (it2.hasNext()) {
            it2.advance();
            dAbs += FastMath.abs(it2.value() - openMapRealVector.getEntry(it2.key()));
        }
        OpenIntToDoubleHashMap.Iterator it3 = openMapRealVector.getEntries().iterator();
        while (it3.hasNext()) {
            it3.advance();
            if (!this.entries.containsKey(it3.key())) {
                dAbs += FastMath.abs(FastMath.abs(it3.value()));
            }
        }
        return dAbs;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getL1Distance((OpenMapRealVector) realVector);
        }
        return super.getL1Distance(realVector);
    }

    private double getLInfDistance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(openMapRealVector.getDimension());
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        double dValue = 0.0d;
        while (it2.hasNext()) {
            it2.advance();
            double dAbs = FastMath.abs(it2.value() - openMapRealVector.getEntry(it2.key()));
            if (dAbs > dValue) {
                dValue = dAbs;
            }
        }
        OpenIntToDoubleHashMap.Iterator it3 = openMapRealVector.getEntries().iterator();
        while (it3.hasNext()) {
            it3.advance();
            if (!this.entries.containsKey(it3.key()) && it3.value() > dValue) {
                dValue = it3.value();
            }
        }
        return dValue;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getLInfDistance((OpenMapRealVector) realVector);
        }
        return super.getLInfDistance(realVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean isInfinite() throws NoSuchElementException, ConcurrentModificationException {
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            it2.advance();
            double dValue = it2.value();
            if (Double.isNaN(dValue)) {
                return false;
            }
            if (Double.isInfinite(dValue)) {
                z = true;
            }
        }
        return z;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean isNaN() throws NoSuchElementException, ConcurrentModificationException {
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (Double.isNaN(it2.value())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector mapAddToSelf(double d) throws OutOfRangeException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, getEntry(i) + d);
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void setEntry(int i, double d) throws OutOfRangeException {
        checkIndex(i);
        if (!isDefaultValue(d)) {
            this.entries.put(i, d);
        } else if (this.entries.containsKey(i)) {
            this.entries.remove(i);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void setSubVector(int i, RealVector realVector) throws OutOfRangeException {
        checkIndex(i);
        checkIndex((realVector.getDimension() + i) - 1);
        for (int i2 = 0; i2 < realVector.getDimension(); i2++) {
            setEntry(i2 + i, realVector.getEntry(i2));
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void set(double d) throws OutOfRangeException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, d);
        }
    }

    public OpenMapRealVector subtract(OpenMapRealVector openMapRealVector) throws OutOfRangeException, DimensionMismatchException, NoSuchElementException, ConcurrentModificationException {
        checkVectorDimensions(openMapRealVector.getDimension());
        OpenMapRealVector openMapRealVectorCopy = copy();
        OpenIntToDoubleHashMap.Iterator it2 = openMapRealVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            int iKey = it2.key();
            if (this.entries.containsKey(iKey)) {
                openMapRealVectorCopy.setEntry(iKey, this.entries.get(iKey) - it2.value());
            } else {
                openMapRealVectorCopy.setEntry(iKey, -it2.value());
            }
        }
        return openMapRealVectorCopy;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public RealVector subtract(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return subtract((OpenMapRealVector) realVector);
        }
        return super.subtract(realVector);
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public OpenMapRealVector unitVector() throws NoSuchElementException, ConcurrentModificationException, MathArithmeticException {
        OpenMapRealVector openMapRealVectorCopy = copy();
        openMapRealVectorCopy.unitize();
        return openMapRealVectorCopy;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public void unitize() throws NoSuchElementException, ConcurrentModificationException, MathArithmeticException {
        double norm = getNorm();
        if (isDefaultValue(norm)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            this.entries.put(it2.key(), it2.value() / norm);
        }
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public double[] toArray() throws NoSuchElementException, ConcurrentModificationException {
        double[] dArr = new double[this.virtualSize];
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            dArr[it2.key()] = it2.value();
        }
        return dArr;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public int hashCode() throws NoSuchElementException, ConcurrentModificationException {
        long jDoubleToLongBits = Double.doubleToLongBits(this.epsilon);
        int i = ((((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) + 31) * 31) + this.virtualSize;
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            long jDoubleToLongBits2 = Double.doubleToLongBits(it2.value());
            i = (i * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >> 32)));
        }
        return i;
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public boolean equals(Object obj) throws NoSuchElementException, ConcurrentModificationException {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenMapRealVector)) {
            return false;
        }
        OpenMapRealVector openMapRealVector = (OpenMapRealVector) obj;
        if (this.virtualSize != openMapRealVector.virtualSize || Double.doubleToLongBits(this.epsilon) != Double.doubleToLongBits(openMapRealVector.epsilon)) {
            return false;
        }
        OpenIntToDoubleHashMap.Iterator it2 = this.entries.iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (Double.doubleToLongBits(openMapRealVector.getEntry(it2.key())) != Double.doubleToLongBits(it2.value())) {
                return false;
            }
        }
        OpenIntToDoubleHashMap.Iterator it3 = openMapRealVector.getEntries().iterator();
        while (it3.hasNext()) {
            it3.advance();
            if (Double.doubleToLongBits(it3.value()) != Double.doubleToLongBits(getEntry(it3.key()))) {
                return false;
            }
        }
        return true;
    }

    public double getSparsity() {
        return this.entries.size() / getDimension();
    }

    @Override // org.apache.commons.math3.linear.RealVector
    public Iterator<RealVector.Entry> sparseIterator() {
        return new OpenMapSparseIterator();
    }

    protected class OpenMapEntry extends RealVector.Entry {
        private final OpenIntToDoubleHashMap.Iterator iter;

        protected OpenMapEntry(OpenIntToDoubleHashMap.Iterator iterator) {
            super();
            this.iter = iterator;
        }

        @Override // org.apache.commons.math3.linear.RealVector.Entry
        public double getValue() {
            return this.iter.value();
        }

        @Override // org.apache.commons.math3.linear.RealVector.Entry
        public void setValue(double d) {
            OpenMapRealVector.this.entries.put(this.iter.key(), d);
        }

        @Override // org.apache.commons.math3.linear.RealVector.Entry
        public int getIndex() {
            return this.iter.key();
        }
    }

    protected class OpenMapSparseIterator implements Iterator<RealVector.Entry> {
        private final RealVector.Entry current;
        private final OpenIntToDoubleHashMap.Iterator iter;

        protected OpenMapSparseIterator() {
            OpenIntToDoubleHashMap.Iterator it2 = OpenMapRealVector.this.entries.iterator();
            this.iter = it2;
            this.current = OpenMapRealVector.this.new OpenMapEntry(it2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.Iterator
        public RealVector.Entry next() throws NoSuchElementException, ConcurrentModificationException {
            this.iter.advance();
            return this.current;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
