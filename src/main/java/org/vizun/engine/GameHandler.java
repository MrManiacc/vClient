package org.vizun.engine;

import org.slf4j.Logger;

import org.vizun.Vizun;
import org.vizun.engine.config.JSONConfiguration;
import org.vizun.engine.display.DisplayManager;
import org.vizun.engine.World.world;
import org.vizun.engine.utils.Camera;
import org.vizun.engine.utils.Camera3D;
import org.vizun.engine.utils.Keys;

public class GameHandler {
    
    private static Game instance;
    private static DisplayManager displayManager;
    private static JSONConfiguration displayConfiguration;
    private static Camera camera;
    private static world world;
    private static Logger logger;
    private static Keys keys;
    
    public GameHandler(Game main) {
        instance = main;
        logger = instance.getLogger();
        displayConfiguration = new JSONConfiguration("display.json");
    }
    public void onEnable() {
        displayConfiguration.getInteger("width");
        displayManager = new DisplayManager(displayConfiguration.getInteger("width"),displayConfiguration.getInteger("height"), displayConfiguration.getInteger("maxframes"), Vizun.getLanguage().getTitle(), displayConfiguration.getBoolean("vsync"));
        displayManager.createDisplay();
        camera = new Camera3D.CameraBuilder().setAspectRatio(GameHandler.displayConfig().getFloat("width") / GameHandler.displayConfig().getFloat("height")).setRotation(0,0,0).setPosition(0,0,0).setFieldOfView(GameHandler.displayConfig().getFloat("fov")).build();
        world = new world();
        keys = new Keys();
        world.init();
    }

    /**
     * Used in a separate thread to clean up everything outside of openGL context, will not close thread until all processes are complete
     */
    public void onDisable() {
        logger.debug("Disabling Vizun.");
        world.dispose();
        logger.debug("Successfully executed shutdown task.");
    }

    /**
     * Called once per a frame inside of the openGL context, which is why there is a "shouldShutDownContext" anything done that needs to be shutdown in openGL
     */
    public void onUpdate() {
        keys.update(camera);
        world.update();
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
