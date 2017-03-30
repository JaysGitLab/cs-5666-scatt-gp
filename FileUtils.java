import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * FileUtils.java
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 */

/**
 * Class for file utilities.
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 * @version Mar 2017
 */
public class FileUtils 
{
    /**
     * Copy file to zips directory to preserve original.
     * Convert file to .zip.
     *
     * @param zipsDir 
     * @param file 
     */
    public static void convertToZip(File zipsDir, File file)
    {
        if (!zipsDir.exists())
        {
            zipsDir.mkdir();
        }
        String zipName = getBaseName(file) + ".zip";
        File zip = new File(zipsDir, zipName);
        if (!zip.exists())
        {
            try
            {
                Files.copy(file.toPath(), zip.toPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Make zip named directory in unzips directory.
     * Copy zip into .zip named directory.
     * Unzip files.
     * Delete copied zip.
     *
     * @param zipsDir 
     * @param unzipsDir 
     * @param sb2 
     */
    public static void unZip(File zipsDir, File unzipsDir, File sb2)
    {
        if (!unzipsDir.exists())
        {
            unzipsDir.mkdir();
        }
        String zipDirName = getBaseName(sb2);
        String zipName = zipDirName + ".zip"; 
        File unzipDir = new File(unzipsDir, zipDirName);
        unzipDir.mkdir();

        File zip = new File(zipsDir, zipName);
        File copy = new File(unzipDir, zipName);
        try
        {
            Files.copy(zip.toPath(), copy.toPath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        unZipUtil(copy, unzipDir);
        
        copy.delete();
    }

    /**
     * Unzip utility.
     * Adapted from Pankaj
     * http://www.journaldev.com/960/java-unzip-file-example
     *
     * @param zip 
     * @param destDir 
     */
    public static void unZipUtil(File zip, File destDir)
    {
        FileInputStream fis;

        // Buffer for read and write data to file.
        byte[] buffer = new byte[1024];

        try
        {
            fis = new FileInputStream(zip);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null)
            {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get filename without extension.
     *
     * @param file 
     * @return base filename
     */
    public static String getBaseName(File file)
    {
        String fileName = file.getName();
        int len = fileName.length();
        String baseName = fileName.substring(0, len - 4);
        return baseName;
    }

    /** 
     * Read JSON file.
     *
     * @param filePath - path to JSON file
     * @returns JSONobj
     * @throws Exception if file not found
     */
    public static JSONObject parseJSONFile(String filePath)
    throws FileNotFoundException
    {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = new JSONObject();
        try
        {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObj = (JSONObject) obj;
           // name = (String) jsonObj.get("objName"); 
           //System.out.println(jsonObj);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return jsonObj;
    }

    /**
     * Get JSON object attribute's by name.
     *
     * @param obj - JSON Object
     * @param name - attribute's name
     * @return value
     */
    public static String getJSONAttribute(JSONObject obj, String name)
    {
        String value = "";
        return value = (String) obj.get(name);
    }

    /**
     * Get JSONArray attribute by name.
     *
     * @param obj - JSON Object
     * @param name - name of Array
     * @return jsonArr
     */
    public static JSONArray getJSONArrayAttribute(JSONObject obj, String name)
    {
        JSONArray jsonArr = new JSONArray();
        return jsonArr = (JSONArray) obj.get(name);
    }

}
