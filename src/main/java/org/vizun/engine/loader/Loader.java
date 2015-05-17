package org.vizun.engine.loader;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.vizun.engine.render.RawModel;

import java.nio.Buffer;
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

    /*loads voxel data to graphics card */
    public RawModel loadToVao(float[] vertices, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, vertices);
        unbindVao();
        return new RawModel(vaoID, indices.length);
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
    private void storeDataInAttributeList(int attribNum, float[] data){
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer buffer = storeInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNum, 3, GL11.GL_FLOAT, false, 0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    public void cleanUp(){
        for(int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
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
