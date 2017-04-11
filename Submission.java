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
    private Sprite[] sprites;
    
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
     * Create sprite objects.
     * Unchecked warnings are suppressed because JSONArray does not
     *  allow for a type specification, and this is a private
     *  method only called from within this class.
     */
    @SuppressWarnings("unchecked")
    public void createSprites()
    {
        if (getSpriteCount() > 0)
        {
            JSONArray children = 
                FileUtils.getJSONArrayAttribute(jsonObj, "children");
            JSONArray spritesJson = new JSONArray();

            Iterator<?> iterator = children.iterator();
            int n = 0;
            while (iterator.hasNext())
            {
                JSONObject next = (JSONObject) iterator.next();
                if (FileUtils.getJSONAttribute(next, "objName") != null)
                {
                    spritesJson.add(next);
                }
            }

            for (int i = 0; i < spritesJson.size(); i++)
            {
                sprites[i] = new Sprite(spritesJson.get(i));
            }
        }
    }

    /**
     * Get sprites.
     *
     * @return sprites
     */
    public Sprite[] getSprites()
    {
        return sprites;
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
