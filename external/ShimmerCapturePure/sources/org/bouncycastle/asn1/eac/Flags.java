package org.bouncycastle.asn1.eac;

import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class Flags {
    int value;

    public Flags() {
        this.value = 0;
    }

    public Flags(int i) {
        this.value = i;
    }

    String decode(Hashtable hashtable) {
        StringJoiner stringJoiner = new StringJoiner(StringUtils.SPACE);
        Enumeration enumerationKeys = hashtable.keys();
        while (enumerationKeys.hasMoreElements()) {
            Integer num = (Integer) enumerationKeys.nextElement();
            if (isSet(num.intValue())) {
                stringJoiner.add((String) hashtable.get(num));
            }
        }
        return stringJoiner.toString();
    }

    public int getFlags() {
        return this.value;
    }

    public boolean isSet(int i) {
        return (i & this.value) != 0;
    }

    public void set(int i) {
        this.value = i | this.value;
    }

    private static class StringJoiner {
        boolean First = true;
        StringBuffer b = new StringBuffer();
        String mSeparator;

        public StringJoiner(String str) {
            this.mSeparator = str;
        }

        public void add(String str) {
            if (this.First) {
                this.First = false;
            } else {
                this.b.append(this.mSeparator);
            }
            this.b.append(str);
        }

        public String toString() {
            return this.b.toString();
        }
    }
}
