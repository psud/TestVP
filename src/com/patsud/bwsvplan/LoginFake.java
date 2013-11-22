package com.patsud.bwsvplan;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoginFake extends Activity {

	Button showCred, bLogin, bInfo;
	EditText username, password;
	LinearLayout credLayout;
	ImageView background;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Hide action bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_sleek);

		Initialize();
		final CheckCredentials checkCred = new CheckCredentials();
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		final Intent openCircle = new Intent(this, VertretungsplanChooser.class);
		

		showCred.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowLoginCreds();
			}
		});

		bLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				
				boolean correctCreds = checkCred.CheckCredentials(username.getText()
						.toString(), password.getText().toString());

				String cookie;
				if (correctCreds){
					startActivity(openCircle);
					overridePendingTransition(R.anim.animtransitionout, R.anim.animtransitionup);
			}
					
				//	Download(cookie);
				//if (correctCreds) {
				//if (correctCreds){	
				//startActivity(openCircle);
					//overridePendingTransition(R.anim.animtransitionout, R.anim.animtransitionup);
				//}
			}
		});
		
		
		bInfo.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				alertDialog.setTitle("Info");
				alertDialog.setMessage("Um Zugang zu Vertretungs- und Klausurenplan der BWS zu erhalten, " +
						"gib hier deine Moodle Login-Daten (Username, Password) ein." +
						"\n\n" +
						"Der Start des Programmes kann einige Sekunden dauern, " +
						"da die PDF-Dateien direkt runtergeladen werden.");

				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
				});
				//alertDialog.setIcon(R.drawable.vplogo2);
				alertDialog.show();
			}
		});

	}

	

	protected void ShowLoginCreds() {
		// TODO Auto-generated method stub
		Animation animFadeOut = AnimationUtils.loadAnimation(this,
				R.anim.animfadeout);
		showCred.startAnimation(animFadeOut);
		showCred.setVisibility(View.GONE);
		Animation animBackgroundUp = AnimationUtils.loadAnimation(this,
				R.anim.animbackgroundup);
		animBackgroundUp.setFillAfter(true);
		background.startAnimation(animBackgroundUp);
		Animation animCredsUp = AnimationUtils.loadAnimation(this,
				R.anim.animcredstoup);
		credLayout.setVisibility(View.VISIBLE);
		credLayout.startAnimation(animCredsUp);

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		showCred = (Button) findViewById(R.id.loginShowLogin);
		bLogin = (Button) findViewById(R.id.loginCredentialsLogin);
		bInfo = (Button) findViewById(R.id.loginCredentialsInfo);
		username = (EditText) findViewById(R.id.loginCredentialsUsername);
		password = (EditText) findViewById(R.id.loginCredentialsPassword);
		credLayout = (LinearLayout) findViewById(R.id.loginCredentialsLayout);
		background = (ImageView) findViewById(R.id.loginBackground);
		
		username.setText("vplanapp");
		password.setText("bwsplan");
		
	}

	@Override
	public void onBackPressed() {
		if (credLayout.isShown()) {
			Log.d("DEBUG", "showing");
			Animation animFadeIn = AnimationUtils.loadAnimation(this,
					R.anim.animfadein);
			showCred.setVisibility(View.VISIBLE);
			showCred.startAnimation(animFadeIn);
			Animation animBackgroundDown = AnimationUtils.loadAnimation(this,
					R.anim.animbackgrounddown);
			animBackgroundDown.setFillAfter(true);
			background.startAnimation(animBackgroundDown);
			Animation animCredsDown = AnimationUtils.loadAnimation(this,
					R.anim.animcredstodown);
			credLayout.setVisibility(View.INVISIBLE);
			credLayout.startAnimation(animCredsDown);
		} else
			super.onBackPressed();
	}

}
