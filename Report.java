/**
 * Report.java
 */

/**
 * Class for the Report. 
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Spr 2017
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
                if (submissions[i].getSpriteCount() > 0)
                {
                    Sprite[] sprites = submissions[i].getSprites();
                    if (sprites != null)
                    {
                        for (int j = 0; j < sprites.length; j++)
                        {
                            System.out.println("Sprite: " 
                                + sprites[j].getName());
                            System.out.println("Script count: " 
                                + sprites[i].getScriptCount());
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
