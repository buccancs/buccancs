package kotlin.internal;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m40017differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM = UByte$$ExternalSyntheticBackport0.m(i, i3);
        int iM2 = UByte$$ExternalSyntheticBackport0.m(i2, i3);
        int iCompare = Integer.compare(iM ^ Integer.MIN_VALUE, iM2 ^ Integer.MIN_VALUE);
        int iM38905constructorimpl = UInt.m38905constructorimpl(iM - iM2);
        return iCompare >= 0 ? iM38905constructorimpl : UInt.m38905constructorimpl(iM38905constructorimpl + i3);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m40018differenceModulosambcqE(long j, long j2, long j3) {
        long jM38877m = UByte$$ExternalSyntheticBackport0.m38877m(j, j3);
        long jM38877m2 = UByte$$ExternalSyntheticBackport0.m38877m(j2, j3);
        int iCompare = Long.compare(jM38877m ^ Long.MIN_VALUE, jM38877m2 ^ Long.MIN_VALUE);
        long jM38984constructorimpl = ULong.m38984constructorimpl(jM38877m - jM38877m2);
        return iCompare >= 0 ? jM38984constructorimpl : ULong.m38984constructorimpl(jM38984constructorimpl + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m40020getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i2 : UInt.m38905constructorimpl(i2 - m40017differenceModuloWZ9TVnA(i2, i, UInt.m38905constructorimpl(i3)));
        }
        if (i3 < 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i2 : UInt.m38905constructorimpl(i2 + m40017differenceModuloWZ9TVnA(i, i2, UInt.m38905constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m40019getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j2 : ULong.m38984constructorimpl(j2 - m40018differenceModulosambcqE(j2, j, ULong.m38984constructorimpl(j3)));
        }
        if (j3 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j2 : ULong.m38984constructorimpl(j2 + m40018differenceModulosambcqE(j, j2, ULong.m38984constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
