import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
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
    	File directory = new File("submissions");  
    	File[] sb2s = directory.listFiles();  
    	Arrays.sort(sb2s);  
    	Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)  
        {
            submissions[i] = new Submission(sb2s[i]);
            submissions[i].convertToZip();
            submissions[i].unZip();
            submissions[i].parseJSONFile();
        }  
        scattReport = new Report(submissions);
    }
    
    /**
     *  Test for the report existing.
     */
    @Test
    public void makeReportTest()
    {
        scattReport.makeReport();
        File reportFile = new File("Report.txt");
        assertTrue(reportFile.exists());
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
