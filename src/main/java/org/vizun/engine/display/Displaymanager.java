package org.vizun.engine.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import java.lang.reflect.Array;

/**
 * Created by James on 5/15/2015.
 */
public class Displaymanager {
    private int width, height, maxFps;
    private String title;
    private PixelFormat pixelFormat;
    private ContextAttribs contexAttribs;

    public Displaymanager(int width, int height, int maxFps, String title){
        this.width = width;
        this.height = height;
        // sets the maximum allowed frames to be rendered per a second
        this.maxFps = maxFps;
        this.title = title;
        this.pixelFormat = new PixelFormat();
        // sets the version of openGL e.g "3.2" for use of later shaders above 1.2 on Legacy based openGL computers.
        PixelFormat pixelFormat = new PixelFormat();
        this.contexAttribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
    }

    public void createDisplay(){
        try {
            //sets the size of the screen to the predefined width and height
            Display.setDisplayMode(new DisplayMode(width, height));
            //creates the openGL display, can throw a exception if the same instance of a game tries to create a window
             Display.create(pixelFormat, contexAttribs);
            //sets the title of the game to defined title
            Display.setTitle(title);
        }catch(LWJGLException ex){
            ex.printStackTrace();
        }
        //creates a openGL view port at 0,0 (top right)
        setViewPort(0,0);
    }
    /**
     * updates the openGL graphics
     */
    public void updateDisplay(){
        Display.sync(maxFps);
        Display.update();
    }
    /**
     *  closes current openGL instance
     */
    public void closeDisplay(){

        Display.destroy();
    }
    private void setViewPort(int startX, int startY){
        //setting the openGL renderer bounds
        GL11.glViewport(startX, startY, width, height);
    }
}
