import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.nio.file.Files;
import java.io.IOException;

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
        // Set up expected files.
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        
        // Create actual Submission files.
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
        // Set up expected files.
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        
        // Create actual Submission files.
        Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
        }
        
        // Set up expected filenames.
        String[] expected = new String[sb2s.length];
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] = sb2s[i].getName();
        }
        
        // Set up actual filenames.
        String[] actual = new String[submissions.length];
        for (int i = 0; i < actual.length; i++)
        {
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
        // Set up expected files.
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        
        // Check each expected file validity.
        boolean[] expected = new boolean[sb2s.length];
        for (int i = 0; i < expected.length; i++)
        {
            String filename = sb2s[i].getName();
            int len = filename.length();
            String ext = filename.substring(len - 4);
            expected[i] = ext.equals(".sb2") && sb2s[i].isFile();
        }
        
        // Create actual Submission files.
        // Check each actaul file validity.
        Submission[] submissions = new Submission[sb2s.length];
        boolean[] actual = new boolean[submissions.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
            actual[i] = submissions[i].isValid();
        }
        
        assertTrue("should be same", Arrays.equals(expected, actual));
    }

    /**
     * Test conversion to .zip.
     *
     * @throws IOException 
     */
    @Test
    public void testZip() throws IOException
    {
        // Set up expected files.
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        
        // Create actual Submission files.
        // Convert to .zip.
        Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
            submissions[i].convertToZip();
        }
        
        // Copy and convert expected files to .zip.
        File expectedDir = new File("expected");
        expectedDir.mkdir();
        for (int i = 0; i < sb2s.length; i++)
        {
            String sb2name = sb2s[i].getName();
            int len = sb2name.length();
            if (sb2name.substring(len - 4).equals(".sb2") && sb2s[i].isFile())
            {
                String zipname = sb2name.substring(0, len - 4) + ".zip";
                File copy = new File(expectedDir, zipname);
                Files.copy(sb2s[i].toPath(), copy.toPath());
            }
        }
        
        // Get expected filenames.
        File[] expectedZips = expectedDir.listFiles();
        String[] expected = new String[expectedZips.length];
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] = expectedZips[i].getName();
        }
        
        // Get actual Submission filenames.
        String[] actual = new String[submissions.length];
        for (int i = 0; i < actual.length; i++)
        {
            actual[i] = submissions[i].getName();
        }

        assertArrayEquals("should be same", expected, actual);
    }
}
