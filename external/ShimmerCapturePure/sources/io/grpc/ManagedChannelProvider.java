package io.grpc;

import io.grpc.ServiceProviders;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class ManagedChannelProvider {
    static final Iterable<Class<?>> HARDCODED_CLASSES;
    private static final ManagedChannelProvider provider;

    static {
        HardcodedClasses hardcodedClasses = new HardcodedClasses();
        HARDCODED_CLASSES = hardcodedClasses;
        provider = (ManagedChannelProvider) ServiceProviders.load(ManagedChannelProvider.class, hardcodedClasses, ManagedChannelProvider.class.getClassLoader(), new ServiceProviders.PriorityAccessor<ManagedChannelProvider>() { // from class: io.grpc.ManagedChannelProvider.1
            @Override // io.grpc.ServiceProviders.PriorityAccessor
            public boolean isAvailable(ManagedChannelProvider managedChannelProvider) {
                return managedChannelProvider.isAvailable();
            }

            @Override // io.grpc.ServiceProviders.PriorityAccessor
            public int getPriority(ManagedChannelProvider managedChannelProvider) {
                return managedChannelProvider.priority();
            }
        });
    }

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider managedChannelProvider = provider;
        if (managedChannelProvider != null) {
            return managedChannelProvider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    protected abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    protected abstract ManagedChannelBuilder<?> builderForTarget(String str);

    protected abstract boolean isAvailable();

    protected abstract int priority();

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }

    private static final class HardcodedClasses implements Iterable<Class<?>> {
        private HardcodedClasses() {
        }

        @Override // java.lang.Iterable
        public Iterator<Class<?>> iterator() {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
            } catch (ClassNotFoundException unused) {
            }
            try {
                arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
            } catch (ClassNotFoundException unused2) {
            }
            return arrayList.iterator();
        }
    }
}
