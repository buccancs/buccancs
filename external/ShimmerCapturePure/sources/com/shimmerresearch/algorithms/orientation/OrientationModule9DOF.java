package com.shimmerresearch.algorithms.orientation;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.orientation.OrientationModule;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class OrientationModule9DOF extends OrientationModule {
    public static final String LN = "_LN";
    public static final String WR = "_WR";
    public static final AlgorithmDetails algo9DoFOrientation_LN_Acc;
    public static final AlgorithmDetails algo9DoFOrientation_WR_Acc;
    public static final ChannelDetails channelQuatW_9DOF_WR;
    public static final ChannelDetails channelQuatX_9DOF_WR;
    public static final ChannelDetails channelQuatY_9DOF_WR;
    public static final ChannelDetails channelQuatZ_9DOF_WR;
    public static final List<ChannelDetails> listChannelsAxisAngle9DOF_LN;
    public static final List<ChannelDetails> listChannelsAxisAngle9DOF_WR;
    public static final List<ChannelDetails> listChannelsEuler9DOF_LN;
    public static final List<ChannelDetails> listChannelsEuler9DOF_WR;
    public static final List<ChannelDetails> listChannelsQuat9DOF_LN;
    public static final List<ChannelDetails> listChannelsQuat9DOF_WR;
    public static final Map<String, AlgorithmDetails> mAlgorithmMapRef;
    public static final SensorGroupingDetails sGD9Dof;
    private static final ChannelDetails channelAngleA_9DOF_LN;
    private static final ChannelDetails channelAngleA_9DOF_WR;
    private static final ChannelDetails channelAngleX_9DOF_LN;
    private static final ChannelDetails channelAngleX_9DOF_WR;
    private static final ChannelDetails channelAngleY_9DOF_LN;
    private static final ChannelDetails channelAngleY_9DOF_WR;
    private static final ChannelDetails channelAngleZ_9DOF_LN;
    private static final ChannelDetails channelAngleZ_9DOF_WR;
    private static final ChannelDetails channelQuatW_9DOF_LN;
    private static final ChannelDetails channelQuatX_9DOF_LN;
    private static final ChannelDetails channelQuatY_9DOF_LN;
    private static final ChannelDetails channelQuatZ_9DOF_LN;
    private static final ChannelDetails channel_Euler_Pitch_9DOF_LN;
    private static final ChannelDetails channel_Euler_Pitch_9DOF_WR;
    private static final ChannelDetails channel_Euler_Roll_9DOF_LN;
    private static final ChannelDetails channel_Euler_Roll_9DOF_WR;
    private static final ChannelDetails channel_Euler_Yaw_9DOF_LN;
    private static final ChannelDetails channel_Euler_Yaw_9DOF_WR;
    private static final long serialVersionUID = -4174847829978293223L;

    static {
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_YAW_LN, ObjectClusterSensorName.EULER_9DOF_YAW_LN, ObjectClusterSensorName.EULER_9DOF_YAW_LN, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Yaw_9DOF_LN = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_PITCH_LN, ObjectClusterSensorName.EULER_9DOF_PITCH_LN, ObjectClusterSensorName.EULER_9DOF_PITCH_LN, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Pitch_9DOF_LN = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_ROLL_LN, ObjectClusterSensorName.EULER_9DOF_ROLL_LN, ObjectClusterSensorName.EULER_9DOF_ROLL_LN, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Roll_9DOF_LN = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleA_9DOF_LN = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleX_9DOF_LN = channelDetails5;
        ChannelDetails channelDetails6 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleY_9DOF_LN = channelDetails6;
        ChannelDetails channelDetails7 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_LN, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleZ_9DOF_LN = channelDetails7;
        ChannelDetails channelDetails8 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_W_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_W_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_W_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatW_9DOF_LN = channelDetails8;
        ChannelDetails channelDetails9 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_X_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_X_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_X_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatX_9DOF_LN = channelDetails9;
        ChannelDetails channelDetails10 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatY_9DOF_LN = channelDetails10;
        ChannelDetails channelDetails11 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_LN, ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_LN, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatZ_9DOF_LN = channelDetails11;
        ChannelDetails channelDetails12 = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_YAW_WR, ObjectClusterSensorName.EULER_9DOF_YAW_WR, ObjectClusterSensorName.EULER_9DOF_YAW_WR, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Yaw_9DOF_WR = channelDetails12;
        ChannelDetails channelDetails13 = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_PITCH_WR, ObjectClusterSensorName.EULER_9DOF_PITCH_WR, ObjectClusterSensorName.EULER_9DOF_PITCH_WR, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Pitch_9DOF_WR = channelDetails13;
        ChannelDetails channelDetails14 = new ChannelDetails(ObjectClusterSensorName.EULER_9DOF_ROLL_WR, ObjectClusterSensorName.EULER_9DOF_ROLL_WR, ObjectClusterSensorName.EULER_9DOF_ROLL_WR, Configuration.CHANNEL_UNITS.DEGREES, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channel_Euler_Roll_9DOF_WR = channelDetails14;
        ChannelDetails channelDetails15 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleA_9DOF_WR = channelDetails15;
        ChannelDetails channelDetails16 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleX_9DOF_WR = channelDetails16;
        ChannelDetails channelDetails17 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleY_9DOF_WR = channelDetails17;
        ChannelDetails channelDetails18 = new ChannelDetails(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_WR, ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelAngleZ_9DOF_WR = channelDetails18;
        ChannelDetails channelDetails19 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_W_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_W_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_W_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatW_9DOF_WR = channelDetails19;
        ChannelDetails channelDetails20 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_X_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_X_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_X_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatX_9DOF_WR = channelDetails20;
        ChannelDetails channelDetails21 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatY_9DOF_WR = channelDetails21;
        ChannelDetails channelDetails22 = new ChannelDetails(ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_WR, ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_WR, Configuration.CHANNEL_UNITS.NO_UNITS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL));
        channelQuatZ_9DOF_WR = channelDetails22;
        List<ChannelDetails> listAsList = Arrays.asList(channelDetails19, channelDetails20, channelDetails21, channelDetails22);
        listChannelsQuat9DOF_WR = listAsList;
        listChannelsAxisAngle9DOF_LN = Arrays.asList(channelDetails4, channelDetails5, channelDetails6, channelDetails7);
        listChannelsQuat9DOF_LN = Arrays.asList(channelDetails8, channelDetails9, channelDetails10, channelDetails11);
        listChannelsAxisAngle9DOF_WR = Arrays.asList(channelDetails15, channelDetails16, channelDetails17, channelDetails18);
        List<ChannelDetails> listAsList2 = Arrays.asList(channelDetails, channelDetails2, channelDetails3);
        listChannelsEuler9DOF_LN = listAsList2;
        listChannelsEuler9DOF_WR = Arrays.asList(channelDetails12, channelDetails13, channelDetails14);
        AlgorithmDetails algorithmDetails = new AlgorithmDetails("LN_Acc_9DoF", OrientationModule.GuiLabelConfig.ORIENTATAION_LN, Arrays.asList(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_X, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_X, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Y, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Z, Configuration.Shimmer3.ObjectClusterSensorName.MAG_X, Configuration.Shimmer3.ObjectClusterSensorName.MAG_Y, Configuration.Shimmer3.ObjectClusterSensorName.MAG_Z), 3145728L, Arrays.asList(2, 30, 32, 37, 38, 42), Configuration.CHANNEL_UNITS.NO_UNITS, AlgorithmDetails.SENSOR_CHECK_METHOD.ANY, listAsList2);
        algo9DoFOrientation_LN_Acc = algorithmDetails;
        AlgorithmDetails algorithmDetails2 = new AlgorithmDetails("WR_Acc_9DoF", OrientationModule.GuiLabelConfig.ORIENTATAION_WR, Arrays.asList(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_X, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Y, Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Z, Configuration.Shimmer3.ObjectClusterSensorName.MAG_X, Configuration.Shimmer3.ObjectClusterSensorName.MAG_Y, Configuration.Shimmer3.ObjectClusterSensorName.MAG_Z), 196608L, Arrays.asList(31, 30, 32, 39, 38, 42), Configuration.CHANNEL_UNITS.NO_UNITS, AlgorithmDetails.SENSOR_CHECK_METHOD.ANY, listAsList);
        algo9DoFOrientation_WR_Acc = algorithmDetails2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(algorithmDetails.mAlgorithmName, algorithmDetails);
        linkedHashMap.put(algorithmDetails2.mAlgorithmName, algorithmDetails2);
        mAlgorithmMapRef = Collections.unmodifiableMap(linkedHashMap);
        sGD9Dof = new SensorGroupingDetails(Configuration.Shimmer3.GuiLabelAlgorithmGrouping.ORIENTATION_9DOF.getTileText(), (List<AlgorithmDetails>) Arrays.asList(algorithmDetails, algorithmDetails2), (List<String>) Arrays.asList(GuiLabelConfig.QUATERNION_OUTPUT_9DOF, GuiLabelConfig.EULER_OUTPUT_9DOF), (Integer) 0);
    }

    public OrientationModule9DOF(ShimmerDevice shimmerDevice, AlgorithmDetails algorithmDetails, double d) {
        super(shimmerDevice, algorithmDetails);
        setupAlgorithm();
        setShimmerSamplingRate(d);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void parseConfigMapFromDb(LinkedHashMap<String, Object> linkedHashMap) {
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setFilteringOption() {
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setGeneralAlgorithmName() {
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setMinSamplingRateForAlgorithm() {
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setupAlgorithm() {
        super.setupAlgorithm();
        this.mAlgorithmType = AbstractAlgorithm.ALGORITHM_TYPE.ALGORITHM_TYPE_CONTINUOUS;
        this.mAlgorithmResultType = AbstractAlgorithm.ALGORITHM_RESULT_TYPE.ALGORITHM_RESULT_TYPE_SINGLE_OBJECT_CLUSTER;
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setSupportedVerInfo() {
        this.mListOfCompatibleSVO.add(svoSh3Module);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.put(GuiLabelConfig.QUATERNION_OUTPUT_9DOF, configOptionQuatOutput);
        this.mConfigOptionsMap.put(GuiLabelConfig.EULER_OUTPUT_9DOF, configOptionEulerOutput);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void generateAlgorithmGroupingMap() {
        this.mMapOfAlgorithmGrouping.put(Integer.valueOf(Configuration.Shimmer3.GuiLabelAlgorithmGrouping.ORIENTATION_9DOF.ordinal()), sGD9Dof);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void initialize() throws Exception {
        double shimmerSamplingRate = 1.0d / getShimmerSamplingRate();
        if (this.mAlgorithmName.equals("LN_Acc_9DoF") || this.mAlgorithmName.equals("WR_Acc_9DoF")) {
            this.orientationType = OrientationModule.ORIENTATION_TYPE.NINE_DOF;
            if (this.orientationAlgorithm == null) {
                this.orientationAlgorithm = new GradDes3DOrientation(shimmerSamplingRate);
            } else {
                this.orientationAlgorithm.setSamplingPeriod(shimmerSamplingRate);
            }
        }
        if (this.mAlgorithmName.equals("LN_Acc_9DoF")) {
            setAccelerometer(OrientationModule.GuiLabelConfig.ORIENTATAION_LN);
        } else {
            setAccelerometer(OrientationModule.GuiLabelConfig.ORIENTATAION_WR);
        }
    }

    @Override // com.shimmerresearch.algorithms.orientation.OrientationModule
    public Orientation3DObject applyOrientationAlgorithm() {
        return this.orientationAlgorithm.update(this.accValues.x, this.accValues.y, this.accValues.z, this.gyroValues.x, this.gyroValues.y, this.gyroValues.z, this.magValues.x, this.magValues.y, this.magValues.z);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public List<ChannelDetails> getChannelDetails(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (this.mAlgorithmDetails.mAlgorithmName.equals("WR_Acc_9DoF")) {
            if (z || isQuaternionOutput()) {
                arrayList.addAll(listChannelsQuat9DOF_WR);
            }
            if (z || isEulerOutput()) {
                arrayList.addAll(listChannelsEuler9DOF_WR);
            }
            if (z || isAxisAngleOutput()) {
                arrayList.addAll(listChannelsAxisAngle9DOF_WR);
            }
        } else if (this.mAlgorithmDetails.mAlgorithmName.equals("LN_Acc_9DoF")) {
            if (z || isQuaternionOutput()) {
                arrayList.addAll(listChannelsQuat9DOF_LN);
            }
            if (z || isEulerOutput()) {
                arrayList.addAll(listChannelsEuler9DOF_LN);
            }
            if (z || isAxisAngleOutput()) {
                arrayList.addAll(listChannelsAxisAngle9DOF_LN);
            }
        }
        return arrayList;
    }

    public void setAccelerometer(String str) {
        this.accelerometerSensor = str;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public Object getSettings(String str) {
        str.hashCode();
        switch (str) {
            case "Quaternion_9DOF":
                return Boolean.valueOf(isQuaternionOutput());
            case "Euler_9DOF":
                return Boolean.valueOf(isEulerOutput());
            case "Sampling Rate":
                return Double.valueOf(getShimmerSamplingRate());
            default:
                return null;
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public Object getDefaultSettings(String str) {
        str.hashCode();
        switch (str) {
            case "Quaternion_9DOF":
                return true;
            case "Euler_9DOF":
                return false;
            case "Sampling Rate":
                return 512;
            default:
                return null;
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setSettings(String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "Quaternion_9DOF":
                if (obj instanceof Boolean) {
                    setQuaternionOutput(((Boolean) obj).booleanValue());
                    break;
                } else if (obj instanceof Integer) {
                    setQuaternionOutput(((Integer) obj).intValue() > 0);
                    break;
                }
                break;
            case "Euler_9DOF":
                if (obj instanceof Boolean) {
                    setEulerOutput(((Boolean) obj).booleanValue());
                    break;
                } else if (obj instanceof Integer) {
                    setEulerOutput(((Integer) obj).intValue() > 0);
                    break;
                }
                break;
            case "Sampling Rate":
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str2.isEmpty()) {
                        setShimmerSamplingRate(Double.parseDouble(str2));
                        break;
                    }
                } else if (obj instanceof Double) {
                    setShimmerSamplingRate(((Double) obj).doubleValue());
                    break;
                }
                break;
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void algorithmMapUpdateFromEnabledSensorsVars(long j) {
        setQuaternionOutput(false);
        setEulerOutput(false);
        setAxisAngleOutput(false);
        setIsEnabled(false);
        long j2 = 1048576 & j;
        if (j2 > 0 || (j & 65536) > 0) {
            setQuaternionOutput(true);
        }
        long j3 = 2097152 & j;
        if (j3 > 0 || (j & 131072) > 0) {
            setEulerOutput(true);
        }
        if (this.mAlgorithmDetails.mAlgorithmName.equals("LN_Acc_9DoF")) {
            if (j2 > 0 || j3 > 0) {
                setIsEnabled(true);
                return;
            }
            return;
        }
        if (this.mAlgorithmDetails.mAlgorithmName.equals("WR_Acc_9DoF")) {
            if ((j & 65536) > 0 || (j & 131072) > 0) {
                setIsEnabled(true);
            }
        }
    }

    @Override // com.shimmerresearch.algorithms.orientation.OrientationModule
    public ObjectCluster addQuaternionToObjectCluster(Orientation3DObject orientation3DObject, ObjectCluster objectCluster) {
        if (this.mAlgorithmName.equals("LN_Acc_9DoF")) {
            if (isEulerOutput()) {
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_YAW_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getYaw());
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_PITCH_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getPitch());
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_ROLL_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getRoll());
            }
            if (isQuaternionOutput()) {
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_W_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionW());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_X_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionX());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionY());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionZ());
            }
            if (isAxisAngleOutput()) {
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getTheta());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleX());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleY());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_LN, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleZ());
            }
        } else if (this.mAlgorithmName.equals("WR_Acc_9DoF")) {
            if (isEulerOutput()) {
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_YAW_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getYaw());
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_PITCH_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getPitch());
                objectCluster.addData(ObjectClusterSensorName.EULER_9DOF_ROLL_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getRoll());
            }
            if (isQuaternionOutput()) {
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_W_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionW());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_X_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionX());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_Y_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionY());
                objectCluster.addData(ObjectClusterSensorName.QUAT_MADGE_9DOF_Z_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getQuaternionZ());
            }
            if (isAxisAngleOutput()) {
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_A_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getTheta());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_X_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleX());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleY());
                objectCluster.addData(ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z_WR, ChannelDetails.CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.NO_UNITS, orientation3DObject.getAngleZ());
            }
        }
        return objectCluster;
    }

    public static class AlgorithmName {
        public static final String ORIENTATION_9DOF_LN = "LN_Acc_9DoF";
        public static final String ORIENTATION_9DOF_WR = "WR_Acc_9DoF";
    }

    public static class DatabaseChannelHandles {
        public static final String EULER_9DOF_PITCH = "EULER_9DOF_PITCH";
        public static final String EULER_9DOF_ROLL = "EULER_9DOF_ROLL";
        public static final String EULER_9DOF_YAW = "EULER_9DOF_YAW";
        public static final String QUARTENION_W_9DOF = "QUAT_MADGE_9DOF_W";
        public static final String QUARTENION_X_9DOF = "QUAT_MADGE_9DOF_X";
        public static final String QUARTENION_Y_9DOF = "QUAT_MADGE_9DOF_Y";
        public static final String QUARTENION_Z_9DOF = "QUAT_MADGE_9DOF_Z";
    }

    public static final class ObjectClusterSensorName {
        public static final String AXIS_ANGLE_9DOF_A = "Axis_Angle_9DOF_A";
        public static final String AXIS_ANGLE_9DOF_A_LN = "Axis_Angle_9DOF_A_LN";
        public static final String AXIS_ANGLE_9DOF_A_WR = "Axis_Angle_9DOF_A_WR";
        public static final String AXIS_ANGLE_9DOF_X = "Axis_Angle_9DOF_X";
        public static final String AXIS_ANGLE_9DOF_X_LN = "Axis_Angle_9DOF_X_LN";
        public static final String AXIS_ANGLE_9DOF_X_WR = "Axis_Angle_9DOF_X_WR";
        public static final String AXIS_ANGLE_9DOF_Y = "Axis_Angle_9DOF_Y";
        public static final String AXIS_ANGLE_9DOF_Y_LN = "Axis_Angle_9DOF_Y_LN";
        public static final String AXIS_ANGLE_9DOF_Y_WR = "Axis_Angle_9DOF_Y_WR";
        public static final String AXIS_ANGLE_9DOF_Z = "Axis_Angle_9DOF_Z";
        public static final String AXIS_ANGLE_9DOF_Z_LN = "Axis_Angle_9DOF_Z_LN";
        public static final String AXIS_ANGLE_9DOF_Z_WR = "Axis_Angle_9DOF_Z_WR";
        public static final String EULER_9DOF_PITCH = "Euler_9DOF_Pitch";
        public static final String EULER_9DOF_PITCH_LN = "Euler_9DOF_Pitch_LN";
        public static final String EULER_9DOF_PITCH_WR = "Euler_9DOF_Pitch_WR";
        public static final String EULER_9DOF_ROLL = "Euler_9DOF_Roll";
        public static final String EULER_9DOF_ROLL_LN = "Euler_9DOF_Roll_LN";
        public static final String EULER_9DOF_ROLL_WR = "Euler_9DOF_Roll_WR";
        public static final String EULER_9DOF_YAW = "Euler_9DOF_Yaw";
        public static final String EULER_9DOF_YAW_LN = "Euler_9DOF_Yaw_LN";
        public static final String EULER_9DOF_YAW_WR = "Euler_9DOF_Yaw_WR";
        public static final String QUAT_MADGE_9DOF_W = "Quat_Madge_9DOF_W";
        public static final String QUAT_MADGE_9DOF_W_LN = "Quat_Madge_9DOF_W_LN";
        public static final String QUAT_MADGE_9DOF_W_WR = "Quat_Madge_9DOF_W_WR";
        public static final String QUAT_MADGE_9DOF_X = "Quat_Madge_9DOF_X";
        public static final String QUAT_MADGE_9DOF_X_LN = "Quat_Madge_9DOF_X_LN";
        public static final String QUAT_MADGE_9DOF_X_WR = "Quat_Madge_9DOF_X_WR";
        public static final String QUAT_MADGE_9DOF_Y = "Quat_Madge_9DOF_Y";
        public static final String QUAT_MADGE_9DOF_Y_LN = "Quat_Madge_9DOF_Y_LN";
        public static final String QUAT_MADGE_9DOF_Y_WR = "Quat_Madge_9DOF_Y_WR";
        public static final String QUAT_MADGE_9DOF_Z = "Quat_Madge_9DOF_Z";
        public static final String QUAT_MADGE_9DOF_Z_LN = "Quat_Madge_9DOF_Z_LN";
        public static final String QUAT_MADGE_9DOF_Z_WR = "Quat_Madge_9DOF_Z_WR";
    }

    public class GuiLabelConfig {
        public static final String ACCELEROMETER_9DOF = "Accelerometer_9DOF";
        public static final String EULER_OUTPUT_9DOF = "Euler_9DOF";
        public static final String QUATERNION_OUTPUT_9DOF = "Quaternion_9DOF";

        public GuiLabelConfig() {
        }
    }
}
