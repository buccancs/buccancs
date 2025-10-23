package org.conscrypt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.conscrypt.NativeLibraryLoader;

/* loaded from: classes5.dex */
final class NativeCryptoJni {
    private static final String DYNAMIC_LIB_NAME_PREFIX = "conscrypt_openjdk_jni";
    private static final String STATIC_LIB_NAME = "conscrypt";

    private NativeCryptoJni() {
    }

    static void init() throws UnsatisfiedLinkError {
        ArrayList arrayList = new ArrayList();
        if (NativeLibraryLoader.loadFirstAvailable(classLoader(), arrayList, platformLibName(), DYNAMIC_LIB_NAME_PREFIX, STATIC_LIB_NAME)) {
            return;
        }
        logResults(arrayList);
        throwBestError(arrayList);
    }

    private static void logResults(List<NativeLibraryLoader.LoadResult> list) {
        Iterator<NativeLibraryLoader.LoadResult> it2 = list.iterator();
        while (it2.hasNext()) {
            it2.next().log();
        }
    }

    private static void throwBestError(List<NativeLibraryLoader.LoadResult> list) {
        Collections.sort(list, ErrorComparator.INSTANCE);
        Throwable th = list.get(0).error;
        Iterator<NativeLibraryLoader.LoadResult> it2 = list.subList(1, list.size()).iterator();
        while (it2.hasNext()) {
            th.addSuppressed(it2.next().error);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw ((Error) new UnsatisfiedLinkError(th.getMessage()).initCause(th));
    }

    private static ClassLoader classLoader() {
        return NativeCrypto.class.getClassLoader();
    }

    private static String platformLibName() {
        return "conscrypt_openjdk_jni-" + osName() + '-' + archName();
    }

    private static String osName() {
        return HostProperties.OS.getFileComponent();
    }

    private static String archName() {
        return HostProperties.ARCH.getFileComponent();
    }

    private static final class ErrorComparator implements Comparator<NativeLibraryLoader.LoadResult> {
        static final ErrorComparator INSTANCE = new ErrorComparator();

        private ErrorComparator() {
        }

        @Override // java.util.Comparator
        public int compare(NativeLibraryLoader.LoadResult loadResult, NativeLibraryLoader.LoadResult loadResult2) {
            Throwable th = loadResult.error;
            Throwable th2 = loadResult2.error;
            boolean z = th instanceof UnsatisfiedLinkError;
            boolean z2 = th2 instanceof UnsatisfiedLinkError;
            if (z != z2) {
                return (z2 ? 1 : 0) - (z ? 1 : 0);
            }
            String message = th.getMessage();
            String message2 = th2.getMessage();
            return ((message2 == null || !message2.contains("java.library.path")) ? 1 : 0) - ((message == null || !message.contains("java.library.path")) ? 1 : 0);
        }
    }
}
