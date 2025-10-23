package org.apache.commons.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.collection.PredicatedCollection;
import org.apache.commons.collections.collection.SynchronizedCollection;
import org.apache.commons.collections.collection.TransformedCollection;
import org.apache.commons.collections.collection.TypedCollection;
import org.apache.commons.collections.collection.UnmodifiableBoundedCollection;
import org.apache.commons.collections.collection.UnmodifiableCollection;

/* loaded from: classes5.dex */
public class CollectionUtils {
    public static final Collection EMPTY_COLLECTION = UnmodifiableCollection.decorate(new ArrayList());
    private static Integer INTEGER_ONE = new Integer(1);

    public static Collection union(Collection collection, Collection collection2) {
        ArrayList arrayList = new ArrayList();
        Map cardinalityMap = getCardinalityMap(collection);
        Map cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int iMax = Math.max(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < iMax; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Collection intersection(Collection collection, Collection collection2) {
        ArrayList arrayList = new ArrayList();
        Map cardinalityMap = getCardinalityMap(collection);
        Map cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int iMin = Math.min(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < iMin; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Collection disjunction(Collection collection, Collection collection2) {
        ArrayList arrayList = new ArrayList();
        Map cardinalityMap = getCardinalityMap(collection);
        Map cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int iMax = Math.max(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2)) - Math.min(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < iMax; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Collection subtract(Collection collection, Collection collection2) {
        ArrayList arrayList = new ArrayList(collection);
        Iterator it2 = collection2.iterator();
        while (it2.hasNext()) {
            arrayList.remove(it2.next());
        }
        return arrayList;
    }

    public static boolean containsAny(Collection collection, Collection collection2) {
        if (collection.size() < collection2.size()) {
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                if (collection2.contains(it2.next())) {
                    return true;
                }
            }
            return false;
        }
        Iterator it3 = collection2.iterator();
        while (it3.hasNext()) {
            if (collection.contains(it3.next())) {
                return true;
            }
        }
        return false;
    }

    public static Map getCardinalityMap(Collection collection) {
        HashMap map = new HashMap();
        for (Object obj : collection) {
            Integer num = (Integer) map.get(obj);
            if (num == null) {
                map.put(obj, INTEGER_ONE);
            } else {
                map.put(obj, new Integer(num.intValue() + 1));
            }
        }
        return map;
    }

    public static boolean isSubCollection(Collection collection, Collection collection2) {
        Map cardinalityMap = getCardinalityMap(collection);
        Map cardinalityMap2 = getCardinalityMap(collection2);
        for (Object obj : collection) {
            if (getFreq(obj, cardinalityMap) > getFreq(obj, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isProperSubCollection(Collection collection, Collection collection2) {
        return collection.size() < collection2.size() && isSubCollection(collection, collection2);
    }

    public static boolean isEqualCollection(Collection collection, Collection collection2) {
        if (collection.size() != collection2.size()) {
            return false;
        }
        Map cardinalityMap = getCardinalityMap(collection);
        Map cardinalityMap2 = getCardinalityMap(collection2);
        if (cardinalityMap.size() != cardinalityMap2.size()) {
            return false;
        }
        for (Object obj : cardinalityMap.keySet()) {
            if (getFreq(obj, cardinalityMap) != getFreq(obj, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    public static int cardinality(Object obj, Collection collection) {
        if (collection instanceof Set) {
            return collection.contains(obj) ? 1 : 0;
        }
        if (collection instanceof Bag) {
            return ((Bag) collection).getCount(obj);
        }
        int i = 0;
        if (obj == null) {
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                if (it2.next() == null) {
                    i++;
                }
            }
        } else {
            Iterator it3 = collection.iterator();
            while (it3.hasNext()) {
                if (obj.equals(it3.next())) {
                    i++;
                }
            }
        }
        return i;
    }

    public static Object find(Collection collection, Predicate predicate) {
        if (collection == null || predicate == null) {
            return null;
        }
        for (Object obj : collection) {
            if (predicate.evaluate(obj)) {
                return obj;
            }
        }
        return null;
    }

    public static void forAllDo(Collection collection, Closure closure) {
        if (collection == null || closure == null) {
            return;
        }
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            closure.execute(it2.next());
        }
    }

    public static void filter(Collection collection, Predicate predicate) {
        if (collection == null || predicate == null) {
            return;
        }
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!predicate.evaluate(it2.next())) {
                it2.remove();
            }
        }
    }

    public static void transform(Collection collection, Transformer transformer) {
        if (collection == null || transformer == null) {
            return;
        }
        if (collection instanceof List) {
            ListIterator listIterator = ((List) collection).listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(transformer.transform(listIterator.next()));
            }
        } else {
            Collection collectionCollect = collect(collection, transformer);
            collection.clear();
            collection.addAll(collectionCollect);
        }
    }

    public static int countMatches(Collection collection, Predicate predicate) {
        int i = 0;
        if (collection != null && predicate != null) {
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                if (predicate.evaluate(it2.next())) {
                    i++;
                }
            }
        }
        return i;
    }

    public static boolean exists(Collection collection, Predicate predicate) {
        if (collection == null || predicate == null) {
            return false;
        }
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            if (predicate.evaluate(it2.next())) {
                return true;
            }
        }
        return false;
    }

    public static Collection select(Collection collection, Predicate predicate) {
        ArrayList arrayList = new ArrayList(collection.size());
        select(collection, predicate, arrayList);
        return arrayList;
    }

    public static void select(Collection collection, Predicate predicate, Collection collection2) {
        if (collection == null || predicate == null) {
            return;
        }
        for (Object obj : collection) {
            if (predicate.evaluate(obj)) {
                collection2.add(obj);
            }
        }
    }

    public static Collection selectRejected(Collection collection, Predicate predicate) {
        ArrayList arrayList = new ArrayList(collection.size());
        selectRejected(collection, predicate, arrayList);
        return arrayList;
    }

    public static void selectRejected(Collection collection, Predicate predicate, Collection collection2) {
        if (collection == null || predicate == null) {
            return;
        }
        for (Object obj : collection) {
            if (!predicate.evaluate(obj)) {
                collection2.add(obj);
            }
        }
    }

    public static Collection collect(Collection collection, Transformer transformer) {
        ArrayList arrayList = new ArrayList(collection.size());
        collect(collection, transformer, arrayList);
        return arrayList;
    }

    public static Collection collect(Iterator it2, Transformer transformer) {
        ArrayList arrayList = new ArrayList();
        collect(it2, transformer, arrayList);
        return arrayList;
    }

    public static Collection collect(Collection collection, Transformer transformer, Collection collection2) {
        return collection != null ? collect(collection.iterator(), transformer, collection2) : collection2;
    }

    public static Collection collect(Iterator it2, Transformer transformer, Collection collection) {
        if (it2 != null && transformer != null) {
            while (it2.hasNext()) {
                collection.add(transformer.transform(it2.next()));
            }
        }
        return collection;
    }

    public static boolean addIgnoreNull(Collection collection, Object obj) {
        if (obj == null) {
            return false;
        }
        return collection.add(obj);
    }

    public static void addAll(Collection collection, Iterator it2) {
        while (it2.hasNext()) {
            collection.add(it2.next());
        }
    }

    public static void addAll(Collection collection, Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            collection.add(enumeration.nextElement());
        }
    }

    public static void addAll(Collection collection, Object[] objArr) {
        for (Object obj : objArr) {
            collection.add(obj);
        }
    }

    public static Object index(Object obj, int i) {
        return index(obj, new Integer(i));
    }

    public static Object index(Object obj, Object obj2) {
        boolean z = obj instanceof Map;
        if (z) {
            Map map = (Map) obj;
            if (map.containsKey(obj2)) {
                return map.get(obj2);
            }
        }
        int iIntValue = obj2 instanceof Integer ? ((Integer) obj2).intValue() : -1;
        if (iIntValue < 0) {
            return obj;
        }
        if (z) {
            return index(((Map) obj).keySet().iterator(), iIntValue);
        }
        if (obj instanceof List) {
            return ((List) obj).get(iIntValue);
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj)[iIntValue];
        }
        if (obj instanceof Enumeration) {
            Enumeration enumeration = (Enumeration) obj;
            while (enumeration.hasMoreElements()) {
                iIntValue--;
                if (iIntValue == -1) {
                    return enumeration.nextElement();
                }
                enumeration.nextElement();
            }
            return obj;
        }
        if (obj instanceof Iterator) {
            return index((Iterator) obj, iIntValue);
        }
        return obj instanceof Collection ? index(((Collection) obj).iterator(), iIntValue) : obj;
    }

    private static Object index(Iterator it2, int i) {
        while (it2.hasNext()) {
            i--;
            if (i == -1) {
                return it2.next();
            }
            it2.next();
        }
        return it2;
    }

    public static Object get(Object obj, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException(new StringBuffer("Index cannot be negative: ").append(i).toString());
        }
        if (obj instanceof Map) {
            return get(((Map) obj).entrySet().iterator(), i);
        }
        if (obj instanceof List) {
            return ((List) obj).get(i);
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj)[i];
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                i--;
                if (i == -1) {
                    return it2.next();
                }
                it2.next();
            }
            throw new IndexOutOfBoundsException(new StringBuffer("Entry does not exist: ").append(i).toString());
        }
        if (obj instanceof Collection) {
            return get(((Collection) obj).iterator(), i);
        }
        if (!(obj instanceof Enumeration)) {
            if (obj == null) {
                throw new IllegalArgumentException("Unsupported object type: null");
            }
            try {
                return Array.get(obj, i);
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(new StringBuffer("Unsupported object type: ").append(obj.getClass().getName()).toString());
            }
        }
        Enumeration enumeration = (Enumeration) obj;
        while (enumeration.hasMoreElements()) {
            i--;
            if (i == -1) {
                return enumeration.nextElement();
            }
            enumeration.nextElement();
        }
        throw new IndexOutOfBoundsException(new StringBuffer("Entry does not exist: ").append(i).toString());
    }

    public static int size(Object obj) {
        if (obj instanceof Map) {
            return ((Map) obj).size();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            int i = 0;
            while (it2.hasNext()) {
                i++;
                it2.next();
            }
            return i;
        }
        if (!(obj instanceof Enumeration)) {
            if (obj == null) {
                throw new IllegalArgumentException("Unsupported object type: null");
            }
            try {
                return Array.getLength(obj);
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(new StringBuffer("Unsupported object type: ").append(obj.getClass().getName()).toString());
            }
        }
        Enumeration enumeration = (Enumeration) obj;
        int i2 = 0;
        while (enumeration.hasMoreElements()) {
            i2++;
            enumeration.nextElement();
        }
        return i2;
    }

    public static boolean sizeIsEmpty(Object obj) {
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        }
        if (obj instanceof Enumeration) {
            return !((Enumeration) obj).hasMoreElements();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Unsupported object type: null");
        }
        try {
            return Array.getLength(obj) == 0;
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(new StringBuffer("Unsupported object type: ").append(obj.getClass().getName()).toString());
        }
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static void reverseArray(Object[] objArr) {
        int length = objArr.length - 1;
        for (int i = 0; length > i; i++) {
            Object obj = objArr[length];
            objArr[length] = objArr[i];
            objArr[i] = obj;
            length--;
        }
    }

    private static final int getFreq(Object obj, Map map) {
        Integer num = (Integer) map.get(obj);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static boolean isFull(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection) collection).isFull();
        }
        try {
            return UnmodifiableBoundedCollection.decorateUsing(collection).isFull();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static int maxSize(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection) collection).maxSize();
        }
        try {
            return UnmodifiableBoundedCollection.decorateUsing(collection).maxSize();
        } catch (IllegalArgumentException unused) {
            return -1;
        }
    }

    public static Collection retainAll(Collection collection, Collection collection2) {
        return ListUtils.retainAll(collection, collection2);
    }

    public static Collection removeAll(Collection collection, Collection collection2) {
        return ListUtils.retainAll(collection, collection2);
    }

    public static Collection synchronizedCollection(Collection collection) {
        return SynchronizedCollection.decorate(collection);
    }

    public static Collection unmodifiableCollection(Collection collection) {
        return UnmodifiableCollection.decorate(collection);
    }

    public static Collection predicatedCollection(Collection collection, Predicate predicate) {
        return PredicatedCollection.decorate(collection, predicate);
    }

    public static Collection typedCollection(Collection collection, Class cls) {
        return TypedCollection.decorate(collection, cls);
    }

    public static Collection transformedCollection(Collection collection, Transformer transformer) {
        return TransformedCollection.decorate(collection, transformer);
    }
}
