package org.vizun.engine.utils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created by jamesraynor on 5/26/15.
 */
public class Keys {

    public void update(Camera camera){
        camera.updateKeys(32, 1);
        camera.updateMouse(1, 90, -90);
        if(Mouse.isButtonDown(0)){
            Mouse.setGrabbed(true);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            Mouse.setGrabbed(false);
        }
    }
}
