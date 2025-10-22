package com.shimmerresearch.utilityfunctions;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class LombScarglePeriodogram {
    private ListStatistics mListStats = new ListStatistics();

    public List<Double> calculatePeriodogram(List<Double> list, List<Double> list2, double[] dArr) {
        int size = list.size();
        int length = dArr.length;
        double dListMean = this.mListStats.ListMean(list);
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < size; i++) {
            arrayList.add(Double.valueOf(list.get(i).doubleValue() - dListMean));
        }
        ArrayList arrayList2 = new ArrayList(length);
        for (double d : dArr) {
            double d2 = 6.283185307179586d * d;
            double d3 = d2 * 2.0d;
            double dDoubleValue = 0.0d;
            double dSin = 0.0d;
            double dCos = 0.0d;
            for (int i2 = 0; i2 < size; i2++) {
                dSin += Math.sin(list2.get(i2).doubleValue() * d3);
                dCos += Math.cos(list2.get(i2).doubleValue() * d3);
            }
            double dAtan2 = Math.atan2(dSin, dCos);
            double dDoubleValue2 = 0.0d;
            double dCos2 = 0.0d;
            double dSin2 = 0.0d;
            for (int i3 = 0; i3 < size; i3++) {
                double dDoubleValue3 = (list2.get(i3).doubleValue() - dAtan2) * d2;
                dDoubleValue += ((Double) arrayList.get(i3)).doubleValue() * Math.cos(dDoubleValue3);
                dDoubleValue2 += ((Double) arrayList.get(i3)).doubleValue() * Math.sin(dDoubleValue3);
                dCos2 += Math.cos(dDoubleValue3) * Math.cos(dDoubleValue3);
                dSin2 += Math.sin(dDoubleValue3) * Math.sin(dDoubleValue3);
            }
            arrayList2.add(Double.valueOf((((dDoubleValue * dDoubleValue) / dCos2) + ((dDoubleValue2 * dDoubleValue2) / dSin2)) / 2.0d));
        }
        return arrayList2;
    }
}
