package org.vizun.engine.utils;

public abstract interface Camera
{
  public abstract void updateMouse();
  
  public abstract void updateMouse(float paramFloat);
  
  public abstract void updateMouse(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract void updateKeys(float paramFloat);
  
  public abstract void updateKeys(float paramFloat1, float paramFloat2);
  
  public abstract void updateKeys(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  public abstract void moveLookDir(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract void setPos(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract void setFOV(float paramFloat);
  
  public abstract void setAspectRation(float paramFloat);
  
  public abstract void applyOrtho();
  
  public abstract void applyProjection();
  
  public abstract void applyTranslations();
  
  public abstract void setRotation(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract float getX();
  
  public abstract float getY();
  
  public abstract float getZ();
  
  public abstract float getPitch();
  
  public abstract float getYaw();
  
  public abstract float getRoll();
  
  public abstract float getFOV();
  
  public abstract float getAspectRatio();
  
  public abstract float getNearClippingPlane();
  
  public abstract float getFarClippingPlane();
}
