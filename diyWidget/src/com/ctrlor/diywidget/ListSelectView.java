package com.ctrlor.diywidget;

import com.ctrlor.diywidget.R;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListSelectView extends LinearLayout implements OnClickListener {
	public static final String TAG = "ListSelectView";

	private TextView tvHeader;
	private TextView tvContent;

	private CharSequence[] mEntries;
	private CharSequence[] mEntryValues;
	private int mClickedDialogEntryIndex;

	public ListSelectView(Context context) {
		this(context, null);

	}

	public ListSelectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.list_select_layout, this, true);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ListSelectView, 0, 0);
		mEntries = a.getTextArray(R.styleable.ListSelectView_entries);
		mEntryValues = a.getTextArray(R.styleable.ListSelectView_entryValues);
		a.recycle();

		tvHeader = (TextView) findViewById(R.id.tvListSelectLayoutTitle);
		tvContent = (TextView) findViewById(R.id.tvListSelectLayoutContent);
		tvHeader.setTextColor(Color.BLACK);
		tvContent.setGravity(Gravity.TOP);
		this.setOnClickListener(this);
	}

	public ListSelectView setHeader(String sHeader) {
		tvHeader.setText(sHeader);
		return this;
	}

	public ListSelectView setContent(String sContent) {
		tvContent.setText(sContent);
		return this;
	}

	public void setEntries(CharSequence[] entries) {
		mEntries = entries;
	}

	public void setEntries(int entriesResId) {
		setEntries(getContext().getResources().getTextArray(entriesResId));
	}

	public CharSequence[] getEntries() {
		return mEntries;
	}

	public void setEntriesValues(CharSequence[] entryValues) {
		mEntries = entryValues;
	}

	public void setEntriesValues(int entriyValuesResId) {
		setEntriesValues(getContext().getResources().getTextArray(entriyValuesResId));
	}

	public CharSequence[] getEntryValues() {
		return mEntryValues;
	}

	public void setEntryIndex(int iIndex) {
		this.mClickedDialogEntryIndex = iIndex;
	}

	@Override
	public final void onClick(View v) {

		SingleSelectionDialog dialog = new SingleSelectionDialog.Builder(this.getContext()).setTitle(tvHeader.getText()).setSingleChoiceItems(mEntries,
				mClickedDialogEntryIndex, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mClickedDialogEntryIndex = which;
						tvContent.setText(mEntryValues[mClickedDialogEntryIndex]);
						dialog.dismiss();
					}

				}).create();

		dialog.show();

		// AlertDialog dlg = new AlertDialog.Builder(new
		// ContextThemeWrapper(this.getContext(),
		// R.style.Theme_Dialog_ListSelect))
		// //.setCustomTitle(arg0)
		// .setSingleChoiceItems(mEntries, mClickedDialogEntryIndex,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int which) {
		// mClickedDialogEntryIndex = which;
		// tvContent.setText(mEntryValues[mClickedDialogEntryIndex]);
		// dialog.dismiss();
		// }
		// }).setTitle(tvHeader.getText()).create();
		//
		// dlg.show();

	}

}
