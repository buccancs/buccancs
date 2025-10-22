package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class ConfigOptionDetailsSensor extends ConfigOptionDetails implements Serializable {
    private static final long serialVersionUID = -8894717489924237791L;
    private Configuration.COMMUNICATION_TYPE mCommunicationType;
    private Integer[] mConfigValuesAlt1;
    private Integer[] mConfigValuesAlt2;
    private Integer[] mConfigValuesAlt3;
    private Integer[] mConfigValuesAlt4;
    private Integer[] mConfigValuesAlt5;
    private Integer[] mConfigValuesAlt6;
    private String[] mGuiValuesAlt1;
    private String[] mGuiValuesAlt2;
    private String[] mGuiValuesAlt3;
    private String[] mGuiValuesAlt4;
    private String[] mGuiValuesAlt5;
    private String[] mGuiValuesAlt6;
    private int mIndexValuesToUse;

    public ConfigOptionDetailsSensor(String str, String str2, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type) {
        super(str, str2, gui_component_type);
        this.mIndexValuesToUse = 0;
    }

    public ConfigOptionDetailsSensor(String str, String str2, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list) {
        super(str, str2, gui_component_type, list);
        this.mIndexValuesToUse = 0;
    }

    public ConfigOptionDetailsSensor(String str, String str2, String[] strArr, Integer[] numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type) {
        super(str, str2, strArr, numArr, gui_component_type);
        this.mIndexValuesToUse = 0;
    }

    public ConfigOptionDetailsSensor(String str, String str2, String[] strArr, Integer[] numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list) {
        super(str, str2, strArr, numArr, gui_component_type, list);
        this.mIndexValuesToUse = 0;
    }

    public ConfigOptionDetailsSensor(String str, String str2, String[] strArr, Integer[] numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type, Configuration.COMMUNICATION_TYPE communication_type) {
        super(str, str2, strArr, numArr, gui_component_type);
        this.mIndexValuesToUse = 0;
        this.mCommunicationType = communication_type;
    }

    public ConfigOptionDetailsSensor(String str, String str2, String[] strArr, Integer[] numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list, Configuration.COMMUNICATION_TYPE communication_type) {
        super(str, str2, strArr, numArr, gui_component_type, list);
        this.mIndexValuesToUse = 0;
        setmCommunicationType(communication_type);
    }

    public ConfigOptionDetailsSensor(String str, String str2, String[] strArr, Integer[] numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list, List<ConfigOptionObject> list2) {
        this(str, str2, strArr, numArr, gui_component_type, list);
        setConfigOptions(list2);
    }

    public Configuration.COMMUNICATION_TYPE getmCommunicationType() {
        return this.mCommunicationType;
    }

    public void setmCommunicationType(Configuration.COMMUNICATION_TYPE communication_type) {
        this.mCommunicationType = communication_type;
    }

    public void setIndexOfValuesToUse(int i) {
        this.mIndexValuesToUse = i;
    }

    public void setConfigOptions(List<ConfigOptionObject> list) {
        for (ConfigOptionObject configOptionObject : list) {
            setConfigValues(configOptionObject.index, configOptionObject.configValues);
            setGuiValues(configOptionObject.index, configOptionObject.guiValues);
        }
    }

    @Override // com.shimmerresearch.driverUtilities.ConfigOptionDetails
    public String[] getGuiValues() {
        int i = this.mIndexValuesToUse;
        return i == 1 ? this.mGuiValuesAlt1 : i == 2 ? this.mGuiValuesAlt2 : i == 3 ? this.mGuiValuesAlt3 : i == 4 ? this.mGuiValuesAlt4 : i == 5 ? this.mGuiValuesAlt5 : i == 6 ? this.mGuiValuesAlt6 : this.mGuiValues;
    }

    @Override // com.shimmerresearch.driverUtilities.ConfigOptionDetails
    public Integer[] getConfigValues() {
        int i = this.mIndexValuesToUse;
        return i == 1 ? this.mConfigValuesAlt1 : i == 2 ? this.mConfigValuesAlt2 : i == 3 ? this.mConfigValuesAlt3 : i == 4 ? this.mConfigValuesAlt4 : i == 5 ? this.mConfigValuesAlt5 : i == 6 ? this.mConfigValuesAlt6 : this.mConfigValues;
    }

    public void setGuiValues(int i, String[] strArr) {
        if (i == 0) {
            this.mGuiValues = strArr;
            return;
        }
        if (i == 1) {
            this.mGuiValuesAlt1 = strArr;
            return;
        }
        if (i == 2) {
            this.mGuiValuesAlt2 = strArr;
            return;
        }
        if (i == 3) {
            this.mGuiValuesAlt3 = strArr;
            return;
        }
        if (i == 4) {
            this.mGuiValuesAlt4 = strArr;
        } else if (i == 5) {
            this.mGuiValuesAlt5 = strArr;
        } else if (i == 6) {
            this.mGuiValuesAlt6 = strArr;
        }
    }

    public void setConfigValues(int i, Integer[] numArr) {
        if (i == 0) {
            this.mConfigValues = numArr;
            return;
        }
        if (i == 1) {
            this.mConfigValuesAlt1 = numArr;
            return;
        }
        if (i == 2) {
            this.mConfigValuesAlt2 = numArr;
            return;
        }
        if (i == 3) {
            this.mConfigValuesAlt3 = numArr;
            return;
        }
        if (i == 4) {
            this.mConfigValuesAlt4 = numArr;
        } else if (i == 5) {
            this.mConfigValuesAlt5 = numArr;
        } else if (i == 6) {
            this.mConfigValuesAlt6 = numArr;
        }
    }

    public static final class VALUE_INDEXES {

        public static final class EXG_REFERENCE_ELECTRODE {
            public static final int CUSTOM = 4;
            public static final int ECG = 0;
            public static final int EMG = 1;
            public static final int RESP = 2;
            public static final int TEST = 3;
            public static final int UNIPOLAR = 5;
        }

        public static final class EXG_RESPIRATION_DETECT_PHASE {
            public static final int PHASE_32KHZ = 0;
            public static final int PHASE_64KHZ = 1;
        }

        public static final class LIS2DW12_ACCEL_RATE {
            public static final int IS_LPM = 1;
            public static final int NOT_LPM = 0;
        }

        public static final class LIS3MDL_MAG_RATE {
            public static final int IS_HP = 2;
            public static final int IS_LP = 0;
            public static final int IS_MP = 1;
            public static final int IS_UP = 3;
        }

        public static final class LSM303_ACCEL_RATE {
            public static final int IS_LPM = 1;
            public static final int NOT_LPM = 0;
        }
    }
}
