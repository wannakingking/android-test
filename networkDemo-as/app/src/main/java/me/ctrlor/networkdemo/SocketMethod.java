package me.ctrlor.networkdemo;

import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.util.Log;


public class SocketMethod extends Activity
{

	private static final String TAG = "ctrlor-networkDemo";
	private static final int FLAG_SERVER_MSG_ITEM = 0x1;
	private static final int FLAG_CLIENT_MSG_ITEM = 0x2;
	private static final int FLAG_INSERT_MSG 	  = 0x3;

	private static final String strServerName = "Server: ";
	private static final String strClientName = "Client: ";
	private static final String host = "127.0.0.1";
	private static final int port = 10010;

	private static ServerSocket listenSocket;
	private static Socket	serverSocket;
	private static Socket	clientSocket;
	private static EditText et;
	private static ListView listView;

	private static final ArrayList< HashMap<String, Object> > items
			= new ArrayList< HashMap<String, Object> >();

	private ClientGuard threadClientGuard;
	private ServerGuard threadServerGuard;

    private Object mServerListenLock;
    private boolean bLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_method);
		setTitle("Socket method");

		listView = (ListView) findViewById(R.id.list_view);
		et = (EditText) findViewById(R.id.et_message);
		Button btnSend = (Button) findViewById(R.id.btn_send);
		btnSend.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				String string = et.getText().toString();

				// Insert message to client thread.
				Message message = new Message();
				message.arg1 = FLAG_INSERT_MSG;
				message.obj = string;
				try {
					threadClientGuard.handler.sendMessage(message);
				} catch(Exception e) {
					e.printStackTrace();
				}
				et.setText("");
			}
		});

        // Start thread tasks
        StartTasks();

		Log.d(TAG, "onCreate().");
	}

	/***
	  *
	  * Server guard
	  */
	public class ServerGuard extends Thread {
		@Override
		public void run() {
			try {

				listenSocket = new ServerSocket(port);

				// Ready to listen, unlock
				synchronized(mServerListenLock) {
					bLock = false;
					mServerListenLock.notify();
				}

				serverSocket = listenSocket.accept();

				BufferedReader in = new BufferedReader(
						new InputStreamReader(serverSocket.getInputStream()));
				PrintWriter   out = new PrintWriter(serverSocket.getOutputStream(),
											true);

				while(true) {

					// Waiting for read.
					String string = "";
					try {
						string = in.readLine();
					} catch (Exception e) {
						Log.d(TAG, "readLine() exception, ServerGuard quit.");
						break;
					}
					if(string.equals("exit") || string.equals("quit")) {
						break;
					}
					out.println("return: " + string);

					Message msg = new Message();
					msg.arg1 = FLAG_SERVER_MSG_ITEM;
					msg.obj = string;
					mHandler.sendMessage(msg);

					// Is interrupt?
					if(Thread.currentThread().isInterrupted()) {
						return;
					}

				}

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	  *
	  * Client guard
	  */
	public class ClientGuard extends Thread {

		public Handler handler = null;

		@Override
		public void run() {
			try {
				// Waiting for server guard ready to listen
				synchronized(mServerListenLock) {
					while(bLock) {
						Log.d(TAG, "ClientGuard is locked, waiting for serverSocket"
							+ "ready to listen.");
						mServerListenLock.wait();
					}
				}

				Log.d(TAG, "ClientGuard is unlocked now");

				// Initializing clientSocket if null.
				clientSocket = new Socket(host, port);

				// Loop to get message from MessageQueue
				Looper.prepare();
				handler = new Handler(Looper.myLooper()) {
					@Override
					public void handleMessage(Message msg) {
						try {
							if (msg.arg1 == FLAG_INSERT_MSG) {
								PrintWriter out = new PrintWriter(
										clientSocket.getOutputStream(), true);

								out.println((String) msg.obj);


								// Send message to UI thread for update the list view.
								Message msgUpdate = new Message();
								msgUpdate.arg1 = FLAG_CLIENT_MSG_ITEM;
								msgUpdate.obj = msg.obj;
								mHandler.sendMessage(msgUpdate);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						super.handleMessage(msg);
					}

				};

				// Is interrupt?
				if(Thread.currentThread().isInterrupted()) {
					return;
				}
				
				Looper.loop();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/***
	  * Add list item
	  */
	private void addListItem(String name, String message) {

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("name", name);
		item.put("message", message);
		items.add(item);

		SimpleAdapter adapter = new SimpleAdapter(this,
			items,
			R.layout.socket_method_server_item,
			new String[]{"name", "message"},
			new int[]{R.id.tv_server_name, R.id.tv_server_message});

			listView.setAdapter(adapter);


	}

    /***
      * Handler message
      */
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == FLAG_SERVER_MSG_ITEM) {
				addListItem(strServerName, (String)msg.obj);
            }

			if(msg.arg1 == FLAG_CLIENT_MSG_ITEM) {
				addListItem(strClientName, (String)msg.obj);
			}
        }
    };

	/***
	  * Start thread tasks
	  */
	private void StartTasks() {

		// Initializing the lock
        bLock = true;
		mServerListenLock = new Object();

		// Start server guard thread.
		threadServerGuard = new ServerGuard();
		threadServerGuard.start();

		// Start client guard thread;
		threadClientGuard = new ClientGuard();
		threadClientGuard.start();



		Log.d(TAG, "Start thread tasks!!!!!!!!!");

	}

	/***
	  * End thread tasks
	  */
	private void EndTasks() {

		try {
            if(!threadServerGuard.currentThread().isInterrupted()) {
			    threadServerGuard.currentThread().interrupt();
            }

            if(!threadClientGuard.currentThread().isInterrupted()) {
			    threadClientGuard.currentThread().interrupt();
            }

			listenSocket.close();

			if(serverSocket != null) {
				serverSocket.close();
			}

			if(clientSocket != null) {
				clientSocket.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
            Log.d(TAG, "End thread tasks!!!!!!!!!");
		}

	}


	@Override
	public void onStop() {

        EndTasks();

		Log.d(TAG, "onStop().");
		super.onStop();
	}


	@Override
	public void onRestart() {

        StartTasks();

		Log.d(TAG, "onRestart().");
		super.onRestart();
	}

	@Override
	public void onDestroy() {

        EndTasks();

		// Clear list items
		items.clear();

		super.onDestroy();

		Log.d(TAG, "onDestroy().");
	}
}
