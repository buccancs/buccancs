package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AbstractChannel.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.AbstractChannel", f = "AbstractChannel.kt", i = {}, l = {633}, m = "receiveCatching-JP2dKIU", n = {}, s = {})
/* loaded from: classes4.dex */
final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {
    final /* synthetic */ AbstractChannel<E> this$0;
    int label;
    /* synthetic */ Object result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractChannel$receiveCatching$1(AbstractChannel<E> abstractChannel, Continuation<? super AbstractChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo40321receiveCatchingJP2dKIU = this.this$0.mo40321receiveCatchingJP2dKIU(this);
        return objMo40321receiveCatchingJP2dKIU == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo40321receiveCatchingJP2dKIU : ChannelResult.m40328boximpl(objMo40321receiveCatchingJP2dKIU);
    }
}
