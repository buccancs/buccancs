package com.shimmerresearch.comms.wiredProtocol;

import com.google.common.base.Strings;
import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public class DockException extends ShimmerException {
    private static final long serialVersionUID = -7922798090312830525L;
    public String mDockID;
    public int mSlotNumber;

    public DockException(String str) {
        super(str);
        this.mDockID = "";
    }

    public DockException(String str, int i, int i2, int i3) {
        this.mDockID = str;
        this.mSlotNumber = i;
        this.mUniqueID = str + "." + String.format("%02d", Integer.valueOf(i));
        this.mErrorCode = i2;
        this.mErrorCodeLowLevel = i3;
    }

    public DockException(String str, int i, int i2, int i3, ShimmerException.ExceptionLevel exceptionLevel) {
        this.mDockID = str;
        this.mSlotNumber = i;
        this.mUniqueID = str + "." + String.format("%02d", Integer.valueOf(i));
        this.mErrorCode = i2;
        this.mErrorCodeLowLevel = i3;
        this.mExceptionLevel = exceptionLevel;
    }

    public DockException(String str, int i, int i2, String str2) {
        this.mDockID = "";
        if (!Strings.isNullOrEmpty(str2)) {
            this.mUniqueID = str2;
            String[] strArrSplit = str2.split("\\.");
            this.mDockID = strArrSplit[0] + "." + strArrSplit[1];
            if (strArrSplit.length >= 3) {
                this.mSlotNumber = Integer.parseInt(strArrSplit[2]);
            } else {
                this.mSlotNumber = -1;
            }
        }
        this.mComPort = str;
        this.mErrorCode = i;
        this.mErrorCodeLowLevel = i2;
    }

    public DockException(String str, int i, int i2, String str2, String str3, StackTraceElement[] stackTraceElementArr) {
        this(str, i, i2, str2);
        this.mExceptionMsg = str3;
        this.mExceptionStackTrace = stackTraceElementArr;
    }

    public DockException(String str, String str2, int i, int i2) {
        super(str, str2, i, i2);
        this.mDockID = str;
        this.mSlotNumber = -1;
        this.mUniqueID = str + "." + String.format("%02d", -1);
    }

    public DockException(ShimmerException shimmerException) {
        this(shimmerException.mComPort, shimmerException.mErrorCode, shimmerException.mErrorCodeLowLevel, shimmerException.mUniqueID, shimmerException.mExceptionMsg, shimmerException.mExceptionStackTrace);
    }

    public void updateDockException(String str) {
        this.mUniqueID = str;
        String[] strArrSplit = str.split("\\.");
        this.mDockID = strArrSplit[0] + "." + strArrSplit[1];
        this.mSlotNumber = Integer.parseInt(strArrSplit[2]);
    }

    public void updateDockException(String str, int i) {
        this.mDockID = str;
        this.mSlotNumber = i;
        this.mUniqueID = str + "." + String.format("%02d", Integer.valueOf(i));
    }

    public void updateDockException(String str, StackTraceElement[] stackTraceElementArr) {
        super.updateDeviceException(str, stackTraceElementArr);
    }
}
