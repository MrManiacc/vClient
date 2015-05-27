package org.vizun.util;

import org.vizun.engine.config.Config;
import org.vizun.engine.config.JSONConfiguration;

import java.io.File;

public class Language {
  private JSONConfiguration config;
  public static enum LANGUAGE {ENGLISH, SPANISH, GERMAN};

  public Language(LANGUAGE language){
      if(language == LANGUAGE.ENGLISH){
        this.config = new JSONConfiguration("english.json");
      }else if(language == LANGUAGE.SPANISH){
          this.config = new JSONConfiguration("spanish.json");
      }else if(language == LANGUAGE.GERMAN){
          this.config = new JSONConfiguration("german.json");
      }
  }
  
  /**
   * public String to return the defined language String of "WELCOME"
   * @return WELCOME
   */
  public String getWelcomeMessage(){
    return config.getString("WELCOME");
  }
  
  /**
   * public String to return the defined language String of "FAREWELL"
   * @return FAREWELL
   */
  public String getGoodbyeMessage(){
    return config.getString("FAREWELL");
  }
  
  /**
   * public String to return the defined language String of "TEXTURE_LOADED"
   * @return TEXTURE_LOADED
   */
  public String getTextureLoadedMessage() {
    return config.getString("TEXTURE_LOADED");
  }
  
  /**
   * 
   * public String to return the defined language String of "TEXTURE_NOT_FOUND"
   * @return TEXTURE_NOT_FOUND
   */
  public String getTextureNotLoadedMessage() {
    return config.getString("TEXTURE_NOT_FOUND");
  }

    /**
     * public String to send the title of the game. Used later on to show version possibly
     * @return TITLE
     */
  public String getTitle(){return this.config.getString("TITLE");}

  /**
   *  prints out a new line with the prefix in front of the string
   *  Example: [Vizun] - Hello world.
   */
  public void sendPrefixedMessage(String msg){
    System.out.println(config.getString("PREFIX") + msg);
  }
  
  /**
   *  prints out error message, instead of print stack-trace (will use stack-trace for debugging purposes)
   *  Example: [Vizun] - Hello world.
   */
  public void sendPrefixedErrorMessage(String msg){
    System.err.println(config.getString("PREFIX") + msg);
  }
  
  /**
   *  prints out the texture name when texture is found and populates message with the prefeix
   *  Example: [Vizun] - [Texture Loaded]: test.png
   */
  public void sendTexturedLoaded(String textureName){
    sendPrefixedMessage(getTextureLoadedMessage() + textureName + ".png");
  }

  /**
   *  Errors out the texture name when texture is not found and populates error with the prefeix
   *  Example: [Vizun] - [Texture not found]: test.png
   */

  public void sendTextureNotLoaded(String textureName){
    sendPrefixedErrorMessage(getTextureNotLoadedMessage() + textureName + ".png");
  }
  
}
