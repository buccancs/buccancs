package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

/* loaded from: classes2.dex */
public class DoubleLargeArray extends LargeArray {
    private static final long serialVersionUID = 7436383149749497101L;
    private double[] data;

    public DoubleLargeArray(long j) {
        this(j, true);
    }

    public DoubleLargeArray(long j, boolean z) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        if (j > getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.length * this.sizeof);
            if (z) {
                zeroNativeMemory(j);
            }
            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.length, this.sizeof));
            MemoryCounter.increaseCounter(this.length * this.sizeof);
            return;
        }
        this.data = new double[(int) j];
    }

    public DoubleLargeArray(long j, double d) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new double[]{d};
    }

    public DoubleLargeArray(double[] dArr) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        this.length = dArr.length;
        this.data = dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getData() {
        return this.data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public DoubleLargeArray clone() {
        if (this.isConstant) {
            return new DoubleLargeArray(this.length, getDouble(0L));
        }
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0L, doubleLargeArray, 0L, this.length);
        return doubleLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        return super.equals(obj) && this.data == ((DoubleLargeArray) obj).data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        double[] dArr = this.data;
        return iHashCode + (dArr != null ? dArr.hashCode() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Double get(long j) {
        return Double.valueOf(getDouble(j));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Double getFromNative(long j) {
        return Double.valueOf(LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean getBoolean(long j) {
        return this.ptr != 0 ? LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)) != 0.0d : this.isConstant ? this.data[0] != 0.0d : this.data[(int) j] != 0.0d;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte getByte(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else {
            if (this.isConstant) {
                i = (int) this.data[0];
                return (byte) i;
            }
            d = this.data[(int) j];
        }
        i = (int) d;
        return (byte) i;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getUnsignedByte(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else {
            if (this.isConstant) {
                i = (int) this.data[0];
                return (short) (i & 255);
            }
            d = this.data[(int) j];
        }
        i = (int) d;
        return (short) (i & 255);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getShort(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else {
            if (this.isConstant) {
                i = (int) this.data[0];
                return (short) i;
            }
            d = this.data[(int) j];
        }
        i = (int) d;
        return (short) i;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int getInt(long j) {
        if (this.ptr != 0) {
            return (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (int) this.data[0];
        }
        return (int) this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long getLong(long j) {
        if (this.ptr != 0) {
            return (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (long) this.data[0];
        }
        return (long) this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float getFloat(long j) {
        if (this.ptr != 0) {
            return (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (float) this.data[0];
        }
        return (float) this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double getDouble(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData() {
        if (this.length > 1073741824) {
            return null;
        }
        boolean[] zArr = new boolean[(int) this.length];
        if (this.ptr != 0) {
            int i = 0;
            while (true) {
                long j = i;
                if (j >= this.length) {
                    break;
                }
                zArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)) != 0.0d;
                i++;
            }
        } else if (this.isConstant) {
            boolean z = this.data[0] != 0.0d;
            for (int i2 = 0; i2 < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; i3 < this.length; i3++) {
                zArr[i3] = this.data[i3] != 0.0d;
            }
        }
        return zArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        boolean[] zArr2 = zArr;
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (zArr2 == null || zArr2.length < jCeil) {
            zArr2 = new boolean[(int) jCeil];
        }
        if (this.ptr != 0) {
            long j4 = j;
            int i = 0;
            while (j4 < j2) {
                int i2 = i + 1;
                zArr2[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j4)) != 0.0d;
                j4 += j3;
                i = i2;
            }
        } else if (this.isConstant) {
            long j5 = j;
            int i3 = 0;
            while (j5 < j2) {
                int i4 = i3 + 1;
                zArr2[i3] = this.data[0] != 0.0d;
                j5 += j3;
                i3 = i4;
            }
        } else {
            long j6 = j;
            int i5 = 0;
            while (j6 < j2) {
                int i6 = i5 + 1;
                zArr2[i5] = this.data[(int) j6] != 0.0d;
                j6 += j3;
                i5 = i6;
            }
        }
        return zArr2;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData() {
        if (this.length > 1073741824) {
            return null;
        }
        byte[] bArr = new byte[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                if (i >= this.length) {
                    break;
                }
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * r1));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                bArr[i] = (byte) d;
                i++;
            }
        } else {
            while (i < this.length) {
                bArr[i] = (byte) this.data[i];
                i++;
            }
        }
        return bArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (bArr == null || bArr.length < jCeil) {
            bArr = new byte[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                bArr[i2] = (byte) this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                bArr[i] = (byte) this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return bArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData() {
        if (this.length > 1073741824) {
            return null;
        }
        short[] sArr = new short[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                if (i >= this.length) {
                    break;
                }
                sArr[i] = (short) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * r1));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                sArr[i] = (short) d;
                i++;
            }
        } else {
            while (i < this.length) {
                sArr[i] = (short) this.data[i];
                i++;
            }
        }
        return sArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (sArr == null || sArr.length < jCeil) {
            sArr = new short[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                sArr[i] = (short) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                sArr[i2] = (short) this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                sArr[i] = (short) this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return sArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData() {
        if (this.length > 1073741824) {
            return null;
        }
        int[] iArr = new int[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = i;
                if (j >= this.length) {
                    break;
                }
                iArr[i] = (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                iArr[i] = (int) d;
                i++;
            }
        } else {
            while (i < this.length) {
                iArr[i] = (int) this.data[i];
                i++;
            }
        }
        return iArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (iArr == null || iArr.length < jCeil) {
            iArr = new int[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                iArr[i] = (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                iArr[i2] = (int) this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                iArr[i] = (int) this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return iArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData() {
        if (this.length > 1073741824) {
            return null;
        }
        long[] jArr = new long[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = i;
                if (j >= this.length) {
                    break;
                }
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                jArr[i] = (long) d;
                i++;
            }
        } else {
            while (i < this.length) {
                jArr[i] = (long) this.data[i];
                i++;
            }
        }
        return jArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (jArr == null || jArr.length < jCeil) {
            jArr = new long[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                jArr[i2] = (long) this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                jArr[i] = (long) this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return jArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData() {
        if (this.length > 1073741824) {
            return null;
        }
        float[] fArr = new float[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = i;
                if (j >= this.length) {
                    break;
                }
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                fArr[i] = (float) d;
                i++;
            }
        } else {
            while (i < this.length) {
                fArr[i] = (float) this.data[i];
                i++;
            }
        }
        return fArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (fArr == null || fArr.length < jCeil) {
            fArr = new float[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                fArr[i2] = (float) this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                fArr[i] = (float) this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return fArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData() {
        if (this.length > 1073741824) {
            return null;
        }
        double[] dArr = new double[(int) this.length];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = i;
                if (j >= this.length) {
                    break;
                }
                dArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                dArr[i] = d;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, dArr, 0, (int) this.length);
        }
        return dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        }
        if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        }
        if (j3 < 1) {
            throw new IllegalArgumentException("step < 1");
        }
        long jCeil = (long) FastMath.ceil((j2 - j) / j3);
        if (jCeil > 1073741824) {
            return null;
        }
        if (dArr == null || dArr.length < jCeil) {
            dArr = new double[(int) jCeil];
        }
        int i = 0;
        if (this.ptr != 0) {
            while (j < j2) {
                dArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                dArr[i2] = this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                dArr[i] = this.data[(int) j];
                j += j3;
                i++;
            }
        }
        return dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setToNative(long j, Object obj) {
        LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), ((Double) obj).doubleValue());
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setBoolean(long j, boolean z) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), !z ? 0.0d : 1.0d);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = !z ? 0.0d : 1.0d;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setByte(long j, byte b) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), b);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = b;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), s);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = s;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), i);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = i;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), j2);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = j2;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), f);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = f;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), d);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = d;
        }
    }
}
