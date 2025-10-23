package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

/* loaded from: classes5.dex */
public final class IdentityPredicate implements Predicate, Serializable {
    private static final long serialVersionUID = -89901658494523293L;
    private final Object iValue;

    public IdentityPredicate(Object obj) {
        this.iValue = obj;
    }

    public static Predicate getInstance(Object obj) {
        if (obj == null) {
            return NullPredicate.INSTANCE;
        }
        return new IdentityPredicate(obj);
    }

    @Override // org.apache.commons.collections.Predicate
    public boolean evaluate(Object obj) {
        return this.iValue == obj;
    }

    public Object getValue() {
        return this.iValue;
    }
}
