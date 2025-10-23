package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.UpdateAction;

/* loaded from: classes5.dex */
public class KohonenUpdateAction implements UpdateAction {
    private final DistanceMeasure distance;
    private final LearningFactorFunction learningFactor;
    private final NeighbourhoodSizeFunction neighbourhoodSize;
    private final AtomicLong numberOfCalls = new AtomicLong(0);

    public KohonenUpdateAction(DistanceMeasure distanceMeasure, LearningFactorFunction learningFactorFunction, NeighbourhoodSizeFunction neighbourhoodSizeFunction) {
        this.distance = distanceMeasure;
        this.learningFactor = learningFactorFunction;
        this.neighbourhoodSize = neighbourhoodSizeFunction;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.UpdateAction
    public void update(Network network, double[] dArr) throws DimensionMismatchException {
        long jIncrementAndGet = this.numberOfCalls.incrementAndGet() - 1;
        double dValue = this.learningFactor.value(jIncrementAndGet);
        Neuron neuronFindAndUpdateBestNeuron = findAndUpdateBestNeuron(network, dArr, dValue);
        int iValue = this.neighbourhoodSize.value(jIncrementAndGet);
        Gaussian gaussian = new Gaussian(dValue, 0.0d, iValue);
        if (iValue > 0) {
            Collection<Neuron> hashSet = new HashSet<>();
            hashSet.add(neuronFindAndUpdateBestNeuron);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(neuronFindAndUpdateBestNeuron);
            int i = 1;
            do {
                hashSet = network.getNeighbours(hashSet, hashSet2);
                Iterator<Neuron> it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    updateNeighbouringNeuron(it2.next(), dArr, gaussian.value(i));
                }
                hashSet2.addAll(hashSet);
                i++;
            } while (i <= iValue);
        }
    }

    public long getNumberOfCalls() {
        return this.numberOfCalls.get();
    }

    private boolean attemptNeuronUpdate(Neuron neuron, double[] dArr, double d) {
        double[] features = neuron.getFeatures();
        return neuron.compareAndSetFeatures(features, computeFeatures(features, dArr, d));
    }

    private void updateNeighbouringNeuron(Neuron neuron, double[] dArr, double d) {
        while (!attemptNeuronUpdate(neuron, dArr, d)) {
        }
    }

    private Neuron findAndUpdateBestNeuron(Network network, double[] dArr, double d) throws DimensionMismatchException {
        Neuron neuronFindBest;
        do {
            neuronFindBest = MapUtils.findBest(dArr, network, this.distance);
        } while (!attemptNeuronUpdate(neuronFindBest, dArr, d));
        return neuronFindBest;
    }

    private double[] computeFeatures(double[] dArr, double[] dArr2, double d) {
        ArrayRealVector arrayRealVector = new ArrayRealVector(dArr, false);
        return new ArrayRealVector(dArr2, false).subtract((RealVector) arrayRealVector).mapMultiplyToSelf(d).add(arrayRealVector).toArray();
    }
}
