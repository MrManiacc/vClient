package org.vizun;

<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import  org.vizun.engine.display.Displaymanager;
=======
import org.vizun.engine.config.Configuration;
import org.vizun.engine.display.Displaymanager;
>>>>>>> 3a0773e4ca524ceec81f69f2e394565728896393

import org.lwjgl.opengl.Display;
import org.vizun.engine.loader.Loader;
import org.vizun.engine.model.ModelTexture;
import org.vizun.engine.model.TexturedModel;
import org.vizun.engine.render.masterRenderer;
import org.vizun.engine.shader.EntityShader;
import org.vizun.engine.util.VoxelData;
import org.vizun.engine.util.language;
import org.vizun.engine.model.RawModel;

public class Vizun {
    
<<<<<<< HEAD
    public static Displaymanager displayManager;
    
    private static Logger logger;
=======
    private static Displaymanager displayManager;
    private static final org.vizun.engine.render.masterRenderer masterRenderer = new masterRenderer();
    private static final language lang = new language(new Configuration("src/main/resources/configurations/english.json"));
    private static final Loader loader = new Loader();
>>>>>>> 3a0773e4ca524ceec81f69f2e394565728896393

    public static void main(String[] args){
        displayManager = new Displaymanager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        EntityShader shader = new EntityShader();
        RawModel model = loader.loadToVao(VoxelData.vertics, VoxelData.indices, VoxelData.textureCoords);
        ModelTexture texture = new ModelTexture(loader.loadTexture("test"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        while(!Display.isCloseRequested()){
            masterRenderer.update();
            {
                shader.start();
                masterRenderer.render(texturedModel);
                shader.stop();
            }
            displayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        masterRenderer.stop();
        displayManager.closeDisplay();
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Vizun.onDisable();
            }
        }));
        
        logger = LoggerFactory.getLogger("org.vizun");
    }

    /**
     * Get the logger instance for Vizun*
     * @return logger instance
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Called on the closure of the application *
     * This method should simply save logs and close out stuff*
     */
    private static void onDisable() {
        
    }

    public static Displaymanager getDisplayManager(){
        return displayManager;
    }
    public static language getLang(){
        return lang;
    }

}
