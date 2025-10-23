package org.bouncycastle.math.ec;

/* loaded from: classes5.dex */
public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int i);
}
