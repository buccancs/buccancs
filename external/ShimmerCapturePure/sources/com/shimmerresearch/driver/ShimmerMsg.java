package com.shimmerresearch.driver;

/* loaded from: classes2.dex */
public class ShimmerMsg {
    public Object mB;
    public int mIdentifier;

    public ShimmerMsg(int i) {
        this.mIdentifier = i;
    }

    public ShimmerMsg(int i, Object obj) {
        this(i);
        this.mB = obj;
    }
}
