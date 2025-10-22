package org.apache.commons.math.random;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.math.stat.descriptive.StatisticalSummary;
import org.apache.commons.math.stat.descriptive.SummaryStatistics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/EmpiricalDistribution.class */
public interface EmpiricalDistribution {
    void load(double[] dArr);

    void load(File file) throws IOException;

    void load(URL url) throws IOException;

    double getNextValue() throws IllegalStateException;

    StatisticalSummary getSampleStats() throws IllegalStateException;

    boolean isLoaded();

    int getBinCount();

    List<SummaryStatistics> getBinStats();

    double[] getUpperBounds();
}
