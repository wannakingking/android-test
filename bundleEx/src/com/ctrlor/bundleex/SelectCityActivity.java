package com.ctrlor.bundleex;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectCityActivity extends ExpandableListActivity {
	
	// Define provinces and cities.
	private String[] strProvinces = new String[]
			{ "河南省", "广西壮族自治区", "浙江省" };
	private String[][] strCities = new String[][] 
		{
				{ "郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", 
					"许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "济源" }, 
				{ "南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", 
					"贺州", "百色", "河池"}, 
				{ "杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水"}
			
		};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			
			private TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 64 );
				TextView tv = new TextView( SelectCityActivity.this );
				tv.setLayoutParams(lp);
				tv.setGravity( Gravity.CENTER_VERTICAL | Gravity.LEFT );
				tv.setPadding(36, 5, 5, 5);
				tv.setTextSize(20);
				
				return tv;
			}

			@Override
			public Object getChild( int groupPosition, int childPosition ) {
				return strCities[groupPosition][childPosition];
			}
			
			@Override
			public long getChildId( int groupPosition, int childPosition) {
				return childPosition;
			}
			
			@Override 
			public int getChildrenCount( int groupPosition ) {
				return strCities[groupPosition].length;
			}
			
			@Override
			public View getChildView( int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {

				TextView tv = getTextView();
				tv.setText(strCities[groupPosition][childPosition]);
				
				return tv;
			}
			
			@Override
			public Object getGroup( int groupPosition ) {
				
				return strProvinces[groupPosition];
			}
			
			@Override
			public long getGroupId( int groupPosition ) {
				
				return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				
				return strProvinces.length;
			}
			
			@Override
			public View getGroupView( int groupPosition, boolean isExpanded, 
					View convertView, ViewGroup parent) {
				
				LinearLayout layout = new LinearLayout( SelectCityActivity.this );
				layout.setOrientation( 0 );
				
				ImageView mImageView = new ImageView( SelectCityActivity.this );
				layout.addView(mImageView);
				
				TextView tv = getTextView();
				tv.setText( strProvinces[groupPosition] );
				layout.addView(tv);
				
				return layout;
			}
			
			@Override
			public boolean isChildSelectable( int groupPosition, int childPosition ) {

				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				
				return true;
			}
		};
		
		// Applay the adapter
		setListAdapter( adapter );
		
		getExpandableListView().setOnChildClickListener( 
				new ExpandableListView.OnChildClickListener() {
					@Override
					public boolean onChildClick(ExpandableListView parent, 
							View source, int groupPosition, int childPosition, long id ) {
						
						Intent mIntent = getIntent();
						mIntent.putExtra( "city", strCities[groupPosition][childPosition] );
						
						// Set the result code
						SelectCityActivity.this.setResult( 0, mIntent );
						SelectCityActivity.this.finish();
						
						return false;
					}
				});
/*
 * 
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return false;
			}
*/
		
			
	}
}



