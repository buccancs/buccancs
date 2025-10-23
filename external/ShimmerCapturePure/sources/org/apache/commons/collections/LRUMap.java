package org.apache.commons.collections;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes5.dex */
public class LRUMap extends SequencedHashMap implements Externalizable {
    private static final long serialVersionUID = 2197433140769957051L;
    private int maximumSize;

    public LRUMap() {
        this(100);
    }

    public LRUMap(int i) {
        super(i);
        this.maximumSize = i;
    }

    public int getMaximumSize() {
        return this.maximumSize;
    }

    public void setMaximumSize(int i) {
        this.maximumSize = i;
        while (size() > i) {
            removeLRU();
        }
    }

    protected void processRemovedLRU(Object obj, Object obj2) {
    }

    @Override // org.apache.commons.collections.SequencedHashMap, java.util.Map
    public Object get(Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        Object objRemove = remove(obj);
        super.put(obj, objRemove);
        return objRemove;
    }

    @Override // org.apache.commons.collections.SequencedHashMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (size() >= this.maximumSize && !containsKey(obj)) {
            removeLRU();
        }
        return super.put(obj, obj2);
    }

    protected void removeLRU() {
        Object firstKey = getFirstKey();
        Object obj = super.get(firstKey);
        remove(firstKey);
        processRemovedLRU(firstKey, obj);
    }

    @Override // org.apache.commons.collections.SequencedHashMap, java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.maximumSize = objectInput.readInt();
        int i = objectInput.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            put(objectInput.readObject(), objectInput.readObject());
        }
    }

    @Override // org.apache.commons.collections.SequencedHashMap, java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.maximumSize);
        objectOutput.writeInt(size());
        for (Object obj : keySet()) {
            objectOutput.writeObject(obj);
            objectOutput.writeObject(super.get(obj));
        }
    }
}
