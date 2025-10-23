package org.apache.commons.math.util;

import androidx.core.view.InputDeviceCompat;
import com.shimmerresearch.driver.ShimmerObject;
import org.bouncycastle.pqc.crypto.qtesla.Parameter;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/util/FastMath.class */
public class FastMath {
    public static final double PI = 3.141592653589793d;
    public static final double E = 2.718281828459045d;
    private static final double LN_2_A = 0.6931470632553101d;
    private static final double LN_2_B = 1.1730463525082348E-7d;
    private static final long HEX_40000000 = 1073741824;
    private static final long MASK_30BITS = -1073741824;
    private static final double TWO_POWER_52 = 4.503599627370496E15d;
    private static final double[] EXP_INT_TABLE_A = new double[1500];
    private static final double[] EXP_INT_TABLE_B = new double[1500];
    private static final double[] EXP_FRAC_TABLE_A = new double[InputDeviceCompat.SOURCE_GAMEPAD];
    private static final double[] EXP_FRAC_TABLE_B = new double[InputDeviceCompat.SOURCE_GAMEPAD];
    private static final double[] FACT = new double[20];
    private static final double[][] LN_MANT = new double[1024];
    private static final double[][] LN_SPLIT_COEF = {new double[]{2.0d, 0.0d}, new double[]{0.6666666269302368d, 3.9736429850260626E-8d}, new double[]{0.3999999761581421d, 2.3841857910019882E-8d}, new double[]{0.2857142686843872d, 1.7029898543501842E-8d}, new double[]{0.2222222089767456d, 1.3245471311735498E-8d}, new double[]{0.1818181574344635d, 2.4384203044354907E-8d}, new double[]{0.1538461446762085d, 9.140260083262505E-9d}, new double[]{0.13333332538604736d, 9.220590270857665E-9d}, new double[]{0.11764700710773468d, 1.2393345855018391E-8d}, new double[]{0.10526403784751892d, 8.251545029714408E-9d}, new double[]{0.0952233225107193d, 1.2675934823758863E-8d}, new double[]{0.08713622391223907d, 1.1430250008909141E-8d}, new double[]{0.07842259109020233d, 2.404307984052299E-9d}, new double[]{0.08371849358081818d, 1.176342548272881E-8d}, new double[]{0.030589580535888672d, 1.2958646899018938E-9d}, new double[]{0.14982303977012634d, 1.225743062930824E-8d}};
    private static final double[][] LN_QUICK_COEF = {new double[]{1.0d, 5.669184079525E-24d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.986821492305628E-8d}, new double[]{-0.25d, -6.663542893624021E-14d}, new double[]{0.19999998807907104d, 1.1921056801463227E-8d}, new double[]{-0.1666666567325592d, -7.800414592973399E-9d}, new double[]{0.1428571343421936d, 5.650007086920087E-9d}, new double[]{-0.12502530217170715d, -7.44321345601866E-11d}, new double[]{0.11113807559013367d, 9.219544613762692E-9d}};
    private static final double[][] LN_HI_PREC_COEF = {new double[]{1.0d, -6.032174644509064E-23d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.9868161777724352E-8d}, new double[]{-0.2499999701976776d, -2.957007209750105E-8d}, new double[]{0.19999954104423523d, 1.5830993332061267E-10d}, new double[]{-0.16624879837036133d, -2.6033824355191673E-8d}};
    private static final double[] SINE_TABLE_A = new double[14];
    private static final double[] SINE_TABLE_B = new double[14];
    private static final double[] COSINE_TABLE_A = new double[14];
    private static final double[] COSINE_TABLE_B = new double[14];
    private static final double[] TANGENT_TABLE_A = new double[14];
    private static final double[] TANGENT_TABLE_B = new double[14];
    private static final long[] RECIP_2PI = {2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L};
    private static final long[] PI_O_4_BITS = {-3958705157555305932L, -4267615245585081135L};
    private static final double[] EIGHTHS = {0.0d, 0.125d, 0.25d, 0.375d, 0.5d, 0.625d, 0.75d, 0.875d, 1.0d, 1.125d, 1.25d, 1.375d, 1.5d, 1.625d};
    private static final double[] CBRTTWO = {0.6299605249474366d, 0.7937005259840998d, 1.0d, 1.2599210498948732d, 1.5874010519681994d};

    /* JADX WARN: Type inference failed for: r0v11, types: [double[], double[][]] */
    /* JADX WARN: Type inference failed for: r0v13, types: [double[], double[][]] */
    /* JADX WARN: Type inference failed for: r0v15, types: [double[], double[][]] */
    /* JADX WARN: Type inference failed for: r0v17, types: [double[], double[][]] */
    static {
        FACT[0] = 1.0d;
        for (int i = 1; i < FACT.length; i++) {
            FACT[i] = FACT[i - 1] * i;
        }
        double[] tmp = new double[2];
        double[] recip = new double[2];
        for (int i2 = 0; i2 < 750; i2++) {
            expint(i2, tmp);
            EXP_INT_TABLE_A[i2 + 750] = tmp[0];
            EXP_INT_TABLE_B[i2 + 750] = tmp[1];
            if (i2 != 0) {
                splitReciprocal(tmp, recip);
                EXP_INT_TABLE_A[750 - i2] = recip[0];
                EXP_INT_TABLE_B[750 - i2] = recip[1];
            }
        }
        for (int i3 = 0; i3 < EXP_FRAC_TABLE_A.length; i3++) {
            slowexp(i3 / 1024.0d, tmp);
            EXP_FRAC_TABLE_A[i3] = tmp[0];
            EXP_FRAC_TABLE_B[i3] = tmp[1];
        }
        for (int i4 = 0; i4 < LN_MANT.length; i4++) {
            double d = Double.longBitsToDouble((i4 << 42) | 4607182418800017408L);
            LN_MANT[i4] = slowLog(d);
        }
        buildSinCosTables();
    }

    private FastMath() {
    }

    private static double doubleHighPart(double d) {
        if (d > -2.2250738585072014E-308d && d < Double.MIN_NORMAL) {
            return d;
        }
        long xl = Double.doubleToLongBits(d);
        return Double.longBitsToDouble(xl & MASK_30BITS);
    }

    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public static double cosh(double x) {
        if (x != x) {
            return x;
        }
        if (x > 20.0d) {
            return exp(x) / 2.0d;
        }
        if (x < -20.0d) {
            return exp(-x) / 2.0d;
        }
        double[] hiPrec = new double[2];
        if (x < 0.0d) {
            x = -x;
        }
        exp(x, 0.0d, hiPrec);
        double ya = hiPrec[0] + hiPrec[1];
        double yb = -((ya - hiPrec[0]) - hiPrec[1]);
        double temp = ya * 1.073741824E9d;
        double yaa = (ya + temp) - temp;
        double yab = ya - yaa;
        double recip = 1.0d / ya;
        double temp2 = recip * 1.073741824E9d;
        double recipa = (recip + temp2) - temp2;
        double recipb = recip - recipa;
        double recipb2 = recipb + (((((1.0d - (yaa * recipa)) - (yaa * recipb)) - (yab * recipa)) - (yab * recipb)) * recip) + ((-yb) * recip * recip);
        double temp3 = ya + recipa;
        double yb2 = yb + (-((temp3 - ya) - recipa));
        double temp4 = temp3 + recipb2;
        double result = temp4 + yb2 + (-((temp4 - temp3) - recipb2));
        return result * 0.5d;
    }

    public static double sinh(double x) {
        double result;
        boolean negate = false;
        if (x != x) {
            return x;
        }
        if (x > 20.0d) {
            return exp(x) / 2.0d;
        }
        if (x < -20.0d) {
            return (-exp(-x)) / 2.0d;
        }
        if (x == 0.0d) {
            return x;
        }
        if (x < 0.0d) {
            x = -x;
            negate = true;
        }
        if (x > 0.25d) {
            double[] hiPrec = new double[2];
            exp(x, 0.0d, hiPrec);
            double ya = hiPrec[0] + hiPrec[1];
            double yb = -((ya - hiPrec[0]) - hiPrec[1]);
            double temp = ya * 1.073741824E9d;
            double yaa = (ya + temp) - temp;
            double yab = ya - yaa;
            double recip = 1.0d / ya;
            double temp2 = recip * 1.073741824E9d;
            double recipa = (recip + temp2) - temp2;
            double recipb = recip - recipa;
            double recipb2 = recipb + (((((1.0d - (yaa * recipa)) - (yaa * recipb)) - (yab * recipa)) - (yab * recipb)) * recip) + ((-yb) * recip * recip);
            double recipa2 = -recipa;
            double recipb3 = -recipb2;
            double temp3 = ya + recipa2;
            double yb2 = yb + (-((temp3 - ya) - recipa2));
            double temp4 = temp3 + recipb3;
            double result2 = temp4 + yb2 + (-((temp4 - temp3) - recipb3));
            result = result2 * 0.5d;
        } else {
            double[] hiPrec2 = new double[2];
            expm1(x, hiPrec2);
            double ya2 = hiPrec2[0] + hiPrec2[1];
            double yb3 = -((ya2 - hiPrec2[0]) - hiPrec2[1]);
            double denom = 1.0d + ya2;
            double denomr = 1.0d / denom;
            double denomb = (-((denom - 1.0d) - ya2)) + yb3;
            double ratio = ya2 * denomr;
            double temp5 = ratio * 1.073741824E9d;
            double ra = (ratio + temp5) - temp5;
            double rb = ratio - ra;
            double temp6 = denom * 1.073741824E9d;
            double za = (denom + temp6) - temp6;
            double zb = denom - za;
            double rb2 = rb + (((((ya2 - (za * ra)) - (za * rb)) - (zb * ra)) - (zb * rb)) * denomr) + (yb3 * denomr) + ((-ya2) * denomb * denomr * denomr);
            double temp7 = ya2 + ra;
            double yb4 = yb3 + (-((temp7 - ya2) - ra));
            double temp8 = temp7 + rb2;
            double result3 = temp8 + yb4 + (-((temp8 - temp7) - rb2));
            result = result3 * 0.5d;
        }
        if (negate) {
            result = -result;
        }
        return result;
    }

    public static double tanh(double x) {
        double result;
        boolean negate = false;
        if (x != x) {
            return x;
        }
        if (x > 20.0d) {
            return 1.0d;
        }
        if (x < -20.0d) {
            return -1.0d;
        }
        if (x == 0.0d) {
            return x;
        }
        if (x < 0.0d) {
            x = -x;
            negate = true;
        }
        if (x >= 0.5d) {
            double[] hiPrec = new double[2];
            exp(x * 2.0d, 0.0d, hiPrec);
            double ya = hiPrec[0] + hiPrec[1];
            double yb = -((ya - hiPrec[0]) - hiPrec[1]);
            double na = (-1.0d) + ya;
            double nb = -((na + 1.0d) - ya);
            double temp = na + yb;
            double nb2 = nb + (-((temp - na) - yb));
            double da = 1.0d + ya;
            double db = -((da - 1.0d) - ya);
            double temp2 = da + yb;
            double db2 = db + (-((temp2 - da) - yb));
            double temp3 = temp2 * 1.073741824E9d;
            double daa = (temp2 + temp3) - temp3;
            double dab = temp2 - daa;
            double ratio = temp / temp2;
            double temp4 = ratio * 1.073741824E9d;
            double ratioa = (ratio + temp4) - temp4;
            double ratiob = ratio - ratioa;
            result = ratioa + ratiob + (((((temp - (daa * ratioa)) - (daa * ratiob)) - (dab * ratioa)) - (dab * ratiob)) / temp2) + (nb2 / temp2) + ((((-db2) * temp) / temp2) / temp2);
        } else {
            double[] hiPrec2 = new double[2];
            expm1(x * 2.0d, hiPrec2);
            double ya2 = hiPrec2[0] + hiPrec2[1];
            double yb2 = -((ya2 - hiPrec2[0]) - hiPrec2[1]);
            double da2 = 2.0d + ya2;
            double db3 = -((da2 - 2.0d) - ya2);
            double temp5 = da2 + yb2;
            double db4 = db3 + (-((temp5 - da2) - yb2));
            double temp6 = temp5 * 1.073741824E9d;
            double daa2 = (temp5 + temp6) - temp6;
            double dab2 = temp5 - daa2;
            double ratio2 = ya2 / temp5;
            double temp7 = ratio2 * 1.073741824E9d;
            double ratioa2 = (ratio2 + temp7) - temp7;
            double ratiob2 = ratio2 - ratioa2;
            result = ratioa2 + ratiob2 + (((((ya2 - (daa2 * ratioa2)) - (daa2 * ratiob2)) - (dab2 * ratioa2)) - (dab2 * ratiob2)) / temp5) + (yb2 / temp5) + ((((-db4) * ya2) / temp5) / temp5);
        }
        if (negate) {
            result = -result;
        }
        return result;
    }

    public static double acosh(double a) {
        return log(a + sqrt((a * a) - 1.0d));
    }

    public static double asinh(double a) {
        double absAsinh;
        boolean negative = false;
        if (a < 0.0d) {
            negative = true;
            a = -a;
        }
        if (a > 0.167d) {
            absAsinh = log(sqrt((a * a) + 1.0d) + a);
        } else {
            double a2 = a * a;
            if (a > 0.097d) {
                absAsinh = a * (1.0d - ((a2 * (0.3333333333333333d - (((a2 * (0.2d - (((a2 * (0.14285714285714285d - (((a2 * (0.1111111111111111d - (((a2 * (0.09090909090909091d - (((a2 * (0.07692307692307693d - (((a2 * (0.06666666666666667d - (((a2 * 0.058823529411764705d) * 15.0d) / 16.0d))) * 13.0d) / 14.0d))) * 11.0d) / 12.0d))) * 9.0d) / 10.0d))) * 7.0d) / 8.0d))) * 5.0d) / 6.0d))) * 3.0d) / 4.0d))) / 2.0d));
            } else if (a > 0.036d) {
                absAsinh = a * (1.0d - ((a2 * (0.3333333333333333d - (((a2 * (0.2d - (((a2 * (0.14285714285714285d - (((a2 * (0.1111111111111111d - (((a2 * (0.09090909090909091d - (((a2 * 0.07692307692307693d) * 11.0d) / 12.0d))) * 9.0d) / 10.0d))) * 7.0d) / 8.0d))) * 5.0d) / 6.0d))) * 3.0d) / 4.0d))) / 2.0d));
            } else if (a > 0.0036d) {
                absAsinh = a * (1.0d - ((a2 * (0.3333333333333333d - (((a2 * (0.2d - (((a2 * (0.14285714285714285d - (((a2 * 0.1111111111111111d) * 7.0d) / 8.0d))) * 5.0d) / 6.0d))) * 3.0d) / 4.0d))) / 2.0d));
            } else {
                absAsinh = a * (1.0d - ((a2 * (0.3333333333333333d - (((a2 * 0.2d) * 3.0d) / 4.0d))) / 2.0d));
            }
        }
        return negative ? -absAsinh : absAsinh;
    }

    public static double atanh(double a) {
        double absAtanh;
        boolean negative = false;
        if (a < 0.0d) {
            negative = true;
            a = -a;
        }
        if (a > 0.15d) {
            absAtanh = 0.5d * log((1.0d + a) / (1.0d - a));
        } else {
            double a2 = a * a;
            if (a > 0.087d) {
                absAtanh = a * (1.0d + (a2 * (0.3333333333333333d + (a2 * (0.2d + (a2 * (0.14285714285714285d + (a2 * (0.1111111111111111d + (a2 * (0.09090909090909091d + (a2 * (0.07692307692307693d + (a2 * (0.06666666666666667d + (a2 * 0.058823529411764705d))))))))))))))));
            } else if (a > 0.031d) {
                absAtanh = a * (1.0d + (a2 * (0.3333333333333333d + (a2 * (0.2d + (a2 * (0.14285714285714285d + (a2 * (0.1111111111111111d + (a2 * (0.09090909090909091d + (a2 * 0.07692307692307693d))))))))))));
            } else if (a > 0.003d) {
                absAtanh = a * (1.0d + (a2 * (0.3333333333333333d + (a2 * (0.2d + (a2 * (0.14285714285714285d + (a2 * 0.1111111111111111d))))))));
            } else {
                absAtanh = a * (1.0d + (a2 * (0.3333333333333333d + (a2 * 0.2d))));
            }
        }
        return negative ? -absAtanh : absAtanh;
    }

    public static double signum(double a) {
        if (a < 0.0d) {
            return -1.0d;
        }
        if (a > 0.0d) {
            return 1.0d;
        }
        return a;
    }

    public static float signum(float a) {
        if (a < 0.0f) {
            return -1.0f;
        }
        if (a > 0.0f) {
            return 1.0f;
        }
        return a;
    }

    public static double nextUp(double a) {
        return nextAfter(a, Double.POSITIVE_INFINITY);
    }

    public static float nextUp(float a) {
        return nextAfter(a, Double.POSITIVE_INFINITY);
    }

    public static double random() {
        return Math.random();
    }

    public static double exp(double x) {
        return exp(x, 0.0d, null);
    }

    private static double exp(double x, double extra, double[] hiPrec) {
        int intVal;
        double intPartA;
        double intPartB;
        double result;
        if (x < 0.0d) {
            int intVal2 = (int) (-x);
            if (intVal2 > 746) {
                if (hiPrec != null) {
                    hiPrec[0] = 0.0d;
                    hiPrec[1] = 0.0d;
                    return 0.0d;
                }
                return 0.0d;
            }
            if (intVal2 > 709) {
                double result2 = exp(x + 40.19140625d, extra, hiPrec) / 2.8504009514401178E17d;
                if (hiPrec != null) {
                    hiPrec[0] = hiPrec[0] / 2.8504009514401178E17d;
                    hiPrec[1] = hiPrec[1] / 2.8504009514401178E17d;
                }
                return result2;
            }
            if (intVal2 == 709) {
                double result3 = exp(x + 1.494140625d, extra, hiPrec) / 4.455505956692757d;
                if (hiPrec != null) {
                    hiPrec[0] = hiPrec[0] / 4.455505956692757d;
                    hiPrec[1] = hiPrec[1] / 4.455505956692757d;
                }
                return result3;
            }
            int intVal3 = intVal2 + 1;
            intPartA = EXP_INT_TABLE_A[750 - intVal3];
            intPartB = EXP_INT_TABLE_B[750 - intVal3];
            intVal = -intVal3;
        } else {
            intVal = (int) x;
            if (intVal > 709) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.POSITIVE_INFINITY;
                    hiPrec[1] = 0.0d;
                    return Double.POSITIVE_INFINITY;
                }
                return Double.POSITIVE_INFINITY;
            }
            intPartA = EXP_INT_TABLE_A[750 + intVal];
            intPartB = EXP_INT_TABLE_B[750 + intVal];
        }
        int intFrac = (int) ((x - intVal) * 1024.0d);
        double fracPartA = EXP_FRAC_TABLE_A[intFrac];
        double fracPartB = EXP_FRAC_TABLE_B[intFrac];
        double epsilon = x - (intVal + (intFrac / 1024.0d));
        double z = (((((((0.04168701738764507d * epsilon) + 0.1666666505023083d) * epsilon) + 0.5000000000042687d) * epsilon) + 1.0d) * epsilon) - 3.940510424527919E-20d;
        double tempA = intPartA * fracPartA;
        double tempB = (intPartA * fracPartB) + (intPartB * fracPartA) + (intPartB * fracPartB);
        double tempC = tempB + tempA;
        if (extra != 0.0d) {
            result = (tempC * extra * z) + (tempC * extra) + (tempC * z) + tempB + tempA;
        } else {
            result = (tempC * z) + tempB + tempA;
        }
        if (hiPrec != null) {
            hiPrec[0] = tempA;
            hiPrec[1] = (tempC * extra * z) + (tempC * extra) + (tempC * z) + tempB;
        }
        return result;
    }

    public static double expm1(double x) {
        return expm1(x, null);
    }

    private static double expm1(double x, double[] hiPrecOut) {
        if (x != x || x == 0.0d) {
            return x;
        }
        if (x <= -1.0d || x >= 1.0d) {
            double[] hiPrec = new double[2];
            exp(x, 0.0d, hiPrec);
            if (x > 0.0d) {
                return (-1.0d) + hiPrec[0] + hiPrec[1];
            }
            double ra = (-1.0d) + hiPrec[0];
            return ra + (-((ra + 1.0d) - hiPrec[0])) + hiPrec[1];
        }
        boolean negative = false;
        if (x < 0.0d) {
            x = -x;
            negative = true;
        }
        int intFrac = (int) (x * 1024.0d);
        double tempA = EXP_FRAC_TABLE_A[intFrac] - 1.0d;
        double tempB = EXP_FRAC_TABLE_B[intFrac];
        double temp = tempA + tempB;
        double tempB2 = -((temp - tempA) - tempB);
        double temp2 = temp * 1.073741824E9d;
        double baseA = (temp + temp2) - temp2;
        double baseB = tempB2 + (temp - baseA);
        double epsilon = x - (intFrac / 1024.0d);
        double zb = ((((((0.008336750013465571d * epsilon) + 0.041666663879186654d) * epsilon) + 0.16666666666745392d) * epsilon) + 0.49999999999999994d) * epsilon * epsilon;
        double temp3 = epsilon + zb;
        double zb2 = -((temp3 - epsilon) - zb);
        double temp4 = temp3 * 1.073741824E9d;
        double temp5 = (temp3 + temp4) - temp4;
        double zb3 = zb2 + (temp3 - temp5);
        double ya = temp5 * baseA;
        double temp6 = ya + (temp5 * baseB);
        double yb = -((temp6 - ya) - (temp5 * baseB));
        double temp7 = temp6 + (zb3 * baseA);
        double yb2 = yb + (-((temp7 - temp6) - (zb3 * baseA)));
        double temp8 = temp7 + (zb3 * baseB);
        double yb3 = yb2 + (-((temp8 - temp7) - (zb3 * baseB)));
        double temp9 = temp8 + baseA;
        double yb4 = yb3 + (-((temp9 - baseA) - temp8));
        double temp10 = temp9 + temp5;
        double yb5 = yb4 + (-((temp10 - temp9) - temp5));
        double temp11 = temp10 + baseB;
        double yb6 = yb5 + (-((temp11 - temp10) - baseB));
        double temp12 = temp11 + zb3;
        double yb7 = yb6 + (-((temp12 - temp11) - zb3));
        double ya2 = temp12;
        if (negative) {
            double denom = 1.0d + ya2;
            double denomr = 1.0d / denom;
            double denomb = (-((denom - 1.0d) - ya2)) + yb7;
            double ratio = ya2 * denomr;
            double temp13 = ratio * 1.073741824E9d;
            double ra2 = (ratio + temp13) - temp13;
            double rb = ratio - ra2;
            double temp14 = denom * 1.073741824E9d;
            double za = (denom + temp14) - temp14;
            double zb4 = denom - za;
            double rb2 = rb + (((((ya2 - (za * ra2)) - (za * rb)) - (zb4 * ra2)) - (zb4 * rb)) * denomr) + (yb7 * denomr) + ((-ya2) * denomb * denomr * denomr);
            ya2 = -ra2;
            yb7 = -rb2;
        }
        if (hiPrecOut != null) {
            hiPrecOut[0] = ya2;
            hiPrecOut[1] = yb7;
        }
        return ya2 + yb7;
    }

    private static double slowexp(double x, double[] result) {
        double[] xs = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        split(x, xs);
        double[] ys = {0.0d, 0.0d};
        for (int i = 19; i >= 0; i--) {
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            split(FACT[i], as);
            splitReciprocal(as, facts);
            splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    private static void split(double d, double[] split) {
        if (d < 8.0E298d && d > -8.0E298d) {
            double a = d * 1.073741824E9d;
            split[0] = (d + a) - a;
            split[1] = d - split[0];
        } else {
            split[0] = ((d + (d * 9.313225746154785E-10d)) - d) * 1.073741824E9d;
            split[1] = d - split[0];
        }
    }

    private static void resplit(double[] a) {
        double c = a[0] + a[1];
        double d = -((c - a[0]) - a[1]);
        if (c < 8.0E298d && c > -8.0E298d) {
            double z = c * 1.073741824E9d;
            a[0] = (c + z) - z;
            a[1] = (c - a[0]) + d;
        } else {
            a[0] = ((c + (c * 9.313225746154785E-10d)) - c) * 1.073741824E9d;
            a[1] = (c - a[0]) + d;
        }
    }

    private static void splitMult(double[] a, double[] b, double[] ans) {
        ans[0] = a[0] * b[0];
        ans[1] = (a[0] * b[1]) + (a[1] * b[0]) + (a[1] * b[1]);
        resplit(ans);
    }

    private static void splitAdd(double[] a, double[] b, double[] ans) {
        ans[0] = a[0] + b[0];
        ans[1] = a[1] + b[1];
        resplit(ans);
    }

    private static void splitReciprocal(double[] in, double[] result) {
        if (in[0] == 0.0d) {
            in[0] = in[1];
            in[1] = 0.0d;
        }
        result[0] = 0.9999997615814209d / in[0];
        result[1] = ((2.384185791015625E-7d * in[0]) - (0.9999997615814209d * in[1])) / ((in[0] * in[0]) + (in[0] * in[1]));
        if (result[1] != result[1]) {
            result[1] = 0.0d;
        }
        resplit(result);
        for (int i = 0; i < 2; i++) {
            double err = (((1.0d - (result[0] * in[0])) - (result[0] * in[1])) - (result[1] * in[0])) - (result[1] * in[1]);
            result[1] = result[1] + (err * (result[0] + result[1]));
        }
    }

    private static void quadMult(double[] a, double[] b, double[] result) {
        double[] xs = new double[2];
        double[] ys = new double[2];
        double[] zs = new double[2];
        split(a[0], xs);
        split(b[0], ys);
        splitMult(xs, ys, zs);
        result[0] = zs[0];
        result[1] = zs[1];
        split(b[1], ys);
        splitMult(xs, ys, zs);
        double tmp = result[0] + zs[0];
        result[1] = result[1] - ((tmp - result[0]) - zs[0]);
        result[0] = tmp;
        double tmp2 = result[0] + zs[1];
        result[1] = result[1] - ((tmp2 - result[0]) - zs[1]);
        result[0] = tmp2;
        split(a[1], xs);
        split(b[0], ys);
        splitMult(xs, ys, zs);
        double tmp3 = result[0] + zs[0];
        result[1] = result[1] - ((tmp3 - result[0]) - zs[0]);
        result[0] = tmp3;
        double tmp4 = result[0] + zs[1];
        result[1] = result[1] - ((tmp4 - result[0]) - zs[1]);
        result[0] = tmp4;
        split(a[1], xs);
        split(b[1], ys);
        splitMult(xs, ys, zs);
        double tmp5 = result[0] + zs[0];
        result[1] = result[1] - ((tmp5 - result[0]) - zs[0]);
        result[0] = tmp5;
        double tmp6 = result[0] + zs[1];
        result[1] = result[1] - ((tmp6 - result[0]) - zs[1]);
        result[0] = tmp6;
    }

    private static double expint(int p, double[] result) {
        double[] as = new double[2];
        double[] ys = new double[2];
        double[] xs = {2.718281828459045d, 1.4456468917292502E-16d};
        split(1.0d, ys);
        while (p > 0) {
            if ((p & 1) != 0) {
                quadMult(ys, xs, as);
                ys[0] = as[0];
                ys[1] = as[1];
            }
            quadMult(xs, xs, as);
            xs[0] = as[0];
            xs[1] = as[1];
            p >>= 1;
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
            resplit(result);
        }
        return ys[0] + ys[1];
    }

    public static double log(double x) {
        return log(x, null);
    }

    private static double log(double x, double[] hiPrec) {
        double lnza;
        if (x == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        long bits = Double.doubleToLongBits(x);
        if (((bits & Long.MIN_VALUE) != 0 || x != x) && x != 0.0d) {
            if (hiPrec != null) {
                hiPrec[0] = Double.NaN;
                return Double.NaN;
            }
            return Double.NaN;
        }
        if (x == Double.POSITIVE_INFINITY) {
            if (hiPrec != null) {
                hiPrec[0] = Double.POSITIVE_INFINITY;
                return Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        int exp = ((int) (bits >> 52)) - 1023;
        if ((bits & 9218868437227405312L) == 0) {
            if (x == 0.0d) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.NEGATIVE_INFINITY;
                    return Double.NEGATIVE_INFINITY;
                }
                return Double.NEGATIVE_INFINITY;
            }
            while (true) {
                bits <<= 1;
                if ((bits & 4503599627370496L) != 0) {
                    break;
                }
                exp--;
            }
        }
        if ((exp == -1 || exp == 0) && x < 1.01d && x > 0.99d && hiPrec == null) {
            double xa = x - 1.0d;
            double d = (xa - x) + 1.0d;
            double tmp = xa * 1.073741824E9d;
            double aa = (xa + tmp) - tmp;
            double ab = xa - aa;
            double ya = LN_QUICK_COEF[LN_QUICK_COEF.length - 1][0];
            double yb = LN_QUICK_COEF[LN_QUICK_COEF.length - 1][1];
            for (int i = LN_QUICK_COEF.length - 2; i >= 0; i--) {
                double aa2 = ya * aa;
                double ab2 = (ya * ab) + (yb * aa) + (yb * ab);
                double tmp2 = aa2 * 1.073741824E9d;
                double ya2 = (aa2 + tmp2) - tmp2;
                double yb2 = (aa2 - ya2) + ab2;
                double aa3 = ya2 + LN_QUICK_COEF[i][0];
                double ab3 = yb2 + LN_QUICK_COEF[i][1];
                double tmp3 = aa3 * 1.073741824E9d;
                ya = (aa3 + tmp3) - tmp3;
                yb = (aa3 - ya) + ab3;
            }
            double aa4 = ya * aa;
            double ab4 = (ya * ab) + (yb * aa) + (yb * ab);
            double tmp4 = aa4 * 1.073741824E9d;
            double ya3 = (aa4 + tmp4) - tmp4;
            double yb3 = (aa4 - ya3) + ab4;
            return ya3 + yb3;
        }
        double[] lnm = LN_MANT[(int) ((bits & 4499201580859392L) >> 42)];
        double epsilon = (bits & 4398046511103L) / (TWO_POWER_52 + (bits & 4499201580859392L));
        double lnzb = 0.0d;
        if (hiPrec != null) {
            double tmp5 = epsilon * 1.073741824E9d;
            double aa5 = (epsilon + tmp5) - tmp5;
            double ab5 = epsilon - aa5;
            double numer = bits & 4398046511103L;
            double denom = TWO_POWER_52 + (bits & 4499201580859392L);
            double xb = ab5 + (((numer - (aa5 * denom)) - (ab5 * denom)) / denom);
            double ya4 = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1][0];
            double yb4 = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1][1];
            for (int i2 = LN_HI_PREC_COEF.length - 2; i2 >= 0; i2--) {
                double aa6 = ya4 * aa5;
                double ab6 = (ya4 * xb) + (yb4 * aa5) + (yb4 * xb);
                double tmp6 = aa6 * 1.073741824E9d;
                double ya5 = (aa6 + tmp6) - tmp6;
                double yb5 = (aa6 - ya5) + ab6;
                double aa7 = ya5 + LN_HI_PREC_COEF[i2][0];
                double ab7 = yb5 + LN_HI_PREC_COEF[i2][1];
                double tmp7 = aa7 * 1.073741824E9d;
                ya4 = (aa7 + tmp7) - tmp7;
                yb4 = (aa7 - ya4) + ab7;
            }
            double aa8 = ya4 * aa5;
            double ab8 = (ya4 * xb) + (yb4 * aa5) + (yb4 * xb);
            lnza = aa8 + ab8;
            lnzb = -((lnza - aa8) - ab8);
        } else {
            double lnza2 = ((-0.16624882440418567d) * epsilon) + 0.19999954120254515d;
            lnza = ((((((((lnza2 * epsilon) - 0.2499999997677497d) * epsilon) + 0.3333333333332802d) * epsilon) - 0.5d) * epsilon) + 1.0d) * epsilon;
        }
        double a = LN_2_A * exp;
        double c = a + lnm[0];
        double d2 = -((c - a) - lnm[0]);
        double b = 0.0d + d2;
        double c2 = c + lnza;
        double d3 = -((c2 - c) - lnza);
        double b2 = b + d3;
        double c3 = c2 + (LN_2_B * exp);
        double d4 = -((c3 - c2) - (LN_2_B * exp));
        double b3 = b2 + d4;
        double c4 = c3 + lnm[1];
        double d5 = -((c4 - c3) - lnm[1]);
        double b4 = b3 + d5;
        double c5 = c4 + lnzb;
        double d6 = -((c5 - c4) - lnzb);
        double b5 = b4 + d6;
        if (hiPrec != null) {
            hiPrec[0] = c5;
            hiPrec[1] = b5;
        }
        return c5 + b5;
    }

    public static double log1p(double x) {
        double xpa = 1.0d + x;
        double xpb = -((xpa - 1.0d) - x);
        if (x == -1.0d) {
            return x / 0.0d;
        }
        if (x > 0.0d && 1.0d / x == 0.0d) {
            return x;
        }
        if (x > 1.0E-6d || x < -1.0E-6d) {
            double[] hiPrec = new double[2];
            double lores = log(xpa, hiPrec);
            if (Double.isInfinite(lores)) {
                return lores;
            }
            double fx1 = xpb / xpa;
            double epsilon = (0.5d * fx1) + 1.0d;
            return (epsilon * fx1) + hiPrec[1] + hiPrec[0];
        }
        double y = (x * 0.333333333333333d) - 0.5d;
        return ((y * x) + 1.0d) * x;
    }

    public static double log10(double x) {
        double[] hiPrec = new double[2];
        double lores = log(x, hiPrec);
        if (Double.isInfinite(lores)) {
            return lores;
        }
        double tmp = hiPrec[0] * 1.073741824E9d;
        double lna = (hiPrec[0] + tmp) - tmp;
        double lnb = (hiPrec[0] - lna) + hiPrec[1];
        return (1.9699272335463627E-8d * lnb) + (1.9699272335463627E-8d * lna) + (0.4342944622039795d * lnb) + (0.4342944622039795d * lna);
    }

    public static double pow(double x, double y) {
        double ya;
        double yb;
        double[] lns = new double[2];
        if (y == 0.0d) {
            return 1.0d;
        }
        if (x != x) {
            return x;
        }
        if (x == 0.0d) {
            long bits = Double.doubleToLongBits(x);
            if ((bits & Long.MIN_VALUE) != 0) {
                long yi = (long) y;
                if (y < 0.0d && y == yi && (yi & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }
                if (y < 0.0d && y == yi && (yi & 1) == 1) {
                    return -0.0d;
                }
                if (y > 0.0d && y == yi && (yi & 1) == 1) {
                    return -0.0d;
                }
            }
            if (y < 0.0d) {
                return Double.POSITIVE_INFINITY;
            }
            if (y > 0.0d) {
                return 0.0d;
            }
            return Double.NaN;
        }
        if (x == Double.POSITIVE_INFINITY) {
            if (y != y) {
                return y;
            }
            if (y < 0.0d) {
                return 0.0d;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (y == Double.POSITIVE_INFINITY) {
            if (x * x == 1.0d) {
                return Double.NaN;
            }
            if (x * x > 1.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return 0.0d;
        }
        if (x == Double.NEGATIVE_INFINITY) {
            if (y != y) {
                return y;
            }
            if (y < 0.0d) {
                long yi2 = (long) y;
                if (y == yi2 && (yi2 & 1) == 1) {
                    return -0.0d;
                }
                return 0.0d;
            }
            if (y > 0.0d) {
                long yi3 = (long) y;
                if (y == yi3 && (yi3 & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }
                return Double.POSITIVE_INFINITY;
            }
        }
        if (y == Double.NEGATIVE_INFINITY) {
            if (x * x == 1.0d) {
                return Double.NaN;
            }
            if (x * x < 1.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return 0.0d;
        }
        if (x < 0.0d) {
            if (y >= TWO_POWER_52 || y <= -4.503599627370496E15d) {
                return pow(-x, y);
            }
            if (y == ((long) y)) {
                return (((long) y) & 1) == 0 ? pow(-x, y) : -pow(-x, y);
            }
            return Double.NaN;
        }
        if (y < 8.0E298d && y > -8.0E298d) {
            double tmp1 = y * 1.073741824E9d;
            ya = (y + tmp1) - tmp1;
            yb = y - ya;
        } else {
            double tmp12 = y * 9.313225746154785E-10d;
            ya = ((tmp12 + (tmp12 * 9.313225746154785E-10d)) - tmp12) * 1.073741824E9d * 1.073741824E9d;
            yb = y - ya;
        }
        double lores = log(x, lns);
        if (Double.isInfinite(lores)) {
            return lores;
        }
        double lna = lns[0];
        double lnb = lns[1];
        double tmp13 = lna * 1.073741824E9d;
        double tmp2 = (lna + tmp13) - tmp13;
        double lnb2 = lnb + (lna - tmp2);
        double aa = tmp2 * ya;
        double ab = (tmp2 * yb) + (lnb2 * ya) + (lnb2 * yb);
        double lna2 = aa + ab;
        double lnb3 = -((lna2 - aa) - ab);
        double z = (0.008333333333333333d * lnb3) + 0.041666666666666664d;
        double result = exp(lna2, ((((((z * lnb3) + 0.16666666666666666d) * lnb3) + 0.5d) * lnb3) + 1.0d) * lnb3, null);
        return result;
    }

    private static double[] slowLog(double xi) {
        double[] x = new double[2];
        double[] x2 = new double[2];
        double[] a = new double[2];
        split(xi, x);
        x[0] = x[0] + 1.0d;
        resplit(x);
        splitReciprocal(x, a);
        x[0] = x[0] - 2.0d;
        resplit(x);
        double[] y = {LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][0], LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][1]};
        splitMult(x, a, y);
        x[0] = y[0];
        x[1] = y[1];
        splitMult(x, x, x2);
        for (int i = LN_SPLIT_COEF.length - 2; i >= 0; i--) {
            splitMult(y, x2, a);
            y[0] = a[0];
            y[1] = a[1];
            splitAdd(y, LN_SPLIT_COEF[i], a);
            y[0] = a[0];
            y[1] = a[1];
        }
        splitMult(y, x, a);
        y[0] = a[0];
        y[1] = a[1];
        return y;
    }

    private static double slowSin(double x, double[] result) {
        double[] xs = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        split(x, xs);
        double[] ys = {0.0d, 0.0d};
        for (int i = 19; i >= 0; i--) {
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if ((i & 1) != 0) {
                split(FACT[i], as);
                splitReciprocal(as, facts);
                if ((i & 2) != 0) {
                    facts[0] = -facts[0];
                    facts[1] = -facts[1];
                }
                splitAdd(ys, facts, as);
                ys[0] = as[0];
                ys[1] = as[1];
            }
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    private static double slowCos(double x, double[] result) {
        double[] xs = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        split(x, xs);
        double[] ys = {0.0d, 0.0d};
        for (int i = 19; i >= 0; i--) {
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if ((i & 1) == 0) {
                split(FACT[i], as);
                splitReciprocal(as, facts);
                if ((i & 2) != 0) {
                    facts[0] = -facts[0];
                    facts[1] = -facts[1];
                }
                splitAdd(ys, facts, as);
                ys[0] = as[0];
                ys[1] = as[1];
            }
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    private static void buildSinCosTables() {
        double[] result = new double[2];
        for (int i = 0; i < 7; i++) {
            double x = i / 8.0d;
            slowSin(x, result);
            SINE_TABLE_A[i] = result[0];
            SINE_TABLE_B[i] = result[1];
            slowCos(x, result);
            COSINE_TABLE_A[i] = result[0];
            COSINE_TABLE_B[i] = result[1];
        }
        for (int i2 = 7; i2 < 14; i2++) {
            double[] xs = new double[2];
            double[] ys = new double[2];
            double[] as = new double[2];
            double[] bs = new double[2];
            double[] temps = new double[2];
            if ((i2 & 1) == 0) {
                xs[0] = SINE_TABLE_A[i2 / 2];
                xs[1] = SINE_TABLE_B[i2 / 2];
                ys[0] = COSINE_TABLE_A[i2 / 2];
                ys[1] = COSINE_TABLE_B[i2 / 2];
                splitMult(xs, ys, result);
                SINE_TABLE_A[i2] = result[0] * 2.0d;
                SINE_TABLE_B[i2] = result[1] * 2.0d;
                splitMult(ys, ys, as);
                splitMult(xs, xs, temps);
                temps[0] = -temps[0];
                temps[1] = -temps[1];
                splitAdd(as, temps, result);
                COSINE_TABLE_A[i2] = result[0];
                COSINE_TABLE_B[i2] = result[1];
            } else {
                xs[0] = SINE_TABLE_A[i2 / 2];
                xs[1] = SINE_TABLE_B[i2 / 2];
                ys[0] = COSINE_TABLE_A[i2 / 2];
                ys[1] = COSINE_TABLE_B[i2 / 2];
                as[0] = SINE_TABLE_A[(i2 / 2) + 1];
                as[1] = SINE_TABLE_B[(i2 / 2) + 1];
                bs[0] = COSINE_TABLE_A[(i2 / 2) + 1];
                bs[1] = COSINE_TABLE_B[(i2 / 2) + 1];
                splitMult(xs, bs, temps);
                splitMult(ys, as, result);
                splitAdd(result, temps, result);
                SINE_TABLE_A[i2] = result[0];
                SINE_TABLE_B[i2] = result[1];
                splitMult(ys, bs, result);
                splitMult(xs, as, temps);
                temps[0] = -temps[0];
                temps[1] = -temps[1];
                splitAdd(result, temps, result);
                COSINE_TABLE_A[i2] = result[0];
                COSINE_TABLE_B[i2] = result[1];
            }
        }
        for (int i3 = 0; i3 < 14; i3++) {
            double[] ys2 = new double[2];
            double[] as2 = {COSINE_TABLE_A[i3], COSINE_TABLE_B[i3]};
            splitReciprocal(as2, ys2);
            splitMult(new double[]{SINE_TABLE_A[i3], SINE_TABLE_B[i3]}, ys2, as2);
            TANGENT_TABLE_A[i3] = as2[0];
            TANGENT_TABLE_B[i3] = as2[1];
        }
    }

    private static double polySine(double x) {
        double x2 = x * x;
        double p = (2.7553817452272217E-6d * x2) - 1.9841269659586505E-4d;
        return ((((p * x2) + 0.008333333333329196d) * x2) - 0.16666666666666666d) * x2 * x;
    }

    private static double polyCosine(double x) {
        double x2 = x * x;
        double p = (2.479773539153719E-5d * x2) - 0.0013888888689039883d;
        return ((((p * x2) + 0.041666666666621166d) * x2) - 0.49999999999999994d) * x2;
    }

    private static double sinQ(double xa, double xb) {
        int idx = (int) ((xa * 8.0d) + 0.5d);
        double epsilon = xa - EIGHTHS[idx];
        double sintA = SINE_TABLE_A[idx];
        double sintB = SINE_TABLE_B[idx];
        double costA = COSINE_TABLE_A[idx];
        double costB = COSINE_TABLE_B[idx];
        double sinEpsB = polySine(epsilon);
        double cosEpsB = polyCosine(epsilon);
        double temp = epsilon * 1.073741824E9d;
        double temp2 = (epsilon + temp) - temp;
        double sinEpsB2 = sinEpsB + (epsilon - temp2);
        double c = 0.0d + sintA;
        double d = -((c - 0.0d) - sintA);
        double b = 0.0d + d;
        double t = costA * temp2;
        double c2 = c + t;
        double d2 = -((c2 - c) - t);
        double a = c2;
        double b2 = b + d2 + (sintA * cosEpsB) + (costA * sinEpsB2) + sintB + (costB * temp2) + (sintB * cosEpsB) + (costB * sinEpsB2);
        if (xb != 0.0d) {
            double t2 = (((costA + costB) * (1.0d + cosEpsB)) - ((sintA + sintB) * (temp2 + sinEpsB2))) * xb;
            double c3 = a + t2;
            double d3 = -((c3 - a) - t2);
            a = c3;
            b2 += d3;
        }
        double result = a + b2;
        return result;
    }

    private static double cosQ(double xa, double xb) {
        double a = 1.5707963267948966d - xa;
        double b = -((a - 1.5707963267948966d) + xa);
        return sinQ(a, b + (6.123233995736766E-17d - xb));
    }

    private static double tanQ(double xa, double xb, boolean cotanFlag) {
        int idx = (int) ((xa * 8.0d) + 0.5d);
        double epsilon = xa - EIGHTHS[idx];
        double sintA = SINE_TABLE_A[idx];
        double sintB = SINE_TABLE_B[idx];
        double costA = COSINE_TABLE_A[idx];
        double costB = COSINE_TABLE_B[idx];
        double sinEpsB = polySine(epsilon);
        double cosEpsB = polyCosine(epsilon);
        double temp = epsilon * 1.073741824E9d;
        double temp2 = (epsilon + temp) - temp;
        double sinEpsB2 = sinEpsB + (epsilon - temp2);
        double c = 0.0d + sintA;
        double d = -((c - 0.0d) - sintA);
        double b = 0.0d + d;
        double t = costA * temp2;
        double c2 = c + t;
        double d2 = -((c2 - c) - t);
        double b2 = b + d2 + (sintA * cosEpsB) + (costA * sinEpsB2) + sintB + (costB * temp2) + (sintB * cosEpsB) + (costB * sinEpsB2);
        double sina = c2 + b2;
        double sinb = -((sina - c2) - b2);
        double t2 = costA * 1.0d;
        double c3 = 0.0d + t2;
        double d3 = -((c3 - 0.0d) - t2);
        double b3 = 0.0d + d3;
        double t3 = (-sintA) * temp2;
        double c4 = c3 + t3;
        double d4 = -((c4 - c3) - t3);
        double b4 = ((((b3 + d4) + (costB * 1.0d)) + (costA * cosEpsB)) + (costB * cosEpsB)) - (((sintB * temp2) + (sintA * sinEpsB2)) + (sintB * sinEpsB2));
        double cosa = c4 + b4;
        double cosb = -((cosa - c4) - b4);
        if (cotanFlag) {
            cosa = sina;
            sina = cosa;
            cosb = sinb;
            sinb = cosb;
        }
        double est = sina / cosa;
        double temp3 = est * 1.073741824E9d;
        double esta = (est + temp3) - temp3;
        double estb = est - esta;
        double temp4 = cosa * 1.073741824E9d;
        double cosaa = (cosa + temp4) - temp4;
        double cosab = cosa - cosaa;
        double err = (((((sina - (esta * cosaa)) - (esta * cosab)) - (estb * cosaa)) - (estb * cosab)) / cosa) + (sinb / cosa) + ((((-sina) * cosb) / cosa) / cosa);
        if (xb != 0.0d) {
            double xbadj = xb + (est * est * xb);
            if (cotanFlag) {
                xbadj = -xbadj;
            }
            err += xbadj;
        }
        return est + err;
    }

    private static void reducePayneHanek(double x, double[] result) {
        long shpi0;
        long shpiA;
        long shpiB;
        long inbits = Double.doubleToLongBits(x);
        int exponent = (((int) ((inbits >> 52) & 2047)) - 1023) + 1;
        long inbits2 = ((inbits & 4503599627370495L) | 4503599627370496L) << 11;
        int idx = exponent >> 6;
        int shift = exponent - (idx << 6);
        if (shift != 0) {
            long shpi02 = idx == 0 ? 0L : RECIP_2PI[idx - 1] << shift;
            shpi0 = shpi02 | (RECIP_2PI[idx] >>> (64 - shift));
            shpiA = (RECIP_2PI[idx] << shift) | (RECIP_2PI[idx + 1] >>> (64 - shift));
            shpiB = (RECIP_2PI[idx + 1] << shift) | (RECIP_2PI[idx + 2] >>> (64 - shift));
        } else {
            shpi0 = idx == 0 ? 0L : RECIP_2PI[idx - 1];
            shpiA = RECIP_2PI[idx];
            shpiB = RECIP_2PI[idx + 1];
        }
        long a = inbits2 >>> 32;
        long b = inbits2 & 4294967295L;
        long c = shpiA >>> 32;
        long d = shpiA & 4294967295L;
        long ac = a * c;
        long bd = b * d;
        long bc = b * c;
        long ad = a * d;
        long prodB = bd + (ad << 32);
        long prodA = ac + (ad >>> 32);
        boolean bita = (bd & Long.MIN_VALUE) != 0;
        boolean bitb = (ad & ShimmerObject.SDLogHeader.MPL_QUAT_6DOF) != 0;
        boolean bitsum = (prodB & Long.MIN_VALUE) != 0;
        if ((bita && bitb) || ((bita || bitb) && !bitsum)) {
            prodA++;
        }
        boolean bita2 = (prodB & Long.MIN_VALUE) != 0;
        boolean bitb2 = (bc & ShimmerObject.SDLogHeader.MPL_QUAT_6DOF) != 0;
        long prodB2 = prodB + (bc << 32);
        long prodA2 = prodA + (bc >>> 32);
        boolean bitsum2 = (prodB2 & Long.MIN_VALUE) != 0;
        if ((bita2 && bitb2) || ((bita2 || bitb2) && !bitsum2)) {
            prodA2++;
        }
        long c2 = shpiB >>> 32;
        long ac2 = a * c2;
        long ac3 = ac2 + (((b * c2) + (a * (shpiB & 4294967295L))) >>> 32);
        boolean bita3 = (prodB2 & Long.MIN_VALUE) != 0;
        boolean bitb3 = (ac3 & Long.MIN_VALUE) != 0;
        long prodB3 = prodB2 + ac3;
        boolean bitsum3 = (prodB3 & Long.MIN_VALUE) != 0;
        if ((bita3 && bitb3) || ((bita3 || bitb3) && !bitsum3)) {
            prodA2++;
        }
        long d2 = shpi0 & 4294967295L;
        long prodA3 = prodA2 + (b * d2) + (((b * (shpi0 >>> 32)) + (a * d2)) << 32);
        int intPart = (int) (prodA3 >>> 62);
        long prodA4 = (prodA3 << 2) | (prodB3 >>> 62);
        long prodB4 = prodB3 << 2;
        long a2 = prodA4 >>> 32;
        long b2 = prodA4 & 4294967295L;
        long c3 = PI_O_4_BITS[0] >>> 32;
        long d3 = PI_O_4_BITS[0] & 4294967295L;
        long ac4 = a2 * c3;
        long bd2 = b2 * d3;
        long bc2 = b2 * c3;
        long ad2 = a2 * d3;
        long prod2B = bd2 + (ad2 << 32);
        long prod2A = ac4 + (ad2 >>> 32);
        boolean bita4 = (bd2 & Long.MIN_VALUE) != 0;
        boolean bitb4 = (ad2 & ShimmerObject.SDLogHeader.MPL_QUAT_6DOF) != 0;
        boolean bitsum4 = (prod2B & Long.MIN_VALUE) != 0;
        if ((bita4 && bitb4) || ((bita4 || bitb4) && !bitsum4)) {
            prod2A++;
        }
        boolean bita5 = (prod2B & Long.MIN_VALUE) != 0;
        boolean bitb5 = (bc2 & ShimmerObject.SDLogHeader.MPL_QUAT_6DOF) != 0;
        long prod2B2 = prod2B + (bc2 << 32);
        long prod2A2 = prod2A + (bc2 >>> 32);
        boolean bitsum5 = (prod2B2 & Long.MIN_VALUE) != 0;
        if ((bita5 && bitb5) || ((bita5 || bitb5) && !bitsum5)) {
            prod2A2++;
        }
        long c4 = PI_O_4_BITS[1] >>> 32;
        long ac5 = a2 * c4;
        long ac6 = ac5 + (((b2 * c4) + (a2 * (PI_O_4_BITS[1] & 4294967295L))) >>> 32);
        boolean bita6 = (prod2B2 & Long.MIN_VALUE) != 0;
        boolean bitb6 = (ac6 & Long.MIN_VALUE) != 0;
        long prod2B3 = prod2B2 + ac6;
        boolean bitsum6 = (prod2B3 & Long.MIN_VALUE) != 0;
        if ((bita6 && bitb6) || ((bita6 || bitb6) && !bitsum6)) {
            prod2A2++;
        }
        long a3 = prodB4 >>> 32;
        long b3 = prodB4 & 4294967295L;
        long c5 = PI_O_4_BITS[0] >>> 32;
        long ac7 = a3 * c5;
        long ac8 = ac7 + (((b3 * c5) + (a3 * (PI_O_4_BITS[0] & 4294967295L))) >>> 32);
        boolean bita7 = (prod2B3 & Long.MIN_VALUE) != 0;
        boolean bitb7 = (ac8 & Long.MIN_VALUE) != 0;
        boolean bitsum7 = ((prod2B3 + ac8) & Long.MIN_VALUE) != 0;
        if ((bita7 && bitb7) || ((bita7 || bitb7) && !bitsum7)) {
            prod2A2++;
        }
        double tmpA = (prod2A2 >>> 12) / TWO_POWER_52;
        double tmpB = ((((prod2A2 & 4095) << 40) + (r0 >>> 24)) / TWO_POWER_52) / TWO_POWER_52;
        double sumA = tmpA + tmpB;
        double sumB = -((sumA - tmpA) - tmpB);
        result[0] = intPart;
        result[1] = sumA * 2.0d;
        result[2] = sumB * 2.0d;
    }

    public static double sin(double x) {
        double remA;
        double remB;
        boolean negative = false;
        int quadrant = 0;
        double xb = 0.0d;
        double xa = x;
        if (x < 0.0d) {
            negative = true;
            xa = -xa;
        }
        if (xa == 0.0d) {
            long bits = Double.doubleToLongBits(x);
            if (bits < 0) {
                return -0.0d;
            }
            return 0.0d;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            int k = (int) (xa * 0.6366197723675814d);
            while (true) {
                double a = (-k) * 1.570796251296997d;
                double remA2 = xa + a;
                double remB2 = -((remA2 - xa) - a);
                double a2 = (-k) * 7.549789948768648E-8d;
                double remA3 = a2 + remA2;
                double remB3 = remB2 + (-((remA3 - remA2) - a2));
                double a3 = (-k) * 6.123233995736766E-17d;
                remA = a3 + remA3;
                remB = remB3 + (-((remA - remA3) - a3));
                if (remA > 0.0d) {
                    break;
                }
                k--;
            }
            quadrant = k & 3;
            xa = remA;
            xb = remB;
        }
        if (negative) {
            quadrant ^= 2;
        }
        switch (quadrant) {
            case 0:
                return sinQ(xa, xb);
            case 1:
                return cosQ(xa, xb);
            case 2:
                return -sinQ(xa, xb);
            case 3:
                return -cosQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    public static double cos(double x) {
        double remA;
        double remB;
        int quadrant = 0;
        double xa = x;
        if (x < 0.0d) {
            xa = -xa;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        double xb = 0.0d;
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            int k = (int) (xa * 0.6366197723675814d);
            while (true) {
                double a = (-k) * 1.570796251296997d;
                double remA2 = xa + a;
                double remB2 = -((remA2 - xa) - a);
                double a2 = (-k) * 7.549789948768648E-8d;
                double remA3 = a2 + remA2;
                double remB3 = remB2 + (-((remA3 - remA2) - a2));
                double a3 = (-k) * 6.123233995736766E-17d;
                remA = a3 + remA3;
                remB = remB3 + (-((remA - remA3) - a3));
                if (remA > 0.0d) {
                    break;
                }
                k--;
            }
            quadrant = k & 3;
            xa = remA;
            xb = remB;
        }
        switch (quadrant) {
            case 0:
                return cosQ(xa, xb);
            case 1:
                return -sinQ(xa, xb);
            case 2:
                return -cosQ(xa, xb);
            case 3:
                return sinQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    public static double tan(double x) {
        double remA;
        double remB;
        double result;
        boolean negative = false;
        int quadrant = 0;
        double xa = x;
        if (x < 0.0d) {
            negative = true;
            xa = -xa;
        }
        if (xa == 0.0d) {
            long bits = Double.doubleToLongBits(x);
            if (bits < 0) {
                return -0.0d;
            }
            return 0.0d;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        double xb = 0.0d;
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            int k = (int) (xa * 0.6366197723675814d);
            while (true) {
                double a = (-k) * 1.570796251296997d;
                double remA2 = xa + a;
                double remB2 = -((remA2 - xa) - a);
                double a2 = (-k) * 7.549789948768648E-8d;
                double remA3 = a2 + remA2;
                double remB3 = remB2 + (-((remA3 - remA2) - a2));
                double a3 = (-k) * 6.123233995736766E-17d;
                remA = a3 + remA3;
                remB = remB3 + (-((remA - remA3) - a3));
                if (remA > 0.0d) {
                    break;
                }
                k--;
            }
            quadrant = k & 3;
            xa = remA;
            xb = remB;
        }
        if (xa > 1.5d) {
            double a4 = 1.5707963267948966d - xa;
            double b = (-((a4 - 1.5707963267948966d) + xa)) + (6.123233995736766E-17d - xb);
            xa = a4 + b;
            xb = -((xa - a4) - b);
            quadrant ^= 1;
            negative = !negative;
        }
        if ((quadrant & 1) == 0) {
            result = tanQ(xa, xb, false);
        } else {
            result = -tanQ(xa, xb, true);
        }
        if (negative) {
            result = -result;
        }
        return result;
    }

    public static double atan(double x) {
        return atan(x, 0.0d, false);
    }

    private static double atan(double xa, double xb, boolean leftPlane) {
        int idx;
        double ya;
        double yb;
        boolean negate = false;
        if (xa == 0.0d) {
            return leftPlane ? copySign(3.141592653589793d, xa) : xa;
        }
        if (xa < 0.0d) {
            xa = -xa;
            xb = -xb;
            negate = true;
        }
        if (xa > 1.633123935319537E16d) {
            return negate ^ leftPlane ? -1.5707963267948966d : 1.5707963267948966d;
        }
        if (xa < 1.0d) {
            idx = (int) (((((-1.7168146928204135d) * xa * xa) + 8.0d) * xa) + 0.5d);
        } else {
            double temp = 1.0d / xa;
            idx = (int) ((-((((-1.7168146928204135d) * temp * temp) + 8.0d) * temp)) + 13.07d);
        }
        double epsA = xa - TANGENT_TABLE_A[idx];
        double epsB = (-((epsA - xa) + TANGENT_TABLE_A[idx])) + (xb - TANGENT_TABLE_B[idx]);
        double temp2 = epsA + epsB;
        double epsB2 = -((temp2 - epsA) - epsB);
        double temp3 = xa * 1.073741824E9d;
        double ya2 = (xa + temp3) - temp3;
        double yb2 = (xb + xa) - ya2;
        double xb2 = xb + yb2;
        if (idx == 0) {
            double denom = 1.0d / (1.0d + ((ya2 + xb2) * (TANGENT_TABLE_A[idx] + TANGENT_TABLE_B[idx])));
            ya = temp2 * denom;
            yb = epsB2 * denom;
        } else {
            double temp22 = ya2 * TANGENT_TABLE_A[idx];
            double za = 1.0d + temp22;
            double zb = -((za - 1.0d) - temp22);
            double temp23 = (xb2 * TANGENT_TABLE_A[idx]) + (ya2 * TANGENT_TABLE_B[idx]);
            double temp4 = za + temp23;
            double zb2 = zb + (-((temp4 - za) - temp23)) + (xb2 * TANGENT_TABLE_B[idx]);
            ya = temp2 / temp4;
            double temp5 = ya * 1.073741824E9d;
            double yaa = (ya + temp5) - temp5;
            double yab = ya - yaa;
            double temp6 = temp4 * 1.073741824E9d;
            double zaa = (temp4 + temp6) - temp6;
            double zab = temp4 - zaa;
            double yb3 = ((((temp2 - (yaa * zaa)) - (yaa * zab)) - (yab * zaa)) - (yab * zab)) / temp4;
            yb = yb3 + ((((-temp2) * zb2) / temp4) / temp4) + (epsB2 / temp4);
        }
        double epsA2 = ya;
        double epsA22 = epsA2 * epsA2;
        double yb4 = (0.07490822288864472d * epsA22) - 0.09088450866185192d;
        double yb5 = ((((((((yb4 * epsA22) + 0.11111095942313305d) * epsA22) - 0.1428571423679182d) * epsA22) + 0.19999999999923582d) * epsA22) - 0.33333333333333287d) * epsA22 * epsA2;
        double temp7 = epsA2 + yb5;
        double yb6 = (-((temp7 - epsA2) - yb5)) + (yb / (1.0d + (epsA2 * epsA2)));
        double za2 = EIGHTHS[idx] + temp7;
        double zb3 = -((za2 - EIGHTHS[idx]) - temp7);
        double temp8 = za2 + yb6;
        double zb4 = zb3 + (-((temp8 - za2) - yb6));
        double result = temp8 + zb4;
        double resultb = -((result - temp8) - zb4);
        if (leftPlane) {
            double za3 = 3.141592653589793d - result;
            double zb5 = -((za3 - 3.141592653589793d) + result);
            double zb6 = zb5 + (1.2246467991473532E-16d - resultb);
            result = za3 + zb6;
            double d = -((result - za3) - zb6);
        }
        if (negate ^ leftPlane) {
            result = -result;
        }
        return result;
    }

    public static double atan2(double y, double x) {
        if (x != x || y != y) {
            return Double.NaN;
        }
        if (y == 0.0d) {
            double result = x * y;
            double invx = 1.0d / x;
            double invy = 1.0d / y;
            if (invx == 0.0d) {
                if (x > 0.0d) {
                    return y;
                }
                return copySign(3.141592653589793d, y);
            }
            if (x >= 0.0d && invx >= 0.0d) {
                return result;
            }
            if (y < 0.0d || invy < 0.0d) {
                return -3.141592653589793d;
            }
            return 3.141592653589793d;
        }
        if (y == Double.POSITIVE_INFINITY) {
            if (x == Double.POSITIVE_INFINITY) {
                return 0.7853981633974483d;
            }
            if (x == Double.NEGATIVE_INFINITY) {
                return 2.356194490192345d;
            }
            return 1.5707963267948966d;
        }
        if (y == Double.NEGATIVE_INFINITY) {
            if (x == Double.POSITIVE_INFINITY) {
                return -0.7853981633974483d;
            }
            if (x == Double.NEGATIVE_INFINITY) {
                return -2.356194490192345d;
            }
            return -1.5707963267948966d;
        }
        if (x == Double.POSITIVE_INFINITY) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 0.0d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -0.0d;
            }
        }
        if (x == Double.NEGATIVE_INFINITY) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 3.141592653589793d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -3.141592653589793d;
            }
        }
        if (x == 0.0d) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 1.5707963267948966d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -1.5707963267948966d;
            }
        }
        double r = y / x;
        if (Double.isInfinite(r)) {
            return atan(r, 0.0d, x < 0.0d);
        }
        double ra = doubleHighPart(r);
        double rb = r - ra;
        double xa = doubleHighPart(x);
        double xb = x - xa;
        double rb2 = rb + (((((y - (ra * xa)) - (ra * xb)) - (rb * xa)) - (rb * xb)) / x);
        double temp = ra + rb2;
        double rb3 = -((temp - ra) - rb2);
        double ra2 = temp;
        if (ra2 == 0.0d) {
            ra2 = copySign(0.0d, y);
        }
        double result2 = atan(ra2, rb3, x < 0.0d);
        return result2;
    }

    public static double asin(double x) {
        if (x != x || x > 1.0d || x < -1.0d) {
            return Double.NaN;
        }
        if (x == 1.0d) {
            return 1.5707963267948966d;
        }
        if (x == -1.0d) {
            return -1.5707963267948966d;
        }
        if (x == 0.0d) {
            return x;
        }
        double temp = x * 1.073741824E9d;
        double xa = (x + temp) - temp;
        double xb = x - xa;
        double ya = xa * xa;
        double yb = (xa * xb * 2.0d) + (xb * xb);
        double ya2 = -ya;
        double yb2 = -yb;
        double za = 1.0d + ya2;
        double zb = -((za - 1.0d) - ya2);
        double temp2 = za + yb2;
        double zb2 = zb + (-((temp2 - za) - yb2));
        double y = sqrt(temp2);
        double temp3 = y * 1.073741824E9d;
        double ya3 = (y + temp3) - temp3;
        double yb3 = y - ya3;
        double yb4 = yb3 + ((((temp2 - (ya3 * ya3)) - ((2.0d * ya3) * yb3)) - (yb3 * yb3)) / (2.0d * y));
        double dx = zb2 / (2.0d * y);
        double r = x / y;
        double temp4 = r * 1.073741824E9d;
        double ra = (r + temp4) - temp4;
        double rb = r - ra;
        double rb2 = rb + (((((x - (ra * ya3)) - (ra * yb4)) - (rb * ya3)) - (rb * yb4)) / y) + ((((-x) * dx) / y) / y);
        double temp5 = ra + rb2;
        return atan(temp5, -((temp5 - ra) - rb2), false);
    }

    public static double acos(double x) {
        if (x != x || x > 1.0d || x < -1.0d) {
            return Double.NaN;
        }
        if (x == -1.0d) {
            return 3.141592653589793d;
        }
        if (x == 1.0d) {
            return 0.0d;
        }
        if (x == 0.0d) {
            return 1.5707963267948966d;
        }
        double temp = x * 1.073741824E9d;
        double xa = (x + temp) - temp;
        double xb = x - xa;
        double ya = xa * xa;
        double yb = (xa * xb * 2.0d) + (xb * xb);
        double ya2 = -ya;
        double yb2 = -yb;
        double za = 1.0d + ya2;
        double zb = -((za - 1.0d) - ya2);
        double temp2 = za + yb2;
        double zb2 = zb + (-((temp2 - za) - yb2));
        double y = sqrt(temp2);
        double temp3 = y * 1.073741824E9d;
        double ya3 = (y + temp3) - temp3;
        double yb3 = y - ya3;
        double yb4 = yb3 + ((((temp2 - (ya3 * ya3)) - ((2.0d * ya3) * yb3)) - (yb3 * yb3)) / (2.0d * y)) + (zb2 / (2.0d * y));
        double y2 = ya3 + yb4;
        double yb5 = -((y2 - ya3) - yb4);
        double r = y2 / x;
        if (Double.isInfinite(r)) {
            return 1.5707963267948966d;
        }
        double ra = doubleHighPart(r);
        double rb = r - ra;
        double rb2 = rb + (((((y2 - (ra * xa)) - (ra * xb)) - (rb * xa)) - (rb * xb)) / x) + (yb5 / x);
        double temp4 = ra + rb2;
        return atan(temp4, -((temp4 - ra) - rb2), x < 0.0d);
    }

    public static double cbrt(double x) {
        long inbits = Double.doubleToLongBits(x);
        int exponent = ((int) ((inbits >> 52) & 2047)) - 1023;
        boolean subnormal = false;
        if (exponent == -1023) {
            if (x == 0.0d) {
                return x;
            }
            subnormal = true;
            x *= 1.8014398509481984E16d;
            inbits = Double.doubleToLongBits(x);
            exponent = ((int) ((inbits >> 52) & 2047)) - 1023;
        }
        if (exponent == 1024) {
            return x;
        }
        int exp3 = exponent / 3;
        double p2 = Double.longBitsToDouble((inbits & Long.MIN_VALUE) | (((exp3 + 1023) & 2047) << 52));
        double mant = Double.longBitsToDouble((inbits & 4503599627370495L) | 4607182418800017408L);
        double est = ((-0.010714690733195933d) * mant) + 0.0875862700108075d;
        double est2 = ((((((est * mant) - 0.3058015757857271d) * mant) + 0.7249995199969751d) * mant) + 0.5039018405998233d) * CBRTTWO[(exponent % 3) + 2];
        double xs = x / ((p2 * p2) * p2);
        double est3 = est2 + ((xs - ((est2 * est2) * est2)) / ((3.0d * est2) * est2));
        double est4 = est3 + ((xs - ((est3 * est3) * est3)) / ((3.0d * est3) * est3));
        double temp = est4 * 1.073741824E9d;
        double ya = (est4 + temp) - temp;
        double yb = est4 - ya;
        double za = ya * ya;
        double zb = (ya * yb * 2.0d) + (yb * yb);
        double temp2 = za * 1.073741824E9d;
        double temp22 = (za + temp2) - temp2;
        double zb2 = zb + (za - temp22);
        double zb3 = (temp22 * yb) + (ya * zb2) + (zb2 * yb);
        double za2 = temp22 * ya;
        double na = xs - za2;
        double nb = -((na - xs) + za2);
        double est5 = (est4 + ((na + (nb - zb3)) / ((3.0d * est4) * est4))) * p2;
        if (subnormal) {
            est5 *= 3.814697265625E-6d;
        }
        return est5;
    }

    public static double toRadians(double x) {
        if (Double.isInfinite(x) || x == 0.0d) {
            return x;
        }
        double xa = doubleHighPart(x);
        double xb = x - xa;
        double result = (xb * 1.997844754509471E-9d) + (xb * 0.01745329052209854d) + (xa * 1.997844754509471E-9d) + (xa * 0.01745329052209854d);
        if (result == 0.0d) {
            result *= x;
        }
        return result;
    }

    public static double toDegrees(double x) {
        if (Double.isInfinite(x) || x == 0.0d) {
            return x;
        }
        double xa = doubleHighPart(x);
        double xb = x - xa;
        return (xb * 3.145894820876798E-6d) + (xb * 57.2957763671875d) + (xa * 3.145894820876798E-6d) + (xa * 57.2957763671875d);
    }

    public static int abs(int x) {
        return x < 0 ? -x : x;
    }

    public static long abs(long x) {
        return x < 0 ? -x : x;
    }

    public static float abs(float x) {
        if (x < 0.0f) {
            return -x;
        }
        if (x == 0.0f) {
            return 0.0f;
        }
        return x;
    }

    public static double abs(double x) {
        if (x < 0.0d) {
            return -x;
        }
        if (x == 0.0d) {
            return 0.0d;
        }
        return x;
    }

    public static double ulp(double x) {
        if (Double.isInfinite(x)) {
            return Double.POSITIVE_INFINITY;
        }
        return abs(x - Double.longBitsToDouble(Double.doubleToLongBits(x) ^ 1));
    }

    public static float ulp(float x) {
        if (Float.isInfinite(x)) {
            return Float.POSITIVE_INFINITY;
        }
        return abs(x - Float.intBitsToFloat(Float.floatToIntBits(x) ^ 1));
    }

    public static double scalb(double d, int n) {
        if (n > -1023 && n < 1024) {
            return d * Double.longBitsToDouble((n + 1023) << 52);
        }
        if (Double.isNaN(d) || Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        if (n < -2098) {
            return d > 0.0d ? 0.0d : -0.0d;
        }
        if (n > 2097) {
            return d > 0.0d ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        long bits = Double.doubleToLongBits(d);
        long sign = bits & Long.MIN_VALUE;
        int exponent = ((int) (bits >>> 52)) & 2047;
        long mantissa = bits & 4503599627370495L;
        int scaledExponent = exponent + n;
        if (n < 0) {
            if (scaledExponent > 0) {
                return Double.longBitsToDouble(sign | (scaledExponent << 52) | mantissa);
            }
            if (scaledExponent <= -53) {
                return sign == 0 ? 0.0d : -0.0d;
            }
            long mantissa2 = mantissa | 4503599627370496L;
            long mostSignificantLostBit = mantissa2 & (1 << (-scaledExponent));
            long mantissa3 = mantissa2 >>> (1 - scaledExponent);
            if (mostSignificantLostBit != 0) {
                mantissa3++;
            }
            return Double.longBitsToDouble(sign | mantissa3);
        }
        if (exponent == 0) {
            while ((mantissa >>> 52) != 1) {
                mantissa <<= 1;
                scaledExponent--;
            }
            int scaledExponent2 = scaledExponent + 1;
            long mantissa4 = mantissa & 4503599627370495L;
            if (scaledExponent2 < 2047) {
                return Double.longBitsToDouble(sign | (scaledExponent2 << 52) | mantissa4);
            }
            return sign == 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        if (scaledExponent < 2047) {
            return Double.longBitsToDouble(sign | (scaledExponent << 52) | mantissa);
        }
        return sign == 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public static float scalb(float f, int n) {
        if (n > -127 && n < 128) {
            return f * Float.intBitsToFloat((n + 127) << 23);
        }
        if (Float.isNaN(f) || Float.isInfinite(f) || f == 0.0f) {
            return f;
        }
        if (n < -277) {
            return f > 0.0f ? 0.0f : -0.0f;
        }
        if (n > 276) {
            return f > 0.0f ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        int bits = Float.floatToIntBits(f);
        int sign = bits & Integer.MIN_VALUE;
        int exponent = (bits >>> 23) & 255;
        int mantissa = bits & Parameter.B_III_P;
        int scaledExponent = exponent + n;
        if (n < 0) {
            if (scaledExponent > 0) {
                return Float.intBitsToFloat(sign | (scaledExponent << 23) | mantissa);
            }
            if (scaledExponent <= -24) {
                return sign == 0 ? 0.0f : -0.0f;
            }
            int mantissa2 = mantissa | 8388608;
            int mostSignificantLostBit = mantissa2 & (1 << (-scaledExponent));
            int mantissa3 = mantissa2 >>> (1 - scaledExponent);
            if (mostSignificantLostBit != 0) {
                mantissa3++;
            }
            return Float.intBitsToFloat(sign | mantissa3);
        }
        if (exponent == 0) {
            while ((mantissa >>> 23) != 1) {
                mantissa <<= 1;
                scaledExponent--;
            }
            int scaledExponent2 = scaledExponent + 1;
            int mantissa4 = mantissa & Parameter.B_III_P;
            if (scaledExponent2 < 255) {
                return Float.intBitsToFloat(sign | (scaledExponent2 << 23) | mantissa4);
            }
            return sign == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        if (scaledExponent < 255) {
            return Float.intBitsToFloat(sign | (scaledExponent << 23) | mantissa);
        }
        return sign == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
    }

    public static double nextAfter(double d, double direction) {
        if (Double.isNaN(d) || Double.isNaN(direction)) {
            return Double.NaN;
        }
        if (d == direction) {
            return direction;
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? -1.7976931348623157E308d : Double.MAX_VALUE;
        }
        if (d == 0.0d) {
            return direction < 0.0d ? -4.9E-324d : Double.MIN_VALUE;
        }
        long bits = Double.doubleToLongBits(d);
        long sign = bits & Long.MIN_VALUE;
        if ((direction < d) ^ (sign == 0)) {
            return Double.longBitsToDouble(sign | ((bits & Long.MAX_VALUE) + 1));
        }
        return Double.longBitsToDouble(sign | ((bits & Long.MAX_VALUE) - 1));
    }

    public static float nextAfter(float f, double direction) {
        if (Double.isNaN(f) || Double.isNaN(direction)) {
            return Float.NaN;
        }
        if (f == direction) {
            return (float) direction;
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? -3.4028235E38f : Float.MAX_VALUE;
        }
        if (f == 0.0f) {
            return direction < 0.0d ? -1.4E-45f : Float.MIN_VALUE;
        }
        int bits = Float.floatToIntBits(f);
        int sign = bits & Integer.MIN_VALUE;
        if ((direction < ((double) f)) ^ (sign == 0)) {
            return Float.intBitsToFloat(sign | ((bits & Integer.MAX_VALUE) + 1));
        }
        return Float.intBitsToFloat(sign | ((bits & Integer.MAX_VALUE) - 1));
    }

    public static double floor(double x) {
        if (x != x) {
            return x;
        }
        if (x >= TWO_POWER_52 || x <= -4.503599627370496E15d) {
            return x;
        }
        long y = (long) x;
        if (x < 0.0d && y != x) {
            y--;
        }
        if (y == 0) {
            return x * y;
        }
        return y;
    }

    public static double ceil(double x) {
        if (x != x) {
            return x;
        }
        double y = floor(x);
        if (y == x) {
            return y;
        }
        double y2 = y + 1.0d;
        if (y2 == 0.0d) {
            return x * y2;
        }
        return y2;
    }

    public static double rint(double x) {
        double y = floor(x);
        double d = x - y;
        if (d > 0.5d) {
            if (y == -1.0d) {
                return -0.0d;
            }
            return y + 1.0d;
        }
        if (d < 0.5d) {
            return y;
        }
        long z = (long) y;
        return (z & 1) == 0 ? y : y + 1.0d;
    }

    public static long round(double x) {
        return (long) floor(x + 0.5d);
    }

    public static int round(float x) {
        return (int) floor(x + 0.5f);
    }

    public static int min(int a, int b) {
        return a <= b ? a : b;
    }

    public static long min(long a, long b) {
        return a <= b ? a : b;
    }

    public static float min(float a, float b) {
        if (a > b) {
            return b;
        }
        if (a < b) {
            return a;
        }
        if (a != b) {
            return Float.NaN;
        }
        int bits = Float.floatToRawIntBits(a);
        if (bits == Integer.MIN_VALUE) {
            return a;
        }
        return b;
    }

    public static double min(double a, double b) {
        if (a > b) {
            return b;
        }
        if (a < b) {
            return a;
        }
        if (a != b) {
            return Double.NaN;
        }
        long bits = Double.doubleToRawLongBits(a);
        if (bits == Long.MIN_VALUE) {
            return a;
        }
        return b;
    }

    public static int max(int a, int b) {
        return a <= b ? b : a;
    }

    public static long max(long a, long b) {
        return a <= b ? b : a;
    }

    public static float max(float a, float b) {
        if (a > b) {
            return a;
        }
        if (a < b) {
            return b;
        }
        if (a != b) {
            return Float.NaN;
        }
        int bits = Float.floatToRawIntBits(a);
        if (bits == Integer.MIN_VALUE) {
            return b;
        }
        return a;
    }

    public static double max(double a, double b) {
        if (a > b) {
            return a;
        }
        if (a < b) {
            return b;
        }
        if (a != b) {
            return Double.NaN;
        }
        long bits = Double.doubleToRawLongBits(a);
        if (bits == Long.MIN_VALUE) {
            return b;
        }
        return a;
    }

    public static double hypot(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y)) {
            return Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(x) || Double.isNaN(y)) {
            return Double.NaN;
        }
        int expX = getExponent(x);
        int expY = getExponent(y);
        if (expX > expY + 27) {
            return abs(x);
        }
        if (expY > expX + 27) {
            return abs(y);
        }
        int middleExp = (expX + expY) / 2;
        double scaledX = scalb(x, -middleExp);
        double scaledY = scalb(y, -middleExp);
        double scaledH = sqrt((scaledX * scaledX) + (scaledY * scaledY));
        return scalb(scaledH, middleExp);
    }

    public static double IEEEremainder(double dividend, double divisor) {
        return StrictMath.IEEEremainder(dividend, divisor);
    }

    public static double copySign(double magnitude, double sign) {
        long m = Double.doubleToLongBits(magnitude);
        long s = Double.doubleToLongBits(sign);
        if ((m >= 0 && s >= 0) || (m < 0 && s < 0)) {
            return magnitude;
        }
        return -magnitude;
    }

    public static float copySign(float magnitude, float sign) {
        int m = Float.floatToIntBits(magnitude);
        int s = Float.floatToIntBits(sign);
        if ((m >= 0 && s >= 0) || (m < 0 && s < 0)) {
            return magnitude;
        }
        return -magnitude;
    }

    public static int getExponent(double d) {
        return ((int) ((Double.doubleToLongBits(d) >>> 52) & 2047)) - 1023;
    }

    public static int getExponent(float f) {
        return ((Float.floatToIntBits(f) >>> 23) & 255) - 127;
    }
}
