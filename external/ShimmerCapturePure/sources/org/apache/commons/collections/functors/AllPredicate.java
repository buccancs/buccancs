package org.apache.commons.collections.functors;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.collections.Predicate;

/* loaded from: classes5.dex */
public final class AllPredicate implements Predicate, PredicateDecorator, Serializable {
    private static final long serialVersionUID = -3094696765038308799L;
    private final Predicate[] iPredicates;

    public AllPredicate(Predicate[] predicateArr) {
        this.iPredicates = predicateArr;
    }

    public static Predicate getInstance(Predicate[] predicateArr) {
        FunctorUtils.validate(predicateArr);
        if (predicateArr.length == 0) {
            return TruePredicate.INSTANCE;
        }
        if (predicateArr.length == 1) {
            return predicateArr[0];
        }
        return new AllPredicate(FunctorUtils.copy(predicateArr));
    }

    public static Predicate getInstance(Collection collection) {
        Predicate[] predicateArrValidate = FunctorUtils.validate(collection);
        if (predicateArrValidate.length == 0) {
            return TruePredicate.INSTANCE;
        }
        if (predicateArrValidate.length == 1) {
            return predicateArrValidate[0];
        }
        return new AllPredicate(predicateArrValidate);
    }

    @Override // org.apache.commons.collections.functors.PredicateDecorator
    public Predicate[] getPredicates() {
        return this.iPredicates;
    }

    @Override // org.apache.commons.collections.Predicate
    public boolean evaluate(Object obj) {
        int i = 0;
        while (true) {
            Predicate[] predicateArr = this.iPredicates;
            if (i >= predicateArr.length) {
                return true;
            }
            if (!predicateArr[i].evaluate(obj)) {
                return false;
            }
            i++;
        }
    }
}
