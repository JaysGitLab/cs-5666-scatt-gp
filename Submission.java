import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Iterator;
import java.util.HashMap;

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
    private File sb2;
    private File zipsDir;
    private File unzipsDir;
    private File json;
    private JSONObject jsonObj;
    private HashMap<String, String> blocks;
    private int controlBlocksForStage;
    private int dataBlocksForStage;
    private int eventsBlocksForStage;
    private int looksBlocksForStage;
    private int moreBlocksBlocksForStage;
    private int motionBlocksForStage;
    private int operatorsBlocksForStage;
    private int penBlocksForStage;
    private int sensingBlocksForStage;
    private int soundBlocksForStage;
    
    /**
     * Submission constructor.
     *
     * @param sb2  
     */
    public Submission(File sb2)
    {
        this.sb2 = sb2;
        zipsDir = new File("zips");
        unzipsDir = new File("unzips");
        blocks = new HashMap<String, String>();
        addControlCategoryMap();
        addDataCategoryMap();
        addEventsCategoryMap();
        addLooksCategoryMap();
        addMoreBlocksCategoryMap();
        addMotionCategoryMap();
        addOperatorsCategoryMap();
        addPenCategoryMap();
        addSensingCategoryMap();
        addSoundCategoryMap();
    }

    /**
     * Get filename of submission.
     *
     * @return filename
     */
    public String getName()
    {
        return sb2.getName();
    }

    /**
     * Check if valid .sb2.
     *
     * @return true if valid .sb2
     */
    public boolean isValid()
    {
        String sb2Name = sb2.getName();
        int len = sb2Name.length();
        String ext = sb2Name.substring(len - 4);
        return ext.equals(".sb2") && sb2.isFile();
    }

    /**
     * Convert .sb2 to .zip.
     * Handles valid .sb2 test internally.
     */
    public void convertToZip()
    {
        if (isValid())
        {
            FileUtils.convertToZip(zipsDir, sb2);
        }
    }

    /**
     * Unzip file. 
     * Handles valid .sb2 test internally.
     */
    public void unZip()
    {
        if (isValid())
        {
            FileUtils.unZip(zipsDir, unzipsDir, sb2);
            String zipDir = FileUtils.getBaseName(sb2);
            json = new File(unzipsDir + File.separator + zipDir 
                + File.separator, "project.json");
        }
    }

    /**
     * Get JSON object of submission.
     *
     * @return jsonObj
     */
    public JSONObject getJSONObject()
    {
        return this.jsonObj;
    }

    /**
     * Parse JSON file.
     */
    public void parseJSONFile()
    {
        if (json != null)
        {
            jsonObj = FileUtils.parseJSONFile(json.getAbsolutePath());
        }
    }

    /**
     * Get sprite count.
     *
     * @return spriteCount 
     */
    public int getSpriteCount()
    {
        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "spriteCount");
    }
    
    /**
     * Get script count.
     *
     * @return scriptCount 
     */
    public int getScriptCount()
    {
        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "scriptCount");
    }

    /**
     * Get script count for stage.
     *
     * @return count 
     */
    public int getScriptCountForStage()
    {
        return getCountForStage("scripts");
    }

    /**
     * Get variable count for stage.
     *
     * @return count 
     */
    public int getVariableCountForStage()
    {
        return getCountForStage("variables");
    }

    /**
     * Get list count for stage.
     *
     * @return count 
     */
    public int getListCountForStage()
    {
        return getCountForStage("lists");
    }
    
    /**
     * Get script comment count for stage.
     *
     * @return count 
     */
    public int getScriptCommentCountForStage()
    {
        return getCountForStage("scriptComments");
    }

    /**
     * Get sound count for stage.
     *
     * @return count 
     */
    public int getSoundCountForStage()
    {
        return getCountForStage("sounds");
    }

    /**
     * Get costume count for stage.
     *
     * @return count 
     */
    public int getCostumeCountForStage()
    {
        return getCountForStage("costumes");
    }
    
    /**
     * Helper method for all CountForStage methods.
     * Pass in JSON attribute name.
     * Get count of specified attribute for stage.
     * 
     * @param attribute  
     * @return count 
     */
    private int getCountForStage(String attribute)
    {
        JSONArray items = 
            FileUtils.getJSONArrayAttribute(jsonObj, attribute);
        if (items != null)
        {
            return (int) items.size();
        }
        return 0;
    }

    /**
     * Count block categories for stage.
     */
    public void countBlockCategoriesForStage()
    {
        controlBlocksForStage = 0;
        dataBlocksForStage = 0;
        eventsBlocksForStage = 0;
        looksBlocksForStage = 0;
        moreBlocksBlocksForStage = 0;
        motionBlocksForStage = 0;
        operatorsBlocksForStage = 0;
        penBlocksForStage = 0;
        sensingBlocksForStage = 0;
        soundBlocksForStage = 0;
        JSONArray scripts = FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        processScripts(scripts);
    }

    /**
     * Process scripts to count blocks by category.
     *
     * @param array 
     */
    private void processScripts(JSONArray array)
    {
        if (array == null || array.size() == 0)
        {
            return;
        }
        // If first element is a String, it is the block name.
        // Get and count its category.
        if (array.get(0) instanceof String)
        {
            String category = getCategory((String) array.get(0));
            if (category != null)
            {
                switch (category)
                {
                    case "control":
                        controlBlocksForStage++;
                        break;
                    case "data":
                        dataBlocksForStage++;
                        break;
                    case "events":
                        eventsBlocksForStage++;
                        break;
                    case "looks":
                        looksBlocksForStage++;
                        break;
                    case "more blocks":
                        moreBlocksBlocksForStage++;
                        break;
                    case "motion":
                        motionBlocksForStage++;
                        break;
                    case "operators":
                        operatorsBlocksForStage++;
                        break;
                    case "pen":
                        penBlocksForStage++;
                        break;
                    case "sensing":
                        sensingBlocksForStage++;
                        break;
                    case "sound":
                        soundBlocksForStage++;
                        break;
                    default:
                        break;
                }
            }
        }

        // Check for additional array elements.
        // Need to drill down to check for additional block names.
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i) instanceof JSONArray)
            {
                processScripts((JSONArray) array.get(i));
            }
        }

        return;
    }

    /**
     * Get control block count.
     *
     * @return count
     */
    public int getControlBlocksForStage()
    {
        return controlBlocksForStage;
    }

    /**
     * Get data block count.
     *
     * @return count
     */
    public int getDataBlocksForStage()
    {
        return dataBlocksForStage;
    }
    
    /**
     * Get events block count.
     *
     * @return count
     */
    public int getEventsBlocksForStage()
    {
        return eventsBlocksForStage;
    }
    
    /**
     * Get looks block count.
     *
     * @return count
     */
    public int getLooksBlocksForStage()
    {
        return looksBlocksForStage;
    }

    /**
     * Get array of sprites.
     * Unchecked warnings are suppressed because JSONArray does not
     *  allow for a type specification, and this is a private
     *  method only called from within this class.
     *
     * @return sprites 
     */
    @SuppressWarnings("unchecked")
    private JSONArray getSprites()
    {
        JSONArray children = 
            FileUtils.getJSONArrayAttribute(jsonObj, "children");
        JSONArray sprites = new JSONArray();

        Iterator<?> iterator = children.iterator();
        int n = 0;
        while (iterator.hasNext())
        {
            JSONObject next = (JSONObject) iterator.next();
            if (FileUtils.getJSONAttribute(next, "objName") != null)
            {
                sprites.add(next);
            }
        }
        return sprites;
    }

    /**
     * Get sprite names.
     *
     * @return array of sprite names.
     */
    public String[] getSpriteNames()
    {
        JSONArray sprites = getSprites(); 
        String[] names = new String[sprites.size()];
        for (int i = 0; i < names.length; i++)
        {
            names[i] = FileUtils.getJSONAttribute(
                (JSONObject) sprites.get(i), "objName");
        }
        return names;
    }

    /**
     * Get script count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getScriptCountForSprite(String spriteName)
    {
        return getCountForSprite("scripts", spriteName);
    }
    
    /**
     * Get variable count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getVariableCountForSprite(String spriteName)
    {
        return getCountForSprite("variables", spriteName);
    }
    
    /**
     * Get list count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getListCountForSprite(String spriteName)
    {
        return getCountForSprite("lists", spriteName);
    }
    
    /**
     * Get script comment count for sprite.
     *
     * @param spriteName 
     * @return count 
     */
    public int getScriptCommentCountForSprite(String spriteName)
    {
        return getCountForSprite("scriptComments", spriteName);
    }

    /**
     * Get sound count for sprite.
     *
     * @param spriteName 
     * @return count
     */
    public int getSoundCountForSprite(String spriteName)
    {
        return getCountForSprite("sounds", spriteName);
    }

    /**
     * Get costume count for sprite.
     *
     * @param spriteName 
     * @return count
     */
    public int getCostumeCountForSprite(String spriteName)
    {
        return getCountForSprite("costumes", spriteName);
    }
    
    /**
     * Helper method for all CountForSprite methods.
     * Pass in JSON attribute name and sprite name.
     * Get count of specified attribute for sprite.
     *
     * @param attribute  
     * @param spriteName 
     * @return count 
     */
    private int getCountForSprite(String attribute, String spriteName)
    {
        JSONArray sprites = getSprites();
        JSONArray items = new JSONArray();
        for (int i = 0; i < sprites.size(); i++)
        {
            if (FileUtils.getJSONAttribute((JSONObject) sprites.get(i), 
                    "objName").equals(spriteName))
            {
                items = FileUtils.getJSONArrayAttribute(
                    (JSONObject) sprites.get(i), attribute); 
            }
        }
        if (items != null)
        {
            return items.size();
        }
        return 0;
    }

    /**
     * Get category name for specified script name.
     *
     * @param scriptName 
     * @return category
     */
    private String getCategory(String scriptName)
    {
        return (String) blocks.get(scriptName);
    }
    
    /**
     * HashMap to store script names with block category.
     * Control scripts.
     */
    private void addControlCategoryMap()
    {
        blocks.put("wait:elapsed:from:", "control");
        blocks.put("wait:elapsed:from:", "control");
        blocks.put("doRepeat", "control");
        blocks.put("doForever", "control");
        blocks.put("doIf", "control");
        blocks.put("doIfElse", "control");
        blocks.put("doWaitUntil", "control");
        blocks.put("doUntil", "control");
        blocks.put("stopScripts", "control");
        blocks.put("whenCloned", "control");
        blocks.put("createCloneOf", "control");
        blocks.put("deleteClone", "control");
    }
    
    /**
     * HashMap to store script names with block category.
     * Data scripts.
     */
    private void addDataCategoryMap()
    {
        blocks.put("readVariable", "data");
        blocks.put("setVar:to:", "data");
        blocks.put("changeVar:by:", "data");
        blocks.put("showVariable:", "data");
        blocks.put("hideVariable:", "data");
        blocks.put("contentsOfList:", "data");
        blocks.put("append:toList:", "data");
        blocks.put("deleteLine:ofList:", "data");
        blocks.put("insert:at:ofList:", "data");
        blocks.put("setLine:ofList:to:", "data");
        blocks.put("getLine:ofList:", "data");
        blocks.put("lineCountOfList:", "data");
        blocks.put("list:contains:", "data");
        blocks.put("showList:", "data");
        blocks.put("hideList:", "data");
    }
    
    /**
     * HashMap to store script names with block category.
     * Events scripts.
     */
    private void addEventsCategoryMap()
    {
        blocks.put("whenGreenFlag", "events");
        blocks.put("whenKeyPressed", "events");
        blocks.put("whenClicked", "events");
        blocks.put("whenSceneStarts", "events");
        blocks.put("whenSensorGreaterThan", "events");
        blocks.put("whenIReceive", "events");
        blocks.put("broadcast:", "events");
        blocks.put("doBroadcastAndWait", "events");
    }
    
    /**
     * HashMap to store script names with block category.
     * Looks scripts.
     */
    private void addLooksCategoryMap()
    {
        blocks.put("say:duration:elapsed:from:", "looks");
        blocks.put("say:", "looks");
        blocks.put("think:duration:elapsed:from:", "looks");
        blocks.put("think:", "looks");
        blocks.put("show", "looks");
        blocks.put("hide", "looks");
        blocks.put("lookLike:", "looks");
        blocks.put("nextCostume", "looks");
        blocks.put("startScene", "looks");
        blocks.put("changeGraphicEffect:by:", "looks");
        blocks.put("setGraphicEffect:to:", "looks");
        blocks.put("filterReset", "looks");
        blocks.put("changeSizeBy:", "looks");
        blocks.put("setSizeTo:", "looks");
        blocks.put("comeToFront", "looks");
        blocks.put("goBackByLayers:", "looks");
        blocks.put("costumeIndex", "looks");
        blocks.put("sceneName", "looks");
        blocks.put("scale", "looks");
    }
    
    /**
     * HashMap to store script names with block category.
     * More blocks scripts.
     */
    private void addMoreBlocksCategoryMap()
    {
        blocks.put("procDef", "more blocks");
        blocks.put("LEGO WeDo\u001fmotorOnFor", "more blocks");
        blocks.put("LEGO WeDo\u001fmotorOn", "more blocks");
        blocks.put("LEGO WeDo\u001fmotorOff", "more blocks");
        blocks.put("LEGO WeDo\u001fstartMotorPower", "more blocks");
        blocks.put("LEGO WeDo\u001fsetMotorDirection", "more blocks");
        blocks.put("LEGO WeDo\u001fwhenDistance", "more blocks");
        blocks.put("LEGO WeDo\u001fwhenTilt", "more blocks");
        blocks.put("LEGO WeDo\u001fgetDistance", "more blocks");
        blocks.put("LEGO WeDo\u001fgetTilt", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fmotorOnFor", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fmotorOn", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fmotorOff", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fstartMotorPower", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fsetMotorDirection", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fsetLED", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fplayNote", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fwhenDistance", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fwhenTilted", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fgetDistance", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fisTilted", "more blocks");
        blocks.put("LEGO WeDo 2.0\u001fgetTilt", "more blocks");
        blocks.put("PicoBoard\u001fwhenSensorConnected", "more blocks");
        blocks.put("PicoBoard\u001fwhenSensorPass", "more blocks");
        blocks.put("PicoBoard\u001fsensorPressed", "more blocks");
        blocks.put("PicoBoard\u001fsensor", "more blocks");
    }
    
    /**
     * HashMap to store script names with block category.
     * Motion scripts.
     */
    private void addMotionCategoryMap()
    {
        blocks.put("forward:", "motion");
        blocks.put("turnRight:", "motion");
        blocks.put("turnLeft:", "motion");
        blocks.put("heading:", "motion");
        blocks.put("pointTowards:", "motion");
        blocks.put("gotoX:y:", "motion");
        blocks.put("gotoSpriteOrMouse:", "motion");
        blocks.put("glideSecs:toX:y:elapsed:from:", "motion");
        blocks.put("changeXposBy:", "motion");
        blocks.put("xpos:", "motion");
        blocks.put("changeYposBy:", "motion");
        blocks.put("ypos:", "motion");
        blocks.put("bounceOffEdge", "motion");
        blocks.put("setRotationStyle", "motion");
    }
    
    /**
     * HashMap to store script names with block category.
     * Operators scripts.
     */
    private void addOperatorsCategoryMap()
    {
        blocks.put("+", "operators");
        blocks.put("-", "operators");
        blocks.put("*", "operators");
        blocks.put("\\/", "operators");
        blocks.put("randomFrom:to:", "operators");
        blocks.put("<", "operators");
        blocks.put("=", "operators");
        blocks.put(">", "operators");
        blocks.put("&", "operators");
        blocks.put("|", "operators");
        blocks.put("not", "operators");
        blocks.put("concatenate:with:", "operators");
        blocks.put("letter:of:", "operators");
        blocks.put("stringLength:", "operators");
        blocks.put("%", "operators");
        blocks.put("rounded", "operators");
        blocks.put("computeFunction:of:", "operators");
    }
    
    /**
     * HashMap to store script names with block category.
     * Pen scripts.
     */
    private void addPenCategoryMap()
    {
        blocks.put("clearPenTrails", "pen");
        blocks.put("stampCostume", "pen");
        blocks.put("putPenDown", "pen");
        blocks.put("putPenUp", "pen");
        blocks.put("penColor:", "pen");
        blocks.put("changePenHueBy:", "pen");
        blocks.put("setPenHueTo:", "pen");
        blocks.put("changePenShadeBy:", "pen");
        blocks.put("setPenShadeTo:", "pen");
        blocks.put("changePenSizeBy:", "pen");
        blocks.put("penSize:", "pen");
    }
    
    /**
     * HashMap to store script names with block category.
     * Sensing scripts.
     */
    private void addSensingCategoryMap()
    {
        blocks.put("touching:", "sensing");
        blocks.put("touchingColor:", "sensing");
        blocks.put("color:sees:", "sensing");
        blocks.put("distanceTo:", "sensing");
        blocks.put("doAsk", "sensing");
        blocks.put("answer", "sensing");
        blocks.put("keyPressed:", "sensing");
        blocks.put("mousePressed", "sensing");
        blocks.put("mouseX", "sensing");
        blocks.put("mouseY", "sensing");
        blocks.put("soundLevel", "sensing");
        blocks.put("senseVideoMotion", "sensing");
        blocks.put("setVideoState", "sensing");
        blocks.put("setVideoTransparency", "sensing");
        blocks.put("timer", "sensing");
        blocks.put("timerReset", "sensing");
        blocks.put("getAttribute:of:", "sensing");
        blocks.put("timeAndDate", "sensing");
        blocks.put("timestamp", "sensing");
        blocks.put("getUserName", "sensing");
    }
    
    /**
     * HashMap to store script names with block category.
     * Sound scripts.
     */
    private void addSoundCategoryMap()
    {
        blocks.put("playSound:", "sound");
        blocks.put("doPlaySoundAndWait", "sound");
        blocks.put("stopAllSounds", "sound");
        blocks.put("playDrum", "sound");
        blocks.put("rest:elapsed:from:", "sound");
        blocks.put("noteOn:duration:elapsed:from:", "sound");
        blocks.put("instrument:", "sound");
        blocks.put("changeVolumeBy:", "sound");
        blocks.put("setVolumeTo:", "sound");
        blocks.put("volume", "sound");
        blocks.put("changeTempoBy:", "sound");
        blocks.put("setTempoTo:", "sound");
        blocks.put("tempo", "sound");
    }
    
    /**
     * Delete zip files.
     */
    public void deleteZips()
    {
        if (zipsDir.exists())
        {
            File[] zipsDirFiles = zipsDir.listFiles();
            for (int i = 0; i < zipsDirFiles.length; i++)
            {
                zipsDirFiles[i].delete();
            }
            zipsDir.delete();
        }
        
        if (unzipsDir.exists())
        {
            File[] unzipsDirs = unzipsDir.listFiles();
            for (int i = 0; i < unzipsDirs.length; i++)
            {
                File[] unzipFiles = unzipsDirs[i].listFiles();
                for (int j = 0; j < unzipFiles.length; j++)
                {
                    unzipFiles[j].delete();
                }
                unzipsDirs[i].delete();
            }
            unzipsDir.delete();
        }
    }
}
