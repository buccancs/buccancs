package androidx.core.telephony;

import android.os.Build;
import android.telephony.SubscriptionManager;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class SubscriptionManagerCompat {
    private static Method sGetSlotIndexMethod;

    private SubscriptionManagerCompat() {
    }

    public static int getSlotIndex(int i) {
        if (i == -1) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getSlotIndex(i);
        }
        try {
            if (sGetSlotIndexMethod == null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    sGetSlotIndexMethod = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2().getDeclaredMethod("getSlotIndex", Integer.TYPE);
                } else {
                    sGetSlotIndexMethod = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2().getDeclaredMethod("getSlotId", Integer.TYPE);
                }
                sGetSlotIndexMethod.setAccessible(true);
            }
            Integer num = (Integer) sGetSlotIndexMethod.invoke(null, Integer.valueOf(i));
            if (num != null) {
                return num.intValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return -1;
    }

    private static class Api29Impl {
        private Api29Impl() {
        }

        static int getSlotIndex(int i) {
            return SubscriptionManager.getSlotIndex(i);
        }
    }
}
