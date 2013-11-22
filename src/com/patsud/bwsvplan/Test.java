package com.patsud.bwsvplan;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Test extends Activity {

	WebView webView;
	Button b;

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private Button startBtn;
	private ProgressDialog mProgressDialog;

	final String url2 = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/pluginfile.php/6677/mod_folder/content/0/Vertretungsplan_2013-11-21_11-28.pdf";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		b = (Button) findViewById(R.id.button1);
		// final String url = "http://joystudios.bplaced.net/moodle/login3.php";
		Log.d("DEBUG", "Got here 1");
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				webView = (WebView) findViewById(R.id.webView1);
				webView.getSettings().setJavaScriptEnabled(true);
				webView.loadUrl("http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2");
				Log.d("DEBUG", "Got here 2");
			/*	webView.setWebViewClient(new WebViewClient() {
					public boolean shouldOverrideUrlLoading(WebView view,
							String url2) {
						Log.d("DEBUG", "Got here 3");
						if (url2.endsWith(".pdf")) {
							// startActivity(new Intent(Intent.ACTION_VIEW, Uri
							// .parse(url2)));
							// Log.d("DEBUG", "Got here 4");
							startDownload();

							return true;
						}
						return false;

					}

				});
*/
			}
		});

	}

	private void startDownload() {
		String url = url2;
		new DownloadFileAsync().execute(url);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Downloading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}

	class DownloadFileAsync extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}

		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {

				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();

				int lenghtOfFile = conexion.getContentLength();
				Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(
						"/sdcard/mynewpdf.pdf");

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
		}
	}
}
