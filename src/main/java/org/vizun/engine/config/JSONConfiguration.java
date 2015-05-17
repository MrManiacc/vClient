package org.vizun.engine.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.vizun.Vizun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by River on 5/17/2015.
 */

public class JSONConfiguration implements Config {
    
    private final ClassLoader loader = getClass().getClassLoader();
    private final Logger logger = Vizun.getLogger();
    
    private final File data_directory = Vizun.getDataDirectory();
    private File config;
    
    private JsonFactory factory = new JsonFactory();
    private JsonParser parser;
    private JsonGenerator writer;
    
    /**
     * Load a json configuration file at the give file location 
     * @param file json
     */
    
    public JSONConfiguration(File file) {
        config = file;
        try {
            parser = factory.createParser(file);
            writer = factory.createGenerator(file, JsonEncoding.UTF8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public JSONConfiguration(String file) {
        try {
            File configDir = new File(data_directory + "/config");
            if(configDir.isDirectory()) {
                File[] configs = configDir.listFiles();
                for(File conf:configs) {
                    if(conf.getName().equals(file)) {
                        config = conf;
                        logger.debug("Found the config file. Set to {}", config.getPath());
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
                File loaded = new File(loader.getResource(file).getFile());
                File dest = new File(data_directory + "/config" + file);
                FileUtils.copyFile(loaded, dest);
                
                logger.trace("copied file from jar to disk config");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void set(String key, String value) {
        
    }

    @Override
    public int getInteger(String key) {
        return 0;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public String[] getArray(String key) {
        return new String[0];
    }

    @Override
    public float getFloat(String key) {
        return 0;
    }
    
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
