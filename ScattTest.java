import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

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
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File testDir;
    private File testFile;
    public Scatt testScatt;

    /**
     * Test Valid Folder.
     */
    @Test
    public void testValidFolder() throws IOException
    {
        testScatt = new Scatt();
        testDir = folder.newFolder("testDir");
        testFile = folder.newFile("testFile.txt");
        //assertTrue(testDir.exists());
        //assertTrue(testFile.exists());
        assertTrue(testScatt.readValidDirectory(testDir.getName()));
        
    }
}
