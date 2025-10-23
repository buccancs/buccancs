package com.androidplot.xy;

import com.androidplot.ui.Formatter;
import com.androidplot.util.ZHash;
import com.androidplot.util.ZIndexable;
import com.androidplot.xy.XYRegionFormatter;

/* loaded from: classes.dex */
public abstract class XYSeriesFormatter<XYRegionFormatterType extends XYRegionFormatter> extends Formatter {
    private ZHash<RectRegion, XYRegionFormatterType> a = new ZHash<>();

    public ZIndexable<RectRegion> getRegions() {
        return this.a;
    }

    public void addRegion(RectRegion rectRegion, XYRegionFormatterType xyregionformattertype) {
        this.a.addToBottom(rectRegion, xyregionformattertype);
    }

    public void removeRegion(RectRegion rectRegion) {
        this.a.remove(rectRegion);
    }

    public XYRegionFormatterType getRegionFormatter(RectRegion rectRegion) {
        return this.a.get(rectRegion);
    }
}
