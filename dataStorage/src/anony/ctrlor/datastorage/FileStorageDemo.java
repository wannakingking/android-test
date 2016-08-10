package anony.ctrlor.datastorage;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class FileStorageDemo extends Activity {

	private final static String TAG  = "ctrlor.FileStorageDemo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_storage_demo );
		setTitle( "File Storage Demo" );
		
        Button btnWrite = (Button) findViewById( R.id.btn_write_fs);
        btnWrite.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                try
                {
                	EditText et = (EditText) findViewById( R.id.et_write_fs );
                    String string = et.getText().toString();

                    Log.d(TAG,  "get edit text:" + string );

                    FileOutputStream fos = openFileOutput( "FileStorage", 
                                  Context.MODE_PRIVATE );
                    fos.write( string.getBytes() );
                    fos.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        Button btnRead = (Button) findViewById( R.id.btn_read_fs);
        btnRead.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                try
                {
                    FileInputStream fis = openFileInput( "FileStorage" ); 
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;

                    while( (len = fis.read(buffer)) != -1 )
                    {
                        baos.write( buffer, 0, len );
                        Log.d( TAG, "fis-len:" + len );
                    }
                    
                    TextView tv = (TextView) findViewById( R.id.tv_read_fs );
                    tv.setText( baos.toString() );

                    fis.close();
                    baos.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

	}
}
