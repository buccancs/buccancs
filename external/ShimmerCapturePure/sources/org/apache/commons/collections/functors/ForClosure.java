package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Closure;

/* loaded from: classes5.dex */
public class ForClosure implements Closure, Serializable {
    private static final long serialVersionUID = -1190120533393621674L;
    private final Closure iClosure;
    private final int iCount;

    public ForClosure(int i, Closure closure) {
        this.iCount = i;
        this.iClosure = closure;
    }

    public static Closure getInstance(int i, Closure closure) {
        if (i <= 0 || closure == null) {
            return NOPClosure.INSTANCE;
        }
        return i == 1 ? closure : new ForClosure(i, closure);
    }

    public Closure getClosure() {
        return this.iClosure;
    }

    public int getCount() {
        return this.iCount;
    }

    @Override // org.apache.commons.collections.Closure
    public void execute(Object obj) {
        for (int i = 0; i < this.iCount; i++) {
            this.iClosure.execute(obj);
        }
    }
}
