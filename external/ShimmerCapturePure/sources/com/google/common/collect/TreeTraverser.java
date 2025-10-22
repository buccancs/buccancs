package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

@Deprecated
/* loaded from: classes.dex */
public abstract class TreeTraverser<T> {
    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> function) {
        Preconditions.checkNotNull(function);
        return new TreeTraverser<T>() { // from class: com.google.common.collect.TreeTraverser.1
            @Override // com.google.common.collect.TreeTraverser
            public Iterable<T> children(T t) {
                return (Iterable) function.apply(t);
            }
        };
    }

    public abstract Iterable<T> children(T t);

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.preOrderIterator(t);
            }
        };
    }

    UnmodifiableIterator<T> preOrderIterator(T t) {
        return new PreOrderIterator(t);
    }

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.postOrderIterator(t);
            }
        };
    }

    UnmodifiableIterator<T> postOrderIterator(T t) {
        return new PostOrderIterator(t);
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.4
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(t);
            }
        };
    }

    private static final class PostOrderNode<T> {
        final Iterator<T> childIterator;
        final T root;

        PostOrderNode(T t, Iterator<T> it2) {
            this.root = (T) Preconditions.checkNotNull(t);
            this.childIterator = (Iterator) Preconditions.checkNotNull(it2);
        }
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> {
        private final Deque<Iterator<T>> stack;

        PreOrderIterator(T t) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.stack = arrayDeque;
            arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(t)));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            Iterator<T> last = this.stack.getLast();
            T t = (T) Preconditions.checkNotNull(last.next());
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator<T> it2 = TreeTraverser.this.children(t).iterator();
            if (it2.hasNext()) {
                this.stack.addLast(it2);
            }
            return t;
        }
    }

    private final class PostOrderIterator extends AbstractIterator<T> {
        private final ArrayDeque<PostOrderNode<T>> stack;

        PostOrderIterator(T t) {
            ArrayDeque<PostOrderNode<T>> arrayDeque = new ArrayDeque<>();
            this.stack = arrayDeque;
            arrayDeque.addLast(expand(t));
        }

        @Override // com.google.common.collect.AbstractIterator
        protected T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode<T> last = this.stack.getLast();
                if (last.childIterator.hasNext()) {
                    this.stack.addLast(expand(last.childIterator.next()));
                } else {
                    this.stack.removeLast();
                    return last.root;
                }
            }
            return endOfData();
        }

        private PostOrderNode<T> expand(T t) {
            return new PostOrderNode<>(t, TreeTraverser.this.children(t).iterator());
        }
    }

    private final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Queue<T> queue;

        BreadthFirstIterator(T t) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.queue = arrayDeque;
            arrayDeque.add(t);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.queue.element();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T tRemove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(tRemove));
            return tRemove;
        }
    }
}
