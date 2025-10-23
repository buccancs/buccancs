package org.apache.commons.math3.ml.neuralnet;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;

/* loaded from: classes5.dex */
public class Network implements Iterable<Neuron>, Serializable {
    private static final long serialVersionUID = 20130207;
    private final int featureSize;
    private final AtomicLong nextId;
    private final ConcurrentHashMap<Long, Neuron> neuronMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<Long>> linkMap = new ConcurrentHashMap<>();

    Network(long j, int i, Neuron[] neuronArr, long[][] jArr) {
        int length = neuronArr.length;
        if (length != jArr.length) {
            throw new MathIllegalStateException();
        }
        for (Neuron neuron : neuronArr) {
            long identifier = neuron.getIdentifier();
            if (identifier >= j) {
                throw new MathIllegalStateException();
            }
            this.neuronMap.put(Long.valueOf(identifier), neuron);
            this.linkMap.put(Long.valueOf(identifier), new HashSet());
        }
        for (int i2 = 0; i2 < length; i2++) {
            Set<Long> set = this.linkMap.get(Long.valueOf(neuronArr[i2].getIdentifier()));
            for (long j2 : jArr[i2]) {
                Long lValueOf = Long.valueOf(j2);
                if (this.neuronMap.get(lValueOf) == null) {
                    throw new MathIllegalStateException();
                }
                addLinkToLinkSet(set, lValueOf.longValue());
            }
        }
        this.nextId = new AtomicLong(j);
        this.featureSize = i;
    }

    public Network(long j, int i) {
        this.nextId = new AtomicLong(j);
        this.featureSize = i;
    }

    public int getFeaturesSize() {
        return this.featureSize;
    }

    public synchronized Network copy() {
        Network network;
        network = new Network(this.nextId.get(), this.featureSize);
        for (Map.Entry<Long, Neuron> entry : this.neuronMap.entrySet()) {
            network.neuronMap.put(entry.getKey(), entry.getValue().copy());
        }
        for (Map.Entry<Long, Set<Long>> entry2 : this.linkMap.entrySet()) {
            network.linkMap.put(entry2.getKey(), new HashSet(entry2.getValue()));
        }
        return network;
    }

    @Override // java.lang.Iterable
    public Iterator<Neuron> iterator() {
        return this.neuronMap.values().iterator();
    }

    public Collection<Neuron> getNeurons(Comparator<Neuron> comparator) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.neuronMap.values());
        Collections.sort(arrayList, comparator);
        return arrayList;
    }

    public long createNeuron(double[] dArr) {
        if (dArr.length != this.featureSize) {
            throw new DimensionMismatchException(dArr.length, this.featureSize);
        }
        long jLongValue = createNextId().longValue();
        this.neuronMap.put(Long.valueOf(jLongValue), new Neuron(jLongValue, dArr));
        this.linkMap.put(Long.valueOf(jLongValue), new HashSet());
        return jLongValue;
    }

    public void deleteNeuron(Neuron neuron) {
        Iterator<Neuron> it2 = getNeighbours(neuron).iterator();
        while (it2.hasNext()) {
            deleteLink(it2.next(), neuron);
        }
        this.neuronMap.remove(Long.valueOf(neuron.getIdentifier()));
    }

    public void addLink(Neuron neuron, Neuron neuron2) {
        long identifier = neuron.getIdentifier();
        long identifier2 = neuron2.getIdentifier();
        if (neuron != getNeuron(identifier)) {
            throw new NoSuchElementException(Long.toString(identifier));
        }
        if (neuron2 != getNeuron(identifier2)) {
            throw new NoSuchElementException(Long.toString(identifier2));
        }
        addLinkToLinkSet(this.linkMap.get(Long.valueOf(identifier)), identifier2);
    }

    private void addLinkToLinkSet(Set<Long> set, long j) {
        set.add(Long.valueOf(j));
    }

    public void deleteLink(Neuron neuron, Neuron neuron2) {
        long identifier = neuron.getIdentifier();
        long identifier2 = neuron2.getIdentifier();
        if (neuron != getNeuron(identifier)) {
            throw new NoSuchElementException(Long.toString(identifier));
        }
        if (neuron2 != getNeuron(identifier2)) {
            throw new NoSuchElementException(Long.toString(identifier2));
        }
        deleteLinkFromLinkSet(this.linkMap.get(Long.valueOf(identifier)), identifier2);
    }

    private void deleteLinkFromLinkSet(Set<Long> set, long j) {
        set.remove(Long.valueOf(j));
    }

    public Neuron getNeuron(long j) {
        Neuron neuron = this.neuronMap.get(Long.valueOf(j));
        if (neuron != null) {
            return neuron;
        }
        throw new NoSuchElementException(Long.toString(j));
    }

    public Collection<Neuron> getNeighbours(Iterable<Neuron> iterable) {
        return getNeighbours(iterable, (Iterable<Neuron>) null);
    }

    public Collection<Neuron> getNeighbours(Iterable<Neuron> iterable, Iterable<Neuron> iterable2) {
        HashSet hashSet = new HashSet();
        Iterator<Neuron> it2 = iterable.iterator();
        while (it2.hasNext()) {
            hashSet.addAll(this.linkMap.get(Long.valueOf(it2.next().getIdentifier())));
        }
        if (iterable2 != null) {
            Iterator<Neuron> it3 = iterable2.iterator();
            while (it3.hasNext()) {
                hashSet.remove(Long.valueOf(it3.next().getIdentifier()));
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it4 = hashSet.iterator();
        while (it4.hasNext()) {
            arrayList.add(getNeuron(((Long) it4.next()).longValue()));
        }
        return arrayList;
    }

    public Collection<Neuron> getNeighbours(Neuron neuron) {
        return getNeighbours(neuron, (Iterable<Neuron>) null);
    }

    public Collection<Neuron> getNeighbours(Neuron neuron, Iterable<Neuron> iterable) {
        Set<Long> set = this.linkMap.get(Long.valueOf(neuron.getIdentifier()));
        if (iterable != null) {
            Iterator<Neuron> it2 = iterable.iterator();
            while (it2.hasNext()) {
                set.remove(Long.valueOf(it2.next().getIdentifier()));
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it3 = set.iterator();
        while (it3.hasNext()) {
            arrayList.add(getNeuron(it3.next().longValue()));
        }
        return arrayList;
    }

    private Long createNextId() {
        return Long.valueOf(this.nextId.getAndIncrement());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        Neuron[] neuronArr = (Neuron[]) this.neuronMap.values().toArray(new Neuron[0]);
        long[][] jArr = new long[neuronArr.length][];
        for (int i = 0; i < neuronArr.length; i++) {
            Collection<Neuron> neighbours = getNeighbours(neuronArr[i]);
            long[] jArr2 = new long[neighbours.size()];
            Iterator<Neuron> it2 = neighbours.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                jArr2[i2] = it2.next().getIdentifier();
                i2++;
            }
            jArr[i] = jArr2;
        }
        return new SerializationProxy(this.nextId.get(), this.featureSize, neuronArr, jArr);
    }

    public static class NeuronIdentifierComparator implements Comparator<Neuron>, Serializable {
        private static final long serialVersionUID = 20130207;

        @Override // java.util.Comparator
        public int compare(Neuron neuron, Neuron neuron2) {
            long identifier = neuron.getIdentifier();
            long identifier2 = neuron2.getIdentifier();
            if (identifier < identifier2) {
                return -1;
            }
            return identifier > identifier2 ? 1 : 0;
        }
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130207;
        private final int featureSize;
        private final long[][] neighbourIdList;
        private final Neuron[] neuronList;
        private final long nextId;

        SerializationProxy(long j, int i, Neuron[] neuronArr, long[][] jArr) {
            this.nextId = j;
            this.featureSize = i;
            this.neuronList = neuronArr;
            this.neighbourIdList = jArr;
        }

        private Object readResolve() {
            return new Network(this.nextId, this.featureSize, this.neuronList, this.neighbourIdList);
        }
    }
}
