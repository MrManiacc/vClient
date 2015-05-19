package org.vizun.engine.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.engine.display.Displaymanager;
import org.vizun.engine.loader.Loader;
import org.vizun.engine.render.masterRenderer;
import org.vizun.engine.shader.EntityShader;
import org.vizun.lib.DataFolder;
import org.vizun.engine.util.language;
import org.vizun.engine.config.JSONConfiguration;


import java.io.File;

/**
 * Created by jamesraynor on 5/18/15.
 */
public class GameHandler {
    private static Logger logger;
    private static Displaymanager displayManager;
    private static masterRenderer masterRenderer;
    private static Loader loader;
    private static File dataDirectory;
    private static language language;
    private static EntityShader entityShader;
    // setting to false in the current state may cause errors
    private static final boolean isDebugMode = true;

    /**
     * Initializes all the needed classes, objects, ect
     */
    public static class onEnable{
        public void onenable(int width, int height, int maxfps, String title){
            GameHandler.logger = LoggerFactory.getLogger("org.vizun");
            GameHandler.displayManager = new Displaymanager(width, height,maxfps, title);
            GameHandler.displayManager.createDisplay();
            GameHandler.masterRenderer = new masterRenderer();
            GameHandler.loader = new Loader();
            GameHandler.entityShader = new EntityShader();
            if(!isDebugMode){
                GameHandler.dataDirectory = new DataFolder().getDataFolder();
                GameHandler.language = new language(new JSONConfiguration(new File("config/spanish.json")));
            }
        }
    }

    /**
     * Called once per a frame inside of the openGL context, which is why there is a "shouldShutDownContext" anything done that needs to be shutdown in openGL
     */
    public static class onUpdate{
        public void onupdate(){
            GameHandler.masterRenderer.update();
            entityShader.start();
            {

            }
            entityShader.stop();
            GameHandler.displayManager.updateDisplay();
        }
    }

    /**
     * Used in a separate thread to clean up everything outside of openGL context, will not close thread until all processes are complete
     */
    public static class onDisable{
        public void ondisable(){
            GameHandler.logger.trace("Disabling Vizun.");
            GameHandler.loader.cleanUp();
            GameHandler.masterRenderer.stop();
        }
    }

    /**
     * Used to disable anything that has to be disabled on the main thread
     */
    public static class onDisableGL{
        public void ondisable(){
            GameHandler.displayManager.closeDisplay();
        }
    }

    public static Displaymanager getDisplayManager() {return displayManager;}

    public static Logger getLogger() {return logger;}

    public static org.vizun.engine.render.masterRenderer getMasterRenderer() {return masterRenderer;}

    public static Loader getLoader() {return loader;}

    public static File getDataDirectory() {return dataDirectory;}

    public static org.vizun.engine.util.language getLanguage() {return language;}

    public static EntityShader getEntityShader() {return entityShader;}
}
