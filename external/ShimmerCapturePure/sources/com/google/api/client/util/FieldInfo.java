package com.google.api.client.util;

import com.google.common.base.Ascii;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
    private final Field field;
    private final boolean isPrimitive;
    private final String name;
    private final Method[] setters;

    FieldInfo(Field field, String str) {
        this.field = field;
        this.name = str == null ? null : str.intern();
        this.isPrimitive = Data.isPrimitive(getType());
        this.setters = settersMethodForField(field);
    }

    public static FieldInfo of(Enum<?> r5) {
        try {
            FieldInfo fieldInfoOf = of(r5.getClass().getField(r5.name()));
            Preconditions.checkArgument(fieldInfoOf != null, "enum constant missing @Value or @NullValue annotation: %s", r5);
            return fieldInfoOf;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static FieldInfo of(Field field) {
        String strValue = null;
        if (field == null) {
            return null;
        }
        Map<Field, FieldInfo> map = CACHE;
        synchronized (map) {
            FieldInfo fieldInfo = map.get(field);
            boolean zIsEnumConstant = field.isEnumConstant();
            if (fieldInfo == null && (zIsEnumConstant || !Modifier.isStatic(field.getModifiers()))) {
                if (zIsEnumConstant) {
                    Value value = (Value) field.getAnnotation(Value.class);
                    if (value != null) {
                        strValue = value.value();
                    } else if (((NullValue) field.getAnnotation(NullValue.class)) == null) {
                        return null;
                    }
                } else {
                    Key key = (Key) field.getAnnotation(Key.class);
                    if (key == null) {
                        return null;
                    }
                    strValue = key.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(strValue)) {
                    strValue = field.getName();
                }
                fieldInfo = new FieldInfo(field, strValue);
                map.put(field, fieldInfo);
            }
            return fieldInfo;
        }
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field, Object obj, Object obj2) throws IllegalAccessException, IllegalArgumentException {
        if (Modifier.isFinal(field.getModifiers())) {
            Object fieldValue = getFieldValue(field, obj);
            if (obj2 == null) {
                if (fieldValue == null) {
                    return;
                }
            } else if (obj2.equals(fieldValue)) {
                return;
            }
            throw new IllegalArgumentException("expected final value <" + fieldValue + "> but was <" + obj2 + "> on " + field.getName() + " field in " + obj.getClass().getName());
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (SecurityException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    private Method[] settersMethodForField(Field field) throws SecurityException {
        ArrayList arrayList = new ArrayList();
        for (Method method : field.getDeclaringClass().getDeclaredMethods()) {
            if (Ascii.toLowerCase(method.getName()).equals("set" + Ascii.toLowerCase(field.getName())) && method.getParameterTypes().length == 1) {
                arrayList.add(method);
            }
        }
        return (Method[]) arrayList.toArray(new Method[0]);
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public Object getValue(Object obj) {
        return getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object obj2) {
        Method[] methodArr = this.setters;
        if (methodArr.length > 0) {
            for (Method method : methodArr) {
                if (obj2 == null || method.getParameterTypes()[0].isAssignableFrom(obj2.getClass())) {
                    try {
                        method.invoke(obj, obj2);
                        return;
                    } catch (IllegalAccessException | InvocationTargetException unused) {
                        continue;
                    }
                }
            }
        }
        setFieldValue(this.field, obj, obj2);
    }

    public ClassInfo getClassInfo() {
        return ClassInfo.of(this.field.getDeclaringClass());
    }

    public <T extends Enum<T>> T enumValue() {
        return (T) Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }
}
