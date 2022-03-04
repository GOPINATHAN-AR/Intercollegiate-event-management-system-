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
import android.widget.TextView;
import android.widget.Toast;

public class UserHome0 extends Activity implements OnClickListener {

	TextView v1;
	SharedPreferences sn;
	
	Button b1,b2,b3,b4,b5,b6,b7,b8;
	ArrayAdapter<String> adapter;
	SharedPreferences sp,sc;
	private String IP, web_url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userhome0);
		v1 = (TextView) findViewById(R.id.textView1);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		b5 = (Button) findViewById(R.id.button5);
		b6 = (Button) findViewById(R.id.button6);
		b7 = (Button) findViewById(R.id.button7);
		b8 = (Button) findViewById(R.id.button8);
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/a.php";
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
	}
	
	@Override
	protected void onDestroy() {	
		super.onDestroy();
		sn.edit().remove("userid").commit();
		sn.edit().remove("uname").commit();
	}

	@Override
	public void onClick(View v) {		
		switch(v.getId()) {
		case R.id.button1:
			startActivity(new Intent(this, AboutCollege.class));
			break;
		case R.id.button2:
			startActivity(new Intent(this, AboutDept.class));
			break;
		case R.id.button3:
			startActivity(new Intent(this, AddParticipant1.class));
			break;
		case R.id.button4:
			startActivity(new Intent(this, ViewParticipant1.class));
			break;
		case R.id.button5:
			startActivity(new Intent(this, ViewPrelims.class));
			break;
		case R.id.button6:
			startActivity(new Intent(this, ViewMains.class));
			break;
		case R.id.button7:
			startActivity(new Intent(this, Feedback.class));
			break;
		case R.id.button8:
			startActivity(new Intent(this, Rules.class));
			break;
		}		
	}
			
	public void showMsg(String s) {		
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}