package org.apache.commons.lang.exception;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

/* loaded from: classes5.dex */
public class ExceptionUtils {
    static final String WRAPPED_MARKER = " [wrapped] ";
    private static final Method THROWABLE_CAUSE_METHOD;
    private static final Method THROWABLE_INITCAUSE_METHOD;
    private static final Object CAUSE_METHOD_NAMES_LOCK = new Object();
    static /* synthetic */ Class class$java$lang$Throwable;
    private static String[] CAUSE_METHOD_NAMES = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};

    static {
        Method method;
        Method method2 = null;
        try {
            Class clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            method = clsClass$.getMethod("getCause", null);
        } catch (Exception unused) {
            method = null;
        }
        THROWABLE_CAUSE_METHOD = method;
        try {
            Class clsClass$2 = class$java$lang$Throwable;
            if (clsClass$2 == null) {
                clsClass$2 = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$2;
            }
            Class<?>[] clsArr = new Class[1];
            Class<?> clsClass$3 = class$java$lang$Throwable;
            if (clsClass$3 == null) {
                clsClass$3 = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$3;
            }
            clsArr[0] = clsClass$3;
            method2 = clsClass$2.getMethod("initCause", clsArr);
        } catch (Exception unused2) {
        }
        THROWABLE_INITCAUSE_METHOD = method2;
    }

    public static boolean isThrowableNested() {
        return THROWABLE_CAUSE_METHOD != null;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void addCauseMethodName(String str) {
        if (!StringUtils.isNotEmpty(str) || isCauseMethodName(str)) {
            return;
        }
        ArrayList causeMethodNameList = getCauseMethodNameList();
        if (causeMethodNameList.add(str)) {
            synchronized (CAUSE_METHOD_NAMES_LOCK) {
                CAUSE_METHOD_NAMES = toArray(causeMethodNameList);
            }
        }
    }

    public static void removeCauseMethodName(String str) {
        if (StringUtils.isNotEmpty(str)) {
            ArrayList causeMethodNameList = getCauseMethodNameList();
            if (causeMethodNameList.remove(str)) {
                synchronized (CAUSE_METHOD_NAMES_LOCK) {
                    CAUSE_METHOD_NAMES = toArray(causeMethodNameList);
                }
            }
        }
    }

    public static boolean setCause(Throwable th, Throwable th2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        boolean z;
        if (th == null) {
            throw new NullArgumentException(TypedValues.AttributesType.S_TARGET);
        }
        Object[] objArr = {th2};
        Method method = THROWABLE_INITCAUSE_METHOD;
        if (method != null) {
            try {
                method.invoke(th, objArr);
                z = true;
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        } else {
            z = false;
        }
        try {
            Class<?> cls = th.getClass();
            Class<?>[] clsArr = new Class[1];
            Class<?> clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            clsArr[0] = clsClass$;
            cls.getMethod("setCause", clsArr).invoke(th, objArr);
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            return z;
        }
    }

    private static String[] toArray(List list) {
        return (String[]) list.toArray(new String[list.size()]);
    }

    private static ArrayList getCauseMethodNameList() {
        ArrayList arrayList;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            arrayList = new ArrayList(Arrays.asList(CAUSE_METHOD_NAMES));
        }
        return arrayList;
    }

    public static boolean isCauseMethodName(String str) {
        boolean z;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            z = ArrayUtils.indexOf(CAUSE_METHOD_NAMES, str) >= 0;
        }
        return z;
    }

    public static Throwable getCause(Throwable th) {
        Throwable cause;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            cause = getCause(th, CAUSE_METHOD_NAMES);
        }
        return cause;
    }

    public static Throwable getCause(Throwable th, String[] strArr) {
        String str;
        if (th == null) {
            return null;
        }
        Throwable causeUsingWellKnownTypes = getCauseUsingWellKnownTypes(th);
        if (causeUsingWellKnownTypes != null) {
            return causeUsingWellKnownTypes;
        }
        if (strArr == null) {
            synchronized (CAUSE_METHOD_NAMES_LOCK) {
                strArr = CAUSE_METHOD_NAMES;
            }
        }
        for (int i = 0; i < strArr.length && ((str = strArr[i]) == null || (causeUsingWellKnownTypes = getCauseUsingMethodName(th, str)) == null); i++) {
        }
        return causeUsingWellKnownTypes == null ? getCauseUsingFieldName(th, "detail") : causeUsingWellKnownTypes;
    }

    public static Throwable getRootCause(Throwable th) {
        List throwableList = getThrowableList(th);
        if (throwableList.size() < 2) {
            return null;
        }
        return (Throwable) throwableList.get(throwableList.size() - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Throwable getCauseUsingWellKnownTypes(Throwable th) {
        if (th instanceof Nestable) {
            return ((Nestable) th).getCause();
        }
        if (th instanceof SQLException) {
            return ((SQLException) th).getNextException();
        }
        if (th instanceof InvocationTargetException) {
            return ((InvocationTargetException) th).getTargetException();
        }
        return null;
    }

    private static Throwable getCauseUsingMethodName(Throwable th, String str) throws NoSuchMethodException, SecurityException {
        Method method;
        try {
            method = th.getClass().getMethod(str, null);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null) {
            Class clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            if (clsClass$.isAssignableFrom(method.getReturnType())) {
                try {
                    return (Throwable) method.invoke(th, ArrayUtils.EMPTY_OBJECT_ARRAY);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
                }
            }
        }
        return null;
    }

    private static Throwable getCauseUsingFieldName(Throwable th, String str) throws NoSuchFieldException {
        Field field;
        try {
            field = th.getClass().getField(str);
        } catch (NoSuchFieldException | SecurityException unused) {
            field = null;
        }
        if (field != null) {
            Class clsClass$ = class$java$lang$Throwable;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = clsClass$;
            }
            if (clsClass$.isAssignableFrom(field.getType())) {
                try {
                    return (Throwable) field.get(th);
                } catch (IllegalAccessException | IllegalArgumentException unused2) {
                }
            }
        }
        return null;
    }

    public static boolean isNestedThrowable(Throwable th) {
        if (th == null) {
            return false;
        }
        if ((th instanceof Nestable) || (th instanceof SQLException) || (th instanceof InvocationTargetException) || isThrowableNested()) {
            return true;
        }
        Class<?> cls = th.getClass();
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            int length = CAUSE_METHOD_NAMES.length;
            for (int i = 0; i < length; i++) {
                try {
                    Method method = cls.getMethod(CAUSE_METHOD_NAMES[i], null);
                    if (method == null) {
                        continue;
                    } else {
                        Class clsClass$ = class$java$lang$Throwable;
                        if (clsClass$ == null) {
                            clsClass$ = class$("java.lang.Throwable");
                            class$java$lang$Throwable = clsClass$;
                        }
                        if (clsClass$.isAssignableFrom(method.getReturnType())) {
                            return true;
                        }
                    }
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            return cls.getField("detail") != null;
        }
    }

    public static int getThrowableCount(Throwable th) {
        return getThrowableList(th).size();
    }

    public static Throwable[] getThrowables(Throwable th) {
        List throwableList = getThrowableList(th);
        return (Throwable[]) throwableList.toArray(new Throwable[throwableList.size()]);
    }

    public static List getThrowableList(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = getCause(th);
        }
        return arrayList;
    }

    public static int indexOfThrowable(Throwable th, Class cls) {
        return indexOf(th, cls, 0, false);
    }

    public static int indexOfThrowable(Throwable th, Class cls, int i) {
        return indexOf(th, cls, i, false);
    }

    public static int indexOfType(Throwable th, Class cls) {
        return indexOf(th, cls, 0, true);
    }

    public static int indexOfType(Throwable th, Class cls, int i) {
        return indexOf(th, cls, i, true);
    }

    private static int indexOf(Throwable th, Class cls, int i, boolean z) {
        if (th != null && cls != null) {
            if (i < 0) {
                i = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i >= throwables.length) {
                return -1;
            }
            if (z) {
                while (i < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < throwables.length) {
                    if (cls.equals(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    public static void printRootCauseStackTrace(Throwable th) {
        printRootCauseStackTrace(th, System.err);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintStream printStream) {
        if (th == null) {
            return;
        }
        if (printStream == null) {
            throw new IllegalArgumentException("The PrintStream must not be null");
        }
        for (String str : getRootCauseStackTrace(th)) {
            printStream.println(str);
        }
        printStream.flush();
    }

    public static void printRootCauseStackTrace(Throwable th, PrintWriter printWriter) {
        if (th == null) {
            return;
        }
        if (printWriter == null) {
            throw new IllegalArgumentException("The PrintWriter must not be null");
        }
        for (String str : getRootCauseStackTrace(th)) {
            printWriter.println(str);
        }
        printWriter.flush();
    }

    public static String[] getRootCauseStackTrace(Throwable th) {
        List stackFrameList;
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        Throwable[] throwables = getThrowables(th);
        int length = throwables.length;
        ArrayList arrayList = new ArrayList();
        int i = length - 1;
        List stackFrameList2 = getStackFrameList(throwables[i]);
        while (true) {
            int i2 = length - 1;
            if (i2 >= 0) {
                if (i2 != 0) {
                    stackFrameList = getStackFrameList(throwables[length - 2]);
                    removeCommonFrames(stackFrameList2, stackFrameList);
                } else {
                    stackFrameList = stackFrameList2;
                }
                if (i2 == i) {
                    arrayList.add(throwables[i2].toString());
                } else {
                    arrayList.add(new StringBuffer(WRAPPED_MARKER).append(throwables[i2].toString()).toString());
                }
                for (int i3 = 0; i3 < stackFrameList2.size(); i3++) {
                    arrayList.add(stackFrameList2.get(i3));
                }
                stackFrameList2 = stackFrameList;
                length = i2;
            } else {
                return (String[]) arrayList.toArray(new String[0]);
            }
        }
    }

    public static void removeCommonFrames(List list, List list2) {
        if (list == null || list2 == null) {
            throw new IllegalArgumentException("The List must not be null");
        }
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (((String) list.get(size)).equals((String) list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }

    public static String getFullStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
        Throwable[] throwables = getThrowables(th);
        for (int i = 0; i < throwables.length; i++) {
            throwables[i].printStackTrace(printWriter);
            if (isNestedThrowable(throwables[i])) {
                break;
            }
        }
        return stringWriter.getBuffer().toString();
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static String[] getStackFrames(Throwable th) {
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return getStackFrames(getStackTrace(th));
    }

    static String[] getStackFrames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return toArray(arrayList);
    }

    static List getStackFrameList(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(getStackTrace(th), SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf("at");
            if (iIndexOf != -1 && strNextToken.substring(0, iIndexOf).trim().length() == 0) {
                arrayList.add(strNextToken);
                z = true;
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        return new StringBuffer().append(ClassUtils.getShortClassName(th, null)).append(": ").append(StringUtils.defaultString(th.getMessage())).toString();
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause != null) {
            th = rootCause;
        }
        return getMessage(th);
    }
}
