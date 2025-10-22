package io.opencensus.proto.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.trace.v1.StackTrace;

/* loaded from: classes4.dex */
public interface StackTraceOrBuilder extends MessageOrBuilder {
    StackTrace.StackFrames getStackFrames();

    StackTrace.StackFramesOrBuilder getStackFramesOrBuilder();

    long getStackTraceHashId();

    boolean hasStackFrames();
}
