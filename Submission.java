import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Iterator;

/**
 * Submission.java
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 */

/**
 * Class for a Scratch submission.
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 * @version Mar 2017
 */
public class Submission
{
    private File sb2;
    private File zipsDir;
    private File unzipsDir;
    private File json;
    private JSONObject jsonObj;
    
    /**
     * Submission constructor.
     *
     * @param sb2  
     */
    public Submission(File sb2)
    {
        this.sb2 = sb2;
        zipsDir = new File("zips");
        unzipsDir = new File("unzips");
    }

    /**
     * Get filename of submission.
     *
     * @return filename
     */
    public String getName()
    {
        return sb2.getName();
    }

    /**
     * Check if valid .sb2.
     *
     * @return true if valid .sb2
     */
    public boolean isValid()
    {
        String sb2Name = sb2.getName();
        int len = sb2Name.length();
        String ext = sb2Name.substring(len - 4);
        return ext.equals(".sb2") && sb2.isFile();
    }

    /**
     * Convert .sb2 to .zip.
     * Handles valid .sb2 test internally.
     */
    public void convertToZip()
    {
        if (isValid())
        {
            FileUtils.convertToZip(zipsDir, sb2);
        }
    }

    /**
     * Unzip file. 
     * Handles valid .sb2 test internally.
     */
    public void unZip()
    {
        if (isValid())
        {
            FileUtils.unZip(zipsDir, unzipsDir, sb2);
            String zipDir = FileUtils.getBaseName(sb2);
            json = new File(unzipsDir + File.separator + zipDir 
                + File.separator, "project.json");
        }
    }

    /**
     * Get JSON object of submission.
     *
     * @return jsonObj
     */
    public JSONObject getJSONObject()
    {
        return this.jsonObj;
    }

    /**
     * Parse JSON file.
     */
    public void parseJSONFile()
    {
        if (json != null)
        {
            jsonObj = FileUtils.parseJSONFile(json.getAbsolutePath());
        }
    }

    /**
     * Get sprite count.
     *
     * @return spriteCount 
     */
    public int getSpriteCount()
    {
        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "spriteCount");
    }
    
    /**
     * Get script count.
     *
     * @return scriptCount 
     */
    public int getScriptCount()
    {
        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "scriptCount");
    }

    /**
     * Get script count for stage.
     *
     * @return count 
     */
    public int getScriptCountForStage()
    {
        return getCountForStage("scripts");
    }

    /**
     * Get variable count for stage.
     *
     * @return count 
     */
    public int getVariableCountForStage()
    {
        return getCountForStage("variables");
    }

    /**
     * Get list count for stage.
     *
     * @return count 
     */
    public int getListCountForStage()
    {
        return getCountForStage("lists");
    }
    
    /**
     * Get script comment count for stage.
     *
     * @return count 
     */
    public int getScriptCommentCountForStage()
    {
        return getCountForStage("scriptComments");
    }

    /**
     * Get sound count for stage.
     *
     * @return count 
     */
    public int getSoundCountForStage()
    {
        return getCountForStage("sounds");
    }

    /**
     * Get costume count for stage.
     *
     * @return count 
     */
    public int getCostumeCountForStage()
    {
        return getCountForStage("costumes");
    }
    
    /**
     * Get count for stage.
     * 
     * @param item 
     * @return count 
     */
    private int getCountForStage(String item)
    {
        JSONArray items = 
            FileUtils.getJSONArrayAttribute(jsonObj, item);
        if (items != null)
        {
            return (int) items.size();
        }
        return 0;
    }

    /**
     * Get array of sprites.
     * Unchecked warnings are suppresed because JSONArray does not
     *  allow for a type specification, and this is a private
     *  method only called from within this class.
     *
     * @return sprites 
     */
    @SuppressWarnings("unchecked")
    private JSONArray getSprites()
    {
        JSONArray children = 
            FileUtils.getJSONArrayAttribute(jsonObj, "children");
        JSONArray sprites = new JSONArray();

        Iterator<?> iterator = children.iterator();
        int n = 0;
        while (iterator.hasNext())
        {
            JSONObject next = (JSONObject) iterator.next();
            if (FileUtils.getJSONAttribute(next, "objName") != null)
            {
                sprites.add(next);
            }
        }
        return sprites;
    }

    /**
     * Get sprite names.
     *
     * @return array of sprite names.
     */
    public String[] getSpriteNames()
    {
        JSONArray sprites = getSprites(); 
        String[] names = new String[sprites.size()];
        for (int i = 0; i < names.length; i++)
        {
            names[i] = FileUtils.getJSONAttribute(
                (JSONObject) sprites.get(i), "objName");
        }
        return names;
    }

    /**
     * Get script count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getScriptCountForSprite(String spriteName)
    {
        return getCountForSprite("scripts", spriteName);
    }
    
    /**
     * Get variable count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getVariableCountForSprite(String spriteName)
    {
        return getCountForSprite("variables", spriteName);
    }
    
    /**
     * Get list count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getListCountForSprite(String spriteName)
    {
        return getCountForSprite("lists", spriteName);
    }
    
    /**
     * Get script comment count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getScriptCommentCountForSprite(String spriteName)
    {
        return getCountForSprite("scriptComments", spriteName);
    }

    /**
     * Get sound count for sprite.
     *
     * @param spriteName 
     * @return count
     */
    public int getSoundCountForSprite(String spriteName)
    {
        return getCountForSprite("sounds", spriteName);
    }

    /**
     * Get costume count for sprite.
     *
     * @param spriteName 
     * @return count
     */
    public int getCostumeCountForSprite(String spriteName)
    {
        return getCountForSprite("costumes", spriteName);
    }
    
    /**
     * Helper method for all CountForSprite methods.
     * Pass in JSON attribute name and sprite name.
     * Get count of specified attribute for sprite.
     *
     * @param item 
     * @param spriteName 
     * @return count 
     */
    private int getCountForSprite(String attribute, String spriteName)
    {
        JSONArray sprites = getSprites();
        JSONArray items = new JSONArray();
        for (int i = 0; i < sprites.size(); i++)
        {
            if (FileUtils.getJSONAttribute((JSONObject) sprites.get(i), 
                    "objName").equals(spriteName))
            {
                items = FileUtils.getJSONArrayAttribute(
                    (JSONObject) sprites.get(i), attribute); 
            }
        }
        if (items != null)
        {
            return items.size();
        }
        return 0;
    }

    /**
     * Delete zip files.
     */
    public void deleteZips()
    {
        if (zipsDir.exists())
        {
            File[] zipsDirFiles = zipsDir.listFiles();
            for (int i = 0; i < zipsDirFiles.length; i++)
            {
                zipsDirFiles[i].delete();
            }
            zipsDir.delete();
        }
        
        if (unzipsDir.exists())
        {
            File[] unzipsDirs = unzipsDir.listFiles();
            for (int i = 0; i < unzipsDirs.length; i++)
            {
                File[] unzipFiles = unzipsDirs[i].listFiles();
                for (int j = 0; j < unzipFiles.length; j++)
                {
                    unzipFiles[j].delete();
                }
                unzipsDirs[i].delete();
            }
            unzipsDir.delete();
        }
    }
}
