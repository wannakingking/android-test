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
import java.util.List;
import java.util.Map;

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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GetMethod extends Activity
{

	private static final String TAG 		= "ctrlor-GetMethod";

	private LinearLayout layoutJsonList;
	private LinearLayout layoutPlayer;
	private TableLayout  layoutUploadInfo;

	private String addressJsonList  = "http://192.168.0.11/test/users.json";
	private String addressPlayer	= "http://192.168.0.11/test/media/example.mp3";
	private String addressUploadInfo= "http://192.168.0.11/test/uploadinfo.php";

	/******************** Player ********************/
	private SimpleAdapter adapter;

	/******************** Player ********************/
	private String tempFilePath = "";
	private boolean bLocal = true;
	private MediaPlayer mPlayer;
	private boolean bStopped = false;

	/******************** UploadInfo ********************/
	private String address;
	private String strResult;

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

	// Handler
	final Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			// Json list
			if(msg.arg1 == 0x1)
			{
				// Create a simple adapter
				adapter = new SimpleAdapter(getBaseContext(),
					(List<? extends Map<String, ?>>) msg.obj,
					R.layout.json_list,
					new String[]{"id", "username", "password"},
					new int[]{R.id.json_list_c1, R.id.json_list_c2, R.id.json_list_c3});

				( (ListView) findViewById(R.id.lv_json_list) )
					.setAdapter(adapter);
				
			}
			// UploadInfo, display the content return.
			if(msg.arg1 == 0x2)
			{
				((TextView)findViewById(R.id.tv_upload_info_return_content) )
					.setText((String)msg.obj);
				
			}
		}
	};
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

						Log.d(TAG, "json:\n" + json);
						/***
						 * json list demo
						 * {
						 * 		"users": [
						 * 				{
						 * 					"id":"1",
						 * 					"username":"username_1",
						 * 					"password":"password_1",
						 * 				},
						 * 				{
						 * 					"id":"2",
						 * 					"username":"username_2",
						 * 					"password":"password_2",
						 * 				}
						 * 				]
						 * }
						 */

						JSONArray jsonArray = (new JSONObject(json))
								.getJSONArray("users");
						for(int i=0; i<jsonArray.length(); i++)
						{
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							HashMap<String, Object> user = new HashMap<String, Object>();

							user.put("id", jsonObject.getInt("id") );
							user.put("username", jsonObject.getString("username") );
							user.put("password", jsonObject.getString("password") );
							users.add(user);

						}
						
						Message msg = new Message();
						msg.arg1 = 0x1;
						msg.obj = users;
						mHandler.sendMessage(msg);
					}
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	/**************************** Player layout **************************************/
	/**
	 * Player layout
	 *
	 */
	private void showPlayer()
	{

		ImageButton btnPlay = (ImageButton) findViewById(R.id.btn_player_play);
		btnPlay.setOnClickListener(new ImageButton.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  playAudio();
            }
        });

		ImageButton btnPause = (ImageButton) findViewById(R.id.btn_player_pause);
		btnPause.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	if(mPlayer != null)
            	{
            		if( !bStopped )
            		{
            			if(mPlayer.isPlaying())
            			{
            				mPlayer.pause();
            			}
            			else
            			{
            				mPlayer.start();
            			}
            		}
            	}
            }
        });

		ImageButton btnReset = (ImageButton) findViewById(R.id.btn_player_reset);
		btnReset.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 if( !bStopped )
                 {
                	 if(mPlayer != null)
                	 {
                		 mPlayer.seekTo(0);
                	 }
                 }

            }
        });

		ImageButton btnStop	= (ImageButton) findViewById(R.id.btn_player_stop);
		btnStop.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	try
            	{
	            	if(mPlayer != null)
	            	{
	            		if( !bStopped )
	            		{
	            			mPlayer.stop();
	            			bStopped = true;

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

	private void playAudio()
	{
		try
		{
			if(mPlayer != null)
			{
				// After pause
				if( !bStopped )
				{
					mPlayer.start();
				}
				// After stop
				else
				{
					mPlayer.prepareAsync();
				}

				return;
			}

			mPlayer = new MediaPlayer();

			Log.d(TAG, "mPlayer is new init object now!");

			mPlayer.setAudioStreamType(2);

			// Error listener
			mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()
			{

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra)
				{
					// TODO Auto-generated method stub
					Log.d(TAG, "Error in lostener, what:" + what + "extra:" + extra);
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
					Log.d(TAG, "Update buffer:" + Integer.toString(percent)+ "%");
				}
			});

			// Completion listener
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
			{
				@Override
				public void onCompletion(MediaPlayer mp)
				{
					// TODO Auto-generated method stub
					Log.d(TAG, "mPlayer listener completion.");
				}
			});

			// Prepared listener
			mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
			{

				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					Log.d(TAG, "Prepared listener");

					if(bStopped)
					{
						mPlayer.seekTo(0);
						bStopped = false;
					}

					mp.start();
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
						setAudioSource(addressPlayer);

						mPlayer.prepareAsync();

					}
					catch(Exception e)
					{
						e.printStackTrace();
					}

					finally
					{
						Log.d(TAG, "Thread(r) is over!");
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
				mPlayer = null;

				Log.d(TAG, "Catch exception, mPlayer released!");
			}
			e.printStackTrace();
		}
	}

	// Set audio source
	private void setAudioSource(final String url) throws Exception
	{
		if(URLUtil.isNetworkUrl(url))
		{
			// Do not play local file
			if(!bLocal)
			{
				mPlayer.setDataSource(url);
				return;
			}
			if(!bStopped)
			{
				// Create URL object
				URLConnection conn = new URL(url).openConnection();
				conn.connect();

				// Get inputStream
				InputStream inputStream = conn.getInputStream();
				if(inputStream == null)
				{
					throw new RuntimeException("Input Stream is null");
				}

				// Create tmp file
				File f = File.createTempFile("music", ".dat");
				tempFilePath = f.getAbsolutePath();

				Log.d(TAG, "tempFilePath is: " + tempFilePath);

				FileOutputStream fos = new FileOutputStream(tempFilePath);

				/* // Also working
				byte[] data = StreamTool.read(inputStream);
				fos.write(data);
				fos.flush();
				*/

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

				Log.d(TAG, "The music is download to the tempFilePath");

				mPlayer.setDataSource(tempFilePath);
				try
				{
					inputStream.close();
				}
				catch(Exception e)
				{
					Log.d(TAG, "setDataSource:" + e.getMessage());
				}
			}
		}
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

	/**************************** UploadInfo layout **************************************/
	private void showUploadInfo()
	{
		Button btnSubmit = (Button) findViewById(R.id.btn_upload_info_submit);
		btnSubmit.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String strUsername = ((EditText)findViewById(R.id.et_upload_info_username))
						.getText().toString();
				String strPassword = ((EditText)findViewById(R.id.et_upload_info_password))
						.getText().toString();


				uploadInfo( strUsername, strPassword );
			}
		});
	}
	private void uploadInfo(final String strUsername, final String strPassword)
	{
		address = addressUploadInfo + "?username=" + strUsername
				+ "&password=" + strPassword;

		Log.d(TAG, "The address requested by UploadInfo: " + address);
		
		strResult = "null";

		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					HttpURLConnection conn = (HttpURLConnection)
							(new URL(address)).openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(3000);
					conn.connect();
					if (conn.getResponseCode() == 200)
					{
						byte[] data = StreamTool.read(conn.getInputStream());
						strResult = new String(data);
                        Log.d(TAG, "strResult: " + strResult);

						Message msg = new Message();
						msg.arg1 = 0x2;
						msg.obj = strResult;
						mHandler.sendMessage(msg);
					}
					else
					{
						Log.d(TAG, "Error in upload info page");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
                    Log.d(TAG, "Error in upload info page");
				}
			}
		};

		new Thread(r).start();

	}

	// Activity onPause
	@Override
	protected void onPause()
	{
		try
		{
			delFile(tempFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		super.onPause();
	}

	// Activity onDestory
	@Override
	protected void onDestroy()
	{
		if(mPlayer != null)
		{
			if(mPlayer.isPlaying())
			{
				mPlayer.stop();
			}

			mPlayer.release();
			mPlayer = null;
		}

		super.onDestroy();
	}
}
