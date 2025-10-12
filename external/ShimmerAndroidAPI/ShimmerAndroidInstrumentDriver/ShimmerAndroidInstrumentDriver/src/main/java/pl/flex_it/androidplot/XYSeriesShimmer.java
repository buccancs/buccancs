//Rev 0.2

package pl.flex_it.androidplot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.androidplot.series.XYSeries;

public class XYSeriesShimmer implements XYSeries {
    private List<Number> mDataY;
    private int mSeriesIndex;
    private String mTitle;
    private int mXAxisLimit = 500;
    private boolean mClearGraphatLimit = false;


        public XYSeriesShimmer(List<Number> datasource, int seriesIndex, String title, int xaxislimit) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = title;
        this.mXAxisLimit = xaxislimit;
    }

        public XYSeriesShimmer(List<Number> datasource, int seriesIndex, String title) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = title;
    }

        public XYSeriesShimmer(List<Number> datasource, int seriesIndex) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = "";
    }

        public XYSeriesShimmer(List<Number> datasource) {
        this.mDataY = datasource;
        this.mTitle = "";
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Override
    public int size() {
        return mDataY.size();
    }

    @Override
    public Number getY(int index) {
        synchronized (mDataY) {
            if (index < mDataY.size()) {
                return mDataY.get(index);
            } else {
                return null;
            }
        }
    }

    @Override
    public Number getX(int index) {
        return index;
    }

    public void setClearGraphatLimit(boolean clear) {
        mClearGraphatLimit = clear;
    }

    public void setXAxisLimit(int limit) {
        mXAxisLimit = limit;
    }

    public void clearData() {
        synchronized (mDataY) {
            mDataY.clear();
        }
    }

    public void updateData(List<Number> datasource) {
        synchronized (mDataY) {
            this.mDataY = datasource;
            if (mClearGraphatLimit) {
                if (mDataY.size() >= mXAxisLimit) {
                    mDataY.clear();
                }

            } else {
                while (mDataY.size() >= mXAxisLimit) {
                    mDataY.remove(0);
                }
            }
        }
    }

    public void addData(Number data) {
        synchronized (mDataY) {
            this.mDataY.add(data);
            if (mClearGraphatLimit) {
                if (mDataY.size() >= mXAxisLimit) {
                    mDataY.clear();
                }
            } else {
                while (mDataY.size() >= mXAxisLimit) {
                    mDataY.remove(0);
                }
            }
        }

    }

}