package org.vizun.engine.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/* using "JSONsimple" for configuration, will write a json parsing library later */
public class Configuration {
	private File configFile;
	private JSONObject jsonObject;

	public Configuration(String fileLocation) {
		this.configFile = new File(fileLocation);
		try {
			setupConfiguration();
		} catch (IOException e) {
			System.err.println("invaild file location");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("invaild json format");
			e.printStackTrace();
		}
	}

	private void setupConfiguration() throws IOException, ParseException {
		FileReader fileReader = new FileReader(configFile);
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(fileReader);
		this.jsonObject = (JSONObject) obj;
	}
	public String getString(String key){
		return (String) jsonObject.get(key);
	}
	/* JSONsimple requires the object to first be cast to a long, then we can cast it back to a int */
	public int getInt(String key){
		return (int) ((long) jsonObject.get(key));
	}
	public float getFloat(String key){
		return (float) ((long)jsonObject.get(key));
	}
	
}
