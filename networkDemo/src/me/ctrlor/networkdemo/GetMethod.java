package me.ctrlor.networkdemo;

import me.ctrlor.common.*;

import java.io.InputStream;
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
import android.util.Log;
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
	private String addressUploadInfo= "http://192.168.0.11/test/uploadinfo.php";


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
		
		btnJsonList.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				JsonListDemo mJsonList = new JsonListDemo(addressJsonList);
				mJsonList.buildList();
			}
		});

		btnPlayer.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				PlayerDemo mPlayer = new PlayerDemo(addressPlayer);
			}
		});

		btnUploadInfo.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UploadInfoDemo mUploadInfo = new UploadInfoDemo(addressUploadInfo);
			}
		});

	}
	
	private void hideAllLayout()
	{
		layoutJsonList.setVisibility(View.GONE);
		layoutPlayer.setVisibility(View.GONE);
		layoutUploadInfo.setVisibility(View.GONE);
	}
	
	/** 
	 * Class of json list 
	 * 
	 * @author ctrlor
	 *
	 */
	public class JsonListDemo
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
						Log.d(TAG, "URL error");
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

	/** 
	 * Class of mp3 player
	 * 
	 * @author ctrlor
	 * 
	 */
	public class PlayerDemo
	{
        /* The url string of the mp3 */
		private String address;
		
        /* The tmp file path of the mp3 stored */
        private String path;

        /* Image button */
		private Button btnPlay;
		private Button btnPause;
		private Button btnReset;
		private Button btnStop;
		
		public PlayerDemo(String strAddr)
		{
			this.address = strAddr;

			hideAllLayout();
			layoutPlayer.setVisibility(View.VISIBLE); 

			btnPlay 	= (Button) findViewById(R.id.btn_player_play);
			btnPause 	= (Button) findViewById(R.id.btn_player_pause);
			btnReset	= (Button) findViewById(R.id.btn_player_reset);
			btnStop		= (Button) findViewById(R.id.btn_player_stop);
            
            setButtonListener();
        }
        
        private void setButtonListener()
        {
            /**
             * Button 'Play'
             */
			btnPlay.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    
                }
            }

            /**
             * Button 'Pause'
             */
			btnPause.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    
                }
            }

            /**
             * Button 'Reset'
             */
			btnReset.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    
                }
            }

            /**
             * Button 'Stop'
             */
			btnStop.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    
                }
            }

		} 
		
	} 

	/** 
	 * Class of json list 
	 * 
	 * @author ctrlor
	 *
	 */
	public class UploadInfoDemo
	{
		private String address;
		
		public UploadInfoDemo(String strAddr)
		{
			hideAllLayout();
			layoutUploadInfo.setVisibility(View.VISIBLE); 
			
			this.address = strAddr;
		} 
	} 
	
	
}
