package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

/* loaded from: classes5.dex */
public abstract class Clusterer<T extends Clusterable> {
    private DistanceMeasure measure;

    protected Clusterer(DistanceMeasure distanceMeasure) {
        this.measure = distanceMeasure;
    }

    public abstract List<? extends Cluster<T>> cluster(Collection<T> collection) throws ConvergenceException, MathIllegalArgumentException;

    public DistanceMeasure getDistanceMeasure() {
        return this.measure;
    }

    protected double distance(Clusterable clusterable, Clusterable clusterable2) {
        return this.measure.compute(clusterable.getPoint(), clusterable2.getPoint());
    }
}
