package org.vizun.engine.model;

/**
 * Created by James on 5/16/2015.
 */
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture texutre;

    public TexturedModel(RawModel rawModel, ModelTexture texutre) {
        this.rawModel = rawModel;
        this.texutre = texutre;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexutre() {
        return texutre;
    }
}
