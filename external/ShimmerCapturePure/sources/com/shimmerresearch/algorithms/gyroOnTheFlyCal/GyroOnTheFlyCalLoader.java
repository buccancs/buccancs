package com.shimmerresearch.algorithms.gyroOnTheFlyCal;

import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmLoaderInterface;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class GyroOnTheFlyCalLoader implements AlgorithmLoaderInterface {
    @Override // com.shimmerresearch.algorithms.AlgorithmLoaderInterface
    public void initialiseSupportedAlgorithms(ShimmerDevice shimmerDevice, Configuration.COMMUNICATION_TYPE communication_type) {
        Iterator<AlgorithmDetails> it2 = getMapOfSupportedAlgorithms(shimmerDevice).values().iterator();
        while (it2.hasNext()) {
            shimmerDevice.addAlgorithmModule(new GyroOnTheFlyCalModule(shimmerDevice, it2.next(), shimmerDevice.getSamplingRateShimmer(communication_type)));
        }
    }

    @Override // com.shimmerresearch.algorithms.AlgorithmLoaderInterface
    public LinkedHashMap<String, AlgorithmDetails> getMapOfSupportedAlgorithms(ShimmerDevice shimmerDevice) {
        new LinkedHashMap();
        return AlgorithmDetails.loadAlgorithmsWhereSensorsAreAvailable(shimmerDevice, GyroOnTheFlyCalModule.mAlgorithmMapRef);
    }
}
