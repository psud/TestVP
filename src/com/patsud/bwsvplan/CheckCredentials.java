package com.patsud.bwsvplan;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.message.BasicNameValuePair;

import android.app.DownloadManager.Request;
import android.net.Uri;
import android.util.Log;

public class CheckCredentials {

	NetworkRequest netR;
	String url = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";
	final DownloadFile dFile = new DownloadFile();
	
	public boolean CheckCredentials(String username, String password) {

		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("password", password.toString()));
		param.add(new BasicNameValuePair("username", username.toString()));
		Log.d("DEBUG", "3");
		netR = new NetworkRequest(url, param,
				new NetworkCallbackInterface() {

					@Override
					public void OnNetworkRequestFinished(CookieStore CStore,
							String Result) {

						
						// TODO Auto-generated method stub
						//Log.d("DEBUG", "Finished Network Request");
						//Log.d("DEBUG", CStore.toString());
						 Log.d("DEBUG", Result);

						 
						 
						 CookieStore cookie = netR.getcStore();
						 String cookieShort = cookie.toString().substring(42, 65);
						 Log.d("total cookie", cookie.toString());
						 Log.d("cookie", cookieShort);
							
						 if (true){
							 Download(cookie);
						 }
						
						}

					@Override
					public void OnException(Exception e) {
						// TODO Auto-generated method stub

					}
				});
		netR.execute();

		return true;

	}


	protected void Download(CookieStore cookie) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String dir = "/sdcard/Download/newVPlan";
			
			DownloadFile df;
			
			//dFile.DownloadFile(dir, null, this, cookie);
			
	}

}
