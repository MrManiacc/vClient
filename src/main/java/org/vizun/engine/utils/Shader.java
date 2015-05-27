package org.vizun.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;







public class Shader
{
  private String vertexLoc;
  private String fragmentLoc;
  private int vShader;
  private int fShader;
  
  public Shader(String vertexLoc, String fragmentLoc)
  {
    this.vertexLoc = vertexLoc;
    this.fragmentLoc = fragmentLoc;
    
    loadShader();
  }
  
  private void loadShader() {
    this.vShader = GL20.glCreateShader(35633);
    this.fShader = GL20.glCreateShader(35632);
    
    StringBuilder vSource = new StringBuilder();
    StringBuilder fSource = new StringBuilder();
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(this.vertexLoc)));
      String line;
      while ((line = reader.readLine()) != null) {
        vSource.append(line).append('\n');
      }
      reader.close();
    } catch (IOException e) {
      System.err.println(vSource);
      System.err.println("Error loading vertex shader source at Shader.java location: " + this.vertexLoc);
    }
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(this.fragmentLoc)));
      String line;
      while ((line = reader.readLine()) != null) {
        fSource.append(line).append('\n');
      }
      reader.close();
    } catch (IOException e) {
      System.err.println(fSource);
      System.err.println("Error loading fragment shader source at Shader.java location: " + this.fragmentLoc);
    }
    
    compileShaders(vSource, fSource);
  }
  
  private void compileShaders(StringBuilder vSource, StringBuilder fSource) {
    GL20.glShaderSource(this.vShader, vSource);
    GL20.glCompileShader(this.vShader);
    if (GL20.glGetShaderi(this.vShader, 35713) == 0) {
      System.err.println(GL20.glGetShaderInfoLog(this.vShader, 1024));
      System.err.println("Error compiling vertex shader at Shader.java location: " + this.vertexLoc);
    }
    
    GL20.glShaderSource(this.fShader, fSource);
    GL20.glCompileShader(this.fShader);
    if (GL20.glGetShaderi(this.fShader, 35713) == 0) {
      System.err.println(GL20.glGetShaderInfoLog(this.fShader, 1024));
      System.err.println("Error compiling fragment shader at Shader.java location: " + this.fragmentLoc);
    }
  }
  
  public int getvShader() {
    return this.vShader;
  }
  
  public int getfShader() {
    return this.fShader;
  }
  
  public void dispose() {
    GL20.glDeleteShader(this.vShader);
    GL20.glDeleteShader(this.fShader);
  }
}
