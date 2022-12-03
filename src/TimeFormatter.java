import java.util.List;

public interface TimeFormatter {
    String formatUnit(float time, int unitIndex);
    String formatUnit(float time, int unitIndex, boolean zeroForbidden);
    String formatTime(List<String> units);

    int getUnitCount();
    boolean isZeroForbidden(int unitIndex);
}
