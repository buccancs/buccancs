package com.shimmerresearch.algorithms.orientation;

public class Orientation3DObject {

    private static boolean isUseQuatToEuler = true;
    private double quaternionW, quaternionX, quaternionY, quaternionZ;
    private double rho;
    private double theta, angleX, angleY, angleZ;
        private double yaw, pitch, roll;

    public Orientation3DObject(double q1, double q2, double q3, double q4) {
        this.quaternionW = q1;
        this.quaternionX = q2;
        this.quaternionY = q3;
        this.quaternionZ = q4;
        calculateAngles();
        calculateEuler();
    }

    private void calculateAngles() {
        rho = Math.acos(quaternionW);
        theta = rho * 2;
        angleX = quaternionX / Math.sin(rho);
        angleY = quaternionY / Math.sin(rho);
        angleZ = quaternionZ / Math.sin(rho);
    }

    public double getQuaternionW() {
        return quaternionW;
    }

    public double getQuaternionX() {
        return quaternionX;
    }

    public double getQuaternionY() {
        return quaternionY;
    }

    public double getQuaternionZ() {
        return quaternionZ;
    }

    public double getTheta() {
        return theta;
    }

    public double getAngleX() {
        return angleX;
    }

    public double getAngleY() {
        return angleY;
    }

    public double getAngleZ() {
        return angleZ;
    }


    public void calculateEuler() {
        if (isUseQuatToEuler) {
            quaternianToEuler(quaternionW, quaternionX, quaternionY, quaternionZ);
        } else {
            axisAngleToEuler(angleX, angleY, angleZ, theta);
        }
    }

    public void axisAngleToEuler(double x, double y, double z, double angle) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double t = 1 - c;
        if ((x * y * t + z * s) > 0.998) {
            yaw = 2 * Math.atan2(x * Math.sin(angle / 2), Math.cos(angle / 2));
            pitch = Math.PI / 2;
            roll = 0;
            return;
        }
        if ((x * y * t + z * s) < -0.998) {
            yaw = -2 * Math.atan2(x * Math.sin(angle / 2), Math.cos(angle / 2));
            pitch = -Math.PI / 2;
            roll = 0;
            return;
        }
        yaw = Math.atan2(y * s - x * z * t, 1 - (y * y + z * z) * t) * 180 / Math.PI;
        pitch = Math.asin(x * y * t + z * s) * 180 / Math.PI;
        roll = Math.atan2(x * s - y * z * t, 1 - (x * x + z * z) * t) * 180 / Math.PI;
    }

    public void quaternianToEuler(double q0, double q1, double q2, double q3) {
        roll = Math.atan2(2 * (q0 * q1 + q2 * q3), 1 - 2 * (Math.pow(q1, 2) + Math.pow(q2, 2))) * 180 / Math.PI;
        pitch = Math.asin(2 * (q0 * q2 - q3 * q1)) * 180 / Math.PI;
        yaw = Math.atan2(2 * (q0 * q3 + q1 * q2), 1 - 2 * (Math.pow(q2, 2) + Math.pow(q3, 2))) * 180 / Math.PI;
    }

//
//

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public double getRoll() {
        return roll;
    }

}
