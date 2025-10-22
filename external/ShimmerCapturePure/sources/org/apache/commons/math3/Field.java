package org.apache.commons.math3;

/* loaded from: classes5.dex */
public interface Field<T> {
    T getOne();

    Class<? extends FieldElement<T>> getRuntimeClass();

    T getZero();
}
