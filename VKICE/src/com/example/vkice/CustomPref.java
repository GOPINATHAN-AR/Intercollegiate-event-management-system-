package com.example.vkice;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class CustomPref extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.custom_pref);
	}
}
