package org.vizun.util;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Matrix4f;
import org.vizun.engine.entities.Camera;

/**
 * Created by River on 5/17/2015.
 */
public class Maths {
    public static Matrix4f makeTransform(Vector3f translation, Vector3f rotation, Vector3f scale){
        Matrix4f mat = new Matrix4f();
        mat.setIdentity();
        Matrix4f.translate(translation, mat, mat);
        Matrix4f.rotate((float) Math.toRadians(rotation.getX()), new Vector3f(1, 0, 0), mat, mat);
        Matrix4f.rotate((float) Math.toRadians(rotation.getY()), new Vector3f(0, 1, 0), mat, mat);
        Matrix4f.rotate((float) Math.toRadians(rotation.getZ()), new Vector3f(0, 0, 1), mat, mat);
        Matrix4f.scale(scale, mat, mat);
        return mat;
    }

    public static Matrix4f makeView(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRoll()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getLocation();
        Vector3f negPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
}
