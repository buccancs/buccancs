package org.apache.commons.collections.map;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.FunctorException;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.iterators.EmptyIterator;
import org.apache.commons.collections.iterators.IteratorChain;

/* loaded from: classes5.dex */
public class MultiValueMap extends AbstractMapDecorator implements MultiMap {
    static /* synthetic */ Class class$java$util$ArrayList;
    private final Factory collectionFactory;
    private transient Collection values;

    public MultiValueMap() {
        HashMap map = new HashMap();
        Class clsClass$ = class$java$util$ArrayList;
        if (clsClass$ == null) {
            clsClass$ = class$("java.util.ArrayList");
            class$java$util$ArrayList = clsClass$;
        }
        this(map, new ReflectionFactory(clsClass$));
    }

    protected MultiValueMap(Map map, Factory factory) {
        super(map);
        if (factory == null) {
            throw new IllegalArgumentException("The factory must not be null");
        }
        this.collectionFactory = factory;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static MultiValueMap decorate(Map map) {
        Class clsClass$ = class$java$util$ArrayList;
        if (clsClass$ == null) {
            clsClass$ = class$("java.util.ArrayList");
            class$java$util$ArrayList = clsClass$;
        }
        return new MultiValueMap(map, new ReflectionFactory(clsClass$));
    }

    public static MultiValueMap decorate(Map map, Class cls) {
        return new MultiValueMap(map, new ReflectionFactory(cls));
    }

    public static MultiValueMap decorate(Map map, Factory factory) {
        return new MultiValueMap(map, factory);
    }

    @Override // org.apache.commons.collections.map.AbstractMapDecorator, java.util.Map
    public void clear() {
        getMap().clear();
    }

    @Override // java.util.Map, org.apache.commons.collections.MultiMap
    public Object remove(Object obj, Object obj2) {
        Collection collection = getCollection(obj);
        if (collection == null || !collection.remove(obj2)) {
            return null;
        }
        if (collection.isEmpty()) {
            remove(obj);
        }
        return obj2;
    }

    @Override // org.apache.commons.collections.map.AbstractMapDecorator, java.util.Map
    public boolean containsValue(Object obj) {
        Set setEntrySet = getMap().entrySet();
        if (setEntrySet == null) {
            return false;
        }
        Iterator it2 = setEntrySet.iterator();
        while (it2.hasNext()) {
            if (((Collection) ((Map.Entry) it2.next()).getValue()).contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    // org.apache.commons.collections.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections.BidiMap
    public Object put(Object obj, Object obj2) {
        boolean zAdd;
        Collection collection = getCollection(obj);
        if (collection == null) {
            Collection collectionCreateCollection = createCollection(1);
            zAdd = collectionCreateCollection.add(obj2);
            if (collectionCreateCollection.size() > 0) {
                getMap().put(obj, collectionCreateCollection);
            }
            return null;
        }
        zAdd = collection.add(obj2);
        if (zAdd) {
            return obj2;
        }
        return null;
    }

    @Override // org.apache.commons.collections.map.AbstractMapDecorator, java.util.Map
    public void putAll(Map map) {
        if (map instanceof MultiMap) {
            for (Map.Entry entry : map.entrySet()) {
                putAll(entry.getKey(), (Collection) entry.getValue());
            }
            return;
        }
        for (Map.Entry entry2 : map.entrySet()) {
            put(entry2.getKey(), entry2.getValue());
        }
    }

    @Override // org.apache.commons.collections.map.AbstractMapDecorator, java.util.Map
    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values(this, null);
        this.values = values;
        return values;
    }

    public boolean containsValue(Object obj, Object obj2) {
        Collection collection = getCollection(obj);
        if (collection == null) {
            return false;
        }
        return collection.contains(obj2);
    }

    public Collection getCollection(Object obj) {
        return (Collection) getMap().get(obj);
    }

    public int size(Object obj) {
        Collection collection = getCollection(obj);
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public boolean putAll(Object obj, Collection collection) {
        if (collection == null || collection.size() == 0) {
            return false;
        }
        Collection collection2 = getCollection(obj);
        if (collection2 == null) {
            Collection collectionCreateCollection = createCollection(collection.size());
            boolean zAddAll = collectionCreateCollection.addAll(collection);
            if (collectionCreateCollection.size() <= 0) {
                return zAddAll;
            }
            getMap().put(obj, collectionCreateCollection);
            return false;
        }
        return collection2.addAll(collection);
    }

    public Iterator iterator(Object obj) {
        if (!containsKey(obj)) {
            return EmptyIterator.INSTANCE;
        }
        return new ValuesIterator(obj);
    }

    public int totalSize() {
        Iterator it2 = getMap().values().iterator();
        int size = 0;
        while (it2.hasNext()) {
            size += ((Collection) it2.next()).size();
        }
        return size;
    }

    protected Collection createCollection(int i) {
        return (Collection) this.collectionFactory.create();
    }

    private static class ReflectionFactory implements Factory {
        private final Class clazz;

        public ReflectionFactory(Class cls) {
            this.clazz = cls;
        }

        @Override // org.apache.commons.collections.Factory
        public Object create() {
            try {
                return this.clazz.newInstance();
            } catch (Exception e) {
                throw new FunctorException(new StringBuffer("Cannot instantiate class: ").append(this.clazz).toString(), e);
            }
        }
    }

    /* renamed from: org.apache.commons.collections.map.MultiValueMap$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private class Values extends AbstractCollection {
        private Values() {
        }

        /* synthetic */ Values(MultiValueMap multiValueMap, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            IteratorChain iteratorChain = new IteratorChain();
            Iterator it2 = MultiValueMap.this.keySet().iterator();
            while (it2.hasNext()) {
                iteratorChain.addIterator(MultiValueMap.this.new ValuesIterator(it2.next()));
            }
            return iteratorChain;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MultiValueMap.this.totalSize();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MultiValueMap.this.clear();
        }
    }

    private class ValuesIterator implements Iterator {
        private final Iterator iterator;
        private final Object key;
        private final Collection values;

        public ValuesIterator(Object obj) {
            this.key = obj;
            Collection collection = MultiValueMap.this.getCollection(obj);
            this.values = collection;
            this.iterator = collection.iterator();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                MultiValueMap.this.remove(this.key);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            return this.iterator.next();
        }
    }
}
