package org.vizun.engine.loader;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.vizun.Vizun;
import org.vizun.engine.model.RawModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 5/16/2015.
 */
public class Loader {
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();


    /*loads voxel data to graphics card */
    public RawModel loadToVao(float[] vertices, int[] indices, float[] textureCoords){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, vertices, 3);
        storeDataInAttributeList(1, textureCoords,2);
        unbindVao();
        return new RawModel(vaoID, indices.length);
    }
    public int loadTexture(String textureName) {
        int texutreId = 0;
        try {
            texutreId = textureLoader.loadTexture(ImageIO.read(new File("src/main/resources/textures/" + textureName + ".png")));
            Vizun.getLang().sendTexturedLoaded(textureName);
            textures.add(texutreId);
        }catch(Exception e){Vizun.getLang().sendTextureNotLoaded(textureName);}
        return texutreId;
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
    private void storeDataInAttributeList(int attribNum, float[] data, int offset){
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer buffer = storeInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNum, offset, GL11.GL_FLOAT, false, 0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    public void cleanUp(){
        for(int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int texture : textures){
            GL11.glDeleteTextures(texture);
        }
    }

    private void unbindVao(){
        GL30.glBindVertexArray(0);
    }
    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER , buffer, GL15.GL_STATIC_DRAW);
    }
    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private FloatBuffer storeInFloatBuffer(float data[]){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}

