package pl.edu.icm.jlargearrays;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

/* loaded from: classes2.dex */
public class ShortLargeArray extends LargeArray {
    private static final long serialVersionUID = 8813991144303908703L;
    private short[] data;

    public ShortLargeArray(long j) {
        this(j, true);
    }

    public ShortLargeArray(long j, boolean z) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2L;
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
        this.data = new short[(int) j];
    }

    public ShortLargeArray(long j, short s) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new short[]{s};
    }

    public ShortLargeArray(short[] sArr) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2L;
        this.length = sArr.length;
        this.data = sArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getData() {
        return this.data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public ShortLargeArray clone() {
        if (this.isConstant) {
            return new ShortLargeArray(this.length, getShort(0L));
        }
        ShortLargeArray shortLargeArray = new ShortLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0L, shortLargeArray, 0L, this.length);
        return shortLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        return super.equals(obj) && this.data == ((ShortLargeArray) obj).data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        short[] sArr = this.data;
        return iHashCode + (sArr != null ? sArr.hashCode() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Short get(long j) {
        return Short.valueOf(getShort(j));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Short getFromNative(long j) {
        return Short.valueOf(LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean getBoolean(long j) {
        return this.ptr != 0 ? LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0 : this.isConstant ? this.data[0] != 0 : this.data[(int) j] != 0;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte getByte(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (byte) s;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getUnsignedByte(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (short) (s & Http2CodecUtil.MAX_UNSIGNED_BYTE);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getShort(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int getInt(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long getLong(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return s;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float getFloat(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return s;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double getDouble(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return s;
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
                zArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0;
                i++;
            }
        } else if (this.isConstant) {
            boolean z = this.data[0] != 0;
            for (int i2 = 0; i2 < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; i3 < this.length; i3++) {
                zArr[i3] = this.data[i3] != 0;
            }
        }
        return zArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
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
        if (zArr == null || zArr.length < jCeil) {
            zArr = new boolean[(int) jCeil];
        }
        if (this.ptr != 0) {
            int i = 0;
            while (j < j2) {
                int i2 = i + 1;
                zArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0;
                j += j3;
                i = i2;
            }
        } else if (this.isConstant) {
            int i3 = 0;
            while (j < j2) {
                int i4 = i3 + 1;
                zArr[i3] = this.data[0] != 0;
                j += j3;
                i3 = i4;
            }
        } else {
            int i5 = 0;
            while (j < j2) {
                int i6 = i5 + 1;
                zArr[i5] = this.data[(int) j] != 0;
                j += j3;
                i5 = i6;
            }
        }
        return zArr;
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
                long j = i;
                if (j >= this.length) {
                    break;
                }
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            byte b = (byte) this.data[0];
            while (i < this.length) {
                bArr[i] = b;
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
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                long j = i;
                if (j >= this.length) {
                    break;
                }
                sArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            short s = this.data[0];
            while (i < this.length) {
                sArr[i] = s;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, sArr, 0, (int) this.length);
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
                sArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                sArr[i2] = this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                sArr[i] = this.data[(int) j];
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
                iArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            short s = this.data[0];
            while (i < this.length) {
                iArr[i] = s;
                i++;
            }
        } else {
            while (i < this.length) {
                iArr[i] = this.data[i];
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
                iArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                iArr[i2] = this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                iArr[i] = this.data[(int) j];
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
                if (i >= this.length) {
                    break;
                }
                jArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * r1));
                i++;
            }
        } else if (this.isConstant) {
            long j = this.data[0];
            while (i < this.length) {
                jArr[i] = j;
                i++;
            }
        } else {
            while (i < this.length) {
                jArr[i] = this.data[i];
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
                jArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                jArr[i2] = this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                jArr[i] = this.data[(int) j];
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
                if (i >= this.length) {
                    break;
                }
                fArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * r1));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (i < this.length) {
                fArr[i] = f;
                i++;
            }
        } else {
            while (i < this.length) {
                fArr[i] = this.data[i];
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
                fArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                fArr[i2] = this.data[0];
                j += j3;
                i2++;
            }
        } else {
            while (j < j2) {
                fArr[i] = this.data[(int) j];
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
                if (i >= this.length) {
                    break;
                }
                dArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * r1));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (i < this.length) {
                dArr[i] = d;
                i++;
            }
        } else {
            while (i < this.length) {
                dArr[i] = this.data[i];
                i++;
            }
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
                dArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
        LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), ((Short) obj).shortValue());
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setBoolean(long j, boolean z) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), z ? (short) 1 : (short) 0);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = z ? (short) 1 : (short) 0;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setByte(long j, byte b) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), b);
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
    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), s);
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
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) i);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = (short) i;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) j2);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = (short) j2;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) f);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = (short) f;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) d);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = (short) d;
        }
    }
}
