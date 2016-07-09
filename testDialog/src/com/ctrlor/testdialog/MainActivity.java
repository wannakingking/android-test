package com.ctrlor.testdialog;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	// define four button object
//	private static final int DIALOG1 = 1;
//	private static final int DIALOG2 = 2;
//	private static final int DIALOG3 = 3;
//	private static final int DIALOG4 = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);
		Button button6 = (Button) findViewById(R.id.button6);
		Button button7 = (Button) findViewById(R.id.button7);
		
		button1.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_TwoButtons();
			}
		});

		button2.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_ThreeButtons();
			}
		});

		button3.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_InputView();
			}
		});

		button4.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_RadioButton();
			}
		});

		button5.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_CheckBox();
			}
		});

		button6.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_ListView();
			}
		});

		button7.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog_DiyLayout();
			}
		});

	}
	
	// 1, show two buttons dialog
	private void showDialog_TwoButtons() {
		
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("Want to exit?");
		builder.setTitle("notice");

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MainActivity.this.finish();
			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		builder.create().show();

	}

	// 2, show three buttons dialog
	private void showDialog_ThreeButtons() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.btn_star);
		builder.setTitle("check number");

		// set cancel button
		builder.setNegativeButton("1", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "you have choice the number 1",
						Toast.LENGTH_LONG).show();
			}
		});

		// set neutral button
		builder.setNeutralButton("2", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "you have choice the number 2", 
						Toast.LENGTH_LONG).show();
				
			}
		});

		// set ok button
		builder.setPositiveButton("3", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "you have choice the number 3", 
						Toast.LENGTH_LONG).show();
				
			}
		});
		
		
		builder.create().show();
	}

	// 3, show input view dialog
	private void showDialog_InputView() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("input view");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setView(new EditText(this));
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("OK", null);
		
		builder.create().show();
		
		
	}

	// 4, show radio button dialog
	private void showDialog_RadioButton() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Single choice");
		builder.setSingleChoiceItems(
				new String[] {"1", "2", "3"}, 0, null);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("OK", null);
		
		builder.create().show();
	}

	// 5, show checkbox dialog
	private void showDialog_CheckBox() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("multi choice");
		builder.setMultiChoiceItems(
				new String[] {"1", "2", "3"}, null, null);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("OK", null);
		builder.create().show();
	}

	// 6, show list view dialog
	private void showDialog_ListView() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("list view");
		builder.setItems(
				new String[] {"1", "2", "3"}, null);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("OK", null);
		
		builder.create().show();

	}

	// 7, show diy layout dialog
	private void showDialog_DiyLayout() {

		LayoutInflater inflater = LayoutInflater.from(this);
		final View textEntryView = inflater.inflate(
				R.layout.alert_dialog_input_entry,
				(ViewGroup) findViewById(R.id.diy_layout_dialog));

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setView(textEntryView);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("OK", null);
		
		builder.create().show();
	}

}
