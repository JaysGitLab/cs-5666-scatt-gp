import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

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
     *
     * @throws FileNotFoundException ex
     */
    public void  parseJSONFile() throws FileNotFoundException
    {
        if (json != null)
        {
            try
            {
                jsonObj = FileUtils.parseJSONFile(json.getAbsolutePath());
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else 
        {
            System.out.println("Invalid JSON file.");
            return;
        }
    }

    /**
     * Get value of JSON attribute.
     *
     * @param name - name of the attribute
     * @return value - value of attribute
     */
    public String getJSONAttribute(String name)
    {
        return FileUtils.getJSONAttribute(jsonObj, name);
    }

    /** Get JSONArray attribute.
     *
     * @param name - name of the attribute
     * @return jsonArr - JSONArray
     */
    public JSONArray getJSONArrayAttribute(String name)
    {
        return FileUtils.getJSONArrayAttribute(jsonObj, name);
    }
}
