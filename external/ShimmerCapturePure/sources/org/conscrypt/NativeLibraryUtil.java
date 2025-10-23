package org.conscrypt;

/* loaded from: classes5.dex */
final class NativeLibraryUtil {
    private NativeLibraryUtil() {
    }

    public static void loadLibrary(String str, boolean z) {
        if (z) {
            System.load(str);
        } else {
            System.loadLibrary(str);
        }
    }
}
