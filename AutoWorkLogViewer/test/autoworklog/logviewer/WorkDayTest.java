package autoworklog.logviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the work day class.
 * Created by kagy on 26.07.2017.
 */
class WorkDayTest {


    private LocalDate date1, date2, date3;
    private DateRange[] intervals1, intervals2, intervals3;
    private WorkDay day1, day2, day3;

    /**
     * Creates the first date involved in the simulation.
     * @return it the firs date involved in the simulation.
     */
    private LocalDate createDate() {
        return LocalDate.of(2017, 1, 1);
    }

    /**
     * Creates work intervals for a work day.
     * @param date is the date of the work intervals.
     * @return is the array of work intervals.
     */
    private DateRange[] createWorkIntervals(LocalDate date) {
        DateRange[] intervals;

        intervals=new DateRange[3];
        intervals[0]=new DateRange(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                1, 10, 11),
                LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                        2, 0, 0));
        intervals[1]=new DateRange(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                10, 21, 11),
                LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                        12, 10, 59));
        intervals[2]=new DateRange(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                5, 40, 12),
                LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                        7, 30, 19));

        return intervals;
    }

    /**
     * Creates a new work day with a given set of intervals.
     * @param date is the input date.
     * @param workIntervals is the vector of work intervals.
     * @return is a work day obtained, on the day, with the given intervals.
     */
    private WorkDay createWorkDay(LocalDate date, DateRange[] workIntervals) {
        WorkDay day;

        day=new WorkDay(date);
        for (DateRange range : workIntervals)
            day.addWorkInterval(range);
        return day;
    }

    @BeforeEach
    void setUp() {

        //creating the first work day
        date1=createDate();
        intervals1=createWorkIntervals(date1);
        day1=createWorkDay(date1, intervals1);
        //creating the second work day equal with the first one
        date2=createDate();
        intervals2=createWorkIntervals(date2);
        day2=createWorkDay(date1, intervals2);
        //creating the third wrk day different from the previous ones
        date3=LocalDate.of(2017, 1, 2);
        intervals3=new DateRange[3];
        intervals3[0]=new DateRange(LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                1, 10, 11),
                LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                        2, 0, 0));
        intervals3[1]=new DateRange(LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                10, 21, 11),
                LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                        12, 10, 59));
        intervals3[2]=new DateRange(LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                5, 40, 12),
                LocalDateTime.of(date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth(),
                        7, 30, 19));
        day3=createWorkDay(date3, intervals3);
    }

    @Test
    void addWorkInterval() {
        DateRange range;

        //adding a valid data range
        range=new DateRange(LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                        3, 49, 11),
                LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                        4, 1, 0));
        try {
            day1.addWorkInterval(range);
        }
        catch (IllegalArgumentException e) {
            fail("Illegal argument exception thrown!");
        }
        //adding data range that ovewlaps with the previous data range
        //only the minutes overlap with range 1
        range=new DateRange(LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                3, 40, 11),
                LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                        4, 11, 0));
        try {
            day1.addWorkInterval(range);
            fail("IllegalArgumentException is not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        //adding a range with invalid starting date
        range=new DateRange(LocalDateTime.of(date1.getYear()-1, date1.getMonthValue(), date1.getDayOfMonth(),
                3, 49, 11),
                LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                        4, 1, 0));
        try {
            day1.addWorkInterval(range);
            fail("IllegalArgumentException is not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        //adding range with invalid end date
        range=new DateRange(LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(),
                3, 49, 11),
                LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth()+1,
                        4, 1, 0));
        try {
            day1.addWorkInterval(range);
            fail("IllegalArgumentException is not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getWorkDuration() {
        Duration dur=Duration.ofNanos(0);

        for (DateRange range : intervals1)
            dur=dur.plus(range.getDuration());
        assertTrue(dur.equals(day1.getWorkDuration()));
    }

    @Test
    void getMinWorkInterval() {
        DateRange minWorkInterval=day1.getMinWorkInterval();

        assertTrue(minWorkInterval.equals(intervals1[0]));
    }

    @Test
    void getMaxWorkInterval() {
        DateRange maxWorkInterval=day1.getMaxWorkInterval();

        assertTrue(maxWorkInterval.equals(intervals1[2]));
    }

    @Test
    void getDay() {
        assertTrue(date1.equals(day1.getDay()));
        assertTrue(date3.equals(day3.getDay()));
    }

    @Test
    void getWorkIntervals() {
        DateRange[] intervals=day1.getWorkIntervals();

        for (int i=0; i+1<intervals.length; i++) {
            assertTrue(intervals[i].compareTo(intervals[i+1])<0);
        }
    }

    @Test
    void testCompareTo() {
        assertTrue(day1.compareTo(day2)==0);
        assertTrue(day2.compareTo(day1)==0);
        assertTrue(day1.compareTo(day3)<0);
        assertTrue(day3.compareTo(day1)>0);
        assertTrue(day2.compareTo(day3)<0);
        assertTrue(day3.compareTo(day2)>0);
        assertTrue(day3.compareTo(day3)==0);
    }

    @Test
    void testEquals() {
        assertTrue(day1.equals(day2));
        assertTrue(day2.equals(day1));
        assertFalse(day1.equals(day3));
        assertFalse(day3.equals(day1));
        assertFalse(day2.equals(day3));
        assertFalse(day3.equals(day2));
        assertFalse(day1.equals(new Object()));
    }

    @Test
    void testCashCode() {
        assertTrue(day1.hashCode()==day2.hashCode());
        assertTrue(day2.hashCode()==day1.hashCode());
        assertTrue(day3.hashCode()!=day1.hashCode());
        assertTrue(day1.hashCode()!=day3.hashCode());
        assertTrue(day3.hashCode()!=day2.hashCode());
        assertTrue(day2.hashCode()!=day3.hashCode());
    }

}