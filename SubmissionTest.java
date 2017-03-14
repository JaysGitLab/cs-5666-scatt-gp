import org.junit.Test;
import org.junit.Before;
import org.junit.After;
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
    private File directory;
    private File[] sb2s;
    private Submission[] submissions;
    
    /**
     * Set up for tests.
     */
    @Before
    public void setUp()
    {
        // Set up expected files.
        directory = new File("submissions");
        sb2s = directory.listFiles();
        
        // Create actual Submission files.
        submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
        }
    }
    
    /**
     * Test constructor of Submission object.
     */
    @Test
    public void testSubmissionConstructor()
    {
        assertNotNull("should not be null", submissions);
    }

    /**
     * Test getName.
     */
    @Test
    public void testGetName()
    {
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
        // Check each expected file validity.
        boolean[] expected = new boolean[sb2s.length];
        for (int i = 0; i < expected.length; i++)
        {
            String filename = sb2s[i].getName();
            int len = filename.length();
            String ext = filename.substring(len - 4);
            expected[i] = ext.equals(".sb2") && sb2s[i].isFile();
        }
        
        // Check each actaul file validity.
        boolean[] actual = new boolean[submissions.length];
        for (int i = 0; i < submissions.length; i++)
        {
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
        // Copy and convert actual files to .zip.
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].convertToZip();
        }
        
        // Copy and convert expected files to .zip.
        File expectedDir = new File("expected");
        if (!expectedDir.exists())
        {
            expectedDir.mkdir();
        }
        for (int i = 0; i < sb2s.length; i++)
        {
            String sb2name = sb2s[i].getName();
            int len = sb2name.length();
            if (sb2name.substring(len - 4).equals(".sb2") && sb2s[i].isFile())
            {
                String zipname = sb2name.substring(0, len - 4) + ".zip";
                File copy = new File(expectedDir, zipname);
                if (!copy.exists())
                {
                    Files.copy(sb2s[i].toPath(), copy.toPath());
                }
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
        File actualDir = new File("zips");
        File[] actualZips = actualDir.listFiles();
        String[] actual = new String[actualZips.length];
        for (int i = 0; i < actual.length; i++)
        {
            actual[i] = actualZips[i].getName();
        }

        assertArrayEquals("should be same", expected, actual);
    }

    /**
     * Test unzip of files.
     * 
     * @throws IOException 
     */
    @Test
    public void testUnzip() throws IOException
    {
        // Copy and convert actual files to .zip.
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].convertToZip();
        }
        
        // Get list of expected dir names.
        String[] expected = new String[submissions.length]; 
        for (int i = 0; i < submissions.length; i++)
        {
            String zipName = submissions[i].getName();
            int len = zipName.length();
            expected[i] = zipName.substring(0, len - 4);
        }
        
        // Unzip files.
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].unZip();
        }

        // Get list of new zip dirs.
        File zipDir = new File("zips");
        File[] zipDirs = zipDir.listFiles();
        String[] actual = new String[zipDirs.length];
        for (int i = 0; i < actual.length; i++)
        {
            actual[i] = zipDirs[i].getName();
        }

        assertArrayEquals("should be same", expected, actual);
    }

    /**
     * Tear down after tests.
     */
    @After
    public void tearDown()
    {
        // Delete expected files.
        directory.delete();
        
        // Set arrays to null.
        sb2s = null;
        submissions = null;
    }
}
