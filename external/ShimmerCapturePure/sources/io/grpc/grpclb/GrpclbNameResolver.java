package io.grpc.grpclb;

import com.google.common.base.Stopwatch;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.internal.DnsNameResolver;
import io.grpc.internal.SharedResourceHolder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class GrpclbNameResolver extends DnsNameResolver {
    private static final String GRPCLB_NAME_PREFIX = "_grpclb._tcp.";
    private static final Logger logger = Logger.getLogger(GrpclbNameResolver.class.getName());

    GrpclbNameResolver(@Nullable String str, String str2, NameResolver.Args args, SharedResourceHolder.Resource<Executor> resource, Stopwatch stopwatch, boolean z) {
        super(str, str2, args, resource, stopwatch, z);
    }

    static void setEnableTxt(boolean z) {
        DnsNameResolver.enableTxt = z;
    }

    @Override // io.grpc.internal.DnsNameResolver
    protected DnsNameResolver.InternalResolutionResult doResolve(boolean z) {
        List<EquivalentAddressGroup> listResolveBalancerAddresses = resolveBalancerAddresses();
        DnsNameResolver.InternalResolutionResult internalResolutionResultDoResolve = super.doResolve(!listResolveBalancerAddresses.isEmpty());
        if (!listResolveBalancerAddresses.isEmpty()) {
            internalResolutionResultDoResolve.attributes = Attributes.newBuilder().set(GrpclbConstants.ATTR_LB_ADDRS, listResolveBalancerAddresses).build();
        }
        return internalResolutionResultDoResolve;
    }

    private List<EquivalentAddressGroup> resolveBalancerAddresses() {
        List<DnsNameResolver.SrvRecord> listEmptyList = Collections.emptyList();
        DnsNameResolver.ResourceResolver resourceResolver = getResourceResolver();
        Exception exc = null;
        if (resourceResolver != null) {
            try {
                listEmptyList = resourceResolver.resolveSrv(GRPCLB_NAME_PREFIX + getHost());
                e = null;
            } catch (Exception e) {
                e = e;
            }
        } else {
            e = null;
        }
        ArrayList arrayList = new ArrayList(listEmptyList.size());
        Level level = Level.WARNING;
        for (DnsNameResolver.SrvRecord srvRecord : listEmptyList) {
            try {
                String strSubstring = srvRecord.host.substring(0, srvRecord.host.length() - 1);
                List<InetAddress> listResolveAddress = this.addressResolver.resolveAddress(srvRecord.host);
                ArrayList arrayList2 = new ArrayList(listResolveAddress.size());
                Iterator<InetAddress> it2 = listResolveAddress.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new InetSocketAddress(it2.next(), srvRecord.port));
                }
                arrayList.add(new EquivalentAddressGroup((List<SocketAddress>) Collections.unmodifiableList(arrayList2), Attributes.newBuilder().set(GrpclbConstants.ATTR_LB_ADDR_AUTHORITY, strSubstring).build()));
            } catch (Exception e2) {
                logger.log(level, "Can't find address for SRV record " + srvRecord, (Throwable) e2);
                if (exc == null) {
                    level = Level.FINE;
                    exc = e2;
                }
            }
        }
        if (e != null || (exc != null && arrayList.isEmpty())) {
            logger.log(Level.FINE, "Balancer resolution failure", (Throwable) e);
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // io.grpc.internal.DnsNameResolver
    protected void setAddressResolver(DnsNameResolver.AddressResolver addressResolver) {
        super.setAddressResolver(addressResolver);
    }

    @Override // io.grpc.internal.DnsNameResolver
    protected void setResourceResolver(DnsNameResolver.ResourceResolver resourceResolver) {
        super.setResourceResolver(resourceResolver);
    }

    @Override // io.grpc.internal.DnsNameResolver
    protected String getHost() {
        return super.getHost();
    }
}
