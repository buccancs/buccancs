package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class PredicateTransformer implements Transformer, Serializable {
    private static final long serialVersionUID = 5278818408044349346L;
    private final Predicate iPredicate;

    public PredicateTransformer(Predicate predicate) {
        this.iPredicate = predicate;
    }

    public static Transformer getInstance(Predicate predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate must not be null");
        }
        return new PredicateTransformer(predicate);
    }

    public Predicate getPredicate() {
        return this.iPredicate;
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        return this.iPredicate.evaluate(obj) ? Boolean.TRUE : Boolean.FALSE;
    }
}
