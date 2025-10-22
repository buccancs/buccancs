package com.google.re2j;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.message.TokenParser;

/* loaded from: classes2.dex */
class RE2 {
    static final int ANCHOR_BOTH = 2;
    static final int ANCHOR_START = 1;
    static final int CLASS_NL = 4;
    static final int DOT_NL = 8;
    static final int FOLD_CASE = 1;
    static final int LITERAL = 2;
    static final int MATCH_NL = 12;
    static final int NON_GREEDY = 32;
    static final int ONE_LINE = 16;
    static final int PERL = 212;
    static final int PERL_X = 64;
    static final int POSIX = 0;
    static final int UNANCHORED = 0;
    static final int UNICODE_GROUPS = 128;
    static final int WAS_DOLLAR = 256;
    final int cond;
    final String expr;
    final int numSubexp;
    final Prog prog;
    private final List<Machine> machine = new ArrayList();
    boolean longest;
    String prefix;
    boolean prefixComplete;
    int prefixRune;
    byte[] prefixUTF8;

    RE2(String str) throws PatternSyntaxException {
        RE2 re2Compile = compile(str);
        this.expr = re2Compile.expr;
        this.prog = re2Compile.prog;
        this.cond = re2Compile.cond;
        this.numSubexp = re2Compile.numSubexp;
        this.longest = re2Compile.longest;
        this.prefix = re2Compile.prefix;
        this.prefixUTF8 = re2Compile.prefixUTF8;
        this.prefixComplete = re2Compile.prefixComplete;
        this.prefixRune = re2Compile.prefixRune;
    }

    private RE2(String str, Prog prog, int i, boolean z) {
        this.expr = str;
        this.prog = prog;
        this.numSubexp = i;
        this.cond = prog.startCond();
        this.longest = z;
    }

    static RE2 compile(String str) throws PatternSyntaxException {
        return compileImpl(str, PERL, false);
    }

    static RE2 compilePOSIX(String str) throws PatternSyntaxException {
        return compileImpl(str, 0, true);
    }

    static RE2 compileImpl(String str, int i, boolean z) throws PatternSyntaxException {
        Regexp regexp = Parser.parse(str, i);
        int iMaxCap = regexp.maxCap();
        Prog progCompileRegexp = Compiler.compileRegexp(Simplify.simplify(regexp));
        RE2 re2 = new RE2(str, progCompileRegexp, iMaxCap, z);
        StringBuilder sb = new StringBuilder();
        re2.prefixComplete = progCompileRegexp.prefix(sb);
        String string = sb.toString();
        re2.prefix = string;
        try {
            re2.prefixUTF8 = string.getBytes("UTF-8");
            if (!re2.prefix.isEmpty()) {
                re2.prefixRune = re2.prefix.codePointAt(0);
            }
            return re2;
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("can't happen");
        }
    }

    static boolean match(String str, CharSequence charSequence) throws PatternSyntaxException {
        return compile(str).match(charSequence);
    }

    static String quoteMeta(String str) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ("\\.+*?()|[]{}^$".indexOf(cCharAt) >= 0) {
                sb.append(TokenParser.ESCAPE);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    int numberOfCapturingGroups() {
        return this.numSubexp;
    }

    public String toString() {
        return this.expr;
    }

    synchronized Machine get() {
        int size = this.machine.size();
        if (size > 0) {
            return this.machine.remove(size - 1);
        }
        return new Machine(this);
    }

    synchronized void reset() {
        this.machine.clear();
    }

    synchronized void put(Machine machine) {
        this.machine.add(machine);
    }

    private int[] doExecute(MachineInput machineInput, int i, int i2, int i3) {
        Machine machine = get();
        machine.init(i3);
        int[] iArrSubmatches = machine.match(machineInput, i, i2) ? machine.submatches() : null;
        put(machine);
        return iArrSubmatches;
    }

    boolean match(CharSequence charSequence) {
        return doExecute(MachineInput.fromUTF16(charSequence), 0, 0, 0) != null;
    }

    boolean match(CharSequence charSequence, int i, int i2, int i3, int[] iArr, int i4) {
        int[] iArrDoExecute;
        if (i > i2 || (iArrDoExecute = doExecute(MachineInput.fromUTF16(charSequence, 0, i2), i, i3, i4 * 2)) == null) {
            return false;
        }
        if (iArr == null) {
            return true;
        }
        System.arraycopy(iArrDoExecute, 0, iArr, 0, iArrDoExecute.length);
        return true;
    }

    boolean matchUTF8(byte[] bArr) {
        return doExecute(MachineInput.fromUTF8(bArr), 0, 0, 0) != null;
    }

    String replaceAll(String str, final String str2) {
        return replaceAllFunc(str, new ReplaceFunc() { // from class: com.google.re2j.RE2.1
            @Override // com.google.re2j.RE2.ReplaceFunc
            public String replace(String str3) {
                return str2;
            }
        }, (str.length() * 2) + 1);
    }

    String replaceFirst(String str, final String str2) {
        return replaceAllFunc(str, new ReplaceFunc() { // from class: com.google.re2j.RE2.2
            @Override // com.google.re2j.RE2.ReplaceFunc
            public String replace(String str3) {
                return str2;
            }
        }, 1);
    }

    String replaceAllFunc(String str, ReplaceFunc replaceFunc, int i) {
        int[] iArrDoExecute;
        StringBuilder sb = new StringBuilder();
        MachineInput machineInputFromUTF16 = MachineInput.fromUTF16(str);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 <= str.length() && (iArrDoExecute = doExecute(machineInputFromUTF16, i2, 0, 2)) != null && iArrDoExecute.length != 0) {
            sb.append(str.substring(i3, iArrDoExecute[0]));
            int i5 = iArrDoExecute[1];
            if (i5 > i3 || iArrDoExecute[0] == 0) {
                sb.append(replaceFunc.replace(str.substring(iArrDoExecute[0], i5)));
                i4++;
            }
            i3 = iArrDoExecute[1];
            int iStep = (machineInputFromUTF16.step(i2) & 7) + i2;
            int i6 = iArrDoExecute[1];
            if (iStep > i6) {
                i2 = iStep;
            } else {
                i2++;
                if (i2 <= i6) {
                    i2 = i6;
                }
            }
            if (i4 >= i) {
                break;
            }
        }
        sb.append(str.substring(i3));
        return sb.toString();
    }

    private int[] pad(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int i = (this.numSubexp + 1) * 2;
        if (iArr.length >= i) {
            return iArr;
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        Arrays.fill(iArr2, iArr.length, i, -1);
        return iArr2;
    }

    private void allMatches(MachineInput machineInput, int i, DeliverFunc deliverFunc) {
        boolean z;
        int iEndPos = machineInput.endPos();
        if (i < 0) {
            i = iEndPos + 1;
        }
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i && i4 <= iEndPos) {
            int[] iArrDoExecute = doExecute(machineInput, i4, 0, this.prog.numCap);
            if (iArrDoExecute == null || iArrDoExecute.length == 0) {
                return;
            }
            int i5 = iArrDoExecute[1];
            if (i5 == i4) {
                z = iArrDoExecute[0] != i2;
                int iStep = machineInput.step(i4);
                i4 = iStep < 0 ? iEndPos + 1 : i4 + (iStep & 7);
            } else {
                i4 = i5;
                z = true;
            }
            int i6 = iArrDoExecute[1];
            if (z) {
                deliverFunc.deliver(pad(iArrDoExecute));
                i3++;
            }
            i2 = i6;
        }
    }

    byte[] findUTF8(byte[] bArr) {
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF8(bArr), 0, 0, 2);
        if (iArrDoExecute == null) {
            return null;
        }
        return Utils.subarray(bArr, iArrDoExecute[0], iArrDoExecute[1]);
    }

    int[] findUTF8Index(byte[] bArr) {
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF8(bArr), 0, 0, 2);
        if (iArrDoExecute == null) {
            return null;
        }
        return Utils.subarray(iArrDoExecute, 0, 2);
    }

    String find(String str) {
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF16(str), 0, 0, 2);
        return iArrDoExecute == null ? "" : str.substring(iArrDoExecute[0], iArrDoExecute[1]);
    }

    int[] findIndex(String str) {
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF16(str), 0, 0, 2);
        if (iArrDoExecute == null) {
            return null;
        }
        return iArrDoExecute;
    }

    byte[][] findUTF8Submatch(byte[] bArr) {
        int i;
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF8(bArr), 0, 0, this.prog.numCap);
        if (iArrDoExecute == null) {
            return null;
        }
        int i2 = this.numSubexp + 1;
        byte[][] bArr2 = new byte[i2][];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            if (i4 < iArrDoExecute.length && (i = iArrDoExecute[i4]) >= 0) {
                bArr2[i3] = Utils.subarray(bArr, i, iArrDoExecute[i4 + 1]);
            }
        }
        return bArr2;
    }

    int[] findUTF8SubmatchIndex(byte[] bArr) {
        return pad(doExecute(MachineInput.fromUTF8(bArr), 0, 0, this.prog.numCap));
    }

    String[] findSubmatch(String str) {
        int i;
        int[] iArrDoExecute = doExecute(MachineInput.fromUTF16(str), 0, 0, this.prog.numCap);
        if (iArrDoExecute == null) {
            return null;
        }
        int i2 = this.numSubexp + 1;
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            if (i4 < iArrDoExecute.length && (i = iArrDoExecute[i4]) >= 0) {
                strArr[i3] = str.substring(i, iArrDoExecute[i4 + 1]);
            }
        }
        return strArr;
    }

    int[] findSubmatchIndex(String str) {
        return pad(doExecute(MachineInput.fromUTF16(str), 0, 0, this.prog.numCap));
    }

    List<byte[]> findAllUTF8(final byte[] bArr, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF8(bArr), i, new DeliverFunc() { // from class: com.google.re2j.RE2.3
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(Utils.subarray(bArr, iArr[0], iArr[1]));
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<int[]> findAllUTF8Index(byte[] bArr, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF8(bArr), i, new DeliverFunc() { // from class: com.google.re2j.RE2.4
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(Utils.subarray(iArr, 0, 2));
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<String> findAll(final String str, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF16(str), i, new DeliverFunc() { // from class: com.google.re2j.RE2.5
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(str.substring(iArr[0], iArr[1]));
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<int[]> findAllIndex(String str, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF16(str), i, new DeliverFunc() { // from class: com.google.re2j.RE2.6
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(Utils.subarray(iArr, 0, 2));
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<byte[][]> findAllUTF8Submatch(final byte[] bArr, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF8(bArr), i, new DeliverFunc() { // from class: com.google.re2j.RE2.7
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                int length = iArr.length / 2;
                byte[][] bArr2 = new byte[length][];
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i2 * 2;
                    int i4 = iArr[i3];
                    if (i4 >= 0) {
                        bArr2[i2] = Utils.subarray(bArr, i4, iArr[i3 + 1]);
                    }
                }
                arrayList.add(bArr2);
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<int[]> findAllUTF8SubmatchIndex(byte[] bArr, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF8(bArr), i, new DeliverFunc() { // from class: com.google.re2j.RE2.8
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(iArr);
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<String[]> findAllSubmatch(final String str, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF16(str), i, new DeliverFunc() { // from class: com.google.re2j.RE2.9
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                int length = iArr.length / 2;
                String[] strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i2 * 2;
                    int i4 = iArr[i3];
                    if (i4 >= 0) {
                        strArr[i2] = str.substring(i4, iArr[i3 + 1]);
                    }
                }
                arrayList.add(strArr);
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    List<int[]> findAllSubmatchIndex(String str, int i) {
        final ArrayList arrayList = new ArrayList();
        allMatches(MachineInput.fromUTF16(str), i, new DeliverFunc() { // from class: com.google.re2j.RE2.10
            @Override // com.google.re2j.RE2.DeliverFunc
            public void deliver(int[] iArr) {
                arrayList.add(iArr);
            }
        });
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private interface DeliverFunc {
        void deliver(int[] iArr);
    }

    interface ReplaceFunc {
        String replace(String str);
    }
}
