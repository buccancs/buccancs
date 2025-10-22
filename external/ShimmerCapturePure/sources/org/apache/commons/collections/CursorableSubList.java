package org.apache.commons.collections;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.CursorableLinkedList;

/* compiled from: CursorableLinkedList.java */
/* loaded from: classes5.dex */
class CursorableSubList extends CursorableLinkedList implements List {
    protected CursorableLinkedList _list;
    protected CursorableLinkedList.Listable _post;
    protected CursorableLinkedList.Listable _pre;

    CursorableSubList(CursorableLinkedList cursorableLinkedList, int i, int i2) {
        this._list = null;
        this._pre = null;
        this._post = null;
        if (i < 0 || cursorableLinkedList.size() < i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        this._list = cursorableLinkedList;
        if (i < cursorableLinkedList.size()) {
            this._head.setNext(this._list.getListableAt(i));
            this._pre = this._head.next() == null ? null : this._head.next().prev();
        } else {
            this._pre = this._list.getListableAt(i - 1);
        }
        if (i == i2) {
            this._head.setNext(null);
            this._head.setPrev(null);
            if (i2 < cursorableLinkedList.size()) {
                this._post = this._list.getListableAt(i2);
            } else {
                this._post = null;
            }
        } else {
            this._head.setPrev(this._list.getListableAt(i2 - 1));
            this._post = this._head.prev().next();
        }
        this._size = i2 - i;
        this._modCount = this._list._modCount;
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public void clear() throws ConcurrentModificationException {
        checkForComod();
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            it2.next();
            it2.remove();
        }
    }

    @Override
    // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() throws ConcurrentModificationException {
        checkForComod();
        return super.iterator();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public int size() throws ConcurrentModificationException {
        checkForComod();
        return super.size();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean isEmpty() throws ConcurrentModificationException {
        checkForComod();
        return super.isEmpty();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public Object[] toArray() throws ConcurrentModificationException {
        checkForComod();
        return super.toArray();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) throws ConcurrentModificationException {
        checkForComod();
        return super.toArray(objArr);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean contains(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.contains(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean remove(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.remove(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public Object removeFirst() throws ConcurrentModificationException {
        checkForComod();
        return super.removeFirst();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public Object removeLast() throws ConcurrentModificationException {
        checkForComod();
        return super.removeLast();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean addAll(Collection collection) throws ConcurrentModificationException {
        checkForComod();
        return super.addAll(collection);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean add(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.add(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public boolean addFirst(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.addFirst(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public boolean addLast(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.addLast(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) throws ConcurrentModificationException {
        checkForComod();
        return super.removeAll(collection);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) throws ConcurrentModificationException {
        checkForComod();
        return super.containsAll(collection);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public boolean addAll(int i, Collection collection) throws ConcurrentModificationException {
        checkForComod();
        return super.addAll(i, collection);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public int hashCode() throws ConcurrentModificationException {
        checkForComod();
        return super.hashCode();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) throws ConcurrentModificationException {
        checkForComod();
        return super.retainAll(collection);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public Object set(int i, Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.set(i, obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List, java.util.Collection
    public boolean equals(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.equals(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public Object get(int i) throws ConcurrentModificationException {
        checkForComod();
        return super.get(i);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public Object getFirst() throws ConcurrentModificationException {
        checkForComod();
        return super.getFirst();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    public Object getLast() throws ConcurrentModificationException {
        checkForComod();
        return super.getLast();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public void add(int i, Object obj) throws ConcurrentModificationException {
        checkForComod();
        super.add(i, obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public ListIterator listIterator(int i) throws ConcurrentModificationException {
        checkForComod();
        return super.listIterator(i);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public Object remove(int i) throws ConcurrentModificationException {
        checkForComod();
        return super.remove(i);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public int indexOf(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.indexOf(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public int lastIndexOf(Object obj) throws ConcurrentModificationException {
        checkForComod();
        return super.lastIndexOf(obj);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public ListIterator listIterator() throws ConcurrentModificationException {
        checkForComod();
        return super.listIterator();
    }

    @Override // org.apache.commons.collections.CursorableLinkedList, java.util.List
    public List subList(int i, int i2) throws ConcurrentModificationException {
        checkForComod();
        return super.subList(i, i2);
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    protected CursorableLinkedList.Listable insertListable(CursorableLinkedList.Listable listable, CursorableLinkedList.Listable listable2, Object obj) {
        this._modCount++;
        this._size++;
        CursorableLinkedList.Listable listableInsertListable = this._list.insertListable(listable == null ? this._pre : listable, listable2 == null ? this._post : listable2, obj);
        if (this._head.next() == null) {
            this._head.setNext(listableInsertListable);
            this._head.setPrev(listableInsertListable);
        }
        if (listable == this._head.prev()) {
            this._head.setPrev(listableInsertListable);
        }
        if (listable2 == this._head.next()) {
            this._head.setNext(listableInsertListable);
        }
        broadcastListableInserted(listableInsertListable);
        return listableInsertListable;
    }

    @Override // org.apache.commons.collections.CursorableLinkedList
    protected void removeListable(CursorableLinkedList.Listable listable) {
        this._modCount++;
        this._size--;
        if (this._head.next() == listable && this._head.prev() == listable) {
            this._head.setNext(null);
            this._head.setPrev(null);
        }
        if (this._head.next() == listable) {
            this._head.setNext(listable.next());
        }
        if (this._head.prev() == listable) {
            this._head.setPrev(listable.prev());
        }
        this._list.removeListable(listable);
        broadcastListableRemoved(listable);
    }

    protected void checkForComod() throws ConcurrentModificationException {
        if (this._modCount != this._list._modCount) {
            throw new ConcurrentModificationException();
        }
    }
}
