import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.Arrays;
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
    *  Test for the report passing.
    */
    @Test
    public void makeReportTest()
    {
        scattReport.makeReport();
        String expected = "------------\n"
                        + "SCATT Report\n"
                        + "------------\n\n"
                        + "File: Animate the Crab.sb2\n"
                        + "---------------------------------\n"
                        + "Script count: 3\n"
                        + "Sprite count: 2\n\n"
                        + "Sprite: Crab\n"
                        + "Script count: 3\n\n"
                        + "Sprite: Bananas\n"
                        + "Script count: 0\n\n"
                        + "File: Big Project.sb2\n"
                        + "---------------------------------\n"
                        + "Script count: 6\n"
                        + "Sprite count: 2\n\n"
                        + "Sprite: Sprite1\n"
                        + "Script count: 2\n\n"
                        + "Sprite: Butterfly3\n"
                        + "Script count: 2\n\n"
                        + "File: Empty.sb2\n"
                        + "---------------------------------\n"
                        + "Script count: 0\n"
                        + "Sprite count: 0\n"
                        + "\n";
        assertEquals("should be equal", expected, outContent.toString());
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
