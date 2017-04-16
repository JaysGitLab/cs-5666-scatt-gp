import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.Arrays;

/**
 * SpriteTest.java
 */

/**
 * Class to test Sprite class.
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Spr 2017
 */
public class SpriteTest
{
    private static Submission[] submissions;
    private static Sprite[] spritesAnimateTheCrab;
    private static Sprite[] spritesBigProject;
    private static Sprite[] spritesEmpty;
    
    /**
     * Set up for tests.
     */
    @BeforeClass
    public static void setUp()
    {
        // Set up expected files.
        File directory = new File("submissions");
        File[] sb2s = directory.listFiles();
        Arrays.sort(sb2s);
        
        // Create actual Submission files.
        submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
        }
        spritesAnimateTheCrab = submissions[0].getSprites();
        spritesBigProject = submissions[1].getSprites();
        spritesEmpty = submissions[2].getSprites();
    }
    
    /**
     * Test constructor of Sprite object.
     */
    @Test
    public void testSpriteConstructor()
    {
        assertNotNull("should not be null", spritesBigProject);
    }

    /**
     * Test constructor of Sprite object, empty.
     */
    @Test
    public void testSpriteConstructorEmpty()
    {
        assertNull("should be null", spritesEmpty);
    }

    /**
     * Test getSpriteName method.
     */
    @Test
    public void testGetSpriteName()
    {
        String expected = "Sprite1";
        String actual = spritesBigProject[0].getName();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCount method.
     */
    @Test
    public void testGetScriptCount()
    {
        int expected = 2;
        int actual = spritesBigProject[0].getScriptCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCount method - empty.
     */
    @Test
    public void testGetScriptCountEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getScriptCount();
        assertEquals("should be equal", expected, actual);
    }

    /**
     * Test getVariableCount method.
     */
    @Test
    public void testGetVariableCount()
    {
        int expected = 2;
        int actual = spritesBigProject[0].getVariableCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getVariableCount method - empty.
     */
    @Test
    public void testGetVariableCountEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getVariableCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getListCount method.
     */
    @Test
    public void testGetListCount()
    {
        int expected = 1;
        int actual = spritesBigProject[0].getListCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getListCount method - empty.
     */
    @Test
    public void testGetListCountEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getListCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCommentCount method.
     */
    @Test
    public void testGetScriptCommentCount()
    {
        int expected = 1;
        int actual = spritesBigProject[0].getScriptCommentCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getScriptCommentCount method - empty.
     */
    @Test
    public void testGetScriptCommentCountEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getScriptCommentCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSoundCount method.
     */
    @Test
    public void testGetSoundCount()
    {
        int expected = 1;
        int actual = spritesBigProject[0].getSoundCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getSoundCount method - empty.
     */
    @Test
    public void testGetSoundCountEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getSoundCount();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getCostumeCount method.
     */
    @Test
    public void testGetCostumeCount()
    {
        int expected = 2;
        int actual = spritesBigProject[0].getCostumeCount();
        assertEquals("should be equal", expected, actual);
    }

    /**
     * Test getControlBlocksForSprite method.
     */
    @Test
    public void testGetControlBlocksForSprite()
    {
        int expected = 4;
        int actual = spritesBigProject[0].getControlBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getControlBlocksForSprite method - empty.
     */
    @Test
    public void testGetControlBlocksForSpriteEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getControlBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getDataBlocksForSprite method.
     */
    @Test
    public void testGetDataBlocksForSprite()
    {
        int expected = 3;
        int actual = spritesBigProject[0].getDataBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getDataBlocksForSprite method - empty.
     */
    @Test
    public void testGetDataBlocksForSpriteEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getDataBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getEventsBlocksForSprite method.
     */
    @Test
    public void testGetEventsBlocksForSprite()
    {
        int expected = 2;
        int actual = spritesBigProject[0].getEventsBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Test getEventsBlocksForSprite method - empty.
     */
    @Test
    public void testGetEventsBlocksForSpriteEmpty()
    {
        int expected = 0;
        int actual = spritesAnimateTheCrab[1].getEventsBlocksForSprite();
        assertEquals("should be equal", expected, actual);
    }
    
    /**
     * Tear down after tests.
     */
    @AfterClass
    public static void tearDown()
    {
        // Set arrays to null.
        submissions = null;
        spritesBigProject = null;
        spritesEmpty = null;
    }
}
