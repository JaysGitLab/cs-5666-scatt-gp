import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.After;

/**
 * ReportTest.java
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 */

/**
 * Class to test Report class.
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */
public class ReportTest
{
    private final ByteArrayOutputStream outContent = 
        new ByteArrayOutputStream();
    private Report scattReport;

    /**
     * Set up before tests.
     */
    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
        scattReport = new Report(submissions);
    }
    
    /**
     *  Test for the report existing.
     */
    @Test
    public void makeReportTest()
    {
        scattReport.makeReport();
        assertTrue(scattReport.exists());
    }

    /**
     * Tear down after tests.
     */
    @After
    public void tearDown()
    {
        System.setOut(null);
        scattReport = null;
    }
}
