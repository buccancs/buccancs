package org.apache.commons.collections.functors;

import org.apache.commons.collections.Predicate;

/* loaded from: classes5.dex */
public interface PredicateDecorator extends Predicate {
    Predicate[] getPredicates();
}
