import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

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
        countBlockCategoriesForSprite();
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
        return controlBlocksForSprite;
    }
    
    /**
     * Get data block count for sprite.
     *
     * @return count
     */
    public int getDataBlocksForSprite()
    {
        return dataBlocksForSprite;
    }
    
    /**
     * Get events block count for sprite.
     *
     * @return count
     */
    public int getEventsBlocksForSprite()
    {
        return eventsBlocksForSprite;
    }
    
    /**
     * Get looks block count for sprite.
     *
     * @return count
     */
    public int getLooksBlocksForSprite()
    {
        return looksBlocksForSprite;
    }
    
    /**
     * Get more blocks block count for sprite.
     *
     * @return count
     */
    public int getMoreBlocksBlocksForSprite()
    {
        return moreBlocksBlocksForSprite;
    }
    
    /**
     * Get motion block count for sprite.
     *
     * @return count
     */
    public int getMotionBlocksForSprite()
    {
        return motionBlocksForSprite;
    }
    
    /**
     * Get operators block count for sprite.
     *
     * @return count
     */
    public int getOperatorsBlocksForSprite()
    {
        return operatorsBlocksForSprite;
    }
    
    /**
     * Get pen block count for sprite.
     *
     * @return count
     */
    public int getPenBlocksForSprite()
    {
        return penBlocksForSprite;
    }
    
    /**
     * Get sensing block count for sprite.
     *
     * @return count
     */
    public int getSensingBlocksForSprite()
    {
        return sensingBlocksForSprite;
    }

    /**
     * Count block categories for sprite.
     */
    private void countBlockCategoriesForSprite()
    {
        controlBlocksForSprite = 0;
        dataBlocksForSprite = 0;
        eventsBlocksForSprite = 0;
        looksBlocksForSprite = 0;
        moreBlocksBlocksForSprite = 0;
        motionBlocksForSprite = 0;
        operatorsBlocksForSprite = 0;
        penBlocksForSprite = 0;
        sensingBlocksForSprite = 0;
        soundBlocksForSprite = 0;
        JSONArray scripts = FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        processScripts(scripts);
    }

    /**
     * Process scripts to count blocks by category.
     *
     * @param array 
     */
    private void processScripts(JSONArray array)
    {
        if (array == null || array.size() == 0)
        {
            return;
        }
        
        // If first element is a String, it is the block name.
        // Get and count its category.
        if (array.get(0) instanceof String)
        {
            String category = Submission.getCategory((String) array.get(0));
            if (category != null)
            {
                switch (category)
                {
                    case "control":
                        controlBlocksForSprite++;
                        break;
                    case "data":
                        dataBlocksForSprite++;
                        break;
                    case "events":
                        eventsBlocksForSprite++;
                        break;
                    case "looks":
                        looksBlocksForSprite++;
                        break;
                    case "more blocks":
                        moreBlocksBlocksForSprite++;
                        break;
                    case "motion":
                        motionBlocksForSprite++;
                        break;
                    case "operators":
                        operatorsBlocksForSprite++;
                        break;
                    case "pen":
                        penBlocksForSprite++;
                        break;
                    case "sensing":
                        sensingBlocksForSprite++;
                        break;
                    case "sound":
                        soundBlocksForSprite++;
                        break;
                    default:
                        break;
                }
            }
        }

        // Check for additional array elements, which represent embedded blocks.
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i) instanceof JSONArray)
            {
                processScripts((JSONArray) array.get(i));
            }
        }

        return;
    }
}
