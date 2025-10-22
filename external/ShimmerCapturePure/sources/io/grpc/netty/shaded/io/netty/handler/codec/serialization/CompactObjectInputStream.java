package io.grpc.netty.shaded.io.netty.handler.codec.serialization;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;

/* loaded from: classes3.dex */
class CompactObjectInputStream extends ObjectInputStream {
    private final ClassResolver classResolver;

    CompactObjectInputStream(InputStream inputStream, ClassResolver classResolver) throws IOException {
        super(inputStream);
        this.classResolver = classResolver;
    }

    @Override // java.io.ObjectInputStream
    protected void readStreamHeader() throws IOException {
        int i = readByte() & 255;
        if (i == 5) {
            return;
        }
        throw new StreamCorruptedException("Unsupported version: " + i);
    }

    @Override // java.io.ObjectInputStream
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        int i = read();
        if (i < 0) {
            throw new EOFException();
        }
        if (i == 0) {
            return super.readClassDescriptor();
        }
        if (i == 1) {
            return ObjectStreamClass.lookupAny(this.classResolver.resolve(readUTF()));
        }
        throw new StreamCorruptedException("Unexpected class descriptor type: " + i);
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        try {
            return this.classResolver.resolve(objectStreamClass.getName());
        } catch (ClassNotFoundException unused) {
            return super.resolveClass(objectStreamClass);
        }
    }
}
