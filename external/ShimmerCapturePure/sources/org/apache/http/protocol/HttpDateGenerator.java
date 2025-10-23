package org.apache.http.protocol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class HttpDateGenerator {
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private final DateFormat dateformat;
    private long dateAsLong = 0;
    private String dateAsText = null;

    public HttpDateGenerator() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_RFC1123, Locale.US);
        this.dateformat = simpleDateFormat;
        simpleDateFormat.setTimeZone(GMT);
    }

    public synchronized String getCurrentDate() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.dateAsLong > 1000) {
            this.dateAsText = this.dateformat.format(new Date(jCurrentTimeMillis));
            this.dateAsLong = jCurrentTimeMillis;
        }
        return this.dateAsText;
    }
}
