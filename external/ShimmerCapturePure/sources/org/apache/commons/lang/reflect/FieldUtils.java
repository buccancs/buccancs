package org.apache.commons.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;

import org.apache.commons.lang.ClassUtils;

/* loaded from: classes5.dex */
public class FieldUtils {
    public static Field getField(Class cls, String str) throws NoSuchFieldException, SecurityException {
        Field field = getField(cls, str, false);
        MemberUtils.setAccessibleWorkaround(field);
        return field;
    }

    public static Field getField(Class cls, String str, boolean z) throws NoSuchFieldException {
        Field field;
        Field declaredField;
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("The field name must not be null");
        }
        for (Class superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                declaredField = superclass.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
            }
            if (!Modifier.isPublic(declaredField.getModifiers())) {
                if (!z) {
                    continue;
                } else {
                    declaredField.setAccessible(true);
                }
            }
            return declaredField;
        }
        Iterator it2 = ClassUtils.getAllInterfaces(cls).iterator();
        Field field2 = null;
        while (it2.hasNext()) {
            try {
                field = ((Class) it2.next()).getField(str);
            } catch (NoSuchFieldException unused2) {
            }
            if (field2 != null) {
                throw new IllegalArgumentException(new StringBuffer().append("Reference to field ").append(str).append(" is ambiguous relative to ").append(cls).append("; a matching field exists on two or more implemented interfaces.").toString());
            }
            field2 = field;
        }
        return field2;
    }

    public static Field getDeclaredField(Class cls, String str) {
        return getDeclaredField(cls, str, false);
    }

    public static Field getDeclaredField(Class cls, String str, boolean z) throws NoSuchFieldException {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("The field name must not be null");
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!MemberUtils.isAccessible(declaredField)) {
                if (!z) {
                    return null;
                }
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public static Object readStaticField(Field field) throws IllegalAccessException {
        return readStaticField(field, false);
    }

    public static Object readStaticField(Field field, boolean z) throws IllegalAccessException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!Modifier.isStatic(field.getModifiers())) {
            throw new IllegalArgumentException(new StringBuffer("The field '").append(field.getName()).append("' is not static").toString());
        }
        return readField(field, (Object) null, z);
    }

    public static Object readStaticField(Class cls, String str) throws IllegalAccessException {
        return readStaticField(cls, str, false);
    }

    public static Object readStaticField(Class cls, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(cls, str, z);
        if (field == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate field ").append(str).append(" on ").append(cls).toString());
        }
        return readStaticField(field, false);
    }

    public static Object readDeclaredStaticField(Class cls, String str) throws IllegalAccessException {
        return readDeclaredStaticField(cls, str, false);
    }

    public static Object readDeclaredStaticField(Class cls, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate declared field ").append(cls.getName()).append(".").append(str).toString());
        }
        return readStaticField(declaredField, false);
    }

    public static Object readField(Field field, Object obj) throws IllegalAccessException {
        return readField(field, obj, false);
    }

    public static Object readField(Field field, Object obj, boolean z) throws IllegalAccessException, SecurityException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (z && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        return field.get(obj);
    }

    public static Object readField(Object obj, String str) throws IllegalAccessException {
        return readField(obj, str, false);
    }

    public static Object readField(Object obj, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z);
        if (field == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate field ").append(str).append(" on ").append(cls).toString());
        }
        return readField(field, obj);
    }

    public static Object readDeclaredField(Object obj, String str) throws IllegalAccessException {
        return readDeclaredField(obj, str, false);
    }

    public static Object readDeclaredField(Object obj, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate declared field ").append(cls.getName()).append(".").append(str).toString());
        }
        return readField(declaredField, obj);
    }

    public static void writeStaticField(Field field, Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeStaticField(field, obj, false);
    }

    public static void writeStaticField(Field field, Object obj, boolean z) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!Modifier.isStatic(field.getModifiers())) {
            throw new IllegalArgumentException(new StringBuffer("The field '").append(field.getName()).append("' is not static").toString());
        }
        writeField(field, (Object) null, obj, z);
    }

    public static void writeStaticField(Class cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeStaticField(cls, str, obj, false);
    }

    public static void writeStaticField(Class cls, String str, Object obj, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field field = getField(cls, str, z);
        if (field == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate field ").append(str).append(" on ").append(cls).toString());
        }
        writeStaticField(field, obj);
    }

    public static void writeDeclaredStaticField(Class cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredStaticField(cls, str, obj, false);
    }

    public static void writeDeclaredStaticField(Class cls, String str, Object obj, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate declared field ").append(cls.getName()).append(".").append(str).toString());
        }
        writeField(declaredField, (Object) null, obj);
    }

    public static void writeField(Field field, Object obj, Object obj2) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeField(field, obj, obj2, false);
    }

    public static void writeField(Field field, Object obj, Object obj2, boolean z) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (z && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        field.set(obj, obj2);
    }

    public static void writeField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeField(obj, str, obj2, false);
    }

    public static void writeField(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z);
        if (field == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate declared field ").append(cls.getName()).append(".").append(str).toString());
        }
        writeField(field, obj, obj2);
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredField(obj, str, obj2, false);
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            throw new IllegalArgumentException(new StringBuffer("Cannot locate declared field ").append(cls.getName()).append(".").append(str).toString());
        }
        writeField(declaredField, obj, obj2);
    }
}
