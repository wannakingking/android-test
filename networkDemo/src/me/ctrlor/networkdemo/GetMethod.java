package me.ctrlor.networkdemo;

import me.ctrlor.common.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Button;


public class GetMethod extends Activity 
{
	
	private static final String TAG 		= "ctrlor-networkDemo-GetMethod";
	private static final String TAG_JSON    = "ctrlor-networkDemo-GetMethod-Json";
	private static final String TAG_PLAYER 	= "ctrlor-networkDemo-GetMethod-Player";
	private static final String TAG_UPLOADINFO = "ctrlor-networkDemo-GetMethod-UploadInfo";

	private LinearLayout layoutJsonList;
	private LinearLayout layoutPlayer;
	private TableLayout  layoutUploadInfo;

	private String addressJsonList  = "http://192.168.0.11/test/users.json";
	private String addressPlayer	= "http://192.168.0.11/test/media/example.mp3";
	private String addressUploadInfo= "http://192.168.0.11/test/uploadinfo.php";
	
	/******************** Player ********************/
	private String currentTempFilePath = "";
	private MediaPlayer mPlayer;
	private boolean bReleased = false;
	private boolean bPause 	  = false;


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
				hideAllLayout();
				layoutJsonList.setVisibility(View.VISIBLE);
				showJsonList();
			}
		});

		btnPlayer.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				hideAllLayout();
				layoutPlayer.setVisibility(View.VISIBLE);
				showPlayer();
			}
		});

		btnUploadInfo.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				hideAllLayout();
				layoutUploadInfo.setVisibility(View.VISIBLE);
				showUploadInfo();
			}
		});

	}
	
	private void hideAllLayout()
	{
		layoutJsonList.setVisibility(View.GONE);
		layoutPlayer.setVisibility(View.GONE);
		layoutUploadInfo.setVisibility(View.GONE);
	}
	
	/**************************** Json list layout **************************************/
	/**
	 * Json list demo
	 */
	private void showJsonList()
	{
		final ArrayList<HashMap<String, Object>> users = 
				new ArrayList<HashMap<String, Object>>();

		new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				try 
				{
					URL url = new URL(addressJsonList);
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
		
		( (ListView) findViewById(R.id.lv_json_list) ).setAdapter(adapter);
	} 
	
	/**************************** Player layout **************************************/
	/**
	 * Player layout 
	 * 
	 */
	private void showPlayer()
	{

		Button btnPlay 	= (Button) findViewById(R.id.btn_player_play);
		btnPlay.setOnClickListener(new ImageButton.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  playAudio(addressPlayer); 
            }
        });

		Button btnPause	= (Button) findViewById(R.id.btn_player_pause);
		btnPause.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	if(mPlayer != null)
            	{
            		if(bReleased == false)
            		{
            			if(bPause == false)
            			{
            				mPlayer.pause();
            				bPause = true;
            			}
            			else
            			{
            				mPlayer.start();
            				bPause = false;
            			}
            		}
            	}
            }
        });

		Button btnReset	= (Button) findViewById(R.id.btn_player_reset);
		btnReset.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 if(bReleased == false)
                 {
                	 if(mPlayer != null)
                	 {
                		 mPlayer.seekTo(0);
                	 }
                 }
                   
            }
        });

		Button btnStop	= (Button) findViewById(R.id.btn_player_stop);
		btnStop.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	try
            	{
	            	if(mPlayer != null)
	            	{
	            		if(bReleased == false)
	            		{
	            			mPlayer.stop();
	            			mPlayer.release();
	            			bReleased = true;
	            		}
	            	}
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
                   
            }
        });
	}
        
	private void playAudio(final String path)
	{
		try
		{
			if(mPlayer != null)
			{
				mPlayer.start();
				return;
			}
			
			mPlayer = new MediaPlayer();
			mPlayer.setAudioStreamType(2);
			
			// Error listener
			mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() 
			{
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) 
				{
					// TODO Auto-generated method stub
					Log.d(TAG_PLAYER, "Error in lostener, what:" + what + "extra:" + extra);
					return false;
				}
			});
			
			// Buffer update listener
			mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() 
			{
				
				@Override
				public void onBufferingUpdate(MediaPlayer mp, int percent) 
				{
					// TODO Auto-generated method stub
					Log.d(TAG_PLAYER, "Update buffer:" + Integer.toString(percent)+ "%");
				}
			});
			
			// Completion listener
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
			{
				@Override
				public void onCompletion(MediaPlayer mp) 
				{
					// TODO Auto-generated method stub
					Log.d(TAG_PLAYER, "mPlayer listener completion.");
				}
			});
			
			// Prepared listener
			mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() 
			{
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					Log.d(TAG_PLAYER, "Prepared listener");
				}
			});
			
			
			// Runnable
			Runnable r = new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						// Set media file data source: url or local file.
						setDataSource(addressPlayer);
						
						mPlayer.prepare();
						Log.d(TAG_PLAYER, "Duration:" + mPlayer.getDuration());
						
						// Start to play
						mPlayer.start();
						bReleased = false;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}
		catch(Exception e)
		{
			if(mPlayer != null)
			{
				mPlayer.stop();
				mPlayer.release();
			}
			e.printStackTrace();
		}
	}
	
	// Set Data source
	private void setDataSource(final String path) throws Exception
	{
		if(!URLUtil.isNetworkUrl(path))
		{
			mPlayer.setDataSource(path);
		}
		else
		{
			if(bReleased == false)
			{
				// Create URL object
				URLConnection conn = new URL(path).openConnection();
				conn.connect();
				
				// Get inputStream
				InputStream inputStream = conn.getInputStream();
				if(inputStream == null)
				{
					throw new RuntimeException("Input Stream is null");
				}
				
				// Create tmp file
				File tempFile = File.createTempFile("music",  "." + getFileExtension(path));
				currentTempFilePath= tempFile.getAbsolutePath();
				FileOutputStream fos = new FileOutputStream(tempFile);
				byte buffer[] = new byte[1024];
				
				do
				{
					int numRead = inputStream.read(buffer);
					if(numRead <=0)
					{
						break;
					}
					fos.write(buffer, 0, numRead);
				}while(true);
				/*
				 byte[] data = StreamTool.read(inputStream);
				 fos.write(data);
				 */
				
				mPlayer.setDataSource(currentTempFilePath);
				try
				{
					inputStream.close();
				}
				catch(Exception e)
				{
					Log.d(TAG_PLAYER, "setDataSource:" + e.getMessage());
				}
			}
		}
	}
	
	// Get file extension
	private String getFileExtension(String strFileName)
	{
		File f = new File(strFileName);
		String strExtension = f.getName();
		strExtension = ( strExtension.substring(strExtension.lastIndexOf(".")+1) )
				.toLowerCase();
		
		if(strExtension == "")
		{
			strExtension = "dat";
		}
		
		return strExtension;
	}
	
	// Del tmp file
	private void delFile(String strFileName)
	{
		File f = new File(strFileName);
		if(f.exists())
		{
			f.delete();
		}
	}
	
	// Activity onPause
	@Override
	protected void onPause()
	{
		try
		{
			delFile(currentTempFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		super.onPause();
	}
	/**************************** UploadInfo layout **************************************/
	private void showUploadInfo()
	{
		Button btnSubmit = (Button) findViewById(R.id.btn_upload_info_submit);
		btnSubmit.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
			}
		});
	} 
}	