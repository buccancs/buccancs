package com.androidplot.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;
import com.androidplot.util.FontUtils;

/* loaded from: classes.dex */
public abstract class TextLabelWidget extends Widget {
    private Paint a;
    private TextOrientationType b;

    public TextLabelWidget(SizeMetrics sizeMetrics) {
        this(sizeMetrics, TextOrientationType.HORIZONTAL);
    }

    public TextLabelWidget(SizeMetrics sizeMetrics, TextOrientationType textOrientationType) {
        super(new SizeMetrics(0.0f, SizeLayoutType.ABSOLUTE, 0.0f, SizeLayoutType.ABSOLUTE));
        Paint paint = new Paint();
        this.a = paint;
        paint.setColor(-1);
        this.a.setAntiAlias(true);
        this.a.setTextAlign(Paint.Align.CENTER);
        setSize(sizeMetrics);
        this.b = textOrientationType;
    }

    protected abstract String a();

    public Paint getLabelPaint() {
        return this.a;
    }

    public void setLabelPaint(Paint paint) {
        this.a = paint;
    }

    public TextOrientationType getOrientation() {
        return this.b;
    }

    public void setOrientation(TextOrientationType textOrientationType) {
        this.b = textOrientationType;
    }

    public void pack() {
        if (FontUtils.getStringDimensions(a(), getLabelPaint()) == null) {
            return;
        }
        int i = a.a[this.b.ordinal()];
        if (i == 1) {
            setSize(new SizeMetrics(r0.height(), SizeLayoutType.ABSOLUTE, r0.width() + 2, SizeLayoutType.ABSOLUTE));
        } else if (i == 2 || i == 3) {
            setSize(new SizeMetrics(r0.width(), SizeLayoutType.ABSOLUTE, r0.height() + 2, SizeLayoutType.ABSOLUTE));
        }
    }

    @Override // com.androidplot.ui.widget.Widget
    public void doOnDraw(Canvas canvas, RectF rectF) {
        String strA = a();
        FontUtils.getStringDimensions(strA, this.a);
        float f = this.a.getFontMetrics().descent;
        PointF anchorCoordinates = LayoutManager.getAnchorCoordinates(rectF, AnchorPosition.CENTER);
        try {
            canvas.save(31);
            canvas.translate(anchorCoordinates.x, anchorCoordinates.y);
            int i = a.a[this.b.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    canvas.rotate(-90.0f);
                } else if (i == 3) {
                    canvas.rotate(90.0f);
                } else {
                    throw new UnsupportedOperationException("Orientation " + this.b + " not yet implemented for TextLabelWidget.");
                }
            }
            canvas.drawText(strA, 0.0f, f, this.a);
        } finally {
            canvas.restore();
        }
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[TextOrientationType.values().length];
            a = iArr;
            try {
                iArr[TextOrientationType.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[TextOrientationType.VERTICAL_ASCENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[TextOrientationType.VERTICAL_DESCENDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
