package org.vizun.engine.model;

/**
 * Created by James on 5/16/2015.
 */
public class ModelTexture {
    private int TextureID;

    public ModelTexture(int id){this.TextureID = id;}
    /**
     * Returns the openGL location of texture, after it is loaded up.
     * @return TextureID
     */
    public int getID(){
        return TextureID;
    }

}
