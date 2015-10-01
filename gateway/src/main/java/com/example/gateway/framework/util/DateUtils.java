package com.example.gateway.framework.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 */
public class DateUtils {
    private static final String TAG = "DateUtils";
    private static final int SHORT_DATE_FORMAT_LENGTH = 10;
    private static final int DATE_FORMAT_LENGTH = 23;
    private static SimpleDateFormat shortDateFormatter;
    private static SimpleDateFormat dateFormatter;
    private static SimpleDateFormat timeZoneDateFormatter;
    private static SimpleDateFormat currentTimeZoneDateFormatter;

    static {
        shortDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        shortDateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        timeZoneDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        timeZoneDateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        currentTimeZoneDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        currentTimeZoneDateFormatter.setTimeZone(TimeZone.getDefault());
    }

    public static Date getDateFromJsonValue(String s, boolean includeTimeZone) {
        Date result = null;

        if (s != null && s.length() > 0)
            try {
                if (s.length() == SHORT_DATE_FORMAT_LENGTH) {
                    result = shortDateFormatter.parse(s);
                } else {
                    if (s.length() >= DATE_FORMAT_LENGTH) {
                        result = dateFormatter.parse(s);
                    } else if (includeTimeZone) {
                        result = timeZoneDateFormatter.parse(s);
                    }
                }
            } catch (ParseException e) {
                Log.e(TAG, "Error parsing date " + s + ": " + e.getMessage());
            }

        return result;
    }

    public static boolean dateStringIsValid(String s, boolean includeTimeZone) {
        return getDateFromJsonValue(s, includeTimeZone) != null;
    }

    public static String jsonShortDate(Date date) {
        return shortDateFormatter.format(date);
    }

    public static String jsonTimeZoneDateInCurrentTimeZone(Date date) {
        return currentTimeZoneDateFormatter.format(date);
    }

    public static String jsonTimeZoneDate(Date date) {
        return timeZoneDateFormatter.format(date);
    }
}
