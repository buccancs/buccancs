package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
/* loaded from: classes5.dex */
public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
    private final EmptyClusterStrategy emptyStrategy;
    private final Random random;

    public KMeansPlusPlusClusterer(Random random) {
        this(random, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(Random random, EmptyClusterStrategy emptyClusterStrategy) {
        this.random = random;
        this.emptyStrategy = emptyClusterStrategy;
    }

    private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> list, Collection<T> collection, int[] iArr) {
        int i = 0;
        int i2 = 0;
        for (T t : collection) {
            int nearestCluster = getNearestCluster(list, t);
            if (nearestCluster != iArr[i2]) {
                i++;
            }
            list.get(nearestCluster).addPoint(t);
            iArr[i2] = nearestCluster;
            i2++;
        }
        return i;
    }

    private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(Collection<T> collection, int i, Random random) {
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        int size = listUnmodifiableList.size();
        boolean[] zArr = new boolean[size];
        ArrayList arrayList = new ArrayList();
        int iNextInt = random.nextInt(size);
        Clusterable clusterable = (Clusterable) listUnmodifiableList.get(iNextInt);
        arrayList.add(new Cluster(clusterable));
        zArr[iNextInt] = true;
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != iNextInt) {
                double dDistanceFrom = clusterable.distanceFrom(listUnmodifiableList.get(i2));
                dArr[i2] = dDistanceFrom * dDistanceFrom;
            }
        }
        while (arrayList.size() < i) {
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i3 = 0; i3 < size; i3++) {
                if (!zArr[i3]) {
                    d2 += dArr[i3];
                }
            }
            double dNextDouble = random.nextDouble() * d2;
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    i4 = -1;
                    break;
                }
                if (!zArr[i4]) {
                    d += dArr[i4];
                    if (d >= dNextDouble) {
                        break;
                    }
                }
                i4++;
            }
            if (i4 == -1) {
                int i5 = size - 1;
                while (true) {
                    if (i5 < 0) {
                        break;
                    }
                    if (!zArr[i5]) {
                        i4 = i5;
                        break;
                    }
                    i5--;
                }
            }
            if (i4 < 0) {
                break;
            }
            Clusterable clusterable2 = (Clusterable) listUnmodifiableList.get(i4);
            arrayList.add(new Cluster(clusterable2));
            zArr[i4] = true;
            if (arrayList.size() < i) {
                for (int i6 = 0; i6 < size; i6++) {
                    if (!zArr[i6]) {
                        double dDistanceFrom2 = clusterable2.distanceFrom(listUnmodifiableList.get(i6));
                        double d3 = dDistanceFrom2 * dDistanceFrom2;
                        if (d3 < dArr[i6]) {
                            dArr[i6] = d3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> collection, T t) {
        Iterator<Cluster<T>> it2 = collection.iterator();
        double d = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        while (it2.hasNext()) {
            double dDistanceFrom = t.distanceFrom(it2.next().getCenter());
            if (dDistanceFrom < d) {
                i = i2;
                d = dDistanceFrom;
            }
            i2++;
        }
        return i;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2, int i3) throws ConvergenceException, MathIllegalArgumentException {
        List<Cluster<T>> list = null;
        double d = Double.POSITIVE_INFINITY;
        for (int i4 = 0; i4 < i2; i4++) {
            List<Cluster<T>> listCluster = cluster(collection, i, i3);
            double result = 0.0d;
            for (Cluster<T> cluster : listCluster) {
                if (!cluster.getPoints().isEmpty()) {
                    Clusterable center = cluster.getCenter();
                    Variance variance = new Variance();
                    Iterator<T> it2 = cluster.getPoints().iterator();
                    while (it2.hasNext()) {
                        variance.increment(it2.next().distanceFrom(center));
                    }
                    result += variance.getResult();
                }
            }
            if (result <= d) {
                list = listCluster;
                d = result;
            }
        }
        return list;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2) throws ConvergenceException, MathIllegalArgumentException {
        boolean z;
        Clusterable pointFromLargestVarianceCluster;
        MathUtils.checkNotNull(collection);
        if (collection.size() < i) {
            throw new NumberIsTooSmallException(Integer.valueOf(collection.size()), Integer.valueOf(i), false);
        }
        List<Cluster<T>> listChooseInitialCenters = chooseInitialCenters(collection, i, this.random);
        int[] iArr = new int[collection.size()];
        assignPointsToClusters(listChooseInitialCenters, collection, iArr);
        if (i2 < 0) {
            i2 = Integer.MAX_VALUE;
        }
        int i3 = 0;
        while (i3 < i2) {
            ArrayList arrayList = new ArrayList();
            boolean z2 = false;
            for (Cluster<T> cluster : listChooseInitialCenters) {
                if (cluster.getPoints().isEmpty()) {
                    int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy[this.emptyStrategy.ordinal()];
                    z = true;
                    if (i4 == 1) {
                        pointFromLargestVarianceCluster = getPointFromLargestVarianceCluster(listChooseInitialCenters);
                    } else if (i4 == 2) {
                        pointFromLargestVarianceCluster = getPointFromLargestNumberCluster(listChooseInitialCenters);
                    } else if (i4 == 3) {
                        pointFromLargestVarianceCluster = getFarthestPoint(listChooseInitialCenters);
                    } else {
                        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                    }
                } else {
                    Clusterable clusterable = (Clusterable) cluster.getCenter().centroidOf(cluster.getPoints());
                    z = z2;
                    pointFromLargestVarianceCluster = clusterable;
                }
                arrayList.add(new Cluster<>(pointFromLargestVarianceCluster));
                z2 = z;
            }
            if (assignPointsToClusters(arrayList, collection, iArr) == 0 && !z2) {
                return arrayList;
            }
            i3++;
            listChooseInitialCenters = arrayList;
        }
        return listChooseInitialCenters;
    }

    private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        double d = Double.NEGATIVE_INFINITY;
        Cluster<T> cluster = null;
        for (Cluster<T> cluster2 : collection) {
            if (!cluster2.getPoints().isEmpty()) {
                Clusterable center = cluster2.getCenter();
                Variance variance = new Variance();
                Iterator<T> it2 = cluster2.getPoints().iterator();
                while (it2.hasNext()) {
                    variance.increment(it2.next().distanceFrom(center));
                }
                double result = variance.getResult();
                if (result > d) {
                    cluster = cluster2;
                    d = result;
                }
            }
        }
        if (cluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List<T> points = cluster.getPoints();
        return points.remove(this.random.nextInt(points.size()));
    }

    private T getPointFromLargestNumberCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        Cluster<T> cluster = null;
        int i = 0;
        for (Cluster<T> cluster2 : collection) {
            int size = cluster2.getPoints().size();
            if (size > i) {
                cluster = cluster2;
                i = size;
            }
        }
        if (cluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List<T> points = cluster.getPoints();
        return points.remove(this.random.nextInt(points.size()));
    }

    private T getFarthestPoint(Collection<Cluster<T>> collection) throws ConvergenceException {
        Iterator<Cluster<T>> it2 = collection.iterator();
        double d = Double.NEGATIVE_INFINITY;
        Cluster<T> cluster = null;
        int i = -1;
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Cluster<T> next = it2.next();
            Clusterable center = next.getCenter();
            List<T> points = next.getPoints();
            for (int i2 = 0; i2 < points.size(); i2++) {
                double dDistanceFrom = points.get(i2).distanceFrom(center);
                if (dDistanceFrom > d) {
                    cluster = next;
                    i = i2;
                    d = dDistanceFrom;
                }
            }
        }
        if (cluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        return cluster.getPoints().remove(i);
    }

    public enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR
    }

    /* renamed from: org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy;

        static {
            int[] iArr = new int[EmptyClusterStrategy.values().length];
            $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy = iArr;
            try {
                iArr[EmptyClusterStrategy.LARGEST_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy[EmptyClusterStrategy.LARGEST_POINTS_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy[EmptyClusterStrategy.FARTHEST_POINT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
