package com.google.re2j;

import com.google.re2j.Inst;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
class Prog {
    private final List<Inst> inst = new ArrayList();
    int numCap = 2;
    int start;

    Prog() {
    }

    Inst getInst(int i) {
        return this.inst.get(i);
    }

    int numInst() {
        return this.inst.size();
    }

    void addInst(Inst.Op op) {
        this.inst.add(new Inst(op));
    }

    Inst skipNop(int i) {
        Inst inst = this.inst.get(i);
        while (true) {
            if (inst.op != Inst.Op.NOP && inst.op != Inst.Op.CAPTURE) {
                return inst;
            }
            inst = this.inst.get(i);
            i = inst.out;
        }
    }

    boolean prefix(StringBuilder sb) {
        Inst instSkipNop = skipNop(this.start);
        if (instSkipNop.op() != Inst.Op.RUNE || instSkipNop.runes.length != 1) {
            return instSkipNop.op == Inst.Op.MATCH;
        }
        while (instSkipNop.op() == Inst.Op.RUNE && instSkipNop.runes.length == 1 && (instSkipNop.arg & 1) == 0) {
            sb.appendCodePoint(instSkipNop.runes[0]);
            instSkipNop = skipNop(instSkipNop.out);
        }
        return instSkipNop.op == Inst.Op.MATCH;
    }

    int startCond() {
        int i = this.start;
        int i2 = 0;
        while (true) {
            Inst inst = this.inst.get(i);
            int i3 = AnonymousClass1.$SwitchMap$com$google$re2j$Inst$Op[inst.op.ordinal()];
            if (i3 == 1) {
                i2 |= inst.arg;
            } else {
                if (i3 == 2) {
                    return -1;
                }
                if (i3 != 3 && i3 != 4) {
                    return i2;
                }
            }
            i = inst.out;
        }
    }

    int next(int i) {
        Inst inst = this.inst.get(i >> 1);
        if ((i & 1) == 0) {
            return inst.out;
        }
        return inst.arg;
    }

    void patch(int i, int i2) {
        while (i != 0) {
            Inst inst = this.inst.get(i >> 1);
            if ((i & 1) == 0) {
                i = inst.out;
                inst.out = i2;
            } else {
                i = inst.arg;
                inst.arg = i2;
            }
        }
    }

    int append(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int i3 = i;
        while (true) {
            int next = next(i3);
            if (next == 0) {
                break;
            }
            i3 = next;
        }
        Inst inst = this.inst.get(i3 >> 1);
        if ((i3 & 1) == 0) {
            inst.out = i2;
        } else {
            inst.arg = i2;
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.inst.size(); i++) {
            int length = sb.length();
            sb.append(i);
            if (i == this.start) {
                sb.append('*');
            }
            sb.append("        ".substring(sb.length() - length));
            sb.append(this.inst.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }

    /* renamed from: com.google.re2j.Prog$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$re2j$Inst$Op;

        static {
            int[] iArr = new int[Inst.Op.values().length];
            $SwitchMap$com$google$re2j$Inst$Op = iArr;
            try {
                iArr[Inst.Op.EMPTY_WIDTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.CAPTURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.NOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
