package org.apache.commons.math.optimization.linear;

import java.util.Collection;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/linear/LinearOptimizer.class */
public interface LinearOptimizer {
    int getMaxIterations();

    void setMaxIterations(int i);

    int getIterations();

    RealPointValuePair optimize(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z) throws OptimizationException;
}
