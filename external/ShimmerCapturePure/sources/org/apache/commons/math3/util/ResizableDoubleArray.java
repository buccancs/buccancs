package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class ResizableDoubleArray implements DoubleArray, Serializable {

    @Deprecated
    public static final int ADDITIVE_MODE = 1;
    @Deprecated
    public static final int MULTIPLICATIVE_MODE = 0;
    private static final double DEFAULT_CONTRACTION_DELTA = 0.5d;
    private static final double DEFAULT_EXPANSION_FACTOR = 2.0d;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long serialVersionUID = -3485529955529426875L;
    private double contractionCriterion;
    private double expansionFactor;
    private ExpansionMode expansionMode;
    private double[] internalArray;
    private int numElements;
    private int startIndex;

    public ResizableDoubleArray() {
        this(16);
    }

    public ResizableDoubleArray(int i) throws MathIllegalArgumentException {
        this(i, DEFAULT_EXPANSION_FACTOR);
    }

    public ResizableDoubleArray(double[] dArr) {
        this(16, DEFAULT_EXPANSION_FACTOR, 2.5d, ExpansionMode.MULTIPLICATIVE, dArr);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f) throws MathIllegalArgumentException {
        this(i, f);
    }

    public ResizableDoubleArray(int i, double d) throws MathIllegalArgumentException {
        this(i, d, d + 0.5d);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f, float f2) throws MathIllegalArgumentException {
        this(i, f, f2);
    }

    public ResizableDoubleArray(int i, double d, double d2) throws MathIllegalArgumentException {
        this(i, d, d2, ExpansionMode.MULTIPLICATIVE, null);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f, float f2, int i2) throws MathIllegalArgumentException {
        this(i, f, f2, i2 == 1 ? ExpansionMode.ADDITIVE : ExpansionMode.MULTIPLICATIVE, null);
        setExpansionMode(i2);
    }

    public ResizableDoubleArray(int i, double d, double d2, ExpansionMode expansionMode, double... dArr) throws MathIllegalArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, Integer.valueOf(i));
        }
        checkContractExpand(d2, d);
        this.expansionFactor = d;
        this.contractionCriterion = d2;
        this.expansionMode = expansionMode;
        this.internalArray = new double[i];
        this.numElements = 0;
        this.startIndex = 0;
        if (dArr == null || dArr.length <= 0) {
            return;
        }
        addElements(dArr);
    }

    public ResizableDoubleArray(ResizableDoubleArray resizableDoubleArray) throws NullArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        MathUtils.checkNotNull(resizableDoubleArray);
        copy(resizableDoubleArray, this);
    }

    public static void copy(ResizableDoubleArray resizableDoubleArray, ResizableDoubleArray resizableDoubleArray2) throws NullArgumentException {
        MathUtils.checkNotNull(resizableDoubleArray);
        MathUtils.checkNotNull(resizableDoubleArray2);
        synchronized (resizableDoubleArray) {
            synchronized (resizableDoubleArray2) {
                resizableDoubleArray2.contractionCriterion = resizableDoubleArray.contractionCriterion;
                resizableDoubleArray2.expansionFactor = resizableDoubleArray.expansionFactor;
                resizableDoubleArray2.expansionMode = resizableDoubleArray.expansionMode;
                double[] dArr = new double[resizableDoubleArray.internalArray.length];
                resizableDoubleArray2.internalArray = dArr;
                System.arraycopy(resizableDoubleArray.internalArray, 0, dArr, 0, dArr.length);
                resizableDoubleArray2.numElements = resizableDoubleArray.numElements;
                resizableDoubleArray2.startIndex = resizableDoubleArray.startIndex;
            }
        }
    }

    protected double[] getArrayRef() {
        return this.internalArray;
    }

    public double getContractionCriterion() {
        return this.contractionCriterion;
    }

    @Deprecated
    public float getExpansionFactor() {
        return (float) this.expansionFactor;
    }

    @Deprecated
    public void setExpansionFactor(float f) throws MathIllegalArgumentException {
        double d = f;
        checkContractExpand(getContractionCriterion(), d);
        synchronized (this) {
            this.expansionFactor = d;
        }
    }

    protected int getStartIndex() {
        return this.startIndex;
    }

    @Deprecated
    protected void setInitialCapacity(int i) throws MathIllegalArgumentException {
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized void addElement(double d) {
        if (this.internalArray.length <= this.startIndex + this.numElements) {
            expand();
        }
        double[] dArr = this.internalArray;
        int i = this.startIndex;
        int i2 = this.numElements;
        this.numElements = i2 + 1;
        dArr[i + i2] = d;
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized void addElements(double[] dArr) {
        int i = this.numElements;
        double[] dArr2 = new double[dArr.length + i + 1];
        System.arraycopy(this.internalArray, this.startIndex, dArr2, 0, i);
        System.arraycopy(dArr, 0, dArr2, this.numElements, dArr.length);
        this.internalArray = dArr2;
        this.startIndex = 0;
        this.numElements += dArr.length;
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized double addElementRolling(double d) {
        double d2;
        double[] dArr = this.internalArray;
        int i = this.startIndex;
        d2 = dArr[i];
        if (i + this.numElements + 1 > dArr.length) {
            expand();
        }
        int i2 = this.startIndex + 1;
        this.startIndex = i2;
        this.internalArray[i2 + (this.numElements - 1)] = d;
        if (shouldContract()) {
            contract();
        }
        return d2;
    }

    public synchronized double substituteMostRecentElement(double d) throws MathIllegalStateException {
        double d2;
        int i = this.numElements;
        if (i < 1) {
            throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY, new Object[0]);
        }
        int i2 = this.startIndex + (i - 1);
        double[] dArr = this.internalArray;
        d2 = dArr[i2];
        dArr[i2] = d;
        return d2;
    }

    @Deprecated
    protected void checkContractExpand(float f, float f2) throws MathIllegalArgumentException {
        checkContractExpand(f, f2);
    }

    protected void checkContractExpand(double d, double d2) throws NumberIsTooSmallException {
        if (d < d2) {
            NumberIsTooSmallException numberIsTooSmallException = new NumberIsTooSmallException(Double.valueOf(d), 1, true);
            numberIsTooSmallException.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, Double.valueOf(d), Double.valueOf(d2));
            throw numberIsTooSmallException;
        }
        if (d <= 1.0d) {
            NumberIsTooSmallException numberIsTooSmallException2 = new NumberIsTooSmallException(Double.valueOf(d), 1, false);
            numberIsTooSmallException2.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, Double.valueOf(d));
            throw numberIsTooSmallException2;
        }
        if (d2 > 1.0d) {
            return;
        }
        NumberIsTooSmallException numberIsTooSmallException3 = new NumberIsTooSmallException(Double.valueOf(d), 1, false);
        numberIsTooSmallException3.getContext().addMessage(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, Double.valueOf(d2));
        throw numberIsTooSmallException3;
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized void clear() {
        this.numElements = 0;
        this.startIndex = 0;
    }

    public synchronized void contract() {
        int i = this.numElements;
        double[] dArr = new double[i + 1];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, i);
        this.internalArray = dArr;
        this.startIndex = 0;
    }

    public synchronized void discardFrontElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, true);
    }

    public synchronized void discardMostRecentElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, false);
    }

    private synchronized void discardExtremeElements(int i, boolean z) throws MathIllegalArgumentException {
        int i2 = this.numElements;
        if (i > i2) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, Integer.valueOf(i), Integer.valueOf(this.numElements));
        }
        if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, Integer.valueOf(i));
        }
        this.numElements = i2 - i;
        if (z) {
            this.startIndex += i;
        }
        if (shouldContract()) {
            contract();
        }
    }

    protected synchronized void expand() {
        int length;
        if (this.expansionMode == ExpansionMode.MULTIPLICATIVE) {
            length = (int) FastMath.ceil(this.internalArray.length * this.expansionFactor);
        } else {
            length = (int) (this.internalArray.length + FastMath.round(this.expansionFactor));
        }
        double[] dArr = new double[length];
        double[] dArr2 = this.internalArray;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        this.internalArray = dArr;
    }

    private synchronized void expandTo(int i) {
        double[] dArr = new double[i];
        double[] dArr2 = this.internalArray;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        this.internalArray = dArr;
    }

    @Deprecated
    public float getContractionCriteria() {
        return (float) getContractionCriterion();
    }

    @Deprecated
    public void setContractionCriteria(float f) throws MathIllegalArgumentException {
        checkContractExpand(f, getExpansionFactor());
        synchronized (this) {
            this.contractionCriterion = f;
        }
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized double getElement(int i) {
        if (i >= this.numElements) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (i >= 0) {
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return this.internalArray[this.startIndex + i];
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized double[] getElements() {
        double[] dArr;
        int i = this.numElements;
        dArr = new double[i];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, i);
        return dArr;
    }

    @Deprecated
    public int getExpansionMode() {
        synchronized (this) {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode[this.expansionMode.ordinal()];
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            throw new MathInternalError();
        }
    }

    @Deprecated
    public void setExpansionMode(int i) throws MathIllegalArgumentException {
        if (i != 0 && i != 1) {
            throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, Integer.valueOf(i), 0, "MULTIPLICATIVE_MODE", 1, "ADDITIVE_MODE");
        }
        synchronized (this) {
            try {
                if (i == 0) {
                    setExpansionMode(ExpansionMode.MULTIPLICATIVE);
                } else if (i == 1) {
                    setExpansionMode(ExpansionMode.ADDITIVE);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Deprecated
    public void setExpansionMode(ExpansionMode expansionMode) {
        synchronized (this) {
            this.expansionMode = expansionMode;
        }
    }

    @Deprecated
    synchronized int getInternalLength() {
        return this.internalArray.length;
    }

    public int getCapacity() {
        return this.internalArray.length;
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized int getNumElements() {
        return this.numElements;
    }

    public synchronized void setNumElements(int i) throws MathIllegalArgumentException {
        if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, Integer.valueOf(i));
        }
        int i2 = this.startIndex + i;
        if (i2 > this.internalArray.length) {
            expandTo(i2);
        }
        this.numElements = i;
    }

    @Deprecated
    public synchronized double[] getInternalValues() {
        return this.internalArray;
    }

    public double compute(MathArrays.Function function) {
        double[] dArr;
        int i;
        int i2;
        synchronized (this) {
            dArr = this.internalArray;
            i = this.startIndex;
            i2 = this.numElements;
        }
        return function.evaluate(dArr, i, i2);
    }

    @Override // org.apache.commons.math3.util.DoubleArray
    public synchronized void setElement(int i, double d) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        int i2 = i + 1;
        if (i2 > this.numElements) {
            this.numElements = i2;
        }
        int i3 = this.startIndex;
        if (i3 + i >= this.internalArray.length) {
            expandTo(i3 + i2);
        }
        this.internalArray[this.startIndex + i] = d;
    }

    private synchronized boolean shouldContract() {
        if (this.expansionMode == ExpansionMode.MULTIPLICATIVE) {
            return ((double) (((float) this.internalArray.length) / ((float) this.numElements))) > this.contractionCriterion;
        }
        return ((double) (this.internalArray.length - this.numElements)) > this.contractionCriterion;
    }

    @Deprecated
    public synchronized int start() {
        return this.startIndex;
    }

    public synchronized ResizableDoubleArray copy() {
        ResizableDoubleArray resizableDoubleArray;
        resizableDoubleArray = new ResizableDoubleArray();
        copy(this, resizableDoubleArray);
        return resizableDoubleArray;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResizableDoubleArray)) {
            return false;
        }
        synchronized (this) {
            synchronized (obj) {
                ResizableDoubleArray resizableDoubleArray = (ResizableDoubleArray) obj;
                if (resizableDoubleArray.contractionCriterion != this.contractionCriterion || resizableDoubleArray.expansionFactor != this.expansionFactor || resizableDoubleArray.expansionMode != this.expansionMode || resizableDoubleArray.numElements != this.numElements || resizableDoubleArray.startIndex != this.startIndex) {
                    return false;
                }
                return Arrays.equals(this.internalArray, resizableDoubleArray.internalArray);
            }
        }
    }

    public synchronized int hashCode() {
        return Arrays.hashCode(new int[]{Double.valueOf(this.expansionFactor).hashCode(), Double.valueOf(this.contractionCriterion).hashCode(), this.expansionMode.hashCode(), Arrays.hashCode(this.internalArray), this.numElements, this.startIndex});
    }

    public enum ExpansionMode {
        MULTIPLICATIVE,
        ADDITIVE
    }

    /* renamed from: org.apache.commons.math3.util.ResizableDoubleArray$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode;

        static {
            int[] iArr = new int[ExpansionMode.values().length];
            $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode = iArr;
            try {
                iArr[ExpansionMode.MULTIPLICATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode[ExpansionMode.ADDITIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
