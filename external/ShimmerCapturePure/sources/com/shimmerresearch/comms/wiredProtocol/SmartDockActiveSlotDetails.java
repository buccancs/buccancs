package com.shimmerresearch.comms.wiredProtocol;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class SmartDockActiveSlotDetails {
    public static final int CONNECTION_TYPE_DISCONNECTED = 0;
    public static final int CONNECTION_TYPE_WITHOUT_SD_CARD = 2;
    public static final int CONNECTION_TYPE_WITH_SD_CARD = 1;
    public static final Map<Integer, String> mMapOfConnectionTypes;

    static {
        TreeMap treeMap = new TreeMap();
        treeMap.put(0, "Disconnected");
        treeMap.put(1, "With SD access");
        treeMap.put(2, "Without SD access");
        mMapOfConnectionTypes = Collections.unmodifiableMap(treeMap);
    }

    public int mSlot = -1;
    public int mConnectionType = 0;
}
