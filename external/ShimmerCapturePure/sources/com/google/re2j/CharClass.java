package com.google.re2j;

/* loaded from: classes2.dex */
class CharClass {
    private int len;
    private int[] r;

    CharClass(int[] iArr) {
        this.r = iArr;
        this.len = iArr.length;
    }

    CharClass() {
        this.r = Utils.EMPTY_INTS;
        this.len = 0;
    }

    private static int cmp(int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[i] - i2;
        return i4 != 0 ? i4 : i3 - iArr[i + 1];
    }

    private static void qsortIntPair(int[] iArr, int i, int i2) {
        int i3 = ((i + i2) / 2) & (-2);
        int i4 = iArr[i3];
        int i5 = iArr[i3 + 1];
        int i6 = i;
        int i7 = i2;
        while (i6 <= i7) {
            while (i6 < i2 && cmp(iArr, i6, i4, i5) < 0) {
                i6 += 2;
            }
            while (i7 > i && cmp(iArr, i7, i4, i5) > 0) {
                i7 -= 2;
            }
            if (i6 <= i7) {
                if (i6 != i7) {
                    int i8 = iArr[i6];
                    iArr[i6] = iArr[i7];
                    iArr[i7] = i8;
                    int i9 = i6 + 1;
                    int i10 = iArr[i9];
                    int i11 = i7 + 1;
                    iArr[i9] = iArr[i11];
                    iArr[i11] = i10;
                }
                i6 += 2;
                i7 -= 2;
            }
        }
        if (i < i7) {
            qsortIntPair(iArr, i, i7);
        }
        if (i6 < i2) {
            qsortIntPair(iArr, i6, i2);
        }
    }

    static String charClassToString(int[] iArr, int i) {
        StringBuilder sb = new StringBuilder("[");
        for (int i2 = 0; i2 < i; i2 += 2) {
            if (i2 > 0) {
                sb.append(' ');
            }
            int i3 = iArr[i2];
            int i4 = iArr[i2 + 1];
            if (i3 == i4) {
                sb.append("0x");
                sb.append(Integer.toHexString(i3));
            } else {
                sb.append("0x");
                sb.append(Integer.toHexString(i3));
                sb.append("-0x");
                sb.append(Integer.toHexString(i4));
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void ensureCapacity(int i) {
        int[] iArr = this.r;
        if (iArr.length < i) {
            int i2 = this.len;
            if (i < i2 * 2) {
                i = i2 * 2;
            }
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.r = iArr2;
        }
    }

    int[] toArray() {
        int i = this.len;
        int[] iArr = this.r;
        if (i == iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    CharClass cleanClass() {
        int i = this.len;
        if (i < 4) {
            return this;
        }
        qsortIntPair(this.r, 0, i - 2);
        int i2 = 2;
        for (int i3 = 2; i3 < this.len; i3 += 2) {
            int[] iArr = this.r;
            int i4 = iArr[i3];
            int i5 = iArr[i3 + 1];
            int i6 = i2 - 1;
            int i7 = iArr[i6];
            if (i4 > i7 + 1) {
                iArr[i2] = i4;
                iArr[i2 + 1] = i5;
                i2 += 2;
            } else if (i5 > i7) {
                iArr[i6] = i5;
            }
        }
        this.len = i2;
        return this;
    }

    CharClass appendLiteral(int i, int i2) {
        if ((i2 & 1) != 0) {
            return appendFoldedRange(i, i);
        }
        return appendRange(i, i);
    }

    CharClass appendRange(int i, int i2) {
        if (this.len > 0) {
            for (int i3 = 2; i3 <= 4; i3 += 2) {
                int i4 = this.len;
                if (i4 >= i3) {
                    int[] iArr = this.r;
                    int i5 = iArr[i4 - i3];
                    int i6 = iArr[(i4 - i3) + 1];
                    if (i <= i6 + 1 && i5 <= i2 + 1) {
                        if (i < i5) {
                            iArr[i4 - i3] = i;
                        }
                        if (i2 > i6) {
                            iArr[(i4 - i3) + 1] = i2;
                        }
                        return this;
                    }
                }
            }
        }
        ensureCapacity(this.len + 2);
        int[] iArr2 = this.r;
        int i7 = this.len;
        iArr2[i7] = i;
        this.len = i7 + 2;
        iArr2[i7 + 1] = i2;
        return this;
    }

    CharClass appendFoldedRange(int i, int i2) {
        if (i <= 65 && i2 >= 66639) {
            return appendRange(i, i2);
        }
        if (i2 < 65 || i > 66639) {
            return appendRange(i, i2);
        }
        if (i < 65) {
            appendRange(i, 64);
            i = 65;
        }
        if (i2 > 66639) {
            appendRange(66640, i2);
            i2 = 66639;
        }
        while (i <= i2) {
            appendRange(i, i);
            for (int iSimpleFold = Unicode.simpleFold(i); iSimpleFold != i; iSimpleFold = Unicode.simpleFold(iSimpleFold)) {
                appendRange(iSimpleFold, iSimpleFold);
            }
            i++;
        }
        return this;
    }

    CharClass appendClass(int[] iArr) {
        for (int i = 0; i < iArr.length; i += 2) {
            appendRange(iArr[i], iArr[i + 1]);
        }
        return this;
    }

    CharClass appendFoldedClass(int[] iArr) {
        for (int i = 0; i < iArr.length; i += 2) {
            appendFoldedRange(iArr[i], iArr[i + 1]);
        }
        return this;
    }

    CharClass appendNegatedClass(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2 += 2) {
            int i3 = iArr[i2];
            int i4 = iArr[i2 + 1];
            int i5 = i3 - 1;
            if (i <= i5) {
                appendRange(i, i5);
            }
            i = i4 + 1;
        }
        if (i <= 1114111) {
            appendRange(i, 1114111);
        }
        return this;
    }

    CharClass appendTable(int[][] iArr) {
        for (int[] iArr2 : iArr) {
            int i = iArr2[0];
            int i2 = iArr2[1];
            int i3 = iArr2[2];
            if (i3 == 1) {
                appendRange(i, i2);
            } else {
                while (i <= i2) {
                    appendRange(i, i);
                    i += i3;
                }
            }
        }
        return this;
    }

    CharClass appendNegatedTable(int[][] iArr) {
        int i = 0;
        for (int[] iArr2 : iArr) {
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            int i4 = iArr2[2];
            if (i4 == 1) {
                int i5 = i2 - 1;
                if (i <= i5) {
                    appendRange(i, i5);
                }
                i = i3 + 1;
            } else {
                while (i2 <= i3) {
                    int i6 = i2 - 1;
                    if (i <= i6) {
                        appendRange(i, i6);
                    }
                    i = i2 + 1;
                    i2 += i4;
                }
            }
        }
        if (i <= 1114111) {
            appendRange(i, 1114111);
        }
        return this;
    }

    CharClass appendTableWithSign(int[][] iArr, int i) {
        if (i < 0) {
            return appendNegatedTable(iArr);
        }
        return appendTable(iArr);
    }

    CharClass negateClass() {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.len; i3 += 2) {
            int[] iArr = this.r;
            int i4 = iArr[i3];
            int i5 = iArr[i3 + 1];
            int i6 = i4 - 1;
            if (i2 <= i6) {
                iArr[i] = i2;
                iArr[i + 1] = i6;
                i += 2;
            }
            i2 = i5 + 1;
        }
        this.len = i;
        if (i2 <= 1114111) {
            ensureCapacity(i + 2);
            int[] iArr2 = this.r;
            int i7 = this.len;
            iArr2[i7] = i2;
            this.len = i7 + 2;
            iArr2[i7 + 1] = 1114111;
        }
        return this;
    }

    CharClass appendClassWithSign(int[] iArr, int i) {
        if (i < 0) {
            return appendNegatedClass(iArr);
        }
        return appendClass(iArr);
    }

    CharClass appendGroup(CharGroup charGroup, boolean z) {
        int[] array = charGroup.cls;
        if (z) {
            array = new CharClass().appendFoldedClass(array).cleanClass().toArray();
        }
        return appendClassWithSign(array, charGroup.sign);
    }

    public String toString() {
        return charClassToString(this.r, this.len);
    }
}
