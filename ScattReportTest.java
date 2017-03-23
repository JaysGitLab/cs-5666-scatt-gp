import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.After;

/**
 * ScattTest.java
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 */

/**
 * Class to test Scatt class.
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Feb 2017
 */
public class ScattReportTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    /**
     * Set up before tests.
     */
    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    /**
    *  Test for the reprot passing.
    */
    @Test
    public void out()
    {
        System.out.print("Report Pass");
        assertEquals("Report Pass", outContent.toString());
    }

    /**
     * Test for the report failing.
     */
    @Test
    public void err()
    {
        System.err.print("Report Fail");
        assertEquals("Report Fail", errContent.toString());
    }

    /**
     * Tear down after tests.
     */
    @After
    public void tearDown()
    {
        System.setOut(null);
        System.setErr(null);
    }
}
