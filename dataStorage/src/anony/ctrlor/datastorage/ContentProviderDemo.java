package anony.ctrlor.datastorage;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.util.Log;

public class ContentProviderDemo extends Activity {


	private final static String TAG = "ContentProvider Demo";

	ArrayList< HashMap<String, Object> > contacts = new
			ArrayList< HashMap<String, Object> >();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider_demo );
		setTitle( "ContentProvider Demo" );
		
		// Start thread to read contacts
		readContacts();
		
		// Build list view by the data had been read
		SimpleAdapter adapter = new SimpleAdapter( ContentProviderDemo.this, 
				contacts,
				R.layout.list_items,
				new String[]{ "name", "phoneNumber", "email"},
				new int[]{ R.id.tv_item_1, R.id.tv_item_2, R.id.tv_item_3 });

		( (ListView) findViewById( R.id.list_view) ).setAdapter( adapter );
	}

	// Read contacts by thread
	public void readContacts()
	{
		try
			{
				
				Cursor cursor = getContentResolver().query( 
						ContactsContract.Contacts.CONTENT_URI, null, null, null, null );
				
				// Get the counts of all contacts
				Log.d( TAG, "cursor count:" + cursor.getCount() );
		
	
				while( cursor.moveToNext() )
				{
					String phoneNumber = "";
					String email = "";
					String id 	 = cursor.getString( cursor.getColumnIndex( Contacts._ID ));
					String name = cursor.getString( cursor.getColumnIndex( Contacts.DISPLAY_NAME ));
					
					Log.d( TAG, "id:" + id );
					Log.d( TAG, "name:" + name );
					//Fetch the phone number
					Cursor phoneCursor = getContentResolver().query(
							CommonDataKinds.Phone.CONTENT_URI,
							null, 
							CommonDataKinds.Phone.CONTACT_ID+"="+id, 
							null, 
							null );
	
					while( phoneCursor.moveToNext() )
					{
						phoneNumber = phoneCursor.getString( phoneCursor.getColumnIndex( 
								CommonDataKinds.Phone.NUMBER ));
						Log.d( TAG, "PhoneNumber:" + phoneNumber );
					}
					phoneCursor.close();
					
					// Fetch email
					Cursor emailCursor = getContentResolver().query(
							CommonDataKinds.Email.CONTENT_URI,
							null,
							CommonDataKinds.Email.CONTACT_ID+"="+id,
							null,
							null );
					while( emailCursor.moveToNext() )
					{
						email = emailCursor.getString( emailCursor.getColumnIndex( 
								CommonDataKinds.Email.DATA ));
						Log.d( TAG, "email:" + email );
					}
					emailCursor.close();
					
					HashMap<String, Object> contact = new HashMap<String, Object>();
					contact.put( "id", id );
					contact.put( "name", name );
					contact.put( "phoneNumber", phoneNumber );
					contact.put( "email", email );
					contacts.add( contact );

					// Autoincrement after fetch one contact
				}
				cursor.close();
	
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
	}
}
