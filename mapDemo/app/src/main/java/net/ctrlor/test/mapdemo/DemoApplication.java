package net.ctrlor.test.mapdemo;

/**
 * Created by Adminstrator on 2016/12/26.
 */
import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

public class DemoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializing Baidu map
        SDKInitializer.initialize(this);
    }
}
