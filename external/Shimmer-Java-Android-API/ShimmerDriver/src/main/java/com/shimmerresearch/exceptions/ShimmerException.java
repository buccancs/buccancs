package com.shimmerresearch.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.shimmerresearch.driverUtilities.UtilShimmer;

public class ShimmerException extends ExecutionException {

    private static final long serialVersionUID = -8040452709544630044L;
        public static HashMap<Integer, String> mMapOfErrorCodes = new HashMap<Integer, String>();
    public int mErrorCode;
    public int mErrorCodeLowLevel;
    public String mComPort;
    public String mClassName;
    public String mUniqueID;
        public String mMessage = ""; // Currently used for SD copy fail messages
        public String mExceptionMsg = "";
        public StackTraceElement[] mExceptionStackTrace;
        public ExceptionLevel mExceptionLevel = ExceptionLevel.HIGH;

    public ShimmerException() {
        mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public ShimmerException(String message) {
        super(message);
        mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public ShimmerException(Exception e) {
        setStackTrace(e.getStackTrace());
        mMessage = e.getLocalizedMessage();
        mExceptionMsg = e.getMessage();
    }

    public ShimmerException(String uniqueId, String comPort, int errorType, int lowLevelErrorCode) {
        mUniqueID = uniqueId;
        mComPort = comPort;
        mErrorCode = errorType;
        mErrorCodeLowLevel = lowLevelErrorCode;
        mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

        public ShimmerException(int errorCode, String message) {
        mErrorCode = errorCode;
        mMessage = message;
        mExceptionStackTrace = UtilShimmer.getCurrentStackTrace();
    }

    public static void addToMapOfErrorCodes(Map<Integer, String> mapOfErrorCodes) {
//		System.out.println("Adding to ErrorMap.\t Was size = " + mMapOfErrorCodes.size());
        mMapOfErrorCodes.putAll(mapOfErrorCodes);
//		System.out.println("Adding to ErrorMap.\t Now size = " + mMapOfErrorCodes.size());
    }

    public static void addToMapOfErrorCodes(List<Map<Integer, String>> errorMapsToLoad) {
        for (Map<Integer, String> mapOfErrorCodes : errorMapsToLoad) {
            addToMapOfErrorCodes(mapOfErrorCodes);
        }
    }

    public String getErrStringFormatted() {
        return getErrStringFormatted(mMapOfErrorCodes);
    }

    private String getErrStringFormatted(Map<Integer, String> mapOfErrorCodes) {
        String errorString = "";

        String id = mUniqueID;
//		if(mSlotNumber == -1) {
//			id = mDockID;
//		}
        String errorCode = "Unknown Error";
        String lowLevelErrorCode = "Unknown Error";
        if (mapOfErrorCodes != null) {
            if (mapOfErrorCodes.containsKey(mErrorCode)) {
                errorCode = mapOfErrorCodes.get(mErrorCode);
            }
            if (mapOfErrorCodes.containsKey(mErrorCodeLowLevel)) {
                lowLevelErrorCode = mapOfErrorCodes.get(mErrorCodeLowLevel);
            }
        }
        String exceptionInfo = "";
        if (mExceptionMsg != null && !mExceptionMsg.isEmpty()) {
            exceptionInfo = "Further info: " + mExceptionMsg;
        }
        String messageInfo = "";
        if (mMessage != null && !mMessage.isEmpty()) {
            messageInfo = "Further info: " + mMessage;
        }

        errorString += ("CAUGHT SHIMMER DEVICE EXCEPTION\n");
        errorString += ("\t" + "UniqueID: " + id
                + "\n\t" + "Action: " + "(" + mErrorCode + ") " + errorCode
                + "\n\t" + "LowLevelError: " + "(" + mErrorCodeLowLevel + ") " + lowLevelErrorCode
                + "\n\t" + exceptionInfo
                + "\n\t" + messageInfo
                + "\n");

        String stackTraceString = convertStackTraceToString();
        if (!stackTraceString.isEmpty()) {
            errorString += (stackTraceString);
        }

        return errorString;
    }

    public String convertStackTraceToString() {
        if (mExceptionStackTrace != null) {
            return UtilShimmer.convertStackTraceToString(mExceptionStackTrace);
        } else {
            return "";
        }
    }

    public void updateDeviceException(String exceptionMsg, StackTraceElement[] exceptionStacktrace) {
        mExceptionMsg = exceptionMsg;
        mExceptionStackTrace = exceptionStacktrace;
    }

    public void updateDeviceException(Exception e) {
        updateDeviceException(e.getMessage(), e.getStackTrace());
    }

    public static enum ExceptionLevel {
        LOW,
        HIGH
    }

}
