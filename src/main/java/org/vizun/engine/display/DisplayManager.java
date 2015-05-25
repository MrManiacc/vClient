package org.vizun.engine.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private int width, height, maxFps;
    private String title;
    private PixelFormat pixelFormat;
    private ContextAttribs contexAttribs;
    private boolean vsync;
    public DisplayManager(int width, int height, int maxFps, String title, boolean vsync) {
        this.width = width;
        this.height = height;
        // sets the maximum allowed frames to be rendered per a second
        this.maxFps = maxFps;
        this.title = title;
        this.pixelFormat = new PixelFormat();
        this.vsync = vsync;
        // sets the version of openGL e.g "3.2" for use of later shaders above 1.2 on Legacy based openGL computers.
        PixelFormat pixelFormat = new PixelFormat();
        this.contexAttribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
    }

    public void createDisplay() {
        try {
            //sets the size of the screen to the predefined width and height
            org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(width, height));
            //creates the openGL display, can throw a exception if the same instance of a game tries to create a window
            org.lwjgl.opengl.Display.create(pixelFormat, contexAttribs);
            //sets the title of the game to defined title
            org.lwjgl.opengl.Display.setTitle(title);
            org.lwjgl.opengl.Display.setVSyncEnabled(vsync);
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }
        //creates a openGL view port at 0,0 (top right)
        setViewPort(0, 0);
    }

    /**
     * updates the openGL graphics
     */
    public void updateDisplay() {
        org.lwjgl.opengl.Display.sync(maxFps);
        org.lwjgl.opengl.Display.update();
    }

    /**
     * closes current openGL instance
     */
    public void closeDisplay() {
        org.lwjgl.opengl.Display.destroy();
    }

    private void setViewPort(int startX, int startY) {
        //setting the openGL renderer bounds
        GL11.glViewport(startX, startY, width, height);
    }
}