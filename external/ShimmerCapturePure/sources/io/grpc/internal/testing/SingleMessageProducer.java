package io.grpc.internal.testing;

import io.grpc.internal.StreamListener;

import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class SingleMessageProducer implements StreamListener.MessageProducer {
    private InputStream message;

    public SingleMessageProducer(InputStream inputStream) {
        this.message = inputStream;
    }

    @Override // io.grpc.internal.StreamListener.MessageProducer
    @Nullable
    public InputStream next() {
        InputStream inputStream = this.message;
        this.message = null;
        return inputStream;
    }
}
