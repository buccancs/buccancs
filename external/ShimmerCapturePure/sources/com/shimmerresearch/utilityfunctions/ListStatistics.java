package com.shimmerresearch.utilityfunctions;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ListStatistics {
    public double ListSum(List<Double> list) {
        double dDoubleValue = 0.0d;
        for (int i = 0; i < list.size(); i++) {
            dDoubleValue += list.get(i).doubleValue();
        }
        return dDoubleValue;
    }

    public double ListMean(List<Double> list) {
        return ListSum(list) / list.size();
    }

    public List<Double> ListDiff(List<Double> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < list.size(); i++) {
            arrayList.add(Double.valueOf(list.get(i).doubleValue() - list.get(i - 1).doubleValue()));
        }
        return arrayList;
    }

    public List<Double> ListPower(List<Double> list, double d) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Double.valueOf(Math.pow(list.get(i).doubleValue(), d)));
        }
        return arrayList;
    }

    public double ListStdDev(List<Double> list) {
        int size = list.size();
        double dListMean = ListMean(list);
        double dPow = 0.0d;
        for (int i = 0; i < size; i++) {
            dPow += Math.pow(list.get(i).doubleValue() - dListMean, 2.0d);
        }
        return Math.sqrt(dPow / size);
    }
}
