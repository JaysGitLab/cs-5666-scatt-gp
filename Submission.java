import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

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
        String filename = sb2.getName();
        int len = filename.length();
        String ext = filename.substring(len - 4);
        return ext.equals(".sb2") && sb2.isFile();
    }

    /**
     * Convert .sb2 to .zip.
     *
     * @throws IOException 
     */
    public void convertToZip() throws IOException
    {
        File zipDir = new File("zips");
        if (!zipDir.exists())
        {
            zipDir.mkdir();
        }
        String sb2name = sb2.getName();
        int len = sb2name.length();
        //if (sb2name.substring(len - 4).equals(".sb2") && sb2.isFile())
        if (this.isValid())
        {
            String zipname = sb2name.substring(0, len - 4) + ".zip";
            File zip = new File(zipDir, zipname);
            if (!zip.exists())
            {
                Files.copy(sb2.toPath(), zip.toPath());
            }
        }
    }
}
