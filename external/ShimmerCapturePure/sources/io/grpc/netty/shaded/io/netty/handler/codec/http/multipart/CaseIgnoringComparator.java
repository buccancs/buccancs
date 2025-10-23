package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes3.dex */
final class CaseIgnoringComparator implements Comparator<CharSequence>, Serializable {
    static final CaseIgnoringComparator INSTANCE = new CaseIgnoringComparator();
    private static final long serialVersionUID = 4582133183775373862L;

    private CaseIgnoringComparator() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // java.util.Comparator
    public int compare(CharSequence charSequence, CharSequence charSequence2) {
        char upperCase;
        char upperCase2;
        char lowerCase;
        char lowerCase2;
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int iMin = Math.min(length, length2);
        for (int i = 0; i < iMin; i++) {
            char cCharAt = charSequence.charAt(i);
            char cCharAt2 = charSequence2.charAt(i);
            if (cCharAt != cCharAt2 && (upperCase = Character.toUpperCase(cCharAt)) != (upperCase2 = Character.toUpperCase(cCharAt2)) && (lowerCase = Character.toLowerCase(upperCase)) != (lowerCase2 = Character.toLowerCase(upperCase2))) {
                return lowerCase - lowerCase2;
            }
        }
        return length - length2;
    }
}
