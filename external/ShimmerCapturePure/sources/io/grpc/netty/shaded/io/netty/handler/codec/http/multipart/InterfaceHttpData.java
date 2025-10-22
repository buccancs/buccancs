package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;

/* loaded from: classes3.dex */
public interface InterfaceHttpData extends Comparable<InterfaceHttpData>, ReferenceCounted {

    HttpDataType getHttpDataType();

    String getName();

    InterfaceHttpData retain();

    InterfaceHttpData retain(int i);

    InterfaceHttpData touch();

    InterfaceHttpData touch(Object obj);

    public enum HttpDataType {
        Attribute,
        FileUpload,
        InternalAttribute
    }
}
