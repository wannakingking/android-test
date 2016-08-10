package anony.ctrlor.datastorage;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class SqliteDemo extends Activity 
{

	private final static String TAG = "SqliteDemo";
	private final String nameDB = "sqlite.db";
	private final String nameTable = "user";
	
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_demo );
		setTitle("Sqlite Demo");

		Button btnInit 	= (Button) findViewById( R.id.btn_init_sql );
		Button btnQuery = (Button) findViewById( R.id.btn_query_sql );
		Button btnUpdate= (Button) findViewById( R.id.btn_update_sql );
		Button btnInsert= (Button) findViewById( R.id.btn_insert_sql );
		Button btnDel	= (Button) findViewById( R.id.btn_del_sql );
		Button btnExec	= (Button) findViewById( R.id.btn_exec_sql );
		
		listview = (ListView) findViewById( R.id.lv_users );
		
		// Initialize the sqlite 
		btnInit.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				try
				{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );

					//Drop table
					db.execSQL( "drop table if exists " + nameTable );
					//Then initialize
					String sql = "create table " + nameTable + 
							"(id integer primary key autoincrement, "
							+ "username text, password text)";
					db.execSQL( sql );

					updateListView( db );
					db.close();

					Log.d( TAG, "initlize db:" + db );
					Toast.makeText( SqliteDemo.this, "initlize db:" + db, 
							Toast.LENGTH_SHORT ).show();
				}
				catch( Exception e )
				{ 
					e.printStackTrace();
				}
			}
		});
		
		// Query the sqlite 
		btnQuery.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				try
				{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					Cursor cursor = db.query("user", null, null, null,
							null, null, null );
					if( cursor.moveToFirst() )
					{
						for( int i=0; i<cursor.getCount(); i++ )
						{
							
							int id = cursor.getInt( 0 );
							String username = cursor.getString( 1 );
							String password = cursor.getString( 2 );
							cursor.moveToNext();

							Log.d( TAG, "query sql>" + "\nid:" + id +
									"\nusername:" + username + "\npassword:" +
									password );
							Toast.makeText( SqliteDemo.this,  
									"Query sql>" + "\nid:" + id +
									"\nusername:" + username + "\npassword" +
									password, Toast.LENGTH_SHORT ).show();
						}
					}
					updateListView( db );
					db.close();
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}
		});
		
		// Update the sqlite 
		btnUpdate.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					updateListView( db );
					db.close();
				
			}
		});
		
		// Insert the sqlite 
		btnInsert.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				try
				{
					int id;
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					Cursor cursor = db.query( "user", null, null, null,
							null, null, null );
					if( cursor.moveToLast() )
						id = cursor.getInt( 0 );
					else
						id = 0;
	
					ContentValues cv = new ContentValues();
					cv.put( "username", "username" + (++id) );
					cv.put( "password", "password" + (id) );
						
					long result = db.insert( "user", null, cv );
					Log.d( TAG, "current id:" + id );
					Log.d( TAG, "insert result:" + result );

					updateListView( db );
					db.close();
						
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
					
			}
		});
		
		// Short click to delete the last row of the db
		btnDel.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				try
				{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					Cursor cursor = db.query( nameTable, null, null, null,
							null, null, null );
					if( cursor.moveToLast() )
					{
						int lastid = cursor.getInt( 0 );
						//
						String whereClause = "id=?";
						
						// Del the last row
						String[] whereArgs = {String.valueOf( lastid )};
						
						db.delete( nameTable,  whereClause, whereArgs);
						
						Log.d( TAG,  "dele the last row!\nid:" + lastid);
					}
					updateListView( db );
					db.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		// Long click to delete the table in  the db
		btnDel.setOnLongClickListener( new Button.OnLongClickListener()
		{
			@Override
			public boolean onLongClick( View v )
			{
				try
				{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					db.execSQL( "drop table if exists user" );

					Log.d( TAG,  "drop the table 'user'!" );

					updateListView( db );
					db.close();
					return true;
				}
				catch( Exception e)
				{
					e.printStackTrace();
					return false;
				}
				
			}
		});
		
		// Exec the sql 
		btnExec.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				try
				{
					SQLiteDatabase db = openOrCreateDatabase( nameDB, 0, null );
					String sql		  = ( (EditText) findViewById( R.id.et_sql ) )
							.toString();

					if( sql != null )
						db.execSQL( sql );
					
					updateListView( db );
					db.close();
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}
		});
		
		// Build list view
		//updateListView();
	}

    public void updateListView( SQLiteDatabase db )
    {
        ArrayList< HashMap<String, Object> > users = new
            ArrayList< HashMap<String, Object> >();
        Cursor cursor = db.query( nameTable, null, null, null,
        		null, null, null );
        if( cursor.moveToFirst() )
        {
        	for( int i=0; i<cursor.getCount(); i++ )
        	{
        		int id 			= cursor.getInt( 0 );
        		String username = cursor.getString( 1 );
        		String password = cursor.getString( 2 );

	            HashMap<String, Object> user = new HashMap<String, Object>();
	            user.put( "id", id );
	            user.put( "username", username );
	            user.put( "password", password );
	            users.add( user );
	            
	            cursor.moveToNext();
        	}
        }

        SimpleAdapter adapter = new SimpleAdapter( this, 
                users,
                R.layout.sqlite_demo_list_view_users,
                new String[] { "id", "username", "password" },
                new int[]    { R.id.tv_user_id, R.id.tv_user_name, 
                                R.id.tv_user_password });
        listview.setAdapter( adapter );
        listview.setSelection( adapter.getCount() - 1 );
    }
}
