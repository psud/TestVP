package com.patsud.bwsvplan;

import java.io.File;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VertretungsplanChooser extends Activity implements OnClickListener{
	
	Button neuerPlan, alterPlan, klausurPlan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vertretungsplanchooser);
		
		Initialize();
		
	
		
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		neuerPlan = (Button) findViewById(R.id.chooserVpNew);
		alterPlan = (Button) findViewById(R.id.chooserVpOld);
		klausurPlan = (Button) findViewById(R.id.chooserVpKlausurPlan);

		neuerPlan.setOnClickListener(this);
		alterPlan.setOnClickListener(this);
		klausurPlan.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.chooserVpNew:
			open("/sdcard/Download/Vertretungsplan_Neu.pdf");
			//Download();
			break;
		case R.id.chooserVpOld:
			open("/sdcard/Download/Vertretungsplan_Alt.pdf");
			break;
		case R.id.chooserVpKlausurPlan:
			open("/sdcard/Download/klausurplan2013.pdf");
			break;
		}
	}


	
	
	
	protected void open(String fileStr) {
		// TODO Auto-generated method stub
		Log.d("DEBUG", "Got to 1");
		File file = new File(
				fileStr);
		Log.d("DEBUG", "Got to 2");
		if (file.exists()) {
			Log.d("DEBUG", "Got to 3");
			Uri path = Uri.fromFile(file);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(path, "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			try {
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(VertretungsplanChooser.this,
						"No Application Available to View PDF",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menuAbout:
	        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	        	alertDialog.setTitle("Über Uns");
				alertDialog.setMessage("Diese App wurde entwickelt, um das Anzeigen " +
						"des Vertretungs- und Klausurenplan " +
						"der BWS zu erleichtern. " +
						"\n" +
						"Für die Anzeige beider Pläne wird ein PDF-Reader benötigt." +
						"\n\n" +
						"Entwickler-Team:\n" +
						"Patrick Sudhaus\n" +
						"Elias Herrmann\n" +
						"Philipp Claßen\n" +
						"Felix Krauspe\n" +
						"Marcel Hermann\n" +
						"Jonas Hohmann\n" +
						"Markus Kalusche\n" +
						"Stephan Schiefer\n" +
						"Sebastian Kern" +
						"\n\n" +
						"Special Thanks: " +
						"\n" +
						"Nick Godzieba" +
						"\n\n" +
						"Bei Problemen oder Fragen könnt ihr mir eine Mail an app@patsud.com schicken." +
						"");

				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {	}
				});
				alertDialog.show();
	            break;
	        case R.id.menuEinstellungen:
	        	Intent openPrefs = new Intent(this, Einstellungen.class);
				startActivity(openPrefs);

	            break;
	        case R.id.menuFeedback:
	        	Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String emailAdress[]={"app@patsud.com"};
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAdress);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback BWS VPlan");
                emailIntent.setType("plain/text");
                startActivity(emailIntent);
	        	break;
	        
	       // default:
	         //   return super.onOptionsItemSelected(item);
	    }
		return false;
	}
}
