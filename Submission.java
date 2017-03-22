import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
    private File json;
    private File[] svgs;
    private File[] pngs;
    private File[] wavs;
    
    /**
     * Submission constructor.
     *
     * @param sb2  
     */
    public Submission(File sb2)
    {
        this.sb2 = sb2;
        zipsDir = new File("zips");
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
        if (!zipsDir.exists())
        {
            zipsDir.mkdir();
        }
        if (this.isValid())
        {
            String sb2Name = sb2.getName();
            int len = sb2Name.length();
            String zipName = this.getZipName();
            File zip = new File(zipsDir, zipName);
            if (!zip.exists())
            {
                try
                {
                    Files.copy(sb2.toPath(), zip.toPath());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Make .zip named directory.
     * Move .zip into directory.
     * Unzip files.
     * Delete original .zip.
     * Handles valid .sb2 test internally.
     */
    public void unZip()
    {
        // Create zip named directory.
        if (this.isValid())
        {
            String zipName = this.getZipName();
            int len = zipName.length();
            String zipDirName = zipName.substring(0, len - 4);
            File zipDir = new File(zipsDir, zipDirName);
            zipDir.mkdir();

            // Move .zip into directory.
            File zip = new File(zipsDir, zipName);
            File copy = new File(zipDir, zipName);
            try
            {
                Files.copy(zip.toPath(), copy.toPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            // Unzip file.
            unZip(copy, zipDir);
            
            // Delete original and copied .zips. 
            zip.delete();
            copy.delete();
        }
    }

    /**
     * Unzip utility.
     * Adapted from Pankaj
     * http://www.journaldev.com/960/java-unzip-file-example
     *
     * @param zip 
     * @param destDir 
     */
    private void unZip(File zip, File destDir)
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
     * Get .zip name.
     *
     * @return zipName
     */
    private String getZipName()
    {
        String sb2name = sb2.getName();
        int len = sb2name.length();
        String zipName = sb2name.substring(0, len - 4) + ".zip";
        return zipName;
    }
}
