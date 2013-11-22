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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LeonLogin extends Activity{

	
	EditText userRaw, pasRaw;
	String username, password;
	Button loginB;
	
	ProgressDialog pDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		Initialize();
		
		
		loginB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", "1");
				username = userRaw.getText().toString();
				password = pasRaw.getText().toString();
			//	postData();
				Data2();
			}
		});
	
	}
	
	
	 
	protected  void Data2() {
		// TODO Auto-generated method stub
		HttpClient client = null;
		client =  new DefaultHttpClient();
        String responseStr="";
        String URL= "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/login/index.php";
        HttpPost post = new HttpPost("http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/login/index.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            //nameValuePairs.add(new BasicNameValuePair("id", pick_up_id));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            nameValuePairs.add(new BasicNameValuePair("rememberusername", "1"));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            Log.d("DEBUG", "before");
            if (client!=null){
            	Log.d("DEBUG", "is ungleich null");
            	Log.d("DEBUG", "Client: " + client.toString());
            }
            HttpResponse response = client.execute(post);
            Log.d("DEBUG", "after");            

            int responseCode = response.getStatusLine().getStatusCode();
            switch(responseCode) {
            case 200:
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    responseStr=responseBody;
                }
                break;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } //catch (all)
	}



	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/index.php");
	    Log.d("DEBUG", "2");
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        Log.d("DEBUG", "3");
	        nameValuePairs.add(new BasicNameValuePair("username", userRaw.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("password", pasRaw.getText().toString()));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        Log.d("DEBUG", "4");

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        Log.d("DEBUG", "5");
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
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
