package com.ctrlor.testlauncher;

import android.app.ExpandableListActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestExpandableListActivity extends ExpandableListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_test_expandable_list);

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] mintPic = new int[] {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d
            };
            private String[] straParent = new String[] {
                "parent-1", "parent-2", "parent-3", "parent-4"};
            private String [][] straaChild = new String[][] {
                {"child1-1", "child1-2", "child1-3"},
                {"child2-1", "child2-2", "child2-3"},
                {"child3-1", "child3-2", "child3-3"},
                {"child4-1", "child4-2", "child4-3"}
            };

            // Get the child list items data from specified location and list
            @Override
            public Object getChild( int groupPosition, int childPosition ) {
                return straaChild[groupPosition][childPosition];
            }

            @Override
            public long getChildId( int groupPosition, int childPositioin ) {
                return childPositioin;
            }

            @Override
            public int getChildrenCount( int groupPosition ) {
                return straaChild[groupPosition].length;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView tv = new TextView(TestExpandableListActivity.this);
                tv.setLayoutParams(lp);
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                tv.setPadding(36, 0, 0, 0);
                tv.setTextSize(20);
                return tv;
            }

            // The appearance of the child list item 
            @Override
            public View getChildView( int groupPosition, int childPosition,
                    boolean isLastChild, View convertView, ViewGroup parent) {
                
                TextView tv = getTextView();
                tv.setText(getChild(groupPosition, childPosition).toString());
                return tv;
            }

            // Get the groupData of specified location
            @Override
            public Object getGroup( int groupPosition ) {
                return straParent[groupPosition];
            }

            @Override
            public int getGroupCount() {
                return straParent.length;
            }

            @Override
            public long getGroupId( int groupPosition ) {
                return groupPosition;
            }

            // The appearance of every group
            public View getGroupView( int groupPosition, boolean isExpanded,
                    View convertView, ViewGroup parent ) {
                LinearLayout layout = new LinearLayout( TestExpandableListActivity.this);
                layout.setOrientation( 0 );

                ImageView mImage = new ImageView(TestExpandableListActivity.this);
                mImage.setImageResource( mintPic[groupPosition] );
                layout.addView(mImage);

                TextView tv = getTextView();
                tv.setText( getGroup(groupPosition).toString() );
                layout.addView( tv );

                return layout;
            }

            @Override
            public boolean isChildSelectable( int goupPosition, int childPosition ) {
                return true;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };

        // set the list to display
        setListAdapter( adapter );
	}


}
