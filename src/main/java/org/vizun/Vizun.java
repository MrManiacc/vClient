package org.vizun;

import org.vizun.engine.config.Configuration;
import  org.vizun.engine.display.Displaymanager;

import org.lwjgl.opengl.Display;
import org.vizun.engine.util.language;

/**
 * Created by jamesraynor on 5/14/15.
 */
public class Vizun {
    
    private static Displaymanager displayManager;
    private static final language lang = new language(new Configuration("src/main/resources/configurations/spanish.json"));

    public static void main(String[] args){
        displayManager = new Displaymanager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        while(!Display.isCloseRequested()){
            displayManager.updateDisplay();
        }
        displayManager.closeDisplay();
    }

    public static Displaymanager getDisplayManager(){
        return displayManager;
    }
    public static language getLang(){
        return lang;
    }

}
