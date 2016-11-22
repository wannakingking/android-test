package anony.test.resourcesdemo;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XmlResourcesDemo extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_resources_demo);
        setTitle("Xml resources demo");

        Button btn = (Button) findViewById(R.id.btn_xml);
        btn.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                XmlResourceParser parser = getResources().getXml(R.xml.users);
                try
                {
                    StringBuilder strBuilder = new StringBuilder("");
                    
                    // Not end
                    while( parser.getEventType()
                            != XmlResourceParser.END_DOCUMENT)
                    {
                        // Start label
                        if( parser.getEventType() 
                                == XmlResourceParser.START_TAG)
                        {
                            // Get the name of label
                            String tagName = parser.getName();

                            // Label 'user'
                            if( tagName.equals("user") )
                            {
                                // Get the value of 'tagName'
                                String id = parser.getAttributeValue(null, "id");
                                String pwd = parser.getAttributeValue(1);
                                String name = parser.nextText();

                                strBuilder.append("id: " + id + ", ")
                                        .append("username: " + name + ", ")
                                        .append("password: " + pwd)
                                        .append("\n");
                            }
                        }

                        // Move to next item
                        parser.next();
                    }
                    ( (TextView)findViewById(R.id.tv_xml) ).setText(strBuilder.toString());

                }
                catch(XmlPullParserException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
