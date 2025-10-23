package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class OutlineExtractor {
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;

    public OutlineExtractor(Vector3D vector3D, Vector3D vector3D2) {
        this.u = vector3D;
        this.v = vector3D2;
        this.w = Vector3D.crossProduct(vector3D, vector3D2);
    }

    public Vector2D[][] getOutline(PolyhedronsSet polyhedronsSet) {
        BoundaryProjector boundaryProjector = new BoundaryProjector(polyhedronsSet.getTolerance());
        polyhedronsSet.getTree(true).visit(boundaryProjector);
        Vector2D[][] vertices = boundaryProjector.getProjected().getVertices();
        for (int i = 0; i < vertices.length; i++) {
            Vector2D[] vector2DArr = vertices[i];
            int length = vector2DArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (pointIsBetween(vector2DArr, length, i2)) {
                    int i3 = i2;
                    while (i3 < length - 1) {
                        int i4 = i3 + 1;
                        vector2DArr[i3] = vector2DArr[i4];
                        i3 = i4;
                    }
                    length--;
                } else {
                    i2++;
                }
            }
            if (length != vector2DArr.length) {
                Vector2D[] vector2DArr2 = new Vector2D[length];
                vertices[i] = vector2DArr2;
                System.arraycopy(vector2DArr, 0, vector2DArr2, 0, length);
            }
        }
        return vertices;
    }

    private boolean pointIsBetween(Vector2D[] vector2DArr, int i, int i2) {
        Vector2D vector2D = vector2DArr[((i2 + i) - 1) % i];
        Vector2D vector2D2 = vector2DArr[i2];
        Vector2D vector2D3 = vector2DArr[(i2 + 1) % i];
        double x = vector2D2.getX() - vector2D.getX();
        double y = vector2D2.getY() - vector2D.getY();
        double x2 = vector2D3.getX() - vector2D2.getX();
        double y2 = vector2D3.getY() - vector2D2.getY();
        return FastMath.abs((x * y2) - (x2 * y)) <= FastMath.sqrt(((x * x) + (y * y)) * ((x2 * x2) + (y2 * y2))) * 1.0E-6d && (x * x2) + (y * y2) >= 0.0d;
    }

    private class BoundaryProjector implements BSPTreeVisitor<Euclidean3D> {
        private final double tolerance;
        private PolygonsSet projected;

        BoundaryProjector(double d) {
            this.projected = new PolygonsSet((BSPTree<Euclidean2D>) new BSPTree(Boolean.FALSE), d);
            this.tolerance = d;
        }

        public PolygonsSet getProjected() {
            return this.projected;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitLeafNode(BSPTree<Euclidean3D> bSPTree) {
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> bSPTree) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitInternalNode(BSPTree<Euclidean3D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), true);
            }
        }

        private void addContribution(SubHyperplane<Euclidean3D> subHyperplane, boolean z) {
            AbstractSubHyperplane abstractSubHyperplane = (AbstractSubHyperplane) subHyperplane;
            Plane plane = (Plane) subHyperplane.getHyperplane();
            double dDotProduct = plane.getNormal().dotProduct(OutlineExtractor.this.w);
            if (FastMath.abs(dDotProduct) > 0.001d) {
                Vector2D[][] vertices = ((PolygonsSet) abstractSubHyperplane.getRemainingRegion()).getVertices();
                char c = 0;
                int i = 1;
                if ((dDotProduct < 0.0d) ^ z) {
                    Vector2D[][] vector2DArr = new Vector2D[vertices.length][];
                    for (int i2 = 0; i2 < vertices.length; i2++) {
                        Vector2D[] vector2DArr2 = vertices[i2];
                        Vector2D[] vector2DArr3 = new Vector2D[vector2DArr2.length];
                        if (vector2DArr2[0] == null) {
                            vector2DArr3[0] = null;
                            for (int i3 = 1; i3 < vector2DArr2.length; i3++) {
                                vector2DArr3[i3] = vector2DArr2[vector2DArr2.length - i3];
                            }
                        } else {
                            int i4 = 0;
                            while (i4 < vector2DArr2.length) {
                                int i5 = i4 + 1;
                                vector2DArr3[i4] = vector2DArr2[vector2DArr2.length - i5];
                                i4 = i5;
                            }
                        }
                        vector2DArr[i2] = vector2DArr3;
                    }
                    vertices = vector2DArr;
                }
                ArrayList arrayList = new ArrayList();
                int length = vertices.length;
                int i6 = 0;
                while (i6 < length) {
                    Vector2D[] vector2DArr4 = vertices[i6];
                    boolean z2 = vector2DArr4[c] != null;
                    int length2 = z2 ? vector2DArr4.length - i : 1;
                    Vector3D space = plane.toSpace((Point<Euclidean2D>) vector2DArr4[length2]);
                    int length3 = (length2 + 1) % vector2DArr4.length;
                    boolean z3 = z2;
                    Vector2D vector2D = new Vector2D(space.dotProduct(OutlineExtractor.this.u), space.dotProduct(OutlineExtractor.this.v));
                    int i7 = length2;
                    int i8 = length3;
                    while (i8 < vector2DArr4.length) {
                        Vector3D space2 = plane.toSpace((Point<Euclidean2D>) vector2DArr4[i8]);
                        Vector2D vector2D2 = new Vector2D(space2.dotProduct(OutlineExtractor.this.u), space2.dotProduct(OutlineExtractor.this.v));
                        org.apache.commons.math3.geometry.euclidean.twod.Line line = new org.apache.commons.math3.geometry.euclidean.twod.Line(vector2D, vector2D2, this.tolerance);
                        SubHyperplane subHyperplaneWholeHyperplane = line.wholeHyperplane();
                        if (z3 || i7 != 1) {
                            subHyperplaneWholeHyperplane = subHyperplaneWholeHyperplane.split(new org.apache.commons.math3.geometry.euclidean.twod.Line(vector2D, line.getAngle() + 1.5707963267948966d, this.tolerance)).getPlus();
                        }
                        if (z3 || i8 != vector2DArr4.length - 1) {
                            subHyperplaneWholeHyperplane = subHyperplaneWholeHyperplane.split(new org.apache.commons.math3.geometry.euclidean.twod.Line(vector2D2, line.getAngle() + 1.5707963267948966d, this.tolerance)).getMinus();
                        }
                        arrayList.add(subHyperplaneWholeHyperplane);
                        vector2D = vector2D2;
                        int i9 = i8;
                        i8++;
                        i7 = i9;
                    }
                    i6++;
                    c = 0;
                    i = 1;
                }
                this.projected = (PolygonsSet) new RegionFactory().union(this.projected, new PolygonsSet(arrayList, this.tolerance));
            }
        }
    }
}
