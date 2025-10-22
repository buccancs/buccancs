package com.shimmerresearch.driver.shimmerGq;

import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public class MsgSpan {
    public static final int MSG_ID_DATA_REQUEST_SENT = 25;
    public static final int MSG_ID_SHIMMER_DATA_OBJECT_CLUSTER = 24;
    public static final int MSG_ID_SHIMMER_DATA_OBJECT_CLUSTER_RESULT_AGGREGATOR = 27;
    public static final int MSG_ID_SHIMMER_RECEPTION_RATE_CURRENT = 22;
    public static final int MSG_ID_SHIMMER_RESPONSE_DATA_PACKET = 20;
    public static final int MSG_ID_SHIMMER_RESPONSE_STATUS_PACKET = 21;
    public static final int MSG_ID_SHIMMER_RESPONSE_SYNC_PACKET = 26;
    public static final int MSG_ID_SHIMMER_UNPAIRED = 12;
    public static final int MSG_ID_SHIMMER_WARNING_NO_STATUS_RECEIVED = 23;
    public static final int MSG_ID_SPANMANAGER_INITIALIZED_SUCCESS = 0;
    public static final int MSG_ID_SPAN_CHANNEL_UPDATE = 1;
    public static final int MSG_ID_SPAN_ENABLED_STATE_CHANGE = 11;
    public static final int MSG_ID_SPAN_INITIALISED_STATE_CHANGE = 10;
    public static final int MSG_ID_SPAN_JOB_FAIL_PER_SPAN = 6;
    public static final int MSG_ID_SPAN_JOB_STARTED_PER_SPAN = 5;
    public static final int MSG_ID_SPAN_JOB_SUCCESS_PER_SPAN = 7;
    public static final int MSG_ID_SPAN_MODE_UPDATE = 2;
    public static final int MSG_ID_SPAN_NO_RESPONSE = 15;
    public static final int MSG_ID_SPAN_OPERATION_FINISHED = 9;
    public static final int MSG_ID_SPAN_OPERATION_PROGRESS = 8;
    public static final int MSG_ID_SPAN_PLUGGED_UNPLUGGED = 13;
    public static final int MSG_ID_SPAN_SPECTRUM_ANALYSER_RESPONSE = 3;
    public static final int MSG_ID_SPAN_SPECTRUM_SCAN_SUCCESS = 4;
    public static final int MSG_ID_SPAN_STATE_CHANGE = 14;
    public int mErrorCode;
    public int mErrorCodeLowBsl;
    public int mErrorCodeLowLevel;
    public ShimmerException.ExceptionLevel mExceptionLevel;
    public String mExceptionMsg;
    public StackTraceElement[] mExceptionStackTrace;
    public String mMessage;
    public int mMsgID;
    public Object mObject;
    public String mShimmerMacID;
    public int mShimmerRadioID;
    public String mShimmerUserAssignName;
    public String mSpanID;
    public HwDriverShimmerDeviceDetails.DEVICE_STATE mSpanState;
    public String mUniqueId;

    public MsgSpan(int i, Object obj) {
        this.mSpanID = "";
        this.mShimmerMacID = "";
        this.mShimmerRadioID = -1;
        this.mMessage = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mUniqueId = "";
        this.mShimmerUserAssignName = "";
        this.mMsgID = i;
        this.mObject = obj;
    }

    public MsgSpan(int i) {
        this.mSpanID = "";
        this.mShimmerMacID = "";
        this.mShimmerRadioID = -1;
        this.mMessage = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mUniqueId = "";
        this.mShimmerUserAssignName = "";
        this.mMsgID = i;
    }

    public MsgSpan(int i, String str, Object obj) {
        this.mSpanID = "";
        this.mShimmerMacID = "";
        this.mShimmerRadioID = -1;
        this.mMessage = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mUniqueId = "";
        this.mShimmerUserAssignName = "";
        this.mMsgID = i;
        this.mSpanID = str;
        this.mObject = obj;
    }
}
