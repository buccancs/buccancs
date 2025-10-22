package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.common.primitives.Ints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient AvlNode<E> header;
    private final transient GeneralRange<E> range;
    private final transient Reference<AvlNode<E>> rootReference;

    TreeMultiset(Reference<AvlNode<E>> reference, GeneralRange<E> generalRange, AvlNode<E> avlNode) {
        super(generalRange.comparator());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        AvlNode<E> avlNode = new AvlNode<>(null, 1);
        this.header = avlNode;
        successor(avlNode, avlNode);
        this.rootReference = new Reference<>();
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    public static <E> TreeMultiset<E> create(@NullableDecl Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> treeMultisetCreate = create();
        Iterables.addAll(treeMultisetCreate, iterable);
        return treeMultisetCreate;
    }

    static int distinctElements(@NullableDecl AvlNode<?> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return ((AvlNode) avlNode).distinctElements;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        ((AvlNode) avlNode).succ = avlNode2;
        ((AvlNode) avlNode2).pred = avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        successor(avlNode, avlNode2);
        successor(avlNode2, avlNode3);
    }

    @Override
    // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override
    // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override
    // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(@NullableDecl Object obj, BoundType boundType, @NullableDecl Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode<E> avlNode = this.rootReference.get();
        long jTreeAggregate = aggregate.treeAggregate(avlNode);
        if (this.range.hasLowerBound()) {
            jTreeAggregate -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.hasUpperBound() ? jTreeAggregate - aggregateAboveRange(aggregate, avlNode) : jTreeAggregate;
    }

    private long aggregateBelowRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long jTreeAggregate;
        long jAggregateBelowRange;
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getLowerEndpoint(), ((AvlNode) avlNode).elem);
        if (iCompare < 0) {
            return aggregateBelowRange(aggregate, ((AvlNode) avlNode).left);
        }
        if (iCompare == 0) {
            int i = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.getLowerBoundType().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new AssertionError();
                }
                return aggregate.treeAggregate(((AvlNode) avlNode).left);
            }
            jTreeAggregate = aggregate.nodeAggregate(avlNode);
            jAggregateBelowRange = aggregate.treeAggregate(((AvlNode) avlNode).left);
        } else {
            jTreeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).left) + aggregate.nodeAggregate(avlNode);
            jAggregateBelowRange = aggregateBelowRange(aggregate, ((AvlNode) avlNode).right);
        }
        return jTreeAggregate + jAggregateBelowRange;
    }

    private long aggregateAboveRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long jTreeAggregate;
        long jAggregateAboveRange;
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getUpperEndpoint(), ((AvlNode) avlNode).elem);
        if (iCompare > 0) {
            return aggregateAboveRange(aggregate, ((AvlNode) avlNode).right);
        }
        if (iCompare == 0) {
            int i = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.getUpperBoundType().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new AssertionError();
                }
                return aggregate.treeAggregate(((AvlNode) avlNode).right);
            }
            jTreeAggregate = aggregate.nodeAggregate(avlNode);
            jAggregateAboveRange = aggregate.treeAggregate(((AvlNode) avlNode).right);
        } else {
            jTreeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).right) + aggregate.nodeAggregate(avlNode);
            jAggregateAboveRange = aggregateAboveRange(aggregate, ((AvlNode) avlNode).left);
        }
        return jTreeAggregate + jAggregateAboveRange;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    @Override
        // com.google.common.collect.AbstractMultiset
    int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        try {
            AvlNode<E> avlNode = this.rootReference.get();
            if (this.range.contains(obj) && avlNode != null) {
                return avlNode.count(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int add(@NullableDecl E e, int i) {
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(e);
        }
        Preconditions.checkArgument(this.range.contains(e));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            comparator().compare(e, e);
            AvlNode<E> avlNode2 = new AvlNode<>(e, i);
            AvlNode<E> avlNode3 = this.header;
            successor(avlNode3, avlNode2, avlNode3);
            this.rootReference.checkAndSet(avlNode, avlNode2);
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.add(comparator(), e, i, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int remove(@NullableDecl Object obj, int i) {
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        AvlNode<E> avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.remove(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int setCount(@NullableDecl E e, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        if (!this.range.contains(e)) {
            Preconditions.checkArgument(i == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i > 0) {
                add(e, i);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e, i, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public boolean setCount(@NullableDecl E e, int i, int i2) {
        CollectPreconditions.checkNonnegative(i2, "newCount");
        CollectPreconditions.checkNonnegative(i, "oldCount");
        Preconditions.checkArgument(this.range.contains(e));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e, i, i2, iArr));
            return iArr[0] == i;
        }
        if (i != 0) {
            return false;
        }
        if (i2 > 0) {
            add(e, i2);
        }
        return true;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        if (this.range.hasLowerBound() || this.range.hasUpperBound()) {
            Iterators.clear(entryIterator());
            return;
        }
        AvlNode<E> avlNode = ((AvlNode) this.header).succ;
        while (true) {
            AvlNode<E> avlNode2 = this.header;
            if (avlNode == avlNode2) {
                successor(avlNode2, avlNode2);
                this.rootReference.clear();
                return;
            }
            AvlNode<E> avlNode3 = ((AvlNode) avlNode).succ;
            ((AvlNode) avlNode).elemCount = 0;
            ((AvlNode) avlNode).left = null;
            ((AvlNode) avlNode).right = null;
            ((AvlNode) avlNode).pred = null;
            ((AvlNode) avlNode).succ = null;
            avlNode = avlNode3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.TreeMultiset.1
            @Override // com.google.common.collect.Multiset.Entry
            public E getElement() {
                return (E) avlNode.getElement();
            }

            @Override // com.google.common.collect.Multiset.Entry
            public int getCount() {
                int count = avlNode.getCount();
                return count == 0 ? TreeMultiset.this.count(getElement()) : count;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNodeCeiling;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasLowerBound()) {
            E lowerEndpoint = this.range.getLowerEndpoint();
            avlNodeCeiling = this.rootReference.get().ceiling(comparator(), lowerEndpoint);
            if (avlNodeCeiling == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, avlNodeCeiling.getElement()) == 0) {
                avlNodeCeiling = ((AvlNode) avlNodeCeiling).succ;
            }
        } else {
            avlNodeCeiling = ((AvlNode) this.header).succ;
        }
        if (avlNodeCeiling == this.header || !this.range.contains(avlNodeCeiling.getElement())) {
            return null;
        }
        return avlNodeCeiling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNodeFloor;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasUpperBound()) {
            E upperEndpoint = this.range.getUpperEndpoint();
            avlNodeFloor = this.rootReference.get().floor(comparator(), upperEndpoint);
            if (avlNodeFloor == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, avlNodeFloor.getElement()) == 0) {
                avlNodeFloor = ((AvlNode) avlNodeFloor).pred;
            }
        } else {
            avlNodeFloor = ((AvlNode) this.header).pred;
        }
        if (avlNodeFloor == this.header || !this.range.contains(avlNodeFloor.getElement())) {
            return null;
        }
        return avlNodeFloor;
    }

    @Override
        // com.google.common.collect.AbstractMultiset
    Iterator<E> elementIterator() {
        return Multisets.elementIterator(entryIterator());
    }

    @Override
        // com.google.common.collect.AbstractMultiset
    Iterator<Multiset.Entry<E>> entryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.2
            AvlNode<E> current;

            @NullableDecl
            Multiset.Entry<E> prevEntry;

            {
                this.current = TreeMultiset.this.firstNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooHigh(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                    this.prevEntry = entryWrapEntry;
                    if (((AvlNode) this.current).succ == TreeMultiset.this.header) {
                        this.current = null;
                    } else {
                        this.current = ((AvlNode) this.current).succ;
                    }
                    return entryWrapEntry;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }
        };
    }

    @Override
        // com.google.common.collect.AbstractSortedMultiset
    Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.3
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry = null;

            {
                this.current = TreeMultiset.this.lastNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooLow(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                    this.prevEntry = entryWrapEntry;
                    if (((AvlNode) this.current).pred == TreeMultiset.this.header) {
                        this.current = null;
                    } else {
                        this.current = ((AvlNode) this.current).pred;
                    }
                    return entryWrapEntry;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }
        };
    }

    @Override
    // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(comparator(), e, boundType)), this.header);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(comparator(), e, boundType)), this.header);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.writeMultiset(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set((Serialization.FieldSetter) this, (Object) comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set((Serialization.FieldSetter) this, (Object) GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set((Serialization.FieldSetter) this, (Object) new Reference());
        AvlNode avlNode = new AvlNode(null, 1);
        Serialization.getFieldSetter(TreeMultiset.class, "header").set((Serialization.FieldSetter) this, (Object) avlNode);
        successor(avlNode, avlNode);
        Serialization.populateMultiset(this, objectInputStream);
    }

    private enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1

            @Override
                // com.google.common.collect.TreeMultiset.Aggregate
            int nodeAggregate(AvlNode<?> avlNode) {
                return ((AvlNode) avlNode).elemCount;
            }

            @Override
                // com.google.common.collect.TreeMultiset.Aggregate
            long treeAggregate(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2

            @Override
                // com.google.common.collect.TreeMultiset.Aggregate
            int nodeAggregate(AvlNode<?> avlNode) {
                return 1;
            }

            @Override
                // com.google.common.collect.TreeMultiset.Aggregate
            long treeAggregate(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).distinctElements;
            }
        };

        abstract int nodeAggregate(AvlNode<?> avlNode);

        abstract long treeAggregate(@NullableDecl AvlNode<?> avlNode);
    }

    /* renamed from: com.google.common.collect.TreeMultiset$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static final class Reference<T> {

        @NullableDecl
        private T value;

        private Reference() {
        }

        void clear() {
            this.value = null;
        }

        @NullableDecl
        public T get() {
            return this.value;
        }

        public void checkAndSet(@NullableDecl T t, T t2) {
            if (this.value != t) {
                throw new ConcurrentModificationException();
            }
            this.value = t2;
        }
    }

    private static final class AvlNode<E> {
        @NullableDecl
        private final E elem;
        private int distinctElements;
        private int elemCount;
        private int height;

        @NullableDecl
        private AvlNode<E> left;

        @NullableDecl
        private AvlNode<E> pred;

        @NullableDecl
        private AvlNode<E> right;

        @NullableDecl
        private AvlNode<E> succ;
        private long totalCount;

        AvlNode(@NullableDecl E e, int i) {
            Preconditions.checkArgument(i > 0);
            this.elem = e;
            this.elemCount = i;
            this.totalCount = i;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private static long totalCount(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0L;
            }
            return ((AvlNode) avlNode).totalCount;
        }

        private static int height(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return ((AvlNode) avlNode).height;
        }

        int getCount() {
            return this.elemCount;
        }

        E getElement() {
            return this.elem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e);
            }
            if (iCompare <= 0) {
                return this.elemCount;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return 0;
            }
            return avlNode2.count(comparator, e);
        }

        private AvlNode<E> addRightChild(E e, int i) {
            AvlNode<E> avlNode = new AvlNode<>(e, i);
            this.right = avlNode;
            TreeMultiset.successor(this, avlNode, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i;
            return this;
        }

        private AvlNode<E> addLeftChild(E e, int i) {
            AvlNode<E> avlNode = new AvlNode<>(e, i);
            this.left = avlNode;
            TreeMultiset.successor(this.pred, avlNode, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> add(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return addLeftChild(e, i);
                }
                int i2 = avlNode.height;
                AvlNode<E> avlNodeAdd = avlNode.add(comparator, e, i, iArr);
                this.left = avlNodeAdd;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i;
                return avlNodeAdd.height == i2 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i3 = this.elemCount;
                iArr[0] = i3;
                long j = i;
                Preconditions.checkArgument(((long) i3) + j <= 2147483647L);
                this.elemCount += i;
                this.totalCount += j;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return addRightChild(e, i);
            }
            int i4 = avlNode2.height;
            AvlNode<E> avlNodeAdd2 = avlNode2.add(comparator, e, i, iArr);
            this.right = avlNodeAdd2;
            if (iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += i;
            return avlNodeAdd2.height == i4 ? this : rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> remove(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.remove(comparator, e, i, iArr);
                int i2 = iArr[0];
                if (i2 > 0) {
                    if (i >= i2) {
                        this.distinctElements--;
                        this.totalCount -= i2;
                    } else {
                        this.totalCount -= i;
                    }
                }
                return i2 == 0 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i3 = this.elemCount;
                iArr[0] = i3;
                if (i >= i3) {
                    return deleteMe();
                }
                this.elemCount = i3 - i;
                this.totalCount -= i;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return this;
            }
            this.right = avlNode2.remove(comparator, e, i, iArr);
            int i4 = iArr[0];
            if (i4 > 0) {
                if (i >= i4) {
                    this.distinctElements--;
                    this.totalCount -= i4;
                } else {
                    this.totalCount -= i;
                }
            }
            return rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> setCount(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return i > 0 ? addLeftChild(e, i) : this;
                }
                this.left = avlNode.setCount(comparator, e, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.distinctElements--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i - iArr[0];
                return rebalance();
            }
            if (iCompare <= 0) {
                iArr[0] = this.elemCount;
                if (i == 0) {
                    return deleteMe();
                }
                this.totalCount += i - r3;
                this.elemCount = i;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return i > 0 ? addRightChild(e, i) : this;
            }
            this.right = avlNode2.setCount(comparator, e, i, iArr);
            if (i == 0 && iArr[0] != 0) {
                this.distinctElements--;
            } else if (i > 0 && iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += i - iArr[0];
            return rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> setCount(Comparator<? super E> comparator, @NullableDecl E e, int i, int i2, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return (i != 0 || i2 <= 0) ? this : addLeftChild(e, i2);
                }
                this.left = avlNode.setCount(comparator, e, i, i2, iArr);
                int i3 = iArr[0];
                if (i3 == i) {
                    if (i2 == 0 && i3 != 0) {
                        this.distinctElements--;
                    } else if (i2 > 0 && i3 == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += i2 - i3;
                }
                return rebalance();
            }
            if (iCompare <= 0) {
                int i4 = this.elemCount;
                iArr[0] = i4;
                if (i == i4) {
                    if (i2 == 0) {
                        return deleteMe();
                    }
                    this.totalCount += i2 - i4;
                    this.elemCount = i2;
                }
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return (i != 0 || i2 <= 0) ? this : addRightChild(e, i2);
            }
            this.right = avlNode2.setCount(comparator, e, i, i2, iArr);
            int i5 = iArr[0];
            if (i5 == i) {
                if (i2 == 0 && i5 != 0) {
                    this.distinctElements--;
                } else if (i2 > 0 && i5 == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2 - i5;
            }
            return rebalance();
        }

        private AvlNode<E> deleteMe() {
            int i = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - i;
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - i;
            return avlNode4.rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.distinctElements(this.left) + 1 + TreeMultiset.distinctElements(this.right);
            this.totalCount = this.elemCount + totalCount(this.left) + totalCount(this.right);
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private AvlNode<E> rebalance() {
            int iBalanceFactor = balanceFactor();
            if (iBalanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            }
            if (iBalanceFactor == 2) {
                if (this.left.balanceFactor() < 0) {
                    this.left = this.left.rotateLeft();
                }
                return rotateRight();
            }
            recomputeHeight();
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.ceiling(comparator, e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> floor(Comparator<? super E> comparator, E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.floor(comparator, e);
        }

        public String toString() {
            return Multisets.immutableEntry(getElement(), getCount()).toString();
        }
    }
}
