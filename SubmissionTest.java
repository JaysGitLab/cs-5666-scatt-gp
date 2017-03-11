import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

/**
 * SubmissionTest.java
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 */

/**
 * Class to test Submission class.
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */
public class SubmissionTest
{
    /**
     * Test constructor of Submission object.
     */
    @Test
    public void testSubmissionConstructor()
    {
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
        }
        assertNotNull("should not be null", submissions);
    }

    /**
     * Test getName.
     */
    @Test
    public void testGetName()
    {
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
        }
        String[] expected = new String[sb2s.length];
        String[] actual = new String[submissions.length];
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] = sb2s[i].getName();
            actual[i] = submissions[i].getName();
        }
        assertArrayEquals("should be same", expected, actual);
    }

    /**
     * Test valid .sb2s.
     */
    @Test
    public void testValid()
    {
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        boolean[] expected = new boolean[sb2s.length];
        for (int i = 0; i < expected.length; i++)
        {
            String filename = sb2s[i].getName();
            int len = filename.length();
            String ext = filename.substring(len - 5);
            expected[i] = ext.equals(".sb2") && sb2s[i].isFile();
        }
        Submission[] submissions = new Submission[sb2s.length];
        boolean[] actual = new boolean[submissions.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
            actual[i] = submissions[i].isValid();
        }
        assertTrue("should be same", Arrays.equals(expected, actual));
    }
}
