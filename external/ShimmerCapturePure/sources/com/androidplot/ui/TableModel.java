package com.androidplot.ui;

import android.graphics.RectF;

import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class TableModel {
    private TableOrder a;

    protected TableModel(TableOrder tableOrder) {
        setOrder(tableOrder);
    }

    public abstract Iterator<RectF> getIterator(RectF rectF, int i);

    public TableOrder getOrder() {
        return this.a;
    }

    public void setOrder(TableOrder tableOrder) {
        this.a = tableOrder;
    }

    public enum Axis {
        ROW,
        COLUMN
    }

    public enum CellSizingMethod {
        FIXED,
        FILL
    }
}
