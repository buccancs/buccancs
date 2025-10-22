package androidx.profileinstaller;

import java.util.TreeMap;

/* loaded from: classes.dex */
class DexProfileData {
    final String apkName;
    final long dexChecksum;
    final String dexName;
    final int hotMethodRegionSize;
    final TreeMap<Integer, Integer> methods;
    final int numMethodIds;
    int classSetSize;
    int[] classes;
    long mTypeIdCount;

    DexProfileData(String str, String str2, long j, long j2, int i, int i2, int i3, int[] iArr, TreeMap<Integer, Integer> treeMap) {
        this.apkName = str;
        this.dexName = str2;
        this.dexChecksum = j;
        this.mTypeIdCount = j2;
        this.classSetSize = i;
        this.hotMethodRegionSize = i2;
        this.numMethodIds = i3;
        this.classes = iArr;
        this.methods = treeMap;
    }
}
