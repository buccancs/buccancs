package androidx.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.Person;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.telephony.SubscriptionManager;
import android.view.inspector.InspectionCompanion;
import android.widget.ThemedSpinnerAdapter;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentDialog$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Notification.MessagingStyle m(Object obj) {
        return (Notification.MessagingStyle) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannel m5m(Object obj) {
        return (NotificationChannel) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m6m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Person m7m(Object obj) {
        return (Person) obj;
    }

    public static /* synthetic */ JobWorkItem m(Intent intent) {
        return new JobWorkItem(intent);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ShortcutInfo.Builder m8m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ShortcutInfo m9m(Object obj) {
        return (ShortcutInfo) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Icon m19m(Object obj) {
        return (Icon) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ InspectionCompanion.UninitializedPropertyMapException m22m() {
        return new InspectionCompanion.UninitializedPropertyMapException();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ThemedSpinnerAdapter m23m(Object obj) {
        return (ThemedSpinnerAdapter) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedCallback m24m(Object obj) {
        return (OnBackInvokedCallback) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedDispatcher m25m(Object obj) {
        return (OnBackInvokedDispatcher) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m26m() {
        return Notification.MessagingStyle.class;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m29m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m33m(Object obj) {
        return obj instanceof ThemedSpinnerAdapter;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof Icon;
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return SubscriptionManager.class;
    }
}
