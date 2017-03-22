import java.io.File;

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
    }

    /**
     * Check to see if folder is valid.
     *
     * @param dir directory file object
     * @return true or false
     */
    public static Boolean readValidDirectory(File dir)
    {
        if (dir.exists() || dir.isDirectory())
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
