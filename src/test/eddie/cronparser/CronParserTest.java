import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CronParserTest {

    @Test
    public void testCronParserWithInput1() {
        String cronString = "*/15 0 1,5 * 1-5 /usr/bin/find";
        String expectedOutput = "minute        0 15 30 45\n"
        + "hour          0\n"
        + "day of month  1 5\n"
        + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
        + "day of week   1 2 3 4 5\n"
        + "command       /usr/bin/find";
        assertEquals(expectedOutput, CronParser.parseCronString(cronString));
    }


    @Test
    public void testCronParserWithInput2() {
        String cronString = "*/15 2 4-15 * 2-6 /usr/bin/find";
        String expectedOutput = "minute        0 15 30 45\n"
        + "hour          2\n"
        + "day of month  4 5 6 7 8 9 10 11 12 13 14 15\n"
        + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
        + "day of week   2 3 4 5 6\n"
        + "command       /usr/bin/find";
        assertEquals(expectedOutput, CronParser.parseCronString(cronString));
    }
    // Add more test cases as needed
}
