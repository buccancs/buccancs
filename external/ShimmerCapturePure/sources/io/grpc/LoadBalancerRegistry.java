package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServiceProviders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public final class LoadBalancerRegistry {
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private static final Iterable<Class<?>> HARDCODED_CLASSES = getHardCodedClasses();
    private static LoadBalancerRegistry instance;
    private final LinkedHashSet<LoadBalancerProvider> allProviders = new LinkedHashSet<>();
    private final LinkedHashMap<String, LoadBalancerProvider> effectiveProviders = new LinkedHashMap<>();

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        if (instance == null) {
            List<LoadBalancerProvider> listLoadAll = ServiceProviders.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new LoadBalancerPriorityAccessor());
            instance = new LoadBalancerRegistry();
            for (LoadBalancerProvider loadBalancerProvider : listLoadAll) {
                logger.fine("Service loader found " + loadBalancerProvider);
                if (loadBalancerProvider.isAvailable()) {
                    instance.addProvider(loadBalancerProvider);
                }
            }
            instance.refreshProviderMap();
        }
        return instance;
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", (Throwable) e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", (Throwable) e2);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized void register(LoadBalancerProvider loadBalancerProvider) {
        addProvider(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void addProvider(LoadBalancerProvider loadBalancerProvider) {
        Preconditions.checkArgument(loadBalancerProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(loadBalancerProvider);
    }

    public synchronized void deregister(LoadBalancerProvider loadBalancerProvider) {
        this.allProviders.remove(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void refreshProviderMap() {
        this.effectiveProviders.clear();
        Iterator<LoadBalancerProvider> it2 = this.allProviders.iterator();
        while (it2.hasNext()) {
            LoadBalancerProvider next = it2.next();
            String policyName = next.getPolicyName();
            LoadBalancerProvider loadBalancerProvider = this.effectiveProviders.get(policyName);
            if (loadBalancerProvider == null || loadBalancerProvider.getPriority() < next.getPriority()) {
                this.effectiveProviders.put(policyName, next);
            }
        }
    }

    @Nullable
    public synchronized LoadBalancerProvider getProvider(String str) {
        return this.effectiveProviders.get(Preconditions.checkNotNull(str, "policy"));
    }

    synchronized Map<String, LoadBalancerProvider> providers() {
        return new LinkedHashMap(this.effectiveProviders);
    }

    private static final class LoadBalancerPriorityAccessor implements ServiceProviders.PriorityAccessor<LoadBalancerProvider> {
        LoadBalancerPriorityAccessor() {
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public boolean isAvailable(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.isAvailable();
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public int getPriority(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.getPriority();
        }
    }
}
