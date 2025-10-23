package org.apache.commons.lang3;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet;

import java.util.Random;

/* loaded from: classes5.dex */
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, false, false);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomAscii(int i, int i2) {
        return randomAscii(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphabetic(int i) {
        return random(i, true, false);
    }

    public static String randomAlphabetic(int i, int i2) {
        return randomAlphabetic(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphanumeric(int i) {
        return random(i, true, true);
    }

    public static String randomAlphanumeric(int i, int i2) {
        return randomAlphanumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomGraph(int i) {
        return random(i, 33, ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_EXPID_COMMAND_VALUE, false, false);
    }

    public static String randomGraph(int i, int i2) {
        return randomGraph(RandomUtils.nextInt(i, i2));
    }

    public static String randomNumeric(int i) {
        return random(i, false, true);
    }

    public static String randomNumeric(int i, int i2) {
        return randomNumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomPrint(int i) {
        return random(i, 32, ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_EXPID_COMMAND_VALUE, false, false);
    }

    public static String randomPrint(int i, int i2) {
        return randomPrint(RandomUtils.nextInt(i, i2));
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, null, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char cNextInt;
        if (i == 0) {
            return "";
        }
        if (i < 0) {
            throw new IllegalArgumentException("Requested random string length " + i + " is less than 0.");
        }
        if (cArr != null && cArr.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        }
        if (i2 == 0 && i3 == 0) {
            if (cArr != null) {
                i3 = cArr.length;
            } else if (z || z2) {
                i3 = 123;
                i2 = 32;
            } else {
                i3 = Integer.MAX_VALUE;
            }
        } else if (i3 <= i2) {
            throw new IllegalArgumentException("Parameter end (" + i3 + ") must be greater than start (" + i2 + ")");
        }
        char[] cArr2 = new char[i];
        int i4 = i3 - i2;
        while (true) {
            int i5 = i - 1;
            if (i != 0) {
                if (cArr == null) {
                    cNextInt = (char) (random.nextInt(i4) + i2);
                } else {
                    cNextInt = cArr[random.nextInt(i4) + i2];
                }
                if ((z && Character.isLetter(cNextInt)) || ((z2 && Character.isDigit(cNextInt)) || (!z && !z2))) {
                    if (cNextInt < 56320 || cNextInt > 57343) {
                        if (cNextInt < 55296 || cNextInt > 56191) {
                            if (cNextInt < 56192 || cNextInt > 56319) {
                                cArr2[i5] = cNextInt;
                                i = i5;
                            }
                        } else if (i5 != 0) {
                            cArr2[i5] = (char) (random.nextInt(128) + GeneratorBase.SURR2_FIRST);
                            i -= 2;
                            cArr2[i] = cNextInt;
                        }
                    } else if (i5 != 0) {
                        cArr2[i5] = cNextInt;
                        i -= 2;
                        cArr2[i] = (char) (random.nextInt(128) + GeneratorBase.SURR1_FIRST);
                    }
                }
            } else {
                return new String(cArr2);
            }
        }
    }

    public static String random(int i, String str) {
        if (str == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, str.toCharArray());
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
