package pl.edu.icm.jlargearrays;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

/* loaded from: classes2.dex */
public class ObjectLargeArray extends LargeArray {
    private static final long serialVersionUID = -4096759496772248522L;
    private byte[] byteArray;
    private Object[] data;
    private int maxObjectLength;
    private ShortLargeArray objectLengths;
    private long size;

    public ObjectLargeArray(long j) {
        this(j, 1024);
    }

    public ObjectLargeArray(long j, int i) {
        this(j, i, true);
    }

    public ObjectLargeArray(long j, int i, boolean z) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value.");
        }
        if (i <= 0) {
            throw new IllegalArgumentException(i + " is not a positive int value.");
        }
        this.length = j;
        this.size = i * j;
        this.maxObjectLength = i;
        if (j > getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
            if (z) {
                zeroNativeMemory(this.size);
            }
            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
            MemoryCounter.increaseCounter(this.size * this.sizeof);
            this.objectLengths = new ShortLargeArray(j);
            this.byteArray = new byte[i];
            return;
        }
        this.data = new Object[(int) j];
    }

    public ObjectLargeArray(long j, Object obj) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new Object[]{obj};
    }

    public ObjectLargeArray(Object[] objArr) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1L;
        this.length = objArr.length;
        this.data = objArr;
    }

    private static byte[] toByteArray(Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(obj);
            try {
                objectOutputStream.close();
            } catch (IOException unused) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            throw new SerializationException(e);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object fromByteArray(byte[] r2) throws java.lang.Throwable {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r2)
            r2 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L19
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L19
            java.lang.Object r2 = r1.readObject()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L22
            r1.close()     // Catch: java.io.IOException -> L12
        L12:
            return r2
        L13:
            r2 = move-exception
            goto L1c
        L15:
            r0 = move-exception
            r1 = r2
            r2 = r0
            goto L23
        L19:
            r0 = move-exception
            r1 = r2
            r2 = r0
        L1c:
            com.sun.xml.internal.ws.encoding.soap.SerializationException r0 = new com.sun.xml.internal.ws.encoding.soap.SerializationException     // Catch: java.lang.Throwable -> L22
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L22
            throw r0     // Catch: java.lang.Throwable -> L22
        L22:
            r2 = move-exception
        L23:
            if (r1 == 0) goto L28
            r1.close()     // Catch: java.io.IOException -> L28
        L28:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.ObjectLargeArray.fromByteArray(byte[]):java.lang.Object");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Object[] getData() {
        return this.data;
    }

    public int getMaxObjectLength() {
        return this.maxObjectLength;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public ObjectLargeArray clone() {
        if (this.isConstant) {
            return new ObjectLargeArray(this.length, get(0L));
        }
        ObjectLargeArray objectLargeArray = new ObjectLargeArray(this.length, FastMath.max(1, this.maxObjectLength), false);
        LargeArrayUtils.arraycopy(this, 0L, objectLargeArray, 0L, this.length);
        return objectLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        ShortLargeArray shortLargeArray;
        if (super.equals(obj)) {
            ObjectLargeArray objectLargeArray = (ObjectLargeArray) obj;
            boolean z = this.maxObjectLength == objectLargeArray.maxObjectLength && this.data == objectLargeArray.data;
            ShortLargeArray shortLargeArray2 = this.objectLengths;
            if (shortLargeArray2 != null && (shortLargeArray = objectLargeArray.objectLengths) != null) {
                return z && shortLargeArray2.equals(shortLargeArray);
            }
            if (shortLargeArray2 == objectLargeArray.objectLengths) {
                return z;
            }
        }
        return false;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        Object[] objArr = this.data;
        int iHashCode2 = (iHashCode + (objArr != null ? objArr.hashCode() : 0)) * 29;
        int i = this.maxObjectLength;
        int i2 = (iHashCode2 + (i ^ (i >>> 16))) * 29;
        ShortLargeArray shortLargeArray = this.objectLengths;
        return i2 + (shortLargeArray != null ? shortLargeArray.hashCode() : 0);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Object get(long j) {
        if (this.ptr != 0) {
            short s = this.objectLengths.getShort(j);
            if (s < 0) {
                return null;
            }
            long j2 = this.sizeof * j * this.maxObjectLength;
            for (int i = 0; i < s; i++) {
                this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * i));
            }
            return fromByteArray(this.byteArray);
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final Object getFromNative(long j) {
        short s = this.objectLengths.getShort(j);
        if (s < 0) {
            return null;
        }
        long j2 = this.sizeof * j * this.maxObjectLength;
        for (int i = 0; i < s; i++) {
            this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * i));
        }
        return fromByteArray(this.byteArray);
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean getBoolean(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte getByte(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getUnsignedByte(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short getShort(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int getInt(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long getLong(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float getFloat(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double getDouble(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setToNative(long j, Object obj) throws Throwable {
        if (obj == null) {
            this.objectLengths.setShort(j, (short) -1);
            return;
        }
        byte[] byteArray = toByteArray(obj);
        if (byteArray.length > this.maxObjectLength) {
            throw new IllegalArgumentException("Object  " + obj + " is too long.");
        }
        int length = byteArray.length;
        if (length > 32767) {
            throw new IllegalArgumentException("Object  " + obj + " is too long.");
        }
        this.objectLengths.setShort(j, (short) length);
        long j2 = this.sizeof * j * this.maxObjectLength;
        for (int i = 0; i < length; i++) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * i), byteArray[i]);
        }
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void set(long j, Object obj) {
        if (obj == null) {
            if (this.ptr != 0) {
                this.objectLengths.setShort(j, (short) -1);
                return;
            } else {
                if (this.isConstant) {
                    throw new IllegalAccessError("Constant arrays cannot be modified.");
                }
                this.data[(int) j] = null;
                return;
            }
        }
        if (this.ptr != 0) {
            byte[] byteArray = toByteArray(obj);
            if (byteArray.length > this.maxObjectLength) {
                throw new IllegalArgumentException("Object  " + obj + " is too long.");
            }
            int length = byteArray.length;
            if (length > 32767) {
                throw new IllegalArgumentException("Object  " + obj + " is too long.");
            }
            this.objectLengths.setShort(j, (short) length);
            long j2 = this.sizeof * j * this.maxObjectLength;
            for (int i = 0; i < length; i++) {
                LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * i), byteArray[i]);
            }
            return;
        }
        if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
        this.data[(int) j] = obj;
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
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setByte(long j, byte b) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setUnsignedByte(long j, short s) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setShort(long j, short s) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setInt(long j, int i) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setLong(long j, long j2) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setFloat(long j, float f) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public final void setDouble(long j, double d) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
