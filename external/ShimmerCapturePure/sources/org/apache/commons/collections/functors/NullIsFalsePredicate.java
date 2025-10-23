package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

/* loaded from: classes5.dex */
public final class NullIsFalsePredicate implements Predicate, PredicateDecorator, Serializable {
    private static final long serialVersionUID = -2997501534564735525L;
    private final Predicate iPredicate;

    public NullIsFalsePredicate(Predicate predicate) {
        this.iPredicate = predicate;
    }

    public static Predicate getInstance(Predicate predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate must not be null");
        }
        return new NullIsFalsePredicate(predicate);
    }

    @Override // org.apache.commons.collections.functors.PredicateDecorator
    public Predicate[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }

    @Override // org.apache.commons.collections.Predicate
    public boolean evaluate(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.iPredicate.evaluate(obj);
    }
}
