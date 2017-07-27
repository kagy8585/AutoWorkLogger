package autoworklog.logviewer;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the basic methods of the WorkLogParser.
 * Created by kagy on 27.07.2017.
 */
class WorkLogParserTest {
    @Test
    void createWorkLog() {
        try {
            WorkLogParser parser = new WorkLogParser("tres/autoworklog.log");
            WorkLog log1, log2;

            //creating two identic logs
            log1=parser.createWorkLog();
            assertNotNull(log1);
            log2=parser.createWorkLog();
            assertNotNull(log2);
            //assert equals also should be done here
            assertTrue(log1.equals(log2));
            //testing tha days also
            WorkDay[] days=log1.getDays();

            assertNotNull(days);
            assertTrue(days.length==1);
            //getting work duration
            Duration workDuration=days[0].getWorkDuration();

            assertNotNull(workDuration);
            assertEquals(workDuration.toMinutes(), 84);
        }
        catch (IllegalArgumentException e) {
            fail("IllegalArgumentException thrown with message: \""+e.getMessage()+"\"!");
        }
    }

}