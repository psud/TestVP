package com.patsud.bwsvplan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login2 extends Activity{
	
	EditText userRaw, pasRaw;
	Button loginB;
	TextView tv;
	
	static DefaultHttpClient httpclient;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		Log.d("Debug", "1");
		Initialize();
		Log.d("Debug", "2");
		loginB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Debug", "3");
				String username = userRaw.getText().toString().trim();
				String password = pasRaw.getText().toString().trim();
				String serverAdress = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";
				login(serverAdress, username, password);
			
				
				HtmlReader testBla = new HtmlReader();
				String bla = testBla.okayData;
				
				tv.setText(bla);
			}
		});
	
	}
	
	public void login(String server, String name, String password)
	{
		Log.d("Debug", "4");
		connect();
		
		new HtmlReader(server,name,password).execute();
		Log.d("Debug", "11");
		
	}
	
	private void connect()
	{
		HttpParams httpParams = new BasicHttpParams();
		SchemeRegistry schreg = new SchemeRegistry();
		int timeoutConnection = 30000;
		Log.d("Debug", "5");
		HttpConnectionParams.setSoTimeout(httpParams, timeoutConnection);
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(httpParams,schreg);		
		httpclient = new DefaultHttpClient(cm,httpParams);
		Log.d("Debug", "HTTP: " + httpclient.toString());
	}
	
	private void Initialize() {
		// TODO Auto-generated method stub
		userRaw = (EditText) findViewById(R.id.loginUser);
		pasRaw = (EditText) findViewById(R.id.loginPas);
		loginB = (Button) findViewById(R.id.loginGo);
		tv = (TextView) findViewById(R.id.loginTv);
		
		userRaw.setText("vplanapp");
		pasRaw.setText("bwsplan");
	}

}
