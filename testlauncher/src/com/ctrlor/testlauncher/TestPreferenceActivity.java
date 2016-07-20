package com.ctrlor.testlauncher;

import com.ctrlor.testlauncher.R;
import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;

public class TestPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Build a button for the view
        if( hasHeaders() ) {
            Button button = new Button( this );
            button.setText( "setting" );
            
            // Apply
            setListFooter( button );
        }
	}

    // Ovriride onBuildHeaders
    @Override
    public void onBuildHeaders( List<Header> target ) {
        
        // Load layout
        loadHeadersFromResource( R.xml.preference_headers, 
                target );
    }

    public static class Prefs1Fragment extends PreferenceFragment {
        
        @Override
        public void onCreate( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            addPreferencesFromResource(R.xml.preference_page1);
        }
    }

    public static class Prefs2Fragment extends PreferenceFragment {

        @Override
        public void onCreate( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            addPreferencesFromResource( R.xml.preference_page2 );

            // Get the agruments passed to the Fragment
            String website = getArguments().getString( "website" );
            Toast.makeText(getActivity(), 
                    "the domain of the website is :" + website,
                    Toast.LENGTH_LONG).show();
        }
    }
}
