package me.ctrlor.networkdemo;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Button;


public class GetMethod extends Activity 
{
	
	private static final String TAG = "ctrlor-networkDemo-GetMethod";

	private LinearLayout layoutJsonList;
	private LinearLayout layoutPlayer;
	private TableLayout  layoutUploadInfo;
	
	private String addressJsonList  = "http://192.168.0.11/test/users.json";
	private String addressPlayer	= "http://192.168.0.11/test/media/example.mp3";
	private String addressUploadList= "http://192.168.0.11/test/uploadinfo.php";


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_method);
		setTitle("Get method");

		layoutJsonList 	 = (LinearLayout) findViewById(R.id.layout_json_list);
		layoutPlayer 	 = (LinearLayout) findViewById(R.id.layout_mp3_player);
		layoutUploadInfo = (TableLayout) findViewById(R.id.layout_upload_info);
		
		hideAllLayout();
		
		Button btnJsonList 	 = (Button) findViewById(R.id.btn_json_list_demo);
		Button btnPlayer   	 = (Button) findViewById(R.id.btn_player_demo);
		Button btnUploadInfo = (Button) findViewById(R.id.btn_upload_info_demo);
	}
	
	private void hideAllLayout()
	{
		layoutJsonList.setVisibility(View.GONE);
		layoutPlayer.setVisibility(View.GONE);
		layoutUploadInfo.setVisibility(View.GONE);
	}
	
	/** Class of json list 
	 * 
	 * @author ctrlor
	 *
	 */
	class JsonListDemo
	{
		private String address;
		private ListView listView;
		
		public JsonListDemo(String strAddr)
		{
			hideAllLayout();
			layoutJsonList.setVisibility(View.VISIBLE); 
			listView = (ListView) findViewById(R.id.lv_json_list);

			this.address = strAddr;
		}
		
		public void buildList()
		{
			final String addr = this.address;
			final ArrayList<HashMap<String, Object>> users = 
					new ArrayList<HashMap<String, Object>>();

			new Thread( new Runnable()
			{
				@Override
				public void run()
				{
					try 
					{
						URL url = new URL(addr);
						HttpURLConnection conn = (HttpURLConnection)url.openConnection();
						conn.setConnectTimeout(3000);
						conn.setRequestMethod("GET");
						conn.connect();
						
						if(conn.getResponseCode() == 200)
						{
							byte[] data = StreamTool.read(conn.getInputStream());
							String json = new String(data);
							
							JSONArray jsonArray = new JSONArray(json);
							for(int i=0; i<jsonArray.length(); i++)
							{
								JSONObject jsonObject = jsonArray.getJSONObject(i);
								HashMap<String, Object> user = new HashMap<String, Object>();

								user.put("id", jsonObject.getInt("id") );
								user.put("username", jsonObject.getString("username") );
								user.put("password", jsonObject.getString("password") );
								users.add(user);
								
							}
						}
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
				
			// Create a simple adapter
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), 
					users,
					R.layout.json_list,
					new String[]{"id", "username", "password"},
					new int[]{R.id.json_list_c1, R.id.json_list_c2, R.id.json_list_c3}
			);
			
			listView.setAdapter(adapter);
		} 
		
	} 

	/** Class of mp3 player
	 * 
	 * @author ctrlor
	 * 
	 */
	class PlayerDemo
	{
		private String address;
		
		private Button btnPlay;
		private Button btnPause;
		private Button btnReset;
		private Button btnStop;
		
		public PlayerDemo(String strAddr)
		{
			hideAllLayout();
			layoutPlayer.setVisibility(View.VISIBLE); 

			this.address = strAddr;
			
			btnPlay 	= (Button) findViewById(R.id.btn_player_play);
			btnPause 	= (Button) findViewById(R.id.btn_player_pause);
			btnReset	= (Button) findViewById(R.id.btn_player_reset);
			btnStop		= (Button) findViewById(R.id.btn_player_stop);

		} 
	} 

	/** Class of json list 
	 * 
	 * @author ctrlor
	 *
	 */
	class UploadInfoDemo
	{
		private void test()
		{
			layoutJsonList.setVisibility(View.GONE); 
		} 
	} 
}
