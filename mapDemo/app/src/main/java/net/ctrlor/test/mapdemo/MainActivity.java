package net.ctrlor.test.mapdemo;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private static final int BAIDU_MAP = 0x01;
    private static final int GOOGLE_MAP = 0x02;
    private static final int BING_MAP = 0x03;

    private static int nMapVendor;
    private static boolean bMapVendor;
    private static boolean bCurrentLanLong;
    private static boolean bCurrentGeo;
    private static boolean bTargetGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_open_map);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                int id = ((RadioGroup) findViewById(R.id.rg_map_vendor))
                        .getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radio_baidu_map:
                        nMapVendor = BAIDU_MAP;
                        intent = new Intent(getBaseContext(),
                                BaiduMapDemo.class);
                        startActivityForResult(intent, BAIDU_MAP);
                        break;

                    case R.id.radio_google_map:
                        nMapVendor = GOOGLE_MAP;
                        intent = new Intent(getBaseContext(),
                                GoogleMapDemo.class);
                        startActivityForResult(intent, GOOGLE_MAP);
                        break;

                    case R.id.radio_bing_map:
                        nMapVendor = BING_MAP;
                        intent = new Intent(getBaseContext(),
                                BingMapDemo.class);
                        startActivityForResult(intent, BING_MAP);
                        break;
                }

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        TextView tvOutput = (TextView) findViewById(R.id.tv_output);
        Bundle bundle = intent.getExtras();
        if (resultCode == 1 && bundle != null) {
            CustomMapData data = (CustomMapData)bundle.getSerializable("data");
            tvOutput.setText(
                    "MapVendor:"            + data.getMapVendor() + "\n" +
                    "Current's Latitude: "  + data.getLatitude() + "\n" +
                    "Current's Longitude:"  + data.getLongitude() + "\n" +
                    "Current's Geo:"        + data.getCurrentGeoLocation() + "\n" +
                    "Target's Geo:"         + data.getTargetGeoLocation() + "\n");
        } else {
            tvOutput.setText("null");
        }
    }
}
