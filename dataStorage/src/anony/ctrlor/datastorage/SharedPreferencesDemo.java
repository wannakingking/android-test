package anony.ctrlor.datastorage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class SharedPreferencesDemo extends Activity {


	private EditText et;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences_demo);
		setTitle( "Shared Preferences Demo" );
		
		et = (EditText) findViewById( R.id.et_write_sp );
		tv = (TextView) findViewById( R.id.tv_read_sp );
		
		// Store to sharedPreferences object
		Button btnWrite = (Button)findViewById( R.id.btn_write_sp);
		btnWrite.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				String string = et.getText().toString();

				SharedPreferences sp = getSharedPreferences( "SharedPreferences", 0 );
				SharedPreferences.Editor editor = sp.edit();
				editor.putString( "data", string );
				editor.commit();
			}
		});

		// Read data from SharedPreferences object
		Button btnRead = (Button)findViewById( R.id.btn_read_sp );
		btnRead.setOnClickListener( new Button.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				SharedPreferences sp = getSharedPreferences( "SharedPreferences", 0 );

				String string = sp.getString( "data", "null");
				tv.setText( string );
			}
		});

	}
}
