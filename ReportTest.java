import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
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
        scattReport = new Report();
    }
    
    /**
    *  Test for the report passing.
    */
    @Test
    public void makeReportTest()
    {
        scattReport.makeReport();
        assertEquals("Report Pass\n", outContent.toString());
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

