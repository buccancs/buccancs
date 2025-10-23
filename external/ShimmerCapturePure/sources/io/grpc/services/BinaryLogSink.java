package io.grpc.services;

import com.google.protobuf.MessageLite;

import java.io.Closeable;

/* loaded from: classes3.dex */
public interface BinaryLogSink extends Closeable {
    void write(MessageLite messageLite);
}
