package org.vizun.engine.utils;

import org.lwjgl.opengl.GL11;

public class Text
{
  public static void renderString(Font font, String string, float x, float y, float scale, Color4f color) {
    renderString(font, string, 16, x, y, 0.1F, 0.1F, scale, color);
  }
  
  public static void renderString(Font font, String string, int gridSize, float x, float y, float charWidth, float charHeight, float scale, Color4f color) {
    GL11.glPushAttrib(286720);
    GL11.glEnable(2884);
    GL11.glEnable(3553);
    font.bind();
    
    GL11.glEnable(3042);
    GL11.glBlendFunc(1, 1);
    
    GL11.glColor4f(color.r, color.g, color.b, color.a);
    
    GL11.glPushMatrix();
    GL11.glScalef(scale, scale, 0.0F);
    GL11.glTranslatef(x, y, 0.0F);
    GL11.glBegin(7);
    
    for (int i = 0; i < string.length(); i++) {
      int code = string.charAt(i);
      float cellSize = 1.0F / gridSize;
      float cellX = code % gridSize * cellSize;
      float cellY = code / gridSize * cellSize;
      GL11.glTexCoord2f(cellX, cellY + cellSize);
      GL11.glVertex2f(i * charWidth / 3.0F, y);
      
      GL11.glTexCoord2f(cellX + cellSize, cellY + cellSize);
      GL11.glVertex2f(i * charWidth / 3.0F + charWidth / 2.0F, y);
      
      GL11.glTexCoord2f(cellX + cellSize, cellY);
      GL11.glVertex2f(i * charWidth / 3.0F + charWidth / 2.0F, y + charHeight);
      
      GL11.glTexCoord2f(cellX, cellY);
      GL11.glVertex2f(i * charWidth / 3.0F, y + charHeight);
    }
    GL11.glEnd();
    GL11.glPopMatrix();
    GL11.glPopAttrib();
  }
}
