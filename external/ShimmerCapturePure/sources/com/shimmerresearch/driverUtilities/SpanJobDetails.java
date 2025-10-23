package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.ShimmerDevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class SpanJobDetails extends AbstractErrorCodes {
    public static final int ERROR_CODES_ID = 8;
    public static final Map<Integer, String> mMapOfErrorCodes;

    static {
        TreeMap treeMap = new TreeMap();
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.NONE)), "none");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.SPAN_INITIALISE)), "INITIALISE");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.GET_VERSION_INFO)), "GET_VERSION_INFO");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.WRITE_LED_STATE)), "WRITE_LED_STATE");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.SET_RADIO_CONFIG)), "SET_RADIO_CONFIG");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.GET_RADIO_CONFIG)), "GET_RADIO_CONFIG");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.SPECTRUM_ANALYSER_START)), "SPECTRUM_ANALYSER_START");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.SPECTRUM_ANALYSER_PROGRESS)), "SPECTRUM_ANALYSER_PROGRESS");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.SPECTRUM_ANALYSER_STOP)), "SPECTRUM_ANALYSER_STOP");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.BSL_SPAN_RESET)), "SPAN_RESET_VIA_BSL");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.BSL_SPAN_FW_WRITE)), "SPAN_FW_WRITE");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.CLOSE_EVERYTHING_SAFELY)), "CLOSE_EVERYTHING_SAFELY");
        treeMap.put(Integer.valueOf(getJobErrorCode(SPAN_JOB_TYPE.PERFORM_CONNECTION_TEST)), "PERFORM_CONNECTION_TEST");
        mMapOfErrorCodes = Collections.unmodifiableMap(treeMap);
    }

    public SPAN_JOB_TYPE currentJob;
    public int mRadioChannelToSet;
    public int mRadioGroupIdToSet;
    public boolean mStateToSet;
    List<ShimmerDevice> listofDockedShimmersForJob;

    public SpanJobDetails(SPAN_JOB_TYPE span_job_type) {
        this.currentJob = SPAN_JOB_TYPE.NONE;
        this.listofDockedShimmersForJob = new ArrayList();
        this.mStateToSet = false;
        this.mRadioChannelToSet = -1;
        this.mRadioGroupIdToSet = -1;
        this.currentJob = span_job_type;
    }

    public SpanJobDetails(SPAN_JOB_TYPE span_job_type, boolean z) {
        this(span_job_type);
        this.mStateToSet = z;
    }

    public SpanJobDetails(SPAN_JOB_TYPE span_job_type, int i, int i2) {
        this(span_job_type);
        this.mRadioChannelToSet = i;
        this.mRadioGroupIdToSet = i2;
    }

    public static int getJobErrorCode(SPAN_JOB_TYPE span_job_type) {
        return span_job_type.ordinal() + 8000;
    }

    public enum JobState {
        PENDING,
        INPROGRESS,
        SUCCESS,
        FAIL
    }

    public enum SPAN_JOB_TYPE {
        NONE,
        SPAN_INITIALISE,
        GET_VERSION_INFO,
        WRITE_LED_STATE,
        SET_RADIO_CONFIG,
        GET_RADIO_CONFIG,
        SPECTRUM_ANALYSER_START,
        SPECTRUM_ANALYSER_PROGRESS,
        SPECTRUM_ANALYSER_STOP,
        BSL_SPAN_RESET,
        BSL_SPAN_FW_WRITE,
        CLOSE_EVERYTHING_SAFELY,
        PERFORM_CONNECTION_TEST
    }
}
