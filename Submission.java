/**
 * Submission.java
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 */

/**
 * Class for individual Scratch submission.
 *
 * @author Michelle Melton
 * @author Kara Beason
 * @author Cydney Caldwell
 * @version Mar 2017
 */
public class Submission
{
    private String filename;

    /**
     * Submission constructor.
     *
     * @param filename 
     */
    public Submission(String filename)
    {
        this.filename = filename;
    }

    /**
     * Get filename of submission.
     *
     * @return filename
     */
    public String getFilename()
    {
        return filename;
    }
}
