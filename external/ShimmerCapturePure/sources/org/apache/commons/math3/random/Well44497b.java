package org.apache.commons.math3.random;

/* loaded from: classes5.dex */
public class Well44497b extends AbstractWell {
    private static final int K = 44497;
    private static final int M1 = 23;
    private static final int M2 = 481;
    private static final int M3 = 229;
    private static final long serialVersionUID = 4032007538246675492L;

    public Well44497b() {
        super(K, 23, M2, M3);
    }

    public Well44497b(int i) {
        super(K, 23, M2, M3, i);
    }

    public Well44497b(int[] iArr) {
        super(K, 23, M2, M3, iArr);
    }

    public Well44497b(long j) {
        super(K, 23, M2, M3, j);
    }

    @Override // org.apache.commons.math3.random.AbstractWell, org.apache.commons.math3.random.BitsStreamGenerator
    protected int next(int i) {
        int i2 = this.iRm1[this.index];
        int i3 = this.iRm2[this.index];
        int i4 = this.v[this.index];
        int i5 = this.v[this.i1[this.index]];
        int i6 = this.v[this.i2[this.index]];
        int i7 = this.v[this.i3[this.index]];
        int i8 = (this.v[i2] & (-32768)) ^ (this.v[i3] & 32767);
        int i9 = (i4 ^ (i4 << 24)) ^ (i5 ^ (i5 >>> 30));
        int i10 = ((i6 << 10) ^ i6) ^ (i7 << 26);
        int i11 = i9 ^ i10;
        int i12 = ((i10 << 9) ^ (i10 >>> 23)) & (-67108865);
        if ((i10 & 131072) != 0) {
            i12 ^= -1221985044;
        }
        int i13 = (((i9 ^ (i9 >>> 20)) ^ i8) ^ i12) ^ i11;
        this.v[this.index] = i11;
        this.v[i2] = i13;
        int[] iArr = this.v;
        iArr[i3] = iArr[i3] & (-32768);
        this.index = i2;
        int i14 = ((i13 << 7) & (-1814227968)) ^ i13;
        return (i14 ^ ((i14 << 15) & (-99516416))) >>> (32 - i);
    }
}
