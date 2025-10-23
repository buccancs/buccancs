package org.apache.commons.lang;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.text.StrBuilder;

/* loaded from: classes5.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
    private static final Map abbreviationMap;
    private static final Map primitiveWrapperMap;
    private static final Map reverseAbbreviationMap;
    private static final Map wrapperPrimitiveMap;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$org$apache$commons$lang$ClassUtils;

    static {
        HashMap map = new HashMap();
        primitiveWrapperMap = map;
        Class cls = Boolean.TYPE;
        Class clsClass$ = class$java$lang$Boolean;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.Boolean");
            class$java$lang$Boolean = clsClass$;
        }
        map.put(cls, clsClass$);
        Class cls2 = Byte.TYPE;
        Class clsClass$2 = class$java$lang$Byte;
        if (clsClass$2 == null) {
            clsClass$2 = class$("java.lang.Byte");
            class$java$lang$Byte = clsClass$2;
        }
        map.put(cls2, clsClass$2);
        Class cls3 = Character.TYPE;
        Class clsClass$3 = class$java$lang$Character;
        if (clsClass$3 == null) {
            clsClass$3 = class$("java.lang.Character");
            class$java$lang$Character = clsClass$3;
        }
        map.put(cls3, clsClass$3);
        Class cls4 = Short.TYPE;
        Class clsClass$4 = class$java$lang$Short;
        if (clsClass$4 == null) {
            clsClass$4 = class$("java.lang.Short");
            class$java$lang$Short = clsClass$4;
        }
        map.put(cls4, clsClass$4);
        Class cls5 = Integer.TYPE;
        Class clsClass$5 = class$java$lang$Integer;
        if (clsClass$5 == null) {
            clsClass$5 = class$("java.lang.Integer");
            class$java$lang$Integer = clsClass$5;
        }
        map.put(cls5, clsClass$5);
        Class cls6 = Long.TYPE;
        Class clsClass$6 = class$java$lang$Long;
        if (clsClass$6 == null) {
            clsClass$6 = class$("java.lang.Long");
            class$java$lang$Long = clsClass$6;
        }
        map.put(cls6, clsClass$6);
        Class cls7 = Double.TYPE;
        Class clsClass$7 = class$java$lang$Double;
        if (clsClass$7 == null) {
            clsClass$7 = class$("java.lang.Double");
            class$java$lang$Double = clsClass$7;
        }
        map.put(cls7, clsClass$7);
        Class cls8 = Float.TYPE;
        Class clsClass$8 = class$java$lang$Float;
        if (clsClass$8 == null) {
            clsClass$8 = class$("java.lang.Float");
            class$java$lang$Float = clsClass$8;
        }
        map.put(cls8, clsClass$8);
        map.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        for (Class cls9 : map.keySet()) {
            Class cls10 = (Class) primitiveWrapperMap.get(cls9);
            if (!cls9.equals(cls10)) {
                wrapperPrimitiveMap.put(cls10, cls9);
            }
        }
        abbreviationMap = new HashMap();
        reverseAbbreviationMap = new HashMap();
        addAbbreviation("int", "I");
        addAbbreviation(TypedValues.Custom.S_BOOLEAN, "Z");
        addAbbreviation(TypedValues.Custom.S_FLOAT, "F");
        addAbbreviation("long", "J");
        addAbbreviation("short", "S");
        addAbbreviation("byte", "B");
        addAbbreviation("double", "D");
        addAbbreviation("char", "C");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static void addAbbreviation(String str, String str2) {
        abbreviationMap.put(str, str2);
        reverseAbbreviationMap.put(str2, str);
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static String getShortClassName(Class cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getShortClassName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StrBuilder strBuilder = new StrBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                strBuilder.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
        }
        Map map = reverseAbbreviationMap;
        if (map.containsKey(str)) {
            str = (String) map.get(str);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iIndexOf = str.indexOf(36, iLastIndexOf != -1 ? iLastIndexOf + 1 : 0);
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.replace('$', '.');
        }
        return new StringBuffer().append(strSubstring).append(strBuilder).toString();
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    public static String getPackageName(Class cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getPackageName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf);
    }

    public static List getAllSuperclasses(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    public static List getAllInterfaces(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        getAllInterfaces(cls, arrayList);
        return arrayList;
    }

    private static void getAllInterfaces(Class cls, List list) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (!list.contains(interfaces[i])) {
                    list.add(interfaces[i]);
                    getAllInterfaces(interfaces[i], list);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List convertClassNamesToClasses(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            try {
                arrayList.add(Class.forName((String) it2.next()));
            } catch (Exception unused) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List convertClassesToClassNames(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Class cls = (Class) it2.next();
            if (cls == null) {
                arrayList.add(null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static boolean isAssignable(Class[] clsArr, Class[] clsArr2) {
        return isAssignable(clsArr, clsArr2, false);
    }

    public static boolean isAssignable(Class[] clsArr, Class[] clsArr2, boolean z) {
        if (!ArrayUtils.isSameLength(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (clsArr2 == null) {
            clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!isAssignable(clsArr[i], clsArr2[i], z)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAssignable(Class cls, Class cls2) {
        return isAssignable(cls, cls2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isAssignable(Class cls, Class cls2, boolean z) {
        if (cls2 == 0) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(cls2);
            }
            if (Character.TYPE.equals(cls)) {
                return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Short.TYPE.equals(cls)) {
                return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class primitiveToWrapper(Class cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : (Class) primitiveWrapperMap.get(cls);
    }

    public static Class[] primitivesToWrappers(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = primitiveToWrapper(clsArr[i]);
        }
        return clsArr2;
    }

    public static Class wrapperToPrimitive(Class cls) {
        return (Class) wrapperPrimitiveMap.get(cls);
    }

    public static Class[] wrappersToPrimitives(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = wrapperToPrimitive(clsArr[i]);
        }
        return clsArr2;
    }

    public static boolean isInnerClass(Class cls) {
        return cls != null && cls.getName().indexOf(36) >= 0;
    }

    public static Class getClass(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            Map map = abbreviationMap;
            if (map.containsKey(str)) {
                return Class.forName(new StringBuffer("[").append(map.get(str)).toString(), z, classLoader).getComponentType();
            }
            return Class.forName(toCanonicalName(str), z, classLoader);
        } catch (ClassNotFoundException e) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                try {
                    return getClass(classLoader, new StringBuffer().append(str.substring(0, iLastIndexOf)).append('$').append(str.substring(iLastIndexOf + 1)).toString(), z);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static Class getClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return getClass(classLoader, str, true);
    }

    public static Class getClass(String str) throws ClassNotFoundException {
        return getClass(str, true);
    }

    public static Class getClass(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            Class clsClass$ = class$org$apache$commons$lang$ClassUtils;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.commons.lang.ClassUtils");
                class$org$apache$commons$lang$ClassUtils = clsClass$;
            }
            contextClassLoader = clsClass$.getClassLoader();
        }
        return getClass(contextClassLoader, str, z);
    }

    public static Method getPublicMethod(Class cls, String str, Class[] clsArr) throws NoSuchMethodException, SecurityException {
        Method method = cls.getMethod(str, clsArr);
        if (Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.addAll(getAllInterfaces(cls));
        arrayList.addAll(getAllSuperclasses(cls));
        for (Class cls2 : arrayList) {
            if (Modifier.isPublic(cls2.getModifiers())) {
                try {
                    Method method2 = cls2.getMethod(str, clsArr);
                    if (Modifier.isPublic(method2.getDeclaringClass().getModifiers())) {
                        return method2;
                    }
                } catch (NoSuchMethodException unused) {
                }
            }
        }
        throw new NoSuchMethodException(new StringBuffer("Can't find a public method for ").append(str).append(org.apache.commons.lang3.StringUtils.SPACE).append(ArrayUtils.toString(clsArr)).toString());
    }

    private static String toCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            throw new NullArgumentException("className");
        }
        if (!strDeleteWhitespace.endsWith("[]")) {
            return strDeleteWhitespace;
        }
        StrBuilder strBuilder = new StrBuilder();
        while (strDeleteWhitespace.endsWith("[]")) {
            strDeleteWhitespace = strDeleteWhitespace.substring(0, strDeleteWhitespace.length() - 2);
            strBuilder.append("[");
        }
        String str2 = (String) abbreviationMap.get(strDeleteWhitespace);
        if (str2 != null) {
            strBuilder.append(str2);
        } else {
            strBuilder.append("L").append(strDeleteWhitespace).append(";");
        }
        return strBuilder.toString();
    }

    public static Class[] toClass(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? null : obj.getClass();
        }
        return clsArr;
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getName());
    }

    public static String getShortCanonicalName(Class cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getName());
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageCanonicalName(Class cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    private static String getCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            return null;
        }
        int i = 0;
        while (strDeleteWhitespace.startsWith("[")) {
            i++;
            strDeleteWhitespace = strDeleteWhitespace.substring(1);
        }
        if (i < 1) {
            return strDeleteWhitespace;
        }
        if (strDeleteWhitespace.startsWith("L")) {
            strDeleteWhitespace = strDeleteWhitespace.substring(1, strDeleteWhitespace.endsWith(";") ? strDeleteWhitespace.length() - 1 : strDeleteWhitespace.length());
        } else if (strDeleteWhitespace.length() > 0) {
            strDeleteWhitespace = (String) reverseAbbreviationMap.get(strDeleteWhitespace.substring(0, 1));
        }
        StrBuilder strBuilder = new StrBuilder(strDeleteWhitespace);
        for (int i2 = 0; i2 < i; i2++) {
            strBuilder.append("[]");
        }
        return strBuilder.toString();
    }
}
