package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

/* loaded from: classes5.dex */
public class OpenIntToFieldHashMap<T extends FieldElement<T>> implements Serializable {
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    protected static final byte REMOVED = 2;
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int PERTURB_SHIFT = 5;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final long serialVersionUID = -9179080286849120720L;
    private final Field<T> field;
    private final T missingEntries;
    private transient int count;
    private int[] keys;
    private int mask;
    private int size;
    private byte[] states;
    private T[] values;

    public OpenIntToFieldHashMap(Field<T> field) {
        this(field, 16, field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, T t) {
        this(field, 16, t);
    }

    public OpenIntToFieldHashMap(Field<T> field, int i) {
        this(field, i, field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, int i, T t) {
        this.field = field;
        int iComputeCapacity = computeCapacity(i);
        this.keys = new int[iComputeCapacity];
        this.values = (T[]) buildArray(iComputeCapacity);
        this.states = new byte[iComputeCapacity];
        this.missingEntries = t;
        this.mask = iComputeCapacity - 1;
    }

    public OpenIntToFieldHashMap(OpenIntToFieldHashMap<T> openIntToFieldHashMap) {
        this.field = openIntToFieldHashMap.field;
        int length = openIntToFieldHashMap.keys.length;
        int[] iArr = new int[length];
        this.keys = iArr;
        System.arraycopy(openIntToFieldHashMap.keys, 0, iArr, 0, length);
        T[] tArr = (T[]) buildArray(length);
        this.values = tArr;
        System.arraycopy(openIntToFieldHashMap.values, 0, tArr, 0, length);
        byte[] bArr = new byte[length];
        this.states = bArr;
        System.arraycopy(openIntToFieldHashMap.states, 0, bArr, 0, length);
        this.missingEntries = openIntToFieldHashMap.missingEntries;
        this.size = openIntToFieldHashMap.size;
        this.mask = openIntToFieldHashMap.mask;
        this.count = openIntToFieldHashMap.count;
    }

    private static int changeIndexSign(int i) {
        return (-i) - 1;
    }

    private static int hashOf(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private static int perturb(int i) {
        return i & Integer.MAX_VALUE;
    }

    private static int probe(int i, int i2) {
        return (i2 << 2) + i2 + i + 1;
    }

    private static int computeCapacity(int i) {
        if (i == 0) {
            return 1;
        }
        int iCeil = (int) FastMath.ceil(i / 0.5f);
        return Integer.highestOneBit(iCeil) == iCeil ? iCeil : nextPowerOfTwo(iCeil);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    private static int findInsertionIndex(int[] iArr, byte[] bArr, int i, int i2) {
        int iProbe;
        int i3;
        int iHashOf = hashOf(i);
        int iProbe2 = iHashOf & i2;
        byte b = bArr[iProbe2];
        if (b == 0) {
            return iProbe2;
        }
        if (b == 1 && iArr[iProbe2] == i) {
            return changeIndexSign(iProbe2);
        }
        int iPerturb = perturb(iHashOf);
        if (bArr[iProbe2] == 1) {
            do {
                iProbe2 = probe(iPerturb, iProbe2);
                i3 = iProbe2 & i2;
                iPerturb >>= 5;
                if (bArr[i3] != 1) {
                    break;
                }
            } while (iArr[i3] != i);
            iProbe = iProbe2;
            iProbe2 = i3;
        } else {
            iProbe = iProbe2;
        }
        byte b2 = bArr[iProbe2];
        if (b2 == 0) {
            return iProbe2;
        }
        if (b2 == 1) {
            return changeIndexSign(iProbe2);
        }
        while (true) {
            iProbe = probe(iPerturb, iProbe);
            int i4 = iProbe & i2;
            byte b3 = bArr[i4];
            if (b3 == 0) {
                return iProbe2;
            }
            if (b3 == 1 && iArr[i4] == i) {
                return changeIndexSign(i4);
            }
            iPerturb >>= 5;
        }
    }

    private boolean shouldGrowTable() {
        return ((float) this.size) > ((float) (this.mask + 1)) * 0.5f;
    }

    public int size() {
        return this.size;
    }

    public T get(int i) {
        int iHashOf = hashOf(i);
        int i2 = this.mask & iHashOf;
        if (containsKey(i, i2)) {
            return this.values[i2];
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int iPerturb = perturb(iHashOf);
        int iProbe = i2;
        while (this.states[i2] != 0) {
            iProbe = probe(iPerturb, iProbe);
            i2 = this.mask & iProbe;
            if (containsKey(i, i2)) {
                return this.values[i2];
            }
            iPerturb >>= 5;
        }
        return this.missingEntries;
    }

    public boolean containsKey(int i) {
        int iHashOf = hashOf(i);
        int i2 = this.mask & iHashOf;
        if (containsKey(i, i2)) {
            return true;
        }
        if (this.states[i2] == 0) {
            return false;
        }
        int iPerturb = perturb(iHashOf);
        int iProbe = i2;
        while (this.states[i2] != 0) {
            iProbe = probe(iPerturb, iProbe);
            i2 = this.mask & iProbe;
            if (containsKey(i, i2)) {
                return true;
            }
            iPerturb >>= 5;
        }
        return false;
    }

    public OpenIntToFieldHashMap<T>.Iterator iterator() {
        return new Iterator();
    }

    private int findInsertionIndex(int i) {
        return findInsertionIndex(this.keys, this.states, i, this.mask);
    }

    public T remove(int i) {
        int iHashOf = hashOf(i);
        int i2 = this.mask & iHashOf;
        if (containsKey(i, i2)) {
            return (T) doRemove(i2);
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int iPerturb = perturb(iHashOf);
        int iProbe = i2;
        while (this.states[i2] != 0) {
            iProbe = probe(iPerturb, iProbe);
            i2 = this.mask & iProbe;
            if (containsKey(i, i2)) {
                return (T) doRemove(i2);
            }
            iPerturb >>= 5;
        }
        return this.missingEntries;
    }

    private boolean containsKey(int i, int i2) {
        return (i != 0 || this.states[i2] == 1) && this.keys[i2] == i;
    }

    private T doRemove(int i) {
        this.keys[i] = 0;
        this.states[i] = 2;
        T[] tArr = this.values;
        T t = tArr[i];
        tArr[i] = this.missingEntries;
        this.size--;
        this.count++;
        return t;
    }

    public T put(int i, T t) {
        boolean z;
        int iFindInsertionIndex = findInsertionIndex(i);
        T t2 = this.missingEntries;
        if (iFindInsertionIndex < 0) {
            iFindInsertionIndex = changeIndexSign(iFindInsertionIndex);
            t2 = this.values[iFindInsertionIndex];
            z = false;
        } else {
            z = true;
        }
        this.keys[iFindInsertionIndex] = i;
        this.states[iFindInsertionIndex] = 1;
        this.values[iFindInsertionIndex] = t;
        if (z) {
            this.size++;
            if (shouldGrowTable()) {
                growTable();
            }
            this.count++;
        }
        return t2;
    }

    private void growTable() {
        byte[] bArr = this.states;
        int length = bArr.length;
        int[] iArr = this.keys;
        T[] tArr = this.values;
        int i = length * 2;
        int[] iArr2 = new int[i];
        T[] tArr2 = (T[]) buildArray(i);
        byte[] bArr2 = new byte[i];
        int i2 = i - 1;
        for (int i3 = 0; i3 < length; i3++) {
            if (bArr[i3] == 1) {
                int i4 = iArr[i3];
                int iFindInsertionIndex = findInsertionIndex(iArr2, bArr2, i4, i2);
                iArr2[iFindInsertionIndex] = i4;
                tArr2[iFindInsertionIndex] = tArr[i3];
                bArr2[iFindInsertionIndex] = 1;
            }
        }
        this.mask = i2;
        this.keys = iArr2;
        this.values = tArr2;
        this.states = bArr2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.count = 0;
    }

    private T[] buildArray(int i) {
        return (T[]) ((FieldElement[]) Array.newInstance(this.field.getRuntimeClass(), i));
    }

    public class Iterator {
        private final int referenceCount;
        private int current;
        private int next;

        private Iterator() throws ConcurrentModificationException {
            this.referenceCount = OpenIntToFieldHashMap.this.count;
            this.next = -1;
            try {
                advance();
            } catch (NoSuchElementException unused) {
            }
        }

        public boolean hasNext() {
            return this.next >= 0;
        }

        public int key() throws NoSuchElementException, ConcurrentModificationException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current >= 0) {
                return OpenIntToFieldHashMap.this.keys[this.current];
            }
            throw new NoSuchElementException();
        }

        public T value() throws NoSuchElementException, ConcurrentModificationException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current >= 0) {
                return (T) OpenIntToFieldHashMap.this.values[this.current];
            }
            throw new NoSuchElementException();
        }

        public void advance() throws NoSuchElementException, ConcurrentModificationException {
            byte[] bArr;
            int i;
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            this.current = this.next;
            do {
                try {
                    bArr = OpenIntToFieldHashMap.this.states;
                    i = this.next + 1;
                    this.next = i;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    this.next = -2;
                    if (this.current < 0) {
                        throw new NoSuchElementException();
                    }
                    return;
                }
            } while (bArr[i] != 1);
        }
    }
}
