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
        Arrays.sort(sb2s);
        
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
        Arrays.sort(expectedZips);
        String[] expected = new String[expectedZips.length];
        for (int i = 0; i < expected.length; i++)
        {
            expected[i] = expectedZips[i].getName();
        }
        
        // Get actual Submission filenames.
        File actualDir = new File("zips");
        File[] actualZips = actualDir.listFiles();
        Arrays.sort(actualZips);
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
        Arrays.sort(expectedZips);
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
        Arrays.sort(zipDirs);
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
     */
    @Test
    public void testParseValidJSON()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();
    
        assertNotNull("Should not be null", 
            (Object) submissions[1].getJSONObject());
    }

    /**
     * Test parsing invalid JSON file.
     */
    public void testParseInvalidJSON()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();
     
        assertNull(submissions[1].getJSONObject());
    } 

    /**
     * Test getSpriteCount method, valid.
     */
    @Test
    public void testGetSpriteCountValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 2;
        int actual = submissions[1].getSpriteCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSpriteCount method, invalid.
     */
    @Test
    public void testGetSpriteCountInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 4;
        int actual = submissions[1].getSpriteCount();
        assertFalse("should be false", unexpected == actual);
    }
    
    /**
     * Test getScriptCount method, valid.
     */
    @Test
    public void testGetScriptCountValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 5;
        int actual = submissions[1].getScriptCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCount method, invalid.
     */
    @Test
    public void testGetScriptCountInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 4;
        int actual = submissions[1].getScriptCount();
        assertFalse("should be false", unexpected == actual);
    }

    /**
     * Test getScriptCountForStage, valid.
     */
    @Test
    public void testGetScriptCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getScriptCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCountForStage, valid - stage has no scripts.
     */
    @Test
    public void testGetScriptCountForStageValidEmpty()
    {
        submissions[0].convertToZip();
        submissions[0].unZip();
        submissions[0].parseJSONFile();

        int expected = 0;
        int actual = submissions[0].getScriptCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCountForStage, invalid.
     */
    @Test
    public void testGetScriptCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 2;
        int actual = submissions[1].getScriptCountForStage();
        assertFalse("should be false", unexpected == actual);
    }

    /**
     * Test getVariableCountForStage, valid.
     */
    @Test
    public void testGetVariableCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 2;
        int actual = submissions[1].getVariableCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getVariableCountForStage, valid - stage has no variables.
     */
    @Test
    public void testGetVariableCountForStageValidEmpty()
    {
        submissions[0].convertToZip();
        submissions[0].unZip();
        submissions[0].parseJSONFile();

        int expected = 0;
        int actual = submissions[0].getVariableCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getVariableCountForStage, invalid.
     */
    @Test
    public void testGetVariableCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 4;
        int actual = submissions[1].getVariableCountForStage();
        assertFalse("should be false", unexpected == actual);
    }

    /**
     * Test getListCountForStage, valid.
     */
    @Test
    public void testGetListCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getListCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getListCountForStage, valid - stage has no lists.
     */
    @Test
    public void testGetListCountForStageValidEmpty()
    {
        submissions[0].convertToZip();
        submissions[0].unZip();
        submissions[0].parseJSONFile();

        int expected = 0;
        int actual = submissions[0].getListCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getListCountForStage, invalid.
     */
    @Test
    public void testGetListCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 0;
        int actual = submissions[1].getListCountForStage();
        assertFalse("should be false", unexpected == actual);
    }
    
    /**
     * Test getScriptCommentCountForStage, valid.
     */
    @Test
    public void testGetScriptCommentCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getScriptCommentCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCommentCountForStage, valid - stage has no script comments.
     */
    @Test
    public void testGetScriptCommentCountForStageValidEmpty()
    {
        submissions[0].convertToZip();
        submissions[0].unZip();
        submissions[0].parseJSONFile();

        int expected = 0;
        int actual = submissions[0].getScriptCommentCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCommentCountForStage, invalid.
     */
    @Test
    public void testGetScriptCommentCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 0;
        int actual = submissions[1].getScriptCommentCountForStage();
        assertFalse("should be false", unexpected == actual);
    }
    
    /**
     * Test getSoundCountForStage, valid.
     */
    @Test
    public void testGetSoundCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getSoundCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSoundCountForStage, valid - stage has no sounds.
     */
    @Test
    public void testGetSoundCountForStageValidEmpty()
    {
        submissions[2].convertToZip();
        submissions[2].unZip();
        submissions[2].parseJSONFile();

        int expected = 0;
        int actual = submissions[2].getSoundCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSoundCountForStage, invalid.
     */
    @Test
    public void testGetSoundCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 0;
        int actual = submissions[1].getSoundCountForStage();
        assertFalse("should be false", unexpected == actual);
    }
    
    /**
     * Test getCostumeCountForStage, valid.
     */
    @Test
    public void testGetCostumeCountForStageValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getCostumeCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getCostumeCountForStage, valid - stage has no costumes.
     */
    @Test
    public void testGetCostumeCountForStageValidEmpty()
    {
        submissions[2].convertToZip();
        submissions[2].unZip();
        submissions[2].parseJSONFile();

        int expected = 0;
        int actual = submissions[2].getCostumeCountForStage();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getCostumeCountForStage, invalid.
     */
    @Test
    public void testGetCostumeCountForStageInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int unexpected = 0;
        int actual = submissions[1].getCostumeCountForStage();
        assertFalse("should be false", unexpected == actual);
    }

    /**
     * Test getSpriteNames method, valid.
     */
    @Test
    public void testGetSpriteNamesValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        String[] expected = {"Sprite1", "Butterfly3"};
        String[] actual = submissions[1].getSpriteNames();
        assertArrayEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSpriteNames method, invalid.
     */
    @Test
    public void testGetSpriteNamesInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        String[] expected = {"Bob"};
        String[] actual = submissions[1].getSpriteNames();
        assertFalse("should not be equal", Arrays.equals(expected, actual));
    }

    /**
     * Test getScriptCountForSprite method, valid.
     */
    @Test
    public void testGetScriptCountForSpriteValid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 2;
        int actual = submissions[1].getScriptCountForSprite("Sprite1");
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCountForSprite method, invalid.
     */
    @Test
    public void testGetScriptCountForSpriteInvalid()
    {
        submissions[1].convertToZip();
        submissions[1].unZip();
        submissions[1].parseJSONFile();

        int expected = 1;
        int actual = submissions[1].getScriptCountForSprite("Sprite1");
        assertFalse("should not be equal", expected == actual);
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
