package com.patsud.bwsvplan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

class DownloadFile extends AsyncTask<String, Integer, String> {
	
	private String dir = null;
	private String fileName = null;
	private ProgressDialog progressDialog = null;
	private MainActivity activity = null;
	
	private CookieStore cStore;
	
	/////
	public DownloadFile() {
		// TODO Auto-generated constructor stub
	}
	////
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFile() {
		return fileName;
	}

	public void setFile(String file) {
		this.fileName = file;
	}
	
	public DownloadFile(String Dir) {
		this.dir = Dir;
	}
	
	public DownloadFile(String Dir, ProgressDialog PDialog, MainActivity pActivity, CookieStore CStore) {
		this.dir = Dir;
		this.progressDialog = PDialog;
		this.activity = pActivity;

		this.cStore = CStore;
	}

	

	@Override
	protected String doInBackground(String... sUrl) {
		try {
			URL url = new URL(sUrl[0]);
			URLConnection connection = url.openConnection();
			
			HttpCookie cookie = null;
			if (cStore.getCookies().size() > 0) {
				Cookie c= cStore.getCookies().get(0);
				cookie = new HttpCookie("MoodleSession", c.getValue());
				cookie.setDomain(c.getDomain());
				connection.setRequestProperty("Cookie", cookie.toString().replace(";$", "; "));
				Log.i("cookie", cookie.toString().replace(";$", "; "));
			}
			
			connection.connect();
			// this will be useful so that you can show a typical 0-100% progress bar
			int fileLength = connection.getContentLength();
			
			// download the file
			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream(fileName);
			Log.i("dir", fileName);
			
			byte data[] = new byte[1024];
			long total = 0;
			int count;
			while ((count = input.read(data)) != -1) {
			    total += count;
			    // publishing the progress....
			    publishProgress((int) (total * 100 / fileLength));
			    output.write(data, 0, count);
			}
			
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {
			Log.e("dir", e.getMessage());
			e.printStackTrace();
		}
		
		progressDialog.dismiss();
		
		return null;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if (progressDialog != null) {
			progressDialog.show();
		}
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {
		super.onProgressUpdate(progress);
		
		if (progressDialog != null) {
			progressDialog.setProgress(progress[0]);
		}
	}
	
	@Override
	protected void onPostExecute (String result) {
		Log.i("downFile", "onPostExecute");
		
		File file = new File(fileName);
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.fromFile(file));
		
		try {
			activity.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(activity.getBaseContext(), "No viewer found", Toast.LENGTH_LONG).show();
		}
	}
}
