package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public class SecT233R1Curve extends ECCurve.AbstractF2m {
    private static final int SecT233R1_DEFAULT_COORDS = 6;
    protected SecT233R1Point infinity;

    public SecT233R1Curve() {
        super(233, 74, 0, 0);
        this.infinity = new SecT233R1Point(this, null, null);
        this.a = fromBigInteger(BigInteger.valueOf(1L));
        this.b = fromBigInteger(new BigInteger(1, Hex.decode("0066647EDE6C332C7F8C0923BB58213B333B20E9CE4281FE115F7D8F90AD")));
        this.order = new BigInteger(1, Hex.decode("01000000000000000000000000000013E974E72F8A6922031D2603CFE0D7"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecT233R1Curve();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final long[] jArr = new long[i2 * 8];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat256.copy64(((SecT233FieldElement) eCPoint.getRawXCoord()).x, 0, jArr, i3);
            Nat256.copy64(((SecT233FieldElement) eCPoint.getRawYCoord()).x, 0, jArr, i3 + 4);
            i3 += 8;
        }
        return new ECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT233R1Curve.1
            @Override // org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return i2;
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int i5) {
                long[] jArrCreate64 = Nat256.create64();
                long[] jArrCreate642 = Nat256.create64();
                int i6 = 0;
                for (int i7 = 0; i7 < i2; i7++) {
                    long j = ((i7 ^ i5) - 1) >> 31;
                    for (int i8 = 0; i8 < 4; i8++) {
                        long j2 = jArrCreate64[i8];
                        long[] jArr2 = jArr;
                        jArrCreate64[i8] = j2 ^ (jArr2[i6 + i8] & j);
                        jArrCreate642[i8] = jArrCreate642[i8] ^ (jArr2[(i6 + 4) + i8] & j);
                    }
                    i6 += 8;
                }
                return SecT233R1Curve.this.createRawPoint(new SecT233FieldElement(jArrCreate64), new SecT233FieldElement(jArrCreate642), false);
            }
        };
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecT233R1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        return new SecT233R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT233FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return 233;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 233;
    }

    @Override // org.bouncycastle.math.ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }
}
