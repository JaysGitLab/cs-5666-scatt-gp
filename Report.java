import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

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
        File reportFile = new File("./Report.txt");
        PrintWriter printW;
        try
        {
            printW = new PrintWriter(reportFile);
            printW.println("SCATT Report\n");
            for (int i = 0; i < submissions.length; i++)
            {
                if (submissions[i].isValid())
                {
                    printTotalCounts(printW, i);
                    printStageCounts(printW, i);
                    if (submissions[i].getSpriteCount() > 0)
                    {
                        printSpriteCounts(printW, i);
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

    /** 
     * Print out the total counts for a given submission.
     *
     * @param printW - the PrintWriter to use.
     * @param i - the index of the submission to be printed.
     */
    private void printTotalCounts(PrintWriter printW, int i)
    {
        printW.println("File: " + submissions[i].getName());
        printW.println("---------------------------------");
        printW.println("Script Count Total: " 
            + submissions[i].getScriptCount());
        printW.println("Sprite Count Total: " 
            + submissions[i].getSpriteCount());
        printW.println();
        printW.println("---------------------------------");
        printW.println();
    }

    /**
     * Print out the stage counts for a given submission.
     *
     * @param printW - the PrintWriter to use.
     * @param i - the index of the submission to be printed.
     */
    private void printStageCounts(PrintWriter printW, int i)
    {
        printW.println("Stage Counts");
        printW.println("Scripts: " 
            + submissions[i].getScriptCountForStage());
        printW.println("Variables: " 
            + submissions[i].getVariableCountForStage());
        printW.println("ScriptComments: " 
            + submissions[i].getScriptCommentCountForStage());
        printW.println("Sounds: " 
            + submissions[i].getSoundCountForStage());
        printW.println("Costumes: " 
            + submissions[i].getCostumeCountForStage());
        printW.println("Control Blocks: " 
            + submissions[i].getControlBlocksForStage());
        printW.println("Data Blocks: " 
            + submissions[i].getDataBlocksForStage());
        printW.println("Event Blocks: " 
            + submissions[i].getEventsBlocksForStage());
        printW.println("Looks Blocks: " 
            + submissions[i].getLooksBlocksForStage());
        printW.println("More Blocks Blocks: " 
            + submissions[i].getMoreBlocksBlocksForStage());
        printW.println("Motion Blocks: " 
            + submissions[i].getMotionBlocksForStage());
        printW.println("Operator Blocks: " 
            + submissions[i].getOperatorsBlocksForStage());
        printW.println("Pen Blocks: " 
            + submissions[i].getPenBlocksForStage());
        printW.println("Sensing Blocks: " 
            + submissions[i].getSensingBlocksForStage());
        printW.println("Sound Blocks: " 
            + submissions[i].getSoundBlocksForStage());
        printW.println();
        printW.println("--------------------------------");
        printW.println();
    }

    /**
     * Print out sprite counts for a given submission.
     *
     * @param printW - the PrintWriter to use.
     * @param i - the index of the submission to print counts for.
     */
    private void printSpriteCounts(PrintWriter printW, int i)
    {
        printW.println("Sprite Counts");
        Sprite[] sprites = submissions[i].getSprites();
        for (int j = 0; j < sprites.length; j++)
        {
            printW.println("Sprite: " + sprites[j].getName());
            printW.println("Scripts: " + sprites[j].getScriptCount());
            printW.println("Variables: " + sprites[j].getVariableCount());
            printW.println("ScriptComments: " 
                + sprites[j].getScriptCommentCount());
            printW.println("Sounds: " + sprites[j].getSoundCount());
            printW.println("Costumes: " + sprites[j].getCostumeCount());
            printW.println();
        }
    }   
}
