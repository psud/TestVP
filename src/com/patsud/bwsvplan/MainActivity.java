package com.patsud.bwsvplan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b, b2;
	TextView tv;
	String urlStr = "https://dl.dropboxusercontent.com/s/1ahp7sz9bg5g1ju/Vertretungsplan_2013-11-15_11-22.pdf";

	// String url =
	// "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b = (Button) findViewById(R.id.chooserVpNew);
		b2 = (Button) findViewById(R.id.button2);
		tv = (TextView) findViewById(R.id.loginTv);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				download();
				// open();
			}
		});

		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				open();

			}
		});
	}

	protected void open() {
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
				Toast.makeText(MainActivity.this,
						"No Application Available to View PDF",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	protected void download() {
		// TODO Auto-generated method stub
		// String fileName = "testpdf.pdf";
		/*
		 * String MY_URL =
		 * "https://dl.dropboxusercontent.com/s/1ahp7sz9bg5g1ju/Vertretungsplan_2013-11-15_11-22.pdf"
		 * ; startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MY_URL)));
		 */String fileName = "thenewvp.pdf";
		 Log.d("DEBUG", "GOt to 1");
		try {
			Log.d("DEBUG", "GOt to 2");
			URL url = new URL(urlStr);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();
			Log.d("DEBUG", "GOt to 3");

			String PATH = Environment.getExternalStorageDirectory()
					+ "/download/";
			Log.d("Abhan", "PATH: " + PATH);
			File file = new File(PATH);
			if (!file.exists()) {
				file.mkdirs();
			}
			Log.d("DEBUG", "GOt to 4");
			File outputFile = new File(file, fileName);
			FileOutputStream fos = new FileOutputStream(outputFile);
			InputStream is = c.getInputStream();
			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len1);
			}
			Log.d("DEBUG", "GOt to 5");
			fos.flush();
			fos.close();
			is.close();
			Log.d("DEBUG", "GOt to 6");
		} catch (IOException e) {
			Log.e("Abhan", "Error: " + e);
		}
		Log.i("Abhan", "Check Your File.");
	}
}
// Open