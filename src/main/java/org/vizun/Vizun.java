package org.vizun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import  org.vizun.engine.display.Displaymanager;

import org.lwjgl.opengl.Display;

public class Vizun {
    
    public static Displaymanager displayManager;
    
    private static Logger logger;

    public static void main(String[] args){
        displayManager = new Displaymanager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        while(!Display.isCloseRequested()){
            displayManager.updateDisplay();
        }
        displayManager.closeDisplay();
        
        logger = LoggerFactory.getLogger("org.vizun");
    }

    /**
     * Get the logger instance for Vizun*
     * @return logger instance
     */
    public static Logger getLogger() {
        return logger;
    }
}
