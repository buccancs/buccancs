package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import java.util.Iterator;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public class TopographicErrorHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean relativeCount;

    public TopographicErrorHistogram(boolean z, DistanceMeasure distanceMeasure) {
        this.relativeCount = z;
        this.distance = distanceMeasure;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization
    public double[][] computeImage(NeuronSquareMesh2D neuronSquareMesh2D, Iterable<double[]> iterable) throws DimensionMismatchException {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        Network network = neuronSquareMesh2D.getNetwork();
        LocationFinder locationFinder = new LocationFinder(neuronSquareMesh2D);
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, numberOfRows, numberOfColumns);
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, numberOfRows, numberOfColumns);
        Iterator<double[]> it2 = iterable.iterator();
        while (it2.hasNext()) {
            Pair<Neuron, Neuron> pairFindBestAndSecondBest = MapUtils.findBestAndSecondBest(it2.next(), neuronSquareMesh2D, this.distance);
            Neuron first = pairFindBestAndSecondBest.getFirst();
            LocationFinder.Location location = locationFinder.getLocation(first);
            int row = location.getRow();
            int column = location.getColumn();
            int[] iArr2 = iArr[row];
            iArr2[column] = iArr2[column] + 1;
            if (!network.getNeighbours(first).contains(pairFindBestAndSecondBest.getSecond())) {
                double[] dArr2 = dArr[row];
                dArr2[column] = dArr2[column] + 1.0d;
            }
        }
        if (this.relativeCount) {
            for (int i = 0; i < numberOfRows; i++) {
                for (int i2 = 0; i2 < numberOfColumns; i2++) {
                    double[] dArr3 = dArr[i];
                    dArr3[i2] = dArr3[i2] / iArr[i][i2];
                }
            }
        }
        return dArr;
    }
}
