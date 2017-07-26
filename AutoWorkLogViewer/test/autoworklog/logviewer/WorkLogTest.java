package autoworklog.logviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing class for the worj log,
 * Created by kagy on 26.07.2017.
 */
class WorkLogTest {

    private WorkLog log1, log2, log3;
    private WorkDay[] days1, days2, days3;

    /**
     * Creates simple work days.
     * @return a vector of work days.
     */
    private WorkDay[] createWorkDays() {
        WorkDay[] days = new WorkDay[4];

        for (int i=0; i<days.length; i++) {
            //using full hours in order to compute the difference
            days[i] = new WorkDay(LocalDate.of(2017, 2, i + 1));

            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 8, 10, 0),
                    LocalDateTime.of(2017, 2, i + 1, 10, 0, 0)
            ));
            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 11, 20, 0),
                    LocalDateTime.of(2017, 2, i + 1, 13, 0, 0)
            ));
            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 17, 30, 0),
                    LocalDateTime.of(2017, 2, i + 1, 19, 0, 0)
            ));
        }
        //returning the days
        return days;
    }

    /**
     * Creates more work days than the previous work days.
     * @return is a vector of work days.
     */
    private WorkDay[] createMoreWorkDays() {
        WorkDay[] days=new WorkDay[7];

        for (int i=0; i<days.length; i++) {
            days[i] = new WorkDay(LocalDate.of(2017, 2, i + 1));

            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 9, 0, 0),
                    LocalDateTime.of(2017, 2, i + 1, 10, 0, 0)
            ));
            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 11, 0, 0),
                    LocalDateTime.of(2017, 2, i + 1, 12, 0, 0)
            ));
            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 16, 0, 0),
                    LocalDateTime.of(2017, 2, i + 1, 19, 0, 0)
            ));
            days[i].addWorkInterval(new DateRange(
                    LocalDateTime.of(2017, 2, i + 1, 21, 0, 0),
                    LocalDateTime.of(2017, 2, i + 1, 22, 0, 0)
            ));
        }
        return days;
    }

    /**
     * Creates a simple work day.
     * @param days is the vector of days composing the WorkLog.
     * @return returns the work log that was created.
     */
    private WorkLog createWorkLog(WorkDay[] days) {
        WorkLog log=new WorkLog();

        //Adding the days in reverse order, in order to test the sorting behaviour.
        for (int i=days.length-1; i>=0; i--) {
            log.addDay(days[i]);
        }
        return log;
    }

    @BeforeEach
    void setUp() {
        //creating two same work logs
        days1 = createWorkDays();
        log1=createWorkLog(days1);
        days2 = createWorkDays();
        log2=createWorkLog(days2);
        //and creating a different one
        days3=createMoreWorkDays();
        log3=createWorkLog(days3);
    }

    @Test
    void addDay() {
        WorkDay day=new WorkDay(LocalDate.of(2017, 2, 6));

        try {
            log1.addDay(day);
        }
        catch (IllegalArgumentException e){
            fail("Illegal argument exception thrown!");
        }
        try {
            log1.addDay(null);
            fail("Illegal argument exception not thrown!");
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getAverageWorkDuration() {
        Duration average=log3.getAverageWorkDuration();

        //there is 6 hours of work on each days
        assertEquals(0, average.compareTo(Duration.ofHours(6)));
    }

    @Test
    void getTotalWorkDuration() {
        Duration total=log3.getTotalWorkDuration();

        //there is 6 hours of work on each day
        assertEquals(0, total.compareTo(Duration.ofHours(6*7)));
    }

    @Test
    void getMaxWorkInterval() {
        DateRange maxInterval=log3.getMaxWorkInterval();

        assertTrue(maxInterval.getDuration().equals(Duration.ofHours(3)));
    }

    @Test
    void getMinWorkInterval() {
        DateRange minInterval=log3.getMinWorkInterval();

        assertTrue(minInterval.getDuration().equals(Duration.ofHours(1)));
    }

    @Test
    void getDays() {
        WorkDay[] days=log3.getDays();

        assertNotNull(days);
        assertEquals(days.length, days3.length);
        for (int i=0; i<days.length; i++) {
            assertTrue(days[i].equals(days3[i]));
        }
    }

    @Test
    void testEquals() {
        //the first two logs are actually the same and the third one is different
        assertTrue(Arrays.deepEquals(days1, days2));
        assertTrue(log1.equals(log2));
        assertTrue(log2.equals(log1));
        assertFalse(log1.equals(log3));
        assertFalse(log3.equals(log1));
        assertFalse(log2.equals(log3));
        assertFalse(log3.equals(log2));
    }

    @Test
    void testHashCode() {
        //the first two logs are actually the same and the third one is different
        assertTrue(log1.hashCode()==log2.hashCode());
        assertTrue(log1.hashCode()!=log3.hashCode());
        assertTrue(log2.hashCode()!=log3.hashCode());
    }

}