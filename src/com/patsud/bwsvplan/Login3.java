package com.patsud.bwsvplan;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login3 extends Activity {

	EditText userRaw, pasRaw;
	Button loginB;

	Request request;

	DownloadManager dm;

	NetworkRequest netR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Initialize();
		

		final String url = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";
		Log.d("DEBUG", "1");
		loginB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	Log.d("DEBUG", "2");
				ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("password", pasRaw.getText()
						.toString()));
				param.add(new BasicNameValuePair("username", userRaw.getText()
						.toString()));
				Log.d("DEBUG", "3");
				 netR = new NetworkRequest(url, param, null,
						null, new NetworkCallbackInterface() {

							@Override
							public void OnNetworkRequestFinished(
									CookieStore CStore, String Result) {
								
								// TODO Auto-generated method stub
								Log.d("DEBUG", "Finished Network Request");
								Log.d("DEBUG", CStore.toString());
								//Log.d("DEBUG", Result);
								
								//Download();
							}

							@Override
							public void OnException(Exception e) {
								// TODO Auto-generated method stub

							}
						});
				 netR.execute();
			
			CookieStore cookie = netR.GetCookie();
			Log.d("DEBUG", cookie.toString());
			
			
			}
		}); */
			}
		});
	}


	private void Download() {
		// TODO Auto-generated method stub
		dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		request = new Request(
				Uri.parse("http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/pluginfile.php/6677/mod_folder/content/0/Vertretungsplan_2013-11-21_11-28.pdf?forcedownload=1"));
		dm.enqueue(request);

		Intent i = new Intent();
		i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
		startActivity(i);
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		userRaw = (EditText) findViewById(R.id.loginUser);
		pasRaw = (EditText) findViewById(R.id.loginPas);
		loginB = (Button) findViewById(R.id.loginGo);

		userRaw.setText("vplanapp");
		pasRaw.setText("bwsplan");
	}
}
