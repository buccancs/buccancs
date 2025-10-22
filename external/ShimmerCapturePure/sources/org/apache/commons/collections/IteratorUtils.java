package org.apache.commons.collections;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.commons.collections.iterators.CollatingIterator;
import org.apache.commons.collections.iterators.EmptyIterator;
import org.apache.commons.collections.iterators.EmptyListIterator;
import org.apache.commons.collections.iterators.EmptyMapIterator;
import org.apache.commons.collections.iterators.EmptyOrderedIterator;
import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections.iterators.EnumerationIterator;
import org.apache.commons.collections.iterators.FilterIterator;
import org.apache.commons.collections.iterators.FilterListIterator;
import org.apache.commons.collections.iterators.IteratorChain;
import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.collections.iterators.ListIteratorWrapper;
import org.apache.commons.collections.iterators.LoopingIterator;
import org.apache.commons.collections.iterators.LoopingListIterator;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.collections.iterators.ObjectArrayListIterator;
import org.apache.commons.collections.iterators.ObjectGraphIterator;
import org.apache.commons.collections.iterators.SingletonIterator;
import org.apache.commons.collections.iterators.SingletonListIterator;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.commons.collections.iterators.UnmodifiableIterator;
import org.apache.commons.collections.iterators.UnmodifiableListIterator;
import org.apache.commons.collections.iterators.UnmodifiableMapIterator;

/* loaded from: classes5.dex */
public class IteratorUtils {
    public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
    public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
    public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
    public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
    public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;
    static /* synthetic */ Class class$java$util$Iterator;

    public static ResettableIterator emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public static ResettableListIterator emptyListIterator() {
        return EMPTY_LIST_ITERATOR;
    }

    public static MapIterator emptyMapIterator() {
        return EMPTY_MAP_ITERATOR;
    }

    public static OrderedIterator emptyOrderedIterator() {
        return EMPTY_ORDERED_ITERATOR;
    }

    public static OrderedMapIterator emptyOrderedMapIterator() {
        return EMPTY_ORDERED_MAP_ITERATOR;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static ResettableIterator singletonIterator(Object obj) {
        return new SingletonIterator(obj);
    }

    public static ListIterator singletonListIterator(Object obj) {
        return new SingletonListIterator(obj);
    }

    public static ResettableIterator arrayIterator(Object[] objArr) {
        return new ObjectArrayIterator(objArr);
    }

    public static ResettableIterator arrayIterator(Object obj) {
        return new ArrayIterator(obj);
    }

    public static ResettableIterator arrayIterator(Object[] objArr, int i) {
        return new ObjectArrayIterator(objArr, i);
    }

    public static ResettableIterator arrayIterator(Object obj, int i) {
        return new ArrayIterator(obj, i);
    }

    public static ResettableIterator arrayIterator(Object[] objArr, int i, int i2) {
        return new ObjectArrayIterator(objArr, i, i2);
    }

    public static ResettableIterator arrayIterator(Object obj, int i, int i2) {
        return new ArrayIterator(obj, i, i2);
    }

    public static ResettableListIterator arrayListIterator(Object[] objArr) {
        return new ObjectArrayListIterator(objArr);
    }

    public static ResettableListIterator arrayListIterator(Object obj) {
        return new ArrayListIterator(obj);
    }

    public static ResettableListIterator arrayListIterator(Object[] objArr, int i) {
        return new ObjectArrayListIterator(objArr, i);
    }

    public static ResettableListIterator arrayListIterator(Object obj, int i) {
        return new ArrayListIterator(obj, i);
    }

    public static ResettableListIterator arrayListIterator(Object[] objArr, int i, int i2) {
        return new ObjectArrayListIterator(objArr, i, i2);
    }

    public static ResettableListIterator arrayListIterator(Object obj, int i, int i2) {
        return new ArrayListIterator(obj, i, i2);
    }

    public static Iterator unmodifiableIterator(Iterator it2) {
        return UnmodifiableIterator.decorate(it2);
    }

    public static ListIterator unmodifiableListIterator(ListIterator listIterator) {
        return UnmodifiableListIterator.decorate(listIterator);
    }

    public static MapIterator unmodifiableMapIterator(MapIterator mapIterator) {
        return UnmodifiableMapIterator.decorate(mapIterator);
    }

    public static Iterator chainedIterator(Iterator it2, Iterator it3) {
        return new IteratorChain(it2, it3);
    }

    public static Iterator chainedIterator(Iterator[] itArr) {
        return new IteratorChain(itArr);
    }

    public static Iterator chainedIterator(Collection collection) {
        return new IteratorChain(collection);
    }

    public static Iterator collatedIterator(Comparator comparator, Iterator it2, Iterator it3) {
        return new CollatingIterator(comparator, it2, it3);
    }

    public static Iterator collatedIterator(Comparator comparator, Iterator[] itArr) {
        return new CollatingIterator(comparator, itArr);
    }

    public static Iterator collatedIterator(Comparator comparator, Collection collection) {
        return new CollatingIterator(comparator, collection);
    }

    public static Iterator objectGraphIterator(Object obj, Transformer transformer) {
        return new ObjectGraphIterator(obj, transformer);
    }

    public static Iterator transformedIterator(Iterator it2, Transformer transformer) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (transformer == null) {
            throw new NullPointerException("Transformer must not be null");
        }
        return new TransformIterator(it2, transformer);
    }

    public static Iterator filteredIterator(Iterator it2, Predicate predicate) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new FilterIterator(it2, predicate);
    }

    public static ListIterator filteredListIterator(ListIterator listIterator, Predicate predicate) {
        if (listIterator == null) {
            throw new NullPointerException("ListIterator must not be null");
        }
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new FilterListIterator(listIterator, predicate);
    }

    public static ResettableIterator loopingIterator(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        return new LoopingIterator(collection);
    }

    public static ResettableListIterator loopingListIterator(List list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        return new LoopingListIterator(list);
    }

    public static Iterator asIterator(Enumeration enumeration) {
        if (enumeration == null) {
            throw new NullPointerException("Enumeration must not be null");
        }
        return new EnumerationIterator(enumeration);
    }

    public static Iterator asIterator(Enumeration enumeration, Collection collection) {
        if (enumeration == null) {
            throw new NullPointerException("Enumeration must not be null");
        }
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        return new EnumerationIterator(enumeration, collection);
    }

    public static Enumeration asEnumeration(Iterator it2) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        return new IteratorEnumeration(it2);
    }

    public static ListIterator toListIterator(Iterator it2) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        return new ListIteratorWrapper(it2);
    }

    public static Object[] toArray(Iterator it2) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        return toList(it2, 100).toArray();
    }

    public static Object[] toArray(Iterator it2, Class cls) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (cls == null) {
            throw new NullPointerException("Array class must not be null");
        }
        List list = toList(it2, 100);
        return list.toArray((Object[]) Array.newInstance((Class<?>) cls, list.size()));
    }

    public static List toList(Iterator it2) {
        return toList(it2, 10);
    }

    public static List toList(Iterator it2, int i) {
        if (it2 == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (i < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }
        ArrayList arrayList = new ArrayList(i);
        while (it2.hasNext()) {
            arrayList.add(it2.next());
        }
        return arrayList;
    }

    public static Iterator getIterator(Object obj) throws NoSuchMethodException, SecurityException {
        if (obj == null) {
            return emptyIterator();
        }
        if (obj instanceof Iterator) {
            return (Iterator) obj;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).iterator();
        }
        if (obj instanceof Object[]) {
            return new ObjectArrayIterator((Object[]) obj);
        }
        if (obj instanceof Enumeration) {
            return new EnumerationIterator((Enumeration) obj);
        }
        if (obj instanceof Map) {
            return ((Map) obj).values().iterator();
        }
        if (obj instanceof Dictionary) {
            return new EnumerationIterator(((Dictionary) obj).elements());
        }
        if (obj != null && obj.getClass().isArray()) {
            return new ArrayIterator(obj);
        }
        try {
            Method method = obj.getClass().getMethod("iterator", null);
            Class clsClass$ = class$java$util$Iterator;
            if (clsClass$ == null) {
                clsClass$ = class$("java.util.Iterator");
                class$java$util$Iterator = clsClass$;
            }
            if (clsClass$.isAssignableFrom(method.getReturnType())) {
                Iterator it2 = (Iterator) method.invoke(obj, null);
                if (it2 != null) {
                    return it2;
                }
            }
        } catch (Exception unused) {
        }
        return singletonIterator(obj);
    }
}
