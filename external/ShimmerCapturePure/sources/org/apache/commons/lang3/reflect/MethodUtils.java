package org.apache.commons.lang3.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public class MethodUtils {
    public static Object invokeMethod(Object obj, String str) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(obj, str, ArrayUtils.EMPTY_OBJECT_ARRAY, (Class<?>[]) null);
    }

    public static Object invokeMethod(Object obj, boolean z, String str) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(obj, z, str, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeMethod(obj, str, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static Object invokeMethod(Object obj, boolean z, String str, Object... objArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeMethod(obj, z, str, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static Object invokeMethod(Object obj, boolean z, String str, Object[] objArr, Class<?>[] clsArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String str2;
        Method matchingAccessibleMethod;
        Class<?>[] clsArrNullToEmpty = ArrayUtils.nullToEmpty(clsArr);
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method method = null;
        boolean zIsAccessible = false;
        try {
            if (z) {
                str2 = "No such method: ";
                matchingAccessibleMethod = getMatchingMethod(obj.getClass(), str, clsArrNullToEmpty);
                if (matchingAccessibleMethod != null && !(zIsAccessible = matchingAccessibleMethod.isAccessible())) {
                    matchingAccessibleMethod.setAccessible(true);
                }
            } else {
                str2 = "No such accessible method: ";
                matchingAccessibleMethod = getMatchingAccessibleMethod(obj.getClass(), str, clsArrNullToEmpty);
            }
            if (matchingAccessibleMethod == null) {
                throw new NoSuchMethodException(str2 + str + "() on object: " + obj.getClass().getName());
            }
            Object objInvoke = matchingAccessibleMethod.invoke(obj, toVarArgs(matchingAccessibleMethod, objArrNullToEmpty));
            if (matchingAccessibleMethod != null && z && matchingAccessibleMethod.isAccessible() != zIsAccessible) {
                matchingAccessibleMethod.setAccessible(zIsAccessible);
            }
            return objInvoke;
        } catch (Throwable th) {
            if (0 != 0 && z && method.isAccessible()) {
                method.setAccessible(false);
            }
            throw th;
        }
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(obj, false, str, objArr, clsArr);
    }

    public static Object invokeExactMethod(Object obj, String str) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeExactMethod(obj, str, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeExactMethod(Object obj, String str, Object... objArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeExactMethod(obj, str, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static Object invokeExactMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method accessibleMethod = getAccessibleMethod(obj.getClass(), str, ArrayUtils.nullToEmpty(clsArr));
        if (accessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + obj.getClass().getName());
        }
        return accessibleMethod.invoke(obj, objArrNullToEmpty);
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String str, Object[] objArr, Class<?>[] clsArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method accessibleMethod = getAccessibleMethod(cls, str, ArrayUtils.nullToEmpty(clsArr));
        if (accessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on class: " + cls.getName());
        }
        return accessibleMethod.invoke(null, objArrNullToEmpty);
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object... objArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeStaticMethod(cls, str, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object[] objArr, Class<?>[] clsArr) throws IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method matchingAccessibleMethod = getMatchingAccessibleMethod(cls, str, ArrayUtils.nullToEmpty(clsArr));
        if (matchingAccessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on class: " + cls.getName());
        }
        return matchingAccessibleMethod.invoke(null, toVarArgs(matchingAccessibleMethod, objArrNullToEmpty));
    }

    private static Object[] toVarArgs(Method method, Object[] objArr) {
        return method.isVarArgs() ? getVarArgs(objArr, method.getParameterTypes()) : objArr;
    }

    static Object[] getVarArgs(Object[] objArr, Class<?>[] clsArr) throws NegativeArraySizeException {
        if (objArr.length == clsArr.length && objArr[objArr.length - 1].getClass().equals(clsArr[clsArr.length - 1])) {
            return objArr;
        }
        Object[] objArr2 = new Object[clsArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, clsArr.length - 1);
        Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
        int length = (objArr.length - clsArr.length) + 1;
        Object objNewInstance = Array.newInstance(ClassUtils.primitiveToWrapper(componentType), length);
        System.arraycopy(objArr, clsArr.length - 1, objNewInstance, 0, length);
        if (componentType.isPrimitive()) {
            objNewInstance = ArrayUtils.toPrimitive(objNewInstance);
        }
        objArr2[clsArr.length - 1] = objNewInstance;
        return objArr2;
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String str, Object... objArr) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeExactStaticMethod(cls, str, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static Method getAccessibleMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return getAccessibleMethod(cls.getMethod(str, clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method getAccessibleMethod(Method method) {
        if (!MemberUtils.isAccessible(method)) {
            return null;
        }
        Class<?> declaringClass = method.getDeclaringClass();
        if (Modifier.isPublic(declaringClass.getModifiers())) {
            return method;
        }
        String name = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method accessibleMethodFromInterfaceNest = getAccessibleMethodFromInterfaceNest(declaringClass, name, parameterTypes);
        return accessibleMethodFromInterfaceNest == null ? getAccessibleMethodFromSuperclass(declaringClass, name, parameterTypes) : accessibleMethodFromInterfaceNest;
    }

    private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String str, Class<?>... clsArr) {
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (Modifier.isPublic(superclass.getModifiers())) {
                try {
                    return superclass.getMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (Modifier.isPublic(interfaces[i].getModifiers())) {
                    try {
                        return interfaces[i].getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException unused) {
                        Method accessibleMethodFromInterfaceNest = getAccessibleMethodFromInterfaceNest(interfaces[i], str, clsArr);
                        if (accessibleMethodFromInterfaceNest != null) {
                            return accessibleMethodFromInterfaceNest;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method getMatchingAccessibleMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Method accessibleMethod;
        try {
            Method method = cls.getMethod(str, clsArr);
            MemberUtils.setAccessibleWorkaround(method);
            return method;
        } catch (NoSuchMethodException unused) {
            Method method2 = null;
            for (Method method3 : cls.getMethods()) {
                if (method3.getName().equals(str) && MemberUtils.isMatchingMethod(method3, clsArr) && (accessibleMethod = getAccessibleMethod(method3)) != null && (method2 == null || MemberUtils.compareMethodFit(accessibleMethod, method2, clsArr) < 0)) {
                    method2 = accessibleMethod;
                }
            }
            if (method2 != null) {
                MemberUtils.setAccessibleWorkaround(method2);
            }
            return method2;
        }
    }

    public static Method getMatchingMethod(Class<?> cls, String str, Class<?>... clsArr) throws SecurityException {
        Validate.notNull(cls, "Null class not allowed.", new Object[0]);
        Validate.notEmpty(str, "Null or blank methodName not allowed.", new Object[0]);
        Method[] declaredMethods = cls.getDeclaredMethods();
        Iterator<Class<?>> it2 = ClassUtils.getAllSuperclasses(cls).iterator();
        while (it2.hasNext()) {
            declaredMethods = (Method[]) ArrayUtils.addAll(declaredMethods, it2.next().getDeclaredMethods());
        }
        Method method = null;
        for (Method method2 : declaredMethods) {
            if (str.equals(method2.getName()) && ArrayUtils.isEquals(clsArr, method2.getParameterTypes())) {
                return method2;
            }
            if (str.equals(method2.getName()) && ClassUtils.isAssignable(clsArr, method2.getParameterTypes(), true) && (method == null || distance(clsArr, method2.getParameterTypes()) < distance(clsArr, method.getParameterTypes()))) {
                method = method2;
            }
        }
        return method;
    }

    private static int distance(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (!ClassUtils.isAssignable(clsArr, clsArr2, true)) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (!clsArr[i2].equals(clsArr2[i2])) {
                i = (!ClassUtils.isAssignable(clsArr[i2], clsArr2[i2], true) || ClassUtils.isAssignable(clsArr[i2], clsArr2[i2], false)) ? i + 2 : i + 1;
            }
        }
        return i;
    }

    public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfaces) throws NoSuchMethodException, SecurityException {
        Validate.notNull(method);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Iterator<Class<?>> it2 = ClassUtils.hierarchy(declaringClass, interfaces).iterator();
        it2.next();
        while (it2.hasNext()) {
            Method matchingAccessibleMethod = getMatchingAccessibleMethod(it2.next(), method.getName(), parameterTypes);
            if (matchingAccessibleMethod != null) {
                if (Arrays.equals(matchingAccessibleMethod.getParameterTypes(), parameterTypes)) {
                    linkedHashSet.add(matchingAccessibleMethod);
                } else {
                    Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, matchingAccessibleMethod.getDeclaringClass());
                    int i = 0;
                    while (true) {
                        if (i < parameterTypes.length) {
                            if (!TypeUtils.equals(TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]), TypeUtils.unrollVariables(typeArguments, matchingAccessibleMethod.getGenericParameterTypes()[i]))) {
                                break;
                            }
                            i++;
                        } else {
                            linkedHashSet.add(matchingAccessibleMethod);
                            break;
                        }
                    }
                }
            }
        }
        return linkedHashSet;
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> cls2) throws SecurityException {
        List<Method> methodsListWithAnnotation = getMethodsListWithAnnotation(cls, cls2);
        return (Method[]) methodsListWithAnnotation.toArray(new Method[methodsListWithAnnotation.size()]);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> cls2) throws SecurityException {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(cls2 != null, "The annotation class must not be null", new Object[0]);
        Method[] methods = cls.getMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : methods) {
            if (method.getAnnotation(cls2) != null) {
                arrayList.add(method);
            }
        }
        return arrayList;
    }
}
