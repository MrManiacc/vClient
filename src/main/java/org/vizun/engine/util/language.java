package org.vizun.engine.util;

import org.vizun.engine.config.Configuration;

/**
 * Created by James on 5/15/2015.
 */
public class language {
    private Configuration config;
    public language(Configuration config){
        this.config = config;
    }
    /* gets the currently set string(s) inside our language configuration */
    public String getWelcomeMessage(){
        return config.getString("WELCOME");
    }
    public String getGoodbyeMessage(){
        return config.getString("FAREWELL");
    }
    public String getTextureLoadedMessage() {return config.getString("TEXTURE_LOADED");}
    public String getTextureNotLoadedMessage() {return config.getString("TEXTURE_NOT_FOUND");}
    /* prints out a new line with the prefix in from */
    public void sendPrefixedMessage(String msg){System.out.println(config.getString("PREFIX") + msg);}
    /* prints out error message, instead of print stack-trace (will use stack-trace for debuging purposes) */
    public void sendPrefixedErrorMessage(String msg){System.err.println(config.getString("PREFIX") + msg);}
    /* should be called when texture is loaded into vao */
    public void sendTexturedLoaded(String textureName){sendPrefixedMessage(getTextureLoadedMessage() + textureName + ".png");}
    /* should be called when texture is not found thus it not being loaded */
    public void sendTextureNotLoaded(String textureName){sendPrefixedErrorMessage(getTextureNotLoadedMessage() + textureName + ".png");}
}
