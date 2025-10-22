package org.apache.commons.math.optimization.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/linear/Relationship.class */
public enum Relationship {
    EQ("="),
    LEQ("<="),
    GEQ(">=");

    private final String stringValue;

    Relationship(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.stringValue;
    }

    public Relationship oppositeRelationship() {
        switch (this) {
            case LEQ:
                return GEQ;
            case GEQ:
                return LEQ;
            default:
                return EQ;
        }
    }
}
