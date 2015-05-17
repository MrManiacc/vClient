package org.vizun.engine.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by River on 5/17/2015.
 */

public class JSONConfiguration implements Config {
    
    private final ClassLoader loader = getClass().getClassLoader();
    
    private final File config;
    
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
