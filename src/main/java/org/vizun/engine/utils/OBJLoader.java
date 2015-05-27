package org.vizun.engine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class OBJLoader
{
  public static Model loadModel(File f) throws FileNotFoundException, IOException
  {
    BufferedReader reader = new BufferedReader(new java.io.FileReader(f));
    Model m = new Model();
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.startsWith("v ")) {
        float x = Float.valueOf(line.split(" ")[1]).floatValue();
        float y = Float.valueOf(line.split(" ")[2]).floatValue();
        float z = Float.valueOf(line.split(" ")[3]).floatValue();
        m.vertices.add(new Vector3f(x, y, z));
      } else if (line.startsWith("vn ")) {
        float x = Float.valueOf(line.split(" ")[1]).floatValue();
        float y = Float.valueOf(line.split(" ")[2]).floatValue();
        float z = Float.valueOf(line.split(" ")[3]).floatValue();
        m.normals.add(new Vector3f(x, y, z));
      } else if (line.startsWith("f ")) {
        Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]).floatValue(), Float.valueOf(line.split(" ")[2].split("/")[0]).floatValue(), Float.valueOf(line.split(" ")[3].split("/")[0]).floatValue());
        Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]).floatValue(), Float.valueOf(line.split(" ")[2].split("/")[2]).floatValue(), Float.valueOf(line.split(" ")[3].split("/")[2]).floatValue());
        m.faces.add(new ModelFace(vertexIndices, normalIndices));
      }
    }
    reader.close();
    return m;
  }
}
