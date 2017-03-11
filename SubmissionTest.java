import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;

import java.io.File;

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
        Submission submission = new Submission(directory);
        assertNotNull("should not be null", submission);
    }
    
    /**
     * Test creating File objects for each .sb2.
     */
    @Test
    public void testFileObjects()
    {
        File directory = new File("submissions");
        Submission submission = new Submission(directory);
        File[] expected = directory.listFiles();
        File[] actual = submission.getFiles();
        assertArrayEquals("failure - File arrays not same", expected, actual);
    }
}
