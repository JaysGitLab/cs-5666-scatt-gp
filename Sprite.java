import org.json.simple.JSONObject;

/**
 * Sprite.java
 */

/**
 * Class for a sprite within a Scratch submission.
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 * @version Spr 2017
 */
public class Sprite 
{
    private JSONObject jsonObj;
    private String name;
    private int scriptCount;
    private int variableCount;
    private int listCount;
    private int scriptCommentCount;
    private int soundCount;
    private int costumeCount;
    private int controlBlocksForSprite;
    private int dataBlocksForSprite;
    private int eventsBlocksForSprite;
    private int looksBlocksForSprite;
    private int moreBlocksBlocksForSprite;
    private int motionBlocksForSprite;
    private int operatorsBlocksForSprite;
    private int penBlocksForSprite;
    private int sensingBlocksForSprite;
    private int soundBlocksForSprite;
    
    /**
     * Sprite constructor.
     *
     * @param jsonObj 
     */
    public Sprite(JSONObject jsonObj)
    {
        this.jsonObj = jsonObj;
        name = FileUtils.getJSONAttribute(this.jsonObj, "objName");
        scriptCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "scripts").size();
        variableCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "variables").size();
        listCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "lists").size();
        scriptCommentCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "scriptComments").size();
        soundCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "sounds").size();
        costumeCount = FileUtils.getJSONArrayAttribute(this.jsonObj, 
            "costumes").size();
    }

    /**
     * Get sprite name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get script count for sprite.
     *
     * @return count 
     */
    public int getScriptCount()
    {
        return scriptCount;
    }
    
    /**
     * Get variable count for sprite.
     *
     * @return count 
     */
    public int getVariableCount()
    {
        return variableCount;
    }
    
    /**
     * Get list count for sprite.
     *
     * @return count 
     */
    public int getListCount()
    {
        return listCount;
    }
    
    /**
     * Get script comment count for sprite.
     *
     * @return count 
     */
    public int getScriptCommentCount()
    {
        return scriptCommentCount;
    }

    /**
     * Get sound count for sprite.
     *
     * @return count
     */
    public int getSoundCount()
    {
        return soundCount;
    }

    /**
     * Get costume count for sprite.
     *
     * @return count
     */
    public int getCostumeCount()
    {
        return costumeCount;
    }

    /**
     * Get control block count for sprite.
     *
     * @return count
     */
    public int getControlBlocksForSprite()
    {
        return 0;
    }
}
