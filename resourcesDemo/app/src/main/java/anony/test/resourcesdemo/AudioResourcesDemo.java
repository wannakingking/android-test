package anony.test.resourcesdemo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import java.io.IOException;


public class AudioResourcesDemo extends AppCompatActivity
{
    private MediaPlayer player1 = null;
    private MediaPlayer player2 = null;

    private AssetFileDescriptor f;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_resources_demo);
        setTitle("Audio resources demo");

        player1 = MediaPlayer.create(this, R.raw.ding);

        try
        {
            f = getAssets().openFd("notify.wav");
            player2 = new MediaPlayer();
            player2.setDataSource(f.getFileDescriptor(),
                    f.getStartOffset(),
                    f.getLength());
            player2.prepare();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Button btn1 = (Button) findViewById(R.id.btn_audio_1);
        btn1.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                player1.start();
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn_audio_2);
        btn2.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                player2.start();
            }
        });
    }

    @Override
    public void onDestroy()
    {
        if(f != null)
        {
            try
            {
                f.close();
            }
            catch(Exception e){}
        }
    }
}
