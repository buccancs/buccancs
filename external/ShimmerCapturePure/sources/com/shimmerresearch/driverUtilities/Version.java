package com.shimmerresearch.driverUtilities;

/* loaded from: classes2.dex */
public class Version {
    public int mInternal;
    public int mMajor;
    public int mMinor;

    public Version(String str) {
        int[] version = parseVersion(str);
        if (version != null) {
            this.mMajor = version[0];
            this.mMinor = version[1];
            this.mInternal = version[2];
            System.out.println("Major: " + this.mMajor);
            System.out.println("Minor: " + this.mMinor);
            System.out.println("Internal: " + this.mInternal);
            return;
        }
        System.out.println("Invalid version format.");
    }

    public static int[] parseVersion(String str) {
        if (str.startsWith("v")) {
            str = str.substring(1);
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length != 3) {
            return null;
        }
        try {
            return new int[]{Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2])};
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getVersion() {
        return this.mMajor + "." + this.mMinor + "." + this.mInternal;
    }
}
