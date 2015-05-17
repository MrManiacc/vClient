package org.vizun.engine.model;

/**
 * Created by James on 5/16/2015.
 */
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(RawModel rawModel, ModelTexture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }

    /**
     * Returns a instance of rawModel, see "RawModel"
     * @return rawModel
     */
    public RawModel getRawModel() {
        return rawModel;
    }

    /**
     * Returns a instance of ModelTexture, see "ModelTexture"
     * @return texture
     */
    public ModelTexture getTexutre() {
        return texture;
    }
}
