package com.shimmerresearch.driverUtilities;

/* loaded from: classes2.dex */
public class ConfigOptionObject {
    Integer[] configValues;
    String[] guiValues;
    int index;

    public ConfigOptionObject(int i, String[] strArr, Integer[] numArr) {
        this.index = i;
        this.guiValues = strArr;
        this.configValues = numArr;
    }
}
