package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.ui.DataRenderer;
import com.androidplot.xy.XYSeriesFormatter;

import java.util.Hashtable;

/* loaded from: classes.dex */
public abstract class XYSeriesRenderer<XYFormatterType extends XYSeriesFormatter> extends DataRenderer<XYPlot, XYFormatterType> {
    public XYSeriesRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    public Hashtable<XYRegionFormatter, String> getUniqueRegionFormatters() {
        Hashtable<XYRegionFormatter, String> hashtable = new Hashtable<>();
        for (XYFormatterType xyformattertype : getSeriesAndFormatterList().getFormatterList()) {
            for (RectRegion rectRegion : xyformattertype.getRegions().elements()) {
                hashtable.put(xyformattertype.getRegionFormatter(rectRegion), rectRegion.getLabel());
            }
        }
        return hashtable;
    }

    @Override // com.androidplot.ui.DataRenderer
    public void render(Canvas canvas, RectF rectF) throws PlotRenderException {
        super.render(canvas, rectF);
    }
}
