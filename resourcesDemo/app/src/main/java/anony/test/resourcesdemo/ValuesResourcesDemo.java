package anony.test.resourcesdemo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by ctrlor on 11/14/16.
 */

public class ValuesResourcesDemo extends AppCompatActivity
{
    // Using strings resource
    int[] stringIds = new int[]
    {
        R.string.c1, R.string.c2, R.string.c3,
        R.string.c4, R.string.c5, R.string.c6,
        R.string.c7, R.string.c8, R.string.c9
    };

    // Using colors resource
    int[] colorIds = new int[]
    {
        R.color.c1, R.color.c2, R.color.c3,
        R.color.c4, R.color.c5, R.color.c6,
        R.color.c7, R.color.c8, R.color.c9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.values_resources_demo);

        // Create a BaseAdapter object
        BaseAdapter adapter = new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return stringIds.length;
            }

            @Override
            public Object getItem(int position)
            {
                return getResources().getText(stringIds[ position ]);
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent)
            {
                TextView tv = new TextView(getBaseContext());
                Resources res = getBaseContext().getResources();

                tv.setWidth( (int)res.getDimension(R.dimen.cell_width) );
                tv.setHeight( (int)res.getDimension(R.dimen.cell_height) );

                tv.setGravity(Gravity.CENTER);

                tv.setText( stringIds[position] );
                tv.setBackgroundResource( colorIds[position] );

                tv.setTextSize( getResources().
                        getInteger(R.integer.font_size) );

                return tv;
            }
        };

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);


    }
}
