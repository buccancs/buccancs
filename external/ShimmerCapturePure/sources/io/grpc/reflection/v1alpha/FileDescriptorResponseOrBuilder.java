package io.grpc.reflection.v1alpha;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface FileDescriptorResponseOrBuilder extends MessageOrBuilder {
    ByteString getFileDescriptorProto(int i);

    int getFileDescriptorProtoCount();

    List<ByteString> getFileDescriptorProtoList();
}
