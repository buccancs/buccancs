package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator;
import org.apache.commons.math3.ml.clustering.evaluation.SumOfClusterVariances;

/* loaded from: classes5.dex */
public class MultiKMeansPlusPlusClusterer<T extends Clusterable> extends Clusterer<T> {
    private final KMeansPlusPlusClusterer<T> clusterer;
    private final ClusterEvaluator<T> evaluator;
    private final int numTrials;

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> kMeansPlusPlusClusterer, int i) {
        this(kMeansPlusPlusClusterer, i, new SumOfClusterVariances(kMeansPlusPlusClusterer.getDistanceMeasure()));
    }

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> kMeansPlusPlusClusterer, int i, ClusterEvaluator<T> clusterEvaluator) {
        super(kMeansPlusPlusClusterer.getDistanceMeasure());
        this.clusterer = kMeansPlusPlusClusterer;
        this.numTrials = i;
        this.evaluator = clusterEvaluator;
    }

    public ClusterEvaluator<T> getClusterEvaluator() {
        return this.evaluator;
    }

    public KMeansPlusPlusClusterer<T> getClusterer() {
        return this.clusterer;
    }

    public int getNumTrials() {
        return this.numTrials;
    }

    @Override // org.apache.commons.math3.ml.clustering.Clusterer
    public List<CentroidCluster<T>> cluster(Collection<T> collection) throws ConvergenceException, MathIllegalArgumentException {
        List<CentroidCluster<T>> list = null;
        double d = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.numTrials; i++) {
            List<CentroidCluster<T>> listCluster = this.clusterer.cluster(collection);
            double dScore = this.evaluator.score(listCluster);
            if (this.evaluator.isBetterScore(dScore, d)) {
                list = listCluster;
                d = dScore;
            }
        }
        return list;
    }
}
