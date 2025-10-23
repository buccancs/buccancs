package com.shimmerresearch.sensors.lisxmdl;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.UtilCalibration;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorLIS2MDL extends AbstractSensor {
    public static final double[][] DefaultAlignmentLIS2MDL;
    public static final double[][] DefaultAlignmentMatrixMagShimmer3r;
    public static final double[][] DefaultOffsetVectorMagShimmer3r = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] DefaultSensitivityMatrixMagShimmer3r = {new double[]{667.0d, 0.0d, 0.0d}, new double[]{0.0d, 667.0d, 0.0d}, new double[]{0.0d, 0.0d, 667.0d}};
    public static final byte GET_MAG_CALIBRATION_COMMAND = 25;
    public static final byte GET_MAG_GAIN_COMMAND = 57;
    public static final byte GET_MAG_SAMPLING_RATE_COMMAND = 60;
    public static final String[] ListofLIS2MDLMagRange;
    public static final Integer[] ListofLIS2MDLMagRangeConfigValues;
    public static final String[] ListofLIS2MDLMagRate;
    public static final Integer[] ListofLIS2MDLMagRateConfigValues;
    public static final byte MAG_CALIBRATION_RESPONSE = 24;
    public static final byte MAG_GAIN_RESPONSE = 56;
    public static final byte MAG_SAMPLING_RATE_RESPONSE = 59;
    public static final byte SET_MAG_CALIBRATION_COMMAND = 23;
    public static final byte SET_MAG_GAIN_COMMAND = 55;
    public static final byte SET_MAG_SAMPLING_RATE_COMMAND = 58;
    public static final ChannelDetails channelLIS2MDLMagX;
    public static final ChannelDetails channelLIS2MDLMagY;
    public static final ChannelDetails channelLIS2MDLMagZ;
    public static final ConfigOptionDetailsSensor configOptionMagRange;
    public static final ConfigOptionDetailsSensor configOptionMagRate;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLisMag;
    public static final SensorDetailsRef sensorLIS2MDLMag;
    private static final long serialVersionUID = -2366590050010873738L;

    static {
        double[][] dArr = {new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, -1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        DefaultAlignmentLIS2MDL = dArr;
        DefaultAlignmentMatrixMagShimmer3r = dArr;
        String[] strArr = {"10.0Hz", "20.0Hz", "50.0Hz", "100.0Hz"};
        ListofLIS2MDLMagRate = strArr;
        Integer[] numArr = {0, 1, 2, 3};
        ListofLIS2MDLMagRateConfigValues = numArr;
        String[] strArr2 = {"+/- 49.152Ga"};
        ListofLIS2MDLMagRange = strArr2;
        Integer[] numArr2 = {0};
        ListofLIS2MDLMagRangeConfigValues = numArr2;
        configOptionMagRange = new ConfigOptionDetailsSensor("Mag Range", DatabaseConfigHandle.MAG_RANGE, strArr2, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2MDL);
        configOptionMagRate = new ConfigOptionDetailsSensor("Mag Rate", DatabaseConfigHandle.MAG_RATE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2MDL);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(32L, 32L, "Magnetometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2MDL, Arrays.asList("Mag Range", "Mag Rate"), Arrays.asList(ObjectClusterSensorName.MAG_X, ObjectClusterSensorName.MAG_Y, ObjectClusterSensorName.MAG_Z));
        sensorLIS2MDLMag = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(42, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.MAG_X, ObjectClusterSensorName.MAG_X, DatabaseChannelHandles.MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 7);
        channelLIS2MDLMagX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.MAG_Y, ObjectClusterSensorName.MAG_Y, DatabaseChannelHandles.MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 8);
        channelLIS2MDLMagY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.MAG_Z, ObjectClusterSensorName.MAG_Z, DatabaseChannelHandles.MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 9);
        channelLIS2MDLMagZ = channelDetails3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.MAG_X, channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.MAG_Z, channelDetails3);
        linkedHashMap2.put(ObjectClusterSensorName.MAG_Y, channelDetails2);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupLisMag = new SensorGroupingDetails("Magnetometer", Arrays.asList(42), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2MDL);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put((byte) 25, new BtCommandDetails((byte) 25, "GET_MAG_CALIBRATION_COMMAND", (byte) 24));
        linkedHashMap3.put((byte) 57, new BtCommandDetails((byte) 57, "GET_MAG_GAIN_COMMAND", (byte) 56));
        linkedHashMap3.put((byte) 60, new BtCommandDetails((byte) 60, "GET_MAG_SAMPLING_RATE_COMMAND", (byte) 59));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap3);
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put((byte) 23, new BtCommandDetails((byte) 23, "SET_MAG_CALIBRATION_COMMAND"));
        linkedHashMap4.put((byte) 55, new BtCommandDetails((byte) 55, "SET_MAG_GAIN_COMMAND"));
        linkedHashMap4.put((byte) 58, new BtCommandDetails((byte) 58, "SET_MAG_SAMPLING_RATE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap4);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsMag;
    public boolean mIsUsingDefaultMagParam;
    protected int mLISMagRate;
    protected boolean mLowPowerMag;
    protected int mMagRange;
    protected int mSensorIdMag;
    private CalibDetailsKinematic calibDetailsMag;

    public SensorLIS2MDL() {
        super(AbstractSensor.SENSORS.LIS2MDL);
        this.mMagRange = 0;
        this.mLISMagRate = 0;
        this.mIsUsingDefaultMagParam = true;
        this.mSensorIdMag = -1;
        this.mLowPowerMag = false;
        this.mCurrentCalibDetailsMag = null;
        this.calibDetailsMag = new CalibDetailsKinematic(ListofLIS2MDLMagRangeConfigValues[0].intValue(), ListofLIS2MDLMagRange[0], DefaultAlignmentMatrixMagShimmer3r, DefaultSensitivityMatrixMagShimmer3r, DefaultOffsetVectorMagShimmer3r);
        initialise();
    }

    public SensorLIS2MDL(ShimmerObject shimmerObject) {
        super(AbstractSensor.SENSORS.LIS2MDL, shimmerObject);
        this.mMagRange = 0;
        this.mLISMagRate = 0;
        this.mIsUsingDefaultMagParam = true;
        this.mSensorIdMag = -1;
        this.mLowPowerMag = false;
        this.mCurrentCalibDetailsMag = null;
        this.calibDetailsMag = new CalibDetailsKinematic(ListofLIS2MDLMagRangeConfigValues[0].intValue(), ListofLIS2MDLMagRange[0], DefaultAlignmentMatrixMagShimmer3r, DefaultSensitivityMatrixMagShimmer3r, DefaultOffsetVectorMagShimmer3r);
        initialise();
    }

    public SensorLIS2MDL(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.LIS2MDL, shimmerDevice);
        this.mMagRange = 0;
        this.mLISMagRate = 0;
        this.mIsUsingDefaultMagParam = true;
        this.mSensorIdMag = -1;
        this.mLowPowerMag = false;
        this.mCurrentCalibDetailsMag = null;
        this.calibDetailsMag = new CalibDetailsKinematic(ListofLIS2MDLMagRangeConfigValues[0].intValue(), ListofLIS2MDLMagRange[0], DefaultAlignmentMatrixMagShimmer3r, DefaultSensitivityMatrixMagShimmer3r, DefaultOffsetVectorMagShimmer3r);
        initialise();
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    public int getLIS2MDLMagRate() {
        return this.mLISMagRate;
    }

    public void setLIS2MDLMagRate(int i) {
        this.mLISMagRate = i;
    }

    public int getMagRange() {
        return this.mMagRange;
    }

    public int getMagRateFromFreqForSensor(boolean z, double d, int i) {
        if (!z || i == 0 || d <= 10.0d) {
            return 0;
        }
        if (d <= 20.0d) {
            return 1;
        }
        return d <= 50.0d ? 2 : 3;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public boolean isLowPowerMagEnabled() {
        return this.mLowPowerMag;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    public void setLISMagRateInternal(int i) {
        this.mLISMagRate = i;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.clear();
        addConfigOption(configOptionMagRange);
        addConfigOption(configOptionMagRate);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.MAG_3R.ordinal()), sensorGroupLisMag);
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return new ActionSetting(communication_type);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.MAG_RATE, Integer.valueOf(getLIS2MDLMagRate()));
        linkedHashMap.put(DatabaseConfigHandle.MAG_RANGE, Integer.valueOf(getMagRange()));
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsMag(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_CALIB_TIME);
        return linkedHashMap;
    }

    public CalibDetailsKinematic getCurrentCalibDetailsMag() {
        if (this.mCurrentCalibDetailsMag == null) {
            updateCurrentMagCalibInUse();
        }
        return this.mCurrentCalibDetailsMag;
    }

    public void updateCurrentMagCalibInUse() {
        this.mCurrentCalibDetailsMag = getCurrentCalibDetailsIfKinematic(this.mSensorIdMag, getMagRange());
    }

    public void setLISMagRange(int i) {
        if (ArrayUtils.contains(ListofLIS2MDLMagRangeConfigValues, Integer.valueOf(i))) {
            this.mMagRange = i;
            updateCurrentMagCalibInUse();
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MAG_LPM)) {
            setLowPowerMag(((Double) linkedHashMap.get(DatabaseConfigHandle.MAG_LPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MAG_RANGE)) {
            setLISMagRange(((Double) linkedHashMap.get(DatabaseConfigHandle.MAG_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MAG_RATE)) {
            setLIS2MDLMagRate(((Double) linkedHashMap.get(DatabaseConfigHandle.MAG_RATE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 42, getMagRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdMag = 42;
        super.initialise();
        this.mMagRange = ListofLIS2MDLMagRangeConfigValues[0].intValue();
        updateCurrentMagCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsMag.mRangeValue), this.calibDetailsMag);
        setCalibrationMapPerSensor(42, treeMap);
        updateCurrentMagCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean isSensorUsingDefaultCal(int i) {
        if (i == 42) {
            return isUsingDefaultMagParam();
        }
        return false;
    }

    public int setLIS2MDLAltMagRateFromFreq(double d) {
        boolean zIsSensorEnabled = isSensorEnabled(this.mSensorIdMag);
        if (isLowPowerMagEnabled()) {
            this.mLISMagRate = getMagRateFromFreqForSensor(zIsSensorEnabled, d, 0);
        } else {
            this.mLISMagRate = getMagRateFromFreqForSensor(zIsSensorEnabled, d, -1);
        }
        return this.mLISMagRate;
    }

    public boolean checkLowPowerMag() {
        setLowPowerMag(getLIS2MDLMagRate() == 0);
        return isLowPowerMagEnabled();
    }

    public int getLowPowerMagEnabled() {
        return isLowPowerMagEnabled() ? 1 : 0;
    }

    public void setLowPowerMag(boolean z) {
        this.mLowPowerMag = z;
        if (this.mShimmerDevice != null) {
            setLIS2MDLAltMagRateFromFreq(getSamplingRateShimmer());
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        if (mEnableCalibration && sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Magnetometer") && this.mCurrentCalibDetailsMag != null) {
            double[] dArr = new double[3];
            for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_X)) {
                    dArr[0] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_Y)) {
                    dArr[1] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_Z)) {
                    dArr[2] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                }
            }
            double[] dArrCalibrateInertialSensorData = UtilCalibration.calibrateInertialSensorData(dArr, this.mCurrentCalibDetailsMag);
            if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Magnetometer")) {
                for (ChannelDetails channelDetails2 : sensorDetails.mListOfChannels) {
                    if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_X)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, isUsingDefaultMagParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_Y)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultMagParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_Z)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1, isUsingDefaultMagParam());
                    }
                }
            }
            if (this.mIsDebugOutput) {
                super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.MAG_X, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_Y, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_Z, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_X, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.MAG_Y, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.MAG_Z, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
            }
        }
        return objectClusterProcessDataCommon;
    }

    public boolean isUsingDefaultMagParam() {
        return getCurrentCalibDetailsMag().isUsingDefaultParameters();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(this.mSensorIdMag)) {
            return;
        }
        setDefaultLisMagSensorConfig(false);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            int i = configByteLayoutShimmer3.idxConfigSetupByte2;
            bArr[i] = (byte) (bArr[i] | ((byte) ((getLIS2MDLMagRate() & configByteLayoutShimmer3.maskLSM303DLHCMagSamplingRate) << configByteLayoutShimmer3.bitShiftLSM303DLHCMagSamplingRate)));
            System.arraycopy(generateCalParamLIS2MDLMag(), 0, bArr, configByteLayoutShimmer3.idxLSM303DLHCMagCalibration, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
        }
    }

    public byte[] generateCalParamLIS2MDLMag() {
        return getCurrentCalibDetailsMag().generateCalParamByteArray();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            setLIS2MDLMagRate((bArr[configByteLayoutShimmer3.idxConfigSetupByte2] >> configByteLayoutShimmer3.bitShiftLSM303DLHCMagSamplingRate) & configByteLayoutShimmer3.maskLSM303DLHCMagSamplingRate);
            checkLowPowerMag();
            if (shimmerDevice.isConnected()) {
                getCurrentCalibDetailsMag().mCalibReadSource = CalibDetails.CALIB_READ_SOURCE.INFOMEM;
            }
            byte[] bArr2 = new byte[configByteLayoutShimmer3.lengthGeneralCalibrationBytes];
            System.arraycopy(bArr, configByteLayoutShimmer3.idxLSM303DLHCMagCalibration, bArr2, 0, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            parseCalibParamFromPacketMag(bArr2, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
        }
    }

    public void parseCalibParamFromPacketMag(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        getCurrentCalibDetailsMag().parseCalParamByteArray(bArr, calib_read_source);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        Object configValueUsingConfigLabelCommon;
        str.hashCode();
        switch (str) {
            case "Mag Rate":
                setLIS2MDLMagRate(((Integer) obj).intValue());
                configValueUsingConfigLabelCommon = null;
                break;
            case "Rate":
                if (num.intValue() == this.mSensorIdMag) {
                    setConfigValueUsingConfigLabel("Mag Rate", obj);
                }
                configValueUsingConfigLabelCommon = null;
                break;
            case "Range":
                if (num.intValue() == this.mSensorIdMag) {
                    setConfigValueUsingConfigLabel("Mag Range", obj);
                }
                configValueUsingConfigLabelCommon = null;
                break;
            case "Mag Low-Power Mode":
                setLowPowerMag(((Boolean) obj).booleanValue());
                configValueUsingConfigLabelCommon = null;
                break;
            case "Mag Range":
                setLISMagRange(((Integer) obj).intValue());
                configValueUsingConfigLabelCommon = null;
                break;
            default:
                configValueUsingConfigLabelCommon = super.setConfigValueUsingConfigLabelCommon(num, str, obj);
                break;
        }
        if (str.equals("Mag Rate")) {
            checkConfigOptionValues(str);
        }
        return configValueUsingConfigLabelCommon;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        if (str.equals("Mag Rate")) {
            checkConfigOptionValues(str);
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -73151539:
                if (str.equals("Mag Rate")) {
                    c = 0;
                    break;
                }
                break;
            case 2539776:
                if (str.equals(AbstractSensor.GuiLabelConfigCommon.RATE)) {
                    c = 1;
                    break;
                }
                break;
            case 78727453:
                if (str.equals("Range")) {
                    c = 2;
                    break;
                }
                break;
            case 1935373028:
                if (str.equals("Mag Low-Power Mode")) {
                    c = 3;
                    break;
                }
                break;
            case 2027263984:
                if (str.equals("Mag Range")) {
                    c = 4;
                    break;
                }
                break;
        }
        Integer numValueOf = null;
        switch (c) {
            case 0:
                return Integer.valueOf(getLIS2MDLMagRate());
            case 1:
                if (num.intValue() == this.mSensorIdMag) {
                    return getConfigValueUsingConfigLabel("Mag Rate");
                }
                return null;
            case 2:
                break;
            case 3:
                return Boolean.valueOf(isLowPowerMagEnabled());
            case 4:
                numValueOf = Integer.valueOf(getMagRange());
                break;
            default:
                return super.getConfigValueUsingConfigLabelCommon(num, str);
        }
        return num.intValue() == this.mSensorIdMag ? getConfigValueUsingConfigLabel("Mag Range") : numValueOf;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        setLIS2MDLAltMagRateFromFreq(d);
    }

    public void setDefaultLisMagSensorConfig(boolean z) {
        if (z) {
            setLISMagRange(0);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        if (i != this.mSensorIdMag) {
            return true;
        }
        setDefaultLisMagSensorConfig(z);
        return true;
    }

    public double getCalibTimeMag() {
        return this.mCurrentCalibDetailsMag.getCalibTimeMs();
    }

    public boolean isUsingValidMagParam() {
        return (UtilShimmer.isAllZeros(getAlignmentMatrixMag()) || UtilShimmer.isAllZeros(getSensitivityMatrixMag())) ? false : true;
    }

    public double[][] getAlignmentMatrixMag() {
        return getCurrentCalibDetailsMag().getValidAlignmentMatrix();
    }

    public void updateIsUsingDefaultMagParam() {
        this.mIsUsingDefaultMagParam = getCurrentCalibDetailsMag().isUsingDefaultParameters();
    }

    public double[][] getSensitivityMatrixMag() {
        return getCurrentCalibDetailsMag().getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixMag() {
        return getCurrentCalibDetailsMag().getValidOffsetVector();
    }

    public static class DatabaseChannelHandles {
        public static final String MAG_X = "LIS2MDL_MAG_X";
        public static final String MAG_Y = "LIS2MDL_MAG_Y";
        public static final String MAG_Z = "LIS2MDL_MAG_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String MAG_CALIB_TIME = "LIS2MDL_Mag_Calib_Time";
        public static final String MAG_LPM = "LIS2MDL_Mag_LPM";
        public static final String MAG_RANGE = "LIS2MDL_Mag_Range";
        public static final String MAG_RATE = "LIS2MDL_Mag_Rate";
        public static final String MAG_OFFSET_X = "LIS2MDL_Mag_Offset_X";
        public static final String MAG_OFFSET_Y = "LIS2MDL_Mag_Offset_Y";
        public static final String MAG_OFFSET_Z = "LIS2MDL_Mag_Offset_Z";
        public static final String MAG_GAIN_X = "LIS2MDL_Mag_Gain_X";
        public static final String MAG_GAIN_Y = "LIS2MDL_Mag_Gain_Y";
        public static final String MAG_GAIN_Z = "LIS2MDL_Mag_Gain_Z";
        public static final String MAG_ALIGN_XX = "LIS2MDL_Mag_Align_XX";
        public static final String MAG_ALIGN_XY = "LIS2MDL_Mag_Align_XY";
        public static final String MAG_ALIGN_XZ = "LIS2MDL_Mag_Align_XZ";
        public static final String MAG_ALIGN_YX = "LIS2MDL_Mag_Align_YX";
        public static final String MAG_ALIGN_YY = "LIS2MDL_Mag_Align_YY";
        public static final String MAG_ALIGN_YZ = "LIS2MDL_Mag_Align_YZ";
        public static final String MAG_ALIGN_ZX = "LIS2MDL_Mag_Align_ZX";
        public static final String MAG_ALIGN_ZY = "LIS2MDL_Mag_Align_ZY";
        public static final String MAG_ALIGN_ZZ = "LIS2MDL_Mag_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MAG = Arrays.asList(MAG_OFFSET_X, MAG_OFFSET_Y, MAG_OFFSET_Z, MAG_GAIN_X, MAG_GAIN_Y, MAG_GAIN_Z, MAG_ALIGN_XX, MAG_ALIGN_XY, MAG_ALIGN_XZ, MAG_ALIGN_YX, MAG_ALIGN_YY, MAG_ALIGN_YZ, MAG_ALIGN_ZX, MAG_ALIGN_ZY, MAG_ALIGN_ZZ);
    }

    public static class ObjectClusterSensorName {
        public static String MAG_X = "Mag_X";
        public static String MAG_Y = "Mag_Y";
        public static String MAG_Z = "Mag_Z";
    }

    public class GuiLabelConfig {
        public static final String LIS2MDL_MAG_CALIB_PARAM = "Mag Calibration Details";
        public static final String LIS2MDL_MAG_DEFAULT_CALIB = "Mag Default Calibration";
        public static final String LIS2MDL_MAG_LP = "Mag Low-Power Mode";
        public static final String LIS2MDL_MAG_RANGE = "Mag Range";
        public static final String LIS2MDL_MAG_RATE = "Mag Rate";
        public static final String LIS2MDL_MAG_VALID_CALIB = "Mag Valid Calibration";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String MAG = "Magnetometer";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String MAG = "Magnetometer";

        public LABEL_SENSOR_TILE() {
        }
    }
}
