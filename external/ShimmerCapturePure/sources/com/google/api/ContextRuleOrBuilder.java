package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes.dex */
public interface ContextRuleOrBuilder extends MessageOrBuilder {
    String getAllowedRequestExtensions(int i);

    ByteString getAllowedRequestExtensionsBytes(int i);

    int getAllowedRequestExtensionsCount();

    /* renamed from: getAllowedRequestExtensionsList */
    List<String> mo705getAllowedRequestExtensionsList();

    String getAllowedResponseExtensions(int i);

    ByteString getAllowedResponseExtensionsBytes(int i);

    int getAllowedResponseExtensionsCount();

    /* renamed from: getAllowedResponseExtensionsList */
    List<String> mo706getAllowedResponseExtensionsList();

    String getProvided(int i);

    ByteString getProvidedBytes(int i);

    int getProvidedCount();

    /* renamed from: getProvidedList */
    List<String> mo709getProvidedList();

    String getRequested(int i);

    ByteString getRequestedBytes(int i);

    int getRequestedCount();

    /* renamed from: getRequestedList */
    List<String> mo710getRequestedList();

    String getSelector();

    ByteString getSelectorBytes();
}
