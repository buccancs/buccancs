package com.shimmerresearch.android;

import android.os.Handler;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;

/* loaded from: classes2.dex */
public class Shimmer4Android extends Shimmer4sdk {
    public final transient Handler mHandler;

    public Shimmer4Android(Handler handler) {
        this.mHandler = handler;
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    public void sendCallBackMsg(int i, Object obj) {
        this.mHandler.obtainMessage(i, obj).sendToTarget();
    }
}
