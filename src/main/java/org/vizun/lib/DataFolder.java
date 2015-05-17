package org.vizun.lib;

import java.io.File;

/**
 * Created by River on 5/16/2015.
 */
public class DataFolder {
    
    private static File data_directory;
    
    enum OPERATING_SYSTEM {Windows, Mac, Linux, Unsupported}
    
    private OPERATING_SYSTEM operSys;
    
    public DataFolder() {
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
                break;
            case Linux:
                data_directory = new File(System.getProperty("user.home") + "/.vizun");
                break;
            case Mac:
                data_directory  = new File(System.getProperty("user.home") + "/Library/Application Support");
                break;
            case Unsupported:
            default:
                //Unsupported operating systems will receive no support.
                data_directory = new File(System.getProperty("user.home") + "/.Vizun");
                break;
        }
        
    }

    /**
     * Return the game data directory.*
     * @return Game Directory
     */
    public static File getDataFolder() {
        return data_directory;
    }
}
