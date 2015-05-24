package org.vizun.lib;

import org.slf4j.Logger;
import org.vizun.engine.Game;

import java.io.File;

public class DataFolder {
    
    private Game instance;
    
    private static File data_directory;
    private final Logger logger = instance.getLogger();
    
    enum OPERATING_SYSTEM {Windows, Mac, Linux, Unsupported}
    
    private OPERATING_SYSTEM operSys;
    
    public DataFolder(Game instance) {
        this.instance = instance;
        assignDirectory();
    }
    
    private void assignDirectory() {
        String OS = System.getenv("os.name").toLowerCase();
        
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
                logger.debug("Data Directory set to windows: {}", data_directory);
                break;
            case Linux:
                data_directory = new File(System.getProperty("user.home") + "/.vizun");
                logger.debug("Data Directory set to linux: {}", data_directory);
                break;
            case Mac:
                data_directory  = new File(System.getProperty("user.home") + "/Library/Application Support");
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
    }

    /**
     * Return the game data directory.*
     * @return Game Directory
     */
    public File getDataFolder() {
        return data_directory;
    }
}
