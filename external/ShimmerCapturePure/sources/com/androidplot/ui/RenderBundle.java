package com.androidplot.ui;

import com.androidplot.series.Series;
import com.androidplot.ui.RenderBundle;
import com.androidplot.xy.XYSeriesFormatter;

/* loaded from: classes.dex */
public abstract class RenderBundle<RenderBundleType extends RenderBundle, SeriesType extends Series, SeriesFormatterType extends XYSeriesFormatter> {
    private Series a;
    private SeriesFormatterType b;

    public RenderBundle(SeriesType seriestype, SeriesFormatterType seriesformattertype) {
        this.b = seriesformattertype;
        this.a = seriestype;
    }

    public SeriesFormatterType getFormatter() {
        return this.b;
    }

    public void setFormatter(SeriesFormatterType seriesformattertype) {
        this.b = seriesformattertype;
    }

    public Series getSeries() {
        return this.a;
    }

    public void setSeries(Series series) {
        this.a = series;
    }
}
