package com.itcs4180.homework3;

import org.json.JSONException;
import org.json.JSONObject;

public class Picture {

	int id;
	String title, description;
	
	public Picture(JSONObject pictureJSONObject) throws JSONException{
		try{
			id = pictureJSONObject.getInt("id");
			title = pictureJSONObject.getString("title");
			description = pictureJSONObject.getString("runtime");
			} catch(JSONException e){
		}
	}
}