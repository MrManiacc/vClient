package org.vizun;

import org.vizun.engine.events.GameHandler;
import org.lwjgl.opengl.Display;


public class Vizun {

    private GameHandler.onEnable onEnable;
    private GameHandler.onUpdate onUpdate;
    private GameHandler.onDisable onDisable;
    private GameHandler.onDisableGL onDisableGL;

    public static void main(String[] args){
        final Vizun vizun = new Vizun();
        vizun.initializeEvents();
        /**
         * Creates threads for handling game events.
         */
        new Runnable() {
            @Override
            public void run() {
                vizun.onEnable.onenable(800, 600, 120, "Vizun");
                while (!Display.isCloseRequested()) {
                    vizun.onUpdate.onupdate();
                }
                vizun.onDisableGL.ondisable();
            }
        }.run();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                vizun.onDisable.ondisable();
            }
        }));
    }

    /**
     * Initializes all the needed events/classes
     */
    private void initializeEvents(){
        onEnable =  new GameHandler.onEnable();
        onUpdate = new GameHandler.onUpdate();
        onDisable = new GameHandler.onDisable();
        onDisableGL = new GameHandler.onDisableGL();
    }


}
