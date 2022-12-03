import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Test written with junit:5.8.1
 */
public class TimeUtilTest {
    @Test
    public void timeToString() {
        TimeFormatter myOwnDateFormatter = new DateTimeFormatter() {
            @Override
            public boolean isZeroForbidden(int unitIndex) {
                return unitIndex == 0 || unitIndex >= 4;
            }
        };

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

        assertEquals("1h:1m:1s", TimeUtil.timeToString(3661, myOwnDateFormatter));
        assertEquals("0h:1m:1s", TimeUtil.timeToString(61, myOwnDateFormatter));
        assertEquals("0h:0m:1s", TimeUtil.timeToString(1, myOwnDateFormatter));
        assertEquals("0h:0m:0s", TimeUtil.timeToString(0, myOwnDateFormatter));

        assertEquals("1h:0m:1s", TimeUtil.timeToString(3601, myOwnDateFormatter));
        assertEquals("1d:0h:0m:0s", TimeUtil.timeToString(86400, myOwnDateFormatter));
        assertEquals("1d:1h:0m:1s", TimeUtil.timeToString(90001, myOwnDateFormatter));
    }
}
