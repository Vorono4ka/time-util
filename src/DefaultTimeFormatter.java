import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DefaultTimeFormatter implements TimeFormatter {
    private static final String[] UNIT_NAMES = {"ms", "sec", "min", "hour", "day", "week"};
    private static final List<Function<Float, Integer>> UNIT_ROUNDERS;
    public static final boolean ZERO_FORBIDDEN = true;

    static {
        UNIT_ROUNDERS = new ArrayList<>();
        for (int i = 0; i < UNIT_NAMES.length; i++) {
            UNIT_ROUNDERS.add(i, Math::round);
        }

        // milliseconds formatter
        UNIT_ROUNDERS.set(0, (integer -> Math.round(integer * 1000)));
    }

    @Override
    public String formatUnit(float time, int unitIndex) {
        return formatUnit(time, unitIndex, ZERO_FORBIDDEN);
    }

    @Override
    public String formatUnit(float time, int unitIndex, boolean zeroForbidden) {
        int roundedTime = UNIT_ROUNDERS.get(unitIndex).apply(time);
        if (zeroForbidden && roundedTime == 0) return null;

        return String.format("%d %s", roundedTime, UNIT_NAMES[unitIndex]);
    }

    @Override
    public String formatTime(List<String> units) {
        return String.join(" ", units);
    }

    @Override
    public int getUnitCount() {
        return UNIT_NAMES.length;
    }
}
