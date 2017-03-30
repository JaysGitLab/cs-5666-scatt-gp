import java.io.File;
import java.io.FileNotFoundException;

/**
 * Scatt.java
 *  
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */

/**
 * Scratch Code Analysis Tool for Teachers (SCATT). 
 * 
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */
public class Scatt
{
    /**
     * Main method for Scatt class.
     *
     * @param args 
     */
    public static void main(String[] args)
    {
        System.out.println("Please enter the folder name: ");
        String dirName = System.console().readLine();
        File directory = new File(dirName);
        Boolean isValid = readValidDirectory(directory);
        if (!isValid)
        {
            System.out.println("Invalid folder.");
            return;
        }
        
        File[] sb2s = directory.listFiles();
        Submission[] submissions = new Submission[sb2s.length];
        for (int i = 0; i < submissions.length; i++)
        {
            submissions[i] = new Submission(sb2s[i]);
            submissions[i].convertToZip();
            submissions[i].unZip();
        }
        try
        {
            submissions[0].parseJSONFile();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Check to see if folder is valid.
     *
     * @param dir directory file object
     * @return true or false
     */
    public static Boolean readValidDirectory(File dir)
    {
        if (dir.exists() && dir.isDirectory())
        {
            String[] files = dir.list();
            if (files.length > 0)
            {
                return true;
            }
        }
        return false;
    }
}
