package com.shimmerresearch.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class Filter implements Serializable {
    public static int BAND_PASS = 2;
    public static int BAND_STOP = 3;
    public static int HIGH_PASS = 1;
    public static int LOW_PASS;
    public double[] coefficients;
    private double[] bufferedX;
    private double[] cornerFrequency;
    private double[] defaultCornerFrequency;
    private int defaultNTaps;
    private double defaultSamplingRate;
    private int filterType;
    private double maxCornerFrequency;
    private double minCornerFrequency;
    private int nTaps;
    private double samplingRate;
    private boolean validparameters;

    public Filter() throws Exception {
        this.samplingRate = Double.NaN;
        this.validparameters = false;
        this.defaultSamplingRate = 512.0d;
        double[] dArr = {0.5d};
        this.defaultCornerFrequency = dArr;
        this.defaultNTaps = 200;
        int i = LOW_PASS;
        this.filterType = i;
        SetFilterParameters(i, 512.0d, dArr, 200);
    }

    public Filter(int i) throws Exception {
        this.samplingRate = Double.NaN;
        this.validparameters = false;
        this.defaultSamplingRate = 512.0d;
        double[] dArr = {0.5d};
        this.defaultCornerFrequency = dArr;
        this.defaultNTaps = 200;
        this.filterType = i;
        SetFilterParameters(i, 512.0d, dArr, 200);
    }

    public Filter(int i, double d, double[] dArr) throws Exception {
        this.samplingRate = Double.NaN;
        this.validparameters = false;
        this.defaultSamplingRate = 512.0d;
        this.defaultCornerFrequency = new double[]{0.5d};
        this.defaultNTaps = 200;
        this.filterType = i;
        SetFilterParameters(i, d, dArr, 200);
    }

    public Filter(int i, double d, double[] dArr, int i2) throws Exception {
        this.samplingRate = Double.NaN;
        this.validparameters = false;
        this.defaultSamplingRate = 512.0d;
        this.defaultCornerFrequency = new double[]{0.5d};
        this.defaultNTaps = 200;
        this.filterType = i;
        SetFilterParameters(i, d, dArr, i2);
    }

    public static double[] fromListToArray(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public double[] GetCornerFrequency() {
        return this.cornerFrequency;
    }

    public double GetSamplingRate() {
        return this.samplingRate;
    }

    protected void SamplingRate(double d) {
        this.samplingRate = d;
    }

    protected void SetCornerFrequency(double[] dArr) {
        this.cornerFrequency = dArr;
    }

    public int getFilterType() {
        return this.filterType;
    }

    public double getMaxCornerFrequency() {
        return this.maxCornerFrequency;
    }

    public double getMinCornerFrequency() {
        return this.minCornerFrequency;
    }

    public int getNTaps() {
        return this.nTaps;
    }

    public void resetBuffer() {
        this.bufferedX = null;
    }

    public void SetFilterParameters(int i, double d, double[] dArr, int i2) throws Exception {
        int i3;
        resetBuffer();
        if (dArr.length != 1) {
            double d2 = dArr[0];
            double d3 = dArr[1];
            if (d2 > d3) {
                this.minCornerFrequency = d3;
                this.maxCornerFrequency = d2;
            } else {
                this.minCornerFrequency = d2;
                this.maxCornerFrequency = d3;
            }
        } else {
            double d4 = dArr[0];
            this.maxCornerFrequency = d4;
            this.minCornerFrequency = d4;
        }
        double d5 = this.maxCornerFrequency;
        if (d5 > d / 2.0d) {
            this.validparameters = false;
            throw new Exception("Error: cornerFrequency is greater than Nyquist frequency. Please choose valid parameters.");
        }
        if (i2 % 2 != 0) {
            i2--;
        }
        if (i == LOW_PASS || i == (i3 = HIGH_PASS)) {
            this.samplingRate = d;
            this.cornerFrequency = dArr;
            this.nTaps = i2;
            double d6 = dArr[0] / d;
            this.coefficients = new double[i2];
            this.coefficients = calculateCoefficients(d6, i, i2);
            this.validparameters = true;
            return;
        }
        if (i == BAND_PASS || i == BAND_STOP) {
            if (dArr.length != 2) {
                throw new Exception("Error. Bandpass or bandstop filter requires two corner frequencies to be specified");
            }
            this.samplingRate = d;
            this.nTaps = i2;
            double d7 = this.minCornerFrequency / d;
            double[] dArrCalculateCoefficients = calculateCoefficients(d5 / d, i3, i2);
            double[] dArrCalculateCoefficients2 = calculateCoefficients(d7, LOW_PASS, i2);
            this.coefficients = new double[dArrCalculateCoefficients.length];
            for (int i4 = 0; i4 < dArrCalculateCoefficients.length; i4++) {
                if (i == BAND_PASS) {
                    this.coefficients[i4] = -(dArrCalculateCoefficients[i4] + dArrCalculateCoefficients2[i4]);
                } else {
                    this.coefficients[i4] = dArrCalculateCoefficients[i4] + dArrCalculateCoefficients2[i4];
                }
            }
            if (i == BAND_PASS) {
                double[] dArr2 = this.coefficients;
                int i5 = i2 / 2;
                dArr2[i5] = dArr2[i5] + 1.0d;
            }
            this.validparameters = true;
            return;
        }
        throw new Exception("Error. Undefined filter type: use 0 - lowpass, 1 - highpass, 2- bandpass, or 3- bandstop");
    }

    public double filterData(double d) throws Exception {
        if (!this.validparameters) {
            throw new Exception("Error. Filter parameters are invalid. Please set filter parameters before filtering data.");
        }
        int i = this.nTaps;
        double[] dArr = this.bufferedX;
        if (dArr == null) {
            double[] dArr2 = new double[i + 1];
            this.bufferedX = dArr2;
            Arrays.fill(dArr2, d);
        } else {
            System.arraycopy(dArr, 1, dArr, 0, dArr.length - 1);
            double[] dArr3 = this.bufferedX;
            dArr3[dArr3.length - 1] = d;
        }
        return filter(this.bufferedX);
    }

    public double[] filterData(double[] dArr) throws Exception {
        if (!this.validparameters) {
            throw new Exception("Error. Filter parameters are invalid. Please set filter parameters before filtering data.");
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = filterData(dArr[i]);
        }
        resetBuffer();
        return dArr2;
    }

    public List<Double> filterData(List<Double> list) throws Exception {
        if (!this.validparameters) {
            throw new Exception("Error. Filter parameters are invalid. Please set filter parameters before filtering data.");
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i, Double.valueOf(filterData(list.get(i).doubleValue())));
        }
        resetBuffer();
        return arrayList;
    }

    private double filter(double[] dArr) {
        int length = this.coefficients.length;
        double d = 0.0d;
        for (int i = 0; i < length; i++) {
            d += dArr[length - i] * this.coefficients[i];
        }
        return d;
    }

    private double[] calculateCoefficients(double d, int i, int i2) throws Exception {
        double d2;
        if (i != LOW_PASS && i != HIGH_PASS) {
            throw new Exception("Error: the function calculateCoefficients() can only be called for LPF or HPF.");
        }
        double[] dArr = new double[i2];
        int i3 = 0;
        while (true) {
            d2 = 0.0d;
            if (i3 >= i2) {
                break;
            }
            dArr[i3] = 0.0d;
            i3++;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            double d3 = i4;
            double d4 = i2;
            double dCos = (0.42d - (Math.cos((d3 * 6.283185307179586d) / d4) * 0.5d)) + (Math.cos((d3 * 12.566370614359172d) / d4) * 0.08d);
            dArr[i4] = dCos;
            int i5 = i2 / 2;
            if (i4 != i5) {
                double d5 = i4 - i5;
                dArr[i4] = (dCos * Math.sin((d * 6.283185307179586d) * d5)) / d5;
            } else {
                dArr[i4] = dCos * d * 6.283185307179586d;
            }
        }
        for (int i6 = 0; i6 < i2; i6++) {
            d2 += dArr[i6];
        }
        for (int i7 = 0; i7 < i2; i7++) {
            if (i == HIGH_PASS) {
                dArr[i7] = (-dArr[i7]) / d2;
            } else {
                dArr[i7] = dArr[i7] / d2;
            }
        }
        if (i == HIGH_PASS) {
            int i8 = i2 / 2;
            dArr[i8] = dArr[i8] + 1.0d;
        }
        return dArr;
    }
}
