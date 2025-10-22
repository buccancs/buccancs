package org.apache.commons.collections.buffer;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.functors.InstanceofPredicate;

/* loaded from: classes5.dex */
public class TypedBuffer {
    protected TypedBuffer() {
    }

    public static Buffer decorate(Buffer buffer, Class cls) {
        return new PredicatedBuffer(buffer, InstanceofPredicate.getInstance(cls));
    }
}
