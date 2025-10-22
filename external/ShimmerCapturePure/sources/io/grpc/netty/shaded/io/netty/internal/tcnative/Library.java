package io.grpc.netty.shaded.io.netty.internal.tcnative;

import java.io.File;

/* loaded from: classes3.dex */
public final class Library {
    private static final String PROVIDED = "provided";
    private static final String[] NAMES = {"netty_tcnative", "libnetty_tcnative"};
    private static Library _instance = null;

    private Library() throws Exception {
        String[] strArrSplit = System.getProperty("java.library.path").split(File.pathSeparator);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = false;
        while (true) {
            String[] strArr = NAMES;
            if (i >= strArr.length) {
                break;
            }
            try {
                loadLibrary(strArr[i]);
                z = true;
            } catch (ThreadDeath e) {
                throw e;
            } catch (VirtualMachineError e2) {
                throw e2;
            } catch (Throwable th) {
                String strMapLibraryName = System.mapLibraryName(NAMES[i]);
                for (String str : strArrSplit) {
                    if (new File(str, strMapLibraryName).exists()) {
                        throw new RuntimeException(th);
                    }
                }
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(th.getMessage());
            }
            if (z) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            throw new UnsatisfiedLinkError(sb.toString());
        }
    }

    private Library(String str) {
        if (PROVIDED.equals(str)) {
            return;
        }
        loadLibrary(str);
    }

    private static native boolean aprHasThreads();

    private static native int aprMajorVersion();

    private static native String aprVersionString();

    private static native boolean initialize0();

    private static void loadLibrary(String str) {
        System.loadLibrary(calculatePackagePrefix().replace('.', '_') + str);
    }

    private static String calculatePackagePrefix() {
        String name = Library.class.getName();
        String strReplace = "io!netty!internal!tcnative!Library".replace('!', '.');
        if (!name.endsWith(strReplace)) {
            throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", strReplace, name));
        }
        return name.substring(0, name.length() - strReplace.length());
    }

    public static boolean initialize() throws Exception {
        return initialize(PROVIDED, null);
    }

    public static boolean initialize(String str, String str2) throws Exception {
        if (_instance == null) {
            _instance = str == null ? new Library() : new Library(str);
            if (aprMajorVersion() < 1) {
                throw new UnsatisfiedLinkError("Unsupported APR Version (" + aprVersionString() + ")");
            }
            if (!aprHasThreads()) {
                throw new UnsatisfiedLinkError("Missing APR_HAS_THREADS");
            }
        }
        return initialize0() && SSL.initialize(str2) == 0;
    }
}
