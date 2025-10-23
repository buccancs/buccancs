package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Deprecated
/* loaded from: classes3.dex */
public final class DomainNameMappingBuilder<V> {
    private final V defaultValue;
    private final Map<String, V> map;

    public DomainNameMappingBuilder(V v) {
        this(4, v);
    }

    public DomainNameMappingBuilder(int i, V v) {
        this.defaultValue = (V) ObjectUtil.checkNotNull(v, "defaultValue");
        this.map = new LinkedHashMap(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DomainNameMappingBuilder<V> add(String str, V v) {
        this.map.put(ObjectUtil.checkNotNull(str, "hostname"), ObjectUtil.checkNotNull(v, "output"));
        return this;
    }

    public DomainNameMapping<V> build() {
        return new ImmutableDomainNameMapping(this.defaultValue, this.map);
    }

    private static final class ImmutableDomainNameMapping<V> extends DomainNameMapping<V> {
        private static final int REPR_CONST_PART_LENGTH = 46;
        private static final String REPR_HEADER = "ImmutableDomainNameMapping(default: ";
        private static final String REPR_MAP_CLOSING = "})";
        private static final String REPR_MAP_OPENING = ", map: {";
        private final String[] domainNamePatterns;
        private final Map<String, V> map;
        private final V[] values;

        private ImmutableDomainNameMapping(V v, Map<String, V> map) {
            super((Map) null, v);
            Set<Map.Entry<String, V>> setEntrySet = map.entrySet();
            int size = setEntrySet.size();
            this.domainNamePatterns = new String[size];
            this.values = (V[]) new Object[size];
            LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
            int i = 0;
            for (Map.Entry<String, V> entry : setEntrySet) {
                String strNormalizeHostname = normalizeHostname(entry.getKey());
                V value = entry.getValue();
                this.domainNamePatterns[i] = strNormalizeHostname;
                this.values[i] = value;
                linkedHashMap.put(strNormalizeHostname, value);
                i++;
            }
            this.map = Collections.unmodifiableMap(linkedHashMap);
        }

        private static int estimateBufferSize(int i, int i2, int i3) {
            return REPR_CONST_PART_LENGTH + i + ((int) (i3 * i2 * 1.1d));
        }

        private static StringBuilder appendMapping(StringBuilder sb, String str, String str2) {
            sb.append(str);
            sb.append('=');
            sb.append(str2);
            return sb;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.DomainNameMapping
        public Map<String, V> asMap() {
            return this.map;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.DomainNameMapping
        @Deprecated
        public DomainNameMapping<V> add(String str, V v) {
            throw new UnsupportedOperationException("Immutable DomainNameMapping does not support modification after initial creation");
        }

        @Override // io.grpc.netty.shaded.io.netty.util.DomainNameMapping, io.grpc.netty.shaded.io.netty.util.Mapping
        public V map(String str) {
            if (str != null) {
                String strNormalizeHostname = normalizeHostname(str);
                int length = this.domainNamePatterns.length;
                for (int i = 0; i < length; i++) {
                    if (matches(this.domainNamePatterns[i], strNormalizeHostname)) {
                        return this.values[i];
                    }
                }
            }
            return this.defaultValue;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.DomainNameMapping
        public String toString() {
            String string = this.defaultValue.toString();
            String[] strArr = this.domainNamePatterns;
            int length = strArr.length;
            if (length == 0) {
                return REPR_HEADER + string + ", map: {})";
            }
            String str = strArr[0];
            String string2 = this.values[0].toString();
            StringBuilder sb = new StringBuilder(estimateBufferSize(string.length(), length, str.length() + string2.length() + 3));
            sb.append(REPR_HEADER);
            sb.append(string);
            sb.append(REPR_MAP_OPENING);
            appendMapping(sb, str, string2);
            for (int i = 1; i < length; i++) {
                sb.append(", ");
                appendMapping(sb, i);
            }
            sb.append(REPR_MAP_CLOSING);
            return sb.toString();
        }

        private StringBuilder appendMapping(StringBuilder sb, int i) {
            return appendMapping(sb, this.domainNamePatterns[i], this.values[i].toString());
        }
    }
}
