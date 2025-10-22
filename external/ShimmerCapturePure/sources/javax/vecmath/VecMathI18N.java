package javax.vecmath;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes4.dex */
class VecMathI18N {
    VecMathI18N() {
    }

    static String getString(String str) {
        try {
            return ResourceBundle.getBundle("javax.vecmath.ExceptionStrings").getString(str);
        } catch (MissingResourceException unused) {
            System.err.println(new StringBuffer("VecMathI18N: Error looking up: ").append(str).toString());
            return str;
        }
    }
}
