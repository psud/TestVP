package com.patsud.bwsvplan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class HtmlReader extends AsyncTask<String, Integer, String> {
	
	String url,name,password,data="";
	public String okayData;
	
	public String GetData(){
		return data;
	}
	
	public HtmlReader(String url, String name, String password)
	{
		
		super();
		Log.d("Debug", "7");
		this.url=url;
		this.name=name;
		this.password=password;
		Log.d("Debug", "8");
		
		//doInBackground(null);
		Log.d("Debug", "9");
	}
	
	public HtmlReader() {
		// TODO Auto-generated constructor stub
	}
	
	protected String doInBackground(String... credentials) {
		try
		{Log.d("Debug", "10");
			HttpPost httpost = new HttpPost(url);
			List<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("username", name));
				nv.add(new BasicNameValuePair("password", password));
			httpost.setEntity(new UrlEncodedFormEntity(nv, HTTP.UTF_8));
			HttpContext localContext = new BasicHttpContext();
			HttpResponse response = 			Login2.httpclient.execute(httpost,localContext);
			String line = "";
			StringBuilder total = new StringBuilder();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null)total.append(line);
			data = total.toString();
			Log.d("Debug", data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
protected void onPostExecute(Long result) 
	{
	okayData = data;
		Log.d("Ergebnis der Post-Request",data);
		//TODO Verarbeite das Ergebnis des Login
	}
}