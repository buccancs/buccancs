package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.FunctorException;
import org.apache.commons.collections.Predicate;

/* loaded from: classes5.dex */
public final class ExceptionPredicate implements Predicate, Serializable {
    public static final Predicate INSTANCE = new ExceptionPredicate();
    private static final long serialVersionUID = 7179106032121985545L;

    private ExceptionPredicate() {
    }

    public static Predicate getInstance() {
        return INSTANCE;
    }

    @Override // org.apache.commons.collections.Predicate
    public boolean evaluate(Object obj) {
        throw new FunctorException("ExceptionPredicate invoked");
    }
}
