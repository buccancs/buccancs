package io.grpc.internal;

import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface StreamListener {

    void messagesAvailable(MessageProducer messageProducer);

    void onReady();

    public interface MessageProducer {
        @Nullable
        InputStream next();
    }
}
