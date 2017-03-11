import java.io.File;

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
    private File[] submissions;
    
    /**
     * Submission constructor.
     *
     * @param directory  
     */
    public Submission(File directory)
    {
        submissions = directory.listFiles();
    }

    /**
     * Return File array of submission .sb2 files.
     *
     * @return submissions File array
     */
    public File[] getFiles()
    {
        return submissions;
    }
}
