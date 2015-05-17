package org.vizun;

import org.slf4j.Logger;
import  org.vizun.engine.display.Displaymanager;

import org.lwjgl.opengl.Display;

/**
 * Created by jamesraynor on 5/14/15.
 */
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
        
        
    }
    
    public static Logger getLogger() {
        return logger;
    }
}
