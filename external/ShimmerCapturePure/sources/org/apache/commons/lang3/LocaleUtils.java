package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes5.dex */
public class LocaleUtils {
    private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
    private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();

    public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return new Locale("", "");
        }
        if (str.contains("#")) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        int length = str.length();
        if (length < 2) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        if (str.charAt(0) == '_') {
            if (length < 3) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            char cCharAt = str.charAt(1);
            char cCharAt2 = str.charAt(2);
            if (!Character.isUpperCase(cCharAt) || !Character.isUpperCase(cCharAt2)) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (length == 3) {
                return new Locale("", str.substring(1, 3));
            }
            if (length < 5) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (str.charAt(3) != '_') {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            return new Locale("", str.substring(1, 3), str.substring(4));
        }
        String[] strArrSplit = str.split("_", -1);
        int length2 = strArrSplit.length - 1;
        if (length2 == 0) {
            if (StringUtils.isAllLowerCase(str) && (length == 2 || length == 3)) {
                return new Locale(str);
            }
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        if (length2 == 1) {
            if (StringUtils.isAllLowerCase(strArrSplit[0]) && ((strArrSplit[0].length() == 2 || strArrSplit[0].length() == 3) && strArrSplit[1].length() == 2 && StringUtils.isAllUpperCase(strArrSplit[1]))) {
                return new Locale(strArrSplit[0], strArrSplit[1]);
            }
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        if (length2 == 2 && StringUtils.isAllLowerCase(strArrSplit[0]) && ((strArrSplit[0].length() == 2 || strArrSplit[0].length() == 3) && ((strArrSplit[1].length() == 0 || (strArrSplit[1].length() == 2 && StringUtils.isAllUpperCase(strArrSplit[1]))) && strArrSplit[2].length() > 0))) {
            return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);
    }

    public static List<Locale> localeLookupList(Locale locale) {
        return localeLookupList(locale, locale);
    }

    public static List<Locale> localeLookupList(Locale locale, Locale locale2) {
        ArrayList arrayList = new ArrayList(4);
        if (locale != null) {
            arrayList.add(locale);
            if (locale.getVariant().length() > 0) {
                arrayList.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (locale.getCountry().length() > 0) {
                arrayList.add(new Locale(locale.getLanguage(), ""));
            }
            if (!arrayList.contains(locale2)) {
                arrayList.add(locale2);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static List<Locale> availableLocaleList() {
        return SyncAvoid.AVAILABLE_LOCALE_LIST;
    }

    public static Set<Locale> availableLocaleSet() {
        return SyncAvoid.AVAILABLE_LOCALE_SET;
    }

    public static boolean isAvailableLocale(Locale locale) {
        return availableLocaleList().contains(locale);
    }

    public static List<Locale> languagesByCountry(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        List<Locale> list = cLanguagesByCountry.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        List<Locale> listAvailableLocaleList = availableLocaleList();
        for (int i = 0; i < listAvailableLocaleList.size(); i++) {
            Locale locale = listAvailableLocaleList.get(i);
            if (str.equals(locale.getCountry()) && locale.getVariant().isEmpty()) {
                arrayList.add(locale);
            }
        }
        List<Locale> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        ConcurrentMap<String, List<Locale>> concurrentMap = cLanguagesByCountry;
        concurrentMap.putIfAbsent(str, listUnmodifiableList);
        return concurrentMap.get(str);
    }

    public static List<Locale> countriesByLanguage(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        List<Locale> list = cCountriesByLanguage.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        List<Locale> listAvailableLocaleList = availableLocaleList();
        for (int i = 0; i < listAvailableLocaleList.size(); i++) {
            Locale locale = listAvailableLocaleList.get(i);
            if (str.equals(locale.getLanguage()) && locale.getCountry().length() != 0 && locale.getVariant().isEmpty()) {
                arrayList.add(locale);
            }
        }
        List<Locale> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        ConcurrentMap<String, List<Locale>> concurrentMap = cCountriesByLanguage;
        concurrentMap.putIfAbsent(str, listUnmodifiableList);
        return concurrentMap.get(str);
    }

    static class SyncAvoid {
        private static final List<Locale> AVAILABLE_LOCALE_LIST;
        private static final Set<Locale> AVAILABLE_LOCALE_SET;

        static {
            ArrayList arrayList = new ArrayList(Arrays.asList(Locale.getAvailableLocales()));
            AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(arrayList);
            AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet(arrayList));
        }

        SyncAvoid() {
        }
    }
}
