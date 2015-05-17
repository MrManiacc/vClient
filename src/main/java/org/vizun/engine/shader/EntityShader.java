package org.vizun.engine.shader;

/**
 * Created by James on 5/16/2015.
 */
public class EntityShader extends ProgramShader{
    private static final String vertexShaderLocation = "src/main/resources/shaders/vertexShader.txt";;
    private static final String fragmentShaderLocation = "src/main/resources/shaders/fragmentShader.txt";

    public EntityShader(){
        super(vertexShaderLocation, fragmentShaderLocation);
    }

    protected void bindAttributes(){
        super.bindAttribute(0, "position");
        super.bindAttribute(1,"textureCoords");
    }
     protected void getAllUniformLocation() {

    }
}
