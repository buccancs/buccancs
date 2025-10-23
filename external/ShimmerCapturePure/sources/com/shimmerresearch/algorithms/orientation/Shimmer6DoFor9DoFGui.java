package com.shimmerresearch.algorithms.orientation;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.driverUtilities.ChannelDetails;

import java.util.List;

/* loaded from: classes2.dex */
public class Shimmer6DoFor9DoFGui {
    private boolean is9DoFSelected = false;
    private boolean isBothAccelsAvailable;
    private List<AbstractAlgorithm> listOfEnabledAlgorithmModulesPerGroup;
    private OrientationModule orientationModule;
    private String selectedAccel;
    private String shimmerName;

    public Shimmer6DoFor9DoFGui(String str, String str2, List<AbstractAlgorithm> list) {
        this.isBothAccelsAvailable = false;
        this.shimmerName = str;
        this.selectedAccel = str2;
        this.listOfEnabledAlgorithmModulesPerGroup = list;
        this.isBothAccelsAvailable = list.size() > 1;
        setChannelDetails(this.selectedAccel);
    }

    public String getEnabledAccel() {
        return this.selectedAccel;
    }

    public String getUserAssignedShimmerName() {
        return this.shimmerName;
    }

    public boolean isBothAccelsAvailable() {
        return this.isBothAccelsAvailable;
    }

    public void setSelectedAccelandChannelDetails(String str) {
        this.selectedAccel = str;
        setChannelDetails(str);
    }

    public List<ChannelDetails> getChannelDetails() {
        OrientationModule orientationModule = this.orientationModule;
        if (orientationModule != null) {
            return orientationModule.getChannelDetails();
        }
        System.err.println("orientationModule.getChannelDetails() is NULL!");
        return null;
    }

    private void setChannelDetails(String str) {
        for (AbstractAlgorithm abstractAlgorithm : this.listOfEnabledAlgorithmModulesPerGroup) {
            if (abstractAlgorithm instanceof OrientationModule) {
                OrientationModule orientationModule = (OrientationModule) abstractAlgorithm;
                if (orientationModule.getAccelerometer().equals(str)) {
                    this.orientationModule = orientationModule;
                }
            }
        }
    }
}
