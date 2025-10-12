package com.shimmerresearch.bluetooth;

import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;

public interface DataProcessingInterface {


        public void initializeProcessData(int samplingRate);

        public ObjectCluster processData(ObjectCluster ojc);


    public void updateMapOfAlgorithmModules();

    public void updateMapOfAlgorithmModules(ShimmerDevice shimmerDevice);

}