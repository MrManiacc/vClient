package org.vizun.engine.config;

import com.fasterxml.jackson.core.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.vizun.Vizun;
import org.vizun.engine.GameHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by River on 5/17/2015.
 */

public class JSONConfiguration implements Config {
    
    private final ClassLoader loader = getClass().getClassLoader();
    private final Logger logger = GameHandler.getInstance().getLogger();
    private File data_directory ;
    private File config;
    
    private JsonFactory factory = new JsonFactory();
    private JsonParser parser;
    private JsonGenerator writer;
    private JsonNode jsonNode;
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Load a json configuration file at the give file location, INCOMPLETE
     * @param file json
     */
    
    public JSONConfiguration(File file) {
        config = file;
        try {
            parser = factory.createParser(file);
            writer = factory.createGenerator(file, JsonEncoding.UTF8);
            data_directory = Vizun.getDataFolder().getDataFolder();
            jsonNode = mapper.readTree(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public JSONConfiguration(String file) {
        this.data_directory = Vizun.getDataFolder().getDataFolder();
        try {
            File configDir = new File(data_directory + "/config");
            if(configDir.isDirectory()) {
                File[] configs = configDir.listFiles();
                for(File conf:configs) {
                    if(conf.getName().equalsIgnoreCase(file)) {
                        config = conf;
                        logger.debug("Found " + conf.getName() + ", set to {}", config.getPath());
                    }
                }
            } else {
                logger.debug("Configuration directory on disk is not a directory.. Attempting to fix.");
                configDir.delete();
                configDir.mkdirs();
                if(configDir.isDirectory()) {
                    logger.debug("Configuration directory fixed.");
                    File[] configs = configDir.listFiles();
                    for(File conf:configs) {
                        if(conf.getName().equals(file)) {
                            config = conf;
                            logger.debug("Found the config file. Set to {}", config.getPath());
                        }
                    }
                } else {
                    logger.debug("Unable to fix the configuration directory.");
                    throw new FileNotFoundException("cannot access the data directory");
                }
            }
            if(config == null) {
                Vizun.getResourceLoader().loadConfigurations();
            }
            parser = factory.createParser(config);
            jsonNode = mapper.readTree(config);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void set(String key, String value) {
    }

    @Override
    public int getInteger(String key) {
        return jsonNode.path(key).getIntValue();
    }

    @Override
    public String getString(String key) {
        return jsonNode.path(key).getTextValue();
    }

    @Override
    public String[] getArray(String key) {
        JsonNode array = jsonNode.path(key);
        String[] stringArray = new String[]{};
        if(array.isArray()){
            for(int i = 0; i < array.size(); i++){
                stringArray[i] = array.get(i).asText();
            }
        }
        return stringArray;
    }

    public boolean getBoolean(String key){
        return jsonNode.path(key).getBooleanValue();
    }

    public double getDouble(String key) { return jsonNode.path(key).asDouble();}

    @Override
    public float getFloat(String key) {return (float)(jsonNode.path(key).asDouble());}
    
    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void load(String json) {

    }

    @Override
    public void load(File json) {

    }

    @Override
    public void load(InputStream json) {

    }

    @Override
    public void save() {

    }
}
