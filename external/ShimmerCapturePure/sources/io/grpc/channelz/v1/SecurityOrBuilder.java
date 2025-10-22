package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.channelz.v1.Security;

/* loaded from: classes2.dex */
public interface SecurityOrBuilder extends MessageOrBuilder {
    Security.ModelCase getModelCase();

    Security.OtherSecurity getOther();

    Security.OtherSecurityOrBuilder getOtherOrBuilder();

    Security.Tls getTls();

    Security.TlsOrBuilder getTlsOrBuilder();

    boolean hasOther();

    boolean hasTls();
}
