package org.vizun.engine.model;

/**
 * Created by James on 5/16/2015.
 */
public class RawModel {
    private int vaoID;
    private int VertexCount;
    /* stores data about certain model from vao data */
    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.VertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return VertexCount;
    }
}
