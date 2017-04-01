import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.nio.file.Files;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

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
    private static File directory;
    private static File expectedDir;
    private static File[] sb2s;
    private static Submission[] submissions;
    
    /**
     * Set up for tests.
     */
    @BeforeClass
    public static void setUp()
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
            String sb2Name = sb2s[i].getName();
            int len = sb2Name.length();
            String ext = sb2Name.substring(len - 4);
            expected[i] = ext.equals(".sb2") && sb2s[i].isFile();
        }
        
        // Check each actaul file validity.
        boolean[] actual = new boolean[submissions.length];
        for (int i = 0; i < actual.length; i++)
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
        // Copy and convert expected files to .zip.
        expectedDir = new File("expected");
        if (!expectedDir.exists())
        {
            expectedDir.mkdir();
        }
        for (int i = 0; i < sb2s.length; i++)
        {
            String sb2Name = sb2s[i].getName();
            int len = sb2Name.length();
            if (sb2Name.substring(len - 4).equals(".sb2") && sb2s[i].isFile())
            {
                String zipName = sb2Name.substring(0, len - 4) + ".zip";
                File copy = new File(expectedDir, zipName);
                if (!copy.exists())
                {
                    Files.copy(sb2s[i].toPath(), copy.toPath());
                }
            }
        }
        
        // Copy and convert actual files to .zip.
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].convertToZip();
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
        // Get list of expected dir names.
        expectedDir = new File("expected");
        File[] expectedZips = expectedDir.listFiles(); 
        String[] expected = new String[expectedZips.length]; 
        for (int i = 0; i < expected.length; i++)
        {
            String zipName = expectedZips[i].getName();
            int len = zipName.length();
            expected[i] = zipName.substring(0, len - 4);
        }
        
        // Copy and convert actual files to .zip.
        // Unzip files.
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].convertToZip();
            submissions[i].unZip();
        }

        // Get list of new zip dirs.
        File zipsDir = new File("unzips");
        File[] zipDirs = zipsDir.listFiles();
        String[] actual = new String[zipDirs.length];
        for (int i = 0; i < actual.length; i++)
        {
            // Only track directories with more than one file.
            // Tests more than original zip file exists (unzip success).
            if (zipDirs[i].isDirectory() && zipDirs[i].listFiles().length > 1)
            {
                actual[i] = zipDirs[i].getName();
            }
        }

        assertArrayEquals("should be same", expected, actual);
    }

    /**
     * Test parsing valid JSON file.
     *
     * @throws FileNotFoundException ex
     */
    @Test
    public void testParseValidJSON() throws FileNotFoundException
    {
        submissions[2].convertToZip();
        submissions[2].unZip();
        submissions[2].parseJSONFile();
    
        assertNotNull("Should not be null", 
            (Object) submissions[2].getJSONObject());
    }

    /**
     * Test parsing invalid JSON file.
     *
     * @throws FileNotFoundException ex
     */
    public void testParseInvalidJSON() throws FileNotFoundException
    {
        submissions[0].convertToZip();
        submissions[0].unZip();
        submissions[0].parseJSONFile();
     
        assertNull(submissions[0].getJSONObject());
    } 

    /**
     * Test getting JSON attribute by name.
     *
     * @throws FileNotFoundException ex
     */
    @Test
    public void testGettingJSONAttribute() throws FileNotFoundException
    {
        submissions[2].convertToZip();
        submissions[2].unZip();
        submissions[2].parseJSONFile();

        assertEquals("Stage", submissions[2].getJSONAttribute("objName"));
    }

    /**
     * Test getting JSONArray attribute by name.
     *
     * @throws FileNotFoundException ex
     */
    @Test
    // Suppress warning being caused by adding to JSONArray,
    //  this is only for unit testing purposes.
    @SuppressWarnings("unchecked")
    public void testGettingJSONArrayAttribute() throws FileNotFoundException
    {
        submissions[2].convertToZip();
        submissions[2].unZip();
        submissions[2].parseJSONFile();
     
        // Create expected JSONArray.   
        JSONObject expectedObject = new JSONObject();
        JSONArray expectedArr = new JSONArray();
        // Object to add to the array.
        JSONObject attribute = new JSONObject();
        attribute = new JSONObject();
        attribute.put("soundID", 1);
        attribute.put("sampleCount", 258);
        attribute.put("rate", 11025);
        attribute.put("format", "");
        attribute.put("soundName", "pop"); 
        attribute.put("md5", "83a9787d4cb6f3b7632b4ddfebf74367.wav");
        expectedArr.add(attribute);
        expectedObject.put("sounds", expectedArr);
        
        assertEquals(expectedObject.get("sounds").toString(), 
            submissions[2].getJSONArrayAttribute("sounds").toString());
    }

    /**
     * Test getSpriteCount method.
     */
    @Test
    public void testGetSpriteCount()
    {
        int expected = 1;
        int actual = submissions[2].getSpriteCount();
        assertEquals("should be equal", expected, actual);
    }

    /**
     * Test deleteZips method.
     */
    @Test
    public void testDeleteZips()
    {
        File zipsDir = new File("zips");
        File unzipsDir = new File("unzips");
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i].deleteZips();
        }
        assertFalse("should be false", zipsDir.exists() && unzipsDir.exists());
    }

    /**
     * Tear down after tests.
     */
    @AfterClass
    public static void tearDown()
    {
        // Set arrays to null.
        sb2s = null;
        submissions = null;
        if (expectedDir.exists())
        {
            File[] expectedFiles = expectedDir.listFiles();
            for (int i = 0; i < expectedFiles.length; i++)
            {
                expectedFiles[i].delete();
            }
            expectedDir.delete();
        }
    }
}
