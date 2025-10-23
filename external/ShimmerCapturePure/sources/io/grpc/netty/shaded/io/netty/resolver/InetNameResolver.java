package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/* loaded from: classes3.dex */
public abstract class InetNameResolver extends SimpleNameResolver<InetAddress> {
    private volatile AddressResolver<InetSocketAddress> addressResolver;

    protected InetNameResolver(EventExecutor eventExecutor) {
        super(eventExecutor);
    }

    public AddressResolver<InetSocketAddress> asAddressResolver() {
        AddressResolver<InetSocketAddress> inetSocketAddressResolver = this.addressResolver;
        if (inetSocketAddressResolver == null) {
            synchronized (this) {
                inetSocketAddressResolver = this.addressResolver;
                if (inetSocketAddressResolver == null) {
                    inetSocketAddressResolver = new InetSocketAddressResolver(executor(), this);
                    this.addressResolver = inetSocketAddressResolver;
                }
            }
        }
        return inetSocketAddressResolver;
    }
}
