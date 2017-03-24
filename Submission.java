import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.lang.IllegalArgumentException;
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
    private File unzipsDir;
    private File json;
    
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
        if (!zipsDir.exists())
        {
            zipsDir.mkdir();
        }
        if (this.isValid())
        {
            String zipName = getBaseName(sb2) + ".zip";
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
            if (!unzipsDir.exists())
            {
                unzipsDir.mkdir();
            }
            String zipName = getBaseName(sb2) + ".zip";
            String zipDirName = getBaseName(sb2);
            File unzipDir = new File(unzipsDir, zipDirName);
            unzipDir.mkdir();

            // Move .zip into directory.
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

            // Unzip file.
            unZip(copy, unzipDir);
            
            // Delete copied .zip. 
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
     * Get filename without extension.
     *
     * @param file 
     * @throws IllegalArgumentException
     * @return base filename
     */
    private String getBaseName(File file) throws IllegalArgumentException
    {
        String filename = file.getName();
        int len = filename.length();
        if (filename.charAt(len - 4) == '.')
        {
            String basename = filename.substring(0, len - 4);
            return basename;
        }
        else
        {
            throw new IllegalArgumentException("Filename does not end in extension");
        }
    }
}
