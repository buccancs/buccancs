package org.apache.commons.math3.optimization.linear;

import java.util.Collection;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
/* loaded from: classes5.dex */
public interface LinearOptimizer {
    int getIterations();

    int getMaxIterations();

    void setMaxIterations(int i);

    PointValuePair optimize(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z) throws MathIllegalStateException;
}
