package org.apache.commons.collections.buffer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUnderflowException;

/* loaded from: classes5.dex */
public class UnboundedFifoBuffer extends AbstractCollection implements Buffer, Serializable {
    private static final long serialVersionUID = -3482960336579541419L;
    protected transient Object[] buffer;
    protected transient int head;
    protected transient int tail;

    public UnboundedFifoBuffer() {
        this(32);
    }

    public UnboundedFifoBuffer(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        this.buffer = new Object[i + 1];
        this.head = 0;
        this.tail = 0;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            objectOutputStream.writeObject(it2.next());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        this.buffer = new Object[i + 1];
        for (int i2 = 0; i2 < i; i2++) {
            this.buffer[i2] = objectInputStream.readObject();
        }
        this.head = 0;
        this.tail = i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        int i = this.tail;
        int i2 = this.head;
        return i < i2 ? (this.buffer.length - i2) + i : i - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Attempted to add null object to buffer");
        }
        int size = size() + 1;
        Object[] objArr = this.buffer;
        if (size >= objArr.length) {
            Object[] objArr2 = new Object[((objArr.length - 1) * 2) + 1];
            int iIncrement = this.head;
            int i = 0;
            while (iIncrement != this.tail) {
                Object[] objArr3 = this.buffer;
                objArr2[i] = objArr3[iIncrement];
                objArr3[iIncrement] = null;
                i++;
                iIncrement = increment(iIncrement);
            }
            this.buffer = objArr2;
            this.head = 0;
            this.tail = i;
        }
        Object[] objArr4 = this.buffer;
        int i2 = this.tail;
        objArr4[i2] = obj;
        this.tail = increment(i2);
        return true;
    }

    @Override // org.apache.commons.collections.Buffer
    public Object get() {
        if (isEmpty()) {
            throw new BufferUnderflowException("The buffer is already empty");
        }
        return this.buffer[this.head];
    }

    @Override // org.apache.commons.collections.Buffer
    public Object remove() {
        if (isEmpty()) {
            throw new BufferUnderflowException("The buffer is already empty");
        }
        Object[] objArr = this.buffer;
        int i = this.head;
        Object obj = objArr[i];
        if (obj != null) {
            objArr[i] = null;
            this.head = increment(i);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int increment(int i) {
        int i2 = i + 1;
        if (i2 >= this.buffer.length) {
            return 0;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int decrement(int i) {
        int i2 = i - 1;
        return i2 < 0 ? this.buffer.length - 1 : i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new Iterator() { // from class: org.apache.commons.collections.buffer.UnboundedFifoBuffer.1
            private int index;
            private int lastReturnedIndex = -1;

            {
                this.index = UnboundedFifoBuffer.this.head;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index != UnboundedFifoBuffer.this.tail;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (hasNext()) {
                    int i = this.index;
                    this.lastReturnedIndex = i;
                    this.index = UnboundedFifoBuffer.this.increment(i);
                    return UnboundedFifoBuffer.this.buffer[this.lastReturnedIndex];
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                int i = this.lastReturnedIndex;
                if (i == -1) {
                    throw new IllegalStateException();
                }
                if (i != UnboundedFifoBuffer.this.head) {
                    int iIncrement = UnboundedFifoBuffer.this.increment(this.lastReturnedIndex);
                    while (iIncrement != UnboundedFifoBuffer.this.tail) {
                        UnboundedFifoBuffer.this.buffer[UnboundedFifoBuffer.this.decrement(iIncrement)] = UnboundedFifoBuffer.this.buffer[iIncrement];
                        iIncrement = UnboundedFifoBuffer.this.increment(iIncrement);
                    }
                    this.lastReturnedIndex = -1;
                    UnboundedFifoBuffer unboundedFifoBuffer = UnboundedFifoBuffer.this;
                    unboundedFifoBuffer.tail = unboundedFifoBuffer.decrement(unboundedFifoBuffer.tail);
                    UnboundedFifoBuffer.this.buffer[UnboundedFifoBuffer.this.tail] = null;
                    this.index = UnboundedFifoBuffer.this.decrement(this.index);
                    return;
                }
                UnboundedFifoBuffer.this.remove();
                this.lastReturnedIndex = -1;
            }
        };
    }
}
