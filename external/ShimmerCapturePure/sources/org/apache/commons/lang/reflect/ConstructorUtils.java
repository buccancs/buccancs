package org.apache.commons.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;

/* loaded from: classes5.dex */
public class ConstructorUtils {
    public static Object invokeConstructor(Class cls, Object obj) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        return invokeConstructor(cls, new Object[]{obj});
    }

    public static Object invokeConstructor(Class cls, Object[] objArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeConstructor(cls, objArr, clsArr);
    }

    public static Object invokeConstructor(Class cls, Object[] objArr, Class[] clsArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, InvocationTargetException {
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, clsArr);
        if (matchingAccessibleConstructor == null) {
            throw new NoSuchMethodException(new StringBuffer("No such accessible constructor on object: ").append(cls.getName()).toString());
        }
        return matchingAccessibleConstructor.newInstance(objArr);
    }

    public static Object invokeExactConstructor(Class cls, Object obj) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        return invokeExactConstructor(cls, new Object[]{obj});
    }

    public static Object invokeExactConstructor(Class cls, Object[] objArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeExactConstructor(cls, objArr, clsArr);
    }

    public static Object invokeExactConstructor(Class cls, Object[] objArr, Class[] clsArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Constructor accessibleConstructor = getAccessibleConstructor(cls, clsArr);
        if (accessibleConstructor == null) {
            throw new NoSuchMethodException(new StringBuffer("No such accessible constructor on object: ").append(cls.getName()).toString());
        }
        return accessibleConstructor.newInstance(objArr);
    }

    public static Constructor getAccessibleConstructor(Class cls, Class cls2) {
        return getAccessibleConstructor(cls, new Class[]{cls2});
    }

    public static Constructor getAccessibleConstructor(Class cls, Class[] clsArr) {
        try {
            return getAccessibleConstructor(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Constructor getAccessibleConstructor(Constructor constructor) {
        if (MemberUtils.isAccessible(constructor) && Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
            return constructor;
        }
        return null;
    }

    public static Constructor getMatchingAccessibleConstructor(Class cls, Class[] clsArr) throws NoSuchMethodException, SecurityException {
        Constructor accessibleConstructor;
        try {
            Constructor constructor = cls.getConstructor(clsArr);
            MemberUtils.setAccessibleWorkaround(constructor);
            return constructor;
        } catch (NoSuchMethodException unused) {
            Constructor<?>[] constructors = cls.getConstructors();
            Constructor constructor2 = null;
            for (int i = 0; i < constructors.length; i++) {
                if (ClassUtils.isAssignable(clsArr, (Class[]) constructors[i].getParameterTypes(), true) && (accessibleConstructor = getAccessibleConstructor(constructors[i])) != null) {
                    MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                    if (constructor2 == null || MemberUtils.compareParameterTypes(accessibleConstructor.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0) {
                        constructor2 = accessibleConstructor;
                    }
                }
            }
            return constructor2;
        }
    }
}
