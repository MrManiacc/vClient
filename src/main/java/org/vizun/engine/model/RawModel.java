package org.vizun.engine.model;

/**
 * Created by James on 5/16/2015.
 */
public class RawModel {
    private int vaoID;
    private int VertexCount;
    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.VertexCount = vertexCount;
    }
    /**
     * returns the vaoID, see: https://www.opengl.org/wiki/Vertex_Specification
     * @return vaoID
     */
    public int getVaoID() {
        return vaoID;
    }

    /**
     * returns the total amount of vertices inside the vao
     * @return VertexCount
     */
    public int getVertexCount() {
        return VertexCount;
    }
}
