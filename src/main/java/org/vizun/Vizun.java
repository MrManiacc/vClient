package org.vizun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.engine.Game;
import org.vizun.engine.events.GameHandler;
import org.lwjgl.opengl.Display;

public class Vizun implements Game {
    
    static final Vizun instance = new Vizun();
    private static final GameHandler gameHandler = new GameHandler(instance);

    public static void main(String[] args){
        final Vizun vizun = new Vizun();
        
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


}
