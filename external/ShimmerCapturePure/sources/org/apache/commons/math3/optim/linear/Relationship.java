package org.apache.commons.math3.optim.linear;

/* loaded from: classes5.dex */
public enum Relationship {
    EQ("="),
    LEQ("<="),
    GEQ(">=");

    private final String stringValue;

    Relationship(String str) {
        this.stringValue = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.stringValue;
    }

    public Relationship oppositeRelationship() {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$optim$linear$Relationship[ordinal()];
        return i != 1 ? i != 2 ? EQ : LEQ : GEQ;
    }

    /* renamed from: org.apache.commons.math3.optim.linear.Relationship$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$optim$linear$Relationship;

        static {
            int[] iArr = new int[Relationship.values().length];
            $SwitchMap$org$apache$commons$math3$optim$linear$Relationship = iArr;
            try {
                iArr[Relationship.LEQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$optim$linear$Relationship[Relationship.GEQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
