package org.vizun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.engine.Game;
import org.lwjgl.opengl.Display;
import org.vizun.lib.DataFolder;

public class Vizun implements Game {
    
    static final Vizun instance = new Vizun();
    private static final GameHandler gameHandler = new GameHandler(instance);
    
    private static DataFolder dataFolder;
    
    

    public static void main(String[] args){
        final Vizun vizun = new Vizun();
        
        dataFolder = new DataFolder(vizun);
        
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
}
