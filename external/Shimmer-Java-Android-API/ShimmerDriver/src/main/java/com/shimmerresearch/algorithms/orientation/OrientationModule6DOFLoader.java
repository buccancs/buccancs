package com.shimmerresearch.algorithms.orientation;

import java.util.LinkedHashMap;

import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmLoaderInterface;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

public class OrientationModule6DOFLoader implements AlgorithmLoaderInterface {

    @Override
    public LinkedHashMap<String, AlgorithmDetails> getMapOfSupportedAlgorithms(ShimmerDevice shimmerDevice) {
        ShimmerVerObject svo = shimmerDevice.getShimmerVerObject();
        ExpansionBoardDetails eBD = shimmerDevice.getExpansionBoardDetails();

        LinkedHashMap<String, AlgorithmDetails> mapOfSupportedAlgorithms = new LinkedHashMap<String, AlgorithmDetails>();

        if (svo.getFirmwareIdentifier() == FW_ID.STROKARE) {
        } else {
            if ((svo.isShimmerGen3() || svo.isShimmerGen3R() || svo.isShimmerGen4())) {
                mapOfSupportedAlgorithms = AlgorithmDetails.loadAlgorithmsWhereSensorsAreAvailable(shimmerDevice, OrientationModule6DOF.mAlgorithmMapRef);

            }
        }

        return mapOfSupportedAlgorithms;
    }

    @Override
    public void initialiseSupportedAlgorithms(ShimmerDevice shimmerDevice, COMMUNICATION_TYPE comType) {

        LinkedHashMap<String, AlgorithmDetails> mapOfSupported6DOFCh = getMapOfSupportedAlgorithms(shimmerDevice);
        for (AlgorithmDetails algorithmDetails : mapOfSupported6DOFCh.values()) {
            OrientationModule6DOF orientationModule6DOF = new OrientationModule6DOF(shimmerDevice, algorithmDetails, shimmerDevice.getSamplingRateShimmer(COMMUNICATION_TYPE.BLUETOOTH));


            shimmerDevice.addAlgorithmModule(orientationModule6DOF);
        }
    }

}
