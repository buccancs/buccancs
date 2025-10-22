package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;

import java.util.List;

/* loaded from: classes2.dex */
public class ActionSetting {
    public Configuration.COMMUNICATION_ACTION mAction;
    public byte[] mActionByteArray;
    public List<byte[]> mActionListByteArray;
    public Configuration.COMMUNICATION_TYPE mCommType;
    public int mIndex;

    public ActionSetting(Configuration.COMMUNICATION_TYPE communication_type) {
        this.mCommType = communication_type;
    }
}
