package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes2.dex */
public class ComplexDoubleLargeArray extends LargeArray {
    private static final long serialVersionUID = 15533907580310407L;
    private DoubleLargeArray dataIm;
    private DoubleLargeArray dataRe;

    public ComplexDoubleLargeArray(long j) {
        this(j, true);
    }

    public ComplexDoubleLargeArray(long j, boolean z) {
        this.type = LargeArrayType.COMPLEX_DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.dataRe = new DoubleLargeArray(j, z);
        this.dataIm = new DoubleLargeArray(j, z);
    }

    public ComplexDoubleLargeArray(long j, double[] dArr) {
        this.type = LargeArrayType.COMPLEX_DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        if (dArr == null || dArr.length != 2) {
            throw new IllegalArgumentException("constantValue == null || constantValue.length != 2");
        }
        this.length = j;
        this.isConstant = true;
        this.dataRe = new DoubleLargeArray(j, dArr[0]);
        this.dataIm = new DoubleLargeArray(j, dArr[1]);
    }

    public ComplexDoubleLargeArray(double[] dArr) {
        this(new DoubleLargeArray(dArr));
    }

    public ComplexDoubleLargeArray(DoubleLargeArray doubleLargeArray) {
        if (doubleLargeArray.length() % 2 != 0) {
            throw new IllegalArgumentException("The length of the data array must be even.");
        }
        if (doubleLargeArray.length() <= 0) {
            throw new IllegalArgumentException(doubleLargeArray.length() + " is not a positive long value");
        }
        this.type = LargeArrayType.COMPLEX_DOUBLE;
        this.sizeof = 8L;
        this.length = doubleLargeArray.length / 2;
        this.isConstant = doubleLargeArray.isConstant;
        if (this.isConstant) {
            this.dataRe = new DoubleLargeArray(this.length, doubleLargeArray.getDouble(0L));
            this.dataIm = new DoubleLargeArray(this.length, doubleLargeArray.getDouble(1L));
            return;
        }
        this.dataRe = new DoubleLargeArray(this.length, false);
        this.dataIm = new DoubleLargeArray(this.length, false);
        for (long j = 0; j < this.length; j++) {
            long j2 = j * 2;
            this.dataRe.setDouble(j, doubleLargeArray.getDouble(j2));
            this.dataIm.setDouble(j, doubleLargeArray.getDouble(j2 + 1));
        }
    }

    public ComplexDoubleLargeArray(double[] dArr, double[] dArr2) {
        this(new DoubleLargeArray(dArr), new DoubleLargeArray(dArr2));
    }

    public ComplexDoubleLargeArray(DoubleLargeArray doubleLargeArray, DoubleLargeArray doubleLargeArray2) {
        if (doubleLargeArray.length() != doubleLargeArray2.length()) {
            throw new IllegalArgumentException("The length of the dataRe must be equal to the length of dataIm.");
        }
        if (doubleLargeArray.length() <= 0) {
            throw new IllegalArgumentException(doubleLargeArray.length() + " is not a positive long value");
        }
        if (doubleLargeArray.isLarge() != doubleLargeArray2.isLarge()) {
            throw new IllegalArgumentException("dataRe.isLarge() != dataIm.isLarge()");
        }
        this.type = LargeArrayType.COMPLEX_DOUBLE;
        this.sizeof = 8L;
        this.length = doubleLargeArray.length();
        this.dataRe = doubleLargeArray;
        this.dataIm = doubleLargeArray2;
    }

    public final DoubleLargeArray getImaginaryArray() {
        return this.dataIm;
    }

    public final DoubleLargeArray getRealArray() {
        return this.dataRe;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public ComplexDoubleLargeArray clone() {
        if (this.isConstant) {
            return new ComplexDoubleLargeArray(this.length, new double[]{this.dataRe.getDouble(0L), this.dataIm.getDouble(0L)});
        }
        ComplexDoubleLargeArray complexDoubleLargeArray = new ComplexDoubleLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0L, complexDoubleLargeArray, 0L, this.length);
        return complexDoubleLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) obj;
        return this.dataRe.equals(complexDoubleLargeArray.dataRe) && this.dataIm.equals(complexDoubleLargeArray.dataIm);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        DoubleLargeArray doubleLargeArray = this.dataRe;
        int iHashCode2 = (iHashCode + (doubleLargeArray != null ? doubleLargeArray.hashCode() : 0)) * 29;
        DoubleLargeArray doubleLargeArray2 = this.dataIm;
        return iHashCode2 + (doubleLargeArray2 != null ? doubleLargeArray2.hashCode() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean isLarge() {
        return this.dataRe.isLarge();
    }

    public final DoubleLargeArray getAbsArray() {
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.length, false);
        for (long j = 0; j < this.length; j++) {
            double d = this.dataRe.getDouble(j);
            double d2 = this.dataIm.getDouble(j);
            doubleLargeArray.setDouble(j, FastMath.sqrt((d * d) + (d2 * d2)));
        }
        return doubleLargeArray;
    }

    public final DoubleLargeArray getArgArray() {
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.length, false);
        for (long j = 0; j < this.length; j++) {
            doubleLargeArray.setDouble(j, FastMath.atan2(this.dataIm.getDouble(j), this.dataRe.getDouble(j)));
        }
        return doubleLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] get(long j) {
        return getComplexDouble(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getFromNative(long j) {
        return new double[]{this.dataRe.getFromNative(j).doubleValue(), this.dataIm.getFromNative(j).doubleValue()};
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean getBoolean(long j) {
        return this.dataRe.getBoolean(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte getByte(long j) {
        return this.dataRe.getByte(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getUnsignedByte(long j) {
        return this.dataRe.getUnsignedByte(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getShort(long j) {
        return this.dataRe.getShort(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int getInt(long j) {
        return this.dataRe.getInt(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long getLong(long j) {
        return this.dataRe.getLong(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float getFloat(long j) {
        return this.dataRe.getFloat(j);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double getDouble(long j) {
        return this.dataRe.getDouble(j);
    }

    public final float[] getComplexFloat(long j) {
        return new float[]{this.dataRe.getFloat(j), this.dataIm.getFloat(j)};
    }

    public final double[] getComplexDouble(long j) {
        return new double[]{this.dataRe.getDouble(j), this.dataIm.getDouble(j)};
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[][] getData() {
        if (!isLarge()) {
            return new double[][]{this.dataRe.getData(), this.dataIm.getData()};
        }
        return null;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData() {
        return this.dataRe.getBooleanData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        return this.dataRe.getBooleanData(zArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData() {
        return this.dataRe.getByteData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        return this.dataRe.getByteData(bArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData() {
        return this.dataRe.getShortData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        return this.dataRe.getShortData(sArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData() {
        return this.dataRe.getIntData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        return this.dataRe.getIntData(iArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData() {
        return this.dataRe.getLongData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        return this.dataRe.getLongData(jArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData() {
        return this.dataRe.getFloatData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        return this.dataRe.getFloatData(fArr, j, j2, j3);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData() {
        return this.dataRe.getDoubleData();
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        return this.dataRe.getDoubleData(dArr, j, j2, j3);
    }

    public final double[] getComplexData() {
        if (this.length * 2 > 1073741824) {
            return null;
        }
        double[] dArr = new double[(int) (this.length * 2)];
        int i = 0;
        while (true) {
            long j = i;
            if (j >= this.length) {
                return dArr;
            }
            int i2 = i * 2;
            dArr[i2] = this.dataRe.getDouble(j);
            dArr[i2 + 1] = this.dataIm.getDouble(j);
            i++;
        }
    }

    public final double[] getComplexData(double[] dArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = ((long) FastMath.ceil((j2 - j) / j3)) * 2;
        if (jCeil > 1073741824) {
            return null;
        }
        if (dArr == null || dArr.length < jCeil) {
            dArr = new double[(int) jCeil];
        }
        int i = 0;
        while (j < j2) {
            int i2 = i + 1;
            dArr[i] = this.dataRe.getDouble(j);
            i += 2;
            dArr[i2] = this.dataIm.getDouble(j);
            j += j3;
        }
        return dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setToNative(long j, Object obj) {
        if (!(obj instanceof double[])) {
            throw new IllegalArgumentException(obj + " is not an array of doubles.");
        }
        double[] dArr = (double[]) obj;
        this.dataRe.setToNative(j, Double.valueOf(dArr[0]));
        this.dataIm.setToNative(j, Double.valueOf(dArr[1]));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setBoolean(long j, boolean z) {
        this.dataRe.setBoolean(j, z);
        this.dataIm.setBoolean(j, false);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setByte(long j, byte b) {
        this.dataRe.setByte(j, b);
        this.dataIm.setByte(j, (byte) 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setUnsignedByte(long j, short s) {
        this.dataRe.setUnsignedByte(j, s);
        this.dataIm.setUnsignedByte(j, (short) 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setShort(long j, short s) {
        this.dataRe.setShort(j, s);
        this.dataIm.setShort(j, (short) 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setInt(long j, int i) {
        this.dataRe.setInt(j, i);
        this.dataIm.setInt(j, 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setLong(long j, long j2) {
        this.dataRe.setLong(j, j2);
        this.dataIm.setLong(j, 0L);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setFloat(long j, float f) {
        this.dataRe.setFloat(j, f);
        this.dataIm.setFloat(j, 0.0f);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setDouble(long j, double d) {
        this.dataRe.setDouble(j, d);
        this.dataIm.setDouble(j, 0.0d);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void set(long j, Object obj) {
        if (!(obj instanceof double[])) {
            throw new IllegalArgumentException(obj + " is not an array of doubles.");
        }
        setComplexDouble(j, (double[]) obj);
    }

    public final void setComplexFloat(long j, float[] fArr) {
        this.dataRe.setFloat(j, fArr[0]);
        this.dataIm.setFloat(j, fArr[1]);
    }

    public final void setComplexDouble(long j, double[] dArr) {
        this.dataRe.setDouble(j, dArr[0]);
        this.dataIm.setDouble(j, dArr[1]);
    }
}
