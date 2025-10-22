package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class EquivalentAddressGroup {
    public static final Attributes.Key<String> ATTR_AUTHORITY_OVERRIDE = Attributes.Key.create("io.grpc.EquivalentAddressGroup.authorityOverride");
    private final List<SocketAddress> addrs;
    private final Attributes attrs;
    private final int hashCode;

    public EquivalentAddressGroup(List<SocketAddress> list) {
        this(list, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(List<SocketAddress> list, Attributes attributes) {
        Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
        List<SocketAddress> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addrs = listUnmodifiableList;
        this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
        this.hashCode = listUnmodifiableList.hashCode();
    }

    public EquivalentAddressGroup(SocketAddress socketAddress) {
        this(socketAddress, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(SocketAddress socketAddress, Attributes attributes) {
        this((List<SocketAddress>) Collections.singletonList(socketAddress), attributes);
    }

    public List<SocketAddress> getAddresses() {
        return this.addrs;
    }

    public Attributes getAttributes() {
        return this.attrs;
    }

    public int hashCode() {
        return this.hashCode;
    }

    public String toString() {
        return "[" + this.addrs + "/" + this.attrs + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) obj;
        if (this.addrs.size() != equivalentAddressGroup.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); i++) {
            if (!this.addrs.get(i).equals(equivalentAddressGroup.addrs.get(i))) {
                return false;
            }
        }
        return this.attrs.equals(equivalentAddressGroup.attrs);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Attr {
    }
}
