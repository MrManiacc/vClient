package org.vizun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.engine.Game;
import org.lwjgl.opengl.Display;
import org.vizun.engine.config.JSONConfiguration;
import org.vizun.lib.DataFolder;
import org.vizun.util.Language;
import org.vizun.util.ResourceLoader;

public class Vizun implements Game {

    static final Vizun instance = new Vizun();
    private static GameHandler gameHandler;
    private static ResourceLoader resourceLoader;
    private static DataFolder dataFolder;
    private static Language language;



    public static void main(String[] args){
        final Vizun vizun = new Vizun();
        dataFolder = new DataFolder(vizun);
        resourceLoader = new ResourceLoader(instance);
        resourceLoader.loadNatives();
        resourceLoader.loadConfigurations();
        resourceLoader.loadShaders();
        resourceLoader.loadTextures();
        // Has to be called after because it uses config files, which won't be created until after their loaded and or downloaded.
        gameHandler  = new GameHandler(instance);
        language = new Language(Language.LANGUAGE.ENGLISH);

        /**
         * Creates threads for handling game events.
         */
        new Runnable() {
            @Override
            public void run() {
                gameHandler.onEnable();
                while (!Display.isCloseRequested()) {
                    gameHandler.onUpdate();
                }
                gameHandler.closeGL();
            }
        }.run();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                gameHandler.onDisable();
            }
        }));
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger("org.vizun");
    }

    public static DataFolder getDataFolder() {
        return dataFolder;
    }
    public static ResourceLoader getResourceLoader(){
        return resourceLoader;
    }
    public static Language getLanguage(){return language;}
}
