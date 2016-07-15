package com.ctrlor.diywidget;

import com.ctrlor.diywidget.R;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SingleSelectionDialog extends Dialog {

	public SingleSelectionDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);

	}

	public SingleSelectionDialog(Context context, int theme) {
		super(context, theme);
	}

	public SingleSelectionDialog(Context context) {
		super(context);
	}

	public static class Builder {

		private Context context;
		private CharSequence title;
		private CharSequence[] mListItem;
		private int mClickedDialogEntryIndex;
		private DialogInterface.OnClickListener mOnClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(CharSequence title) {
			this.title = title;
			return this;
		}

		public CharSequence[] getItems() {
			return mListItem;
		}

		public Builder setItems(CharSequence[] mListItem) {
			this.mListItem = mListItem;
			return this;
		}
		
		
		public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
			this.mListItem = items;
            this.mOnClickListener = listener;
            this.mClickedDialogEntryIndex = checkedItem;
            return this;
        } 

	
		public SingleSelectionDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final SingleSelectionDialog dialog = new SingleSelectionDialog(
					context, R.style.Theme_Dialog_ListSelect);
			View layout = inflater.inflate(R.layout.single_selection_dialog,
					null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			AlertDialog ad;
			
			if(mListItem == null){
				throw new RuntimeException("Entries should not be empty");
			}
			ListView lvListItem = (ListView) layout.findViewById(R.id.lvListItem);
//			android.R.layout.simple_list_item_single_choice
			//lvListItem.setAdapter(new ArrayAdapter(context, android.R.layout.simple_list_item_single_choice, android.R.id.text1, mListItem));
//			SingleSelectionAdapter mSingleSelectionAdapter = new SingleSelectionAdapter(context, R.layout.single_list_item, R.id.ctvListItem, mListItem);
//			lvListItem.setAdapter(mSingleSelectionAdapter);
			lvListItem.setAdapter(new ArrayAdapter(context,  R.layout.single_selection_list_item, R.id.ctvListItem, mListItem));
			lvListItem.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					mOnClickListener.onClick(dialog, position);

				}
				
			});
			lvListItem.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			lvListItem.setItemChecked(mClickedDialogEntryIndex, true);
			lvListItem.setSelection(mClickedDialogEntryIndex);
//			mSingleSelectionAdapter.setSelection(mClickedDialogEntryIndex);
			
			
			

			TextView tvHeader = (TextView)layout.findViewById(R.id.title);
			tvHeader.setText(title);
			//dialog.setTitle(title);

			
			
			return dialog;
		}
		
		
		
	}
	
	

}
