package org.apache.commons.math.estimation;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/estimation/EstimatedParameter.class */
public class EstimatedParameter implements Serializable {
    private static final long serialVersionUID = -555440800213416949L;
    private final String name;
    protected double estimate;
    private boolean bound;

    public EstimatedParameter(String name, double firstEstimate) {
        this.name = name;
        this.estimate = firstEstimate;
        this.bound = false;
    }

    public EstimatedParameter(String name, double firstEstimate, boolean bound) {
        this.name = name;
        this.estimate = firstEstimate;
        this.bound = bound;
    }

    public EstimatedParameter(EstimatedParameter parameter) {
        this.name = parameter.name;
        this.estimate = parameter.estimate;
        this.bound = parameter.bound;
    }

    public double getEstimate() {
        return this.estimate;
    }

    public void setEstimate(double estimate) {
        this.estimate = estimate;
    }

    public String getName() {
        return this.name;
    }

    public boolean isBound() {
        return this.bound;
    }

    public void setBound(boolean bound) {
        this.bound = bound;
    }
}
