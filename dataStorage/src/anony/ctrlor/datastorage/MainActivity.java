package anony.ctrlor.datastorage;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        Button btnShareP        = (Button) findViewById( R.id.btn_shared_preferences_demo );
        Button btnFileS         = (Button) findViewById( R.id.btn_file_storage_demo );
        Button btnSqlite        = (Button) findViewById( R.id.btn_sqlite_demo);
        Button btnContentP      = (Button) findViewById( R.id.btn_content_provider_demo );
        Button btnNetworkS      = (Button) findViewById( R.id.btn_network_storage_demo );

        // Start SharedPreference demo
        btnShareP.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent(
                        MainActivity.this, 
                        SharedPreferencesDemo.class );
                startActivity( intent );
            }
        });

        // Start file storage demo
        btnFileS.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent(
                        MainActivity.this, 
                        FileStorageDemo.class );
                startActivity( intent );
            }
        });

        // Start sqlite storage demo
        btnSqlite.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent(
                        MainActivity.this, 
                        SqliteDemo.class );
                startActivity( intent );
            }
        });

        // Start Content Provider demo
        btnContentP.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent(
                        MainActivity.this, 
                        ContentProviderDemo.class );
                startActivity( intent );
            }
        });

        // Start network storage demo
        btnNetworkS.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent(
                        MainActivity.this, 
                        NetworkStorageDemo.class );
                startActivity( intent );
            }
        });

    }
}
