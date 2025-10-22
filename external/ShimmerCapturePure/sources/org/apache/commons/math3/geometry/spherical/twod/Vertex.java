package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class Vertex {
    private final S2Point location;
    private final List<Circle> circles = new ArrayList();
    private Edge incoming = null;
    private Edge outgoing = null;

    Vertex(S2Point s2Point) {
        this.location = s2Point;
    }

    public Edge getIncoming() {
        return this.incoming;
    }

    void setIncoming(Edge edge) {
        this.incoming = edge;
        bindWith(edge.getCircle());
    }

    public S2Point getLocation() {
        return this.location;
    }

    public Edge getOutgoing() {
        return this.outgoing;
    }

    void setOutgoing(Edge edge) {
        this.outgoing = edge;
        bindWith(edge.getCircle());
    }

    void bindWith(Circle circle) {
        this.circles.add(circle);
    }

    Circle sharedCircleWith(Vertex vertex) {
        for (Circle circle : this.circles) {
            Iterator<Circle> it2 = vertex.circles.iterator();
            while (it2.hasNext()) {
                if (circle == it2.next()) {
                    return circle;
                }
            }
        }
        return null;
    }
}
