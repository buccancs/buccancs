package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
class HpackHeaderField {
    static final int HEADER_ENTRY_OVERHEAD = 32;
    final CharSequence name;
    final CharSequence value;

    HpackHeaderField(CharSequence charSequence, CharSequence charSequence2) {
        this.name = (CharSequence) ObjectUtil.checkNotNull(charSequence, "name");
        this.value = (CharSequence) ObjectUtil.checkNotNull(charSequence2, "value");
    }

    static long sizeOf(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence.length() + charSequence2.length() + 32;
    }

    final int size() {
        return this.name.length() + this.value.length() + 32;
    }

    public final boolean equalsForTest(HpackHeaderField hpackHeaderField) {
        return HpackUtil.equalsVariableTime(this.name, hpackHeaderField.name) && HpackUtil.equalsVariableTime(this.value, hpackHeaderField.value);
    }

    public String toString() {
        return ((Object) this.name) + ": " + ((Object) this.value);
    }
}
