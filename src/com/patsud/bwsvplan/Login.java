package com.patsud.bwsvplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.ivoid.helpers.Base64;

public class Login extends Activity{
	
/*	EditText userRaw, pasRaw;
	Button loginB;
	
	ProgressDialog pDialog;
	
	private JSONObject json = null;
	private Globals app;
	private String loginResult;
	private Map<String, String> creds;
	private boolean saveCredentials;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		app = ((Globals)getApplicationContext());
		
		setContentView(R.layout.login);
		Log.d("DEBUG", "Activity Started");
		Log.d("DEBUG", "1");
		Initialize();
		Log.d("DEBUG", "2");
		
		if(true){
			SetupLogin();
		}
		else
			;
		
		
	}

	private void SetupLogin() {
		// TODO Auto-generated method stub
		
		
		
		loginB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String username = userRaw.getText().toString().trim();
				String password = pasRaw.getText().toString().trim();
				String serverAdress = "http://bws.mtk.hessencampus.studiumdigitale.uni-frankfurt.de/moodle2/";

				Log.d("DEBUG", "1");
				if (URLUtil.isValidUrl(serverAdress)){
					Map<String, String> mycreds = new HashMap<String, String>();
					mycreds.put("username", username);
			        mycreds.put("password", password);
			        mycreds.put("url", serverAdress);
			        creds = mycreds;
					Log.d("DEBUG", "2");
					saveCredentials = true;
					
					tryLogin();
				}
				else
					Toast.makeText(getApplicationContext(), "Falsche URL", Toast.LENGTH_LONG).show();
			
			
			
			}
		});
		
	//	Map<String, String> myCreds = new HashMap<String, String>
		
		
	}

	protected void tryLogin() {
		// TODO Auto-generated method stub
		Log.d("DEBUG", "3");
		if (!connectedToInternet()){
			Log.d("DEBUG", "No Internet connection");
			Toast.makeText(getApplicationContext(), "Keine Internet Verbindung", Toast.LENGTH_LONG).show();
			goHome();
			}
		else{
			//Toast.makeText(getApplicationContext(), "All good", Toast.LENGTH_LONG).show();
			Log.d("DEBUG", "Got Internet connection");
			
			pDialog = ProgressDialog.show(this, "Einloggen", "Einloggen",true);
			
			new Thread(new Runnable(){
				@Override
				public void run()
				{		
					Base64 cryptor = new Base64();
					
					fetchJSON(
								cryptor.encode(creds.get("username")), 
								cryptor.encode(creds.get("password")), 
								creds.get("url")
							 );
					
				//	HttpResponse response = app.httphelper.getHttpResponse();
					
					if (response == null){
						loginResult = "Server unavailable, try again later.";
					Log.d("DEBUG", "Server unavailable, try again later.");
					}
					else
					{
				//		app.httphelper.setHeader(response.getFirstHeader("Cookie")); 
						try
						{
							json=new JSONObject(EntityUtils.toString(response.getEntity()));
							Log.d("DEBUG", EntityUtils.toString(response.getEntity()));
						} catch (Exception e){
							if (saveCredentials)
								loginResult = "Invalid username or password.";
							else
								loginResult = "Server error, try again later.";
						}
					}
					progressHandler.sendEmptyMessage(0);	
				}
			}).start();
			
		}
		
	}

	private void goHome() {
		// TODO Auto-generated method stub
		
	}
	
	private void fetchJSON(String user, String pass, String url)
	{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", user));
		nameValuePairs.add(new BasicNameValuePair("password", pass));
		nameValuePairs.add(new BasicNameValuePair("url", url));
		try {
		//	app.httphelper.post("getCourses", nameValuePairs);
		} catch (Exception e){}
	}

	private boolean connectedToInternet() {
		// TODO Auto-generated method stub
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null)
			return false;
		
		return ni.isConnected();
	}
	
	private final Handler progressHandler = new Handler()
	{
		@Override
		public void handleMessage(android.os.Message msg) 
		{			
			pDialog.dismiss();
			goHome();
		}
    };

	private void Initialize() {
		// TODO Auto-generated method stub
		userRaw = (EditText) findViewById(R.id.loginUser);
		pasRaw = (EditText) findViewById(R.id.loginPas);
		loginB = (Button) findViewById(R.id.loginGo);
		
		userRaw.setText("vplanapp");
		pasRaw.setText("bwsplan");
	}
*/
}
