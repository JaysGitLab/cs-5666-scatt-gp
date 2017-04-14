import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

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
		File reportFile = new File("./Report.txt");
        PrintWriter printW;
        try
        {
            printW = new PrintWriter(reportFile);
            printW.println("SCATT Report");
            printW.println();
            for (int i = 0; i < submissions.length; i++)
            {
                if (submissions[i].isValid())
                {
                    printW.println("File: " + submissions[i].getName());
                    printW.println("---------------------------------");
                    printW.println("Script count Total: " 
                        + submissions[i].getScriptCount());
                    printW.println("Sprite count Total: " 
                        + submissions[i].getSpriteCount());
                    printW.println();
                    printW.println("---------------------------------");
                    printW.println();

                    printW.println("Stage Counts");
                    printW.println("Script: " 
                        + submissions[i].getScriptCountForStage());
                    printW.println("Variable: " 
                        + submissions[i].getVariableCountForStage());
                    printW.println("Comments: " 
                        + submissions[i].getScriptCommentCountForStage());
                    printW.println("Sounds: " 
                        + submissions[i].getSoundCountForStage());
                    printW.println("Costumes: " 
                        + submissions[i].getCostumeCountForStage());
                    printW.println("Control Block: " 
                        + submissions[i].getControlBlocksForStage());
                    printW.println("Data Block: " 
                        + submissions[i].getDataBlocksForStage());
                    printW.println("Event Block: " 
                        + submissions[i].getEventsBlocksForStage());
                    printW.println("Looks Block: " 
                        + submissions[i].getLooksBlocksForStage());
                    printW.println("More Block: " 
                        + submissions[i].getMoreBlocksBlocksForStage());
                    printW.println("Motion Block: " 
                        + submissions[i].getMotionBlocksForStage());
                    printW.println("Operator Block: " 
                        + submissions[i].getOperatorsBlocksForStage());
                    printW.println("Pen Block: " 
                        + submissions[i].getPenBlocksForStage());
                    printW.println("Sensing Block: " 
                        + submissions[i].getSensingBlocksForStage());
                    printW.println("Sound Block: " 
                        + submissions[i].getSoundBlocksForStage());
                    printW.println();
                    printW.println("--------------------------------");
                    printW.println();

                    printW.println("Sprite Counts");
                    String[] spriteNames = submissions[i].getSpriteNames();
                    for (int j = 0; j < spriteNames.length; j++)
                    {
                        printW.println("Sprite: " + spriteNames[j]);
                        printW.println("Script: " 
                            + submissions[i].getScriptCountForSprite(
                                spriteNames[j]));
                        printW.println("Variable: " 
                            + submissions[i].getVariableCountForSprite(
                                spriteNames[j]));
                        printW.println("Comment: " 
                            + submissions[i].getScriptCommentCountForSprite(
                                spriteNames[j]));
                        printW.println("Sound: " 
                            + submissions[i].getSoundCountForSprite(
                                spriteNames[j]));
                        printW.println("Costume: " 
                            + submissions[i].getCostumeCountForSprite(
                                spriteNames[j]));

                        printW.println();
                    }
                }
            }
            printW.close();
    
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
