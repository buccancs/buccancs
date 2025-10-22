package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
abstract class Dispatcher {
    Dispatcher() {
    }

    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    abstract void dispatch(Object obj, Iterator<Subscriber> it2);

    private static final class PerThreadQueuedDispatcher extends Dispatcher {
        private final ThreadLocal<Boolean> dispatching;
        private final ThreadLocal<Queue<Event>> queue;

        private PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>() { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.ThreadLocal
                public Queue<Event> initialValue() {
                    return Queues.newArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>() { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.2
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.lang.ThreadLocal
                public Boolean initialValue() {
                    return false;
                }
            };
        }

        @Override
            // com.google.common.eventbus.Dispatcher
        void dispatch(Object obj, Iterator<Subscriber> it2) {
            Preconditions.checkNotNull(obj);
            Preconditions.checkNotNull(it2);
            Queue<Event> queue = this.queue.get();
            queue.offer(new Event(obj, it2));
            if (this.dispatching.get().booleanValue()) {
                return;
            }
            this.dispatching.set(true);
            while (true) {
                try {
                    Event eventPoll = queue.poll();
                    if (eventPoll == null) {
                        return;
                    }
                    while (eventPoll.subscribers.hasNext()) {
                        ((Subscriber) eventPoll.subscribers.next()).dispatchEvent(eventPoll.event);
                    }
                } finally {
                    this.dispatching.remove();
                    this.queue.remove();
                }
            }
        }

        private static final class Event {
            private final Object event;
            private final Iterator<Subscriber> subscribers;

            private Event(Object obj, Iterator<Subscriber> it2) {
                this.event = obj;
                this.subscribers = it2;
            }
        }
    }

    private static final class LegacyAsyncDispatcher extends Dispatcher {
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        private LegacyAsyncDispatcher() {
            this.queue = Queues.newConcurrentLinkedQueue();
        }

        @Override
            // com.google.common.eventbus.Dispatcher
        void dispatch(Object obj, Iterator<Subscriber> it2) {
            Preconditions.checkNotNull(obj);
            while (it2.hasNext()) {
                this.queue.add(new EventWithSubscriber(obj, it2.next()));
            }
            while (true) {
                EventWithSubscriber eventWithSubscriberPoll = this.queue.poll();
                if (eventWithSubscriberPoll == null) {
                    return;
                } else {
                    eventWithSubscriberPoll.subscriber.dispatchEvent(eventWithSubscriberPoll.event);
                }
            }
        }

        private static final class EventWithSubscriber {
            private final Object event;
            private final Subscriber subscriber;

            private EventWithSubscriber(Object obj, Subscriber subscriber) {
                this.event = obj;
                this.subscriber = subscriber;
            }
        }
    }

    private static final class ImmediateDispatcher extends Dispatcher {
        private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        private ImmediateDispatcher() {
        }

        @Override
            // com.google.common.eventbus.Dispatcher
        void dispatch(Object obj, Iterator<Subscriber> it2) {
            Preconditions.checkNotNull(obj);
            while (it2.hasNext()) {
                it2.next().dispatchEvent(obj);
            }
        }
    }
}
