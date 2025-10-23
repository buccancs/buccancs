package org.junit.internal.matchers;

import java.lang.reflect.Method;

import org.hamcrest.BaseMatcher;
import org.junit.internal.MethodSorter;

@Deprecated
/* loaded from: classes2.dex */
public abstract class TypeSafeMatcher<T> extends BaseMatcher<T> {
    private Class<?> expectedType;

    protected TypeSafeMatcher() {
        this.expectedType = findExpectedType(getClass());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected TypeSafeMatcher(Class<T> cls) {
        this.expectedType = cls;
    }

    private static Class<?> findExpectedType(Class<?> cls) throws SecurityException {
        while (cls != Object.class) {
            for (Method method : MethodSorter.getDeclaredMethods(cls)) {
                if (isMatchesSafelyMethod(method)) {
                    return method.getParameterTypes()[0];
                }
            }
            cls = cls.getSuperclass();
        }
        throw new Error("Cannot determine correct type for matchesSafely() method.");
    }

    private static boolean isMatchesSafelyMethod(Method method) {
        return method.getName().equals("matchesSafely") && method.getParameterTypes().length == 1 && !method.isSynthetic();
    }

    public abstract boolean matchesSafely(T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.Matcher
    public final boolean matches(Object obj) {
        return obj != 0 && this.expectedType.isInstance(obj) && matchesSafely(obj);
    }
}
