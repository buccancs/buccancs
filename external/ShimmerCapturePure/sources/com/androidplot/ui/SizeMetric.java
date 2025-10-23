package com.androidplot.ui;

/* loaded from: classes.dex */
public class SizeMetric extends com.androidplot.ui.a<SizeLayoutType> {
    public SizeMetric(float f, SizeLayoutType sizeLayoutType) {
        super(f, sizeLayoutType);
    }

    @Override // com.androidplot.ui.a
    protected final /* bridge */ /* synthetic */ void a(float f, Enum r3) {
        if (a.a[((SizeLayoutType) r3).ordinal()] != 1) {
            return;
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("SizeMetric Relative and Hybrid layout values must be within the range of 0 to 1.");
        }
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
    public float getPixelValue(float f) {
        int i = a.a[getLayoutType().ordinal()];
        if (i == 1) {
            return getValue() * f;
        }
        if (i == 2) {
            return getValue();
        }
        if (i == 3) {
            return f - getValue();
        }
        throw new IllegalArgumentException("Unsupported LayoutType: " + getLayoutType());
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SizeLayoutType.values().length];
            a = iArr;
            try {
                iArr[SizeLayoutType.RELATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SizeLayoutType.ABSOLUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SizeLayoutType.FILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
