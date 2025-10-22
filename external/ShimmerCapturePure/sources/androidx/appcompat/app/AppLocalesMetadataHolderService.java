package androidx.appcompat.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.IBinder;

/* loaded from: classes.dex */
public final class AppLocalesMetadataHolderService extends Service {
    public static ServiceInfo getServiceInfo(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), Build.VERSION.SDK_INT >= 24 ? Api24Impl.getDisabledComponentFlag() | 128 : 640);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException();
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static int getDisabledComponentFlag() {
            return 512;
        }
    }
}
