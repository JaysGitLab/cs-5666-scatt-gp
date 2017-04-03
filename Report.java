/**
 * Report.java
 *  
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */

/**
 * Class for the Report. 
 * 
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Mar 2017
 */
public class Report
{
    private Submission[] submissions;

    /**
     * Constructor.
     *
     * @param submissions 
     */
    public Report(Submission[] submissions)
    {
        this.submissions = submissions;
    }

    /**
     * Method for making the report.
     */
    public void makeReport()
    {
        System.out.println("------------");
        System.out.println("SCATT Report");
        System.out.println("------------");
        System.out.println();
        for (int i = 0; i < submissions.length; i++)
        {
            if (submissions[i].isValid())
            {
                System.out.println("File: " + submissions[i].getName());
                System.out.println("---------------------------------");
                System.out.println("Script count: " 
                    + submissions[i].getScriptCount());
                System.out.println("Sprite count: " 
                    + submissions[i].getSpriteCount());
                System.out.println();
                String[] spriteNames = submissions[i].getSpriteNames();
                for (int j = 0; j < spriteNames.length; j++)
                {
                    System.out.println("Sprite: " + spriteNames[j]);
                    System.out.println("Script count: " 
                        + submissions[i].getScriptCountForSprite(
                            spriteNames[j]));
                    System.out.println();
                }
            }
        }
    }
}
