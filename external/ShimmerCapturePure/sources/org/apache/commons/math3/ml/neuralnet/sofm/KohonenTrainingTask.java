package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Iterator;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.ml.neuralnet.Network;

/* loaded from: classes5.dex */
public class KohonenTrainingTask implements Runnable {
    private final Iterator<double[]> featuresIterator;
    private final Network net;
    private final KohonenUpdateAction updateAction;

    public KohonenTrainingTask(Network network, Iterator<double[]> it2, KohonenUpdateAction kohonenUpdateAction) {
        this.net = network;
        this.featuresIterator = it2;
        this.updateAction = kohonenUpdateAction;
    }

    @Override // java.lang.Runnable
    public void run() throws DimensionMismatchException {
        while (this.featuresIterator.hasNext()) {
            this.updateAction.update(this.net, this.featuresIterator.next());
        }
    }
}
