package org.vizun.engine.util;

import org.vizun.engine.config.Config;

/**
 * Created by James on 5/15/2015.
 */
public class language {
    private Config config;
    public language(Config config){
        this.config = config;
    }
    /* gets the currently set string(s) inside our language configuration */
    public String getWelcomeMessage(){
        return config.getString("WELCOME");
    }
    public String getGoodbyeMessage(){
        return config.getString("FAREWELL");
    }

}
