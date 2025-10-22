package org.joda.time.tz;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTimeUtils;

/* loaded from: classes2.dex */
public class DefaultNameProvider implements NameProvider {
    private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();
    private HashMap<Locale, Map<String, Map<Boolean, Object>>> iByLocaleCache2 = createCache();

    @Override // org.joda.time.tz.NameProvider
    public String getShortName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    @Override // org.joda.time.tz.NameProvider
    public String getName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String str, String str2) {
        String[] strArr;
        Object[] objArr = null;
        if (locale == null || str == null || str2 == null) {
            return null;
        }
        Map map = this.iByLocaleCache.get(locale);
        if (map == null) {
            HashMap<Locale, Map<String, Map<String, Object>>> map2 = this.iByLocaleCache;
            HashMap mapCreateCache = createCache();
            map2.put(locale, mapCreateCache);
            map = mapCreateCache;
        }
        Map mapCreateCache2 = (Map) map.get(str);
        if (mapCreateCache2 == null) {
            mapCreateCache2 = createCache();
            map.put(str, mapCreateCache2);
            String[][] zoneStrings = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
            int length = zoneStrings.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    strArr = null;
                    break;
                }
                strArr = zoneStrings[i];
                if (strArr != null && strArr.length >= 5 && str.equals(strArr[0])) {
                    break;
                }
                i++;
            }
            Object[][] zoneStrings2 = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
            int length2 = zoneStrings2.length;
            int i2 = 0;
            while (true) {
                if (i2 < length2) {
                    Object[] objArr2 = zoneStrings2[i2];
                    if (objArr2 != null && objArr2.length >= 5 && str.equals(objArr2[0])) {
                        objArr = objArr2;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            if (strArr != null && objArr != null) {
                mapCreateCache2.put(strArr[2], new String[]{objArr[2], objArr[1]});
                if (strArr[2].equals(strArr[4])) {
                    mapCreateCache2.put(strArr[4] + "-Summer", new String[]{objArr[4], objArr[3]});
                } else {
                    mapCreateCache2.put(strArr[4], new String[]{objArr[4], objArr[3]});
                }
            }
        }
        return (String[]) mapCreateCache2.get(str2);
    }

    public String getShortName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    public String getName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String str, String str2, boolean z) {
        String[] strArr;
        String[] strArr2 = null;
        if (locale == null || str == null || str2 == null) {
            return null;
        }
        if (str.startsWith("Etc/")) {
            str = str.substring(4);
        }
        Map map = this.iByLocaleCache2.get(locale);
        if (map == null) {
            HashMap<Locale, Map<String, Map<Boolean, Object>>> map2 = this.iByLocaleCache2;
            HashMap mapCreateCache = createCache();
            map2.put(locale, mapCreateCache);
            map = mapCreateCache;
        }
        Map mapCreateCache2 = (Map) map.get(str);
        if (mapCreateCache2 == null) {
            mapCreateCache2 = createCache();
            map.put(str, mapCreateCache2);
            String[][] zoneStrings = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
            int length = zoneStrings.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    strArr = null;
                    break;
                }
                strArr = zoneStrings[i];
                if (strArr != null && strArr.length >= 5 && str.equals(strArr[0])) {
                    break;
                }
                i++;
            }
            String[][] zoneStrings2 = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
            int length2 = zoneStrings2.length;
            int i2 = 0;
            while (true) {
                if (i2 < length2) {
                    String[] strArr3 = zoneStrings2[i2];
                    if (strArr3 != null && strArr3.length >= 5 && str.equals(strArr3[0])) {
                        strArr2 = strArr3;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            if (strArr != null && strArr2 != null) {
                mapCreateCache2.put(Boolean.TRUE, new String[]{strArr2[2], strArr2[1]});
                mapCreateCache2.put(Boolean.FALSE, new String[]{strArr2[4], strArr2[3]});
            }
        }
        return (String[]) mapCreateCache2.get(Boolean.valueOf(z));
    }

    private HashMap createCache() {
        return new HashMap(7);
    }
}
