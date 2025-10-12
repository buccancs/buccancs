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

    //--------- Sensor specific variables start --------------
        //--------- Sensor specific variables end --------------


    //--------- Bluetooth commands start --------------
        //--------- Bluetooth commands end --------------


    //--------- Configuration options start --------------
        //--------- Configuration options end --------------


    //--------- Sensor info start --------------
        //--------- Sensor info end --------------


    //--------- Channel info start --------------
        //--------- Channel info end --------------


    //--------- Constructors for this class start --------------


    public SensorTemplate(ShimmerVerObject svo) {
        super(SENSORS.TEMPLATE, svo);
                initialise();
    }
    //--------- Constructors for this class end --------------


    //--------- Abstract methods implemented start --------------

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
            // first process the data originating from the Shimmer sensor
            byte[] channelByteArray = new byte[channelDetails.mDefaultNumBytes];
            System.arraycopy(sensorByteArray, index, channelByteArray, 0, channelDetails.mDefaultNumBytes);
            objectCluster = SensorDetails.processShimmerChannelData(channelByteArray, channelDetails, objectCluster);

            /**
             *  Get the uncalibrated data from the ObjectCluster if calibration is needed. Example - SensorKionixKXRB52042:
             *
             * 		//Uncalibrated Accelerometer data
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
         *  //Calibration
         *	double[] calibratedAccelData = UtilCalibration.calibrateInertialSensorData(unCalibratedAccelData, mAlignmentMatrixAnalogAccel, mSensitivityMatrixAnalogAccel, mOffsetVectorAnalogAccel);
         *
         *	//Add calibrated data to Object cluster
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
        // TODO Auto-generated method stub

    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {
        // TODO Auto-generated method stub

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
            //Set values for particular Config Option here if applicable
            return true;
        }
        return false;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void parseConfigMap(
            LinkedHashMap<String, Object> mapOfConfigPerShimmer) {
        // TODO Auto-generated method stub

    }


    //--------- Abstract methods implemented end --------------


    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void checkShimmerConfigBeforeConfiguring() {
        // TODO Auto-generated method stub

    }


    //--------- Sensor specific methods start --------------
        //--------- Sensor specific methods end --------------


    //--------- Optional methods to override in Sensor Class start --------
        //--------- Optional methods to override in Sensor Class end --------


}
