package com.shimmerresearch.sensors;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

public class SensorTemplate extends AbstractSensor {

    private static final long serialVersionUID = -1313629173441403991L;


    public SensorTemplate(ShimmerVerObject svo) {
        super(SENSORS.TEMPLATE, svo);
        initialise();
    }


    @Override
    public void generateSensorMap() {
    }

    @Override
    public void generateConfigOptionsMap() {

    }

    @Override
    public void generateSensorGroupMapping() {
        /**
         *  put all the Sensor Grouping Details on mSensorGroupingMap and call updateSensorGroupingMap() :
         *
         *  		if((mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen4())){
         *				mSensorGroupingMap.put(LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL, new SensorGroupingDetails(
         *						Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303DLHC_ACCEL),
         *						CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW));
         *				mSensorGroupingMap.put(LABEL_SENSOR_TILE.MAG, new SensorGroupingDetails(
         *						Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303DLHC_MAG),
         *						CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW));
         *            }
         *			super.updateSensorGroupingMap();
         *
         */
        super.updateSensorGroupingMap();
    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] sensorByteArray, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {
        int index = 0;
        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
            byte[] channelByteArray = new byte[channelDetails.mDefaultNumBytes];
            System.arraycopy(sensorByteArray, index, channelByteArray, 0, channelDetails.mDefaultNumBytes);
            objectCluster = SensorDetails.processShimmerChannelData(channelByteArray, channelDetails, objectCluster);

            /**
             *  Get the uncalibrated data from the ObjectCluster if calibration is needed. Example - SensorKionixKXRB52042:
             *
             *
             *		if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_X)){
             *		unCalibratedAccelData[0] = ((FormatCluster)ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString())).mData;
             *        }
             *		else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Y)){
             *			unCalibratedAccelData[1]  = ((FormatCluster)ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString())).mData;
             *        }
             *		else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Z)){
             *			unCalibratedAccelData[2]  = ((FormatCluster)ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString())).mData;
             *        }
             */

            index = index + channelDetails.mDefaultNumBytes;
        }

        /**
         *  Perform calibration and add the calibrated data to the ObjectCluster. Example - SensorKionixKXRB52042:
         *
         *	double[] calibratedAccelData = UtilCalibration.calibrateInertialSensorData(unCalibratedAccelData, mAlignmentMatrixAnalogAccel, mSensitivityMatrixAnalogAccel, mOffsetVectorAnalogAccel);
         *
         *
         *	for (ChannelDetails channelDetails:sensorDetails.mListOfChannels){
         *		if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_X)){
         *			objectCluster.addCalData(channelDetails, calibratedAccelData[0]);
         *			objectCluster.incrementIndexKeeper();
         *        }
         *		else if(channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Y)){
         *			objectCluster.addCalData(channelDetails, calibratedAccelData[1]);
         *			objectCluster.incrementIndexKeeper();
         *        }
         *		else if(channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Z)){
         *			objectCluster.addCalData(channelDetails, calibratedAccelData[2]);
         *			objectCluster.incrementIndexKeeper();
         *        }
         *    }
         *
         */

        return objectCluster;
    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        return null;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        return null;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {

    }

    @Override
    public boolean setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        if (mSensorMap.containsKey(sensorId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
        if (mConfigOptionsMap.containsKey(stringKey)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override
    public void parseConfigMap(
            LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

    }


    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }


    @Override
    public void checkShimmerConfigBeforeConfiguring() {

    }


}
