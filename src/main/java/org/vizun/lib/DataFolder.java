package org.vizun.lib;

import org.slf4j.Logger;
import org.vizun.engine.Game;

import java.io.File;

public class DataFolder {
    
    private Game instance;
    
    private static File data_directory;
    private static File natives_directory;
    private Logger logger;
    
    public static enum OPERATING_SYSTEM {Windows, Mac, Linux, Unsupported}
    
    private OPERATING_SYSTEM operSys;
    
    public DataFolder(Game instance) {
        this.instance = instance;
        this.logger = instance.getLogger();
        assignDirectory();
    }
    
    private void assignDirectory() {
        String OS = System.getProperty("os.name").toLowerCase();
        
        if(OS.contains("win")) {
            operSys = OPERATING_SYSTEM.Windows;
        } else if(OS.contains("mac")) {
            operSys = OPERATING_SYSTEM.Mac;
        } else if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix") || OS.contains("linux")) {
            operSys = OPERATING_SYSTEM.Linux;
        } else {
            operSys = OPERATING_SYSTEM.Unsupported;
        }
        
        switch (operSys) {
            case Windows:
                data_directory = new File(System.getenv("APPDATA") + "/Vizun");
                natives_directory = new File(data_directory + File.separator + "windows-natives");
                logger.debug("Data Directory set to windows: {}", data_directory);
                break;
            case Linux:
                data_directory = new File(System.getProperty("user.home") + "/.Vizun");
                natives_directory = new File(data_directory + File.separator + "linux-natives");
                logger.debug("Data Directory set to linux: {}", data_directory);
                break;
            case Mac:
                data_directory  = new File(System.getProperty("user.home") + "/Library/Application Support/Vizun");
                natives_directory = new File(data_directory + File.separator + "mac-natives");
                logger.debug("Data Directory set to OSX: {}", data_directory);
                break;
            case Unsupported:
            default:
                //Unsupported operating systems will receive no support.
                data_directory = new File(System.getProperty("user.home") + "/.Vizun");
                logger.debug("Data Directory set to Unsupported OS: {}", data_directory);
                logger.warn("Unsupported operating system. Attempting to use Data Directory: {}", data_directory);
                break;
        }
        makeDataDir(data_directory);
        makeDataDir(natives_directory);
    }


    /**
     * Checks to see whether of not the data directory is created, creates it if it is not
     */
    private void makeDataDir(File file){
        if(!file.isDirectory()){
            logger.debug("Data Directory doesn't exsist, making it at {}", file);
            file.mkdir();
            logger.debug("Succesfully created {}", file);
        }
    }

    /**
     * Returns the set vaule of the operating system
     * @return operSys
     */
    public OPERATING_SYSTEM getOs(){
        return operSys;
    }

    /**
     * Return the game data directory.
     * @return Game Directory
     */
    public File getDataFolder() {
        return data_directory;
    }

    /**
     * Returns the natives game directory.
     * @return Native Directory
     */
    public File getNativesFolder(){
        return natives_directory;
    }
}
