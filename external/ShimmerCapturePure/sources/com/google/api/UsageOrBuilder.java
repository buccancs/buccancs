package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes.dex */
public interface UsageOrBuilder extends MessageOrBuilder {
    String getProducerNotificationChannel();

    ByteString getProducerNotificationChannelBytes();

    String getRequirements(int i);

    ByteString getRequirementsBytes(int i);

    int getRequirementsCount();

    /* renamed from: getRequirementsList */
    List<String> mo2655getRequirementsList();

    UsageRule getRules(int i);

    int getRulesCount();

    List<UsageRule> getRulesList();

    UsageRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends UsageRuleOrBuilder> getRulesOrBuilderList();
}
