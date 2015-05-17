package org.vizun.engine.util;

/**
 * Created by James on 5/16/2015.
 */
public class VoxelData {
    /* Used for rendering a basic quad. */
    private static float scale = 0.5f;
    public static float[] vertics = new float[] {-scale, scale, 0f, -scale, -scale, 0f, scale, -scale, 0f, scale, scale, 0f,};
    public static int[] indices = new int[] {0,1,3,3,1,2};
    public static float[] textureCoords = new float[] {0,0,0,1,1,1,1,0};
}
