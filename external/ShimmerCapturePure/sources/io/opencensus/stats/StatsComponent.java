package io.opencensus.stats;

/* loaded from: classes4.dex */
public abstract class StatsComponent {
    public abstract StatsCollectionState getState();

    @Deprecated
    public abstract void setState(StatsCollectionState statsCollectionState);

    public abstract StatsRecorder getStatsRecorder();

    public abstract ViewManager getViewManager();
}
