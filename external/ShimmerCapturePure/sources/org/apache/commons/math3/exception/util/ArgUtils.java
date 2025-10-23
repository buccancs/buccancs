package org.apache.commons.math3.exception.util;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ArgUtils {
    private ArgUtils() {
    }

    public static Object[] flatten(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj instanceof Object[]) {
                    for (Object obj2 : flatten((Object[]) obj)) {
                        arrayList.add(obj2);
                    }
                } else {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList.toArray();
    }
}
