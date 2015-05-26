package org.vizun.engine.render;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import org.vizun.GameHandler;
import org.vizun.engine.entities.Camera;
import org.vizun.engine.entities.Entity;
import org.vizun.engine.model.TexturedModel;
import org.vizun.engine.shader.EntityShader;
import org.vizun.util.Maths;

public class MasterRenderer {
    private Matrix4f projectionMatrix;
    private static final float FOV = GameHandler.displayConfig().getFloat("fov");
    private static final float NEAR_PLANE = GameHandler.displayConfig().getFloat("nearplane");
    private static final float FAR_PLANE = GameHandler.displayConfig().getFloat("farplane");
    private static EntityShader entityShader;

    public void start(EntityShader shader){
        createProjectionMatrix();
        entityShader = shader;
        entityShader.start();
        entityShader.loadProjectionMatrix(projectionMatrix);
        entityShader.stop();
    }
    public void update(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,0,1,1);
    }

    /**
     * Renders a TexturedModel instance, see "TexturedModel"
     */
    public void render(Entity entity, Camera camera){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        TexturedModel model = entity.getModel();
        GL30.glBindVertexArray(model.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        entityShader.loadViewMatrix(Maths.makeView(camera));
        entityShader.loadTransformationMatrix(Maths.makeTransform(entity.getLocation(), entity.getRotation(), entity.getScale()));
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexutre().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustrum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustrum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustrum_length);
        projectionMatrix.m33 = 0;
    }
    public void stop(){}

}
