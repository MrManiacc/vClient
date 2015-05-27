package org.vizun.engine.utils;

public class Font
{
  private Texture texture;
  
  public Texture loadFont(String name, String location) {
    this.texture = Texture.loadTexture(location);
    ResourceManager.addTexture(name, this.texture);
    return this.texture;
  }
  
  public void bind() {
    this.texture.bind();
  }
}
