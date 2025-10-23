package org.apache.commons.math3.geometry.spherical.twod;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class Edge {
    private final Circle circle;
    private final double length;
    private final Vertex start;
    private Vertex end;

    Edge(Vertex vertex, Vertex vertex2, double d, Circle circle) {
        this.start = vertex;
        this.end = vertex2;
        this.length = d;
        this.circle = circle;
        vertex.setOutgoing(this);
        vertex2.setIncoming(this);
    }

    public Circle getCircle() {
        return this.circle;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public double getLength() {
        return this.length;
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vector3D getPointAt(double d) {
        Circle circle = this.circle;
        return circle.getPointAt(d + circle.getPhase(this.start.getLocation().getVector()));
    }

    void setNextEdge(Edge edge) {
        Vertex start = edge.getStart();
        this.end = start;
        start.setIncoming(this);
        this.end.bindWith(getCircle());
    }

    void split(Circle circle, List<Edge> list, List<Edge> list2) {
        double phase = this.circle.getPhase(this.start.getLocation().getVector());
        Arc insideArc = this.circle.getInsideArc(circle);
        double dNormalizeAngle = MathUtils.normalizeAngle(insideArc.getInf(), 3.141592653589793d + phase) - phase;
        double size = dNormalizeAngle + insideArc.getSize();
        double d = size - 6.283185307179586d;
        double tolerance = this.circle.getTolerance();
        Vertex vertexAddSubEdge = this.start;
        if (d >= this.length - tolerance) {
            list2.add(this);
            return;
        }
        if (d >= 0.0d) {
            vertexAddSubEdge = addSubEdge(vertexAddSubEdge, new Vertex(new S2Point(this.circle.getPointAt(phase + d))), d, list2, circle);
        } else {
            d = 0.0d;
        }
        double d2 = this.length;
        if (dNormalizeAngle >= d2 - tolerance) {
            if (d >= 0.0d) {
                addSubEdge(vertexAddSubEdge, this.end, d2 - d, list, circle);
                return;
            } else {
                list.add(this);
                return;
            }
        }
        double d3 = phase + dNormalizeAngle;
        Vertex vertexAddSubEdge2 = addSubEdge(vertexAddSubEdge, new Vertex(new S2Point(this.circle.getPointAt(d3))), dNormalizeAngle - d, list, circle);
        double d4 = this.length;
        if (size >= d4 - tolerance) {
            addSubEdge(vertexAddSubEdge2, this.end, d4 - dNormalizeAngle, list2, circle);
        } else {
            addSubEdge(addSubEdge(vertexAddSubEdge2, new Vertex(new S2Point(this.circle.getPointAt(d3))), dNormalizeAngle - dNormalizeAngle, list2, circle), this.end, this.length - dNormalizeAngle, list, circle);
        }
    }

    private Vertex addSubEdge(Vertex vertex, Vertex vertex2, double d, List<Edge> list, Circle circle) {
        if (d <= this.circle.getTolerance()) {
            return vertex;
        }
        vertex2.bindWith(circle);
        list.add(new Edge(vertex, vertex2, d, this.circle));
        return vertex2;
    }
}
