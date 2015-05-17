package org.vizun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.engine.config.Configuration;
import  org.vizun.engine.display.Displaymanager;

import org.lwjgl.opengl.Display;
import org.vizun.engine.util.language;

public class Vizun {
    
    public static Displaymanager displayManager;
    
    private static Logger logger;
    private static final language lang = new language(new Configuration("src/main/resources/configurations/spanish.json"));

    public static void main(String[] args){
        displayManager = new Displaymanager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        while(!Display.isCloseRequested()){
            displayManager.updateDisplay();
        }
        displayManager.closeDisplay();
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Vizun.onDisable();
            }
        }));
        
        logger = LoggerFactory.getLogger("org.vizun");
    }

    /**
     * Get the logger instance for Vizun*
     * @return logger instance
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Called on the closure of the application *
     * This method should simply save logs and close out stuff*
     */
    private static void onDisable() {
        
    }

    public static Displaymanager getDisplayManager(){
        return displayManager;
    }
    public static language getLang(){
        return lang;
    }

}
