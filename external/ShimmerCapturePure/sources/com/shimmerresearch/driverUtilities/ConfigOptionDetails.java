package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class ConfigOptionDetails implements Serializable {
    private static final long serialVersionUID = 8021677605649506093L;
    public Integer[] mConfigValues;
    public String mDbHandle;
    public GUI_COMPONENT_TYPE mGuiComponentType;
    public String mGuiHandle;
    public String[] mGuiValues;
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo;

    public ConfigOptionDetails(String str, String str2, GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list) {
        this.mGuiValues = null;
        this.mGuiHandle = str;
        this.mDbHandle = str2;
        this.mGuiComponentType = gui_component_type;
        this.mListOfCompatibleVersionInfo = list;
        if (gui_component_type == GUI_COMPONENT_TYPE.CHECKBOX) {
            this.mGuiValues = new String[]{"Off", "On"};
            this.mConfigValues = new Integer[]{0, 1};
        }
    }

    public ConfigOptionDetails(String str, String str2, String[] strArr, Integer[] numArr, GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list) {
        this(str, str2, gui_component_type, list);
        this.mGuiValues = strArr;
        this.mConfigValues = numArr;
    }

    public ConfigOptionDetails(String str, String str2, String[] strArr, GUI_COMPONENT_TYPE gui_component_type, List<ShimmerVerObject> list) {
        this(str, str2, gui_component_type, list);
        this.mGuiValues = strArr;
        this.mConfigValues = new Integer[strArr.length];
        int i = 0;
        while (true) {
            Integer[] numArr = this.mConfigValues;
            if (i >= numArr.length) {
                return;
            }
            numArr[i] = Integer.valueOf(i);
            i++;
        }
    }

    public ConfigOptionDetails(String str, String str2, GUI_COMPONENT_TYPE gui_component_type) {
        this(str, str2, gui_component_type, null);
    }

    public ConfigOptionDetails(String str, String str2, String[] strArr, Integer[] numArr, GUI_COMPONENT_TYPE gui_component_type) {
        this(str, str2, strArr, numArr, gui_component_type, null);
    }

    public static String getConfigStringFromConfigValue(Integer[] numArr, String[] strArr, Integer num) {
        int iIndexOf = Arrays.asList(numArr).indexOf(num);
        return (iIndexOf < 0 || strArr.length <= iIndexOf) ? "?" : strArr[iIndexOf];
    }

    public Integer[] getConfigValues() {
        return this.mConfigValues;
    }

    public String getGuiFriendlyName() {
        return this.mGuiHandle;
    }

    public String[] getGuiValues() {
        return this.mGuiValues;
    }

    public String getConfigStringFromConfigValue(Integer num) {
        return getConfigStringFromConfigValue(this.mConfigValues, this.mGuiValues, num);
    }

    public enum GUI_COMPONENT_TYPE {
        COMBOBOX,
        CHECKBOX,
        TEXTFIELD,
        JPANEL,
        INFO
    }
}
