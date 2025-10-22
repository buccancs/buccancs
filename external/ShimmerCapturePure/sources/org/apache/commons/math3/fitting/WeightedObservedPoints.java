package org.apache.commons.math3.fitting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WeightedObservedPoints implements Serializable {
    private static final long serialVersionUID = 20130813;
    private final List<WeightedObservedPoint> observations = new ArrayList();

    public void add(double d, double d2) {
        add(1.0d, d, d2);
    }

    public void add(double d, double d2, double d3) {
        this.observations.add(new WeightedObservedPoint(d, d2, d3));
    }

    public void add(WeightedObservedPoint weightedObservedPoint) {
        this.observations.add(weightedObservedPoint);
    }

    public List<WeightedObservedPoint> toList() {
        return new ArrayList(this.observations);
    }

    public void clear() {
        this.observations.clear();
    }
}
