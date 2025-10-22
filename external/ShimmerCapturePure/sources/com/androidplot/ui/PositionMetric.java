package com.androidplot.ui;

import java.lang.Enum;

/* loaded from: classes.dex */
public abstract class PositionMetric<LayoutType extends Enum> extends com.androidplot.ui.a<LayoutType> {

    public PositionMetric(float f, LayoutType layouttype) {
        super(f, layouttype);
    }

    protected static void a(float f, LayoutMode layoutMode) throws IllegalArgumentException {
        int i = a.a[layoutMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new IllegalArgumentException("Unknown LayoutMode: " + layoutMode);
            }
            if (f < -1.0f || f > 1.0f) {
                throw new IllegalArgumentException("Relative layout values must be within the range of -1 to 1.");
            }
        }
    }

    @Override // com.androidplot.ui.a
    public /* bridge */ /* synthetic */ Enum getLayoutType() {
        return super.getLayoutType();
    }

    @Override // com.androidplot.ui.a
    public /* bridge */ /* synthetic */ void setLayoutType(Enum r1) {
        super.setLayoutType(r1);
    }

    @Override // com.androidplot.ui.a
    public /* bridge */ /* synthetic */ float getValue() {
        return super.getValue();
    }

    @Override // com.androidplot.ui.a
    public /* bridge */ /* synthetic */ void setValue(float f) {
        super.setValue(f);
    }

    @Override // com.androidplot.ui.a
    public /* bridge */ /* synthetic */ void set(float f, Enum r2) {
        super.set(f, r2);
    }

    protected final float a(float f, Origin origin) {
        int i = a.b[origin.ordinal()];
        if (i == 1) {
            return getValue();
        }
        if (i == 2) {
            return (f / 2.0f) + getValue();
        }
        if (i == 3) {
            return f - getValue();
        }
        throw new IllegalArgumentException("Unsupported Origin: " + origin);
    }

    protected final float b(float f, Origin origin) {
        int i = a.b[origin.ordinal()];
        if (i == 1) {
            return f * getValue();
        }
        if (i == 2) {
            float f2 = f / 2.0f;
            return f2 + (getValue() * f2);
        }
        if (i == 3) {
            return f + (getValue() * f);
        }
        throw new IllegalArgumentException("Unsupported Origin: " + origin);
    }

    protected enum LayoutMode {
        ABSOLUTE,
        RELATIVE
    }

    protected enum Origin {
        FROM_BEGINING,
        FROM_CENTER,
        FROM_END
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Origin.values().length];
            b = iArr;
            try {
                iArr[Origin.FROM_BEGINING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Origin.FROM_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Origin.FROM_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[LayoutMode.values().length];
            a = iArr2;
            try {
                iArr2[LayoutMode.ABSOLUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[LayoutMode.RELATIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
