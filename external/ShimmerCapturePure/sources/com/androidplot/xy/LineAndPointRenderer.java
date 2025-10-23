package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.series.XYSeries;
import com.androidplot.util.ValPixConverter;
import com.androidplot.xy.LineAndPointFormatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class LineAndPointRenderer<FormatterType extends LineAndPointFormatter> extends XYSeriesRenderer<FormatterType> {
    public LineAndPointRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.androidplot.ui.DataRenderer
    public void onRender(Canvas canvas, RectF rectF) throws PlotRenderException {
        PointF pointFValToPix;
        List<XYSeries> seriesListForRenderer = getPlot().getSeriesListForRenderer(getClass());
        if (seriesListForRenderer != null) {
            for (XYSeries xYSeries : seriesListForRenderer) {
                LineAndPointFormatter lineAndPointFormatter = (LineAndPointFormatter) getFormatter(xYSeries);
                ArrayList arrayList = new ArrayList(xYSeries.size());
                PointF pointF = null;
                PointF pointF2 = null;
                Path path = null;
                for (int i = 0; i < xYSeries.size(); i++) {
                    Number y = xYSeries.getY(i);
                    Number x = xYSeries.getX(i);
                    if (y == null || x == null) {
                        pointFValToPix = null;
                    } else {
                        pointFValToPix = ValPixConverter.valToPix(x, y, rectF, getPlot().getCalculatedMinX(), getPlot().getCalculatedMaxX(), getPlot().getCalculatedMinY(), getPlot().getCalculatedMaxY());
                        arrayList.add(pointFValToPix);
                    }
                    if (pointFValToPix != null) {
                        if (pointF == null) {
                            Path path2 = new Path();
                            path2.moveTo(pointFValToPix.x, pointFValToPix.y);
                            pointF = pointFValToPix;
                            path = path2;
                        }
                        if (pointF2 != null) {
                            a(path, pointFValToPix, pointF2);
                        }
                        pointF2 = pointFValToPix;
                    } else {
                        if (pointF2 != null) {
                            a(canvas, rectF, path, pointF, pointF2, lineAndPointFormatter);
                        }
                        pointF = null;
                        pointF2 = null;
                    }
                }
                if (pointF != null) {
                    a(canvas, rectF, path, pointF, pointF2, lineAndPointFormatter);
                }
                if (lineAndPointFormatter.getVertexPaint() != null) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        PointF pointF3 = (PointF) it2.next();
                        canvas.drawPoint(pointF3.x, pointF3.y, lineAndPointFormatter.getVertexPaint());
                    }
                }
            }
        }
    }

    @Override // com.androidplot.ui.DataRenderer
    public void doDrawLegendIcon(Canvas canvas, RectF rectF, LineAndPointFormatter lineAndPointFormatter) {
        float fCenterY = rectF.centerY();
        float fCenterX = rectF.centerX();
        if (lineAndPointFormatter.getFillPaint() != null) {
            canvas.drawRect(rectF, lineAndPointFormatter.getFillPaint());
        }
        if (lineAndPointFormatter.getLinePaint() != null) {
            canvas.drawLine(rectF.left, rectF.bottom, rectF.right, rectF.top, lineAndPointFormatter.getLinePaint());
        }
        if (lineAndPointFormatter.getVertexPaint() != null) {
            canvas.drawPoint(fCenterX, fCenterY, lineAndPointFormatter.getVertexPaint());
        }
    }

    protected void a(Path path, PointF pointF, PointF pointF2) {
        path.lineTo(pointF.x, pointF.y);
    }

    private void a(Canvas canvas, RectF rectF, Path path, PointF pointF, PointF pointF2, LineAndPointFormatter lineAndPointFormatter) {
        Path path2 = new Path(path);
        int i = a.a[lineAndPointFormatter.getFillDirection().ordinal()];
        if (i == 1) {
            path.lineTo(pointF2.x, rectF.bottom);
            path.lineTo(pointF.x, rectF.bottom);
            path.close();
        } else if (i == 2) {
            path.lineTo(pointF2.x, rectF.top);
            path.lineTo(pointF.x, rectF.top);
            path.close();
        } else if (i == 3) {
            float fValToPix = ValPixConverter.valToPix(getPlot().getRangeOrigin().doubleValue(), getPlot().getCalculatedMinY().doubleValue(), getPlot().getCalculatedMaxY().doubleValue(), rectF.height(), true) + rectF.top;
            path.lineTo(pointF2.x, fValToPix);
            path.lineTo(pointF.x, fValToPix);
            path.close();
        } else {
            throw new UnsupportedOperationException("Fill direction not yet implemented: " + lineAndPointFormatter.getFillDirection());
        }
        if (lineAndPointFormatter.getFillPaint() != null) {
            canvas.drawPath(path, lineAndPointFormatter.getFillPaint());
        }
        double dDoubleValue = getPlot().getCalculatedMinX().doubleValue();
        double dDoubleValue2 = getPlot().getCalculatedMaxX().doubleValue();
        double dDoubleValue3 = getPlot().getCalculatedMinY().doubleValue();
        double dDoubleValue4 = getPlot().getCalculatedMaxY().doubleValue();
        for (RectRegion rectRegion : RectRegion.regionsWithin(lineAndPointFormatter.getRegions().elements(), Double.valueOf(dDoubleValue), Double.valueOf(dDoubleValue2), Double.valueOf(dDoubleValue3), Double.valueOf(dDoubleValue4))) {
            XYRegionFormatter regionFormatter = lineAndPointFormatter.getRegionFormatter(rectRegion);
            RectF rectF2 = rectRegion.getRectF(rectF, Double.valueOf(dDoubleValue), Double.valueOf(dDoubleValue2), Double.valueOf(dDoubleValue3), Double.valueOf(dDoubleValue4));
            if (rectF2 != null) {
                try {
                    canvas.save(31);
                    canvas.clipPath(path);
                    canvas.drawRect(rectF2, regionFormatter.getPaint());
                } finally {
                    canvas.restore();
                }
            }
        }
        canvas.drawPath(path2, lineAndPointFormatter.getLinePaint());
        if (path != null) {
            path.rewind();
        }
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[FillDirection.values().length];
            a = iArr;
            try {
                iArr[FillDirection.BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[FillDirection.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[FillDirection.RANGE_ORIGIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
