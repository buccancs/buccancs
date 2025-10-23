package com.shimmerresearch.exceptions;

import com.shimmerresearch.driverUtilities.UtilShimmer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ShimmerException extends ExecutionException {
    private static final long serialVersionUID = -8040452709544630044L;
    public static HashMap<Integer, String> mMapOfErrorCodes = new HashMap<>();
    public String mClassName;
    public String mComPort;
    public int mErrorCode;
    public int mErrorCodeLowLevel;
    public ExceptionLevel mExceptionLevel;
    public String mExceptionMsg;
    public StackTraceElement[] mExceptionStackTrace;
    public String mMessage;
    public String mUniqueID;

    public ShimmerException() {
        this.mMessage = "";
        this.mExceptionMsg = "";
        this.mExceptionLevel = ExceptionLevel.HIGH;
        this.mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public ShimmerException(String str) {
        super(str);
        this.mMessage = "";
        this.mExceptionMsg = "";
        this.mExceptionLevel = ExceptionLevel.HIGH;
        this.mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public ShimmerException(Exception exc) {
        this.mMessage = "";
        this.mExceptionMsg = "";
        this.mExceptionLevel = ExceptionLevel.HIGH;
        setStackTrace(exc.getStackTrace());
        this.mMessage = exc.getLocalizedMessage();
        this.mExceptionMsg = exc.getMessage();
    }

    public ShimmerException(String str, String str2, int i, int i2) {
        this.mMessage = "";
        this.mExceptionMsg = "";
        this.mExceptionLevel = ExceptionLevel.HIGH;
        this.mUniqueID = str;
        this.mComPort = str2;
        this.mErrorCode = i;
        this.mErrorCodeLowLevel = i2;
        this.mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public ShimmerException(int i, String str) {
        this.mMessage = "";
        this.mExceptionMsg = "";
        this.mExceptionLevel = ExceptionLevel.HIGH;
        this.mErrorCode = i;
        this.mMessage = str;
        this.mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public static void addToMapOfErrorCodes(Map<Integer, String> map) {
        mMapOfErrorCodes.putAll(map);
    }

    public static void addToMapOfErrorCodes(List<Map<Integer, String>> list) {
        Iterator<Map<Integer, String>> it2 = list.iterator();
        while (it2.hasNext()) {
            addToMapOfErrorCodes(it2.next());
        }
    }

    public void updateDeviceException(String str, StackTraceElement[] stackTraceElementArr) {
        this.mExceptionMsg = str;
        this.mExceptionStackTrace = stackTraceElementArr;
    }

    public String getErrStringFormatted() {
        return getErrStringFormatted(mMapOfErrorCodes);
    }

    private String getErrStringFormatted(Map<Integer, String> map) {
        String str;
        String str2;
        String str3;
        String str4 = this.mUniqueID;
        str = "Unknown Error";
        if (map != null) {
            String str5 = map.containsKey(Integer.valueOf(this.mErrorCode)) ? map.get(Integer.valueOf(this.mErrorCode)) : "Unknown Error";
            str2 = map.containsKey(Integer.valueOf(this.mErrorCodeLowLevel)) ? map.get(Integer.valueOf(this.mErrorCodeLowLevel)) : "Unknown Error";
            str = str5;
        } else {
            str2 = "Unknown Error";
        }
        String str6 = this.mExceptionMsg;
        String str7 = "";
        if (str6 == null || str6.isEmpty()) {
            str3 = "";
        } else {
            str3 = "Further info: " + this.mExceptionMsg;
        }
        String str8 = this.mMessage;
        if (str8 != null && !str8.isEmpty()) {
            str7 = "Further info: " + this.mMessage;
        }
        String str9 = "CAUGHT SHIMMER DEVICE EXCEPTION\n\tUniqueID: " + str4 + "\n\tAction: (" + this.mErrorCode + ") " + str + "\n\tLowLevelError: (" + this.mErrorCodeLowLevel + ") " + str2 + "\n\t" + str3 + "\n\t" + str7 + StringUtils.LF;
        String strConvertStackTraceToString = convertStackTraceToString();
        if (strConvertStackTraceToString.isEmpty()) {
            return str9;
        }
        return str9 + strConvertStackTraceToString;
    }

    public String convertStackTraceToString() {
        StackTraceElement[] stackTraceElementArr = this.mExceptionStackTrace;
        return stackTraceElementArr != null ? UtilShimmer.convertStackTraceToString(stackTraceElementArr) : "";
    }

    public void updateDeviceException(Exception exc) {
        updateDeviceException(exc.getMessage(), exc.getStackTrace());
    }

    public enum ExceptionLevel {
        LOW,
        HIGH
    }
}
