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
     * @return scripts
     */
    public int getScriptCountForStage()
    {
        JSONArray scripts = FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        if (scripts != null)
        {
            return (int) scripts.size();
        }
        return 0;
    }

    /**
     * Get variable count for stage.
     *
     * @return variables
     */
    public int getVariableCountForStage()
    {
        JSONArray variables = 
            FileUtils.getJSONArrayAttribute(jsonObj, "variables");
        if (variables != null)
        {
            return (int) variables.size();
        }
        return 0;
    }

    /**
     * Get list count for stage.
     *
     * @return lists
     */
    public int getListCountForStage()
    {
        JSONArray lists = FileUtils.getJSONArrayAttribute(jsonObj, "lists");
        if (lists != null)
        {
            return (int) lists.size();
        }
        return 0;
    }
    
    /**
     * Get script comment count for stage.
     *
     * @return scriptComments 
     */
    public int getScriptCommentCountForStage()
    {
        JSONArray scriptComments = 
            FileUtils.getJSONArrayAttribute(jsonObj, "scriptComments");
        if (scriptComments != null)
        {
            return (int) scriptComments.size();
        }
        return 0;
    }

    /**
     * Get sound count for stage.
     *
     * @return sounds
     */
    public int getSoundCountForStage()
    {
        JSONArray sounds = FileUtils.getJSONArrayAttribute(jsonObj, "sounds");
        if (sounds != null)
        {
            return (int) sounds.size();
        }
        return 0;
    }

    /**
     * Get costume count for stage.
     *
     * @return costumes
     */
    public int getCostumeCountForStage()
    {
        JSONArray costumes = 
            FileUtils.getJSONArrayAttribute(jsonObj, "costumes");
        if (costumes != null)
        {
            return (int) costumes.size();
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
     * @return scripts 
     */
    public int getScriptCountForSprite(String spriteName)
    {
        JSONArray sprites = getSprites();
        JSONArray scripts = new JSONArray();
        for (int i = 0; i < sprites.size(); i++)
        {
            if (FileUtils.getJSONAttribute((JSONObject) sprites.get(i), 
                    "objName").equals(spriteName))
            {
                scripts = FileUtils.getJSONArrayAttribute(
                    (JSONObject) sprites.get(i), "scripts");
            }
        }
        if (scripts != null)
        {
            return scripts.size();
        }
        return 0;
    }
    
    /**
     * Get variable count for sprite.
     *
     * @param spriteName 
     * @return variables 
     */
    public int getVariableCountForSprite(String spriteName)
    {
        JSONArray sprites = getSprites();
        JSONArray variables = new JSONArray();
        for (int i = 0; i < sprites.size(); i++)
        {
            if (FileUtils.getJSONAttribute((JSONObject) sprites.get(i), 
                    "objName").equals(spriteName))
            {
                variables = FileUtils.getJSONArrayAttribute(
                    (JSONObject) sprites.get(i), "variables");
            }
        }
        if (variables != null)
        {
            return variables.size();
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
