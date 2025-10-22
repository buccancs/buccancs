package io.grpc.netty.shaded.io.netty.handler.ipfilter;

import java.net.InetSocketAddress;

/* loaded from: classes3.dex */
public interface IpFilterRule {
    boolean matches(InetSocketAddress inetSocketAddress);

    IpFilterRuleType ruleType();
}
