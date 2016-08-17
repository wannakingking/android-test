package anony.ctrlor.datastorage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NetworkStorageDemo extends Activity {

	private final static String TAG = "NetworkStorageDemo";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_storage_demo );
		
		getRequest( "http://162.211.181.21");
	}
	
	private void getRequest( String urlString )
	{
		try
		{
			URL url = new URL( urlString ); 
			HttpURLConnection conn = ( HttpURLConnection )url.openConnection();
			conn.setRequestMethod( "GET" );
			conn.setRequestProperty( "User-Agent", "Mozilla/5.0" );
			conn.setDoInput( true );

			BufferedReader rd = new BufferedReader( 
					new InputStreamReader( conn.getInputStream() ));

			String line = "";
			StringBuffer result = new StringBuffer();
			while(( line = rd.readLine()) != null )
			{
				result.append( line );
				Log.d( TAG, "line:" + line );
			}
			rd.close();

			( (TextView)findViewById( R.id.tv_html ) ).setText( result.toString() );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
