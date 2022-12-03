import com.vorono4ka.timeutil.DateTimeFormatter;
import com.vorono4ka.timeutil.TimeFormatter;
import com.vorono4ka.timeutil.TimeUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Test written with junit:5.8.1
 */
public class TimeUtilTest {
    // Units as seconds
    private static final int aDay = 86400;
    private static final int anHour = 3600;
    private static final int aMinute = 60;
    private static final int aSecond = 1;
    private static final float aMillisecond = 0.001f;

    private static final float testValue1 = aDay + anHour + aMinute + aSecond + aMillisecond;
    private static final int testValue2 = anHour + aSecond;

    @Test
    public void timeToStringDefaultFormatter() {
        assertEquals("0 ms", TimeUtil.timeToString(0));
        assertEquals("1 ms", TimeUtil.timeToString(aMillisecond));
        assertEquals("1 sec", TimeUtil.timeToString(aSecond));
        assertEquals("1 min", TimeUtil.timeToString(aMinute));
        assertEquals("1 hour", TimeUtil.timeToString(anHour));
        assertEquals("1 day", TimeUtil.timeToString(aDay));
        assertEquals("1 day 1 hour 1 min 1 sec", TimeUtil.timeToString(testValue1));

        assertEquals("1 hour 1 sec", TimeUtil.timeToString(testValue2));
    }

    @Test
    public void timeToStringDateFormatter() {
        assertEquals("0ms", TimeUtil.timeToString(0, TimeUtil.getDateTimeFormatter()));
        assertEquals("1ms", TimeUtil.timeToString(aMillisecond, TimeUtil.getDateTimeFormatter()));
        assertEquals("1s", TimeUtil.timeToString(aSecond, TimeUtil.getDateTimeFormatter()));
        assertEquals("1m", TimeUtil.timeToString(aMinute, TimeUtil.getDateTimeFormatter()));
        assertEquals("1h", TimeUtil.timeToString(anHour, TimeUtil.getDateTimeFormatter()));
        assertEquals("1d", TimeUtil.timeToString(aDay, TimeUtil.getDateTimeFormatter()));
        assertEquals("1d:1h:1m:1s", TimeUtil.timeToString(testValue1, TimeUtil.getDateTimeFormatter()));

        assertEquals("1h:1s", TimeUtil.timeToString(testValue2, TimeUtil.getDateTimeFormatter()));
    }

    @Test
    public void timeToStringOwnFormatter() {
        TimeFormatter myOwnDateFormatter = new DateTimeFormatter() {
            @Override
            public boolean isZeroForbidden(int unitIndex) {
                return unitIndex == 0 || unitIndex >= 4;
            }
        };

        assertEquals("0h:0m:0s", TimeUtil.timeToString(0, myOwnDateFormatter));
        assertEquals("0h:0m:0s:1ms", TimeUtil.timeToString(aMillisecond, myOwnDateFormatter));
        assertEquals("0h:0m:1s", TimeUtil.timeToString(aSecond, myOwnDateFormatter));
        assertEquals("0h:1m:0s", TimeUtil.timeToString(aMinute, myOwnDateFormatter));
        assertEquals("1h:0m:0s", TimeUtil.timeToString(anHour, myOwnDateFormatter));
        assertEquals("1d:0h:0m:0s", TimeUtil.timeToString(aDay, myOwnDateFormatter));
        assertEquals("1d:1h:1m:1s", TimeUtil.timeToString(testValue1, myOwnDateFormatter));

        assertEquals("1h:0m:1s", TimeUtil.timeToString(testValue2, myOwnDateFormatter));

        assertEquals("1d:1h:0m:1s", TimeUtil.timeToString(aDay + anHour + aSecond, myOwnDateFormatter));
    }
}
