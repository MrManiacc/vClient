package org.vizun.engine.config;

import java.io.File;
import java.io.InputStream;

@SuppressWarnings("unused")
public interface Config {
    
    public void set(String key, String value);
    
    public int getInteger(String key);
    public String getString(String key);
    public String[] getArray(String key);
    public float getFloat(String key);
    public Object get(String key);
    
    public void load(String json);
    public void load(File json);
    public void load(InputStream json);
    
    public void save();
    
}
