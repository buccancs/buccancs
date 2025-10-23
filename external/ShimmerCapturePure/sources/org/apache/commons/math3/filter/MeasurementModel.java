package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes5.dex */
public interface MeasurementModel {
    RealMatrix getMeasurementMatrix();

    RealMatrix getMeasurementNoise();
}
