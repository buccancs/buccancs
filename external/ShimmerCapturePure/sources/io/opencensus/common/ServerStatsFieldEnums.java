package io.opencensus.common;

import java.util.TreeMap;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class ServerStatsFieldEnums {
    private static final int TOTALSIZE = computeTotalSize();

    private ServerStatsFieldEnums() {
    }

    public static int getTotalSize() {
        return TOTALSIZE;
    }

    private static int computeTotalSize() {
        int iValue = 0;
        for (Size size : Size.values()) {
            iValue = iValue + size.value() + 1;
        }
        return iValue;
    }

    public enum Id {
        SERVER_STATS_LB_LATENCY_ID(0),
        SERVER_STATS_SERVICE_LATENCY_ID(1),
        SERVER_STATS_TRACE_OPTION_ID(2);

        private static final TreeMap<Integer, Id> map = new TreeMap<>();

        static {
            for (Id id : values()) {
                map.put(Integer.valueOf(id.value), id);
            }
        }

        private final int value;

        Id(int i) {
            this.value = i;
        }

        @Nullable
        public static Id valueOf(int i) {
            return map.get(Integer.valueOf(i));
        }

        public int value() {
            return this.value;
        }
    }

    public enum Size {
        SERVER_STATS_LB_LATENCY_SIZE(8),
        SERVER_STATS_SERVICE_LATENCY_SIZE(8),
        SERVER_STATS_TRACE_OPTION_SIZE(1);

        private final int value;

        Size(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }
}
