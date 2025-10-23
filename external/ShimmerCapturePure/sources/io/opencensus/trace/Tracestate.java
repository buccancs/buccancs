package io.opencensus.trace;

import io.opencensus.internal.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public abstract class Tracestate {
    private static final int KEY_MAX_SIZE = 256;
    private static final int MAX_KEY_VALUE_PAIRS = 32;
    private static final int VALUE_MAX_SIZE = 256;

    Tracestate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateKey(String str) {
        if (str.length() > 256 || str.isEmpty() || str.charAt(0) < 'a' || str.charAt(0) > 'z') {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'a' || cCharAt > 'z') && !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '_' || cCharAt == '-' || cCharAt == '*' || cCharAt == '/')) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateValue(String str) {
        if (str.length() > 256 || str.charAt(str.length() - 1) == ' ') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ',' || cCharAt == '=' || cCharAt < ' ' || cCharAt > '~') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Tracestate create(List<Entry> list) {
        Utils.checkState(list.size() <= 32, "Invalid size");
        return new AutoValue_Tracestate(Collections.unmodifiableList(list));
    }

    public abstract List<Entry> getEntries();

    @Nullable
    public String get(String str) {
        for (Entry entry : getEntries()) {
            if (entry.getKey().equals(str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private static final Tracestate EMPTY = Tracestate.create(Collections.emptyList());
        private final Tracestate parent;
        @Nullable
        private ArrayList<Entry> entries;

        private Builder(Tracestate tracestate) {
            Utils.checkNotNull(tracestate, "parent");
            this.parent = tracestate;
            this.entries = null;
        }

        public Builder set(String str, String str2) {
            Entry entryCreate = Entry.create(str, str2);
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i = 0;
            while (true) {
                if (i >= this.entries.size()) {
                    break;
                }
                if (this.entries.get(i).getKey().equals(entryCreate.getKey())) {
                    this.entries.remove(i);
                    break;
                }
                i++;
            }
            this.entries.add(0, entryCreate);
            return this;
        }

        public Builder remove(String str) {
            Utils.checkNotNull(str, "key");
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i = 0;
            while (true) {
                if (i >= this.entries.size()) {
                    break;
                }
                if (this.entries.get(i).getKey().equals(str)) {
                    this.entries.remove(i);
                    break;
                }
                i++;
            }
            return this;
        }

        public Tracestate build() {
            ArrayList<Entry> arrayList = this.entries;
            return arrayList == null ? this.parent : Tracestate.create(arrayList);
        }
    }

    public static abstract class Entry {
        Entry() {
        }

        public static Entry create(String str, String str2) {
            Utils.checkNotNull(str, "key");
            Utils.checkNotNull(str2, "value");
            Utils.checkArgument(Tracestate.validateKey(str), "Invalid key %s", str);
            Utils.checkArgument(Tracestate.validateValue(str2), "Invalid value %s", str2);
            return new AutoValue_Tracestate_Entry(str, str2);
        }

        public abstract String getKey();

        public abstract String getValue();
    }
}
