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
    public String getWelcomeMessage(){
        return config.getString("WELCOME");
    }
    public String getGoodbyeMessage(){return config.getString("FAREWELL");}

}
