package com.instinctools.reducerlink.service.support;

import java.util.Calendar;

public final class CalendarUtils {
    private CalendarUtils() {
    }

    public static Calendar fromTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000L);
        return calendar;
    }

    public static Calendar truncateTo(Calendar calendar, int field) {
        if (field == Calendar.MILLISECOND) {
            return calendar;
        }

        calendar.set(Calendar.MILLISECOND, 0);

        if (field == Calendar.SECOND) {
            return calendar;
        }

        calendar.set(Calendar.SECOND, 0);

        if (field == Calendar.MINUTE) {
            return calendar;
        }

        calendar.set(Calendar.MINUTE, 0);

        if (field == Calendar.HOUR_OF_DAY) {
            return calendar;
        }

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        if (field == Calendar.DAY_OF_MONTH) {
            return calendar;
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        if (field == Calendar.MONTH) {
            return calendar;
        }

        calendar.set(Calendar.MONTH, Calendar.JANUARY);

        return calendar;
    }
}
