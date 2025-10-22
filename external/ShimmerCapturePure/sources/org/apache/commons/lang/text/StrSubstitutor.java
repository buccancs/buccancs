package org.apache.commons.lang.text;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes5.dex */
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher(VectorFormat.DEFAULT_SUFFIX);
    private boolean enableSubstitutionInVariables;
    private char escapeChar;
    private StrMatcher prefixMatcher;
    private StrMatcher suffixMatcher;
    private StrLookup variableResolver;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StrSubstitutor() {
        this((StrLookup) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(Map map) {
        this(StrLookup.mapLookup(map), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(Map map, String str, String str2) {
        this(StrLookup.mapLookup(map), str, str2, '$');
    }

    public StrSubstitutor(Map map, String str, String str2, char c) {
        this(StrLookup.mapLookup(map), str, str2, c);
    }

    public StrSubstitutor(StrLookup strLookup) {
        this(strLookup, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(StrLookup strLookup, String str, String str2, char c) {
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
    }

    public StrSubstitutor(StrLookup strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c) {
        setVariableResolver(strLookup);
        setVariablePrefixMatcher(strMatcher);
        setVariableSuffixMatcher(strMatcher2);
        setEscapeChar(c);
    }

    public static String replace(Object obj, Map map) {
        return new StrSubstitutor(map).replace(obj);
    }

    public static String replace(Object obj, Map map, String str, String str2) {
        return new StrSubstitutor(map, str, str2).replace(obj);
    }

    public static String replace(Object obj, Properties properties) {
        if (properties == null) {
            return obj.toString();
        }
        HashMap map = new HashMap();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            map.put(str, properties.getProperty(str));
        }
        return replace(obj, map);
    }

    public static String replaceSystemProperties(Object obj) {
        return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(obj);
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public void setEscapeChar(char c) {
        this.escapeChar = c;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable prefix matcher must not be null!");
        }
        this.prefixMatcher = strMatcher;
        return this;
    }

    public StrLookup getVariableResolver() {
        return this.variableResolver;
    }

    public void setVariableResolver(StrLookup strLookup) {
        this.variableResolver = strLookup;
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable suffix matcher must not be null!");
        }
        this.suffixMatcher = strMatcher;
        return this;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public void setEnableSubstitutionInVariables(boolean z) {
        this.enableSubstitutionInVariables = z;
    }

    public String replace(String str) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilder = new StrBuilder(str);
        return !substitute(strBuilder, 0, str.length()) ? str : strBuilder.toString();
    }

    public String replace(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(str, i, i2);
        if (!substitute(strBuilderAppend, 0, i2)) {
            return str.substring(i, i2 + i);
        }
        return strBuilderAppend.toString();
    }

    public String replace(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(cArr.length).append(cArr);
        substitute(strBuilderAppend, 0, cArr.length);
        return strBuilderAppend.toString();
    }

    public String replace(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(cArr, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(stringBuffer.length()).append(stringBuffer);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(stringBuffer, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(strBuilder.length()).append(strBuilder);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(strBuilder, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(Object obj) {
        if (obj == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder().append(obj);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public boolean replaceIn(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return false;
        }
        return replaceIn(stringBuffer, 0, stringBuffer.length());
    }

    public boolean replaceIn(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return false;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(stringBuffer, i, i2);
        if (!substitute(strBuilderAppend, 0, i2)) {
            return false;
        }
        stringBuffer.replace(i, i2 + i, strBuilderAppend.toString());
        return true;
    }

    public boolean replaceIn(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, 0, strBuilder.length());
    }

    public boolean replaceIn(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, i, i2);
    }

    protected boolean substitute(StrBuilder strBuilder, int i, int i2) {
        return substitute(strBuilder, i, i2, null) > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int substitute(org.apache.commons.lang.text.StrBuilder r22, int r23, int r24, java.util.List r25) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.text.StrSubstitutor.substitute(org.apache.commons.lang.text.StrBuilder, int, int, java.util.List):int");
    }

    private void checkCyclicSubstitution(String str, List list) {
        if (list.contains(str)) {
            StrBuilder strBuilder = new StrBuilder(256);
            strBuilder.append("Infinite loop in property interpolation of ");
            strBuilder.append(list.remove(0));
            strBuilder.append(": ");
            strBuilder.appendWithSeparators(list, "->");
            throw new IllegalStateException(strBuilder.toString());
        }
    }

    protected String resolveVariable(String str, StrBuilder strBuilder, int i, int i2) {
        StrLookup variableResolver = getVariableResolver();
        if (variableResolver == null) {
            return null;
        }
        return variableResolver.lookup(str);
    }

    public StrSubstitutor setVariablePrefix(char c) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariablePrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Variable prefix must not be null!");
        }
        return setVariablePrefixMatcher(StrMatcher.stringMatcher(str));
    }

    public StrSubstitutor setVariableSuffix(char c) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariableSuffix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Variable suffix must not be null!");
        }
        return setVariableSuffixMatcher(StrMatcher.stringMatcher(str));
    }
}
