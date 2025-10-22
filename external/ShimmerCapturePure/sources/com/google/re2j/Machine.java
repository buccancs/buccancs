package com.google.re2j;

import com.google.re2j.Inst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes2.dex */
class Machine {
    private final Prog prog;
    private final Queue q0;
    private final Queue q1;
    private int[] matchcap;
    private boolean matched;
    private List<Thread> pool = new ArrayList();
    private RE2 re2;

    Machine(RE2 re2) {
        Prog prog = re2.prog;
        this.prog = prog;
        this.re2 = re2;
        this.q0 = new Queue(prog.numInst());
        this.q1 = new Queue(prog.numInst());
        this.matchcap = new int[prog.numCap >= 2 ? prog.numCap : 2];
    }

    void init(int i) {
        Iterator<Thread> it2 = this.pool.iterator();
        while (it2.hasNext()) {
            it2.next().cap = new int[i];
        }
        this.matchcap = new int[i];
    }

    int[] submatches() {
        int[] iArr = this.matchcap;
        if (iArr.length == 0) {
            return Utils.EMPTY_INTS;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private Thread alloc(Inst inst) {
        int size = this.pool.size();
        Thread threadRemove = size > 0 ? this.pool.remove(size - 1) : new Thread(this.matchcap.length);
        threadRemove.inst = inst;
        return threadRemove;
    }

    private void free(Thread thread) {
        this.pool.add(thread);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:

        r13 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean match(com.google.re2j.MachineInput r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Machine.match(com.google.re2j.MachineInput, int, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void step(com.google.re2j.Machine.Queue r15, com.google.re2j.Machine.Queue r16, int r17, int r18, int r19, int r20, int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Machine.step(com.google.re2j.Machine$Queue, com.google.re2j.Machine$Queue, int, int, int, int, int, boolean):void");
    }

    private Thread add(Queue queue, int i, int i2, int[] iArr, int i3, Thread thread) {
        Thread threadAlloc = thread;
        if (i == 0 || queue.contains(i)) {
            return threadAlloc;
        }
        Queue.Entry entryAdd = queue.add(i);
        Inst inst = this.prog.getInst(i);
        switch (AnonymousClass1.$SwitchMap$com$google$re2j$Inst$Op[inst.op().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (threadAlloc == null) {
                    threadAlloc = alloc(inst);
                } else {
                    threadAlloc.inst = inst;
                }
                if (iArr.length > 0 && threadAlloc.cap != iArr) {
                    System.arraycopy(iArr, 0, threadAlloc.cap, 0, iArr.length);
                }
                entryAdd.thread = threadAlloc;
                return null;
            case 6:
                break;
            case 7:
            case 8:
                return add(queue, inst.arg, i2, iArr, i3, add(queue, inst.out, i2, iArr, i3, thread));
            case 9:
                if ((inst.arg & (~i3)) == 0) {
                    return add(queue, inst.out, i2, iArr, i3, thread);
                }
                break;
            case 10:
                return add(queue, inst.out, i2, iArr, i3, thread);
            case 11:
                if (inst.arg < iArr.length) {
                    int i4 = iArr[inst.arg];
                    iArr[inst.arg] = i2;
                    add(queue, inst.out, i2, iArr, i3, null);
                    iArr[inst.arg] = i4;
                    break;
                } else {
                    return add(queue, inst.out, i2, iArr, i3, thread);
                }
            default:
                throw new IllegalStateException("unhandled");
        }
        return threadAlloc;
    }

    private static class Thread {
        int[] cap;
        Inst inst;

        Thread(int i) {
            this.cap = new int[i];
        }
    }

    private static class Queue {
        final Entry[] dense;
        final int[] sparse;
        int size;

        Queue(int i) {
            this.sparse = new int[i];
            this.dense = new Entry[i];
        }

        boolean isEmpty() {
            return this.size == 0;
        }

        boolean contains(int i) {
            Entry entry;
            int i2 = this.sparse[i];
            return i2 < this.size && (entry = this.dense[i2]) != null && entry.pc == i;
        }

        Entry add(int i) {
            int i2 = this.size;
            this.size = i2 + 1;
            this.sparse[i] = i2;
            Entry[] entryArr = this.dense;
            Entry entry = entryArr[i2];
            if (entry == null) {
                entry = new Entry();
                entryArr[i2] = entry;
            }
            entry.thread = null;
            entry.pc = i;
            return entry;
        }

        void clear(List<Thread> list) {
            for (int i = 0; i < this.size; i++) {
                Entry entry = this.dense[i];
                if (entry != null && entry.thread != null) {
                    list.add(entry.thread);
                }
            }
            this.size = 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(VectorFormat.DEFAULT_PREFIX);
            for (int i = 0; i < this.size; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(this.dense[i].pc);
            }
            sb.append('}');
            return sb.toString();
        }

        static class Entry {
            int pc;
            Thread thread;

            Entry() {
            }
        }
    }

    /* renamed from: com.google.re2j.Machine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$re2j$Inst$Op;

        static {
            int[] iArr = new int[Inst.Op.values().length];
            $SwitchMap$com$google$re2j$Inst$Op = iArr;
            try {
                iArr[Inst.Op.MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.RUNE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.RUNE1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.RUNE_ANY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.RUNE_ANY_NOT_NL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.FAIL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.ALT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.ALT_MATCH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.EMPTY_WIDTH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.NOP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$re2j$Inst$Op[Inst.Op.CAPTURE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
