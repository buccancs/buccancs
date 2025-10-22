package com.shimmerresearch.comms.radioProtocol;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class MemReadDetails implements Serializable {
    private static final long serialVersionUID = 6209428298720646676L;
    public int mCommand;
    public int mCurrentMemAddress;
    public int mCurrentMemLengthToRead = 0;
    public int mEndMemAddress = 0;
    protected int mMemAddressLimit;
    private int mTotalMemLengthToRead = 0;

    public MemReadDetails(int i, int i2, int i3, int i4) {
        this.mMemAddressLimit = 0;
        this.mCommand = i;
        this.mCurrentMemAddress = i2;
        setTotalMemLengthToRead(i3);
        this.mMemAddressLimit = i4;
    }

    private void updateEndAddress() {
        this.mEndMemAddress = this.mCurrentMemAddress + this.mTotalMemLengthToRead;
    }

    public int getTotalMemLengthToRead() {
        return this.mTotalMemLengthToRead;
    }

    public void setTotalMemLengthToRead(int i) {
        this.mTotalMemLengthToRead = i;
        updateEndAddress();
    }
}
