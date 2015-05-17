package org.vizun.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.vizun.engine.model.RawModel;
import org.vizun.engine.model.TexturedModel;

/**
 * Created by James on 5/15/2015.
 */
public class masterRenderer {
    public void start(){}

    public void update(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(1,0,0, 1);
    }

    /**
     * Renders a TexturedModel instance, see "TexturedModel"
     */
    public void render(TexturedModel model){
        GL30.glBindVertexArray(model.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexutre().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void stop(){}

}
