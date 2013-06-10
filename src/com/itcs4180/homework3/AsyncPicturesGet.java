package com.itcs4180.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncPicturesGet extends AsyncTask<String, Void, ArrayList<Picture>>{
	MainActivity activity;
	
	public AsyncPicturesGet(MainActivity activity){
		this.activity = activity;
	}
	
	@Override
	protected ArrayList<Picture> doInBackground(String... params) {
		String urlString = params[0];
		try {
			URL url = new URL(urlString);			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();			
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {				
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = reader.readLine();
				Log.d("demo", "line");
				while (line != null) {
					sb.append(line);
					line = reader.readLine();
				}
				ArrayList<Picture> movies = PicturesUtil.PicturesJSONParser.parsePictures(sb.toString());
				return movies;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Picture> result) {
		if(result != null){
			activity.setupListView(result);
			Log.d("demo", result.toString());
		} else{
			Log.d("demo", "null result");
		}

	}	
}