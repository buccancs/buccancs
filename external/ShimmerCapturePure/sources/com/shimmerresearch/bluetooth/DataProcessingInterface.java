package com.shimmerresearch.bluetooth;

import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;

/* loaded from: classes2.dex */
public interface DataProcessingInterface {
    void initializeProcessData(int i);

    ObjectCluster processData(ObjectCluster objectCluster);

    void updateMapOfAlgorithmModules();

    void updateMapOfAlgorithmModules(ShimmerDevice shimmerDevice);
}
