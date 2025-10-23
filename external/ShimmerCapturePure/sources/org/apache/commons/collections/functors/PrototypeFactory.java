package org.apache.commons.collections.functors;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.FunctorException;

/* loaded from: classes5.dex */
public class PrototypeFactory {

    private PrototypeFactory() {
    }

    public static Factory getInstance(Object obj) throws NoSuchMethodException, SecurityException {
        if (obj == null) {
            return ConstantFactory.NULL_INSTANCE;
        }
        AnonymousClass1 anonymousClass1 = null;
        try {
            try {
                return new PrototypeCloneFactory(obj, obj.getClass().getMethod("clone", null), anonymousClass1);
            } catch (NoSuchMethodException unused) {
                obj.getClass().getConstructor(obj.getClass());
                return new InstantiateFactory(obj.getClass(), new Class[]{obj.getClass()}, new Object[]{obj});
            }
        } catch (NoSuchMethodException unused2) {
            if (obj instanceof Serializable) {
                return new PrototypeSerializationFactory((Serializable) obj, anonymousClass1);
            }
            throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
        }
    }

    static class PrototypeCloneFactory implements Factory, Serializable {
        private static final long serialVersionUID = 5604271422565175555L;
        private final Object iPrototype;
        private transient Method iCloneMethod;

        /* synthetic */ PrototypeCloneFactory(Object obj, Method method, AnonymousClass1 anonymousClass1) {
            this(obj, method);
        }

        private PrototypeCloneFactory(Object obj, Method method) {
            this.iPrototype = obj;
            this.iCloneMethod = method;
        }

        private void findCloneMethod() {
            try {
                this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", null);
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
            }
        }

        @Override // org.apache.commons.collections.Factory
        public Object create() {
            if (this.iCloneMethod == null) {
                findCloneMethod();
            }
            try {
                return this.iCloneMethod.invoke(this.iPrototype, null);
            } catch (IllegalAccessException e) {
                throw new FunctorException("PrototypeCloneFactory: Clone method must be public", e);
            } catch (InvocationTargetException e2) {
                throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", e2);
            }
        }
    }

    static class PrototypeSerializationFactory implements Factory, Serializable {
        private static final long serialVersionUID = -8704966966139178833L;
        private final Serializable iPrototype;

        /* synthetic */ PrototypeSerializationFactory(Serializable serializable, AnonymousClass1 anonymousClass1) {
            this(serializable);
        }

        private PrototypeSerializationFactory(Serializable serializable) {
            this.iPrototype = serializable;
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // org.apache.commons.collections.Factory
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object create() throws java.lang.Throwable {
            /*
                r5 = this;
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
                r1 = 512(0x200, float:7.17E-43)
                r0.<init>(r1)
                r1 = 0
                java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                r2.<init>(r0)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                java.io.Serializable r3 = r5.iPrototype     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                r2.writeObject(r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                byte[] r3 = r0.toByteArray()     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L34 java.lang.ClassNotFoundException -> L3e
                java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.io.IOException -> L2b java.lang.ClassNotFoundException -> L2d java.lang.Throwable -> L48
                r1.<init>(r2)     // Catch: java.io.IOException -> L2b java.lang.ClassNotFoundException -> L2d java.lang.Throwable -> L48
                java.lang.Object r1 = r1.readObject()     // Catch: java.io.IOException -> L2b java.lang.ClassNotFoundException -> L2d java.lang.Throwable -> L48
                r2.close()     // Catch: java.io.IOException -> L27
            L27:
                r0.close()     // Catch: java.io.IOException -> L2a
            L2a:
                return r1
            L2b:
                r1 = move-exception
                goto L38
            L2d:
                r1 = move-exception
                goto L42
            L2f:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
                goto L49
            L34:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
            L38:
                org.apache.commons.collections.FunctorException r3 = new org.apache.commons.collections.FunctorException     // Catch: java.lang.Throwable -> L48
                r3.<init>(r1)     // Catch: java.lang.Throwable -> L48
                throw r3     // Catch: java.lang.Throwable -> L48
            L3e:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
            L42:
                org.apache.commons.collections.FunctorException r3 = new org.apache.commons.collections.FunctorException     // Catch: java.lang.Throwable -> L48
                r3.<init>(r1)     // Catch: java.lang.Throwable -> L48
                throw r3     // Catch: java.lang.Throwable -> L48
            L48:
                r1 = move-exception
            L49:
                if (r2 == 0) goto L4e
                r2.close()     // Catch: java.io.IOException -> L4e
            L4e:
                r0.close()     // Catch: java.io.IOException -> L51
            L51:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.functors.PrototypeFactory.PrototypeSerializationFactory.create():java.lang.Object");
        }
    }

    /* renamed from: org.apache.commons.collections.functors.PrototypeFactory$1, reason: invalid class name */
    class AnonymousClass1 {
    }
}
