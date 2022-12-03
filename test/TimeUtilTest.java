import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Test written with junit:5.8.1
 */
public class TimeUtilTest {
    @Test
    public void timeToString() {
        assertEquals("1 hour 1 min 1 sec", TimeUtil.timeToString(3661));
        assertEquals("1 min 1 sec", TimeUtil.timeToString(61));
        assertEquals("1 sec", TimeUtil.timeToString(1));
        assertEquals("0 ms", TimeUtil.timeToString(0));

        assertEquals("1 hour 1 sec", TimeUtil.timeToString(3601));

        assertEquals("1h:1m:1s", TimeUtil.timeToString(3661, TimeUtil.getDateTimeFormatter()));
        assertEquals("1m:1s", TimeUtil.timeToString(61, TimeUtil.getDateTimeFormatter()));
        assertEquals("1s", TimeUtil.timeToString(1, TimeUtil.getDateTimeFormatter()));
        assertEquals("0ms", TimeUtil.timeToString(0, TimeUtil.getDateTimeFormatter()));

        assertEquals("1h:1s", TimeUtil.timeToString(3601, TimeUtil.getDateTimeFormatter()));
    }
}
