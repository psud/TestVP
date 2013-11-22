package com.patsud.bwsvplan;

import java.io.File;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class OpenFile extends Application {

	
	protected void open(String fileLocation) {
		// TODO Auto-generated method stub
		Log.d("DEBUG", "Got to 1");
		File file = new File(
				"/sdcard/Download/Vertretungsplan_2013-11-15_11-22.pdf");
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
				Toast.makeText(OpenFile.this,
						"No Application Available to View PDF",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
