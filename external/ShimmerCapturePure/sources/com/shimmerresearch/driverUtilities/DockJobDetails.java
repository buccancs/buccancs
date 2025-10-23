package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.ShimmerDevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class DockJobDetails extends AbstractErrorCodes {
    public static final int ERROR_CODES_ID = 4;
    public static final Map<Integer, String> mMapOfErrorCodes;

    static {
        TreeMap treeMap = new TreeMap();
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.NONE)), "none");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_VERSION_INFO)), "Dock_Cmd_Version_Info_Get");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_AUTO_NOTIFY_STATE)), "Can't read the SmartDock auto-notify enable setting");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_AUTO_NOTIFY_STATE)), "Can't set the SmartDock auto-notify enable setting");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_BSL_MASK_DOCK)), "BSL_MASK_DOCK_SET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_BSL_MASK_DOCK)), "BSL_MASK_DOCK_GET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_BSL_MASK_SHIMMER)), "BSL_MASK_SHIMMER_SET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_BSL_MASK_SHIMMER)), "BSL_MASK_SHIMMER_GET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_CONNECTED_SLOTS)), "Dock_Cmd_Query_Connected_Slots");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DISCONNECT_ALL_SLOTS)), "Dock_cmd_Slots_Disconnect");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_ACTIVE_SLOT_WITHOUT_SD)), "Fail to set the active slot");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_ACTIVE_SLOT_WITH_SD)), "Fail to set the active slot");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_ACTIVE_SLOT)), "Can't read the dock position");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_SHIMMERS_RESET_STATE)), "SHIMMER_RESET_SET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_SHIMMERS_RESET_STATE)), "SHIMMER_RESET_GET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SHIMMER_RESET_ALL)), "PERFORM_SHIMMER_RESET_ALL");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SHIMMER_RESET_SINGLE)), "PERFORM_SHIMMER_RESET_SINGLE");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.PERFORM_IDENTIFY_SLOT)), "PERFORM_IDENTIFY_SLOT");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.PERFORM_IDENTIFY_DOCK)), "PERFORM_IDENTIFY_DOCK");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_GPIO_STATE)), "SHIMMER_GPIO_SET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_GPIO_STATE)), "SHIMMER_GPIO_GET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_GPIO_OUT_TOGGLE)), "SET_GPIO_OUT_TOGGLE");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SET_INDICATOR_LEDS_STATE)), "INDICATOR_LEDS_SET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.GET_INDICATOR_LEDS_STATE)), "INDICATOR_LEDS_GET");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.ACCESS_SD_CARD)), "ACCESS_SD_CARD");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.FW_DOCK)), "FW_DOCK");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.SHIMMER_WRITE_EXP_BRD_MEMORY)), "SHIMMER_WRITE_EXP");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_BOOT)), "Fail to detect bootup");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_RESET_VIA_FW)), "Failed to reset Base");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_RESET_VIA_BSL)), "Failed to reset Base");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_MANAGER_RELOAD)), "DOCK_MANAGER_RELOAD");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_MANAGER_LOAD)), "DOCK_MANAGER_LOAD");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_MANAGER_RETRY_SETUP_DOCK)), "DOCK_MANAGER_RETRY_SETUP_DOCK");
        treeMap.put(Integer.valueOf(getJobErrorCode(DOCK_JOB_TYPE.DOCK_MANAGER_RETRY_SETUP_DOCKS)), "DOCK_MANAGER_RETRY_SETUP_DOCKS");
        mMapOfErrorCodes = Collections.unmodifiableMap(treeMap);
    }

    public DOCK_JOB_TYPE currentJob;
    public String dockId;
    public GPIO_STATE gpioState;
    public int gpioToggleCount;
    public int gpioToggleDelay;
    public List<Integer> ledDisplayList;
    public int ledToggleDelay;
    public List<String> listOfDockIds;
    public int slotNumber;
    public boolean state;
    List<ShimmerDevice> listofDockedShimmersForJob;

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type) {
        this.currentJob = DOCK_JOB_TYPE.NONE;
        this.listofDockedShimmersForJob = new ArrayList();
        this.state = false;
        this.gpioState = GPIO_STATE.UNKNOWN;
        this.gpioToggleCount = 1;
        this.gpioToggleDelay = 1000;
        this.ledDisplayList = new ArrayList();
        this.ledToggleDelay = 1000;
        this.slotNumber = -1;
        this.dockId = "";
        this.listOfDockIds = new ArrayList();
        this.currentJob = dock_job_type;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, boolean z) {
        this(dock_job_type);
        this.state = z;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, GPIO_STATE gpio_state) {
        this(dock_job_type);
        this.gpioState = gpio_state;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, int i, int i2) {
        this(dock_job_type);
        this.gpioToggleCount = i;
        this.gpioToggleDelay = i2;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, List<Integer> list, int i) {
        this(dock_job_type);
        this.ledDisplayList = list;
        this.ledToggleDelay = i;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, int i) {
        this(dock_job_type);
        this.slotNumber = i;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, String str) {
        this(dock_job_type);
        this.dockId = str;
    }

    public DockJobDetails(DOCK_JOB_TYPE dock_job_type, List<String> list) {
        this(dock_job_type);
        this.listOfDockIds = list;
    }

    public static int getJobErrorCode(DOCK_JOB_TYPE dock_job_type) {
        return dock_job_type.ordinal() + 4000;
    }

    public enum DOCK_JOB_TYPE {
        NONE,
        GET_VERSION_INFO,
        GET_AUTO_NOTIFY_STATE,
        SET_AUTO_NOTIFY_STATE,
        SET_BSL_MASK_DOCK,
        GET_BSL_MASK_DOCK,
        SET_BSL_MASK_SHIMMER,
        GET_BSL_MASK_SHIMMER,
        GET_CONNECTED_SLOTS,
        DISCONNECT_ALL_SLOTS,
        SET_ACTIVE_SLOT_WITHOUT_SD,
        SET_ACTIVE_SLOT_WITH_SD,
        GET_ACTIVE_SLOT,
        SET_SHIMMERS_RESET_STATE,
        GET_SHIMMERS_RESET_STATE,
        SHIMMER_RESET_ALL,
        SHIMMER_RESET_SINGLE,
        PERFORM_IDENTIFY_SLOT,
        PERFORM_IDENTIFY_DOCK,
        SET_GPIO_STATE,
        GET_GPIO_STATE,
        SET_GPIO_OUT_TOGGLE,
        SET_INDICATOR_LEDS_STATE,
        GET_INDICATOR_LEDS_STATE,
        ACCESS_SD_CARD,
        DOCK_BOOT,
        DOCK_RESET_VIA_FW,
        DOCK_RESET_VIA_BSL,
        FW_DOCK,
        SHIMMER_WRITE_EXP_BRD_MEMORY,
        SHIMMER_WRITE_DAUGHTER_CARD_ID,
        DOCK_MANAGER_RELOAD,
        DOCK_MANAGER_LOAD,
        DOCK_MANAGER_RETRY_SETUP_DOCK,
        DOCK_MANAGER_RETRY_SETUP_DOCKS
    }

    public enum GPIO_STATE {
        UNKNOWN,
        GPIO_IN,
        GPIO_OUT_HIGH,
        GPIO_OUT_LOW
    }

    public enum JobState {
        PENDING,
        INPROGRESS,
        SUCCESS,
        FAIL
    }
}
