package pl.flex_it.androidplot;

import com.androidplot.series.XYSeries;

import java.util.List;

/* loaded from: classes2.dex */
public class XYSeriesShimmer implements XYSeries {
    private boolean mClearGraphatLimit;
    private List<Number> mDataY;
    private int mSeriesIndex;
    private String mTitle;
    private int mXAxisLimit;

    public XYSeriesShimmer(List<Number> list, int i, String str, int i2) {
        this.mClearGraphatLimit = false;
        this.mDataY = list;
        this.mSeriesIndex = i;
        this.mTitle = str;
        this.mXAxisLimit = i2;
    }

    public XYSeriesShimmer(List<Number> list, int i, String str) {
        this.mXAxisLimit = 500;
        this.mClearGraphatLimit = false;
        this.mDataY = list;
        this.mSeriesIndex = i;
        this.mTitle = str;
    }

    public XYSeriesShimmer(List<Number> list, int i) {
        this.mXAxisLimit = 500;
        this.mClearGraphatLimit = false;
        this.mDataY = list;
        this.mSeriesIndex = i;
        this.mTitle = "";
    }

    public XYSeriesShimmer(List<Number> list) {
        this.mXAxisLimit = 500;
        this.mClearGraphatLimit = false;
        this.mDataY = list;
        this.mTitle = "";
    }

    @Override // com.androidplot.series.Series
    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setClearGraphatLimit(boolean z) {
        this.mClearGraphatLimit = z;
    }

    public void setXAxisLimit(int i) {
        this.mXAxisLimit = i;
    }

    @Override // com.androidplot.series.Series
    public int size() {
        return this.mDataY.size();
    }

    @Override // com.androidplot.series.XYSeries
    public Number getY(int i) {
        synchronized (this.mDataY) {
            if (i >= this.mDataY.size()) {
                return null;
            }
            return this.mDataY.get(i);
        }
    }

    @Override // com.androidplot.series.XYSeries
    public Number getX(int i) {
        return Integer.valueOf(i);
    }

    public void clearData() {
        synchronized (this.mDataY) {
            this.mDataY.clear();
        }
    }

    public void updateData(List<Number> list) {
        synchronized (this.mDataY) {
            this.mDataY = list;
            if (this.mClearGraphatLimit) {
                if (list.size() >= this.mXAxisLimit) {
                    this.mDataY.clear();
                }
            } else {
                while (this.mDataY.size() >= this.mXAxisLimit) {
                    this.mDataY.remove(0);
                }
            }
        }
    }

    public void addData(Number number) {
        synchronized (this.mDataY) {
            this.mDataY.add(number);
            if (this.mClearGraphatLimit) {
                if (this.mDataY.size() >= this.mXAxisLimit) {
                    this.mDataY.clear();
                }
            } else {
                while (this.mDataY.size() >= this.mXAxisLimit) {
                    this.mDataY.remove(0);
                }
            }
        }
    }
}
