package com.itcs4180.homework3;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PicturesUtil {	
	static public class PicturesJSONParser{		
		static ArrayList<Picture> parsePictures(String jsonString) throws JSONException{
			ArrayList<Picture> pictures = new ArrayList<Picture>();	
			JSONArray picturesJSONArray = new JSONArray(jsonString);
			for(int i=0; i<picturesJSONArray.length(); i++){
				JSONObject pictureJSONObject = picturesJSONArray.getJSONObject(i);
				Picture picture = new Picture(pictureJSONObject);	
				pictures.add(picture);
			}
			return pictures;
		}
	}
}