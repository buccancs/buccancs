package com.shimmerresearch.algorithms.orientation;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmResultObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

import java.util.Collection;
import java.util.List;
import javax.vecmath.Vector3d;

/* loaded from: classes2.dex */
public abstract class OrientationModule extends AbstractAlgorithm {
    public static final ConfigOptionDetails configOptionEulerOutput;
    public static final ConfigOptionDetails configOptionQuatOutput;
    protected static final String[] EULER_OPTIONS;
    protected static final String[] QUATERNION_OPTIONS;
    protected static final ShimmerVerObject svoSh3Module = new ShimmerVerObject(3, -1, -1, -1, -1, 37);
    private static final long serialVersionUID = 2501565989909361523L;

    static {
        String[] strArr = {"Off", "On"};
        QUATERNION_OPTIONS = strArr;
        String[] strArr2 = {"Off", "On"};
        EULER_OPTIONS = strArr2;
        configOptionQuatOutput = new ConfigOptionDetails(GuiLabelConfig.QUATERNION_OUTPUT, (String) null, strArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, (List<ShimmerVerObject>) null);
        configOptionEulerOutput = new ConfigOptionDetails(GuiLabelConfig.EULER_OUTPUT, (String) null, strArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, (List<ShimmerVerObject>) null);
    }

    public boolean axisAngleOutput;
    public boolean eulerOutput;
    public boolean quaternionOutput;
    protected Vector3d accValues;
    protected String accelerometerSensor;
    protected Vector3d gyroValues;
    protected Vector3d magValues;
    protected double samplingRate;
    transient GradDes3DOrientation orientationAlgorithm;
    ORIENTATION_TYPE orientationType;

    public OrientationModule(ShimmerDevice shimmerDevice, AlgorithmDetails algorithmDetails) {
        super(shimmerDevice, algorithmDetails);
        this.quaternionOutput = true;
        this.eulerOutput = false;
        this.axisAngleOutput = false;
    }

    public abstract ObjectCluster addQuaternionToObjectCluster(Orientation3DObject orientation3DObject, ObjectCluster objectCluster);

    public abstract Orientation3DObject applyOrientationAlgorithm();

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void eventDataReceived(ShimmerMsg shimmerMsg) {
    }

    public String getAccelerometer() {
        return this.accelerometerSensor;
    }

    public GradDes3DOrientation getOrientationAlgorithm() {
        return this.orientationAlgorithm;
    }

    public ORIENTATION_TYPE getOrientationType() {
        return this.orientationType;
    }

    public void setOrientationType(ORIENTATION_TYPE orientation_type) {
        this.orientationType = orientation_type;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public double getShimmerSamplingRate() {
        return this.samplingRate;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setShimmerSamplingRate(double d) {
        if (this.mShimmerDevice != null && this.mShimmerDevice.getFirmwareIdentifier() == 15) {
            d = 51.2d;
        }
        this.samplingRate = d;
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAxisAngleOutput() {
        return this.axisAngleOutput;
    }

    public void setAxisAngleOutput(boolean z) {
        this.axisAngleOutput = z;
        checkIfToDisable();
    }

    public boolean isEulerOutput() {
        return this.eulerOutput;
    }

    public void setEulerOutput(boolean z) {
        this.eulerOutput = z;
        checkIfToDisable();
    }

    public boolean isQuaternionOutput() {
        return this.quaternionOutput;
    }

    public void setQuaternionOutput(boolean z) {
        this.quaternionOutput = z;
        checkIfToDisable();
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public String printBatchMetrics() {
        return null;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public AlgorithmResultObject processDataRealTime(ObjectCluster objectCluster) throws Exception {
        FormatCluster formatClusterReturnFormatCluster;
        this.accValues = new Vector3d();
        this.magValues = new Vector3d();
        this.gyroValues = new Vector3d();
        for (String str : this.mAlgorithmDetails.mListOfAssociatedSensorChannels) {
            Collection<FormatCluster> collectionOfFormatClusters = objectCluster.getCollectionOfFormatClusters(str);
            if (collectionOfFormatClusters != null && (formatClusterReturnFormatCluster = ObjectCluster.returnFormatCluster(collectionOfFormatClusters, this.mAlgorithmDetails.mChannelType.toString())) != null) {
                double d = formatClusterReturnFormatCluster.mData;
                if (!Double.isNaN(d)) {
                    setChannelValue(str, d);
                }
            }
            return null;
        }
        return new AlgorithmResultObject(this.mAlgorithmResultType, addQuaternionToObjectCluster(applyOrientationAlgorithm(), objectCluster), getTrialName());
    }

    private void setChannelValue(String str, double d) {
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.MAG_X)) {
            this.magValues.x = d;
            return;
        }
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.MAG_Y)) {
            this.magValues.y = d;
            return;
        }
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.MAG_Z)) {
            this.magValues.z = d;
            return;
        }
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.GYRO_X)) {
            this.gyroValues.x = d * 0.017453292519943295d;
            return;
        }
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Y)) {
            this.gyroValues.y = d * 0.017453292519943295d;
            return;
        }
        if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.GYRO_Z)) {
            this.gyroValues.z = d * 0.017453292519943295d;
            return;
        }
        if (this.mAlgorithmName.equals("LN_Acc_9DoF") || this.mAlgorithmName.equals("LN_Acc_6DoF")) {
            if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_X)) {
                this.accValues.x = d;
                return;
            } else if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y)) {
                this.accValues.y = d;
                return;
            } else {
                if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z)) {
                    this.accValues.z = d;
                    return;
                }
                return;
            }
        }
        if (this.mAlgorithmName.equals("WR_Acc_9DoF") || this.mAlgorithmName.equals("WR_Acc_6DoF")) {
            if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X)) {
                this.accValues.x = d;
            } else if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y)) {
                this.accValues.y = d;
            } else if (str.equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z)) {
                this.accValues.z = d;
            }
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public AlgorithmResultObject processDataPostCapture(Object obj) throws Exception {
        throw new Error("Method: Object processDataPostCapture(List<?> objectList) is not valid for Orientation9DoF Module. Use: Object processDataRealTime(Object object).");
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void resetAlgorithm() throws Exception {
        resetAlgorithmBuffers();
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void resetAlgorithmBuffers() {
        GradDes3DOrientation gradDes3DOrientation = this.orientationAlgorithm;
        if (gradDes3DOrientation != null) {
            gradDes3DOrientation.resetInitialConditions();
        }
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setIsEnabled(boolean z) {
        this.mIsEnabled = z;
    }

    protected void checkIfToDisable() {
        if (!isEnabled() || isQuaternionOutput() || isEulerOutput()) {
            return;
        }
        setIsEnabled(false);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public long getDerivedSensorBitmapID() {
        long j;
        long j2;
        if (this.mAlgorithmDetails == null || !isEnabled()) {
            return 0L;
        }
        if (this.mAlgorithmDetails.mAlgorithmName.equals("LN_Acc_9DoF")) {
            j = isQuaternionOutput() ? 1048576L : 0L;
            if (!isEulerOutput()) {
                return j;
            }
            j2 = 2097152;
        } else if (this.mAlgorithmDetails.mAlgorithmName.equals("WR_Acc_9DoF")) {
            j = isQuaternionOutput() ? 65536L : 0L;
            if (!isEulerOutput()) {
                return j;
            }
            j2 = 131072;
        } else if (this.mAlgorithmDetails.mAlgorithmName.equals("WR_Acc_6DoF")) {
            j = isQuaternionOutput() ? 262144L : 0L;
            if (!isEulerOutput()) {
                return j;
            }
            j2 = 524288;
        } else {
            if (!this.mAlgorithmDetails.mAlgorithmName.equals("LN_Acc_6DoF")) {
                return 0L;
            }
            j = isQuaternionOutput() ? 4194304L : 0L;
            if (!isEulerOutput()) {
                return j;
            }
            j2 = 8388608;
        }
        return j | j2;
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void setDefaultSetting() {
        super.setDefaultSetting();
        if (isEnabled()) {
            if (isQuaternionOutput() || isEulerOutput()) {
                return;
            }
            setQuaternionOutput(true);
            return;
        }
        setQuaternionOutput(false);
        setEulerOutput(false);
    }

    @Override // com.shimmerresearch.algorithms.AbstractAlgorithm
    public void loadAlgorithmVariables(AbstractAlgorithm abstractAlgorithm) {
        if (abstractAlgorithm instanceof OrientationModule) {
            GradDes3DOrientation gradDes3DOrientation = ((OrientationModule) abstractAlgorithm).orientationAlgorithm;
            this.orientationAlgorithm.setInitialConditions(gradDes3DOrientation.mBeta, gradDes3DOrientation.q0, gradDes3DOrientation.q1, gradDes3DOrientation.q2, gradDes3DOrientation.q3);
        }
    }

    public enum ORIENTATION_TYPE {
        NINE_DOF,
        SIX_DOF
    }

    public static class AlgorithmName {
        public static final String ORIENTATION_6DOF_LN = "LN_Acc_6DoF";
        public static final String ORIENTATION_6DOF_WR = "WR_Acc_6DoF";
        public static final String ORIENTATION_9DOF_LN = "LN_Acc_9DoF";
        public static final String ORIENTATION_9DOF_WR = "WR_Acc_9DoF";
    }

    public class GuiLabelConfig {
        public static final String EULER_OUTPUT = "Euler";
        public static final String ORIENTATAION_LN = "Low-Noise Accel";
        public static final String ORIENTATAION_WR = "Wide-Range Accel";
        public static final String QUATERNION_OUTPUT = "Quaternion";

        public GuiLabelConfig() {
        }
    }
}
