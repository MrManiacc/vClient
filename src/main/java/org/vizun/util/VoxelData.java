package org.vizun.util;

/**
 * Created by James on 5/16/2015.
 */
public class VoxelData {
    /**
     * These are all used for rendering a quad on to the screen, will be changed to loading in from json file in the future
     */
    private static final float scale = 0.5f;
    public static final float[] vertics = new float[] {-scale, scale, 0f, -scale, -scale, 0f, scale, -scale, 0f, scale, scale, 0f,};
    public static final int[] indices = new int[] {0,1,3,3,1,2};
    public static final float[] textureCoords = new float[] {0,0,0,1,1,1,1,0};
}
