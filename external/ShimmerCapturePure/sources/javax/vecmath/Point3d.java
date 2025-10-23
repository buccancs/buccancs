package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Point3d extends Tuple3d implements Serializable {
    static final long serialVersionUID = 5718062286069042927L;

    public Point3d(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    public Point3d(double[] dArr) {
        super(dArr);
    }

    public Point3d(Point3d point3d) {
        super(point3d);
    }

    public Point3d(Point3f point3f) {
        super(point3f);
    }

    public Point3d(Tuple3f tuple3f) {
        super(tuple3f);
    }

    public Point3d(Tuple3d tuple3d) {
        super(tuple3d);
    }

    public Point3d() {
    }

    public final double distanceSquared(Point3d point3d) {
        double d = this.x - point3d.x;
        double d2 = this.y - point3d.y;
        double d3 = this.z - point3d.z;
        return (d * d) + (d2 * d2) + (d3 * d3);
    }

    public final double distance(Point3d point3d) {
        double d = this.x - point3d.x;
        double d2 = this.y - point3d.y;
        double d3 = this.z - point3d.z;
        return Math.sqrt((d * d) + (d2 * d2) + (d3 * d3));
    }

    public final double distanceL1(Point3d point3d) {
        return Math.abs(this.x - point3d.x) + Math.abs(this.y - point3d.y) + Math.abs(this.z - point3d.z);
    }

    public final double distanceLinf(Point3d point3d) {
        return Math.max(Math.max(Math.abs(this.x - point3d.x), Math.abs(this.y - point3d.y)), Math.abs(this.z - point3d.z));
    }

    public final void project(Point4d point4d) {
        double d = 1.0d / point4d.w;
        this.x = point4d.x * d;
        this.y = point4d.y * d;
        this.z = point4d.z * d;
    }
}
