package com.google.re2j;

import com.google.re2j.Regexp;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.util.ArrayList;

import org.apache.http.message.TokenParser;

/* loaded from: classes2.dex */
class Parser {
    private static final int[][] ANY_TABLE = {new int[]{0, 1114111, 1}};
    private static final String ERR_INTERNAL_ERROR = "regexp/syntax: internal error";
    private static final String ERR_INVALID_CHAR_CLASS = "invalid character class";
    private static final String ERR_INVALID_CHAR_RANGE = "invalid character class range";
    private static final String ERR_INVALID_ESCAPE = "invalid escape sequence";
    private static final String ERR_INVALID_NAMED_CAPTURE = "invalid named capture";
    private static final String ERR_INVALID_PERL_OP = "invalid or unsupported Perl syntax";
    private static final String ERR_INVALID_REPEAT_OP = "invalid nested repetition operator";
    private static final String ERR_INVALID_REPEAT_SIZE = "invalid repeat count";
    private static final String ERR_MISSING_BRACKET = "missing closing ]";
    private static final String ERR_MISSING_PAREN = "missing closing )";
    private static final String ERR_MISSING_REPEAT_ARGUMENT = "missing argument to repetition operator";
    private static final String ERR_TRAILING_BACKSLASH = "trailing backslash at end of expression";
    private final String wholeRegexp;
    private final Stack stack = new Stack(null);
    private int flags;
    private Regexp free;
    private int numCap = 0;

    Parser(String str, int i) {
        this.wholeRegexp = str;
        this.flags = i;
    }

    private static int minFoldRune(int i) {
        if (i < 65 || i > 66639) {
            return i;
        }
        int i2 = i;
        for (int iSimpleFold = Unicode.simpleFold(i); iSimpleFold != i; iSimpleFold = Unicode.simpleFold(iSimpleFold)) {
            if (i2 > iSimpleFold) {
                i2 = iSimpleFold;
            }
        }
        return i2;
    }

    private static Regexp leadingRegexp(Regexp regexp) {
        if (regexp.op == Regexp.Op.EMPTY_MATCH) {
            return null;
        }
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            regexp = regexp.subs[0];
            if (regexp.op == Regexp.Op.EMPTY_MATCH) {
                return null;
            }
        }
        return regexp;
    }

    private static Regexp literalRegexp(String str, int i) {
        Regexp regexp = new Regexp(Regexp.Op.LITERAL);
        regexp.flags = i;
        regexp.runes = Utils.stringToRunes(str);
        return regexp;
    }

    static Regexp parse(String str, int i) throws PatternSyntaxException {
        return new Parser(str, i).parseInternal();
    }

    private static int parseRepeat(StringIterator stringIterator) throws PatternSyntaxException {
        int i;
        int iPos = stringIterator.pos();
        if (stringIterator.more() && stringIterator.lookingAt('{')) {
            stringIterator.skip(1);
            int i2 = parseInt(stringIterator);
            if (i2 == -1 || !stringIterator.more()) {
                return -1;
            }
            if (stringIterator.lookingAt(StringUtil.COMMA)) {
                stringIterator.skip(1);
                if (!stringIterator.more()) {
                    return -1;
                }
                if (stringIterator.lookingAt('}')) {
                    i = -1;
                } else {
                    i = parseInt(stringIterator);
                    if (i == -1) {
                        return -1;
                    }
                }
            } else {
                i = i2;
            }
            if (stringIterator.more() && stringIterator.lookingAt('}')) {
                stringIterator.skip(1);
                if (i2 < 0 || i2 > 1000 || i == -2 || i > 1000 || (i >= 0 && i2 > i)) {
                    throw new PatternSyntaxException(ERR_INVALID_REPEAT_SIZE, stringIterator.from(iPos));
                }
                return (i2 << 16) | (65535 & i);
            }
        }
        return -1;
    }

    private static boolean isValidCaptureName(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '_' && !Utils.isalnum(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    private static int parseInt(StringIterator stringIterator) {
        int iPeek;
        int iPos = stringIterator.pos();
        while (stringIterator.more() && (iPeek = stringIterator.peek()) >= 48 && iPeek <= 57) {
            stringIterator.skip(1);
        }
        String strFrom = stringIterator.from(iPos);
        if (strFrom.isEmpty()) {
            return -1;
        }
        if (strFrom.length() > 1 && strFrom.charAt(0) == '0') {
            return -1;
        }
        if (strFrom.length() > 8) {
            return -2;
        }
        return Integer.valueOf(strFrom, 10).intValue();
    }

    private static boolean isCharClass(Regexp regexp) {
        return (regexp.op == Regexp.Op.LITERAL && regexp.runes.length == 1) || regexp.op == Regexp.Op.CHAR_CLASS || regexp.op == Regexp.Op.ANY_CHAR_NOT_NL || regexp.op == Regexp.Op.ANY_CHAR;
    }

    private static boolean matchRune(Regexp regexp, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[regexp.op.ordinal()];
        if (i2 != 1) {
            return i2 != 2 ? i2 != 3 ? i2 == 4 : i != 10 : regexp.runes.length == 1 && regexp.runes[0] == i;
        }
        for (int i3 = 0; i3 < regexp.runes.length; i3 += 2) {
            if (regexp.runes[i3] <= i && i <= regexp.runes[i3 + 1]) {
                return true;
            }
        }
        return false;
    }

    private static void mergeCharClass(Regexp regexp, Regexp regexp2) {
        int i = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[regexp.op.ordinal()];
        if (i == 1) {
            if (regexp2.op == Regexp.Op.LITERAL) {
                regexp.runes = new CharClass(regexp.runes).appendLiteral(regexp2.runes[0], regexp2.flags).toArray();
                return;
            } else {
                regexp.runes = new CharClass(regexp.runes).appendClass(regexp2.runes).toArray();
                return;
            }
        }
        if (i != 2) {
            if (i == 3 && matchRune(regexp2, 10)) {
                regexp.op = Regexp.Op.ANY_CHAR;
                return;
            }
            return;
        }
        if (regexp2.runes[0] == regexp.runes[0] && regexp2.flags == regexp.flags) {
            return;
        }
        regexp.op = Regexp.Op.CHAR_CLASS;
        regexp.runes = new CharClass().appendLiteral(regexp.runes[0], regexp.flags).appendLiteral(regexp2.runes[0], regexp2.flags).toArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x004c, code lost:

        if (r6.peek() <= 55) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int parseEscape(com.google.re2j.Parser.StringIterator r6) throws com.google.re2j.PatternSyntaxException {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Parser.parseEscape(com.google.re2j.Parser$StringIterator):int");
    }

    private static int parseClassChar(StringIterator stringIterator, int i) throws PatternSyntaxException {
        if (!stringIterator.more()) {
            throw new PatternSyntaxException(ERR_MISSING_BRACKET, stringIterator.from(i));
        }
        if (stringIterator.lookingAt(TokenParser.ESCAPE)) {
            return parseEscape(stringIterator);
        }
        return stringIterator.pop();
    }

    private static Pair<int[][], int[][]> unicodeTable(String str) {
        if (str.equals("Any")) {
            int[][] iArr = ANY_TABLE;
            return Pair.of(iArr, iArr);
        }
        int[][] iArr2 = UnicodeTables.CATEGORIES.get(str);
        if (iArr2 != null) {
            return Pair.of(iArr2, UnicodeTables.FOLD_CATEGORIES.get(str));
        }
        int[][] iArr3 = UnicodeTables.SCRIPTS.get(str);
        if (iArr3 != null) {
            return Pair.of(iArr3, UnicodeTables.FOLD_SCRIPT.get(str));
        }
        return null;
    }

    static Regexp[] subarray(Regexp[] regexpArr, int i, int i2) {
        Regexp[] regexpArr2 = new Regexp[i2 - i];
        for (int i3 = i; i3 < i2; i3++) {
            regexpArr2[i3 - i] = regexpArr[i3];
        }
        return regexpArr2;
    }

    private static int[] concatRunes(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[iArr.length + iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    private Regexp newRegexp(Regexp.Op op) {
        Regexp regexp = this.free;
        if (regexp != null && regexp.subs != null && regexp.subs.length > 0) {
            this.free = regexp.subs[0];
            regexp.reinit();
            regexp.op = op;
            return regexp;
        }
        return new Regexp(op);
    }

    private void reuse(Regexp regexp) {
        if (regexp.subs != null && regexp.subs.length > 0) {
            regexp.subs[0] = this.free;
        }
        this.free = regexp;
    }

    private Regexp pop() {
        return this.stack.remove(r0.size() - 1);
    }

    private Regexp[] popToPseudo() {
        int size = this.stack.size();
        int i = size;
        while (i > 0 && !this.stack.get(i - 1).op.isPseudo()) {
            i--;
        }
        Regexp[] regexpArr = (Regexp[]) this.stack.subList(i, size).toArray(new Regexp[size - i]);
        this.stack.removeRange(i, size);
        return regexpArr;
    }

    private Regexp push(Regexp regexp) {
        if (regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 2 && regexp.runes[0] == regexp.runes[1]) {
            if (maybeConcat(regexp.runes[0], this.flags & (-2))) {
                return null;
            }
            regexp.op = Regexp.Op.LITERAL;
            regexp.runes = new int[]{regexp.runes[0]};
            regexp.flags = this.flags & (-2);
        } else if ((regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 4 && regexp.runes[0] == regexp.runes[1] && regexp.runes[2] == regexp.runes[3] && Unicode.simpleFold(regexp.runes[0]) == regexp.runes[2] && Unicode.simpleFold(regexp.runes[2]) == regexp.runes[0]) || (regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 2 && regexp.runes[0] + 1 == regexp.runes[1] && Unicode.simpleFold(regexp.runes[0]) == regexp.runes[1] && Unicode.simpleFold(regexp.runes[1]) == regexp.runes[0])) {
            if (maybeConcat(regexp.runes[0], this.flags | 1)) {
                return null;
            }
            regexp.op = Regexp.Op.LITERAL;
            regexp.runes = new int[]{regexp.runes[0]};
            regexp.flags = this.flags | 1;
        } else {
            maybeConcat(-1, 0);
        }
        this.stack.add(regexp);
        return regexp;
    }

    private boolean maybeConcat(int i, int i2) {
        int size = this.stack.size();
        if (size < 2) {
            return false;
        }
        Regexp regexp = this.stack.get(size - 1);
        Regexp regexp2 = this.stack.get(size - 2);
        if (regexp.op == Regexp.Op.LITERAL && regexp2.op == Regexp.Op.LITERAL && (regexp.flags & 1) == (regexp2.flags & 1)) {
            regexp2.runes = concatRunes(regexp2.runes, regexp.runes);
            if (i >= 0) {
                regexp.runes = new int[]{i};
                regexp.flags = i2;
                return true;
            }
            pop();
            reuse(regexp);
        }
        return false;
    }

    private Regexp newLiteral(int i, int i2) {
        Regexp regexpNewRegexp = newRegexp(Regexp.Op.LITERAL);
        regexpNewRegexp.flags = i2;
        if ((i2 & 1) != 0) {
            i = minFoldRune(i);
        }
        regexpNewRegexp.runes = new int[]{i};
        return regexpNewRegexp;
    }

    private void literal(int i) {
        push(newLiteral(i, this.flags));
    }

    private Regexp op(Regexp.Op op) {
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.flags = this.flags;
        return push(regexpNewRegexp);
    }

    private void repeat(Regexp.Op op, int i, int i2, int i3, StringIterator stringIterator, int i4) throws PatternSyntaxException {
        int i5 = this.flags;
        if ((i5 & 64) != 0) {
            if (stringIterator.more() && stringIterator.lookingAt('?')) {
                stringIterator.skip(1);
                i5 ^= 32;
            }
            if (i4 != -1) {
                throw new PatternSyntaxException(ERR_INVALID_REPEAT_OP, stringIterator.from(i4));
            }
        }
        int size = this.stack.size();
        if (size == 0) {
            throw new PatternSyntaxException(ERR_MISSING_REPEAT_ARGUMENT, stringIterator.from(i3));
        }
        int i6 = size - 1;
        Regexp regexp = this.stack.get(i6);
        if (regexp.op.isPseudo()) {
            throw new PatternSyntaxException(ERR_MISSING_REPEAT_ARGUMENT, stringIterator.from(i3));
        }
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.min = i;
        regexpNewRegexp.max = i2;
        regexpNewRegexp.flags = i5;
        regexpNewRegexp.subs = new Regexp[]{regexp};
        this.stack.set(i6, regexpNewRegexp);
    }

    private Regexp concat() {
        maybeConcat(-1, 0);
        Regexp[] regexpArrPopToPseudo = popToPseudo();
        if (regexpArrPopToPseudo.length == 0) {
            return push(newRegexp(Regexp.Op.EMPTY_MATCH));
        }
        return push(collapse(regexpArrPopToPseudo, Regexp.Op.CONCAT));
    }

    private Regexp alternate() {
        Regexp[] regexpArrPopToPseudo = popToPseudo();
        if (regexpArrPopToPseudo.length > 0) {
            cleanAlt(regexpArrPopToPseudo[regexpArrPopToPseudo.length - 1]);
        }
        if (regexpArrPopToPseudo.length == 0) {
            return push(newRegexp(Regexp.Op.NO_MATCH));
        }
        return push(collapse(regexpArrPopToPseudo, Regexp.Op.ALTERNATE));
    }

    private void cleanAlt(Regexp regexp) {
        if (AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[regexp.op.ordinal()] != 1) {
            return;
        }
        regexp.runes = new CharClass(regexp.runes).cleanClass().toArray();
        if (regexp.runes.length == 2 && regexp.runes[0] == 0 && regexp.runes[1] == 1114111) {
            regexp.runes = null;
            regexp.op = Regexp.Op.ANY_CHAR;
        } else if (regexp.runes.length == 4 && regexp.runes[0] == 0 && regexp.runes[1] == 9 && regexp.runes[2] == 11 && regexp.runes[3] == 1114111) {
            regexp.runes = null;
            regexp.op = Regexp.Op.ANY_CHAR_NOT_NL;
        }
    }

    private Regexp collapse(Regexp[] regexpArr, Regexp.Op op) {
        if (regexpArr.length == 1) {
            return regexpArr[0];
        }
        int length = 0;
        for (Regexp regexp : regexpArr) {
            length += regexp.op == op ? regexp.subs.length : 1;
        }
        Regexp[] regexpArr2 = new Regexp[length];
        int length2 = 0;
        for (Regexp regexp2 : regexpArr) {
            if (regexp2.op == op) {
                System.arraycopy(regexp2.subs, 0, regexpArr2, length2, regexp2.subs.length);
                length2 += regexp2.subs.length;
                reuse(regexp2);
            } else {
                regexpArr2[length2] = regexp2;
                length2++;
            }
        }
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.subs = regexpArr2;
        if (op != Regexp.Op.ALTERNATE) {
            return regexpNewRegexp;
        }
        regexpNewRegexp.subs = factor(regexpNewRegexp.subs, regexpNewRegexp.flags);
        if (regexpNewRegexp.subs.length != 1) {
            return regexpNewRegexp;
        }
        Regexp regexp3 = regexpNewRegexp.subs[0];
        reuse(regexpNewRegexp);
        return regexp3;
    }

    private Regexp[] factor(Regexp[] regexpArr, int i) {
        int i2;
        Regexp regexpLeadingRegexp;
        int i3;
        int[] iArr;
        int length;
        int i4;
        if (regexpArr.length < 2) {
            return regexpArr;
        }
        int length2 = regexpArr.length;
        int i5 = 0;
        int i6 = 0;
        int[] iArr2 = null;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 <= length2; i9++) {
            if (i9 < length2) {
                Regexp regexp = regexpArr[i9];
                if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
                    regexp = regexp.subs[0];
                }
                if (regexp.op == Regexp.Op.LITERAL) {
                    iArr = regexp.runes;
                    length = regexp.runes.length;
                    i3 = regexp.flags & 1;
                } else {
                    i3 = 0;
                    iArr = null;
                    length = 0;
                }
                if (i3 == i6) {
                    int i10 = 0;
                    while (i10 < i7 && i10 < length && iArr2[i10] == iArr[i10]) {
                        i10++;
                    }
                    if (i10 > 0) {
                        i7 = i10;
                    }
                }
            } else {
                i3 = 0;
                iArr = null;
                length = 0;
            }
            if (i9 != i8) {
                if (i9 == i8 + 1) {
                    i4 = i5 + 1;
                    regexpArr[i5] = regexpArr[i8];
                } else {
                    Regexp regexpNewRegexp = newRegexp(Regexp.Op.LITERAL);
                    regexpNewRegexp.flags = i6;
                    regexpNewRegexp.runes = Utils.subarray(iArr2, 0, i7);
                    for (int i11 = i8; i11 < i9; i11++) {
                        regexpArr[i11] = removeLeadingString(regexpArr[i11], i7);
                    }
                    Regexp regexpCollapse = collapse(subarray(regexpArr, i8, i9), Regexp.Op.ALTERNATE);
                    Regexp regexpNewRegexp2 = newRegexp(Regexp.Op.CONCAT);
                    regexpNewRegexp2.subs = new Regexp[]{regexpNewRegexp, regexpCollapse};
                    i4 = i5 + 1;
                    regexpArr[i5] = regexpNewRegexp2;
                }
                i5 = i4;
            }
            i8 = i9;
            i6 = i3;
            iArr2 = iArr;
            i7 = length;
        }
        int i12 = 0;
        int i13 = 0;
        Regexp regexp2 = null;
        int i14 = 0;
        while (i12 <= i5) {
            if (i12 < i5) {
                regexpLeadingRegexp = leadingRegexp(regexpArr[i12]);
                if (regexp2 != null && regexp2.equals(regexpLeadingRegexp)) {
                    regexpLeadingRegexp = regexp2;
                }
                i12++;
                regexp2 = regexpLeadingRegexp;
            } else {
                regexpLeadingRegexp = null;
            }
            if (i12 == i14) {
                i14 = i12;
            } else if (i12 == i14 + 1) {
                regexpArr[i13] = regexpArr[i14];
                i13++;
                i14 = i12;
            } else {
                int i15 = i14;
                while (i15 < i12) {
                    regexpArr[i15] = removeLeadingRegexp(regexpArr[i15], i15 != i14);
                    i15++;
                }
                Regexp regexpCollapse2 = collapse(subarray(regexpArr, i14, i12), Regexp.Op.ALTERNATE);
                Regexp regexpNewRegexp3 = newRegexp(Regexp.Op.CONCAT);
                regexpNewRegexp3.subs = new Regexp[]{regexp2, regexpCollapse2};
                regexpArr[i13] = regexpNewRegexp3;
                i13++;
                i14 = i12;
            }
            i12++;
            regexp2 = regexpLeadingRegexp;
        }
        int i16 = 0;
        int i17 = 0;
        for (int i18 = 0; i18 <= i13; i18++) {
            if (i18 >= i13 || !isCharClass(regexpArr[i18])) {
                if (i18 != i17) {
                    int i19 = i17 + 1;
                    if (i18 == i19) {
                        i2 = i16 + 1;
                        regexpArr[i16] = regexpArr[i17];
                    } else {
                        int i20 = i17;
                        for (int i21 = i19; i21 < i18; i21++) {
                            Regexp regexp3 = regexpArr[i20];
                            Regexp regexp4 = regexpArr[i21];
                            if (regexp3.op.ordinal() < regexp4.op.ordinal() || (regexp3.op == regexp4.op && regexp3.runes.length < regexp4.runes.length)) {
                                i20 = i21;
                            }
                        }
                        Regexp regexp5 = regexpArr[i17];
                        regexpArr[i17] = regexpArr[i20];
                        regexpArr[i20] = regexp5;
                        while (i19 < i18) {
                            mergeCharClass(regexpArr[i17], regexpArr[i19]);
                            reuse(regexpArr[i19]);
                            i19++;
                        }
                        cleanAlt(regexpArr[i17]);
                        i2 = i16 + 1;
                        regexpArr[i16] = regexpArr[i17];
                    }
                    i16 = i2;
                }
                if (i18 < i13) {
                    regexpArr[i16] = regexpArr[i18];
                    i16++;
                }
                i17 = i18 + 1;
            }
        }
        int i22 = 0;
        int i23 = 0;
        while (i22 < i16) {
            int i24 = i22 + 1;
            if (i24 >= i16 || regexpArr[i22].op != Regexp.Op.EMPTY_MATCH || regexpArr[i22 + 1].op != Regexp.Op.EMPTY_MATCH) {
                regexpArr[i23] = regexpArr[i22];
                i23++;
            }
            i22 = i24;
        }
        return subarray(regexpArr, 0, i23);
    }

    private Regexp removeLeadingString(Regexp regexp, int i) {
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            Regexp regexpRemoveLeadingString = removeLeadingString(regexp.subs[0], i);
            regexp.subs[0] = regexpRemoveLeadingString;
            if (regexpRemoveLeadingString.op != Regexp.Op.EMPTY_MATCH) {
                return regexp;
            }
            reuse(regexpRemoveLeadingString);
            int length = regexp.subs.length;
            if (length == 0 || length == 1) {
                regexp.op = Regexp.Op.EMPTY_MATCH;
                regexp.subs = null;
                return regexp;
            }
            if (length == 2) {
                Regexp regexp2 = regexp.subs[1];
                reuse(regexp);
                return regexp2;
            }
            regexp.subs = subarray(regexp.subs, 1, regexp.subs.length);
            return regexp;
        }
        if (regexp.op == Regexp.Op.LITERAL) {
            regexp.runes = Utils.subarray(regexp.runes, i, regexp.runes.length);
            if (regexp.runes.length == 0) {
                regexp.op = Regexp.Op.EMPTY_MATCH;
            }
        }
        return regexp;
    }

    private Regexp removeLeadingRegexp(Regexp regexp, boolean z) {
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            if (z) {
                reuse(regexp.subs[0]);
            }
            regexp.subs = subarray(regexp.subs, 1, regexp.subs.length);
            int length = regexp.subs.length;
            if (length == 0) {
                regexp.op = Regexp.Op.EMPTY_MATCH;
                regexp.subs = Regexp.EMPTY_SUBS;
                return regexp;
            }
            if (length != 1) {
                return regexp;
            }
            Regexp regexp2 = regexp.subs[0];
            reuse(regexp);
            return regexp2;
        }
        if (z) {
            reuse(regexp);
        }
        return newRegexp(Regexp.Op.EMPTY_MATCH);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.google.re2j.Regexp parseInternal() throws com.google.re2j.PatternSyntaxException {
        /*
            Method dump skipped, instructions count: 554
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Parser.parseInternal():com.google.re2j.Regexp");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0089, code lost:

        if (r2 < 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008e, code lost:

        if (r2 >= 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0090, code lost:

        if (r5 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0092, code lost:

        r1 = ~r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0093, code lost:

        if (r6 != 58) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0095, code lost:

        op(com.google.re2j.Regexp.Op.LEFT_PAREN);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:

        r9.flags = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:

        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parsePerlFlags(com.google.re2j.Parser.StringIterator r10) throws com.google.re2j.PatternSyntaxException {
        /*
            r9 = this;
            int r0 = r10.pos()
            java.lang.String r1 = r10.rest()
            java.lang.String r2 = "(?P<"
            boolean r2 = r1.startsWith(r2)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L4e
            r0 = 62
            int r0 = r1.indexOf(r0)
            java.lang.String r2 = "invalid named capture"
            if (r0 < 0) goto L48
            r5 = 4
            java.lang.String r5 = r1.substring(r5, r0)
            r10.skipString(r5)
            r6 = 5
            r10.skip(r6)
            boolean r10 = isValidCaptureName(r5)
            if (r10 == 0) goto L3e
            com.google.re2j.Regexp$Op r10 = com.google.re2j.Regexp.Op.LEFT_PAREN
            com.google.re2j.Regexp r10 = r9.op(r10)
            int r0 = r9.numCap
            int r0 = r0 + r4
            r9.numCap = r0
            r10.cap = r0
            r10.name = r5
            return
        L3e:
            com.google.re2j.PatternSyntaxException r10 = new com.google.re2j.PatternSyntaxException
            java.lang.String r0 = r1.substring(r3, r0)
            r10.<init>(r2, r0)
            throw r10
        L48:
            com.google.re2j.PatternSyntaxException r10 = new com.google.re2j.PatternSyntaxException
            r10.<init>(r2, r1)
            throw r10
        L4e:
            r1 = 2
            r10.skip(r1)
            int r1 = r9.flags
            r2 = 1
        L55:
            r5 = 0
        L56:
            boolean r6 = r10.more()
            if (r6 == 0) goto L9d
            int r6 = r10.pop()
            r7 = 41
            r8 = 58
            if (r6 == r7) goto L8e
            r7 = 45
            if (r6 == r7) goto L89
            if (r6 == r8) goto L8e
            r5 = 85
            if (r6 == r5) goto L85
            r5 = 105(0x69, float:1.47E-43)
            if (r6 == r5) goto L82
            r5 = 109(0x6d, float:1.53E-43)
            if (r6 == r5) goto L7f
            r5 = 115(0x73, float:1.61E-43)
            if (r6 != r5) goto L9d
            r1 = r1 | 8
            goto L87
        L7f:
            r1 = r1 & (-17)
            goto L87
        L82:
            r1 = r1 | 1
            goto L87
        L85:
            r1 = r1 | 32
        L87:
            r5 = 1
            goto L56
        L89:
            if (r2 < 0) goto L9d
            int r1 = ~r1
            r2 = -1
            goto L55
        L8e:
            if (r2 >= 0) goto L93
            if (r5 == 0) goto L9d
            int r1 = ~r1
        L93:
            if (r6 != r8) goto L9a
            com.google.re2j.Regexp$Op r10 = com.google.re2j.Regexp.Op.LEFT_PAREN
            r9.op(r10)
        L9a:
            r9.flags = r1
            return
        L9d:
            com.google.re2j.PatternSyntaxException r1 = new com.google.re2j.PatternSyntaxException
            java.lang.String r2 = "invalid or unsupported Perl syntax"
            java.lang.String r10 = r10.from(r0)
            r1.<init>(r2, r10)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Parser.parsePerlFlags(com.google.re2j.Parser$StringIterator):void");
    }

    private void parseVerticalBar() {
        concat();
        if (swapVerticalBar()) {
            return;
        }
        op(Regexp.Op.VERTICAL_BAR);
    }

    private boolean swapVerticalBar() {
        int size = this.stack.size();
        if (size >= 3 && this.stack.get(size - 2).op == Regexp.Op.VERTICAL_BAR) {
            int i = size - 1;
            if (isCharClass(this.stack.get(i))) {
                int i2 = size - 3;
                if (isCharClass(this.stack.get(i2))) {
                    Regexp regexp = this.stack.get(i);
                    Regexp regexp2 = this.stack.get(i2);
                    if (regexp.op.ordinal() > regexp2.op.ordinal()) {
                        this.stack.set(i2, regexp);
                    } else {
                        regexp2 = regexp;
                        regexp = regexp2;
                    }
                    mergeCharClass(regexp, regexp2);
                    reuse(regexp2);
                    pop();
                    return true;
                }
            }
        }
        if (size < 2) {
            return false;
        }
        int i3 = size - 1;
        Regexp regexp3 = this.stack.get(i3);
        int i4 = size - 2;
        Regexp regexp4 = this.stack.get(i4);
        if (regexp4.op != Regexp.Op.VERTICAL_BAR) {
            return false;
        }
        if (size >= 3) {
            cleanAlt(this.stack.get(size - 3));
        }
        this.stack.set(i4, regexp3);
        this.stack.set(i3, regexp4);
        return true;
    }

    private void parseRightParen() throws PatternSyntaxException {
        concat();
        if (swapVerticalBar()) {
            pop();
        }
        alternate();
        if (this.stack.size() < 2) {
            throw new PatternSyntaxException(ERR_INTERNAL_ERROR, "stack underflow");
        }
        Regexp regexpPop = pop();
        Regexp regexpPop2 = pop();
        if (regexpPop2.op != Regexp.Op.LEFT_PAREN) {
            throw new PatternSyntaxException(ERR_MISSING_PAREN, this.wholeRegexp);
        }
        this.flags = regexpPop2.flags;
        if (regexpPop2.cap == 0) {
            push(regexpPop);
            return;
        }
        regexpPop2.op = Regexp.Op.CAPTURE;
        regexpPop2.subs = new Regexp[]{regexpPop};
        push(regexpPop2);
    }

    private boolean parsePerlClassEscape(StringIterator stringIterator, CharClass charClass) {
        int iPos = stringIterator.pos();
        if ((this.flags & 64) == 0 || !stringIterator.more() || stringIterator.pop() != 92 || !stringIterator.more()) {
            return false;
        }
        stringIterator.pop();
        CharGroup charGroup = CharGroup.PERL_GROUPS.get(stringIterator.from(iPos));
        if (charGroup == null) {
            return false;
        }
        charClass.appendGroup(charGroup, (this.flags & 1) != 0);
        return true;
    }

    private boolean parseNamedClass(StringIterator stringIterator, CharClass charClass) throws PatternSyntaxException {
        String strRest = stringIterator.rest();
        int iIndexOf = strRest.indexOf(":]");
        if (iIndexOf < 0) {
            return false;
        }
        String strSubstring = strRest.substring(0, iIndexOf + 2);
        stringIterator.skipString(strSubstring);
        CharGroup charGroup = CharGroup.POSIX_GROUPS.get(strSubstring);
        if (charGroup == null) {
            throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, strSubstring);
        }
        charClass.appendGroup(charGroup, (this.flags & 1) != 0);
        return true;
    }

    private boolean parseUnicodeClass(StringIterator stringIterator, CharClass charClass) throws PatternSyntaxException {
        String strSubstring;
        int iPos = stringIterator.pos();
        if ((this.flags & 128) == 0 || !(stringIterator.lookingAt("\\p") || stringIterator.lookingAt("\\P"))) {
            return false;
        }
        stringIterator.skip(1);
        int i = stringIterator.pop() == 80 ? -1 : 1;
        int iPop = stringIterator.pop();
        if (iPop != 123) {
            strSubstring = Utils.runeToString(iPop);
        } else {
            String strRest = stringIterator.rest();
            int iIndexOf = strRest.indexOf(125);
            if (iIndexOf < 0) {
                stringIterator.rewindTo(iPos);
                throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.rest());
            }
            strSubstring = strRest.substring(0, iIndexOf);
            stringIterator.skipString(strSubstring);
            stringIterator.skip(1);
        }
        if (!strSubstring.isEmpty() && strSubstring.charAt(0) == '^') {
            i = -i;
            strSubstring = strSubstring.substring(1);
        }
        Pair<int[][], int[][]> pairUnicodeTable = unicodeTable(strSubstring);
        if (pairUnicodeTable == null) {
            throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.from(iPos));
        }
        int[][] iArr = pairUnicodeTable.first;
        int[][] iArr2 = pairUnicodeTable.second;
        if ((this.flags & 1) == 0 || iArr2 == null) {
            charClass.appendTableWithSign(iArr, i);
        } else {
            charClass.appendClassWithSign(new CharClass().appendTable(iArr).appendTable(iArr2).cleanClass().toArray(), i);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x008b, code lost:

        r13.rewindTo(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0097, code lost:

        throw new com.google.re2j.PatternSyntaxException(com.google.re2j.Parser.ERR_INVALID_CHAR_RANGE, r13.rest());
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseClass(com.google.re2j.Parser.StringIterator r13) throws com.google.re2j.PatternSyntaxException {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Parser.parseClass(com.google.re2j.Parser$StringIterator):void");
    }

    private static class Stack extends ArrayList<Regexp> {
        private Stack() {
        }

        /* synthetic */ Stack(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.ArrayList, java.util.AbstractList
        public void removeRange(int i, int i2) {
            super.removeRange(i, i2);
        }
    }

    /* renamed from: com.google.re2j.Parser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$re2j$Regexp$Op;

        static {
            int[] iArr = new int[Regexp.Op.values().length];
            $SwitchMap$com$google$re2j$Regexp$Op = iArr;
            try {
                iArr[Regexp.Op.CHAR_CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.LITERAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.ANY_CHAR_NOT_NL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.ANY_CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static class StringIterator {
        private final String str;
        private int pos = 0;

        StringIterator(String str) {
            this.str = str;
        }

        int pos() {
            return this.pos;
        }

        void rewindTo(int i) {
            this.pos = i;
        }

        void skip(int i) {
            this.pos += i;
        }

        boolean more() {
            return this.pos < this.str.length();
        }

        int peek() {
            return this.str.codePointAt(this.pos);
        }

        void skipString(String str) {
            this.pos += str.length();
        }

        int pop() {
            int iCodePointAt = this.str.codePointAt(this.pos);
            this.pos += Character.charCount(iCodePointAt);
            return iCodePointAt;
        }

        boolean lookingAt(char c) {
            return this.str.charAt(this.pos) == c;
        }

        boolean lookingAt(String str) {
            return rest().startsWith(str);
        }

        String rest() {
            return this.str.substring(this.pos);
        }

        String from(int i) {
            return this.str.substring(i, this.pos);
        }

        public String toString() {
            return rest();
        }
    }

    private static class Pair<F, S> {
        final F first;
        final S second;

        Pair(F f, S s) {
            this.first = f;
            this.second = s;
        }

        static <F, S> Pair<F, S> of(F f, S s) {
            return new Pair<>(f, s);
        }
    }
}
