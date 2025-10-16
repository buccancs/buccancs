package com.shimmerresearch.android;

import android.os.Handler;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;


public class Shimmer4Android extends Shimmer4sdk {

    transient public final Handler mHandler;

    public Shimmer4Android(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void sendCallBackMsg(int msgid, Object obj) {
        mHandler.obtainMessage(msgid, obj).sendToTarget();
    }

}
