package com.androidplot.ui;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import com.androidplot.Plot;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.series.Series;
import com.androidplot.series.XYSeries;
import com.androidplot.ui.Formatter;
import com.androidplot.xy.XYRegionFormatter;

/* loaded from: classes.dex */
public abstract class DataRenderer<PlotType extends Plot, SeriesFormatterType extends Formatter> {
    private PlotType a;

    public DataRenderer(PlotType plottype) {
        this.a = plottype;
    }

    protected abstract void doDrawLegendIcon(Canvas canvas, RectF rectF, SeriesFormatterType seriesformattertype);

    public PlotType getPlot() {
        return this.a;
    }

    public void setPlot(PlotType plottype) {
        this.a = plottype;
    }

    public abstract void onRender(Canvas canvas, RectF rectF) throws PlotRenderException;

    public SeriesAndFormatterList<XYSeries, SeriesFormatterType> getSeriesAndFormatterList() {
        return this.a.getSeriesAndFormatterListForRenderer(getClass());
    }

    public SeriesFormatterType getFormatter(Series series) {
        return (SeriesFormatterType) this.a.getFormatter(series, getClass());
    }

    public void render(Canvas canvas, RectF rectF) throws PlotRenderException {
        onRender(canvas, rectF);
    }

    public void drawSeriesLegendIcon(Canvas canvas, RectF rectF, SeriesFormatterType seriesformattertype) {
        try {
            canvas.save(31);
            canvas.clipRect(rectF, Region.Op.INTERSECT);
            doDrawLegendIcon(canvas, rectF, seriesformattertype);
        } finally {
            canvas.restore();
        }
    }

    public void drawRegionLegendIcon(Canvas canvas, RectF rectF, XYRegionFormatter xYRegionFormatter) {
        canvas.drawRect(rectF, xYRegionFormatter.getPaint());
    }
}
