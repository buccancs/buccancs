package io.grpc;

import io.grpc.Attributes;
import io.grpc.NameResolver;

import java.util.List;

/* loaded from: classes2.dex */
public abstract class NameResolverProvider extends NameResolver.Factory {

    @Deprecated
    public static final Attributes.Key<Integer> PARAMS_DEFAULT_PORT = NameResolver.Factory.PARAMS_DEFAULT_PORT;

    @Deprecated
    public static List<NameResolverProvider> providers() {
        return NameResolverRegistry.getDefaultRegistry().providers();
    }

    @Deprecated
    public static NameResolver.Factory asFactory() {
        return NameResolverRegistry.getDefaultRegistry().asFactory();
    }

    protected abstract boolean isAvailable();

    protected abstract int priority();
}
