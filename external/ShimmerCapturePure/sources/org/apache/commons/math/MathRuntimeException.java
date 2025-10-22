package org.apache.commons.math;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ConcurrentModificationException;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.apache.commons.math.exception.MathThrowable;
import org.apache.commons.math.exception.util.DummyLocalizable;
import org.apache.commons.math.exception.util.Localizable;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/MathRuntimeException.class */
public class MathRuntimeException extends RuntimeException implements MathThrowable {
    private static final long serialVersionUID = 9058794795027570002L;
    private final Localizable pattern;
    private final Object[] arguments;

    @Deprecated
    public MathRuntimeException(String pattern, Object... arguments) {
        this(new DummyLocalizable(pattern), arguments);
    }

    public MathRuntimeException(Localizable pattern, Object... arguments) {
        this.pattern = pattern;
        this.arguments = arguments == null ? new Object[0] : (Object[]) arguments.clone();
    }

    public MathRuntimeException(Throwable rootCause) {
        super(rootCause);
        this.pattern = LocalizedFormats.SIMPLE_MESSAGE;
        Object[] objArr = new Object[1];
        objArr[0] = rootCause == null ? "" : rootCause.getMessage();
        this.arguments = objArr;
    }

    @Deprecated
    public MathRuntimeException(Throwable rootCause, String pattern, Object... arguments) {
        this(rootCause, new DummyLocalizable(pattern), arguments);
    }

    public MathRuntimeException(Throwable rootCause, Localizable pattern, Object... arguments) {
        super(rootCause);
        this.pattern = pattern;
        this.arguments = arguments == null ? new Object[0] : (Object[]) arguments.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String buildMessage(Locale locale, Localizable pattern, Object... arguments) {
        return new MessageFormat(pattern.getLocalizedString(locale), locale).format(arguments);
    }

    @Deprecated
    public static ArithmeticException createArithmeticException(String pattern, Object... arguments) {
        return createArithmeticException(new DummyLocalizable(pattern), arguments);
    }

    public static ArithmeticException createArithmeticException(final Localizable pattern, final Object... arguments) {
        return new ArithmeticException() { // from class: org.apache.commons.math.MathRuntimeException.1
            private static final long serialVersionUID = 5305498554076846637L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static ArrayIndexOutOfBoundsException createArrayIndexOutOfBoundsException(String pattern, Object... arguments) {
        return createArrayIndexOutOfBoundsException(new DummyLocalizable(pattern), arguments);
    }

    public static ArrayIndexOutOfBoundsException createArrayIndexOutOfBoundsException(final Localizable pattern, final Object... arguments) {
        return new ArrayIndexOutOfBoundsException() { // from class: org.apache.commons.math.MathRuntimeException.2
            private static final long serialVersionUID = 6718518191249632175L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static EOFException createEOFException(String pattern, Object... arguments) {
        return createEOFException(new DummyLocalizable(pattern), arguments);
    }

    public static EOFException createEOFException(final Localizable pattern, final Object... arguments) {
        return new EOFException() { // from class: org.apache.commons.math.MathRuntimeException.3
            private static final long serialVersionUID = 6067985859347601503L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    public static IOException createIOException(Throwable rootCause) {
        IOException ioe = new IOException(rootCause.getLocalizedMessage());
        ioe.initCause(rootCause);
        return ioe;
    }

    @Deprecated
    public static IllegalArgumentException createIllegalArgumentException(String pattern, Object... arguments) {
        return createIllegalArgumentException(new DummyLocalizable(pattern), arguments);
    }

    public static IllegalArgumentException createIllegalArgumentException(final Localizable pattern, final Object... arguments) {
        return new IllegalArgumentException() { // from class: org.apache.commons.math.MathRuntimeException.4
            private static final long serialVersionUID = -4284649691002411505L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    public static IllegalArgumentException createIllegalArgumentException(Throwable rootCause) {
        IllegalArgumentException iae = new IllegalArgumentException(rootCause.getLocalizedMessage());
        iae.initCause(rootCause);
        return iae;
    }

    @Deprecated
    public static IllegalStateException createIllegalStateException(String pattern, Object... arguments) {
        return createIllegalStateException(new DummyLocalizable(pattern), arguments);
    }

    public static IllegalStateException createIllegalStateException(final Localizable pattern, final Object... arguments) {
        return new IllegalStateException() { // from class: org.apache.commons.math.MathRuntimeException.5
            private static final long serialVersionUID = 6880901520234515725L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static ConcurrentModificationException createConcurrentModificationException(String pattern, Object... arguments) {
        return createConcurrentModificationException(new DummyLocalizable(pattern), arguments);
    }

    public static ConcurrentModificationException createConcurrentModificationException(final Localizable pattern, final Object... arguments) {
        return new ConcurrentModificationException() { // from class: org.apache.commons.math.MathRuntimeException.6
            private static final long serialVersionUID = -1878427236170442052L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static NoSuchElementException createNoSuchElementException(String pattern, Object... arguments) {
        return createNoSuchElementException(new DummyLocalizable(pattern), arguments);
    }

    public static NoSuchElementException createNoSuchElementException(final Localizable pattern, final Object... arguments) {
        return new NoSuchElementException() { // from class: org.apache.commons.math.MathRuntimeException.7
            private static final long serialVersionUID = 1632410088350355086L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static UnsupportedOperationException createUnsupportedOperationException(final Localizable pattern, final Object... arguments) {
        return new UnsupportedOperationException() { // from class: org.apache.commons.math.MathRuntimeException.8
            private static final long serialVersionUID = -4284649691002411505L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static NullPointerException createNullPointerException(String pattern, Object... arguments) {
        return createNullPointerException(new DummyLocalizable(pattern), arguments);
    }

    @Deprecated
    public static NullPointerException createNullPointerException(final Localizable pattern, final Object... arguments) {
        return new NullPointerException() { // from class: org.apache.commons.math.MathRuntimeException.9
            private static final long serialVersionUID = 451965530686593945L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, pattern, arguments);
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), pattern, arguments);
            }
        };
    }

    @Deprecated
    public static ParseException createParseException(int offset, String pattern, Object... arguments) {
        return createParseException(offset, new DummyLocalizable(pattern), arguments);
    }

    public static ParseException createParseException(int offset, Localizable pattern, Object... arguments) {
        return new AnonymousClass10(null, offset, pattern, arguments);
    }

    public static RuntimeException createInternalError(Throwable cause) {
        return new RuntimeException(cause) { // from class: org.apache.commons.math.MathRuntimeException.11
            private static final long serialVersionUID = -201865440834027016L;

            @Override // java.lang.Throwable
            public String getMessage() {
                return MathRuntimeException.buildMessage(Locale.US, LocalizedFormats.INTERNAL_ERROR, "https://issues.apache.org/jira/browse/MATH");
            }

            @Override // java.lang.Throwable
            public String getLocalizedMessage() {
                return MathRuntimeException.buildMessage(Locale.getDefault(), LocalizedFormats.INTERNAL_ERROR, "https://issues.apache.org/jira/browse/MATH");
            }
        };
    }

    @Deprecated
    public String getPattern() {
        return this.pattern.getSourceString();
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Localizable getSpecificPattern() {
        return null;
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Localizable getGeneralPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Object[] getArguments() {
        return (Object[]) this.arguments.clone();
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public String getMessage(Locale locale) {
        if (this.pattern != null) {
            return buildMessage(locale, this.pattern, this.arguments);
        }
        return "";
    }

    @Override // java.lang.Throwable, org.apache.commons.math.exception.MathThrowable
    public String getMessage() {
        return getMessage(Locale.US);
    }

    @Override // java.lang.Throwable, org.apache.commons.math.exception.MathThrowable
    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream out) {
        synchronized (out) {
            PrintWriter pw = new PrintWriter((OutputStream) out, false);
            printStackTrace(pw);
            pw.flush();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* renamed from: org.apache.commons.math.MathRuntimeException$10, reason: invalid class name */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/MathRuntimeException$10.class */
    static class AnonymousClass10 extends ParseException {
        private static final long serialVersionUID = 8153587599409010120L;
        final /* synthetic */ Localizable val$pattern;
        final /* synthetic */ Object[] val$arguments;

        /*  JADX ERROR: Types fix failed
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
            */
        /* JADX WARN: Failed to apply debug info
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
         */
        /* JADX WARN: Failed to calculate best type for var: r5v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r5v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r7v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r7v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r8v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r8v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Not initialized variable reg: 5, insn: 0x000c: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('x0' java.lang.String)]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 6, insn: 0x000d: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('x1' int)]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 7, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 8, insn: 0x0006: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        AnonymousClass10() {
            /*
                r4 = this;
                r0 = r4
                r1 = r7
                r0.val$pattern = r1
                r0 = r4
                r1 = r8
                r0.val$arguments = r1
                r0 = r4
                r1 = r5
                r2 = r6
                super/*android.app.Activity*/.onCreate(r1)
            return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.MathRuntimeException.AnonymousClass10.maybeForceBuilderInitialization():void");
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
            	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
            	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
            	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
            	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
            	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
            */
        public io.grpc.channelz.v1.SocketOptionLinger.Builder mergeDuration(com.google.protobuf.Duration r5) {
            /*
                r4 = this;
                java.util.Locale r0 = java.util.Locale.US
                r1 = r4
                org.apache.commons.math.exception.util.Localizable r1 = r1.val$pattern
                r2 = r4
                java.lang.Object[] r2 = r2.val$arguments
                void r0 = android.app.AlertDialog.Builder.<init>(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.MathRuntimeException.AnonymousClass10.mergeDuration(com.google.protobuf.Duration):io.grpc.channelz.v1.SocketOptionLinger$Builder");
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
            	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
            	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
            	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
            	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
            	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
            */
        public com.google.protobuf.AbstractMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) {
            /*
                r4 = this;
                android.app.AlertDialog r0 = android.app.AlertDialog.Builder.create()
                r1 = r4
                org.apache.commons.math.exception.util.Localizable r1 = r1.val$pattern
                r2 = r4
                java.lang.Object[] r2 = r2.val$arguments
                void r0 = android.app.AlertDialog.Builder.<init>(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.MathRuntimeException.AnonymousClass10.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.AbstractMessage$Builder");
        }
    }
}
