package org.apache.commons.math3.stat.descriptive.rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class PSquarePercentile extends AbstractStorelessUnivariateStatistic implements StorelessUnivariateStatistic, Serializable {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");
    private static final double DEFAULT_QUANTILE_DESIRED = 50.0d;
    private static final int PSQUARE_CONSTANT = 5;
    private static final long serialVersionUID = 2283912083175715479L;
    private final List<Double> initialFive;
    private final double quantile;
    private long countOfObservations;
    private transient double lastObservation;
    private PSquareMarkers markers;
    private double pValue;

    public PSquarePercentile(double d) {
        this.initialFive = new FixedCapacityList(5);
        this.markers = null;
        this.pValue = Double.NaN;
        if (d > 100.0d || d < 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE, Double.valueOf(d), 0, 100);
        }
        this.quantile = d / 100.0d;
    }

    PSquarePercentile() {
        this(DEFAULT_QUANTILE_DESIRED);
    }

    public static PSquareMarkers newMarkers(List<Double> list, double d) {
        return new Markers(list, d);
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.countOfObservations;
    }

    public double quantile() {
        return this.quantile;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic
    public int hashCode() {
        double result = getResult();
        if (Double.isNaN(result)) {
            result = 37.0d;
        }
        return Arrays.hashCode(new double[]{result, this.quantile, this.markers == null ? 0.0d : r2.hashCode(), this.countOfObservations});
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof PSquarePercentile)) {
            PSquarePercentile pSquarePercentile = (PSquarePercentile) obj;
            PSquareMarkers pSquareMarkers = this.markers;
            boolean z = (pSquareMarkers == null || pSquarePercentile.markers == null) ? false : true;
            boolean zEquals = pSquareMarkers == null && pSquarePercentile.markers == null;
            if (z) {
                zEquals = pSquareMarkers.equals(pSquarePercentile.markers);
            }
            if (zEquals && getN() == pSquarePercentile.getN()) {
                return true;
            }
        }
        return false;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        this.countOfObservations++;
        this.lastObservation = d;
        if (this.markers == null) {
            if (this.initialFive.add(Double.valueOf(d))) {
                Collections.sort(this.initialFive);
                this.pValue = this.initialFive.get((int) (this.quantile * (r5.size() - 1))).doubleValue();
                return;
            }
            this.markers = newMarkers(this.initialFive, this.quantile);
        }
        this.pValue = this.markers.processDataPoint(d);
    }

    public String toString() {
        if (this.markers != null) {
            return String.format("obs=%s markers=%s", DECIMAL_FORMAT.format(this.lastObservation), this.markers.toString());
        }
        DecimalFormat decimalFormat = DECIMAL_FORMAT;
        return String.format("obs=%s pValue=%s", decimalFormat.format(this.lastObservation), decimalFormat.format(this.pValue));
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public StorelessUnivariateStatistic copy() {
        PSquarePercentile pSquarePercentile = new PSquarePercentile(this.quantile * 100.0d);
        PSquareMarkers pSquareMarkers = this.markers;
        if (pSquareMarkers != null) {
            pSquarePercentile.markers = (PSquareMarkers) pSquareMarkers.clone();
        }
        pSquarePercentile.countOfObservations = this.countOfObservations;
        pSquarePercentile.pValue = this.pValue;
        pSquarePercentile.initialFive.clear();
        pSquarePercentile.initialFive.addAll(this.initialFive);
        return pSquarePercentile;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.markers = null;
        this.initialFive.clear();
        this.countOfObservations = 0L;
        this.pValue = Double.NaN;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        if (Double.compare(this.quantile, 1.0d) == 0) {
            this.pValue = maximum();
        } else if (Double.compare(this.quantile, 0.0d) == 0) {
            this.pValue = minimum();
        }
        return this.pValue;
    }

    private double maximum() {
        PSquareMarkers pSquareMarkers = this.markers;
        if (pSquareMarkers != null) {
            return pSquareMarkers.height(5);
        }
        if (this.initialFive.isEmpty()) {
            return Double.NaN;
        }
        return this.initialFive.get(r0.size() - 1).doubleValue();
    }

    private double minimum() {
        PSquareMarkers pSquareMarkers = this.markers;
        if (pSquareMarkers != null) {
            return pSquareMarkers.height(1);
        }
        if (this.initialFive.isEmpty()) {
            return Double.NaN;
        }
        return this.initialFive.get(0).doubleValue();
    }

    protected interface PSquareMarkers extends Cloneable {
        Object clone();

        double estimate(int i);

        double getPercentileValue();

        double height(int i);

        double processDataPoint(double d);
    }

    private static class Markers implements PSquareMarkers, Serializable {
        private static final int HIGH = 4;
        private static final int LOW = 2;
        private static final long serialVersionUID = 1;
        private final Marker[] markerArray;
        private transient int k;

        private Markers(Marker[] markerArr) throws NullArgumentException {
            this.k = -1;
            MathUtils.checkNotNull(markerArr);
            this.markerArray = markerArr;
            int i = 1;
            while (i < 5) {
                Marker[] markerArr2 = this.markerArray;
                int i2 = i + 1;
                markerArr2[i].previous(markerArr2[i - 1]).next(this.markerArray[i2]).index(i);
                i = i2;
            }
            Marker marker = this.markerArray[0];
            marker.previous(marker).next(this.markerArray[1]).index(0);
            Marker[] markerArr3 = this.markerArray;
            markerArr3[5].previous(markerArr3[4]).next(this.markerArray[5]).index(5);
        }

        private Markers(List<Double> list, double d) {
            this(createMarkerArray(list, d));
        }

        private static Marker[] createMarkerArray(List<Double> list, double d) {
            int size = list == null ? -1 : list.size();
            if (size < 5) {
                throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(size), 5);
            }
            Collections.sort(list);
            double d2 = d * 2.0d;
            return new Marker[]{new Marker(), new Marker(list.get(0).doubleValue(), 1.0d, 0.0d, 1.0d), new Marker(list.get(1).doubleValue(), d2 + 1.0d, d / 2.0d, 2.0d), new Marker(list.get(2).doubleValue(), (4.0d * d) + 1.0d, d, 3.0d), new Marker(list.get(3).doubleValue(), d2 + 3.0d, (d + 1.0d) / 2.0d, 4.0d), new Marker(list.get(4).doubleValue(), 5.0d, 1.0d, 5.0d)};
        }

        public int hashCode() {
            return Arrays.deepHashCode(this.markerArray);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Markers)) {
                return false;
            }
            return Arrays.deepEquals(this.markerArray, ((Markers) obj).markerArray);
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double processDataPoint(double d) {
            incrementPositions(1, findCellAndUpdateMinMax(d) + 1, 5);
            updateDesiredPositions();
            adjustHeightsOfMarkers();
            return getPercentileValue();
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double getPercentileValue() {
            return height(3);
        }

        private int findCellAndUpdateMinMax(double d) {
            this.k = -1;
            if (d < height(1)) {
                this.markerArray[1].markerHeight = d;
                this.k = 1;
            } else if (d < height(2)) {
                this.k = 1;
            } else if (d < height(3)) {
                this.k = 2;
            } else if (d < height(4)) {
                this.k = 3;
            } else if (d <= height(5)) {
                this.k = 4;
            } else {
                this.markerArray[5].markerHeight = d;
                this.k = 4;
            }
            return this.k;
        }

        private void adjustHeightsOfMarkers() {
            for (int i = 2; i <= 4; i++) {
                estimate(i);
            }
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double estimate(int i) {
            if (i < 2 || i > 4) {
                throw new OutOfRangeException(Integer.valueOf(i), 2, 4);
            }
            return this.markerArray[i].estimate();
        }

        private void incrementPositions(int i, int i2, int i3) {
            while (i2 <= i3) {
                this.markerArray[i2].incrementPosition(i);
                i2++;
            }
        }

        private void updateDesiredPositions() {
            int i = 1;
            while (true) {
                Marker[] markerArr = this.markerArray;
                if (i >= markerArr.length) {
                    return;
                }
                markerArr[i].updateDesiredPosition();
                i++;
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws NullArgumentException, ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            int i = 1;
            while (i < 5) {
                Marker[] markerArr = this.markerArray;
                int i2 = i + 1;
                markerArr[i].previous(markerArr[i - 1]).next(this.markerArray[i2]).index(i);
                i = i2;
            }
            Marker marker = this.markerArray[0];
            marker.previous(marker).next(this.markerArray[1]).index(0);
            Marker[] markerArr2 = this.markerArray;
            markerArr2[5].previous(markerArr2[4]).next(this.markerArray[5]).index(5);
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double height(int i) {
            Marker[] markerArr = this.markerArray;
            if (i >= markerArr.length || i <= 0) {
                throw new OutOfRangeException(Integer.valueOf(i), 1, Integer.valueOf(this.markerArray.length));
            }
            return markerArr[i].markerHeight;
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public Object clone() {
            return new Markers(new Marker[]{new Marker(), (Marker) this.markerArray[1].clone(), (Marker) this.markerArray[2].clone(), (Marker) this.markerArray[3].clone(), (Marker) this.markerArray[4].clone(), (Marker) this.markerArray[5].clone()});
        }

        public String toString() {
            return String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", this.markerArray[1].toString(), this.markerArray[2].toString(), this.markerArray[3].toString(), this.markerArray[4].toString(), this.markerArray[5].toString());
        }
    }

    private static class Marker implements Serializable, Cloneable {
        private static final long serialVersionUID = -3575879478288538431L;
        private final UnivariateInterpolator nonLinear;
        private double desiredMarkerIncrement;
        private double desiredMarkerPosition;
        private int index;
        private double intMarkerPosition;
        private transient UnivariateInterpolator linear;
        private double markerHeight;
        private transient Marker next;
        private transient Marker previous;

        private Marker() {
            this.nonLinear = new NevilleInterpolator();
            this.linear = new LinearInterpolator();
            this.previous = this;
            this.next = this;
        }

        private Marker(double d, double d2, double d3, double d4) {
            this();
            this.markerHeight = d;
            this.desiredMarkerPosition = d2;
            this.desiredMarkerIncrement = d3;
            this.intMarkerPosition = d4;
        }

        private double difference() {
            return this.desiredMarkerPosition - this.intMarkerPosition;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void incrementPosition(int i) {
            this.intMarkerPosition += i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker index(int i) {
            this.index = i;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateDesiredPosition() {
            this.desiredMarkerPosition += this.desiredMarkerIncrement;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker previous(Marker marker) throws NullArgumentException {
            MathUtils.checkNotNull(marker);
            this.previous = marker;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker next(Marker marker) throws NullArgumentException {
            MathUtils.checkNotNull(marker);
            this.next = marker;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double estimate() throws NullArgumentException, DimensionMismatchException {
            double dDifference = difference();
            Marker marker = this.next;
            double d = marker.intMarkerPosition;
            double d2 = this.intMarkerPosition;
            boolean z = d - d2 > 1.0d;
            Marker marker2 = this.previous;
            double d3 = marker2.intMarkerPosition;
            boolean z2 = d3 - d2 < -1.0d;
            if ((dDifference >= 1.0d && z) || (dDifference <= -1.0d && z2)) {
                int i = dDifference >= 0.0d ? 1 : -1;
                double[] dArr = {d3, d2, d};
                double[] dArr2 = {marker2.markerHeight, this.markerHeight, marker.markerHeight};
                double d4 = d2 + i;
                double dValue = this.nonLinear.interpolate(dArr, dArr2).value(d4);
                this.markerHeight = dValue;
                if (isEstimateBad(dArr2, dValue)) {
                    double d5 = dArr[1];
                    int i2 = (d4 - d5 > 0.0d ? 1 : -1) + 1;
                    double[] dArr3 = {d5, dArr[i2]};
                    double[] dArr4 = {dArr2[1], dArr2[i2]};
                    MathArrays.sortInPlace(dArr3, dArr4);
                    this.markerHeight = this.linear.interpolate(dArr3, dArr4).value(d4);
                }
                incrementPosition(i);
            }
            return this.markerHeight;
        }

        private boolean isEstimateBad(double[] dArr, double d) {
            return d <= dArr[0] || d >= dArr[2];
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Marker)) {
                Marker marker = (Marker) obj;
                if (Double.compare(this.markerHeight, marker.markerHeight) == 0 && Double.compare(this.intMarkerPosition, marker.intMarkerPosition) == 0 && Double.compare(this.desiredMarkerPosition, marker.desiredMarkerPosition) == 0 && Double.compare(this.desiredMarkerIncrement, marker.desiredMarkerIncrement) == 0 && this.next.index == marker.next.index && this.previous.index == marker.previous.index) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new double[]{this.markerHeight, this.intMarkerPosition, this.desiredMarkerIncrement, this.desiredMarkerPosition, this.previous.index, this.next.index});
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.next = this;
            this.previous = this;
            this.linear = new LinearInterpolator();
        }

        public Object clone() {
            return new Marker(this.markerHeight, this.desiredMarkerPosition, this.desiredMarkerIncrement, this.intMarkerPosition);
        }

        public String toString() {
            return String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", Double.valueOf(this.index), Double.valueOf(Precision.round(this.intMarkerPosition, 0)), Double.valueOf(Precision.round(this.desiredMarkerPosition, 2)), Double.valueOf(Precision.round(this.markerHeight, 2)), Double.valueOf(Precision.round(this.desiredMarkerIncrement, 2)), Integer.valueOf(this.previous.index), Integer.valueOf(this.next.index));
        }
    }

    private static class FixedCapacityList<E> extends ArrayList<E> implements Serializable {
        private static final long serialVersionUID = 2283952083075725479L;
        private final int capacity;

        FixedCapacityList(int i) {
            super(i);
            this.capacity = i;
        }

        @Override
        // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E e) {
            if (size() < this.capacity) {
                return super.add(e);
            }
            return false;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> collection) {
            if (collection == null || collection.size() + size() > this.capacity) {
                return false;
            }
            return super.addAll(collection);
        }
    }
}
