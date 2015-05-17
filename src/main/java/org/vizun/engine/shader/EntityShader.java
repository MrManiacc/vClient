package org.vizun.engine.shader;

/**
 * Created by James on 5/16/2015.
 */
public class EntityShader extends ProgramShader{
    private static final String vertexShaderLocation = "src/main/resources/shaders/vertexShader.vs";;
    private static final String fragmentShaderLocation = "src/main/resources/shaders/fragmentShader.fs";

    public EntityShader(){
        super(vertexShaderLocation, fragmentShaderLocation);
    }

    protected void bindAttributes(){
     super.bindAttribute(0, "position");
    }
     protected void getAllUniformLocation() {

    }
}
