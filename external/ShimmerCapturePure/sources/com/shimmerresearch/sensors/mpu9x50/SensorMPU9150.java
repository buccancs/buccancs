package com.shimmerresearch.sensors.mpu9x50;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.OldCalDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorMPU9150 extends SensorMPU9X50 {
    public static final ChannelDetails channelAccelMpuMplX;
    public static final ChannelDetails channelAccelMpuMplY;
    public static final ChannelDetails channelAccelMpuMplZ;
    public static final ChannelDetails channelAccelX;
    public static final ChannelDetails channelAccelY;
    public static final ChannelDetails channelAccelZ;
    public static final ChannelDetails channelEulerMpl6DofX;
    public static final ChannelDetails channelEulerMpl6DofY;
    public static final ChannelDetails channelEulerMpl6DofZ;
    public static final ChannelDetails channelEulerMpl9DofX;
    public static final ChannelDetails channelEulerMpl9DofY;
    public static final ChannelDetails channelEulerMpl9DofZ;
    public static final ChannelDetails channelGyroMpuMplX;
    public static final ChannelDetails channelGyroMpuMplY;
    public static final ChannelDetails channelGyroMpuMplZ;
    public static final ChannelDetails channelGyroX;
    public static final ChannelDetails channelGyroY;
    public static final ChannelDetails channelGyroZ;
    public static final ChannelDetails channelMagMpuMplX;
    public static final ChannelDetails channelMagMpuMplY;
    public static final ChannelDetails channelMagMpuMplZ;
    public static final ChannelDetails channelMagX;
    public static final ChannelDetails channelMagY;
    public static final ChannelDetails channelMagZ;
    public static final ChannelDetails channelMplHeading;
    public static final ChannelDetails channelMplMotion;
    public static final ChannelDetails channelMplMotionAndOrient;
    public static final ChannelDetails channelMplOrient;
    public static final ChannelDetails channelMplPedomCount;
    public static final ChannelDetails channelMplPedomTime;
    public static final ChannelDetails channelMplTapCnt;
    public static final ChannelDetails channelMplTapDir;
    public static final ChannelDetails channelMplTemperature;
    public static final ChannelDetails channelQuatDmp6DofW;
    public static final ChannelDetails channelQuatDmp6DofX;
    public static final ChannelDetails channelQuatDmp6DofY;
    public static final ChannelDetails channelQuatDmp6DofZ;
    public static final ChannelDetails channelQuatMpl6DofW;
    public static final ChannelDetails channelQuatMpl6DofX;
    public static final ChannelDetails channelQuatMpl6DofY;
    public static final ChannelDetails channelQuatMpl6DofZ;
    public static final ChannelDetails channelQuatMpl9DofW;
    public static final ChannelDetails channelQuatMpl9DofX;
    public static final ChannelDetails channelQuatMpl9DofY;
    public static final ChannelDetails channelQuatMpl9DofZ;
    public static final ConfigOptionDetailsSensor configOptionMpu9150AccelRange;
    public static final ConfigOptionDetailsSensor configOptionMpu9150Dmp;
    public static final ConfigOptionDetailsSensor configOptionMpu9150DmpGyroCal;
    public static final ConfigOptionDetailsSensor configOptionMpu9150GyroLpm;
    public static final ConfigOptionDetailsSensor configOptionMpu9150GyroRange;
    public static final ConfigOptionDetailsSensor configOptionMpu9150GyroRate;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MagRate;
    public static final ConfigOptionDetailsSensor configOptionMpu9150Mpl;
    public static final ConfigOptionDetailsSensor configOptionMpu9150Mpl9DofSensorFusion;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MplGyroCal;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MplLpf;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MplMagCal;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MplRate;
    public static final ConfigOptionDetailsSensor configOptionMpu9150MplVectorCal;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, OldCalDetails> mOldCalRangeMap;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorMpu9150AccelRef;
    public static final SensorDetailsRef sensorMpu9150GyroRef;
    public static final SensorDetailsRef sensorMpu9150MagRef;
    private static final long serialVersionUID = -8462796728357952990L;

    static {
        ChannelDetails channelDetails = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_X, SensorMPU9X50.ObjectClusterSensorName.GYRO_X, DatabaseChannelHandles.GYRO_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 10);
        channelGyroX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_Y, SensorMPU9X50.ObjectClusterSensorName.GYRO_Y, DatabaseChannelHandles.GYRO_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 11);
        channelGyroY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_Z, SensorMPU9X50.ObjectClusterSensorName.GYRO_Z, DatabaseChannelHandles.GYRO_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 12);
        channelGyroZ = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_X, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_X, DatabaseChannelHandles.ALTERNATIVE_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelX = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Y, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Y, DatabaseChannelHandles.ALTERNATIVE_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelY = channelDetails5;
        ChannelDetails channelDetails6 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Z, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Z, DatabaseChannelHandles.ALTERNATIVE_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelZ = channelDetails6;
        ChannelDetails channelDetails7 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_X, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_X, DatabaseChannelHandles.ALTERNATIVE_MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagX = channelDetails7;
        ChannelDetails channelDetails8 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Y, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Y, DatabaseChannelHandles.ALTERNATIVE_MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagY = channelDetails8;
        ChannelDetails channelDetails9 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Z, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Z, DatabaseChannelHandles.ALTERNATIVE_MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagZ = channelDetails9;
        ChannelDetails channelDetails10 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_W, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_W, DatabaseChannelHandles.MPU_QUAT_6DOF_W, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl6DofW = channelDetails10;
        ChannelDetails channelDetails11 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_X, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_X, DatabaseChannelHandles.MPU_QUAT_6DOF_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl6DofX = channelDetails11;
        ChannelDetails channelDetails12 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Y, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Y, DatabaseChannelHandles.MPU_QUAT_6DOF_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl6DofY = channelDetails12;
        ChannelDetails channelDetails13 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Z, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Z, DatabaseChannelHandles.MPU_QUAT_6DOF_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl6DofZ = channelDetails13;
        ChannelDetails channelDetails14 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_W, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_W, DatabaseChannelHandles.MPU_QUAT_9DOF_W, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl9DofW = channelDetails14;
        ChannelDetails channelDetails15 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_X, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_X, DatabaseChannelHandles.MPU_QUAT_9DOF_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl9DofX = channelDetails15;
        ChannelDetails channelDetails16 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Y, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Y, DatabaseChannelHandles.MPU_QUAT_9DOF_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl9DofY = channelDetails16;
        ChannelDetails channelDetails17 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Z, SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Z, DatabaseChannelHandles.MPU_QUAT_9DOF_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatMpl9DofZ = channelDetails17;
        ChannelDetails channelDetails18 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_X, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_X, DatabaseChannelHandles.MPU_EULER_6DOF_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl6DofX = channelDetails18;
        ChannelDetails channelDetails19 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Y, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Y, DatabaseChannelHandles.MPU_EULER_6DOF_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl6DofY = channelDetails19;
        ChannelDetails channelDetails20 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Z, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Z, DatabaseChannelHandles.MPU_EULER_6DOF_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl6DofZ = channelDetails20;
        ChannelDetails channelDetails21 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_X, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_X, DatabaseChannelHandles.MPU_EULER_9DOF_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl9DofX = channelDetails21;
        ChannelDetails channelDetails22 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Y, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Y, DatabaseChannelHandles.MPU_EULER_9DOF_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl9DofY = channelDetails22;
        ChannelDetails channelDetails23 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Z, SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Z, DatabaseChannelHandles.MPU_EULER_9DOF_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelEulerMpl9DofZ = channelDetails23;
        ChannelDetails channelDetails24 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MPL_HEADING, SensorMPU9X50.ObjectClusterSensorName.MPL_HEADING, "MPU9150_MPL_Heading", ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.DEGREES, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplHeading = channelDetails24;
        ChannelDetails channelDetails25 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MPL_TEMPERATURE, SensorMPU9X50.ObjectClusterSensorName.MPL_TEMPERATURE, DatabaseChannelHandles.MPU_TEMP, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.DEGREES_CELSIUS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplTemperature = channelDetails25;
        ChannelDetails channelDetails26 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_CNT, SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_CNT, DatabaseChannelHandles.PEDOMETER_CNT, ChannelDetails.CHANNEL_DATA_TYPE.UINT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplPedomCount = channelDetails26;
        ChannelDetails channelDetails27 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_TIME, SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_TIME, DatabaseChannelHandles.PEDOMETER_TIME, ChannelDetails.CHANNEL_DATA_TYPE.UINT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplPedomTime = channelDetails27;
        ChannelDetails channelDetails28 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.TAPDIR, SensorMPU9X50.ObjectClusterSensorName.TAPDIR, DatabaseChannelHandles.TAP_DIR, ChannelDetails.CHANNEL_DATA_TYPE.UINT8, 1, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplTapDir = channelDetails28;
        ChannelDetails channelDetails29 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.TAPCNT, SensorMPU9X50.ObjectClusterSensorName.TAPCNT, DatabaseChannelHandles.TAP_CNT, ChannelDetails.CHANNEL_DATA_TYPE.UINT8, 1, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplTapCnt = channelDetails29;
        ChannelDetails channelDetails30 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MOTIONANDORIENT, SensorMPU9X50.ObjectClusterSensorName.MOTIONANDORIENT, DatabaseChannelHandles.MOTION_AND_ORIENT, ChannelDetails.CHANNEL_DATA_TYPE.UINT8, 1, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplMotionAndOrient = channelDetails30;
        ChannelDetails channelDetails31 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MOTION, SensorMPU9X50.ObjectClusterSensorName.MOTION, DatabaseChannelHandles.MOTION, ChannelDetails.CHANNEL_DATA_TYPE.UINT8, 1, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplMotion = channelDetails31;
        ChannelDetails channelDetails32 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ORIENT, SensorMPU9X50.ObjectClusterSensorName.ORIENT, DatabaseChannelHandles.ORIENT, ChannelDetails.CHANNEL_DATA_TYPE.UINT8, 1, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMplOrient = channelDetails32;
        ChannelDetails channelDetails33 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_X, SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_X, DatabaseChannelHandles.MPU_MPL_GYRO_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGyroMpuMplX = channelDetails33;
        ChannelDetails channelDetails34 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Y, SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Y, DatabaseChannelHandles.MPU_MPL_GYRO_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGyroMpuMplY = channelDetails34;
        ChannelDetails channelDetails35 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Z, SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Z, DatabaseChannelHandles.MPU_MPL_GYRO_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGyroMpuMplZ = channelDetails35;
        ChannelDetails channelDetails36 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_X, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_X, DatabaseChannelHandles.MPU_MPL_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.GRAVITY, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelMpuMplX = channelDetails36;
        ChannelDetails channelDetails37 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Y, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Y, DatabaseChannelHandles.MPU_MPL_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.GRAVITY, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelMpuMplY = channelDetails37;
        ChannelDetails channelDetails38 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Z, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Z, DatabaseChannelHandles.MPU_MPL_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.GRAVITY, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelAccelMpuMplZ = channelDetails38;
        ChannelDetails channelDetails39 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_X, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_X, DatabaseChannelHandles.MPU_MPL_MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagMpuMplX = channelDetails39;
        ChannelDetails channelDetails40 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Y, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Y, DatabaseChannelHandles.MPU_MPL_MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagMpuMplY = channelDetails40;
        ChannelDetails channelDetails41 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Z, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Z, DatabaseChannelHandles.MPU_MPL_MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.U_TESLA, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelMagMpuMplZ = channelDetails41;
        ChannelDetails channelDetails42 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_W, SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_W, DatabaseChannelHandles.MPU_QUAT_6DOF_DMP_W, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatDmp6DofW = channelDetails42;
        ChannelDetails channelDetails43 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_X, SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_X, DatabaseChannelHandles.MPU_QUAT_6DOF_DMP_X, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatDmp6DofX = channelDetails43;
        ChannelDetails channelDetails44 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Y, SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Y, DatabaseChannelHandles.MPU_QUAT_6DOF_DMP_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatDmp6DofY = channelDetails44;
        ChannelDetails channelDetails45 = new ChannelDetails(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Z, SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Z, DatabaseChannelHandles.MPU_QUAT_6DOF_DMP_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT32, 4, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelQuatDmp6DofZ = channelDetails45;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_X, channelDetails);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_Y, channelDetails2);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_Z, channelDetails3);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_X, channelDetails4);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Y, channelDetails5);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Z, channelDetails6);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_X, channelDetails7);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Y, channelDetails8);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Z, channelDetails9);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_X, channelDetails33);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Y, channelDetails34);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Z, channelDetails35);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_X, channelDetails36);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Y, channelDetails37);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Z, channelDetails38);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_X, channelDetails39);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Y, channelDetails40);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Z, channelDetails41);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_W, channelDetails10);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_X, channelDetails11);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Y, channelDetails12);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Z, channelDetails13);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_W, channelDetails14);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_X, channelDetails15);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Y, channelDetails16);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Z, channelDetails17);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_X, channelDetails18);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Y, channelDetails19);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Z, channelDetails20);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_X, channelDetails21);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Y, channelDetails22);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Z, channelDetails23);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MPL_HEADING, channelDetails24);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MPL_TEMPERATURE, channelDetails25);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_CNT, channelDetails26);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_TIME, channelDetails27);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.TAPDIR, channelDetails28);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.TAPCNT, channelDetails29);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MOTIONANDORIENT, channelDetails30);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.MOTION, channelDetails31);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.ORIENT, channelDetails32);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_W, channelDetails42);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_X, channelDetails43);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Y, channelDetails44);
        linkedHashMap.put(SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Z, channelDetails45);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(64L, 64L, "Gyroscope", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, Arrays.asList(58), Arrays.asList("Gyro Range", "Gyro Sampling Rate"), Arrays.asList(SensorMPU9X50.ObjectClusterSensorName.GYRO_X, SensorMPU9X50.ObjectClusterSensorName.GYRO_Y, SensorMPU9X50.ObjectClusterSensorName.GYRO_Z), false);
        sensorMpu9150GyroRef = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef(4194304L, 4194304L, "Alternative Accel", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoShimmer4, Arrays.asList(59), Arrays.asList(SensorMPU9X50.GuiLabelConfig.MPU9X50_ACCEL_RANGE), Arrays.asList(SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_X, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Y, SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Z), false);
        sensorMpu9150AccelRef = sensorDetailsRef2;
        SensorDetailsRef sensorDetailsRef3 = new SensorDetailsRef(2097152L, 2097152L, "Alternative Mag", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoShimmer4, Arrays.asList(60), Arrays.asList(SensorMPU9X50.GuiLabelConfig.MPU9X50_MAG_RATE), Arrays.asList(SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_X, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Y, SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Z), false);
        sensorMpu9150MagRef = sensorDetailsRef3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(30, sensorDetailsRef);
        linkedHashMap2.put(33, sensorDetailsRef2);
        linkedHashMap2.put(34, sensorDetailsRef3);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put("gyro_250dps", new OldCalDetails("gyro_250dps", 30, 0));
        linkedHashMap3.put("gyro_500dps", new OldCalDetails("gyro_500dps", 30, 1));
        linkedHashMap3.put("gyro_1000ps", new OldCalDetails("gyro_1000ps", 30, 2));
        linkedHashMap3.put("gyro_2000dps", new OldCalDetails("gyro_2000dps", 30, 3));
        mOldCalRangeMap = Collections.unmodifiableMap(linkedHashMap3);
        configOptionMpu9150GyroRange = new ConfigOptionDetailsSensor("Gyro Range", DatabaseConfigHandle.GYRO_RANGE, ListofGyroRange, ListofMPU9X50GyroRangeConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        configOptionMpu9150AccelRange = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_ACCEL_RANGE, DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE, ListofMPU9X50AccelRange, ListofMPU9X50AccelRangeConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150DmpGyroCal = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_DMP_GYRO_CAL, DatabaseConfigHandle.MPU_GYRO, ListofMPU9150MplCalibrationOptions, ListofMPU9150MplCalibrationOptionsConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MplLpf = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_LPF, DatabaseConfigHandle.MPU_LPF, ListofMPU9150MplLpfOptions, ListofMPU9150MplLpfOptionsConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MplRate = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_RATE, DatabaseConfigHandle.MPU_MPL_SAMPLING_RATE, ListofMPU9150MplRate, ListofMPU9150MplRateConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MagRate = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MAG_RATE, DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE, ListofMPU9X50MagRate, ListofMPU9X50MagRateConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150Dmp = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_DMP, DatabaseConfigHandle.MPU_DMP, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150Mpl = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL, DatabaseConfigHandle.MPU_MPL_ENABLE, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150Mpl9DofSensorFusion = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_9DOF_SENSOR_FUSION, DatabaseConfigHandle.MPU_MPL_SENSOR_FUSION, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MplGyroCal = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_GYRO_CAL, DatabaseConfigHandle.MPU_GYRO, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MplVectorCal = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_VECTOR_CAL, DatabaseConfigHandle.MPU_MPL_VECT_COMP, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150MplMagCal = new ConfigOptionDetailsSensor(SensorMPU9X50.GuiLabelConfig.MPU9X50_MPL_MAG_CAL, DatabaseConfigHandle.MPU_MAG, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMPLSensors);
        configOptionMpu9150GyroRate = new ConfigOptionDetailsSensor("Gyro Sampling Rate", DatabaseConfigHandle.GYRO_RATE, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        configOptionMpu9150GyroLpm = new ConfigOptionDetailsSensor("Gyro Low-Power Mode", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
    }

    public SensorMPU9150() {
        super(AbstractSensor.SENSORS.MPU9X50);
        initialise();
    }

    public SensorMPU9150(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.MPU9X50, shimmerDevice);
        initialise();
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdGyro = 30;
        this.mSensorIdAccel = 33;
        this.mSensorIdMag = 34;
        super.initialise();
        setCalibSensitivityScaleFactor(this.mSensorIdGyro, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        updateCurrentGyroCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.clear();
        addConfigOption(configOptionMpu9150GyroRange);
        addConfigOption(configOptionMpu9150AccelRange);
        addConfigOption(configOptionMpu9150DmpGyroCal);
        addConfigOption(configOptionMpu9150MplLpf);
        addConfigOption(configOptionMpu9150MplRate);
        addConfigOption(configOptionMpu9150MagRate);
        addConfigOption(configOptionMpu9150Dmp);
        addConfigOption(configOptionMpu9150Mpl);
        addConfigOption(configOptionMpu9150Mpl9DofSensorFusion);
        addConfigOption(configOptionMpu9150MplGyroCal);
        addConfigOption(configOptionMpu9150MplVectorCal);
        addConfigOption(configOptionMpu9150MplMagCal);
        addConfigOption(configOptionMpu9150GyroRate);
        addConfigOption(configOptionMpu9150GyroLpm);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(mSensorMapRefCommon);
        linkedHashMap.putAll(mSensorMapRef);
        super.createLocalSensorMapWithCustomParser(linkedHashMap, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        if (this.mShimmerVerObject.isShimmerGen3()) {
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.GYRO.ordinal()), new SensorGroupingDetails("Gyroscope", Arrays.asList(Integer.valueOf(this.mSensorIdGyro)), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW));
        }
        this.mShimmerVerObject.isSupportedMpl();
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.GYRO_RATE, Integer.valueOf(getMPU9X50GyroAccelRate()));
        linkedHashMap.put(DatabaseConfigHandle.GYRO_RANGE, Integer.valueOf(getGyroRange()));
        linkedHashMap.put(DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE, Integer.valueOf(getMPU9X50AccelRange()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_DMP, Integer.valueOf(getMPU9X50DMP()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_LPF, Integer.valueOf(getMPU9X50LPF()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MOT_CAL_CFG, Integer.valueOf(getMPU9X50MotCalCfg()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MPL_SAMPLING_RATE, Integer.valueOf(getMPU9X50MPLSamplingRate()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE, Integer.valueOf(getMPU9X50MagSamplingRate()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MPL_SENSOR_FUSION, Double.valueOf(getMPLSensorFusion()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MPL_GYRO_TC, Double.valueOf(getMPLGyroCalTC()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MPL_VECT_COMP, Double.valueOf(getMPLVectCompCal()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MAG_DIST, Double.valueOf(getMPLMagDistCal()));
        linkedHashMap.put(DatabaseConfigHandle.MPU_MPL_ENABLE, Double.valueOf(getMPLEnable()));
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsGyro(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO, DatabaseConfigHandle.GYRO_CALIB_TIME);
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_ACC, getOffsetVectorMPLAccel(), getSensitivityMatrixMPLAccel(), getAlignmentMatrixMPLAccel());
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_MAG, getOffsetVectorMPLMag(), getSensitivityMatrixMPLMag(), getAlignmentMatrixMPLMag());
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_GYRO, getOffsetVectorMPLGyro(), getSensitivityMatrixMPLGyro(), getAlignmentMatrixMPLGyro());
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.GYRO_RATE)) {
            setMPU9X50GyroAccelRate(((Double) linkedHashMap.get(DatabaseConfigHandle.GYRO_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.GYRO_RANGE)) {
            setGyroRange(((Double) linkedHashMap.get(DatabaseConfigHandle.GYRO_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE)) {
            setMPU9X50AccelRange(((Double) linkedHashMap.get(DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_DMP)) {
            setMPU9150DMP(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_DMP)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_LPF)) {
            setMPU9X50LPF(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_LPF)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MOT_CAL_CFG)) {
            setMPU9X150MotCalCfg(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MOT_CAL_CFG)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MPL_SAMPLING_RATE)) {
            setMPU9150MPLSamplingRate(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MPL_SAMPLING_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE)) {
            setMPU9X50MagSamplingRate(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MPL_SENSOR_FUSION)) {
            setMPLSensorFusion(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MPL_SENSOR_FUSION)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MPL_GYRO_TC)) {
            setMPLGyroCalTC(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MPL_GYRO_TC)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MPL_VECT_COMP)) {
            setMPLVectCompCal(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MPL_VECT_COMP)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MAG_DIST)) {
            setMPLMagDistCal(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MAG_DIST)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MPU_MPL_ENABLE)) {
            setMPLEnabled(((Double) linkedHashMap.get(DatabaseConfigHandle.MPU_MPL_ENABLE)).doubleValue() > 0.0d);
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, this.mSensorIdGyro, getGyroRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO, DatabaseConfigHandle.GYRO_CALIB_TIME);
        parseCalibDetailsKinematicFromDb(linkedHashMap, 59, getMPU9X50AccelRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_ACC);
        parseCalibDetailsKinematicFromDb(linkedHashMap, 60, 0, DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_MAG);
        parseCalibDetailsKinematicFromDb(linkedHashMap, 58, getGyroRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_GYRO);
    }

    public static class DatabaseChannelHandles {
        public static final String ALTERNATIVE_ACC_X = "MPU9150_ACC_X";
        public static final String ALTERNATIVE_ACC_Y = "MPU9150_ACC_Y";
        public static final String ALTERNATIVE_ACC_Z = "MPU9150_ACC_Z";
        public static final String ALTERNATIVE_MAG_X = "MPU9150_MAG_X";
        public static final String ALTERNATIVE_MAG_Y = "MPU9150_MAG_Y";
        public static final String ALTERNATIVE_MAG_Z = "MPU9150_MAG_Z";
        public static final String GYRO_X = "MPU9150_GYRO_X";
        public static final String GYRO_Y = "MPU9150_GYRO_Y";
        public static final String GYRO_Z = "MPU9150_GYRO_Z";
        public static final String MOTION = "MPU9150_MPL_MOTION";
        public static final String MOTION_AND_ORIENT = "MPU9150_MPL_MOTION_AND_ORIENT";
        public static final String MPU_EULER_6DOF_X = "MPU9150_MPL_EULER_6DOF_X";
        public static final String MPU_EULER_6DOF_Y = "MPU9150_MPL_EULER_6DOF_Y";
        public static final String MPU_EULER_6DOF_Z = "MPU9150_MPL_EULER_6DOF_Z";
        public static final String MPU_EULER_9DOF_X = "MPU9150_MPL_EULER_9DOF_X";
        public static final String MPU_EULER_9DOF_Y = "MPU9150_MPL_EULER_9DOF_Y";
        public static final String MPU_EULER_9DOF_Z = "MPU9150_MPL_EULER_9DOF_Z";
        public static final String MPU_HEADING = "MPU9150_MPL_Heading";
        public static final String MPU_MPL_ACC_X = "MPU9150_MPL_ACC_X_CAL";
        public static final String MPU_MPL_ACC_Y = "MPU9150_MPL_ACC_Y_CAL";
        public static final String MPU_MPL_ACC_Z = "MPU9150_MPL_ACC_Z_CAL";
        public static final String MPU_MPL_GYRO_X = "MPU9150_MPL_GYRO_X_CAL";
        public static final String MPU_MPL_GYRO_Y = "MPU9150_MPL_GYRO_Y_CAL";
        public static final String MPU_MPL_GYRO_Z = "MPU9150_MPL_GYRO_Z_CAL";
        public static final String MPU_MPL_MAG_X = "MPU9150_MPL_MAG_X_CAL";
        public static final String MPU_MPL_MAG_Y = "MPU9150_MPL_MAG_Y_CAL";
        public static final String MPU_MPL_MAG_Z = "MPU9150_MPL_MAG_Z_CAL";
        public static final String MPU_QUAT_6DOF_DMP_W = "MPU9150_QUAT_6DOF_W";
        public static final String MPU_QUAT_6DOF_DMP_X = "MPU9150_QUAT_6DOF_X";
        public static final String MPU_QUAT_6DOF_DMP_Y = "MPU9150_QUAT_6DOF_Y";
        public static final String MPU_QUAT_6DOF_DMP_Z = "MPU9150_QUAT_6DOF_Z";
        public static final String MPU_QUAT_6DOF_W = "MPU9150_MPL_QUAT_6DOF_W";
        public static final String MPU_QUAT_6DOF_X = "MPU9150_MPL_QUAT_6DOF_X";
        public static final String MPU_QUAT_6DOF_Y = "MPU9150_MPL_QUAT_6DOF_Y";
        public static final String MPU_QUAT_6DOF_Z = "MPU9150_MPL_QUAT_6DOF_Z";
        public static final String MPU_QUAT_9DOF_W = "MPU9150_MPL_QUAT_9DOF_W";
        public static final String MPU_QUAT_9DOF_X = "MPU9150_MPL_QUAT_9DOF_X";
        public static final String MPU_QUAT_9DOF_Y = "MPU9150_MPL_QUAT_9DOF_Y";
        public static final String MPU_QUAT_9DOF_Z = "MPU9150_MPL_QUAT_9DOF_Z";
        public static final String MPU_TEMP = "MPU9150_Temperature";
        public static final String ORIENT = "MPU9150_MPL_ORIENT";
        public static final String PEDOMETER_CNT = "MPU9150_MPL_PEDOM_CNT";
        public static final String PEDOMETER_TIME = "MPU9150_MPL_PEDOM_TIME";
        public static final String TAP_CNT = "MPU9150_MPL_TAP_CNT";
        public static final String TAP_DIR = "MPU9150_MPL_TAP_DIR";
        public static final String TAP_DIR_AND_CNT = "MPU9150_MPL_TAP";
    }

    public static final class DatabaseConfigHandle {
        public static final String ALTERNATIVE_ACC_RANGE = "MPU9150_Acc_Range";
        public static final String GYRO_CALIB_TIME = "MPU9150_Gyro_Calib_Time";
        public static final String GYRO_RANGE = "MPU9150_Gyro_Range";
        public static final String GYRO_RATE = "MPU9150_Gyro_Rate";
        public static final String MPU_ACC = "MPU9150_MPL_Acc_Cal";
        public static final String MPU_DMP = "MPU9150_DMP";
        public static final String MPU_EULER_6DOF = "MPU9150_MPL_Euler_6DOF";
        public static final String MPU_GYRO = "MPU9150_MPL_Gyro_Cal";
        public static final String MPU_HEADING_ENABLE = "MPU9150_MPL_Heading";
        public static final String MPU_LPF = "MPU9150_LFP";
        public static final String MPU_MAG = "MPU9150_MPL_Mag_Cal";
        public static final String MPU_MAG_DIST = "MPU9150_MAG_Dist";
        public static final String MPU_MAG_SAMPLING_RATE = "MPU9150_MAG_Sampling_rate";
        public static final String MPU_MOTION_ORIENT = "MPU9150_MPL_Motion";
        public static final String MPU_MOT_CAL_CFG = "MPU9150_MOT_Cal_Cfg";
        public static final String MPU_MPL_ENABLE = "MPU9150_MPL_Enable";
        public static final String MPU_MPL_GYRO_TC = "MPU9150_MPL_Gyro_TC";
        public static final String MPU_MPL_SAMPLING_RATE = "MPU9150_MPL_Sampling_rate";
        public static final String MPU_MPL_SENSOR_FUSION = "MPU9150_MPL_Sensor_Fusion";
        public static final String MPU_MPL_VECT_COMP = "MPU9150_MPL_Vect_Comp";
        public static final String MPU_PEDOMETER = "MPU9150_MPL_Pedometer";
        public static final String MPU_QUAT_6DOF = "MPU9150_MPL_Quat_6DOF";
        public static final String MPU_QUAT_6DOF_DMP = "MPU9150_Quat_6DOF_Dmp";
        public static final String MPU_TAP = "MPU9150_MPL_Tap";
        public static final String GYRO_OFFSET_X = "MPU9150_Gyro_Offset_X";
        public static final String GYRO_OFFSET_Y = "MPU9150_Gyro_Offset_Y";
        public static final String GYRO_OFFSET_Z = "MPU9150_Gyro_Offset_Z";
        public static final String GYRO_GAIN_X = "MPU9150_Gyro_Gain_X";
        public static final String GYRO_GAIN_Y = "MPU9150_Gyro_Gain_Y";
        public static final String GYRO_GAIN_Z = "MPU9150_Gyro_Gain_Z";
        public static final String GYRO_ALIGN_XX = "MPU9150_Gyro_Align_XX";
        public static final String GYRO_ALIGN_XY = "MPU9150_Gyro_Align_XY";
        public static final String GYRO_ALIGN_XZ = "MPU9150_Gyro_Align_XZ";
        public static final String GYRO_ALIGN_YX = "MPU9150_Gyro_Align_YX";
        public static final String GYRO_ALIGN_YY = "MPU9150_Gyro_Align_YY";
        public static final String GYRO_ALIGN_YZ = "MPU9150_Gyro_Align_YZ";
        public static final String GYRO_ALIGN_ZX = "MPU9150_Gyro_Align_ZX";
        public static final String GYRO_ALIGN_ZY = "MPU9150_Gyro_Align_ZY";
        public static final String GYRO_ALIGN_ZZ = "MPU9150_Gyro_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_GYRO = Arrays.asList(GYRO_OFFSET_X, GYRO_OFFSET_Y, GYRO_OFFSET_Z, GYRO_GAIN_X, GYRO_GAIN_Y, GYRO_GAIN_Z, GYRO_ALIGN_XX, GYRO_ALIGN_XY, GYRO_ALIGN_XZ, GYRO_ALIGN_YX, GYRO_ALIGN_YY, GYRO_ALIGN_YZ, GYRO_ALIGN_ZX, GYRO_ALIGN_ZY, GYRO_ALIGN_ZZ);
        public static final String MPU_ACC_OFFSET_X = "MPU9150_MPL_Acc_Cal_Offset_X";
        public static final String MPU_ACC_OFFSET_Y = "MPU9150_MPL_Acc_Cal_Offset_Y";
        public static final String MPU_ACC_OFFSET_Z = "MPU9150_MPL_Acc_Cal_Offset_Z";
        public static final String MPU_ACC_GAIN_X = "MPU9150_MPL_Acc_Cal_Gain_X";
        public static final String MPU_ACC_GAIN_Y = "MPU9150_MPL_Acc_Cal_Gain_Y";
        public static final String MPU_ACC_GAIN_Z = "MPU9150_MPL_Acc_Cal_Gain_Z";
        public static final String MPU_ACC_ALIGN_XX = "MPU9150_MPL_Acc_Cal_Align_XX";
        public static final String MPU_ACC_ALIGN_XY = "MPU9150_MPL_Acc_Cal_Align_XY";
        public static final String MPU_ACC_ALIGN_XZ = "MPU9150_MPL_Acc_Cal_Align_XZ";
        public static final String MPU_ACC_ALIGN_YX = "MPU9150_MPL_Acc_Cal_Align_YX";
        public static final String MPU_ACC_ALIGN_YY = "MPU9150_MPL_Acc_Cal_Align_YY";
        public static final String MPU_ACC_ALIGN_YZ = "MPU9150_MPL_Acc_Cal_Align_YZ";
        public static final String MPU_ACC_ALIGN_ZX = "MPU9150_MPL_Acc_Cal_Align_ZX";
        public static final String MPU_ACC_ALIGN_ZY = "MPU9150_MPL_Acc_Cal_Align_ZY";
        public static final String MPU_ACC_ALIGN_ZZ = "MPU9150_MPL_Acc_Cal_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MPU_MPL_ACC = Arrays.asList(MPU_ACC_OFFSET_X, MPU_ACC_OFFSET_Y, MPU_ACC_OFFSET_Z, MPU_ACC_GAIN_X, MPU_ACC_GAIN_Y, MPU_ACC_GAIN_Z, MPU_ACC_ALIGN_XX, MPU_ACC_ALIGN_XY, MPU_ACC_ALIGN_XZ, MPU_ACC_ALIGN_YX, MPU_ACC_ALIGN_YY, MPU_ACC_ALIGN_YZ, MPU_ACC_ALIGN_ZX, MPU_ACC_ALIGN_ZY, MPU_ACC_ALIGN_ZZ);
        public static final String MPU_MAG_OFFSET_X = "MPU9150_MPL_Mag_Cal_Offset_X";
        public static final String MPU_MAG_OFFSET_Y = "MPU9150_MPL_Mag_Cal_Offset_Y";
        public static final String MPU_MAG_OFFSET_Z = "MPU9150_MPL_Mag_Cal_Offset_Z";
        public static final String MPU_MAG_GAIN_X = "MPU9150_MPL_Mag_Cal_Gain_X";
        public static final String MPU_MAG_GAIN_Y = "MPU9150_MPL_Mag_Cal_Gain_Y";
        public static final String MPU_MAG_GAIN_Z = "MPU9150_MPL_Mag_Cal_Gain_Z";
        public static final String MPU_MAG_ALIGN_XX = "MPU9150_MPL_Mag_Cal_Align_XX";
        public static final String MPU_MAG_ALIGN_XY = "MPU9150_MPL_Mag_Cal_Align_XY";
        public static final String MPU_MAG_ALIGN_XZ = "MPU9150_MPL_Mag_Cal_Align_XZ";
        public static final String MPU_MAG_ALIGN_YX = "MPU9150_MPL_Mag_Cal_Align_YX";
        public static final String MPU_MAG_ALIGN_YY = "MPU9150_MPL_Mag_Cal_Align_YY";
        public static final String MPU_MAG_ALIGN_YZ = "MPU9150_MPL_Mag_Cal_Align_YZ";
        public static final String MPU_MAG_ALIGN_ZX = "MPU9150_MPL_Mag_Cal_Align_ZX";
        public static final String MPU_MAG_ALIGN_ZY = "MPU9150_MPL_Mag_Cal_Align_ZY";
        public static final String MPU_MAG_ALIGN_ZZ = "MPU9150_MPL_Mag_Cal_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MPU_MPL_MAG = Arrays.asList(MPU_MAG_OFFSET_X, MPU_MAG_OFFSET_Y, MPU_MAG_OFFSET_Z, MPU_MAG_GAIN_X, MPU_MAG_GAIN_Y, MPU_MAG_GAIN_Z, MPU_MAG_ALIGN_XX, MPU_MAG_ALIGN_XY, MPU_MAG_ALIGN_XZ, MPU_MAG_ALIGN_YX, MPU_MAG_ALIGN_YY, MPU_MAG_ALIGN_YZ, MPU_MAG_ALIGN_ZX, MPU_MAG_ALIGN_ZY, MPU_MAG_ALIGN_ZZ);
        public static final String MPU_GYRO_OFFSET_X = "MPU9150_MPL_Gyro_Cal_Offset_X";
        public static final String MPU_GYRO_OFFSET_Y = "MPU9150_MPL_Gyro_Cal_Offset_Y";
        public static final String MPU_GYRO_OFFSET_Z = "MPU9150_MPL_Gyro_Cal_Offset_Z";
        public static final String MPU_GYRO_GAIN_X = "MPU9150_MPL_Gyro_Cal_Gain_X";
        public static final String MPU_GYRO_GAIN_Y = "MPU9150_MPL_Gyro_Cal_Gain_Y";
        public static final String MPU_GYRO_GAIN_Z = "MPU9150_MPL_Gyro_Cal_Gain_Z";
        public static final String MPU_GYRO_ALIGN_XX = "MPU9150_MPL_Gyro_Cal_Align_XX";
        public static final String MPU_GYRO_ALIGN_XY = "MPU9150_MPL_Gyro_Cal_Align_XY";
        public static final String MPU_GYRO_ALIGN_XZ = "MPU9150_MPL_Gyro_Cal_Align_XZ";
        public static final String MPU_GYRO_ALIGN_YX = "MPU9150_MPL_Gyro_Cal_Align_YX";
        public static final String MPU_GYRO_ALIGN_YY = "MPU9150_MPL_Gyro_Cal_Align_YY";
        public static final String MPU_GYRO_ALIGN_YZ = "MPU9150_MPL_Gyro_Cal_Align_YZ";
        public static final String MPU_GYRO_ALIGN_ZX = "MPU9150_MPL_Gyro_Cal_Align_ZX";
        public static final String MPU_GYRO_ALIGN_ZY = "MPU9150_MPL_Gyro_Cal_Align_ZY";
        public static final String MPU_GYRO_ALIGN_ZZ = "MPU9150_MPL_Gyro_Cal_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MPU_MPL_GYRO = Arrays.asList(MPU_GYRO_OFFSET_X, MPU_GYRO_OFFSET_Y, MPU_GYRO_OFFSET_Z, MPU_GYRO_GAIN_X, MPU_GYRO_GAIN_Y, MPU_GYRO_GAIN_Z, MPU_GYRO_ALIGN_XX, MPU_GYRO_ALIGN_XY, MPU_GYRO_ALIGN_XZ, MPU_GYRO_ALIGN_YX, MPU_GYRO_ALIGN_YY, MPU_GYRO_ALIGN_YZ, MPU_GYRO_ALIGN_ZX, MPU_GYRO_ALIGN_ZY, MPU_GYRO_ALIGN_ZZ);
    }
}
