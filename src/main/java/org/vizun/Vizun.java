package org.vizun;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.vizun.engine.config.JSONConfiguration;

import org.vizun.lib.DataFolder;

import org.lwjgl.opengl.Display;

import org.vizun.engine.display.Displaymanager;

import org.vizun.engine.loader.Loader;

import org.vizun.engine.model.ModelTexture;
import org.vizun.engine.model.TexturedModel;

import org.vizun.engine.render.masterRenderer;

import org.vizun.engine.shader.EntityShader;

import org.vizun.engine.util.VoxelData;
import org.vizun.engine.util.language;

import org.vizun.engine.model.RawModel;


import java.io.File;

public class Vizun {
    
    private static Displaymanager displayManager;
    private static final org.vizun.engine.render.masterRenderer masterRenderer = new masterRenderer();
    private static final Loader loader = new Loader();
    private static Logger logger;
    private static final language lang = new language(new JSONConfiguration(new File("config/spanish.json")));
    
    private static File dataDirectory = new DataFolder().getDataFolder();
    /**
     * Will not be used until "JSONConfiguration" is complete.
     * private static final language lang = new language(new JSONConfiguration(new File("src/main/resources/configurations/english.json")));
     */

    public static void main(String[] args){
        
        final Vizun vizun = new Vizun();

        logger = LoggerFactory.getLogger("org.vizun");
        
        new Runnable() {
            @Override
            public void run() {
                vizun.setupDisplay();
            }
        }.run();
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Vizun.onDisable();
            }
        }));
        
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
        logger.trace("Disabling Vizun.");
    }

    public static Displaymanager getDisplayManager(){
        return displayManager;
    }
    
    public static language getLang() {
        return lang;
    }
    
    public static File getDataDirectory() {
        return dataDirectory;
    }
    
    private void setupDisplay() {
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
    }

    /**
    * Will not be used until "JSONConfiguration" is complete.
    * public static language getLang(){return lang;}
    */

}
