package org.vizun.engine.shader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
/**
 * Created by James on 5/15/2015.
 */
public abstract class ProgramShader {
    private int ProgramID;
    private int VertexShaderID;
    private int FragmentShaderID;
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

  public ProgramShader(String VertexFile, String FragmentFile){
      VertexShaderID = loadShader(VertexFile, GL20.GL_VERTEX_SHADER);
      FragmentShaderID = loadShader(FragmentFile, GL20.GL_FRAGMENT_SHADER);
      ProgramID = GL20.glCreateProgram();
      GL20.glAttachShader(ProgramID, VertexShaderID);
      GL20.glAttachShader(ProgramID, FragmentShaderID);
      bindAttributes();
      GL20.glLinkProgram(ProgramID);
      GL20.glValidateProgram(ProgramID);
      getAllUniformLocation();
  }
    public void start() {
        GL20.glUseProgram(ProgramID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GL20.glDetachShader(ProgramID, VertexShaderID);
        GL20.glDetachShader(ProgramID, FragmentShaderID);
        GL20.glDeleteShader(VertexShaderID);
        GL20.glDeleteShader(FragmentShaderID);
        GL20.glDeleteProgram(ProgramID);
    }

    protected abstract void getAllUniformLocation();

    protected int getUniformLocation(String uniform) {
        return GL20.glGetUniformLocation(ProgramID, uniform);
    }

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(ProgramID, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    protected void load2dVector(int location, Vector2f value) {
        GL20.glUniform2f(location, value.x, value.y);
    }

    protected void loadInt(int location, int value) {
        GL20.glUniform1i(location, value);
    }

    protected void loadBoolean(int location, boolean value) {
        float toLoad = 0;
        if (value) {
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f martrix) {
        martrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

    protected abstract void bindAttributes();

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file " + file);
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not Compile shader " + file);
            System.exit(-1);
        }
        return shaderID;
    }

}
