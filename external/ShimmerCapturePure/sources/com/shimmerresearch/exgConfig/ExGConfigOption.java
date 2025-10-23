package com.shimmerresearch.exgConfig;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ExGConfigOption implements Serializable {
    public int configValueInt;
    public String guiValue;
    public String settingTitle;
    public int bitShift = 0;
    public int mask = 0;

    public ExGConfigOption(String str, String str2, int i) {
        this.settingTitle = str;
        this.guiValue = str2;
        this.configValueInt = i;
    }
}
