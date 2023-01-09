package com.vorono4ka.timeutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A utility for formatting time into string.
 * @author <a href="https://github.com/Vorono4ka/">Vorono4ka</a>
 */
public class TimeUtil {
    private static final int[] UNIT_DIVIDERS = new int[]{1, 60, 60, 24, 7, 1};

    // Formatters
    private static final TimeFormatter DEFAULT_TIME_FORMATTER = new DefaultTimeFormatter();
    private static final TimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatter();

    /**
     * Formats time into string using {@link DefaultTimeFormatter}.
     *
     * @param seconds time as seconds
     * @return time string
     * @see TimeUtil#timeToString(float, TimeFormatter)
     */
    public static String timeToString(float seconds) {
        return timeToString(seconds, DEFAULT_TIME_FORMATTER);
    }

    /**
     * Formats time into string using given {@link TimeFormatter} object.
     *
     * @param seconds time as seconds
     * @param formatter {@link TimeFormatter} object
     * @return time string
     */
    public static String timeToString(float seconds, TimeFormatter formatter) {
        if (seconds < 0) {
            throw new RuntimeException("Time cannot be negative.");
        }

        List<String> unitsStrings = new ArrayList<>();
        for (int i = 0; i < formatter.getUnitCount(); i++) {
            int divider = UNIT_DIVIDERS[i];

            float unit = seconds % divider;
            seconds = (int) (seconds / divider);

            String unitString = formatter.formatUnit(unit, i);
            if (unitString == null) {
                if (i == formatter.getUnitCount() - 1 && seconds != 0) {
                    unitString = formatter.formatUnit(seconds, i, false);
                } else {
                    continue;
                }
            }

            unitsStrings.add(unitString);
        }

        if (unitsStrings.size() == 0) {
            String unitString;
            if (seconds == 0) unitString = formatter.formatUnit(0, 0, false);
            else unitString = formatter.formatUnit(seconds, formatter.getUnitCount() - 1, false);
            return formatter.formatTime(List.of(unitString));
        }

        Collections.reverse(unitsStrings);
        return formatter.formatTime(unitsStrings);
    }

    @SuppressWarnings("unused")
    public static TimeFormatter getDefaultTimeFormatter() {
        return DEFAULT_TIME_FORMATTER;
    }

    @SuppressWarnings("unused")
    public static TimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }
}