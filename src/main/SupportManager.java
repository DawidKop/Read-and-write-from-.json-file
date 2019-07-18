package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;
import org.json.JSONTokener;

public class SupportManager {

	public Map <Integer, JSONObject> database;
	
	public SupportManager() {
		loadJson();
	}
	
	public void loadJson(){
		String resourceName = "/files/example.json";
		database = new TreeMap<>();
		int id=0;
        InputStream is = SupportManager.class.getResourceAsStream(resourceName);
	
        if (is == null) {
            throw new NullPointerException("The file was not found " + resourceName);
        }

        JSONTokener tokener = new JSONTokener(is);
        
        while(tokener.more()) {
        	JSONObject obj = new JSONObject(tokener);
        	database.put(id, obj);
        	id++;
        }
        try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveJson(String txt){
        FileWriter fw=null;
        BufferedWriter jsonFile=null;
		try {
			fw = new FileWriter("bin/files/example.json");
			//fw = new FileWriter("bin/files/example.json");
	        jsonFile = new BufferedWriter(fw);
	        jsonFile.write(txt);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				jsonFile.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	try {
				jsonFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	
}
