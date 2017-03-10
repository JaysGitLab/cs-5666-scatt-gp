import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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
public class ScattTest
{
    /**
     * Test constructor of Submission object.
     */
    @Test
    public void testSubmissionConstructor()
    {
        String filename = "File1.sb2";
        Submission fileOne = new Submission(filename);
        assertNotNull("should not be null", fileOne);
    }
    
    /**
     * Test getFilename for Submission object.
     */
    @Test
    public void testSubmissionGetFilename()
    {
        String filename = "File1.sb2";
        Submission fileOne = new Submission(filename);
        String actual = fileOne.getFilename();
        assertEquals("failure - strings are not equal", filename, actual);
    }
}
