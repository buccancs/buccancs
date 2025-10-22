package io.grpc.xds.internal.rbac.engine.cel;

import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class InterpreterException extends Exception {

    private InterpreterException(String str, Throwable th) {
        super(str, th);
    }

    public static class Builder {
        private Throwable cause;

        @Nullable
        private String location;

        @Nullable
        private String message;
        private int position;

        public Builder(String str, Object... objArr) {
        }

        public InterpreterException build() {
            String str;
            Object[] objArr = new Object[2];
            if (this.location != null) {
                str = " at " + this.location + ":" + this.position;
            } else {
                str = "";
            }
            objArr[0] = str;
            objArr[1] = this.message;
            return new InterpreterException(String.format("evaluation error%s: %s", objArr), this.cause);
        }
    }
}
