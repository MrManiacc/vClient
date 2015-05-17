package org.vizun;

import org.vizun.engine.config.Configuration;
import org.vizun.engine.display.DisplayManager;

import org.lwjgl.opengl.Display;
import org.vizun.engine.loader.Loader;
import org.vizun.engine.render.MasterRenderer;
import org.vizun.engine.shader.EntityShader;
import org.vizun.engine.util.VoxelData;
import org.vizun.engine.util.language;
import org.vizun.engine.render.RawModel;

/**
 * Created by jamesraynor on 5/14/15.
 */
public class Vizun {
    
    private static DisplayManager displayManager;
    private static final MasterRenderer masterRenderer = new MasterRenderer();
    private static final language lang = new language(new Configuration("src/main/resources/configurations/spanish.json"));
    private static final Loader loader = new Loader();

    public static void main(String[] args){
        displayManager = new DisplayManager(800, 600, 120, "Vizun");
        displayManager.createDisplay();
        EntityShader shader = new EntityShader();
        RawModel model = loader.loadToVao(VoxelData.vertics, VoxelData.indices);
        while(!Display.isCloseRequested()){
            masterRenderer.update();
            {
                shader.start();
                masterRenderer.render(model);
                shader.stop();
            }
            displayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        masterRenderer.stop();
        displayManager.closeDisplay();
    }

    public static DisplayManager getDisplayManager(){
        return displayManager;
    }
    public static language getLang(){
        return lang;
    }

}
