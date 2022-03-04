package com.example.vkice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewParticipant1 extends Activity implements OnClickListener {
	
	TextView v1;
	SharedPreferences sn;
	
	Button b1;
	Spinner sp1;
	ArrayAdapter<String> adapter;
	SharedPreferences sp,sc;
	private String IP, web_url, data[] = {"BCA","BSC CS","BSC IT","ALL"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewparticipant1);
		v1 = (TextView) findViewById(R.id.textView1);
		b1 = (Button) findViewById(R.id.button1);
		sp1 = (Spinner) findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter);
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/viewparticipant.php";		
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i1 = new Intent(this, ViewParticipant2.class);
		i1.putExtra("dept", sp1.getSelectedItem().toString().trim());
		startActivity(i1);
	}
}