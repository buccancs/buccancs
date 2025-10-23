package io.grpc.util;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.annotation.Nonnull;

/* loaded from: classes3.dex */
final class RoundRobinLoadBalancer extends LoadBalancer {
    static final Attributes.Key<Ref<ConnectivityStateInfo>> STATE_INFO = Attributes.Key.create("state-info");
    private static final Status EMPTY_OK = Status.OK.withDescription("no subchannels ready");
    private final LoadBalancer.Helper helper;
    private final Map<EquivalentAddressGroup, LoadBalancer.Subchannel> subchannels = new HashMap();
    private final Random random = new Random();
    private ConnectivityState currentState;
    private RoundRobinPicker currentPicker = new EmptyPicker(EMPTY_OK);

    RoundRobinLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    private static List<LoadBalancer.Subchannel> filterNonFailingSubchannels(Collection<LoadBalancer.Subchannel> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (LoadBalancer.Subchannel subchannel : collection) {
            if (isReady(subchannel)) {
                arrayList.add(subchannel);
            }
        }
        return arrayList;
    }

    private static Map<EquivalentAddressGroup, EquivalentAddressGroup> stripAttrs(List<EquivalentAddressGroup> list) {
        HashMap map = new HashMap(list.size() * 2);
        for (EquivalentAddressGroup equivalentAddressGroup : list) {
            map.put(stripAttrs(equivalentAddressGroup), equivalentAddressGroup);
        }
        return map;
    }

    private static EquivalentAddressGroup stripAttrs(EquivalentAddressGroup equivalentAddressGroup) {
        return new EquivalentAddressGroup(equivalentAddressGroup.getAddresses());
    }

    private static Ref<ConnectivityStateInfo> getSubchannelStateInfoRef(LoadBalancer.Subchannel subchannel) {
        return (Ref) Preconditions.checkNotNull(subchannel.getAttributes().get(STATE_INFO), "STATE_INFO");
    }

    static boolean isReady(LoadBalancer.Subchannel subchannel) {
        return getSubchannelStateInfoRef(subchannel).value.getState() == ConnectivityState.READY;
    }

    private static <T> Set<T> setsDifference(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(set2);
        return hashSet;
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        Set<EquivalentAddressGroup> setKeySet = this.subchannels.keySet();
        Map<EquivalentAddressGroup, EquivalentAddressGroup> mapStripAttrs = stripAttrs(addresses);
        Set set = setsDifference(setKeySet, mapStripAttrs.keySet());
        for (Map.Entry<EquivalentAddressGroup, EquivalentAddressGroup> entry : mapStripAttrs.entrySet()) {
            EquivalentAddressGroup key = entry.getKey();
            EquivalentAddressGroup value = entry.getValue();
            LoadBalancer.Subchannel subchannel = this.subchannels.get(key);
            if (subchannel != null) {
                subchannel.updateAddresses(Collections.singletonList(value));
            } else {
                final LoadBalancer.Subchannel subchannel2 = (LoadBalancer.Subchannel) Preconditions.checkNotNull(this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(value).setAttributes(Attributes.newBuilder().set(STATE_INFO, new Ref(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE))).build()).build()), "subchannel");
                subchannel2.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.util.RoundRobinLoadBalancer.1
                    @Override // io.grpc.LoadBalancer.SubchannelStateListener
                    public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                        RoundRobinLoadBalancer.this.processSubchannelState(subchannel2, connectivityStateInfo);
                    }
                });
                this.subchannels.put(key, subchannel2);
                subchannel2.requestConnection();
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            arrayList.add(this.subchannels.remove((EquivalentAddressGroup) it2.next()));
        }
        updateBalancingState();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            shutdownSubchannel((LoadBalancer.Subchannel) it3.next());
        }
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        ConnectivityState connectivityState = ConnectivityState.TRANSIENT_FAILURE;
        RoundRobinPicker emptyPicker = this.currentPicker;
        if (!(emptyPicker instanceof ReadyPicker)) {
            emptyPicker = new EmptyPicker(status);
        }
        updateBalancingState(connectivityState, emptyPicker);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        if (this.subchannels.get(stripAttrs(subchannel.getAddresses())) != subchannel) {
            return;
        }
        if (connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            subchannel.requestConnection();
        }
        Ref<ConnectivityStateInfo> subchannelStateInfoRef = getSubchannelStateInfoRef(subchannel);
        if (subchannelStateInfoRef.value.getState().equals(ConnectivityState.TRANSIENT_FAILURE) && (connectivityStateInfo.getState().equals(ConnectivityState.CONNECTING) || connectivityStateInfo.getState().equals(ConnectivityState.IDLE))) {
            return;
        }
        subchannelStateInfoRef.value = connectivityStateInfo;
        updateBalancingState();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, io.grpc.ConnectivityStateInfo] */
    private void shutdownSubchannel(LoadBalancer.Subchannel subchannel) {
        subchannel.shutdown();
        getSubchannelStateInfoRef(subchannel).value = ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        Iterator<LoadBalancer.Subchannel> it2 = getSubchannels().iterator();
        while (it2.hasNext()) {
            shutdownSubchannel(it2.next());
        }
    }

    private void updateBalancingState() {
        List<LoadBalancer.Subchannel> listFilterNonFailingSubchannels = filterNonFailingSubchannels(getSubchannels());
        if (listFilterNonFailingSubchannels.isEmpty()) {
            Status status = EMPTY_OK;
            Iterator<LoadBalancer.Subchannel> it2 = getSubchannels().iterator();
            boolean z = false;
            while (it2.hasNext()) {
                ConnectivityStateInfo connectivityStateInfo = getSubchannelStateInfoRef(it2.next()).value;
                if (connectivityStateInfo.getState() == ConnectivityState.CONNECTING || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                    z = true;
                }
                if (status == EMPTY_OK || !status.isOk()) {
                    status = connectivityStateInfo.getStatus();
                }
            }
            updateBalancingState(z ? ConnectivityState.CONNECTING : ConnectivityState.TRANSIENT_FAILURE, new EmptyPicker(status));
            return;
        }
        updateBalancingState(ConnectivityState.READY, new ReadyPicker(listFilterNonFailingSubchannels, this.random.nextInt(listFilterNonFailingSubchannels.size())));
    }

    private void updateBalancingState(ConnectivityState connectivityState, RoundRobinPicker roundRobinPicker) {
        if (connectivityState == this.currentState && roundRobinPicker.isEquivalentTo(this.currentPicker)) {
            return;
        }
        this.helper.updateBalancingState(connectivityState, roundRobinPicker);
        this.currentState = connectivityState;
        this.currentPicker = roundRobinPicker;
    }

    Collection<LoadBalancer.Subchannel> getSubchannels() {
        return this.subchannels.values();
    }

    private static abstract class RoundRobinPicker extends LoadBalancer.SubchannelPicker {
        private RoundRobinPicker() {
        }

        abstract boolean isEquivalentTo(RoundRobinPicker roundRobinPicker);
    }

    static final class ReadyPicker extends RoundRobinPicker {
        private static final AtomicIntegerFieldUpdater<ReadyPicker> indexUpdater = AtomicIntegerFieldUpdater.newUpdater(ReadyPicker.class, "index");
        private final List<LoadBalancer.Subchannel> list;
        private volatile int index;

        ReadyPicker(List<LoadBalancer.Subchannel> list, int i) {
            super();
            Preconditions.checkArgument(!list.isEmpty(), "empty list");
            this.list = list;
            this.index = i - 1;
        }

        List<LoadBalancer.Subchannel> getList() {
            return this.list;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withSubchannel(nextSubchannel());
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) ReadyPicker.class).add("list", this.list).toString();
        }

        private LoadBalancer.Subchannel nextSubchannel() {
            int size = this.list.size();
            AtomicIntegerFieldUpdater<ReadyPicker> atomicIntegerFieldUpdater = indexUpdater;
            int iIncrementAndGet = atomicIntegerFieldUpdater.incrementAndGet(this);
            if (iIncrementAndGet >= size) {
                int i = iIncrementAndGet % size;
                atomicIntegerFieldUpdater.compareAndSet(this, iIncrementAndGet, i);
                iIncrementAndGet = i;
            }
            return this.list.get(iIncrementAndGet);
        }

        @Override
            // io.grpc.util.RoundRobinLoadBalancer.RoundRobinPicker
        boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (!(roundRobinPicker instanceof ReadyPicker)) {
                return false;
            }
            ReadyPicker readyPicker = (ReadyPicker) roundRobinPicker;
            return readyPicker == this || (this.list.size() == readyPicker.list.size() && new HashSet(this.list).containsAll(readyPicker.list));
        }
    }

    static final class EmptyPicker extends RoundRobinPicker {
        private final Status status;

        EmptyPicker(@Nonnull Status status) {
            super();
            this.status = (Status) Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.status.isOk() ? LoadBalancer.PickResult.withNoResult() : LoadBalancer.PickResult.withError(this.status);
        }

        @Override
            // io.grpc.util.RoundRobinLoadBalancer.RoundRobinPicker
        boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (roundRobinPicker instanceof EmptyPicker) {
                EmptyPicker emptyPicker = (EmptyPicker) roundRobinPicker;
                if (Objects.equal(this.status, emptyPicker.status) || (this.status.isOk() && emptyPicker.status.isOk())) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).add(NotificationCompat.CATEGORY_STATUS, this.status).toString();
        }
    }

    static final class Ref<T> {
        T value;

        Ref(T t) {
            this.value = t;
        }
    }
}
