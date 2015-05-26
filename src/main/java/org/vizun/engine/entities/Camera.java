package org.vizun.engine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by dev on 5/25/2015.
 */
public class Camera {
    private Vector3f location = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;
    private final float MOVE_SPEED = 0.08f;

    /**
     * Will add configuration for keys in the future.
     */
    public void moveCamera(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            location.z-=MOVE_SPEED;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            location.z+=MOVE_SPEED;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            location.x+=MOVE_SPEED;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            location.x-=MOVE_SPEED;
        }
    }

    public Vector3f getLocation() {
        return location;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
