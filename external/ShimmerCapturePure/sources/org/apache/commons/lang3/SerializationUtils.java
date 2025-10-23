package org.apache.commons.lang3;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class SerializationUtils {
    public static <T extends Serializable> T clone(T t) throws Throwable {
        ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream;
        ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream2 = null;
        if (t == null) {
            return null;
        }
        try {
            try {
                classLoaderAwareObjectInputStream = new ClassLoaderAwareObjectInputStream(new ByteArrayInputStream(serialize(t)), t.getClass().getClassLoader());
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (ClassNotFoundException e2) {
            e = e2;
        }
        try {
            T t2 = (T) classLoaderAwareObjectInputStream.readObject();
            try {
                classLoaderAwareObjectInputStream.close();
                return t2;
            } catch (IOException e3) {
                throw new SerializationException("IOException on closing cloned object data InputStream.", e3);
            }
        } catch (IOException e4) {
            e = e4;
            throw new SerializationException("IOException while reading cloned object data", e);
        } catch (ClassNotFoundException e5) {
            e = e5;
            throw new SerializationException("ClassNotFoundException while reading cloned object data", e);
        } catch (Throwable th2) {
            th = th2;
            classLoaderAwareObjectInputStream2 = classLoaderAwareObjectInputStream;
            if (classLoaderAwareObjectInputStream2 != null) {
                try {
                    classLoaderAwareObjectInputStream2.close();
                } catch (IOException e6) {
                    throw new SerializationException("IOException on closing cloned object data InputStream.", e6);
                }
            }
            throw th;
        }
    }

    public static <T extends Serializable> T roundtrip(T t) {
        return (T) deserialize(serialize(t));
    }

    public static void serialize(Serializable serializable, OutputStream outputStream) throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(serializable);
            try {
                objectOutputStream.close();
            } catch (IOException unused) {
            }
        } catch (IOException e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            throw new SerializationException(e);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static byte[] serialize(Serializable serializable) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserialize(InputStream inputStream) throws Throwable {
        ObjectInputStream objectInputStream;
        if (inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (ClassNotFoundException e2) {
            e = e2;
        }
        try {
            T t = (T) objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (IOException unused) {
            }
            return t;
        } catch (IOException e3) {
            e = e3;
            throw new SerializationException(e);
        } catch (ClassNotFoundException e4) {
            e = e4;
            throw new SerializationException(e);
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static <T> T deserialize(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The byte[] must not be null");
        }
        return (T) deserialize(new ByteArrayInputStream(bArr));
    }

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes;

        static {
            HashMap map = new HashMap();
            primitiveTypes = map;
            map.put("byte", Byte.TYPE);
            map.put("short", Short.TYPE);
            map.put("int", Integer.TYPE);
            map.put("long", Long.TYPE);
            map.put(TypedValues.Custom.S_FLOAT, Float.TYPE);
            map.put("double", Double.TYPE);
            map.put(TypedValues.Custom.S_BOOLEAN, Boolean.TYPE);
            map.put("char", Character.TYPE);
            map.put("void", Void.TYPE);
        }

        private final ClassLoader classLoader;

        public ClassLoaderAwareObjectInputStream(InputStream inputStream, ClassLoader classLoader) throws IOException {
            super(inputStream);
            this.classLoader = classLoader;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException, IOException {
            String name = objectStreamClass.getName();
            try {
                try {
                    return Class.forName(name, false, this.classLoader);
                } catch (ClassNotFoundException e) {
                    Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw e;
                }
            } catch (ClassNotFoundException unused) {
                return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
            }
        }
    }
}
