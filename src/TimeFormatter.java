import java.util.List;

public interface TimeFormatter {
    String formatUnit(float time, int unitIndex, boolean allowZero);
    String formatTime(List<String> units);

    int getUnitCount();
}
