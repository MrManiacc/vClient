package org.vizun.engine.utils;

public abstract interface ScreenObject
{
  public abstract void init();
  
  public abstract void initGL();
  
  public abstract void update();
  
  public abstract void render();
  
  public abstract void dispose();
}
