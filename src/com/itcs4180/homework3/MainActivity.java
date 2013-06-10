package com.itcs4180.homework3;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	String picturesJSONUrl = "http://api.flickr.com/services/rest/?method=flickr.photosets.getList&api_key=65ab778f3fdd58d7fc20acc7d505a631&user_id=40729938%40N03&format=json&nojsoncallback=1&api_sig=cd5739276ee1ac5a9d7b9e744a90f5d3";
	ArrayList<Picture> pictures;
	
	/*
	 * 	Flickr API Key: 159b9ae6542497bd648ad8a43db0e169
	 * Flickr API Secret: 243aa0359cde3412
	 * 
	 * GetList Arguments
	 * 
	 * api_key (Required) Your API application key. See here for more details.
	 * 
	 * user_id (Optional) The NSID of the user to get a photoset list for. If
	 * none is specified, the calling user is assumed.
	 * 
	 * page (Optional) The page of results to get. Currently, if this is not
	 * provided, all sets are returned, but this behaviour may change in future.
	 * 
	 * per_page (Optional) The number of sets to get per page. If paging is
	 * enabled, the maximum number of sets per page is 500.
	 * 
	 * GetList Example Response <photosets page="1" pages="1" perpage="30"
	 * total="2" * cancreate="1"> <photoset id="72157626216528324"
	 * primary="5504567858" secret="017804c585" server="5174" farm="6"
	 * photos="22" videos="0" count_views="137" count_comments="0"
	 * can_comment="1" date_create="1299514498" date_update="1300335009">
	 * <title>Avis Blanche</title> <description>My Grandma's Recipe
	 * File.</description> </photoset> <photoset id="72157624618609504"
	 * primary="4847770787" secret="6abd09a292" server="4153" farm="5"
	 * photos="43" videos="12" count_views="523" count_comments="1"
	 * can_comment="1" date_create="1280530593" date_update="1308091378">
	 * <title>Mah Kittehs</title> <description>Sixty and Niner. Born on the 3rd
	 * of May, 2010, or thereabouts. Came to my place on Thursday, July 29,
	 * 2010.</description> </photoset> </photosets>
	 * 
	 * Error Codes
	 * 
	 * 1: User not found The user NSID passed was not a valid user NSID and the
	 * calling user was not logged in. 100: Invalid API Key The API key passed
	 * was not valid or has expired. 105: Service currently unavailable The
	 * requested service is temporarily unavailable. 111: Format "xxx" not found
	 * The requested response format was not found. 112: Method "xxx" not found
	 * The requested method was not found. 114: Invalid SOAP envelope The SOAP
	 * envelope send in the request could not be parsed. 115: Invalid XML-RPC
	 * Method Call The XML-RPC request document could not be parsed. 116: Bad
	 * URL found One or more arguments contained a URL that has been used for
	 * abuse on Flickr.
	 * 
	 * GetPhotos Arguments:
	 * 
	 * api_key (Required) Your API application key. See here for more details.
	 * 
	 * photoset_id (Required) The id of the photoset to return the photos for.
	 * 
	 * extras (Optional) A comma-delimited list of extra information to fetch
	 * for each returned record. Currently supported fields are: license,
	 * date_upload, date_taken, owner_name, icon_server, original_format,
	 * last_update, geo, tags, machine_tags, o_dims, views, media, path_alias,
	 * url_sq, url_t, url_s, url_m, url_o
	 * 
	 * privacy_filter (Optional) Return photos only matching a certain privacy
	 * level. This only applies when making an authenticated call to view a
	 * photoset you own. Valid values are: 1 public photos 2 private photos
	 * visible to friends 3 private photos visible to family 4 private photos
	 * visible to friends & family 5 completely private
	 * 
	 * photos per_page (Optional) Number of photos to return per page. If this
	 * argument is omitted, it defaults to 500. The maximum allowed value is
	 * 500.
	 * 
	 * page (Optional) The page of results to return. If this argument is
	 * omitted, it defaults to 1.
	 * 
	 * media (Optional) Filter results by media type. Possible values are all
	 * (default), photos or videos
	 * 
	 * Example:
	 * 
	 * <photoset id="4" primary="2483" page="1" perpage="500" pages="1"
	 * total="2"> <photo id="2484" secret="123456" server="1" title="my photo"
	 * isprimary="0" /> <photo id="2483" secret="123456" server="1"
	 * title="flickr rocks" isprimary="1" /> </photoset>
	 * 
	 * Error Codes
	 * 
	 * 1: Photoset not found The photoset id passed was not a valid photoset id.
	 * 100: Invalid API Key The API key passed was not valid or has expired.
	 * 105: Service currently unavailable The requested service is temporarily
	 * unavailable. 111: Format "xxx" not found The requested response format
	 * was not found. 112: Method "xxx" not found The requested method was not
	 * found. 114: Invalid SOAP envelope The SOAP envelope send in the request
	 * could not be parsed. 115: Invalid XML-RPC Method Call The XML-RPC request
	 * document could not be parsed. 116: Bad URL found One or more arguments
	 * contained a URL that has been used for abuse on Flickr.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new AsyncPicturesGet(this).execute(picturesJSONUrl);
	}
	
	public void setupListView(ArrayList<Picture> result){
		ListView myListView = (ListView) findViewById(R.id.mylist);
		this.pictures = result;

		ArrayAdapter<Picture> adapter = new ArrayAdapter<Picture>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, pictures);
		myListView.setAdapter(adapter);
		adapter.setNotifyOnChange(true);

		myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Log.d("demo", pictures.get(position).toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static class PictureAdapter extends ArrayAdapter<Picture> {
		Context context;
		Picture[] objects;

		public PictureAdapter(Context context, Picture[] objects) {
			super(context, R.layout.activity_image_viewer, objects);
			this.context = context;
			this.objects = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View itemRowView = inflater.inflate(R.layout.activity_image_viewer, parent,
					false);
			TextView pictureName = (TextView) itemRowView
					.findViewById(R.id.mylist);
			pictureName.setText(objects[position].title);
			return itemRowView;
		}
	}

}
