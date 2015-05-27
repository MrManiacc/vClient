package org.vizun.engine;

import org.slf4j.Logger;

import org.vizun.Vizun;
import org.vizun.engine.config.JSONConfiguration;
import org.vizun.engine.display.DisplayManager;

public class GameHandler {
    
    private static Game instance;
    
    private static DisplayManager displayManager;

    private static JSONConfiguration displayConfiguration;
    private Logger logger;
    
    public GameHandler(Game main) {
        instance = main;
        logger = instance.getLogger();
        displayConfiguration = new JSONConfiguration("display.json");
    }
    public void onEnable() {
        displayConfiguration.getInteger("width");
        displayManager = new DisplayManager(displayConfiguration.getInteger("width"),displayConfiguration.getInteger("height"), displayConfiguration.getInteger("maxframes"), Vizun.getLanguage().getTitle(), displayConfiguration.getBoolean("vsync"));
        displayManager.createDisplay();
    }

    /**
     * Used in a separate thread to clean up everything outside of openGL context, will not close thread until all processes are complete
     */
    public void onDisable() {
        logger.debug("Disabling Vizun.");
        logger.debug("Successfully executed shutdown task.");
    }

    /**
     * Called once per a frame inside of the openGL context, which is why there is a "shouldShutDownContext" anything done that needs to be shutdown in openGL
     */
    public void onUpdate() {
        displayManager.updateDisplay();
    }

    /**
     * Used to disable anything that has to be disabled on the main thread
     */
    public void closeGL() {
        displayManager.closeDisplay();
    }


    public static Game getInstance() {
        return GameHandler.instance;
    }

    public static JSONConfiguration displayConfig(){return displayConfiguration;}
}
