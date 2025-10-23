package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes.dex */
public interface AuthProviderOrBuilder extends MessageOrBuilder {
    String getAudiences();

    ByteString getAudiencesBytes();

    String getAuthorizationUrl();

    ByteString getAuthorizationUrlBytes();

    String getId();

    ByteString getIdBytes();

    String getIssuer();

    ByteString getIssuerBytes();

    String getJwksUri();

    ByteString getJwksUriBytes();
}
