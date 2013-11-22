package com.patsud.bwsvplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceClickListener;
import android.view.WindowManager;


public class Einstellungen extends PreferenceActivity {

	final Context context = this;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.einstellungen);

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());



		PreferenceScreen devDownload = (PreferenceScreen) findPreference("devDownload");
		devDownload.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub
				//Klausurplan
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/giqs6a22p5gixjy/klausurplan2013.pdf?dl=1&token_hash=AAEp1DluvWp7Z5eLHOFfqcfdoH2kkHiQFeorx2_73A0Wig")));
				//Neu
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/d0fol8d93r63360/Vertretungsplan_Neu.pdf?dl=1&token_hash=AAGMP04nx-kGqvw_NZ7w6ghdN3FzqaVEow4NykRUsO3R4A")));
				//Alt
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/s/5lxphwa71hov6og/Vertretungsplan_Alt.pdf?dl=1&token_hash=AAFNqLyyIMIOhY-wdqU6e7YXmtQYa4rov1H9-WieaHjUdQ")));

				return false;
			}
		});

	}
}