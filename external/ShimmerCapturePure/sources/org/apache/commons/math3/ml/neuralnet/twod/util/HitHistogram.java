package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import java.util.Iterator;

import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;

/* loaded from: classes5.dex */
public class HitHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean normalizeCount;

    public HitHistogram(boolean z, DistanceMeasure distanceMeasure) {
        this.normalizeCount = z;
        this.distance = distanceMeasure;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization
    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D, Iterable<double[]> iterable) {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        LocationFinder locationFinder = new LocationFinder(neuronSquareMesh2D);
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, numberOfRows, numberOfColumns);
        Iterator<double[]> it2 = iterable.iterator();
        int i = 0;
        while (it2.hasNext()) {
            LocationFinder.Location location = locationFinder.getLocation(MapUtils.findBest(it2.next(), neuronSquareMesh2D, this.distance));
            int row = location.getRow();
            int column = location.getColumn();
            double[] dArr2 = dArr[row];
            dArr2[column] = dArr2[column] + 1.0d;
            i++;
        }
        if (this.normalizeCount) {
            for (int i2 = 0; i2 < numberOfRows; i2++) {
                for (int i3 = 0; i3 < numberOfColumns; i3++) {
                    double[] dArr3 = dArr[i2];
                    dArr3[i3] = dArr3[i3] / i;
                }
            }
        }
        return dArr;
    }
}
