package org.apache.commons.math;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/FieldElement.class */
public interface FieldElement<T> {
    T add(T t);

    T subtract(T t);

    T multiply(T t);

    T divide(T t) throws ArithmeticException;

    Field<T> getField();
}
