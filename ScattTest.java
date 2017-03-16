import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertTrue;

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
     * Test Valid Folder.
     */
    @Test
    public void testValidFolder()
    {
        File dirName = new File("Test");
        assertTrue(dirName.exists());

    }
}
