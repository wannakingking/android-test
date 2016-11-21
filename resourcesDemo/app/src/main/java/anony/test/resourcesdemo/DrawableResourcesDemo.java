package anony.test.resourcesdemo;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class DrawableResourcesDemo extends AppCompatActivity
						implements View.OnClickListener
{
	ImageButton btn4;
	ImageButton btn5;
	ImageButton btn6;
	ImageButton btn8;
	ClipDrawable clipDrawable;
	TransitionDrawable transDrawable;
	ScaleDrawable scaleDrawable;
	LevelListDrawable levelListDrawable;

	boolean aBoolean = false;

	final static String TAG = "Drawable resources demo";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_resources_demo);

		setTitle("Drawable resources demo");


		btn4 = (ImageButton) findViewById(R.id.btn_drawable_4);
		clipDrawable = (ClipDrawable) btn4.getDrawable();
		clipDrawable.setLevel(1000);
		btn4.setOnClickListener(this);

        // Transition animation drawable
        btn5 = (ImageButton) findViewById(R.id.btn_drawable_5);
		transDrawable = (TransitionDrawable) btn5.getDrawable();
		btn5.setOnClickListener(this);

		// Scale animation drawable
		btn6 = (ImageButton) findViewById(R.id.btn_drawable_6);
		scaleDrawable = (ScaleDrawable) btn6.getDrawable();
		scaleDrawable.setLevel(1000);
		btn6.setOnClickListener(this);

		// Level list drawable
		btn8 = (ImageButton) findViewById(R.id.btn_drawable_8);
		levelListDrawable = (LevelListDrawable) btn8.getDrawable();
		levelListDrawable.setLevel(0);
		btn8.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.btn_drawable_1:
			break;

		case R.id.btn_drawable_2:
			break;

		case R.id.btn_drawable_3:
			break;

		case R.id.btn_drawable_4:
			clipDrawable.setLevel( (clipDrawable.getLevel() + 1000) % 10000 );
			break;

		case R.id.btn_drawable_5:
			if(!aBoolean)
			{
				transDrawable.startTransition(500);
			}
			else
			{
				transDrawable.reverseTransition(500);
			}
			Log.d(TAG, "aBoolean is: " + aBoolean);
			aBoolean = !aBoolean;

			break;

		case R.id.btn_drawable_6:
			scaleDrawable.setLevel( (scaleDrawable.getLevel() + 1000) % 10000 );
			break;

		case R.id.btn_drawable_8:
			levelListDrawable.setLevel( (levelListDrawable.getLevel() + 1) % 2);
			break;

		default:
			break;
		}

    }

}
