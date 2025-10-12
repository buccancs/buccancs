package com.shimmerresearch.algorithms.orientation;

public class GradDes3DOrientation {

    public final static double Q0_INITIAL_DEFAULT = 1;
    public final static double Q1_INITIAL_DEFAULT = 0;
    public final static double Q2_INITIAL_DEFAULT = 0;
    public final static double Q3_INITIAL_DEFAULT = 0;
    public static double BETA = 0.5;
    double mBeta = BETA;
    double mSamplingPeriod = 1;
    double q0Initial, q1Initial, q2Initial, q3Initial;
    double q0, q1, q2, q3;

    public GradDes3DOrientation(double samplingPeriod) {
        this(BETA, samplingPeriod, Q0_INITIAL_DEFAULT, Q1_INITIAL_DEFAULT, Q2_INITIAL_DEFAULT, Q3_INITIAL_DEFAULT);
    }

    public GradDes3DOrientation(double beta, double samplingPeriod, double q0, double q1, double q2, double q3) {
        setSamplingPeriod(samplingPeriod);
        setInitialConditions(beta, q0, q1, q2, q3);
    }

    public void setSamplingPeriod(double samplingPeriod) {
        mSamplingPeriod = samplingPeriod;
    }

    public void setInitialConditions(double beta, double q0, double q1, double q2, double q3) {
        mBeta = beta;
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q0Initial = q0;
        this.q1Initial = q1;
        this.q2Initial = q2;
        this.q3Initial = q3;
    }

    public void resetInitialConditions() {
        setInitialConditions(mBeta, q0Initial, q1Initial, q2Initial, q3Initial);
    }

    public Orientation3DObject update(double ax, double ay, double az, double gx, double gy, double gz, double mx, double my, double mz) {
        double recipNorm;
        double s0, s1, s2, s3;
        double qDot1, qDot2, qDot3, qDot4;
        double hx, hy;
        double _2q0, _2q1, _2q2, _2q3, _2bz, _2bx, q0q0, q0q1, q0q2, q0q3, q1q1, q1q2, q1q3, q2q2, q2q3, q3q3;

        if ((mx == 0.0f) && (my == 0.0f) && (mz == 0.0f)) {
            return update(gx, gy, gz, ax, ay, az);
        }

        q0q0 = q0 * q0;
        q1q1 = q1 * q1;
        q2q2 = q2 * q2;
        q3q3 = q3 * q3;

        _2q0 = 2 * q0;
        _2q1 = 2 * q1;
        _2q2 = 2 * q2;
        _2q3 = 2 * q3;

        q0q1 = q0 * q1;
        q0q2 = q0 * q2;
        q0q3 = q0 * q3;
        q1q2 = q1 * q2;
        q1q3 = q1 * q3;
        q2q3 = q2 * q3;

        recipNorm = Math.sqrt(ax * ax + ay * ay + az * az);
        if (isFinite(recipNorm) && recipNorm > 0.0) {
            recipNorm = 1.0 / recipNorm;
            ax *= recipNorm;
            ay *= recipNorm;
            az *= recipNorm;
        } else {
        }

        recipNorm = Math.sqrt(mx * mx + my * my + mz * mz);
        if (isFinite(recipNorm) && recipNorm > 0.0) {
            recipNorm = 1.0 / recipNorm;
            mx *= recipNorm;
            my *= recipNorm;
            mz *= recipNorm;
        } else {
        }

        hx = mx * (q0q0 + q1q1 - q2q2 - q3q3) + 2 * my * (q1q2 - q0q3) + 2 * mz * (q1q3 + q0q2);
        hy = 2 * mx * (q0q3 + q1q2) + my * (q0q0 - q1q1 + q2q2 - q3q3) * 2 * mz * (q2q3 - q0q1);
        _2bz = 2 * mx * (q1q3 - q0q2) + 2 * my * (q0q1 + q2q3) + mz * (q0q0 - q1q1 - q2q2 + q3q3);
        _2bx = Math.sqrt(hx * hx + hy * hy);

        s0 = -_2q2 * (2 * (q1q3 - q0q2) - ax) +
                _2q1 * (2 * (q0q1 + q2q3) - ay) - _2bz * q2 * (_2bx * (0.5 - q2q2 - q3q3) +
                _2bz * (q1q3 - q0q2) - mx) + (-_2bx * q3 + _2bz * q1) * (_2bx * (q1q2 - q0q3) +
                _2bz * (q0q1 + q2q3) - my) + _2bx * q2 * (_2bx * (q0q2 + q1q3) + _2bz * (0.5 - q1q1 - q2q2) - mz);

        s1 = _2q3 * (2 * (q1q3 - q0q2) - ax) +
                _2q0 * (2 * (q0q1 + q2q3) - ay) -
                4 * q1 * (1 - 2 * (q1q1 + q2q2) - az) +
                _2bz * q3 * (_2bx * (0.5 - q2q2 - q3q3) + _2bz * (q1q3 - q0q2) - mx) +
                (_2bx * q2 + _2bz * q0) * (_2bx * (q1q2 - q0q3) + _2bz * (q0q1 + q2q3) - my) +
                (_2bx * q3 - _2bz * _2q1) * (_2bx * (q0q2 + q1q3) + _2bz * (0.5 - q1q1 - q2q2) - mz);

        s2 = -_2q0 * (2 * (q1q3 - q0q2) - ax) +
                _2q3 * (2 * (q0q1 + q2q3) - ay) -
                4 * q2 * (1 - 2 * (q1q1 + q2q2) - az) +
                (-_2bx * _2q2 - _2bz * q0) * (_2bx * (0.5 - q2q2 - q3q3) + _2bz * (q1q3 - q0q2) - mx) +
                (_2bx * q1 + _2bz * q3) * (_2bx * (q1q2 - q0q3) + _2bz * (q0q1 + q2q3) - my) +
                (_2bx * q0 - _2bz * _2q2) * (_2bx * (q0q2 + q1q3) + _2bz * (0.5 - q1q1 - q2q2) - mz);

        s3 = _2q1 * (2.0 * (q1q3 - q0q2) - ax) +
                _2q2 * (2 * (q0q1 + q2q3) - ay) +
                (-_2bx * _2q3 + _2bz * q1) * (_2bx * (0.5 - q2q2 - q3q3) + _2bz * (q1q3 - q0q2) - mx) +
                (-_2bx * q0 + _2bz * q2) * (_2bx * (q1q2 - q0q3) + _2bz * (q0q1 + q2q3) - my) +
                _2bx * q1 * (_2bx * (q0q2 + q1q3) + _2bz * (0.5 - q1q1 - q2q2) - mz);


        recipNorm = 1.0 / Math.sqrt(s0 * s0 + s1 * s1 + s2 * s2 + s3 * s3);
        if (isFinite(recipNorm) && recipNorm > 0.0) {
            s0 *= recipNorm;
            s1 *= recipNorm;
            s2 *= recipNorm;
            s3 *= recipNorm;
        } else {
        }

        qDot1 = 0.5 * (-q1 * gx - q2 * gy - q3 * gz) - mBeta * s0;
        qDot2 = 0.5 * (q0 * gx - q3 * gy + q2 * gz) - mBeta * s1;
        qDot3 = 0.5 * (q3 * gx + q0 * gy - q1 * gz) - mBeta * s2;
        qDot4 = 0.5 * (-q2 * gx + q1 * gy + q0 * gz) - mBeta * s3;

        q0 += qDot1 * mSamplingPeriod;
        q1 += qDot2 * mSamplingPeriod;
        q2 += qDot3 * mSamplingPeriod;
        q3 += qDot4 * mSamplingPeriod;
        recipNorm = 1.0 / Math.sqrt(q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3);

        q0 *= recipNorm;
        q1 *= recipNorm;
        q2 *= recipNorm;
        q3 *= recipNorm;

        return new Orientation3DObject(q0, q1, q2, q3);
    }

    public double getBeta() {
        return mBeta;
    }

    public double getQ0() {
        return q0;
    }

    public double getQ1() {
        return q1;
    }

    public double getQ2() {
        return q2;
    }

    public double getQ3() {
        return q3;
    }

    public Orientation3DObject update(double ax, double ay, double az, double gx, double gy, double gz) {
        double recipNorm;
        double s0, s1, s2, s3;
        double qDot1, qDot2, qDot3, qDot4;
        double _2q0, _2q1, _2q2, _2q3, _4q0, _4q1, _4q2, _8q1, _8q2, q0q0, q1q1, q2q2, q3q3;

        qDot1 = 0.5f * (-q1 * gx - q2 * gy - q3 * gz);
        qDot2 = 0.5f * (q0 * gx + q2 * gz - q3 * gy);
        qDot3 = 0.5f * (q0 * gy - q1 * gz + q3 * gx);
        qDot4 = 0.5f * (q0 * gz + q1 * gy - q2 * gx);

        if (!((ax == 0.0f) && (ay == 0.0f) && (az == 0.0f))) {

            recipNorm = Math.sqrt(ax * ax + ay * ay + az * az);
            if (isFinite(recipNorm) && recipNorm > 0.0) {
                recipNorm = 1.0 / recipNorm;
                ax *= recipNorm;
                ay *= recipNorm;
                az *= recipNorm;
            } else {
            }

            _2q0 = 2.0f * q0;
            _2q1 = 2.0f * q1;
            _2q2 = 2.0f * q2;
            _2q3 = 2.0f * q3;
            _4q0 = 4.0f * q0;
            _4q1 = 4.0f * q1;
            _4q2 = 4.0f * q2;
            _8q1 = 8.0f * q1;
            _8q2 = 8.0f * q2;
            q0q0 = q0 * q0;
            q1q1 = q1 * q1;
            q2q2 = q2 * q2;
            q3q3 = q3 * q3;

            s0 = _4q0 * q2q2 + _2q2 * ax + _4q0 * q1q1 - _2q1 * ay;
            s1 = _4q1 * q3q3 - _2q3 * ax + 4.0f * q0q0 * q1 - _2q0 * ay - _4q1 + _8q1 * q1q1 + _8q1 * q2q2 + _4q1 * az;
            s2 = 4.0f * q0q0 * q2 + _2q0 * ax + _4q2 * q3q3 - _2q3 * ay - _4q2 + _8q2 * q1q1 + _8q2 * q2q2 + _4q2 * az;
            s3 = 4.0f * q1q1 * q3 - _2q1 * ax + 4.0f * q2q2 * q3 - _2q2 * ay;

            recipNorm = 1.0 / Math.sqrt(s1 * s1 + s2 * s2 + s3 * s3 + s0 * s0);
            if (isFinite(recipNorm) && recipNorm > 0.0) {
                s0 *= recipNorm;
                s1 *= recipNorm;
                s2 *= recipNorm;
                s3 *= recipNorm;
            } else {
            }

            qDot1 -= mBeta * s0;
            qDot2 -= mBeta * s1;
            qDot3 -= mBeta * s2;
            qDot4 -= mBeta * s3;
        }

        q0 += qDot1 * mSamplingPeriod;
        q1 += qDot2 * mSamplingPeriod;
        q2 += qDot3 * mSamplingPeriod;
        q3 += qDot4 * mSamplingPeriod;
        recipNorm = 1.0 / Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3 + q0 * q0);

        q0 *= recipNorm;
        q1 *= recipNorm;
        q2 *= recipNorm;
        q3 *= recipNorm;

        return new Orientation3DObject(q0, q1, q2, q3);
    }

    public boolean isFinite(double d) {

        return !(Double.isNaN(d) || Double.isInfinite(d));
    }


}
