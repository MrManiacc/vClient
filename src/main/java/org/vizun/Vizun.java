package org.vizun;

import  org.vizun.engine.display.Displaymanager;

import org.lwjgl.opengl.Display;

/**
 * Created by jamesraynor on 5/14/15.
 */
public class Vizun {
    
    public static Displaymanager displayManager;

    public static void main(String[] args){
        displayManager = new Displaymanager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        while(!Display.isCloseRequested()){
            displayManager.updateDisplay();
        }
        displayManager.closeDisplay();
    }
}
