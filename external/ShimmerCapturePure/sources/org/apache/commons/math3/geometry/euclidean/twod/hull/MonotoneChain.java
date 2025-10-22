package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class MonotoneChain extends AbstractConvexHullGenerator2D {
    public MonotoneChain() {
        this(false);
    }

    public MonotoneChain(boolean z) {
        super(z);
    }

    public MonotoneChain(boolean z, double d) {
        super(z, d);
    }

    @Override
    // org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D, org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHullGenerator2D, org.apache.commons.math3.geometry.hull.ConvexHullGenerator
    public /* bridge */ /* synthetic */ ConvexHull2D generate(Collection collection) throws NullArgumentException, ConvergenceException {
        return super.generate((Collection<Vector2D>) collection);
    }

    @Override // org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D
    public /* bridge */ /* synthetic */ double getTolerance() {
        return super.getTolerance();
    }

    @Override // org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D
    public /* bridge */ /* synthetic */ boolean isIncludeCollinearPoints() {
        return super.isIncludeCollinearPoints();
    }

    @Override // org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D
    public Collection<Vector2D> findHullVertices(Collection<Vector2D> collection) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, new Comparator<Vector2D>() { // from class: org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.1
            @Override // java.util.Comparator
            public int compare(Vector2D vector2D, Vector2D vector2D2) {
                double tolerance = MonotoneChain.this.getTolerance();
                int iCompareTo = Precision.compareTo(vector2D.getX(), vector2D2.getX(), tolerance);
                return iCompareTo == 0 ? Precision.compareTo(vector2D.getY(), vector2D2.getY(), tolerance) : iCompareTo;
            }
        });
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            updateHull((Vector2D) it2.next(), arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            updateHull((Vector2D) arrayList.get(size), arrayList3);
        }
        ArrayList arrayList4 = new ArrayList((arrayList2.size() + arrayList3.size()) - 2);
        for (int i = 0; i < arrayList2.size() - 1; i++) {
            arrayList4.add(arrayList2.get(i));
        }
        for (int i2 = 0; i2 < arrayList3.size() - 1; i2++) {
            arrayList4.add(arrayList3.get(i2));
        }
        if (arrayList4.isEmpty() && !arrayList2.isEmpty()) {
            arrayList4.add(arrayList2.get(0));
        }
        return arrayList4;
    }

    private void updateHull(Vector2D vector2D, List<Vector2D> list) {
        double tolerance = getTolerance();
        if (list.size() != 1 || list.get(0).distance((Vector<Euclidean2D>) vector2D) >= tolerance) {
            while (list.size() >= 2) {
                int size = list.size();
                Vector2D vector2D2 = list.get(size - 2);
                int i = size - 1;
                Vector2D vector2D3 = list.get(i);
                double offset = new Line(vector2D2, vector2D3, tolerance).getOffset((Vector<Euclidean2D>) vector2D);
                if (FastMath.abs(offset) >= tolerance) {
                    if (offset <= 0.0d) {
                        break;
                    } else {
                        list.remove(i);
                    }
                } else {
                    double dDistance = vector2D2.distance((Vector<Euclidean2D>) vector2D);
                    if (dDistance < tolerance || vector2D3.distance((Vector<Euclidean2D>) vector2D) < tolerance) {
                        return;
                    }
                    double dDistance2 = vector2D2.distance((Vector<Euclidean2D>) vector2D3);
                    if (isIncludeCollinearPoints()) {
                        if (dDistance < dDistance2) {
                            size = i;
                        }
                        list.add(size, vector2D);
                        return;
                    } else {
                        if (dDistance > dDistance2) {
                            list.remove(i);
                            list.add(vector2D);
                            return;
                        }
                        return;
                    }
                }
            }
            list.add(vector2D);
        }
    }
}
