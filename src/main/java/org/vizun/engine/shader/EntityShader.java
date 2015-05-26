package org.vizun.engine.shader;

import com.sun.javafx.geom.Matrix3f;

import org.lwjgl.util.vector.Matrix4f;
import org.vizun.Vizun;

import java.io.File;

/**
 * Created by James on 5/16/2015.
 */
public class EntityShader extends ProgramShader{
    private static final String vertexShaderLocation = Vizun.getDataFolder().getShaderFolder().getAbsolutePath() + File.separator + "vertexShader.txt";;
    private static final String fragmentShaderLocation = Vizun.getDataFolder().getShaderFolder().getAbsolutePath() + File.separator + "fragmentShader.txt";
    private int transformationMatrix;
    private int projectionMatrix;
    private int viewMatrix;

    public EntityShader(){
        super(vertexShaderLocation, fragmentShaderLocation);
    }


    protected void bindAttributes(){
        super.bindAttribute(0, "position");
        super.bindAttribute(1,"textureCoords");
    }
     protected void getAllUniformLocation() {
         transformationMatrix = super.getUniformLocation("transformationMatrix");
         projectionMatrix = super.getUniformLocation("projectionMatrix");
         viewMatrix = super.getUniformLocation("viewMatrix");
     }
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(transformationMatrix, matrix);
    }
    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(projectionMatrix, projection);
    }
    public void loadViewMatrix(Matrix4f view){
        super.loadMatrix(viewMatrix, view);
    }

}
