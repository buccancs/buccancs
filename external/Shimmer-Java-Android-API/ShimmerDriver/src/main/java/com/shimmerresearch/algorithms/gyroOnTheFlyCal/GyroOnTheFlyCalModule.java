package com.shimmerresearch.algorithms.gyroOnTheFlyCal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmDetails.SENSOR_CHECK_METHOD;
import com.shimmerresearch.algorithms.AlgorithmResultObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driver.Configuration.Shimmer3.DerivedSensorsBitMask;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;

public class GyroOnTheFlyCalModule extends AbstractAlgorithm {

    public static final AlgorithmDetails algoGyroOnTheFlyCal = new AlgorithmDetails(
            Arrays.asList(//Configuration.Shimmer2.SENSOR_ID.GYRO,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_MPU9X50_GYRO,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM6DSV_GYRO),
            CHANNEL_UNITS.NO_UNITS,
            SENSOR_CHECK_METHOD.ANY);

    public static final Map<String, AlgorithmDetails> mAlgorithmMapRef;
    public static final ConfigOptionDetails configOptionGyroOnTheFlyCalibThreshold = new ConfigOptionDetails(
            GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_THRESHOLD,
            DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_THRESHOLD,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.INFO,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
    public static final ConfigOptionDetails configOptionGyroOnTheFlyCalibBufferSize = new ConfigOptionDetails(
            GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE,
            DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.INFO,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
    private static final long serialVersionUID = 5109697319098246753L;
    private static final SensorGroupingDetails sGDGyroOnTheFlyCalib = new SensorGroupingDetails(
            Configuration.Shimmer3.GuiLabelAlgorithmGrouping.GYRO_ON_THE_FLY_CAL.getTileText(),
            Arrays.asList(algoGyroOnTheFlyCal),
            Arrays.asList(GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_THRESHOLD,
                    GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE),
            0);
    public static String GENERAL_ALGORITHM_NAME = "Gyro on-the-fly calibration";

    static {
        Map<String, AlgorithmDetails> aMap = new LinkedHashMap<String, AlgorithmDetails>();
        aMap.put(algoGyroOnTheFlyCal.mAlgorithmName, algoGyroOnTheFlyCal);
        mAlgorithmMapRef = Collections.unmodifiableMap(aMap);
    }


    public int sensorId = -1;
    public String ojcNameGyroX = SensorMPU9X50.ObjectClusterSensorName.GYRO_X;

    public String ojcNameGyroY = SensorMPU9X50.ObjectClusterSensorName.GYRO_Y;
    public String ojcNameGyroZ = SensorMPU9X50.ObjectClusterSensorName.GYRO_Z;
    protected OnTheFlyGyroOffsetCal mOnTheFlyGyroOffsetCal = new OnTheFlyGyroOffsetCal();
    private AbstractSensor.SENSORS sensorClass = AbstractSensor.SENSORS.MPU9X50;

    {
        algoGyroOnTheFlyCal.mAlgorithmName = GENERAL_ALGORITHM_NAME;
        algoGyroOnTheFlyCal.mDerivedSensorBitmapID = DerivedSensorsBitMask.GYRO_ON_THE_FLY_CAL;
    }

    public GyroOnTheFlyCalModule(ShimmerDevice shimmerDevice, AlgorithmDetails algorithmDetails, double samplingRateShimmer) {
        super(shimmerDevice, algorithmDetails);
        setupAlgorithm();


        setShimmerSamplingRate(samplingRateShimmer);
    }

    @Override
    public void setGeneralAlgorithmName() {
        super.setGeneralAlgorithmName(GENERAL_ALGORITHM_NAME);
    }



    @Override
    public void setFilteringOption() {
        mFilteringOptions = FILTERING_OPTION.NONE;
    }


    @Override
    public void setMinSamplingRateForAlgorithm() {
    }

    @Override
    public void setSupportedVerInfo() {
    }

    @Override
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionGyroOnTheFlyCalibThreshold);
        addConfigOption(configOptionGyroOnTheFlyCalibBufferSize);
    }

    @Override
    public void generateAlgorithmGroupingMap() {
        mMapOfAlgorithmGrouping.put(Configuration.Shimmer3.GuiLabelAlgorithmGrouping.GYRO_ON_THE_FLY_CAL.ordinal(), sGDGyroOnTheFlyCalib);
    }

    @Override
    public void initialize() throws Exception {
        setBufferSizeFromSamplingRate(mShimmerSamplingRate);
    }

    @Override
    public void resetAlgorithm() throws Exception {
        resetAlgorithmBuffers();
    }

    @Override
    public void resetAlgorithmBuffers() {
        mOnTheFlyGyroOffsetCal.setupBuffers();
    }

    @Override
    public Object getSettings(String componentName) {
        Object returnValue = null;
        switch (componentName) {
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_THRESHOLD):
                returnValue = mOnTheFlyGyroOffsetCal.getOffsetThreshold();
                break;
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE):
                returnValue = mOnTheFlyGyroOffsetCal.getBufferSize();
                break;
        }
        return returnValue;
    }

    @Override
    public void setSettings(String componentName, Object valueToSet) {
        switch (componentName) {
            case (AbstractAlgorithm.GuiLabelConfigCommon.SHIMMER_SAMPLING_RATE):
                if (valueToSet instanceof Double) {
                    mOnTheFlyGyroOffsetCal.setBufferSizeFromSamplingRate((Double) valueToSet);
                }
                break;
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_THRESHOLD):
                if (valueToSet instanceof Double) {
                    mOnTheFlyGyroOffsetCal.setOffsetThreshold((Double) valueToSet);
                }
                break;
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE):
                if (valueToSet instanceof Integer) {
                    mOnTheFlyGyroOffsetCal.setBufferSize((int) valueToSet);
                }
                break;
        }
    }

    @Override
    public Object getDefaultSettings(String componentName) {
        Object returnValue = null;
        switch (componentName) {
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_THRESHOLD):
                returnValue = OnTheFlyGyroOffsetCal.DEFAULT_THRESHOLD;
                break;
            case (GuiLabelConfig.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE):
                returnValue = mShimmerSamplingRate;
                break;
        }
        return returnValue;
    }

    @Override
    public AlgorithmResultObject processDataRealTime(ObjectCluster ojc) throws Exception {



        double[] gyroCalibratedData = new double[]{
                ojc.getFormatClusterValue(ojcNameGyroX, CHANNEL_TYPE.CAL.toString()),
                ojc.getFormatClusterValue(ojcNameGyroY, CHANNEL_TYPE.CAL.toString()),
                ojc.getFormatClusterValue(ojcNameGyroZ, CHANNEL_TYPE.CAL.toString())};

        double[] gyroUncalibratedData = new double[]{
                ojc.getFormatClusterValue(ojcNameGyroX, CHANNEL_TYPE.UNCAL.toString()),
                ojc.getFormatClusterValue(ojcNameGyroY, CHANNEL_TYPE.UNCAL.toString()),
                ojc.getFormatClusterValue(ojcNameGyroZ, CHANNEL_TYPE.UNCAL.toString())};

        if (mShimmerDevice instanceof ShimmerObject) {
            ShimmerObject shimmerObject = (ShimmerObject) mShimmerDevice;
            CalibDetails calibDetails = shimmerObject.getCurrentCalibDetailsGyro();
            mOnTheFlyGyroOffsetCal.updateGyroOnTheFlyGyroOVCal((CalibDetailsKinematic) calibDetails, gyroCalibratedData, gyroUncalibratedData);
        } else {
            Object object = mShimmerDevice.getConfigValueUsingConfigLabel(Integer.toString(sensorId), AbstractSensor.GuiLabelConfigCommon.CALIBRATION_CURRENT_PER_SENSOR);
            if (object != null && (object instanceof CalibDetails)) {
                CalibDetails calibDetails = (CalibDetails) object;
                mOnTheFlyGyroOffsetCal.updateGyroOnTheFlyGyroOVCal((CalibDetailsKinematic) calibDetails, gyroCalibratedData, gyroUncalibratedData);
            }
        }


        return null;
    }

    @Override
    public AlgorithmResultObject processDataPostCapture(Object object) throws Exception {
        return null;
    }

    @Override
    public String printBatchMetrics() {
        return null;
    }

    @Override
    public void eventDataReceived(ShimmerMsg shimmerMSG) {

    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> mapOfConfig = new LinkedHashMap<String, Object>();
        mapOfConfig.put(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_THRESHOLD, getOffsetThreshold());
        mapOfConfig.put(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE, mOnTheFlyGyroOffsetCal.getBufferSize());
        return mapOfConfig;
    }

    @Override
    public void parseConfigMapFromDb(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_THRESHOLD)) {
            double threshold = (Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_THRESHOLD);
            setOffsetThreshold(threshold);
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE)) {
            double bufferSize = (Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE);
            setBufferSizeFromSamplingRate(bufferSize);
        }
    }

    @Override
    public void setIsEnabled(boolean isEnabled) {
        super.setIsEnabled(isEnabled);
        setOnTheFlyGyroCal(isEnabled);
    }

    @Override
    public void setShimmerSamplingRate(double samplingRate) {
        super.setShimmerSamplingRate(samplingRate);
        setBufferSizeFromSamplingRate(samplingRate);
    }


        public void enableOnTheFlyGyroCal(boolean state, int bufferSize, double threshold) {
        mOnTheFlyGyroOffsetCal.setIsEnabled(state, bufferSize, threshold);
    }

    public void setOnTheFlyGyroCal(boolean state) {
        mOnTheFlyGyroOffsetCal.setIsEnabled(state);
    }


    public boolean isGyroOnTheFlyCalEnabled() {
        return mOnTheFlyGyroOffsetCal.isEnabled();
    }

    public OnTheFlyGyroOffsetCal getOnTheFlyCalGyro() {
        return mOnTheFlyGyroOffsetCal;
    }

    public void setBufferSizeFromSamplingRate(double samplingRate) {
        mOnTheFlyGyroOffsetCal.setBufferSizeFromSamplingRate(samplingRate);
    }

    public double getOffsetThreshold() {
        return mOnTheFlyGyroOffsetCal.getOffsetThreshold();
    }

    public void setOffsetThreshold(double threshold) {
        mOnTheFlyGyroOffsetCal.setOffsetThreshold(threshold);
    }

    public void setGyroAxisLabels(String Gyro_X, String Gyro_Y, String Gyro_Z) {
        ojcNameGyroX = Gyro_X;
        ojcNameGyroY = Gyro_Y;
        ojcNameGyroZ = Gyro_Z;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public static final class DatabaseConfigHandle {
        public static final String GYRO_ON_THE_FLY_CALIB_THRESHOLD = "Gyro_on_the_fly_offset_cal_Threshold";
        public static final String GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE = "Gyro_on_the_fly_offset_cal_BufferSize";
    }

    public class GuiLabelConfig {
        public static final String GYRO_ON_THE_FLY_CALIB_THRESHOLD = "GyroOTFCal Threshold(deg/s)";
        public static final String GYRO_ON_THE_FLY_CALIB_BUFFER_SIZE = "GyroOTFCal BufferSize";
    }

}
