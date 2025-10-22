package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.algorithms.AlgorithmDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SensorGroupingDetails implements Serializable {
    private static final long serialVersionUID = 4373658361698230203L;
    public String mGroupName;
    public boolean mIsPermanentGroup;
    public List<AlgorithmDetails> mListOfAlgorithmDetails;
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo;
    public List<String> mListOfConfigOptionKeysAssociated;
    public List<Integer> mListOfSensorIdsAssociated;
    public List<String> mListofGuiConfigNames;

    public SensorGroupingDetails(String str, List<Integer> list) {
        this.mGroupName = "";
        this.mListOfSensorIdsAssociated = new ArrayList();
        this.mListOfCompatibleVersionInfo = new ArrayList();
        this.mListOfConfigOptionKeysAssociated = new ArrayList();
        this.mListofGuiConfigNames = new ArrayList();
        this.mListOfAlgorithmDetails = new ArrayList();
        this.mIsPermanentGroup = false;
        this.mGroupName = str;
        this.mListOfSensorIdsAssociated = list;
    }

    public SensorGroupingDetails(String str, List<Integer> list, List<ShimmerVerObject> list2) {
        this(str, list);
        this.mListOfCompatibleVersionInfo = list2;
    }

    public SensorGroupingDetails(String str, List<AlgorithmDetails> list, List<String> list2, Integer num) {
        this.mGroupName = "";
        this.mListOfSensorIdsAssociated = new ArrayList();
        this.mListOfCompatibleVersionInfo = new ArrayList();
        this.mListOfConfigOptionKeysAssociated = new ArrayList();
        this.mListofGuiConfigNames = new ArrayList();
        new ArrayList();
        this.mIsPermanentGroup = false;
        this.mGroupName = str;
        this.mListOfAlgorithmDetails = list;
        if (list2 != null) {
            this.mListOfConfigOptionKeysAssociated = list2;
        }
    }

    public SensorGroupingDetails(String str, List<Integer> list, List<ShimmerVerObject> list2, boolean z) {
        this(str, list, list2);
        this.mIsPermanentGroup = z;
    }
}
