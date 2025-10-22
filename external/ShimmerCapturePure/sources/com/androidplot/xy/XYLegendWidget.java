package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.androidplot.series.XYSeries;
import com.androidplot.ui.SeriesAndFormatterList;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TableModel;
import com.androidplot.ui.widget.Widget;
import com.androidplot.util.FontUtils;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class XYLegendWidget extends Widget {
    static {
        new a();
    }

    private XYPlot a;
    private Paint b;
    private Paint c;
    private TableModel d;
    private boolean e;
    private boolean f;
    private SizeMetrics g;

    public XYLegendWidget(XYPlot xYPlot, SizeMetrics sizeMetrics, TableModel tableModel, SizeMetrics sizeMetrics2) {
        super(sizeMetrics);
        this.e = true;
        this.f = true;
        Paint paint = new Paint();
        this.b = paint;
        paint.setColor(-3355444);
        this.b.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.c = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.a = xYPlot;
        setTableModel(tableModel);
        this.g = sizeMetrics2;
    }

    public SizeMetrics getIconSizeMetrics() {
        return this.g;
    }

    public void setIconSizeMetrics(SizeMetrics sizeMetrics) {
        this.g = sizeMetrics;
    }

    public TableModel getTableModel() {
        return this.d;
    }

    public synchronized void setTableModel(TableModel tableModel) {
        this.d = tableModel;
    }

    public Paint getTextPaint() {
        return this.b;
    }

    public void setTextPaint(Paint paint) {
        this.b = paint;
    }

    public boolean isDrawIconBackgroundEnabled() {
        return this.e;
    }

    public void setDrawIconBackgroundEnabled(boolean z) {
        this.e = z;
    }

    public boolean isDrawIconBorderEnabled() {
        return this.f;
    }

    public void setDrawIconBorderEnabled(boolean z) {
        this.f = z;
    }

    private RectF a(RectF rectF) {
        float fHeight = rectF.top + (rectF.height() / 2.0f);
        RectF rectF2 = this.g.getRectF(rectF);
        rectF2.offsetTo(rectF.left + 1.0f, fHeight - (rectF2.height() / 2.0f));
        return rectF2;
    }

    private void a(Canvas canvas, RectF rectF) {
        Paint gridBackgroundPaint = this.a.getGraphWidget().getGridBackgroundPaint();
        if (!this.e || gridBackgroundPaint == null) {
            return;
        }
        canvas.drawRect(rectF, gridBackgroundPaint);
    }

    private void a(Canvas canvas, RectF rectF, RectF rectF2, String str) {
        Paint gridBackgroundPaint = this.a.getGraphWidget().getGridBackgroundPaint();
        if (this.f && gridBackgroundPaint != null) {
            this.c.setColor(gridBackgroundPaint.getColor());
            canvas.drawRect(rectF2, this.c);
        }
        canvas.drawText(str, rectF2.right + 2.0f, rectF.top + (rectF.height() / 2.0f) + (FontUtils.getFontHeight(this.b) / 2.0f), this.b);
    }

    @Override // com.androidplot.ui.widget.Widget
    protected synchronized void doOnDraw(Canvas canvas, RectF rectF) {
        if (this.a.isEmpty()) {
            return;
        }
        Hashtable hashtable = new Hashtable();
        TreeSet treeSet = new TreeSet(new a());
        int size = 0;
        for (XYSeriesRenderer xYSeriesRenderer : this.a.getRendererList()) {
            size += this.a.getSeriesAndFormatterListForRenderer(xYSeriesRenderer.getClass()).size();
            Iterator<XYRegionFormatter> it2 = xYSeriesRenderer.getUniqueRegionFormatters().keySet().iterator();
            while (it2.hasNext()) {
                hashtable.put(it2.next(), xYSeriesRenderer);
            }
            treeSet.addAll(xYSeriesRenderer.getUniqueRegionFormatters().entrySet());
        }
        Iterator<RectF> iterator = this.d.getIterator(rectF, size + treeSet.size());
        for (XYSeriesRenderer xYSeriesRenderer2 : this.a.getRendererList()) {
            SeriesAndFormatterList<XYSeries, XYSeriesFormatter> seriesAndFormatterListForRenderer = this.a.getSeriesAndFormatterListForRenderer(xYSeriesRenderer2.getClass());
            for (int i = 0; i < seriesAndFormatterListForRenderer.size() && iterator.hasNext(); i++) {
                RectF next = iterator.next();
                XYSeriesFormatter formatter = seriesAndFormatterListForRenderer.getFormatter(i);
                String title = ((XYSeries) seriesAndFormatterListForRenderer.getSeries(i)).getTitle();
                RectF rectFA = a(next);
                a(canvas, rectFA);
                xYSeriesRenderer2.drawSeriesLegendIcon(canvas, rectFA, formatter);
                a(canvas, next, rectFA, title);
            }
        }
        Iterator it3 = treeSet.iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            if (!iterator.hasNext()) {
                break;
            }
            RectF next2 = iterator.next();
            XYRegionFormatter xYRegionFormatter = (XYRegionFormatter) entry.getKey();
            XYSeriesRenderer xYSeriesRenderer3 = (XYSeriesRenderer) hashtable.get(xYRegionFormatter);
            String str = (String) entry.getValue();
            RectF rectFA2 = a(next2);
            a(canvas, rectFA2);
            xYSeriesRenderer3.drawRegionLegendIcon(canvas, rectFA2, xYRegionFormatter);
            a(canvas, next2, rectFA2, str);
        }
    }

    private static class a implements Comparator<Map.Entry<XYRegionFormatter, String>> {
        /* synthetic */ a() {
            this((byte) 0);
        }

        private a(byte b) {
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Map.Entry<XYRegionFormatter, String> entry, Map.Entry<XYRegionFormatter, String> entry2) {
            return entry.getValue().compareTo(entry2.getValue());
        }
    }
}
