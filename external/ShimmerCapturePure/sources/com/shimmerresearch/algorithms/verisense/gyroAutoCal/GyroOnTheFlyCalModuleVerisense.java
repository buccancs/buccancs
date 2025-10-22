package com.shimmerresearch.algorithms.verisense.gyroAutoCal;

import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.gyroOnTheFlyCal.GyroOnTheFlyCalModule;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.verisense.sensors.SensorLSM6DS3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class GyroOnTheFlyCalModuleVerisense extends GyroOnTheFlyCalModule {
    public static final AlgorithmDetails algoGyroOnTheFlyCalVerisense;
    public static final Map<String, AlgorithmDetails> mAlgorithmMapRefVerisense;
    private static final long serialVersionUID = -8105079411104081532L;

    static {
        AlgorithmDetails algorithmDetails = new AlgorithmDetails(GENERAL_ALGORITHM_NAME, GENERAL_ALGORITHM_NAME, new ArrayList(), 128L, Arrays.asList(Integer.valueOf(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO)), Configuration.CHANNEL_UNITS.NO_UNITS);
        algoGyroOnTheFlyCalVerisense = algorithmDetails;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(algorithmDetails.mAlgorithmName, algorithmDetails);
        mAlgorithmMapRefVerisense = Collections.unmodifiableMap(linkedHashMap);
    }

    public GyroOnTheFlyCalModuleVerisense(ShimmerDevice shimmerDevice, AlgorithmDetails algorithmDetails, double d) {
        super(shimmerDevice, algorithmDetails, d);
        algoGyroOnTheFlyCalVerisense.mSensorCheckMethod = AlgorithmDetails.SENSOR_CHECK_METHOD.ANY;
        super.setSensorId(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO);
        super.setGyroAxisLabels(SensorLSM6DS3.ObjectClusterSensorName.LSM6DS3_GYRO_X, SensorLSM6DS3.ObjectClusterSensorName.LSM6DS3_GYRO_Y, SensorLSM6DS3.ObjectClusterSensorName.LSM6DS3_GYRO_Z);
    }
}
