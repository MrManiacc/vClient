package org.vizun.engine.utils;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;


public class Model
{
  public List<Vector3f> vertices = new ArrayList();
  public List<Vector3f> normals = new ArrayList();
  public List<ModelFace> faces = new ArrayList();
}
