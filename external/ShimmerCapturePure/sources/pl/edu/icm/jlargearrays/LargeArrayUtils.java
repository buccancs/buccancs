package pl.edu.icm.jlargearrays;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.math3.util.FastMath;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
public class LargeArrayUtils {
    public static final Unsafe UNSAFE;

    static {
        Object obj = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(cls);
            e = null;
            obj = obj2;
        } catch (ClassNotFoundException e) {
            e = e;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (IllegalArgumentException e3) {
            e = e3;
        } catch (NoSuchFieldException e4) {
            e = e4;
        } catch (SecurityException e5) {
            e = e5;
        }
        Unsafe unsafe = (Unsafe) obj;
        UNSAFE = unsafe;
        if (unsafe == null) {
            throw new Error("Could not obtain access to sun.misc.Unsafe", e);
        }
    }

    private LargeArrayUtils() {
    }

    public static void arraycopy(LargeArray largeArray, long j, LargeArray largeArray2, long j2, long j3) {
        if (largeArray.getType() != largeArray2.getType()) {
            throw new IllegalArgumentException("The type of source array is different than the type of destimation array.");
        }
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArray.getType().ordinal()]) {
            case 1:
                arraycopy((LogicLargeArray) largeArray, j, (LogicLargeArray) largeArray2, j2, j3);
                return;
            case 2:
                arraycopy((UnsignedByteLargeArray) largeArray, j, (UnsignedByteLargeArray) largeArray2, j2, j3);
                return;
            case 3:
                arraycopy((ShortLargeArray) largeArray, j, (ShortLargeArray) largeArray2, j2, j3);
                return;
            case 4:
                arraycopy((IntLargeArray) largeArray, j, (IntLargeArray) largeArray2, j2, j3);
                return;
            case 5:
                arraycopy((LongLargeArray) largeArray, j, (LongLargeArray) largeArray2, j2, j3);
                return;
            case 6:
                arraycopy((FloatLargeArray) largeArray, j, (FloatLargeArray) largeArray2, j2, j3);
                return;
            case 7:
                arraycopy((DoubleLargeArray) largeArray, j, (DoubleLargeArray) largeArray2, j2, j3);
                return;
            case 8:
                arraycopy((ComplexFloatLargeArray) largeArray, j, (ComplexFloatLargeArray) largeArray2, j2, j3);
                return;
            case 9:
                arraycopy((ComplexDoubleLargeArray) largeArray, j, (ComplexDoubleLargeArray) largeArray2, j2, j3);
                return;
            case 10:
                arraycopy((StringLargeArray) largeArray, j, (StringLargeArray) largeArray2, j2, j3);
                return;
            case 11:
                arraycopy((ObjectLargeArray) largeArray, j, (ObjectLargeArray) largeArray2, j2, j3);
                return;
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    public static void arraycopy(Object obj, long j, LargeArray largeArray, long j2, long j3) {
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArray.getType().ordinal()]) {
            case 1:
                arraycopy((boolean[]) obj, (int) j, (LogicLargeArray) largeArray, j2, j3);
                return;
            case 2:
                arraycopy((byte[]) obj, (int) j, (ByteLargeArray) largeArray, j2, j3);
                return;
            case 3:
                arraycopy((short[]) obj, (int) j, (ShortLargeArray) largeArray, j2, j3);
                return;
            case 4:
                arraycopy((int[]) obj, (int) j, (IntLargeArray) largeArray, j2, j3);
                return;
            case 5:
                arraycopy((long[]) obj, (int) j, (LongLargeArray) largeArray, j2, j3);
                return;
            case 6:
                arraycopy((float[]) obj, (int) j, (FloatLargeArray) largeArray, j2, j3);
                return;
            case 7:
                arraycopy((double[]) obj, (int) j, (DoubleLargeArray) largeArray, j2, j3);
                return;
            case 8:
                arraycopy((float[]) obj, (int) j, (ComplexFloatLargeArray) largeArray, j2, j3);
                return;
            case 9:
                arraycopy((double[]) obj, (int) j, (ComplexDoubleLargeArray) largeArray, j2, j3);
                return;
            case 10:
                arraycopy((String[]) obj, (int) j, (StringLargeArray) largeArray, j2, j3);
                return;
            case 11:
                arraycopy((Object[]) obj, (int) j, (ObjectLargeArray) largeArray, j2, j3);
                return;
            case 12:
                if (obj.getClass().getComponentType() == Byte.TYPE) {
                    arraycopy((byte[]) obj, (int) j, (UnsignedByteLargeArray) largeArray, j2, j3);
                    return;
                } else {
                    arraycopy((short[]) obj, (int) j, (UnsignedByteLargeArray) largeArray, j2, j3);
                    return;
                }
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    public static void arraycopy(final LogicLargeArray logicLargeArray, final long j, final LogicLargeArray logicLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= logicLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= logicLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (logicLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                logicLargeArray2.setByte(j5, logicLargeArray.getByte(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        logicLargeArray2.setByte(j2 + j9, logicLargeArray.getByte(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                logicLargeArray2.setByte(j10, logicLargeArray.getByte(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                logicLargeArray2.setByte(j12, logicLargeArray.getByte(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final boolean[] zArr, final int i, final LogicLargeArray logicLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= zArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= logicLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (logicLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                logicLargeArray.setBoolean(j3, zArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        logicLargeArray.setBoolean(j + j7, zArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                logicLargeArray.setBoolean(j7, zArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                logicLargeArray.setBoolean(j8, zArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final ByteLargeArray byteLargeArray, final long j, final ByteLargeArray byteLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= byteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= byteLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (byteLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                byteLargeArray2.setByte(j5, byteLargeArray.getByte(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.3
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        byteLargeArray2.setByte(j2 + j9, byteLargeArray.getByte(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                byteLargeArray2.setByte(j10, byteLargeArray.getByte(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                byteLargeArray2.setByte(j12, byteLargeArray.getByte(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final byte[] bArr, final int i, final ByteLargeArray byteLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= byteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (byteLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                byteLargeArray.setByte(j3, bArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.4
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        byteLargeArray.setByte(j + j7, bArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                byteLargeArray.setByte(j7, bArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                byteLargeArray.setByte(j8, bArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final UnsignedByteLargeArray unsignedByteLargeArray, final long j, final UnsignedByteLargeArray unsignedByteLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= unsignedByteLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (unsignedByteLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                unsignedByteLargeArray2.setByte(j5, unsignedByteLargeArray.getByte(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.5
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        unsignedByteLargeArray2.setByte(j2 + j9, unsignedByteLargeArray.getByte(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                unsignedByteLargeArray2.setByte(j10, unsignedByteLargeArray.getByte(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                unsignedByteLargeArray2.setByte(j12, unsignedByteLargeArray.getByte(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final byte[] bArr, final int i, final UnsignedByteLargeArray unsignedByteLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (unsignedByteLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                unsignedByteLargeArray.setByte(j3, bArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.6
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        unsignedByteLargeArray.setByte(j + j7, bArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                unsignedByteLargeArray.setByte(j7, bArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                unsignedByteLargeArray.setByte(j8, bArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final short[] sArr, final int i, final UnsignedByteLargeArray unsignedByteLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= sArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (unsignedByteLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                unsignedByteLargeArray.setUnsignedByte(j3, sArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.7
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        unsignedByteLargeArray.setUnsignedByte(j + j7, sArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                unsignedByteLargeArray.setUnsignedByte(j7, sArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                unsignedByteLargeArray.setUnsignedByte(j8, sArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final ShortLargeArray shortLargeArray, final long j, final ShortLargeArray shortLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= shortLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= shortLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (shortLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                shortLargeArray2.setShort(j5, shortLargeArray.getShort(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.8
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        shortLargeArray2.setShort(j2 + j9, shortLargeArray.getShort(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                shortLargeArray2.setShort(j10, shortLargeArray.getShort(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                shortLargeArray2.setShort(j12, shortLargeArray.getShort(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final short[] sArr, final int i, final ShortLargeArray shortLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= sArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= shortLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (shortLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                shortLargeArray.setShort(j3, sArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.9
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        shortLargeArray.setShort(j + j7, sArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                shortLargeArray.setShort(j7, sArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                shortLargeArray.setShort(j8, sArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final IntLargeArray intLargeArray, final long j, final IntLargeArray intLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= intLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= intLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (intLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                intLargeArray2.setInt(j5, intLargeArray.getInt(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.10
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        intLargeArray2.setInt(j2 + j9, intLargeArray.getInt(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                intLargeArray2.setInt(j10, intLargeArray.getInt(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                intLargeArray2.setInt(j12, intLargeArray.getInt(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final int[] iArr, final int i, final IntLargeArray intLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= iArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= intLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (intLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                intLargeArray.setInt(j3, iArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.11
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        intLargeArray.setInt(j + j7, iArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                intLargeArray.setInt(j7, iArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                intLargeArray.setInt(j8, iArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final LongLargeArray longLargeArray, final long j, final LongLargeArray longLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= longLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= longLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (longLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                longLargeArray2.setLong(j5, longLargeArray.getLong(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.12
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        longLargeArray2.setLong(j2 + j9, longLargeArray.getLong(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                longLargeArray2.setLong(j10, longLargeArray.getLong(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                longLargeArray2.setLong(j12, longLargeArray.getLong(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final long[] jArr, final int i, final LongLargeArray longLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= jArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= longLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (longLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                longLargeArray.setLong(j3, jArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.13
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        longLargeArray.setLong(j + j7, jArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                longLargeArray.setLong(j7, jArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                longLargeArray.setLong(j8, jArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final FloatLargeArray floatLargeArray, final long j, final FloatLargeArray floatLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= floatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= floatLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (floatLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                floatLargeArray2.setFloat(j5, floatLargeArray.getFloat(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.14
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        floatLargeArray2.setFloat(j2 + j9, floatLargeArray.getFloat(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                floatLargeArray2.setFloat(j10, floatLargeArray.getFloat(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                floatLargeArray2.setFloat(j12, floatLargeArray.getFloat(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final float[] fArr, final int i, final FloatLargeArray floatLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= fArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= floatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (floatLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                floatLargeArray.setFloat(j3, fArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.15
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        floatLargeArray.setFloat(j + j7, fArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                floatLargeArray.setFloat(j7, fArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                floatLargeArray.setFloat(j8, fArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final DoubleLargeArray doubleLargeArray, final long j, final DoubleLargeArray doubleLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= doubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= doubleLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (doubleLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                doubleLargeArray2.setDouble(j5, doubleLargeArray.getDouble(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.16
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        doubleLargeArray2.setDouble(j2 + j9, doubleLargeArray.getDouble(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                doubleLargeArray2.setDouble(j10, doubleLargeArray.getDouble(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                doubleLargeArray2.setDouble(j12, doubleLargeArray.getDouble(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final double[] dArr, final int i, final DoubleLargeArray doubleLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= dArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= doubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (doubleLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                doubleLargeArray.setDouble(j3, dArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.17
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        doubleLargeArray.setDouble(j + j7, dArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                doubleLargeArray.setDouble(j7, dArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                doubleLargeArray.setDouble(j8, dArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final ComplexFloatLargeArray complexFloatLargeArray, final long j, final ComplexFloatLargeArray complexFloatLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= complexFloatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= complexFloatLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (complexFloatLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                complexFloatLargeArray2.setComplexFloat(j5, complexFloatLargeArray.getComplexFloat(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.18
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        complexFloatLargeArray2.setComplexFloat(j2 + j9, complexFloatLargeArray.getComplexFloat(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                complexFloatLargeArray2.setComplexFloat(j10, complexFloatLargeArray.getComplexFloat(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                complexFloatLargeArray2.setComplexFloat(j12, complexFloatLargeArray.getComplexFloat(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final float[] fArr, final int i, final ComplexFloatLargeArray complexFloatLargeArray, final long j, long j2) {
        int i2 = i;
        if (fArr.length % 2 != 0) {
            throw new IllegalArgumentException("The length of the source array must be even.");
        }
        if (i2 < 0 || i2 >= fArr.length / 2) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
        }
        if (j < 0 || j >= complexFloatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (complexFloatLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            float[] fArr2 = new float[2];
            for (long j3 = j; j3 < j + j2; j3++) {
                int i3 = i2 * 2;
                fArr2[0] = fArr[i3];
                fArr2[1] = fArr[i3 + 1];
                complexFloatLargeArray.setComplexFloat(j3, fArr2);
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i4 = 0;
        while (i4 < iMin) {
            final long j5 = i4 * j4;
            final long j6 = i4 == iMin + (-1) ? j2 : j5 + j4;
            int i5 = i4;
            Future[] futureArr2 = futureArr;
            futureArr2[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.19
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr3 = new float[2];
                    for (long j7 = j5; j7 < j6; j7++) {
                        float[] fArr4 = fArr;
                        int i6 = i;
                        int i7 = (int) j7;
                        fArr3[0] = fArr4[(i6 + i7) * 2];
                        fArr3[1] = fArr4[((i6 + i7) * 2) + 1];
                        complexFloatLargeArray.setComplexFloat(j + j7, fArr3);
                    }
                }
            });
            i4 = i5 + 1;
            iMin = iMin;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            float[] fArr3 = new float[2];
            for (long j7 = j; j7 < j + j2; j7++) {
                int i6 = i2 * 2;
                fArr3[0] = fArr[i6];
                fArr3[1] = fArr[i6 + 1];
                complexFloatLargeArray.setComplexFloat(j7, fArr3);
                i2++;
            }
        } catch (ExecutionException unused2) {
            float[] fArr4 = new float[2];
            for (long j8 = j; j8 < j + j2; j8++) {
                int i7 = i2 * 2;
                fArr4[0] = fArr[i7];
                fArr4[1] = fArr[i7 + 1];
                complexFloatLargeArray.setComplexFloat(j8, fArr4);
                i2++;
            }
        }
    }

    public static void arraycopy(final ComplexDoubleLargeArray complexDoubleLargeArray, final long j, final ComplexDoubleLargeArray complexDoubleLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= complexDoubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= complexDoubleLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (complexDoubleLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                complexDoubleLargeArray2.setComplexDouble(j5, complexDoubleLargeArray.getComplexDouble(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.20
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        complexDoubleLargeArray2.setComplexDouble(j2 + j9, complexDoubleLargeArray.getComplexDouble(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                complexDoubleLargeArray2.setComplexDouble(j10, complexDoubleLargeArray.getComplexDouble(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                complexDoubleLargeArray2.setComplexDouble(j12, complexDoubleLargeArray.getComplexDouble(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final double[] dArr, final int i, final ComplexDoubleLargeArray complexDoubleLargeArray, final long j, long j2) {
        int i2 = i;
        if (dArr.length % 2 != 0) {
            throw new IllegalArgumentException("The length of the source array must be even.");
        }
        if (i2 < 0 || i2 >= dArr.length / 2) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
        }
        if (j < 0 || j >= complexDoubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (complexDoubleLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            double[] dArr2 = new double[2];
            for (long j3 = j; j3 < j + j2; j3++) {
                int i3 = i2 * 2;
                dArr2[0] = dArr[i3];
                dArr2[1] = dArr[i3 + 1];
                complexDoubleLargeArray.setComplexDouble(j3, dArr2);
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i4 = 0;
        while (i4 < iMin) {
            final long j5 = i4 * j4;
            final long j6 = i4 == iMin + (-1) ? j2 : j5 + j4;
            int i5 = i4;
            Future[] futureArr2 = futureArr;
            futureArr2[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.21
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr3 = new double[2];
                    for (long j7 = j5; j7 < j6; j7++) {
                        double[] dArr4 = dArr;
                        int i6 = i;
                        int i7 = (int) j7;
                        dArr3[0] = dArr4[(i6 + i7) * 2];
                        dArr3[1] = dArr4[((i6 + i7) * 2) + 1];
                        complexDoubleLargeArray.setComplexDouble(j + j7, dArr3);
                    }
                }
            });
            i4 = i5 + 1;
            iMin = iMin;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            double[] dArr3 = new double[2];
            for (long j7 = j; j7 < j + j2; j7++) {
                int i6 = i2 * 2;
                dArr3[0] = dArr[i6];
                dArr3[1] = dArr[i6 + 1];
                complexDoubleLargeArray.setComplexDouble(j7, dArr3);
                i2++;
            }
        } catch (ExecutionException unused2) {
            double[] dArr4 = new double[2];
            for (long j8 = j; j8 < j + j2; j8++) {
                int i7 = i2 * 2;
                dArr4[0] = dArr[i7];
                dArr4[1] = dArr[i7 + 1];
                complexDoubleLargeArray.setComplexDouble(j8, dArr4);
                i2++;
            }
        }
    }

    public static void arraycopy(final StringLargeArray stringLargeArray, final long j, final StringLargeArray stringLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= stringLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= stringLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (stringLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                stringLargeArray2.set(j5, stringLargeArray.get(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.22
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        stringLargeArray2.set(j2 + j9, stringLargeArray.get(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                stringLargeArray2.set(j10, stringLargeArray.get(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                stringLargeArray2.set(j12, stringLargeArray.get(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final String[] strArr, final int i, final StringLargeArray stringLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= strArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= stringLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (stringLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                stringLargeArray.set(j3, strArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.23
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        stringLargeArray.set(j + j7, strArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                stringLargeArray.set(j7, strArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                stringLargeArray.set(j8, strArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static void arraycopy(final ObjectLargeArray objectLargeArray, final long j, final ObjectLargeArray objectLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= objectLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= objectLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (objectLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                objectLargeArray2.set(j5, objectLargeArray.get(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / iMin;
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = i * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.24
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        objectLargeArray2.set(j2 + j9, objectLargeArray.get(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                objectLargeArray2.set(j10, objectLargeArray.get(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                objectLargeArray2.set(j12, objectLargeArray.get(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final Object[] objArr, final int i, final ObjectLargeArray objectLargeArray, final long j, long j2) {
        int i2 = i;
        if (i2 < 0 || i2 >= objArr.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        }
        if (j < 0 || j >= objectLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (objectLargeArray.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j3 = j;
            while (j3 < j + j2) {
                objectLargeArray.set(j3, objArr[i2]);
                j3++;
                i2++;
            }
            return;
        }
        long j4 = j2 / iMin;
        Future[] futureArr = new Future[iMin];
        int i3 = 0;
        while (i3 < iMin) {
            final long j5 = i3 * j4;
            final long j6 = i3 == iMin + (-1) ? j2 : j5 + j4;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.25
                @Override // java.lang.Runnable
                public void run() {
                    for (long j7 = j5; j7 < j6; j7++) {
                        objectLargeArray.set(j + j7, objArr[i + ((int) j7)]);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j7 = j;
            while (j7 < j + j2) {
                objectLargeArray.set(j7, objArr[i2]);
                j7++;
                i2++;
            }
        } catch (ExecutionException unused2) {
            long j8 = j;
            while (j8 < j + j2) {
                objectLargeArray.set(j8, objArr[i2]);
                j8++;
                i2++;
            }
        }
    }

    public static LargeArray createConstant(LargeArrayType largeArrayType, long j, Object obj) {
        long jLongValue;
        float fFloatValue;
        double dDoubleValue;
        byte bByteValue = 0;
        short sShortValue = 0;
        int iIntValue = 0;
        short sShortValue2 = 0;
        byte bByteValue2 = 0;
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
            case 1:
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        bByteValue = 1;
                    }
                } else if (obj instanceof Byte) {
                    bByteValue = ((Byte) obj).byteValue();
                } else if (obj instanceof Short) {
                    bByteValue = ((Short) obj).byteValue();
                } else if (obj instanceof Integer) {
                    bByteValue = ((Integer) obj).byteValue();
                } else if (obj instanceof Long) {
                    bByteValue = ((Long) obj).byteValue();
                } else if (obj instanceof Float) {
                    bByteValue = ((Float) obj).byteValue();
                } else if (obj instanceof Double) {
                    bByteValue = ((Double) obj).byteValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new LogicLargeArray(j, bByteValue);
            case 2:
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        bByteValue2 = 1;
                    }
                } else if (obj instanceof Byte) {
                    bByteValue2 = ((Byte) obj).byteValue();
                } else if (obj instanceof Short) {
                    bByteValue2 = ((Short) obj).byteValue();
                } else if (obj instanceof Integer) {
                    bByteValue2 = ((Integer) obj).byteValue();
                } else if (obj instanceof Long) {
                    bByteValue2 = ((Long) obj).byteValue();
                } else if (obj instanceof Float) {
                    bByteValue2 = ((Float) obj).byteValue();
                } else if (obj instanceof Double) {
                    bByteValue2 = ((Double) obj).byteValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new ByteLargeArray(j, bByteValue2);
            case 3:
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        sShortValue2 = 1;
                    }
                } else if (obj instanceof Byte) {
                    sShortValue2 = ((Byte) obj).shortValue();
                } else if (obj instanceof Short) {
                    sShortValue2 = ((Short) obj).shortValue();
                } else if (obj instanceof Integer) {
                    sShortValue2 = ((Integer) obj).shortValue();
                } else if (obj instanceof Long) {
                    sShortValue2 = ((Long) obj).shortValue();
                } else if (obj instanceof Float) {
                    sShortValue2 = ((Float) obj).shortValue();
                } else if (obj instanceof Double) {
                    sShortValue2 = ((Double) obj).shortValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new ShortLargeArray(j, sShortValue2);
            case 4:
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        iIntValue = 1;
                    }
                } else if (obj instanceof Byte) {
                    iIntValue = ((Byte) obj).intValue();
                } else if (obj instanceof Short) {
                    iIntValue = ((Short) obj).intValue();
                } else if (obj instanceof Integer) {
                    iIntValue = ((Integer) obj).intValue();
                } else if (obj instanceof Long) {
                    iIntValue = ((Long) obj).intValue();
                } else if (obj instanceof Float) {
                    iIntValue = ((Float) obj).intValue();
                } else if (obj instanceof Double) {
                    iIntValue = ((Double) obj).intValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new IntLargeArray(j, iIntValue);
            case 5:
                if (obj instanceof Boolean) {
                    jLongValue = ((Boolean) obj).booleanValue() ? 1L : 0L;
                } else if (obj instanceof Byte) {
                    jLongValue = ((Byte) obj).longValue();
                } else if (obj instanceof Short) {
                    jLongValue = ((Short) obj).longValue();
                } else if (obj instanceof Integer) {
                    jLongValue = ((Integer) obj).longValue();
                } else if (obj instanceof Long) {
                    jLongValue = ((Long) obj).longValue();
                } else if (obj instanceof Float) {
                    jLongValue = ((Float) obj).longValue();
                } else if (obj instanceof Double) {
                    jLongValue = ((Double) obj).longValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new LongLargeArray(j, jLongValue);
            case 6:
                if (obj instanceof Boolean) {
                    fFloatValue = ((Boolean) obj).booleanValue() ? 1.0f : 0.0f;
                } else if (obj instanceof Byte) {
                    fFloatValue = ((Byte) obj).floatValue();
                } else if (obj instanceof Short) {
                    fFloatValue = ((Short) obj).floatValue();
                } else if (obj instanceof Integer) {
                    fFloatValue = ((Integer) obj).floatValue();
                } else if (obj instanceof Long) {
                    fFloatValue = ((Long) obj).floatValue();
                } else if (obj instanceof Float) {
                    fFloatValue = ((Float) obj).floatValue();
                } else if (obj instanceof Double) {
                    fFloatValue = ((Double) obj).floatValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new FloatLargeArray(j, fFloatValue);
            case 7:
                if (obj instanceof Boolean) {
                    dDoubleValue = ((Boolean) obj).booleanValue() ? 1.0d : 0.0d;
                } else if (obj instanceof Byte) {
                    dDoubleValue = ((Byte) obj).doubleValue();
                } else if (obj instanceof Short) {
                    dDoubleValue = ((Short) obj).doubleValue();
                } else if (obj instanceof Integer) {
                    dDoubleValue = ((Integer) obj).doubleValue();
                } else if (obj instanceof Long) {
                    dDoubleValue = ((Long) obj).doubleValue();
                } else if (obj instanceof Float) {
                    dDoubleValue = ((Float) obj).doubleValue();
                } else if (obj instanceof Double) {
                    dDoubleValue = ((Double) obj).doubleValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new DoubleLargeArray(j, dDoubleValue);
            case 8:
                if (obj.getClass().getComponentType() != Float.TYPE) {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new ComplexFloatLargeArray(j, (float[]) obj);
            case 9:
                if (obj.getClass().getComponentType() != Double.TYPE) {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new ComplexDoubleLargeArray(j, (double[]) obj);
            case 10:
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new StringLargeArray(j, (String) obj);
            case 11:
                return new ObjectLargeArray(j, obj);
            case 12:
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        sShortValue = 1;
                    }
                } else if (obj instanceof Byte) {
                    sShortValue = ((Byte) obj).shortValue();
                } else if (obj instanceof Short) {
                    sShortValue = ((Short) obj).shortValue();
                } else if (obj instanceof Integer) {
                    sShortValue = ((Integer) obj).shortValue();
                } else if (obj instanceof Long) {
                    sShortValue = ((Long) obj).shortValue();
                } else if (obj instanceof Float) {
                    sShortValue = ((Float) obj).shortValue();
                } else if (obj instanceof Double) {
                    sShortValue = ((Double) obj).shortValue();
                } else {
                    throw new IllegalArgumentException("Invalid value type.");
                }
                return new UnsignedByteLargeArray(j, sShortValue);
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    public static LargeArray create(LargeArrayType largeArrayType, long j) {
        return create(largeArrayType, j, true);
    }

    public static LargeArray create(LargeArrayType largeArrayType, long j, boolean z) {
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
            case 1:
                return new LogicLargeArray(j, z);
            case 2:
                return new ByteLargeArray(j, z);
            case 3:
                return new ShortLargeArray(j, z);
            case 4:
                return new IntLargeArray(j, z);
            case 5:
                return new LongLargeArray(j, z);
            case 6:
                return new FloatLargeArray(j, z);
            case 7:
                return new DoubleLargeArray(j, z);
            case 8:
                return new ComplexFloatLargeArray(j, z);
            case 9:
                return new ComplexDoubleLargeArray(j, z);
            case 10:
                return new StringLargeArray(j, 100, z);
            case 11:
                return new ObjectLargeArray(j, 100, z);
            case 12:
                return new UnsignedByteLargeArray(j, z);
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static LargeArray generateRandom(LargeArrayType largeArrayType, long j) {
        LargeArray largeArrayCreate = create(largeArrayType, j, false);
        Random random = new Random();
        long j2 = 0;
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
            case 1:
                while (j2 < j) {
                    largeArrayCreate.setBoolean(j2, random.nextBoolean());
                    j2++;
                }
                return largeArrayCreate;
            case 2:
            case 12:
                while (j2 < j / 4) {
                    int iNextInt = random.nextInt();
                    largeArrayCreate.setByte(j2, (byte) (iNextInt >> 8));
                    largeArrayCreate.setByte(j2 + 1, (byte) (iNextInt >> 16));
                    largeArrayCreate.setByte(j2 + 2, (byte) (iNextInt >> 24));
                    largeArrayCreate.setByte(3 + j2, (byte) (iNextInt >> 32));
                    j2 += 4;
                }
                int iNextInt2 = random.nextInt();
                while (j2 < j) {
                    iNextInt2 >>= 8;
                    largeArrayCreate.setByte(j2, (byte) iNextInt2);
                    j2++;
                }
                return largeArrayCreate;
            case 3:
                while (j2 < j / 2) {
                    int iNextInt3 = random.nextInt();
                    largeArrayCreate.setShort(j2, (short) (iNextInt3 >> 16));
                    largeArrayCreate.setShort(j2 + 1, (short) (iNextInt3 >> 32));
                    j2 += 2;
                }
                int iNextInt4 = random.nextInt();
                while (j2 < j) {
                    iNextInt4 >>= 16;
                    largeArrayCreate.setShort(j2, (short) iNextInt4);
                    j2++;
                }
                return largeArrayCreate;
            case 4:
                while (j2 < j) {
                    largeArrayCreate.setInt(j2, random.nextInt());
                    j2++;
                }
                return largeArrayCreate;
            case 5:
                while (j2 < j) {
                    largeArrayCreate.setLong(j2, random.nextLong());
                    j2++;
                }
                return largeArrayCreate;
            case 6:
                while (j2 < j) {
                    largeArrayCreate.setFloat(j2, random.nextFloat());
                    j2++;
                }
                return largeArrayCreate;
            case 7:
                while (j2 < j) {
                    largeArrayCreate.setDouble(j2, random.nextDouble());
                    j2++;
                }
                return largeArrayCreate;
            case 8:
                ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArrayCreate;
                float[] fArr = new float[2];
                while (j2 < j) {
                    fArr[0] = random.nextFloat();
                    fArr[1] = random.nextFloat();
                    complexFloatLargeArray.setComplexFloat(j2, fArr);
                    j2++;
                }
                return largeArrayCreate;
            case 9:
                ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArrayCreate;
                double[] dArr = new double[2];
                while (j2 < j) {
                    dArr[0] = random.nextDouble();
                    dArr[1] = random.nextDouble();
                    complexDoubleLargeArray.setComplexDouble(j2, dArr);
                    j2++;
                }
                return largeArrayCreate;
            case 10:
                while (j2 < j) {
                    largeArrayCreate.setFloat(j2, random.nextFloat());
                    j2++;
                }
                return largeArrayCreate;
            case 11:
                while (j2 < j) {
                    largeArrayCreate.set(j2, Float.valueOf(random.nextFloat()));
                    j2++;
                }
                return largeArrayCreate;
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static LargeArray convert(final LargeArray largeArray, final LargeArrayType largeArrayType) {
        if (largeArray.getType() == largeArrayType) {
            return largeArray;
        }
        long j = 0;
        if (largeArray.isConstant()) {
            switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
                case 1:
                    return new LogicLargeArray(largeArray.length(), largeArray.getByte(0L));
                case 2:
                    return new ByteLargeArray(largeArray.length(), largeArray.getByte(0L));
                case 3:
                    return new ShortLargeArray(largeArray.length(), largeArray.getShort(0L));
                case 4:
                    return new IntLargeArray(largeArray.length(), largeArray.getInt(0L));
                case 5:
                    return new LongLargeArray(largeArray.length(), largeArray.getLong(0L));
                case 6:
                    return new FloatLargeArray(largeArray.length(), largeArray.getFloat(0L));
                case 7:
                    return new DoubleLargeArray(largeArray.length(), largeArray.getDouble(0L));
                case 8:
                    return new ComplexFloatLargeArray(largeArray.length(), ((ComplexFloatLargeArray) largeArray).getComplexFloat(0L));
                case 9:
                    return new ComplexDoubleLargeArray(largeArray.length(), ((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L));
                case 10:
                    return new StringLargeArray(largeArray.length(), largeArray.get(0L).toString());
                case 11:
                    return new ObjectLargeArray(largeArray.length(), largeArray.get(0L));
                case 12:
                    return new UnsignedByteLargeArray(largeArray.length(), largeArray.getUnsignedByte(0L));
                default:
                    throw new IllegalArgumentException("Invalid array type.");
            }
        }
        long j2 = largeArray.length;
        final LargeArray largeArrayCreate = create(largeArrayType, j2, false);
        int iMin = (int) FastMath.min(j2, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
            switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
                case 1:
                case 2:
                    while (j < j2) {
                        largeArrayCreate.setByte(j, largeArray.getByte(j));
                        j++;
                    }
                    break;
                case 3:
                    while (j < j2) {
                        largeArrayCreate.setShort(j, largeArray.getShort(j));
                        j++;
                    }
                    break;
                case 4:
                    while (j < j2) {
                        largeArrayCreate.setInt(j, largeArray.getInt(j));
                        j++;
                    }
                    break;
                case 5:
                    while (j < j2) {
                        largeArrayCreate.setLong(j, largeArray.getLong(j));
                        j++;
                    }
                    break;
                case 6:
                    while (j < j2) {
                        largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                        j++;
                    }
                    break;
                case 7:
                    while (j < j2) {
                        largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                        j++;
                    }
                    break;
                case 8:
                    if (largeArray.getType() != LargeArrayType.COMPLEX_DOUBLE) {
                        while (j < j2) {
                            largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                            j++;
                        }
                        break;
                    } else {
                        while (j < j2) {
                            ((ComplexFloatLargeArray) largeArrayCreate).setComplexDouble(j, ((ComplexDoubleLargeArray) largeArray).getComplexDouble(j));
                            j++;
                        }
                        break;
                    }
                case 9:
                    if (largeArray.getType() != LargeArrayType.COMPLEX_FLOAT) {
                        while (j < j2) {
                            largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                            j++;
                        }
                        break;
                    } else {
                        while (j < j2) {
                            ((ComplexDoubleLargeArray) largeArrayCreate).setComplexFloat(j, ((ComplexFloatLargeArray) largeArray).getComplexFloat(j));
                            j++;
                        }
                        break;
                    }
                case 10:
                    while (j < j2) {
                        largeArrayCreate.set(j, largeArray.get(j).toString());
                        j++;
                    }
                    break;
                case 11:
                    while (j < j2) {
                        largeArrayCreate.set(j, largeArray.get(j));
                        j++;
                    }
                    break;
                case 12:
                    while (j < j2) {
                        largeArrayCreate.setUnsignedByte(j, largeArray.getUnsignedByte(j));
                        j++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            long j3 = j2 / iMin;
            Future[] futureArr = new Future[iMin];
            int i = 0;
            while (i < iMin) {
                final long j4 = i * j3;
                int i2 = i;
                final long j5 = i == iMin + (-1) ? j2 : j4 + j3;
                Future[] futureArr2 = futureArr;
                futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.26
                    @Override // java.lang.Runnable
                    public void run() {
                        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
                            case 2:
                                for (long j6 = j4; j6 < j5; j6++) {
                                    largeArrayCreate.setByte(j6, largeArray.getByte(j6));
                                }
                                return;
                            case 3:
                                for (long j7 = j4; j7 < j5; j7++) {
                                    largeArrayCreate.setShort(j7, largeArray.getShort(j7));
                                }
                                return;
                            case 4:
                                for (long j8 = j4; j8 < j5; j8++) {
                                    largeArrayCreate.setInt(j8, largeArray.getInt(j8));
                                }
                                return;
                            case 5:
                                for (long j9 = j4; j9 < j5; j9++) {
                                    largeArrayCreate.setLong(j9, largeArray.getLong(j9));
                                }
                                return;
                            case 6:
                                for (long j10 = j4; j10 < j5; j10++) {
                                    largeArrayCreate.setFloat(j10, largeArray.getFloat(j10));
                                }
                                return;
                            case 7:
                                for (long j11 = j4; j11 < j5; j11++) {
                                    largeArrayCreate.setDouble(j11, largeArray.getDouble(j11));
                                }
                                return;
                            case 8:
                                if (largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                                    for (long j12 = j4; j12 < j5; j12++) {
                                        ((ComplexFloatLargeArray) largeArrayCreate).setComplexDouble(j12, ((ComplexDoubleLargeArray) largeArray).getComplexDouble(j12));
                                    }
                                    return;
                                }
                                for (long j13 = j4; j13 < j5; j13++) {
                                    largeArrayCreate.setFloat(j13, largeArray.getFloat(j13));
                                }
                                return;
                            case 9:
                                if (largeArray.getType() == LargeArrayType.COMPLEX_FLOAT) {
                                    for (long j14 = j4; j14 < j5; j14++) {
                                        ((ComplexDoubleLargeArray) largeArrayCreate).setComplexFloat(j14, ((ComplexFloatLargeArray) largeArray).getComplexFloat(j14));
                                    }
                                    return;
                                }
                                for (long j15 = j4; j15 < j5; j15++) {
                                    largeArrayCreate.setDouble(j15, largeArray.getDouble(j15));
                                }
                                return;
                            case 10:
                                for (long j16 = j4; j16 < j5; j16++) {
                                    largeArrayCreate.set(j16, largeArray.get(j16).toString());
                                }
                                return;
                            case 11:
                                for (long j17 = j4; j17 < j5; j17++) {
                                    largeArrayCreate.set(j17, largeArray.get(j17));
                                }
                                return;
                            case 12:
                                for (long j18 = j4; j18 < j5; j18++) {
                                    largeArrayCreate.setUnsignedByte(j18, largeArray.getUnsignedByte(j18));
                                }
                                return;
                            default:
                                throw new IllegalArgumentException("Invalid array type.");
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
                iMin = iMin;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
                    case 1:
                    case 2:
                        while (j < j2) {
                            largeArrayCreate.setByte(j, largeArray.getByte(j));
                            j++;
                        }
                        break;
                    case 3:
                        while (j < j2) {
                            largeArrayCreate.setShort(j, largeArray.getShort(j));
                            j++;
                        }
                        break;
                    case 4:
                        while (j < j2) {
                            largeArrayCreate.setInt(j, largeArray.getInt(j));
                            j++;
                        }
                        break;
                    case 5:
                        while (j < j2) {
                            largeArrayCreate.setLong(j, largeArray.getLong(j));
                            j++;
                        }
                        break;
                    case 6:
                        while (j < j2) {
                            largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                            j++;
                        }
                        break;
                    case 7:
                        while (j < j2) {
                            largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                            j++;
                        }
                        break;
                    case 8:
                        if (largeArray.getType() != LargeArrayType.COMPLEX_DOUBLE) {
                            while (j < j2) {
                                largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                                j++;
                            }
                            break;
                        } else {
                            while (j < j2) {
                                ((ComplexFloatLargeArray) largeArrayCreate).setComplexDouble(j, ((ComplexDoubleLargeArray) largeArray).getComplexDouble(j));
                                j++;
                            }
                            break;
                        }
                    case 9:
                        if (largeArray.getType() != LargeArrayType.COMPLEX_FLOAT) {
                            while (j < j2) {
                                largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                                j++;
                            }
                            break;
                        } else {
                            while (j < j2) {
                                ((ComplexDoubleLargeArray) largeArrayCreate).setComplexFloat(j, ((ComplexFloatLargeArray) largeArray).getComplexFloat(j));
                                j++;
                            }
                            break;
                        }
                    case 10:
                        while (j < j2) {
                            largeArrayCreate.set(j, largeArray.get(j).toString());
                            j++;
                        }
                        break;
                    case 11:
                        while (j < j2) {
                            largeArrayCreate.set(j, largeArray.get(j));
                            j++;
                        }
                        break;
                    case 12:
                        while (j < j2) {
                            largeArrayCreate.setUnsignedByte(j, largeArray.getUnsignedByte(j));
                            j++;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid array type.");
                }
            } catch (ExecutionException unused2) {
                switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
                    case 1:
                    case 2:
                        while (j < j2) {
                            largeArrayCreate.setByte(j, largeArray.getByte(j));
                            j++;
                        }
                        break;
                    case 3:
                        while (j < j2) {
                            largeArrayCreate.setShort(j, largeArray.getShort(j));
                            j++;
                        }
                        break;
                    case 4:
                        while (j < j2) {
                            largeArrayCreate.setInt(j, largeArray.getInt(j));
                            j++;
                        }
                        break;
                    case 5:
                        while (j < j2) {
                            largeArrayCreate.setLong(j, largeArray.getLong(j));
                            j++;
                        }
                        break;
                    case 6:
                        while (j < j2) {
                            largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                            j++;
                        }
                        break;
                    case 7:
                        while (j < j2) {
                            largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                            j++;
                        }
                        break;
                    case 8:
                        if (largeArray.getType() != LargeArrayType.COMPLEX_DOUBLE) {
                            while (j < j2) {
                                largeArrayCreate.setFloat(j, largeArray.getFloat(j));
                                j++;
                            }
                            break;
                        } else {
                            while (j < j2) {
                                ((ComplexFloatLargeArray) largeArrayCreate).setComplexDouble(j, ((ComplexDoubleLargeArray) largeArray).getComplexDouble(j));
                                j++;
                            }
                            break;
                        }
                    case 9:
                        if (largeArray.getType() != LargeArrayType.COMPLEX_FLOAT) {
                            while (j < j2) {
                                largeArrayCreate.setDouble(j, largeArray.getDouble(j));
                                j++;
                            }
                            break;
                        } else {
                            while (j < j2) {
                                ((ComplexDoubleLargeArray) largeArrayCreate).setComplexFloat(j, ((ComplexFloatLargeArray) largeArray).getComplexFloat(j));
                                j++;
                            }
                            break;
                        }
                    case 10:
                        while (j < j2) {
                            largeArrayCreate.set(j, largeArray.get(j).toString());
                            j++;
                        }
                        break;
                    case 11:
                        while (j < j2) {
                            largeArrayCreate.set(j, largeArray.get(j));
                            j++;
                        }
                        break;
                    case 12:
                        while (j < j2) {
                            largeArrayCreate.setUnsignedByte(j, largeArray.getUnsignedByte(j));
                            j++;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid array type.");
                }
            }
        }
        return largeArrayCreate;
    }

    public static LargeArray select(LargeArray largeArray, final LogicLargeArray logicLargeArray) {
        if (largeArray.length != logicLargeArray.length) {
            throw new IllegalArgumentException("src.length != mask.length");
        }
        long j = largeArray.length;
        int iMin = (int) FastMath.min(j, ConcurrencyUtils.getNumberOfThreads());
        long j2 = j / iMin;
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j3 = i * j2;
            final long j4 = i == iMin + (-1) ? j : j3 + j2;
            int i2 = i;
            futureArr[i2] = executorServiceNewCachedThreadPool.submit(new Callable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.27
                @Override // java.util.concurrent.Callable
                public Long call() {
                    long j5 = 0;
                    for (long j6 = j3; j6 < j4; j6++) {
                        if (logicLargeArray.getByte(j6) == 1) {
                            j5++;
                        }
                    }
                    return Long.valueOf(j5);
                }
            });
            i = i2 + 1;
        }
        long jLongValue = 0;
        for (int i3 = 0; i3 < iMin; i3++) {
            try {
                Long l = (Long) futureArr[i3].get();
                jLongValue += l.longValue();
            } catch (Exception unused) {
                for (long j5 = 0; j5 < j; j5++) {
                    if (logicLargeArray.getByte(j5) == 1) {
                        jLongValue++;
                    }
                }
            }
        }
        if (jLongValue <= 0) {
            return null;
        }
        LargeArray largeArrayCreate = create(largeArray.getType(), jLongValue, false);
        long j6 = 0;
        for (long j7 = 0; j7 < j; j7++) {
            if (logicLargeArray.getByte(j7) == 1) {
                largeArrayCreate.set(j6, largeArray.get(j7));
                j6++;
            }
        }
        return largeArrayCreate;
    }

    /* renamed from: pl.edu.icm.jlargearrays.LargeArrayUtils$28, reason: invalid class name */
    static /* synthetic */ class AnonymousClass28 {
        static final /* synthetic */ int[] $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType;

        static {
            int[] iArr = new int[LargeArrayType.values().length];
            $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType = iArr;
            try {
                iArr[LargeArrayType.LOGIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.COMPLEX_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.COMPLEX_DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.OBJECT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.UNSIGNED_BYTE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }
}
