package com.vizun.engine.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by James on 5/15/2015.
 */
public class Displaymanager {

    public static void createdisplay(int width, int height, String title){
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create();
            while(!Display.isCloseRequested()){
                Display.update();
            }
            Display.destroy();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
}
