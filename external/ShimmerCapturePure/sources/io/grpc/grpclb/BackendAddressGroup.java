package io.grpc.grpclb;

import com.google.common.base.Preconditions;
import io.grpc.EquivalentAddressGroup;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class BackendAddressGroup {
    private final EquivalentAddressGroup addresses;

    @Nullable
    private final String token;

    BackendAddressGroup(EquivalentAddressGroup equivalentAddressGroup, @Nullable String str) {
        this.addresses = (EquivalentAddressGroup) Preconditions.checkNotNull(equivalentAddressGroup, "addresses");
        this.token = str;
    }

    EquivalentAddressGroup getAddresses() {
        return this.addresses;
    }

    @Nullable
    String getToken() {
        return this.token;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addresses);
        if (this.token != null) {
            sb.append("(");
            sb.append(this.token);
            sb.append(")");
        }
        return sb.toString();
    }
}
