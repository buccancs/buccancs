package com.shimmerresearch.algorithms.orientation;

import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmLoaderInterface;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class OrientationModule6DOFLoader implements AlgorithmLoaderInterface {
    @Override // com.shimmerresearch.algorithms.AlgorithmLoaderInterface
    public LinkedHashMap<String, AlgorithmDetails> getMapOfSupportedAlgorithms(ShimmerDevice shimmerDevice) {
        ShimmerVerObject shimmerVerObject = shimmerDevice.getShimmerVerObject();
        shimmerDevice.getExpansionBoardDetails();
        LinkedHashMap<String, AlgorithmDetails> linkedHashMap = new LinkedHashMap<>();
        return shimmerVerObject.getFirmwareIdentifier() == 15 ? linkedHashMap : (shimmerVerObject.isShimmerGen3() || shimmerVerObject.isShimmerGen3R() || shimmerVerObject.isShimmerGen4()) ? AlgorithmDetails.loadAlgorithmsWhereSensorsAreAvailable(shimmerDevice, OrientationModule6DOF.mAlgorithmMapRef) : linkedHashMap;
    }

    @Override // com.shimmerresearch.algorithms.AlgorithmLoaderInterface
    public void initialiseSupportedAlgorithms(ShimmerDevice shimmerDevice, Configuration.COMMUNICATION_TYPE communication_type) {
        Iterator<AlgorithmDetails> it2 = getMapOfSupportedAlgorithms(shimmerDevice).values().iterator();
        while (it2.hasNext()) {
            shimmerDevice.addAlgorithmModule(new OrientationModule6DOF(shimmerDevice, it2.next(), shimmerDevice.getSamplingRateShimmer(Configuration.COMMUNICATION_TYPE.BLUETOOTH)));
        }
    }
}
