package com.androidplot.xy;

import com.androidplot.ui.PositionMetric;

/* loaded from: classes.dex */
public class YPositionMetric extends PositionMetric<YLayoutStyle> {
    public YPositionMetric(float f, YLayoutStyle yLayoutStyle) {
        super(f, yLayoutStyle);
    }

    @Override // com.androidplot.ui.a
    protected final /* bridge */ /* synthetic */ void a(float f, Enum r3) {
        switch (a.a[((YLayoutStyle) r3).ordinal()]) {
            case 1:
            case 2:
            case 3:
                a(f, PositionMetric.LayoutMode.ABSOLUTE);
                break;
            case 4:
            case 5:
            case 6:
                a(f, PositionMetric.LayoutMode.RELATIVE);
                break;
        }
    }

    @Override // com.androidplot.ui.a
    public float getPixelValue(float f) {
        switch (a.a[((YLayoutStyle) getLayoutType()).ordinal()]) {
            case 1:
                return a(f, PositionMetric.Origin.FROM_BEGINING);
            case 2:
                return a(f, PositionMetric.Origin.FROM_END);
            case 3:
                return a(f, PositionMetric.Origin.FROM_CENTER);
            case 4:
                return b(f, PositionMetric.Origin.FROM_BEGINING);
            case 5:
                return b(f, PositionMetric.Origin.FROM_END);
            case 6:
                return b(f, PositionMetric.Origin.FROM_CENTER);
            default:
                throw new IllegalArgumentException("Unsupported LayoutType: " + getLayoutType());
        }
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[YLayoutStyle.values().length];
            a = iArr;
            try {
                iArr[YLayoutStyle.ABSOLUTE_FROM_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[YLayoutStyle.ABSOLUTE_FROM_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[YLayoutStyle.ABSOLUTE_FROM_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[YLayoutStyle.RELATIVE_TO_TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[YLayoutStyle.RELATIVE_TO_BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[YLayoutStyle.RELATIVE_TO_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
