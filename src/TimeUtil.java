import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeUtil {
    private static final int[] UNIT_DIVIDERS = new int[]{1, 60, 60, 24, 7, 1};

    // Formatters
    private static final TimeFormatter DEFAULT_TIME_FORMATTER = new DefaultTimeFormatter();
    private static final TimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatter();

    public static String timeToString(float seconds) {
        return timeToString(seconds, DEFAULT_TIME_FORMATTER);
    }

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
            if (unitString == null) continue;

            unitsStrings.add(unitString);
        }

        if (unitsStrings.size() == 0) {
            String unitString = formatter.formatUnit(0, 0, false);
            return formatter.formatTime(List.of(unitString));
        }

        Collections.reverse(unitsStrings);
        return formatter.formatTime(unitsStrings);
    }

    public static TimeFormatter getDefaultTimeFormatter() {
        return DEFAULT_TIME_FORMATTER;
    }

    public static TimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }
}