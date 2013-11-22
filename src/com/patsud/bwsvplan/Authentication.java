package com.patsud.bwsvplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentication extends Activity {

	Button anmelden, info;
	EditText number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentication);
		
		anmelden = (Button) findViewById(R.id.authAnmelden);
		info = (Button) findViewById(R.id.authInfo);
		number = (EditText) findViewById(R.id.authText);
		
		final Intent startVP = new Intent(this, VertretungsplanChooser.class);
		
		final DateToNum code = new DateToNum();
		code.CreateCode();
		//final String todayCode = code.GetCode();
		//Log.d("TodayCode", todayCode);
		
		anmelden.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String codeToday = "5";
				
				if (code.CheckCode(number.getText().toString())){
					// Toast tRichtig = Toast.makeText(getApplicationContext(), "Richtig!", Toast.LENGTH_SHORT);
					// tRichtig.setGravity(Gravity.CENTER, Gravity.CENTER, 70);
					// tRichtig.show();
					
					startActivity(startVP);
					finish();
				}
				else {
					// Toast tFalsch = Toast.makeText(getApplicationContext(), "Code ungültig", Toast.LENGTH_SHORT);
					Toast tFalsch = Toast.makeText(getApplicationContext(), "Unglültiger Code", Toast.LENGTH_SHORT);
					tFalsch.setGravity(Gravity.CENTER, Gravity.CENTER, 70);
					 tFalsch.show();
				}
			}
		});
		
		info.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog builder = new AlertDialog.Builder(Authentication.this)
		        .setTitle("Info")
		        .setMessage("Der Code ist heute " + code.GetCodeLenth() + " Zahlen lang.\n"+
		        		"Den Code für heute kannst du dir im Sekretariat abholen wenn dieser nicht mehr am schwarzen Brett aushängt.\n" +
		        		"Bei Problemen könnt ihr mir eine E-Mail an app@patsud.com schicken.")
		        .show();    
			}
		});
		
	}
}
