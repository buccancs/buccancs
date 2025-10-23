package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class ClosureTransformer implements Transformer, Serializable {
    private static final long serialVersionUID = 478466901448617286L;
    private final Closure iClosure;

    public ClosureTransformer(Closure closure) {
        this.iClosure = closure;
    }

    public static Transformer getInstance(Closure closure) {
        if (closure == null) {
            throw new IllegalArgumentException("Closure must not be null");
        }
        return new ClosureTransformer(closure);
    }

    public Closure getClosure() {
        return this.iClosure;
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        this.iClosure.execute(obj);
        return obj;
    }
}
