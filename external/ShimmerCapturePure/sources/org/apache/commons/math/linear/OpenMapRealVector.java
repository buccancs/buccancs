package org.apache.commons.math.linear;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.OpenIntToDoubleHashMap;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/OpenMapRealVector.class */
public class OpenMapRealVector extends AbstractRealVector implements SparseRealVector, Serializable {
    public static final double DEFAULT_ZERO_TOLERANCE = 1.0E-12d;
    private static final long serialVersionUID = 8772222695580707260L;
    private final OpenIntToDoubleHashMap entries;
    private final int virtualSize;
    private final double epsilon;

    public OpenMapRealVector() {
        this(0, 1.0E-12d);
    }

    public OpenMapRealVector(int dimension) {
        this(dimension, 1.0E-12d);
    }

    public OpenMapRealVector(int dimension, double epsilon) {
        this.virtualSize = dimension;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = epsilon;
    }

    protected OpenMapRealVector(OpenMapRealVector v, int resize) {
        this.virtualSize = v.getDimension() + resize;
        this.entries = new OpenIntToDoubleHashMap(v.entries);
        this.epsilon = v.epsilon;
    }

    public OpenMapRealVector(int dimension, int expectedSize) {
        this(dimension, expectedSize, 1.0E-12d);
    }

    public OpenMapRealVector(int dimension, int expectedSize, double epsilon) {
        this.virtualSize = dimension;
        this.entries = new OpenIntToDoubleHashMap(expectedSize, 0.0d);
        this.epsilon = epsilon;
    }

    public OpenMapRealVector(double[] values) {
        this(values, 1.0E-12d);
    }

    public OpenMapRealVector(double[] values, double epsilon) {
        this.virtualSize = values.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = epsilon;
        for (int key = 0; key < values.length; key++) {
            double value = values[key];
            if (!isDefaultValue(value)) {
                this.entries.put(key, value);
            }
        }
    }

    public OpenMapRealVector(Double[] values) {
        this(values, 1.0E-12d);
    }

    public OpenMapRealVector(Double[] values, double epsilon) {
        this.virtualSize = values.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = epsilon;
        for (int key = 0; key < values.length; key++) {
            double value = values[key].doubleValue();
            if (!isDefaultValue(value)) {
                this.entries.put(key, value);
            }
        }
    }

    public OpenMapRealVector(OpenMapRealVector v) {
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToDoubleHashMap(v.getEntries());
        this.epsilon = v.epsilon;
    }

    public OpenMapRealVector(RealVector v) {
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = 1.0E-12d;
        for (int key = 0; key < this.virtualSize; key++) {
            double value = v.getEntry(key);
            if (!isDefaultValue(value)) {
                this.entries.put(key, value);
            }
        }
    }

    private OpenIntToDoubleHashMap getEntries() {
        return this.entries;
    }

    protected boolean isDefaultValue(double value) {
        return FastMath.abs(value) < this.epsilon;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public RealVector add(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return add((OpenMapRealVector) v);
        }
        return super.add(v);
    }

    public OpenMapRealVector add(OpenMapRealVector v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.getDimension());
        boolean copyThis = this.entries.size() > v.entries.size();
        OpenMapRealVector res = copyThis ? copy() : v.copy();
        OpenIntToDoubleHashMap.Iterator iter = copyThis ? v.entries.iterator() : this.entries.iterator();
        OpenIntToDoubleHashMap randomAccess = copyThis ? this.entries : v.entries;
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (randomAccess.containsKey(key)) {
                res.setEntry(key, randomAccess.get(key) + iter.value());
            } else {
                res.setEntry(key, iter.value());
            }
        }
        return res;
    }

    public OpenMapRealVector append(OpenMapRealVector v) throws MatrixIndexException, NoSuchElementException, ConcurrentModificationException {
        OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
        OpenIntToDoubleHashMap.Iterator iter = v.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key() + this.virtualSize, iter.value());
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector append(RealVector v) {
        if (v instanceof OpenMapRealVector) {
            return append((OpenMapRealVector) v);
        }
        return append(v.getData());
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector append(double d) throws MatrixIndexException {
        OpenMapRealVector res = new OpenMapRealVector(this, 1);
        res.setEntry(this.virtualSize, d);
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector append(double[] a) throws MatrixIndexException {
        OpenMapRealVector res = new OpenMapRealVector(this, a.length);
        for (int i = 0; i < a.length; i++) {
            res.setEntry(i + this.virtualSize, a[i]);
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector copy() {
        return new OpenMapRealVector(this);
    }

    public double dotProduct(OpenMapRealVector v) throws NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.getDimension());
        boolean thisIsSmaller = this.entries.size() < v.entries.size();
        OpenIntToDoubleHashMap.Iterator iter = thisIsSmaller ? this.entries.iterator() : v.entries.iterator();
        OpenIntToDoubleHashMap larger = thisIsSmaller ? v.entries : this.entries;
        double dValue = 0.0d;
        while (true) {
            double d = dValue;
            if (iter.hasNext()) {
                iter.advance();
                dValue = d + (iter.value() * larger.get(iter.key()));
            } else {
                return d;
            }
        }
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double dotProduct(RealVector v) throws IllegalArgumentException {
        if (v instanceof OpenMapRealVector) {
            return dotProduct((OpenMapRealVector) v);
        }
        return super.dotProduct(v);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector ebeDivide(RealVector v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), iter.value() / v.getEntry(iter.key()));
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector ebeDivide(double[] v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.length);
        OpenMapRealVector res = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), iter.value() / v[iter.key()]);
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector ebeMultiply(RealVector v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), iter.value() * v.getEntry(iter.key()));
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector ebeMultiply(double[] v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.length);
        OpenMapRealVector res = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), iter.value() * v[iter.key()]);
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public OpenMapRealVector getSubVector(int index, int n) throws MatrixIndexException, NoSuchElementException, ConcurrentModificationException {
        checkIndex(index);
        checkIndex((index + n) - 1);
        OpenMapRealVector res = new OpenMapRealVector(n);
        int end = index + n;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (key >= index && key < end) {
                res.setEntry(key - index, iter.value());
            }
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double[] getData() throws NoSuchElementException, ConcurrentModificationException {
        double[] res = new double[this.virtualSize];
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res[iter.key()] = iter.value();
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public int getDimension() {
        return this.virtualSize;
    }

    public double getDistance(OpenMapRealVector v) throws NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        double res;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        double d = 0.0d;
        while (true) {
            res = d;
            if (!iter.hasNext()) {
                break;
            }
            iter.advance();
            int key = iter.key();
            double delta = iter.value() - v.getEntry(key);
            d = res + (delta * delta);
        }
        OpenIntToDoubleHashMap.Iterator iter2 = v.getEntries().iterator();
        while (iter2.hasNext()) {
            iter2.advance();
            int key2 = iter2.key();
            if (!this.entries.containsKey(key2)) {
                double value = iter2.value();
                res += value * value;
            }
        }
        return FastMath.sqrt(res);
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getDistance(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getDistance((OpenMapRealVector) v);
        }
        return getDistance(v.getData());
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getDistance(double[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        double res = 0.0d;
        for (int i = 0; i < v.length; i++) {
            double delta = this.entries.get(i) - v[i];
            res += delta * delta;
        }
        return FastMath.sqrt(res);
    }

    @Override // org.apache.commons.math.linear.RealVector
    public double getEntry(int index) throws MatrixIndexException {
        checkIndex(index);
        return this.entries.get(index);
    }

    public double getL1Distance(OpenMapRealVector v) throws NoSuchElementException, ConcurrentModificationException {
        double max = 0.0d;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
            max += delta;
        }
        OpenIntToDoubleHashMap.Iterator iter2 = v.getEntries().iterator();
        while (iter2.hasNext()) {
            iter2.advance();
            int key = iter2.key();
            if (!this.entries.containsKey(key)) {
                double delta2 = FastMath.abs(iter2.value());
                max += FastMath.abs(delta2);
            }
        }
        return max;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getL1Distance(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getL1Distance((OpenMapRealVector) v);
        }
        return getL1Distance(v.getData());
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getL1Distance(double[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        double max = 0.0d;
        for (int i = 0; i < v.length; i++) {
            double delta = FastMath.abs(getEntry(i) - v[i]);
            max += delta;
        }
        return max;
    }

    private double getLInfDistance(OpenMapRealVector v) throws NoSuchElementException, ConcurrentModificationException {
        double max = 0.0d;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
            if (delta > max) {
                max = delta;
            }
        }
        OpenIntToDoubleHashMap.Iterator iter2 = v.getEntries().iterator();
        while (iter2.hasNext()) {
            iter2.advance();
            int key = iter2.key();
            if (!this.entries.containsKey(key) && iter2.value() > max) {
                max = iter2.value();
            }
        }
        return max;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getLInfDistance(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getLInfDistance((OpenMapRealVector) v);
        }
        return getLInfDistance(v.getData());
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double getLInfDistance(double[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        double max = 0.0d;
        for (int i = 0; i < v.length; i++) {
            double delta = FastMath.abs(getEntry(i) - v[i]);
            if (delta > max) {
                max = delta;
            }
        }
        return max;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public boolean isInfinite() throws NoSuchElementException, ConcurrentModificationException {
        boolean infiniteFound = false;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double value = iter.value();
            if (Double.isNaN(value)) {
                return false;
            }
            if (Double.isInfinite(value)) {
                infiniteFound = true;
            }
        }
        return infiniteFound;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public boolean isNaN() throws NoSuchElementException, ConcurrentModificationException {
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            if (Double.isNaN(iter.value())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector mapAddToSelf(double d) throws MatrixIndexException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, getEntry(i) + d);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public RealMatrix outerProduct(double[] v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.length);
        RealMatrix res = new OpenMapRealMatrix(this.virtualSize, this.virtualSize);
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int row = iter.key();
            double value = iter.value();
            for (int col = 0; col < this.virtualSize; col++) {
                res.setEntry(row, col, value * v[col]);
            }
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.RealVector
    public RealVector projection(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        return v.mapMultiply(dotProduct(v) / v.dotProduct(v));
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector projection(double[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        return (OpenMapRealVector) projection(new OpenMapRealVector(v));
    }

    @Override // org.apache.commons.math.linear.RealVector
    public void setEntry(int index, double value) throws MatrixIndexException {
        checkIndex(index);
        if (!isDefaultValue(value)) {
            this.entries.put(index, value);
        } else if (this.entries.containsKey(index)) {
            this.entries.remove(index);
        }
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public void setSubVector(int index, RealVector v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex((index + v.getDimension()) - 1);
        setSubVector(index, v.getData());
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public void setSubVector(int index, double[] v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex((index + v.length) - 1);
        for (int i = 0; i < v.length; i++) {
            setEntry(i + index, v[i]);
        }
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public void set(double value) throws MatrixIndexException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, value);
        }
    }

    public OpenMapRealVector subtract(OpenMapRealVector v) throws MatrixIndexException, NoSuchElementException, IllegalArgumentException, ConcurrentModificationException {
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = copy();
        OpenIntToDoubleHashMap.Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (this.entries.containsKey(key)) {
                res.setEntry(key, this.entries.get(key) - iter.value());
            } else {
                res.setEntry(key, -iter.value());
            }
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector subtract(RealVector v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return subtract((OpenMapRealVector) v);
        }
        return subtract(v.getData());
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector subtract(double[] v) throws MatrixIndexException, IllegalArgumentException {
        checkVectorDimensions(v.length);
        OpenMapRealVector res = new OpenMapRealVector(this);
        for (int i = 0; i < v.length; i++) {
            if (this.entries.containsKey(i)) {
                res.setEntry(i, this.entries.get(i) - v[i]);
            } else {
                res.setEntry(i, -v[i]);
            }
        }
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public OpenMapRealVector unitVector() throws NoSuchElementException, ConcurrentModificationException {
        OpenMapRealVector res = copy();
        res.unitize();
        return res;
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public void unitize() throws NoSuchElementException, ConcurrentModificationException {
        double norm = getNorm();
        if (isDefaultValue(norm)) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.entries.put(iter.key(), iter.value() / norm);
        }
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public double[] toArray() {
        return getData();
    }

    public int hashCode() throws NoSuchElementException, ConcurrentModificationException {
        long temp = Double.doubleToLongBits(this.epsilon);
        int result = (31 * 1) + ((int) (temp ^ (temp >>> 32)));
        int result2 = (31 * result) + this.virtualSize;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            long temp2 = Double.doubleToLongBits(iter.value());
            result2 = (31 * result2) + ((int) (temp2 ^ (temp2 >> 32)));
        }
        return result2;
    }

    public boolean equals(Object obj) throws MatrixIndexException, NoSuchElementException, ConcurrentModificationException {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenMapRealVector)) {
            return false;
        }
        OpenMapRealVector other = (OpenMapRealVector) obj;
        if (this.virtualSize != other.virtualSize || Double.doubleToLongBits(this.epsilon) != Double.doubleToLongBits(other.epsilon)) {
            return false;
        }
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double test = other.getEntry(iter.key());
            if (Double.doubleToLongBits(test) != Double.doubleToLongBits(iter.value())) {
                return false;
            }
        }
        OpenIntToDoubleHashMap.Iterator iter2 = other.getEntries().iterator();
        while (iter2.hasNext()) {
            iter2.advance();
            double test2 = iter2.value();
            if (Double.doubleToLongBits(test2) != Double.doubleToLongBits(getEntry(iter2.key()))) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public double getSparcity() {
        return getSparsity();
    }

    public double getSparsity() {
        return this.entries.size() / getDimension();
    }

    @Override // org.apache.commons.math.linear.AbstractRealVector, org.apache.commons.math.linear.RealVector
    public Iterator<RealVector.Entry> sparseIterator() {
        return new OpenMapSparseIterator();
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/OpenMapRealVector$OpenMapEntry.class */
    protected class OpenMapEntry extends RealVector.Entry {
        private final OpenIntToDoubleHashMap.Iterator iter;

        protected OpenMapEntry(OpenIntToDoubleHashMap.Iterator iter) {
            this.iter = iter;
        }

        @Override // org.apache.commons.math.linear.RealVector.Entry
        public double getValue() {
            return this.iter.value();
        }

        @Override // org.apache.commons.math.linear.RealVector.Entry
        public void setValue(double value) {
            OpenMapRealVector.this.entries.put(this.iter.key(), value);
        }

        @Override // org.apache.commons.math.linear.RealVector.Entry
        public int getIndex() {
            return this.iter.key();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/OpenMapRealVector$OpenMapSparseIterator.class */
    protected class OpenMapSparseIterator implements Iterator<RealVector.Entry> {
        private final OpenIntToDoubleHashMap.Iterator iter;
        private final RealVector.Entry current;

        protected OpenMapSparseIterator() {
            this.iter = OpenMapRealVector.this.entries.iterator();
            this.current = OpenMapRealVector.this.new OpenMapEntry(this.iter);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
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
