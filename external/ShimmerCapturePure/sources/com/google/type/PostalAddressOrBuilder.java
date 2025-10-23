package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface PostalAddressOrBuilder extends MessageOrBuilder {
    String getAddressLines(int i);

    ByteString getAddressLinesBytes(int i);

    int getAddressLinesCount();

    /* renamed from: getAddressLinesList */
    List<String> mo4316getAddressLinesList();

    String getAdministrativeArea();

    ByteString getAdministrativeAreaBytes();

    String getLanguageCode();

    ByteString getLanguageCodeBytes();

    String getLocality();

    ByteString getLocalityBytes();

    String getOrganization();

    ByteString getOrganizationBytes();

    String getPostalCode();

    ByteString getPostalCodeBytes();

    String getRecipients(int i);

    ByteString getRecipientsBytes(int i);

    int getRecipientsCount();

    /* renamed from: getRecipientsList */
    List<String> mo4319getRecipientsList();

    String getRegionCode();

    ByteString getRegionCodeBytes();

    int getRevision();

    String getSortingCode();

    ByteString getSortingCodeBytes();

    String getSublocality();

    ByteString getSublocalityBytes();
}
