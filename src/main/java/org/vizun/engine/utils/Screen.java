package org.vizun.engine.utils;

public abstract class Screen
  implements ScreenObject
{
  public abstract void init();
  
  public abstract void initGL();
  
  public abstract void update();
  
  public abstract void render();
  
  public abstract void dispose();
}
