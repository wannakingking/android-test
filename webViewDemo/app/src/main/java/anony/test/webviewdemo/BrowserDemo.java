package anony.test.webviewdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.net.InetAddress;

public class BrowserDemo extends AppCompatActivity
{
    private final static String TAG = "anony.test.webViewDemo";
    private final static int FLAG_LOAD_URL = 0x1;

    private EditText et;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        setTitle("Browser demo");

        et = (EditText) findViewById(R.id.et_url_address);
        et.setText("oschina.net");

        webView = (WebView) findViewById(R.id.wv_display_web_page);

        Button btn  = (Button) findViewById(R.id.btn_get_web_page);
        btn.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = et.getText().toString();

                if(!url.isEmpty())
                {
                    AnalyzeAddress(url);
                }

                // Load assets resources if url in null
                else
                {
                    LoadJSDemo();
                }

            }
        });

    }

    private void AnalyzeAddress(final String url)
    {
        final String address = url;


        // If a url
        if(URLUtil.isNetworkUrl(address))
        {
            // Start with 'http:'
            if (address.startsWith("http:"))
            {
                try
                {
                    Message msg = new Message();
                    msg.arg1 = FLAG_LOAD_URL;
                    msg.obj  = address;
                    mHandler.sendMessage(msg);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            // Start with 'https:'
            else if (address.startsWith("https:"))
            {
                Toast.makeText(getBaseContext(),
                        "HTTPS is not supported!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Is local file or directory ?
        else if(new File(address).exists())
        {
            Toast.makeText(getBaseContext(),
                    "That's a local file you inputted!",
                    Toast.LENGTH_SHORT).show();
        }

        // Is url just forget to input 'http://'
        else
        {
            try
            {
                String[] parts = address.split("/");
                final String hostname = parts[0];

                Log.d(TAG, "hostname:" + hostname);
                new Thread(new Runnable()
                {
                        @Override
                        public void run()
                        {
                            try
                            {
                                InetAddress mInetAddress =
                                        InetAddress.getByName(hostname);

                                // Send msg to UI thread to load url.
                                Message msg = new Message();
                                msg.arg1 = FLAG_LOAD_URL;
                                msg.obj  = "http://" + address;
                                mHandler.sendMessage(msg);

                                Log.d(TAG, "Inetaddress:" + mInetAddress);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }

                        }
                }).start();

            }
            catch(Exception e)
            {
                Log.d(TAG, "address is error:");
                e.printStackTrace();
            }

        }

    }

    // Load JS
    private void LoadJSDemo()
    {
        try
        {
            webView.getSettings().setJavaScriptEnabled(true);

            JavaScriptInterface mInterface = new JavaScriptInterface(this);
            webView.addJavascriptInterface(mInterface, "AndroidFunction");

            webView.loadUrl("file:///android_asset/test.html");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public class JavaScriptInterface
    {
        Context mContext;

        JavaScriptInterface(Context c)
        {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String info)
        {
            Toast.makeText(mContext, info, 
                    Toast.LENGTH_LONG).show();
        }
    }

    // Handle message
    final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            // Load remote 'http' web page
            if (msg.arg1 == FLAG_LOAD_URL)
            {
                try
                {
                    Log.d(TAG, "LoadUrl:" + msg.obj);

                    // Enable to support javascript.
                    WebView webView = (WebView) findViewById(R.id.wv_display_web_page);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);

                    // Load page in current app activity
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl((String) msg.obj);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
    };

    // Catch key down
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check the key event was the Back button and if there's history
        if( (keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack() )
        {
            webView.goBack();
            return true;
        }

        // If not
        return super.onKeyDown(keyCode, event);
    }
}
