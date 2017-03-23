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
 * @version Mar 2017
 */
public class ScattReportTest
{
    private final ByteArrayOutputStream outContent = 
        new ByteArrayOutputStream();
    private ScattReport scattReportTests;

    /**
     * Set up before tests.
     */
    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        scattReportTests = new ScattReport();
    }
    
    /**
    *  Test for the reprot passing.
    */
    @Test
    public void out()
    {
        scattReportTests.makeReport();
        assertEquals("Report Pass\n", outContent.toString());
    }    

    /**
     * Tear down after tests.
     */
    @After
    public void tearDown()
    {
        System.setOut(null);
        System.setErr(null);
        scattReportTests = null;
    }
}
