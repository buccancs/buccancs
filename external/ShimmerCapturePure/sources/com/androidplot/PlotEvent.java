package com.androidplot;

/* loaded from: classes.dex */
public class PlotEvent {
    private final Plot a;

    public PlotEvent(Plot plot, Type type) {
        this.a = plot;
    }

    public Plot getSource() {
        return this.a;
    }

    public enum Type {
        PLOT_REDRAWN
    }
}
