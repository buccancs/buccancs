package org.apache.commons.collections.buffer;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.collection.SynchronizedCollection;

/* loaded from: classes5.dex */
public class SynchronizedBuffer extends SynchronizedCollection implements Buffer {
    private static final long serialVersionUID = -6859936183953626253L;

    protected SynchronizedBuffer(Buffer buffer) {
        super(buffer);
    }

    protected SynchronizedBuffer(Buffer buffer, Object obj) {
        super(buffer, obj);
    }

    public static Buffer decorate(Buffer buffer) {
        return new SynchronizedBuffer(buffer);
    }

    protected Buffer getBuffer() {
        return (Buffer) this.collection;
    }

    public Object get() {
        Object obj;
        synchronized (this.lock) {
            obj = getBuffer().get();
        }
        return obj;
    }

    public Object remove() {
        Object objRemove;
        synchronized (this.lock) {
            objRemove = getBuffer().remove();
        }
        return objRemove;
    }
}
