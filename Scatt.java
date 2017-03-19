import java.lang.String;
import java.io.*;

/**
 * Scatt.java
 *  
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Feb 2017
 */

/**
 * Scratch Code Analysis Tool for Teachers (SCATT). 
 * 
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Feb 2017
 */
public class Scatt
{
    public static void main(String[] args)
    {
        System.out.println("Please enter the folder name: ");
        String folderName = System.console().readLine();
        Boolean isValid = readValidDirectory(folderName);
        System.out.println(isValid);
    }

    /**
     * Check to see if folder is valid.
     *
     * @return true or false
     */
    public static Boolean readValidDirectory(String dirName)
    {
        File folder = new File(dirName);
        if (folder.exists() || folder.isDirectory())
        {
           String[] files = folder.list();
           if (files.length > 0)
           {
               return true;
           }
        }
        return false;
    }
}
