import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

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
            printW.println("SCATT Report " 
                + getReportDateTimeForHeader() + "\n");
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
                printW.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                printW.println();
            }
            printW.close();
            System.out.println("Report finished. " 
                + "Report located in current directory.");
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            System.out.println("Error. Report not made.");
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
        printW.println("Scripts Total: \t\t\t" 
            + submissions[i].getScriptCount());
        printW.println("Sprites Total: \t\t\t" 
            + submissions[i].getSpriteCount());
        printW.println("Variables Total: \t\t" 
            + submissions[i].getVariableCountForProgram());
        printW.println("Lists Total: \t\t\t" 
            + submissions[i].getListCountForProgram());
        printW.println("ScriptComments Total: \t" 
            + submissions[i].getScriptCommentCountForProgram());
        printW.println("Sounds Total: \t\t\t" 
            + submissions[i].getSoundCountForProgram());
        printW.println("Costumes Total: \t\t" 
            + submissions[i].getCostumeCountForProgram());
        printW.println("Control Blocks: \t\t" 
            + submissions[i].getControlBlocksForProgram());
        printW.println("Data Blocks: \t\t\t" 
            + submissions[i].getDataBlocksForProgram());
        printW.println("Event Blocks: \t\t\t" 
            + submissions[i].getEventsBlocksForProgram());
        printW.println("Looks Blocks: \t\t\t" 
            + submissions[i].getLooksBlocksForProgram());
        printW.println("More Blocks Blocks: \t" 
            + submissions[i].getMoreBlocksBlocksForProgram());
        printW.println("Motion Blocks: \t\t\t" 
            + submissions[i].getMotionBlocksForProgram());
        printW.println("Operator Blocks: \t\t" 
            + submissions[i].getOperatorsBlocksForProgram());
        printW.println("Pen Blocks: \t\t\t" 
            + submissions[i].getPenBlocksForProgram());
        printW.println("Sensing Blocks: \t\t" 
            + submissions[i].getSensingBlocksForProgram());
        printW.println("Sound Blocks: \t\t\t" 
            + submissions[i].getSoundBlocksForProgram());
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
        printW.println("---------------------------------");
        printW.println("Scripts: \t\t\t\t" 
            + submissions[i].getScriptCountForStage());
        printW.println("Variables: \t\t\t\t" 
            + submissions[i].getVariableCountForStage());
        printW.println("Lists: \t\t\t\t\t" 
                    + submissions[i].getListCountForStage());
        printW.println("ScriptComments: \t\t" 
            + submissions[i].getScriptCommentCountForStage());
        printW.println("Sounds: \t\t\t\t" 
            + submissions[i].getSoundCountForStage());
        printW.println("Costumes: \t\t\t\t" 
            + submissions[i].getCostumeCountForStage());
        printW.println("Control Blocks: \t\t" 
            + submissions[i].getControlBlocksForStage());
        printW.println("Data Blocks: \t\t\t" 
            + submissions[i].getDataBlocksForStage());
        printW.println("Event Blocks: \t\t\t" 
            + submissions[i].getEventsBlocksForStage());
        printW.println("Looks Blocks: \t\t\t" 
            + submissions[i].getLooksBlocksForStage());
        printW.println("More Blocks Blocks: \t" 
            + submissions[i].getMoreBlocksBlocksForStage());
        printW.println("Motion Blocks: \t\t\t" 
            + submissions[i].getMotionBlocksForStage());
        printW.println("Operator Blocks: \t\t" 
            + submissions[i].getOperatorsBlocksForStage());
        printW.println("Pen Blocks: \t\t\t" 
            + submissions[i].getPenBlocksForStage());
        printW.println("Sensing Blocks: \t\t" 
            + submissions[i].getSensingBlocksForStage());
        printW.println("Sound Blocks: \t\t\t" 
            + submissions[i].getSoundBlocksForStage());
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
        printW.println("---------------------------------");
        Sprite[] sprites = submissions[i].getSprites();
        for (int j = 0; j < sprites.length; j++)
        {
            printW.println("Sprite: " + sprites[j].getName());
            printW.println("Scripts: \t\t\t\t" 
                + sprites[j].getScriptCount());
            printW.println("Variables: \t\t\t\t" 
                + sprites[j].getVariableCount());
            printW.println("Lists: \t\t\t\t\t" 
                + sprites[j].getListCount());
            printW.println("ScriptComments: \t\t" 
                + sprites[j].getScriptCommentCount());
            printW.println("Sounds: \t\t\t\t" 
                + sprites[j].getSoundCount());
            printW.println("Costumes: \t\t\t\t" 
                + sprites[j].getCostumeCount());
            printW.println("Control Blocks: \t\t" 
                + sprites[j].getControlBlocksForSprite());
            printW.println("Data Blocks: \t\t\t" 
                + sprites[j].getDataBlocksForSprite());
            printW.println("Event Blocks: \t\t\t" 
                + sprites[j].getEventsBlocksForSprite());
            printW.println("Looks Blocks: \t\t\t" 
                + sprites[j].getLooksBlocksForSprite());
            printW.println("More Blocks Blocks: \t" 
                + sprites[j].getMoreBlocksBlocksForSprite());
            printW.println("Motion Blocks: \t\t\t" 
                + sprites[j].getMotionBlocksForSprite());
            printW.println("Operator Blocks: \t\t" 
                + sprites[j].getOperatorsBlocksForSprite());
            printW.println("Pen Blocks: \t\t\t" 
                + sprites[j].getPenBlocksForSprite());
            printW.println("Sensing Blocks: \t\t" 
                + sprites[j].getSensingBlocksForSprite());
            printW.println("Sound Blocks: \t\t\t" 
                + sprites[j].getSoundBlocksForSprite());
            printW.println();
        }
    }   

    /**
     * Method to get the current datetime for the report's header.
     *
     * @return formatted datetime
     */
    public String getReportDateTimeForHeader()
    {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm:ss a");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
