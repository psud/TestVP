package com.patsud.bwsvplan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

Button b;
TextView tv;
WebView web;
 String url = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    b =(Button) findViewById(R.id.button1);
	    tv=(TextView) findViewById(R.id.textView1);
	    web = (WebView) findViewById(R.id.webView1);
	    
	    b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		//	web.loadUrl(url);
			//	String data = "username=vplanapp&password=bwsplan";
			//web.postUrl(URL_STRING, data.getBytes());
			try {
				DoThing();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	}
	
	protected void DoThing() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vplanapp"));
		nvps.add(new BasicNameValuePair("password", "bwsplan"));
		nvps.add(new BasicNameValuePair("rememberusername", "on"));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

	//	HttpResponse response = httpclient.execute(httpost);
		//HttpEntity entity = response.getEntity();

		//if (entity != null) {
		 // entity.consumeContent();
	//	}

		//List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		//String out = cookies.toString();
		//tv.setText(out);
	}

	
}