package io.grpc.alts;

import java.net.SocketOption;
import java.nio.channels.MembershipKey;
import java.nio.channels.NetworkChannel;
import java.security.AlgorithmConstraints;
import java.security.cert.CertificateRevokedException;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.X509ExtendedTrustManager;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Class m() {
        return SNIHostName.class;
    }

    public static /* bridge */ /* synthetic */ SocketOption m(Object obj) {
        return (SocketOption) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MembershipKey m6291m(Object obj) {
        return (MembershipKey) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NetworkChannel m6292m(Object obj) {
        return (NetworkChannel) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AlgorithmConstraints m6299m(Object obj) {
        return (AlgorithmConstraints) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ BiFunction m6308m(Object obj) {
        return (BiFunction) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Predicate m6309m(Object obj) {
        return (Predicate) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIHostName m6310m(Object obj) {
        return (SNIHostName) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ SNIHostName m6311m(String str) {
        return new SNIHostName(str);
    }

    public static /* synthetic */ SNIHostName m(byte[] bArr) {
        return new SNIHostName(bArr);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIMatcher m6312m(Object obj) {
        return (SNIMatcher) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIServerName m6313m(Object obj) {
        return (SNIServerName) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ X509ExtendedTrustManager m6314m(Object obj) {
        return (X509ExtendedTrustManager) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m6317m(Object obj) {
        return obj instanceof SNIHostName;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return BiFunction.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof X509ExtendedTrustManager;
    }

    public static /* bridge */ /* synthetic */ boolean m$2(Object obj) {
        return obj instanceof CertificateRevokedException;
    }
}
