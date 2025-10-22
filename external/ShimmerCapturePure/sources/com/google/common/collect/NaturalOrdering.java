package com.google.common.collect;

import com.google.common.base.Preconditions;

import java.io.Serializable;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final NaturalOrdering INSTANCE = new NaturalOrdering();
    private static final long serialVersionUID = 0;

    @NullableDecl
    private transient Ordering<Comparable> nullsFirst;

    @NullableDecl
    private transient Ordering<Comparable> nullsLast;

    private NaturalOrdering() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable comparable, Comparable comparable2) {
        Preconditions.checkNotNull(comparable);
        Preconditions.checkNotNull(comparable2);
        return comparable.compareTo(comparable2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsFirst() {
        Ordering<S> ordering = (Ordering<S>) this.nullsFirst;
        if (ordering != null) {
            return ordering;
        }
        Ordering<S> orderingNullsFirst = super.nullsFirst();
        this.nullsFirst = orderingNullsFirst;
        return orderingNullsFirst;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsLast() {
        Ordering<S> ordering = (Ordering<S>) this.nullsLast;
        if (ordering != null) {
            return ordering;
        }
        Ordering<S> orderingNullsLast = super.nullsLast();
        this.nullsLast = orderingNullsLast;
        return orderingNullsLast;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }
}
