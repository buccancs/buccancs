package io.grpc.grpclb;

import com.google.common.base.Preconditions;
import io.grpc.EquivalentAddressGroup;

/* loaded from: classes2.dex */
final class LbAddressGroup {
    private final EquivalentAddressGroup addresses;
    private final String authority;

    LbAddressGroup(EquivalentAddressGroup equivalentAddressGroup, String str) {
        this.addresses = (EquivalentAddressGroup) Preconditions.checkNotNull(equivalentAddressGroup, "addresses");
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
    }

    EquivalentAddressGroup getAddresses() {
        return this.addresses;
    }

    String getAuthority() {
        return this.authority;
    }
}
