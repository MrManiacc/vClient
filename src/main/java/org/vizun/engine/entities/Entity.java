package org.vizun.engine.entities;

import org.lwjgl.util.vector.Vector3f;
import org.vizun.engine.model.TexturedModel;

/**
 * Created by dev on 5/25/2015.
 */
public class Entity {
    private TexturedModel model;
    private Vector3f location;
    private Vector3f rotation;
    private Vector3f scale;

    public Entity(TexturedModel model, Vector3f location, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.location = location;
        this.rotation = rotation;
        this.scale = scale;
    }
    public void incrimentLocation(Vector3f offset){
        this.location.x += offset.x;
        this.location.y += offset.y;
        this.location.z += offset.z;
    }
    public void incrimentRotation(Vector3f offset){
        this.rotation.x += offset.x;
        this.rotation.y += offset.y;
        this.rotation.z += offset.z;
    }
    public void incrimentScale(Vector3f offset){
        this.scale.x += offset.x;
        this.scale.y += offset.y;
        this.scale.z += offset.z;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public TexturedModel getModel() {
        return model;
    }

    public Vector3f getLocation() {
        return location;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

}
