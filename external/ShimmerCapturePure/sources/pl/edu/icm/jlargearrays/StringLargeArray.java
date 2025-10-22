package pl.edu.icm.jlargearrays;

import java.io.UnsupportedEncodingException;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

/* loaded from: classes2.dex */
public class StringLargeArray extends LargeArray {
    private static final String CHARSET = "UTF-8";
    private static final int CHARSET_SIZE = 4;
    private static final long serialVersionUID = -4096759496772248522L;
    private byte[] byteArray;
    private String[] data;
    private int maxStringLength;
    private long size;
    private ShortLargeArray stringLengths;

    public StringLargeArray(long j) {
        this(j, 100);
    }

    public StringLargeArray(long j, int i) {
        this(j, i, true);
    }

    public StringLargeArray(long j, int i, boolean z) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value.");
        }
        if (i <= 0) {
            throw new IllegalArgumentException(i + " is not a positive int value.");
        }
        this.length = j;
        this.size = i * j * 4;
        this.maxStringLength = i;
        if (j > getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
            if (z) {
                zeroNativeMemory(this.size);
            }
            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
            MemoryCounter.increaseCounter(this.size * this.sizeof);
            this.stringLengths = new ShortLargeArray(j);
            this.byteArray = new byte[i * 4];
            return;
        }
        this.data = new String[(int) j];
    }

    public StringLargeArray(long j, String str) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new String[]{str};
    }

    public StringLargeArray(String[] strArr) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1L;
        this.length = strArr.length;
        this.data = strArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final String[] getData() {
        return this.data;
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public StringLargeArray clone() {
        if (this.isConstant) {
            return new StringLargeArray(this.length, get(0L));
        }
        StringLargeArray stringLargeArray = new StringLargeArray(this.length, FastMath.max(1, this.maxStringLength), false);
        LargeArrayUtils.arraycopy(this, 0L, stringLargeArray, 0L, this.length);
        return stringLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        ShortLargeArray shortLargeArray;
        if (super.equals(obj)) {
            StringLargeArray stringLargeArray = (StringLargeArray) obj;
            boolean z = this.maxStringLength == stringLargeArray.maxStringLength && this.data == stringLargeArray.data;
            ShortLargeArray shortLargeArray2 = this.stringLengths;
            if (shortLargeArray2 != null && (shortLargeArray = stringLargeArray.stringLengths) != null) {
                return z && shortLargeArray2.equals(shortLargeArray);
            }
            if (shortLargeArray2 == stringLargeArray.stringLengths) {
                return z;
            }
        }
        return false;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        String[] strArr = this.data;
        int iHashCode2 = (iHashCode + (strArr != null ? strArr.hashCode() : 0)) * 29;
        int i = this.maxStringLength;
        int i2 = (iHashCode2 + (i ^ (i >>> 16))) * 29;
        ShortLargeArray shortLargeArray = this.stringLengths;
        return i2 + (shortLargeArray != null ? shortLargeArray.hashCode() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final String get(long j) {
        if (this.ptr != 0) {
            short s = this.stringLengths.getShort(j);
            if (s < 0) {
                return null;
            }
            long j2 = this.sizeof * j * this.maxStringLength * 4;
            for (int i = 0; i < s; i++) {
                this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * i));
            }
            try {
                return new String(this.byteArray, 0, s, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final String getFromNative(long j) {
        short s = this.stringLengths.getShort(j);
        if (s < 0) {
            return null;
        }
        long j2 = this.sizeof * j * this.maxStringLength * 4;
        for (int i = 0; i < s; i++) {
            this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * i));
        }
        try {
            return new String(this.byteArray, 0, s, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean getBoolean(long j) {
        String str = get(j);
        return (str == null || str.length() == 0) ? false : true;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte getByte(long j) {
        String str = get(j);
        return (byte) (str != null ? str.length() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getUnsignedByte(long j) {
        String str = get(j);
        return (short) (str != null ? str.length() & 255 : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getShort(long j) {
        String str = get(j);
        return (short) (str != null ? str.length() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int getInt(long j) {
        String str = get(j);
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long getLong(long j) {
        return get(j) != null ? r1.length() : 0;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float getFloat(long j) {
        return get(j) != null ? r1.length() : 0;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double getDouble(long j) {
        return get(j) != null ? r1.length() : 0;
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
                zArr[i] = this.stringLengths.getShort(j) != 0;
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            boolean z = (str == null || str.length() == 0) ? false : true;
            for (int i2 = 0; i2 < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; i3 < this.length; i3++) {
                String str2 = this.data[i3];
                zArr[i3] = (str2 == null || str2.length() == 0) ? false : true;
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
        int i = 0;
        if (this.ptr != 0) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                zArr[i2] = this.stringLengths.getShort(j) > 0;
                j += j3;
                i2 = i3;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            boolean z = (str == null || str.length() == 0) ? false : true;
            while (j < j2) {
                zArr[i] = z;
                j += j3;
                i++;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                String str2 = this.data[(int) j];
                int i5 = i4 + 1;
                zArr[i4] = (str2 != null ? str2.length() : 0) != 0;
                j += j3;
                i4 = i5;
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
                bArr[i] = (byte) this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            byte length = (byte) (str != null ? str.length() : 0);
            while (i < this.length) {
                bArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; i2 < this.length; i2++) {
                String str2 = this.data[i2];
                bArr[i2] = (byte) (str2 != null ? str2.length() : 0);
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
                bArr[i] = (byte) this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                String str = this.data[0];
                bArr[i2] = (byte) (str != null ? str.length() : 0);
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                String str2 = this.data[(int) j];
                bArr[i4] = (byte) (str2 != null ? str2.length() : 0);
                j += j3;
                i4 = i5;
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
                sArr[i] = this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            short length = (short) (str != null ? str.length() : 0);
            while (i < this.length) {
                sArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; i2 < this.length; i2++) {
                String str2 = this.data[i2];
                sArr[i2] = (short) (str2 != null ? str2.length() : 0);
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
                sArr[i] = this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                String str = this.data[0];
                sArr[i2] = (short) (str != null ? str.length() : 0);
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                String str2 = this.data[(int) j];
                sArr[i4] = (short) (str2 != null ? str2.length() : 0);
                j += j3;
                i4 = i5;
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
                iArr[i] = this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (i < this.length) {
                iArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; i2 < this.length; i2++) {
                String str2 = this.data[i2];
                iArr[i2] = str2 != null ? str2.length() : 0;
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
                iArr[i] = this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                String str = this.data[0];
                iArr[i2] = str != null ? str.length() : 0;
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                String str2 = this.data[(int) j];
                iArr[i4] = str2 != null ? str2.length() : 0;
                j += j3;
                i4 = i5;
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
                jArr[i] = this.stringLengths.getShort(r1);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (i < this.length) {
                jArr[i] = length;
                i++;
            }
        } else {
            while (i < this.length) {
                jArr[i] = this.data[i] != null ? r1.length() : 0L;
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
                jArr[i] = this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                jArr[i2] = this.data[0] != null ? r2.length() : 0;
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                jArr[i4] = this.data[(int) j] != null ? r2.length() : 0;
                j += j3;
                i4 = i5;
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
                fArr[i] = this.stringLengths.getShort(r1);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (i < this.length) {
                fArr[i] = length;
                i++;
            }
        } else {
            while (i < this.length) {
                fArr[i] = this.data[i] != null ? r1.length() : 0.0f;
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
                fArr[i] = this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                fArr[i2] = this.data[0] != null ? r2.length() : 0.0f;
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                fArr[i4] = this.data[(int) j] != null ? r2.length() : 0;
                j += j3;
                i4 = i5;
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
                dArr[i] = this.stringLengths.getShort(r1);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (i < this.length) {
                dArr[i] = length;
                i++;
            }
        } else {
            while (i < this.length) {
                dArr[i] = this.data[i] != null ? r1.length() : 0.0d;
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
                dArr[i] = this.stringLengths.getShort(j);
                j += j3;
                i++;
            }
        } else if (this.isConstant) {
            int i2 = 0;
            while (j < j2) {
                int i3 = i2 + 1;
                dArr[i2] = this.data[0] != null ? r2.length() : 0.0d;
                j += j3;
                i2 = i3;
            }
        } else {
            int i4 = 0;
            while (j < j2) {
                int i5 = i4 + 1;
                dArr[i4] = this.data[(int) j] != null ? r2.length() : 0;
                j += j3;
                i4 = i5;
            }
        }
        return dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setToNative(long j, Object obj) throws UnsupportedEncodingException {
        if (obj == null) {
            this.stringLengths.setShort(j, (short) -1);
            return;
        }
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException(obj + " is not a string.");
        }
        String str = (String) obj;
        if (str.length() > this.maxStringLength) {
            throw new IllegalArgumentException("String  " + str + " is too long.");
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            int length = bytes.length;
            if (length > 32767) {
                throw new IllegalArgumentException("String  " + str + " is too long.");
            }
            this.stringLengths.setShort(j, (short) length);
            long j2 = this.sizeof * j * this.maxStringLength * 4;
            for (int i = 0; i < length; i++) {
                LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * i), bytes[i]);
            }
        } catch (UnsupportedEncodingException unused) {
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void set(long j, Object obj) {
        if (obj == null) {
            if (this.ptr != 0) {
                this.stringLengths.setShort(j, (short) -1);
                return;
            } else {
                if (this.isConstant) {
                    throw new IllegalAccessError("Constant arrays cannot be modified.");
                }
                this.data[(int) j] = null;
                return;
            }
        }
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException(obj + " is not a string.");
        }
        String str = (String) obj;
        if (this.ptr != 0) {
            if (str.length() > this.maxStringLength) {
                throw new IllegalArgumentException("String  " + str + " is too long.");
            }
            try {
                byte[] bytes = str.getBytes("UTF-8");
                int length = bytes.length;
                if (length > 32767) {
                    throw new IllegalArgumentException("String  " + str + " is too long.");
                }
                this.stringLengths.setShort(j, (short) length);
                long j2 = this.sizeof * j * this.maxStringLength * 4;
                for (int i = 0; i < length; i++) {
                    LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * i), bytes[i]);
                }
                return;
            } catch (UnsupportedEncodingException unused) {
                return;
            }
        }
        if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
        this.data[(int) j] = str;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void set_safe(long j, Object obj) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        set(j, obj);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setBoolean(long j, boolean z) {
        set(j, Boolean.toString(z));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setByte(long j, byte b) {
        set(j, Byte.toString(b));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setShort(long j, short s) {
        set(j, Short.toString(s));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setInt(long j, int i) {
        set(j, Integer.toString(i));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setLong(long j, long j2) {
        set(j, Long.toString(j2));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setFloat(long j, float f) {
        set(j, Float.toString(f));
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setDouble(long j, double d) {
        set(j, Double.toString(d));
    }
}
