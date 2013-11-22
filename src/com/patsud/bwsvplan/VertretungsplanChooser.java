package com.patsud.bwsvplan;

import java.io.File;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VertretungsplanChooser extends Activity implements OnClickListener{
	
	Button neuerPlan, alterPlan, klausurPlan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vertretungsplanchooser);
		
		Initialize();
		
	
		
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		neuerPlan = (Button) findViewById(R.id.chooserVpNew);
		alterPlan = (Button) findViewById(R.id.chooserVpOld);
		klausurPlan = (Button) findViewById(R.id.chooserVpKlausurPlan);

		neuerPlan.setOnClickListener(this);
		alterPlan.setOnClickListener(this);
		klausurPlan.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.chooserVpNew:
			
			open("/sdcard/Download/Vertretungsplan_2013-11-19_11-26 (1).pdf");
			//Download();
			break;
		case R.id.chooserVpOld:
			open("/sdcard/Download/Vertretungsplan_2013-11-18_11-25 (1).pdf");
			break;
		case R.id.chooserVpKlausurPlan:
			open("/sdcard/Download/klausurplan2013.pdf");
			break;
		}
	}

	private void Loading() {
		// TODO Auto-generated method stub
		ProgressDialog dialog = ProgressDialog.show(this, "Loading", "");
		open("/sdcard/Download/Vertretungsplan_2013-11-15_11-22.pdf");
	}
	
	
	private void Download() {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/qbayoe0cdrp95vo/Vertretungsplan_2013-11-19_11-26%20%281%29.pdf")));
			//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/giqs6a22p5gixjy/klausurplan2013.pdf")));
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/nejudcynar4ou8c/Vertretungsplan_2013-11-18_11-25%20%281%29.pdf")));
	
			//https://dl.dropboxusercontent.com/s/nejudcynar4ou8c/Vertretungsplan_2013-11-18_11-25%20%281%29.pdf?dl=1&token_hash=AAG6WqYJpkHJuVcZ1YbDD_FvlF0BUjU6D8KaLnpg3fxQFA}
	
	}
	protected void open(String fileStr) {
		// TODO Auto-generated method stub
		Log.d("DEBUG", "Got to 1");
		File file = new File(
				fileStr);
		Log.d("DEBUG", "Got to 2");
		if (file.exists()) {
			Log.d("DEBUG", "Got to 3");
			Uri path = Uri.fromFile(file);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(path, "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			try {
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(VertretungsplanChooser.this,
						"No Application Available to View PDF",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
