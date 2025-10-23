package org.apache.commons.math.exception.util;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/util/ArgUtils.class */
public class ArgUtils {
    private ArgUtils() {
    }

    public static Object[] flatten(Object[] array) {
        List<Object> list = new ArrayList<>();
        if (array != null) {
            for (Object o : array) {
                if (o instanceof Object[]) {
                    Object[] arr$ = flatten((Object[]) o);
                    for (Object oR : arr$) {
                        list.add(oR);
                    }
                } else {
                    list.add(o);
                }
            }
        }
        return list.toArray();
    }
}
