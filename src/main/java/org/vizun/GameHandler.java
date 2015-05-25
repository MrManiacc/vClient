package org.vizun;

import org.slf4j.Logger;

import org.vizun.engine.Game;
import org.vizun.engine.display.DisplayManager;
import org.vizun.engine.loader.Loader;
import org.vizun.engine.render.MasterRenderer;
import org.vizun.engine.shader.EntityShader;


public class GameHandler {
    
    private static Game instance;
    
    private static DisplayManager displayManager;
    private static MasterRenderer masterRenderer;
    private static Loader loader;
    private static EntityShader entityShader;

    private Logger logger;
    
    public GameHandler(Game main) {
        instance = main;
        logger = instance.getLogger();
    }

    public void onEnable() {
        //TODO replace values for the DisplayManager with config values
        displayManager = new DisplayManager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        masterRenderer = new MasterRenderer();
        loader = new Loader();
        entityShader = new EntityShader();
    }

    /**
     * Used in a separate thread to clean up everything outside of openGL context, will not close thread until all processes are complete
     */
    public void onDisable() {
        logger.debug("Disabling Vizun.");
        loader.cleanUp();
        masterRenderer.stop();
        logger.debug("Successfully executed shutdown task.");
    }

    /**
     * Called once per a frame inside of the openGL context, which is why there is a "shouldShutDownContext" anything done that needs to be shutdown in openGL
     */
    public void onUpdate() {
        masterRenderer.update();
        entityShader.start();
        entityShader.stop();
        displayManager.updateDisplay();
    }

    /**
     * Used to disable anything that has to be disabled on the main thread
     */
    public void closeGL() {
        displayManager.closeDisplay();
    }

    public static MasterRenderer getRenderer() {
        return masterRenderer;
    }

    public static Loader getLoader() {
        return loader;
    }

    public static EntityShader getEntityShader() {
        return entityShader;
    }
    
    public static Game getInstance() {
        return GameHandler.instance;
    }
}
