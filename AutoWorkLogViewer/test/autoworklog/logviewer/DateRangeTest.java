package autoworklog.logviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Data Range testing class.
 * Created by kagy on 25.07.2017.
 */
class DateRangeTest {

    //3 basic data ranges
    private DateRange range1, range2, range3;

    @BeforeEach
    void setUp() {
        //creating data ranges
        //range 1 and 2 should be the same
        LocalDateTime start1=LocalDateTime.of(2017, 6, 1, 10, 0, 0);
        LocalDateTime end1=LocalDateTime.of(2017, 6, 1, 10, 49, 0);
        range1=new DateRange(start1, end1);
        LocalDateTime start2=LocalDateTime.of(2017, 6, 1, 10, 0, 0);
        LocalDateTime end2=LocalDateTime.of(2017, 6, 1, 10, 49, 0);
        range2=new DateRange(start2, end2);

        //range 3 contains only a minor difference
        LocalDateTime start3=LocalDateTime.of(2017, 6, 1, 10, 0, 0);
        LocalDateTime end3=LocalDateTime.of(2018, 6, 1, 10, 49, 0);
        range3=new DateRange(start3, end3);
    }

    @Test
    void setBounds() {
        DateRange range=new DateRange();
        LocalDateTime start, end;

        start=LocalDateTime.of(2017, 6, 1, 10, 30, 0);
        end=LocalDateTime.of(2017, 6, 1, 11, 0, 0);
        //setting up correct bounds
        range.setBounds(start, end);
        assertTrue(start.equals(range.getStartDate()));
        assertTrue(end.equals(range.getEndDate()));
        //setting up incorrect bounds
        try {
            //setting illegal bounds
            range.setBounds(end, start);
            fail("Illegal argument exception not thrown!");
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getDuration() {
        LocalDateTime start=LocalDateTime.of(2017, 6, 1, 10, 0, 0);
        LocalDateTime end=LocalDateTime.of(2017, 6, 1, 10, 49, 0);
        DateRange range=new DateRange(start, end);
        //computing duration
        Duration d=range.getDuration();

        //verification
        assertTrue(d.equals(Duration.ofMinutes(49)));
    }

    @Test
    void contains() {
        LocalDateTime between=LocalDateTime.of(2017, 6, 1, 10, 39, 0);
        LocalDateTime outside=LocalDateTime.of(2017, 6, 1, 12, 39, 0);

        assertTrue(range1.contains(between));
        assertFalse(range1.contains(outside));
        assertTrue(range1.contains(range1.getStartDate()));
        assertTrue(range1.contains(range1.getEndDate()));
    }

    @Test
    void overlapsWith() {
        //creating a range, that contains range1
        LocalDateTime start=LocalDateTime.of(2017, 6, 1, 9, 0, 0);
        LocalDateTime end=LocalDateTime.of(2017, 6, 1, 10, 50, 0);
        DateRange rangeTemp=new DateRange(start, end);
        assertTrue(range1.overlapsWith(rangeTemp));
        assertTrue(rangeTemp.overlapsWith(range1));

        //creating a range that partially overlaps with range 1
        start=LocalDateTime.of(2017, 6, 1, 10, 30, 0);
        end=LocalDateTime.of(2017, 6, 1, 10, 51, 0);
        rangeTemp=new DateRange(start, end);
        assertTrue(range1.overlapsWith(rangeTemp));
        assertTrue(rangeTemp.overlapsWith(range1));

        //tetsting with thwe other ranges
        assertTrue(range1.overlapsWith(range2));
        assertTrue(range1.overlapsWith(range3));
    }

    @Test
    void testCompareTo() {
        assertTrue(range1.compareTo(range2)==0);
        assertTrue(range2.compareTo(range1)==0);
        assertTrue(range1.compareTo(range3)<0);
        assertTrue(range3.compareTo(range1)>0);
        assertTrue(range2.compareTo(range3)<0);
        assertTrue(range3.compareTo(range2)>0);

    }

    @Test
    void testEquals() {
        assertTrue(range1.equals(range2));
        assertFalse(range1.equals(range3));
        assertFalse(range2.equals(range3));
    }

    @Test
    void testHashCode() {
        assertTrue(range1.hashCode()==range2.hashCode());
        assertFalse(range1.hashCode()==range3.hashCode());
        assertFalse(range2.hashCode()==range3.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(range1.toString());
        assertNotNull(range2.toString());
        assertNotNull(range3.toString());
    }

}