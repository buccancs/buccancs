package org.apache.commons.math3.special;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class Erf {
    private static final double X_CRIT = 0.4769362762044697d;

    private Erf() {
    }

    public static double erf(double d) {
        if (FastMath.abs(d) > 40.0d) {
            return d > 0.0d ? 1.0d : -1.0d;
        }
        double dRegularizedGammaP = Gamma.regularizedGammaP(0.5d, d * d, 1.0E-15d, 10000);
        return d < 0.0d ? -dRegularizedGammaP : dRegularizedGammaP;
    }

    public static double erfc(double d) {
        if (FastMath.abs(d) > 40.0d) {
            return d > 0.0d ? 0.0d : 2.0d;
        }
        double dRegularizedGammaQ = Gamma.regularizedGammaQ(0.5d, d * d, 1.0E-15d, 10000);
        return d < 0.0d ? 2.0d - dRegularizedGammaQ : dRegularizedGammaQ;
    }

    public static double erf(double d, double d2) {
        double dErf;
        double dErf2;
        if (d > d2) {
            return -erf(d2, d);
        }
        if (d < -0.4769362762044697d) {
            if (d2 < 0.0d) {
                dErf = erfc(-d2);
                dErf2 = erfc(-d);
            }
            return dErf - dErf2;
        }
        if (d2 > X_CRIT && d > 0.0d) {
            return erfc(d) - erfc(d2);
        }
        dErf = erf(d2);
        dErf2 = erf(d);
        return dErf - dErf2;
    }

    public static double erfInv(double d) {
        double d2;
        double dSqrt;
        double d3;
        double d4;
        double d5 = -FastMath.log((1.0d - d) * (1.0d + d));
        if (d5 < 6.25d) {
            dSqrt = d5 - 3.125d;
            d3 = ((((((((((((((((((((((((((((((((((((((((((-3.64441206401782E-21d) * dSqrt) - 1.6850591381820166E-19d) * dSqrt) + 1.28584807152564E-18d) * dSqrt) + 1.1157877678025181E-17d) * dSqrt) - 1.333171662854621E-16d) * dSqrt) + 2.0972767875968562E-17d) * dSqrt) + 6.637638134358324E-15d) * dSqrt) - 4.054566272975207E-14d) * dSqrt) - 8.151934197605472E-14d) * dSqrt) + 2.6335093153082323E-12d) * dSqrt) - 1.2975133253453532E-11d) * dSqrt) - 5.415412054294628E-11d) * dSqrt) + 1.0512122733215323E-9d) * dSqrt) - 4.112633980346984E-9d) * dSqrt) - 2.9070369957882005E-8d) * dSqrt) + 4.2347877827932404E-7d) * dSqrt) - 1.3654692000834679E-6d) * dSqrt) - 1.3882523362786469E-5d) * dSqrt) + 1.8673420803405714E-4d) * dSqrt) - 7.40702534166267E-4d) * dSqrt) - 0.006033670871430149d) * dSqrt) + 0.24015818242558962d;
            d4 = 1.6536545626831027d;
        } else if (d5 < 16.0d) {
            dSqrt = FastMath.sqrt(d5) - 3.25d;
            d3 = (((((((((((((((((((((((((((((((((2.2137376921775787E-9d * dSqrt) + 9.075656193888539E-8d) * dSqrt) - 2.7517406297064545E-7d) * dSqrt) + 1.8239629214389228E-8d) * dSqrt) + 1.5027403968909828E-6d) * dSqrt) - 4.013867526981546E-6d) * dSqrt) + 2.9234449089955446E-6d) * dSqrt) + 1.2475304481671779E-5d) * dSqrt) - 4.7318229009055734E-5d) * dSqrt) + 6.828485145957318E-5d) * dSqrt) + 2.4031110387097894E-5d) * dSqrt) - 3.550375203628475E-4d) * dSqrt) + 9.532893797373805E-4d) * dSqrt) - 0.0016882755560235047d) * dSqrt) + 0.002491442096107851d) * dSqrt) - 0.003751208507569241d) * dSqrt) + 0.005370914553590064d) * dSqrt) + 1.0052589676941592d;
            d4 = 3.0838856104922208d;
        } else {
            if (Double.isInfinite(d5)) {
                d2 = Double.POSITIVE_INFINITY;
                return d2 * d;
            }
            dSqrt = FastMath.sqrt(d5) - 5.0d;
            d3 = ((((((((((((((((((((((((((((((-2.7109920616438573E-11d) * dSqrt) - 2.555641816996525E-10d) * dSqrt) + 1.5076572693500548E-9d) * dSqrt) - 3.789465440126737E-9d) * dSqrt) + 7.61570120807834E-9d) * dSqrt) - 1.496002662714924E-8d) * dSqrt) + 2.914795345090108E-8d) * dSqrt) - 6.771199775845234E-8d) * dSqrt) + 2.2900482228026655E-7d) * dSqrt) - 9.9298272942317E-7d) * dSqrt) + 4.526062597223154E-6d) * dSqrt) - 1.968177810553167E-5d) * dSqrt) + 7.599527703001776E-5d) * dSqrt) - 2.1503011930044477E-4d) * dSqrt) - 1.3871931833623122E-4d) * dSqrt) + 1.0103004648645344d;
            d4 = 4.849906401408584d;
        }
        d2 = (d3 * dSqrt) + d4;
        return d2 * d;
    }

    public static double erfcInv(double d) {
        return erfInv(1.0d - d);
    }
}
